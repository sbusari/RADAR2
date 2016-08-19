package radar.model;
import java.util.List;

import radar.utilities.PseudoRandom;

class AND_Refinement extends Expression {

	/*
	 * the decision name in which this ANDRef refers to 
	 */
	String decisionAndRefRefersTo_;
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
	public void setDecisionNameAndRefRefersTo (String decisionNameAndRefReferTo){
		decisionAndRefRefersTo_ = decisionNameAndRefReferTo;
	}
	public String getDecisionNameAndRefRefersTo (){
		return decisionAndRefRefersTo_;
	}
	public SolutionSet getAllSolutions(Model m){
		SolutionSet result = new SolutionSet();
		for (QualityVariable var: this.getChildren()){
			if (var.getDefinition() == null){ 
				QualityVariable qv = m.getQualityVariables().get(var.getLabel());
				if (qv != null){
					result = result.merge(qv.getAllSolutions(m));
					//result.addAll(qv.getAllSolutions(m));
				}else{ // if it is a paramter within an expr it  will return null cos its labe does not exist
					Solution s = new Solution();
					//String uniqueID = ""+ m.getSolutionCount(); 
					//s.setUniqueID(uniqueID);
					result.add(s);
				}
			}else{
				result = result.merge(var.getAllSolutions(m));
				//result.addAll(var.getAllSolutions(m));
			}
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

	@Override
	public void getCyclicDependentVariables(Model m) {
		 definition_.getCyclicDependentVariables(m);
	}


}
