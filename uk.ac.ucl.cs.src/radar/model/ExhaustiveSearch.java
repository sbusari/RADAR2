package radar.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 class ExhaustiveSearch extends Algorithm{
	Model semanticModel_;
	DecisionVector decisionVector_;
	List<SolutionValues> solutionValues_;
	List<Solution> alternatives_;
	public ExhaustiveSearch (Model model){
		semanticModel_ = model;
		//decisionVector_ = getDecisionVector(model);
		alternatives_ =semanticModel_.getAllSolutions();
		solutionValues_ = new ArrayList<SolutionValues>();
	}
	public SolutionValues solve(Solution s) {
		SolutionValues value = new SolutionValues ();
		Simulator simulator = new Simulator(s, semanticModel_);
		value =  simulator.computeObjectivesValues();	
		semanticModel_.addAlternative(s);
		return value;
	}
	public List<SolutionValues> solve() {
		long initTime = System.currentTimeMillis();
		List<SolutionValues> optimalSolutionValues = new ArrayList<SolutionValues>();
		for (int i = 0; i < alternatives_.size(); i++){
			SolutionValues value = new SolutionValues ();
			Simulator simulator = new Simulator(alternatives_.get(i), semanticModel_);
			value =  simulator.computeObjectivesValues();	
			semanticModel_.addAlternative(alternatives_.get(i));
			solutionValues_.add(value);
			System.out.print("Solution number "+ i + "\n");
		}
		optimalSolutionValues = ParetoSet(solutionValues_);
		long estimatedTime = System.currentTimeMillis() - initTime;
		runTime_ = estimatedTime/1000;
		return optimalSolutionValues;
	}
	private List<SolutionValues> ParetoSet (List<SolutionValues> solutionValues){
		
	 	List<SolutionValues> paretoSet = new ArrayList<SolutionValues>();
		boolean [] isPareto =  new boolean[solutionValues.size()];
		Arrays.fill(isPareto, Boolean.TRUE);
		int i =0;
		while (i < (solutionValues.size()-1)){
			int j = i+1;
			while (isPareto[i] && j < solutionValues.size()){
				int dominate =0;
				dominate = dominate(solutionValues.get(i),solutionValues.get(j));
		        if (dominate == -1){
		        	 isPareto[j] = Boolean.FALSE; 
		        }else if (dominate == 1){	
		        	 isPareto[i] = Boolean.FALSE; 
		        }
		        j++;
			}			
			i++;				
		}
		for (int k =0 ; k < isPareto.length; k++){
			if(isPareto[k]){
				paretoSet.add(solutionValues.get(k));
			}
		}
		return paretoSet;
	}
	int dominate (SolutionValues s1, SolutionValues s2){
	    
	    int dominate1 = 0 ; //dominate1 : Solution 1 domainates solution 2 for an objective
	    int dominate2 = 0 ; //dominate2 : Solution 2 domainates solution 1 for an objective
	    int result;     
	    double value1, value2;
	    List<Double> s1_objectiveValue = new ArrayList<Double>(s1.getObjectiveValue().values());
	    List<Double> s2_objectiveValue = new ArrayList<Double>(s2.getObjectiveValue().values());
	    for (int i = 0; i < s1_objectiveValue.size(); i++) {
	    	value1 = s1_objectiveValue.get(i);
	    	value2 = s2_objectiveValue.get(i);
	    	if (value1 < value2) {
	    		result = -1;
	    	} else if (value1 > value2 )  {
	    		result = 1;
	    	} else {
	    		result = 0;
	    	}
	    	if (result == -1) {
	    		dominate1 = 1;
	    	}
	    	if (result == 1) {
	    		dominate2 = 1;           
	    	}
	    }         
	    if (dominate1 == dominate2) {            
	      return 0; //No one dominate the other
	    }
	    if (dominate1 == 1) {
	      return -1; // solution1 dominate
	    }
	    return 1;    // solution2 dominate 
	}
	public List<SolutionValues> getAllSolutionValues (){
		return solutionValues_;
	}
	
}
