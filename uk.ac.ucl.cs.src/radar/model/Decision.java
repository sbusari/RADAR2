package radar.model;

import java.util.ArrayList;
import java.util.List;

public class Decision {
	private String decisionLabel_;
	private List<String> options_;
	public Decision(){}
	public String getDecisionLabel (){
		return decisionLabel_;
	}
	public void setDecisionLabel (String decisonLabel){
		decisionLabel_ =decisonLabel;
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
	@Override
    public boolean equals(Object o){
        if (o.getClass() !=  getClass()) return false;
        Decision d = (Decision) o;
        return this.decisionLabel_.equals(d.decisionLabel_);
    }
    
    @Override
    public String toString(){
        return decisionLabel_;
    }

	boolean equals(Decision d){
		return this.decisionLabel_.equals(d.decisionLabel_);
	}

	public int hashCode(){
		return decisionLabel_.hashCode();
	}
}
