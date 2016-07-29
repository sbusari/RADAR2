//  PAES_Settings.java 
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
import radar.model.Parser;
import radar.jmetal.core.Algorithm;
import radar.jmetal.core.Operator;
import radar.jmetal.core.Problem;
import radar.jmetal.evolutionary.operators.*;
import radar.jmetal.experiments.Settings;
import radar.jmetal.util.JMException;
import radar.jmetal.util.ProblemFactory;

/**
 * Settings class of algorithm PAES
 */
public class PAES_Settings extends Settings{

  public int maxEvaluations_ ;
  public int archiveSize_    ;
  public int biSections_     ;
  public double mutationProbability_ ;
  public double mutationDistributionIndex_;
  public Parser parserEngine_		 ;
  public String alternativesObjectiveAndDecisionsPath_;
  DecisionVectorType typeOfDecisonVector_;

  /**
   * Constructor
   */
  public PAES_Settings(String problem,DecisionVectorType typeOfDecisonVector) {
    super(problem,typeOfDecisonVector) ;

    Object [] problemParams = {"Real"};
    try {
      problem_ = (new ProblemFactory()).getProblem(problemName_, problemParams);
    } catch (JMException e) {
      e.printStackTrace();
    }      
    // Default experiments.settings
    maxEvaluations_ = 25000 ;
    archiveSize_    = 100   ;
    biSections_     = 5     ;
    mutationProbability_ = 1.0/problem_.getNumberOfVariables() ;
    mutationDistributionIndex_ = 20.0 ;
  } // PAES_Settings
	public PAES_Settings(String problemName, String solutionType,
			Parser goalParserEngine, Problem problem,AlgorithmParameter param, String alternativesObjectiveAndDecisionsPath,DecisionVectorType typeOfDecisonVector) {
		parserEngine_= goalParserEngine;
		problem_ =problem;
	    maxEvaluations_              = param.getMaxEvaluation() ;
	    archiveSize_                = param.getPopulationSize()   ;
	    biSections_     			= 5     ;
	    mutationProbability_         = param.getMutationProbability() ;
	    mutationDistributionIndex_   = 20.0  ;
	    alternativesObjectiveAndDecisionsPath_ = alternativesObjectiveAndDecisionsPath;
	    typeOfDecisonVector_ = typeOfDecisonVector;
	}
	public PAES_Settings(String problemName, String solutionType,
			Parser goalParserEngine, Problem problem,AlgorithmParameter param, DecisionVectorType typeOfDecisonVector) {
		parserEngine_= goalParserEngine;
		problem_ =problem;
	    maxEvaluations_              = param.getMaxEvaluation() ;
	    archiveSize_                =  param.getPopulationSize() ;
	    biSections_     			= 5     ;
	    mutationProbability_         = param.getMutationProbability() ;
	    mutationDistributionIndex_   = 20.0  ;
	    typeOfDecisonVector_= typeOfDecisonVector;
	}

  /**
   * Configure the MOCell algorithm with default parameter experiments.settings
   * @return an algorithm object
   * @throws jmetal.util.JMException
   */
  public Algorithm configure() throws JMException {
    Algorithm algorithm ;
    Mutation  mutation   ;

    HashMap  parameters ; // Operator parameters

    // Creating the problem
    algorithm = new PAES(problem_,parserEngine_) ;

    // Algorithm parameters
    algorithm.setInputParameter("maxEvaluations", maxEvaluations_);
    algorithm.setInputParameter("biSections", biSections_);
    algorithm.setInputParameter("archiveSize",archiveSize_ );

    // Mutation (Real variables)
    parameters = new HashMap() ;
    parameters.put("probability", mutationProbability_) ;
    parameters.put("distributionIndex", mutationDistributionIndex_) ;
    mutation = MutationFactory.getMutationOperator("BitFlipMutation", parameters,typeOfDecisonVector_);                    

    // Add the operators to the algorithm
    algorithm.addOperator("mutation", mutation);

    return algorithm ;
  } // configure

  /**
   * Configure PAES with user-defined parameter experiments.settings
   * @return A PAES algorithm object
   */
  @Override
  public Algorithm configure(Properties configuration) throws JMException {
    Algorithm algorithm ;
    Mutation   mutation  ;

    HashMap  parameters ; // Operator parameters

    // Creating the algorithm.
    algorithm = new PAES(problem_) ;

    // Algorithm parameters
    archiveSize_ = Integer.parseInt(configuration.getProperty("archiveSize",String.valueOf(archiveSize_)));
    maxEvaluations_  = Integer.parseInt(configuration.getProperty("maxEvaluations",String.valueOf(maxEvaluations_)));
    biSections_  = Integer.parseInt(configuration.getProperty("biSections",String.valueOf(biSections_)));
    algorithm.setInputParameter("maxEvaluations", maxEvaluations_);
    algorithm.setInputParameter("biSections", biSections_);
    algorithm.setInputParameter("archiveSize",archiveSize_ );


    mutationProbability_ = Double.parseDouble(configuration.getProperty("mutationProbability",String.valueOf(mutationProbability_)));
    mutationDistributionIndex_ = Double.parseDouble(configuration.getProperty("mutationDistributionIndex",String.valueOf(mutationDistributionIndex_)));
    parameters = new HashMap() ;
    parameters.put("probability", mutationProbability_) ;
    parameters.put("distributionIndex", mutationDistributionIndex_) ;
    mutation = MutationFactory.getMutationOperator("BitFlipMutation", parameters,typeOfDecisonVector_);

    // Add the operators to the algorithm
    algorithm.addOperator("mutation",mutation);

    return algorithm ;
  }
} // PAES_Settings
