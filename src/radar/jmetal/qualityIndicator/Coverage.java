package radar.jmetal.qualityIndicator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import radar.jmetal.core.Solution;
import radar.jmetal.util.NonDominatedSolutionList;

public class Coverage {
	public radar.jmetal.qualityIndicator.MetricsUtil utils_;  //utils_ is used to access to the
     //MetricsUtil funcionalities
	static final double pow_ = 2.0;          //pow. This is the pow used for the
     //distances

	/**
	* Constructor.
	* Creates a new instance of the precision metric. 
	*/
	public Coverage() {
		utils_ = new radar.jmetal.qualityIndicator.MetricsUtil();
	} // Coverage
	public  NonDominatedSolutionList commonSolutionsManyObjectives (NonDominatedSolutionList  front, NonDominatedSolutionList trueParetoFront){
		
		   NonDominatedSolutionList intersection = new NonDominatedSolutionList() ;
			if(front.size() >= trueParetoFront.size()){
				Iterator<Solution> iterator = front.iterator();
				double value1, value2;
				int equal=0;			
				for(int i =0; i< front.size(); i++){
					Solution frontIndividual = front.get(i);
					for (int j=0; j <trueParetoFront.size(); j++ ){			
						Solution trueParetoFrontIndividual = trueParetoFront.get(j);
						
						for(int k=0; k < frontIndividual.getNumberOfObjectives(); k++){
							value1 = frontIndividual.getObjective(k);
						    value2 = trueParetoFrontIndividual.getObjective(k);
						     if(value1 == value2){
						    	 equal += 1;
						    	 
						     }
						}
						if(equal == frontIndividual.getNumberOfObjectives()){
							intersection.addExactSolution(trueParetoFrontIndividual);
						}
						equal =0;
					}
				}
			}else if ( trueParetoFront.size() > front.size() ){
				Iterator<Solution> iterator = front.iterator();
				double value1, value2;
				int equal=0;			
				for(int i =0; i< trueParetoFront.size(); i++){
					Solution trueParetoFrontIndividual  =  trueParetoFront.get(i);
					for (int j=0; j <front.size(); j++ ){			
						Solution frontIndividual = front.get(j);
						for(int k=0; k < frontIndividual.getNumberOfObjectives(); k++){
							value1 = frontIndividual.getObjective(k);
						    value2 = trueParetoFrontIndividual.getObjective(k);
						     if(value1 == value2){
						    	 equal += 1;					    	 
						     }						
						}
						if(equal == frontIndividual.getNumberOfObjectives()){
							intersection.addExactSolution(frontIndividual);
						}
						equal =0;
					}
				}
				
			}
		   return intersection;
			
		}
	public  NonDominatedSolutionList commonSolutionsManySolutionSpace (NonDominatedSolutionList  front, NonDominatedSolutionList trueParetoFront){
			
			   NonDominatedSolutionList intersection = new NonDominatedSolutionList() ;
				if(front.size() >= trueParetoFront.size()){
					Iterator<Solution> iterator = front.iterator();
					String value1, value2;
					int equal=0;			
					for(int i =0; i< front.size(); i++){
						Solution frontIndividual = front.get(i);
						for (int j=0; j <trueParetoFront.size(); j++ ){			
							Solution trueParetoFrontIndividual = trueParetoFront.get(j);
							value1 = frontIndividual.getStringedDecisionVariables();
						    value2 = trueParetoFrontIndividual.getStringedDecisionVariables();
						    if(value1.equals(value2)){
						    	 equal += 1;
						     }
							if(equal >0){
								intersection.addSolutionForDecisionVector(trueParetoFrontIndividual);
						     }
							equal =0;
						}
					}
					
					
				}else if ( trueParetoFront.size() > front.size() ){
					Iterator<Solution> iterator = front.iterator();
					String value1, value2;
					int equal=0;			
					for(int i =0; i< trueParetoFront.size(); i++){
						Solution trueParetoFrontIndividual  =  trueParetoFront.get(i);
						for (int j=0; j <front.size(); j++ ){			
							Solution frontIndividual = front.get(j);
							value1 = frontIndividual.getStringedDecisionVariables();
						    value2 = trueParetoFrontIndividual.getStringedDecisionVariables();
						    if(value1.equalsIgnoreCase(value2) ){
						    	 equal += 1;					    	 
						    }
							if(equal > 0){
								intersection.addSolutionForDecisionVector(frontIndividual);
							}
							equal =0;
						}
					}
					
				}
			   return intersection;
				
			}
	public NonDominatedSolutionList removeDuplicateManyObjectives(double[][] arr) {
	
		NonDominatedSolutionList nonDuplicatedSolutionSet = new NonDominatedSolutionList();
		
		for (int j =0; j < arr.length ; j++){
			int noObj= arr[j].length;
			Solution solution = new Solution(noObj);
			for(int i =0; i < noObj; i++){			
				solution.setObjective(i,arr[j][i]);
			}	
			boolean added = nonDuplicatedSolutionSet.addExactSolution(solution);
		}
		
		return nonDuplicatedSolutionSet;
	
	}
	public NonDominatedSolutionList removeDuplicateManyObjectives(List<String> arr, int noOfObj) {
	
		NonDominatedSolutionList nonDuplicatedSolutionSet = new NonDominatedSolutionList();
		for (int j =0; j < arr.size() ; j++){
			Solution solution = new Solution( noOfObj,arr.get(j));
			boolean added = nonDuplicatedSolutionSet.addExactSolution(solution);
		}
		return nonDuplicatedSolutionSet;
	
	}
	public NonDominatedSolutionList removeDuplicateManyObjectivesSolutionSpace(List<String> arr, int noOfObj) {
		
		NonDominatedSolutionList nonDuplicatedSolutionSet = new NonDominatedSolutionList();
		for (int j =0; j < arr.size() ; j++){
			Solution solution = new Solution( noOfObj,arr.get(j));
			boolean added = nonDuplicatedSolutionSet.addSolutionForDecisionVectorUnique(solution);
		}
		//
		return nonDuplicatedSolutionSet;
	
	}
	/**
	* Returns the precision value for a given front
	* @param front The front 
	* @param trueParetoFront The true pareto front
	*/
	public double coverage(double [][] front, double [][] trueParetoFront, int numberOfObjectives) {
	
	NonDominatedSolutionList uniquefront = removeDuplicateManyObjectives(front);
	
	NonDominatedSolutionList uniquetrueParetoFront =removeDuplicateManyObjectives(trueParetoFront);
	NonDominatedSolutionList cleanCommonSolution  =commonSolutionsManyObjectives(uniquefront,uniquetrueParetoFront);
	double recall = (double) cleanCommonSolution.size()/ uniquetrueParetoFront.size();	
	return recall;
	
	}
	public double coverage(List<String> front, List<String>  trueParetoFront, int numberOfObjectives) {
		double coverage;
		NonDominatedSolutionList uniquefront = removeDuplicateManyObjectivesSolutionSpace(front,numberOfObjectives);
		
		NonDominatedSolutionList uniquetrueParetoFront = removeDuplicateManyObjectivesSolutionSpace(trueParetoFront, numberOfObjectives);
		NonDominatedSolutionList cleanCommonSolution  = commonSolutionsManySolutionSpace(uniquefront,uniquetrueParetoFront);
		coverage = (double) cleanCommonSolution.size()/ (double)uniquetrueParetoFront.size();	
		return coverage;
	}
}//coverage


