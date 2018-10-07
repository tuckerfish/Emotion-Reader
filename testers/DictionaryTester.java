class DictionaryTester{
	public static void main(String[] args){
		Dictionary newDict = new Dictionary();

		System.out.println("-------------------ADDING----------------");

		newDict.add("anger","fight");
		newDict.add("anger", "frustrated");
		newDict.add("anger","jerk");
		newDict.add("anger","mean");
		newDict.add("anger","stop");

		newDict.add("happy","charming");
		newDict.add("happy","fun");
		newDict.add("happy","good");
		newDict.add("happy","nice");

		newDict.add("sad", "cold");
		newDict.add("sad", "dark");
		newDict.add("sad", "fail");
		newDict.add("sad", "gloom");
		
		newDict.print();

		System.out.println("-----------------REMOVING----------------");

		newDict.remove("anger","mean");
		newDict.remove("happy", "fun");
		newDict.remove("sad", "fail");

		newDict.print();

		System.out.println("-----------------REMOVING----------------");

		newDict.remove("anger","jerk");
		newDict.remove("happy", "good");
		newDict.remove("sad", "dark");

		newDict.print();

		System.out.println("-----------------REMOVING ALL------------");

		newDict.removeAll();

		newDict.print();
	}
}