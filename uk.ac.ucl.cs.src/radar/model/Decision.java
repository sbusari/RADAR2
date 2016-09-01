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
	/**
	 * Sets the name of a decision.
	 * @param decisonLabel name of decision.
	 */
	public void setDecisionLabel (String decisonLabel){
		decisionLabel_ =decisonLabel;
	}
	/**
	 * Sets the option names for a decision instance.
	 * @param options the list of option names for a decision instance.
	 */
	public void setOptions (List<String>  options){
		options_ =options;
	}
	/**
	 * @return the list of option names for a decision instance.
	 */
	public List<String> getOptions (){
		return options_;
	}
	/**
	 * adds an option name to a decision instance.
	 * @param option an option name of a decision instance.
	 */
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
	/**
	 * @return the decision name.
	 */
    @Override
    public String toString(){
        return decisionLabel_;
    }
    /**
     * @param d decision to compare.
	 * @return true if two decision instances are equal
	 */
	boolean equals(Decision d){
		return this.decisionLabel_.equals(d.decisionLabel_);
	}

	public int hashCode(){
		return decisionLabel_.hashCode();
	}
}
