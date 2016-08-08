package radar.model;

import radar.utilities.Statistics;

class BooleanProbability extends Statistic {

	public BooleanProbability() {
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		return new Statistics(simData).computeMean();
	}

}
