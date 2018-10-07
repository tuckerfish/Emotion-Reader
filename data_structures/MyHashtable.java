 class MyHashtable implements MyHashtableInterface {

 	// protected values for the 'MyHashtable' class.
	protected int tableSize;
	protected int size;
	protected MyLinkedList[] table;

	// contructor of 'MyHashtable' which initializes the protected values above.
	public MyHashtable(int tableSize){
		this.tableSize = tableSize;
		this.size = 0;
		this.table = new MyLinkedList[tableSize];
	}

	// entry class which simultaneously stores and associates a String 'key' and object 'value'
	protected class Entry {
		String key;
		Object value;

		// constructor for the entry class, which takes a String 'newKey' and Object 'newValue'
		// as parameters.
		Entry(String newKey, Object newValue){
			this.key = newKey;
			this.value = newValue;
		}
	}

	// 'isEmpty()' method which checks to see if the given table is table is Hashtable is empty, by checking if the int counter
	// 'size' is equal to zero.
	public boolean isEmpty(){

		// if size is zero, it returns true
		if(size == 0)
			return true;
		// if the size is anything else, it returns false by default. 
	return false;
	}

	// 'size()' method which simply returns the int 'size'
	public int size(){
		return size;
	}

	// 'put()' method which adds values to the hashtable
	public Object put(String key, Object value){

		// generates an int value 'index' which connotates the position in the table the 'key' String has. 
		int index = Math.abs(key.hashCode())%tableSize;

		// the object value that is overwritten/returned when a new value is added at its position.
		Object oldValue = null;

		// boolean switcher which identifiees if a value was properly inputted in a linked list
		boolean inputted = false;

		// if the hashtable doesn't have a linked list stored at the generated 'index', create
		// a new 'MyLinkedList'.
		if(table[index] == null){
			MyLinkedList newMyLinkedList = new MyLinkedList();

			// create a new entry with the 'key' and 'value' being passed through the constructor.
			Entry newEntry = new Entry(key,value);

			// add the entry 'newEntry' to the the linked list 'newMyLinkedList' at the first position because the 
			// order of the entries doesn't matter as we'll simply cycle through the (hopefully short) list.
			// add the new entry using MyLinkedList's add() method.
			newMyLinkedList.add(0,newEntry);

			// store the new linked list in the hashtable by setting the value of the generated index of hashtable 
			// table equal to the newly created linked list. 
			table[index] = newMyLinkedList;

			// increment the int size after a new word is added so we can track the number of total words 
			// stored in the table.
			size++;
		} else {

			// else if the table[index] != null, meaning there is a value stored at the generated index (which is 
			// more than likly that it's a linked list). Pull the given list by making a new linked list 
			// 'foundMyLinkedList' and setting it equal to value stored in table at index.
			MyLinkedList foundMyLinkedList = table[index];

			// cycle through the values of the linked list.
			for(int i = 0; i < foundMyLinkedList.size(); i++){

				// pull each entry within the linked list by setting it equal to a temp entry.
				Entry foundEntry = (Entry)foundMyLinkedList.get(i);

				// if the String key of the found entry is equal to the String 'key' passed through the constructor,
				// then there is a value previously stored at that key. 
				
				if(foundEntry.key.compareTo(key) == 0){ // could cause issues!!!

					// Store the previous value in the String'oldValue'.
					oldValue = foundEntry.value;

					// store the new value in the entry.
					foundEntry.value = value;

					// Make the indicator 'inputted' true.
					inputted = true;
				}	
			}

			// if inputted is false, this means that the given key wasn't already within an entry on the linked list.
			if(inputted == false){

				// make a new entry with the given key and value.
				Entry newEntry = new Entry(key,value);

				// add the newly made entry to the linked list 'foundMyLinkedList'.
				foundMyLinkedList.add(0,newEntry);

				// increment the int size after a new word is added so we can track the number of total words 
				// stored in the table.
				size++;
			}
		}

		// if the String 'oldValue' has contents, this means that an old value was overwritten by a new value. 
		if(oldValue != null){

			// return the old value.
			return oldValue;
		}

		// default return is null.
		return null;
	}

	// 'get()' method which returns the value of the given key.
	public Object get(String key){

		// generate an index for the given key.
		int index = Math.abs(key.hashCode())%tableSize;

		// if the table is empty at the index, return null.
		if(table[index] == null){
			return null;
		} else {
			// make a new linked list 'foundMyLinkedList' by setting it equal to the linked list stored 
			// at index in table.
			MyLinkedList foundMyLinkedList = table[index];

			// cycle through the entries stored within the linked list.
			for(int i = 0; i < foundMyLinkedList.size(); i++){

				// pull each entry out of the list by setting it equal to a temp entry.
				Entry foundEntry = (Entry)foundMyLinkedList.get(i);

				// if the found entry's key is equal to the key passed through the contructor, retun the 
				// found entry's value.
				if(foundEntry.key.compareTo(key) == 0){ // might cause errors
					return foundEntry.value;
				}
			}
		}

		// default return is null.
		return null;
	}

	// 'remove()' method which removes an individual value based off the passed key.
	public void remove(String key){

		// generate an index for the given key.
		int index = Math.abs(key.hashCode())%tableSize;

		// if the hashtable doesn't have a linked list stored at the generated 'index', don't do anything.
		if(table[index] == null){
			// don't remove anything because there's nothing to remove.
		} else {

			// make a new linked list 'foundMyLinkedList' by setting it equal to the linked list stored 
			// at index in table.
			MyLinkedList foundMyLinkedList = table[index];

			// cycle through the entries stored within the linked list.
			for(int i = 0; i < foundMyLinkedList.size(); i++){

				// pull each entry out of the list by setting it equal to a temp entry.
				Entry foundEntry = (Entry)foundMyLinkedList.get(i);

				// if the found entry's key is equal to the key passed through the contructor,
				// use 'MyLinkedList's' 'remove()' method to delete the node storing the given 
				// String 'key'.
				if(foundEntry.key == key){
					foundMyLinkedList.remove(i);

					// decrement the int size after a new word is added so we can track the number of total words 
					// stored in the table.
					size--;
				}
			}
		}
	}

	// 'clear()' method which removes all contents of the hashtable.
	public void clear(){
		// rather than systematically removing each individual value of the table, we create a new table by
		// setting it equal to a new array of MyLinkedLists.
		table = new MyLinkedList[tableSize]; // NOT SURE IF THIS IS CORRECT: using tableSize.

		// set size equal to zero, as to keep the size consistent with the number of contents within the table.
		size = 0;
	}

	// 'getKeys()' method which returns a String array of all the String keys within the table.
	public String[] getKeys(){

		// int variable which tracks the number of keys added to the array. This helps discover what the 
		// next position should be added to.
		int keyCount = 0;

		// create a String array with the length of current int 'size'.
		String[] keyStringArray = new String[size]; 

		// cycle through every index of the table.
		for(int i = 0; i < tableSize; i++){

			// if the table is not empty at the given posotion...
			if(table[i] != null){

				// make a new linked list 'foundMyLinkedList' by setting it equal to the linked list stored 
				// at index in table.
				MyLinkedList foundMyLinkedList = table[i];

				// cycle through all the nodes storing entries of the given linked list.
				for(int j = 0; j < foundMyLinkedList.size(); j++){

					// pull each entry out of the list by setting it equal to a temp entry.
					Entry foundEntry = (Entry)foundMyLinkedList.get(j);

					// add the key of tge found entry to the growing String array 'keyStringArray'.
					keyStringArray[keyCount] = foundEntry.key;

					// increment the int size after a new word is added so we can see the next position 
					// a string should be added.
					keyCount++;
				}
			}
		}

		// after all the keys have been discovered and logged, return the String array.
		return keyStringArray;
	}


	// Returns the size of the biggest bucket (most collisions) in the hashtable. 
	public int biggestBucket() {
		int biggestBucket = 0; 
		for(int i = 0; i < table.length; i++) {
			// Loop through the table looking for non-null locations. 
			if (table[i] != null) {
				// If you find a non-null location, compare the bucket size against the largest
				// bucket size found so far. If the current bucket size is bigger, set biggestBucket
				// to this new size. 
				MyLinkedList bucket = table[i];
				if (biggestBucket < bucket.size())
					biggestBucket = bucket.size();
			}
		}
		return biggestBucket; // Return the size of the biggest bucket found. 
	}

	// Returns the average bucket length. Gives a sense of how many collisions are happening overall.
	public float averageBucket() {
		float bucketCount = 0; // Number of buckets (non-null table locations)
		float bucketSizeSum = 0; // Sum of the size of all buckets
		for(int i = 0; i < table.length; i++) {
			// Loop through the table 
			if (table[i] != null) {
				// For a non-null location, increment the bucketCount and add to the bucketSizeSum
				MyLinkedList bucket = table[i];
				bucketSizeSum += bucket.size();
				bucketCount++;
			}
		}

		// Divide bucketSizeSum by the number of buckets to get an average bucket length. 
		return bucketSizeSum/bucketCount; 
	}

	public String toString() {
		String s = "";
		for(int tableIndex = 0; tableIndex < tableSize; tableIndex++) {
			if (table[tableIndex] != null) {
				MyLinkedList bucket = table[tableIndex];
				for(int listIndex = 0; listIndex < bucket.size(); listIndex++) {
					Entry e = (Entry)bucket.get(listIndex);
					s = s + "key: " + e.key + ", value: " + e.value + "\n";
				}
			}
		}
		return s; 
	}
}