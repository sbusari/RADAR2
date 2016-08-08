package uk.ac.ucl.cs.radar.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.ac.ucl.cs.radar.model.Decision;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.Parser;
import uk.ac.ucl.cs.radar.utilities.Helper;

public class ParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String inputFile = "./subjectmodels/CBA.gm"; 
		String inputFile = "./subjectmodels/PCFDM_FULL2.gm"; 
		//String inputFile = "./subjectmodels/SAS2.gm"; 
		//String inputFile = "./subjectmodels/ECS2.gm"; 
		//String inputFile = "./subjectmodels/BSPDM2.gm"; 
		//String inputFile = "./model/fitnessExpr.gm"; 
        String modelString = Helper.readFile(inputFile);
		Parser testParser = new Parser (modelString,10000); 
		Model semantic_model = testParser.getSemanticModel();
		Map<String , Decision> decisions = semantic_model.getDecisions();
		//List<Solution> allSolutions = new SolutionAnalyser(semantic_model).getAllSolutions();
		System.out.print("Done");

	}

}
