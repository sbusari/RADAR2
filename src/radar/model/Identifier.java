package radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;

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
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name,Map<String, Node> cache) {
		List<Node> results = new ArrayList<Node>();
		Node id = createNode (g, id_,"Identifier", id_, cache);
		results.add(id);
		Map<String, QualityVariable> qvList = model.getQualityVariables();
		QualityVariable qv = qvList.get(id_);
		List<Node> children = qv.addNodeToGraph(g,model,id_, cache);
		if (children != null &&  children.size() > 0){
			for (int i =0 ; i <  children.size() ; i ++){
				g.addEdge(id, children.get(i));
			}
		}
		return results;
	}
}
