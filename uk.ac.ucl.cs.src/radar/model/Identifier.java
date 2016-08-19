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
	@Override
	public void getCyclicDependentVariables(Model m) {
		QualityVariable qv = m.getQualityVariables().get(id_);
		for (String child : qv.getChildren()){
			if (parent_.getLabel().equals(child)){
				throw new RuntimeException ("Cyclic dependency in the model between " + parent_.getLabel() + " and " + id_);
			}
			// is the child (NB) of qv(A) is already on stack, get that child and check if any of its child is not qv(A)
			if (isChildVariableOnStack(child, m)){
				List<String> grandChildren =  m.getQualityVariables().get(child).getChildren();
				for (String grandChild : grandChildren){
					if (id_.equals(grandChild)){
						throw new RuntimeException( "Cyclic dependency in the model between " + id_ + " and " + grandChild + "\n");
					}
				}
			}
			
		}
		m.addVariableToStack(qv);
	}
	boolean isChildVariableOnStack (String variable, Model m){
		boolean result =false;
		if (m.getStackedVariable().containsKey(variable)){
			return true;
		}
		return result;
	}
}
