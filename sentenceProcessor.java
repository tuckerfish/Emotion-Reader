class sentenceProcessor{

	// String array which will copy over all the values of the given Linked List.
	String[] sentenceArray;

	// String array which holds several greetings that don't necessarily need to come with a name.
	String[] greetings = {"hello","hey","hi","greetings","dear"};

	// integer which will save the size of the String array 'sentenceArray'.
	int size;






	// constructor for the SentenceProcessor class which takes in a linked list.
	sentenceProcessor(MyLinkedList sentenceList){

		// save the size of the linked list within the classes size integer variable.
		this.size = sentenceList.size();

		// initialize the string array 'sentenceArray' to the length of the linked list
		// being passed through the constructor, 'sentenceList'. 
		sentenceArray = new String[size];

		// cycle through each value within the linked list 'sentenceList'...
		for(int i = 0; i < size; i++){

			// and save them within the String array 'sentenceArray'
			this.sentenceArray[i] = (String)sentenceList.get(i);
		}
		
	}




	public void print(){
		for(int i = 0; i < sentenceArray.length; i++)
			System.out.println(sentenceArray[i]);
	}
	
	public String[] findNames(){

		String[] returned = {"speaker", "subject"};

		// temporary String to hold the value of each line within the text.
    	String oneStringLine;

    	// temporary String to hold the value of each following the '1sttempStringLine'.
    	String twoStringLine;

    	// temporary character array to hold each character within a sentence string.
    	char[] oneCharArray;

    	// temporary character array to hold each character within the following
    	// sentence string of '1sttempCharArray'.
    	char[] twoCharArray;

    	// integer to save the length of the '1sttempCharArray'.
    	int oneCharLength = 0;

    	// integer to save the lend of the '2ndtempCharArray'.
    	int twoCharLength = 0;

    	// string that saves the name of the speaker.
    	String speaker = null;

    	// string that saves the name of the person being written two before it's been edited.
    	String subjectSentence = null;

    	// String array that saves all the words within the subject string.
    	String[] splitSubject;

    	// switcher that determines if the name of the speaker has been found.
    	boolean foundSpeaker = false;

    	// switcher that determines if the name of the subject has been found.
    	boolean foundSubject = false; 

    	// while loopp that cycles through each sentence string within the 
    	// Queue 'sentenceQueue'.
		for(int i = 0; i < size-1; i++){

			// save the given String in the temporary string holder '1sttempStringLine'.
			oneStringLine = sentenceArray[i];

			// save the following String of the given String in the temporary string holder 'tempStringLine'.
			twoStringLine = sentenceArray[i+1];
			

			// convert the sentence string '1sttempStringLine' and the '2ndtempStringLine' to an array of characters.
			oneCharArray = oneStringLine.toCharArray();
			twoCharArray = twoStringLine.toCharArray();


			// save the length of characters for each sentence in the integer variables '1stcharLength' and '2ndcharLength'. 
			oneCharLength = oneCharArray.length; 
			twoCharLength = twoCharArray.length;


			// if the speaker has not been found yet.
			if(foundSpeaker == false){

				// if the second sentence following the current sentence does not end with a period or comma,
				// it's likely the current sentence is towards the end of the email.
				if(twoCharArray[twoCharLength-2] != '.' 
					&& twoCharArray[twoCharLength-2] != ','

					// if the number of characters within the sentence string is less than 20.
					&& oneCharLength < 20 

					// if the final character with the given sentence string is a comma.
					&& oneCharArray[oneCharLength - 1] == ','){

					// the speaker is the line that follows in the 'sentenceArray'.
					returned[0] = twoStringLine;

					// change the value of the boolean variable 'foundSpeaker' to true,
					// preventing the search from occuring again.
					foundSpeaker = true;

				// if the greater for loop cycling through each sentence of the given text has reached
				// its final 3 values (which allows for the subject to list their contact information
				// below their name).
				}else if(i > size-4

					// if the number of characters within the sentence string is less than 20.
					&& twoCharLength < 20 

					// if the first character within the final setence string is a dash,
					// it suggests the writer is signing their name.
					&& twoCharArray[0] == '-'

					&& twoCharArray[twoCharLength-1] != '.'){

					// save the final sentence String within the 'returned' string array without any
					// dashes or punctuation.
					returned[0] = twoStringLine.replaceAll("[^a-zA-Z ]","");

				// if the greater for loop cycling through each sentence of the given text has reached
				// its final 3 values and the number of characters within the sentence string is less than 20,
				// it's safe to assume this final String is the writer's name. 
				}else if(i > size-4 && twoCharLength < 20 && twoCharArray[twoCharLength-1] != '.'){
					returned[0] = twoStringLine.replaceAll("[^a-zA-Z ]","");

				// if none of these instances ever occurs, the system can't find the name of the speaker,
				// and he/she must be addressed simply as "speaker".
				}else{
					speaker = "speaker";
				}
			}



			// if the subject has not been found yet.
			if(foundSubject == false){

				// if the final character of the given sentence string is not a period
				if (oneCharArray[oneCharLength - 2] != '.'

					// if the number of characters within the sentence string is less than 20.
					// IE we're looking for a sentence string that is rather short, 
					// such as a greeting.
					&& oneCharLength < 20

					// if the second sentence following the current sentence ends with a period or comma,
					// it's safe to assume we're looking at a sentence towards the top of the message.
					&& twoCharArray[twoCharLength-2] == '.' 
					|| twoCharArray[twoCharLength-2] == ','){

					// save the sentence string containing the subject's name in the String 'subjectSentence'
					// remove all non-letter characters.
					subjectSentence = sentenceArray[i].replaceAll("[^a-zA-Z ]","");			

					// split the String 'subjectSentence' by spaces, saving each word in the 'splitSubject' String array
					splitSubject = subjectSentence.split(" ");


					// if the length of the String array 'splitSubject' is 1, there is either just a greeting or the
					// name of the subject.
					if(splitSubject.length == 1){

						// cycle through each value within the 'greetings' string array.
						for(int c = 0; c < greetings.length; c++){

							// if one of the greetings string values is equivalent to 
							// the only word within the splitSubject, there is no subject
							if(greetings[c].compareTo(splitSubject[0].toLowerCase()) == 0){

								// end the loop because the single word is a greeting.
								break;

							} else {

								// if their is an instance where they aren't equivalent, it suggest the greeting
								// is simply the subject name followed by a comma.
								returned[1] = splitSubject[0];
							}
						}

					// if the length of the String array 'splitSubject' greater than 1, it's likely it contains the name of the subject.
					}else if(splitSubject.length >= 2){

						// cycle through each value within the 'greetings' string array.
						for(int c = 0; c < greetings.length; c++){

							// if one of the greetings string values is equivalent to 
							// the first word within the splitSubject, the subject name follows
							// greeting
							if(greetings[c].compareTo(splitSubject[0].toLowerCase()) == 0){

								// set the second value within the 'returned' array equal to the second value within the
								// splitSubject array, which should be the first name of the subject.
								returned[1] = splitSubject[1];

								// end the loop, because we found the name of the subject.
								break;

							} else {

								// if their is an instance where they aren't equivalent, it suggest the greeting
								// is simply the subject name followed by a comma.
								returned[1] = splitSubject[0];
							}
						}

						//if the subject name continues (i.e. middle or last), add it to the second position of the 
						// returned array.
						for(int t = 2; t < splitSubject.length; t++){
							returned[1] = returned[1] + " " + splitSubject[t];
						}


					}


					// change the value of the boolean variable 'foundSpeaker' to true,
					// preventing the search from occuring again.
					foundSubject = true;
				}
			}

			if(foundSpeaker == true && foundSubject == true)
				break;
		}

		return returned;
	}	
}