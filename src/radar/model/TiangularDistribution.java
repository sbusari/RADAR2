package radar.model;

import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;

class TriangularDistribution extends Distribution {

	private double lower_, mode_, upper_;
	int N;
	public TriangularDistribution(){}
	public TriangularDistribution (double lower, double mode, double upper, int simulation){
		lower_ =lower;
		mode_ =mode;
		upper_ = upper;
		N =simulation;
	}
	double [] simulate (){
		return  triangularDistribution(lower_,mode_, upper_, N);
	}
	@Override
	public double[] simulate(Alternative s) {
		isExpresionDistribution_ = true;
		return  triangularDistribution(lower_,mode_, upper_, N);
	}
	@Override
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name,Map<String, Node> cache) {
		return null;
	}
	
}
