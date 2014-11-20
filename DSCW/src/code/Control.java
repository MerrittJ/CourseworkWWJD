package code;

import java.io.Console;
import java.util.Scanner;

import externalCode.Book;

public class Control {

	//Global Variables
	private Book[] books;

	//Constructor
	public Control(){
		Tokeniser toke = new Tokeniser();
		
		try{
			books = toke.build();
		}
		catch(Exception e){
			System.out.println("ERROR:FROM TOKENISER CLASS.");
		}

	}

	public static void main (String args[]){
		Control control = new Control();
		control.runProgram();
	}
	
	public void runProgram(){

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
		/*Scanner is instantiated to read input from the command line*/
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		
		/*Set of checks made to see which feature the user has selected.*/
		if(input == 1){
			System.out.println(findNumofTimesFromWord());
		}
		else if(input == 2){
			System.out.println(findVersesFromWord());
		}
		else if(input == 3){
			System.out.println(getLocationFromWord());
		}
		else if(input == 4){
			System.out.println(getChapterFromBookAndChapNum());
		}
		else if(input == 5){
			System.out.println(getVersesFromFirstAndLastVerses());
		}
		else if(input == 6){
			System.out.println(getSpecificVerse());
		}
		
	}


	public String findNumofTimesFromWord(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the word you would like searched and the number of times it appears in the bible will appear below.");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		Search search = new Search(books);
		return search.findNumOfTimesFromWord(input);
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
		return search.getLocationFromWord(input);
	}

	public String getChapterFromBookAndChapNum(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the book that the chapter you are searching for appears in.");
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next();
		
		System.out.println("");
		System.out.println("Please enter the chapter number.");
		int input2 = sc.nextInt();

		Search search = new Search(books);
		return search.getChapterFromBookAndChapNum(input1, input2);
	}

	public String getVersesFromFirstAndLastVerses(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the book that the chapter you are searching for appears in.");
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next();

		System.out.println("");
		System.out.println("Please enter the chapter number.");
		int input2 = sc.nextInt();

		System.out.println("");
		System.out.println("Please enter the first verse you would like returned.");
		int input3 = sc.nextInt();

		System.out.println("");
		System.out.println("Please enter the last verse you would like returned.");
		int input4 = sc.nextInt();

		Search search = new Search(books);
		return search.getVersesFromFirstAndLastVerses(input1, input2, input3, input4);
	}

	public String getSpecificVerse(){
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the book that the chapter you are searching for appears in.");
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next();

		System.out.println("");
		System.out.println("Please enter the chapter number.");
		int input2 = sc.nextInt();

		System.out.println("");
		System.out.println("Please enter the verse you would like returned.");
		int input3 = sc.nextInt();

		Search search = new Search(books);
		return search.getSpecificVerse(input1, input2, input3);
	}
}
