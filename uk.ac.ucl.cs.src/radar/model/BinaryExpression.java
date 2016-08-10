package radar.model;

import java.util.ArrayList;
import java.util.List;


class BinaryExpression extends ArithmeticExpression {
	private BinaryOperator bop_;
	private ArithmeticExpression leftExpr_, rightExpr_;
	public void setBinaryOperator (BinaryOperator bop){
		bop_ = bop;
	}
	public BinaryOperator getBinaryOperator(){
		return bop_;
	}
	public void setLeftExpression (ArithmeticExpression expr){
		leftExpr_ = expr;
	}
	public ArithmeticExpression getLeftExpression (){
		return leftExpr_;
	}
	public void setRightExpression (ArithmeticExpression expr){
		rightExpr_ = expr;
	}
	public ArithmeticExpression getRightExpression (){
		return rightExpr_;
	}
	@Override
	public double[] simulate(Solution s) {
		double [] leftSim = leftExpr_.simulate(s);
		double [] rightSim = rightExpr_.simulate(s);
		double [] combinedSim = new double [leftSim.length];
		for (int i =0 ; i < leftSim.length; i ++){
			switch (bop_.getBinaryOperatorValue()){
				case "+" : {
					combinedSim[i] = leftSim[i] + rightSim[i];
				}; break;
				case "-" : {
					combinedSim[i] = leftSim[i] - rightSim[i];
				};break;
				case "*" : {
					combinedSim[i] = leftSim[i] * rightSim[i];
				};break;
				case "/" : {
					combinedSim[i] = leftSim[i] / rightSim[i];
				};break;
				case "||": {
					combinedSim[i] = Math.max(leftSim[i] , rightSim[i]);
				};break;
				case "&&": {
					combinedSim[i] = leftSim[i] * rightSim[i];
				};break;
				case "<": {
					combinedSim[i] = leftSim[i] < rightSim[i] ? 1 : 0;
				};break;
				case ">": {
					combinedSim[i] = leftSim[i] > rightSim[i] ? 1 : 0;
				};break;
				case "<=": {
					combinedSim[i] = leftSim[i] <= rightSim[i] ? 1 : 0;
				};break;
				case ">=": {
					combinedSim[i] = leftSim[i] <= rightSim[i] ? 1 : 0;
				};break;
				default : combinedSim[i] = leftSim[i] + rightSim[i];
			}
		}
		return combinedSim;
	}
	@Override
	List<QualityVariable> getQualityVariable() {
		List<QualityVariable> result = new ArrayList<QualityVariable>();
		List<QualityVariable> leftVars = leftExpr_.getQualityVariable();
		List<QualityVariable> rightVars = rightExpr_.getQualityVariable();
		result.addAll(leftVars);
		result.addAll(rightVars);
		return result;
	}
	@Override
	public QualityVariable getParent() {
		return null;
	}
	@Override
	public void accept(ModelVisitor visitor) {
		this.accept(visitor);
	}
	
}
