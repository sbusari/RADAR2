package radar.userinterface;
/*package radar.commandline;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import radar.model.AnalysisResult;
import radar.model.ExperimentData;
import radar.model.GraphGenerator;
import radar.model.Model;
import radar.model.ModelSolver;
import radar.model.OptimisationType;
import radar.model.Parser;
import radar.model.SbseParameter;
import radar.utilities.ConfigSetting;
import radar.utilities.Helper;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class CommandLine2 {
	
	// how to exit the command.
	@Parameter(names = "--debug", description = "Debug mode")
	public boolean debug = false;
	
	@Parameter(names = "--help", description = "Help")
	private boolean help = false;

	@Parameter(names = "--output", description = "Output folder where the results are saved. Input <file path>.  ")
	public String output = null;
	
	@Parameter(names = "--model", description = "The file that contains the decision model. Input <file path>. ")
	public String model = null;
	
	@Parameter(names = "--parse", description = "Parses the decision model to check for syntax error.")
	private boolean parse = false;
	
	@Parameter(names = "--solve-using", description = "Solves the decision model. Input <algorithm name> e.g. ExhaustiveSearch, NSGAII, SPEA2, IBEA. ")
	public List<String> solve = new ArrayList<String>();;
	
	@Parameter(names = "--decision", description = "Displays the model decisions and their corresponding options.")
	public boolean decision = false;
	
	@Parameter(names = "--nbr_simulation", description = "Number of simulation run. Input <sample size> .")
	public Integer nbr_Simulation = 10000;
	
	@Parameter(names = "--threads", description = "Specifies the number of threads to run. Input <number of threads>. "  )
	public Integer threads = 1;
	
	@Parameter(names = "--exp-name", description = "Name of the experiment. Input <experiment name>  ")
	public String expName = "";
	
	@Parameter(names = "--param",arity = 5, description = "Specify the approximate algorithm parameters. Inputs <population size> (integer), <crossover rate> (double), <mutation rate> (double), <maximum evaluation> (integer) and <runs> (integer) in this order. ")
	public List<String> algorithmParameter = new ArrayList<String>();
	
	@Parameter(names = "--param-default", description = "Approximate algorithm uses default parameters for <population size> (100), <crossover rate> (0.8), <mutation rate> (1/#options), <maximum evaluation> (1000) and <runs> (10) . ")
	public boolean defaultAlgorithmParameter = false;
	
	// a list needs an arity, but because we do not know the arity we enforce every thing in a quoted string separated by comma.
	@Parameter(names ="--infoValueObjective", description = "Computes evtpi and evppi. Input is an objective name. An example usage of this command is: --infoValueObjective 'FinancialLoss' ")
	private String infoValueObjective = null;
	
	@Parameter(names ="--pareto", description = "Displays the pareto plots.")
	public boolean pareto = false;
	
   
	public static void main(String[]args) {
    	CommandLine2 cmd = new CommandLine2();
    	JCommander jcommander = new JCommander(cmd, args);
    	jcommander.setProgramName("Radar");
        if (cmd.help) {
        	jcommander.usage();
            return;
        }
        cmd.run(cmd);
    }
	
	private Model parseModel (String modelPath,int nbr_simulation, String infoValueObjective){
		Model semanticModel = null;
		try {
			String model = Helper.readFile(modelPath);
			Parser parser  = new Parser(model,nbr_simulation,infoValueObjective );
			semanticModel = parser.getSemanticModel();
		}
		catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		
		return semanticModel;
	}
	private boolean runSbseAlgorithm(){
		boolean result = false;
		if ( solve.contains("NSGAII") ||  solve.contains("SPEA2") ||  solve.contains("IBEA") ||  solve.contains("MoCell") ||  solve.contains("PAES") ||  solve.contains("RandomSearch")){
			result = true;
		}
		return result;
	}
	
	void setSpecifiedSbseParameter (SbseParameter param,  List<String> algorithmParameter){
		// one has to specify 5 paramters
		if (algorithmParameter  != null && algorithmParameter.size() == ConfigSetting.NUMBER_OF_ALGORITHMS -1) {
			param.setCrossoverProbability(Double.parseDouble(algorithmParameter.get(1).trim()));
			param.setMutationProbability(Double.parseDouble(algorithmParameter.get(2).trim()));
			if (Integer.parseInt(algorithmParameter.get(0).trim()) % 2 != 0){
				param.setPopulationSize(Integer.parseInt(algorithmParameter.get(0).trim()) +1);
			}else{
				param.setPopulationSize(Integer.parseInt(algorithmParameter.get(0).trim().trim()));
			}
			param.setNbr_Runs(Integer.parseInt(algorithmParameter.get(4).trim()));
			param.setMaxEvaluation(Integer.parseInt(algorithmParameter.get(3).trim()));
		}else{
			throw new RuntimeException ("Algorithm parameters must be 5 or specify the use default algorithm.");
		}
		
	}
    void setSbseParameters (SbseParameter param){
    	param.setCrossoverProbability(ConfigSetting.CROSSOVER);
    	param.setMutationProbability(ConfigSetting.MUTATION);
		if (ConfigSetting.POPULATION_SIZE % 2 != 0){
			param.setPopulationSize(ConfigSetting.POPULATION_SIZE +1);
		}else{
			param.setPopulationSize(ConfigSetting.POPULATION_SIZE);
		}
		param.setNbr_Runs(ConfigSetting.ALGORITHM_RUNS);
		param.setMaxEvaluation(ConfigSetting.MAX_EVALUATIONS);

    }
    SbseParameter validateSbseParameter () throws Exception{
    	SbseParameter sbse_param = new SbseParameter();
    	// validate inputs
    	InputValidator.validateSbseParameters(defaultAlgorithmParameter, solve, algorithmParameter);    	
    	InputValidator.validateSbseParameterValues(algorithmParameter); 
    	
    	return sbse_param;
    }
	ExperimentData populateExperimentData () throws Exception{
		ExperimentData result = new ExperimentData();
		InputValidator.validateSolveNotNull(solve);
		InputValidator.validateModelPath(model);
		InputValidator.validateOutputPath(output);
		// populate data
		result.setUseDefaultParameterSettings(defaultAlgorithmParameter);
		result.setRunAllApproxAlgorithms(solve != null && solve.size() == ConfigSetting.NUMBER_OF_ALGORITHMS? true:false);
		result.setSimulationNumber(nbr_Simulation);
		result.setThreads(threads);
		result.setExperimentName(expName != null?expName:"NewExperiemnt");
		if (output.trim().charAt(output.length()-1) != '/'){
			result.setOutputDirectory(output.trim() +"/");
		}
		return result;
		
	}
	Model loadModel () throws Exception{
		//4. when parse is specified quickly parse and write a message that the model is parsed.
		Model semanticModel =null;
		if (parse == true || decision == true){
			InputValidator.validateModelPath(model);
    		InputValidator.validateOutputPath(output);
    		semanticModel = parseModel(model.trim(), nbr_Simulation, infoValueObjective);
		}
		// if only exhaustive seach is specified, we can still parse the model and solve.
		if (semanticModel == null && solve.size() > 0){
			semanticModel = parseModel(model.trim(), nbr_Simulation, infoValueObjective);
		}
		if (model != null && output != null && semanticModel == null){
			throw new RuntimeException ("Specify a command to solve the model. Use the --help command for more information.");
		}
		return semanticModel;
		
	}
	public void run(CommandLine2 cmdParam) {
    	
    	try {
    		// populate model and algorithm data
    		ExperimentData dataInput = populateExperimentData();
    		String typeOfOptimisation = "EXACT";
    		OptimisationType optimisationType = null;
    		SbseParameter sbse_param = new SbseParameter();
    		if (runSbseAlgorithm()){
    			validateSbseParameter();
    			typeOfOptimisation = "APPROXIMATE";
    			if (defaultAlgorithmParameter == true){
    				setSbseParameters(sbse_param);
    			}else{
    				setSpecifiedSbseParameter (sbse_param, algorithmParameter);
    			}
    			optimisationType = OptimisationType.valueOf(typeOfOptimisation.toUpperCase(Locale.ENGLISH));
    			sbse_param.setApproxAlgorithmList(solve.toArray(new String[solve.size()]));
    		}else{
    			typeOfOptimisation = "EXACT";
    			optimisationType = OptimisationType.valueOf(typeOfOptimisation.toUpperCase(Locale.ENGLISH));
    		}
    		dataInput.setTypeOfOptimisation(optimisationType);
    		
    		// get sematic model from model file
    		Model semanticModel = loadModel ();
    		semanticModel.setNbr_Simulation(nbr_Simulation);
    		
    		// update experiemnt data with semantic model and information value objective.
    		dataInput.setProblemName(semanticModel.getModelName());
    		InputValidator.objectiveExist(semanticModel, infoValueObjective);

    		// analyse model
    		AnalysisResult result = null; 
			if (dataInput.getTypeOfOptimisation().equals(OptimisationType.EXACT)){
				result = ModelSolver.solve(semanticModel);
				String analysisResult = result.analysisToString();
				Helper.printResults (dataInput.getOutputDirectory() + dataInput.getProblemName()+ "/" , analysisResult, dataInput.getProblemName() +".out", false);
				Helper.printResults (dataInput.getOutputDirectory() + dataInput.getProblemName()+ "/referenceFronts/" , result.getReferenceObjectives(), "objectives", true);
				Helper.printResults (dataInput.getOutputDirectory() + dataInput.getProblemName()+ "/referenceFronts/" , result.getReferenceDecisions(), "decisions", true);
			}else{
				for (int  i=0 ; i < sbse_param.getApproxAlgorithmList().length; i ++){
					for (int j =0;  j <sbse_param.getNbr_Runs(); j++){
						String algorithm = sbse_param.getApproxAlgorithmList()[i];
						result = new ModelAnalysisResult(algorithm, dataInput);
						result.analyse();
						result.resultsToOutputFolder(j);
					}
				}
			}
			
			// generate graphs
			String variableGraph = new GraphGenerator().generateVariableGraph(semanticModel);
			String decisionGraph = new GraphGenerator().generateDecisionGraph(semanticModel);
			Helper.printResults (dataInput.getOutputDirectory() + dataInput.getProblemName()+ "/graph/", variableGraph, "vgraph.dot", false);
			Helper.printResults (dataInput.getOutputDirectory() + dataInput.getProblemName()+ "/graph/", decisionGraph, "dgraph.dot", false);
			
    		if (pareto == true){
    			// display and save the images.
    		}
    		
    		System.out.println("Finished!");
    		
    	}catch (Exception e){
    		System.out.println("Error");
    		System.out.println(e.getMessage());
    	}
    	
		
    }

	
}
*/