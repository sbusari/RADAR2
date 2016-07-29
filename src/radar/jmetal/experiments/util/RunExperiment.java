//  runExperiment.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//       Jorge Rodriguez
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo, Jorge Rodriguez
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package radar.jmetal.experiments.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import radar.enumeration.DecisionVectorType;
import radar.enumeration.OptimisationType;
import radar.jmetal.core.Algorithm;
import radar.jmetal.core.Problem;
import radar.jmetal.core.SolutionSet;
import radar.jmetal.experiments.Experiment;
import radar.jmetal.experiments.Settings;
import radar.jmetal.util.JMException;
import radar.utilities.Helper;
import radar.utilities.SolutionDetail;
/**
 * Class implementing the steps to run an experiment
 */
public class RunExperiment extends Thread {

	public Experiment experiment_ ;
	public int id_ ;
	public HashMap<String, Object> map_ ;
	public int numberOfThreads_ ;
	public int numberOfProblems_ ;

	// Inicio modificación planificación Threads
	static boolean finished;
	// Fin modificación planificación Threads

	String experimentName_;
	OptimisationType optimisationType_;
	String[] algorithmNameList_; // List of the names of the algorithms to be executed
	String[] problemList_; // List of problems to be solved
	String[] paretoFrontFile_; // List of the files containing the pareto fronts
	// corresponding to the problems in problemList_
	String[] indicatorList_; // List of the quality indicators to be applied
	String experimentBaseDirectory_; // Directory to store the results
	String latexDirectory_; // Directory to store the latex files
	String rDirectory_; // Directory to store the generated R scripts
	String paretoFrontDirectory_; // Directory containing the Pareto front files
	String outputParetoFrontFile_; // Name of the file containing the output
	// Pareto front
	String outputParetoSetFile_; // Name of the file containing the output
	// Pareto set
	int independentRuns_; // Number of independent runs per algorithm
	Settings[] algorithmSettings_; // Paremeter experiments.settings of each algorithm
	

	public RunExperiment(Experiment experiment, 
			HashMap<String, Object> map,
			int id,
			int numberOfThreads,
			int numberOfProblems) {
		experiment_ = experiment ;
		id_ = id ;
		map_ = map ;
		numberOfThreads_ = numberOfThreads  ;
		numberOfProblems_ = numberOfProblems;
		// Inicio modificación planificación Threads
		finished = false;
		// Fin modificación planificación Threads		
	}

	public void run() {
		Algorithm[] algorithm; // jMetal algorithms to be executed

		String experimentName = (String) map_.get("experimentName");
		experimentBaseDirectory_ = (String) map_.get("experimentDirectory");
		algorithmNameList_ = (String[]) map_.get("algorithmNameList");
		problemList_ = (String[]) map_.get("problemList");
		indicatorList_ = (String[]) map_.get("indicatorList");
		paretoFrontDirectory_ = (String) map_.get("paretoFrontDirectory");
		paretoFrontFile_ = (String[]) map_.get("paretoFrontFile");
		independentRuns_ = (Integer) map_.get("independentRuns");
		outputParetoFrontFile_ = (String) map_.get("outputParetoFrontFile");
		outputParetoSetFile_ = (String) map_.get("outputParetoSetFile");
		optimisationType_ = (OptimisationType) map_.get("optimisationType") ;

		int numberOfAlgorithms = algorithmNameList_.length;
		
		algorithm = new Algorithm[numberOfAlgorithms] ;

		// Inicio modificación planificación Threads

		//SolutionSet resultFront = null;  
		SolutionDetail solutionDetail = null;  

		int[] problemData; // Contains current problemId, algorithmId and iRun

		while(!finished){

			problemData = null;
			problemData = experiment_.getNextProblem();

			if(!finished && problemData != null){
				int problemId = problemData[0];
				int alg = problemData[1];
				int runs = problemData[2];

				// The problem to solve
				Problem problem;
				String problemName;

				// STEP 2: get the problem from the list
				problemName = problemList_[problemId];

				// STEP 3: check the file containing the Pareto front of the problem

				// STEP 4: configure the algorithms
				try {
					experiment_.algorithmSettings(problemName, problemId, algorithm);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				problem = algorithm[0].getProblem() ;
				
				// STEP 5: run the algorithms
				
				// STEP 6: create output directories
				File experimentDirectory;
				String directory;

				directory = experimentBaseDirectory_ + "/data/" + algorithmNameList_[alg] + "/" +
						problemList_[problemId];

				experimentDirectory = new File(directory);
				if (!experimentDirectory.exists()) {
					boolean result = new File(directory).mkdirs();
					System.out.println("Creating " + directory);
				}

				// STEP 7: run the algorithm
				System.out.println(Thread.currentThread().getName() + " Running algorithm: " + 
						algorithmNameList_[alg] +
						", problem: " + problemList_[problemId] +
						", run: " + runs);
				try {
					try {
						long initRunTime =System.currentTimeMillis();
						solutionDetail= algorithm[alg].execute();
						long elapsedTime = System.currentTimeMillis() -initRunTime;
						String algorithmAndRun = "" +algorithmNameList_[alg]+ "." + runs;
						String runTime = "" +elapsedTime;
						solutionDetail.setExecutionTime(elapsedTime);
						// print run time
						experiment_.setAllRunSolutionDetails_(algorithmAndRun, solutionDetail);
						try {
							Helper.printResult(directory , runTime,  "runtime" + "."+ runs );
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (JMException ex) {
					finished = true;
					Logger.getLogger(Experiment.class.getName()).log(Level.SEVERE, null, ex);
					throw new RuntimeException (ex);
				}catch ( RuntimeException e){
					finished = true;
					throw new RuntimeException (e);
				}

				// STEP 8: put the results in the output directory
				solutionDetail.getNonDominatedSolutions().printObjectivesToFile(directory + "/" + outputParetoFrontFile_ + "." + runs);
				try {
					solutionDetail.getNonDominatedSolutions().printArrayBitVectorVariablesToFile(directory + "/" + outputParetoSetFile_ + "." + runs);					
				} catch (JMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!finished){
					if(experiment_.finished_){
						finished = true;						
					}
				}
			} // if
		} //while
		// Fin modificación planificación Threads
	}
}
