package radar.model;

import java.util.List;

import prefuse.data.Graph;
import prefuse.data.Node;

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
	public double[] simulate(Alternative s) {
		isExpresionDistribution_ = true;
		return  exponentialDistribution(mean_, N);
	}
	@Override
	public List<Node> createDependecyGraph(Graph g, Model model, String qv_name) {
		// just return null for distributions
		return null;
	}
}
