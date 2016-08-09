package radar.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Pareto {
	
	int dominate (double[] s1, double [] s2){
	    int dominate1 = 0 ; //dominate1 : Solution 1 domainates solution 2 for an objective
	    int dominate2 = 0 ; //dominate2 : Solution 2 domainates solution 1 for an objective
	    int result;     
	    double value1, value2;
	    for (int i = 0; i < s1.length; i++) {
	    	value1 = s1[i];
	    	value2 = s2[i];
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
	public  Map<Solution, double[]> getParetoSet (Map<Solution, double[]> evaluatedSolutions){
		
		Map<Solution, double[]> paretoSet = new LinkedHashMap<Solution, double[]>();
		List<Solution> solutions = new ArrayList<Solution>(evaluatedSolutions.keySet());
		List<double[]> objectiveValues = new ArrayList<double[]> (evaluatedSolutions.values());
		boolean [] isPareto =  new boolean[evaluatedSolutions.size()];
		Arrays.fill(isPareto, Boolean.TRUE);
		int i =0;
		while (i < (objectiveValues.size()-1)){
			int j = i+1;
			while (isPareto[i] && j < objectiveValues.size()){
				int dominate =0;
				dominate = dominate(objectiveValues.get(i),objectiveValues.get(j));
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
				paretoSet.put(solutions.get(k),objectiveValues.get(k));
			}
		}
		return paretoSet;
	}
}
