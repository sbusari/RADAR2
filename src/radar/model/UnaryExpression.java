package radar.model;


class UnaryExpression extends Expression {

	private UnaryOperator uop_;
	private Expression expr_;
	public void setUnaryOperator (UnaryOperator uop){
		uop_ = uop;
	}
	public UnaryOperator getUnaryOperator (){
		return uop_;
	}
	public void setExpression (Expression expr){
		expr_ = expr;
	}
	public Expression getExpression (){
		return expr_;
	}
	@Override
	public double[] simulate(Alternative s) {
		double [] expr = expr_.simulate(s);
		if (uop_ != null){
			for (int i =0 ; i < expr.length; i ++){
				switch (uop_.getUnaryOperatorValue()){
					case "+" : {
						expr[i] = expr[i] ;
					}; break;
					case "-" : {
						expr[i] =-1* expr[i];
					}; break;
					default : expr[i] = expr[i];
				}
			}
		}
		return expr;
	}
}
