package radar.information.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.model.Alternative;
import radar.model.Model;
import radar.model.Objective;
import radar.model.QualityVariable;

public class InformationValueAnalysis {
	
	public InformationValueAnalysis (){

	}
	public double computeEVTPI (Objective infoValueObj, List<Alternative> allAlternative){
		QualityVariable var = infoValueObj.getQualityVariable();
		Map<Alternative, double[]> varSimData = var.getSimData();
		Map<Alternative, double[]> alternativesSimData = new LinkedHashMap<Alternative,double[]> ();
		for (Map.Entry<Alternative, double[]> entry:varSimData.entrySet() ){
			if (entry.getKey().getInfoValueObjective().equals(var.getLabel())){
				alternativesSimData.put(entry.getKey(), entry.getValue());
			}
			/*for (int i =0 ; i < allAlternative.size(); i ++){
				if (entry.getKey().selectionToString().equals(allAlternative.get(i).selectionToString())){
					alternativesSimData.put(allAlternative.get(i), entry.getValue());
				}
			}*/
		}
		double [][] simData = getSimData (alternativesSimData);
 		return InformationAnalysis.evpi(simData);
	}

	/*private double[][] getSimData(List<Alternative> allAlternative) {
		double [][] simData = new double [allAlternative.size()][];
		for (int i =0; i <  allAlternative.size() ; i ++){
			simData[i] = allAlternative.get(i).getObjectiveSimData();
			i++;
		}
		return simData;
	}*/
	private double[][] getSimData(Map<Alternative, double[]> allAlternative) {
		double [][] simData = new double [allAlternative.size()][];
		int i =0;
		for (Map.Entry<Alternative, double[]> entry: allAlternative.entrySet()){
			simData[i] = entry.getValue();
			i++;
		}
		return simData;
	}

}
