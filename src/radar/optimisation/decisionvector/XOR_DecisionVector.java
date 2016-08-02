package radar.optimisation.decisionvector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import radar.model.Alternative;
import radar.model.Decision;
import radar.model.Model;

public class XOR_DecisionVector extends DecisionVector {

	Model semanticModel_;
	public XOR_DecisionVector ( Model semanticModel ){
		semanticModel_ = semanticModel;
	}
	@Override
	public  List<Alternative> getAllSolutions(){
		List<Alternative> solutions = new ArrayList<Alternative>();
		List<Decision> allDecisions = new ArrayList<Decision>(semanticModel_.getDecisions().values());
		List<Integer[]> selectedOptionIndices = generateSelectedOptionIndices (semanticModel_.getDecisions());
		String print = "";
		for (int i =0; i <selectedOptionIndices.size(); i++ ){
			Integer [] aSelectedOptionIndex = selectedOptionIndices.get(i);
			Alternative s = new Alternative();
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
	private ArrayList<Integer[]> generateSelectedOptionIndices(Map<String, Decision> decisions){
	     ArrayList<Integer[]> result = new ArrayList<Integer[]>();
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
	@Override
	public List<Integer[]> encodeSolution() {
		   ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		   int []decisionOptionsCount = getDecisionOptionsCount (semanticModel_.getDecisions());
	       if(decisionOptionsCount.length == 1){
		       	Integer[] optionIndex = null;
		   		for(int i = 0; i < decisionOptionsCount[0]; i++ ){
		   			optionIndex =  new Integer[]{0};
		   			optionIndex[0] =i;
		   			result.add(optionIndex);    			
		   		}
	       }
	       else{
	       		result = getSelectedOptionIndices(decisionOptionsCount,result);
	       }
	      return result;
	}
	@Override
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
	@Override
	public Alternative encodingToAlternative(List<Integer[]> encodedSolution) {
		Alternative result = new Alternative ();
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
	protected ArrayList<Integer[]> rowtoColumn (ArrayList<Integer[]> rowArray){
    	ArrayList<Integer[]> columnArray = new ArrayList<Integer[]>();
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
	protected  ArrayList<Integer[]> getSelectedOptionIndices(int[] decisionOptionCount, ArrayList<Integer[]> results ){
    	if(decisionOptionCount.length == 1){
    		ArrayList<Integer[]> alternatives = new ArrayList<Integer[]>();
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
    		ArrayList<Integer[]> tempAltenatives = getSelectedOptionIndices(tail,results);
    		int n=0; // the value by which to repeat the next option for tempAltenatives. 
    		n =  (tempAltenatives.size() <= 1) ? tempAltenatives.get(0).length:tempAltenatives.size(); 
    		for (int i =0; i< head; i++){
    			Integer[] repeatedColumnOption =  new Integer[n]; // to be repeated n times for each previously listed option indices.
    			for(int j=0; j < n; j++){
    				repeatedColumnOption[j]= i;
    			}
    			ArrayList<Integer[]> repeatedColumnOptionArray = new ArrayList<Integer[]>();    			
    			repeatedColumnOptionArray.add(repeatedColumnOption);   
    			
    			// this check ensures that the first head aligns with its inner tails that have been recursively obtained.
    			if(tempAltenatives.size() != repeatedColumnOptionArray.size() && tempAltenatives.size() == repeatedColumnOption.length ){ 
    				tempAltenatives = rowtoColumn(tempAltenatives); // this converts temp back to row so that we can combine it with innerrepeatedColumnOptionArray
    				ArrayList<Integer[]> innerResults = new ArrayList<Integer[]>();
    				for(int k =0 ; k < head; k++){ 
    					ArrayList<Integer[]> innercombinedRepeatedColumnOptionArray = new ArrayList<Integer[]>();
    	    			Integer[]innerRepeatedColumnOption =  new Integer[n];
    	    			for(int j=0; j < n; j++){
    	    				innerRepeatedColumnOption[j]= k;
    	    			}
    	    			ArrayList<Integer[]> innerRepeatedColumnOptionArray = new ArrayList<Integer[]>();    			
    	    			innerRepeatedColumnOptionArray.add(innerRepeatedColumnOption);
    	    			innercombinedRepeatedColumnOptionArray.addAll(innerRepeatedColumnOptionArray);
            			innercombinedRepeatedColumnOptionArray.addAll(tempAltenatives);
            			ArrayList<Integer[]> innerconvertedcombinedRepeatedColumnOptionArray = rowtoColumn(innercombinedRepeatedColumnOptionArray);
            			innerResults.addAll(innerconvertedcombinedRepeatedColumnOptionArray);
    				}
    				return innerResults; // this is what is eventually returned after we consider the first head and the rest of the returned outcome for tail.
    				    				
    			}
    			ArrayList<Integer[]> combinedRepeatedColumnOptionArray = new ArrayList<Integer[]>(); 
    			combinedRepeatedColumnOptionArray.addAll(repeatedColumnOptionArray);
    			combinedRepeatedColumnOptionArray.addAll(tempAltenatives);
    			ArrayList<Integer[]> convertedcombinedRepeatedColumnOptionArray = rowtoColumn(combinedRepeatedColumnOptionArray);
    			results.addAll(convertedcombinedRepeatedColumnOptionArray); 
    			
    		}  		
    		return results;
    	}
   }

	
	
	

}
