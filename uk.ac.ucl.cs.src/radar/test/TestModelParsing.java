package uk.ac.ucl.cs.radar.test;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import uk.ac.ucl.cs.radar.parser.error.handler.UnderlineModelExceptionListener;
import uk.ac.ucl.cs.radar.parser.generated.ModelLexer;
import uk.ac.ucl.cs.radar.parser.generated.ModelParser;
import uk.ac.ucl.cs.radar.utilities.Helper;


public class TestModelParsing {

	public static void main(String[] args) throws Exception {
		
		String inputFile = "./subjectmodels/CBA.gm"; 
		//String inputFile = "./subjectmodels/PCFDM_FULL2.gm"; 
		//String inputFile = "./subjectmodels/SAS.gm"; 
		//String inputFile = "./subjectmodels/ECS.gm"; 
		//String inputFile = "./subjectmodels/analysis.gm"; 
		//String inputFile = "./model/fitnessExpr.gm"; 
        String modelString = Helper.readFile(inputFile);

        ANTLRInputStream input = new ANTLRInputStream(modelString);
        ModelLexer lexer = new ModelLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ModelParser parser = new ModelParser(tokens);
        parser.removeErrorListeners(); // remove ConsoleErrorListener
        parser.addErrorListener(new UnderlineModelExceptionListener()); 
        //parser.addErrorListener(new GoalModelExceptionListener()); 
        //parser.addErrorListener(new UnderlineGoalModelExceptionListener()); 
       // parser.setErrorHandler(new GoalModelParserErrorStrategy());
        //HelloParser parser = new HelloParser(tokens);
        ParseTree tree = parser.model(); // begin parsing at rule 'r'
        //System.out.println (createJasminFile("ldc 42"));
        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),tree);
        viewr.setScale(0.7);//scale a little
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);
        
        
        
	}
}
