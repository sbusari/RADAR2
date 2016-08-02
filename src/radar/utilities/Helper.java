package radar.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Helper {

	public static void printResults (String directory, String expResult, String fileName) throws IOException{
		File resultFile;
		resultFile = new File(directory);
		if (!resultFile.exists()) {
			new File(directory).mkdirs();
			System.out.println("Creating " + directory);
		}
	      FileOutputStream fos   = new FileOutputStream(directory + fileName ) ;
	      OutputStreamWriter osw = new OutputStreamWriter(fos)    ;
	      BufferedWriter bw      = new BufferedWriter(osw)        ;
	      bw.write(expResult);
	      bw.close();
	}
	@SuppressWarnings("resource")
	public static String readFile(String fileName) {
		StringBuilder model = new StringBuilder(100);
		BufferedReader bfr = null;

		try {
			bfr = new BufferedReader(new FileReader(new File(fileName)));
			String line = null;
			while ((line = bfr.readLine()) != null) {
				model.append(line + "\n");
			}
			model.delete(model.length() - 1, model.length());
			return model.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void printResult (String path, String infoValue, String fileName) throws IOException{
		File resultFile;
		String directory;
		directory = path;

		resultFile = new File(directory);
		if (!resultFile.exists()) {
			boolean result = new File(directory).mkdirs();
			System.out.println("Creating " + directory);
		}
		
	      FileOutputStream fos   = new FileOutputStream(directory + "/"+ fileName, true ) ;
	      OutputStreamWriter osw = new OutputStreamWriter(fos)    ;
	      BufferedWriter bw      = new BufferedWriter(osw)        ;
	      bw.write(infoValue);
	      bw.close();
	}
	public static ArrayList<String> readDecisionVectorsString(String path) {
	    try {
		      FileInputStream fis   = new FileInputStream(path)     ;
		      InputStreamReader isr = new InputStreamReader(fis)    ;
		      BufferedReader br      = new BufferedReader(isr)      ;
		      ArrayList<String> decisionVectors = new ArrayList<String>();
		      String aux = br.readLine();
		      while (aux!= null) {
		    	decisionVectors.add(aux);
		        aux = br.readLine();
		      }
		      br.close();
		      return decisionVectors;
	    } catch (Exception e) {
	      System.out.println("goalModel.util.decisionVectorString: "+path);
	      e.printStackTrace();
	    }
	    return null;
	  } 

}
