package learn;

import java.io.File;
import java.io.FileInputStream;

public class LearnFromDir {

	public static NathDB learn(String path) {
		
		// Create a database
		NathDB db = new NathDB();
		
		// Scan all files in directory
		File actual = new File(path);
        for( File f : actual.listFiles()) {
        	
        	// Check extension
        	if (f.getAbsolutePath().endsWith(".txt")) {
        	
	        	System.out.println("Reading " + f.getAbsolutePath());
	        	
	        	// Read the file
	        	String content = readFullFile(f);
	        	
	        	// Split the words
	        	String[] words = content.split(" ");
	        	
	        	// Add them to database
	        	String lastWord = NathDB.START;
	        	for (String word : words) {
	        		db.add(lastWord, word);
	        		lastWord = word + ""; // We add a empty string to prevent that lastWord became a reference to word
	        	}
	        	db.add(lastWord, NathDB.END);
	        	
        	}
        	
        }
    	
    	System.out.println(db.getFullCount() + " correspondences found.");
		
		return db;
	}
	
	// Read a full file and return the content as a text
	public static String readFullFile(File f) {
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] data = new byte[(int) f.length()];
			fis.read(data);
			fis.close();
			return new String(data);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
