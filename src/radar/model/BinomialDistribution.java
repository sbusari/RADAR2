package radar.model;

import java.util.List;

import prefuse.data.Graph;
import prefuse.data.Node;

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
	public double[] simulate(Alternative s) {
		isExpresionDistribution_ = true;
		return  binomialDistribution(trials_, prob_, N);
	}
	@Override
	public List<Node> createDependecyGraph(Graph g, Model model, String qv_name) {
		// TODO Auto-generated method stub
		return null;
	}
}
