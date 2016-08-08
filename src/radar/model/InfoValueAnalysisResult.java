package radar.model;

import java.util.LinkedHashMap;
import java.util.Map;

class InfoValueAnalysisResult{

	private String objective;
	private double evtpi;
	private Map<String, Double> evppi;
	private boolean isObjectiveMinimisation;

	InfoValueAnalysisResult(String obj, boolean isObjMin){
		objective = obj;
		isObjectiveMinimisation = isObjMin;
		evppi = new LinkedHashMap<String, Double>();
	}

	void setEVTPI(double n){
		evtpi = n;
	}

	void addEVPPI(String param, Double n){
		evppi.put(param, n);
	}

	public String toString(){
		String result = "";
		
		result += "EVTPI: \t\t\t "+ evtpi + "\n";
		result += "Parameter \t\t\t EVPPI \n";
		for (Map.Entry<String, Double> entry: evppi.entrySet()){
			result += entry.getKey() + ": \t\t\t "+ entry.getValue() +"\n";
		}
		return result;
	}
	
}
