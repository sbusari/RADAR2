package radar.model;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

final class Solution {

	private final Map<Decision, String> selection;
	private Model sematicModel;
	
	Solution(){
		selection = new LinkedHashMap<Decision, String>();
	}

	Solution(Map<Decision, String> mapping){
		selection = mapping;
	}

	Solution addDecision (Decision d, String option){
		Map<Decision, String> mapping = new LinkedHashMap<Decision, String>(this.selection);
		mapping.put(d, option);
		return new Solution(mapping);
	}

	/*
	* Returns a new solution that takes the union of all decisions in `this` and `s`.
	* If a decision is made in both `this` and s, the union keeps the option selected in `this`. 
	*/
	Solution union(Solution s){
		Map<Decision, String> mapping = new LinkedHashMap<Decision, String>(s.selection);
		mapping.putAll(this.selection);
		return new Solution(mapping);		
	}

	public String selection (Decision d){
		return selection.get(d);
	}

	Set<Decision> decisions(){
		return selection.keySet();
	}
	/*public String selectionToString (){
			The standard string format for maps is better than an ad-hoc one 	
		// but not helpful for debugging as it shows objects ref and not the string its self
		return this.toString();
	}*/
	public String selectionToString (){
		String output = "";
		if (selection != null && selection.size() > 0){
			for (Map.Entry<Decision, String> entry:selection.entrySet() ){
				output += entry.getKey().getDecisionLabel() + ":" +entry.getValue() + ",";
			}
			output = output.substring(0,output.length()-1 );
		}
		return output;
	}
	/*
	* Returns true if both solutions have the same option selection on all their decisions.
	*/
	@Override
    public boolean equals(Object o){
        if (o.getClass() != getClass()) return false;
        Solution s = (Solution) o;
        return this.selection.equals(s.selection);
    }
  
    @Override
    public String toString(){
        return selection.toString();
    }

	@Override
	public int hashCode (){
		return selection.hashCode();
	}
	public Model getSemanticModel (){
		return sematicModel;
	}
	public void setSemanticModel(Model model){
		sematicModel =  model;
	}
	public Map<Decision, String> getSelection(){
		return selection;
	}
	
	
}
