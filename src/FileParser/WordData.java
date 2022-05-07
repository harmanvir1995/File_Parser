package FileParser;

/**
 * This class supports the Driver class. Object of the class 
 * has a string and a count. If the String word is repeated 
 * then the count of the object is increased.
 * @author HARMANVIR SINGH(40019114) 
 * @author SARABPREET SINGH(40154067)
 */
public class WordData implements Comparable<WordData> {
	private String word;
	int count;
	/**
	 * Parameterized Constructor.
	 * @param word
	 */
	public WordData(String word) {
		this.word = word;
		this.count = 1;
	}
	
	/**
	 * This method returns the word.
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * This method sets the word.
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * This method gets the count from the word.
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	@Override
	/**
	 * This method returns true if the objects are equal, false if the objects are not equal.
	 * @param obj 	accepts the parameter as an object.
	 * @return boolean true if the objects are equal, false if the objects are not equal.
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordData other = (WordData) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} 
		else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	@Override
	/**
	 * This method overrides the toString method from the Object class.
	 * @return It returns the String to be printed on screen.
	 *
	 */
	public String toString() {
		return "WordData [word=" + word + ", count=" + count + "]";
	}

	@Override
	/**
	 * It overrides the compareTo method from the Object class.
	 * @param WordData 	The WordData object which needs to be compared.
	 * @return 	It returns the 1 if the passed object is greater than the compared object.
	 */
	public int compareTo(WordData o) {
		//this.word is alphabetically greater.
		if(this.word.compareTo(o.word) > 0) {
			return 1;
		}
		//this.word is alphabetically lower.
		else {
			return -1;
		}
	}

	
}
