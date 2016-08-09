package radar.model;

import java.util.List;
import java.util.Map;

public class SbseData {
	private int nbr_objectives_;
	private List<Integer[]> decisionVectorBlock_;
	ExperimentData expData ;
	SbseParameter sbse_param_;
	public SbseData (){	}
	public SbseData (ExperimentData data, SbseParameter sbse_param, int nbr_objectives, List<Integer[]> decisionVectorBlock){
		nbr_objectives_ = nbr_objectives;
		decisionVectorBlock_ = decisionVectorBlock;
		expData =data;
		sbse_param_ = sbse_param;
	}
	public int getNbrObjectives (){
		return nbr_objectives_;
	}
	public  List<Integer[]> getDecisionVectorBlock (){
		return decisionVectorBlock_;
	}
	public void setExperimentData (ExperimentData expdata){
		expData = expdata;
	}
	public ExperimentData  getExperimentData  (){
		return expData;
	}
	public SbseParameter  getSbseParameter  (){
		return sbse_param_;
	}
	

}
