package radar.model;

import java.util.List;


 abstract class Expression implements ModelVisitorElement {

	public abstract double [] simulate (Solution s);
	List<Solution> allSolutions_;
	QualityVariable parent_;
	public QualityVariable getParent() {
		return parent_;
	}
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	public List<Solution> getAllSolutions(){
		return allSolutions_;
	}
	public abstract void accept (ModelVisitor visitor, Model m);
	public Expression() {}
	
}
