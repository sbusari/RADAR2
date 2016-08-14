package radar.model;

import java.util.ArrayList;
import java.util.List;

class SolutionSet {
	/* 
	* Invariant: solutions contain no duplicate solutions and no solution that is a sub-solution of another
	*/
	private List<Solution> solutions;

	SolutionSet(){
		solutions = new ArrayList<Solution>();
	}

	/*
	* Returns the list of solutions (makes it easy to loop over all solutions in the set)
	*/
	List<Solution> list(){
		return solutions;
	}
	public int size ()
	{
		return solutions.size();
	}
	public Solution get (int i)
	{
		return solutions.get(i);
	}
	/*
	 * Needed when combining solutions in binary expression
	 */
	public void setSolutions(List<Solution> solns){
		solutions = solns;
	}
	/*
	* Returns true if 'this' contains a solution that subsumes s, i.e. a solution s0 such that s is a sub-solution of s0
	*/
	boolean contains(Solution s){
		for(Solution s0: solutions){
			if (s.subSolution(s0)) return true;
		}
		return false;
	}

	/*
	*  Adds solution s to the solution set. 
	*  If the solution set already contains a solution that is equal to or subsumes s, the solution set is unchanged.
	*  If the solution set contains a sub-solution of s, the sub-solution is removed from the solution set and replaced by s
	*/
	void addSolution(Solution s){
		if ( !this.contains(s)) {
			solutions.add(s);
			// remove sub-solution if needed
			for (Solution s0: solutions){
				if (s0.subSolution(s)){
					solutions.remove(s0);
					return; // early exit as soon as we found and removed a sub-solution
				}
			}
		}
	}

	/*
	*  Adds all solutions in solution set `slns` to this solution set
	*/	
	void addAll(SolutionSet slns){
		for (Solution s: slns.list()){
			addSolution(s);
		}
	}
	
	/*
	 * Adds a new Solution with no selection, and do not check for subsolution.
	 */
	void addNewSolution(Solution newSolution){
		solutions.add(newSolution);
	}
	

}
