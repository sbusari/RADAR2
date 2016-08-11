package radar.model;

import java.util.ArrayList;
import java.util.List;

class RefinementGraphGenerator implements ModelVisitor {

	private String dotString =""; // the dot string to be generated.
	private int refCounter ; // a counter used to give unique label to each refinement
	private List<ModelVisitorElement> visited;
	private List<String> edges; 
	public RefinementGraphGenerator(){
		visited = new ArrayList<ModelVisitorElement>();
		edges = new ArrayList<String>();
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

	@Override
	public void visit(Statistic stat) {
	}

	@Override
	public void visit(QualityVariable var) {
		if (!visited.contains(var)){
			String varDotString =  "\""+ var.getLabel() +  "\"" + " [shape = oval] \n";
			dotString +=  varDotString;
			
			
			
			visited.add(var);
		}
	}

	@Override
	public void visit(AND_Refinement andRef) {
		if (!visited.contains(andRef) ){
			String refID = "AndRef" + refCounter;
			refCounter++;
			dotString += refID + "[shape = point] \n";
			String parentLabel = andRef.getParent().getLabel();
			String andDotString = refID + "->" +  "\""+  parentLabel +  "\"" + "\n";
			for (QualityVariable child : andRef.getChildren()){
				String childLabel = child.getLabel();
				andDotString += "\""+ childLabel + "\"" + "->" + refID + " [dir = none] \n";
			}
			dotString +=  andDotString;
			visited.add(andRef);
			
		}
	}
	@Override
	public void visit(OR_Refinement orRef) {
		
	}
	@Override
	public void visit(Distribution andRef) {

	}
	@Override
	public void visit(Identifier id) {
		if (!visited.contains(id)){
			if ( id.getParent() != null){
				String idDotString = "\""+ id.getID() + "\"" + "->" + id.getParent().getLabel()+ " \n";
				// prevent variable referencing itself
				if (!edges.contains(idDotString)){
					dotString +=  idDotString;
					edges.add(idDotString);
				}
			}
			visited.add(id);
		}
	}
	

	@Override
	public void visit(BinaryExpression bin_expr) {
		// do nothing.
	}

}
