package radar.userinterface;
import java.util.Locale;

import radar.model.AnalysisResult;
import radar.model.ExperimentData;
import radar.model.Model;
import radar.model.ModelSolver;
import radar.model.OptimisationType;
import radar.model.Parser;
import radar.model.ScatterPlot3D;
import radar.model.TwoDPlotter;
import radar.utilities.Helper;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class RADAR_CMD {
	
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
	
	
	@Parameter(names = "--solve", description = "Solves the decision model using exhaustive search. ")
	public boolean solve = false;
	
	@Parameter(names = "--decision", description = "Displays the model decisions and their corresponding options.")
	public boolean decision = false;
	
	
	@Parameter(names = "--nbr_simulation", description = "Number of simulation run. Input <sample size> .")
	public Integer nbr_Simulation = 10000;
	
	@Parameter(names = "--problem-name", description = "Name of the experiment. Input <experiment name>  ")
	public String expName = "";
		
	// a list needs an arity, but because we do not know the arity we enforce every thing in a quoted string separated by comma.
	@Parameter(names ="--infoValueObjective", description = "Computes evtpi and evppi. Input is an objective name. An example usage of this command is: --infoValueObjective 'FinancialLoss' ")
	private String infoValueObjective = null;
	
	@Parameter(names ="--subGraphObjective", description = "Generates AND/OR subgraph for the specified objective only. Input is an objective name. An example usage of this command is: --subGraphObjective 'InvestigationCost' ")
	private String subGraphObjective = null;
	
	
	@Parameter(names ="--pareto", description = "Displays the pareto plots.")
	public boolean pareto = false;
	
   
	public static void main(String[]args) {
    	RADAR_CMD cmd = new RADAR_CMD();
    	JCommander jcommander = new JCommander(cmd, args);
    	jcommander.setProgramName("Radar");
        if (cmd.help) {
        	jcommander.usage();
            return;
        }
        cmd.run(cmd);
    }
	
/*	private Model parseModel (String modelPath,int nbr_simulation, String infoValueObjective, String subGraphObjective){
		Model semanticModel = null;
		try {
			String model = Helper.readFile(modelPath);
			Parser parser  = new Parser(model,nbr_simulation,infoValueObjective,subGraphObjective );
			semanticModel = parser.getSemanticModel();
		}
		catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		
		return semanticModel;
	}*/

	ExperimentData populateExperimentData () throws Exception{
		ExperimentData result = new ExperimentData();
		InputValidator.validateModelPath(model);
		InputValidator.validateOutputPath(output);
		// populate data
		result.setSimulationNumber(nbr_Simulation);
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
    		try {
    			semanticModel = new Parser().parseCommandLineModel(model.trim(), nbr_Simulation, infoValueObjective,subGraphObjective);
    		}catch (RuntimeException re){
    			throw new RuntimeException( "Error: "+ re.getMessage());
    		}
    		
		}
		// if solve is specified, we  parse the model and solve.
		if (semanticModel == null && solve == true){
			semanticModel = new Parser().parseCommandLineModel(model.trim(), nbr_Simulation, infoValueObjective,subGraphObjective);
		}
		if (model != null && output != null && semanticModel == null){
			throw new RuntimeException ("Specify a command to solve the model. Use the --help command for more information.");
		}
		return semanticModel;
		
	}
	public void run(RADAR_CMD cmdParam) {
    	
    	try {
    		// populate model and algorithm data
    		ExperimentData dataInput = populateExperimentData();
    		String typeOfOptimisation = "EXACT";
    		OptimisationType optimisationType = OptimisationType.valueOf(typeOfOptimisation.toUpperCase(Locale.ENGLISH));
    		dataInput.setTypeOfOptimisation(optimisationType);
    		
    		
    		// get sematic model from model file
    		Model semanticModel = loadModel ();
    		semanticModel.setNbr_Simulation(nbr_Simulation);
    		
    		// update experiemnt data with semantic model and information value objective.
    		dataInput.setProblemName(semanticModel.getModelName());
    		InputValidator.objectiveExist(semanticModel, infoValueObjective);
    		InputValidator.objectiveExist(semanticModel, subGraphObjective);
    		

    		// analyse model
    		AnalysisResult result = ModelSolver.solve(semanticModel);
			String analysisResult = result.analysisToString();
			Helper.printResults (dataInput.getOutputDirectory() + dataInput.getProblemName()+ "/" , analysisResult, dataInput.getProblemName() +".out", false);
			
			// generate graphs
			String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, result.getSubGraphObjective());
			String decisionGraph = semanticModel.generateDecisionDiagram();
			Helper.printResults (dataInput.getOutputDirectory() + dataInput.getProblemName()+ "/graph/", variableGraph, "vgraph.dot", false);
			Helper.printResults (dataInput.getOutputDirectory() + dataInput.getProblemName()+ "/graph/", decisionGraph, "dgraph.dot", false);
			
			
    		if (pareto == true){
    			String imageOutput = dataInput.getOutputDirectory() + dataInput.getProblemName() + "/";
    			if (result.getShortListObjectives().get(0).length == 2){
					TwoDPlotter twoDPlot = new TwoDPlotter();
					twoDPlot.plotAll(semanticModel,imageOutput, result);
				}else if (result.getShortListObjectives().get(0).length == 3){
					ScatterPlot3D sc3D2= new ScatterPlot3D( );
					sc3D2.plot(semanticModel, imageOutput, result);;
;				}
    		}
    		System.out.println("Finished!");
    		
    	}catch (Exception e){
    		System.out.print("Error: ");
    		System.out.println(e.getMessage());
    	}
    	
		
    }

	
}
