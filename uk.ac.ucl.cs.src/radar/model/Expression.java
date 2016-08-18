package radar.model;

import java.util.List;


 abstract class Expression implements ModelVisitorElement {

	public abstract double [] simulate (Solution s);
	public abstract SolutionSet getAllSolutions(Model m);
	QualityVariable parent_;
	public QualityVariable getParent() {
		return parent_;
	}
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	public abstract void accept (ModelVisitor visitor, Model m);
	public abstract void checkAcyclicity (Model m);
	public Expression() {}
	
}
