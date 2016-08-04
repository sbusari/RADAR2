package radar.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.jmetal.core.SolutionSet;
import radar.jmetal.core.Variable;
import radar.jmetal.util.JMException;
import radar.model.Alternative;
import radar.model.Decision;
import radar.model.Model;
import radar.model.Objective;

public class Helper {

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
			 //result.addDecisionToGlobalSelection(decisionList.get(i), selectedOption);
		}
		
		return result;
	}
	public static void printResults (String directory, String expResult, String fileName) throws IOException{
		File resultFile;
		resultFile = new File(directory);
		if (!resultFile.exists()) {
			new File(directory).mkdirs();
			System.out.println("Creating " + directory);
		}
	      FileOutputStream fos   = new FileOutputStream(directory + fileName ) ;
	      OutputStreamWriter osw = new OutputStreamWriter(fos)    ;
	      BufferedWriter bw      = new BufferedWriter(osw)        ;
	      bw.write(expResult);
	      bw.close();
	}
	@SuppressWarnings("resource")
	public static String readFile(String fileName) {
		StringBuilder model = new StringBuilder(100);
		BufferedReader bfr = null;

		try {
			bfr = new BufferedReader(new FileReader(new File(fileName)));
			String line = null;
			while ((line = bfr.readLine()) != null) {
				model.append(line + "\n");
			}
			model.delete(model.length() - 1, model.length());
			return model.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void printResult (String path, String infoValue, String fileName) throws IOException{
		File resultFile;
		String directory;
		directory = path;

		resultFile = new File(directory);
		if (!resultFile.exists()) {
			boolean result = new File(directory).mkdirs();
			System.out.println("Creating " + directory);
		}
		
	      FileOutputStream fos   = new FileOutputStream(directory + "/"+ fileName, true ) ;
	      OutputStreamWriter osw = new OutputStreamWriter(fos)    ;
	      BufferedWriter bw      = new BufferedWriter(osw)        ;
	      bw.write(infoValue);
	      bw.close();
	}
	public static ArrayList<String> readDecisionVectorsString(String path) {
	    try {
		      FileInputStream fis   = new FileInputStream(path)     ;
		      InputStreamReader isr = new InputStreamReader(fis)    ;
		      BufferedReader br      = new BufferedReader(isr)      ;
		      ArrayList<String> decisionVectors = new ArrayList<String>();
		      String aux = br.readLine();
		      while (aux!= null) {
		    	decisionVectors.add(aux);
		        aux = br.readLine();
		      }
		      br.close();
		      return decisionVectors;
	    } catch (Exception e) {
	      System.out.println("goalModel.util.decisionVectorString: "+path);
	      e.printStackTrace();
	    }
	    return null;
	  } 

}
