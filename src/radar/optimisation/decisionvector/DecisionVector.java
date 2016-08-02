package radar.optimisation.decisionvector;
import java.util.List;
import java.util.Map;

import radar.model.Alternative;
import radar.model.Decision;

public abstract class DecisionVector {
	
	protected abstract List<Integer[]> encodeSolution();
	protected abstract Alternative encodingToAlternative (List<Integer[]> encodedSolution);
	protected abstract int [] getDecisionOptionsCount (Map<String, Decision> decisions);
	public abstract List<Alternative> getAllSolutions ();
}	
	
	

