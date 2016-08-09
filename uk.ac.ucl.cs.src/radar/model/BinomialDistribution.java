package radar.model;

import java.util.List;

class BinomialDistribution extends Distribution {
	private double prob_; 
	private int trials_ ;
	int N;
	public BinomialDistribution (){
		
	}
	public BinomialDistribution (int trials, double prob,int simulation){
		prob_ = prob;
		trials_ =trials;
		N = simulation;
	}
	double [] simulate (){
		return  binomialDistribution(trials_, prob_, N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  binomialDistribution(trials_, prob_, N);
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
