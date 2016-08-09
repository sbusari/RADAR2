package radar.model;
import java.util.List;
import java.util.Map;

import radar.utilities.ConfigSetting;
import radar.utilities.Helper;


public class SbseParameter {

	private int populationSize_;
	private double mutationProbability_;
	private double crossoverProbability_;
	private String selection_;
	private int nbr_runs_;
	private int maxEvaluation_;
	String[] approxAlgorithmList_;
	String defaultApproximateAlg_;
	public SbseParameter(){}

	public SbseParameter getParameterSettings (Parser parserEngine){
		
		SbseParameter param = new SbseParameter();
		boolean useDefaultParameter = ConfigSetting.USE_DEFAULT_PARAMETER_SETTINGS;
		param.populationSize_ = useDefaultParameter == true? ConfigSetting.POPULATION_SIZE: ConfigSetting.POPULATION_SIZE;
		if (param.populationSize_ % 2 != 0){
			param.populationSize_ = param.populationSize_ +1;
		}
		param.crossoverProbability_ = useDefaultParameter == true? 0.8: ConfigSetting.CROSSOVER;
		param.mutationProbability_ = useDefaultParameter == true? (double)1/getTotalOptions(parserEngine.getSemanticModel()):ConfigSetting.MUTATION ;
		param.maxEvaluation_ = useDefaultParameter == true? ConfigSetting.MAX_EVALUATIONS: ConfigSetting.MAX_EVALUATIONS;
		return param;
	}
	private int getTotalOptions (Model semanticModel){
		List <Decision> decisions  = semanticModel.getDecisions();
		int counter =0;
	    if(decisions != null){
	    	for (int i=0; i <decisions.size(); i++ ){
	    		counter= decisions.get(i).getOptions().size();
        	}
	    }
	    return counter;
	}
	public void setPopulationSize(int populationSize){
		populationSize_=populationSize;
	}
	public int getPopulationSize(){
		return populationSize_;
	}
	public void setMaxEvaluation(int maxEvaluation){
		maxEvaluation_=maxEvaluation;
	}
	public int getNbr_Runs(){
		return nbr_runs_;
	}
	public void setNbr_Runs(int nbr_runs){
		nbr_runs_=nbr_runs;
	}
	public int getMaxEvaluation(){
		return maxEvaluation_;
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
	public void setApproxAlgorithmList (String[] approxAlgorithmList ){
		approxAlgorithmList_ = approxAlgorithmList;
	}
	public String[] getApproxAlgorithmList (){
		return approxAlgorithmList_;
	}
	public void setDefaultApproximateAlg(String defaultApproximateAlg ){
		defaultApproximateAlg_ = defaultApproximateAlg;
	}
	public String getDefaultApproximateAlg(){
		return defaultApproximateAlg_;
	}
}
