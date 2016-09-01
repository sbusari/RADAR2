package radar.model;
public class AnalysisData {
	
	public AnalysisData(){
		
	}
	String outputDirectory_;
	String problemName_;
	private int noOfSimulation_;
	/**
	 * Sets the number of simulation.
	 * @param noOfSimulation number of simulation.
	 */
	public void setSimulationNumber(int noOfSimulation) {
		this.noOfSimulation_ = noOfSimulation;
	}
	/**
	 * @return the number of simulation.
	 */
	public int getSimulationNumber() {
		return noOfSimulation_;
	}
	/**
	 * Sets the results output directory.
	 * @param outputDirectory output directory.
	 */
	public void setOutputDirectory(String outputDirectory ){
		outputDirectory_ = outputDirectory;
	}
	/**
	 * @return the output directory.
	 */
	public String getOutputDirectory(){
		return outputDirectory_;
	}
	/**
	 * Sets the name of the model problem.
	 * @param problemName the name of model problem.
	 */
	public void setProblemName(String problemName ){
		problemName_ = problemName;
	}
	/**
	 * @return the the name of the model problem.
	 */
	public String getProblemName(){
		return problemName_;
	}



}
