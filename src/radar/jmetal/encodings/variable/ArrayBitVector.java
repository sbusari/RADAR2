package radar.jmetal.encodings.variable;


import java.util.ArrayList;
import java.util.Collections;

import radar.jmetal.core.Problem;
import radar.jmetal.core.Variable;
import radar.jmetal.util.Configuration;
import radar.jmetal.util.JMException;
import radar.jmetal.util.PseudoRandom;


/**
* Class implementing a decision encodings.variable representing an array of integers.
* The integer values of the array have their own bounds.
*/
public class ArrayBitVector extends Variable {

	/**
	 * Problem using the type
	 */
	private Problem problem_ ;
	
	/**
	* Stores an array of integer values
	*/
	public ArrayList<int[]> multiArray_;
	
	/**
	* Stores the length of the array
	*/
	private int size_;
	
	private int options_;
	
	private int decisionPoints_;

	/**
	* Store the lower and upper bounds of each int value of the array in case of
	* having each one different limits
	*/
	private int []lowerBounds_ ;
	private int []upperBounds_ ;
	
	/**
	* Constructor
	*/
	public ArrayBitVector() {
	lowerBounds_ = null ;
	upperBounds_ = null ;
	size_   = 0;
	decisionPoints_ =0;
	options_ =0;
	multiArray_ = null;
	problem_ = null ;
	} // Constructor

	/**
	* Constructor
	* @param size Size of the array
	*/
	public ArrayBitVector(ArrayList<int[]> decisionOptions) {
		size_   = decisionOptions.size();
		multiArray_ = decisionOptions;
		decisionPoints_ =decisionOptions.size();
		//options_ =options;
		lowerBounds_ = new int[size_];
		upperBounds_ = new int[size_];
	} // Constructor


	/**
	* Constructor
	* @param size Size of the array
	*/
	public ArrayBitVector(ArrayList<int[]> decisionOptions, Problem problem) {
		problem_ = problem ;
		size_   = decisionOptions.size();
		decisionPoints_ =decisionOptions.size();
		//options_ =options;
		multiArray_ = decisionOptions;
		lowerBounds_ = new int[size_] ;
		upperBounds_ = new int[size_];
		
		for (int i = 0; i < decisionPoints_ ; i++) {
			int[] a = new int[decisionOptions.get(i).length];
			for(int j = 0; j < a.length ; j++){
				lowerBounds_[i] = (int)problem_.getLowerLimit(i);
				  upperBounds_[i] = (int)problem_.getUpperLimit(i);
				 a[j] =PseudoRandom.randInt(lowerBounds_[i], upperBounds_[i]);
			}
			 multiArray_ .add(i, a);
		}  
	
	} // Constructor

	/**
	* Constructor 
	* @param size The size of the array
	* @param lowerBounds Lower bounds
	* @param upperBounds Upper bounds
	*/
	public ArrayBitVector(ArrayList<int[]> decisionOptions, double[] lowerBounds, double [] upperBounds) {
		size_   = decisionOptions.size();
		decisionPoints_ =decisionOptions.size();	
		multiArray_ = decisionOptions;	
		lowerBounds_ = new int[decisionPoints_] ;
		upperBounds_ = new int[decisionPoints_] ;
	
		for (int i = 0; i < decisionPoints_ ; i++) {
			int[] a = new int[decisionOptions.get(i).length];
			for(int j = 0; j < a.length ; j++){
				lowerBounds_[i] = (int)problem_.getLowerLimit(i);
				  upperBounds_[i] = (int)problem_.getUpperLimit(i);
				 a[j] =PseudoRandom.randInt(lowerBounds_[i], upperBounds_[i]);
			}
			 multiArray_ .add(i, a);
		}  
	} // Constructor

	/** 
	* Copy Constructor
	* @param arrayInt The arrayInt to copy
	*/
	private ArrayBitVector(ArrayBitVector arrayInt) {
		size_   = arrayInt.size_;
		decisionPoints_ = arrayInt.decisionPoints_;
		options_ = arrayInt.options_;
		    	
		lowerBounds_ = new int[decisionPoints_] ;
		upperBounds_ = new int[decisionPoints_] ; 
			
		Collections.copy(multiArray_, arrayInt.multiArray_);
		lowerBounds_= arrayInt.lowerBounds_ ;
		upperBounds_ = arrayInt.upperBounds_ ;
		
		/*for (int i = 0; i < decisionPoints_; i++) {	
			lowerBounds_[i] = arrayInt.lowerBounds_[i] ;
			upperBounds_[i] = arrayInt.upperBounds_[i] ;
		 
		} // for
	*/} // Copy Constructor

	@Override
	public Variable deepCopy() {
		return new ArrayBitVector(this);
	} // deepCopy

	/**
	* Returns the length of the MultiarrayInt.
	* @return The length
	*/
	public int getLength(){
		return size_;
	} // getLength
	  
	/**
	* getValue
	* @param index Index of value to be returned
	* @return the value in position index
	*/
	public int[] getValue(int index) throws JMException {
		  if ((index >= 0) && (index < size_))
			  return multiArray_.get(index) ;
		  else {
			  Configuration.logger_.severe(radar.jmetal.encodings.variable.ArrayBitVector.class+".getValue(): index value (" + index + ") invalid");
			  throw new JMException(radar.jmetal.encodings.variable.ArrayBitVector.class+": index value (" + index + ") invalid") ;
		  } // if
	} // getValue


	/**
	* setValue
	* @param index Index of value to be returned
	* @param value The value to be set in position index
	*/
	public void setValue(int index, int [] value) throws JMException {
		if ((index >= 0) && (index < size_))
			multiArray_.add(index, value);
		else {
		 Configuration.logger_.severe(radar.jmetal.encodings.variable.ArrayBitVector.class+".setValue(): index value (" + index + ") invalid");
		 throw new JMException(radar.jmetal.encodings.variable.ArrayBitVector.class+": index value (" + index + ") invalid") ;
		} // else
	} // setValue

	public int getMultipleValue(int indexI, int indexJ) throws JMException {
		
		if ((indexI >= 0) && (indexI < decisionPoints_) && (indexJ >= 0) && (indexJ < options_) ){
						return multiArray_.get(indexI)[indexJ];	
		}	
		else {
		 Configuration.logger_.severe(radar.jmetal.encodings.variable.ArrayBitVector.class+".getMultipleValue(): index value (" + indexI + ") invalid");
		 throw new JMException(radar.jmetal.encodings.variable.ArrayBitVector.class+": index value (" + indexI + ") invalid") ;
		} // else
	
	} // setValue


	public void setMultipleValue(int indexI, int indexJ, int value) throws JMException {
		if ((indexI >= 0) && (indexI < decisionPoints_)){
			if((indexJ >= 0) && (indexJ < options_)){
				multiArray_.get(indexI)[indexJ] = value;
			}
		}	
		else {
		 Configuration.logger_.severe(radar.jmetal.encodings.variable.ArrayBitVector.class+".setMultipleValue(): index value (" + indexI + ") invalid");
		 throw new JMException(radar.jmetal.encodings.variable.ArrayBitVector.class+": index value (" + indexI + ") invalid") ;
		} // else
	} // setValue


	/**
	 * Get the lower bound of a value
	 * @param index The index of the value
	 * @return the lower bound
	 */
	public double getLowerBound(int index) throws JMException {
		if ((index >= 0) && (index < size_))
			return lowerBounds_[index] ;
		else {
			Configuration.logger_.severe(radar.jmetal.encodings.variable.ArrayBitVector.class+".getLowerBound(): index value (" + index + ") invalid");
			throw new JMException(radar.jmetal.encodings.variable.ArrayBitVector.class+".getLowerBound: index value (" + index + ") invalid") ;
		} // else	
	} // getLowerBound

	/**
	 * Get the upper bound of a value
	 * @param index The index of the value
	 * @return the upper bound
	 */
	public double getUpperBound(int index) throws JMException {
		if ((index >= 0) && (index < size_))
			return upperBounds_[index];
		else {
			Configuration.logger_.severe(radar.jmetal.encodings.variable.ArrayBitVector.class+".getUpperBound(): index value (" + index + ") invalid");
			throw new JMException(radar.jmetal.encodings.variable.ArrayBitVector.class+".getUpperBound: index value (" + index + ") invalid") ;
		} // else
	} // getLowerBound


	/**
	* Returns a string representing the object
	* @return The string
	*/ 
	public String toString(){
		 String string ;
		  
		 string = "" ;
		 for (int i = 0; i < decisionPoints_ ; i ++){
			 for(int j = 0; j < options_ ; j++){
				 string += multiArray_.get(i)[j] + " " ;
			 }
		   
		 }
		 return string ;
	} // toString  
} // ArrayBitVector

