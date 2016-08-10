package radar.utilities;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Helper {

	public static void printResults (String directory, String expResult, String fileName, boolean append) throws IOException{
		File resultFile;
		resultFile = new File(directory);
		if (!resultFile.exists()) {
			new File(directory).mkdirs();
			System.out.println("Creating " + directory);
		}
	      FileOutputStream fos   = new FileOutputStream(directory + fileName, append ) ;
	      OutputStreamWriter osw = new OutputStreamWriter(fos)    ;
	      BufferedWriter bw      = new BufferedWriter(osw)        ;
	      bw.write(expResult);
	      bw.write("\n");
	      bw.close();
	      
	      /*FileWriter fileWritter = new FileWriter(directory + fileName,append);
	      fileWritter.write(expResult);
	      fileWritter.write("\n");
	      fileWritter.close();*/
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
	public static boolean deletePreviousResults (String outputdirectory, String folderToDelete){
		String path = outputdirectory + folderToDelete ;
		File previousResults = new File(path);
		boolean deleted = false;
		if (previousResults.exists()) {
			deleted = previousResults.delete();
			System.out.println("deleted " + path);
		}else{
			return false;
		}
		return deleted;
	     
	}
	public static BufferedImage getImage(Component c) {
	    BufferedImage bi = null;
	    try {
	        bi = new BufferedImage(c.getWidth(),c.getHeight(), BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d =bi.createGraphics();
	        c.print(g2d);
	        g2d.dispose();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    return bi;
	}

}
