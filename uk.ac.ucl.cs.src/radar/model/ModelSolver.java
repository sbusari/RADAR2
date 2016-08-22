package radar.model;
import java.util.List;
import java.util.Map;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
public class ModelSolver {
	/**
	 * Solves the model and saves the analysis results. 
	 * @param m semantic model obtained during parsing.
	 */
	public static AnalysisResult solve(Model m){

		// get all objectives
		List<Objective> objectives = m.getObjectives();
			
		// check for cylic dependencies in quality variables.
		try{
			m.getCyclicDependentVariables();
		}catch (Exception e){
			throw new RuntimeException (e.getMessage());
		}
		
		// get all decisions
		List<Decision> decisions = m.getDecisions();
		
		// instantiate the result object
		AnalysisResult result = new AnalysisResult(objectives,decisions);
		
		long start = System.currentTimeMillis();
		
		// get all solutions
		List<Solution> allSolutions = m.getAllSolutions().list(); 
		result.addAllSolutions(allSolutions);
		
		// solution space
		result.addSolutionSpace(m.getSolutionSpace());

		// add subgraph obejective
		result.addSubGraphObejctive(m.getSubGraphObjective());
		
		// Evaluate objectives for all solutions
		int i =0;
		for (Solution s: allSolutions){
			//result.addEvaluation(s, m.evaluate(objectives, s));	
			result.addEvaluation(s, new Simulator().evaluate(objectives, s,m));	
			System.out.println("Solution index "+ i);
			i++;
		}

		// add -ve sign for maximisaiton
		Map<Solution, double[]> evaluatedSolutions = m.addMaximisationSign(result.getEvaluatedSolutions());
		
		// Shortlists Pareto-optimal solutions
		result.addShortlist(new Optimiser().getParetoSet(evaluatedSolutions));
		
		long end = System.currentTimeMillis();
		long runTime = (end - start) / 1000;
		result.addRunTime(runTime);
		
		// Computes Value of Information
		Objective infoValueObjective = m.getInfoValueObjective();
		
		if (infoValueObjective != null ){
			List<String> paramNames = m.getParameters();
			List<Parameter> parameters = Model.getParameterList(paramNames, m);
			m.computeInformationValue(result,infoValueObjective, result.getShortListSolutions(), parameters);
		}
		
		result.addSubGraphObejctive(m.getSubGraphObjective());
		result.addEviObjective(infoValueObjective);
		//
		return result;
	}
}
