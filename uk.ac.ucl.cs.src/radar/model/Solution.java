package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
class Solution {

	private Map<Decision, String> selection;
	private Model sematicModel;
	// needed to maintain unique solution ID
	private String uniqueID_;
	public Solution(){
		selection = new LinkedHashMap<Decision, String>();
	}
	public Solution (Solution a){
		sematicModel = a.sematicModel;
		selection = new LinkedHashMap<Decision, String>();
		if (a.selection != null){
			selection.putAll(a.selection);
		}
	}
	/*
	* Returns true if `this` and `s` agree on the decisions they have in common
	* Formally, if this.selection(d) == s.selection(d) for all decisions d present in both solutions
	*/
	boolean compatible(Solution s){
		for (Decision d: this.decisions()){
			if ( s.selection(d) != null && !s.selection(d).equals(this.selection(d)) ) return false;
		}
		return true;
	}

	/*
	* Returns a new solution that combines decisions in 'this` and s.
	* If `this` and s disagree on some decision, the new solution keeps the decision in `this`.
	*/
	Solution merge(Solution s){
		Solution result = new Solution();
		s.selection.putAll(this.selection);
		result =s;
		return result;		
	}

	public String selection (Decision d){
		String option = null;
		if (selection  != null){
			for (Map.Entry<Decision, String> entry:selection.entrySet() ){
				if (entry.getKey().getDecisionLabel().equals(d.getDecisionLabel())){
					option =  entry.getValue();
					break;
				}
			}
		}
		return option;
	}
	public Map<Decision, String> getSelection(){
		return selection;
	}
	public void addDecision (Decision d, String option){
		selection.put(d, option);
	}
	public void setUniqueID (String uniqueID){
		uniqueID_ =uniqueID;
	}
	public String getUniqueID (){
		return uniqueID_;
	}
	public Model getSemanticModel (){
		return sematicModel;
	}
	public void setSemanticModel(Model model){
		sematicModel =  model;
	}
	public String selectionToString (){
		String output = "";
		if (selection != null){
			for (Map.Entry<Decision, String> entry:selection.entrySet() ){
				output += entry.getKey().getDecisionLabel() + ":" +entry.getValue() + ",";
			}
			output = output.substring(0,output.length()-1 );
		}
		return output;
	}
	List<Decision> decisions(){
		return new ArrayList<Decision>( selection.keySet());
	}

	/*
	* Returns true if both solutions have the same option selection on all their decisions.
	*/
	boolean equals(Solution s){
		return this.selection.equals(s.selection);
	}

	/*
	* Returns true if all decisions in 'this' are the same as in s.
	* Note: if this.equals(s) then this.subSolution(s), 
	* but not vice-versa because s may contain decisions that are not defined in `this`.
	*/
	boolean subSolution(Solution s){
		// we do not want to do a  check when a new Solution "this" with an empty selection is the same as new Solution "s" populated within the same AND_refinement
		if (this.getUniqueID().equals(s.getUniqueID())){
			return false;
		}
		for (Decision d: this.decisions()){
			if (!this.selection(d).equals(s.selection(d))) return false;
		}
		return true;
	}
	@Override
	public int hashCode (){
		if (selection == null){
			return 0;
		}
		return selection.hashCode();
	}
	
	
}
