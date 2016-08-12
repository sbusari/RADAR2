package radar.model;
import java.util.Set;


 abstract class Expression implements ModelVisitorElement {

	public abstract double [] simulate (Solution s);
	public abstract Set<Solution> getAllSolutions(Model m);
	QualityVariable parent_;
	public QualityVariable getParent() {
		return parent_;
	}
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	public abstract void accept (ModelVisitor visitor, Model m);
	public Expression() {}
	
}
