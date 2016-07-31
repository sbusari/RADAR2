package radar.model;

import java.util.LinkedHashMap;
import java.util.Map;
public class Alternative {

	private Map<Decision, String> selection;
	// since the selection sometimes is null and its been carried on from one quality variable to the children
	// i then introduced a global selection which does not change and only used to create selection in subsolution method.
	private Map<Decision, String> globalSelection;
	// added this cos during simulation, for an identifier expr e.g cost, we need to get the qv definition from this model.
	private Model sematicModel;
	// added this here to aid computation of evtpi
	private String infoValueObj_;
	// added this here to aid computation of evtpi
	private Objective informationValueObjective_;
	private Objective currentSimulatedObjective_;
	public Alternative(){}
	public Alternative (Alternative a){
		sematicModel = a.sematicModel;
		infoValueObj_ = a.getInfoValueObjectiveName();
		if (a.getCurrentSimulatedObjective() != null){
			currentSimulatedObjective_ = a.getCurrentSimulatedObjective();
		}
		if (a.getInformationValueObjective() != null){
			informationValueObjective_ = a.getInformationValueObjective();
		}
		selection = new LinkedHashMap<Decision, String>();
		if (a.selection != null){
			selection.putAll(a.selection);
		}
		globalSelection = new LinkedHashMap<Decision, String>();
		if (a.globalSelection != null){
			globalSelection.putAll(a.globalSelection);
		}
		
		/*if (a.getSelection() != null){
			for (Map.Entry<Decision, String> entry : a.getSelection().entrySet()){
				selection.put(entry.getKey(), entry.getValue());
			}
		}*/
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
		if (selection == null){
			selection = new LinkedHashMap<Decision, String>();
			selection.put(d, option);
		}else{
			selection.put(d, option);
		}
	}
	public void setGlobalSelection(Map<Decision, String> aGlobalSelection){
		globalSelection =aGlobalSelection ;
	}
	public Map<Decision, String> getGlobalSelection(){
		return globalSelection;
	}
	public void addDecisionToGlobalSelection (Decision d, String option){
		if (globalSelection == null){
			globalSelection = new LinkedHashMap<Decision, String>();
			globalSelection.put(d, option);
		}else{
			globalSelection.put(d, option);
		}
	}
	public Model getSemanticModel (){
		return sematicModel;
	}
	public void setSemanticModel(Model model){
		sematicModel =  model;
	}
	public String getInfoValueObjectiveName (){
		return infoValueObj_;
	}
	public void setInfoValueObjectiveName (String infoValueObj){
		infoValueObj_ =  infoValueObj;
	}
	public Objective getInformationValueObjective (){
		return informationValueObjective_;
	}
	public void setInformationValueObjective (Objective infoValueObj){
		informationValueObjective_ =  infoValueObj;
	}
	public Objective getCurrentSimulatedObjective (){
		return currentSimulatedObjective_;
	}
	public void setCurrentSimulatedObjective (Objective currentSimulatedObjective){
		currentSimulatedObjective_ =  currentSimulatedObjective;
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
	@Override
	public int hashCode (){
		if (selection == null){
			return 0;
		}
		return selection.hashCode();
	}
	
	
}
