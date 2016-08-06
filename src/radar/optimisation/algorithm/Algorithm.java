package radar.optimisation.algorithm;

import java.util.List;

import radar.model.Solution;
import radar.model.SolutionValues;

public abstract class Algorithm {
	boolean solveAll_;
	public abstract  List<SolutionValues> solve ();
	public abstract  SolutionValues solve (Solution s);
}
