package radar.empirical.study;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import radar.enumeration.DecisionVectorType;
import radar.enumeration.OptimisationType;
import radar.exception.ModelException;
import radar.experiment.data.AlgorithmParameter;
import radar.experiment.data.ExperiementData;
import radar.experiment.data.UIResult;
import radar.information.analysis.InformationValueAnalysis;
import radar.jmetal.approximate.algorithm.IBEA_Settings;
import radar.jmetal.approximate.algorithm.MoCell_Settings;
import radar.jmetal.approximate.algorithm.NSGAII_Settings;
import radar.jmetal.approximate.algorithm.PAES_Settings;
import radar.jmetal.approximate.algorithm.RandomSearch_Settings;
import radar.jmetal.approximate.algorithm.SPEA2_Settings;
import radar.jmetal.core.Algorithm;
import radar.jmetal.core.Problem;
import radar.jmetal.exact.algorithm.ExhaustiveSearch_Settings;
import radar.jmetal.experiments.Experiment;
import radar.jmetal.experiments.Settings;
import radar.jmetal.experiments.util.Friedman;
import radar.jmetal.util.JMException;
import radar.model.AlternativeAnalyser;
import radar.model.Parser;
import radar.model.QualityVariable;
import radar.model.problem.DecisionProblem;
import radar.utilities.ConfigSetting;
import radar.utilities.SolutionDetail;

public class EmpiricalStudy extends Experiment {

	private String modelFilePath;
	String alternativesObjectiveAndDecisionsPath;
	private String solutionType;
	private Parser goalParserEngine;
	private Problem problem;
	private AlgorithmParameter algorithmParameter;
	public int repetitions;
	private int numberOfThreads;
	private DecisionVectorType typeOfDecisonVector;
	ExperiementData expData_;
	UIResult expResult_;
	public EmpiricalStudy(){
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void algorithmSettings(String problemName,
			int problemIndex, Algorithm[] algorithm)
			throws ClassNotFoundException {
		try {
			int numberOfAlgorithms = algorithmNameList_.length;
			HashMap[] parameters = new HashMap[numberOfAlgorithms];
			for (int i = 0; i < numberOfAlgorithms; i++) {
				parameters[i] = new HashMap();
			} // for

			if (!paretoFrontFile_[problemIndex].equals("")) {
				for (int i = 0; i < numberOfAlgorithms; i++)
					parameters[i].put("paretoFrontFile_",
							paretoFrontFile_[problemIndex]);
			} // if

			if ((!paretoFrontFile_[problemIndex].equals(""))
					|| (paretoFrontFile_[problemIndex] == null)) {
				for (int i = 0; i < numberOfAlgorithms; i++)
					parameters[i].put("paretoFrontFile_",
							paretoFrontFile_[problemIndex]);
			} // if
			for (int i = 0; i < numberOfAlgorithms; i++) {
				switch (this.algorithmNameList_[i]) {
				case "ExhaustiveSearch": {
					algorithm[i] = new ExhaustiveSearch_Settings(problemName,
							solutionType, goalParserEngine, problem, alternativesObjectiveAndDecisionsPath)
							.configure(parameters[i]);
					break;
				}
				case "NSGAII": {
					algorithm[i] = new NSGAII_Settings(problemName,solutionType, goalParserEngine, problem,algorithmParameter,alternativesObjectiveAndDecisionsPath,typeOfDecisonVector).configure(parameters[i]);
					break;
				}
				case "SPEA2": {
					algorithm[i] = new SPEA2_Settings(problemName,solutionType, goalParserEngine, problem,algorithmParameter,alternativesObjectiveAndDecisionsPath,typeOfDecisonVector).configure(parameters[i]);
					break;
				}
				case "IBEA": {
					algorithm[i] = new IBEA_Settings(problemName, solutionType,goalParserEngine, problem, algorithmParameter,alternativesObjectiveAndDecisionsPath,typeOfDecisonVector)
							.configure(parameters[i]);
					break;
				}
				case "MoCell": {
					algorithm[i] = new MoCell_Settings(problemName, solutionType,goalParserEngine, problem, algorithmParameter,alternativesObjectiveAndDecisionsPath,typeOfDecisonVector)
							.configure(parameters[i]);
					break;
				}
				case "PAES": {
					algorithm[i] = new PAES_Settings(problemName, solutionType,goalParserEngine, problem, algorithmParameter,alternativesObjectiveAndDecisionsPath,typeOfDecisonVector)
							.configure(parameters[i]);
					break;
				}
				case "RandomSearch": {
					algorithm[i] = new RandomSearch_Settings(problemName, solutionType,goalParserEngine, problem, algorithmParameter,typeOfDecisonVector)
							.configure(parameters[i]);
					break;
				}
				default:
					System.out.println("Design Optimisation Class:  No optimisation algorithm specified.");
				}
			}

		} catch (IllegalArgumentException | IllegalAccessException
				| JMException ex) {
			Logger.getLogger(EmpiricalStudy.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	} // algorithmSettings
	
	//public ExperimentsResults run (String model, String optimisationtype, String problemName, int simulationRun,boolean useDefault) throws IOException, JMException {
	/**
	 * Main method
	 * 
	 * @param args
	 * @throws JMException
	 * @throws Exception
	 */
	public static void main(String[] args) throws IOException, JMException,ModelException, RuntimeException, InterruptedException {
		UIResult expResults = new UIResult();
		try
		{	
			EmpiricalStudy exp = new EmpiricalStudy();
			String typeOfOptimisation = "EXACT";
			// first index is now the algorithm.
			String algorithm = "ExhaustiveSearch";
			if(args[0] != null || !StringUtils.isEmpty(args[0] )){
				algorithm =args[0]; //"APPROXIMATE";
			}
			String problemName = "ERAM";
			if(args[1] != null || !StringUtils.isEmpty(args[1] )){
				problemName =args[1]; //"APPROXIMATE";
			}
			List<String> infoValueObj = new ArrayList<String>();
			if(args[2] != null || !StringUtils.isEmpty(args[2] )){
				infoValueObj =new ArrayList<String>( Arrays.asList(args[2].split(",")));
			}
			OptimisationType optimisationType = OptimisationType.valueOf("APPROXIMATE".toUpperCase(Locale.ENGLISH));
			if (algorithm.equals("ExhaustiveSearch")){
				optimisationType = OptimisationType.valueOf("EXACT".toUpperCase(Locale.ENGLISH));
				typeOfOptimisation ="EXACT";
			}else if (algorithm.equals("NSGAII") || algorithm.equals("IBEA") || algorithm.equals("SPEA2") ){
				optimisationType = OptimisationType.valueOf("APPROXIMATE".toUpperCase(Locale.ENGLISH));
				typeOfOptimisation ="APPROXIMATE";
			}else{
				
			}
			String exactAlgorithm = ConfigSetting.DEFAULT_EXACT_ALGORITHM;

			String[] algorithmNameList_ = {algorithm};
			long overallExecutionTime = 0L;
			
			// set the type of optimisation, whether exact, approximate or both.
			exp.optimisationType_ = optimisationType;
			
			try {
				
				String modelFilePath = "";
				if (problemName.equals("NASAM") ){
					modelFilePath = ConfigSetting.ROOTDIRECTORY + ConfigSetting.NASAM;
				}else if (problemName.equals("ERAM") ){
					modelFilePath = ConfigSetting.ROOTDIRECTORY +  ConfigSetting.ERAM;
				}else if (problemName .equals("BSPDM" )){
					modelFilePath = ConfigSetting.ROOTDIRECTORY +  ConfigSetting.BSPDM;
				}else if (problemName.equals("PCFDM") ){
					modelFilePath = ConfigSetting.ROOTDIRECTORY +  ConfigSetting.PCFDM;
				}else if (problemName.equals("CBA")){
					modelFilePath = ConfigSetting.ROOTDIRECTORY +  ConfigSetting.CBA;
				}
				System.out.println (" model path: "+ modelFilePath);
				exp.setUp(modelFilePath,typeOfOptimisation, infoValueObj);
			} catch (Exception e) {
				throw new RuntimeException (e.getMessage());
			}

			if (exp.goalParserEngine != null && exp.goalParserEngine.getSemanticModel() != null && exp.goalParserEngine.getSemanticModel().getDecisions() == null){
				throw new RuntimeException ("Model contains no alternative options. Thus, optimisation can't be performed.");
			}
			expResults.setGoalModelParserEngine(exp.goalParserEngine);
			// assign name for the experiment.
			exp.experimentName_ = problemName ; // Config.getConfig("EXPERIMENT_NAME") ;
			// assign the kind of proble to be solved.
			exp.problemList_ = new String[] { problemName};
			// assign name of pareto front file.
			exp.paretoFrontFile_ = new String[] { problemName+ ".pf" };
			// assign indicators used to measue performance of the approximate
			// algorithms.
			exp.indicatorList_ = new String[] { "Coverage", "HV", "SPREAD", "IGD", "GD","EPSILON" };
			
			// assign algorithm to be run
			exp.algorithmNameList_ = algorithmNameList_;
			int numberOfAlgorithms = exp.algorithmNameList_.length;
			// assign the number of algorithms.
			String outputDirectory = ConfigSetting.OUTPUT_DIRECTORY;
			if (outputDirectory == null) {
				throw new FileNotFoundException(
						"The output directory path needs to be specified in the config.properties file.");
			}
			// assign folder where all outputs are stored.
			exp.experimentBaseDirectory_ = outputDirectory
					+ exp.experimentName_ ;//+ "/" + typeOfOptimisation;
			
			// assign this to be used in reading the pareto optimal solutions to be dispalyed.
			//exp.alternativesObjectiveAndDecisionsPath =  exp.experimentBaseDirectory_ + "/referenceFronts/";
			exp.alternativesObjectiveAndDecisionsPath =  exp.experimentBaseDirectory_;
			exp.paretoFrontDirectory_ = outputDirectory + "data/paretoFronts";
			// initialise all algorithm settings.
			exp.algorithmSettings_ = new Settings[numberOfAlgorithms];
			// assign independentRuns to repetitions so as to compute indicator
			// values over all runs.
			exp.independentRuns_ = exp.repetitions;
			exp.numberOfThreads = exp.numberOfThreads;
			
			exp.useExactAsTrueFront_ = true;
			if (typeOfOptimisation.equals("EXACT")){	
				exp.independentRuns_ = 1;
				exp.numberOfThreads =1;
			}else{
				exp.independentRuns_ = ConfigSetting.ALGORITHM_RUNS;
				exp.numberOfThreads = ConfigSetting.THREADS;
			}
			
			// initialise experiment parameters.
			exp.initExperiment();
			// Run the experiments
			long start = System.currentTimeMillis();
			LinkedHashMap<String, SolutionDetail> solutionDetail = exp.runExperiment(exp.numberOfThreads);
			long end = System.currentTimeMillis();
			long runTime = (end - start) / 1000;
			System.err.println(" execution time used for running "
					+ exp.repetitions + " repetitions is :\t" + runTime);
			overallExecutionTime += runTime;
			// generate quality indicators
			exp.generateQualityIndicators(true);
			
			exp.WriteIndicatorResultsToCSV();
			// generate latex tables
			exp.generateLatexTables();
			
			// Configure the R scripts to be generated
			int rows;
			int columns;
			String prefix;
			String[] problems;
			boolean notch;

			// Configuring scripts for DesignProblem
			rows = 3;
			columns = 2;
			prefix = new String("Optimising");
			problems = exp.problemList_;

			exp.generateRBoxplotScripts(rows, columns, problems, prefix,notch = false, exp);
			exp.generateRWilcoxonScripts(problems, prefix, exp);

			// Applying Friedman test
			Friedman test = new Friedman(exp);
			test.executeTest("EPSILON");
			test.executeTest("HV");
			test.executeTest("SPREAD");
			
			LinkedHashMap<Integer, LinkedHashMap<String,SolutionDetail>> solutionDetailsForEachRun = new LinkedHashMap<Integer, LinkedHashMap<String,SolutionDetail>>();
  			for (int i=0; i < exp.independentRuns_; i++){
  				LinkedHashMap<String,SolutionDetail> solutionDetailsPerAlg = new LinkedHashMap<String,SolutionDetail> ();
				for (String alg : algorithmNameList_){
					for (Map.Entry<String, SolutionDetail> entry: solutionDetail.entrySet()){
						String algorithAndRun = alg + "." + i;
						if ( entry.getKey().equals(algorithAndRun)){
							solutionDetailsPerAlg.put(alg,entry.getValue());
						}
					}
					
				}
				solutionDetailsForEachRun.put(i, solutionDetailsPerAlg);
			}
			Parser parser = null;
			InformationValueAnalysis infoAnalysis = null;
			// first index is run, second index is algorithm and value is shortlist
			LinkedHashMap<Integer, LinkedHashMap<String,LinkedHashMap<String, List<ArrayList<String>>>>> allAlgorithmRunsshortlist = new LinkedHashMap<Integer, LinkedHashMap<String,LinkedHashMap<String, List<ArrayList<String>>>>>(); 
			//int i =0;
			for (Map.Entry<Integer, LinkedHashMap<String,SolutionDetail>> mainentry : solutionDetailsForEachRun.entrySet()){
				//int j= 0;
				LinkedHashMap<String,LinkedHashMap<String, List<ArrayList<String>>>> anAlgorithmShortlist = new LinkedHashMap<String,LinkedHashMap<String, List<ArrayList<String>>>>();
				for (Map.Entry<String,SolutionDetail> entry : mainentry.getValue().entrySet()){
					parser = entry.getValue().getGoalModelParserEngine();
				    infoAnalysis = new InformationValueAnalysis(parser.getSemanticModel().getSimulationNumber());
				    infoAnalysis.performInformationAnalysis(parser.getSemanticModel(),entry.getValue().getNonDominatedSolutions());
				    LinkedHashMap<String, List<ArrayList<String>>> shortlist = AlternativeAnalyser.getShortlist (entry.getValue(), entry.getKey(),mainentry.getKey());
					anAlgorithmShortlist.put(entry.getKey(),shortlist);
				}
				allAlgorithmRunsshortlist. put(mainentry.getKey(),anAlgorithmShortlist );
			}
			
		}catch (IndexOutOfBoundsException e){
			throw new RuntimeException ("Results cannot be displayed!");
		}
		catch (NullPointerException e){
			throw new RuntimeException ("Computation error!");
		}
		catch (RuntimeException e){
			throw new RuntimeException (e.getMessage());
		}
		
	} // main
	


	public void setUp(String modelFilePath, String typeOfOptimisation, List<String> infoValueObj) throws Exception ,ModelException, RuntimeException, InterruptedException {
		this.modelFilePath = modelFilePath; 
		//String typeOfOptimisation = Config.getConfig("OPTIMISATION_TYPE","APROXIMATE");
		OptimisationType optimisationType = OptimisationType.valueOf(typeOfOptimisation.toUpperCase(Locale.ENGLISH));
		
		String model = readFile(modelFilePath);
		this.goalParserEngine = new Parser (model,ConfigSetting.NUMBER_OF_SIMULATION,infoValueObj);
		if (!typeOfOptimisation.equals("EXACT")){
			this.algorithmParameter = new AlgorithmParameter().getParameterSettings(goalParserEngine);		
		}
		this.goalParserEngine.getSemanticModel().setSimulationNumber(ConfigSetting.NUMBER_OF_SIMULATION);
		//this.goalParserEngine.getSemanticModel().setInfoValueObjective(infoValueObj);
		// need  to remove params from model at this point.
		///this.goalParserEngine.getSemanticModel().setParams(params);
		this.solutionType = "ArrayBitVector";	
		problem = new DecisionProblem(solutionType, goalParserEngine, optimisationType);
	}
	@SuppressWarnings("resource")
	public String readFile(String fileName) {
		StringBuilder model = new StringBuilder(100);
		BufferedReader bfr = null;

		try {
			bfr = new BufferedReader(new FileReader(new File(fileName)));
			String line = null;
			while ((line = bfr.readLine()) != null) {
				model.append(line + "\n");
			}
			model.delete(model.length() - 1, model.length());
			return model.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//public void setUp(String model, String optimisationtype, int simulationRun, boolean useDefault) throws Exception {
	public void setUp(ExperiementData expdata) throws Exception {
		
		String modelString = expdata.getTextualModel(); //model;
		//String typeOfOptimisation = expdata.getTypeOfOptimisation();
		
		OptimisationType optimisationType =expdata.getTypeOfOptimisation();
		
		this.goalParserEngine = new Parser(modelString,ConfigSetting.NUMBER_OF_SIMULATION, null);
		this.goalParserEngine.getSemanticModel().setSimulationNumber(expdata.getSimulationNumber());
		//this.goalParserEngine.getSemanticModel().setInfoValueObjective(infoValueObj);
		this.algorithmParameter = new AlgorithmParameter(goalParserEngine,expdata);
		this.problem = new DecisionProblem(solutionType, goalParserEngine,optimisationType);		
	}
	String[] getAlgorithmList(String typeOfOptimisation, String exactAlgorithm) {
		boolean runAllApproximate = ConfigSetting.RUN_ALL_APPROX_ALG;
		String[] approxAlgorithmList = ConfigSetting.APROXIMATE_ALGORITHM_LIST.split("[,]");
		String[] algorithmNameList_ = null;
		switch (typeOfOptimisation) {
		case "EXACT": {
			algorithmNameList_ = new String[] { exactAlgorithm };
			repetitions = 1;
			numberOfThreads = 1;
			break;
		}

		case "APPROXIMATE": {
			algorithmNameList_ = runAllApproximate == true ? approxAlgorithmList: new String[] { ConfigSetting.DEFAULT_APRROX_ALGORITHM };
			repetitions = ConfigSetting.ALGORITHM_RUNS;
			numberOfThreads = ConfigSetting.THREADS;
			break;
		}
		// remember to remove this before publishing cos its a paper.
		case "BOTH": {
			ArrayList<String> appAlgList = new ArrayList<String>();
			repetitions = 1;
			numberOfThreads = 1;
			// add exact first so as to use its solutions for comaparison.
			appAlgList.add(exactAlgorithm);
			appAlgList.addAll(Arrays.asList(approxAlgorithmList));
			algorithmNameList_ = appAlgList.toArray(new String[appAlgList
					.size()]);
			break;
		}

		default: {
			algorithmNameList_ = new String[] { exactAlgorithm };
			repetitions = 1;
			numberOfThreads = 1;
			break;
		}

		}
		return algorithmNameList_;
	}
	
	 String[] getAlgorithmList( ExperiementData expData_) {
		
		boolean runAllApproximate = expData_.getRunAllApproxAlgorithms();
		String[] approxAlgorithmList = expData_.getApproxAlgorithmList();
		String[] algorithmNameList_ = null;
		switch (expData_.getTypeOfOptimisation().toString()) {
			case "EXACT" : {
				algorithmNameList_ = new String[] { expData_.getExactAlgorithm() };
				repetitions = 1;
				numberOfThreads = 1;
				break;
			}
			case "APPROXIMATE": {
				ArrayList<String> appAlgList = new ArrayList<String>();
				appAlgList.addAll(Arrays.asList(approxAlgorithmList));
				algorithmNameList_ = runAllApproximate == true ? approxAlgorithmList: appAlgList.toArray(new String[appAlgList.size()]);
				repetitions = expData_.getAlgorithmRuns();
				numberOfThreads = expData_.getThreads();
				break;
			}
			case "BOTH": {
				ArrayList<String> appAlgList = new ArrayList<String>();
				repetitions = 1;
				numberOfThreads = 1;
				// add exact first so as to use its solutions for comaparison.
				appAlgList.add(expData_.getExactAlgorithm());
				appAlgList.addAll(Arrays.asList(approxAlgorithmList));
				algorithmNameList_ = appAlgList.toArray(new String[appAlgList.size()]);
				break;
			}
			default: {
				algorithmNameList_ = new String[] { expData_.getExactAlgorithm()};
				repetitions = 1;
				numberOfThreads = 1;
				break;
			}
		}
		return algorithmNameList_;
	}
	private boolean deleteSolutionsFromPreviousRun (String path){
		File solutionsFromPreviousRun;
		String directory;
		//directory = baseDitectory + "/referenceFronts/";
		boolean deleted = false;
		solutionsFromPreviousRun = new File(path);
		if (solutionsFromPreviousRun.exists()) {
			//deleted = new File(directory + "solutions.all.lastRun." + algorithm).delete();
			deleted = new File(path).delete();
			System.out.println("deleted " + path);
		}else{
			return true;
		}
		return deleted;
	     
	}
}

