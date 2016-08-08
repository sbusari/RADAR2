package uk.ac.ucl.cs.radar.model;

import uk.ac.ucl.cs.radar.utilities.Statistics;

class BooleanProbability extends Statistic {

	public BooleanProbability() {
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		//s.setObjectiveSimData(simData);
		return new Statistics(simData).computeMean();
	}

}
