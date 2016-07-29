package radar.model;

import java.util.LinkedHashMap;
import java.util.Map;
public class Alternative {

	private Map<Decision, String> selection;
	// added this cos during simulation, for an identifier expr e.g cost, we need to get the qv definition from this model.
	private Model sematicModel;
	private String infoValueObj_;
	public String getOption (Decision d){
		String option ="";
		for (Map.Entry<Decision, String> entry:selection.entrySet() ){
			if (entry.getKey().getDecisionLabel().equals(d.getDecisionLabel())){
				option =  entry.getValue();
			}
		}
		return option;
	}
	public Map<Decision, String> getSelection(){
		return selection;
	}
	public void addDecision (Decision d, String option){
		if (selection == null){
			selection = new LinkedHashMap<Decision, String>();
			selection.put(d, option);
		}else{
			selection.put(d, option);
		}
	}
	public Model getSemanticModel (){
		return sematicModel;
	}
	public void setSemanticModel(Model model){
		sematicModel =  model;
	}
	public String getInfoValueObjective (){
		return infoValueObj_;
	}
	public void setInfoValueObjective (String infoValueObj){
		infoValueObj_ =  infoValueObj;
	}
	public String selectionToString (){
		String output = "";
		if (selection != null){
			for (Map.Entry<Decision, String> entry:selection.entrySet() ){
				output += entry.getKey().getDecisionLabel() + " " +entry.getValue() + ",";
			}
			output += output.substring(0,output.length()-1 );
		}
		return output;
	}
	
	
}
