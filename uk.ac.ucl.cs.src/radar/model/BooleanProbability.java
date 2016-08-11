package radar.model;

import radar.utilities.Statistics;

class BooleanProbability extends Statistic {
	Identifier varName_;
	public void setVarName (Identifier varName){
		varName_ =varName;
	}
	public Identifier getVarName (){
		return varName_;
	}
	public BooleanProbability() {
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		return new Statistics(simData).computeMean();
	}

}
