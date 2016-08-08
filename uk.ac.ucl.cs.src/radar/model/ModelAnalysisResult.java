package radar.model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import radar.utilities.Helper;


public class ModelAnalysisResult {

	List<Objective> obejctives_;
	List<SolutionValues> optimalSolutions_;
	List<SolutionValues> allSolutions_;
	List<String> shortlist_;
	String variableDependencyGraph_;
	String decisionDependencyGraph_;
	Model semanticModel_;
	OptimisationType optimisationType_;
	Algorithm alg_;
	String outputDirectory_;
	String algorithmName_;
	ExperimentData expData_;
	InfoValueAnalysisResult infoValueResult;
	SolutionQuality solutionquality_;
	public ModelAnalysisResult( String algorithmName, ExperimentData expData){
		algorithmName_ = algorithmName;
		expData_ = expData;
		obejctives_ = new ArrayList<Objective>(expData.getSemanticModel().getObjectives().values());
		semanticModel_ = expData_.getSemanticModel();
		optimisationType_ =expData_.getTypeOfOptimisation();;
		alg_ = getAlgorithm(optimisationType_);
		optimalSolutions_ = new ArrayList<SolutionValues>();
		allSolutions_ =  new ArrayList<SolutionValues>();
		shortlist_ = new ArrayList<String>();
		
	}
	Algorithm getAlgorithm (OptimisationType optimisationType){
		Algorithm alg = null;
		if (optimisationType.equals(OptimisationType.EXACT)){
			alg = new ExhaustiveSearch (semanticModel_);
		}else{
			alg = new SbseAlgorithm (algorithmName_, expData_);
		}
		return alg;
	}
	
	public void analyse(){	
		// stochastic simulation and  multi-objective optimisation
		long start = System.currentTimeMillis();
		optimalSolutions_ =  alg_.solve();
		long end = System.currentTimeMillis();
		long runTime = (end - start) / 1000;
		System.err.println(" execution time used is " + runTime);
		
		List<Solution> optimalSolutions = new ArrayList<Solution>();
		for (int i =0; i < optimalSolutions_.size(); i ++){
			Solution s = optimalSolutions_.get(i).getSolution();
			optimalSolutions.add(s);
		}
		// get all simulated solutions
		allSolutions_ =  allSimulatedSolution ();
		
		// perform information value analysis
		Objective infoValueObjective = semanticModel_.getObjectives().get(expData_.getInformationValueObjective());
		if (infoValueObjective != null){
			List<String> paramNames = semanticModel_.getParameters();
			List<Parameter> parameters = Model.getParameterList(paramNames, semanticModel_);
			infoValueResult = semanticModel_.computeInformationValue(infoValueObjective, optimalSolutions, parameters);
		}
		// generate variable dependency graphs
		variableDependencyGraph_ = generateVariableDependencyGraph();

		// generate decision graph
		decisionDependencyGraph_ = generateDecisionDependencyGraph();
		
	}
	
	
	public List<String> getShortList (){
		return shortlist_;
	}
	public List<SolutionValues> getOptimalSolutions (){
		return optimalSolutions_;
	}
	long getRunTime (){
		return alg_.getRunTime();
	}
	public List<SolutionValues> allSimulatedSolution (){
		List<SolutionValues> results = new ArrayList<SolutionValues>();
		if (alg_ instanceof ExhaustiveSearch){
			results =((ExhaustiveSearch)alg_).getAllSolutionValues();;
		}else{
			results = ((SbseAlgorithm)alg_).getAllSolutionValues();
		}
		return results;
	}
	public List<Objective> getObjectives(){
		return obejctives_;
	}
	public Model getSemanticModel (){
		return semanticModel_;
	}
	public String getVariableDependencyGraph(){
		return variableDependencyGraph_;
	}
	public String getDecisionDependencyGraph(){
		return decisionDependencyGraph_;
	}
	
	public String generateResultHeader (){
		String result =",";
		for(Map.Entry<String ,Decision> entry: semanticModel_.getDecisions().entrySet()){
			result +=entry.getValue().getDecisionLabel() + ",";
		}
		for(Map.Entry<String, Objective> entry: semanticModel_.getObjectives().entrySet()){
			result +=entry.getValue().getLabel() + ",";
		}
		result += "Optimal";
		return result;
	}
	
	private String optimalObjectiveToString (int index, String separator, List<SolutionValues> solutions){
		String record ="";
		for(Map.Entry<Objective, Double> entry: solutions.get(index).getObjectiveValue().entrySet()){
        	double value= (entry.getKey().getIsMinimisation())? entry.getValue():  (-1 * entry.getValue());
        	record += value + separator;
        }
		return record;
	}
	private String optimalDecisionsToString (int index,String separator, List<SolutionValues> solutions){
		String record ="";
		for(Map.Entry<Decision, String> entry: solutions.get(index).getSolution().getSelection().entrySet()){
        	record += entry.getValue() + separator;
        }
		return record;
	}

	private String getSolutionsToCSV(int offset, List<SolutionValues> solutions, boolean isOptimal ){
		StringBuilder result = new StringBuilder();
		for (int index = 0; index < solutions.size(); index++) {
			String row = "";
			row += (index+ 1 + offset + ",");
	        String record = "";
	        
	        record += optimalDecisionsToString (index, ",", solutions);
	        record += optimalObjectiveToString (index, ",", solutions);
	        
	        row += (record);
	        String optimal = isOptimal == true ? "Yes" :"No";
	        row += (optimal + "\n");
	        result.append(row);
	    }
		return result.toString();
	}
	public String shortlistToCSV (){
		StringBuilder solutions = new StringBuilder();
		String shortlistHeader = generateResultHeader();
		solutions.append(shortlistHeader);
		solutions.append("\n");
		String optimalSolutions = getSolutionsToCSV(0, optimalSolutions_, true);
		solutions.append(optimalSolutions);
		List<SolutionValues> nonOptimalSolutions =getNonOptimalSolutions();
		String otherSolutions = getSolutionsToCSV(optimalSolutions_.size(), nonOptimalSolutions, false);
		solutions.append(otherSolutions);
		return solutions.toString();
	}
	private List<SolutionValues> getNonOptimalSolutions (){
		
		List<Integer> solutionIndexToRemove = new ArrayList<Integer>();
		for (int i =0; i < allSolutions_.size(); i++){
			for (int j=0; j < optimalSolutions_.size(); j++){
				if (allSolutions_.get(i).getSolution().selectionToString().equals(optimalSolutions_.get(j).getSolution().selectionToString())){
					solutionIndexToRemove.add(i);
				}
			}
		}
		
		List<SolutionValues> nonOptimalSolutions = new ArrayList<SolutionValues>();
		for (int i =0; i<allSolutions_.size(); i++ ){
			 if( !solutionIndexToRemove.contains((Integer)i) ){
				 nonOptimalSolutions.add(allSolutions_.get(i));
			 }
		}
		return nonOptimalSolutions;
	}
	public String generateDecisionDependencyGraph (){
		String result ="digraph G { \n";
		result += "rankdir = BT; \n";
		result += "edge[dir=back]; \n";

		GraphGenerator de = new GraphGenerator(semanticModel_);
		de.createDecisionsGraph();
		
		Map<String, Node> allEntries = de.getNodeList();
		for (Map.Entry<String, Node> entry: allEntries.entrySet()){
			String shape = "shape="+ entry.getValue().getShape();
			String style = (entry.getValue().getStyle() != "")?  ", style=" + entry.getValue().getStyle(): "";
			System.out.println( entry.getKey() + "[" + shape + style+ "]");
			result += entry.getKey() + "[" + shape + style+ "]" + "\n";
		}
		
		List<String> allEdgeEntries = de.getEdgeStatements();
		for (String entry: allEdgeEntries){
			String [] entryElement = entry.split("->");
			if (!entryElement[0].equals(entryElement[1])){
				result += entry + "\n";
				System.out.println( entry);
			}
		}
		result += "}";
		return result;
	}
	public String generateVariableDependencyGraph (){
		String result ="digraph G { \n";
		result += "rankdir = BT; \n";
		result += "edge[dir=forward]; \n";
		
		GraphGenerator de = new GraphGenerator(semanticModel_);
		de.createVariableGraph();
		
		Map<String, Node> allEntries = de.getNodeList();
		for (Map.Entry<String, Node> entry: allEntries.entrySet()){
			String shape = "shape="+ entry.getValue().getShape();
			String style = (entry.getValue().getStyle() != "")?  ", style=" + entry.getValue().getStyle(): "";
			System.out.println( entry.getKey() + "[" + shape + style+ "]");
			result +=  entry.getKey() + "[" + shape + style+ "]" + "\n";
		}
		
		List<String> allEdgeEntries = de.getEdgeStatements();
		for (String entry: allEdgeEntries){
			String [] entryElement = entry.split("->");
			if (!entryElement[0].equals(entryElement[1])){
				result += entry + "\n";
				System.out.println( entry);
			}
		}
		result += "}";
		return result;
	}
	public String decisionsToCSV ( ){
		StringBuilder decision = new StringBuilder ();
		if (semanticModel_ != null){
			int count =1;
			decision.append("ID , Decision , Options\n");
			for (Map.Entry<String, Decision> entry: semanticModel_.getDecisions().entrySet()){
				StringBuilder row = new StringBuilder();
				row.append(count + ",");
				row.append(entry.getKey() + ",");
				String options ="";
				options ="[";
				List<String> decisionOptions = entry.getValue().getOptions();
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
		}
		return decision.toString();
	}
	
	public void resultsToOutputFolder (int run) throws IOException{
		// delete previous result  for this algorithm
		String printTime = String.valueOf(System.currentTimeMillis());
		boolean deleted = Helper.deletePreviousResults (expData_.getOutputDirectory(), algorithmName_);
		String folderIdentifier = deleted == false ? " " : printTime.substring(printTime.length()-8, printTime.length());
		
		String folder = algorithmName_ + folderIdentifier ;
	
		// print shortlist
		String shortlist = this.shortlistToCSV();
		Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" +folder + "/shortlist/" , shortlist, "shortlist" + run + ".csv", false);
		// print evtpi and evppi in outputfolder
		String infoValueToCSV = infoValueResult.toString();
		Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" + folder+ "/infoValue/", infoValueToCSV, "infoValue", true);
			    	
		String runtime = String.valueOf(this.getRunTime());
		Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" + folder + "/RunTime/", runtime, "runtime", true);
		
		SolutionQuality solnQuality = alg_.getSolutionQuality();
		if (solnQuality != null){
			Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" + folder + "/SolutionQuality/", String.valueOf(solnQuality.getHypervolume()), "hv" + run, true);	
			Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" + folder + "/SolutionQuality/", String.valueOf(solnQuality.getIGD()), "igd" + run, true);	
			Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" + folder + "/SolutionQuality/", String.valueOf(solnQuality.getGD()),  "gd" + run, true);	
			Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" + folder + "/SolutionQuality/", String.valueOf(solnQuality.getSpread()),"spread" + run, true);	
			Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" + folder + "/SolutionQuality/", String.valueOf(solnQuality.getCoverage()),"coverage" + run, true);	
			Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/" + folder + "/SolutionQuality/", String.valueOf(solnQuality.getEpsilon()), "epsilon" + run, true);	
		}
	}
	
	public void graphsToOutputFolder () throws IOException{
		
		String variableDependencyInDOT = this.getVariableDependencyGraph();
		Helper.printResults (expData_.getOutputDirectory()  + expData_.getProblemName() + "/graph/", variableDependencyInDOT, "vgraph.dot", false);
		
		String decisionDependencyInDOT = this.getDecisionDependencyGraph();
		Helper.printResults (expData_.getOutputDirectory()  + expData_.getProblemName() + "/graph/", decisionDependencyInDOT, "dgraph.dot", false);

	}
	public void printReferenceFronts () throws IOException{
		printObjectivesToReferenceFronts();
		printDecisionsToReferenceFronts();
	}
	private void printObjectivesToReferenceFronts () throws IOException{
		String record = "";
		for (int index = 0; index < optimalSolutions_.size(); index++) {
			record += optimalObjectiveToString (index, " ", optimalSolutions_);
			record += "\n";
		}
		Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/referenceFronts/" , record,  "objectives", true);
	}
	private void printDecisionsToReferenceFronts () throws IOException{
		String record = "";
		for (int index = 0; index < optimalSolutions_.size(); index++) {
			record += optimalDecisionsToString (index, " ",  optimalSolutions_);
			record += "\n";
		}
		Helper.printResults (expData_.getOutputDirectory() + expData_.getProblemName()+ "/referenceFronts/" , record, "decisions", true);
	}
	
 }
