package radar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import prefuse.data.Graph;

public class Objective {
	private double margin_;
	private boolean isMinimisation_;
	private String label_;
	private QualityVariable qualityVariable_;
	private Statistic definition_;
	private Map<Alternative, Double> value_;
	public Objective(){}
	public Objective (Objective o){
		isMinimisation_ = o.getIsMinimisation();
		label_ = o.getLabel();
		qualityVariable_ = o.getQualityVariable();
		definition_ =o.getStatistic();
		margin_ = o.getMargin();
		
	}
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
	public void setMargin(double margin ){
		margin_ =margin;
	}
	public double getMargin (){
		return margin_;
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
		else if (value_.get(a) == null) {
			double result  = definition_.evaluate(a, qualityVariable_);
			value_.put(a, result);
			return result;
		}
		else{
			return value_.get(a);
		}
	}
	@Override
	public int hashCode (){
		if (qualityVariable_ == null){
			return 0;
		}
		return qualityVariable_.hashCode();
	}
}
