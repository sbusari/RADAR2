package radar.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import radar.utilities.Helper;

public class SyntheticModelGenerator {

	int nbr_decisions_;
	// used to track if all decisions have been used in the model
	int decision_counter_;
	//store the min no variables required in the model.
	int min_nbr_variables_;
	int nbr_objectives_;
	int min_nbr_options_;
	int max_nbr_options_;
	int loopCounter_;
	static int var_index_ =0;
	int nbr_radar_var_ =3;
	String modelName_;
	Random rn = new Random();
	// store all model variables and their expression 
	Map<String, String> modelVariables_;
	// store model variables  for an or refinement
	Map<String, String> orRefModelVariables_;
	// store model variable and their definition whether and, or, param est.
	Map<String, String> variableDefinition_;
	List<String> decisionNames_;
	String[] opt_dir = new String[]{"Max", "Min"};
	String[] obj_statistic = new String[]{"EV", "Pr"};
	String[] var_symbols = new String[]{"X", "Y", "Z", "V", "T", "H", "M", "L", "N", "R", "S", "Q", "P"};
	
	public SyntheticModelGenerator(){
		modelVariables_ = new LinkedHashMap<String, String>();
		variableDefinition_ = new LinkedHashMap<String, String>();
		decisionNames_ = new ArrayList<String>();
		orRefModelVariables_ = new LinkedHashMap<String, String>();
	}
	
	List<String> generateAllDecisionVariables(int nbr_radar_var){
		int total_var = (int)Math.pow(2,nbr_radar_var);
		List<String> result = new ArrayList<String>();
		int index=0;
		while (index < total_var ){
			java.math.BigInteger decision_var = new java.math.BigInteger(""+index);
			String padded= StringUtils.leftPad(decision_var.toString(2), 3, '0');
			result.add(padded);
			index ++;
		}
		return result;
	}
	char[] getRadarVariableDefinitionIndex (){
		int total_var = (int)Math.pow(2,nbr_radar_var_);
		int rand = getRandom(0,total_var-1);
		List<String> allDecisionVariables = generateAllDecisionVariables(total_var);
		char[] decision_var = allDecisionVariables.get(rand).toCharArray();
		return decision_var;
	}
	String getExpression(int index, String var_symbol, String left_expr ){
		//generate boolean value to be used to know the selection of AndRef: Parameter : ORRef. e.g. 0 0 1
		//char[] radarVariableDefinitionIndex = getRadarVariableDefinitionIndex();
		StringBuilder sb = new StringBuilder();
		String and_var = "";
		String param_def = "";
		String or_ref = "";
		
		// and refinement
		and_var = var_symbol + (++var_index_);
		variableDefinition_.put(and_var, "and_def");
		String andRef =  and_var + " " + getRandomOperator() + " "; 
		sb.append(andRef);	
		min_nbr_variables_--;
		
		//param estimation
		param_def =  var_symbol + (++var_index_);
		variableDefinition_.put(param_def, "param_def");
		String param_estimation = param_def + " " + getRandomOperator() + " ";
		sb.append(param_estimation);
		min_nbr_variables_--;
	
		String X_def = "";
		if(nbr_decisions_ > 0){
			or_ref = var_symbol + (++var_index_);
			variableDefinition_.put(or_ref, "or_def");
			sb.append(or_ref);
			// define the current variable here
			X_def = "decision(" + getDecisionName() + "){\n";
			int nbr_random_option = getRandom(min_nbr_options_,max_nbr_options_);
			for (int i =0; i < nbr_random_option; i++){
				//X_def += "\t \"Option" + i +" : "+ generateRandomParameterEstimation()+ "\n";
				X_def += "\t \"Option" + i +"\" : "+ generateRandomExpression(var_symbol)+ " \n";
			}
			X_def += "}";
			nbr_decisions_--;
			min_nbr_variables_--;
		}
		
		// refine the equation to remove last oprator incase the number of decisions is reached and execution does not brack the XOR.
		String sb_toString= sb.toString();
		System.out.print("check: " + sb_toString );
		String operators = "+/*-";
		String lastChar = sb_toString.trim().substring(sb_toString.length()-1);
		if (operators.contains(lastChar)){
			System.out.print("length: " + sb_toString.length() +"\n");
			sb_toString = sb_toString.substring(0,sb_toString.length()-2);
		}
		/*if (sb_toString != null && sb_toString.length() > 0 && sb_toString.trim().charAt(sb_toString.length()-1) == '+' || sb_toString.trim().charAt(sb_toString.length()-1) == '-' || sb_toString.trim().charAt(sb_toString.length()-1) == '*' 
				|| sb_toString.trim().charAt(sb_toString.length()-1) == '/'   ){
			System.out.print("length: " + sb_toString.length() +"\n");
			sb_toString = sb_toString.substring(0,sb_toString.length()-1);
		}*/
		System.out.print(", outcome: " + sb_toString +"\n");
		//modelVariables_.put(left_expr, sb.toString() + ";");
		modelVariables_.put(left_expr, sb_toString + ";");
		// add the or refinement expressions to the global variable
		for (Map.Entry<String, String> entry: orRefModelVariables_.entrySet()){
			modelVariables_.put(entry.getKey(), entry.getValue());
		}
		if(param_def != ""){modelVariables_.put(param_def,generateRandomParameterEstimation());}
		if (X_def != "" ){modelVariables_.put(or_ref, X_def);}	
		return sb.toString() + ";";
	}
	String generateRandomExpression(String var_symbol){
		String result =generateRandomParameterEstimation();
		int rand =  getRandom(0,2);
		switch (rand){
			case 0: result = generateRandomORRef(var_symbol); break;
			case 1: result =  generateRandomAndRef(var_symbol); break;
			case 2: result =  generateRandomParameterEstimation(); break;
			default : result = generateRandomParameterEstimation();
		}
		return result;
	}
	String getDecisionName (){
		if (decision_counter_ == 0){
			int rand = getRandom(0,decisionNames_.size()-1);
			return decisionNames_.get(rand);
		}else{
			String decisionName = "\"D" + decision_counter_-- + "\"";
			decisionNames_.add(decisionName);
			return decisionName;
		}
	}
	int getRandom (int min, int max){
		
		int range = max - min + 1;
		int num =  rn.nextInt(range) + min;
		return num;
	}
	String getRandomOperator (){
		String result = "+";
		int rand = getRandom(0,3);
		switch (rand){
			case 0 : result= "+"; break;
			case 1 : result= "-"; break;
			case 2 : result= "/"; break;
			case 3 : result= "*"; break;
			default : return "*";
		}
		return result;
	}

	void generateObjectiveModel (String var_symbol){
		while (nbr_decisions_ > 0 || min_nbr_variables_ > 0)	{	
			String left_expr = var_symbol + loopCounter_;
			String right_expr = getExpression(loopCounter_, var_symbol, left_expr );
			//modelVariables_.put(left_expr, right_expr);
			variableDefinition_.put(left_expr, "and_def");
			loopCounter_++;
		}
	}
	String generateRandomORRef(String var_symbol){
		
		String or_ref = var_symbol + (++var_index_);
		variableDefinition_.put(or_ref, "or_def");
		String X_def = "decision(" + getDecisionName() + "){\n";
		int nbr_random_option = getRandom(min_nbr_options_,max_nbr_options_);
		for (int i =0; i < nbr_random_option; i++){
			X_def += "\t \"Option" + i +"\" : "+ generateRandomParameterEstimation()+ " \n";
		}
		X_def += "}";
		nbr_decisions_--;
		min_nbr_variables_--;
		orRefModelVariables_.put(or_ref, X_def);
		return or_ref + ";";
	}
	String generateRandomAndRef(String var_symbol){
		String arith_def_left =  var_symbol + (++var_index_);
		variableDefinition_.put(arith_def_left, "param_def");
		min_nbr_variables_--;
		
		String arith_def_right =  var_symbol + (++var_index_);
		variableDefinition_.put(arith_def_right, "param_def");
		min_nbr_variables_--;
		
		orRefModelVariables_.put(arith_def_left, generateRandomParameterEstimation());
		orRefModelVariables_.put(arith_def_right, generateRandomParameterEstimation());

		return arith_def_left + " " + getRandomOperator() + " " + arith_def_right + ";";
	}
	String generateRandomParameterEstimation (){	
		String result = "normalCI(" + getRandom(0,10) + "," + getRandom(0,10) + ");"; 
		int rand =  getRandom(0,2);
		switch (rand){
			case 0: result =  "normalCI(" + getRandom(0,5) + "," + getRandom(6,10) + ");"; break;
			case 1: result =  "normal(" + getRandom(0,5) + "," + getRandom(6,10) + ");"; break;
			case 2: result =  "triangular(" + getRandom(0,3) + "," + getRandom(4,7) + "," + getRandom(8,10) + ");"; break;
			default : result = "normal" + getRandom(0,10) + "," + getRandom(0,10) + ");";
		}
		return result;	
	}
	
	String generateRadarModel (){
		
		// determine the minimum number of variables for each objective
		int obj_min_nbr_var = min_nbr_variables_/nbr_objectives_;
		int obj_decision = nbr_decisions_/nbr_objectives_;
		
		// determine the no of decisions each objective can take
		decision_counter_ = nbr_decisions_;
		StringBuilder model =  new StringBuilder();
		model.append("Model " + modelName_ + "; \n" );
		for (int i =0; i < nbr_objectives_; i ++){
			model.append(generateObjectiveDefinition(i) + "\n");
			min_nbr_variables_ = obj_min_nbr_var;
			nbr_decisions_ = obj_decision;
			loopCounter_ =0;
			var_index_ =0;
			generateObjectiveModel(var_symbols[i]);
			// now loop through variable definition and check those yet to have expr from "model variable",  then
			for (Map.Entry<String, String> entry: variableDefinition_.entrySet() ){
				if (!modelVariables_.containsKey(entry.getKey())){
					String expr = generateRandomParameterEstimation();
					modelVariables_.put(entry.getKey(), expr);
				}
			}
			model.append("");
		}
		
		
		for (Map.Entry<String, String> entry: modelVariables_.entrySet() ){
			model.append(entry.getKey() + " = " +  entry.getValue() + "\n");
		}
		return model.toString();
	}
	String generateObjectiveDefinition(int index){
		String obj_def = "";
		obj_def += "Objective " + opt_dir[getRandom(0,1)] + " OF" +  index + " = " + obj_statistic[getRandom(0,1)] + "(" + var_symbols[index] +"0" + ");";	
		return obj_def;
	}
	
	public static void main(String[]args) throws Exception {
    	SyntheticModelGenerator smg = new SyntheticModelGenerator();
    	smg.max_nbr_options_ = 10;
    	smg.min_nbr_options_ = 2;
    	smg.min_nbr_variables_ = 10;
    	smg.nbr_decisions_ = 10;
    	smg.nbr_objectives_ = 4;
    	smg.modelName_ = "Test";
    	System.out.print(smg.generateRadarModel());
    }
	public String generate(){
		return this.generateRadarModel();
	}
	public void setMaxNbrOptions(int max_nbr_options){
		max_nbr_options_ = max_nbr_options;
	}
	public void setMinNbrOptions(int min_nbr_options){
		min_nbr_options_ = min_nbr_options;
	}
	public void setMinNbrVariables(int min_nbr_variables){
		min_nbr_variables_ = min_nbr_variables;
	}
	public void setNbrDecisions(int nbr_decisions){
		nbr_decisions_ = nbr_decisions;
	}
	public void setNbrObjectives(int nbr_objectives){
		nbr_objectives_ = nbr_objectives;
	}
	public void setModelName(String modelName){
		modelName_ = modelName;
	}

	
}
