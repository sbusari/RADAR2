package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import radar.exception.CyclicDependencyException;

public class QualityVariable extends ArithmeticExpression implements ModelVisitorElement {

	private String label_;
	private Expression definition_;
	private Map<Solution, double[]> simData_;
	private List<String> children_;
	public QualityVariable(){
		simData_ = new LinkedHashMap<Solution, double[]>();
	}
	public void setLabel (String label){
		label_ = label;
	}
	public String getLabel (){
		return label_;
	}
	public void setDefinition (Expression def){
		definition_ = def;
	}
	public Expression getDefinition (){
		return definition_;
	}
	public void setChildren (List<String> children){
		children_ =children;
	}
	public List<String> getChildren (){
		return children_;
	}
	public double[] getSimData(Solution s) {
		return simulate(s);
	}
	public double[][] getSimData(List<Solution> s) {
		double [][] result = new double [s.size()][];
		for (int i =0 ; i < s.size(); i ++){
			result[i] = getSimData (s.get(i));
		}
		return result;
	}
	double[][] simulate(List<Solution> s) {
		double [][] result = new double [s.size()][];
		for (int i =0 ; i < s.size(); i ++){
			result[i] = simulate(s.get(i));
		}
		return result;
	}
	public double [] simulate (Solution s){
		
		return definition_.simulate(s);
	}
	public Map<Solution, double[]> getSimData(){
		return simData_;
	}
	public void setSimData(Map<Solution, double[]> simdata){
		simData_ =simdata;
	}
	@Override
	List<QualityVariable> getQualityVariable() {
		List<QualityVariable> result = new ArrayList<QualityVariable>();
		result.add(this);
		return result;
	}
	
	public int hashCode(){
		return this.getLabel().hashCode();
	}
	@Override
	public SolutionSet getAllSolutions(Model m){
		Expression expr = this.definition_;
		return expr.getAllSolutions(m);
		
	}
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		// definition for the option may be null
		// can only be null if it was populated partially during parsing for an arithmtetic expr 
		if (definition_ == null){ 
			QualityVariable qv = m.getQualityVariables().get(label_);
			// if we consider parameter, the label would have smt like qv_name[option], hence, qv may be null as such qv named do not exist.
			if (qv != null){
				qv.getDefinition().accept(visitor,m);
				// update the definition of the this quality varible, so that when it visit itself it can prints the children of the arithmetic expression if it they exist.
				this.setDefinition(qv.getDefinition());
			}
		}else{
			definition_.accept(visitor,m);
		}
		visitor.visit(this);
	}
	@Override
	public QualityVariable getParent() {
		return null;
	}
	@Override
	public void getCyclicDependentVariables(Model m) throws CyclicDependencyException {
		definition_.getCyclicDependentVariables(m);
	}





	
}
