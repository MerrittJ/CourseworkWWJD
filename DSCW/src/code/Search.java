package code;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

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
		//return "Success! The word you entered was '" + word + "'.";
	 }
	
	public String findVersesFromWord(String word){
		// returns every verse that a specified word appears in
//		
//		return "Success! The word you entered was '" + word + "'.";
//		String locations = getLocationFromWord(word);
//		Scanner locScan = new Scanner(word);
//		locScan.useDelimiter("\n");
//		
//		String[] locCollec = new String[];
//		
//		int i = 0;
//		while(locScan.hasNext()){
//			
//		locCollec[i] = locScan.next();
//		
//		
//		}
//		
		
		return "string";
	
	}
	 
	public String getLocationFromWord(String word){
		// returns a list of verses that a specified word appears in, formatted as [book chapter:verse]
		word = word.toLowerCase(); // this is input cleaning, should this be here? Yes because it shows parameter word and scanned word are both lowercase. No because cleaning should go in Control (?)
		
		Set<Entry<String, String>> keySet = map.entrySet();
		ArrayList<String> verseLocArray = new ArrayList();
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
				}
			}
			verseSc.close();
			}
		
		String verseReturn = verseLocArray.size() + " ";
		for(int i = 0; i<verseLocArray.size(); i++){
			 verseReturn = verseReturn + verseLocArray.get(i) + " ";
		}
		return verseReturn;
	}
	 
	public String getChapterFromBookAndChapNum(String book, String chapNum){
		
		String retChap = "";
		int i = 1;
		while (i < 99) { // 99 needs to be changed to an actual number???
				String getV = map.get(book+":"+chapNum+":"+i);
				if (getV == null) {
					break;
				}
				else {
				retChap = retChap + getV + "\n";
				}
			i++;
		}
		return retChap;	
		//return "Success! The book you entered was '" + book + "' and the chapter number was '" + chapNum + "'.";
	}
	 
	public String getVersesFromFirstAndLastVerses(String book, String chapNum, String firstVerseNum, String lastVerseNum){
		
		String retVs = "";
		int i = Integer.parseInt(firstVerseNum);
		int lastV = Integer.parseInt(lastVerseNum);
		while (i <= lastV) {
			retVs = retVs + map.get(book+":"+chapNum+":"+i) + "\n";
			i++;
		}
		return retVs;
		
		
		//return "Success! The book you entered was '" + book + "', the Chapter number was '" + chapNum + "', the first verse number you entered was '" + firstVerseNum + "' and the last verse number you entered was '" + lastVerseNum + "'.";
	}
	 
	public String getSpecificVerse(String book, String chapNum, String verseNum){
		String ref = book + ":" + chapNum + ":" + verseNum;
		return map.get(ref);
	//	return "Success! The book you entered was '" + book + "', the chapter number was '" + chapNum + "' and the verse number was '" + verseNum + "'." ;
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		Tokeniser toke= new Tokeniser();
		File file = new File("src/textDocs/Psalms.txt");
		
		Search search = new Search(toke.loadToo(file, "Psalms"));
		//System.out.println(search.getChapterFromBookAndChapNum("Genesis", "1")); // ignore the mystery newline at the end of Genesis 1, there's a carriage return in the .txt
		System.out.println(search.getSpecificVerse("Psalms", "1", "1"));
		//System.out.println(search.getVersesFromFirstAndLastVerses("Genesis", "1", "1", "5"));
		System.out.println(search.findNumOfTimesFromWord("God"));
	}
}
