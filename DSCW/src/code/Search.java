package code;


import java.util.HashMap;

import java.util.Map;


import externalCode.Book;

public class Search {
	
	private HashMap<String,String> books;

	private Map<String, String> map;

	public Search(HashMap books){
		this.books = books;
	}
	public Search(Map<String, String> map){
		this.map = map;
	}
	
	
	public String findNumOfTimesFromWord(String word){
		return "Success! The word you entered was '" + word + "'.";
	 }
	
	public String findVersesFromWord(String word){
		return "Success! The word you entered was '" + word + "'.";
	}
	 
	public String getLocationFromWord(String word){
		return "Success! The word you entered was '" + word + "'.";
	}
	 
	public String getChapterFromBookAndChapNum(String book, String chapNum){
		
		String retChap = "";
		int i = 0;
		while (i < 99) {
			try {
				retChap.concat("\n" + map.get(book+":"+chapNum+":"+i));
				}
			catch (NullPointerException e) {
				break;
			}
			i++;
		}
		return retChap;	
		//return "Success! The book you entered was '" + book + "' and the chapter number was '" + chapNum + "'.";
	}
	 
	public String getVersesFromFirstAndLastVerses(String book, int chapNum, int firstVerseNum, int lastVerseNum){
		return "Success! The book you entered was '" + book + "', the Chapter number was '" + chapNum + "', the first verse number you entered was '" + firstVerseNum + "' and the last verse number you entered was '" + lastVerseNum + "'.";
	}
	 
	public String getSpecificVerse(String book, int chapNum, int verseNum){
		return "Success! The book you entered was '" + book + "', the chapter number was '" + chapNum + "' and the verse number was '" + verseNum + "'." ;
	}
}
