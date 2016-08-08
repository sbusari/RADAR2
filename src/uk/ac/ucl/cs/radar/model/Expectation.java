package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.utilities.Statistics;

class Expectation extends Statistic {
	public Expectation() {
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		return new Statistics(simData).computeMean();
	}

}
