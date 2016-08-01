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
	private Map<String, double[]> simParameters_;
	private Map<String, Decision> decisionsBeforeVar;
	private Map<String, Decision> decisionsAfterVar;
	public QualityVariable(){}
	public QualityVariable (QualityVariable qv){
		label_ = qv.getLabel();
		definition_ = qv.getDefinition();
		simData_ = new LinkedHashMap<Alternative, double[]>();
		if (qv.getSimData() != null){
			for (Map.Entry<Alternative, double[]> entry : qv.getSimData().entrySet()){
				simData_.put(entry.getKey(), entry.getValue());
			}
		}
		simParameters_ = new LinkedHashMap<String, double[]>();
		if (qv.getParameterSimData() != null){
			for (Map.Entry<String, double[]> entry : qv.getParameterSimData().entrySet()){
				simParameters_.put(entry.getKey(), entry.getValue());
			}
		}
		
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

	private boolean alreadySimulatedAlternative (Alternative a){
		boolean result = false;
		if (simData_ != null && a.getSelection() != null){
			// they have equal selection and refer to the same objective function.
			for (Map.Entry<Alternative, double []> entry:simData_.entrySet() ){
				if (entry.getKey().hashCode() == a.hashCode() 
						&& entry.getKey().getCurrentSimulatedObjective().getLabel().equals(a.getCurrentSimulatedObjective().getLabel())){
					return true;
				}
			}
		}
		return result;
	}
	@Override
	public List<Node> createDependecyGraph(Graph g, Model model, String qv_name) {
		Node qv_node = g.addNode();
		qv_node.set("id", label_);
		qv_node.set("nodeType", "QualityVariable");
		qv_node.set("nodeValue", label_);
		List<Node> results = new ArrayList<Node>();
		results.add(qv_node);
		List<Node> children = definition_.createDependecyGraph(g,model,qv_name);
		if (children != null &&  children.size() > 0){
			for (int i =0 ; i <  children.size() ; i ++){
				g.addEdge(qv_node, children.get(i));
			}
		}
		return results;
	}
	public double [] simulate (Alternative s){
		//System.out.println("entered quality variable " + label_);
		double [] simdata = null;
		if (label_.equals("TrueAlertRate")){
			System.out.print("TrueAlertRate");
		}
		Alternative solutionCopy = new Alternative (s);
		Alternative localSolution =  subSolution(solutionCopy);
		if (simData_ == null ){
			simData_ = new HashMap<Alternative, double[]> ();
			double [] sim = definition_.simulate(localSolution);
			simData_.put(localSolution, sim);
			simdata=sim ;
		}
		else{
			if ( !alreadySimulatedAlternative(localSolution) || simData_.get(localSolution) == null){
				double [] sim = definition_.simulate(localSolution);
				simData_.put(localSolution, sim);
				simdata=sim ;
			}else{
				simdata = simData_.get(localSolution);
			}
		}
		// also check that the current obj is the objective to be used in information analysis.
		if (definition_.getIsExpresionDistribution() && solutionCopy.getSemanticModel().getParameters() != null &&  solutionCopy.getSemanticModel().getParameters().contains(label_)
				&& solutionCopy.getInformationValueObjective() != null && solutionCopy.getInformationValueObjective().getQualityVariable().getLabel().equals( solutionCopy.getInfoValueObjectiveName())){
			if (simParameters_ == null){
				simParameters_ = new LinkedHashMap<String, double[]>();
				addParameterDistributions(simParameters_,simdata);
			}else{
				addParameterDistributions(simParameters_,simdata);
			}
		}
		return simdata;
	}
	private void addParameterDistributions (Map<String, double[]> simParameters, double [] simdata){
		if (definition_.getparameterOption() != null && StringUtils.isNoneEmpty(definition_.getparameterOption())){
			simParameters.put(label_ + "[" + definition_.getparameterOption() +"]",simdata);
		}else{
			simParameters.put(label_ ,simdata);
		}
	}
	private Alternative subSolution (Alternative s){
		Alternative subsolution = new Alternative();
		subsolution.setGlobalSelection(s.getGlobalSelection());
		// set gobal selection and use it to populate a subselection.
		if (decisionsAfterVar != null){
			for (Map.Entry<String, Decision> entry:decisionsAfterVar.entrySet()){
				Decision d = entry.getValue();
				// instead, use the global selection to get options cos selectio at times is null.
				String option = s.getOptionFromSelction(d);
				if (option != "" && StringUtils.isNotEmpty(option)){
					subsolution.addDecision(d,option);
				}
				
			}
		}
		subsolution.setInfoValueObjectiveName(s.getInfoValueObjectiveName());
		subsolution.setInformationValueObjective(s.getInformationValueObjective());
		subsolution.setCurrentSimulatedObjective(s.getCurrentSimulatedObjective());
		subsolution.setSemanticModel(s.getSemanticModel());
		return subsolution;
	}
	public Map<Alternative, double[]> getSimData(){
		return simData_;
	}
	public void setSimData(Map<Alternative, double[]> simdata){
		simData_ =simdata;
	}
	public Map<String, double[]> getParameterSimData(){
		return simParameters_;
	}



	
}
