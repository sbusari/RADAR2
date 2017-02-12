package radar.scale.test;

import java.io.IOException;
import java.util.HashMap;

public class ScaleTest extends Experiment  {

	public static void main(String[] args) throws IOException, InterruptedException {
		ScaleTest exp = new ScaleTest();

	    exp.experimentName_ = "Scalability";
		exp.map_ = new HashMap<String, Integer[]>();
		exp.map_.put("Simulation", new Integer[]{10000, 10000});
		exp.map_.put("Decision", new Integer[]{4, 4 });
		exp.map_.put("Option", new Integer[]{3,3});
		exp.map_.put("Objective", new Integer[]{2,2});
		exp.map_.put("Variable", new Integer[]{10,10});
		exp.modelName = "QuickTest";
		exp.modelType = "simple";
		exp.testResultName= "QuickTest";
		exp.output = args[0];
		//exp.output = "/Users/INTEGRALSABIOLA/Documents/JavaProject";
		exp.outputPath = exp.output + "/" + exp.modelName + "/ICSE/AnalysisResult/" ;
		//exp.maxRunTimeMilliSeconds = 1800000L;
		exp.maxRunTimeMilliSeconds = Long.parseLong(args[1]);
	    // Run the experiments
	    int numberOfThreads =1 ;
	    exp.runExperiment(numberOfThreads) ;
	}

}
