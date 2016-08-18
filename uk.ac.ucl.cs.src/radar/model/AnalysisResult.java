package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.utilities.TableBuilder;


public class AnalysisResult {
	// the optimisation objectives. 
	private List<Objective> objectives;
	
	private List<Decision> decisions;

	//value.get(s)[j] gives the value of solution s for the j^th objective
	private Map<Solution, double[]> value; 

	// the list of shortlisted solutions
	private Map<Solution, double[]> shortlist;
	
	// the list of all generated solutions
	private List<Solution> allSolutions;

	// objective with respect to which information value is measured
	private Objective eviObjective;
	
	private Objective subGraphObjective;

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
	public List<Solution> getAllSolutions(){
		return allSolutions;
	}
	public void addShortlist (Map<Solution, double[]> optimalSolns){
		shortlist= optimalSolns;
	}
	public List<Solution> getShortListSolutions (){
		return new ArrayList<Solution>(shortlist.keySet());
	}
	public List<double[]> getShortListObjectives (){
		return new ArrayList<double[]>(shortlist.values());
	}
	public List<double[]> getEvaluatedObjectives (){
		return new ArrayList<double[]>(value.values());
	}
	public void addEVTPI (double evtpi_value){
		evtpi = evtpi_value;
	}
	void addAllSolutions (List<Solution> allSolns){
		allSolutions = allSolns;
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
	void addSubGraphObejctive (Objective subGraphObj){
		subGraphObjective =subGraphObj;
	}
	public List<Objective> getObjectives (){
		return objectives;
	}
	public Objective getSubGraphObjective (){
		return subGraphObjective;
	}
	public String generateSolutionHeader ( String separator){
		
		// we can't use decision to generate the header due to change in the getAllSolutions
		String result ="ID" + separator;
		for(int i =0 ; i  < decisions.size(); i ++){
			result += decisions.get(i).getDecisionLabel() + separator;
		}
		for(int j=0; j < objectives.size(); j++){
			result +=objectives.get(j).getLabel() + separator;
		}
		result += "Optimal";
		return result;
	}
	public void generateSolutionHeader2 (TableBuilder tableBuilder){
		String result ="ID" + ",";
		for(int i =0 ; i  < decisions.size(); i ++){
			result += decisions.get(i).getDecisionLabel() + ",";
		}
		for(int j=0; j < objectives.size(); j++){
			result +=objectives.get(j).getLabel() + ",";
		}
		result += "Optimal";
		tableBuilder.addRow(result.split(","));
	}
	
	public String objectivesToString (){
		String result ="";
		for(int j=0; j < objectives.size(); j++){
			String optimisationDirection = objectives.get(j).getIsMinimisation() ? "Min" : "Max";
			result += "Objective: " + "\t\t" + optimisationDirection + objectives.get(j).getLabel() +"\n" ;
		}
		return result;
	}
	String getOptimisationAnalysisResult (){
		TableBuilder tableBuilder = new TableBuilder ();
		tableBuilder.addRow (new String []{"Optimisation", "Analysis"});
		tableBuilder.addRow ("----------------------", "\n");
		for(int j=0; j < objectives.size(); j++){
			String optimisationDirection = objectives.get(j).getIsMinimisation() ? "Min" : "Max";
			tableBuilder.addRow (new String []{"Objective: ",optimisationDirection + objectives.get(j).getLabel() +"\n" });
		}
		tableBuilder.addRow ("SolutionSpace:", "solutionSpace\n");
		tableBuilder.addRow ("Evaluated:", value.size() +"\n");
		tableBuilder.addRow ("Shortlisted:", shortlist.size() +"\n");
		tableBuilder.addRow ("Runtime(s):", runtime +"\n");
		return tableBuilder.toString();
	}
	public List<String> optimisationAnalysisDetails (){
		List<String> result = new ArrayList<String> ();
		for(int j=0; j < objectives.size(); j++){
			String optimisationDirection = objectives.get(j).getIsMinimisation() ? "Min" : "Max";
			result.add ("Objective ,"+optimisationDirection + objectives.get(j).getLabel() +"\n");
		}
		result.add ("SolutionSpace ," + solutionSpace);
		result.add ("Evaluated ,"+ value.size());
		result.add ("Shortlisted ,"+ shortlist.size() );
		result.add ("Runtime(s) ,"+ runtime);
		return result;
	}
	public List<String> infoValueDetails (){
		List<String> result = new ArrayList<String> ();
		if (eviObjective != null){
			result.add ("Objective,"+ eviObjective.getLabel());
			result.add("EVTPI," + evtpi );
			result.add ("Parameter,"+ "EVPPI \n");
			for (Map.Entry<String, Double> entry: evppi.entrySet()){
				result.add(entry.getKey()+ ","+entry.getValue() );
			}
		}
		return result;
	}
	public String analysisToStringNew (){
		StringBuilder analysisResult = new StringBuilder();
		analysisResult.append (getOptimisationAnalysisResult());
		TableBuilder resultBuilder = new TableBuilder ();
		generateSolutionHeader2(resultBuilder);
		getSolutionsToCSVNew(0, shortlist, true,resultBuilder);
		Map<Solution, double []> nonOptimalSolutions =getNonOptimalSolutions();
		getSolutionsToCSVNew(shortlist.size(), nonOptimalSolutions, false,resultBuilder);
		analysisResult.append (resultBuilder.toString());
		analysisResult.append ("\n");
		if (eviObjective != null){
			String infoAnalysis =informationValueToString2();
			analysisResult.append (infoAnalysis);
		}
		System.out.println ("Analysis result: \n"+analysisResult.toString());
		return analysisResult.toString();
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
		String shortlistHeader = generateSolutionHeader("\t");
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
		if (eviObjective != null){
			analysisResult.append(informationValueToString());
		}
		return analysisResult.toString();
	}
	String getSolutionTableRow (int index, int offset, Map<Solution, double[]> solutions, boolean isOptimal ){
		String row = "";
		row += (index+ offset+ 1 ) + "," ;
        String record = "";
        record += optimalDecisionsToString (index, ",", shortlist);
        record += optimalObjectiveToString (index, ",", shortlist);
        row += (record);
        String optimal = isOptimal == true ? "Yes" :"No";
        row += (optimal + "\n");
        return row;
	}
	public String getSolutionTableColumnIdentifier (){
		String shortlistHeader = generateSolutionHeader(",");
		return shortlistHeader;
	}
	public List<String> solutionTable (){
		List<String> result = new ArrayList<String>();
		for (int i =0; i < shortlist.size(); i++){
			result.add(getSolutionTableRow(i, 0, shortlist, true));
		}
		Map<Solution, double []> nonOptimalSolutions =getNonOptimalSolutions();
		for (int i =0; i < nonOptimalSolutions.size(); i++){
			result.add(getSolutionTableRow(i, shortlist.size(), nonOptimalSolutions, true));
		}
		return result;
		
	}
	private void getSolutionsToCSVNew(int offset, Map<Solution, double[]> solutions, boolean isOptimal ,TableBuilder table){
		if (solutions.size() > 0){
			for (int index = 0; index < solutions.size(); index++) {
				String row = "";
				row += (index+ 1 + offset) + "," ;
		        String record = "";
		        record += optimalDecisionsToString (index, ",", solutions);
		        record += optimalObjectiveToString (index, ",", solutions);
		        
		        row += (record);
		        String optimal = isOptimal == true ? "Yes" :"No";
		        row += (optimal + "\n");
		        table.addRow(row.split(","));
		    }
		}
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
			for (Map.Entry<Solution, double []> optimalSolutionEntry:shortlist.entrySet() ){
				if (allSolutionEntry.getKey().selectionToString().equals(optimalSolutionEntry.getKey().selectionToString())){
					solutionIndexToRemove.add(i);
					break;
				}
			}
			i++;
		}
		Map<Solution, double[]> nonOptimalSolutions = new LinkedHashMap<Solution, double[]>();
		int  k=0;
		for (Map.Entry<Solution, double []> allSolutionEntry : value.entrySet()){
			if( !solutionIndexToRemove.contains((Integer)k) ){
				 nonOptimalSolutions.put(allSolutionEntry.getKey(), allSolutionEntry.getValue());
			 }
			k++;
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
		// we now use deicision to get the option because we do not know the order in which the selections are in solution.
		for(int i =0 ; i  < decisions.size(); i ++){
			String decision = decisions.get(i).getDecisionLabel();
			for (Map.Entry<Decision, String> entry: selection.entrySet()){
				if (decision.equals(entry.getKey().getDecisionLabel())){
					record += entry.getValue() + separator;
					break;
				}
			}
		}
		
		/*for (Map.Entry<Decision, String> entry: selection.entrySet()){
			record += entry.getValue() + separator;
		}*/
		return record;
	}
	public  String informationValueToString(){
		String result = "";
		result += "Objective: \t\t\t" + eviObjective.getLabel() + "\n";
		result += "EVTPI: \t\t\t "+ evtpi + "\n\n";
		result += "Parameter \t\t\t EVPPI \n";
		for (Map.Entry<String, Double> entry: evppi.entrySet()){
			result += entry.getKey() + ": \t\t\t "+ entry.getValue() +"\n";
		}
		return result;
	}
	public  String informationValueToString2(){
		TableBuilder result = new TableBuilder();
		result.addRow ("Information Value Analysis", "\n");
		result.addRow ("--------------------------", "\n\n");
		result.addRow ("Objective:", eviObjective.getLabel() + "\n");
		result.addRow ("EVTPI:", evtpi + "\n\n");
		result.addRow ("Parameter", "EVPPI \n");
		result.addRow ("----------","----- \n\n");
		for (Map.Entry<String, Double> entry: evppi.entrySet()){
			result.addRow(new String[]{ entry.getKey() ,  entry.getValue() +"\n"});
		}
		return result.toString();
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
