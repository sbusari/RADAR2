package radar.jmetal.core;

import java.util.ArrayList;

public class CompositeVariable {
	ArrayList<Variable []> decisionSubset_;
	ArrayList<Variable[]> decision_;
	public CompositeVariable (ArrayList<Variable []> decisionSubset, ArrayList<Variable[]> decision){
		decisionSubset_ = decisionSubset;
		decision_ = decision;
	}
	public ArrayList<Variable []> getDecisionSubsetVariable (){
		return decisionSubset_;
	}
	public ArrayList<Variable[]> getDecisionVariable (){
		return decision_;
	}
}
