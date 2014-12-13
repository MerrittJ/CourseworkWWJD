package code;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class responsible for searching through HashMaps to find word counts, word locations, and verses
 * 
 * @author Josh Merritt, Theo Matthews
 *
 */
public class Search {
	
	/**
	 * HashMap variable to hold a single book
	 */
	private HashMap<String,String> map;

	/**
	 * Constructor assigning parameter to global variable
	 * @param map
	 */
	public Search(HashMap<String, String> map){
		this.map = map;
	}
	
	/**
	 * Method to find the number of times a word appears in the entire Bible
	 * @param word you wish you search for
	 * @return the number of times it appears
	 */
	public int findNumOfTimesFromWord(String word){
		// standardise input
		word = word.toLowerCase();
		int wordCount = 0;
		// iterate through the HashMap values
		for (String verse : map.values()) {
			// standardise the verses by removing punctuation and carriage returns and making them lowercase
			String cleanVerse = verse.replaceAll("[^a-zA-Z\\s]", "");
			cleanVerse = cleanVerse.replaceAll("\r", "");
			cleanVerse = cleanVerse.toLowerCase();
			Scanner verseSc = new Scanner(cleanVerse);
			verseSc.useDelimiter(" ");
			// scan the verse and check if it matches the input word
			while (verseSc.hasNext()) {
				// if so, increment the count
				if (verseSc.next().equals(word)) {
					wordCount++;
				}
			}
			verseSc.close();
		}
		
		return wordCount;
		 }
	
	/**
	 * Method to return verses that a given word appears in. If a word appears more than once in a verse, the verse only appears once in the return result. This returns the whole verse.
	 * @param word you wish to search verses for
	 * @return a string of all the verses the word appears in
	 */
	public String findVersesFromWord(String word){
		// use the method getLocationsFromWord to do all of the searching
		ArrayList<String> verseLocations = getLocationsFromWord(word);
		String result = "";	
		// using the verse locations keys, add each verse values to the returning String
		for (String ver : verseLocations){
			result = result + map.get(ver) + "\n";
		}
		return result;
	
	}
	 /**
	  * Method to get the locations of verses where a given word appears in. If a word appears more than once in a verse, the location only appears once in the return result. This returns the location of the verse.
	  * @param word you wish to search verses for
	  * @return an ArrayList of each of the locations 
	  */
	public ArrayList<String> getLocationsFromWord(String word){
		
		// standardise input
		word = word.toLowerCase(); 
		
		// Create a Set that can store both the Key and the Value stored in the Map
		Set<Entry<String, String>> keySet = map.entrySet();
		//An ArrayList to store the verse locations. 45,000 was chosen for capacity as it's roughly 1.5 times the number of verse in the Bible.
		ArrayList<String> verseLocations = new ArrayList<String>(45000);
		
		for (String verse : map.values()) {
			// standardise the verses by removing punctuation and carriage returns and making them lowercase
			String cleanVerse = verse.replaceAll("[^a-zA-Z\\s]", "");
			cleanVerse = cleanVerse.replaceAll("\r", "");
			cleanVerse = cleanVerse.toLowerCase();
			Scanner verseSc = new Scanner(cleanVerse);
			verseSc.useDelimiter(" ");
			
			//Go through the text, scanning each word and if the word matches up with the searched word, search through 
			//the set that contains all the verses, then when the matching verse is found, return its Key and place it 
			//the array verseLocations
			while (verseSc.hasNext()) {
				if (verseSc.next().equals(word)) 
				{
					for(Map.Entry<String, String> e: keySet)
					{
						String verseLocation = e.getKey();
						String verseContents = e.getValue();
						if(verseContents.equals(verse))
						{
							verseLocations.add(verseLocation);
						}
					}
				break;
				}
			}
			verseSc.close();
		}
		return verseLocations;
	}
	 
	/**
	 * Method to return a chapter given the book and chapter number.
	 * @param book the desired chapter is found in
	 * @param chapNum - the chapter/psalm number to be returned
	 * @return a string of all the verses in the desired chapter, separated by newlines
	 */
	public String getChapterFromBookAndChapNum(String book, String chapNum){
		
		String retChap = "";
		int i = 1;
		// iterate through the verses in the desired chapter and add them to the returning String
		while (i < 99) { 
				String getV = map.get(book+" "+chapNum+":"+i);
				if (getV == null) {
					// if the number of verses in the chapter is less than 99 (certain), break the loop to prevent returning more null values
					break;
				}
				else {
				retChap = retChap + getV + "\n";
				}
			i++;
		}
		return retChap;	
	}
	 
	/**
	 * Method to return all verses between two verses
	 * @param book that the verses are found in
	 * @param chapNum - chapter/psalm number the verses are found in
	 * @param firstVerseNum - verse number at start of block of verses to be returned
	 * @param lastVerseNum - final verse in block of verses to be returned
	 * @return string of all the desired verses, separated by newlines
	 */
	public String getVersesFromFirstAndLastVerses(String book, String chapNum, String firstVerseNum, String lastVerseNum){
		
		String retVs = "";
		int i = Integer.parseInt(firstVerseNum);
		int lastV = Integer.parseInt(lastVerseNum);
		// iterate through the verses between first and last verse and add to the returning String
		while (i <= lastV) {
			retVs = retVs + map.get(book+" "+chapNum+":"+i) + "\n";
			i++;
		}
		return retVs;
		
	}
	 
	/**
	 * Method to return a specific verse
	 * @param book that the verse appears in
	 * @param chapNum - chapter/psalm number the verse appears in
	 * @param verseNum - desired verse
	 * @return a string of the desired verse
	 */
	public String getSpecificVerse(String book, String chapNum, String verseNum){
		String ref = book + " " + chapNum + ":" + verseNum;
		return map.get(ref);
	}
	
	/**
	 * Main method used for testing this class
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String args[]) throws FileNotFoundException {
		Tokeniser toke= new Tokeniser();
		File file = new File("src/textDocs/Genesis.txt");
		
		Search search = new Search(toke.loadBook(file, "Genesis"));
		
		System.out.println(search.getSpecificVerse("Genesis", "2", "13"));
		System.out.println(search.findVersesFromWord("day"));
		
	}
}
