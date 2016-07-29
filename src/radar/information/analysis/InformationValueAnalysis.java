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
		Map<Alternative, double[]> alternativesSimData =getAlternativeSimData(infoValueObj);
		double [][] simData = getSimData (alternativesSimData);
 		return InformationAnalysis.evpi(simData);
	}
	public double computeEVPPI (Objective infoValueObj, List<Alternative> allAlternative, double [] param){
		Map<Alternative, double[]> alternativesSimData =getAlternativeSimData(infoValueObj);
		double [][] simData = getSimData (alternativesSimData);
 		return InformationAnalysis.evppi(param, simData);
	}
	private Map<Alternative, double[]> getAlternativeSimData (Objective infoValueObj){
		Map<Alternative, double[]> alternativesSimData = new LinkedHashMap<Alternative,double[]> ();
		QualityVariable var = infoValueObj.getQualityVariable();
		Map<Alternative, double[]> varSimData = var.getSimData();
		for (Map.Entry<Alternative, double[]> entry:varSimData.entrySet() ){
			// dis this check cos varSimData contains sim for all objective, had to get sim data for the objective we want.
			if (entry.getKey().getInfoValueObjectiveName().equals(var.getLabel())
					&& entry.getKey().getInformationValueObjective().getStatistic().equals(infoValueObj.getStatistic()) ){
				alternativesSimData.put(entry.getKey(), entry.getValue());
			}
		}
		return alternativesSimData;
	}
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
