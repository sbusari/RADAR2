package radar.model;


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
	@Override
	public void  checkAcyclicity(Model m) {
	}
}
