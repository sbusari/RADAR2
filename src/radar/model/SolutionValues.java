package radar.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class SolutionValues {

	Map<Objective, Double> objvalue_ ;
	Alternative solution_;
	public SolutionValues(){
		objvalue_ = new LinkedHashMap<Objective, Double>();
	}
	public void setSolution (Alternative solution){
		solution_ = solution;
	}
	public Alternative getSolution (){
		return solution_;
	}
	public void setObjectiveValue (Map<Objective, Double> value ){
		objvalue_ = value;
	}
	public Map<Objective, Double>  getObjectiveValue  (){
		return objvalue_;
	}
	public void  addObjectiveValue (Objective obj, double value){
		objvalue_.put(obj, value);
	}
	public String solutionValueToString (){
		return null;
	}
	
}
