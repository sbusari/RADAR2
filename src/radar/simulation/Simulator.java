package radar.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.model.Alternative;
import radar.model.Model;
import radar.model.Objective;
import radar.model.SolutionValues;
import radar.utilities.Statistics;

public class Simulator {

	private Map<Objective, Double> objectiveValues_;
	Alternative selectedAlternative;
	Model semanticModel;
	boolean objectivesReferToSameQV_;
	public Simulator (Alternative selectedAlternative, Model semanticModel){
		this.selectedAlternative =selectedAlternative;
		this.semanticModel = semanticModel;
		this.objectivesReferToSameQV_ = objectiveReferToSameQV();
	}
	public Map<Objective, Double> computeObjectiveValues (){
		objectiveValues_ = new LinkedHashMap<Objective, Double>();
		this.selectedAlternative.setSemanticModel(semanticModel);
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			this.selectedAlternative.setStoreSimParameter(storeSimulationParameters(obj));
			double value = obj.evaluate(this.selectedAlternative);
			objectiveValues_.put(obj,value);
		}
		return objectiveValues_;
	}
	public SolutionValues computeObjectivesValues (){
		SolutionValues results = new SolutionValues();
		this.selectedAlternative.setSemanticModel(semanticModel);
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			this.selectedAlternative.setStoreSimParameter(storeSimulationParameters(obj));
			double value = obj.evaluate(this.selectedAlternative);
			value = obj.getIsMinimisation() == false ? value *-1 : value;
			results.addObjectiveValue(obj, value);
		}
		results.setSolution(this.selectedAlternative);
		return results;
	}
	boolean storeSimulationParameters (Objective obj){
		boolean result =false;
		if (this.objectivesReferToSameQV_){
			// we only want to check  for the objective statistic when the objective refer to the same qv.
			if (semanticModel.getInfoValueObjectiveName().equals(obj.getQualityVariable().getLabel()) && semanticModel.getInfoValueObjective().getStatistic().getObjExpression().getClass().equals(obj.getStatistic().getObjExpression().getClass())){
				result =true;
			}
		}else{
			result = true;
		}
		return result;
	}
	boolean objectiveReferToSameQV (){
		objectivesReferToSameQV_ = false;
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		String qv_referTo ="";
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			objectivesReferToSameQV_ = obj.getQualityVariable().getLabel().equals(qv_referTo)? true:false;
			qv_referTo = obj.getQualityVariable().getLabel();
		}
		return objectivesReferToSameQV_;
	}
	

}
