package radar.model;

class NormalDistribution extends Distribution{

	private double mean_, sd_;
	int N;
	public NormalDistribution(){
		
	}
	public NormalDistribution(double mean, double sd, int simulation){
		mean_ =mean;
		sd_ =sd;
		N= simulation;
	}
	double [] simulate (){
		return  normalDistribution(mean_, sd_, N);
	}
	@Override
	public double[] simulate(Alternative s) {
		return  normalCIDistribution(mean_,sd_, N);
	}
}
