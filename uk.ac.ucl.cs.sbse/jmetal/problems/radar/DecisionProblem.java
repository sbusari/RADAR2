package jmetal.problems.radar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import radar.model.ExperimentData;
import radar.model.SbseAlgorithm;
import radar.model.SbseData;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.ArrayBitVectorSolutionType;
import jmetal.util.JMException;

public class DecisionProblem extends Problem {
	public DecisionProblem (SbseData sbse_data) throws FileNotFoundException, IOException{
		numberOfObjectives_  = sbse_data.getNbrObjectives();
		sbseData = sbse_data;
		problemName_= sbse_data.getExperimentData().getProblemName();
		upperLimit_ = new double[sbseData.getDecisionVectorBlock().size()] ;
		lowerLimit_ = new double[sbseData.getDecisionVectorBlock().size()] ;
		for (int i = 0; i < sbseData.getDecisionVectorBlock().size(); i++) {
	      lowerLimit_[i] = 0;
	      upperLimit_[i] = 1 ;
		} // for
		solutionType_ = new ArrayBitVectorSolutionType(this);
	 }
	 public void evaluate(Solution solution) throws JMException {
		 solution = new SbseAlgorithm ().evaluate(solution);
	 } // evaluate
	 
	 public Solution checkViolations (Solution solution) throws JMException{
		 return solution;
	 }//constraint checking

	 

	 
}
