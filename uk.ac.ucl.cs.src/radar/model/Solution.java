package radar.model;

import java.util.LinkedHashMap;
import java.util.Map;
class Solution {

	private Map<Decision, String> selection;
	// since the selection sometimes is null and its been carried on from one quality variable to the children
	// i then introduced a global selection which does not change and only used to create selection in subsolution method.
	private Map<Decision, String> globalSelection;
	// added this cos during simulation, for an identifier expr e.g cost, we need to get the qv definition from this model.
	private Model sematicModel;
	// used during simulation of each objective to know which obj we would like to store its simulation values.
	// incase more than one obj refer to the same qv, and we want to perform info analyis on the objectives, this field is used to distinguish objs.
	private Objective infoValueObj_;
	private String parameter_;
	private boolean storeObjSimParameter_;
	public Solution(){
		selection = new LinkedHashMap<Decision, String>();
		globalSelection = new LinkedHashMap<Decision, String>();
	}
	public Solution (Solution a){
		sematicModel = a.sematicModel;
		infoValueObj_ = a.getInfoValueObjective();
		storeObjSimParameter_ = a.getIsObjSimParameterStored();
		selection = new LinkedHashMap<Decision, String>();
		if (a.selection != null){
			selection.putAll(a.selection);
		}
		globalSelection = new LinkedHashMap<Decision, String>();
		if (a.globalSelection != null){
			globalSelection.putAll(a.globalSelection);
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
	public String getOptionFromSelction (Decision d){
		String option ="";
		if (globalSelection  != null){
			for (Map.Entry<Decision, String> entry:globalSelection.entrySet() ){
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
		globalSelection.put(d, option);
	}
	public void setGlobalSelection(Map<Decision, String> aGlobalSelection){
		globalSelection =aGlobalSelection ;
	}
	public Map<Decision, String> getGlobalSelection(){
		return globalSelection;
	}
	public void setStoredObjSimParameter (boolean storedObjSimParameter){
		storeObjSimParameter_ = storedObjSimParameter;
	}
	public boolean getIsObjSimParameterStored (){
		return 	storeObjSimParameter_;
	}
	public Model getSemanticModel (){
		return sematicModel;
	}
	public void setSemanticModel(Model model){
		sematicModel =  model;
	}
	public Objective getInfoValueObjective (){
		return infoValueObj_;
	}
	public void setInfoValueObjective (Objective infoValueObj){
		infoValueObj_ =  infoValueObj;
	}
	public void setParameter (String parameter){
		parameter_ =parameter;
	}
	public String getParameter (){
		return parameter_;
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
