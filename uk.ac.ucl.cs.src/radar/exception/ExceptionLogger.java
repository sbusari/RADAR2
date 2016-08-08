package radar.exception;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ExceptionLogger {

	final String exceptionFileName = "Exception.txt";
	public void logException (String baseDirectory, String message) throws FileNotFoundException{
		File exceptionDirectory;
		exceptionDirectory = new File(baseDirectory);
		if (!exceptionDirectory.exists()) {
			boolean result = new File(baseDirectory).mkdirs();
			System.out.println("Creating " + baseDirectory);
		}
		
	      FileOutputStream fos   = new FileOutputStream(baseDirectory + exceptionFileName ) ;
	      OutputStreamWriter osw = new OutputStreamWriter(fos)    ;
	      BufferedWriter bw      = new BufferedWriter(osw)        ;

	}
	public String getLoggedException (String baseDirectory){
		String errorMsg = "";
		try {
		      FileInputStream fis   = new FileInputStream(baseDirectory + exceptionFileName);
		      InputStreamReader isr = new InputStreamReader(fis)    ;
		      BufferedReader br      = new BufferedReader(isr)      ;
		      
		      int lineNo =0;
		      String aux = br.readLine();
		      while (aux!= null) {
		    	  if (aux != "=ERROR="){
		    		  
		    	  }else{
		    		  
		    	  }
		    	errorMsg+=aux + "\n";
		        aux = br.readLine();
		      }
		      br.close();
		      return errorMsg;
	    } catch (Exception e) {
	      
	    }
	    return errorMsg;
	}
}
