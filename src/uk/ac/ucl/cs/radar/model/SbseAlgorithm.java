package uk.ac.ucl.cs.radar.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jmetal.core.SolutionSet;
import jmetal.core.Variable;
import jmetal.metaheuristics.nsgaII.NSGAII_Solver;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.JMException;

public class SbseAlgorithm  extends Algorithm{
	
	Model semanticModel_;
	DecisionVector decisionVector_;
	List<SolutionValues> evaluatedSolutions_;
	ExperimentData expData_;
	String algorithm_;
	public SbseAlgorithm(){}
	public SbseAlgorithm (String algorithm, ExperimentData expData){
		semanticModel_ = expData.getSemanticModel();
		decisionVector_ = getDecisionVector(semanticModel_);
		evaluatedSolutions_ = new ArrayList<SolutionValues>();
		expData_ = expData;
		algorithm_ = algorithm;
	}
	@Override
	public List<SolutionValues> solve() {
		List<SolutionValues> results = new ArrayList<SolutionValues>();
		try {
			// solutions for each run
			SolutionSet approximateSolutions = getApproximateSolution ();
			for (int i = 0; i <approximateSolutions.size(); i ++ ){
				SolutionValues solutionvalues = convertSbseSolutionToRadarSolution(approximateSolutions.get(i));
				results.add(solutionvalues);
			}
			SolutionSet evaluatedSolutions = approximateSolutions.getEvaluatedSolutions();
			for (int i = 0; i <evaluatedSolutions.size(); i ++ ){
				SolutionValues evaluatedSolution = convertSbseSolutionToRadarSolution(evaluatedSolutions.get(i));
				evaluatedSolutions_.add(evaluatedSolution);
			}
			runTime_ = evaluatedSolutions.getExecutionTime();
			solutionQuality_ = approximateSolutions.getSolutionQuality();
		} catch (SecurityException | ClassNotFoundException | JMException
				| IOException e) {
			e.printStackTrace();
		}
		return results;
	}
	SolutionValues convertSbseSolutionToRadarSolution (jmetal.core.Solution solution) throws JMException{
		SolutionValues result = new SolutionValues();
		List<Objective> objectives = new ArrayList<Objective>(semanticModel_.getObjectives().values());
		Map<Objective, Double> objValue = new LinkedHashMap<Objective, Double>();
		for (int i =0 ; i < objectives.size(); i ++){
			objValue.put(objectives.get(i), solution.getObjective(i));
		}
		result.setObjectiveValue(objValue);
		Solution selectedSolution = convertDecisionVectorToSolution(solution.getArrayBitVectorVariables(), solution.getProblem().getSbseData());	
		selectedSolution.setSemanticModel(semanticModel_);
		result.setSolution(selectedSolution);
		return result;
	}
	public SolutionSet getApproximateSolution() throws SecurityException, ClassNotFoundException, JMException, IOException {
		List<Integer []> decisionVectorBlock = decisionVector_.encodeSBSEDecisionVector();
		SbseData sbsedata =  new SbseData (this.expData_, this.semanticModel_.getObjectives().size(),decisionVectorBlock );
		SolutionSet optimalSolutions = new SolutionSet();
		switch (algorithm_) {
			case "NSGAII": {
				optimalSolutions = new NSGAII_Solver().solve(sbsedata);
				break;
			}
			default:
				System.out.println(" No optimisation algorithm specified.");
		}
		return optimalSolutions;
	}
	public List<SolutionValues> getAllSolutionValues (){
		return evaluatedSolutions_;
	}
	
	public String decisionVectorToString  (jmetal.core.Solution solution) throws JMException{
		Solution s = convertDecisionVectorToSolution(solution.getArrayBitVectorVariables(), solution.getProblem().getSbseData());
		String result ="";
		for (Map.Entry<Decision, String> entry : s.getSelection().entrySet()){
			result +=  entry.getValue() + ",";
		}
		result= result.substring(0, result.length()-1);
		return result;
	}
	public jmetal.core.Solution evaluate (jmetal.core.Solution solution) throws JMException{
		List<Variable[]> variable  = solution.getArrayBitVectorVariables(); 
		Solution s = convertDecisionVectorToSolution(variable, solution.getProblem().getSbseData());
		Simulator simulator = new Simulator(s, solution.getProblem().getSbseData().getExperimentData().getSemanticModel());
		Map<Objective, Double> fitness =  simulator.computeFitnessValues();
		int i =0;
		for (Map.Entry<Objective, Double> entry: fitness.entrySet()){
			if (entry.getKey().getIsMinimisation() == false){
				 solution.setObjective(i, entry.getValue() * -1);
			}else{
				 solution.setObjective(i, entry.getValue());
			}
			solution.setMargin(i, entry.getKey().getMargin());
			i++;
		}
		return solution;
	}
	Solution convertDecisionVectorToSolution (List<Variable[]> decisionVector, SbseData data ) throws JMException{
		Solution result = new Solution ();
		Map<String, Decision> decisions = data.getExperimentData().getSemanticModel().getDecisions();
		List<Decision> decisionList = new ArrayList<Decision>(decisions.values());
		for (int i =0; i < decisionVector.size(); i++ ){
			 String selectedOption = "";
			 for (int j =0; j < decisionVector.get(i).length; j++ ){
				 if ((int)decisionVector.get(i)[j].getValue() == 1){
					 selectedOption = decisionList.get(i).getOptions().get(j);
				 }
			 }
			 result.addDecision(decisionList.get(i), selectedOption);
		}
		return result;
	}
	
}
