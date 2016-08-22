package radar.model;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.tree.ParseTree;

import radar.parser.engine.Visitor;
import radar.parser.error.handler.ModelExceptionListener;
import radar.parser.error.handler.ModelParserErrorStrategy;
import radar.parser.generated.ModelLexer;
import radar.parser.generated.ModelParser;
import radar.utilities.Helper;
/**
 * @author Saheed A. Busari and Emmanuel Letier
 */
public class Parser {
	Model semanticmodel_;
	int nbr_simulation_;
	public Parser(){}
	 /**
	 * Sets the semantic model obtained after parsng the decision model.
	 * @param model the semantic model obtained after parsing.
	 */
	public void setSemanticModel (Model model){
		semanticmodel_ =model;
	}
	 /**
	 * @return the semantic model obtained after parsing.
	 */
	public Model getSemanticModel(){
		return semanticmodel_;
	}
	 /**
	 * Sets the number of simulation to be run.
	 * @param nbrSimulation the number of simulation.
	 */
	public void setSimulationRun (int nbrSimulation){
		nbr_simulation_ =nbrSimulation;
	}
	 /**
	 * @return the number of simulation.
	 */
	public int getSimulationRun(){
		return nbr_simulation_;
	}
	public Parser(String modelString, int nbr_simulation, String infoValueObjective,String subGraphObjective){
		parse(modelString,nbr_simulation, infoValueObjective, subGraphObjective);
	}
	public Visitor runVisitor (String inputString, int nbr_simulation,  String infoValueObjective,String subGraphObjective) {
		Visitor visitor = new Visitor(nbr_simulation, infoValueObjective, subGraphObjective);
		String model = inputString + "\n\n\n";
		ModelExceptionListener errorListener = null;
		try{
			ANTLRInputStream input = new ANTLRInputStream(model); 
	        ModelLexer lexer = new ModelLexer(input); 
	        CommonTokenStream tokens = new CommonTokenStream(lexer); 
	        ModelParser parser = new ModelParser(tokens); 
	        parser.removeErrorListeners(); // remove ConsoleErrorListener
	        errorListener = new ModelExceptionListener();
	        parser.addErrorListener(errorListener);
	        //parser.setErrorHandler(new ModelParserErrorStrategy());
	        ParseTree tree = parser.model(); 
	        if (errorListener.getErrorMsg() != "") {
				throw new RuntimeException(errorListener.getErrorMsg() + "\nPossible resolution hints:\n1. Remove extraneous and unrecognised tokens within expressions. \n2. Ensure model keywords are written according to RADAR syntax. \n3. The equality signs '=' must be used when defining a quality variable. \n4 Add semicolon at the end of each statement and ensure quality variable names do not start with a number.");
			}
			visitor.visit(tree);
		}
		catch(InputMismatchException e){
			throw new RuntimeException("Input mismatch error: "+ e.getMessage());
		}
		catch(RuntimeException e){
			if (errorListener.getErrorMsg() != "") {
				throw new RuntimeException(errorListener.getErrorMsg() + "\nPossible resolution hints:\n1. Remove extraneous and unrecognised tokens within expressions. \n2. Ensure model keywords are written according to RADAR syntax. \n3. The equality signs '=' must be used when defining a quality variable. \n4 Add semicolon at the end of each statement and ensure quality variable names do not start with a number. ");
			}else{
				throw new RuntimeException( e.getMessage());
			}
		}
		return visitor;
	}
	void parse(String inputString, int nbr_simulation,  String infoValueObjective,String subGraphObjective)  {
		Visitor visitor = runVisitor (inputString,nbr_simulation, infoValueObjective, subGraphObjective);	
		Model semanticModel = visitor.getSemanticModel();
		setSemanticModel(semanticModel);
	}
	public Model parseCommandLineModel (String modelPath,int nbr_simulation, String infoValueObjective, String subGraphObjective){
		Model semanticModel = null;
		try {
			String model = Helper.readFile(modelPath);
			Parser parser  = new Parser(model,nbr_simulation,infoValueObjective,subGraphObjective );
			semanticModel = parser.getSemanticModel();
		}
		catch (RuntimeException re){
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		
		return semanticModel;
	}
	public Model parseUIModel (String model,int nbr_simulation, String infoValueObjective, String subGraphObjective){
		Model semanticModel = null;
		try {
			Parser parser  = new Parser(model,nbr_simulation,infoValueObjective,subGraphObjective );
			semanticModel = parser.getSemanticModel();
		}
		catch (RuntimeException re){
			throw new RuntimeException(re.getMessage());
		}
		return semanticModel;
	}
}
