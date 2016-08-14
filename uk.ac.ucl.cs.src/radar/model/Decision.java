package radar.model;

import java.util.ArrayList;
import java.util.List;

public class Decision {
	private String decisonLabel_;
	private List<String> options_;
	public Decision(){}
	public String getDecisionLabel (){
		return decisonLabel_;
	}
	public void setDecisionLabel (String decisonLabel){
		decisonLabel_ =decisonLabel;
	}
	public List<String> getOptions (){
		return options_;
	}
	public void setOptions (List<String>  options){
		options_ =options;
	}
	public void addOption (String  option){
		if (options_ == null){
			options_ = new ArrayList<String>();
			options_.add(option);
		}else{
			options_.add(option);
		}
	}
	boolean equals(Decision d){
		return this.decisonLabel_.equals(d.decisonLabel_);
	}

	public int hashCode(){
		return decisonLabel_.hashCode();
	}
}
