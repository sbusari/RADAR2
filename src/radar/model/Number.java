package radar.model;

import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;

class Number extends ArithmeticExpression {

	private double value_;
	public void setValue (double value){
		value_ = value;
	}
	public double  getValue (){
		return value_;
	}
	@Override
	public double[] simulate(Alternative s) {
		// in the case where a deterministic has expr as argument, we have already handled that during parsing
		// this is just for a binaru expression that has a number.
		int simulationNo = s.getSemanticModel().getSimulationNumber();
		double [] sim = new double [simulationNo];
		for (int i = 0; i < sim.length; ++i) {
			sim[i] =value_;
		}
		return sim;
	}
	@Override
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name, Map<String, Node> cache) {
		return null;
	}
}
