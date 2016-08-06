package radar.plot.goal.graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import radar.model.Model;
import radar.model.Objective;
import radar.model.QualityVariable;

public class Graph {
	Model semanticModel_;
	static int operatorId_;
	Map<String, Node> nodeList_;
	List<String> edgeStatements_;
	public Graph (){
		nodeList_ = new LinkedHashMap<String, Node>();
		edgeStatements_ = new ArrayList<String>();
	}
	public Graph (Model semanticModel){
		semanticModel_ =semanticModel;
		nodeList_ = new LinkedHashMap<String, Node>();
		edgeStatements_ = new ArrayList<String>();
	}
	public Node addDOTNode (){
		return new Node();
	}
	public void addEdge (String child, String parent){
		//String edge = parent+ "->" +  child;
		String edge  = child+ "->" +  parent;
		if ( !edgeStatements_.contains(edge)){
			edgeStatements_.add(edge);
		}
	}
	public void addDOTNodeToList (Node node){
		nodeList_.put(node.getLabel(), node);
	}
	public Map<String, Node> getNodeList (){
		return nodeList_;
	}
	public List<String> getEdgeStatements (){
		return edgeStatements_;
	}
	public int getOperatorID (){
		return operatorId_++;
	}
	public void createVariableGraph(){
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel_.getObjectives().values());
		
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			Node obj_node = new Node ();
			obj_node.setLabel(obj.getLabel());
			obj_node.setShape("box");
			obj_node.setStyle("");
			this.nodeList_.put(obj.getLabel(), obj_node);
			QualityVariable qvObjReferTo = obj.getQualityVariable();
			if (!this.nodeList_.containsKey(qvObjReferTo.getLabel())){
				List<Node> qv_nodes = qvObjReferTo.addDOTNodeToGraph(this , this.semanticModel_, qvObjReferTo.getLabel());
				for (int j=0; j < qv_nodes.size(); j ++){
					this.addEdge(qv_nodes.get(j).getLabel(), obj_node.getLabel());
				}
			}else{
				Node qv_node = this.nodeList_.get(qvObjReferTo.getLabel());
				this.addEdge(qv_node.getLabel(), obj_node.getLabel());
			}
		}
		
	}
	public void createDecisionsGraph(){
		List<Objective> objList = new ArrayList<Objective>(this.semanticModel_.getObjectives().values());
		Node obj_node = new Node ();
		obj_node.setLabel(this.semanticModel_.getModelName() );
		obj_node.setShape("circle");
		obj_node.setStyle("");
		this.nodeList_.put(obj_node.getLabel(), obj_node);
		for (int i =0; i < objList.size(); i ++){
			QualityVariable qvObjReferTo =objList.get(i).getQualityVariable();
			if (!this.nodeList_.containsKey(qvObjReferTo.getLabel())){
				List<Node> qv_nodes = qvObjReferTo.addDOTNodeToDecisionGraph(this , this.semanticModel_, qvObjReferTo.getLabel());
				for (int j=0; j < qv_nodes.size(); j ++){
					this.addEdge(qv_nodes.get(j).getLabel(), obj_node.getLabel());
				}
			}else{
				Node qv_node = this.nodeList_.get(qvObjReferTo.getLabel());
				this.addEdge(qv_node.getLabel(), obj_node.getLabel());
			}
		}
		
	}
}
