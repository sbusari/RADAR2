package jmetal.encodings.solutionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import radar.model.Decision;
import jmetal.core.Problem;
import jmetal.core.SolutionType;
import jmetal.core.Variable;
import jmetal.encodings.variable.BitVector;
import jmetal.util.PseudoRandom;


public class ArrayBitVectorSolutionType extends SolutionType{
	
	
	public ArrayBitVectorSolutionType(Problem problem) {
		super(problem) ;
	}
	/**
	 * Creates the variables of the solution
	 */
	public Variable[] createVariables() {
		Variable[] variables = new Variable[problem_.getNumberOfVariables()];

		for (int var = 0; var < problem_.getNumberOfVariables(); var++)
			variables[var] = new BitVector((int)problem_.getLowerLimit(var), (int)problem_.getUpperLimit(var));    

		return variables ;
	} // createVariables
	/**
	 * Copy the variables
	 * @param vars Variables to copy
	 * @return An array of variables
	 */
	public Variable[] copyVariables(Variable[] vars) {
		Variable[] variables ;
		
		variables = new Variable[1];
	  variables[0] = vars[0].deepCopy();
		
		return variables ;
	} // copyVariables
	public List<Variable[]> createArrayBitVectorVariables() { 
		List<Variable[]> variables = new ArrayList<Variable[]>();
		for (int j =0; j < problem_.getDecisionVectorBlock().size(); j++){
			Variable[] var = new Variable[problem_.getDecisionVectorBlock().get(j).length];
			for (int k = 0; k < var.length; k++){
				var[k] = new BitVector(0, (int)problem_.getLowerLimit(j), (int)problem_.getUpperLimit(j));
			}
			variables.add(j, var);
		}
		for (int i =0; i< problem_.getDecisionVectorBlock().size(); i++){
			int indexToChange = PseudoRandom.randInt(0, problem_.getDecisionVectorBlock().get(i).length - 1);
			variables.get(i)[indexToChange] =new BitVector(1, (int)problem_.getLowerLimit(i), (int)problem_.getUpperLimit(i));
		}
		return variables ;
	} // createArrayBitVectorVariables

		
	
	
}
