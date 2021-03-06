package code;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class responsible for dealing with user interaction and searching the Bible for words, verses, and chapters
 * @author Tom Connolly
 *
 */
public class Control {

	/**
	 * HashMap containing the entire Bible
	 */
	private HashMap<String, String> books;
	private int incorrectOptionValue = 7;

	/**
	 * Constructor initialising variables and compiling all the books of the Bible into a single HashMap
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public Control() throws FileNotFoundException{
		//build tokeniser
		Tokeniser toke = new Tokeniser();
		//build large HashMap of capacity 45,000. (this is 1.5x the number of verses in the bible.)
		books = new HashMap<String, String>(45000);
		
		//build scanner to read the file directory of books.
		Scanner fileDirScan = new Scanner(new File("src/textDocs/fileDir.txt"));
		//use new line delimiter
		fileDirScan.useDelimiter("\n");
		
		for(int i = 0; i < 66; i++){
			
			//build the path name for each book using each value in fileDir
			String rawFileName = fileDirScan.next().replaceAll("\r", "");
			String filePathName = "src/textDocs/" + rawFileName + ".txt";
			
			//create file for use in tokeniser
			File file =  new File(filePathName);
			
			//take the returned HashMap and add it to the mother HashMap.
			books.putAll(toke.loadBook(file, rawFileName));
		}
	}

	/**
	 * Main method by which program is run
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main (String args[]) throws FileNotFoundException{
		Control control = new Control();
		control.runProgram();
	}
	
	/**
	 * Primary method to run TUI
	 */
	@SuppressWarnings("resource")
	public void runProgram(){
		
		boolean run = true;
		
		while(run == true){
			runTUI();
			
			boolean runExit = true;
			//clause to offer exit program option
			while(runExit == true){
				
				System.out.println("Would you like to return to the main menu? ('yes' or 'no')");
				Scanner sc = new Scanner(System.in);
				String endInput = sc.next();
				
				if(endInput.equals("no")){
					runExit = false;
					run = false;
				}
				else if(endInput.equals("yes")){
					runExit = false;
					run = true;
				}
				else{
					System.out.print("\nInput not recognised please type 'yes or 'no'.\n");
				}
			}
		}
			exit();	
	}
	
	/**
	 * Method to inform user application has closed
	 */
	public void exit(){
		System.out.println("\n\n" + "Application Exited.");	
	}
	
	/**
	 * Method to interact with user. Contains menu options and returns search results to user
	 */
	@SuppressWarnings("resource")
	public void runTUI(){
		
			
				// set of print statements to print to the console the instructions for use of this program
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
				System.out.println("Please select '0' for Help on formatting your inputs.");
				System.out.println("");
				
				boolean run = true;
				while(run){
					// scanner is instantiated to read input from the command line
					
						
					Scanner sc = new Scanner(System.in);
					int input;
					
					try{
						input = sc.nextInt();
						if(input<0){
							input = incorrectOptionValue;
						}
					}
					catch(Exception e){
						input = incorrectOptionValue;
					}
					
					String returnString = null;
					
					// set of checks made to see which option the user has selected.
						if(input == 0){
							returnString = " ";
							System.out.println("\nHelp:");
							System.out.println("");
							System.out.println("-Enter any inputs in upper or lower case characters.");
							System.out.println("");
							System.out.println("-If the book name is one of a set ie Samuel or Chronicles enter the number as \n '1chronicles' or '2Samuel'.");
							System.out.println("");
							System.out.println("");
							System.out.println("Please now select an option from the main menu.");
						
						}
						//chek input and perform necessary methods
						else if(input == 1){
							returnString = findNumOfTimesFromWord();
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
						
						//If the input returned from the search object is empty or null, an appropriate error message is given and the user can try again
						if(input<incorrectOptionValue){
							if(returnString.equals("") || returnString.contains("null")){
								System.out.println("\n\n" + "Sorry no matches were found, please try again.");
								System.out.println("");
							}
							else{
								//prints the string returned from the search methods
							System.out.println("\n" + returnString);
							System.out.println("");
							}
						}
				}
		}

	/**
	 * Method to allow user to find the number of times a word appears in the Bible
	 * @return a String informing the user of the input word and output number of occurrences
	 */
	@SuppressWarnings("resource")
	public String findNumOfTimesFromWord(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the word you would like searched and the number of times it appears in the bible will appear below.");
		
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		String cleanInput = input.toLowerCase();
		
		Search search = new Search(books);
		int wordCount = search.findNumOfTimesFromWord(cleanInput);
		String wordCountString = "The word '" + input + "' appeared " + wordCount + " times."; 
		return wordCountString;
		
	}

	/**
	 * Method to return all complete verses that contain a given word.
	 * @return a String containing all the verses meeting the requirement
	 */
	@SuppressWarnings("resource")
	public String findVersesFromWord(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the word you would like searched and the verse that appear in will appear below.");
		Scanner sc = new Scanner(System.in);
		String input = sc.next().toLowerCase();
		Search search = new Search(books);

		return search.findVersesFromWord(input);
	}

	/**
	 * Method to return all verse locations that contain a given word in the form [book chapter:verse]
	 * @return a String containing all the locations meeting the requirement
	 */
	@SuppressWarnings("resource")
	public String getLocationFromWord(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the word you would like searched and the locations of that word will appear below in the format as shown above.");
		Scanner sc = new Scanner(System.in);
		String input = sc.next().toLowerCase();
		Search search = new Search(books);
		String result = "";
		for (String verse : search.getLocationsFromWord(input)){
			result = result + verse +"\n";
		}
		return result;
	}

	/**
	 * Method to return a specific chapter/psalm from a Bible book
	 * @return a String of the complete chapter/psalm
	 */
	@SuppressWarnings("resource")
	public String getChapterFromBookAndChapNum(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the book that the chapter you are searching for appears in.");
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next().toLowerCase();
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

	/**
	 * Method to return all verses between two points (verses)
	 * @return a String of all the verses meeting the requirement
	 */
	@SuppressWarnings("resource")
	public String getVersesFromFirstAndLastVerses(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the book that the chapter you are searching for appears in.");
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next().toLowerCase();
		String input2 = "";
		Search search = new Search(books);
		
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
		
		boolean stop = false;
		String input3 = "";
		String input4 = "";
		
		while(stop == false){
			System.out.println("");
			System.out.println("Please enter the first verse you would like returned.");
			input3 = sc.next();
	
			System.out.println("");
			System.out.println("Please enter the last verse you would like returned.");
			input4 = sc.next();
			
			if(Integer.parseInt(input3)>Integer.parseInt(input4)){
				System.out.println("The first verse you entered appears after the last verse please try again.");
			}
			else{
				stop = true;
			}
		}
		return search.getVersesFromFirstAndLastVerses(input1, input2, input3, input4);
	}

	/**
	 * Method to return a specific verse from the Bible
	 * @return a String of the specific verse
	 */
	@SuppressWarnings("resource")
	public String getSpecificVerse(){
		
		boolean run = true;
		String verse = "";
		
		while(run){
			System.out.println("");
			System.out.println("");
			System.out.println("Please enter the book that the chapter you are searching for appears in.");
			Scanner sc = new Scanner(System.in);
			String input1 = sc.next().toLowerCase();
			String input2 = "";
			
			if(input1.equals("psalms")){
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
				System.out.println("\n\n" + "The verse you attempted to select does not exist, please try again.");
				run = true;
			}
			else{
				run = false;
				
			}
		}
		return verse;
	}
}
