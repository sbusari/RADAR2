package uk.ac.ucl.cs.radar.model;

import java.util.List;

class GeometricDistribution extends Distribution {
	private double prob_ ;
	int N;
	public GeometricDistribution (){
		
	}
	public GeometricDistribution (double prob,int simulation){
		prob_ = prob;
		N = simulation;
	}
	double [] simulate (){
		return  geometricDistribution(prob_, N);
	}
	
	@Override
	public double[] simulate(Solution s) {
		isExpresionDistribution_ = true;
		return  geometricDistribution(prob_, N);
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
