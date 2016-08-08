package radar.model;

import java.util.List;


 abstract class Expression {

	public abstract double [] simulate (Solution s);
	public Expression() {}
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
