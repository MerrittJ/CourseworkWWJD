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
		// returns the number of times a word appears in the whole Bible
		
		word = word.toLowerCase(); // this is input cleaning, should this be here? Yes because it shows parameter word and scanned word are both lowercase. No because cleaning should go in Control (?)
		int wordCount = 0;
		for (String verse : map.values()) {
			String cleanVerse = verse.replaceAll("[^a-zA-Z\\s]", "");
			cleanVerse = cleanVerse.replaceAll("\r", "");
			cleanVerse = cleanVerse.toLowerCase();
			Scanner verseSc = new Scanner(cleanVerse);
			verseSc.useDelimiter(" ");
			while (verseSc.hasNext()) {
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
//		 returns every verse that a specified word appears in
		
		ArrayList<String> verseLocations = getLocationsFromWord(word);
		String result = "";	
		
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
		word = word.toLowerCase(); // this is input cleaning, should this be here? Yes because it shows parameter word and scanned word are both lowercase. No because cleaning should go in Control (?)
		
//		TreeMap<String, String> sortableMap = new TreeMap<String, String>();
//		sortableMap.putAll(map);
		
		
		Set<Entry<String, String>> keySet = map.entrySet();
		ArrayList<String> verseLocArray = new ArrayList<String>();
		
		for (String verse : map.values()) {
			String cleanVerse = verse.replaceAll("[^a-zA-Z\\s]", "");
			cleanVerse = cleanVerse.replaceAll("\r", "");
			cleanVerse = cleanVerse.toLowerCase();
			Scanner verseSc = new Scanner(cleanVerse);
			verseSc.useDelimiter(" ");
			
			while (verseSc.hasNext()) {
				if (verseSc.next().equals(word)) {
				for(Map.Entry<String, String> e: keySet){
						String verseLoc = e.getKey();
						String verseContents = e.getValue();
						if(verseContents.equals(verse)){
							verseLocArray.add(verseLoc);
						}
					}
				break;
				}
			}
			verseSc.close();
			}
		
		Collections.sort(verseLocArray);
		return verseLocArray;
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
		while (i < 99) { // 99 needs to be changed to an actual number???
				String getV = map.get(book+" "+chapNum+":"+i);
				if (getV == null) {
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
	
	public static void main(String args[]) throws FileNotFoundException {
		Tokeniser toke= new Tokeniser();
		File file = new File("src/textDocs/Genesis.txt");
		
		Search search = new Search(toke.loadToo(file, "Genesis"));
		
		System.out.println(search.getSpecificVerse("Genesis", "2", "13"));
		System.out.println(search.findVersesFromWord("day"));
		
	}
}
