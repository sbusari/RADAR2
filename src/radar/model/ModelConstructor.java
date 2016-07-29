package radar.model;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class ModelConstructor {

	int simulation;
	public ModelConstructor(int simulationRun){
		simulation =simulationRun;
	}
	public  Model createNewModel (){
		return Model.getInstance();
	}
	 void addModelObjective (Model model, String obj_name, Objective objective){
		model.addObjective(obj_name, objective);
	}
	 void addModelQualityVariable(Model model, String qv_name, QualityVariable qualityVariable){
		model.addQualityVariables(qv_name, qualityVariable);
	}
	public  Model addQualityVariablesToModel (Model model, Map<String, QualityVariable> qvlist){
		for (Map.Entry<String, QualityVariable> entry: qvlist.entrySet()){
			QualityVariable modelQv = entry.getValue();
			addModelQualityVariable(model,entry.getKey(),modelQv);
		}
		return model;
	}
	public  Model addDecisionsToModel (Model model, Map<String, Decision> decision_list){
		model.setDecisions(decision_list);
		return model;
	}
	public  Model addModelName (Model model, String modelName){
		model.setModelName(modelName);
		return model;
	}
	public  Model updateDecisionsAfterAllQualityVariables (Model model, Map<String, Decision> decisions){
		Map<String, QualityVariable> qvList = model.getQualityVariables();
		for (Map.Entry<String, QualityVariable> entry: qvList.entrySet()){
			if (entry.getValue().getDecisionsBeforeVar() != null){
				for (Map.Entry<String, Decision> decisionEntry: decisions.entrySet()){
					boolean decisionBefore = false;
					for (Map.Entry<String, Decision> qvdecisionEntry :entry.getValue().getDecisionsBeforeVar().entrySet()){
						if (entry.getValue().getDecisionsBeforeVar().containsKey(decisionEntry.getKey())){
							decisionBefore =true;
						}
					}
					if (decisionBefore == false){
						// add decision after to qv
						entry.getValue().addDecisionsAfterVar(decisionEntry.getKey(), decisionEntry.getValue());
						
					}
					
				}
				
			}
			
		}
		return model;
	}
	public  Model addObjectivesToModel (Model model, Map<String, Value> obj_definition,Map<String, Objective> obj_list, Map<String, QualityVariable> qvlist){
		for (Map.Entry<String, Objective> entry: obj_list.entrySet()){
			Value obj = obj_definition.get(entry.getKey());
			Objective modelObj = entry.getValue();
			if (obj.getStatistic() instanceof Expectation){
				Expectation exp = (Expectation)obj.getStatistic();
				Identifier id = (Identifier)exp.getObjExpression();
				//add Objective QualityVariable ReferedTo
				modelObj.setQualityVariable(qvlist.get(id.getID()));
				modelObj.setStatistic(exp);
			}else if (obj.getStatistic() instanceof BooleanProbability){
				BooleanProbability bool_prob = (BooleanProbability)obj.getStatistic();
				Identifier id = (Identifier)bool_prob.getObjExpression();
				//add Objective Quality Variable ReferedTo
				modelObj.setQualityVariable(qvlist.get(id.getID()));
				modelObj.setStatistic(bool_prob);
			}else if (obj.getStatistic() instanceof Probability){
				Probability prob = (Probability)obj.getStatistic();
				modelObj.setStatistic(prob);
				BinaryExpression binaryExpr = (BinaryExpression)prob.getObjExpression();
				if (!(binaryExpr.getLeftExpression() instanceof Identifier)){
					throw new RuntimeException ("Left operand of a comparison expression must be a quality variable");
				}else{
					//add Objective Quality Variable ReferedTo
					modelObj.setQualityVariable(qvlist.get(((Identifier)binaryExpr.getLeftExpression()).getID()));
				}
				//add Objective Quality Variable ReferedTo
				/*if ((binaryExpr.getRightExpression() instanceof Identifier)){
					modelObj.addQualityVariable(qvlist.get(((Identifier)binaryExpr.getRightExpression()).getID()));
				}*/
			}
			addModelObjective(model,modelObj.getLabel(),modelObj );
		}
		return model;
	}
	public  QualityVariable createNewQualityVariable(){
		QualityVariable qv = new QualityVariable ();
		return qv;
	}
	public  QualityVariable addQualityVariableExpression (QualityVariable qv, String qv_name, Value qv_def){
		qv.setLabel(qv_name.toString());
		qv.setDefinition(qv_def.getExpression());
		return qv;
	}
	public  QualityVariable addDecisionsBeforeQualityVariable (QualityVariable qv, Map<String, Decision> precedingDecision){
		Map<String, Decision> decision = new HashMap<String, Decision>(precedingDecision);
		qv.setDecisionsBeforeVar(decision);
		return qv;
	}
	public  QualityVariable updateCurrentQVWhenItDependsOnDecision (QualityVariable qv){
		if (qv.getDefinition() instanceof OR_Refinement){
			Decision d = ((OR_Refinement)qv.getDefinition()).getDecision();
			if (qv.getDecisionsBeforeVar() !=  null && qv.getDecisionsBeforeVar().containsKey(d.getDecisionLabel())){
				qv.getDecisionsBeforeVar().remove(d.getDecisionLabel());
			}
		}
		return qv;
	}
	public  QualityVariable addDecisionsAfterQualityVariable (QualityVariable qv, Map<String, Decision> suceedingDecision){
		qv.setDecisionsAfterVar(suceedingDecision);
		return qv;
	}
	
	public  OR_Refinement createNewOr_Refinement (){
		OR_Refinement or_refinement = new OR_Refinement ();
		return or_refinement;
	}

	public  Value addOR_RefinementDefinition (OR_Refinement or_ref, String option_name, Value option_def){
		or_ref.addDefinition(option_name, option_def.getExpression());
		return new Value(or_ref);
	}
	public  Decision createNewDecision(){
		Decision decision = new Decision ();
		return decision;
	}
	public  Decision addDecisionDefinition (Decision d, String decision_name, String option){
		d.setDecisionLabel(decision_name);
		d.addOption(option);
		return d;
	}
	public  Objective createNewObjective (String obj_name,String optimisationDirection){
		Objective obj = new Objective ();
		obj.setLabel(obj_name);
		if (optimisationDirection.endsWith("Min")){
			obj.setIsMinimisation(true);
		}
		return obj;
	}
	public  Value addObjectiveExpectation (String referredQV){
		Expectation exp = new Expectation();
		Identifier qv_id =new Identifier();
		qv_id.setID(referredQV);
		exp.setObjExpression(qv_id);
		return new Value (exp);
	}
	public  Value addObjectiveBooleanProbablity (String referredQV){
		BooleanProbability boop = new BooleanProbability();
		Identifier qv_id =new Identifier();
		qv_id.setID(referredQV);
		boop.setObjExpression(qv_id);
		return new Value (boop);
	}
	public  Value addObjectiveProbablity (Value referredQV){
		Probability p = new Probability();
		// get the comparison expression from Value referredQV
		p.setObjExpression(referredQV.getExpression());
		BinaryExpression binexpr = (BinaryExpression)referredQV.getExpression();
		String comparator = binexpr.getBinaryOperator().getBinaryOperatorValue();
		p.setComparator(comparator);
		if(binexpr.getRightExpression() instanceof Number){
			p.xIsAScaler(true);
			p.setScalerValue(((Number)binexpr.getRightExpression()).getValue());
		}else if (binexpr.getRightExpression() instanceof Identifier){
			p.xIsAScaler(false);
		}
		return new Value (p);
	}
	public  Value addNumberExpression (String number){
		Number numeric = new Number();
		Double decimalValue = null;
		try{
			decimalValue =Double.parseDouble(number);
		}catch (Exception e){
			
		}
		numeric.setValue(decimalValue);
		return new Value(numeric);
	}
	public  Value addDistribution (Value distribution, List<Value> distributionArguments){
		Expression baseExpr =null;
		if (distribution.toString().equals(ParameterDistribution.NORMAL.toString())){
			//TODO: check that the  distributionArguments.get(0) are instance of number expression.
			double mean = distributionArguments.get(0).convertToDouble();
			double sd = distributionArguments.get(1).convertToDouble();
			baseExpr = new NormalDistribution(mean,sd,simulation);
		}else if (distribution.toString().equals(ParameterDistribution.TRIANGULAR.toString())){
			double lower = distributionArguments.get(0).convertToDouble();
			double mode = distributionArguments.get(1).convertToDouble();
			double upper = distributionArguments.get(2).convertToDouble();
			baseExpr = new TriangularDistribution(lower, mode, upper,simulation);
		}else if (distribution.toString().equals(ParameterDistribution.NORMAL_CI.toString())){
			double a = distributionArguments.get(0).convertToDouble();
			double b = distributionArguments.get(1).convertToDouble();
			baseExpr = new NormalCIDistribution(a,b,simulation);
		}else if (distribution.toString().equals(ParameterDistribution.UNIFORM.toString())){
			double lower = distributionArguments.get(0).convertToDouble();
			double upper = distributionArguments.get(1).convertToDouble();
			baseExpr = new UniformDistribution(lower,upper,simulation);
		}else if (distribution.toString().equals(ParameterDistribution.DETERMINISTIC.toString())){
			double value = distributionArguments.get(0).convertToDouble();
			baseExpr = new DeterministicDistribution(value);
		}
		return new Value(baseExpr);
	}
	public  Value addIdentifierExpression (String id){
		Identifier var_id = new Identifier();
		var_id.setID(id);
		return new Value(var_id);
	}
	private  BinaryOperator getBinaryOperator (String operator){
		BinaryOperator bop = null;
		switch(operator){
			case "+": bop = BinaryOperator.ADD; break;
			case "-": bop = BinaryOperator.SUB; break;
			case "/": bop = BinaryOperator.DIV; break;
			case "*": bop = BinaryOperator.MUL; break;
			case "||": bop = BinaryOperator.SUB; break;
			case "&&": bop = BinaryOperator.AND; break;
			case "<": bop = BinaryOperator.LESS; break;
			case ">": bop = BinaryOperator.GRET; break;
			case "<=": bop = BinaryOperator.LEQ; break;
			case ">=": bop = BinaryOperator.GEQ; break;
		}
		return bop;
	}
	private  UnaryOperator getUnaryOperator (String operator){
		UnaryOperator uop = null;
		switch(operator){
			case "!": uop = UnaryOperator.NOT; break;
			case "-": uop = UnaryOperator.NEG; break;
			case "+": uop = UnaryOperator.POS; break;
		}
		return uop;
	}
	public  Value addComparatorExpression (Value left, Value right, String op){
		BinaryExpression bexpr = new BinaryExpression();
		if (!(left.getExpression() instanceof Identifier)){
			throw new RuntimeException ("Left operand of a comparison expression must be a quality variable");
		}
		bexpr.setLeftExpression(left.getExpression());
		bexpr.setRightExpression(right.getExpression());
		BinaryOperator bop = getBinaryOperator(op);
		bexpr.setBinaryOperator(bop);
		return new Value (bexpr);
	}
	public  Value addBinaryExpression (Value left, Value right, String op){
		BinaryExpression bexpr = new BinaryExpression();
		bexpr.setLeftExpression(left.getExpression());
		bexpr.setRightExpression(right.getExpression());
		BinaryOperator bop = getBinaryOperator(op);
		bexpr.setBinaryOperator(bop);
		return new Value (bexpr);
	}
	public  Value addUnaryExpression (Value expr, String op){
		UnaryExpression uexpr = new UnaryExpression();
		uexpr.setExpression(expr.getExpression());
		UnaryOperator uop = getUnaryOperator(op);
		uexpr.setUnaryOperator(uop);
		return new Value (uexpr);
	}
	public  boolean doesDistributionArgumentHasExpr2(Value arguement){
		boolean distributionArgumentHasExpr = false;
		if (arguement.getExpression().getClass().equals(BinaryExpression.class)){
			distributionArgumentHasExpr = true;
		}
		return distributionArgumentHasExpr;
	}
	public  boolean doesDistributionArgumentHasExpr (String distributionArgument, String operator){
		boolean distributionArgumentHasExpr = false;
		String operators = "["+ operator +"]";
        Pattern opeartorPattern = Pattern.compile(operators);
        boolean operatorExist = opeartorPattern.matcher(distributionArgument).find();
        if (operatorExist == true){
        	distributionArgumentHasExpr =true;
		}
        return distributionArgumentHasExpr;
	}
	public  Value updateDistributionArguementValue (String computedDistributionExpression){
		Number numeric = new Number();
		Double exprValue = null;
		try{
			exprValue =Double.parseDouble(computedDistributionExpression);
		}catch (Exception e){
			throw new RuntimeException ("Parameter distribution expression cannot be cast to a number.");
		}
		numeric.setValue(exprValue);
		return new Value(numeric);
	}
	public  double findExponent (Value base, Value power){
		Double exprBase = null; 
		Double exprPower =null; 
		try{
			// check if it is a unary expression and get the number ffrom it.
			if (base.getExpression() instanceof UnaryExpression){
				Number base_no =  (Number)((UnaryExpression) base.getExpression()).getExpression();
				UnaryOperator op = ((UnaryExpression) base.getExpression()).getUnaryOperator();
				if ( op.equals(UnaryOperator.NOT)){
					exprBase = 1 - base_no.getValue();
				}else if (op.equals(UnaryOperator.NEG)){
					exprBase = (-1 * base_no.getValue());
				}else if (op.equals(UnaryOperator.POS)){
					exprBase = base_no.getValue();
				}
			}else{
				exprBase = Double.parseDouble( base.toString());
			}
			if (power.getExpression() instanceof UnaryExpression){
				Number power_no = (Number)((UnaryExpression) power.getExpression()).getExpression();
				UnaryOperator op = ((UnaryExpression) power.getExpression()).getUnaryOperator();
				if ( op.equals(UnaryOperator.NOT)){
					exprPower = 1 - power_no.getValue();
				}else if (op.equals(UnaryOperator.NEG)){
					exprPower = (-1 * power_no.getValue());
				}else if (op.equals(UnaryOperator.POS)){
					exprPower = power_no.getValue();
				}
			}else{
				exprPower = Double.parseDouble( power.toString());
			}
			
		}catch(Exception e){
			throw new RuntimeException ("Incorrect syntax for exponential arithmetic_expr. Ensure numbers are separated from key words and other string literals. \n  Check documentation for details.");
		}
		double exponent = Math.pow(exprBase,exprPower); 
		return exponent;
	}


}
