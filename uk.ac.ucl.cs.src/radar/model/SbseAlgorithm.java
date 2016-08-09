package radar.model;

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
	SolutionQuality solutionQuality_;
	public SbseAlgorithm(){}
	public long getRunTime (){
		return runTime_;
	}
	public SolutionQuality getSolutionQuality (){
		return solutionQuality_;
	}
	private List<Integer[]> getDecisionVectorBlock (Model m){
		return XOR_DecisionVector.encodeSBSEDecisionVector(m);
		
	}
	public Map<Solution, double []> solve(String algorithm, Model semanticModel,  ExperimentData expData, SbseParameter param, List<Integer[]> decisionVectorBlock ) {
		List<SolutionValues> results = new ArrayList<SolutionValues>();
		try {
			// solutions for each run
			SolutionSet approximateSolutions = getApproximateSolution ( algorithm, semanticModel ,  expData,  param, getDecisionVectorBlock (semanticModel));
			for (int i = 0; i <approximateSolutions.size(); i ++ ){
				SolutionValues solutionvalues = convertSbseSolutionToRadarSolution(approximateSolutions.get(i),semanticModel);
				results.add(solutionvalues);
			}
			SolutionSet evaluatedSolutions = approximateSolutions.getEvaluatedSolutions();
			for (int i = 0; i <evaluatedSolutions.size(); i ++ ){
				SolutionValues evaluatedSolution = convertSbseSolutionToRadarSolution(evaluatedSolutions.get(i),semanticModel);
				evaluatedSolutions_.add(evaluatedSolution);
			}
			runTime_ = evaluatedSolutions.getExecutionTime();
			solutionQuality_ = approximateSolutions.getSolutionQuality();
		} catch (SecurityException | ClassNotFoundException | JMException
				| IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	SolutionValues convertSbseSolutionToRadarSolution (jmetal.core.Solution solution, Model semanticModel ) throws JMException{
		SolutionValues result = new SolutionValues();
		List<Objective> objectives = semanticModel.getObjectives();
		Map<Objective, Double> objValue = new LinkedHashMap<Objective, Double>();
		for (int i =0 ; i < objectives.size(); i ++){
			objValue.put(objectives.get(i), solution.getObjective(i));
		}
		result.setObjectiveValue(objValue);
		Solution selectedSolution = convertDecisionVectorToSolution(solution.getArrayBitVectorVariables(), semanticModel);	
		selectedSolution.setSemanticModel(semanticModel);
		result.setSolution(selectedSolution);
		return result;
	}
	public SolutionSet getApproximateSolution(String algorithm,  Model m , ExperimentData data, SbseParameter param, List<Integer[]> decisionVectorBlock) throws SecurityException, ClassNotFoundException, JMException, IOException {
		SolutionSet optimalSolutions = new SolutionSet();
		switch (algorithm) {
			case "NSGAII": {
				optimalSolutions = new NSGAII_Solver().solve( m , data, param, decisionVectorBlock);
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
		Solution s = convertDecisionVectorToSolution(solution.getArrayBitVectorVariables(), solution.getProblem().getSemanticModel());
		String result ="";
		for (Map.Entry<Decision, String> entry : s.getSelection().entrySet()){
			result +=  entry.getValue() + ",";
		}
		result= result.substring(0, result.length()-1);
		return result;
	}
	public jmetal.core.Solution evaluate (jmetal.core.Solution solution, Model semanticModel) throws JMException{
		List<Variable[]> variable  = solution.getArrayBitVectorVariables(); 
		Solution s = convertDecisionVectorToSolution(variable,semanticModel);
		Simulator simulator = new Simulator(s, semanticModel);
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
	Solution convertDecisionVectorToSolution (List<Variable[]> decisionVector, Model semanticModel) throws JMException{
		Solution result = new Solution ();
		List<Decision> decisionList = semanticModel.getDecisions();
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
