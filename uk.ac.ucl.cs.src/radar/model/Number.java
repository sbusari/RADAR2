package radar.model;

import java.util.List;

class Number extends ArithmeticExpression {

	private double value_;
	public void setValue (double value){
		value_ = value;
	}
	public double  getValue (){
		return value_;
	}
	
	@Override
	public double[] simulate(Solution s) {
		// in the case where a deterministic has expr as argument, we have already handled that during parsing
		// this is just for a binary expression that has a number.
		int simulationNo = s.getSemanticModel().getNbr_Simulation();
		double [] sim = new double [simulationNo];
		for (int i = 0; i < sim.length; ++i) {
			sim[i] =value_;
		}
		return sim;
	}
	@Override
	public List<Node> addNodeToVariableGraph(Graph g, Model model,
			String qv_name) {
		return null;
	}
	@Override
	public List<Node> addNodeToDecisionGraph(Graph g, Model model,
			String qv_name) {
		return null;
	}
}
