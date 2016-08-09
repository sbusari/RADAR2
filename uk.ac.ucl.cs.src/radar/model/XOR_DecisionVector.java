package radar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import radar.utilities.PseudoRandom;

 class XOR_DecisionVector {

	Model semanticModel_;
	public static List<Integer[]> encodeExactDecisionVector(Model model, Integer[] selectedOptionIndex){
		List<Integer[]> decisionVector = new ArrayList<Integer[]>();
		List<Decision> decisions = model.getDecisions();
		for (int j =0 ; j < selectedOptionIndex.length; j ++){
			Decision d = decisions.get(j);
			Integer[] var = new Integer[d.getOptions().size()];
			int selectedOption = selectedOptionIndex[j];
			var[selectedOption] = 1;
			decisionVector.add(j, var);
		}
		return decisionVector;
	}
	public static List<Integer[]> encodeSBSEDecisionVector(Model model){
		List<Integer[]> decisionVector = new ArrayList<Integer[]>();
		List<Decision> decisions = model.getDecisions();
		for (int i =0; i < decisions.size(); i ++){
			Integer[] var = new Integer[decisions.get(i).getOptions().size()];
			for (int j = 0; j < var.length; j++){
				var[j] = 0;
			}
			decisionVector.add(i, var);	
			i++;
		}
		return decisionVector;
	}
	
	
	

	
   

	
	
	

}
