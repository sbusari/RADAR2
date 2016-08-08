package uk.ac.ucl.cs.radar.model;

import java.util.List;

class NormalCIDistribution extends Distribution {

	private double a_, b_;
	int N;
	public NormalCIDistribution(){}
	public NormalCIDistribution(double a, double b, int simulation){
		a_ =a;
		b_ =b;
		N= simulation;
	}
	double [] simulate (){
		return  normalCIDistribution(a_,b_, N);
	}
	@Override
	public double[] simulate(Solution s) {
	
		isExpresionDistribution_ = true;
		return  normalCIDistribution(a_,b_, N);
	}
	@Override
	public List<Node> addNodeToVariableGraph(GraphGenerator g, Model model,
			String qv_name) {
		return null;
	}
	@Override
	public List<Node> addNodeToDecisionGraph(GraphGenerator g, Model model,
			String qv_name) {
		// TODO Auto-generated method stub
		return null;
	}
}
