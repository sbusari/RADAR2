package radar.model;

import java.util.List;
import java.util.Map;

public class GraphGenerator {
	
	public String generateVariableGraph (Model m){
		// generate variable dependency graphs
		return generateVariableDependencyGraph(m);
	}
	public String generateDecisionGraph (Model m){
		// generate decision graph
		return generateDecisionDependencyGraph(m);
	}
	private String generateDecisionDependencyGraph (Model m){
		String result ="digraph G { \n";
		result += "rankdir = BT; \n";
		result += "edge[dir=back]; \n";

		Graph de = new Graph(m);
		de.createDecisionsGraph();
		
		Map<String, Node> allEntries = de.getNodeList();
		for (Map.Entry<String, Node> entry: allEntries.entrySet()){
			String shape = "shape="+ entry.getValue().getShape();
			String style = (entry.getValue().getStyle() != "")?  ", style=" + entry.getValue().getStyle(): "";
			System.out.println( entry.getKey() + "[" + shape + style+ "]");
			result += entry.getKey() + "[" + shape + style+ "]" + "\n";
		}
		
		List<String> allEdgeEntries = de.getEdgeStatements();
		for (String entry: allEdgeEntries){
			String [] entryElement = entry.split("->");
			if (!entryElement[0].equals(entryElement[1])){
				result += entry + "\n";
				System.out.println( entry);
			}
		}
		result += "}";
		return result;
	}
	private String generateVariableDependencyGraph (Model m){
		String result ="digraph G { \n";
		result += "rankdir = BT; \n";
		result += "edge[dir=forward]; \n";
		
		Graph de = new Graph(m);
		de.createVariableGraph();
		
		Map<String, Node> allEntries = de.getNodeList();
		for (Map.Entry<String, Node> entry: allEntries.entrySet()){
			String shape = "shape="+ entry.getValue().getShape();
			String style = (entry.getValue().getStyle() != "")?  ", style=" + entry.getValue().getStyle(): "";
			System.out.println( entry.getKey() + "[" + shape + style+ "]");
			result +=  entry.getKey() + "[" + shape + style+ "]" + "\n";
		}
		
		List<String> allEdgeEntries = de.getEdgeStatements();
		for (String entry: allEdgeEntries){
			String [] entryElement = entry.split("->");
			if (!entryElement[0].equals(entryElement[1])){
				result += entry + "\n";
				System.out.println( entry);
			}
		}
		result += "}";
		return result;
	}
}
