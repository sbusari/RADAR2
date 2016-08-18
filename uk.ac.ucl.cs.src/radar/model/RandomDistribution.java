package radar.model;

import java.util.ArrayList;
import java.util.List;

class RandomDistribution extends Distribution {
	int N;
	public RandomDistribution (){
		
	}
	public RandomDistribution (int simulation){
		N = simulation;
	}
	double [] simulate (){
		return  randomDistribution( N);
	}
	@Override
	public double[] simulate(Solution s) {
		return  randomDistribution(N);
	}
	@Override
	public void checkAcyclicity(Model m) {
	}
}
