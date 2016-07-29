package radar.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import radar.model.*;
import radar.jmetal.core.SolutionSet;
import radar.jmetal.core.Variable;
import radar.jmetal.util.JMException;
import radar.utilities.SolutionDetail;
public class AlternativeAnalyser {
	
	private static ArrayList<String> getSolutionsObjectives ( SolutionSet solutions) throws JMException{
		 ArrayList<String> solutionsObjective = new ArrayList<String>();
		 for (int i=0; i < solutions.size(); i++){
			 String objectiveValue = solutions.get(i).toString();
			 solutionsObjective.add(objectiveValue);
		 }
		 return solutionsObjective;
	}
	public static ArrayList<ArrayList<String>> solutionInTableFormat (List<String> selectedOption , List<String> solutionObjective, List<Objective> objList){
		ArrayList<ArrayList<String>> decisionVectorAndObjectives  = new ArrayList<ArrayList<String>>();
		 for (int i =0; i <  selectedOption.size(); i++){
			 String [] adecisionVector = null;
			 adecisionVector = selectedOption.get(i).split(",");

			 String [] anObjective = solutionObjective.get(i).split("\\s+");
			 for (int j =0; j < anObjective.length; j++){
				 if (objList.get(j).getIsMinimisation() == false){
					anObjective[j]= anObjective[j].substring(1);
					 
				 }
			 }
			 ArrayList<String> solutionRowRecord = new ArrayList<String>();
			 solutionRowRecord.addAll (Arrays.asList(adecisionVector));
			 solutionRowRecord.addAll (Arrays.asList(anObjective));
			 decisionVectorAndObjectives.add( solutionRowRecord);
			 
		 }
		 return decisionVectorAndObjectives;
	}
	public static  LinkedHashMap<String, List<ArrayList<String>>> getShortlist ( SolutionDetail anAlgorithmSolutionDetail, String algorithm, int run) throws JMException{

		 LinkedHashMap<String, List<ArrayList<String>>> results = new  LinkedHashMap<String, List<ArrayList<String>>>();
		 //SolutionDetail anAlgorithmSolutionDetail = solutionDetail.get(algorithm + "." + run);
		 
		 SolutionSet nonDomSolution= anAlgorithmSolutionDetail.getNonDominatedSolutions();
		 SolutionSet domSolution= anAlgorithmSolutionDetail.getDominatedSolutions();
		 
		 List<String> nonDominatedSolutionsDecisionVector = getSelectedOptions(nonDomSolution);
		 ArrayList<String> nonDominatedSolutionsObjective= getSolutionsObjectives(nonDomSolution);
		 
		 List<Objective> objList = new ArrayList<Objective>(anAlgorithmSolutionDetail.getGoalModelParserEngine().getSemanticModel().getObjectives().values());
		 
		 List<String> dominatedSolutionsDecisionVector = getSelectedOptions(domSolution);
		 ArrayList<String> dominatedSolutionsObjective= getSolutionsObjectives(domSolution);
		 
		 LinkedHashMap<String, ArrayList<String>> dominatedSolutions = uniqueDominatedSolutions(dominatedSolutionsDecisionVector,nonDominatedSolutionsDecisionVector,dominatedSolutionsObjective,nonDominatedSolutionsObjective);
		 dominatedSolutionsDecisionVector = dominatedSolutions.get("Decision");
		 dominatedSolutionsObjective = dominatedSolutions.get("Objective");
		 
		 ArrayList<ArrayList<String>> dominatedDecisionVectorAndObjectives = solutionInTableFormat (dominatedSolutionsDecisionVector,dominatedSolutionsObjective,objList);
		 
		 ArrayList<ArrayList<String>> nonDominatedDecisionVectorAndObjectives = solutionInTableFormat (nonDominatedSolutionsDecisionVector,nonDominatedSolutionsObjective,objList);
		
		 // remove optimal from all solutions
		 if (nonDomSolution.size() != domSolution.size()){
			 results.put("AllSolutions", dominatedDecisionVectorAndObjectives);
		 }else{
			 // if the dominated and non diminated solutions are equal. dont display.
			 results.put("AllSolutions", new ArrayList<ArrayList<String>>());
		 }
		 results.put("OptimalSolutions", nonDominatedDecisionVectorAndObjectives);
		 return results; 
	}
	private static LinkedHashMap<String, ArrayList<String>>  uniqueDominatedSolutions (List<String> dominatedDecisionVector, List<String> nonDominatedDecisionVector, List<String> dominatedObjective, List<String> nonDominatedObjective  ){
		 // remove the optimal solutions from all the solutions.
		 ArrayList<Integer> toRemove = new ArrayList<Integer>();
		 for (int i =0; i < dominatedDecisionVector.size(); i++ ){
			 String dominatedDecisionVectorAndObjective = "";
			 dominatedDecisionVectorAndObjective = dominatedDecisionVector.get(i)+ dominatedObjective.get(i);
			 for (int k=0; k< nonDominatedDecisionVector.size(); k++){
				 String nonDominatedDecisionVectorAndObjective = "";
				 nonDominatedDecisionVectorAndObjective = nonDominatedDecisionVector.get(k)+ nonDominatedObjective.get(k);
				 if (dominatedDecisionVectorAndObjective.equals(nonDominatedDecisionVectorAndObjective)){
					 toRemove.add(i);
				 }
				 nonDominatedDecisionVectorAndObjective ="";
			 }
		 }
		 
		 ArrayList<String> clonedDominatedDecisionVector = new ArrayList<String>();
		 ArrayList<String> clonedDominatedObjective = new ArrayList<String>();
		 for (int i =0; i<dominatedDecisionVector.size(); i++ ){
			 if( !toRemove.contains((Integer)i) ){
				 clonedDominatedDecisionVector.add(dominatedDecisionVector.get(i));
				 clonedDominatedObjective.add( dominatedObjective.get(i));
			 }
		 }
		 
		 LinkedHashMap<String, ArrayList<String>> dominatedSolutions = new LinkedHashMap<String, ArrayList<String>>();
		 dominatedSolutions.put("Decision", clonedDominatedDecisionVector);
		 dominatedSolutions.put("Objective", clonedDominatedObjective);
		 return dominatedSolutions;
	}

	
	public static String getResultHeader ( Model sematicModel){
		String outputHeader = "ID";
		for (Map.Entry<String, Decision> entry: sematicModel.getDecisions().entrySet()){
			outputHeader+= "," + entry.getKey();
		}
		for (Map.Entry<String, Objective> entry: sematicModel.getObjectives().entrySet()){
			outputHeader+= "," + entry.getKey();

		}
		outputHeader += ",Optimal";
		return outputHeader;
	}
	private static   List<String> getSelectedOptions ( SolutionSet solutions) throws JMException{
		 List<String> solutionsDecisionVector = new ArrayList<String>();
		 for (int i=0; i < solutions.size(); i++){
			 	Alternative alternative  = convertDecisionVectorToSolution(solutions.get(i).getArrayBitVectorVariables(), solutions.get(i).getProblem().getParserEngine().getSemanticModel());
	            String entryRecord ="";
	        	for (Map.Entry<Decision, String> entry: alternative.getSelection().entrySet()){
	        		entryRecord +=  entry.getValue() + ",";
	            }
	        	entryRecord= entryRecord.substring(0, entryRecord.length()-1);
	        	solutionsDecisionVector.add(entryRecord);
		 }
		 return solutionsDecisionVector;
	}
	public static List<Alternative> getAllAlternative(Map<String, Decision> decisions){
		List<Alternative> solutions = new ArrayList<Alternative>();
		List<Decision> allDecisions = new ArrayList<Decision>(decisions.values());
		ArrayList<Integer[]> selectedOptionIndices = generateSelectedOptionIndices (decisions);
		String print = "";
		for (int i =0; i <selectedOptionIndices.size(); i++ ){
			Integer [] aSelectedOptionIndex = selectedOptionIndices.get(i);
			Alternative s = new Alternative();
			for (int j =0 ; j < aSelectedOptionIndex.length; j ++){
				Decision d = allDecisions.get(j);
				String selectedOption = allDecisions.get(j).getOptions().get(aSelectedOptionIndex[j]);
				s.addDecision(d, selectedOption);
				print += selectedOption + ",";
			}
			print += "\n";
			
			solutions.add(s);
		}
		//System.out.print(print);
		return solutions;
	}
	private static ArrayList<Integer[]> rowtoColumn (ArrayList<Integer[]> rowArray){
    	ArrayList<Integer[]> columnArray = new ArrayList<Integer[]>();
    	int column = rowArray.get(0).length;
    	int row = rowArray.size();
    	for (int c = 0 ; c < column ; c++) {
    		Integer[] rows = new Integer[row];
    	    for (int r = 0 ; r < row ; r++) {
    	    	rows[r] = rowArray.get(r)[c];
    	    }
    	    columnArray.add(rows);
    	}
    	return columnArray;
	}
	public static int getTotalOptions (Model semanticModel){
		Map<String, Decision> decisions  = semanticModel.getDecisions();
		int counter =0;
	    if(decisions != null){
	    	for (Map.Entry<String, Decision> entry : decisions.entrySet() ){
	    		counter= entry.getValue().getOptions().size();
        	}
	    }
	    return counter;
	}
	public static Alternative convertDecisionVectorToSolution (List<Variable[]> decisionVector, Model semanticModel) throws JMException{
		Alternative result = new Alternative ();
		Map<String, Decision> decisions = semanticModel.getDecisions();
		List<Decision> decisionList = new ArrayList<Decision>(decisions.values());
		for (int i =0; i < decisionVector.size(); i++ ){
			 String selectedOption = "";
			 for (int j =0; j < decisionVector.get(i).length; j++ ){
				 if ((int)decisionVector.get(i)[j].getValue() == 1){
					 selectedOption = decisionList.get(i).getOptions().get(j);
				 }
			 }
			 result.addDecision(decisionList.get(i), selectedOption);
		}
		
		return result;
	}
	private static int [] getArrayOptionsCount (Map<String, Decision> decisions){
		int[] result = null;
        if(decisions != null){
        	result = new int[decisions.size()];
        	int j =0;
        	for (Map.Entry<String, Decision> entry : decisions.entrySet() ){
        		result[j]= entry.getValue().getOptions().size();
        		j++;
        	}
        }else{
			throw new RuntimeException ("Optimisation error: decision vector block cannot be empty, ensure you specify alternatives in the model.");
		}
        return result;
	}
	private static ArrayList<Integer[]> generateSelectedOptionIndices(Map<String, Decision> decisions){
	     ArrayList<Integer[]> result = new ArrayList<Integer[]>();
    	int []decisionOptionsCount = getArrayOptionsCount (decisions);
        // added this part to cater for just one single decision.
        if(decisionOptionsCount.length == 1){
        	Integer[] optionIndex = null;
    		for(int i = 0; i < decisionOptionsCount[0]; i++ ){
    			optionIndex =  new Integer[]{0};
    			optionIndex[0] =i;
    			result.add(optionIndex);    			
    		}
        }else{
        	result = getSelectedOptionIndices(decisionOptionsCount,result);
        }
        return result;
	}
	private static ArrayList<Integer[]> getSelectedOptionIndices(int[] decisionOptionCount, ArrayList<Integer[]> results ){
    	if(decisionOptionCount.length == 1){
    		ArrayList<Integer[]> alternatives = new ArrayList<Integer[]>();
    		Integer[] optionIndex =  new Integer[decisionOptionCount[0]];
    		for(int i = 0; i < decisionOptionCount[0]; i++ ){
    			optionIndex[i] = i;    			
    		}
    		alternatives.add(optionIndex);
    		return alternatives;    		
    	}
    	else{
    		int head = decisionOptionCount[0];
    		int [] tail  = new int[decisionOptionCount.length -1];
    		for (int i = 0; i < decisionOptionCount.length -1; i++ ){
    			tail[i] = decisionOptionCount[i+1];
    		}
    		ArrayList<Integer[]> tempAltenatives = getSelectedOptionIndices(tail,results);
    		int n=0; // the value by which to repeat the next option for tempAltenatives. 
    		n =  (tempAltenatives.size() <= 1) ? tempAltenatives.get(0).length:tempAltenatives.size(); 
    		for (int i =0; i< head; i++){
    			Integer[] repeatedColumnOption =  new Integer[n]; // to be repeated n times for each previously listed option indices.
    			for(int j=0; j < n; j++){
    				repeatedColumnOption[j]= i;
    			}
    			ArrayList<Integer[]> repeatedColumnOptionArray = new ArrayList<Integer[]>();    			
    			repeatedColumnOptionArray.add(repeatedColumnOption);   
    			
    			// this check ensures that the first head aligns with its inner tails that have been recursively obtained.
    			if(tempAltenatives.size() != repeatedColumnOptionArray.size() && tempAltenatives.size() == repeatedColumnOption.length ){ 
    				tempAltenatives = rowtoColumn(tempAltenatives); // this converts temp back to row so that we can combine it with innerrepeatedColumnOptionArray
    				ArrayList<Integer[]> innerResults = new ArrayList<Integer[]>();
    				for(int k =0 ; k < head; k++){ 
    					ArrayList<Integer[]> innercombinedRepeatedColumnOptionArray = new ArrayList<Integer[]>();
    	    			Integer[]innerRepeatedColumnOption =  new Integer[n];
    	    			for(int j=0; j < n; j++){
    	    				innerRepeatedColumnOption[j]= k;
    	    			}
    	    			ArrayList<Integer[]> innerRepeatedColumnOptionArray = new ArrayList<Integer[]>();    			
    	    			innerRepeatedColumnOptionArray.add(innerRepeatedColumnOption);
    	    			innercombinedRepeatedColumnOptionArray.addAll(innerRepeatedColumnOptionArray);
            			innercombinedRepeatedColumnOptionArray.addAll(tempAltenatives);
            			ArrayList<Integer[]> innerconvertedcombinedRepeatedColumnOptionArray = rowtoColumn(innercombinedRepeatedColumnOptionArray);
            			innerResults.addAll(innerconvertedcombinedRepeatedColumnOptionArray);
    				}
    				return innerResults; // this is what is eventually returned after we consider the first head and the rest of the returned outcome for tail.
    				    				
    			}
    			ArrayList<Integer[]> combinedRepeatedColumnOptionArray = new ArrayList<Integer[]>(); 
    			combinedRepeatedColumnOptionArray.addAll(repeatedColumnOptionArray);
    			combinedRepeatedColumnOptionArray.addAll(tempAltenatives);
    			ArrayList<Integer[]> convertedcombinedRepeatedColumnOptionArray = rowtoColumn(combinedRepeatedColumnOptionArray);
    			results.addAll(convertedcombinedRepeatedColumnOptionArray); 
    			
    		}  		
    		return results;
    	}
   }
}
