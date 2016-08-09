package radar.model;

import java.util.List;

abstract class Algorithm {
	boolean solveAll_;
	long runTime_;
	SolutionQuality solutionQuality_;
	public long getRunTime (){
		return runTime_;
	}
	public SolutionQuality getSolutionQuality (){
		return solutionQuality_;
	}
	public abstract List<SolutionValues> getAllSolutionValues ();
}
