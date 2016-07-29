//  IBEA_Settings.java 
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
import radar.jmetal.exact.algorithm.exhaustiveSearch;
import radar.jmetal.experiments.Settings;
import radar.jmetal.util.JMException;
import radar.jmetal.util.ProblemFactory;
import radar.jmetal.util.comparators.FitnessComparator;
import radar.model.Parser;

/**
 * Settings class of algorithm IBEA
 */
public class IBEA_Settings extends Settings {

	public int populationSize_   ;
	public int maxEvaluations_   ;
	public int archiveSize_      ;
	public Parser parserEngine_;

	public double mutationProbability_   ;
	public double crossoverProbability_  ;

	public double crossoverDistributionIndex_ ;
	public double mutationDistributionIndex_  ;
	public String alternativesObjectiveAndDecisionsPath_;
	DecisionVectorType typeOfDecisonVector_;

	/**
	 * Constructor
	 */
	public IBEA_Settings(String problemName, DecisionVectorType typeOfDecisonVector) {
		super(problemName,typeOfDecisonVector) ;
		typeOfDecisonVector_ = typeOfDecisonVector;
		Object [] problemParams = {"Real"};
    try {
	    problem_ = (new ProblemFactory()).getProblem(problemName_, problemParams);
    } catch (JMException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    }      
  	// Default experiments.settings
		populationSize_ = 100   ;
		maxEvaluations_ = 25000 ;
		archiveSize_    = 100 ;
		mutationProbability_  = 1.0/problem_.getNumberOfVariables() ;
		crossoverProbability_ = 0.9 ;
		crossoverDistributionIndex_ = 20.0  ;
		mutationDistributionIndex_  = 20.0  ;
	} // IBEA_Settings

	public IBEA_Settings(String problemName, String solutionType,
			Parser goalParserEngine, Problem problem, AlgorithmParameter param, String  alternativesObjectiveAndDecisionsPath, DecisionVectorType typeOfDecisonVector) {
		parserEngine_= goalParserEngine;
		problem_ = problem;	
		typeOfDecisonVector_ = typeOfDecisonVector;
		populationSize_              = param.getPopulationSize()   ;
		archiveSize_				 = populationSize_;
	    maxEvaluations_              = param.getMaxEvaluation() ;
	    mutationProbability_         = param.getMutationProbability() ;
	    crossoverProbability_        = param.getCrossoverProbability() ;
	    mutationDistributionIndex_   = 20.0  ;
	    crossoverDistributionIndex_  = 20.0  ;
	    alternativesObjectiveAndDecisionsPath_ = alternativesObjectiveAndDecisionsPath;
	}

	public IBEA_Settings(String problemName, String solutionType,
			Parser goalParserEngine, Problem problem, AlgorithmParameter param,DecisionVectorType typeOfDecisonVector) {
		parserEngine_= goalParserEngine;
		problem_ = problem;	
		typeOfDecisonVector_ = typeOfDecisonVector;
		populationSize_              = param.getPopulationSize()   ;
		archiveSize_				 = populationSize_;
	    maxEvaluations_              = param.getMaxEvaluation() ;
	    mutationProbability_         = param.getMutationProbability() ;
	    crossoverProbability_        = param.getCrossoverProbability() ;
	    mutationDistributionIndex_   = 20.0  ;
	    crossoverDistributionIndex_  = 20.0  ;
	}

	/**
	 * Configure IBEA with user-defined parameter experiments.settings
	 * @return A IBEA algorithm object
	 * @throws radar.jmetal.util.JMException
	 */
	public Algorithm configure() throws JMException {
		Algorithm algorithm ;
		Operator  selection ;
		Operator  crossover ;
		Operator  mutation  ;

        HashMap  parameters ; // Operator parameters
		algorithm = new IBEA(problem_,parserEngine_,alternativesObjectiveAndDecisionsPath_  ) ;

		// Algorithm parameters
		algorithm.setInputParameter("populationSize", populationSize_);
		algorithm.setInputParameter("maxEvaluations", maxEvaluations_);
		algorithm.setInputParameter("archiveSize", archiveSize_);
		
	    // Mutation and Crossover for Real codification 
	    parameters = new HashMap() ;
	    parameters.put("probability", crossoverProbability_) ;
	    parameters.put("distributionIndex", crossoverDistributionIndex_) ;
	    crossover = CrossoverFactory.getCrossoverOperator("SinglePointCrossover", parameters,typeOfDecisonVector_);                   
	
	    parameters = new HashMap() ;
	    parameters.put("probability", mutationProbability_) ;
	    parameters.put("distributionIndex", mutationDistributionIndex_) ;
	    mutation = MutationFactory.getMutationOperator("BitFlipMutation", parameters,typeOfDecisonVector_);                        

	    /* Selection Operator */
	    parameters = new HashMap() ; 
	    parameters.put("comparator", new FitnessComparator()) ;
	    //selection = new BinaryTournament2(parameters);
	    selection = SelectionFactory.getSelectionOperator("BinaryTournament2",parameters);
		
	    
	    // Add the operators to the algorithm
		algorithm.addOperator("crossover",crossover);
		algorithm.addOperator("mutation",mutation);
		algorithm.addOperator("selection",selection);

		return algorithm ;
	} // configure

  /**
   * Configure IBEA with user-defined parameter experiments.settings
   * @return An IBEA algorithm object
   */
  @Override
  public Algorithm configure(Properties configuration) throws JMException {
    Algorithm algorithm ;
    Selection selection ;
    Crossover crossover ;
    Mutation mutation  ;

    HashMap  parameters ; // Operator parameters

    // Creating the algorithm.
    algorithm = new IBEA(problem_) ;

    // Algorithm parameters
    populationSize_ = Integer.parseInt(configuration.getProperty("populationSize",String.valueOf(populationSize_)));
    maxEvaluations_  = Integer.parseInt(configuration.getProperty("maxEvaluations",String.valueOf(maxEvaluations_)));
    archiveSize_  = Integer.parseInt(configuration.getProperty("archiveSize",String.valueOf(archiveSize_)));
    algorithm.setInputParameter("populationSize",populationSize_);
    algorithm.setInputParameter("maxEvaluations",maxEvaluations_);
    algorithm.setInputParameter("archiveSize",archiveSize_);

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


    /* Selection Operator */
    parameters = new HashMap() ;
    parameters.put("comparator", new FitnessComparator()) ;
    selection = new BinaryTournament2(parameters);

    // Add the operators to the algorithm
    algorithm.addOperator("crossover",crossover);
    algorithm.addOperator("mutation",mutation);
    algorithm.addOperator("selection",selection);

    return algorithm ;
  }
} // IBEA_Settings
