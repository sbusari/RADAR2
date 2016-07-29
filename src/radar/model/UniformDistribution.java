package radar.model;

class UniformDistribution extends Distribution {

	private double lower_, upper_;
	int N;
	public UniformDistribution (){
		
	}
	public UniformDistribution (double lower, double upper, int simulation){
		lower_ = lower;
		upper_=upper;
		N = simulation;
	}
	@Override
	public double[] simulate(Alternative s) {
		return  uniformDistribution(lower_,upper_, N);
	}
}
