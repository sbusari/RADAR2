package radar.model;
public class AnalysisData {
	
	public AnalysisData(){
		
	}
	String outputDirectory_;
	String problemName_;
	private int noOfSimulation_;

	public void setSimulationNumber(int noOfSimulation) {
		this.noOfSimulation_ = noOfSimulation;
	}
	public int getSimulationNumber() {
		return noOfSimulation_;
	}
	public void setOutputDirectory(String outputDirectory ){
		outputDirectory_ = outputDirectory;
	}
	public String getOutputDirectory(){
		return outputDirectory_;
	}
	public void setProblemName(String problemName ){
		problemName_ = problemName;
	}
	public String getProblemName(){
		return problemName_;
	}



}
