package uk.ac.ucl.cs.radar.model;
import java.util.Map;
public class ExperimentData {
	
	public ExperimentData(){
		
	}
	String outputDirectory_;
	String problemName_;
	String experimentName_;
	String defaultApproximateAlg_;
	String textualModel_;
	OptimisationType typeOfOptimisation_;
	boolean runAllApproxAlgorithms_;
	boolean useDefaultParameterSettings_;
	String exactAlgorithm_;
	String[] approxAlgorithmList_;
	String []informationValueToCompute_;
	int algorithmRuns_;
	int threads_;
	String objectivePlotPath_;
	private int populationSize_;
	private double mutationProbability_;
	private double crossoverProbability_;
	private String selection_;
	private int maxEvaluation_;
	private int noOfSimulation_;
	String nonDominatedSolutionPlotPath_;
	String dominatedSolutionPlotPath_;
	String expBaseDirectory_;
	String  informationValueObjective_;
	Model semanticModel_;
	int nbr_objectives_;
	public void setInformationValueObjective( String informationValueObjective ){
		informationValueObjective_ = informationValueObjective;
	}
	public  String  getInformationValueObjective(){
		return informationValueObjective_;
	}
	public void setSemanticModel(Model semanticModel ){
		semanticModel_ = semanticModel;
	}
	public Model getSemanticModel(){
		return semanticModel_;
	}
	public void setSimulationNumber(int noOfSimulation) {
		this.noOfSimulation_ = noOfSimulation;
	}
	public int getSimulationNumber() {
		return noOfSimulation_;
	}
	public void setMaxEvaluation(int maxEvaluation){
		maxEvaluation_=maxEvaluation;
	}
	public int getMaxEvaluation(){
		return maxEvaluation_;
	}
	public void setPopulationSize(int populationSize){
		populationSize_=populationSize;
	}
	public int getPopulationSize(){
		return populationSize_;
	}
	public void setMutationProbability(double mutationProbability){
		mutationProbability_=mutationProbability;
	}
	public double getMutationProbability(){
		return mutationProbability_;
	}
	public void setCrossoverProbability(double crossoverProbability){
		crossoverProbability_=crossoverProbability;
	}
	public double getCrossoverProbability(){
		return crossoverProbability_;
	}
	public void setSelection(String selection){
		selection_=selection;
	}
	public String getSelection(){
		return selection_;
	}
	public void setOutputDirectory(String outputDirectory ){
		outputDirectory_ = outputDirectory;
	}
	public String getOutputDirectory(){
		return outputDirectory_;
	}
	public void setProblemName(String problemName ){
		problemName_ = problemName;
	}
	public String getProblemName(){
		return problemName_;
	}
	public void setDefaultApproximateAlg(String defaultApproximateAlg ){
		defaultApproximateAlg_ = defaultApproximateAlg;
	}
	public String getDefaultApproximateAlg(){
		return defaultApproximateAlg_;
	}
	public void setExperimentName (String experimentName ){
		experimentName_ = experimentName;
	}
	public String getExperimentName (){
		return experimentName_;
	}
	public void setTypeOfOptimisation (OptimisationType typeOfOptimisation ){
		typeOfOptimisation_ = typeOfOptimisation;
	}
	public OptimisationType getTypeOfOptimisation (){
		return typeOfOptimisation_;
	}
	public void setTextualModel (String textualModel ){
		textualModel_ = textualModel;
	}
	public String getTextualModel (){
		return textualModel_;
	}
	public void setExactAlgorithm (String exactAlgorithm ){
		exactAlgorithm_ = exactAlgorithm;
	}
	public String getExactAlgorithm (){
		return exactAlgorithm_;
	}
	public void setRunAllApproxAlgorithms (boolean runAllApproxAlgs ){
		runAllApproxAlgorithms_ = runAllApproxAlgs;
	}
	public boolean getRunAllApproxAlgorithms (){
		return runAllApproxAlgorithms_;
	}
	public void setUseDefaultParameterSettings(boolean useDefaultParameterSettings ){
		useDefaultParameterSettings_ = useDefaultParameterSettings;
	}
	public boolean getUseDefaultParameterSettings(){
		return useDefaultParameterSettings_;
	}
	public void setApproxAlgorithmList (String[] approxAlgorithmList ){
		approxAlgorithmList_ = approxAlgorithmList;
	}
	public String[] getApproxAlgorithmList (){
		return approxAlgorithmList_;
	}
	public void setAlgorithmRuns(int algorithmRuns ){
		algorithmRuns_ = algorithmRuns;
	}
	public int getAlgorithmRuns(){
		return algorithmRuns_;
	}
	public void setThreads(int threads ){
		threads_ = threads;
	}
	public int getThreads(){
		return threads_;
	}



}
