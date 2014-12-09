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

public class Search {
	
	private HashMap<String,String> map;

	
	public Search(HashMap<String, String> map){
		this.map = map;
	}
	
	
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
	
	public String findVersesFromWord(String word){
//		 returns every verse that a specified word appears in
		
		ArrayList<String> verseLocations = getLocationsFromWord(word);
		String result = "";	
		
		for (String ver : verseLocations){
			result = result + map.get(ver) + "\n";
		}
		return result;
	
	}
	 
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
