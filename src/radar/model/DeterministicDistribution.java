package radar.model;

import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;

class DeterministicDistribution extends Distribution {

	private double value_;
	int N;
	public DeterministicDistribution(double value, int simulation){
		value_ =value;
		N = simulation;
	}
	@Override
	public double[] simulate(Alternative s) {
		return  deterministicDistribution(value_, N);
	}
	@Override
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name, Map<String, Node> cache) {
		return null;
	}

}
