package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Identifier extends ArithmeticExpression implements ModelVisitorElement {
	private String id_;
	QualityVariable parent_;
	// the quality variable its self
	QualityVariable linkedQv_;
	public void setID (String id){
		id_ = id;
	}
	public String  getID (){
		return id_;
	}
	public void setLinkedQualityVariable (QualityVariable linkedqv){
		linkedQv_ = linkedqv;
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
	public void accept(ModelVisitor visitor, Model m) {
		
		QualityVariable qv = m.getQualityVariables().get(id_);
		qv.accept(visitor, m);
	}
	@Override
	List<QualityVariable> getQualityVariable() {
		List<QualityVariable> result = new ArrayList<QualityVariable>();
		result.add(linkedQv_);
		return result;
	}
	@Override
	public SolutionSet getAllSolutions(Model m) {
		QualityVariable qv = m.getQualityVariables().get(id_);
		SolutionSet solutions = qv.getDefinition().getAllSolutions(m);
		return solutions;
	}
}
