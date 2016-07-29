//  RandomSearch_Settings.java 
//
//  Authors:
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

import java.util.HashMap;
import java.util.Properties;

import radar.enumeration.DecisionVectorType;
import radar.experiment.data.AlgorithmParameter;
import radar.jmetal.core.Algorithm;
import radar.jmetal.core.Problem;
import radar.jmetal.evolutionary.operators.*;
import radar.jmetal.experiments.Settings;
import radar.jmetal.util.JMException;
import radar.jmetal.util.ProblemFactory;
import radar.model.Parser;

/**
 * Settings class of algorithm RandomSearch
 */
public class RandomSearch_Settings extends Settings {
  // Default experiments.settings
  public int maxEvaluations_ = 25000;
  public int populationSize_                 ;
  public Parser parserEngine_		 ;
  public String alternativesObjectiveAndDecisionsPath_;
  DecisionVectorType typeOfDecisonVector_;
  
  /**
   * Constructor
   * @param problem Problem to solve
   */
  public RandomSearch_Settings(String problem,DecisionVectorType typeOfDecisonVector) {
    super(problem, typeOfDecisonVector);
    
    Object [] problemParams = {"Real"};
    try {
	    problem_ = (new ProblemFactory()).getProblem(problemName_, problemParams);
    } catch (JMException e) {
	    e.printStackTrace();
    }      
  } // RandomSearch_Settings
	public RandomSearch_Settings(String problemName, String solutionType,
			Parser goalParserEngine, Problem problem,AlgorithmParameter param, DecisionVectorType typeOfDecisonVector) {
		parserEngine_= goalParserEngine;
		problem_ =problem;
	    populationSize_              = param.getPopulationSize()   ;
	    maxEvaluations_              = param.getMaxEvaluation() ;
	    typeOfDecisonVector_= typeOfDecisonVector;
	}

  /**
   * Configure the random search algorithm with default parameter experiments.settings
   * @return an algorithm object
   * @throws jmetal.util.JMException
   */
  public Algorithm configure() throws JMException {
    Algorithm algorithm;

    // Creating the problem
    algorithm = new RandomSearch(problem_,parserEngine_);

    // Algorithm parameters
    algorithm.setInputParameter("maxEvaluations", maxEvaluations_);
    algorithm.setInputParameter("populationSize",populationSize_);

    return algorithm;
  } // Constructor

  /**
   * Configure SMPSO with user-defined parameter experiments.settings
   * @return A SMPSO algorithm object
   */
  @Override
  public Algorithm configure(Properties configuration) throws JMException {
    Algorithm algorithm ;

    HashMap parameters ; // Operator parameters

    // Creating the algorithm.
    algorithm = new RandomSearch(problem_) ;

    // Algorithm parameters
    maxEvaluations_  = Integer.parseInt(configuration.getProperty("maxEvaluations",String.valueOf(maxEvaluations_)));

    algorithm.setInputParameter("maxEvaluations", maxEvaluations_);

    return algorithm ;
  }
} // RandomSearch_Settings
