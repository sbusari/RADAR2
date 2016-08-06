package radar.information.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import radar.model.Solution;
import radar.model.Model;
import radar.model.Objective;
import radar.model.Parser;
import radar.model.QualityVariable;

public class InformationValueAnalysis {
	
	
	int simulation;
	Model semanticModel_;
	public InformationValueAnalysis (int noOfSim){
		simulation = noOfSim;
	}
	public double computeEVTPI (Objective infoValueObj, List<Solution> allAlternative){
		Map<Solution, double[]> alternativesSimData = getAlternativeSimData(infoValueObj,allAlternative);
		double [][] simData = getSimData (alternativesSimData);
		double value = InformationAnalysis.evpi(simData);
 		return value ;
	}
	public double computeEVPPI (Objective infoValueObj, List<Solution> allAlternative, double [] param){
		Map<Solution, double[]> alternativesSimData = getAlternativeSimData(infoValueObj,allAlternative);
		double [][] simData = getSimData (alternativesSimData);
		double value = InformationAnalysis.evppi(param, simData);
 		return value ;
	}
	private Map<Solution, double[]> getAlternativeSimData (Objective infoValueObj, List<Solution> optimalSolutions){
		Map<Solution, double[]> alternativesSimData = new LinkedHashMap<Solution,double[]> ();
		QualityVariable var = infoValueObj.getQualityVariable();
		Map<Solution, double[]> varSimData =  var.getSimData();
		// create a var data for only the optimal solutions only. 
		if (optimalSolutions != null && optimalSolutions.size() > 0){
			for (int i =0; i < optimalSolutions.size(); i++){
				for (Map.Entry<Solution , double[]> simEntry : varSimData.entrySet()){
					// did this check in case two objectives refer to same qv and may be only one obj is to be used for information value analysis
					if (optimalSolutions.get(i).selectionToString().equals(simEntry.getKey().selectionToString()) && simEntry.getKey().getIsObjSimParameterStored() 
							&& infoValueObj.getLabel().equals(simEntry.getKey().getInfoValueObjective().getLabel())){
						alternativesSimData.put(simEntry.getKey(), simEntry.getValue());
					}	
				}
			}
		}else{
			alternativesSimData = varSimData;
		}
		return alternativesSimData;
	}
	private double[][] getSimData(Map<Solution, double[]> allAlternative) {
		double [][] simData = new double [allAlternative.size()][simulation];
		int i =0;
		if(allAlternative.size() > 0){
			for (Map.Entry<Solution, double[]> entry: allAlternative.entrySet()){
				// did this looping to create new instance as computation updates dim values if i pass by reference.
				for (int j = 0 ; j < entry.getValue().length; j ++){
					simData[i][j] = entry.getValue()[j];
				}
				i++;
			}
		}
		return simData;
	}

}
