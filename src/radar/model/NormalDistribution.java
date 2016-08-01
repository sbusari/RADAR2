package radar.model;

import java.util.List;

import prefuse.data.Graph;
import prefuse.data.Node;

class NormalDistribution extends Distribution{

	private double mean_, sd_;
	int N;
	public NormalDistribution(){
		
	}
	public NormalDistribution(int simulation){
		
	}
	public NormalDistribution(double mean, double sd, int simulation){
		mean_ =mean;
		sd_ =sd;
		N= simulation;
	}
	public double [] simulate (){
		return  normalDistribution(mean_, sd_, N);
	}
	@Override
	public double[] simulate(Alternative s) {
		isExpresionDistribution_ = true;
		return  normalCIDistribution(mean_,sd_, N);
	}
	@Override
	public List<Node> createDependecyGraph(Graph g, Model model, String qv_name) {
		// just return null for distributions
		return null;
	}
}
