package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


class UnaryExpression extends ArithmeticExpression {

	private UnaryOperator uop_;
	private ArithmeticExpression expr_;
	public void setUnaryOperator (UnaryOperator uop){
		uop_ = uop;
	}
	public UnaryOperator getUnaryOperator (){
		return uop_;
	}
	public void setExpression (ArithmeticExpression expr){
		expr_ = expr;
	}
	public ArithmeticExpression getExpression (){
		return expr_;
	}
	@Override
	public double[] simulate(Solution s) {
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
					case "!" : {
						expr[i] =1 - expr[i];
					}; break;
					default : expr[i] = expr[i];
				}
			}
		}
		return expr;
	}
	@Override
	List<QualityVariable> getQualityVariable() {
		return expr_.getQualityVariable();
	}
	@Override
	public QualityVariable getParent() {
		return parent_;
	}
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		expr_.accept(visitor, m);
		
	}
	@Override
	public List<Solution> getAllSolutions(Model m) {
		List<Solution> results = new ArrayList<Solution>();
		List<Solution> solutions= expr_.getAllSolutions(m);
		results.addAll(solutions);
		return results;
	}





}
