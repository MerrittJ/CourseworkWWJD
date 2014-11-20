package code;

import java.io.*;
import java.util.Scanner;

public class Counter {
	
	private String fileName;
	private Scanner scan;

	/*constructor passes fileName to a global variable*/
	public Counter(String fileName){
		this.fileName = fileName;
	}
	
	
	/*returns the number of chapters in the book passed in
	 * 
	 * Note: still needs testing for robustness
	 * */
	public int getNumOfChapters() {
		
		try{
			scan = new Scanner(new File(fileName));
			}
		catch(Exception e){
			System.out.println("File not Found.");
			}
		int chapterCount = 0;
		
		while(scan.hasNext()){
			if(scan.next().equals("CHAPTER")){
				chapterCount++;
			}
		}
		return chapterCount;
	}
	/*returns the number of verses when given a chapter to sort through
	 * 
	 * Note: still needs testing for robustness
	 * */
	public int getNumOfVerses(int chapNum){
		try{
			scan = new Scanner(new File(fileName));
			}
		catch(Exception e){
			System.out.println("File not Found.");
			}
		int verseCount = 0;
		int chapCount = 0;
		
		while(scan.hasNext()){
			if(scan.next().equals("CHAPTER")){
				chapCount++;
			}
			if(chapCount == chapNum){
				scan.next();
				
				while(scan.hasNextLine()){
					if(scan.next().equals("CHAPTER")){
						break;
					}
					verseCount++;
					scan.nextLine();
				}
			break;
			}
		}
		return verseCount;
	}
	
	
	/*returns the number of words when given a chapter and a verse to search
	 * 
	 * Note: still needs testing for robustness
	 * */
	public int getNumOfWords(int chapNum, int lineNum){
		
		try{
			scan = new Scanner(new File(fileName));
			}
		catch(Exception e){
			System.out.println("File not Found.");
			}
		String lineNumStr = Integer.toString(lineNum);
		int lineNum1 = lineNum + 1;
		String lineNumStr1 = Integer.toString(lineNum1);
		int chapCount = 0;
		int wordCount = 0;
		
		while(scan.hasNext()){
			if(scan.next().equals("CHAPTER")){
				chapCount++;
			}
			if(chapCount == chapNum){
				scan.next();
				while(scan.hasNext()){
					String line = scan.nextLine();
					if(scan.next().equals(lineNumStr)){
						
						while(scan.hasNext()){
							String check = scan.next();
							if(check.equals(lineNumStr1) || check.equals("CHAPTER")){
								break;
							}
							else{
								wordCount++;
							}
						}
					break;
					}
				}
			break;
			}
		}
		return wordCount;
	}
	
	
	//main method for testing
	public static void main (String args[]) throws IOException{
		Counter counter = new Counter("KJBible/Genesis.txt");
		
	}
}
