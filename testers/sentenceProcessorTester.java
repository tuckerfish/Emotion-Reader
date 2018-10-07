import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.BreakIterator;

class sentenceProcessorTester{
	
	public static void main(String[] args){

		// temporaty String to hold the value of each line within the text.
    	String tempStringLine;

		 /* SCANNING IN THE SENTENCE */
        // create a queue called sentenceQueue to temporary store each line of the text file as a String.
        MyLinkedList sentenceList = new MyLinkedList();

        // integer which keeps track of the index position for each new sentence string addition.
        int listCount = 0;

        // create a new file by opening a text file.
        File file2 = new File("testEmail2.txt");
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

		sentenceProcessor one = new sentenceProcessor(sentenceList);
		String[] names = one.findNames();
        System.out.println("Speaker " + names[0]);
        System.out.println("Subject " + names[1]);
	}
}