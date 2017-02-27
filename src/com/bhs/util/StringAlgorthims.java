package com.bhs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

public class StringAlgorthims {

	/**
	 * @param args
	 * @throws IOException
	 */

	// Program: Find longest substring without repeating characters.
	// Write a program to read words from a file. Count the repeated or
	// duplicated words. Sort it by maximum repeated or uplicated word count.
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// duplicateCharactersFromString();
		// anagramsStrings();
		// firstNonRepeatedCharacter();
		// reverseString();
		// duplicateCharactersInString();
		// vowelsAndConsonantsInString();
		// occurrenceOfAGivenCharacterInString();
		// replaceEachGivenCharacterToOther();
		// reverseWordsInAsentence();
		// removeDuplicateCharactersFromString();
		// highestOccurredCharacterInString();
		// removeGivenCharactersFromString();
		// aStringIsNumorNot();
		// permutations();
		// readFromFileCountRepaetedWords();
		// alphabetical();
		sortOrderOfWords();
	}

	/**
	 * To start with, we have a simple String related coding question frequently
	 * asked in programming interviews. You need to write a program in C, C++,
	 * Java or Python to print duplicate characters from a given String, for
	 * example if String is "Java" then program should print "a". Bonus points
	 * if your program is robust and handle different kinds of input e.g. String
	 * without duplicate, null or empty String etc. Bonus points if you also
	 * write unit tests for normal and edge cases.
	 */

	public static void duplicateCharactersFromString() {

		String str = "Javakaaaaaaak";
		HashSet<Character> hSet = new HashSet<Character>();
		HashMap<Character, Integer> hMap = new HashMap<Character, Integer>();
		// int count=0;
		for (int i = 0; i < str.length(); i++) {
			if (!hSet.add(str.charAt(i))) {
				hMap.put(str.charAt(i), hMap.get(str.charAt(i)) + 1);
			} else
				hMap.put(str.charAt(i), 1);

		}
		Set<Map.Entry<Character, Integer>> entrySet = hMap.entrySet();
		Iterator itr = hMap.entrySet().iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());

		}

		for (Map.Entry<Character, Integer> entrySet1 : entrySet) {
			if (entrySet1.getValue() > 1)
				System.out.println(entrySet1.getKey() + "= "
						+ entrySet1.getValue());

		}
		/*
		 * for(int i=0;i<hMap.size();i++){ hMap.keySet().; }
		 */
		String a = "Java";
		String res = "";

		while (a.length() > 0) {
			int c = 1;
			for (int j = 1; j < a.length(); j++) {
				if (a.charAt(0) == a.charAt(j))
					c = c + 1;
			}
			// if(c>1)
			res = res + a.charAt(0) + "=" + c + "\n";
			String k[] = a.split(a.charAt(0) + "");
			a = new String("");
			for (int i = 0; i < k.length; i++)
				a = a + k[i];
		}
		System.out.println(res);
	}

	/**
	 * A simple coding problem based upon String, but could also be asked with
	 * numbers. You need to write a Java program to check if two given strings
	 * are anagrams of Each other. Two strings are anagrams if they are written
	 * using the same exact letters, ignoring space, punctuation and
	 * capitalization. Each letter should have the same count in both strings.
	 * For example, Army and Mary are anagram of each other.
	 */
	public static void anagramsStrings() {

		char[] str1 = "word".toCharArray();
		char[] str2 = "wrdo".toCharArray();
		String res = "A";
		int str1Length = str1.length;
		int str2Length = str2.length;

		if (str1Length != str2Length)
			System.out.println("Not Anagram");

		for (int i = 0; i < str1Length; i++) {
			int count = 0;
			for (int j = 0; j < str1Length; j++) {
				if (str1[i] == str2[j])
					count++;
			}
			if (count != 1) {
				System.out.println("Not Anagram");
				res = "NA";
				break;
			}

		}

		if (res.equalsIgnoreCase("A"))
			System.out.println("Anagram");

	}

	/**
	 * One of the most common string interview questions: Find the first
	 * non-repeated (unique) character in a given string. for Example if given
	 * String is "Morning" then it should print "M". This question demonstrates
	 * efficient use of Hashtable. We scan the string from left to right
	 * counting the number occurrences of each character in a Hashtable. Then we
	 * perform a second pass and check the counts of every character. Whenever
	 * we hit a count of 1 we return that character, that’s the first unique
	 * letter. Be prepared for follow-up question for improving memory
	 * efficiency, solving it without hash table as well.
	 */
	public static void firstNonRepeatedCharacter() {

		String str = "Morning";

		while (str.length() > 0) {
			int count = 1;
			for (int i = 1; i < str.length(); i++) {

				if (str.charAt(0) == str.charAt(i))
					count++;
			}

			if (count == 1) {

				System.out.println("First Non repeated character is :"
						+ str.charAt(0));
				break;

			}

			String[] str1 = str.split(str.charAt(0) + "");
			str = "";
			for (int i = 0; i < str1.length; i++)
				str += str1[i];
		}

		/*
		 * for (int i = 0; i < str.length(); i++) { int count = 0; for (int j =
		 * 0; j < str.length(); j++) { if (str.charAt(i) == str.charAt(j))
		 * count++; } if (count == 1) {
		 * 
		 * System.out.println("First Non repeated character is :" +
		 * str.charAt(i)); break;
		 * 
		 * }
		 * 
		 * }
		 */

	}

	/**
	 * Your task is to write a program to reverse String in Java without using
	 * StringBuffer class. You also need to provide both iterative and recursive
	 * algorithm for String reversal. You can use other String utility methods
	 * e.g. charAt(), toCharArray() or substring() from java.lang.String class.
	 */
	public static void reverseString() {

		String str = "Java";
		String revStr = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			revStr += str.charAt(i);
		}

		System.out.println(revStr);

		String str1 = reverseString(str);
		System.out.println("revStrRecursion==" + str1);

	}

	public static String reverseString(String str) {

		if (str.length() == 1)
			return str.substring(0);

		return str.charAt(str.length() - 1)
				+ reverseString(str.substring(0, str.length() - 1));

	}

	/**
	 * You need to write a program to print all duplicate character and their
	 * count in Java. For example if given String is "Programming" then your
	 * program should print g : 2 r : 2 m : 2
	 */
	public static void duplicateCharactersInString() {

		String str = "Programmingmrrr";
		String res = "";

		while (str.length() > 0) {
			int count = 1;
			for (int i = 1; i < str.length(); i++) {
				if (str.charAt(0) == str.charAt(i))
					count++;
			}

			if (count > 1)
				System.out.println(str.charAt(0) + " : " + count);

			String[] str1 = str.split(str.charAt(0) + "");
			str = "";
			for (int i = 0; i < str1.length; i++)
				str = str + str1[i];
		}
	}

	/**
	 * One of easiest String question you will ever see. You have to write a
	 * Java program which will take a String input and print out number of
	 * vowels and consonants on that String. For example if input is "Java" then
	 * your program should print "2 vowels and 2 consonants". If you get this
	 * question on Interview, you should clarify that whether String can contain
	 * numbers, special characters or not e.g. anything other than vowels and
	 * consonants.
	 */
	public static void vowelsAndConsonantsInString() {

		String str = "Java".toLowerCase();
		int vowelCount = 0;
		int constCount = 0;
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == 'a' || str.charAt(i) == 'e'
					|| str.charAt(i) == 'i' || str.charAt(i) == 'o'
					|| str.charAt(i) == 'u')
				vowelCount++;
			else
				constCount++;
		}
		System.out.println(vowelCount + " vowels and " + constCount
				+ " consonants");
	}

	/**
	 * If interviewer ask you to count occurrence of more than one character
	 * than you can either use an array, hash table or any additional data
	 * structure. In order to solve this problem, you are not allowed to do so.
	 * Your method must return count of given character, for example if input
	 * String is "Java" and given character is 'a' then it should return 2.
	 * Bonus point if you handle case, null and empty String and come up with
	 * unit tests.
	 */
	public static void occurrenceOfAGivenCharacterInString() {

		String str = "Today is Monday";
		char countChar = 'a';
		int count = 0;
		// while(str.length()>0){

		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == countChar)
				count++;
		}

		/*
		 * for(int i=0;i<str.length();i++){
		 * 
		 * if(str.charAt(i)==countChar){
		 * 
		 * for(int j=i;j<str.length();j++){ if(str.charAt(i)==str.charAt(j))
		 * count++; } break; }
		 * 
		 * }
		 */

		System.out.println(countChar + " " + count);
		// }
	}

	/**
	 * Write a Java program to replace a given character in a String to other
	 * provided character, for example if you are asked to replace each blank in
	 * a String with %20, similar to URL encoding done by browser, so that
	 * Server can read all request parameters. For example if input is
	 * "Java is Great" and asked to replace space with %20 then it should be
	 * "Java%20is%20Great".
	 */
	public static void replaceEachGivenCharacterToOther() {

		String str = "Java is Great";
		char replaceChar = ' ';
		String res = "";
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == replaceChar)
				res += "%20 ";
			else
				res += str.charAt(i);
		}

		System.out.println(res);
	}

	/**
	 * Write a function, which takes a String word and return sentence on which
	 * words are reversed in order e.g. if input is
	 * "Java is best programming language", output should be
	 * "language programming best is Java".
	 */
	public static void reverseWordsInAsentence() {

		String str = "Java is best programming language";
		String str1[] = str.split(" ");
		String res = "";
		for (int i = str1.length - 1; i >= 0; i--) {

			res += str1[i] + " ";
		}

		System.out.println(res);
	}

	/**
	 * This is one of the interesting String question, which also has lots of
	 * variants. You need to remove duplicate characters from a given string
	 * keeping only the first occurrences. For example, if the input is
	 * ‘bananas’ the output will be ‘bans’. Pay attention to what output could
	 * be, because if you look closely original order of characters are retained
	 * in output. This is where many developer make mistake of shorting
	 * character array of String and removing duplicates, similar to how you
	 * remove duplicates from array. That destroys original order of characters
	 * and will not be correct solution in this case.
	 */
	public static void removeDuplicateCharactersFromString() {

		String str = "bananas";
		String res = "";

		while (str.length() > 0) {
			int count = 0;
			/*
			 * for(int i=0;i<str.length();i++){
			 * 
			 * if(str.charAt(0)==str.charAt(i)) count++; }
			 */

			res += str.charAt(0);
			String[] str1 = str.split(str.charAt(0) + "");
			str = "";
			for (int i = 0; i < str1.length; i++) {
				str += str1[i];
			}

		}

		System.out.println(res);

	}

	/**
	 * You need to write a function to implement algorithm which will accept a
	 * string of characters and should find the highest occurrence of the
	 * character and display it. For example if input is
	 * "aaaaaaaaaaaaaaaaabbbbcddddeeeeee" it should return "a".
	 */
	public static void highestOccurredCharacterInString() {

		String str = "aaaaaaaaaaaaaaaaabbbbcddddeeeeee";
		String res = "";
		int hCount = 0;
		char hCar = ' ';

		while (str.length() > 0) {

			int count = 0;

			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(0) == str.charAt(i))
					count++;

				if (count > hCount) {
					hCount = count;
					hCar = str.charAt(0);
				}
			}

			String[] str1 = str.split(str.charAt(0) + "");
			str = "";
			for (int i = 0; i < str1.length; i++) {
				str += str1[i];
			}
		}
		System.out.println(hCar);
	}

	/**
	 * One of my favorite coding question, when I interview Java developers. You
	 * need to write a Java method which will accept a String and a character to
	 * be removed and return a String, which doesn't has that character e.g
	 * remove(String word, char ch). You need to provide both iterative and
	 * recursive solution of this method and also has to write JUnit tests to
	 * cover cases like null and empty String, input which only contains letter
	 * to be removed, String which doesn't contain given character etc.
	 */
	public static void removeGivenCharactersFromString() {

		String str = "aaaaaaaaaaaaaaaaabbbbcddddeeeeee";
		char removeChar = 'a';
		String str1 = "";
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) != removeChar)
				str1 += str.charAt(i);
		}

		System.out.println(str1);
	}

	/*
	 * 5) How to check if a String contains only digits?
	 */

	static void aStringIsNumorNot() {
		String str = "a09g12567";

		for (int i = 0; i < str.length(); i++) {
			int x = (int) str.charAt(i) - (int) '0';
			if (x < 0 || x > 9) {
				System.out.println("Is nuber" + x);
			} else
				System.out.println("Is not nuber" + x);
		}
	}

	static void permutations() {
		String str = "xyz";

		for (int i = 0; i < str.length(); i++) {
			String strCom = str.charAt(i) + "";
			for (int j = 0; j < str.length(); j++) {

				// if(i==0)
				// strCom+=str.charAt(j);
				// else
				if (j > i)
					strCom += str.charAt(i + j);

				else if (j < i)
					strCom += str.charAt(i - j);

			}
			System.out.println(strCom);
		}

	}

	static void readFromFileCountRepaetedWords() throws IOException {

		// FileOutputStream fos=new FileOutputStream(new
		// File("C:\\ReadFile\\TestFile.txt"));
		FileReader fr = new FileReader(new File("C:\\ReadFile\\TestFile.txt"));
		BufferedReader br = new BufferedReader(fr);
		String curStr = null;
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		HashSet<String> hs = new HashSet<String>();
		while ((curStr = br.readLine()) != null) {

			System.out.println(curStr);

			String strArray[] = curStr.split(" ");

			for (int i = 0; i < strArray.length; i++) {

				if (!hs.add(strArray[i]))
					hm.put(strArray[i], hm.get(strArray[i]) + 1);
				else
					hm.put(strArray[i], 1);

			}

		}

		Object dupStr[] = hm.keySet().toArray();
		for (Object dup : dupStr) {

			System.out.println(dup.toString() + ": " + hm.get(dup.toString()));
		}

	}

	static void alphabetical() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter a word : ");
		String n = br.readLine();
		int l = n.length();
		char b[] = new char[l];
		for (int i = 0; i < l; i++)
			b[i] = n.charAt(i);
		char t;
		for (int j = 0; j < l - 1; j++) {
			for (int k = 0; k < l - 1 - j; k++) {
				if (b[k] > b[k + 1]) {
					t = b[k];
					b[k] = b[k + 1];
					b[k + 1] = t;
				}
			}
		}
		System.out.println("\nOriginal word : " + n);
		System.out.print("Sorted word : ");
		for (int m = 0; m < l; m++)
			System.out.print(b[m]);
		System.out.print("\n");

	}

	static void sortOrderOfWords() throws IOException {

		String org = "Hi How Are You";

		String[] orgArr = org.split(" ");
		/*
		 * Arrays.sort(orgArr);
		 * 
		 * StringBuffer sb=new StringBuffer(); for(int i=0;i<orgArr.length;i++){
		 * 
		 * sb.append(orgArr[i]); sb.append(" "); }
		 * 
		 * System.out.println(sb.toString().trim());
		 */

		/*
		 * for(int i=0;i<orgArr.length;i++){
		 * 
		 * // for(int ) }
		 */

		String[] arr = { "Banana", "Apple", "Orange", "Fruit", "Watermelon",
				"Hello World" };
		String tmp;
		for (int i = 0; i < arr.length; i++) {
			// tmp = arr[i];
			for (int j = 0; j < arr.length-1; j++) {
				// if (i == j) continue;
				// int x = tmp.compareTo(arr[j]);
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					tmp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = tmp;
				}

			}
		}
			for (int i = 0; i < arr.length; i++)
				System.out.println(arr[i]);

		
	}
}
