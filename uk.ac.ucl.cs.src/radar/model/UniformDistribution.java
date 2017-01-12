package radar.model;

import radar.exception.ParameterDistributionException;

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
	double [] simulate (){
		return  uniformDistribution(lower_,upper_, N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  uniformDistribution(lower_,upper_, N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
	@Override
	public double getParamExpressionValue(Model m)
			throws ParameterDistributionException {
		throw new RuntimeException ("Uniform distribution cannot be used as an argument for another distribution.");
	}

}
