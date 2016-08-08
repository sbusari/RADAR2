package radar.model;

import java.util.LinkedHashMap;
import java.util.Map;
class Solution {

	private Map<Decision, String> selection;
	private Model sematicModel;
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
	public String getOption (Decision d){
		String option ="";
		if (selection  != null){
			for (Map.Entry<Decision, String> entry:selection.entrySet() ){
				if (entry.getKey().getDecisionLabel().equals(d.getDecisionLabel())){
					option =  entry.getValue();
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
	@Override
	public int hashCode (){
		if (selection == null){
			return 0;
		}
		return selection.hashCode();
	}
	
	
}
