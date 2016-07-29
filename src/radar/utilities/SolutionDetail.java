package radar.utilities;

import java.io.Serializable;

import radar.jmetal.core.SolutionSet;
import radar.model.Parser;

public class SolutionDetail implements Serializable {

	SolutionSet nonDominated_;
	SolutionSet dominated_;
	long executionTime_;
	Parser parserEngine_;
	public SolutionDetail(){
		
	}
	public void setNonDominatedSolutions (SolutionSet nonDominated){
		nonDominated_ = nonDominated;
	}
	public SolutionSet getNonDominatedSolutions (){
		return nonDominated_;
	}
	public void setDominatedSolutions (SolutionSet dominated){
		dominated_ = dominated;
	}
	public SolutionSet getDominatedSolutions (){
		return dominated_;
	}
	public void setGoalModelParserEngine (Parser parserEngine){
		parserEngine_ = parserEngine;
	}
	public Parser getGoalModelParserEngine(){
		return parserEngine_;
	}
	public void setExecutionTime (long executionTime){
		executionTime_ = executionTime;
	}
	public long getExecutionTime(){
		return executionTime_;
	}
}
