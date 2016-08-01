package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.jmetal.core.Variable;
import radar.simulation.Simulator;

public class ModelAnalysisResult {

	List<Objective> obejctives_;
	List<Alternative> solutions_;
	double evtpi_;
	double evppi_;
	Model semanticModel_;
	public ModelAnalysisResult(Model semanticModel){
		semanticModel_ = semanticModel;
	}
	
	public ObjectiveValues computeObjectiveValues (Alternative selectedAlternative){
		 ObjectiveValues objValues = new ObjectiveValues();
		 ArrayList<Variable[]> variable  = null;//solution.getArrayBitVectorVariables(); 
		 
		 Alternative a = null;  //radar.model.AlternativeAnalyser.convertDecisionVectorToSolution(variable, semanticModel_);
		 Simulator simulator = new Simulator(a, semanticModel_);
		 objValues=  simulator.computeObjectivesValues();
		 semanticModel_.addAlternative(a);
		 resetSimulationVariables();
		 return objValues;
		 
	}
	// since we were getting out memory error
	private void resetSimulationVariables(){
		 Map<String, QualityVariable> qvList = semanticModel_.getQualityVariables();
		 for (Map.Entry<String, QualityVariable> entry: qvList.entrySet()){
			 if( !semanticModel_.getInfoValueObjectiveName().equals(entry.getValue().getLabel()) ){
				entry.getValue().setSimData(new LinkedHashMap<Alternative, double[]>());
			 }
		 }
	 }
 }
