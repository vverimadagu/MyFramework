package com.bhs.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DuplicateWordsInString {
	public static void main(String[] args) {
		String test = "This sentence contains two words, one and two";
		Set<String> duplicates = duplicateWords(test);
		System.out.println("input : " + test);
		System.out.println("output : " + duplicates);
		duplicateWords1(test);
	}

	/**
	 * * Method to find duplicate words in a Sentence or String * @param input
	 * String * @return set of duplicate words
	 */
	public static Set<String> duplicateWords(String input) {
		if (input == null || input.isEmpty()) {
			return Collections.emptySet();
		}
		Set<String> duplicates = new HashSet<>();
		String[] words = input.split(" ");
		Set<String> set = new HashSet<>();
		for (String word : words) {
			if (!set.add(word)) {
				duplicates.add(word);
			}
		}
		return duplicates;
	}
	
	public static String duplicateWords1(String input) {
		if (input == null || input.isEmpty()) {
			return "";
		}
		//Set<String> duplicates = new HashSet<>();
		String[] words = input.split(" ");
		HashSet<String> hs=new HashSet<String>();
		//HashSet<String> rw=new HashSet<String>();
		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		int count=1;
		for(int i=0;i<words.length;i++){
			if(!hs.add(words[i])){
				hm.put(words[i], hm.get(words[i])+1);
			}
			else
				hm.put(words[i], 1);
		}
		
		Object [] sk=hm.keySet().toArray();
		
			for(Object str:sk )
			System.out.println(str.toString() +" : "+hm.get(str.toString()));
			/*if(hm.get(sk.next())){
				
			}*/
		//}
		//hm.g;
		
		return "";
	}
}
