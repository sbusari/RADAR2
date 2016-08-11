package radar.model;

import radar.utilities.Statistics;

class Expectation extends Statistic {
	Identifier varName_;
	public void setVarName (Identifier varName){
		varName_ =varName;
	}
	public Identifier getVarName (){
		return varName_;
	}
	public Expectation() {
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		return new Statistics(simData).computeMean();
	}

}
