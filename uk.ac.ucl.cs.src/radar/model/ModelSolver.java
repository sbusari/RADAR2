package radar.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModelSolver {
	
	public static AnalysisResult solve(Model m){

		List<Objective> objectives = m.getObjectives();
		List<Solution> allSolutions = m.getAllSolutions().list(); 
		List<Decision> decisions = m.getDecisions();
		
		AnalysisResult result = new AnalysisResult(objectives,decisions);
		
		// solution space
		result.addSolutionSpace(m.getSolutionSpace());

		// add subgraph obejective
		result.addSubGraphObejctive(m.getSubGraphObjective());
		
		long start = System.currentTimeMillis();

		for (int i =0; i < allSolutions.size(); i++){
			System.out.println("Solution "+ i+ ": "+ allSolutions.get(i).selectionToString() );
		}
		
		// Evaluate objectives for all solutions
		int i =0;
		for (Solution s: allSolutions){
			result.addEvaluation(s, m.evaluate(objectives, s));	
			System.out.println("Solution index "+ i);
			i++;
		}
		
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