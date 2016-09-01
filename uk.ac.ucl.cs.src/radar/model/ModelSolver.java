package radar.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
public class ModelSolver {
	/**
	 * Solves the model and saves the analysis results. 
	 * @param m semantic model obtained during parsing.
	 * @return analysis result.
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
		System.out.println("Finished checking cyclic dependecies");
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
		System.out.println("Evaluation and computation of optimal solutions to begin");
		int i =0;
		for (Solution s: allSolutions){
			//result.addEvaluation(s, m.evaluate(objectives, s));	
			result.addEvaluation(s, new Simulator().evaluate(objectives, s,m));	
			
			i++;
		}
		
		// add -ve sign for maximisaiton
		Map<Solution, double[]> evaluatedSolutions = m.addMaximisationSign(result.getEvaluatedSolutions());
		
		// Shortlists Pareto-optimal solutions
		result.addShortlist(new Optimiser().getParetoSet(evaluatedSolutions, objectives));
		System.out.println("Optimal solutions computed");
		
		long end = System.currentTimeMillis();
		long runTime = (end - start) / 1000;
		result.addRunTime(runTime);
		
		result.addNumberOfVariables(m.getQualityVariables().size());
		result.addNumberOfDecisions(m.getDecisions().size());
		
		// Computes Value of Information
		Objective infoValueObjective = m.getInfoValueObjective();
		List<String> paramNames = m.getParameters();
		List<Parameter> parameters = Model.getParameterList(paramNames, m);
		int nbrParam = parameters.size();
		
		if (infoValueObjective != null ){
			InformationValueAnalyser.computeInformationValue(result,infoValueObjective, result.getShortListSolutions(), parameters);;
		}
		result.addNumberOfParameters(nbrParam);
		result.addSubGraphObejctive(m.getSubGraphObjective());
		result.addEviObjective(infoValueObjective);
		return result;
	}
}
