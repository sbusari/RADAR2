package radar.model;

import java.util.ArrayList;
import java.util.List;

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
	public List<Node> createDependecyGraph(Graph g, Model model,String qv_name) {
		List<Node> result = new ArrayList<Node>();
		Node exprJoiner = addNode(g, "ExpressionJoiner","ExpressionJoiner","" );
		List<Node> leftChild = leftExpr_.createDependecyGraph(g, model, qv_name);
		List<Node> rightChild = rightExpr_.createDependecyGraph(g, model, qv_name);
		if (leftChild != null &&  leftChild.size() > 0){
			for (int i =0 ; i <  leftChild.size() ; i ++){
				g.addEdge(exprJoiner, leftChild.get(i));
			}
		}
		if (rightChild != null &&  rightChild.size() > 0){
			for (int i =0 ; i <  rightChild.size() ; i ++){
				g.addEdge(exprJoiner, rightChild.get(i));
			}
		}
		result.add(exprJoiner);
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
