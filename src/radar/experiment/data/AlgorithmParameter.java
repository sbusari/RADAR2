package radar.experiment.data;
import radar.model.AlternativeAnalyser;
import radar.model.Parser;
import radar.utilities.ConfigSetting;


public class AlgorithmParameter {

	private int populationSize_;
	private double mutationProbability_;
	private double crossoverProbability_;
	private String selection_;
	private int maxEvaluation_;
	public AlgorithmParameter(){}
	public AlgorithmParameter(Parser parserEngine, UIData expData){
		this.populationSize_ = expData.getUseDefaultParameterSettings() == true? ConfigSetting.POPULATION_SIZE: expData.getPopulationSize() ;	
		if (this.populationSize_ % 2 != 0){
			this.populationSize_ = this.populationSize_ +1;
		}
		this.crossoverProbability_ = expData.getUseDefaultParameterSettings() == true? ConfigSetting.CROSSOVER: expData.getCrossoverProbability() ;
		this.mutationProbability_ = expData.getUseDefaultParameterSettings() == true? (double)1/AlternativeAnalyser.getTotalOptions(parserEngine.getSemanticModel()):expData.getMutationProbability() ;
		this.maxEvaluation_ = expData.getUseDefaultParameterSettings() == true? ConfigSetting.MAX_EVALUATIONS:expData.getMaxEvaluation();

	}

	public AlgorithmParameter getParameterSettings (Parser parserEngine){
		
		AlgorithmParameter param = new AlgorithmParameter();
		boolean useDefaultParameter = ConfigSetting.USE_DEFAULT_PARAMETER_SETTINGS;
		param.populationSize_ = useDefaultParameter == true? ConfigSetting.POPULATION_SIZE: ConfigSetting.POPULATION_SIZE;
		if (param.populationSize_ % 2 != 0){
			param.populationSize_ = param.populationSize_ +1;
		}
		param.crossoverProbability_ = useDefaultParameter == true? 0.8: ConfigSetting.CROSSOVER;
		param.mutationProbability_ = useDefaultParameter == true? (double)1/AlternativeAnalyser.getTotalOptions(parserEngine.getSemanticModel()):ConfigSetting.MUTATION ;
		param.maxEvaluation_ = useDefaultParameter == true? ConfigSetting.MAX_EVALUATIONS: ConfigSetting.MAX_EVALUATIONS;
		return param;
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
}
