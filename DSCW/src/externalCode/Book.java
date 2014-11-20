package externalCode;

public class Book {
	
	private Chapter[] chapters;
	public Book(){
	}

	public void createChapters(int numOfChaps){
		chapters = new Chapter[numOfChaps];
		for (int i = 0; i < numOfChaps; i++){
			Chapter chapter = new Chapter();
			chapters[i] = chapter;
		}
	}
	
	public Chapter getChapter(int i){
		return chapters[i];
	}
}