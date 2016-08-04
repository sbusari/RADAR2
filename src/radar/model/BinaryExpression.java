package radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;


class BinaryExpression extends ArithmeticExpression {
	private BinaryOperator bop_;
	private Expression leftExpr_, rightExpr_;
	public void setBinaryOperator (BinaryOperator bop){
		bop_ = bop;
	}
	public BinaryOperator getBinaryOperator(){
		return bop_;
	}
	public void setLeftExpression (Expression expr){
		leftExpr_ = expr;
	}
	public Expression getLeftExpression (){
		return leftExpr_;
	}
	public void setRightExpression (Expression expr){
		rightExpr_ = expr;
	}
	public Expression getRightExpression (){
		return rightExpr_;
	}
	@Override
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name,Map<String, Node> cache) {
		List<Node> result = new ArrayList<Node>();
		//String joinerNodeName = qv_name + System.currentTimeMillis();
		Node Joiner = null;  
		List<Node> leftChild = null;
		List<Node> rightChild = null;
		boolean isleftExprANumber = (leftExpr_ instanceof Number )? true :false;
		boolean isrightExprANumber =(rightExpr_ instanceof Number )? true :false;
		// even when a no exits in a binary expr,we do not want a number o show in the graph, hence the check
		// when there is no no in the expr, we create a joiner node fro the expr terms
		if (isleftExprANumber == false && isrightExprANumber ==false){
			Joiner = g.addNode(); //createNode (g, "","Joiner", "", cache);
			Joiner.set("id", "");
			Joiner.set("nodeType", "Joiner");
			Joiner.set("nodeValue", "");
		}
		leftChild = leftExpr_.addNodeToGraph(g, model, qv_name, cache);
		rightChild = rightExpr_.addNodeToGraph(g, model, qv_name, cache);
		if (isleftExprANumber == false &&  isrightExprANumber == false){
			if (leftChild != null && leftChild.size() > 0){
				for (int i =0; i <leftChild.size(); i++ ){
					g.addEdge(Joiner, leftChild.get(i));
				}
			}
			if (rightChild != null && rightChild.size() > 0){
				for (int i =0; i <rightChild.size(); i++ ){
					g.addEdge(Joiner, rightChild.get(i));
				}
			}
			result.add(Joiner);
		}else{
			if (leftChild != null){
				result.addAll(leftChild);
			}
			if (rightChild != null){
				result.addAll(rightChild);
			}
		}
		
		
		return result;
	}
	@Override
	public double[] simulate(Alternative s) {
		double [] leftSim = leftExpr_.simulate(s);
		double [] rightSim = rightExpr_.simulate(s);
		double [] combinedSim = new double [leftSim.length];
		for (int i =0 ; i < leftSim.length; i ++){
			switch (bop_.getBinaryOperatorValue()){
				case "+" : {
					combinedSim[i] = leftSim[i] + rightSim[i];
				}; break;
				case "-" : {
					combinedSim[i] = leftSim[i] - rightSim[i];
				};break;
				case "*" : {
					combinedSim[i] = leftSim[i] * rightSim[i];
				};break;
				case "/" : {
					combinedSim[i] = leftSim[i] / rightSim[i];
				};break;
				case "||": {
					combinedSim[i] = Math.max(leftSim[i] , rightSim[i]);
				};break;
				case "&&": {
					combinedSim[i] = leftSim[i] * rightSim[i];
				};break;
				case "<": {
					combinedSim[i] = leftSim[i] < rightSim[i] ? 1 : 0;
				};break;
				case ">": {
					combinedSim[i] = leftSim[i] > rightSim[i] ? 1 : 0;
				};break;
				case "<=": {
					combinedSim[i] = leftSim[i] <= rightSim[i] ? 1 : 0;
				};break;
				case ">=": {
					combinedSim[i] = leftSim[i] <= rightSim[i] ? 1 : 0;
				};break;
				default : combinedSim[i] = leftSim[i] + rightSim[i];
			}
		}
		return combinedSim;
	}
	
}
