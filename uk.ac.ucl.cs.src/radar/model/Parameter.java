package radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 class Parameter extends QualityVariable {
    private Distribution distribution;
    private double[] simData;
    public double[] simulate(Solution s){
    	if (simData == null) simData = distribution.simulate(s);
        return simData;
    }
	public void accept(ModelVisitor visitor, Model m) {
		
		distribution.accept(visitor,m);
		//visitor.visit(distribution);
		//visit(this);
		//this.accept(visitor,m);
	}
    public void setDistribution (Distribution distr){
    	distribution = distr;
	}
	public Distribution getDistribution (){
		return distribution;
	}
	public double[] getSimulationData (){
		return simData;
	}
	public List<Node> addNodeToVariableGraph(Graph g, Model model,
			String qv_name) {
		return null;
	}
	public List<Node> addNodeToDecisionGraph(Graph g, Model model,
			String qv_name) {
		return null;
	}
}
