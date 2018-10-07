class Dictionary{

	int count;
	MyLinkedList mainList;
	MySortedLinkedList tempSublist;
	ListInterior tempListInterior;
	String tempCategory;
	String toStringList;
	
	
	Dictionary(){
		mainList = new MyLinkedList();
	}

	public void add(String title, String word){
		boolean makeNewCategory = true;

		// cycle through each object within the main list.
		for(int i = 0; i < mainList.size(); i++){

			// pull each object (ListInteriors) and save them in the pointer 'tempListInterior'.
			tempListInterior = (ListInterior)mainList.get(i);

			// pull each category from the ListInteriors and save them in the pointer 'tempCategory'.
			tempCategory = tempListInterior.getCategory();

			// as we're cycling through each of the mainlist's categories, we see if our contructor's
			// 'title' matches any of the previous categories already saved.
			if(tempCategory.compareTo(title) == 0){

				// if so, we pull the sorted linked list from the ListInteriors and save it in the
				// pointer 'tempSublist'.
				tempSublist = (MySortedLinkedList)tempListInterior.getWordList();

				// we add the word to the newly established pointer, which is equivalent to the actual list.
				tempSublist.add(word);

				// change the 'makeNewCategory' to false, because we recycled an old category and 
				// we don't need to continue with the alternative step. 
				makeNewCategory = false;
			}
		}

		// the alternative step when none of the categories match our given 'title'
		if(makeNewCategory == true){

			// make a new sorted linked list for our InteriorList object.
			MySortedLinkedList newList = new MySortedLinkedList();

			// add the given word to the newly created sorted linked list.
			newList.add(word);

			// add the newly created sorted linked list to a new ListInteror object.
			ListInterior divider = new ListInterior(title, newList);

			// add the newly created ListInterior object to the main linked list.
			mainList.add(count,divider);

			// increase the count.
			count++;
		}
	}

	public String get(String word){
		if(mainList.size()<0){
			return null;
		}
		else{
			for(int i = 0; i < mainList.size(); i++){
				int found = -1;
				// pull each 'ListInterior' object by saving it with the pointer 'tempListInterior'.
				tempListInterior = (ListInterior)mainList.get(i);

				// pull the ListInterior's sorted linked list by saving it within 
				// the pointer 'tempSublist'.
				tempSublist = (MySortedLinkedList)tempListInterior.getWordList();

				found = tempSublist.find(word);

				if(found != -1){
					return tempListInterior.getCategory();
				}

			}
		}
		return null;
	}

	public void print(){

		// cycle through each object (ListInterior) within the main linked list.
		if(mainList.size()>0){
			for(int i = 0; i < mainList.size(); i++){

				// pull each 'ListInterior' object by saving it with the pointer 'tempListInterior'.
				tempListInterior = (ListInterior)mainList.get(i);

				// pull each category String from the given ListInterior object by saving it within
				// the pointer 'tempCategory'.
				tempCategory = tempListInterior.getCategory();

				// print the category String in a distinguishing way.
				System.out.println("<" + tempCategory + ">");

				// also the pull the ListInterior's sorted linked list by saving it within 
				// the pointer 'tempSublist'.
				tempSublist = (MySortedLinkedList)tempListInterior.getWordList();

				// make a string from the sorted list.
				toStringList = (String)tempSublist.toString();

				// print the newly created string.
				System.out.println(toStringList);
			}
		}else{
			System.out.println("{EMPTY}");
		}
	}	

	public void remove(String title, String word){

		int found = -1;

		// cycle through each object (ListInterior) within the main linked list.
		for(int i = 0; i < mainList.size(); i++){

			tempListInterior = (ListInterior)mainList.get(i);

			if(title.compareTo(tempListInterior.getCategory()) == 0 ){

				// pull the sorted linked list from the ListInteriors and save it in the pointer 'tempSublist'.
				tempSublist = (MySortedLinkedList)tempListInterior.getWordList();

				found = tempSublist.find(word);				

				if(found != -1){
					tempSublist.remove(found);
				}
			}
		}
	}

	public void removeAll(){
		mainList.removeAll();
	}


}