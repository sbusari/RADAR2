package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.enumeration.OptimisationType;
import radar.information.analysis.InformationValueAnalysis;
import radar.optimisation.algorithm.Algorithm;
import radar.optimisation.algorithm.ExhaustiveSearch;
import radar.plot.goal.graph.Graph;
import radar.plot.goal.graph.Node;


public class ModelAnalysisResult {

	List<Objective> obejctives_;
	List<SolutionValues> optimalSolutions_;
	List<SolutionValues> allSolutions_;
	List<String> shortlist_;
	String variableDependencyGraph_;
	String decisionDependencyGraph_;
	Map<Objective, Double> evtpi_;
	Map<Objective, Map<String, Double>> evppi_;
	Model semanticModel_;
	OptimisationType optimisationType_;
	Algorithm alg_;
	String outputDirectory_;
	public ModelAnalysisResult(Model semanticModel, OptimisationType optimisationType){
		obejctives_ = new ArrayList<Objective>(semanticModel.getObjectives().values());
		semanticModel_ = semanticModel;
		optimisationType_ =optimisationType;
		alg_ = getAlgorithm(optimisationType);
		optimalSolutions_ = new ArrayList<SolutionValues>();
		allSolutions_ =  new ArrayList<SolutionValues>();
		evppi_ = new LinkedHashMap<Objective, Map<String, Double>>();
		evtpi_ = new LinkedHashMap<Objective, Double>();
		shortlist_ = new ArrayList<String>();
		 
	}
	Algorithm getAlgorithm (OptimisationType optimisationType){
		Algorithm alg = null;
		if (optimisationType.equals(OptimisationType.EXACT)){
			alg = new ExhaustiveSearch (semanticModel_);
			alg.setSolveAll(true);
		}
		return alg;
	}
	public void anlyseByExhaustiveSearch(){	
		
		// stochastic simulation and  multi-objective optimisation
		optimalSolutions_ =  alg_.solve();
		List<Alternative> optimalSolutions = new ArrayList<Alternative>();
		for (int i =0; i < optimalSolutions_.size(); i ++){
			Alternative s = optimalSolutions_.get(i).getSolution();
			optimalSolutions.add(s);
		}
		
		
		// get all simulated solutions
		allSolutions_ =  allSimulatedSolution ();
		
		// perform information value analysis
		InformationValueAnalysis infoValueAnalysis = new InformationValueAnalysis (semanticModel_.getSimulationNumber());
		if (semanticModel_.getInfoValueObjective() != null && semanticModel_.getInfoValueObjective().size() > 0){
			
			// for each supplied objective, we perform infomation vaue analysis
			List<Objective> infoValueObjs = semanticModel_.getInfoValueObjective();
			for (Objective currentInfoValueObj : infoValueObjs){
				
				// compute evpi
				double evtpi = infoValueAnalysis.computeEVTPI(currentInfoValueObj, optimalSolutions);
				evtpi_.put(currentInfoValueObj, evtpi);
				System.out.println("evtpi for "+ currentInfoValueObj .getLabel()+ " is "+ evtpi );
				
				// compute evppi for all parameters
				List<String> params = semanticModel_.getParameters();
				Map<String, Double> evppi = new LinkedHashMap<String, Double>();
				if (params != null){
					for (int i =0; i < params.size(); i ++){
						QualityVariable qvSim= semanticModel_.getQualityVariables().get(params.get(i));
						Map<Alternative, double[]> paramSimData = qvSim.getParameterSimData();
						// get sim data of the the current currentInfoValueObj
						Map<Alternative, double[]> currentInfoValueObjSimData  = new LinkedHashMap<Alternative, double[]>();
						for (Map.Entry<Alternative, double[]> entry: paramSimData.entrySet()){
							if (entry.getKey().getInfoValueObjective().getLabel().equals(currentInfoValueObj.getLabel())){
								currentInfoValueObjSimData.put(entry.getKey(), entry.getValue());
							}
						}
						for (Map.Entry<Alternative, double[]> entry: currentInfoValueObjSimData.entrySet()){
							double value = infoValueAnalysis.computeEVPPI(currentInfoValueObj, optimalSolutions, entry.getValue());

							evppi.put(entry.getKey().getParameter(), value);
							System.out.println("evppi for  objective "+  currentInfoValueObj.getLabel()+ " and selection "+ entry.getKey().getParameter()+ " is "+ value );
						}
					}
				}
				evppi_.put(currentInfoValueObj, evppi);
			}
		}
		
		// generate variable dependency graphs
		variableDependencyGraph_ = generateVariableDependencyGraph();
		
		/*for (int i =0; i < obejctives_.size()-1 ; i++){
			DOT_Graph de = new DOT_Graph(semanticModel_);
			//de.createVariableGraphPerObjective(obejctives_.get(i));
			de.createVariableGraph();
			
			Map<String, DOT_Node> allEntries = de.getNodeList();
			for (Map.Entry<String, DOT_Node> entry: allEntries.entrySet()){
				String shape = "shape="+ entry.getValue().getShape();
				String style = (entry.getValue().getStyle() != "")?  ", style=" + entry.getValue().getStyle(): "";
				System.out.println( entry.getKey() + "[" + shape + style+ "]");
			}
			
			List<String> allEdgeEntries = de.getEdgeStatements();
			for (String entry: allEdgeEntries){
				String [] entryElement = entry.split("->");
				if (!entryElement[0].equals(entryElement[1])){
					System.out.println( entry);
				}
			}
			System.out.print("===============DONE==============");
		}*/
	

		// generate decision graph
		decisionDependencyGraph_ = generateDecisionDependencyGraph();
		
		/*DOT_Graph de = new DOT_Graph(semanticModel_);
		de.createDecisionsGraph();
		
		Map<String, DOT_Node> allEntries = de.getNodeList();
		for (Map.Entry<String, DOT_Node> entry: allEntries.entrySet()){
			String shape = "shape="+ entry.getValue().getShape();
			String style = (entry.getValue().getStyle() != "")?  ", style=" + entry.getValue().getStyle(): "";
			System.out.println( entry.getKey() + "[" + shape + style+ "]");
		}
		
		List<String> allEdgeEntries = de.getEdgeStatements();
		for (String entry: allEdgeEntries){
			String [] entryElement = entry.split("->");
			if (!entryElement[0].equals(entryElement[1])){
				System.out.println( entry);
			}
		}*/
		
	}
	
	public List<String> getShortList (){
		return shortlist_;
	}
	public List<SolutionValues> getOptimalSolutions (){
		return optimalSolutions_;
	}
	public List<SolutionValues> allSimulatedSolution (){
		return ((ExhaustiveSearch)alg_).getAllSolutionValues();
	}
	public List<Objective> getObjectives(){
		return obejctives_;
	}
	public Model getSemanticModel (){
		return semanticModel_;
	}
	public String getVariableDependencyGraph(){
		return variableDependencyGraph_;
	}
	public String getDecisionDependencyGraph(){
		return decisionDependencyGraph_;
	}
	public String generateResultHeader (){
		String result =",";
		for(Map.Entry<String ,Decision> entry: semanticModel_.getDecisions().entrySet()){
			result +=entry.getValue().getDecisionLabel() + ",";
		}
		for(Map.Entry<String, Objective> entry: semanticModel_.getObjectives().entrySet()){
			result +=entry.getValue().getLabel() + ",";
		}
		result += "Optimal";
		return result;
	}
	
	private String getSolutionsToCSV(int startIndex, List<SolutionValues> solutions, boolean isOptimal ){
		StringBuilder result = new StringBuilder();
		for (int count = startIndex; count < solutions.size(); count++) {
			String row = "";
			row += (count+1 + ",");
	        String record = "";
	        
	        for(Map.Entry<Decision, String> entry: solutions.get(count).getSolution().getGlobalSelection().entrySet()){
	        	record += entry.getValue() +",";
	        }
	        
	        for(Map.Entry<Objective, Double> entry: solutions.get(count).getObjectiveValue().entrySet()){
	        	double value= (entry.getKey().getIsMinimisation())? entry.getValue():  (-1 * entry.getValue());
	        	record += value + ",";
	        }
	        
	        row += (record);
	        String optimal = isOptimal == true ? "Yes" :"No";
	        row += (optimal + "\n");
	        result.append(row);
	    }
		return result.toString();
	}
	public String shortlistToCSV (){
		StringBuilder solutions = new StringBuilder();
		String shortlistHeader = generateResultHeader();
		solutions.append(shortlistHeader);
		solutions.append("\n");
		String optimalSolutions = getSolutionsToCSV(0, optimalSolutions_, true);
		solutions.append(optimalSolutions);
		List<SolutionValues> nonOptimalSolutions =getNonOptimalSolutions();
		String otherSolutions = getSolutionsToCSV(optimalSolutions_.size()+1, nonOptimalSolutions, false);
		solutions.append(otherSolutions);
		return solutions.toString();
	}
	private List<SolutionValues> getNonOptimalSolutions (){
		
		List<Integer> solutionIndexToRemove = new ArrayList<Integer>();
		for (int i =0; i < allSolutions_.size(); i++){
			for (int j=0; j < optimalSolutions_.size(); j++){
				if (allSolutions_.get(i).getSolution().selectionToString().equals(optimalSolutions_.get(j).getSolution().selectionToString())){
					solutionIndexToRemove.add(i);
				}
			}
		}
		
		List<SolutionValues> nonOptimalSolutions = new ArrayList<SolutionValues>();
		for (int i =0; i<allSolutions_.size(); i++ ){
			 if( !solutionIndexToRemove.contains((Integer)i) ){
				 nonOptimalSolutions.add(allSolutions_.get(i));
			 }
		}
		return nonOptimalSolutions;
	}
	public String  evtpiToString (){
		Map<Objective, Double> evtpi = evtpi_;
		String result = "";
		for (Map.Entry<Objective, Double> entry: evtpi.entrySet()){
			result += entry.getKey().getLabel() + ", " + entry.getValue() + "\n";
		}
		return result;
	}
	
	public String  evppiToString (){
		Map<Objective, Map<String, Double>> evppi = evppi_;
		String result = "";
		for (Map.Entry<Objective, Map<String, Double>> entry: evppi.entrySet()){
			result += entry.getKey().getLabel() + "\n ";
			for (Map.Entry<String, Double> valueEntry : entry.getValue().entrySet()){
				result += valueEntry.getKey() + ", " + valueEntry.getValue() + "\n";
			}
		}
		return result;
	}
	public String generateDecisionDependencyGraph (){
		String result ="digraph G { \n";
		result += "rankdir = BT; \n";
		result += "edge[dir=back]; \n";

		Graph de = new Graph(semanticModel_);
		de.createDecisionsGraph();
		
		Map<String, Node> allEntries = de.getNodeList();
		for (Map.Entry<String, Node> entry: allEntries.entrySet()){
			String shape = "shape="+ entry.getValue().getShape();
			String style = (entry.getValue().getStyle() != "")?  ", style=" + entry.getValue().getStyle(): "";
			System.out.println( entry.getKey() + "[" + shape + style+ "]");
			result += entry.getKey() + "[" + shape + style+ "]" + "\n";
		}
		
		List<String> allEdgeEntries = de.getEdgeStatements();
		for (String entry: allEdgeEntries){
			String [] entryElement = entry.split("->");
			if (!entryElement[0].equals(entryElement[1])){
				result += entry + "\n";
				System.out.println( entry);
			}
		}
		result += "}";
		return result;
	}
	public String generateVariableDependencyGraph (){
		String result ="digraph G { \n";
		result += "rankdir = BT; \n";
		result += "edge[dir=forward]; \n";
		
		Graph de = new Graph(semanticModel_);
		de.createVariableGraph();
		
		Map<String, Node> allEntries = de.getNodeList();
		for (Map.Entry<String, Node> entry: allEntries.entrySet()){
			String shape = "shape="+ entry.getValue().getShape();
			String style = (entry.getValue().getStyle() != "")?  ", style=" + entry.getValue().getStyle(): "";
			System.out.println( entry.getKey() + "[" + shape + style+ "]");
			result +=  entry.getKey() + "[" + shape + style+ "]" + "\n";
		}
		
		List<String> allEdgeEntries = de.getEdgeStatements();
		for (String entry: allEdgeEntries){
			String [] entryElement = entry.split("->");
			if (!entryElement[0].equals(entryElement[1])){
				result += entry + "\n";
				System.out.println( entry);
			}
		}
		result += "}";
		return result;
	}
	public String decisionsToCSV ( ){
		StringBuilder decision = new StringBuilder ();
		if (semanticModel_ != null){
			int count =1;
			decision.append("ID , Decision , Options\n");
			for (Map.Entry<String, Decision> entry: semanticModel_.getDecisions().entrySet()){
				StringBuilder row = new StringBuilder();
				row.append(count + ",");
				row.append(entry.getKey() + ",");
				String options ="";
				options ="[";
				List<String> decisionOptions = entry.getValue().getOptions();
				for (String option : decisionOptions){
					options += option + ", ";
				}
				options = options.trim(); 
				options = options.substring(0, options.length()-1);
				options +="]";
				row.append(options + "\n");
				decision.append(row.toString());
				count++;
			}
		}
		return decision.toString();
	}
	
 }
