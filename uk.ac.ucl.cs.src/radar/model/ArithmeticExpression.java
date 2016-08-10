package radar.model;

import java.util.List;

// still extend Expression as Statistic has expression and during parsing i return arithmetic expression
// Can only return one thing during parsing, otherwise the code will be messy as I would have to intriduce 
// some boolean operators which will make the visitor complex.
abstract class ArithmeticExpression extends Expression {
	public abstract double [] simulate (Solution s);
	abstract List<QualityVariable> getQualityVariable ();
}
