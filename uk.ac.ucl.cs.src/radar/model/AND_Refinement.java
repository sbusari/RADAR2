package radar.model;
import java.util.List;

import radar.exception.CyclicDependencyException;
/**
 * @author Saheed Busari and Emmanuel Letier
 */
class AND_Refinement extends Expression {

	/*
	 * the decision name in which this ANDRef refers to 
	 */
	String aDecisionAndRefRefersTo_;
	QualityVariable parent_;
	private ArithmeticExpression definition_;
	/**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	@Override
	public double[] simulate(Solution s) {
		return definition_.simulate(s);
	}
	/**
	 * Returns a list of quality variables that are AND_Refienement children.
	 * @return a list of quality variables.
	 */
	List<QualityVariable> getChildren(){
		return definition_.getQualityVariable();
	}
	/**
	 * Adds arithemetic definition of the AND_Refinement.
	 * @param definition an arithmetic expression that defines the current AND_Refinement.
	 */
	public void addDefinition (ArithmeticExpression definition){
		definition_ = definition;
	}
	/**
	 * Returns the arithemetic definition of the AND_Refinement.
	 * @return arithmetic expression.
	 */
	public ArithmeticExpression getDefinition (){
		return definition_;
	}
	/**
	 * Returns the parent of an AND_Refinement.
	 * @return a quality variable that is a parent of an AND_Refinement.
	 */
	@Override
	public QualityVariable getParent() {
		return parent_;
	}
	/**
	 * Adds the parent of an AND_Refinement.
	 * @param parent the quality variable that is a parent of the AND_Refinement.
	 */
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	public void setDecisionNameAndRefRefersTo (String decisionNameAndRefReferTo){
		aDecisionAndRefRefersTo_ = decisionNameAndRefReferTo;
	}
	public String getDecisionNameAndRefRefersTo (){
		return aDecisionAndRefRefersTo_;
	}
	/**
	 * Traverses the model recursively from a AND_refinement to its children and to the leaf quality variables of the model.
	 * @param m semantic model obtained from parsing.
	 * @return solutions from the leaf quality variables of the decision model up to the point of an AND_Refinement.
	 */
	public SolutionSet getAllSolutions(Model m){
		SolutionSet result = new SolutionSet();
		for (QualityVariable var: this.getChildren()){
			if (var.getDefinition() == null){ 
				QualityVariable qv = m.getQualityVariables().get(var.getLabel());
				if (qv != null){
					result = result.merge(qv.getAllSolutions(m));
				}else{ // if it is a paramter within an expr it  will return null cos its labe does not exist
					Solution s = new Solution();
					result.add(s);
				}
			}else{
				result = result.merge(var.getAllSolutions(m));
			}
		}
		return result;
	}
	/**
	 * Visits the children of AND_Refinement to generate the variable dependency graph.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m ) {
		for (QualityVariable var : this.getChildren()){
			var.accept(visitor, m);
		}
		visitor.visit(this);

	}
	/**
	 * Traverses the model recursively from a AND_refinement expression  to its children and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		 definition_.getCyclicDependentVariables(m);
	}


}
