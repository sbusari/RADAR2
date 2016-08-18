package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class RefinementGraphGenerator implements ModelVisitor {

	private String dotString =""; // the dot string to be generated.
	private int refCounter ; // a counter used to give unique label to each refinement
	private List<ModelVisitorElement> visited;
	private List<String> edges; 
	private Map<AND_Refinement, Integer> andRefDecisionID;
	Objective subGraphObjective;
	public RefinementGraphGenerator(Objective subGraphObj){
		visited = new ArrayList<ModelVisitorElement>();
		andRefDecisionID = new LinkedHashMap<AND_Refinement, Integer>();
		edges = new ArrayList<String>();
		subGraphObjective = subGraphObj;
		dotString += "digraph G { \n";
		dotString += "rankdir = BT \n";
	}
	String getDotString(){
		dotString += "}";
		return dotString;
	}
	@Override
	public void visit(Model m) {

	}

	@Override
	public void visit(Objective obj) {
		
		if (!visited.contains(obj)){
			// when subgraph is specified, we do not want the objective to show, it starts from the quality variable.
			if (subGraphObjective != null
					&& !subGraphObjective.getQualityVariable().getLabel().equals(obj.getQualityVariable().getLabel())){
				String objDotString = obj.getLabel() + " [shape = box] \n";
				dotString +=  objDotString;
				QualityVariable qvReferTo = obj.getQualityVariable();
				// no edge when objective name equals var it refers to.
				if (!obj.getLabel().equals(qvReferTo.getLabel())){
					String childDotString = qvReferTo.getLabel() + "->" +  "\""+  obj.getLabel() +  "\"" + "\n";
					dotString +=  childDotString;
				}
				visited.add(obj);
			}
			
		}
	}

	@Override
	public void visit(Statistic stat) {
		
	}
	
	@Override
	public void visit(QualityVariable var) {
		if (!visited.contains(var)){
			String varDotString =  "\""+ var.getLabel() +  "\"" + "[shape = box, style = rounded] \n";
			dotString +=  varDotString;
			// we do not want a qv whose parent is objective to be added here
			// implement the same for unary
			if (var.getDefinition() != null && var.getDefinition().getParent() != null && (var.getDefinition() instanceof BinaryExpression || var.getDefinition() instanceof UnaryExpression) ){ 
				String refID = "AndRef" + refCounter;
				refCounter++;
				dotString += refID + "[shape = point] \n";
				if (var.getDefinition() instanceof BinaryExpression){
					String parentLabel = ((BinaryExpression)var.getDefinition()).getParent().getLabel();
					String andDotString = refID + "->" +  "\""+  parentLabel +  "\"" + "\n";
					for (QualityVariable child : ((BinaryExpression)var.getDefinition()).getQualityVariable()){
						String childLabel = child.getLabel();
						String childDotString =  "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
						if (!edges.contains(childDotString)){
							andDotString += childDotString;
							edges.add(childDotString);
						}
					}
					dotString +=  andDotString;
					visited.add(((BinaryExpression)var.getDefinition()));
				}else{// unary expr
					String parentLabel = ((UnaryExpression)var.getDefinition()).getParent().getLabel();
					String andDotString = refID + "->" +  "\""+  parentLabel +  "\"" + "\n";
					for (QualityVariable child : ((UnaryExpression)var.getDefinition()).getQualityVariable()){
						String childLabel = child.getLabel();
						String childDotString =  "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
						if (!edges.contains(childDotString)){
							andDotString += childDotString;
							edges.add(childDotString);
						}
					}
					dotString +=  andDotString;
					visited.add(((UnaryExpression)var.getDefinition()));
				}
			}
			visited.add(var);
		}
	}
	/*
	 * gets ANDRefinemnet decision ID if ANDRefinement parent already visited by another ANDRefinement that share the same parent.
	 */
	Integer getANDRefID (AND_Refinement andRef){
		Integer result =null;
		for (Map.Entry<AND_Refinement, Integer> entry:andRefDecisionID.entrySet()){
			if (entry.getKey().getParent().getLabel().equals(andRef.getParent().getLabel())){
				result = entry.getValue();
			}
		}
		return result;
	}
	// this does not include decisions betwee quality variables
	@Override
	public void visit(AND_Refinement andRef) {
		if (!visited.contains(andRef) ){
			String refID = "AndRef" + refCounter;
			refCounter++;
			dotString += refID + "[shape = point] \n";
			
			String decisionID ="";
			Integer andRefID = getANDRefID(andRef);
			if ( andRefID != null){
				decisionID = ""+andRefID; 
			}else{
				int ID = refCounter;
				decisionID = ""+ID;
				andRefDecisionID.put(andRef, ID);
			}
			dotString += decisionID + "[label=\""  + andRef.getDecisionNameAndRefRefersTo()   +"\", shape = polygon, sides =8]"; 
			String parentLabel = andRef.getParent().getLabel();
			String decisionToParent =  decisionID + "->" +  "\""+  parentLabel +  "\"" + "\n";
			if (!edges.contains(decisionToParent)){
				dotString += decisionToParent;
				edges.add(decisionToParent);
			}
			String andDotString = refID + "->" +    decisionID + "\n";
			for (QualityVariable child : andRef.getChildren()){
				String childLabel = child.getLabel();
				dotString += "\"" +  childLabel + "\"" + "[shape = box, style = rounded] \n";
				String childDotString =  "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
				if (!edges.contains(childDotString)){
					andDotString += childDotString;
					edges.add(childDotString);
				}
				
			}
			dotString +=  andDotString;
			visited.add(andRef);
			
		}
	}
	// this does not include decisions betwee quality variables
/*	@Override
	public void visit(AND_Refinement andRef) {
		if (!visited.contains(andRef) ){
			String refID = "AndRef" + refCounter;
			refCounter++;
			dotString += refID + "[shape = point] \n";
			String parentLabel = andRef.getParent().getLabel();
			String andDotString = refID + "->" +  "\""+  parentLabel +  "\"" + "\n";
			for (QualityVariable child : andRef.getChildren()){
				String childLabel = child.getLabel();
				String childDotString =  "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
				if (!edges.contains(childDotString)){
					andDotString += childDotString;
					edges.add(childDotString);
				}
				
			}
			dotString +=  andDotString;
			visited.add(andRef);
			
		}
	}*/
	@Override
	public void visit(OR_Refinement orRef) {

	}



}
