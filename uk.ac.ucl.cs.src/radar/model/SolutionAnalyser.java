package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 class SolutionAnalyser {
	
	Model semanticModel_;
	public SolutionAnalyser(Model semanticModel){
		semanticModel_ =semanticModel;
	}
	public  List<Solution> getAllSolutions(){
		List<Solution> solutions = new ArrayList<Solution>();
		List<Decision> allDecisions = new ArrayList<Decision>(semanticModel_.getDecisions().values());
		List<Integer[]> selectedOptionIndices = generateSelectedOptionIndices (semanticModel_.getDecisions());
		String print = "";
		for (int i =0; i <selectedOptionIndices.size(); i++ ){
			Integer [] aSelectedOptionIndex = selectedOptionIndices.get(i);
			Solution s = new Solution();
			for (int j =0 ; j < aSelectedOptionIndex.length; j ++){
				Decision d = allDecisions.get(j);
				String selectedOption = allDecisions.get(j).getOptions().get(aSelectedOptionIndex[j]);
				s.addDecision(d, selectedOption);
				print += selectedOption + ",";
			}
			print += "\n";
			solutions.add(s);
		}
		//System.out.print(print);
		return solutions;
	}
	//
	private List<Integer[]> generateSelectedOptionIndices(Map<String, Decision> decisions){
	     List<Integer[]> result = new ArrayList<Integer[]>();
	     int []decisionOptionsCount = getDecisionOptionsCount (decisions);
	     // added this part to cater for just one single decision.
	     if(decisionOptionsCount.length == 1){
	    	 Integer[] optionIndex = null;
	    	 for(int i = 0; i < decisionOptionsCount[0]; i++ ){
	    		 optionIndex =  new Integer[]{0};
	    		 optionIndex[0] =i;
	    		 result.add(optionIndex);    			
	    	 }
	     }else{
	    	 result = getSelectedOptionIndices(decisionOptionsCount,result);
      }
      return result;
	}
	public List<Solution> egetAllSolutions (){
		List<Solution> solutions = new ArrayList<Solution>();
		List<Decision> allDecisions = new ArrayList<Decision>(semanticModel_.getDecisions().values());
		List<Integer[]> selectedOptionIndices = generateSelectedOptionIndices (semanticModel_.getDecisions());
		String print = "";
		for (int i =0; i <selectedOptionIndices.size(); i++ ){
			Integer [] aSelectedOptionIndex = selectedOptionIndices.get(i);
			Solution s = new Solution();
			for (int j =0 ; j < aSelectedOptionIndex.length; j ++){
				Decision d = allDecisions.get(j);
				String selectedOption = allDecisions.get(j).getOptions().get(aSelectedOptionIndex[j]);
				s.addDecision(d, selectedOption);
				print += selectedOption + ",";
			}
			print += "\n";
			solutions.add(s);
		}
		return solutions;
	}
	protected  int [] getDecisionOptionsCount (Map<String, Decision> decisions){
		int[] result = null;
        if(decisions != null){
        	result = new int[decisions.size()];
        	int j =0;
        	for (Map.Entry<String, Decision> entry : decisions.entrySet() ){
        		result[j]= entry.getValue().getOptions().size();
        		j++;
        	}
        }else{
			throw new RuntimeException ("Optimisation error: decision vector block cannot be empty, ensure you specify alternatives in the model.");
		}
        return result;
	}
	public Solution encodingToAlternative(List<Integer[]> encodedSolution) {
		Solution result = new Solution ();
		Map<String, Decision> decisions = semanticModel_.getDecisions();
		List<Decision> decisionList = new ArrayList<Decision>(decisions.values());
		for (int i =0; i < encodedSolution.size(); i++ ){
			 String selectedOption = "";
			 for (int j =0; j < encodedSolution.get(i).length; j++ ){
				 if (encodedSolution.get(i)[j].equals(1)){
					 selectedOption = decisionList.get(i).getOptions().get(j);
				 }
			 }
			 result.addDecision(decisionList.get(i), selectedOption);
		}
		return result;
	}
	//
	protected List<Integer[]> rowtoColumn (List<Integer[]> rowArray){
    	List<Integer[]> columnArray = new ArrayList<Integer[]>();
    	int column = rowArray.get(0).length;
    	int row = rowArray.size();
    	for (int c = 0 ; c < column ; c++) {
    		Integer[] rows = new Integer[row];
    	    for (int r = 0 ; r < row ; r++) {
    	    	rows[r] = rowArray.get(r)[c];
    	    }
    	    columnArray.add(rows);
    	}
    	return columnArray;
	}
	//
	protected  List<Integer[]> getSelectedOptionIndices(int[] decisionOptionCount, List<Integer[]> results ){
    	if(decisionOptionCount.length == 1){
    		List<Integer[]> alternatives = new ArrayList<Integer[]>();
    		Integer[] optionIndex =  new Integer[decisionOptionCount[0]];
    		for(int i = 0; i < decisionOptionCount[0]; i++ ){
    			optionIndex[i] = i;    			
    		}
    		alternatives.add(optionIndex);
    		return alternatives;    		
    	}
    	else{
    		int head = decisionOptionCount[0];
    		int [] tail  = new int[decisionOptionCount.length -1];
    		for (int i = 0; i < decisionOptionCount.length -1; i++ ){
    			tail[i] = decisionOptionCount[i+1];
    		}
    		List<Integer[]> tempAltenatives = getSelectedOptionIndices(tail,results);
    		int n=0; // the value by which to repeat the next option for tempAltenatives. 
    		n =  (tempAltenatives.size() <= 1) ? tempAltenatives.get(0).length:tempAltenatives.size(); 
    		for (int i =0; i< head; i++){
    			Integer[] repeatedColumnOption =  new Integer[n]; // to be repeated n times for each previously listed option indices.
    			for(int j=0; j < n; j++){
    				repeatedColumnOption[j]= i;
    			}
    			List<Integer[]> repeatedColumnOptionArray = new ArrayList<Integer[]>();    			
    			repeatedColumnOptionArray.add(repeatedColumnOption);   
    			
    			// this check ensures that the first head aligns with its inner tails that have been recursively obtained.
    			if(tempAltenatives.size() != repeatedColumnOptionArray.size() && tempAltenatives.size() == repeatedColumnOption.length ){ 
    				tempAltenatives = rowtoColumn(tempAltenatives); // this converts temp back to row so that we can combine it with innerrepeatedColumnOptionArray
    				List<Integer[]> innerResults = new ArrayList<Integer[]>();
    				for(int k =0 ; k < head; k++){ 
    					List<Integer[]> innercombinedRepeatedColumnOptionArray = new ArrayList<Integer[]>();
    	    			Integer[]innerRepeatedColumnOption =  new Integer[n];
    	    			for(int j=0; j < n; j++){
    	    				innerRepeatedColumnOption[j]= k;
    	    			}
    	    			List<Integer[]> innerRepeatedColumnOptionArray = new ArrayList<Integer[]>();    			
    	    			innerRepeatedColumnOptionArray.add(innerRepeatedColumnOption);
    	    			innercombinedRepeatedColumnOptionArray.addAll(innerRepeatedColumnOptionArray);
            			innercombinedRepeatedColumnOptionArray.addAll(tempAltenatives);
            			List<Integer[]> innerconvertedcombinedRepeatedColumnOptionArray = rowtoColumn(innercombinedRepeatedColumnOptionArray);
            			innerResults.addAll(innerconvertedcombinedRepeatedColumnOptionArray);
    				}
    				return innerResults; // this is what is eventually returned after we consider the first head and the rest of the returned outcome for tail.
    				    				
    			}
    			List<Integer[]> combinedRepeatedColumnOptionArray = new ArrayList<Integer[]>(); 
    			combinedRepeatedColumnOptionArray.addAll(repeatedColumnOptionArray);
    			combinedRepeatedColumnOptionArray.addAll(tempAltenatives);
    			List<Integer[]> convertedcombinedRepeatedColumnOptionArray = rowtoColumn(combinedRepeatedColumnOptionArray);
    			results.addAll(convertedcombinedRepeatedColumnOptionArray); 
    			
    		}  		
    		return results;
    	}
	}
}
