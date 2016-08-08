package uk.ac.ucl.cs.radar.exception;

import java.io.Serializable;

public class ModelException extends Exception implements Serializable {
	  
	  /**
	   * Constructor
	   * @param Error message
	   */
	  public ModelException (String message){
	     super(message);      
	  } // ModelException
	  
	  
	  //TODO:
	  //IllegalArgumentException
		  
}
