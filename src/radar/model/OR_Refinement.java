package radar.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OR_Refinement extends Expression {
	private Decision decision_;
	private Map<String, Expression> definition_;
	
	public OR_Refinement(){}
	@Override
	public double[] simulate(Alternative s) {
		String option = s.getOption(decision_);
		Expression expr = definition_.get(option);
		if (expr instanceof Distribution){
			isExpresionDistribution_ = true;
			parameterOption_ = option;
		}
		//check if expr is a distribution
		// also in the distribution qv, we set this boolean value to true i there.
		// set a field with option name and this name wil be used in QV that calls this
		return expr.simulate(s);
	}
	public void setDecision (Decision decision){
		decision_ = decision;
	}
	public Decision getDecision (){
		return decision_;
	}
	public void setDefinition (Map<String, Expression>  definition){
		definition_ = definition;
	}
	public Map<String, Expression>  getDefinition (){
		return definition_;
	}
	public void addDefinition (String option_name, Expression def){
		if (definition_ == null){
			definition_ = new LinkedHashMap<String, Expression>();
			definition_.put(option_name, def);
		}else{
			definition_.put(option_name, def);
		}
	}
	
}
