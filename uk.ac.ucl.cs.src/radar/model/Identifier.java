package radar.model;
import java.util.List;
import java.util.Map;

class Identifier extends ArithmeticExpression implements ModelVisitorElement {
	private String id_;
	QualityVariable parent_;
	public void setID (String id){
		id_ = id;
	}
	public String  getID (){
		return id_;
	}
	
	@Override
	public double[] simulate(Solution s) {
		Map<String, QualityVariable> qvList = s.getSemanticModel().getQualityVariables();
		QualityVariable qv = qvList.get(id_);
		if (qv ==null){
			throw new RuntimeException ("Quality variable " + id_ + " is not defined in the model.");
		}
		double [] result = qv.simulate(s);
		return result;
	}
	public QualityVariable getParent() {
		return parent_;
	}
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	@Override
	public void accept(ModelVisitor visitor) {
		this.accept(visitor);
	}
	@Override
	List<QualityVariable> getQualityVariable() {
		return null;
	}
}
