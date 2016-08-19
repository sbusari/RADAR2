package radar.model;

import java.util.Arrays;

class Percentile extends Statistic {

    private int percentile; // should be between 0 and 100
    Identifier varName_;
    public Percentile(){}
    Percentile( int p){
        percentile = p;
    }
	public void setVarName (Identifier varName){
		varName_ =varName;
	}
	public Identifier getVarName (){
		return varName_;
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double[] simData = var.getSimData(s);
        // 1. sort the simulation data
        Arrays.sort(simData);
        // 2. return the value in the sorted array
        // such that `percentile`% of value are below the returned value
        int position = (int) simData.length * percentile/100;
        return simData[position];
	}

}
