package radar.model;

import java.util.List;
import radar.plot.goal.graph.Graph;
import radar.plot.goal.graph.Node;

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
	public List<Node> addNodeToVariableGraph(Graph g, Model model,
			String qv_name) {
		return null;
	}
	@Override
	public List<Node> addNodeToDecisionGraph(Graph g, Model model,
			String qv_name) {
		// TODO Auto-generated method stub
		return null;
	}
}
