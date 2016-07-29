package radar.model;

class DeterministicDistribution extends Distribution {

	private double value_;
	int N;
	public DeterministicDistribution(double value){
		value_ =value;
	}
	@Override
	public double[] simulate(Alternative s) {
		return  deterministicDistribution(value_, N);
	}

}
