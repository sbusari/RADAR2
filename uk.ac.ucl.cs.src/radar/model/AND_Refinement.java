package radar.model;

import java.util.ArrayList;
import java.util.List;

class AND_Refinement extends Expression {

	QualityVariable parent_;
	private ArithmeticExpression definition_;
	@Override
	public double[] simulate(Solution s) {
		return definition_.simulate(s);
	}
	
	List<QualityVariable> getChildren(){
		return definition_.getQualityVariable();
	}
	public void addDefinition (ArithmeticExpression definition){
		definition_ = definition;
	}
	public ArithmeticExpression getDefinition (){
		return definition_;
	}
	@Override
	public QualityVariable getParent() {
		return parent_;
	}
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	public List<Solution> getAllSolutions(){
		List<Solution> result = new ArrayList<Solution>();
		for (QualityVariable var: this.getChildren()){
			result.addAll(var.getAllSolutions());
		}
		return result;
	}
	@Override
	public void accept(ModelVisitor visitor, Model m ) {
		for (QualityVariable var : this.getChildren()){
			var.accept(visitor, m);
		}
		visitor.visit(this);

	}


}
