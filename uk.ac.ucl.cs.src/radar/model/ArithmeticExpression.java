package radar.model;

import java.util.List;

abstract class ArithmeticExpression extends Expression {
	public abstract double [] simulate (Solution s);
	abstract List<QualityVariable> getQualityVariable ();

}
