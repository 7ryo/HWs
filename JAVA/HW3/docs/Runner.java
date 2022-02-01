import java.io.*;
import java.util.*;

/**
 * The Runner class read some pieces of articles
 * and count the freqeuncy of words.
 */


public class Runner {
	public static void main(String[] args) throws FileNotFoundException{
		
		/**
		 * Input the list of stop words, which are some meaningless
		 * words that should not be include when counting 
		 * frequency, and set up an arraylist.
		 */
		File inputStopWords = new File("stop_words.txt");
		Scanner in = new Scanner(inputStopWords);
		ArrayList<String> stopwordList = new ArrayList<>();
		while(in.hasNextLine()) {			
			stopwordList.add(in.nextLine());
		}
		in.close();
		/**
		 * Input the document file. Only get the words
		 * between <TEXT> and </TEXT>. And if a word is
		 * stop word, skip it. Otherwise add it into 
		 * the arraylist wordList.
		 */
		File inputFile = new File("docset2020.txt");
		Scanner sc = new Scanner(inputFile);
		String readWord;
		String w;
		ArrayList<String> wordList = new ArrayList<>();
		while(sc.hasNext()) {
			readWord = sc.next();
			if(!readWord.equals("<TEXT>")) {
				continue;
			}
			if(readWord.equals("<TEXT>")) {
				do {
					w = sc.next();					
					if(w.equals("</TEXT>")) {
						break;
					}
					w = w.replaceAll("[^a-zA-Z[.]]", "");
					while(w.length()>0 && w.charAt(w.length()-1) == '.'){
						w = w.substring(0, w.length()-1);

					}
					
					w = w.toLowerCase();
					
					if(stopwordList.contains(w) || w.equals("")) {
						continue; /*skip the stop word*/
					}
					else {
						wordList.add(w);
					}
				}while(true);
			}
		}
		sc.close();
		
		String[] arrwordList = wordList.toArray(new String[0]);
		int len = arrwordList.length;

		/**
		 * Now there will be a lot of words in the 
		 * arraylist wordList.
		 * Convert it to array arrwordList.
		 * And sort the words by alphabetical order.
		 */
		String temp;
		for(int i=0;i<arrwordList.length;i++) {
			for(int j=i+1;j<arrwordList.length;j++) {
				if(arrwordList[i].compareTo(arrwordList[j]) > 0) {
					temp = arrwordList[i];
					arrwordList[i] = arrwordList[j];
					arrwordList[j] = temp;
				}
			}
		}	
		
		/**
		 * Calculate the frequency of each word.
		 */		
		int[] index_count = new int[len];
		String[] index_word = new String[len];
		
		int i=0, j=0;/*i for arrwordList, j for index*/
		while(i<len-1) {
			index_word[j] = arrwordList[i];
			if(arrwordList[i].equals(arrwordList[i+1])) {
				index_count[j]++;
				i++;				
			}
			else {
				index_count[j]++;
				i++;
				j++;
			}			
		}
		if(i==len-1){
			if(arrwordList[i].equals(arrwordList[i-1])){
				index_count[j-1]++;
			}
			else{
				index_word[j] = arrwordList[i];
				index_count[j]++;
			}
		}
		i=0;
		
		/**
		 * Build a list of index number which will
		 * show in front of each word.
		 * The term would be "0001", "0023", "0534", etc.
		 */
		String[] index_num = new String[j+1];/*j+1 = length of counted words*/
		i=1;
		while(i<=j+1) {
			if(i/10<1) {
				index_num[i-1] = "000" + i;
				i++;
			}
			else if(i/100<1) {
				index_num[i-1] = "00" + i;
				i++;
			}
			else{
				index_num[i-1] = "0" + i;
				i++;
			}
		}
		/**
		 * Output a txt file which the words were sorted by alphabetical order.
		 * The name of the file is docProcess.txt.
		 */
		i=0;
		PrintWriter out1 = new PrintWriter("docProcess.txt");
		while(i<=j) {
			out1.println(index_num[i] + " " + index_word[i] + " " + index_count[i]);
			i++;
		}
		out1.close();		
		/**
		 * Output a txt file which the words were sorted by frequency.
		 * The name of the file is docProcessOrdered.txt.
		 */
		sortByFreq(index_count, index_word, j);
		i=0;
		PrintWriter out2 = new PrintWriter("docProcessOrdered.txt");
		while(i<j) {
			out2.println(index_num[i] + " " + index_word[i] + " " + index_count[i]);
			i++;
		}
		out2.close();

	}
	
	/**
	 * A method that sort the words by freqeuncy.
	 * @param index_count contains the number of times a word appeared in the article
	 * @param index_word contains the words that is not stop word
	 * @param len is the number of unique words, also the length of index_word[]
	 */
	public static void sortByFreq(int[] index_count, String[] index_word, int len) {
		int tempint;
		String tempstring;
		for(int i=0;i<len;i++) {
			for(int j=i+1;j<len;j++) {
				if(index_count[i] < index_count[j]) {
					tempint = index_count[i];
					index_count[i] = index_count[j];
					index_count[j] = tempint;
					tempstring = index_word[i];
					index_word[i] = index_word[j];
					index_word[j] = tempstring;
				}
				/**
				 * If two words have the same frequency, sort them by
				 * alphabetical order.
				 */
				if(index_count[i] == index_count[j]) {
					if(index_word[i].compareTo(index_word[j]) > 0) {
						tempint = index_count[i];
						index_count[i] = index_count[j];
						index_count[j] = tempint;
						tempstring = index_word[i];
						index_word[i] = index_word[j];
						index_word[j] = tempstring;						
					}
				}
			}
		}		
	}	
}
