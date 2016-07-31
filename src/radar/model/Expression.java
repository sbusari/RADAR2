package radar.model;


public abstract class Expression {

	// used in knowing which expr to use in computing evppi and populating simparameter
	boolean isExpresionDistribution_;
	String parameterOption_;
	public abstract double [] simulate (Alternative s);
	public Expression() {
		
	}
	public boolean getIsExpresionDistribution(){
		return isExpresionDistribution_;
	}
	public String getparameterOption(){
		return parameterOption_;
	}

	
	

}
