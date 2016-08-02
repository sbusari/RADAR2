package radar.optimisation.algorithm;


import java.util.ArrayList;
import java.util.List;

import radar.enumeration.SolutionType;
import radar.model.Alternative;
import radar.model.Model;
import radar.model.SolutionValues;
import radar.optimisation.decisionvector.DecisionVector;
import radar.optimisation.decisionvector.XOR_DecisionVector;
import radar.simulation.Simulator;

public class ExhaustiveSearch extends Algorithm{
	Model semanticModel_;
	DecisionVector decisionVector_;
	List<SolutionValues> objValues_;
	List<Alternative> alternatives_;
	public ExhaustiveSearch (Model model){
		semanticModel_ = model;
		decisionVector_ = getDecisionVector(model);
		alternatives_ = decisionVector_.getAllSolutions();
		objValues_ = new ArrayList<SolutionValues>();
		
	}
	private DecisionVector getDecisionVector ( Model model){
		DecisionVector result = null;
		if (model.getSolutionType().equals(SolutionType.XOR.toString())){
			result = new XOR_DecisionVector(model);
		}
		return result;
	}
	@Override
	public SolutionValues solve(Alternative s) {
		SolutionValues value = new SolutionValues ();
		Simulator simulator = new Simulator(s, semanticModel_);
		value =  simulator.computeObjectivesValues();	
		semanticModel_.addAlternative(s);
		semanticModel_.resetSimulationVariables();
		return value;
	}
	public List<SolutionValues> solveAll() {
		for (int i = 0; i < alternatives_.size(); i++){
			SolutionValues value = new SolutionValues ();
			Simulator simulator = new Simulator(alternatives_.get(i), semanticModel_);
			value =  simulator.computeObjectivesValues();	
			semanticModel_.addAlternative(alternatives_.get(i));
			semanticModel_.resetSimulationVariables();
			objValues_.add(value);
		}
		return objValues_;
	}
	
	public List<Alternative> getAlternatives (){
		return alternatives_;
	}
	
}
