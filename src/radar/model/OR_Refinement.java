package radar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import prefuse.data.Graph;
import prefuse.data.Node;

public class OR_Refinement extends Expression {
	private Decision decision_;
	private Map<String, Expression> definition_;
	
	public OR_Refinement(){}
	@Override
	public List<Node> addNodeToGraph(Graph g, Model model, String qv_name,Map<String, Node> cache) {
		List<Node> result = new ArrayList<Node>();
		for (Map.Entry<String, Expression> entry: definition_.entrySet()){
			String optionName = qv_name + "[" + entry.getKey() + "]";
			Node option = createNode (g, optionName,"Option",optionName, cache);
			result.add(option);
			// add edge between option node and its own children
			List<Node> optionChildren = entry.getValue().addNodeToGraph(g,model,qv_name,cache);
			if (optionChildren != null &&  optionChildren.size() > 0){
				for (int i =0 ; i <  optionChildren.size() ; i ++){
					g.addEdge(option, optionChildren.get(i));
				}
			}
		}
		return result;
	}
	@Override
	public double[] simulate(Alternative s) {
		String option = s.getOption(decision_);
		Expression expr = definition_.get(option);
		//check if expr is a distribution, set this boolean value to true i there.
		// set a field with option name and this name wil be used in QV that calls this
		if (expr instanceof Distribution && !(expr instanceof DeterministicDistribution)){
			isExpresionDistribution_ = true;
			parameterOption_ = option;
		}
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
