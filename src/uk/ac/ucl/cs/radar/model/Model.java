package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ucl.cs.radar.information.analysis.InformationAnalysis;


public class Model {
	
	private static Model instance = null;
	public  Model getInstance() {
		if(instance == null) {
			instance = new Model();
		}
		
		return instance;
	}
	private String modelName_;
	private Map<String, Objective> objectives_;
	private Map<String, QualityVariable> qualityVariables_;
	private List<String> parameters_;
	// list param variables to be used in finding evppi.
	private List<String> params_;
	private Map<String, Decision> decisions_;
	private List<Solution> alternative_;
	private Objective infoValueObjective_;
	private String infoValueObjectiveName_;
	private int noOfSimulation_;
	private String solutionType_;
	public void setModelName(String modelName ){
		modelName_ =modelName;
	}
	public String getModelName(){
		return modelName_;
	}
	public void addQualityVariables(String qv_name, QualityVariable qualityVariable){
		if (qualityVariables_ == null){
			qualityVariables_ = new LinkedHashMap<String,QualityVariable >();
			qualityVariables_.put(qv_name, qualityVariable);
			
		}else{
			qualityVariables_.put(qv_name, qualityVariable);
		}
	}
	public Map<String, QualityVariable> getQualityVariables(){
		return qualityVariables_;
	}
	public void addParameters(String param_name){
		if (parameters_ == null){
			parameters_ = new ArrayList<String>();
			if (!parameters_.contains(param_name)){
				parameters_.add(param_name);
			}
			
		}else{
			if (!parameters_.contains(param_name)){
				parameters_.add(param_name);
			}
		}
	}
	public List<String> getParameters(){
		return parameters_;
	}
	public void addObjective(String obj_name, Objective objective){
		if (objectives_ == null){
			objectives_ = new LinkedHashMap<String,Objective >();
			objectives_.put(obj_name, objective);
		}else{
			objectives_.put(obj_name, objective);
		}
	}
	public Map<String, Objective> getObjectives (){
		return objectives_;
	}
	public void setDecisions(Map<String, Decision> decision){
		decisions_ = decision;
	}
	public Map<String, Decision> getDecisions (){
		return decisions_;
	}
	public void setAlternative(List<Solution>  solutions){
		alternative_ =solutions;
	}
	public List<Solution>  getAlternative (){
		return alternative_;
	}
	public void addAlternative(Solution a){
		if (alternative_ == null){
			alternative_ = new ArrayList<Solution>();
			alternative_.add(a);
		}else{
			alternative_.add(a);
		}
	}
	public String getInfoValueObjectiveName (){
		return infoValueObjectiveName_ ;
	}
	public void setInfoValueObjectiveName (String infoValueObjectiveName){
		infoValueObjectiveName_ = infoValueObjectiveName;
	}
	public void setInfoValueObjective (Objective objective){
			infoValueObjective_ = objective;
	}
	public Objective getInfoValueObjective (){
		return infoValueObjective_ ;
	}
	public void setSimulationNumber(int noOfSimulation) {
		noOfSimulation_ = noOfSimulation;
	}
	public int getSimulationNumber() {
		return noOfSimulation_;
	}
	public void setParams(List<String> param) {
		params_ = param;
	}
	public List<String> getParams() {
		return params_;
	}
	// since we were getting out memory error
	public  void resetSimulationVariables(){
		 Map<String, QualityVariable> qvList = this.getQualityVariables();
		 for (Map.Entry<String, QualityVariable> entry: qvList.entrySet()){
			 if(this.getInfoValueObjectiveName() != null && !this.getInfoValueObjectiveName().equals(entry.getValue().getLabel()) ){
				entry.getValue().setSimData(new LinkedHashMap<Solution, double[]>());
			 }
		 }
	}
	public String getSolutionType (){
		return solutionType_;
	}
	public void setSolutionType (String solutionType){
		solutionType_ =solutionType ;
	}
	
	InfoValueAnalysisResult computeInformationValue(Objective objective, List<Solution> solutions, List<Parameter> params){
		InfoValueAnalysisResult result = new InfoValueAnalysisResult(objective.getLabel(), objective.getIsMinimisation());
		double[][] objSim = objective.getQualityVariable().simulate(solutions);
		// compute evtpi
		double evtpi = InformationAnalysis.evpi(objSim);
		//System.out.println("evtpi for  objective "+  objective.getLabel() +" is "+ evtpi );
		result.setEVTPI(evtpi);
		// compute evppi for each quality variable in params
		for (int i=0; i <params.size(); i++){
			Parameter param = params.get(i) ;
	        double[] paramSim = param.getSimulationData();
	        //System.out.println("evppi  paramters  "+paramSim [0] + ", " + paramSim[1]);
	        //System.out.println("objective sim  "+ objSim [0][0] + ", " + objSim[0][1]);
	        double evppi = InformationAnalysis.evppi(paramSim, objSim);
	        //System.out.println("evppi for  objective "+  objective.getLabel()+ " and parameter " + param.getLabel() +" is "+ evppi );
	        result.addEVPPI(param.getLabel(), evppi);
		}
		return result;
	}
	public static List<Parameter> getParameterList (List<String> paramNames, Model m){
		List<Parameter> parameters = new ArrayList<Parameter>();
		for (int i =0; i < paramNames.size(); i ++){
			QualityVariable qv = m.getQualityVariables().get(paramNames.get(i));
			if (qv.getDefinition() instanceof Parameter &&  !(((Parameter)qv.getDefinition()).getDistribution() instanceof DeterministicDistribution) ){
				Parameter value = (Parameter)qv.getDefinition();
				value.setLabel(qv.getLabel());
				parameters.add(value);
			}else if (qv.getDefinition() instanceof OR_Refinement){
				Map<String, Expression> optionsExpr = ((OR_Refinement)qv.getDefinition()).getDefinition();
				for (Map.Entry<String, Expression> entry: optionsExpr.entrySet()){
					if (entry.getValue() instanceof Parameter && !(((Parameter)entry.getValue()).getDistribution() instanceof DeterministicDistribution)){
						Parameter value = (Parameter)entry.getValue();
						value.setLabel(qv.getLabel() + "[" +entry.getKey() + "]");
						parameters.add(value);
					}
				}
			}
		}
		return parameters;
	}

}
