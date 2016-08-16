package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.GeometricDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TriangularDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

import radar.utilities.ConfigSetting;

abstract class Distribution extends Expression {
	List<Expression> distributionParamter_;
	RandomGenerator rg_; 
	QualityVariable parent_;
	public Distribution(){
		rg_ = new JDKRandomGenerator();
		if (ConfigSetting.SEED != 0){
			rg_.setSeed(ConfigSetting.SEED);
		}
	}
	public QualityVariable getParent() {
		return parent_;
	}
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	public void accept(ModelVisitor visitor, Model m) {

	}
	@Override
	public SolutionSet getAllSolutions(Model m){
		Solution s = new Solution();
		//String uniqueParentID = ""+ m.getSolutionCount(); 
		//s.setUniqueID(uniqueParentID);
		SolutionSet result = new SolutionSet();
		result.add(s);
		return result;
	}
	protected double [] deterministicDistribution (double value, int sampleSize){
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
		   sample[i] =value;
		}
		return sample;
	}
	protected double [] randomDistribution (int sampleSize){
			GaussianRandomGenerator generator = new GaussianRandomGenerator(rg_);
			double[] sample = new double[sampleSize];
			for (int i = 0; i < sample.length; ++i) {
			   sample[i] = generator.nextNormalizedDouble();
			}
			return sample;
	 }
	protected double [] normalDistribution ( double mean, double sd, int sampleSize){
		boolean zeros = false;
    	if (mean == 0 && sd == 0){
    		zeros = true;
		}
    	NormalDistribution generator = null;
    	generator = zeros == false?	new NormalDistribution(rg_, mean, sd): null;
    	
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
			if (zeros){
				sample[i] = 0;
			}else{
				sample[i] = generator.sample();
			}
		   
		}
		return sample;
    }
	protected double [] normalCIDistribution ( double a, double b, int sampleSize){
    	boolean zeros = false;
    	if (a == 0 && b == 0){
    		zeros = true;
		}
    	double mean = (a+b)/2;
    	double sd = Math.abs((b - a)/3.29);
    	NormalDistribution generator = null;
    	generator = zeros == false?	new NormalDistribution(rg_, mean, sd): null;
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
			if (zeros){
				sample[i] = 0;
			}else{
				 sample[i] = generator.sample();
			}
		  
		}
		return sample;
 	}
	protected double [] exponentialDistribution ( double mean, int sampleSize){
		ExponentialDistribution generator = new ExponentialDistribution(rg_, mean);
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
		   sample[i] = generator.sample();
		}
		return sample;
    }
    public double [] binomialDistribution ( int trials, double prob, int sampleSize){
		BinomialDistribution generator = new BinomialDistribution(rg_, trials, prob);
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
		   sample[i] = (double)generator.sample();
		}
		return sample;
    }
    protected double [] geometricDistribution ( double prob, int sampleSize){
		GeometricDistribution generator = new GeometricDistribution(rg_, prob);
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
		   sample[i] = (double)generator.sample();
		}
		return sample;
    }
    protected double [] uniformDistribution ( double lower, double upper, int sampleSize){
    	boolean zeros = false;
    	if (lower == 0 && upper == 0){
    		zeros = true;
		}
    	UniformRealDistribution generator =  null;
    	generator = zeros == false?	new UniformRealDistribution(rg_, lower, upper): null;
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
			if (zeros){
				sample[i] = 0;
			}else{
				sample[i] = (double)generator.sample();
			}
		   
		}
		return sample;
 	}
    protected double [] triangularDistribution ( double lower, double mode, double upper, int sampleSize){
    	boolean zeros = false;
    	if (lower == 0 && upper == 0 &&  mode == 0){
    		zeros = true;
		}
		TriangularDistribution generator = null;
		generator = zeros == false?new TriangularDistribution(rg_, lower,mode, upper): null;
		double[] sample = new double[sampleSize];
		for (int i = 0; i < sample.length; ++i) {
			if (zeros){
				sample[i] = 0;
			}else{
				sample[i] = (double)generator.sample();
			}
		 
		}
		return sample;
    }
	    
}
