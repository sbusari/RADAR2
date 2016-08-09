package radar.model;

import java.util.List;

class UniformDistribution extends Distribution {

	private double lower_, upper_;
	int N;
	public UniformDistribution (){
		
	}
	public UniformDistribution (double lower, double upper, int simulation){
		lower_ = lower;
		upper_=upper;
		N = simulation;
	}
	double [] simulate (){
		return  uniformDistribution(lower_,upper_, N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  uniformDistribution(lower_,upper_, N);
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
