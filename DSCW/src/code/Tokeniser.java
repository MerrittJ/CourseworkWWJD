package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import externalCode.*;

public class Tokeniser {

	private Scanner direcScan;
	private static final int NUM_OF_BOOKS = 66;
	
	public Tokeniser(){
		
	}
	
	public Book[] build() throws FileNotFoundException{
		//create array of books to hold arrays of chapters
		Book[] books = new Book[NUM_OF_BOOKS];
		for (int i = 0; i < NUM_OF_BOOKS; i++){
			Book book = new Book();
			books[i] = book;
		}
		//create scanner that will scanner
		direcScan = new Scanner(new File("C://Users/Tom/Documents/Aston/ComputerScience/Y2.T1/CS2310/Labs/BibleApp/bin/textdocs/fileDirec.txt"));
		
		while(direcScan.hasNextLine()){
			
			String fileName = "C://Users/Tom/Documents/Aston/ComputerScience/Y2.T1/CS2310/Labs/BibleApp/bin/textdocs/".concat(direcScan.nextLine());
			System.out.println(fileName);
			
			Counter counter = new Counter(fileName);
			
			//loop to create the correct amount of chapters (counter.getNumOfChapters()) in each book object
			for(int bookCounter = 1; bookCounter < NUM_OF_BOOKS; bookCounter++){
				
				books[bookCounter].createChapters(counter.getNumOfChapters());
				
				//loop to create the correct amount of verses (counter.getNumOfVerses(i)) in each chapter object
				for(int chapterCounter = 1; chapterCounter < counter.getNumOfChapters(); chapterCounter++){
					
					books[bookCounter].getChapter(chapterCounter).createVerses(counter.getNumOfVerses(chapterCounter));
					

				}
			}
		}
		return books;
	}
	
	/*
	public void load(){
		
		Scanner fileDirecScan = new Scanner(new File("BibleApp/KJBible/fileDirec.txt"));
		fileDirecScan.useDelimiter("\n");
		int i = 0;
		int j = 0;
		int k = 0;
		
		while(fileDirecScan.hasNext()){
			Scanner chapScan = new Scanner(fileDirec.next());
			chapScan.useDelimiter("Chapter");
			
			while(chapScan.hasNext()){
				Scanner verseScan = new Scanner(chapScan.next());
				verseScan.useDelimiter("\n");
				
				while(verseScan.hasNext()){
					Scanner wordScan = new Scanner(verseScan.next());
					wordScan.useDelimiter(" ");
					
					while(wordScan.hasNext()){
						books.getChapter(i).getVerse(k).setWord(l) = wordScan.next();
						l++;
					}
					k++;
				}
				j++;
			}
		}
		*/
		
	public static void main(String args[]) throws FileNotFoundException{
		Tokeniser toke= new Tokeniser();
		toke.build();
	
	
	}
	
}
