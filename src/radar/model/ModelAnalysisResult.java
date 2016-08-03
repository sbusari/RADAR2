package radar.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.enumeration.OptimisationType;
import radar.information.analysis.InformationValueAnalysis;
import radar.optimisation.algorithm.Algorithm;
import radar.optimisation.algorithm.ExhaustiveSearch;


public class ModelAnalysisResult {

	List<Objective> obejctives_;
	List<SolutionValues> optimalSolutions_;
	List<SolutionValues> allSolutions_;
	List<String> shortlist_;
	double evtpi_;
	Map<String, Double> evppi_;
	Model semanticModel_;
	OptimisationType optimisationType_;
	Algorithm alg_;
	public ModelAnalysisResult(Model semanticModel, OptimisationType optimisationType){
		obejctives_ = new ArrayList<Objective>(semanticModel.getObjectives().values());
		semanticModel_ = semanticModel;
		optimisationType_ =optimisationType;
		alg_ = getAlgorithm(optimisationType);
		optimalSolutions_ = new ArrayList<SolutionValues>();
		allSolutions_ =  new ArrayList<SolutionValues>();
		evppi_ = new LinkedHashMap<String, Double>();
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
		// compute objective values
		List<SolutionValues> optimalsolutionValues =  alg_.solve();
		
		List<Alternative> optimalSolutions = new ArrayList<Alternative>();
		for (int i =0; i < optimalsolutionValues.size(); i ++){
			Alternative s = optimalsolutionValues.get(i).getSolution();
			optimalSolutions.add(s);
		}
		
		// perform information analsysis
		InformationValueAnalysis infoValueAnalysis = new InformationValueAnalysis (semanticModel_.getSimulationNumber());
		
		
		if (semanticModel_.getInfoValueObjective() != null && semanticModel_.getInfoValueObjective().size() > 0){
			List<Objective> infoValueObjs = semanticModel_.getInfoValueObjective();
			for (Objective currentInfoValueObj : infoValueObjs){
				// compute evpi
				evtpi_ = infoValueAnalysis.computeEVTPI(currentInfoValueObj, optimalSolutions);
				System.out.println("evtpi for "+ currentInfoValueObj .getLabel()+ " is "+ evtpi_ );
				
				// compute evppi for all parameters
				List<String> params = semanticModel_.getParameters();
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
							double evppi = infoValueAnalysis.computeEVPPI(currentInfoValueObj, optimalSolutions, entry.getValue());
							evppi_.put(params.get(i), evppi);
							System.out.println("evppi for  objective "+  currentInfoValueObj.getLabel()+ " and parameter "+ entry.getKey().getParameter()+ " is "+ evppi );
						}
						
						
					}
				}	
			}
			
		}
		// plot goal graphs and decision graph
		
	}
	
	public List<String> getShortList (){
		return shortlist_;
	}
	public double getEvtpi (){
		return evtpi_;
	}
	public Map<String, Double> getEvppi (){
		return evppi_;
	}
	public List<SolutionValues> getOptimalSolutions (){
		return optimalSolutions_;
	}
	public List<Alternative> allSolution (){
		return ((ExhaustiveSearch)alg_).getAlternatives();
	}
	public List<Objective> getObjectives(){
		return obejctives_;
	}
	public String generateResultHeader (){
		String result ="";
		for(Map.Entry<String ,Decision> entry: semanticModel_.getDecisions().entrySet()){
			result +=entry.getValue().getDecisionLabel() + ",";
		}
		for(Map.Entry<String, Objective> entry: semanticModel_.getObjectives().entrySet()){
			result +=entry.getValue() + ",";
		}
		result += "Optimal";
		return result;
	}
	
	private String getSolutionsToCSV(int startIndex, List<SolutionValues> solutions ){
		StringBuilder result = new StringBuilder();
		for (int count = startIndex; count < solutions.size(); count++) {
			String row = "";
			row += (count+1 + ",");
	        String record = "";
	        
	        for(Map.Entry<Decision, String> entry: solutions.get(count).getSolution().getGlobalSelection().entrySet()){
	        	record += entry.getValue() +",";
	        }
	        
	        for(Map.Entry<Objective, Double> entry: solutions.get(count).getObjectiveValue().entrySet()){
	        	record += entry.getValue() +",";
	        }
	        
	        row += (record);
	        row += ("Yes\n");
	        result.append(row);
	    }
		return result.toString();
	}
	public String shortlistToCSV (){
		StringBuilder solutions = new StringBuilder();
		String shortlistHeader = generateResultHeader();
		solutions.append(shortlistHeader);
		solutions.append("\n");
		String optimalSolutions = getSolutionsToCSV(0, optimalSolutions_);
		solutions.append(optimalSolutions);
		List<SolutionValues> nonOptimalSolutions =getNonOptimalSolutions();
		String otherSolutions = getSolutionsToCSV(optimalSolutions_.size()+1, nonOptimalSolutions);
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
