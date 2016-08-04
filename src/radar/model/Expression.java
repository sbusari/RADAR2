package radar.model;

import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;


public abstract class Expression {

	// used in knowing which expr to use in computing evppi and populating simparameter
	boolean isExpresionDistribution_;
	String parameterOption_;
	public abstract double [] simulate (Alternative s);
	public Expression() {
		
	}
	protected boolean getIsExpresionDistribution(){
		return isExpresionDistribution_;
	}
	protected String getparameterOption(){
		return parameterOption_;
	}
	public abstract List<Node> addNodeToGraph (Graph g, Model model, String qv_name, Map<String, Node> cache);
	
	public Node createNode (Graph g, String node_name, String node_type, String node_value, Map<String, Node> cache){
		Node n =null;;
		if (!cache.containsKey(node_name)){
			n = g.addNode();
			n.set("id", node_name);
			n.set("nodeType", node_type);
			n.set("nodeValue", node_value);
			cache.put(node_name, n);
		}else{
			n= cache.get(node_name);
		}
		return n;
	}
	

}
