public class ListInterior {
	private String category;
	private ListInterface wordList;
	

	public ListInterior(String title, ListInterface wl){
		category = title;
		wordList = wl; 
	}

	public String getCategory(){
		return category;
	}

	public ListInterface getWordList(){
		return wordList;
	}
	
}