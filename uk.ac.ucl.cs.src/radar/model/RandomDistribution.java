package radar.model;
class RandomDistribution extends Distribution {
	int N;
	public RandomDistribution (){
		
	}
	public RandomDistribution (int nbr_simulation){
		N = nbr_simulation;
	}
	double [] simulate (){
		return  randomDistribution( N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  randomDistribution(N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
}
