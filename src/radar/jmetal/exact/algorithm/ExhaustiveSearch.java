package radar.jmetal.exact.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import radar.model.Alternative;
import radar.model.Decision;
import radar.model.AlternativeAnalyser;
import radar.model.Parser;
import radar.utilities.SolutionDetail;
import radar.jmetal.core.Algorithm;
import radar.jmetal.core.Problem;
import radar.jmetal.core.Solution;
import radar.jmetal.core.SolutionSet;
import radar.jmetal.util.JMException;
import radar.jmetal.util.comparators.DominanceComparator;

public class ExhaustiveSearch extends Algorithm  {
	 
	 static double margin_ = 0;
	 int maxEvaluation_;
	 Parser parserEngine_;
	 List<Alternative> alternatives_;
	 List<char[]> decisionsInBitForm;
	 String alternativesObjectiveAndDecisionsPath_;
	 /**
	   * stores a <code>Comparator</code> for dominance checking
	   */
	  private static final Comparator dominance_ = new DominanceComparator();
	  
	 /**
	   * Constructor
	   * @param problem Problem to solve
	   */
	  public ExhaustiveSearch(Problem problem) {
	    super (problem) ;
		
	  }
	  public ExhaustiveSearch (Problem problem, Parser parserEngine){
		  super (problem) ;
		  parserEngine_= parserEngine;
		  alternatives_ = getAllAlternatives(parserEngine);
		  //margin_ = parserEngine_;
	  }
	  public ExhaustiveSearch (Problem problem, Parser parserEngine,String alternativesObjectiveAndDecisionsPath){
		  super (problem) ;
		  parserEngine_= parserEngine;
		  alternatives_ = getAllAlternatives(parserEngine);
		  alternativesObjectiveAndDecisionsPath_ =alternativesObjectiveAndDecisionsPath;
	  }
	  public SolutionDetail execute() throws ClassNotFoundException, JMException {	
			SolutionDetail result = new SolutionDetail();	
			result = exactParetoSet(parserEngine_, problem_ );
			return result; 
	  }
	  @SuppressWarnings("unchecked")
	  private SolutionSet findExactSolutons (SolutionSet population){
			
			SolutionSet exactolutions = new SolutionSet(population.size());
			boolean [] isPareto =  new boolean[population.size()];
			Arrays.fill(isPareto, Boolean.TRUE);
			int i =0;
			while (i < (population.size()-1)){
				int j = i+1;
				while (isPareto[i] && j < population.size()){
					int flagDominate =0;
					flagDominate =dominance_.compare(population.get(i),population.get(j));
			        if (flagDominate == -1)
			        {	// solution i dominates j, so we remove j from the list of pareto set.
			        	 isPareto[j] = Boolean.FALSE; 
			        }
			        else if (flagDominate == 1)
			        {	// solution j  dominates i,so we remove i from the list of pareto set.
			        	 isPareto[i] = Boolean.FALSE; 
			        }
				    j++;
				}			
				i++;				
			}
			for (int k =0 ; k < isPareto.length; k++){
				if(isPareto[k]){
					exactolutions.add(population.get(k));
				}
			}
			return exactolutions;
		}
	  private ArrayList<Alternative> getAllAlternatives (Parser parserEngine){
		ArrayList<Alternative> results = null;//AlternativeAnalyser.getAllAlternative(parserEngine.getSemanticModel().getDecisions());
		return results;
	  }
	  // decision block in the sense that it is initialised to zero. shows the structure of the decision block.
	  private ArrayList<char[]> getDecisionVectorBlock(Parser parserEngine){
		  Map<String, Decision> decisions = parserEngine.getSemanticModel().getDecisions();
		  ArrayList<char[]> decisionVectorBlock = new ArrayList<char[]>();
		  int i =0;
		  for (Map.Entry<String, Decision> entry : decisions.entrySet()){
			  char[]options = new char[entry.getValue().getOptions().size()];
			  for (int k =0; k < options.length; k++ ){
					options[k]='0';
			  }
			  decisionVectorBlock.add(i,options);
			  i++;
		  }
		  return decisionVectorBlock;
	  }
	  
	  private int getOptionPositionIndex (Decision d, String option){
		  int result = -1;
		  List<String> options  =  d.getOptions();
		  for (int i =0; i < options.size(); i ++){
			  if (options.get(i).equals(option)){
				  result = i;
				  return result;
			  }
		  }
		  return result;
	  }
	  private void solutionsInBitForm( Parser parserEngine, int alternativeID){
		  	decisionsInBitForm =  getDecisionVectorBlock (parserEngine);
			Map<Decision, String> selectedAlternative = alternatives_.get(alternativeID).getSelection();
			int i =0;
			for (Map.Entry<Decision, String> entry : selectedAlternative.entrySet()){
				int optionPositionIndexInDecision = getOptionPositionIndex(entry.getKey(), entry.getValue());
				if (optionPositionIndexInDecision == -1){
					throw new RuntimeException ("option position index in decision cannot be determined.");
				}
				char [] optionsForDecisionAtIndexI =  decisionsInBitForm.get(i);
				optionsForDecisionAtIndexI[optionPositionIndexInDecision]='1';
				decisionsInBitForm.set(i,optionsForDecisionAtIndexI);
				i++;
			}	
	  }
	  public SolutionDetail exactParetoSet (Parser parserEngine, Problem problem ) throws ClassNotFoundException, JMException{
		  	SolutionDetail solutionDetail = new SolutionDetail() ;
		  	int solutionSpace = alternatives_.size();
		  	SolutionSet dominatedSolution = new SolutionSet(solutionSpace);
		  	SolutionSet population = new SolutionSet(solutionSpace);
			Solution newSolution = null;
			for (int j = 0; j < population.getCapacity(); j++ ){
				solutionsInBitForm(parserEngine, j);		
				newSolution = new Solution(problem, decisionsInBitForm);					
				problem.evaluate(newSolution);
				System.out.println ("============================");
				System.out.println ("Solution Index "+ j);
				population.add(newSolution);
				dominatedSolution.add(newSolution);
			}
			SolutionSet exactSolutions = new SolutionSet();
			exactSolutions = findExactSolutons(population);
			exactSolutions.setParserEngne(parserEngine_);
			solutionDetail.setNonDominatedSolutions(exactSolutions);
			solutionDetail.setDominatedSolutions(dominatedSolution);
			solutionDetail.setGoalModelParserEngine(parserEngine_);
			return solutionDetail;
	  }
	  
}
