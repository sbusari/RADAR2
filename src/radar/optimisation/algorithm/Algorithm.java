package radar.optimisation.algorithm;

import java.util.List;

import radar.model.Alternative;
import radar.model.SolutionValues;

public abstract class Algorithm {
	boolean solveAll_;
	public abstract  List<SolutionValues> solve ();
	public abstract  SolutionValues solve (Alternative s);
	public void setSolveAll (boolean solveAll){
		solveAll_ = solveAll;
	}
	public boolean getSolveAll (){
		return solveAll_ ;
	}
}
