//  RandomSearch.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
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

package radar.jmetal.approximate.algorithm;

import java.util.Random;

import radar.jmetal.core.*;
import radar.jmetal.qualityIndicator.QualityIndicator;
import radar.jmetal.util.Distance;
import radar.jmetal.util.JMException;
import radar.jmetal.util.NonDominatedSolutionList;
import radar.jmetal.util.Ranking;
import radar.jmetal.util.comparators.CrowdingComparator;
import radar.model.Parser;
import radar.utilities.ConfigSetting;
import radar.utilities.SolutionDetail;

/**
 * This class implements a simple random search algorithm.
 */
public class RandomSearch extends Algorithm {
	Parser parserEngine_;
	Random rand;
  /**
  * Constructor
  * @param problem Problem to solve
  */
  public RandomSearch(Problem problem){
    super (problem) ;
  } // RandomSearch
  public RandomSearch(Problem problem, Parser parserEngine) {
	    super (problem) ;
	    parserEngine_= parserEngine;
	    rand = new Random(ConfigSetting.SEED);
	  } // RandomSearch

  /**
  * Runs the RandomSearch algorithm.
  * @return a <code>SolutionSet</code> that is a set of solutions
  * as a result of the algorithm execution
   * @throws JMException
  */
  public SolutionDetail execute() throws JMException, ClassNotFoundException {
    int maxEvaluations ;
    int evaluations    ;

    maxEvaluations    = ((Integer)getInputParameter("maxEvaluations")).intValue();
    int populationSize = ((Integer) getInputParameter("populationSize")).intValue();

    //Initialize the variables
    evaluations = 0;
    SolutionDetail solutionDetail = new SolutionDetail ();
    SolutionSet dominatedSolution = new SolutionSet(2*maxEvaluations);
    NonDominatedSolutionList ndl = new NonDominatedSolutionList();

    // Create the initial solutionSet
    Solution newSolution;
    for (int i = 0; i < maxEvaluations; i++) {
      newSolution = new Solution(problem_);
      problem_.evaluate(newSolution);
      problem_.evaluateConstraints(newSolution);
      evaluations++;
      ndl.addExactSolution(newSolution);
      dominatedSolution.add(newSolution);
    } //for
    
    while  (evaluations < maxEvaluations){
    	  for (int i = 0; i < maxEvaluations; i++) {
    		  Solution solution = new Solution(problem_);
    	      problem_.evaluate(solution);
    	      problem_.evaluateConstraints(solution);
    	      evaluations++;
    	      ndl.addExactSolution(solution);
    	      dominatedSolution.add(solution);
    	    } //for
    	  
    	  while (ndl.size() > populationSize){
    		  int size = ndl.size();
    		  int index = rand.nextInt(size);
    		  ndl.remove(index);
    	  }
    }

    while (ndl.size() > populationSize){
		  int size = ndl.size();
		  int index = rand.nextInt(size);
		  ndl.remove(index);
	  }
    //return ndl;
    ndl.setParserEngne(parserEngine_);
    solutionDetail.setNonDominatedSolutions(ndl);
    solutionDetail.setDominatedSolutions(dominatedSolution);
    solutionDetail.setGoalModelParserEngine(parserEngine_);
    return solutionDetail;
  } // execute
} // RandomSearch
