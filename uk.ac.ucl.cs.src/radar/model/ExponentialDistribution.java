package radar.model;

import java.util.List;

class ExponentialDistribution extends Distribution {

	private double mean_ ;
	int N;
	public ExponentialDistribution (){
		
	}
	public ExponentialDistribution (double mean,int simulation){
		mean_ = mean;
		N = simulation;
	}
	double [] simulate (){
		return  exponentialDistribution(mean_, N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  exponentialDistribution(mean_, N);
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
