package code;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class responsible for tokenising a book into verses and storing them in a HashMap to be searched through by Search
 * @author Josh Merritt, Tom Connolly
 *
 */
public class Tokeniser extends ClassLoader{

	/**
	 * HashMap to store the book being read by this instance
	 */
	private HashMap<String, String> map;

	/**
	 * Constructor initialising HashMap
	 */
	public Tokeniser(){
		map = new HashMap<String, String>();
	}
	
	/**
	 * Primary method to load a book into the HashMap
	 * @param file of the Bible to be loaded, e.g. Genesis.txt
	 * @param bookName - name of the book being loaded
	 * @return Entire HashMap of the book loaded
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public HashMap<String, String> loadBook(File file, String bookName) throws FileNotFoundException {
		Scanner chapSc = new Scanner(file);
		String currentChap;
		
		// use different delimiters depending on if book is Psalms or not
		if (bookName.equals("psalms")) {
			chapSc.useDelimiter("PSALM");
			chapSc.next();
		}
		else if (!bookName.equals("psalms")){
		chapSc.useDelimiter("CHAPTER");
		}
		
		currentChap = chapSc.next();
		
		//Number of chapter being loaded. used for making Key reference
		int chapNum = 1;
		
		
		while(chapSc.hasNext()){
			// skip 'Book of xxx' section of text on first run
			currentChap = chapSc.next(); 
			Scanner verseSc = new Scanner(currentChap);
			verseSc.useDelimiter("\n");

			
			
			// number of verse being loaded. used for making Key reference
			int verseNum = 1;
			// concatenation to build HashMap key in the form [book chapter:verse]
			String verseRef = bookName +" "+ chapNum +":"+ verseNum;
			
			//clause to move past the chapter/Psalm number
			if(verseSc.hasNext()){
				verseSc.next();
			}
			while (verseSc.hasNext()) {
				//instantiate scanner to check the first word in the string for a number
				String currentVerse = verseSc.next();
				Scanner checkSc = new Scanner(currentVerse);
					if(checkSc.hasNext()){
						//load first word in verse into a variable
						String check = checkSc.next();
						//check if word can be interpreted as a digit (even though it is currently held in a string)
						if (!Character.isDigit(check.charAt(0)) && verseSc.hasNext()) {
							//if it is not a digit, it must be pre-amble before a Psalm or Chapter and so should be skipped
							currentVerse = verseSc.next();
						}
				
				}
								
				// update the key creator
				verseRef = bookName +" "+ chapNum +":"+ verseNum;
				map.put(verseRef, currentVerse);
				
				// update the key creator when verse is finished
				verseNum++;
				
			}
			verseSc.close();
			chapNum++;
			// update key creator when chapter is finished
			verseRef = bookName +" "+ chapNum +":"+ verseNum;
		}
		
		chapSc.close();
		return map;
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		Tokeniser toke = new Tokeniser();
		File file = new File("src/textDocs/Psalms2.txt");
		toke.loadBook(file,"psalms");
		@SuppressWarnings({ "rawtypes", "unused" })
		ArrayList boo = new ArrayList();
	}
}
