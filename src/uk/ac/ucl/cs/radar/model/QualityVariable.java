package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class QualityVariable extends ArithmeticExpression {

	private String label_;
	private Expression definition_;
	private Map<Solution, double[]> simData_;
	private Map<Solution, double[]> simParameters_;
	private Map<String, Decision> decisionsBeforeVar;
	private Map<String, Decision> decisionsAfterVar;
	public QualityVariable(){
		simData_ = new LinkedHashMap<Solution, double[]>();
		simParameters_ = new LinkedHashMap<Solution, double[]>();
		decisionsBeforeVar = new LinkedHashMap <String, Decision>();
		decisionsAfterVar  = new LinkedHashMap <String, Decision>();
	}
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
	public double[] getSimData(Solution s) {
		return simulate(s);
	}
	public double[][] getSimData(List<Solution> s) {
		double [][] result = new double [s.size()][];
		for (int i =0 ; i < s.size(); i ++){
			result[i] = getSimData (s.get(i));
		}
		return result;
	}
	@Override
	public List<Node> addNodeToVariableGraph(GraphGenerator g, Model model,
			String qv_name) {
		//g.incrementOperatorID();
		List<Node> results = new ArrayList<Node>();
		Node qv_node =createDOTNode (g, label_,"box", "rounded");
		List<Node> children = definition_.addNodeToVariableGraph(g,model,qv_name);
		if (children != null &&  children.size() > 0){
			for (int i =0 ; i <  children.size() ; i ++){
				g.addEdge( children.get(i).getLabel(), qv_node.getLabel());
			}
		}
		results.add(qv_node);
		return results;
	}
	@Override
	public List<Node> addNodeToDecisionGraph(GraphGenerator g, Model model,
			String qv_name) {
		List<Node> children = definition_.addNodeToDecisionGraph(g,model,qv_name.replaceAll(" ", "_"));
		return children;
	}
	double[][] simulate(List<Solution> s) {
		double [][] result = new double [s.size()][];
		for (int i =0 ; i < s.size(); i ++){
			result[i] = simulate(s.get(i));
		}
		return result;
	}

	public double [] simulate (Solution s){
		//System.out.println(s.selectionToString() + " and " + label_ );
		return definition_.simulate(s);
	}
	
	public double [] simulate2 (Solution s){
		double [] simdata = null;
		Solution localSolution = new Solution(s);
		//Alternative localSolution = subSolution(s);
		if (simData_.get(localSolution) == null){
			double [] sim = definition_.simulate(localSolution);
			simData_.put(localSolution, sim);
			simdata=sim ;
		}else{
			simdata = simData_.get(localSolution);
		}
		//used this check getIsObjSimParameterStored when objs refer to the same qv and to prevent one repacing another.
		if (definition_.getIsExpresionDistribution() && s.getSemanticModel().getParameters() != null &&  s.getSemanticModel().getParameters().contains(label_) && s.getIsObjSimParameterStored()){
			addParameterDistributions(simParameters_,localSolution, simdata);
		}
		return simdata;
	}
	private void addParameterDistributions (Map<Solution, double[]> simParameters, Solution s,  double [] simdata){
		if (definition_.getparameterOption() != null && StringUtils.isNoneEmpty(definition_.getparameterOption())){
			// set the parameter, added [option] incase the parameter depends on deciion.
			s.setParameter( s.selectionToString() + ", with model parameter " +  label_ + "[" + definition_.getparameterOption() +"]");
			simParameters.put(s,simdata);
		}else{
			s.setParameter( s.selectionToString() + ", with model parameter " +  label_);
			simParameters.put(s ,simdata);
		}
	}
	private Solution subSolution (Solution s){
		Solution subsolution = new Solution();
		subsolution.setGlobalSelection(s.getGlobalSelection());
		// set gobal selection and use it to populate a subselection.
		if (decisionsAfterVar != null){
			for (Map.Entry<String, Decision> entry:decisionsAfterVar.entrySet()){
				Decision d = entry.getValue();
				String option = s.getOptionFromSelction(d);
				if (option != "" && StringUtils.isNotEmpty(option)){
					subsolution.addDecision(d,option);
				}
			}
		}
		subsolution.setSemanticModel(s.getSemanticModel());
		subsolution.setStoredObjSimParameter(s.getIsObjSimParameterStored());
		subsolution.setInfoValueObjective(s.getInfoValueObjective());
		return subsolution;
	}
	public Map<Solution, double[]> getSimData(){
		return simData_;
	}
	public void setSimData(Map<Solution, double[]> simdata){
		simData_ =simdata;
	}
	public Map<Solution, double[]> getParameterSimData(){
		return simParameters_;
	}






	
}
