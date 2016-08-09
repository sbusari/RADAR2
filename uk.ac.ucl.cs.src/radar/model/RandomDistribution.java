package radar.model;

import java.util.List;

class RandomDistribution extends Distribution {
	int N;
	public RandomDistribution (){
		
	}
	public RandomDistribution (int simulation){
		N = simulation;
	}
	double [] simulate (){
		return  randomDistribution( N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  randomDistribution(N);
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
