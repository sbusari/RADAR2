package radar.model;

import java.util.List;

abstract class Algorithm {
	boolean solveAll_;
	long runTime_;
	SolutionQuality solutionQuality_;
	public abstract  List<SolutionValues> solve ();
	public DecisionVector getDecisionVector ( Model model){
		DecisionVector result =new XOR_DecisionVector(model);
		return result;
	}
	public long getRunTime (){
		return runTime_;
	}
	public SolutionQuality getSolutionQuality (){
		return solutionQuality_;
	}
	public abstract List<SolutionValues> getAllSolutionValues ();
}
