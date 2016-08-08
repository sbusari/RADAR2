package radar.model;

import radar.utilities.Statistics;

class Probability extends Statistic {

	private double x_scaler;
	private double[]  x_array;
	boolean xIsAScaler;
	Comparator comparator_;
	public Probability(){}
	public void xIsAScaler(boolean isXScalerValue){
		xIsAScaler = isXScalerValue;
	}
	public void setScalerValue (double x){
		x_scaler =x;
	}
	public void setComparator (String comparator){
		switch(comparator){
			case "<": comparator_ = Comparator.LESS; break;
			case ">": comparator_ = Comparator.GRET; break;
			case "<=": comparator_ = Comparator.LEQ; break;
			case ">=": comparator_ = Comparator.GEQ; break;
		}
	}
	@Override
	double evaluate(Solution s, QualityVariable var) {
		double [] simData = var.getSimData(s);
		double [] booleanData = applyComparator (simData);
		return new Statistics(booleanData).computeMean();
	}
	
	double [] applyComparator (double[] simData){
		for (int i =0 ; i < simData.length; i ++){
			switch (comparator_.getComparatorValue()){
				case "<" : {
					simData[i] = (simData[i] < x_scaler)? 1:0;
				}; break;
				case ">" : {
					simData[i] = (simData[i] > x_scaler)? 1:0;
				}; break;
				case "<=" : {
					simData[i] = (simData[i] <= x_scaler)? 1:0;
				}; break;
				case ">=" : {
					simData[i] = (simData[i] >= x_scaler)? 1:0;
				}; break;
			}
		}
		return simData;
	}

}
