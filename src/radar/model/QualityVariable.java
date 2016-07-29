package radar.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QualityVariable extends ArithmeticExpression {

	private String label_;
	private Expression definition_;
	private Map<Alternative, double[]> simData_;
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

	public double [] simulate (Alternative s){
		double [] simdata = null;
		Alternative localSolution = subSolution(s);
		if (simData_ == null ){
			simData_ = new HashMap<Alternative, double[]> ();
			double [] sim = definition_.simulate(localSolution);
			simData_.put(localSolution, sim);
			simdata=sim ;
		}
		else if (!simData_.containsKey(localSolution)){
			//simData_ = new HashMap<Alternative, double[]> ();
			double [] sim = definition_.simulate(s);
			simData_.put(localSolution, sim);
			simdata=sim ;
		}else{
			return simData_.get(localSolution);
		}
		return simdata;
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
		subsolution.setSemanticModel(s.getSemanticModel());
		return subsolution;
	}
	public Map<Alternative, double[]> getSimData(){
		return simData_;
	}


	
}
