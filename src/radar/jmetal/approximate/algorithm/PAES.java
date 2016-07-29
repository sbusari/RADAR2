//  PAES.java
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
import radar.jmetal.core.*;
import radar.jmetal.util.JMException;
import radar.jmetal.util.AdaptiveGridArchive;
import radar.jmetal.util.comparators.DominanceComparator;
import radar.model.Parser;
import radar.utilities.SolutionDetail;

import java.util.Comparator;

/**
 * This class implements the PAES algorithm. 
 */
public class PAES extends Algorithm {        
	Parser parserEngine_;
 /** 
  * Create a new PAES instance for resolve a problem
  * @param problem Problem to solve
  */                 
  public PAES(Problem problem) {                
    super (problem) ;
  } // Paes
  
  public PAES(Problem problem, Parser parserEngine) {
     super (problem) ;
     parserEngine_= parserEngine;
  } //  PAes
    
  /**
   * Tests two solutions to determine which one becomes be the guide of PAES
   * algorithm
   * @param solution The actual guide of PAES
   * @param mutatedSolution A candidate guide
 * @throws ClassNotFoundException 
   */
  public Solution test(Solution solution, 
                       Solution mutatedSolution, 
                       AdaptiveGridArchive archive) throws ClassNotFoundException{  
    
    int originalLocation = archive.getGrid().location(solution);
    int mutatedLocation  = archive.getGrid().location(mutatedSolution); 

    if (originalLocation == -1) {
    	return (Solution)Solution.deepClone(mutatedSolution);
      //return new Solution(mutatedSolution);
    }
    
    if (mutatedLocation == -1) {
    	return (Solution)Solution.deepClone(solution);
      //return new Solution(solution);
    }
        
    if (archive.getGrid().getLocationDensity(mutatedLocation) < 
        archive.getGrid().getLocationDensity(originalLocation)) {
    	return (Solution)Solution.deepClone(mutatedSolution);
      //return new Solution(mutatedSolution);
    }
    return (Solution)Solution.deepClone(solution);
    //return new Solution(solution);          
  } // test
    
  /**   
  * Runs of the Paes algorithm.
  * @return a <code>SolutionSet</code> that is a set of non dominated solutions
  * as a result of the algorithm execution  
   * @throws JMException 
  */    
  public SolutionDetail execute() throws JMException, ClassNotFoundException {     
    int bisections, archiveSize, maxEvaluations, evaluations;
    AdaptiveGridArchive archive;
    Operator mutationOperator;
    Comparator dominance;
    SolutionDetail solutionDetail = new SolutionDetail() ;
    //Read the params
    bisections     = ((Integer)this.getInputParameter("biSections")).intValue();
    archiveSize    = ((Integer)this.getInputParameter("archiveSize")).intValue();
    maxEvaluations = ((Integer)this.getInputParameter("maxEvaluations")).intValue();
    SolutionSet dominatedSolution = new SolutionSet(2*maxEvaluations);
    //Read the operators        
    mutationOperator = this.operators_.get("mutation");        

    //Initialize the variables                
    evaluations = 0;
    archive     = new AdaptiveGridArchive(archiveSize,bisections,problem_.getNumberOfObjectives());        
    dominance = new DominanceComparator();           
            
    //-> Create the initial solution and evaluate it and his constraints
    Solution solution = new Solution(problem_);
    problem_.evaluate(solution);        
    problem_.evaluateConstraints(solution);
    evaluations++;
    dominatedSolution.add(solution);
    // Add it to the archive
    
    //archive.add(new Solution(solution));  
    archive.add((Solution)Solution.deepClone(solution));  
   
    //Iterations....
    do {
      // Create the mutate one
      //Solution mutatedIndividual = new Solution(solution);  
      Solution mutatedIndividual = (Solution)Solution.deepClone(solution);
      mutationOperator.execute(mutatedIndividual);
            
      problem_.evaluate(mutatedIndividual);                     
      problem_.evaluateConstraints(mutatedIndividual);
      evaluations++;
      dominatedSolution.add(mutatedIndividual);
      //<-
            
      // Check dominance
      int flag = dominance.compare(solution,mutatedIndividual);            
            
      if (flag == 1) { //If mutate solution dominate                  
        //solution = new Solution(mutatedIndividual);   
        solution = (Solution)Solution.deepClone(mutatedIndividual);
        archive.add(mutatedIndividual);                
      } else if (flag == 0) { //If none dominate the other                               
        if (archive.add(mutatedIndividual)) {                    
          solution = test(solution,mutatedIndividual,archive);
        }                                
      }      
      /*
      if ((evaluations % 100) == 0) {
        archive.printObjectivesToFile("FUN"+evaluations) ;
        archive.printVariablesToFile("VAR"+evaluations) ;
        archive.printObjectivesOfValidSolutionsToFile("FUNV"+evaluations) ;
      }
      */
    } while (evaluations < maxEvaluations);                                    
        
    //Return the  population of non-dominated solution
    //archive.printFeasibleFUN("FUN_PAES") ;
    archive.setParserEngne(parserEngine_);
    solutionDetail.setNonDominatedSolutions((SolutionSet)archive);
    solutionDetail.setDominatedSolutions(dominatedSolution);
    solutionDetail.setGoalModelParserEngine(parserEngine_);
    return solutionDetail;
    //return archive;                
  }  // execute  
} // PAES
