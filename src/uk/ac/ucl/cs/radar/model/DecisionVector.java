package radar.model;
import java.util.List;
import java.util.Map;

 abstract class DecisionVector {
	public abstract List<Integer[]> encodeExactDecisionVector (Integer[] selectedOptionIndex);
	public abstract List<Integer[]> encodeSBSEDecisionVector ();
}	
	
	

