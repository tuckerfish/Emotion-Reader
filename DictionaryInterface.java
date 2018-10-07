public interface DictionaryInterface{

	public void add(String title, String word);

	public String get(String word);

	public String getCategory();

	public ListInterface getWordList();
	
}