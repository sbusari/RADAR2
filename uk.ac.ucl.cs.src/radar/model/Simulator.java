package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import uk.ac.ucl.cs.radar.utilities.Statistics;

 class Simulator {

	private Map<Objective, Double> fitnessValues_;
	Solution selectedAlternative;
	Model semanticModel;
	boolean objectivesReferToSameQV_;
	public Simulator (Solution selectedAlternative, Model semanticModel){
		this.selectedAlternative =selectedAlternative;
		this.semanticModel = semanticModel;
		this.objectivesReferToSameQV_ = objectiveReferToSameQV();
	}
	public Map<Objective, Double> computeFitnessValues (){
		fitnessValues_ = new LinkedHashMap<Objective, Double>();
		this.selectedAlternative.setSemanticModel(semanticModel);
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			double value = obj.evaluate(this.selectedAlternative);
			fitnessValues_.put(obj,value);
		}
		return fitnessValues_;
	}
	public SolutionValues computeObjectivesValues (){
		SolutionValues results = new SolutionValues();
		this.selectedAlternative.setSemanticModel(semanticModel);
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			double value = obj.evaluate(this.selectedAlternative);
			value = obj.getIsMinimisation() == false ? value *-1 : value;
			results.addObjectiveValue(obj, value);
		}
		results.setSolution(this.selectedAlternative);
		return results;
	}
	public SolutionValues computeObjectivesValues2 (){
		SolutionValues results = new SolutionValues();
		this.selectedAlternative.setSemanticModel(semanticModel);
		//this.selectedAlternative.setInfoValueObjectiveName(semanticModel.getInfoValueObjective().getQualityVariable().getLabel());
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			if ( semanticModel.getInfoValueObjective() != null){
				boolean storedObjSimParams = storeObjectiveSimulationParameters(obj);
				this.selectedAlternative.setStoredObjSimParameter(storedObjSimParams);
				this.selectedAlternative.setInfoValueObjective(obj);
			}
			double value = obj.evaluate(this.selectedAlternative);
			value = obj.getIsMinimisation() == false ? value *-1 : value;
			results.addObjectiveValue(obj, value);
		}
		this.semanticModel.resetSimulationVariables();
		results.setSolution(this.selectedAlternative);
		return results;
	}
	Objective getInfoValueObj (Objective obj){
		Objective infoValueObjective =new Objective();
		Objective infoValueObj = semanticModel.getInfoValueObjective();
		if (infoValueObj.getLabel().equals(obj.getLabel())){
			infoValueObjective = obj;
		}
		return infoValueObjective;
	}
	boolean storeObjectiveSimulationParameters (Objective obj){
		boolean result =false;
		Objective infoValueObj = semanticModel.getInfoValueObjective();
		if (infoValueObj.getLabel().equals(obj.getLabel()) && 
				infoValueObj.getStatistic().getObjExpression().getClass().equals(obj.getStatistic().getObjExpression().getClass())){
			if (infoValueObj.getQualityVariable().getLabel().equals(obj.getQualityVariable().getLabel())){
				return true;
			}
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
