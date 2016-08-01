package radar.model;

import java.util.List;

import prefuse.data.Graph;
import prefuse.data.Node;


public abstract class Expression {

	// used in knowing which expr to use in computing evppi and populating simparameter
	boolean isExpresionDistribution_;
	String parameterOption_;
	public abstract double [] simulate (Alternative s);
	public Expression() {
		
	}
	public boolean getIsExpresionDistribution(){
		return isExpresionDistribution_;
	}
	public String getparameterOption(){
		return parameterOption_;
	}
	public abstract List<Node> createDependecyGraph (Graph g, Model model, String qv_name);
	public Node addNode (Graph g, String node_name, String node_type, String node_value){
		Node n = g.addNode();
		n.set("id", node_name);
		n.set("nodeType", node_type);
		n.set("nodeValue", node_value);
		return n;
	}
	

}
