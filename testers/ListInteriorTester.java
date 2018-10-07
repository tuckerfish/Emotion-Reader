class ListInteriorTester{
	public static void main(String[] args){
		MySortedLinkedList wordList = new MySortedLinkedList();

		wordList.add("gloom");
		wordList.add("hate");
		wordList.add("mean");
		wordList.add("phony");
		wordList.add("jerk");

		ListInterior list = new ListInterior("sad", wordList);
		System.out.println(list.getCategory());

		MySortedLinkedList temp = (MySortedLinkedList)list.getWordList();
		System.out.println(temp.toString());

		temp.add("dark");
		System.out.println(list.getCategory());
		System.out.println(temp.toString());



		wordList.removeAll();

		wordList.add("good");
		wordList.add("positive");
		wordList.add("nice");
		wordList.add("content");
		wordList.add("glory");

		ListInterior list2 = new ListInterior("happy", wordList);
		System.out.println(list2.getCategory());

		MySortedLinkedList temp2 = (MySortedLinkedList)list2.getWordList();
		System.out.println(temp2.toString());


	}
}