package radar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import radar.utilities.Statistics;

 class Simulator {

	private Map<Objective, Double> fitnessValues_;
	Solution selectedAlternative;
	Model semanticModel;
	public Simulator (Solution selectedAlternative, Model semanticModel){
		this.selectedAlternative =selectedAlternative;
		this.semanticModel = semanticModel;
	}
	public Map<Objective, Double> computeFitnessValues (){
		fitnessValues_ = new LinkedHashMap<Objective, Double>();
		this.selectedAlternative.setSemanticModel(semanticModel);
		List<Objective> objList = this.semanticModel.getObjectives();
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
		List<Objective> objList = this.semanticModel.getObjectives();
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			double value = obj.evaluate(this.selectedAlternative);
			value = obj.getIsMinimisation() == false ? value *-1 : value;
			results.addObjectiveValue(obj, value);
		}
		results.setSolution(this.selectedAlternative);
		return results;
	}

	

}
