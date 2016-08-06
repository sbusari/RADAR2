package radar.model;

import java.util.List;
import radar.plot.goal.graph.Graph;
import radar.plot.goal.graph.Node;

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
	public double[] simulate(Alternative s) {
		isExpresionDistribution_ = true;
		return  geometricDistribution(prob_, N);
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
