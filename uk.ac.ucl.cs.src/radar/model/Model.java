package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import radar.information.analysis.InformationAnalysis;


public class Model implements ModelVisitorElement {
	
	public Model (){
		objectives_ = new LinkedHashMap<String, Objective>();
		qualityVariables_ = new LinkedHashMap<String, QualityVariable>();
		parameters_ =  new ArrayList<String>();
		decisions_ = new LinkedHashMap<String, Decision>();
		qualityVariableStack_ = new LinkedHashMap<String,QualityVariable>();
	}
	private String modelName_;
	private Map<String, Objective> objectives_;
	private Map<String, QualityVariable> qualityVariables_;
	private List<String> parameters_;
	private Map<String, Decision> decisions_;
	private Objective infoValueObjective_;
	private Objective subgraphObjective;
	private int noOfSimulation_;
	private Map<String, QualityVariable> qualityVariableStack_;
	// needed to maintain unique solution ID in getAllSolutions
	private int solutionCounter_;
	public void setModelName(String modelName ){
		modelName_ =modelName;
	}
	public String getModelName(){
		return modelName_;
	}
	public void addQualityVariables(String qv_name, QualityVariable qualityVariable){
		qualityVariables_.put(qv_name, qualityVariable);
	}
	public Map<String, QualityVariable> getQualityVariables(){
		return qualityVariables_;
	}
	public void addParameters(String param_name){
		if (!parameters_.contains(param_name)){
			parameters_.add(param_name);
		}
	}
	public void addVariableToStack (QualityVariable qv){
		qualityVariableStack_.put(qv.getLabel(), qv);
	}
	public Map<String, QualityVariable> getStackedVariable (){
		return qualityVariableStack_;
	}
	public List<String> getParameters(){
		return parameters_;
	}
	public void addObjective(String obj_name, Objective objective){
		objectives_.put(obj_name, objective);
	}
	public List<Objective> getObjectives (){
		return new ArrayList<Objective>(objectives_.values());
	}
	public void setDecisions(Map<String, Decision> decision){
		decisions_ = decision;
	}
	public List<Decision> getDecisions (){
		return new ArrayList<Decision>(decisions_.values());
	}
	public void setInfoValueObjective (Objective objective){
			infoValueObjective_ = objective;
	}
	public Objective getInfoValueObjective (){
		return infoValueObjective_ ;
	}
	public void setSubGraphObjective (Objective subGraphObj){
		subgraphObjective = subGraphObj;
	}
	public Objective getSubGraphObjective (){
		return subgraphObjective ;
	}
	public void setNbr_Simulation(int noOfSimulation) {
		noOfSimulation_ = noOfSimulation;
	}
	public int getNbr_Simulation() {
		return noOfSimulation_;
	}
	public int getSolutionCount(){
		return solutionCounter_++;
	}
	public int getSolutionSpace (){
		int result =1;
		for (Map.Entry<String , Decision> entry: this.decisions_.entrySet()){
			result *= entry.getValue().getOptions().size();
		}
		return result;
	}
	public double[] evaluate (List<Objective> objectives, Solution s){
		s.setSemanticModel(this);
		double [] objectiveValues = new double [objectives.size()];
		for (int i =0; i < objectives.size(); i ++){
			Objective obj = objectives.get(i);
			double value = obj.evaluate(s);
			objectiveValues[i] = value;
		}
		return objectiveValues;
	}
	void computeInformationValue(AnalysisResult result, Objective objective, List<Solution> solutions, List<Parameter> params){
		result.addEviObjective (objective);
		double[][] objSim = objective.getQualityVariable().simulate(solutions);
		// compute evtpi
		double evtpi = InformationAnalysis.evpi(objSim);
		result.addEVTPI(evtpi);
		// compute evppi for each quality variable in params
		for (int i=0; i <params.size(); i++){
			Parameter param = params.get(i) ;
	        double[] paramSim = param.getSimulationData();
	        double evppi = InformationAnalysis.evppi(paramSim, objSim);
	        result.addEVPPI(param.getLabel(), evppi);
		}
	}
	public static List<Parameter> getParameterList (List<String> paramNames, Model m){
		List<Parameter> parameters = new ArrayList<Parameter>();
		for (int i =0; i < paramNames.size(); i ++){
			QualityVariable qv = m.getQualityVariables().get(paramNames.get(i));			
			if (qv.getDefinition() instanceof Parameter &&  !(((Parameter)qv.getDefinition()).getDistribution() instanceof DeterministicDistribution) ){
				Parameter value = (Parameter)qv.getDefinition();
				value.setLabel(qv.getLabel());
				parameters.add(value);
			}else if (qv.getDefinition() instanceof OR_Refinement){
				Map<String, AND_Refinement> optionsExpr = ((OR_Refinement)qv.getDefinition()).getDefinition();
				for (Map.Entry<String, AND_Refinement> entry: optionsExpr.entrySet()){
					if (entry.getValue().getDefinition() instanceof Parameter && !(((Parameter)entry.getValue().getDefinition()).getDistribution() instanceof DeterministicDistribution)){
						Parameter value = (Parameter)entry.getValue().getDefinition();
						value.setLabel(qv.getLabel() + "[" +entry.getKey() + "]");
						parameters.add(value);
					}
				}
			}
		}
		return parameters;
	}
	public  SolutionSet getAllSolutions(){
		SolutionSet result = new SolutionSet();
		for (Objective obj: this.getObjectives()){
			SolutionSet solnSet = obj.getAllSolutions(this);
			result = result.merge(solnSet);
			// result.addAll(solnSet);
		}
		return result;
	}
	public void checkAcyclicity(){
		for (Objective obj: this.getObjectives()){
			obj.checkAcyclicity(this);
		}
	}
	public  List<Solution> getAllSolutionss(){
		List<Solution> solutions = new ArrayList<Solution>();
		List<Decision> allDecisions = this.getDecisions();
		List<Integer[]> selectedOptionIndices = SolutionAnalyser.generateSelectedOptionIndices (this.getDecisions());
		for (int i =0; i <selectedOptionIndices.size(); i++ ){
			Integer [] aSelectedOptionIndex = selectedOptionIndices.get(i);
			Solution s = new Solution();
			for (int j =0 ; j < aSelectedOptionIndex.length; j ++){
				Decision d = allDecisions.get(j);
				String selectedOption = allDecisions.get(j).getOptions().get(aSelectedOptionIndex[j]);
				s.addDecision(d, selectedOption);
			}
			solutions.add(s);
		}
		return solutions;
	}	@Override
	public void accept(ModelVisitor visitor, Model m) {
		if (m.getSubGraphObjective() == null){
			for (Objective obj: this.getObjectives()){
				obj.accept(visitor, m);
			}
		}else{
			m.getSubGraphObjective().accept(visitor, m);
		}
		
		visitor.visit(this);
	}
	// Generates the subgraph for any model element
	public String generateDOTRefinementGraph(ModelVisitorElement e, Model m, Objective subGraphObjective){
		RefinementGraphGenerator graphGenerator = new RefinementGraphGenerator(subGraphObjective);
		e.accept(graphGenerator,m);
		return graphGenerator.getDotString();
	}

	// Generates the refinement graph for the whole model
	public String generateDOTRefinementGraph(Model m, Objective subGraphObj){
		return generateDOTRefinementGraph(this, m,subGraphObj);
	}
	/*
	* returns true if decision d1 is dependent on selection of option 'option' 
	* in decision d0; in other words, if d1 is null every time 'option' is not 
	* selected in d0.
	* Formally: isDependent(d1, d0, option) iff
	*  for all s: solution| s.selection(d0) != option imples s.selection(d1) == null
	*/
	boolean isDependent(Decision d1, Decision d0, String option){
		// need when we have just only one decision in the model.
		boolean result = true;
		if (d1.equals(d0)){
			return false;
		}
		/*for (Solution s: this.getAllSolutions().list()){
			if(s.selection(d0) != null && !s.selection(d0).equals(option) && s.selection(d1) != null) return false;
		}*/
		for (Solution s: this.getAllSolutions().list()){
			if(s.selection(d0) != null){
				if ( !s.selection(d0).equals(option) && s.selection(d1) != null) return false;
			}else{
				result =false;
			}
		}
		return result;
	}
	/*
	* Generates the decision diagram in DOT format
	*/
	public String generateDecisionDiagram(){
		int equalDecisionAndOptionNameCounter =1;
		List<String> edges = new ArrayList<String>();
		String result = "digraph G { \n";
		for(Decision d: this.getDecisions()){
			Object o = (Object) d;
			String dID = o.toString(); 
			// String dShape = "d_" + dID + "[label=\"" + d.getDecisionLabel() +"\"]"; 
			String dShape =  "\""+ d.getDecisionLabel() +  "\"" + " [shape = polygon, sides =8] \n";
			result +=  dShape;
			for(String option: d.getOptions()){
				String newLine ="";
				if (d.getDecisionLabel().equals(option)){
					newLine = "\"" + d.getDecisionLabel() + "\"" + " -> " + "\"" + option + "_"+equalDecisionAndOptionNameCounter++ + "\"" + "[arrowhead= odot]"+ "\n";
				}else{
					newLine = "\"" + d.getDecisionLabel() + "\"" + " -> " + "\"" + option + "\"" + "[arrowhead= odot]"+ "\n";
				}
				if (!edges.contains(newLine)){
					result = result + newLine;
					edges.add(newLine);
				}
				for (Decision d1: this.getDecisions()){
					if(this.isDependent(d1, d, option)){
						String d1Shape =  "\""+ d1.getDecisionLabel() +  "\"" + " [shape = polygon, sides =8] \n";
						result +=  d1Shape;
						if (d.getDecisionLabel().equals(option)){
							newLine = "\"" + option+ "_"+equalDecisionAndOptionNameCounter++ + "\"" + " -> " + "\"" + d1.getDecisionLabel() + "\"" + "[arrowhead= dot]"+ "\n";
						}else{
							newLine = "\"" + option + "\"" + " -> " + "\"" + d1.getDecisionLabel() + "\"" + "[arrowhead= dot]"+ "\n";
						}
						
						if (!edges.contains(newLine)){
							result = result + newLine;
							edges.add(newLine);
						}
					} 
				}
			}
		}
		result += "}";
		return result;
	}
	public void objectiveExist ( String objective) throws Exception{
		if (objective != null){
			List<Objective>  objs = this.getObjectives();
			boolean exist = false;
			for (int i =0; i <objs.size(); i ++ ){
				if (objs.get(i).getLabel().equals(objective.trim())){
					exist =true;
				}
			}
			if (exist == false){
				throw new Exception ("Error: "+ "specified objective name "+ objective+ " does not exist in the model."); 
			}
		}
	}
}
