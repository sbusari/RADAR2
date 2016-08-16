package radar.model;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.tree.ParseTree;

import radar.parser.engine.Visitor;
import radar.parser.error.handler.ModelExceptionListener;
import radar.parser.generated.ModelLexer;
import radar.parser.generated.ModelParser;
import radar.utilities.Helper;

public class Parser {
	Model semanticmodel_;
	int nbr_simulation_;
	public Parser(){}
	public void setSemanticModel (Model model){
		semanticmodel_ =model;
	}
	public Model getSemanticModel(){
		return semanticmodel_;
	}
	public void setSimulationRun (int simulation){
		nbr_simulation_ =simulation;
	}
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
	        ParseTree tree = parser.model(); 
			visitor.visit(tree);
		}
		catch(InputMismatchException e){
			throw new RuntimeException("Input mismatch error: "+ e.getMessage());
		}
		catch(RuntimeException e){
			if (errorListener.getErrorMsg() != "") {
				throw new RuntimeException(errorListener.getErrorMsg() + "\nPossible resolution hints:\n1. Remove extraneous and unrecognised tokens within expressions. \n2. Ensure model keywords are written according to RADAR syntax. \n3. The equality signs '=' must be used when defining a quality variable. ");
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
			throw new RuntimeException( "Error: "+ re.getMessage());
		}
		return semanticModel;
	}
}
