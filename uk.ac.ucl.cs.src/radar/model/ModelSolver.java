package radar.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.utilities.Helper;

public class ModelSolver {
	
	public static AnalysisResult solve(Model m){

		List<Objective> objectives = m.getObjectives();
		try{
			m.checkAcyclicity();
		}catch (Exception e){
			throw new RuntimeException (e.getMessage());
		}
		
		
		
		
		
		List<Decision> decisions = m.getDecisions();
		
		AnalysisResult result = new AnalysisResult(objectives,decisions);
		
		long solutionGenStart = System.currentTimeMillis();
		List<Solution> allSolutions = m.getAllSolutions().list(); 
		//List<Solution> allSolutions = m.getAllSolutionss();
		result.addAllSolutions(allSolutions);
		
		long solutionGenEnd = System.currentTimeMillis();
		long solutionGenRunTime = (solutionGenEnd - solutionGenStart) / 1000;
		System.out.println("Solution Gen runtime is "+ solutionGenRunTime );
		
		// solution space
		result.addSolutionSpace(m.getSolutionSpace());

		// add subgraph obejective
		result.addSubGraphObejctive(m.getSubGraphObjective());
		
		long start = System.currentTimeMillis();

		for (int i =0; i < allSolutions.size(); i++){
			System.out.println("Solution "+ i+ ": "+ allSolutions.get(i).selectionToString() );
			String sol = "Solution "+ i+ ": "+ allSolutions.get(i).selectionToString();
			/*try {
				Helper.printResults("/Users/INTEGRALSABIOLA/Documents/JavaProject/RADAR/uk.ac.ucl.cs.results/", sol, "Sol"+ m.getModelName(), true);
			} catch (IOException e) {
				//System.out.println(e.getMessage());
			}*/
		}
		
		// Evaluate objectives for all solutions
		int i =0;
		for (Solution s: allSolutions){
			result.addEvaluation(s, m.evaluate(objectives, s));	
			System.out.println("Solution index "+ i);
			i++;
		}
		System.out.println("Solution Gen runtime is "+ solutionGenRunTime );
		// add -ve sign for maximisaiton
		Map<Solution, double[]> evaluatedSolutions = addMaximisationSign(result.getEvaluatedSolutions(), objectives);
			
		// Shortlists Pareto-optimal solutions
		result.addShortlist(new Pareto().getParetoSet(evaluatedSolutions));
		
		long end = System.currentTimeMillis();
		long runTime = (end - start) / 1000;
		result.addRunTime(runTime);
		
		// Computes Value of Information
		Objective infoValueObjective = m.getInfoValueObjective();
		
		if (infoValueObjective != null){
			List<String> paramNames = m.getParameters();
			List<Parameter> parameters = Model.getParameterList(paramNames, m);
			m.computeInformationValue(result,infoValueObjective, result.getShortListSolutions(), parameters);
		}
		
		result.addSubGraphObejctive(m.getSubGraphObjective());
		result.addEviObjective(infoValueObjective);
		//
		return result;
	}
	private static Map<Solution, double[]> addMaximisationSign (Map<Solution, double[]> evaluatedSoltions, List<Objective> objectives){
		Map<Solution, double[]> evaluatedSolutions = new LinkedHashMap<Solution, double[]> ();
		evaluatedSolutions.putAll(evaluatedSoltions);
		for (Map.Entry<Solution, double[]> entry: evaluatedSolutions.entrySet() ){
			for (int i =0; i < entry.getValue().length ; i++){
				if (objectives.get(i).getIsMinimisation() == false){
					entry.getValue()[i] = entry.getValue()[i] *-1;
				}
			}
		}
		return evaluatedSolutions;
	}
}
