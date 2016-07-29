package radar.model;

class Number extends ArithmeticExpression {

	private double value_;
	public void setValue (double value){
		value_ = value;
	}
	public double  getValue (){
		return value_;
	}
	@Override
	public double[] simulate(Alternative s) {
		double [] sim = new double [1];
		sim[0] = value_;
		return sim;
	}
}
