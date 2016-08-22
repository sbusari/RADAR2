package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import radar.exception.CyclicDependencyException;
/**
 * @author Saheed A. Busari and Emmanuel Letier
 */
public class OR_Refinement extends Expression {
	private Decision decision_;
	private Map<String, AND_Refinement> definition_;
	QualityVariable parent_;
	public OR_Refinement(){
		definition_ = new LinkedHashMap<String, AND_Refinement>();
	}
	/**
	 * Simulates a solution s.
	 * @param s a solution to be simulated through monte-carlo simulation.
	 * @return an array of simulated values.
	 */
	@Override
	public double[] simulate(Solution s) {
		String option = s.selection(decision_);
		AND_Refinement and_ref = definition_.get(option);
		return and_ref.simulate(s);
	}
	/**
	 * Sets the decision for the OR_Refinement.
	 * @param decision OR_Refinement decision.
	 */
	public void setDecision (Decision decision){
		decision_ = decision;
	}
	/**
	 * @return OR_Refinement decision.
	 */
	public Decision getDecision (){
		return decision_;
	}
	/**
	 * Adds all arithemetic definitions of all alternate AND_Refinement to the OR_Refinement.
	 * @param definition a map of arithmetic expression, where the map key is the option name and the map value is the AND_Refinement corresponding to the option.
	 */
	public void setDefinition (Map<String, AND_Refinement>  definition){
		definition_ = definition;
	}
	/**
	 * @return OR_Refinement definition, which is a map of arithmetic expression, where the map key is the option name and the map value is the AND_Refinement corresponding to the option.
	 */
	public Map<String, AND_Refinement>  getDefinition (){
		return definition_;
	}
	/**
	 * Adds individual arithemetic definition of all alternate options to OR_Refinement.
	 * @param option_name  option name that correspond to the AND_Refinement def.
	 * @param def  AND_Refinement to be added.
	 */
	public void addDefinition (String option_name, AND_Refinement def){
		definition_.put(option_name, def);
	}
	/**
	 * @return a quality variable that is a parent of an OR_Refinement.
	 */
	@Override
	public QualityVariable getParent() {
		return parent_;
	}
	/**
	 * Adds the parent of an OR_Refinement.
	 * @param parent the quality variable that is a parent of the OR_Refinement.
	 */
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	/**
	 * Traverses the model recursively from a OR_Refinement to its children (alternate AND_Refinement) up to the leaf quality variables of the model.
	 * @param m semantic model obtained from parsing.
	 * @return solutions from the leaf quality variables of the decision model constructed up to the point of this OR_Refinement, where solutions are combined.
	 */
	@Override
	public SolutionSet getAllSolutions(Model m){
		SolutionSet result = new SolutionSet();
		for (String option: this.decision_.getOptions()){
			AND_Refinement ref = definition_.get(option);
			SolutionSet solutions = ref.getAllSolutions(m);
			 if (solutions.isEmpty()){
                Solution s = new Solution();
                s = s.addDecision(this.decision_, option);
                result.add(s);
	         }else {
                for(Solution s: solutions.set()){
                    s = s.addDecision(this.decision_, option);
                    result.add(s);
                }
	         }
			
		}
		return result;
	}
	/**
	 * Visits the children of OR_Refinement to generate the variable dependency graph.
	 * @param visitor model visitor
	 * @param m semantic model obtained from parsing.
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		for (AND_Refinement andRef : getAndrefinements()){
			andRef.accept(visitor,m);
		}
		visitor.visit(this);
		
	}
	/**
	 * @return all AND_Refinemnt of a OR_Refinement
	 */
	List<AND_Refinement> getAndrefinements(){
		return new ArrayList<AND_Refinement>(definition_.values());
	}
	/**
	 * Traverses the model recursively from a OR_refinement to its children (AND_Refinement) and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		for (AND_Refinement andRef : getAndrefinements()){
			andRef.getCyclicDependentVariables(m);
		}
	}
	
}
