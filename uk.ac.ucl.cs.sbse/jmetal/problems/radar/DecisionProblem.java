package jmetal.problems.radar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import radar.model.ExperimentData;
import radar.model.Model;
import radar.model.SbseAlgorithm;
import radar.model.SbseData;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.ArrayBitVectorSolutionType;
import jmetal.util.JMException;

public class DecisionProblem extends Problem {
	public DecisionProblem (Model m, List<Integer[]> decisionVectorBlock) throws FileNotFoundException, IOException{
		numberOfObjectives_  = m.getObjectives().size();
		semanticModel_= m;
		problemName_= m.getModelName();
		upperLimit_ = new double[decisionVectorBlock.size()] ;
		lowerLimit_ = new double[decisionVectorBlock.size()] ;
		for (int i = 0; i < decisionVectorBlock.size(); i++) {
	      lowerLimit_[i] = 0;
	      upperLimit_[i] = 1 ;
		} // for
		solutionType_ = new ArrayBitVectorSolutionType(this);
	 }
	 public void evaluate(Solution solution) throws JMException {
		 solution = new SbseAlgorithm ().evaluate(solution,semanticModel_);
	 } // evaluate
	 
	 public Solution checkViolations (Solution solution) throws JMException{
		 return solution;
	 }//constraint checking

	 

	 
}
