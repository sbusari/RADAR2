package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OR_Refinement extends Expression {
	private Decision decision_;
	private Map<String, AND_Refinement> definition_;
	QualityVariable parent_;
	public OR_Refinement(){
		definition_ = new LinkedHashMap<String, AND_Refinement>();
	}
	@Override
	public double[] simulate(Solution s) {
		String option = s.selection(decision_);
		AND_Refinement and_ref = definition_.get(option);
		return and_ref.simulate(s);
	}
	
	
	public void setDecision (Decision decision){
		decision_ = decision;
	}
	public Decision getDecision (){
		return decision_;
	}
	public void setDefinition (Map<String, AND_Refinement>  definition){
		definition_ = definition;
	}
	public Map<String, AND_Refinement>  getDefinition (){
		return definition_;
	}
	public void addDefinition (String option_name, AND_Refinement def){
		definition_.put(option_name, def);
	}
	@Override
	public QualityVariable getParent() {
		return parent_;
	}
	public void setParent(QualityVariable parent) {
		parent_ = parent;
	}
	@Override
	public SolutionSet getAllSolutions(Model m){
		SolutionSet result = new SolutionSet();
		for (String option: this.decision_.getOptions()){
			AND_Refinement ref = definition_.get(option);
			SolutionSet solutions = ref.getAllSolutions(m);
			for(Solution s: solutions.set()){
				s.addDecision(this.decision_, option);
				result.add(s);
			}
			//result = result.merge(solutions);
			//result.addAll(solutions);
			
		}
		return result;
	}
	@Override
	public void accept(ModelVisitor visitor, Model m) {
		for (AND_Refinement andRef : getAndrefinements()){
			andRef.accept(visitor,m);
		}
		visitor.visit(this);
		
	}
	List<AND_Refinement> getAndrefinements(){
		return new ArrayList<AND_Refinement>(definition_.values());
	}




	
	
}
