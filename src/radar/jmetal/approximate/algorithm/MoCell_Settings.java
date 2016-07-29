//  MOCell_Settings.java 
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
import radar.jmetal.core.Operator;
import radar.jmetal.core.Problem;
import radar.jmetal.evolutionary.operators.*;
import radar.jmetal.experiments.Settings;
import radar.jmetal.util.JMException;
import radar.jmetal.util.ProblemFactory;
import radar.model.Parser;

/**
 * Settings class of algorithm MOCell
 */
public class MoCell_Settings extends Settings{

  public int populationSize_                ;
  public int maxEvaluations_                ;
  public int archiveSize_                   ;
  public int feedback_                      ;
  public double mutationProbability_        ;
  public double crossoverProbability_       ;
  public double crossoverDistributionIndex_ ;
  public double mutationDistributionIndex_  ;
  public Parser parserEngine_		 ;
  public String alternativesObjectiveAndDecisionsPath_;
  DecisionVectorType typeOfDecisonVector_;

  /**
   * Constructor
   */
  public MoCell_Settings(String problem,DecisionVectorType typeOfDecisonVector) {
	    super(problem,typeOfDecisonVector) ;
	    typeOfDecisonVector_ =typeOfDecisonVector;
	    Object [] problemParams = {"Real"};
	    try {
		    problem_ = (new ProblemFactory()).getProblem(problemName_, problemParams);
	    } catch (JMException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
	    }
	    // Default experiments.settings
	    populationSize_              = 100   ;
	    maxEvaluations_              = 25000 ;
	    archiveSize_                = 100   ;
	    feedback_                   = 20    ;
	    mutationProbability_         = 1.0/problem_.getNumberOfVariables() ;
	    crossoverProbability_        = 0.9   ;
	    mutationDistributionIndex_   = 20.0  ;
	    crossoverDistributionIndex_  = 20.0  ;
	  } // MOCell_Settings
	public MoCell_Settings(String problemName, String solutionType,
			Parser goalParserEngine, Problem problem,AlgorithmParameter param, String alternativesObjectiveAndDecisionsPath,DecisionVectorType typeOfDecisonVector) {
		parserEngine_= goalParserEngine;
		problem_ =problem;
		
	    populationSize_              = param.getPopulationSize() < 100 ? 100: param.getPopulationSize() ;
	    maxEvaluations_              = param.getPopulationSize() < 100?  100*(param.getMaxEvaluation()/param.getPopulationSize()): param.getMaxEvaluation();
	    archiveSize_                = param.getPopulationSize()   ;
	    feedback_                   = 20    ;
	    mutationProbability_         = param.getMutationProbability() ;
	    crossoverProbability_        = param.getCrossoverProbability() ;
	    mutationDistributionIndex_   = 20.0  ;
	    crossoverDistributionIndex_  = 20.0  ;
	    alternativesObjectiveAndDecisionsPath_ = alternativesObjectiveAndDecisionsPath;
	    typeOfDecisonVector_ = typeOfDecisonVector;
	}
	public MoCell_Settings(String problemName, String solutionType,
			Parser goalParserEngine, Problem problem,AlgorithmParameter param, DecisionVectorType typeOfDecisonVector) {
		parserEngine_= goalParserEngine;
		problem_ =problem;
		
	    populationSize_              =  param.getPopulationSize() < 100 ? 100: param.getPopulationSize() ;
	    maxEvaluations_              = param.getPopulationSize() < 100?  100*(param.getMaxEvaluation()/param.getPopulationSize()): param.getMaxEvaluation();
	    archiveSize_                = param.getPopulationSize()  ;
	    feedback_                   = 20    ;
	    mutationProbability_         = param.getMutationProbability() ;
	    crossoverProbability_        = param.getCrossoverProbability() ;
	    mutationDistributionIndex_   = 20.0  ;
	    crossoverDistributionIndex_  = 20.0  ;
	    typeOfDecisonVector_= typeOfDecisonVector;
	}

  /**
   * Configure the MOCell algorithm with default parameter experiments.settings
   * @return an algorithm object
   * @throws jmetal.util.JMException
   */
  public Algorithm configure() throws JMException {
    Algorithm algorithm ;

    Crossover crossover ;
    Mutation  mutation  ;
    Operator  selection ;

    HashMap  parameters ; // Operator parameters

    // Selecting the algorithm: there are six MOCell variants
    //algorithm = new sMOCell1(problem_) ;
    //algorithm = new sMOCell2(problem_) ;
    //algorithm = new aMOCell1(problem_) ;
    //algorithm = new aMOCell2(problem_) ;
    //algorithm = new aMOCell3(problem_) ;
   // algorithm = new MOCell(problem_) ;
    algorithm = new MoCell(problem_,parserEngine_) ;

    // Algorithm parameters
    algorithm.setInputParameter("populationSize", populationSize_);
    algorithm.setInputParameter("maxEvaluations", maxEvaluations_);
    algorithm.setInputParameter("archiveSize",archiveSize_ );
    algorithm.setInputParameter("feedBack", feedback_);


    // Mutation and Crossover for Real codification 
    parameters = new HashMap() ;
    parameters.put("probability", crossoverProbability_) ;
    parameters.put("distributionIndex", crossoverDistributionIndex_) ;
    crossover = CrossoverFactory.getCrossoverOperator("SinglePointCrossover", parameters,typeOfDecisonVector_);                   

    parameters = new HashMap() ;
    parameters.put("probability", mutationProbability_) ;
    parameters.put("distributionIndex", mutationDistributionIndex_) ;
    mutation = MutationFactory.getMutationOperator("BitFlipMutation", parameters,typeOfDecisonVector_);                         

    // Selection Operator 
    parameters = null ;
    selection = SelectionFactory.getSelectionOperator("BinaryTournament", parameters) ;   

    // Add the operators to the algorithm
    algorithm.addOperator("crossover", crossover);
    algorithm.addOperator("mutation", mutation);
    algorithm.addOperator("selection", selection);

    return algorithm ;
  } // configure

  /**
   * Configure MOCell with user-defined parameter experiments.settings
   * @return A MOCell algorithm object
   */
  @Override
  public Algorithm configure(Properties configuration) throws JMException {
    Algorithm algorithm ;
    Selection selection ;
    Crossover  crossover ;
    Mutation   mutation  ;

    HashMap  parameters ; // Operator parameters

    // Selecting the algorithm: there are six MOCell variants
    //algorithm = new sMOCell1(problem_) ;
    //algorithm = new sMOCell2(problem_) ;
    //algorithm = new aMOCell1(problem_) ;
    //algorithm = new aMOCell2(problem_) ;
    //algorithm = new aMOCell3(problem_) ;
    algorithm = new MoCell(problem_) ;

    // Algorithm parameters
    populationSize_ = Integer.parseInt(configuration.getProperty("populationSize",String.valueOf(populationSize_)));
    maxEvaluations_  = Integer.parseInt(configuration.getProperty("maxEvaluations",String.valueOf(maxEvaluations_)));
    archiveSize_  = Integer.parseInt(configuration.getProperty("archiveSize",String.valueOf(archiveSize_)));
    feedback_  = Integer.parseInt(configuration.getProperty("feedback",String.valueOf(feedback_)));
    algorithm.setInputParameter("populationSize",populationSize_);
    algorithm.setInputParameter("maxEvaluations",maxEvaluations_);
    algorithm.setInputParameter("archiveSize",archiveSize_);
    algorithm.setInputParameter("feedback",feedback_);

    // Mutation and Crossover for Real codification
    crossoverProbability_ = Double.parseDouble(configuration.getProperty("crossoverProbability",String.valueOf(crossoverProbability_)));
    crossoverDistributionIndex_ = Double.parseDouble(configuration.getProperty("crossoverDistributionIndex",String.valueOf(crossoverDistributionIndex_)));
    parameters = new HashMap() ;
    parameters.put("probability", crossoverProbability_) ;
    parameters.put("distributionIndex", crossoverDistributionIndex_) ;
    crossover = CrossoverFactory.getCrossoverOperator("SinglePointCrossover", parameters,typeOfDecisonVector_);

    mutationProbability_ = Double.parseDouble(configuration.getProperty("mutationProbability",String.valueOf(mutationProbability_)));
    mutationDistributionIndex_ = Double.parseDouble(configuration.getProperty("mutationDistributionIndex",String.valueOf(mutationDistributionIndex_)));
    parameters = new HashMap() ;
    parameters.put("probability", mutationProbability_) ;
    parameters.put("distributionIndex", mutationDistributionIndex_) ;
    mutation = MutationFactory.getMutationOperator("BitFlipMutation", parameters,typeOfDecisonVector_);

    // Selection Operator
    parameters = null ;
    selection = SelectionFactory.getSelectionOperator("BinaryTournament2", parameters) ;

    // Add the operators to the algorithm
    algorithm.addOperator("crossover",crossover);
    algorithm.addOperator("mutation",mutation);
    algorithm.addOperator("selection",selection);

    return algorithm ;
  }
} // MOCell_Settings
