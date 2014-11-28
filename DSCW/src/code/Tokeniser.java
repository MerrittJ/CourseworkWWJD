package code;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class Tokeniser extends ClassLoader{


	private HashMap<String, String> map;

	
	public Tokeniser(){
		map = new HashMap<String, String>();
	}
	
	public HashMap<String, String> loadToo(File file, String bookName) throws FileNotFoundException {
		Scanner chapSc = new Scanner(file);
		chapSc.useDelimiter("CHAPTER");
		
		String currentChap = chapSc.next();
		System.out.println(currentChap);
		
		//Number of chapter being loaded. used for making Key reference
		int chapNum = 1;
		
		
		while(chapSc.hasNext()){
			currentChap = chapSc.next(); // skip 'Book of xxx' section of text on first run
			Scanner verseSc = new Scanner(currentChap);
			verseSc.useDelimiter("\n");
			verseSc.next();
			//Number of verse being loaded. used for making Key reference
			int verseNum = 1;
			//concatenation to build hashmap key
			String verseRef = bookName +":"+ chapNum +":"+ verseNum;
			
			while (verseSc.hasNext()) {
				
				String scanned = verseSc.next();
				map.put(verseRef, scanned);
				
				verseRef = bookName +":"+ chapNum +":"+ verseNum;
				
				System.out.println(verseRef);
				System.out.println(scanned);
				
				verseNum++;
				
			}
			verseSc.close();
			chapNum++;
			verseRef = bookName +":"+ chapNum +":"+ verseNum;
		}
		
		chapSc.close();
		return map;
	}
		
	public static void main(String args[]) throws FileNotFoundException{
		Tokeniser toke= new Tokeniser();
		File file = new File("src/textDocs/Genesis.txt");
		toke.loadToo(file, "Genesis");
	}
	
}
