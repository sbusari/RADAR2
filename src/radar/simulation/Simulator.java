package radar.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.model.Alternative;
import radar.model.Model;
import radar.model.Objective;
import radar.utilities.Statistics;

public class Simulator {

	private Map<Objective, Double> objectiveValues_;
	Alternative selectedAlternative;
	Model semanticModel;
	public Simulator (Alternative selectedAlternative, Model semanticModel){
		this.selectedAlternative =selectedAlternative;
		this.semanticModel = semanticModel;
	}
/*	public Map<Objective, Double> computeObjectiveValues (){
		objectiveValues_ = new LinkedHashMap<Objective, Double>();
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			this.selectedAlternative.setSemanticModel(semanticModel);
			this.selectedAlternative.setInfoValueObjectiveName(obj.getQualityVariable().getLabel());
			this.selectedAlternative.setInformationValueObjective(obj);
			//double [] value = obj.getQualityVariable().simulate(this.selectedAlternative);
			double value = obj.evaluate(this.selectedAlternative);
			objectiveValues_.put(obj,value);
		}
		return objectiveValues_;
	}*/
	public Map<Objective, Double> computeObjectiveValues (){
		objectiveValues_ = new LinkedHashMap<Objective, Double>();
		this.selectedAlternative.setSemanticModel(semanticModel);
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			// if they have same name and same statistic definition.
			if (semanticModel.getInfoValueObjectiveName().equals(obj.getQualityVariable().getLabel())
					&& semanticModel.getInfoValueObjective().getStatistic().getObjExpression().getClass().equals(obj.getStatistic().getObjExpression().getClass())){	
				this.selectedAlternative.setInfoValueObjectiveName(obj.getQualityVariable().getLabel());
				this.selectedAlternative.setInformationValueObjective(semanticModel.getInfoValueObjective());
			}
			// set this to to know if an alternative has already been simulated for a particular objective.
			this.selectedAlternative.setCurrentSimulatedObjective(obj);
			double value = obj.evaluate(this.selectedAlternative);
			// set the alternative infoValueOb so that if the next objective also has it willbe updated back to prevent carrying on for all obejectives
			this.selectedAlternative.setInformationValueObjective(null);
			objectiveValues_.put(obj,value);
		}
		this.selectedAlternative.setInformationValueObjective(semanticModel.getInfoValueObjective());
		return objectiveValues_;
	}
	

}
