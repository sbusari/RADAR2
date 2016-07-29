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
	public Map<Objective, Double> computeObjectiveValues (){
		objectiveValues_ = new LinkedHashMap<Objective, Double>();
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel.getObjectives().values());
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			this.selectedAlternative.setSemanticModel(semanticModel);
			//double [] value = obj.getQualityVariable().simulate(this.selectedAlternative);
			double value = obj.evaluate(this.selectedAlternative);
			objectiveValues_.put(obj,value);
		}
		return objectiveValues_;
	}
	

}
