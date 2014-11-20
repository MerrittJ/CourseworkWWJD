import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tokeniser {

	String[] chapters;
	Pattern p = Pattern.compile("Chapter");
	File file;

	// Input needs to be a File, i.e. do something like 
	//File dir = new File("c:/temp/files");
	// before inputting parameter

	public Tokeniser(File bibBook) throws IOException{
		file = bibBook;
		//		//gets number of lines and makes an array of that size
		//		LineNumberReader  lnr = new LineNumberReader(new FileReader(bibBook));
		//		lnr.skip(Long.MAX_VALUE); //skips to absolute end of possible number of characters i.e. file would need to be 16 exabytes long to exceed
		//		String[] lines = new String[lnr.getLineNumber()]; //maybe +1 since there's a line 0?
		//		lnr.close();
		//		
		//		//reads the file line by line and stores in array
		//		BufferedReader bReader = new BufferedReader(new FileReader(bibBook));
		//		int num = 0;
		//		while (num < lines.length){
		//			lines[num] = bReader.readLine();
		//			num++;
		//		}
		//		bReader.close();

	}

	public void tokeniseChapters() throws IOException{
		
		
		chapters = new String[countPattern(file, "Chapter")];
		
		Scanner sc = new Scanner(file);
		sc.useDelimiter("Chapter");
		int count = 0;
		while (sc.hasNext()){
			chapters[count] = "Chapter";
			count++;
			chapters[count] = sc.next();
			count++;
		}
	}

	public int countPattern(File file, String stringPat) throws IOException{
		String fileString = fileToString(file);
		Pattern pat = Pattern.compile(stringPat);
		Matcher matcher = pat.matcher(fileString);
		
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	public String fileToString(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

}
