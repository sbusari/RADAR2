package radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import radar.plot.goal.graph.Graph;
import radar.plot.goal.graph.Node;

class Identifier extends Expression {
	private String id_;
	public void setID (String id){
		id_ = id;
	}
	public String  getID (){
		return id_;
	}
	@Override
	public double[] simulate(Alternative s) {
		Map<String, QualityVariable> qvList = s.getSemanticModel().getQualityVariables();
		QualityVariable qv = qvList.get(id_);
		if (qv ==null){
			throw new RuntimeException ("Quality variable " + id_ + " is not defined in the model.");
		}
		double [] result = qv.simulate(s);
		return result;
	}
	@Override
	public List<Node> addDOTNodeToDecisionGraph(Graph g, Model model,String qv_name) {
		Map<String, QualityVariable> qvList = model.getQualityVariables();
		QualityVariable qv = qvList.get(id_);
		List<Node> children = qv.addDOTNodeToDecisionGraph(g,model,id_.replaceAll(" ", "_"));
		return children;
	}


	@Override
	public List<Node> addDOTNodeToGraph(Graph g, Model model,
			String qv_name) {
		List<Node> results = new ArrayList<Node>();
		Node id = createDOTNode (g, id_,  "box", "rounded");
		results.add(id);
		Map<String, QualityVariable> qvList = model.getQualityVariables();
		QualityVariable qv = qvList.get(id_);
		List<Node> children = qv.addDOTNodeToGraph(g,model,id_);
		if (children != null &&  children.size() > 0){
			for (int i =0 ; i <  children.size() ; i ++){
				g.addEdge(children.get(i).getLabel(), id.getLabel());
			}
		}
		return results;
	}





}
