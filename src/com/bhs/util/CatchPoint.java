package com.bhs.util;

public class CatchPoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sumOfNumbers(10);
		
		int hn=7;
		int nh=hn;
		for(int i=0;;i++){
			
			nh= happyNumer(nh);
			if(nh==1){
				System.out.println("Happy Number: "+hn);
				break;
			}
			
				
		}
		
		pangrams();
	}
	
	/**
	  	Your program should read lines from standard input. Each line contains a positive integer.
		Output:
		For each line of input, print to standard output the sum of the numbers from 1 through n, 
		disregarding those divisible by 5 and 7. Print out each result on a new line.
		
		Test 1
		Test Input Download Test Input10
		Expected Output Download Test Output33
		Test 2
		Test Input Download Test Input7
		Expected Output Download Test Output16

	 */
	
	static void sumOfNumbers(int num){
		
		int sum=0;
		
		for(int i=1;i<=num;i++){
			
			if(!(i%5==0 || i%7==0))
				sum+=i;
		}
		
		System.out.println("SUM====="+sum);
	}
	int num1=7;
	
	static int happyNumer(int num){
		int sum=0;
		while(num>0){
			
		sum+=(num%10) * (num%10);
		num=num/10;
		}
		System.out.println("Hhappy: "+sum);
		return sum;
	}
	
	/**
	 * The sentence "A quick brown fox jumps over the lazy dog" contains every
	 *  single letter in the English alphabet. Such sentences are called pangrams. 
	 *  You are to write a program which takes a sentence and outputs all the letters 
	 *  it is missing which prevent it from being a pangram. You should ignore the case
	 *   of the letters in the input sentence, and your output should be all lowercase letters 
	 *   in alphabetical order. You should also ignore all non US-ASCII characters. In case the 
	 *   input sentence is already a pangram, output the string NULL.
	 */
	
	
	static void pangrams(){
	String pangram="A quick brown fox jumps over the lazy dog";
	//pangram.toLowerCase();
	boolean isPangram=true;
	String[] alpArray={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	for(int i=0;i<alpArray.length;i++){
		
		if(!pangram.contains(alpArray[i])){
			
			System.out.println("Missing Letter: "+alpArray[i]);
			isPangram=false;
		}
		
	}
	
	for (char ch = 'A'; ch <= 'Z'; ch++) {
		System.out.println( ch + 32);
		System.out.println(((char) (ch + 32)));
		System.out.println( pangram.indexOf((char) (ch + 32)));
		if (pangram.indexOf(ch) < 0
				&& pangram.indexOf((char) (ch + 32)) < 0) {
			System.out.println("Missing Letter: "+ch);
		}
	}

	
	System.out.println("isPangram: "+isPangram);
	
	
	}

}
