package FileParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * A program that will accept any text file, as input, and creates a file parser
 * that processes all the words found in that input file based on the below mentioned rules. The input file may have
 * zero or more words, as well as specific limited set of other characters /punctuation that are used in a specific and
 * predetermined manner. In particular, the text file may contain any characters from the ASCII character set.
 * 
 * @author HARMANVIR SINGH(40019114) 
 * @author SARABPREET SINGH(40154067)
 */
public class Driver {
	public static void main(String[] args) {
		Scanner input = null;
		PrintWriter outputWithoutNOA = null;
		PrintWriter outputMoreThan3time = null;
		PrintWriter outputNon_alphanumerics = null;
		Scanner keyIn = new Scanner(System.in);
		ArrayList<String> withoutNOA = new ArrayList<String>();
		ArrayList<WordData> moreThan3Times = new ArrayList<WordData>();
		ArrayList<String> popular_peeps = new ArrayList<String>();
		ArrayList<String> non_alphanumeric = new ArrayList<String>();
		char alphaNumArray[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
				'p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		System.out.println("\n***************************************************************************************\n"
				+ "\t\tWelcome to the File Parsing Program \n"
				+ "***************************************************************************************");
		System.out.print("Enter the name of the file along with extention : ");
		String keyInName = keyIn.nextLine();
		try {
			input = new Scanner(new FileInputStream(keyInName));
			outputWithoutNOA = new PrintWriter("obsessive_non_ona.txt");
			outputMoreThan3time = new PrintWriter("popular_peeps.txt");
			outputNon_alphanumerics = new PrintWriter("non_alphanumerics.txt");
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found...\nExiting the program...");
			System.exit(0);
		}
		String word = null;
		while(input.hasNext()) {
			boolean hasNOA = false;
			word = input.next().toLowerCase();
			//Part 1
			//Verifying if <word> contains 'n' or 'o' or 'a'. 
			for(int i = 0; i<word.length(); i++) {
				if(word.charAt(i) == 'n' || word.charAt(i) == 'o' || word.charAt(i) == 'a' ) {
					hasNOA = true;
					break;
				}
			}
			if(!hasNOA && !withoutNOA.contains(word)) {
				withoutNOA.add(word);
			}
			//Part 2
			//Adding WordData objects to the arraylist <moreThan3Times>
			boolean hasWord = false;
			if(moreThan3Times == null) {
				moreThan3Times.add(new WordData(word));
			}
			else {
				for(int i = 0; i<moreThan3Times.size();i++) {
					if(moreThan3Times.get(i).equals(new WordData(word))) {
						moreThan3Times.get(i).count++;
						hasWord = true;
					}
				}
			}
			if(!hasWord) {
				moreThan3Times.add(new WordData(word));
			}
			//Part3
			//Adding all non-alphanumeric words.
			boolean hasAlphaNum = false;
			for(int i=0; i<word.length(); i++) {
				for(int j=0; j<alphaNumArray.length; j++) {
					if(word.charAt(i)==alphaNumArray[j]) {
						hasAlphaNum = true;
						break;
					}
				}
			}
			if(!hasAlphaNum && !non_alphanumeric.contains(word)) {
				non_alphanumeric.add(word);
			}
		}
		//Writing the output on the file.
		Collections.sort(withoutNOA);
		outputWithoutNOA.println("Word Count : " + withoutNOA.size());
		for(int i=0; i<withoutNOA.size(); i++) {
			outputWithoutNOA.println(withoutNOA.get(i));
		}
		// Filling up the ArrayList <popular_peeps> with the Strings.
		for(int i=0; i<moreThan3Times.size(); i++) {
			if(moreThan3Times.get(i).getCount() > 3) {
				popular_peeps.add(moreThan3Times.get(i).getWord());
			}
		}
		//Writing the output on the txt file.
		outputMoreThan3time.println("Word Count : " + popular_peeps.size());
		Collections.sort(popular_peeps);
		for(int i=0; i<popular_peeps.size(); i++) {
			outputMoreThan3time.println(popular_peeps.get(i));
		}
		//Writing the output on the txt file.
		Collections.sort(non_alphanumeric);
		outputNon_alphanumerics.println("Word Count : " + non_alphanumeric.size());
		for(int i=0; i<non_alphanumeric.size(); i++) {
			outputNon_alphanumerics.println(non_alphanumeric.get(i));
		}
		//Closing all the files that were opened.
		outputNon_alphanumerics.close();
		outputWithoutNOA.close();
		outputMoreThan3time.close();
		input.close();
		keyIn.close();
		System.out.println("File has been succesfully Parsed....!!");
	}
}
