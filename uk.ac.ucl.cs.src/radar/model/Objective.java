package radar.model;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import radar.exception.CyclicDependencyException;
/**
 * @author Saheed Busari and Emmanuel Letier
 * This class holds the objective of the model. 
 */
public  class Objective implements ModelVisitorElement {
	private double margin_;
	private boolean isMinimisation_;
	private String label_;
	private QualityVariable qualityVariable_;
	private Statistic definition_;
	private Map<Solution, Double> value_;
	public Objective(){}
	/**
	 * Copy constructor.
	 * @param o objective to copy.
	 */
	public Objective (Objective o){
		isMinimisation_ = o.getIsMinimisation();
		label_ = o.getLabel();
		qualityVariable_ = o.getQualityVariable();
		definition_ =o.getStatistic();
		margin_ = o.getMargin();
		
	}
	/**
	 * Sets a boolean value if an objective is a minimisation objective.
	 * @param isMinimisation boolean value set if objective is a minimisation objective.
	 */
	public void setIsMinimisation(boolean isMinimisation ){
		isMinimisation_ =isMinimisation;
	}
	/**
	 * @return a boolean value if an objective is a minimisation objective.
	 */
	public boolean getIsMinimisation(){
		return isMinimisation_;
	}
	/**
	 * Sets the name of the objective.
	 * @param label name of the objective.
	 */
	public void setLabel(String label ){
		label_ =label;
	}
	/**
	 * @return the name of the objective.
	 */
	public String getLabel (){
		return label_;
	}
	/**
	 * Sets a margin for an objective to reduce modelling error.
	 * @param margin objective margin to set.
	 */
	public void setMargin(double margin ){
		margin_ =margin;
	}
	/**
	 * @return objective margin  value.
	 */
	public double getMargin (){
		return margin_;
	}
	/**
	 * Sets the quality variable an objective refers to.
	 * @param qualityVariable the quality variable an objective refers to.
	 */
	public void setQualityVariable(QualityVariable qualityVariable ){
		qualityVariable_ =qualityVariable;
	}
	/**
	 * @return the quality variable an objective refers to.
	 */
	public QualityVariable getQualityVariable (){
		return qualityVariable_;
	}
	/**
	 * Sets the statistic definition of an objective.
	 * @param definition the statistic definition of an objective.
	 */
	public void setStatistic (Statistic definition){
		definition_ =  definition;
	}
	/**
	 * @return the statistic definition of an objective.
	 */
	public Statistic getStatistic (){
		return definition_;
	}
	/**
	 * Evaluates the objective values for a solution s.
	 * @param s the solution to simulate through monte-carlo simulation.
	 * @return the simulated values of an objective.
	 */
	public double evaluate (Solution s){
		if (value_ == null){
			value_ = new LinkedHashMap<Solution, Double>();
			double result  = definition_.evaluate(s, qualityVariable_);
			value_.put(s, result);
			return result;
		}
		else if (value_.get(s) == null) {
			double result  = definition_.evaluate(s, qualityVariable_);
			value_.put(s, result);
			return result;
		}
		else{
			return value_.get(s);
		}
	}
	/**
	 * @param m semantic model obtained through parsing.
	 * @return all solutions for an objective instance.
	 */
	public SolutionSet getAllSolutions(Model m){
		QualityVariable var = this.getQualityVariable();
		return var.getAllSolutions(m);
	}
	/**
	 * Traverses the model recursively from a quality variable objective refers to and to the leaf quality variables of the model to check for cyclic dependencies between quality variables.
	 * @param m semantic model obtained from parsing.
	 * @throws CyclicDependencyException if there exist a cyclic dependency between quality variables.
	 */
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException{
		QualityVariable var = this.getQualityVariable();
		var.getCyclicDependentVariables(m);
	}
	/**
	 * Visits the quality variable an objective refers to and generate the variable dependency graph.
	 *@param visitor model visitor
	 * @param m semantic model obtained from parsing.
	 */
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		definition_.accept(visitor, m);
		visitor.visit(this);
	}
	@Override
	public int hashCode (){
		if (qualityVariable_ == null){
			return 0;
		}
		return qualityVariable_.hashCode();
	}

}
