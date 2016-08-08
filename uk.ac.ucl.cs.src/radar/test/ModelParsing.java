package uk.ac.ucl.cs.radar.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import junit.framework.Assert;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ucl.cs.radar.model.Decision;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.Parser;
import uk.ac.ucl.cs.radar.parser.error.handler.UnderlineModelExceptionListener;
import uk.ac.ucl.cs.radar.parser.generated.ModelLexer;
import uk.ac.ucl.cs.radar.parser.generated.ModelParser;
import uk.ac.ucl.cs.radar.utilities.Helper;

public class ModelParsing {

	String modelString ="";
	ANTLRInputStream input = null;
	ModelLexer lexer = null;
	ParseTree tree = null;
	ModelParser parser = null;
	Model semantic_model =null;

	@Before
	public void setUp() throws Exception {
		//String inputFile = "./subjectmodels/CBA.gm"; 
		String inputFile = "./subjectmodels/PCFDM_FULL2.gm"; 
		//String inputFile = "./subjectmodels/SAS.gm"; 
		//String inputFile = "./subjectmodels/ECS.gm"; 
		//String inputFile = "./subjectmodels/analysis.gm"; 
		//String inputFile = "./model/fitnessExpr.gm"; 
        modelString = Helper.readFile(inputFile);
        input = new ANTLRInputStream(modelString);
        lexer = new ModelLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new ModelParser(tokens);
        parser.removeErrorListeners(); // remove ConsoleErrorListener
        parser.addErrorListener(new UnderlineModelExceptionListener()); 
	}

	@Test
	public void testModelParsing() throws Exception {
		tree = parser.model(); // begin parsing at rule 'r'
		display();
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testSemanicModel() throws Exception {
		Parser model_parser = new Parser (modelString, 10000);
		semantic_model = model_parser.getSemanticModel();
		Assert.assertNotNull(semantic_model);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void generateSolutions() throws Exception {
		Parser model_parser = new Parser (modelString,10000);
		semantic_model = model_parser.getSemanticModel();
		Map<String , Decision> decisions = semantic_model.getDecisions();
		//List<Solution> allSolutions =  new SolutionAnalyser(semantic_model).getAllSolutions() ;
		//Assert.assertNotSame(0, allSolutions.size());
	}
	
	public void display() throws Exception {
		JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()),tree);
        viewr.setScale(0.7);//scale a little
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);
	}

}