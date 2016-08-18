package radar.model;

import java.util.ArrayList;
import java.util.List;

class NormalDistribution extends Distribution{

	private double mean_, sd_;
	int N;
	public NormalDistribution(){
		
	}
	public NormalDistribution(int simulation){
		
	}
	public NormalDistribution(double mean, double sd, int simulation){
		mean_ =mean;
		sd_ =sd;
		N= simulation;
	}
	public double [] simulate (){
		return  normalDistribution(mean_, sd_, N);
	}
	
	@Override
	public double[] simulate(Solution s) {
		return  normalCIDistribution(mean_,sd_, N);
	}
	@Override
	public void checkAcyclicity(Model m) {
	}
}
