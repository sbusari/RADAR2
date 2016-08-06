package radar.model;

import java.util.List;
import radar.plot.goal.graph.Graph;
import radar.plot.goal.graph.Node;

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
	public List<Node> addDOTNodeToGraph(Graph g, Model model,
			String qv_name) {
		return null;
	}
	@Override
	public List<Node> addDOTNodeToDecisionGraph(Graph g, Model model,
			String qv_name) {
		return null;
	}
}
