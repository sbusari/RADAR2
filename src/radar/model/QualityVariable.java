package radar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import prefuse.data.Graph;
import prefuse.data.Node;

public class QualityVariable extends ArithmeticExpression {

	private String label_;
	private Expression definition_;
	private Map<Alternative, double[]> simData_;
	private Map<Alternative, double[]> simParameters_;
	private Map<String, Decision> decisionsBeforeVar;
	private Map<String, Decision> decisionsAfterVar;
	public QualityVariable(){
		simData_ = new LinkedHashMap<Alternative, double[]>();
		simParameters_ = new LinkedHashMap<Alternative, double[]>();
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
	@Override
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name, Map<String, Node> cache) {
		if (label_.equals("ContinuousAlertThreshold")){
			System.out.print("ContinuousAlertThreshold");
		}
		List<Node> results = new ArrayList<Node>();
		Node qv_node = null;
		qv_node =createNode (g, label_,"QualityVariable", label_ ,cache);
		List<Node> children = definition_.addNodeToGraph(g,model,qv_name,cache);
		if (children != null &&  children.size() > 0){
			for (int i =0 ; i <  children.size() ; i ++){
				g.addEdge(qv_node, children.get(i));
			}
		}
		results.add(qv_node);
		return results;
	}
	public double [] simulate (Alternative s){
		double [] simdata = null;
		Alternative localSolution = new Alternative(s);
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
	private void addParameterDistributions (Map<Alternative, double[]> simParameters, Alternative s,  double [] simdata){
		if (definition_.getparameterOption() != null && StringUtils.isNoneEmpty(definition_.getparameterOption())){
			// set the parameter, added [option] incase the parameter depends on deciion.
			s.setParameter( s.selectionToString() + ", with model parameter " +  label_ + "[" + definition_.getparameterOption() +"]");
			simParameters.put(s,simdata);
		}else{
			s.setParameter( s.selectionToString() + ", with model parameter " +  label_);
			simParameters.put(s ,simdata);
		}
	}
	private Alternative subSolution (Alternative s){
		Alternative subsolution = new Alternative();
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
	public Map<Alternative, double[]> getSimData(){
		return simData_;
	}
	public void setSimData(Map<Alternative, double[]> simdata){
		simData_ =simdata;
	}
	public Map<Alternative, double[]> getParameterSimData(){
		return simParameters_;
	}



	
}
