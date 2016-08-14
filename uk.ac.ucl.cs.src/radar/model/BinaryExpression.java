package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
		return parent_;
	}
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		leftExpr_.accept(visitor, m);
		rightExpr_.accept(visitor, m);
	}
	@Override
	public SolutionSet getAllSolutions(Model m) {
		SolutionSet results = new SolutionSet();
		SolutionSet leftSolution= leftExpr_.getAllSolutions(m);
		SolutionSet rightSolution= rightExpr_.getAllSolutions(m);
		results.addAll(leftSolution);
		results.addAll (rightSolution);
		return results;
	}
	
	SolutionSet combinedBinarySolutions (SolutionSet left, SolutionSet right){
		SolutionSet result = new SolutionSet();
		List<Solution> combinedSolutions = new ArrayList<Solution>();
		for (int i = 0 ; i <left.size() ; i++ ){
			Solution aLeftSolution = left.get(i);
			for (int j=0; j < right.size(); j++){
				Solution aRightSolution = right.get(j);
				for (Map.Entry<Decision, String> entry:aRightSolution.getSelection().entrySet() ){
					aLeftSolution.addDecision(entry.getKey(), entry.getValue());
				}
				result.addSolution(aLeftSolution);
			}
			
		}
		//result.setSolutions(combinedSolutions);
		return result;
	}
	
}
