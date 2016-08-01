package radar.model;

import java.util.List;

import prefuse.data.Graph;
import prefuse.data.Node;

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
	public double[] simulate(Alternative s) {
		isExpresionDistribution_ = true;
		return  randomDistribution(N);
	}
	@Override
	public List<Node> createDependecyGraph(Graph g, Model model, String qv_name) {
		// just return null for distributions
		return null;
	}
}
