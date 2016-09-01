package radar.model;
 abstract class Statistic implements ModelVisitorElement {
	ArithmeticExpression expression_;
	/**
	 * @return definition an arithmetic expression that defines a statistic.
	 */
	public ArithmeticExpression getObjExpression(){
		return expression_;
	}
	/**
	 * Sets the arithemetic definition of a statistic.
	 * @param expression an arithmetic expression that defines a statistic.
	 */
	public void setObjExpression(ArithmeticExpression expression){
		expression_ = expression;
	}
	/**
	 * Visits the objective statistic's definition to generate the AND/OR variable dependency graph.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	public void accept(ModelVisitor visitor, Model m) {
		expression_.accept(visitor,m);
		visitor.visit(this);
	}
	abstract double evaluate (Solution s, QualityVariable var);
}
