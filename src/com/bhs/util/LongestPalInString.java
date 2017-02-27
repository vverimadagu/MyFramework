package com.bhs.util;

public class LongestPalInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pal=longestPalindrome1("9912321456");
		System.out.println("Longest Palindrome=="+pal);
		String longestConsStr=longestconsecutiveString("ababc !!!!bbBcdefgjkl");
		System.out.println("longestconsecutiveString=="+longestConsStr);
	}
	
	public static String longestPalindrome1(String s) {
		 
		int maxPalinLength = 0;
		String longestPalindrome = null;
		int length = s.length();
	 
		// check all possible sub strings
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				//int len = j+1 - i;
				String curr = s.substring(i, j + 1);
				int len =curr.length();
				if (isPalindrome(curr)) {
					if (len > maxPalinLength) {
						longestPalindrome = curr;
						maxPalinLength = len;
					}
				}
			}
		}
	 
		return longestPalindrome;
	}
	 
	public static boolean isPalindrome(String s) {
	 
		/*int strLength=s.length() - 1;
		for (int i = 0; i < strLength; i++) {
			char chr=s.charAt(i);
			if (chr!= s.charAt(strLength - i)) {
				return false;
			}
		}*/
		
		String newString="";
		for(int i=s.length()-1;i>=0;i--){
			newString+=s.charAt(i);
		}
		if(newString.equalsIgnoreCase(s))
			return true;
		else			
			return false;
	}

	
	public static String longestconsecutiveString(String s) {
		 //s="bcabc";
		int maxConsLength = 0;
		String longestconsString = null;
		int length = s.length();
	 
		// check all possible sub strings
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int len = j+1 - i;
				String curr = s.substring(i, j + 1);
				if (isRepeatString(curr)) {
					if (len > maxConsLength) {
						longestconsString = curr;
						maxConsLength = len;
					}
				}
			}
		}
	 
		return longestconsString;
	}
	 
	public static boolean isRepeatString(String s) {
	 
		int strLength=s.length()-1;
		for (int i = 0; i < strLength; i++) {
			int chr=(int)s.charAt(i);
			int chr1=(int)s.charAt(i+1);
			if (chr!= (chr1-1)) {
				return false;
			}
		}
	 
		return true;
	}


}
