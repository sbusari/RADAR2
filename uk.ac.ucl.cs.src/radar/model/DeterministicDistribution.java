package radar.model;

import java.util.ArrayList;
import java.util.List;

class DeterministicDistribution extends Distribution {

	private double value_;
	int N;
	public DeterministicDistribution(double value, int simulation){
		value_ =value;
		N = simulation;
	}
	@Override
	public double[] simulate(Solution s) {
		return  deterministicDistribution(value_, N);
	}
	@Override
	public void checkAcyclicity(Model m) {
	}


}
