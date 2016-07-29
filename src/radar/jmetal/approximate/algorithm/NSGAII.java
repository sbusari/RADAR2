//  NSGAII.java
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

import radar.enumeration.DecisionVectorType;
import radar.jmetal.core.*;
import radar.jmetal.qualityIndicator.QualityIndicator;
import radar.jmetal.util.Distance;
import radar.jmetal.util.JMException;
import radar.jmetal.util.Ranking;
import radar.jmetal.util.comparators.CrowdingComparator;
import radar.model.Parser;
import radar.utilities.SolutionDetail;

/** 
 *  Implementation of NSGA-II.
 *  This implementation of NSGA-II makes use of a QualityIndicator object
 *  to obtained the convergence speed of the algorithm. This version is used
 *  in the paper:
 *     A.J. Nebro, J.J. Durillo, C.A. Coello Coello, F. Luna, E. Alba 
 *     "A Study of Convergence Speed in Multi-Objective Metaheuristics." 
 *     To be presented in: PPSN'08. Dortmund. September 2008.
 */

public class NSGAII extends Algorithm {
	Parser parserEngine_;
	String alternativesObjectiveAndDecisionsPath_;
  /**
   * Constructor
   * @param problem Problem to solve
   */
  public NSGAII(Problem problem) {
    super (problem) ;
  } // NSGAII
  public NSGAII(Problem problem, Parser parserEngine, String alternativesObjectiveAndDecisionsPath) {
	    super (problem) ;
	    alternativesObjectiveAndDecisionsPath_ = alternativesObjectiveAndDecisionsPath;
	    parserEngine_= parserEngine;
	  } // NSGAII
  /**
   * Constructor.
   * Create a new NSGAII instance
   * @param problem Problem to solve
   */
   public NSGAII(Problem problem, Parser parserEngine) {
     super (problem) ;
     parserEngine_= parserEngine;
   } //  NSGAII

  /**   
   * Runs the NSGA-II algorithm.
   * @return a <code>SolutionSet</code> that is a set of non dominated solutions
   * as a result of the algorithm execution
   * @throws JMException 
   */
  public SolutionDetail execute() throws JMException, ClassNotFoundException {
    int populationSize;
    int maxEvaluations;
    int evaluations;

    QualityIndicator indicators; // QualityIndicator object
    int requiredEvaluations; // Use in the example of use of the
    // indicators object (see below)

    SolutionSet population;
    SolutionSet offspringPopulation;
    SolutionSet union;

    Operator mutationOperator;
    Operator crossoverOperator;
    Operator selectionOperator;

    Distance distance = new Distance();
    
    SolutionDetail solutionDetail = new SolutionDetail() ;

    //Read the parameters
    populationSize = ((Integer) getInputParameter("populationSize")).intValue();
    maxEvaluations = ((Integer) getInputParameter("maxEvaluations")).intValue();
    indicators = (QualityIndicator) getInputParameter("indicators");
    
    SolutionSet dominatedSolution = new SolutionSet(2*maxEvaluations);

    //Initialize the variables
    population = new SolutionSet(populationSize);
    evaluations = 0;

    requiredEvaluations = 0;

    //Read the operators
    mutationOperator = operators_.get("mutation");
    crossoverOperator = operators_.get("crossover");
    selectionOperator = operators_.get("selection");

    // Create the initial solutionSet
    Solution newSolution;
    for (int i = 0; i < populationSize; i++) {
      newSolution = new Solution(problem_);
      problem_.evaluate(newSolution);
      problem_.evaluateConstraints(newSolution);
      evaluations++;
      population.add(newSolution);
   // commentd here for empirical study, work on it later like writing to file.
      dominatedSolution.add(newSolution);
    } //for    
    
    
   
    
    // write the generated initial population.
   /* if (parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType() == DecisionVectorType.Decision){
     	 new Helper().printAlternativesObjectivesAndDecisions(population,alternativesObjectiveAndDecisionsPath_,"NSGAII");
     }else if (parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType() == DecisionVectorType.DecisionSubset){
     	new Helper().printAlternativesObjectivesAndDecisionSubsets(population,alternativesObjectiveAndDecisionsPath_,"NSGAII");
     }else{
     	
     }*/
    
    
    // Generations 
    while (evaluations < maxEvaluations) {

      // Create the offSpring solutionSet      
      offspringPopulation = new SolutionSet(populationSize);
      Solution[] parents = new Solution[2];
      for (int i = 0; i < (populationSize / 2); i++) {
        if (evaluations < maxEvaluations) {
    	 // print population solution
        /*System.out.println ("Population before selection");
        for (int m =0; m < population.size(); m++){
        	population.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
        }*/
        
        	
          //obtain parents
        	//System.out.println ("=====Enter evaluation======");
 
          
          parents[0] = (Solution) selectionOperator.execute(population);
          parents[1] = (Solution) selectionOperator.execute(population);
          
          /*System.out.println ("Population after selection");
          for (int m =0; m < population.size(); m++){
          	population.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
          }
          
          System.out.println ("===========Selected Parents=============");
          
          parents[0].solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 0);
          parents[1].solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 1);
  */        
          Solution[] offSpring = (Solution[]) crossoverOperator.execute(parents);
          
          /*System.out.println ("Population after crossver");
          for (int m =0; m < population.size(); m++){
          	population.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
          }
          System.out.println ("===============Offspring After  Crossover===========");
          
          offSpring[0].solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 0);
          offSpring[1].solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 1);
*/          
          /*System.out.println ("Population before mutation");
          for (int m =0; m < population.size(); m++){
          	population.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
          }*/
          
          mutationOperator.execute(offSpring[0]);
          mutationOperator.execute(offSpring[1]);

          /*System.out.println ("Population after mutation");
          for (int m =0; m < population.size(); m++){
          	population.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
          }

          
          System.out.println ("==============After Mutation Before fitness update===============");
          offSpring[0].solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 0);
          offSpring[1].solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 1);
*/          
          problem_.evaluate(offSpring[0]);
          problem_.evaluateConstraints(offSpring[0]);
          problem_.evaluate(offSpring[1]);
          problem_.evaluateConstraints(offSpring[1]);
          
          /*System.out.println ("Population after fitness update");
          for (int m =0; m < population.size(); m++){
          	population.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
          }
          
          System.out.println ("================After Mutation After fitness update==============");
          offSpring[0].solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 0);
          offSpring[1].solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 1);
*/
          offspringPopulation.add(offSpring[0]);
          offspringPopulation.add(offSpring[1]);
          
          // commentd here for empirical study, work on it later like writing to file.
          dominatedSolution.add(offSpring[0]);
          dominatedSolution.add(offSpring[1]);
          
          evaluations += 2;
          System.out.println ("============================");
          System.out.println ("evaluation for NSGAII is  "+ evaluations);
        } // if                            
      } // for

      /*System.out.println ("Begin Offspring");
      for (int m =0; m < offspringPopulation.size(); m++){
    	  offspringPopulation.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
      }
      System.out.println ("Begin Offspring");
      
      
      if (parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType() == DecisionVectorType.Decision){
       	 new Helper().printAlternativesObjectivesAndDecisions(offspringPopulation,alternativesObjectiveAndDecisionsPath_,"NSGAII");
       }else if (parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType() == DecisionVectorType.DecisionSubset){
       	new Helper().printAlternativesObjectivesAndDecisionSubsets(offspringPopulation,alternativesObjectiveAndDecisionsPath_,"NSGAII");
       }else{
       	
       }*/
       
      
      // Create the solutionSet of the generated offspring 
      union = ((SolutionSet) population).union(offspringPopulation);
      
      /*System.out.println ("Begin Union");
      for (int m =0; m < union.size(); m++){
    	  union.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
      }
      System.out.println ("Begin Union");
      */
      
      
      // Ranking the union
      Ranking ranking = new Ranking(union);

     
      int remain = populationSize;
      int index = 0;
      SolutionSet front = null;
      population.clear();

      // Obtain the next front
      front = ranking.getSubfront(index);

      while ((remain > 0) && (remain >= front.size())) {
        //Assign crowding distance to individuals
        distance.crowdingDistanceAssignment(front, problem_.getNumberOfObjectives());
        //Add the individuals of this front
        for (int k = 0; k < front.size(); k++) {
          population.add(front.get(k));
        } // for

        //Decrement remain
        remain = remain - front.size();

        //Obtain the next front
        index++;
        if (remain > 0) {
          front = ranking.getSubfront(index);
        } // if        
      } // while

      // Remain is less than front(index).size, insert only the best one
      if (remain > 0) {  // front contains individuals to insert                        
        distance.crowdingDistanceAssignment(front, problem_.getNumberOfObjectives());
        front.sort(new CrowdingComparator());
        for (int k = 0; k < remain; k++) {
          population.add(front.get(k));
        } // for

        remain = 0;
      } // if                               

      // This piece of code shows how to use the indicator object into the code
      // of NSGA-II. In particular, it finds the number of evaluations required
      // by the algorithm to obtain a Pareto front with a hypervolume higher
      // than the hypervolume of the true Pareto front.
      if ((indicators != null) &&
          (requiredEvaluations == 0)) {
        double HV = indicators.getHypervolume(population);
        if (HV >= (0.98 * indicators.getTrueParetoFrontHypervolume())) {
          requiredEvaluations = evaluations;
        } // if
      } // if
      
      
      /*System.out.println ("Begin Latest Population");
      for (int m =0; m < population.size(); m++){
    	  population.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), m);
      }
      System.out.println ("Begin Latest Population");*/
      
      
      //System.out.println ("===================================END=======================");
      
      
      
      
    } // while

    // Return as output parameter the required evaluations
    setOutputParameter("evaluations", requiredEvaluations);
    population.setParserEngne(parserEngine_);
    
   
   
    // Return the first non-dominated front
    Ranking ranking = new Ranking(population);
    ranking.getSubfront(0).printFeasibleFUN("FUN_NSGAII") ;
    SolutionSet subFront = ranking.getSubfront(0);
    subFront.setParserEngne(parserEngine_);
    /*System.out.println ("===========Printed Optimal Solution===============");
    for (int m =0; m < subFront.size(); m++){
    	subFront.get(m).solutionToString(parserEngine_.getDecisionModel().getDecisionVectorBlock().getDecisionVectorType(), 0);
    }*/
    solutionDetail.setNonDominatedSolutions(subFront);
    solutionDetail.setDominatedSolutions(dominatedSolution);
    solutionDetail.setGoalModelParserEngine(parserEngine_);
    return solutionDetail;
  } // execute
} // NSGA-II
