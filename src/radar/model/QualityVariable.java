package radar.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class QualityVariable extends ArithmeticExpression {

	private String label_;
	private Expression definition_;
	private Map<Alternative, double[]> simData_;
	// may not use simParameters_ again
	private Map<String, double[]> simParameters_;
	private Map<String, Decision> decisionsBeforeVar;
	private Map<String, Decision> decisionsAfterVar;
	public void setLabel (String label){
		label_ = label;
	}
	public String getLabel (){
		return label_;
	}
	public void setDefinition (Expression def){
		definition_ = def;
	}
	public Expression getDefinition (){
		return definition_;
	}
	public void setDecisionsAfterVar ( Map<String, Decision> decAfterVar){
		decisionsAfterVar = decAfterVar;
	}
	public void addDecisionsAfterVar ( String decisionName,  Decision d){
		if (decisionsAfterVar == null){
			decisionsAfterVar = new LinkedHashMap<String,Decision>();
			decisionsAfterVar.put(decisionName, d);
		}else{
			decisionsAfterVar.put(decisionName, d);
		}
	}
	public void setDecisionsBeforeVar ( Map<String, Decision> decBeforeVar){
		decisionsBeforeVar = decBeforeVar;
	}
	
	public Map<String, Decision> getDecisionsAfterVar ( ){
		return decisionsAfterVar ;
	}
	public Map<String, Decision> getDecisionsBeforeVar ( ){
		return decisionsBeforeVar;
	}
	public double[] getSimData(Alternative s) {
		return simulate(s);
	}
	public double[][] getSimData(List<Alternative> s) {
		double [][] result = new double [s.size()][];
		for (int i =0 ; i < s.size(); i ++){
			result[i] = getSimData (s.get(i));
		}
		return result;
	}

	private Alternative alreadySimulatedAlternative (Alternative a){
		Alternative result = null;
		if (simData_ != null && a.getSelection() != null){
			// they haave equal selection and refer to the same objective function.
			for (Map.Entry<Alternative, double []> entry:simData_.entrySet() ){
				if (entry.getKey().selectionToString().equals(a.selectionToString())
						&& entry.getKey().getInformationValueObjective().getLabel().equals(a.getInformationValueObjective().getLabel())){
					return entry.getKey();	
				}
			}
		}
		return result;
	}
	public double [] simulate (Alternative s){
		double [] simdata = null;
		Alternative localSolution = subSolution(s);
		
		if (simData_ == null ){
			simData_ = new HashMap<Alternative, double[]> ();
			double [] sim = definition_.simulate(localSolution);
			simData_.put(localSolution, sim);
			simdata=sim ;
		}else if (simData_.get(localSolution) == null){
			double [] sim = definition_.simulate(s);
			simData_.put(localSolution, sim);
			simdata=sim ;
		}else{
			simdata = simData_.get(localSolution);
			//simdata = simData_.get(alreadySimulated);
		}
		
		
		/*else{
			//else if (!simData_.containsKey(localSolution)){
			//else if (simData_.get(localSolution) == null){
			Alternative alreadySimulated = alreadySimulatedAlternative(localSolution);
			if (alreadySimulated == null){
				double [] sim = definition_.simulate(s);
				simData_.put(localSolution, sim);
				simdata=sim ;
			}else{
				//simdata = simData_.get(localSolution);
				simdata = simData_.get(alreadySimulated);
			}
		}*/
		
		if (definition_.getIsExpresionDistribution() && s.getSemanticModel().getParams() != null &&  s.getSemanticModel().getParams().contains(label_)){
			if (simParameters_ == null){
				simParameters_ = new LinkedHashMap<String, double[]>();
				addParameterDistributions(simParameters_,simdata);
				
			}else{
				addParameterDistributions(simParameters_,simdata);
			}
		}
		// the option that comes out with the distribution value are added in a 
		// map that stores the option name and sim value.
		// may be boolean express from the returning expr can also be used to know whether to populate the map
		// set the boolean varibale back to false so that another qv that uses it can set it itself
		// finally we do a checkof the list of papam and see if this qv is part of it, if yes, we save the distribution values.

		return simdata;
	}
	private void addParameterDistributions (Map<String, double[]> simParameters, double [] simdata){
		if (definition_.getparameterOption() != null && StringUtils.isNoneEmpty(definition_.getparameterOption())){
			simParameters.put(label_ + " " + definition_.getparameterOption(),simdata);
		}else{
			simParameters.put(label_ ,simdata);
		}
	}
	private Alternative subSolution (Alternative s){
		Alternative subsolution = new Alternative();
		if (decisionsAfterVar != null){
			//for (int i =0; i < decisionsAfterVar.size(); i++){
			for (Map.Entry<String, Decision> entry:decisionsAfterVar.entrySet()){
				Decision d = entry.getValue();
				String option = s.getOption(d);
				subsolution.addDecision(d,option);
			}
		}
		subsolution.setInfoValueObjectiveName(s.getInfoValueObjectiveName());
		subsolution.setInformationValueObjective(s.getInformationValueObjective());
		subsolution.setSemanticModel(s.getSemanticModel());
		return subsolution;
	}
	public Map<Alternative, double[]> getSimData(){
		return simData_;
	}
	public Map<String, double[]> getParameterSimData(){
		return simParameters_;
	}


	
}
