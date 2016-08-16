package radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

class SolutionSet {

	private Set<Solution> solutions;

	SolutionSet(){
		solutions = new HashSet<Solution>();
	}

	/*
	* Returns the set of solutions
	*/
	Set<Solution> set(){
		return solutions;
	}

	public List<Solution> list(){
		return new ArrayList<Solution>(solutions);
	}
	
	boolean isEmpty(){
		return solutions.isEmpty();
	}

	public int size ()
	{
		return solutions.size();
	}

	public Solution get (int i)
	{
		return new ArrayList<Solution>(solutions).get(i);
	}

	void add(Solution s){
		
		solutions.add(s);
	}

	void addAll(SolutionSet slns){
		solutions.addAll(slns.set());
	}
	
	/*
	*  Returns a solution set that merges the solution set with another solution set.
	* The returned solution set includes the union of all non-conflicting solution pairs.
	*/
	SolutionSet merge(SolutionSet slns){

		if (this.isEmpty()){return slns;}
		if (slns.isEmpty()){return this;}

		SolutionSet result = new SolutionSet();
		for (Solution s: this.set()){
			for (Solution s1: slns.set()){
				if (!conflicting(s, s1)){
					Solution newSol = s.union(s1);
					result.add(newSol);
				}
			}
		}
		return result;
	}


	/*
	* Returns true if s1 and s2 have different option selection for a same decision
	*/ 
	private static boolean conflicting(Solution s1, Solution s2){
		for (Decision d: s1.decisions()){
			if (s2.selection(d) != null && !s2.selection(d).equals(s1.selection(d))) return true;
		}
		return false;
	}

}
