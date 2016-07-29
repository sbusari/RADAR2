package radar.jmetal.evolutionary.operators;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import radar.enumeration.DecisionVectorType;
import radar.jmetal.core.CompositeVariable;
import radar.jmetal.core.Solution;
import radar.jmetal.core.Variable;
import radar.jmetal.encodings.solutionType.ArrayBitVectorSolutionType;
import radar.jmetal.encodings.variable.ArrayBitVector;
import radar.jmetal.encodings.variable.BitVector;
import radar.jmetal.util.Configuration;
import radar.jmetal.util.JMException;
import radar.jmetal.util.PseudoRandom;

/**
* This class allows to apply a Single Point crossover operator using two parent
* solutions.
*/
public class SinglePointCrossover extends Crossover {
/**
* Valid solution types to apply this operator 
*/
private static final List VALID_TYPES = Arrays.asList(ArrayBitVectorSolutionType.class) ;

private Double crossoverProbability_ = null;
private DecisionVectorType typeOfDecisionVector_ = null;

/**x
* Constructor
* Creates a new instance of the single point crossover operator
*/
public SinglePointCrossover(HashMap<String, Object> parameters,DecisionVectorType typeOfDecisionVector) {
	super(parameters) ;
	typeOfDecisionVector_ =typeOfDecisionVector;
	if (parameters.get("probability") != null)
		crossoverProbability_ = (Double) parameters.get("probability") ;  		
} // SinglePointCrossover

private Solution[] compositeCrossover(double probability, Solution parent1, Solution parent2){
	Solution[] offSpring = new Solution[2];
	try {
		offSpring[0] = new Solution(parent1);
		offSpring[1] = new Solution(parent2);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (PseudoRandom.randDouble() < probability) {
		CompositeVariable valueX1 = new CompositeVariable (parent1.getBitVectorVariables(),parent1.getArrayBitVectorVariables());
		CompositeVariable valueX2 = new CompositeVariable (parent2.getBitVectorVariables(),parent2.getArrayBitVectorVariables());
		offSpring[0].setArrayBitVectorAndBitVectorVariables(valueX2.getDecisionSubsetVariable(),valueX2.getDecisionVariable());
        offSpring[1].setArrayBitVectorAndBitVectorVariables(valueX1.getDecisionSubsetVariable(),valueX1.getDecisionVariable());
	}
	return offSpring;
}

private Solution[] decisionCrossover (double probability, Solution parent1, Solution parent2) throws JMException{
	Solution[] offSpring = new Solution[2];
	try {
		/*try {
			offSpring[0] = (Solution)parent1.clone();
			offSpring[1] = (Solution)parent2.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	// commented this part due to memry issue.
		//offSpring[0] = (Solution)Solution.deepClone(parent1);
		//offSpring[1] = (Solution)Solution.deepClone(parent2);
		
		offSpring[0] = new Solution(parent1);
		offSpring[1] = new Solution(parent2);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	}
	try {
		  if (PseudoRandom.randDouble() < probability) {
		      
			  int crossoverPoint =0;	
			  //while (crossoverPoint >0){
				 crossoverPoint = PseudoRandom.randInt(0, parent1.getArrayBitVectorVariables().size()-1);
			  //}
			  
		      for (int i = crossoverPoint; i < parent1.getArrayBitVectorVariables().size(); i++) {
		    	//valueX1 = parent1.getArrayBitVectorVariables().get(i);
			    //valueX2 = parent2.getArrayBitVectorVariables().get(i);
		   
		    	Variable [] valueX1 = new Variable[parent1.getArrayBitVectorVariables().get(i).length];
			    Variable[] valueX2 = new Variable[parent1.getArrayBitVectorVariables().get(i).length];
		    	for (int j =0; j <   parent1.getArrayBitVectorVariables().get(i).length ; j++ ){
		    		valueX1[j] =   parent1.getArrayBitVectorVariables().get(i)[j].deepCopy();
		    		valueX2[j] =   parent2.getArrayBitVectorVariables().get(i)[j].deepCopy();
		    	}
		    	
		    	//valueX1 = Arrays.copyOf( parent1.getArrayBitVectorVariables().get(i), parent1.getArrayBitVectorVariables().get(i).length);
		        //valueX2 = Arrays.copyOf(parent2.getArrayBitVectorVariables().get(i),parent2.getArrayBitVectorVariables().get(i).length);
		        
		        offSpring[0].setArrayBitVectorVariables(i, valueX2);
		        offSpring[1].setArrayBitVectorVariables(i, valueX1);
		        
		        /*offSpring[0].getArrayBitVectorVariables().set(i, valueX2);
		        offSpring[1].getArrayBitVectorVariables().set(i, valueX1);*/		        
		      } // fo
		     
		    } // Decision
		  }
		 catch (ClassCastException e1) {
		  Configuration.logger_.severe("SinglePointCrossover.doCrossover: Cannot perfom " +
		          "SinglePointCrossover");
		  Class cls = java.lang.String.class;
		  String name = cls.getName();
		  throw new JMException("Exception in " + name + ".doCrossover()");
		}
	return offSpring;
}

private Solution[] decisionSubsetCrossover (double probability,Solution parent1,Solution parent2) throws ClassNotFoundException, JMException{
	Solution[] offSpring = new Solution[2];
	try {
			offSpring[0] = new Solution(parent1);
			offSpring[1] = new Solution(parent2);
	      if (PseudoRandom.randDouble() < probability) {
	    	  // for each subset we perform 
	    	  // if the have only one subset
	    	 
	    	  if (parent1.getBitVectorVariables().size() == 1){ // given one decision subset.
	    		  Variable [] valueX1;
			      Variable[] valueX2;
	    		  for (int i = 0; i < parent1.getBitVectorVariables().size(); i++) {
	    			  int crossoverPoint =0;	
	    			  while (crossoverPoint != 0 && crossoverPoint!= parent1.getBitVectorVariables().size() ){
	    				 crossoverPoint = PseudoRandom.randInt(0, parent1.getBitVectorVariables().get(i).length);
	    			  }
				        valueX1 = parent1.getBitVectorVariables().get(i);
				        valueX2 = parent2.getBitVectorVariables().get(i);
				        for (int j = crossoverPoint; j < parent1.getBitVectorVariables().get(i).length; j++) {
				        	Object temp = valueX1[j];
				        	valueX1[j]= valueX2[j];
				        	valueX2[j]= (BitVector)temp;
				        }
				        offSpring[0].setBitVectorVariables(i, valueX2);
				        offSpring[1].setBitVectorVariables(i, valueX1);
	    		  }
	    	  }else{
	    		  ArrayList<Variable []> valueX1 = parent1.getBitVectorVariables();
	    		  ArrayList<Variable []> valueX2 = parent2.getBitVectorVariables();
	    		  int crossoverPoint = PseudoRandom.randInt(0, parent1.getBitVectorVariables().size());
		    	  for (int i = crossoverPoint; i < parent1.getBitVectorVariables().size(); i++) {
		    		  offSpring[0].setBitVectorVariables(i, valueX2.get(i));
				      offSpring[1].setBitVectorVariables(i, valueX1.get(i));
		    	  }
	    	  }
	        
	      }
	    } catch (ClassCastException e1) {
	      Configuration.logger_.severe("SinglePointCrossover.doCrossover: Cannot perfom " +
	              "SinglePointCrossover");
	      Class cls = java.lang.String.class;
	      String name = cls.getName();
	      throw new JMException("Exception in " + name + ".doCrossover()");
	    }
	return offSpring;
}
/**
* Perform the crossover operation.
* @param probability Crossover probability
* @param parent1 The first parent
* @param parent2 The second parent   
* @return An array containig the two offsprings
* @throws JMException
 * @throws ClassNotFoundException 
*/
public Solution[] doCrossover(double probability,
		Solution parent1,
      	Solution parent2) throws JMException, ClassNotFoundException {
		Solution[] offSpring = new Solution[2];
		/*try {
			offSpring[0] = new Solution(parent1);
			offSpring[1] = new Solution(parent2);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		if (typeOfDecisionVector_ == DecisionVectorType.Decision){
			offSpring =decisionCrossover (probability,parent1,parent2);
		}else if (typeOfDecisionVector_ == DecisionVectorType.DecisionSubset){
			offSpring =decisionSubsetCrossover (probability,parent1,parent2);
		}else{
			offSpring =compositeCrossover(probability,parent1,parent2);
		}
		
		/*try {
		  if (PseudoRandom.randDouble() < probability) {
				  int crossoverPoint = PseudoRandom.randInt(0, parent1.getArrayBitVectorVariables().size()- 1);
			      Variable [] valueX1;
			      Variable[] valueX2;
			      for (int i = crossoverPoint; i < parent1.getArrayBitVectorVariables().size(); i++) {
			        valueX1 = parent1.getArrayBitVectorVariables().get(i);
			        valueX2 = parent2.getArrayBitVectorVariables().get(i);
			        offSpring[0].setArrayBitVectorVariables(i, valueX2);
			        offSpring[1].setArrayBitVectorVariables(i, valueX1);
			        offSpring[0].getArrayBitVectorVariables().set(i, valueX2);
			        offSpring[1].getArrayBitVectorVariables().set(i, valueX1);		        
			      } // for
		     
		    } // Decision
		  }
		 catch (ClassCastException e1) {
		  Configuration.logger_.severe("GModelSinglePointCrossover.doCrossover: Cannot perfom " +
		          "GModelSinglePointCrossover");
		  Class cls = java.lang.String.class;
		  String name = cls.getName();
		  throw new JMException("Exception in " + name + ".doCrossover()");
		}*/
		return offSpring;
} // doCrossover

/**
* Executes the operation
* @param object An object containing an array of two solutions
* @return An object containing an array with the offSprings
* @throws JMException
*/
public Object execute(Object object) throws JMException {
Solution[] parents = (Solution[]) object;

if (!(VALID_TYPES.contains(parents[0].getType().getClass())  &&
    VALID_TYPES.contains(parents[1].getType().getClass())) ) {

  Configuration.logger_.severe("SinglePointCrossover.execute: the solutions " +
          "are not of the right type. The type should be 'Binary' or 'Int', but " +
          parents[0].getType() + " and " +
          parents[1].getType() + " are obtained");

  Class cls = java.lang.String.class;
  String name = cls.getName();
  throw new JMException("Exception in " + name + ".execute()");
} // if

if (parents.length < 2) {
  Configuration.logger_.severe("SinglePointCrossover.execute: operator " +
          "needs two parents");
  Class cls = java.lang.String.class;
  String name = cls.getName();
  throw new JMException("Exception in " + name + ".execute()");
} 

Solution[] offSpring = null;
try {
	offSpring = doCrossover(crossoverProbability_,
	        parents[0],
	        parents[1]);
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

//-> Update the offSpring solutions 
//we set to zero since its a new solution..
for (int i = 0; i < offSpring.length; i++) {
  offSpring[i].setCrowdingDistance(0.0);
  offSpring[i].setRank(0);
}
return offSpring;
} // execute
} // SinglePointCrossover

