package main;

import learn.LearnFromDir;
import learn.NathDB;
import speak.GenerateText;

public class NathGen {

	public static void main(String args[]) {
		
		// Path to search ("out" directory)
		String path = "./out/";
		
		System.out.println("Starting learning from " + path);
		
		// Start lerning
		NathDB db = LearnFromDir.learn(path);
		 
		// Generate some sentences
		System.out.println(GenerateText.fromDB(db));
		
		// Print a JSon
		//System.out.println(db.toJSon());
		
	}

}
