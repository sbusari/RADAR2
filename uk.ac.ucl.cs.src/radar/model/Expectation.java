package radar.model;

import radar.utilities.Statistics;
/**
 * @author Saheed Busari and Emmanuel Letier
 * This class represents an objective statistic of type expectation.
 */
class Expectation extends Statistic {
	Identifier varName_;
	/**
	 * Sets the the quality variable an objective refers to.
	 * @param varName the quality variable name.
	 */
	public void setVarName (Identifier varName){
		varName_ =varName;
	}
	/**
	 * Returns the the quality variable an objective refers to.
	 * @return the quality variable name an objective refers to.
	 */
	public Identifier getVarName (){
		return varName_;
	}
	/**
	 * Constructors an expectation for an objective statistic.
	 */
	public Expectation() {
	}
	/**
	 * Evaluates a solution through monte-carlo simulation.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @param var is a quality variable an objective refers to i.e a quality variable attached to an objective definition.
	 * @return an simulated objective value.
	 */
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		return new Statistics(simData).computeMean();
	}

}
