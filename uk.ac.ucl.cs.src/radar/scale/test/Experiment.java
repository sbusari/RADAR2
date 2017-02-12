package radar.scale.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import radar.utilities.Helper;

public abstract class Experiment  {
	  String experimentName_;
	  Map<String, Integer[]> map_;
	  Map<String, Integer> param_;
	  String modelName;
	  String modelType;
	  String output;
	  String outputPath;
	  String testResultName;
	  long maxRunTimeMilliSeconds;
	  static Integer [] getArrayElements (Integer [] arrayRange){
			List<Integer> result = new ArrayList<Integer>();
			for (int i = arrayRange[0]; i <= arrayRange[1]; i++){
				result.add(i);
			}
			return result.toArray(new Integer [result.size()]);
	  }
	  static Integer [] getArrayVariableElements (Integer [] arrayRange){
			List<Integer> result = new ArrayList<Integer>();
			for (int i = arrayRange[0]; i <= arrayRange[1]; i+=100){
				result.add(i);
			}
			return result.toArray(new Integer [result.size()]);
	  }
	  static Integer [] getArraySimulationElements (Integer [] arrayRange){
			List<Integer> result = new ArrayList<Integer>();
			for (int i = arrayRange[0]; i <= arrayRange[1]; i*=2){
				result.add(i);
			}
			return result.toArray(new Integer [result.size()]);
	  }
	  public void runExperiment(int numberOfThreads) throws IOException, InterruptedException {
		  
		  	Integer[] simulation = getArraySimulationElements(map_.get("Simulation"));
			Integer[] decision = getArrayElements(map_.get("Decision"));
			Integer[] option = getArrayElements(map_.get("Option"));
			Integer[] variable = getArrayVariableElements(map_.get("Variable"));
			Integer[] objective = getArrayElements(map_.get("Objective"));
			
			Helper.printResults (outputPath, "ModelInstance, Solution Space, Design Space, Design Space Time, Simulation Time, Optimisation Time, Information Value Time, Total time (seconds), , Design Space Memory, Simulation Memory, Optimiser Memory, InformationValueMemory, Total Memory (MB) \n", testResultName+".csv", true);
			for (int l =0; l < simulation.length; l++){
				for (int m = 0; m <  objective.length; m++){
					for (int j =0; j < decision.length ; j ++){
						for (int k =0;  k < option.length; k++){
							for (int n =0; n < variable.length; n++){
								param_ = new HashMap<String, Integer>();
						    	param_.put("Simulation", simulation[l]);
						    	param_.put("Decision", decision[j]);
						    	param_.put("Option", option[k]);
						    	param_.put("Objective", objective[m]);
						    	param_.put("Variable", variable[0]);
								
								 long startTime = System.currentTimeMillis();
								  Thread[] p = new RadarExperiment[numberOfThreads];
								    for (int i = 0; i < numberOfThreads; i++) {
								      p[i] = new RadarExperiment( modelName, modelType, output, testResultName, param_);
								      p[i].start();
								    }
								    
								    for (int i = 0; i < numberOfThreads; i++) {
								    	 while (p[i].isAlive()) {
									    	  ///System.out.println("Still waiting...");
									            // Wait maximum of 1 second
									            // for MessageLoop thread
									            // to finish.
									    	  p[i].join(1000L);
									            if (((System.currentTimeMillis() - startTime) > maxRunTimeMilliSeconds)
									                  && p[i].isAlive()) {
									            	System.out.println("Tired of waiting!");
									            	p[i].interrupt();
									            	// stop immediately
									            	p[i].stop();
									            	String param = "Param: " + "Obj(" + objective[m] + ")-O(" +  option[k] + ")-D(" + decision[j] + ")-S(" + simulation[l] + ")";
									            	Helper.printResults (outputPath, param + ", NIL, NIL, NIL, NIL, NIL, NIL, >"+ maxRunTimeMilliSeconds/1000, testResultName+".csv", true);
									            }
									        }
								      }
								     
								 
							}
							
						}
					}
				}
			}
		  }

}
