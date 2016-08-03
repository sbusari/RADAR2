package radar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;

public class Model {
	
	private static Model instance = null;
	public static Model getInstance() {
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
	private List<Alternative> alternative_;
	private List<Objective> infoValueObjective_;
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
	public void setAlternative(List<Alternative>  solutions){
		alternative_ =solutions;
	}
	public List<Alternative>  getAlternative (){
		return alternative_;
	}
	public void addAlternative(Alternative a){
		if (alternative_ == null){
			alternative_ = new ArrayList<Alternative>();
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
	public void setInfoValueObjective (List<Objective> objective){
			infoValueObjective_ = objective;
	}
	public List<Objective> getInfoValueObjective (){
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
				entry.getValue().setSimData(new LinkedHashMap<Alternative, double[]>());
			 }
		 }
	}
	public String getSolutionType (){
		return solutionType_;
	}
	public void setSolutionType (String solutionType){
		solutionType_ =solutionType ;
	}
	
	
	
}
