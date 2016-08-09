package radar.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.utilities.Helper;

public class AnalysisResult {
	// the optimisation objectives. 
	private List<Objective> objectives;
	
	private List<Decision> decisions;

	//value.get(s)[j] gives the value of solution s for the j^th objective
	private Map<Solution, double[]> value; 

	// the list of shortlisted solutions
	private Map<Solution, double[]> shortlist;
	

	// objective with respect to which information value is measured
	private Objective eviObjective;

	private double evtpi;
	
	private int solutionSpace;
	
	private long runtime;

	// mapping from parameter's label to parameter's evppi
	private Map<String, Double> evppi;

	AnalysisResult(List<Objective> objs,  List<Decision> ds){
		objectives = objs;
		decisions = ds;
		value = new LinkedHashMap<Solution, double[]>();
		evppi = new LinkedHashMap<String, Double>();
	}
	public void addEvaluation (Solution s, double [] objValues){
		value.put(s, objValues);
	}
	
	public Map<Solution, double[]> getEvaluatedSolutions (){
		return value;
	}
	
	public void addShortlist (Map<Solution, double[]> optimalSolns){
		shortlist= optimalSolns;
	}
	public List<Solution> getShortList (){
		return new ArrayList<Solution>(shortlist.keySet());
	}
	public void addEVTPI (double evtpi_value){
		evtpi = evtpi_value;
	}
	
	void addEVPPI(String param, Double n){
		evppi.put(param, n);
	}
	
	void addEviObjective (Objective eviObj){
		eviObjective = eviObj;
	}
	void addSolutionSpace (int solnSpace){
		solutionSpace = solnSpace;
	}
	void addRunTime (long time){
		runtime = time;
	}
	public String generateSolutionHeader (){
		String result ="ID \t";
		for(int i =0 ; i  < decisions.size(); i ++){
			result +=decisions.get(i).getDecisionLabel() + "\t";
		}
		for(int j=0; j < objectives.size(); j++){
			result +=objectives.get(j).getLabel() + "\t";
		}
		result += "Optimal";
		return result;
	}
	public String objectivesToString (){
		String result ="";
		for(int j=0; j < objectives.size(); j++){
			String optimisationDirection = objectives.get(j).getIsMinimisation() ? "Min" : "Max";
			result += "Objective: " + "\t\t" + optimisationDirection + objectives.get(j).getLabel() +"\n" ;
		}
		return result;
	}
	public String analysisToString (){
		StringBuilder analysisResult = new StringBuilder();
		analysisResult.append ("Optimisation Analysis \n");
		analysisResult.append ("============================ \n\n");
		analysisResult.append(objectivesToString());
		analysisResult.append ("SolutionSpace: \t\t\t" + solutionSpace +"\n");
		analysisResult.append ("Evaluated: \t\t\t" + value.size() +"\n");
		analysisResult.append ("Shortlisted: \t\t\t" + shortlist.size() +"\n");
		analysisResult.append ("Runtime(s): \t\t\t" + runtime +"\n\n");
		String shortlistHeader = generateSolutionHeader();
		analysisResult.append(shortlistHeader);
		analysisResult.append("\n");
		String optimalSoln = getSolutionsToCSV(0, shortlist, true);
		analysisResult.append(optimalSoln);
		Map<Solution, double []> nonOptimalSolutions =getNonOptimalSolutions();
		String otherSolutions = getSolutionsToCSV(shortlist.size(), nonOptimalSolutions, false);
		analysisResult.append(otherSolutions);
		analysisResult.append ("\n");
		analysisResult.append ("Information Value Analysis \n");
		analysisResult.append ("===============================\n\n");
		analysisResult.append(informationValueToString());
		return analysisResult.toString();
	}
	
	private String getSolutionsToCSV(int offset, Map<Solution, double[]> solutions, boolean isOptimal ){
		StringBuilder result = new StringBuilder();
		for (int index = 0; index < solutions.size(); index++) {
			String row = "";
			row += (index+ 1 + offset + "\t");
	        String record = "";
	        
	        record += optimalDecisionsToString (index, "\t", solutions);
	        record += optimalObjectiveToString (index, "\t", solutions);
	        
	        row += (record);
	        String optimal = isOptimal == true ? "Yes" :"No";
	        row += (optimal + "\n");
	        result.append(row);
	    }
		return result.toString();
	}
	private Map<Solution, double []> getNonOptimalSolutions (){
		List<Integer> solutionIndexToRemove = new ArrayList<Integer>();
		int  i=0;
		for (Map.Entry<Solution, double []> allSolutionEntry : value.entrySet()){
			int j =0;
			for (Map.Entry<Solution, double []> optimalSolutionEntry:shortlist.entrySet() ){
				if (allSolutionEntry.getKey().selectionToString().equals(optimalSolutionEntry.getKey().selectionToString())){
					solutionIndexToRemove.add(i);
				}
			}
		}
		Map<Solution, double[]> nonOptimalSolutions = new LinkedHashMap<Solution, double[]>();
		int  k=0;
		for (Map.Entry<Solution, double []> allSolutionEntry : value.entrySet()){
			if( !solutionIndexToRemove.contains((Integer)k) ){
				 nonOptimalSolutions.put(allSolutionEntry.getKey(), allSolutionEntry.getValue());
			 }
		}
		return nonOptimalSolutions;
	}
	private String optimalObjectiveToString (int index, String separator, Map<Solution, double[]> solutions){
		String record ="";
		List<double []> objValues = new ArrayList<double []>(solutions.values());
		int i =0;
		for(double  entry: objValues.get(index)){
			double value = objectives.get(i).getIsMinimisation() == true? entry: entry *-1;
        	record += value + separator;
        	i++;
        }
		return record;
	}
	private String optimalDecisionsToString (int index,String separator, Map<Solution, double[]> solutions){
		String record ="";
		List<Solution> solutionList = new ArrayList<Solution>(solutions.keySet());
		Map<Decision, String>  selection = solutionList.get(index).getSelection();
		for (Map.Entry<Decision, String> entry: selection.entrySet()){
			record += entry.getValue() + separator;
		}
		return record;
	}
	private String informationValueToString(){
		String result = "";
		result += "Objective: \t\t\t" + eviObjective.getLabel() + "\n";
		result += "EVTPI: \t\t\t "+ evtpi + "\n\n";
		result += "Parameter \t\t\t EVPPI \n";
		for (Map.Entry<String, Double> entry: evppi.entrySet()){
			result += entry.getKey() + ": \t\t\t "+ entry.getValue() +"\n";
		}
		return result;
	}
	public String decisionsToCSV ( ){
		StringBuilder decision = new StringBuilder ();
		int count =1;
		decision.append("ID , Decision , Options\n");
		for (int i =0; i <  decisions.size(); i ++){
			StringBuilder row = new StringBuilder();
			row.append(count + ",");
			row.append(decisions.get(i).getDecisionLabel() + ",");
			String options ="";
			options ="[";
			List<String> decisionOptions = decisions.get(i).getOptions();
			for (String option : decisionOptions){
				options += option + ", ";
			}
			options = options.trim(); 
			options = options.substring(0, options.length()-1);
			options +="]";
			row.append(options + "\n");
			decision.append(row.toString());
			count++;
		}
		return decision.toString();
	}
	public  String getReferenceObjectives () {
		String record = "";
		for (int index = 0; index < shortlist.size(); index++) {
			record += optimalObjectiveToString (index, " ", shortlist);
			record += "\n";
		}
		return record;
	}
	public String getReferenceDecisions (){
		String record = "";
		for (int index = 0; index < shortlist.size(); index++) {
			record += optimalDecisionsToString (index, " ",  shortlist);
			record += "\n";
		}
		return record;
	}
	

	
}
