import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.BreakIterator;

class emotionReader{

    public static void main(String[] args){

    	// hashtable that contains every category and subsequent int value.
        MyHashtable categoryTable = new MyHashtable(5);

        // add each string category with an int set to zero in the hashtbale 'categoryTable'
        double vote = 0;

        // create a double 'totalCount' to tally up all the votes.
        double totalCount = 0;

        // keeps track of the most applicable category.
        double largest = 0.0;

        // string array to contain every category string.
        String[] totalCategoryArray;

        // temporaty String to hold the value of each line within the text.
    	String tempStringLine;

    	// saves the largest category string.
    	String largestString = null;


        /*LOAD THE DICTIONARY*/
        // create a new dictionary object called 'emotionDict'.
        Dictionary emotionDict = new Dictionary();

        // create a new file by opening a text file.
        File file1 = new File("emotionDict.txt");

        // implement a try-catch block to read into the file.
        try{

        	// create a new scanner 'sk' for the newly allocated file.
        	Scanner sk = new Scanner(file1);

        	// while there are still lines within the file, continue.
        	while(sk.hasNextLine()){

        		// save each line within the file to the String 'tempStringLine'.
        		tempStringLine = sk.nextLine();

        		// split each line into two tokens (the category and the remaining wordlist)
        		// where the scanner finds a colon, saving the two halves in the String array 'colonSplit'.
        		String[] colonSplit = tempStringLine.split(":");

        		// take the first token (the given category String) and add it to the MyHashtable 'categoryTable'.
        		categoryTable.put(colonSplit[0], vote);

        		// take the second token, or word list, and divide the words by the comma,
        		// saving each word within the String array 'words'
        		String[] words = colonSplit[1].split(",");

        		// scan through each value within the 'words' array
        		for(int i = 0; i<words.length; i++){
        			
        			// add each word to the newly allocated dictionary 'emotionDict' with the first
        			// value within the colonSplit array, or category, and each word saved in the 'words' array.
        			emotionDict.add(colonSplit[0],words[i]);
        		}
        	}

        	// close the scanner 'sk'.
        	sk.close();

        // if the file could not be opened, throw a FileNotFoundException.
        } catch(FileNotFoundException e){
        	e.printStackTrace();
        }


        /* SCANNING IN THE SENTENCE */
        // create a queue called sentenceQueue to temporary store each line of the text file as a String.
        MyLinkedList sentenceList = new MyLinkedList();

        // integer which keeps track of the index position for each new sentence string addition.
        int listCount = 0;

        // create a new file by opening a text file.
        File file2 = new File("firedEmail.txt");
        try {

            // create a new scanner 'sc' for the newly allocated file.
            Scanner sc = new Scanner(file2);

            // while there are still lines within the file, continue.
            while (sc.hasNextLine()) {

                // save each line within the file to the String 'tempStringLine'.
                tempStringLine = sc.nextLine();

                // create a new BreakIterator called 'sentenceIterator' to break the 'tempStringLine' into sentences.
                BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();

                // Set a new text string 'tempStringLine' to be scanned.
                sentenceIterator.setText(tempStringLine);

                // save the first index boundary in the integer 'start'.
                // The iterator's current position is set to the first text boundary.
                int start = sentenceIterator.first();

                // save the boundary following the current boundary in the integer 'end'.
                for(int end = sentenceIterator.next();

                    // while the end integer does not equal 'BreakIterator.DONE' or the end of the boundary.
                    end != BreakIterator.DONE;

                    // set the start integer equal to the end integer. Set the end integer equal to the next boundary.
                    start = end, end = sentenceIterator.next()){

                    // create a substring of tempStringLine of the start and end boundsries, which are just Strings of
                    // each sentence.
                    sentenceList.add(listCount,tempStringLine.substring(start, end));

                    // add to the count.
                    listCount++;
                }
            }

            // close the scanner 'sc'.
            sc.close(); 

        // if the file could not be opened, throw a FileNotFoundException.
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // use MyHashtable's getkey's method to save an String array 'totalCategoryArray'.
        totalCategoryArray = categoryTable.getKeys();


        /* ANALYZE EACH INDIVIDUAL WORD */
        // while the Queue 'sentenceQueue' is not empty.
        for(int i = 0; i < sentenceList.size(); i++){

        	// split the given dequeued string by each space to find each word. Save each word 
        	// string in the 'words' array.
        	String[] words = ((String)sentenceList.get(i)).split(" ");

        	// create a for loop that loops for the length of the newly created words array.
        	for(int j = 0; j< words.length; j++){

        		// for each word saved within the 'words' array, using the get method 
        		// within the Dictionary class, find the word's category. Save the category 
        		// within the String 'category' (if it's not included in the dictionary, the
        		// get() method will simply return 'null').
        		String category = emotionDict.get(words[j].replaceAll("[^a-zA-Z ]",""));

        		// if the 'category' string is not equal to null...
        		if(category != null){

        			// use a for loop to loop for the length of the String array 'totalCategoryArray'.
        			for(int t = 0; t < totalCategoryArray.length; t++){

        				// pull each String category from the String array totalCategoryArray to see
        				// If it's ASCII code is equivalent to the found category.
        				if(totalCategoryArray[t].compareTo(category)== 0){

        					// use MyHashtables get() method to return the double value of
        					// the given category String and save it in the double 'changingDouble'
        					double changingDouble = (double)categoryTable.get(category);

        					// increase the size of the double by one.
        					changingDouble++;

        					// add the changed double value back into the hashtable 'categoryTable'.
        					categoryTable.put(category,changingDouble);
        				}
        			}
        		}
        	}
        }

        


        // loop through the length of the array 'totalCategoryArray'.
        for(int s = 0; s < categoryTable.size(); s++){

        	// use MyHashtables get() method to find the double value for each category String
        	// saved within the array 'totalCategoryArray'. Add its given value to the total.
        	totalCount = totalCount + (double)categoryTable.get(totalCategoryArray[s]);
        }

        /* PROGRAM RESPONSE */
        // create a new sentenceProcessor object 'text' with 'sentenceList' is an input.
        sentenceProcessor text = new sentenceProcessor(sentenceList);

        // preform the sentenceProcessor's 'findNames' function to find the speaker and subject
        // to return them in the 2 length string 'names'.
        String[] names = text.findNames();

        // print out a general response statement with addressing both the speaker and subject.
        System.out.println(names[0] + "'s message to " + names[1] + " is: ");

        // loop through every category String within the array 'totalCategoryArray'.
        for(int s = 0; s < totalCategoryArray.length; s++){

        	// pull each categories individual tally and save it within the double 'individualCount'.
        	double individualCount = (double)categoryTable.get(totalCategoryArray[s]);

        	// (not currently in use), but this will determine the most common category.
        	if(largest < individualCount){
        		largest = individualCount;
        		largestString = totalCategoryArray[s];
        	}     	

        	// calculate the percentage for each category.
        	System.out.println(((int)((individualCount/totalCount)*100) + "% " + totalCategoryArray[s]));
        }

        
        
    }
}