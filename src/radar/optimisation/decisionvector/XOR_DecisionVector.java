package radar.optimisation.decisionvector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import radar.jmetal.core.Variable;
import radar.jmetal.encodings.variable.BitVector;
import radar.jmetal.util.PseudoRandom;
import radar.model.Alternative;
import radar.model.Decision;
import radar.model.Model;

public class XOR_DecisionVector extends DecisionVector {

	Model semanticModel_;
	public XOR_DecisionVector ( Model semanticModel ){
		semanticModel_ = semanticModel;
	}
	
	public List<Integer[]> encodeExactDecisionVector(Integer[] selectedOptionIndex){
		List<Integer[]> decisionVector = new ArrayList<Integer[]>();
		Map<String, Decision> decisions = semanticModel_.getDecisions();
		for (int j =0 ; j < selectedOptionIndex.length; j ++){
			Decision d = decisions.get(j);
			Integer[] var = new Integer[d.getOptions().size()];
			int selectedOption = selectedOptionIndex[j];
			var[selectedOption] = 1;
			decisionVector.add(j, var);
		}
		return decisionVector;
	}
	public List<Integer[]> encodeSBSEDecisionVector(){
		List<Integer[]> decisionVector = new ArrayList<Integer[]>();
		Map<String, Decision> decisions = semanticModel_.getDecisions();
		int i =0;
		for (Map.Entry<String, Decision> entry : decisions.entrySet()){
			Integer[] var = new Integer[entry.getValue().getOptions().size()];
			for (int j = 0; j < var.length; j++){
				var[j] = 0;
			}
			decisionVector.add(i, var);	
			i++;
		}
		
		for (Map.Entry<String, Decision> entry : decisions.entrySet()){
			int indexToChange = PseudoRandom.randInt(0, entry.getValue().getOptions().size() - 1);
			decisionVector.get(i)[indexToChange] =1;
			i++;
		}
		return decisionVector;
	}
	
	
	

	
   

	
	
	

}
