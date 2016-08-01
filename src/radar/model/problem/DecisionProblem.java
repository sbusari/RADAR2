package radar.model.problem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import radar.enumeration.OptimisationDirection;
import radar.enumeration.OptimisationType;
import radar.jmetal.core.Problem;
import radar.jmetal.core.Solution;
import radar.jmetal.core.Variable;
import radar.jmetal.encodings.solutionType.ArrayBitVectorSolutionType;
import radar.jmetal.util.JMException;
import radar.model.Alternative;
import radar.model.Objective;
import radar.model.Parser;
import radar.model.QualityVariable;
import radar.simulation.Simulator;

public class DecisionProblem extends Problem {
	
	
	public DecisionProblem (String solutionType,  Parser parserEngine, OptimisationType typeOfOptimisation) throws FileNotFoundException, IOException{
		decisions_ = parserEngine.getSemanticModel().getDecisions();
		numberOfObjectives_  = parserEngine.getSemanticModel().getObjectives().size();
		problemName_= parserEngine.getSemanticModel().getModelName();
		parserEngine_ =parserEngine;
		upperLimit_ = new double[decisions_.size()] ;
		lowerLimit_ = new double[decisions_.size()] ;
		for (int i = 0; i < decisions_.size(); i++) {
	      lowerLimit_[i] = 0;
	      upperLimit_[i] = 1 ;
		} // for
		
		if (solutionType.compareTo("ArrayBitVector") == 0)
		    solutionType_ = new ArrayBitVectorSolutionType(this);
		else {
	    	System.out.println("Error: solution type " + solutionType + " invalid") ;
	    	System.exit(-1) ;
		}
	 }
	 public void evaluate(Solution solution) throws JMException {
		 ArrayList<Variable[]> variable  = solution.getArrayBitVectorVariables(); 
		 Alternative a = radar.model.AlternativeAnalyser.convertDecisionVectorToSolution(variable, parserEngine_.getSemanticModel());
		 Simulator simulator = new Simulator(a, parserEngine_.getSemanticModel());
		 Map<Objective, Double> fitness =  simulator.computeObjectiveValues();
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
		 parserEngine_.getSemanticModel().addAlternative(a);
		 resetSimulationVariables();
		 solution.setAlternative(a);
		 
	 } // evaluate
	 
	 public Solution checkViolations (Solution solution) throws JMException{
		 return solution;
	 }//constraint checking
	 private void resetSimulationVariables(){
		 Map<String, QualityVariable> qvList = parserEngine_.getSemanticModel().getQualityVariables();
		 for (Map.Entry<String, QualityVariable> entry: qvList.entrySet()){
			 if( !parserEngine_.getSemanticModel().getInfoValueObjectiveName().equals(entry.getValue().getLabel()) ){
				entry.getValue().setSimData(new LinkedHashMap<Alternative, double[]>());
			 }
		 }
	 }
	 

	 
}
