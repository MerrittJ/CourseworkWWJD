package code;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Control {

	//Global Variables
	private HashMap<String, String> books;
	private Boolean exit;

	//Constructor
	public Control() throws FileNotFoundException{
		Tokeniser toke = new Tokeniser();
		books = new HashMap<String, String>();
		exit = false;
		
		Scanner fileDirScan = new Scanner(new File("src/textDocs/fileDir.txt"));
		fileDirScan.useDelimiter("\n");
		
		for(int i = 0; i < 66; i++){
			
			String rawFileName = fileDirScan.next().replaceAll("\r", "");
			String filePathName = "src/textDocs/" + rawFileName + ".txt";

			File file =  new File(filePathName);
			
			books.putAll(toke.loadToo(file, rawFileName));
			
			/*
			System.out.println(filePathName);
			System.out.println(rawFileName);*/
		}
	}

	public static void main (String args[]) throws FileNotFoundException{
		Control control = new Control();
		control.runProgram();
	}
	
	public void runProgram(){
		
		boolean run = true;
		
		while(run == true){
			runGUI();
			
			System.out.println("Would you like to return to the main menu? ('yes' or 'no')");
			Scanner sc = new Scanner(System.in);
			String endInput = sc.next();
			
			if(endInput.equals("no")){
				run = false;
			}
		}
			exit();	
	}
	
	public void exit(){
		System.out.println("\n\n" + "Application Exited.");	
	}
	
	public void runGUI(){
		
			
				/*Set of print statements to print to the console the instructions for use of this program*/
				System.out.println("");
				System.out.println("This Program provides several different methods to search the text from King James' Bible.");
				System.out.println("Please select the search method you require, type it's corresponding number in and hit enter.");
				System.out.println("");
				System.out.println("");
				System.out.println("1. Find the number of times a word appears in the Bible.");
				System.out.println("2. Find all verses that a given word appears in.");
				System.out.println("3. Find all locations (format: [book chapter:verse]) of a given word.");
				System.out.println("4. Find a chapter from a given book name and chapter number.");
				System.out.println("5. Find multiple verses when given a start verse and an end verse.");
				System.out.println("6. Find a verse from a given book name, chapter number and verse number.");
				System.out.println("");
				
				boolean run = true;
				while(run){
					/*Scanner is instantiated to read input from the command line*/
					
						
					Scanner sc = new Scanner(System.in);
					int input;
					
					try{
						input = sc.nextInt();
					}
					catch(Exception e){
						run = true;
						input = 7;
					}
					
					String returnString = null;
					
					/*Set of checks made to see which feature the user has selected.*/
						if(input == 1){
							returnString = findNumofTimesFromWord();
							System.out.println("");
							run = false;
						}
						else if(input == 2){
							returnString = findVersesFromWord();
							run = false;
						}
						else if(input == 3){
							returnString = getLocationFromWord();
							run = false;
						}
						else if(input == 4){
							returnString = getChapterFromBookAndChapNum();
							run = false;
						}
						else if(input == 5){
							returnString = getVersesFromFirstAndLastVerses();
							run = false;
						}
						else if(input == 6){
							returnString = getSpecificVerse();
							run = false;
						}
						else{
							System.out.println("Incorrect option chosen please try again.");
							run = true;
						}
						
						
						if(input<7){
							if(returnString.equals("") || returnString.contains("null")){
								System.out.println("\n\n" + "Sorry no matches were found, please try again.");
								System.out.println("");
							}
							else{
							System.out.println("\n\n" + returnString);
							System.out.println("");
							}
						}
				}
		}

	public String findNumofTimesFromWord(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the word you would like searched and the number of times it appears in the bible will appear below.");
		
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		Search search = new Search(books);
		int wordCount = search.findNumOfTimesFromWord(input);
		String wordCountString = "The word " + input + " appeared " + wordCount + " times."; 
		return wordCountString;
		
	}

	public String findVersesFromWord(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the word you would like searched and the verse that appear in will appear below.");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		Search search = new Search(books);

		return search.findVersesFromWord(input);
	}

	public String getLocationFromWord(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the word you would like searched and the locations of that word will appear below in the format as shown above.");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		Search search = new Search(books);
		String result = "";
		for (String verse : search.getLocationsFromWord(input)){
			result = result + verse +"\n";
		}
		return result;
	}

	public String getChapterFromBookAndChapNum(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the book that the chapter you are searching for appears in.");
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next();
		String input2 = "";
		
		if(input1.equals("Psalms")){
			System.out.println("");
			System.out.println("Please enter the Psalm number.");
			input2 = sc.next();
		}
		else{
			System.out.println("");
			System.out.println("Please enter the chapter number.");
			input2 = sc.next();
		}
		
		Search search = new Search(books);

		return search.getChapterFromBookAndChapNum(input1, input2);
	}

	public String getVersesFromFirstAndLastVerses(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the book that the chapter you are searching for appears in.");
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next();
		String input2 = "";
		
		if(input1.equals("Psalms")){
			System.out.println("");
			System.out.println("Please enter the Psalm number.");
			input2 = sc.next();
		}
		else{
			System.out.println("");
			System.out.println("Please enter the chapter number.");
			input2 = sc.next();
		}

		System.out.println("");
		System.out.println("Please enter the first verse you would like returned.");
		String input3 = sc.next();

		System.out.println("");
		System.out.println("Please enter the last verse you would like returned.");
		String input4 = sc.next();

		Search search = new Search(books);

		return search.getVersesFromFirstAndLastVerses(input1, input2, input3, input4);
	}

	public String getSpecificVerse(){
		
		boolean run = true;
		String verse = "";
		
		while(run){
			System.out.println("");
			System.out.println("");
			System.out.println("Please enter the book that the chapter you are searching for appears in.");
			Scanner sc = new Scanner(System.in);
			String input1 = sc.next();
			String input2 = "";
			
			if(input1.equals("Psalms")){
				System.out.println("");
				System.out.println("Please enter the Psalm number.");
				input2 = sc.next();
			}
			else{
				System.out.println("");
				System.out.println("Please enter the chapter number.");
				input2 = sc.next();
			}
	
			System.out.println("");
			System.out.println("Please enter the verse you would like returned.");
			String input3 = sc.next();
	
			Search search = new Search(books);
			
			verse = search.getSpecificVerse(input1, input2, input3);
			
			if (verse == null){
				System.out.println("\n\n" + "The Verse you attempted to select does not exist, please try again.");
				run = true;
			}
			else{
				run = false;
				
			}
		}
		return verse;
	}
}
