package radar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Objective {
	private boolean isMinimisation_;
	private String label_;
	private QualityVariable qualityVariable_;
	private Statistic definition_;
	private Map<Alternative, Double> value_;
	public Objective(){}
	public void setIsMinimisation(boolean isMinimisation ){
		isMinimisation_ =isMinimisation;
	}
	public boolean getIsMinimisation(){
		return isMinimisation_;
	}
	public void setLabel(String label ){
		label_ =label;
	}
	public String getLabel (){
		return label_;
	}
	public void setQualityVariable(QualityVariable qualityVariable ){
		qualityVariable_ =qualityVariable;
	}
	public QualityVariable getQualityVariable (){
		return qualityVariable_;
	}
	public void setStatistic (Statistic definition){
		definition_ =  definition;
	}
	public Statistic getStatistic (){
		return definition_;
	}
	public double evaluate (Alternative a){
		if (value_ == null){
			value_ = new LinkedHashMap<Alternative, Double>();
			double result  = definition_.evaluate(a, qualityVariable_);
			value_.put(a, result);
			return result;
		}
		//else if (!value_.containsKey(a)) {
		else if (value_.get(a) == null) {
			double result  = definition_.evaluate(a, qualityVariable_);
			value_.put(a, result);
			return result;
		}
		else{
			return value_.get(a);
		}
	}
}
