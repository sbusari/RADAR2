package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import radar.exception.CyclicDependencyException;
import radar.exception.ParameterDistributionException;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
class BinaryExpression extends ArithmeticExpression {
	private BinaryOperator bop_;
	private ArithmeticExpression leftExpr_, rightExpr_;
	/**
	 * Sets operator for the binary expression.
	 * @param bop binary operator.
	 */
	public void setBinaryOperator (BinaryOperator bop){
		bop_ = bop;
	}
	/**
	 * Returns a binary operator for a binary expression.
	 * @return a binary operator such as +, -, /, * etc.
	 */
	public BinaryOperator getBinaryOperator(){
		return bop_;
	}
	/**
	 * Sets the left operand of a binary expression.
	 * @param expr arithmetic expression which could also be a number.
	 */
	public void setLeftExpression (ArithmeticExpression expr){
		leftExpr_ = expr;
	}
	/**
	 * @return the left expression of binary expression, which could also be a number.
	 */
	public ArithmeticExpression getLeftExpression (){
		return leftExpr_;
	}
	/**
	 * Sets the right operand of a binary expression.
	 * @param expr arithmetic expression which could also be a number.
	 */
	public void setRightExpression (ArithmeticExpression expr){
		rightExpr_ = expr;
	}
	/**
	 * @return the right expression of binary expression, which could also be a number.
	 */
	public ArithmeticExpression getRightExpression (){
		return rightExpr_;
	}
	/**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
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
	/**
	 * @return a list of quality variables that defines a binary expression.
	 */
	@Override
	List<QualityVariable> getQualityVariable() {
		List<QualityVariable> result = new ArrayList<QualityVariable>();
		List<QualityVariable> leftVars = leftExpr_.getQualityVariable();
		List<QualityVariable> rightVars = rightExpr_.getQualityVariable();
		result.addAll(leftVars);
		result.addAll(rightVars);
		return result;
	}
	/**
	 * @return a quality variable that is a parent of an AND_Refinement.
	 */
	@Override
	public QualityVariable getParent() {
		return parent_;
	}
	/**
	 * Visits the children of a binary expression to generate the variable dependency graph.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		leftExpr_.accept(visitor, m);
		rightExpr_.accept(visitor, m);
	}
	/**
	 * Traverses the operands of a binary expression recursively until reaching the leaf quality variables.
	 * @param m parsed decison model.
	 * @return a set of solutions constructed while traversing a binary expression from the leaf quality variables to the point of the calling binary expression.
	 */
	@Override
	public SolutionSet getAllSolutions(Model m) {
		SolutionSet results = new SolutionSet();
		SolutionSet leftSolution= leftExpr_.getAllSolutions(m);
		SolutionSet rightSolution= rightExpr_.getAllSolutions(m);
		results = results.merge(leftSolution);
		results = results.merge(rightSolution);
		return results;
	}
	/**
	 * Traverses the model recursively from a quality variable binary expression to its children and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		leftExpr_.getCyclicDependentVariables(m);
		rightExpr_.getCyclicDependentVariables(m);
	}
	@Override
	public double getParamExpressionValue(Model m) throws ParameterDistributionException { 
		double left = this.getLeftExpression().getParamExpressionValue(m);
		double right = this.getRightExpression().getParamExpressionValue(m);
		double result =0;
		switch (this.bop_.getBinaryOperatorValue()){
			case "+" : result= left + right; break;
			case "-" :  result= left -right; break;
			case "*" : result= left * right;break;
			case "/" : result = left / right;break;
			default : return 0;
		}
		return result;
	}

	
}
