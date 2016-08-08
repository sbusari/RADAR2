package radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 class Parameter extends QualityVariable {
    private Distribution distribution;
    private double[] simData;
    public double[] simulate(Solution s){
    	if (simData == null) simData = distribution.simulate(s);
    	//System.out.println(simData[0] + ", " + simData[1] );
        return simData;
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
	
	public List<Node> addNodeToVariableGraph(GraphGenerator g, Model model,
			String qv_name) {
		return null;
	}
	public List<Node> addNodeToDecisionGraph(GraphGenerator g, Model model,
			String qv_name) {
		return null;
	}
}
