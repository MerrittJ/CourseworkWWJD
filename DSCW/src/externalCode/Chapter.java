package externalCode;

public class Chapter {
	public Verse[] verses;

	public Chapter(){
	}
	
	public void createVerses(int numOfVerses){
		verses = new Verse[numOfVerses];
		for (int i = 0; i < numOfVerses; i++){
			Verse verse = new Verse();
			verses[i] = verse;
		}
	}
	
	public Verse getVerse(int i){
		return verses[i];
	}
	
	}