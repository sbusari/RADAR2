package radar.commandline;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;
import radar.enumeration.OptimisationType;
import radar.experiment.data.ExperiementData;
import radar.model.Model;
import radar.model.ModelAnalysisResult;
import radar.model.Parser;
import radar.plot.goal.graph.DynamicGoalGraph;
import radar.utilities.ConfigSetting;
import radar.utilities.Helper;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class CommandLine {
	
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
	public ArrayList<String> solve = new ArrayList<String>();;
	
	@Parameter(names = "--decision", description = "Displays the model decisions and their corresponding options.")
	public boolean decision = false;
	
/*	@Parameter(names = "--shortlist", description = "Generates the optimal solutions.")
	public boolean shortlist = false;*/
	
	@Parameter(names = "--simulation", description = "Number of simulation run. Input <sample size> .")
	public Integer simulationNumber = 10000;
	
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
	private List<String> infoValueObjective = new ArrayList<String>();

	@Parameter(names ="--variable_dependeny_graph", description = "Displays the AND/OR goal graph.")
	public boolean goalmodel = false;
	
	@Parameter(names ="--pareto", description = "Displays the pareto plots.")
	public boolean pareto = false;
	
	@Parameter(names ="--decision_graph", description = "Displays the decision graph.")
	public boolean decisiongraph = false;
   
	public static void main(String[]args) {
    	CommandLine cmd = new CommandLine();
    	JCommander jcommander = new JCommander(cmd, args);
    	jcommander.setProgramName("Radar");
        if (cmd.help) {
        	jcommander.usage();
            return;
        }
        cmd.run(cmd);
    }
	
	private Model parseModel (String modelPath,int simulation, List<String> infoValueObjective){
		Model semanticModel = null;
		try {
			String model = Helper.readFile(modelPath);
			Parser parser  = new Parser(model,simulation,infoValueObjective );
			semanticModel = parser.getSemanticModel();
		}
		catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		
		return semanticModel;
	}
	private boolean runSbseAlgorithm(){
		boolean result = false;
		if (solve != null && solve.contains("NSGAII") ||  solve.contains("SPEA2") ||  solve.contains("IBEA") ||  solve.contains("MoCell") ||  solve.contains("PAES") ||  solve.contains("RandomSearch")){
			result = true;
		}
		return result;
	}
	
	void addAlgorithmParameterToExperiementData (ExperiementData dataInput,  List<String> algorithmParameter){
		// one has to specify 5 paramters
		if (algorithmParameter  != null && algorithmParameter.size() == 5) {
			dataInput.setCrossoverProbability(Double.parseDouble(algorithmParameter.get(1).trim()));
			dataInput.setMutationProbability(Double.parseDouble(algorithmParameter.get(2).trim()));
			if (Integer.parseInt(algorithmParameter.get(0).trim()) % 2 != 0){
				dataInput.setPopulationSize(Integer.parseInt(algorithmParameter.get(0).trim()) +1);
			}else{
				dataInput.setPopulationSize(Integer.parseInt(algorithmParameter.get(0).trim().trim()));
			}
			dataInput.setAlgorithmRuns(Integer.parseInt(algorithmParameter.get(4).trim()));
			dataInput.setMaxEvaluation(Integer.parseInt(algorithmParameter.get(3).trim()));
		}else{
			throw new RuntimeException ("Algorithm parameters must be 5 or specify the use default algorithm.");
		}
		
	}
    
	ExperiementData populateExperimentData () throws Exception{
		ExperiementData result = new ExperiementData();
		// validate inputs
		InputValidator.validateAlgorithmParameters(algorithmParameter); 
		InputValidator.validateSolveNotNull(solve);
		InputValidator.validateSbseParameters(defaultAlgorithmParameter, solve, algorithmParameter);    	
		InputValidator.validateModelPath(model);
		InputValidator.validateOutputPath(output);
		// populate data
		
		result.setUseDefaultParameterSettings(defaultAlgorithmParameter);
		result.setRunAllApproxAlgorithms(solve != null && solve.size() == 6? true:false);
		result.setSimulationNumber(simulationNumber);
		result.setDefaultApproximateAlg("NSGAII");
		result.setExactAlgorithm("ExhaustiveSearch");
		result.setThreads(threads);
		result.setExperimentName(expName != null?expName:"NewExperiemnt");
		if (output.trim().charAt(output.length()-1) != '/'){
			result.setOutputDirectory(output.trim() +"/");
		}
		
		String typeOfOptimisation = "EXACT";
		OptimisationType optimisationType = null;
		if (runSbseAlgorithm()){
			typeOfOptimisation = "APPROXIMATE";
			if (defaultAlgorithmParameter == true){
				result.setAlgorithmRuns(ConfigSetting.ALGORITHM_RUNS);
			}else{
				addAlgorithmParameterToExperiementData (result, algorithmParameter);
			}
			 optimisationType = OptimisationType.valueOf(typeOfOptimisation.toUpperCase(Locale.ENGLISH));
			result.setTypeOfOptimisation(optimisationType);
			result.setApproxAlgorithmList(solve.toArray(new String[solve.size()]));
		}else{
			typeOfOptimisation = "EXACT";
			optimisationType = OptimisationType.valueOf(typeOfOptimisation.toUpperCase(Locale.ENGLISH));
			result.setTypeOfOptimisation(optimisationType);
			result.setAlgorithmRuns(1);
		}
		return result;
		
	}
	Model loadModel () throws Exception{
		//4. when parse is specified quickly parse and write a message that the model is parsed.
		Model semanticModel =null;
		if (parse == true || decision == true){
			InputValidator.validateModelPath(model);
    		InputValidator.validateOutputPath(output);
    		semanticModel = parseModel(model.trim(), simulationNumber, infoValueObjective);
		}
		// if only exhaustive seach is specified, we can still parse the model and solve.
		if (semanticModel == null && solve.size() > 0){
			semanticModel = parseModel(model.trim(), simulationNumber, infoValueObjective);
		}
		if (model != null && output != null && semanticModel == null){
			throw new RuntimeException ("Specify a command to solve the model. Use the --help command for more information.");
		}
		return semanticModel;
		
	}
	public void run(CommandLine cmdParam) {
    	
    	try {
    		// populate model and algorithm data
    		ExperiementData dataInput = populateExperimentData();
    		// get sematic model from model file
    		Model semanticModel = loadModel ();
    		semanticModel.setSimulationNumber(simulationNumber);
    		
    		// update experiemnt data with semantic model and information value objective.
    		dataInput.setSemanticModel(semanticModel);
    		dataInput.setProblemName(semanticModel.getModelName());
    		InputValidator.objectiveExist(semanticModel, infoValueObjective);
    		dataInput.setInformationValueObjective(infoValueObjective);

    		// analyse model
    		ModelAnalysisResult result = new ModelAnalysisResult(dataInput.getSemanticModel(), dataInput.getTypeOfOptimisation());
			if (dataInput.getTypeOfOptimisation().equals(OptimisationType.EXACT)){
				result.anlyseByExhaustiveSearch();
			}
			// print result to ouput folder
			String cvsResult = result.shortlistToCSV();
			Helper.printResults (dataInput.getOutputDirectory() + "/", cvsResult, dataInput.getProblemName()+ "_shortlist.csv");
			
			// print evtpi and evppi in outputfolder
			String evtpiToCSV = result.evtpiToString();
			Helper.printResults (dataInput.getOutputDirectory() + "/", evtpiToCSV, dataInput.getProblemName()+ "_evtpi.csv");
			
			String evppiToCSV = result.evppiToString();
			Helper.printResults (dataInput.getOutputDirectory() + "/", evppiToCSV, dataInput.getProblemName()+ "_evppi.csv");
			
			
			//5. when decision is specified, display the decision on console.
    		if (decision == true ){
    			InputValidator.validateModelPath(model);
        		InputValidator.validateOutputPath(output);
       			String decision =  result.decisionsToCSV();
       			System.out.println(decision);
    			Helper.printResults (dataInput.getExpBaseDirectory() + "/",decision,dataInput.getProblemName()+"_decisions.csv" );
    		}
    		
    		//6. if goal graph and pareto is specified invoke that jframe to display
    		if (goalmodel == true){
    			Map <String, Node> nodeList = null; 
    			Graph varGraph = null;
    			DynamicGoalGraph varibaleDependecyGraph = new DynamicGoalGraph (nodeList,varGraph);
    			varibaleDependecyGraph.goalGraph("GoalGraph");
    		}
    		
    		if (pareto == true){
    			// display and save the images.
    		}
    		
    		System.out.println("Finished!");
    		
    	}catch (Exception e){
    		System.out.println("got here5");
    		System.out.println(e.getMessage());
    	}
    	
		
		
    }

	
}
