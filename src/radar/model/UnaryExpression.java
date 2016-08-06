package radar.model;

import java.util.ArrayList;
import java.util.List;
import radar.plot.goal.graph.Graph;
import radar.plot.goal.graph.Node;


class UnaryExpression extends Expression {

	private UnaryOperator uop_;
	private Expression expr_;
	public void setUnaryOperator (UnaryOperator uop){
		uop_ = uop;
	}
	public UnaryOperator getUnaryOperator (){
		return uop_;
	}
	public void setExpression (Expression expr){
		expr_ = expr;
	}
	public Expression getExpression (){
		return expr_;
	}
	@Override
	public List<Node> addNodeToVariableGraph(Graph g, Model model,
			String qv_name) {
		List<Node> result = new ArrayList<Node>();
		List<Node> expr = expr_.addNodeToVariableGraph(g, model,qv_name);
		result.addAll(expr);
		return result;
	}
	@Override
	public List<Node> addNodeToDecisionGraph(Graph g, Model model,
			String qv_name) {
		List<Node> result = new ArrayList<Node>();
		List<Node> expr = expr_.addNodeToDecisionGraph(g, model,qv_name);
		result.addAll(expr);
		return result;
	}
	@Override
	public double[] simulate(Alternative s) {
		double [] expr = expr_.simulate(s);
		if (uop_ != null){
			for (int i =0 ; i < expr.length; i ++){
				switch (uop_.getUnaryOperatorValue()){
					case "+" : {
						expr[i] = expr[i] ;
					}; break;
					case "-" : {
						expr[i] =-1* expr[i];
					}; break;
					case "!" : {
						expr[i] =1 - expr[i];
					}; break;
					default : expr[i] = expr[i];
				}
			}
		}
		return expr;
	}





}
