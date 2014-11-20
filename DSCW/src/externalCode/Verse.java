package externalCode;

public class Verse {
	public String[] words;
	
	public Verse(){
	}
	
	public void createWords(int numOfWords){
		words = new String[numOfWords];
		for (int i = 0; i < numOfWords; i++){
			String word = new String();
			words[i] = word;
		}
	}
}
