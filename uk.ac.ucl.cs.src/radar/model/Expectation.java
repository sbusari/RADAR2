package radar.model;

import radar.utilities.Statistics;

class Expectation extends Statistic {
	public Expectation() {
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		return new Statistics(simData).computeMean();
	}

}
