package radar.optimisation.decisionvector;
import java.util.List;
import java.util.Map;

import radar.model.Alternative;
import radar.model.Decision;

public abstract class DecisionVector {
	protected abstract List<Integer[]> encodeExactDecisionVector (Integer[] selectedOptionIndex);
	protected abstract List<Integer[]> encodeSBSEDecisionVector ();
}	
	
	

