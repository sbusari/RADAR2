package radar.model;

import java.util.List;

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
	public double[] simulate(Solution s) {
		return  geometricDistribution(prob_, N);
	}
}
