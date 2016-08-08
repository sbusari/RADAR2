package radar.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class SolutionValues {

	Map<Objective, Double> objvalue_ ;
	Solution solution_;
	public SolutionValues(){
		objvalue_ = new LinkedHashMap<Objective, Double>();
	}
	public void setSolution (Solution solution){
		solution_ = solution;
	}
	public Solution getSolution (){
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
		String result ="";
		Map<Decision,String> selection = solution_.getGlobalSelection();
		for(Map.Entry<Decision,String> entry: selection.entrySet()){
			result +=entry.getValue() + ",";
		}
		for(Map.Entry<Objective,Double> entry: objvalue_.entrySet()){
			result +=entry.getValue() + ",";
		}
		result.substring(0, result.length()-1);
		return result;
	}
	
}
