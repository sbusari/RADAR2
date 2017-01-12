package radar.userinterface;

import java.util.Random;

import radar.model.AnalysisData;
import radar.model.AnalysisResult;
import radar.model.Model;
import radar.model.ModelSolver;
import radar.model.Parser;
import radar.model.ScatterPlot3D;
import radar.model.SyntheticModelGenerator;
import radar.model.TwoDPlotter;
import radar.utilities.Helper;

public class RADAR_SCALETEST {
	int min_nbr_variables_;
	int nbr_objectives_;
	int min_nbr_options_;
	int max_nbr_options_;
	int nbr_decisions_;
	String modelName_;
	Random rn = new Random();
	int getRandom (int min, int max){
		
		int range = max - min + 1;
		int num =  rn.nextInt(range) + min;
		return num;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RADAR_SCALETEST rst = new RADAR_SCALETEST();
		
		rst.infoValueObjective = "OF0";
		rst.subGraphObjective = "OF0";
		rst.output = "/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/";
		
		// the number of simulation
		int small_sim = 10000; //rst.getRandom(5000,10000);
		int medium_sim = 50000; //rst.getRandom (10000, 50000);
		int large_sim = 100000; //rst.getRandom (50000, 100000);
		int x_large_sim = 1000000; //rst.getRandom (100000, 1000000);
		int[] simulationArray = new int[]{small_sim, medium_sim, large_sim, x_large_sim};
		
		// the number of decision
		int small_decision = rst.getRandom(2,5);
		int medium_decision= rst.getRandom (5, 10);
		int large_decision = rst.getRandom (10, 50);
		int x_large_decision = rst.getRandom (50, 200);
		int[] decisionArray = new int[]{small_decision, medium_decision, large_decision,x_large_decision};
		
		// the number of option
		int small_option_min = rst.getRandom(1,2);
		int small_option_max = rst.getRandom(3,5);
		//int small_option = rst.getRandom(small_option_min, small_option_max);
		
		int medium_option_min= rst.getRandom (3, 5);
		int medium_option_max= rst.getRandom (6, 10);
		//int mediun_option = rst.getRandom(medium_option_min, medium_option_max);
		
		int large_option_min = rst.getRandom (5, 10);
		int large_option_max = rst.getRandom (11, 15);
		//int large_option = rst.getRandom(large_option_min, large_option_max);
		
		int x_large_option_min = rst.getRandom (10, 15);
		int x_large_option_max = rst.getRandom (16, 20);
		
		int[] optionMinArray = new int[]{small_option_min, medium_option_min, large_option_min, x_large_option_min};
		int[] optionMaxArray = new int[]{small_option_max, medium_option_max, large_option_max, x_large_option_max};
		
		// the minimum number of model variables
		int small_min_variable = 8; //rst.getRandom(1,100);
		int medium_min_variable = 45; //rst.getRandom (101, 500);
		int large_min_variable = 150; //rst.getRandom (501, 1000);
		int x_large_min_variable = 2500; //rst.getRandom (501, 1000);
		int[] min_model_variable = new int[]{small_min_variable, medium_min_variable, large_min_variable,x_large_min_variable };

		// the minimum number of model variables
		int small_min_objective = rst.getRandom(2,3);
		int medium_min_objective = rst.getRandom (3, 5);
		int large_min_objective= rst.getRandom (3, 7);
		int x_large_min_objective = rst.getRandom (5, 10);
		int[] objective_Array = new int[]{small_min_objective, medium_min_objective, large_min_objective,x_large_min_objective };

		
		
		// why not use uniform distribution
		/*for (int i = 0; i <  objective_Array.length; i++){
			for (int j =0; j < min_model_variable.length ; j ++){
				for (int k =0;  k < decisionArray.length; k++){
					for (int l =0; l < optionMinArray.length; l++){
						rst.max_nbr_options_ = optionMaxArray[l];
						rst.min_nbr_options_ = optionMinArray[l];
						rst.min_nbr_variables_ = min_model_variable[j];
						rst.nbr_decisions_ = decisionArray[k];
						rst.nbr_objectives_ = objective_Array[i];
						rst.modelName_ = "Test";
						rst.nbr_Simulation = 10000;
						rst.analyseRadarModel(rst.nbr_Simulation, rst.modelName_ + "-" + rst.nbr_objectives_ + "-" + rst.min_nbr_options_ + "-"+ rst.max_nbr_options_ + "-"+ rst.min_nbr_variables_ +"-" + rst.nbr_decisions_);
					}
				}
			}
		}*/
		
		for (int i = 0; i <  1; i++){
			for (int j =0; j < 1 ; j ++){
				for (int k =0;  k < 1; k++){
					for (int l =0; l < 1; l++){
						rst.max_nbr_options_ = 20;
						rst.min_nbr_options_ = 15;
						rst.min_nbr_variables_ = 1500;
						rst.nbr_decisions_ = 50;
						rst.nbr_objectives_ = 2;
						rst.modelName_ = "Test";
						rst.nbr_Simulation = 10000;
						rst.analyseRadarModel(rst.nbr_Simulation, "2-5-10-10-10-Test");
					}
				}
			}
		}
		
	}
	String output;
	int nbr_Simulation;
	private String infoValueObjective;
	private String subGraphObjective;
	AnalysisData populateExperimentData () throws Exception{
		AnalysisData result = new AnalysisData();
		InputValidator.validateOutputPath(output);
		// populate data
		result.setSimulationNumber(nbr_Simulation);
		if (output.trim().charAt(output.length()-1) != '/'){
			result.setOutputDirectory(output.trim() +"/");
		}
		return result;
		
	}
	Model loadModel (String resultpath, String modelName) throws Exception{
		//4. when parse is specified quickly parse and write a message that the model is parsed.
		Model semanticModel =null;
		String testmodel = null;
		SyntheticModelGenerator smg = new SyntheticModelGenerator();
		smg.setMinNbrOptions(min_nbr_options_);
		smg.setMaxNbrOptions(max_nbr_options_);
		smg.setMinNbrVariables(min_nbr_variables_);
		smg.setModelName(modelName_);
		smg.setNbrDecisions(nbr_decisions_);
		smg.setNbrObjectives(nbr_objectives_);
		testmodel = smg.generate();
		Helper.printResults (resultpath , testmodel, modelName +".radar", false);
		System.out.print(testmodel);
		InputValidator.validateOutputPath(output);
		try {
			semanticModel = new Parser().parseModel(testmodel, nbr_Simulation, infoValueObjective,subGraphObjective);
		}catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		return semanticModel;
	}
	void analyseRadarModel (int simulation, String expID){
		try {
    		// populate model and algorithm data
    		AnalysisData dataInput = populateExperimentData();

    		String modelResultPath = dataInput.getOutputDirectory() + modelName_ + "/ICSE/AnalysisResult/" + expID + "/"
    				+ "";
    		// get sematic model from model file
    		Model semanticModel = loadModel (modelResultPath, dataInput.getProblemName());
    		
    		
    		semanticModel.setNbr_Simulation(simulation);
    		
    		// update experiemnt data with semantic model and information value objective.
    		dataInput.setProblemName(semanticModel.getModelName());
    		InputValidator.objectiveExist(semanticModel, infoValueObjective);
    		InputValidator.objectiveExist(semanticModel, subGraphObjective);
    		
    		// analyse model
    		AnalysisResult result = ModelSolver.solve(semanticModel);
			String analysisResult = result.analysisToString();
			String analysisResultToCSV = result.analysisResultToCSV();
			Helper.printResults (modelResultPath , analysisResult, dataInput.getProblemName() +".out", false);
			Helper.printResults (modelResultPath , analysisResultToCSV, semanticModel.getModelName() +".csv", false);
			
			// generate graphs
			if (true){
				String decisionGraph = semanticModel.generateDecisionDiagram(result.getAllSolutions());
				Helper.printResults (modelResultPath + "graph/", decisionGraph, dataInput.getProblemName() + "dgraph.dot", false);
			}
			
			if (true){
				String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, result.getSubGraphObjective());
				Helper.printResults (modelResultPath + "graph/", variableGraph,  dataInput.getProblemName() + "vgraph.dot", false);
			}
			
    		if (true){
    			if (result.getShortListObjectives().get(0).length == 2){
					TwoDPlotter twoDPlot = new TwoDPlotter();
					twoDPlot.plot(semanticModel,modelResultPath, result);
				}else if (result.getShortListObjectives().get(0).length == 3){
					ScatterPlot3D sc3D= new ScatterPlot3D( );
					sc3D.plot(semanticModel, modelResultPath, result);;
;				}
    		}
    		System.out.println("Finished!");
    		
    	}catch (Exception e){
    		System.out.print("Error: ");
    		System.out.println(e.getMessage());
    	}
	}
	

}
