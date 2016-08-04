package radar.information.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.jmetal.core.SolutionSet;
import radar.model.Alternative;
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
	public void performInformationAnalysis (Model sematicmodel,  SolutionSet optimalSolutions ){
		if (sematicmodel.getInfoValueObjective() != null && sematicmodel.getInfoValueObjective().size() > 0 ){
			List<Objective> infoValueObjs = sematicmodel.getInfoValueObjective();
			for (Objective currentInfoValueObj : infoValueObjs){
				List<Alternative> optimalAlternative = new ArrayList<Alternative>();
				if (optimalSolutions != null){
					for (int i =0 ; i < optimalSolutions.size(); i ++){
						optimalAlternative.add(optimalSolutions.get(i).getAlternative());
					}
				}
				
				double infoValue = computeEVTPI(currentInfoValueObj, optimalAlternative);
				System.out.println("evtpi for objective "+  currentInfoValueObj.getLabel()+ " is: "+ infoValue );

				List<String> params = sematicmodel.getParameters();
				if (params != null){
					for (int i =0; i < params.size(); i ++){
						QualityVariable qvSim= sematicmodel.getQualityVariables().get(params.get(i));
						Map<Alternative, double[]> paramSimData = qvSim.getParameterSimData();
						
						// sim data of the the current currentInfoValueObj
						Map<Alternative, double[]> currentInfoValueObjSimData  = new LinkedHashMap<Alternative, double[]>();
						for (Map.Entry<Alternative, double[]> entry: paramSimData.entrySet()){
							if (entry.getKey().getInfoValueObjective().getLabel().equals(currentInfoValueObj.getLabel())){
								currentInfoValueObjSimData.put(entry.getKey(), entry.getValue());
							}
						}
						// 
						for (Map.Entry<Alternative, double[]> entry: currentInfoValueObjSimData.entrySet()){
							double evppi = computeEVPPI(currentInfoValueObj, optimalAlternative, entry.getValue());
							System.out.println("evppi for  objective "+  currentInfoValueObj.getLabel()+ " and parameter "+ entry.getKey().getParameter()+ " is "+ evppi );
						}
					}
				}
			}
			
		}else{
			System.out.println("information valu analysis cannot be performed because of any of the following: \n1. There was no specified objective. \n2. The specified objective is not in the model. \n3. The specified objective has no quality variable it refers to." );
		}
	}
	public double computeEVTPI (Objective infoValueObj, List<Alternative> allAlternative){
		Map<Alternative, double[]> alternativesSimData = getAlternativeSimData(infoValueObj,allAlternative);
		double [][] simData = getSimData (alternativesSimData);
		double value = InformationAnalysis.evpi(simData);
 		return value ;
	}
	public double computeEVPPI (Objective infoValueObj, List<Alternative> allAlternative, double [] param){
		Map<Alternative, double[]> alternativesSimData = getAlternativeSimData(infoValueObj,allAlternative);
		double [][] simData = getSimData (alternativesSimData);
		double value = InformationAnalysis.evppi(param, simData);
 		return value ;
	}
	private Map<Alternative, double[]> getAlternativeSimData (Objective infoValueObj, List<Alternative> optimalSolutions){
		Map<Alternative, double[]> alternativesSimData = new LinkedHashMap<Alternative,double[]> ();
		QualityVariable var = infoValueObj.getQualityVariable();
		Map<Alternative, double[]> varSimData =  var.getSimData();
		// create a var data for only the optimal solutions only. 
		if (optimalSolutions != null && optimalSolutions.size() > 0){
			for (int i =0; i < optimalSolutions.size(); i++){
				for (Map.Entry<Alternative , double[]> simEntry : varSimData.entrySet()){
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
	private double[][] getSimData(Map<Alternative, double[]> allAlternative) {
		double [][] simData = new double [allAlternative.size()][simulation];
		int i =0;
		if(allAlternative.size() > 0){
			for (Map.Entry<Alternative, double[]> entry: allAlternative.entrySet()){
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
