package radar.model;
 abstract class Statistic implements ModelVisitorElement {
	ArithmeticExpression expression_;
	public ArithmeticExpression getObjExpression(){
		return expression_;
	}
	public void setObjExpression(ArithmeticExpression expression){
		expression_ = expression;
	}
	public void accept(ModelVisitor visitor) {
		expression_.accept(visitor);
		this.accept(visitor);
	}
	abstract double evaluate (Solution s, QualityVariable var);
}
