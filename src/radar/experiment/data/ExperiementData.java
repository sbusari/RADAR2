package radar.experiment.data;

import java.util.ArrayList;
import java.util.List;

import radar.enumeration.OptimisationType;
import radar.model.Model;
import radar.model.Parser;
public class ExperiementData {
	
	public ExperiementData(){
		
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
	List<String>  informationValueObjective_;
	Model semanticModel_;
	public void setInformationValueObjective( List<String> informationValueObjective ){
		informationValueObjective_ = informationValueObjective;
	}
	public  List<String>  getInformationValueObjective(){
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
	public void setInformationValueToCompute (String[] informationValueToCompute ){
		informationValueToCompute_ = informationValueToCompute;
	}
	public String[] getInformationValueToCompute (){
		return informationValueToCompute_;
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
	public void setNonDominatedSolutionPlotPath(String nonDominatedSolutionPlotPath){
		nonDominatedSolutionPlotPath_ = nonDominatedSolutionPlotPath;
	}
	public String getNonDominatedSolutionPlotPath(){
		return nonDominatedSolutionPlotPath_;
	}
	public void setDominatedSolutionPlotPath(String dominatedSolutionPlotPath){
		dominatedSolutionPlotPath_ = dominatedSolutionPlotPath;
	}
	public String getDominatedSolutionPlotPath(){
		return dominatedSolutionPlotPath_;
	}

	public void setExpBaseDirectory(String expBaseDirectory){
		expBaseDirectory_ = expBaseDirectory;
	}
	public String getExpBaseDirectory(){
		return expBaseDirectory_;
	}

}
