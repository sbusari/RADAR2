package radar.model;

import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;

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
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name,Map<String, Node> cache) {
		return null;
	}
}
