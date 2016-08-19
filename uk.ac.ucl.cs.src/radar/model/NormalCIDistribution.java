package radar.model;

import java.util.ArrayList;
import java.util.List;

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
	public double[] simulate(Solution s) {
		return  normalCIDistribution(a_,b_, N);
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
}
