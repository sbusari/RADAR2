//  SolutionType.java
//
//  Authors:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
// 
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package radar.jmetal.core;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class representing solution types, which define the types of the 
 * variables constituting a solution 
 */
public abstract class SolutionType implements Serializable {	
	
	public final Problem problem_ ; /** Problem to be solved */
	
  /**
   * Constructor
   * @param problem The problem to solve
   */
  public SolutionType(Problem problem) {
  	problem_ = problem ;
  } // Constructor
    
  /**
   * Abstract method to create the variables of the solution
   */
	public abstract Variable[] createVariables() throws ClassNotFoundException ;
	
	/**
	 * Copies the decision variables
	 * @param vars
	 * @return An array of variables
	 */
	public Variable[] copyVariables(Variable[] vars) {
		Variable[] variables ;
		variables = new Variable[vars.length];
		for (int var = 0; var < vars.length; var++) {
			variables[var] = vars[var].deepCopy();
		} // for
		return variables ;
	} // copyVariables
	
	public ArrayList<Variable[]>  copyArrayBitVectorVariables( ArrayList<Variable[]> vars) {
		ArrayList<Variable[]> variables = new ArrayList<Variable[]>();
		variables.addAll(vars);
		return variables ;
	} // copyVariables

	public abstract CompositeVariable  createArrayBitVectorAndBitVectorVariables( ArrayList<char[]> decisionVariables, ArrayList<char[]> deicsionSubsetVariable)throws ClassNotFoundException ; ;
	public abstract CompositeVariable  createArrayBitVectorAndBitVectorVariables( )throws ClassNotFoundException ; 
	
	public abstract ArrayList<Variable[]> createArrayBitVectorVariables() throws ClassNotFoundException ;
	public abstract ArrayList<Variable[]> createArrayBitVectorVariables(ArrayList<char[]> decisionVariable) throws ClassNotFoundException ;

	public abstract ArrayList<Variable[]> createBitVectorVariables() throws ClassNotFoundException ;
	public abstract ArrayList<Variable[]> createBitVectorVariables(ArrayList<char[]> decisionSubsetVariable) throws ClassNotFoundException ;

	  
} // SolutionType
