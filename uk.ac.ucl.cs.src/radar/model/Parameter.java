package radar.model;

 class Parameter extends QualityVariable {
    private Distribution distribution;
    private double[] simData;
    public double[] simulate(Solution s){
    	if (simData == null) simData = distribution.simulate(s);
        return simData;
    }
	public void accept(ModelVisitor visitor, Model m) {
		distribution.accept(visitor,m);
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

}
