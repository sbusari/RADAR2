package radar.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import radar.information.analysis.InformationAnalysis;


public class Model implements ModelVisitorElement {
	
	public Model (){
		objectives_ = new LinkedHashMap<String, Objective>();
		qualityVariables_ = new LinkedHashMap<String, QualityVariable>();
		parameters_ =  new ArrayList<String>();
		decisions_ = new LinkedHashMap<String, Decision>();
		alternative_ = new ArrayList<Solution> ();
		
	}
	private String modelName_;
	private Map<String, Objective> objectives_;
	private Map<String, QualityVariable> qualityVariables_;
	private List<String> parameters_;
	private Map<String, Decision> decisions_;
	private List<Solution> alternative_;
	private Objective infoValueObjective_;
	private int noOfSimulation_;
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
	public void setAlternative(List<Solution>  solutions){
		alternative_ =solutions;
	}
	public List<Solution>  getAlternative (){
		return alternative_;
	}
	public void addAlternative(Solution a){
		alternative_.add(a);
	}
	public void setInfoValueObjective (Objective objective){
			infoValueObjective_ = objective;
	}
	public Objective getInfoValueObjective (){
		return infoValueObjective_ ;
	}
	public void setNbr_Simulation(int noOfSimulation) {
		noOfSimulation_ = noOfSimulation;
	}
	public int getNbr_Simulation() {
		return noOfSimulation_;
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
	InfoValueAnalysisResult computeInformationValue(Objective objective, List<Solution> solutions, List<Parameter> params){
		
		InfoValueAnalysisResult result = new InfoValueAnalysisResult(objective.getLabel(), objective.getIsMinimisation());
		double[][] objSim = objective.getQualityVariable().simulate(solutions);
		// compute evtpi
		double evtpi = InformationAnalysis.evpi(objSim);
		result.setEVTPI(evtpi);
		// compute evppi for each quality variable in params
		for (int i=0; i <params.size(); i++){
			Parameter param = params.get(i) ;
	        double[] paramSim = param.getSimulationData();
	        double evppi = InformationAnalysis.evppi(paramSim, objSim);
	        result.addEVPPI(param.getLabel(), evppi);
		}
		return result;
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
	public  List<Solution> getAllSolutions(){
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
		for (Objective obj: this.getObjectives()){
			obj.accept(visitor, m);
		}
		visitor.visit(this);
	}
	// Generates the subgraph for any model element
	public String generateDOTRefinementGraph(ModelVisitorElement e, Model m){
		RefinementGraphGenerator graphGenerator = new RefinementGraphGenerator();
		e.accept(graphGenerator,m);
		return graphGenerator.getDotString();
	}

	// Generates the refinement graph for the whole model
	public String generateDOTRefinementGraph(Model m){
		return generateDOTRefinementGraph(this,m);
	}
	
	


}
