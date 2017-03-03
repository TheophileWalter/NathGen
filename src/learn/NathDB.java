package learn;

import java.util.HashMap;
import java.util.Map.Entry;

public class NathDB {
	
	// First String represents the current word, second the word that comes after and Integer the number of occurences
	private HashMap<String, HashMap<String, Integer>> db;
	
	// Contains the number of words comming after a word (sum of Integer of the second HashMap in db)
	private HashMap<String, Integer> dbCount;
	
	// Representation of start and end of a sentence
	public static String START = "&start;";
	public static String END = "&end;";

	public NathDB() {
		
		// Set up the database
		db = new HashMap<String, HashMap<String, Integer>>();
		dbCount = new HashMap<String, Integer>();
		
	}
	
	// Add an entry
	public void add(String firstWord, String secondWord) {
		
		// Check if we need to create an entry for firstWord
		if (!db.containsKey(firstWord)) {
			db.put(firstWord, new HashMap<String, Integer>()); // Create the correspondence
			dbCount.put(firstWord, 1); // Create the total count
		} else {
			// Increment the count
			dbCount.put(firstWord, dbCount.get(firstWord)+1);
		}
		
		// Check if we need to create an entry for secondWord
		HashMap<String, Integer> subDb = db.get(firstWord);
		if (!subDb.containsKey(secondWord)) {
			// We put 1 because it is the first time we meet that word configuration
			subDb.put(secondWord, 1);
		} else {
			// Else we just increment the count
			subDb.put(secondWord, subDb.get(secondWord)+1);
		}
		
	}
	
	// Return the total correspondences in the database
	public int getFullCount() {
		int total = 0;
		for (Entry<String, Integer> entry : dbCount.entrySet()) {
			total += entry.getValue();
		}
		return total;
	}
	
	// Return a hashmap for a word
	public HashMap<String, Integer> getStat(String word) {
		return db.get(word);
	}
	
	// Return the total count for a word
	public int getStatCount(String word) {
		return dbCount.get(word);
	}
	
	// Export database as JSon (for JavaScript generation)
	public String toJSon() {
		String JSon = "{\"words\":{";
		
		// Make an iteration on the whole database
		for (Entry<String, HashMap<String, Integer>> entry : db.entrySet()) {
			
			JSon += "\"" + entry.getKey().replaceAll("\"", "\\\\\"") + "\":{\"c\":" + dbCount.get(entry.getKey()) + ",\"l\":{";
			
			// Iterate on the statistics
			for (Entry<String, Integer> subEntry : db.get(entry.getKey()).entrySet()) {
				
				JSon += "\"" + subEntry.getKey().replaceAll("\\\"", "\\\\\"") + "\":" + subEntry.getValue() + ",";
				
			}
			
			JSon = JSon.substring(0, JSon.length()-1); // Remode last coma
			JSon += "}},";
			
			
		}

		JSon = JSon.substring(0, JSon.length()-1); // Remode last coma
		return JSon + "}}";
	}
	
}
