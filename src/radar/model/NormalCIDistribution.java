package radar.model;

import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;

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
	public double[] simulate(Alternative s) {
	
		isExpresionDistribution_ = true;
		return  normalCIDistribution(a_,b_, N);
	}
	@Override
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name, Map<String, Node> cache) {
		return null;
	}
}
