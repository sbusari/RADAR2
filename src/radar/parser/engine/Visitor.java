package radar.parser.engine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.antlr.v4.runtime.tree.TerminalNode;

import prefuse.data.Node;
import radar.model.Decision;
import radar.model.Model;
import radar.model.ModelConstructor;
import radar.model.OR_Refinement;
import radar.model.Objective;
import radar.model.QualityVariable;
import radar.model.Value;
import radar.parser.generated.ModelBaseVisitor;
import radar.parser.generated.ModelParser;
import radar.parser.generated.ModelParser.Model_elementContext;

public class Visitor extends ModelBaseVisitor<Value> {
	
	String operator = "\\+|\\-|\\*|\\/|\\^|\\%";
	boolean argumentHasExpr;
	String computedArgumentExpression;
	Model semanticModel;
	Map<String, QualityVariable> qv_list;
	//stores objective definition
	Map<String, Value> obj_definitions;
	Map<String, Objective> obj_list;
	Map<String, Decision> decision_list;
	int simulationRun;
	String infoValueObjectiveName;
	ModelConstructor modelConstructor;
	public Visitor(int simulation, String infoValueObjName ){
		simulationRun =simulation;
		infoValueObjectiveName= infoValueObjName;
	}
	public Model getSemanticModel() {
		return semanticModel;
	}
	@Override 
	public Value visitModel(ModelParser.ModelContext ctx) {
		modelConstructor = new ModelConstructor (simulationRun);
		qv_list = new LinkedHashMap<String,QualityVariable > ();
		obj_list = new LinkedHashMap<String,Objective > ();
		decision_list = new LinkedHashMap<String,Decision>();
		obj_definitions = new LinkedHashMap<String, Value> ();
		semanticModel = modelConstructor.createNewModel();
		if (ctx.model_element() != null && ctx.model_element().size() > 0 ){
			for (Model_elementContext modelElementContext : ctx.model_element()){
				visit (modelElementContext);
			}
		}else{
			throw new RuntimeException ("Model cannot be empty.");
		}
		
		
		// need objective definition to get the quality variable an objective refers to
		
		modelConstructor.addObjectivesToModel(semanticModel, obj_definitions, obj_list,qv_list, infoValueObjectiveName );
		modelConstructor.addQualityVariablesToModel(semanticModel, qv_list );
		modelConstructor.addDecisionsToModel(semanticModel,decision_list);
		modelConstructor.addModelName(semanticModel,ctx.var_name().getText());
		modelConstructor.updateDecisionsAfterAllQualityVariables(semanticModel, decision_list);
		return new Value(null);
	}
	@Override 
	public Value visitModelObjectiveList(ModelParser.ModelObjectiveListContext ctx) { 
		if (ctx.objective_decl() != null && ctx.objective_decl().size() > 0){
			for(ModelParser.Objective_declContext obj_decl_ctx : ctx.objective_decl()){
				visit(obj_decl_ctx);
			}
		}else{
			throw new RuntimeException ("Model must have objectives.");
		}
		return new Value(null);
	}
	@Override 
	public Value visitObjective_decl(ModelParser.Objective_declContext ctx) {
		String obj_name =  ctx.var_name().getText().trim();
		Value obj_def = null;
		if (ctx.objective_def() != null){
			obj_def = visit(ctx.objective_def());
		}else{
			obj_def= modelConstructor.addObjectiveExpectation(obj_name);
		}
		String obj_opt_direction = ctx.optimisationDirection().getText();
		Objective obj = modelConstructor.createNewObjective(obj_name,obj_opt_direction);
		// set model infovalueObj if the objective name equal info value obj
		if (ctx.number() != null){
			Value margin = visit (ctx.number());
			obj.setMargin(margin.convertToDouble());
		}
		obj_definitions.put(obj_name, obj_def);
		obj_list.put(obj_name, obj);
		return new Value(obj);
	}
	@Override 
	public Value visitObjectiveExpectation(ModelParser.ObjectiveExpectationContext ctx) {
		String obj_qv =  ctx.var_name().getText().trim();
		Value obj_exp = modelConstructor.addObjectiveExpectation(obj_qv);
		return obj_exp; 
	}
	@Override 
	public Value visitObjectiveBooleanProbability(ModelParser.ObjectiveBooleanProbabilityContext ctx) {
		String obj_qv =  ctx.var_name().getText().trim();
		Value obj_bool_prob = modelConstructor.addObjectiveBooleanProbablity(obj_qv);
		return obj_bool_prob; 
	}
	@Override 
	public Value visitObjectiveProbability(ModelParser.ObjectiveProbabilityContext ctx) {
		Value comparisonExpr =  visit(ctx.comparision());
		Value obj_prob = modelConstructor.addObjectiveProbablity(comparisonExpr);
		return obj_prob; 
	}
	public Value visitModelQualityVariableList(ModelParser.ModelQualityVariableListContext ctx) { 
		if (ctx.quality_var_decl() != null && ctx.quality_var_decl().size() > 0){
			for(ModelParser.Quality_var_declContext qv_decl_ctx : ctx.quality_var_decl()){
				visit(qv_decl_ctx);
			}
		}
		return new Value(null);
	}
	@Override 
	public Value visitQuality_var_decl(ModelParser.Quality_var_declContext ctx) { 
		QualityVariable qv = modelConstructor.createNewQualityVariable();
		qv = modelConstructor.addDecisionsBeforeQualityVariable(qv, decision_list);
		String qv_name = ctx.var_name().getText().trim();
		Value qv_def = visit(ctx.quality_var_def());
		qv = modelConstructor.addQualityVariableExpression(qv, qv_name,qv_def);
		qv_list.put(qv_name.toString(), qv);
		qv =modelConstructor.updateCurrentQVWhenItDependsOnDecision(qv);
		modelConstructor.addInformationValueParameters(semanticModel,qv_name,qv_def);
		return new Value (qv);
	}
	@Override 
	public Value visitQualityVariableArithmetic(ModelParser.QualityVariableArithmeticContext ctx) {
		return visit (ctx.arithmetic_expr());
	}
	@Override 
	public Value visitQualityVariableDecision(ModelParser.QualityVariableDecisionContext ctx) {
		return visit (ctx.decision_def());
	}
	@Override 
	public Value visitDecisionXOR(ModelParser.DecisionXORContext ctx) {
		return visit(ctx.decision_body());
	}
	@Override 
	public Value visitDecision_body(ModelParser.Decision_bodyContext ctx) { 
		OR_Refinement or_ref = modelConstructor.createNewOr_Refinement();
		Decision d = modelConstructor.createNewDecision();
		String decision_name = ctx.decision_Name.getText().replace("\"", "").trim();
		d.setDecisionLabel(decision_name);
		ArrayList<String> optionNames =  new ArrayList<String>();
		for (ModelParser.Option_nameContext optionNameContext : ctx.option_name()){
			String optionName = optionNameContext.getText().replace("\"", "").trim();
			optionNames.add(optionName);
			d.addOption(optionName);
		}
		int i =0;
		for (ModelParser.Option_defContext optionDefContext : ctx.option_def()){
			// could be a unary, binary expr or a parameter.
			Value definition =visit(optionDefContext);
			modelConstructor.addOR_RefinementDefinition(or_ref,optionNames.get(i),definition);
			i++;
		}
		
		or_ref.setDecision(d);
		decision_list.put(decision_name, d);
		return new Value (or_ref);
	}
	@Override 
	public Value visitOptionExpression(ModelParser.OptionExpressionContext ctx) { 		
		Value option_param_expr =  visit(ctx.arithmetic_expr());
		return  option_param_expr;
	}
	@Override 
	public Value visitOptionParameter(ModelParser.OptionParameterContext ctx) {
		Value option_param_def_expr = visit(ctx.parameter_def());
		return  option_param_def_expr;
	}
	@Override 
	public Value visitQualityVariableParameter(ModelParser.QualityVariableParameterContext ctx) {
		return visit (ctx.parameter_def());
	}
	@Override 
	public Value visitParameter_def(ModelParser.Parameter_defContext ctx) { 
		Value distribution = visit(ctx.distribution());
		List<Value>distributionArgument = new ArrayList<Value>();
		if (ctx.distribution_arg() != null){ 
			for (ModelParser.Distribution_argContext arg : ctx.distribution_arg()){
				distributionArgument.add(visit(arg));
			}
		}
		Value param_def_expr =  modelConstructor.addDistribution(distribution, distributionArgument) ;
		return param_def_expr;
	}
	@Override public Value visitDistribution(ModelParser.DistributionContext ctx) {
		Value exprValue = new Value (ctx.distributionValue.getText().trim().toUpperCase(Locale.ENGLISH));
		return exprValue;
	}

	@Override public Value visitDistribution_arg(ModelParser.Distribution_argContext ctx) {
		
		String distributionArgument = ctx.arithmetic_expr().getText();
		argumentHasExpr = modelConstructor.doesDistributionArgumentHasExpr(distributionArgument, operator);
		Value atomicExpr = visit(ctx.arithmetic_expr());
		if (computedArgumentExpression != null){
			atomicExpr = modelConstructor.updateDistributionArguementValue(computedArgumentExpression);
			computedArgumentExpression = null;
		}
		argumentHasExpr = false;
		return atomicExpr;
	}
	@Override 
	public Value visitVar_name(ModelParser.Var_nameContext ctx) {
		String idValue = ctx.Identifier().getText();
		Value varname = modelConstructor.addIdentifierExpression(idValue);
		return varname;
		
	}

	@Override 
	public Value visitExprCompare(ModelParser.ExprCompareContext ctx) {
		Value comparison= visit (ctx.comparision());
		return comparison;
	}
	@Override public Value visitCompareVariables(ModelParser.CompareVariablesContext ctx) { 
		Value left_var = visit (ctx.var_name(0));
		Value right_var = visit (ctx.var_name(1));
		String relaionalOperator = ctx.relationalOp.getText().trim();
		Value expValue = modelConstructor.addComparatorExpression (left_var,right_var, relaionalOperator);
		return expValue;
	}
	@Override public Value visitCompareExpression(ModelParser.CompareExpressionContext ctx) { 
		Value var = visit (ctx.var_name());
		String comparisonArgument = ctx.arithmetic_expr().getText();
		argumentHasExpr = modelConstructor.doesDistributionArgumentHasExpr(comparisonArgument, operator);
		Value expr =  visit (ctx.arithmetic_expr());
		if (computedArgumentExpression != null){
			expr = modelConstructor.updateDistributionArguementValue(computedArgumentExpression);
			computedArgumentExpression = null;
		}
		argumentHasExpr = false;
		String relaionalOperator = ctx.relationalOp.getText().trim();
		Value expValue = modelConstructor.addComparatorExpression (var,expr, relaionalOperator);
		return expValue; 
	}

	@Override public Value visitExprOr(ModelParser.ExprOrContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =visit (ctx.arithmetic_expr(1));
		String op = ctx.OR().getText();
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, op);
		return expValue;
	}

	@Override public Value visitExprAnd(ModelParser.ExprAndContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		String op = ctx.AND().getText();
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, op);
		return expValue;
	}

	@Override public Value visitExprNot(ModelParser.ExprNotContext ctx) {
		Value expr = visit (ctx.arithmetic_expr());
		Value expValue = modelConstructor.addUnaryExpression(expr, "!");
		return expValue;
	}

	@Override public Value visitExprBracket(ModelParser.ExprBracketContext ctx) { 
		Value exprValue =visit(ctx.arithmetic_expr());
		return exprValue;
	}	
	@Override 
	public Value visitExprPreOperator(ModelParser.ExprPreOperatorContext ctx) { 
		
		String op = ctx.op.getText();
		Value expr = visit (ctx.arithmetic_expr());
		Value expValue = modelConstructor.addUnaryExpression(expr, op);
		return expValue;
	}
	@Override public Value visitExprDiv(ModelParser.ExprDivContext ctx) { 
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, "/");
		if (argumentHasExpr == true){
			computedArgumentExpression = String.valueOf(leftOperand.convertToDouble() / rightOperand.convertToDouble());
		}
		return expValue;
	}
	@Override public Value visitExprMult(ModelParser.ExprMultContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, "*");
		if (argumentHasExpr == true){
			computedArgumentExpression = String.valueOf(leftOperand.convertToDouble() * rightOperand.convertToDouble());
		}
		return expValue;
	}
	@Override public Value visitExprAdd(ModelParser.ExprAddContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, "+");
		if (argumentHasExpr == true){
			computedArgumentExpression = String.valueOf(leftOperand.convertToDouble() + rightOperand.convertToDouble());
		}
		return expValue;
	}
	@Override public Value visitExprSub(ModelParser.ExprSubContext ctx) {
		Value leftOperand = visit (ctx.arithmetic_expr(0));
		Value rightOperand =  visit (ctx.arithmetic_expr(1));
		Value expValue = modelConstructor.addBinaryExpression (leftOperand,rightOperand, "-");
		if (argumentHasExpr == true){
			computedArgumentExpression = String.valueOf(leftOperand.convertToDouble() - rightOperand.convertToDouble());
		}
		return expValue;
	}
	@Override public Value visitExprPower(ModelParser.ExprPowerContext ctx) {
		Double arithmetic_expr = null;
		Value base = visit (ctx.arithmetic_expr(0));
		Value power = visit (ctx.arithmetic_expr(1));
		arithmetic_expr= modelConstructor.findExponent(base,power);
		Value value = modelConstructor.addNumberExpression(String.valueOf(arithmetic_expr) );
		if (argumentHasExpr == true){
			computedArgumentExpression = arithmetic_expr.toString();
		}
		return value;
	}
	@Override 
	public Value visitExprPercent(ModelParser.ExprPercentContext ctx) {
		Value number = visit( ctx.arithmetic_expr());
		try {
			Double.parseDouble( number.toString());
		}catch(Exception e){
			throw new RuntimeException ("Incorrect syntax for percentage arithmetic_expr. Ensure you specify a number. \n  Check documentation for details.");
		}
		Double percentValue = number.convertToDouble()/100;
		Value value = modelConstructor.addNumberExpression(String.valueOf(percentValue) );
		return value;
	}
	@Override public Value visitExprNumber(ModelParser.ExprNumberContext ctx) {
		Value atomicExpr = visit(ctx.number());
		return atomicExpr;
	}
	@Override 
	public Value visitAtomicInteger(ModelParser.AtomicIntegerContext ctx) { 
		String integerValue = ctx.integerLiteral().getText().trim();
		Value value = modelConstructor.addNumberExpression(integerValue);
		return value;  
	}
	@Override 
	public Value visitAtomicFloat(ModelParser.AtomicFloatContext ctx) {
		String floatValue =ctx.FloatingPointLiteral().getText().trim();
		Value value = modelConstructor.addNumberExpression(floatValue);
		return value;
	}
	@Override 
	public Value visitAtomicDecimal(ModelParser.AtomicDecimalContext ctx) { 
		String decimalValue =ctx.DecimalLiteral().getText().trim();
		Value atomicdecimal = modelConstructor.addNumberExpression(decimalValue);
		return atomicdecimal;
	}

	
}
