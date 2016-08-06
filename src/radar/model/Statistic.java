package radar.model;

public abstract class Statistic {
	Expression expression_;
	public Expression getObjExpression(){
		return expression_;
	}
	public void setObjExpression(Expression expression){
		expression_ = expression;
	}
	abstract double evaluate (Solution s, QualityVariable var);
}
