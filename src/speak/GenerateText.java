package speak;

import java.util.HashMap;
import java.util.Map.Entry;

import learn.NathDB;

public class GenerateText {
	
	public static int MAX_LENGTH = 100; // Maximum length for a sentence
	
	public static String fromDB(NathDB db) {
		
		// The first last word is represented by NathDB.START
		String last = NathDB.START, sentence = "";
		
		// Retrieves a sequence of words randomly weighted by the values of the database
		int i = 0;
		while (!last.equals(NathDB.END) && i < MAX_LENGTH) {
			
			// Get the statistics
			HashMap<String, Integer> stats = db.getStat(last);
			int count = db.getStatCount(last);
			
			// Pick up a random number
			int rand = (int)(Math.random()*count);
			
			// Select the word wich is in the good interval
			int currentCount = 0;
			last = NathDB.END; // The default word is the end word if nothing can come after (normaly not possible but...)
			for (Entry<String, Integer> entry : stats.entrySet()) {
				currentCount += entry.getValue();
				if (rand < currentCount) {
					last = entry.getKey();
					break;
				}
			}
			
			// Add the word to the sentence (only if not the end word)
			if (!last.equals(NathDB.END)) {
				sentence += last + " ";
			}
			
			// Increment max length checker
			i++;
			
		}
		
		return sentence;
	}
	
}
