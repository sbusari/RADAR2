package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class QualityVariable extends ArithmeticExpression {

	private String label_;
	private Expression definition_;
	private Map<Solution, double[]> simData_;
	public QualityVariable(){
		simData_ = new LinkedHashMap<Solution, double[]>();
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
		return definition_.simulate(s);
	}
	public Map<Solution, double[]> getSimData(){
		return simData_;
	}
	public void setSimData(Map<Solution, double[]> simdata){
		simData_ =simdata;
	}





	
}
