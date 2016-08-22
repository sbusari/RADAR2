package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
 * @author Saheed Busari and Emmanuel Letier
 */
class Number extends ArithmeticExpression {

	private double value_;
	public void setValue (double value){
		value_ = value;
	}
	public double  getValue (){
		return value_;
	}
	
	@Override
	public double[] simulate(Solution s) {
		// Expr in the argument of a deterministic distribution have been handled that during parsing
		// Here, we handle a binary expression that has a number as an operand.
		int simulationNo = s.getSemanticModel().getNbr_Simulation();
		double [] sim = new double [simulationNo];
		for (int i = 0; i < sim.length; ++i) {
			sim[i] =value_;
		}
		return sim;
	}
	@Override
	List<QualityVariable> getQualityVariable() {
		return new ArrayList<QualityVariable>();
	}
	@Override
	public QualityVariable getParent() {
		return null;
	}
	@Override
	public void accept(ModelVisitor visitor, Model m) {

	}
	@Override
	public SolutionSet getAllSolutions(Model m) {
		return new SolutionSet();
	}
	@Override
	public void getCyclicDependentVariables(Model m) {
	}
}
