package radar.model;

import java.util.List;


 abstract class Expression {

	// used in knowing which expr to use in computing evppi and populating simparameter
	boolean isExpresionDistribution_;
	String parameterOption_;
	
	public abstract double [] simulate (Solution s);
	public Expression() {
		
	}
	protected boolean getIsExpresionDistribution(){
		return isExpresionDistribution_;
	}
	protected String getparameterOption(){
		return parameterOption_;
	}
	
	
	public abstract List<Node> addNodeToDecisionGraph(GraphGenerator g, Model model, String qv_name);
	public abstract List<Node> addNodeToVariableGraph(GraphGenerator g, Model model, String qv_name);
	public Node createDOTNode (GraphGenerator g, String label, String shape, String style){
		Node n =null;
		if (!g.getNodeList().containsKey(label)){
			n = new Node (); 
			n.setLabel(label);
			n.setShape(shape);
			n.setStyle(style);
			g.addDOTNodeToList(n);
		}else{
			n= g.getNodeList().get(label);
		}
		return n;
	}
	


}
