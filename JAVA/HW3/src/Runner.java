import java.io.*;
import java.util.*;


public class Runner {
	public static void main(String[] args) throws FileNotFoundException{
		/*
		File file = new File(".");
		for(String fileNames : file.list()) 
		System.out.println(fileNames);
		*/
		File inputStopWords = new File("stop_words.txt");
		Scanner in = new Scanner(inputStopWords);
		ArrayList<String> stopwordList = new ArrayList<>();
		while(in.hasNextLine()) {			
			stopwordList.add(in.nextLine());
		}
		in.close();

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
						//then skip
						continue;
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

		//sort by alphabetical order
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
		
		//frequency		
		int[] index_count = new int[len];
		String[] index_word = new String[len];
		
		int i=0, j=0;//i for arrwordlist, j for index
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
		//System.out.println("j=" + j);
		
		//number ex: 0001, 0002
		String[] index_num = new String[j+1];//length of counted words
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
		//output txt sort by alphabet
		i=0;
		PrintWriter out1 = new PrintWriter("docProcess.csv");
		while(i<=j) {
			out1.println(index_num[i] + " " + index_word[i] + " " + index_count[i]);
			i++;
		}
		out1.close();
		
		//output txt sort by freqeuncy
		sortByFreq(index_count, index_word, j);
		i=0;
		PrintWriter out2 = new PrintWriter("docProcessOrdered.csv");
		while(i<j) {
			out2.println(index_num[i] + " " + index_word[i] + " " + index_count[i]);
			i++;
		}
		out2.close();
	}
	
	//need a method sortbyfrequncy
	public static void sortByFreq(int[] index_count, String[] index_word, int len) {
		int tempint;
		String tempstring;
		for(int i=0;i<len;i++) {
			for(int j=i+1;j<len;j++) {
				//
				if(index_count[i] < index_count[j]) {
					tempint = index_count[i];
					index_count[i] = index_count[j];
					index_count[j] = tempint;
					tempstring = index_word[i];
					index_word[i] = index_word[j];
					index_word[j] = tempstring;
				}
				//when counts are equal, sort by alphabet
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
