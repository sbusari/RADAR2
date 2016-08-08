	package jmetal.metaheuristics.nsgaII;
	
	import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.problems.ProblemFactory;
import jmetal.problems.radar.DecisionProblem;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import uk.ac.ucl.cs.radar.model.SbseData;
import uk.ac.ucl.cs.radar.model.SolutionQuality;
	
	public class NSGAII_Solver {
	public static Logger      logger_ ;      // Logger object
	public static FileHandler fileHandler_ ;// FileHandler object
	
	/**
	* @param args Command line arguments.
	* @throws JMException 
	* @throws IOException 
	* @throws SecurityException 
	* Usage: three options
	*      - jmetal.metaheuristics.nsgaII.NSGAII_main
	*      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName
	*      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName paretoFrontFile
	*/
	@SuppressWarnings("unchecked")
	public SolutionSet solve(SbseData data) throws 
	                              JMException, 
	                              SecurityException, 
	                              IOException, 
	                              ClassNotFoundException {
	
	Map<Integer,SolutionSet> result = new LinkedHashMap<Integer,SolutionSet>();
	Problem   problem   ; // The problem to solve
	Algorithm algorithm ; // The algorithm to use
	Operator  crossover ; // Crossover operator
	Operator  mutation  ; // Mutation operator
	Operator  selection ; // Selection operator
	
	HashMap  parameters ; // Operator parameters
	
	QualityIndicator indicators ; // Object to get quality indicators
	
	// Logger object and file to store log messages
	logger_      = Configuration.logger_ ;
	fileHandler_ = new FileHandler("NSGAII_main.log"); 
	//logger_.addHandler(fileHandler_) ;
	    
	indicators = null;
	problem = new DecisionProblem(data);

	algorithm = new NSGAII(problem);
	
	// Algorithm parameters
	algorithm.setInputParameter("populationSize",data.getExperimentData().getPopulationSize());
	algorithm.setInputParameter("maxEvaluations",data.getExperimentData().getMaxEvaluation());
	
	// Mutation and Crossover for Real codification 
	parameters = new HashMap() ;
	parameters.put("probability", data.getExperimentData().getCrossoverProbability()) ;
	parameters.put("distributionIndex", 20.0) ;
	crossover = CrossoverFactory.getCrossoverOperator("RadarSinglePointCrossover", parameters);                   
	
	parameters = new HashMap() ;
	parameters.put("probability", data.getExperimentData().getMutationProbability()) ;
	parameters.put("distributionIndex", 20.0) ;
	mutation = MutationFactory.getMutationOperator("RadarBitFlipMutation", parameters);                    
	
	// Selection Operator 
	parameters = null ;
	selection = SelectionFactory.getSelectionOperator("BinaryTournament2", parameters) ;                           
	
	// Add the operators to the algorithm
	algorithm.addOperator("crossover",crossover);
	algorithm.addOperator("mutation",mutation);
	algorithm.addOperator("selection",selection);
	
	// Add the indicator object to the algorithm
	algorithm.setInputParameter("indicators", indicators) ;
	
	// Execute the Algorithm
	
	long initTime = System.currentTimeMillis();
	//for (int i =0; i < data.getExperimentData().getAlgorithmRuns(); i++){
	SolutionSet population = algorithm.execute();
	long estimatedTime = System.currentTimeMillis() - initTime;
	
	
	
	// Result messages 
	//logger_.info("Variables values have been writen to file VAR");
	population.printArrayBitVectorVariablesToFile(data.getExperimentData().getOutputDirectory() + "VAR");    
	//logger_.info("Objectives values have been writen to file FUN");
	population.printObjectivesToFile(data.getExperimentData().getOutputDirectory() +"FUN");
	
	
	String referenceFrontPath = data.getExperimentData().getOutputDirectory() + data.getExperimentData().getProblemName() + "/referenceFronts";
	
	File f = new File (referenceFrontPath); 
	if (f.exists()){
		 indicators = new QualityIndicator(problem, referenceFrontPath + "/objectives", population.size());
		 if (indicators != null) {
				SolutionQuality sq = new SolutionQuality();
				sq.setHypervolume(indicators.getHypervolume(population)) ;
				sq.setGD(indicators.getGD(population)) ;
				sq.setIGD(indicators.getIGD(population));
				sq.setSpread( indicators.getSpread(population));
				sq.setEpsilon(indicators.getEpsilon(population)) ;
				population.setSolutionQuality(sq);
				
		 } // if
		
	}
	
	int evaluations = ((Integer)algorithm.getOutputParameter("evaluations")).intValue();
	//logger_.info("Speed      : " + evaluations + " evaluations") ;
 	//logger_.info("Total execution time: "+estimatedTime + "ms"); 

	
	population.setExecutionTime(estimatedTime);
	return population;
	} //main
	} // NSGAII_main
	
