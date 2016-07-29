package radar.jmetal.encodings.solutionType;

import java.util.ArrayList;

import java.util.Map;

import radar.jmetal.core.CompositeVariable;
import radar.jmetal.core.Problem;
import radar.jmetal.core.SolutionType;
import radar.jmetal.core.Variable;
import radar.jmetal.encodings.variable.BitVector;
import radar.jmetal.util.PseudoRandom;
import radar.model.Decision;


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
	 * Creates the variables of the solution
	 */
	public Variable[] createVariables(int[] decisionVariable) {
		Variable[] variables = new Variable[decisionVariable.length];

		for (int var = 0; var < decisionVariable.length; var++)
			variables[var] = new BitVector (decisionVariable[var],decisionVariable[var]);   

		return variables ;
	} 
	/**
	 * Creates the variables of the solution
	 */
	public Variable[] createVariables(char[] decisionVariable) {
		Variable[] variables = new Variable[decisionVariable.length];

		for (int var = 0; var < decisionVariable.length; var++)
			//variables[var] = new Int (Integer.valueOf(decisionVariable[var]),Integer.valueOf(decisionVariable[var]));   
		    variables[var] = new BitVector (Integer.parseInt(String.valueOf(decisionVariable[var])), Integer.parseInt(String.valueOf(decisionVariable[var])));

		return variables ;
	} 
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
	@Override
	public ArrayList<Variable[]> createArrayBitVectorVariables(ArrayList<char[]> decisionVariable) throws ClassNotFoundException {
		ArrayList<Variable[]>  variables = new ArrayList<Variable[]> ();
		for (int i = 0; i < decisionVariable.size(); i++){
			int innerloopInteration =  decisionVariable.get(i).length;	
			 Variable[] var = new Variable[innerloopInteration];
			for (int j = 0; j <var.length; j++){
				var[j] = new BitVector (Integer.parseInt(String.valueOf(decisionVariable.get(i)[j])), Integer.parseInt(String.valueOf(decisionVariable.get(i)[j]))); 
			}	
			variables.add(i, var);
		}		
		return variables ;
	}
	
	public ArrayList<Variable[]> createArrayBitVectorVariables() { 
		// added decisionOptions to Problem class for encoding of metaheuristice algorithms...
		ArrayList<Variable[]> variables = new ArrayList<Variable[]>();
		Map<String, Decision> decisions = problem_.getDecisions();
		int count =0;
		for (Map.Entry<String, Decision> entry : decisions.entrySet()){
			int innerloopInteration =  entry.getValue().getOptions().size();	
			Variable[] var = new Variable[innerloopInteration];
			for (int j = 0; j < var.length; j++){
				var[j] = new BitVector(0, (int)problem_.getLowerLimit(count), (int)problem_.getUpperLimit(count));
			}	
			variables.add(count, var);	
			count++;
		}
		int i =0;
		for (Map.Entry<String, Decision> entry : decisions.entrySet()){
			int indexToChange = PseudoRandom.randInt(0, entry.getValue().getOptions().size() - 1);
			variables.get(i)[indexToChange] =new BitVector(1, (int)problem_.getLowerLimit(i), (int)problem_.getUpperLimit(i));
			i++;
		}
		return variables ;
	} // createArrayBitVectorVariables

	public  CompositeVariable  createArrayBitVectorAndBitVectorVariables( ArrayList<char[]> decisionVariables, ArrayList<char[]> deicsionSubsetVariable)throws ClassNotFoundException{
		return null;
	}
	public  CompositeVariable  createArrayBitVectorAndBitVectorVariables( )throws ClassNotFoundException {
		return null;
		
	}
	public ArrayList<Variable[]> createBitVectorVariables() { 
		return null;
	}

	public ArrayList<Variable[]> createBitVectorVariables(ArrayList<char[]> decisionSubsetVariable) throws ClassNotFoundException {
		return null;
	}

		
	
	
}
