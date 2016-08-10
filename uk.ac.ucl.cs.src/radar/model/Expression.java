package radar.model;


 abstract class Expression implements ModelVisitorElement {

	public abstract double [] simulate (Solution s);
	public abstract QualityVariable getParent(); 
	public abstract void accept (ModelVisitor visitor);
	public Expression() {}
	
}
