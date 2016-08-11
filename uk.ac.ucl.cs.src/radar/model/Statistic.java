package radar.model;
 abstract class Statistic implements ModelVisitorElement {
	ArithmeticExpression expression_;
	public ArithmeticExpression getObjExpression(){
		return expression_;
	}
	public void setObjExpression(ArithmeticExpression expression){
		expression_ = expression;
	}
	public void accept(ModelVisitor visitor, Model m) {
		
		expression_.accept(visitor,m);
		//visitor.visit(expression_);
		visitor.visit(this);
		//this.accept(visitor,m);
	}
	abstract double evaluate (Solution s, QualityVariable var);
}
