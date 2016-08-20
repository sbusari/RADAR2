package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import radar.exception.CyclicDependencyException;
import radar.exception.ModelException;
/**
 * @author Saheed Busari and Emmanuel Letier
 * This class holds the name of a quality varibale within an expression.
 */
class Identifier extends ArithmeticExpression implements ModelVisitorElement {
	private String id_;
	QualityVariable parent_;
	// the quality variable its self
	QualityVariable linkedQv_;
	/**
	 * Stores the name of a quality variable in an expression.
	 * @param id quality variable name in an expression.
	 */
	public void setID (String id){
		id_ = id;
	}
	/**
	 * @return the quality variable name in an expression.
	 */
	public String  getID (){
		return id_;
	}
	public void setLinkedQualityVariable (QualityVariable linkedqv){
		linkedQv_ = linkedqv;
	}
	 /**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	@Override
	public double[] simulate(Solution s) {
		Map<String, QualityVariable> qvList = s.getSemanticModel().getQualityVariables();
		
		if (id_.equals("ContinuousTrueAlertRate")){
			System.out.println("QV: "+ id_);
		}
		QualityVariable qv = qvList.get(id_);
		if (qv ==null){
			throw new RuntimeException ("Quality variable " + id_ + " is not defined in the model.");
		}
		double [] result = qv.simulate(s);
		return result;
	}
	/**
	 * Returns the parent of an identifier.
	 * @return a quality variable that is a parent of an expression.
	 */
	public QualityVariable getParent() {
		return parent_;
	}
	/**
	 * Sets the parent of an identifier.
	 * @param parent  quality variable that is a parent of an identifier.
	 */
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	/**
	 * Visits the children of an identifier, but first gets the quality variable instance of the identifier.
	 * @param m semantic model obtained from parsing.
	 *@param visitor model visitor
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		
		QualityVariable qv = m.getQualityVariables().get(id_);
		qv.accept(visitor, m);
	}
	/**
	 * @return a quality variable instance of an identifier.
	 */
	@Override
	List<QualityVariable> getQualityVariable() {
		List<QualityVariable> result = new ArrayList<QualityVariable>();
		result.add(linkedQv_);
		return result;
	}
	/**
	 * Traverses a quality variable instance of an identifier recursively until reaching the leaf quality variables to get all solutions.
	 * @param m semantic model obtained from parsing.
	 * @return a set of solutions constructed while traversing a quality variable instance of an identifier up to the leaf quality variables.
	 */
	@Override
	public SolutionSet getAllSolutions(Model m) {
		QualityVariable qv = m.getQualityVariables().get(id_);
		SolutionSet solutions = qv.getDefinition().getAllSolutions(m);
		return solutions;
	}
	/**
	 * Traverses a quality variable instance of an identifier recursively until reaching the leaf quality variables to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if variable dependency graph is cyclic. 
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		QualityVariable qv = m.getQualityVariables().get(id_);
		for (String child : qv.getChildren()){
			if (parent_.getLabel().equals(child)){
				throw new RuntimeException ("Cyclic dependency in the model between " + parent_.getLabel() + " and " + id_);
			}
			// is the child (NB) of qv(A) is already on stack, get that child and check if any of its child is not qv(A)
			if (isQualityVariableVisited(child, m)){
				List<String> grandChildren =  m.getQualityVariables().get(child).getChildren();
				for (String grandChild : grandChildren){
					if (id_.equals(grandChild)){
						throw new CyclicDependencyException( "Cyclic dependency in the model between " + id_ + " and " + grandChild + "\n");
					}
				}
			}
			
		}
		m.addVisitedQualityVariable(qv);
	}
	/**
	 * @param m parsed decison model.
	 * @param variable quality variable name to check.
	 * @return true if a quality variable has been visited, else returns false.
	 */
	boolean isQualityVariableVisited (String variable, Model m){
		boolean result =false;
		if (m.getVisitedQualityVariable().containsKey(variable)){
			return true;
		}
		return result;
	}
}
