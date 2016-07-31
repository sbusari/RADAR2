package radar.jmetal.exact.algorithm;
import java.util.HashMap;
import java.util.Properties;

import radar.jmetal.core.Algorithm;
import radar.jmetal.core.Problem;
import radar.jmetal.experiments.Settings;
import radar.jmetal.util.JMException;
import radar.jmetal.util.ProblemFactory;
import radar.model.Parser;

public class ExhaustiveSearch_Settings extends Settings {
	Parser parserEngine_;
	public String alternativesObjectiveAndDecisionsPath_;
	public ExhaustiveSearch_Settings(){
		
	}
	public ExhaustiveSearch_Settings(String problemName, String solutionType,
			Parser goalParserEngine,Problem problem) {
		parserEngine_= goalParserEngine;
		problem_ = problem;	    
	}
	public ExhaustiveSearch_Settings(String problemName, String solutionType,
			Parser goalParserEngine,Problem problem, String alternativesObjectiveAndDecisionsPath ) {
		parserEngine_= goalParserEngine;
		problem_ = problem;	
		alternativesObjectiveAndDecisionsPath_ =alternativesObjectiveAndDecisionsPath ;
	}
	
	/**
	* Configure ExhaustiveSearch
	* @return A ExhaustiveSearch algorithm object
	* @throws radar.jmetal.util.JMException
	*/
	public Algorithm configure() throws JMException {
		Algorithm algorithm ;
		algorithm = new ExhaustiveSearch(problem_,parserEngine_ ,alternativesObjectiveAndDecisionsPath_ ) ;
		return algorithm ;
	} // configure
	
	/**
	* Configure ExhaustiveSearch
	* @return A ExhaustiveSearch algorithm object
	*/
	@Override
	public Algorithm configure(Properties configuration) throws JMException {
	
		Algorithm algorithm ;
		algorithm = new ExhaustiveSearch(problem_,parserEngine_  ) ;
		return algorithm ;
	}

}
