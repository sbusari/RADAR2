package radar.model;

import java.util.List;

class DeterministicDistribution extends Distribution {

	private double value_;
	int N;
	public DeterministicDistribution(double value, int simulation){
		value_ =value;
		N = simulation;
	}
	@Override
	public double[] simulate(Solution s) {
		return  deterministicDistribution(value_, N);
	}
	@Override
	public List<Node> addNodeToVariableGraph(GraphGenerator g, Model model,
			String qv_name) {
		return null;
	}
	
	@Override
	public List<Node> addNodeToDecisionGraph(GraphGenerator g, Model model,
			String qv_name) {
		return null;
	}


}
