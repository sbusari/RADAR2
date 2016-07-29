package radar.jmetal.evolutionary.operators;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import radar.enumeration.DecisionVectorType;
import radar.jmetal.core.Solution;
import radar.jmetal.core.Variable;
import radar.jmetal.encodings.solutionType.ArrayBitVectorSolutionType;
import radar.jmetal.encodings.variable.ArrayBitVector;
import radar.jmetal.encodings.variable.BitVector;
import radar.jmetal.util.Configuration;
import radar.jmetal.util.JMException;
import radar.jmetal.util.PseudoRandom;

/**
* This class implements a bit flip mutation operator.
* NOTE: the operator is applied to binary or integer solutions, considering the
* whole solution as a single encodings.variable.
*/
public class BitFlipMutation extends Mutation {
/**
* Valid solution types to apply this operator 
*/
private static final List VALID_TYPES = Arrays.asList(ArrayBitVectorSolutionType.class) ;

private Double mutationProbability_ = null ;
private DecisionVectorType typeOfDecisionVector_ = null;

/**
 * Constructor
 * Creates a new instance of the Bit Flip mutation operator
 */
public BitFlipMutation(HashMap<String, Object> parameters,DecisionVectorType typeOfDecisionVector) {
	super(parameters) ;
	typeOfDecisionVector_ =typeOfDecisionVector;
	if (parameters.get("probability") != null)
		mutationProbability_ = (Double) parameters.get("probability") ;  		
} // BitFlipMutation


private void mutateADecision (double probability, Solution solution){
	for (int i = 0; i < solution.getArrayBitVectorVariables().size(); i++) {				
		if (PseudoRandom.randDouble() < probability) {
			
			Variable [] Value = new Variable[solution.getArrayBitVectorVariables().get(i).length];
	    	for (int j =0; j <   Value.length ; j++ ){
	    		Value[j] =   solution.getArrayBitVectorVariables().get(i)[j].deepCopy();
	    	}
	    	Collections.shuffle(Arrays.asList(Value));
	    	solution.setArrayBitVectorVariables(i, Value);
	    	
			//Collections.shuffle(Arrays.asList(solution.getArrayBitVectorVariables().get(i)));
			
			//System.out.println ("mutation: after shifting "+ solution.solutionToString(typeOfDecisionVector_, i));
		}// if
	}	
}
private void mutateADecisionSubset (double probability, Solution solution) throws JMException{
	for (int i = 0; i < solution.getBitVectorVariables().size(); i++) {
		for(int j=0; j< solution.getBitVectorVariables().get(i).length ; j++){
			if (PseudoRandom.randDouble() < probability) {
				int value = PseudoRandom.randInt(
						(int)solution.getBitVectorVariables().get(i)[j].getLowerBound(),
						(int)solution.getBitVectorVariables().get(i)[j].getUpperBound());
				Variable var = new BitVector (value, value); // returns either 0 or 1 depending on value.
				solution.setBitVectorVariables(i,j,var);
			} // if
		}
	}
}
/**
 * Perform the mutation operation
 * @param probability Mutation probability
 * @param solution The solution to mutate
 * @throws JMException
 */
public void doMutation(double probability, Solution solution) throws JMException {
	try {
		if (typeOfDecisionVector_ == DecisionVectorType.Decision){
			mutateADecision(probability,solution);
		}else if (typeOfDecisionVector_ == DecisionVectorType.DecisionSubset){
			mutateADecisionSubset(probability,solution);
		}else{
			mutateADecision(probability,solution);
			mutateADecisionSubset(probability,solution);
		}
		
	} catch (ClassCastException e1) {
		Configuration.logger_.severe("BitFlipMutation.doMutation: " +
				"ClassCastException error" + e1.getMessage());
		Class cls = java.lang.String.class;
		String name = cls.getName();
		throw new JMException("Exception in " + name + ".doMutation()");
	}
} // doMutation

/**
 * Executes the operation
 * @param object An object containing a solution to mutate
 * @return An object containing the mutated solution
 * @throws JMException 
 */
public Object execute(Object object) throws JMException {
	Solution solution = (Solution) object;

	if (!VALID_TYPES.contains(solution.getType().getClass())) {
		Configuration.logger_.severe("BitFlipMutation.execute: the solution " +
				"is not of the right type. The type should be 'Binary', " +
				"'BinaryReal' or 'Int', but " + solution.getType() + " is obtained");

		Class cls = java.lang.String.class;
		String name = cls.getName();
		throw new JMException("Exception in " + name + ".execute()");
	} // if 

	doMutation(mutationProbability_, solution);
	return solution;
} // execute
} // BitFlipMutation
