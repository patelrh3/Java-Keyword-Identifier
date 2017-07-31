/*
 * Rishabh Patel
 * Project 5 - Java Keyword Identifier
 * CMSC 256 - 001
 * Due Date: 04/29/17
 * Class Description - Project5.java: Main class that implements AVLTree methods after creating an object of the AVLTree class.
 * Two methods written separately (apart from main) to read in the two files needed for the program to run. Both the files written
 * for reading in the input files are run with proper errors and exceptions. Three more methods written to correctly parse the 
 * compiling file or source code file. 
 */

import java.util.*; // imports all of java.util.*
import java.util.regex.*; // imports all of java.util.regex.*
import java.io.*; // imports all of java.io.*

public class Project5 
{
	
	static HashMap<String, Integer> userIdentifiersMap = null; // creates the dictionary called userIdentifiersMap
	static ArrayList<String> inputKeywordsList = new ArrayList<String>(); // creates an ArrayList called inputKeywordsList
	static AVLTree<String> avlTree = new AVLTree<String>(); // creates an object of the AVLTree class called avlTree
	public static void main (String[] args) throws IOException // creates the main that runs all of the code
	{
		printHeading(); // calls the printHeading() method
		ArrayList<String> inputFilesList = new ArrayList<String>(Arrays.asList(args)); // creates an array list called inputFilesList that holds the names of the files
		//If less than 2 arguments are present, in that case prompt user to enter both the files
		if (inputFilesList.size() < 2 || inputFilesList.size() > 2) // condition to make sure that there are exactly two arguments taken in
		{
			System.out.println("Please enter both the files: \n 1. File with keywords \n 2. File that is being compiled"); // prints out an error message if there are not exactly 2 arguments
			inputFilesList = getUserInput(); // sets the inputFilesList to a method called getUserInput()
		}
		readInputFile1(inputFilesList); // calls the method readInputFile1 with a parameter of inputFilesList (arg[0])
		readInputFile2(inputFilesList); // calls the method readInputFile2 with a parameter of inputFilesList (arg[1])
	
	}
	
	public static void printHeading()	// creates the print heading method
	{
		System.out.println("Rishabh Patel"); // prints out my name
		System.out.println("Project 5 - Java Keyword Identifier"); // prints out the project number and name
		System.out.println("CMSC 256 - 001"); // prints out the course name and number and course section
		System.out.println("Due Date: 04/29/2017");
		System.out.println();
	}
	
	public static ArrayList<String> getUserInput() // creates a method called getUserInput that prompts the user to enter the two file names
	{
		ArrayList<String> tempInputFiles = new ArrayList<String>(); // creates an ArrayList for temporary input file names
		//Get the first file name
		Scanner fileNameReader = new Scanner(System.in);
		System.out.println("Enter the file name for the first file you would like to input (file with the keywords):"); // asks user to enter the first file name
		String inputFile1 = fileNameReader.nextLine(); // reads in the user input
		tempInputFiles.add(inputFile1); // adds the first file name to the ArrayList tempInputFiles
		//Get the second file name
		System.out.println("Enter the file name for the second file you would like to input (file you would like to compile):"); // asks user to enter the second file name
		String inputFile2 = fileNameReader.nextLine(); // reads in the user input
		tempInputFiles.add(inputFile2); // adds the second file name to the ArrayList tempInputFiles
		
		return tempInputFiles; // returns the ArrayList tempInputFiles
	}
	public static void readInputFile1(ArrayList<String> inputFilesList) // creates a method called readInputFile1
	{
		//1. Read from keywords file and store the items in "inputKeywordsList" variable - ignore the spaces
		String inputFileName1 = inputFilesList.get(0); // sets argument 1 of the user input equal to inputFileName1
        String line;

		try // try block to read in the input file which allows the user to access it
		{			
			File fileOne = new File(inputFileName1);
			
			while(!fileOne.exists()) // condition while loop for boolean of fileOne to see if it exists
			{
				//Keep asking user for input
				Scanner fileOneNameScanner = new Scanner(System.in); // asks for user input
				System.out.println("Please enter the correct name for the first file you would like to input or type \"QUIT\" if you would like to quit the program:");
				inputFileName1 = fileOneNameScanner.nextLine(); // reads in the user input
				//Store every input in "inputFileName1"
				fileOne = new File(inputFileName1); // calls the File to open up
				
				if (inputFileName1.equals("QUIT")) // condition for user to quit the program
	            {
	            	System.out.println("The program is quitting.");
	            	System.exit(0); // quits the program
	            }
			}
            FileReader fileOneReader = new FileReader(inputFileName1); // starts a FileReader for the first input file
            BufferedReader bufferedReader = new BufferedReader(fileOneReader); // starts a bufferedReader for the first input file
            
			while((line = bufferedReader.readLine()) != null) // condition to read in the file
			{
				line = line.replaceAll(" ", ""); // replaces all white space with empty strings
				line = line.replaceAll("\t", ""); // replaces all white space with empty strings
				line = line.replaceAll("\n", ""); // replaces all white space with empty strings
				
                inputKeywordsList.add(line); // adds the line to the ArrayList inputKeywordsList

                avlTree.insert(line.trim()); // adds a trimmed line to the avlTree object
            }   
			
			System.out.print("A level order traversal of the Java operators and keywords:" +"\n"); // prints out a statement
			avlTree.printTreeTraversal(); // calls the printTreeTraversal method located in the AVLTree class
			
			bufferedReader.close();
		}
		catch(IOException ex) // catch block if there is an index out of bounds error
		{
            System.out.println("Error reading file."); // throws an error if the file cannot be read
        }
	}
	
	static StringTokenizer stringTokenizer = null; // creates a global variable of type StringTokenizer

	public static void readInputFile2(ArrayList<String> inputFilesList) throws IOException // creates a method to read in the second file input (source code file)
	{
		String inputFileName2 = inputFilesList.get(1); // sets the second argument equal to inputFileName2
		userIdentifiersMap = new HashMap<String, Integer>(); // creates a value for the userIdentifiersMap
		String line;
		
	    File fileTwo = new File(inputFileName2); 
		

    	while(!fileTwo.exists()) // condition to see if the second file exists, if it doesn't exist.. the user is prompted 
		{
			Scanner fileTwoNameScanner = new Scanner(System.in); // Scanner for user input
			System.out.println("Please enter the correct name for the second file you would like to input or type \"QUIT\" if you would like to quit the program:");
			inputFileName2 = fileTwoNameScanner.nextLine(); //

			fileTwo = new File(inputFileName2); // opens up the second file
			
			if (inputFileName2.equals("QUIT")) // condition to check if the user wants to quit
		    {
		    	System.out.println("The program is quitting."); 
		    	System.exit(0); // system quits 
		    }
		}
        try
        {
        	FileReader fileTwoReader = new FileReader(inputFileName2); // opens up the FileReader
            BufferedReader bufferedReader2 = new BufferedReader(fileTwoReader); // starts the Buffered Reader
        
        	while((line = bufferedReader2.readLine()) != null) // condition to check if the line being read is not null
			{
				
				if(line.startsWith("//")) // if line starts with "//", ignore it
				{
					continue;
				}
				
				if(line.indexOf("//") > -1) // ignores the comments even if the line doesn't begin with '//'
				{
					line = line.substring(0, line.indexOf("//"));
				}
				
				
				String finalTempString = removeStringsFromSourceFile(line); // creates a finalTempString
				tokenizeFinalString(finalTempString); // method call 
				
			}
			bufferedReader2.close(); // closes the buffereReader
			System.out.println("\nThe following tokens are not Java keywords in the file, " + inputFileName2 +":"); // prints out statement in quotes
			printUserIdentifiers(); // calls the print method for the dictionary


        }
        
        catch(IOException ex) // catch block if there is an index out of bounds error
        {
        	System.out.println("Error reading file.");                  
        }		
	}
	
	public static String removeStringsFromSourceFile(String remStr) // creates method for parsing the strings
	{
		String regex = "\"(?:\\\\\"|[^\"])*?\"";
		String replaceRegex = "";
		String improvedString = remStr.replaceAll(regex, replaceRegex);
		return improvedString;
	}
	
	public static void tokenizeFinalString(String tokenStr) // creates a method to tokenize all of the line read in from the file
	{
		stringTokenizer = new StringTokenizer(tokenStr, ";,(){}.[] "); // sets the stringTokenizer equal to an object of itself
		Integer count = null; // creates a null counter
		while(stringTokenizer.hasMoreTokens()) // condition to check if there are more tokens (loops until there are no more tokens)
		{
			String token = stringTokenizer.nextToken(); // accesses each token
			
			if(Pattern.matches("[0-9/+*-+><%]+", token)) // condition to check for parsing implementation
			{
				continue;
			}
			if(avlTree.find(token) != null) // if the avlTree doesn't have the token, then ignore it
			{
				continue;
			}
			if(userIdentifiersMap.containsKey(token)) // if the dictionary already has a key for the token, then count is equal to it's own value
			{
				count = userIdentifiersMap.get(token);
			}
			else // else start a count at 0
			{
				count = new Integer(0);
			}
			count++; // increment the count by 1
			
			userIdentifiersMap.put(token, count); // add the token to the dictionary if it doesn't exist and update the count
		}
	}
	
	
	public static void printUserIdentifiers() // creates a print method for the dictionary
	{
		Set<String> keySet = userIdentifiersMap.keySet(); // creates a Set for the keys in the dictionary (userIdentifiersMap)
		int maxLength = 0;
		for (String key: keySet) // looping condition to check if the key exists in the set
		{
			if(key.length() > maxLength) // as long as it does exist in the set, then maxLength = key.length()
			{
				maxLength = key.length();
			}
		}
				
		for (String key: keySet) // looping condition to check if the key exists in the set
		{
			Integer value = userIdentifiersMap.get(key); // as long as it does, value belonging to the key is returned and set equal to the variabe value
			System.out.println(key + " " + value); // prints out the key and value (count) as a pair 
		}
	}
}

 