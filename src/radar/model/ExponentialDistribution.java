package radar.model;

class ExponentialDistribution extends Distribution {

	private double mean_ ;
	int N;
	public ExponentialDistribution (){
		
	}
	public ExponentialDistribution (double mean,int simulation){
		mean_ = mean;
		N = simulation;
	}
	double [] simulate (){
		return  exponentialDistribution(mean_, N);
	}
	@Override
	public double[] simulate(Alternative s) {
		isExpresionDistribution_ = true;
		return  exponentialDistribution(mean_, N);
	}
}
