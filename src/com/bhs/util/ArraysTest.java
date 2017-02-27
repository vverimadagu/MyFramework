package com.bhs.util;

import java.util.HashMap;
import java.util.HashSet;

public class ArraysTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = { 1, 2, 3, 4 };

		for (int i = 0; i < a.length; i++) {
			int mul = 1;
			for (int j = 0; j < a.length; j++) {
				if (j != i)
					mul *= a[j];

			}
			System.out.println(mul);
			// System.out.println("Multiply==="+multiply(i,a.length-1));

		}

		// duplicatesInArray();
		 //duplicatesInArray1();
		// missingNumber();
		//largestAndSmallest();
		//findPairOfIntegersInArraywhoseSumIsGivenNumber();
		//repeatedNumbersInAnArrayContainsMultipleDuplicate();
		//UniqueNumberWhichIsNotRepeatedTwice();
		//unionOfTwoSortedArrayInJava();
		//intersectionOfTwoSortedArrayInJava();
		//arrayContainsADuplicateNumber();
		reverseAnArray();
		//findAllElementsAppearMoreThanNKtimes();
		//commonEelementsInThreeSortedArray();
		//firstRrepeatingElementInAnArrayOfIntegers();
		//firstNonRepeatingElementInArray();
		//topTwoNumbersFromAnIntegerArray();
		findSmallest();
		//subArrayWithSumEqualToZero();
	}

	static void duplicatesInArray() {

		int array[] = { 1, 6, 9, 1, 8, 9 };

		HashSet<Integer> hs = new HashSet<Integer>();
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {

			if (!hs.add(array[i]))
				hm.put(array[i], hm.get(array[i]) + 1);
			else
				hm.put(array[i], 1);
		}

		Object[] keyArray = hm.keySet().toArray();

		for (Object key : keyArray)
			System.out.println(key + ": " + hm.get(key));

	}

	static void duplicatesInArray1() {

		//int[] arr = { 2, 3, 6, 6, 8, 9, 10, 10, 10, 6, 12 };
		int[] arr = { 1,2,1};
		int end = arr.length;

		for (int i = 0; i < end; i++) {
			for (int j = i + 1; j < end; j++) {
				if (arr[i] == arr[j]) {
					int shiftLeft = j;
					for (int k = j + 1; k < end; k++, shiftLeft++) {
						arr[shiftLeft] = arr[k];
					}
					end--;
					j--;
				}
			}
		}

		int[] whitelist = new int[end];
		for (int i = 0; i < end; i++) {
			whitelist[i] = arr[i];

			System.out.println(whitelist[i]);
		}

	}

	/**
	 * calculate sum of all numbers in the array and compare with expected sum,
	 * the difference would be the missing number
	 */

	static void missingNumber() {

		int arr[] = { 1, 2, 3, 5 };
		int ts = 0;
		for (int i = 1; i <= 5; i++) {
			ts += i;
		}
		int arrSum = 0;
		for (int i = 0; i < arr.length; i++) {
			arrSum += arr[i];
		}

		System.out.println("MIssing Number: " + (ts - arrSum));

	}

	public static void largestAndSmallest() {

		int[] numbers = { -20, 34, 21, -87, 92, 2147483647 };

		int largest = numbers[0];
		int smallest = numbers[0];

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > largest) {
				largest = numbers[i];
			} else if (numbers[i] < smallest) {
				smallest = numbers[i];
			}
		}

		System.out.println("Largest number in array is : " + largest);
		System.out.println("Smallest number in array is : " + smallest);

	}
	
	public static void findPairOfIntegersInArraywhoseSumIsGivenNumber() {
		int sum = 9;
		int arr[] = { 5, 6, 3, 4, 11 };
		// int pairArr[]=new int[2];
		for (int i = 0; i < arr.length; i++) {

			for (int j = i + 1; j < arr.length; j++) {

				if (arr[i] + arr[j] == sum) {

					System.out.println("(" + arr[i] + "," + arr[j] + ")");
				}

			}

		}

	}
	
	public static void repeatedNumbersInAnArrayContainsMultipleDuplicate() {

		int arr[] = { 1, 5, 6, 5, 1 };
		int end = arr.length;
		for (int i = 0; i < end; i++) {
			int count = 1;
			for (int j = i + 1; j < end; j++) {

				int leftShift = j;
				if (arr[i] == arr[j]) {
					for (int k = j + 1; k < end; leftShift++, k++) {
						arr[leftShift] = arr[k];

					}
					count++;
					j--;
					end--;
				}
			}

			System.out.println(arr[i] + ": " + count);
		}
		for (int i = 0; i < end; i++) {

			System.out.println(arr[i]);
		}

	}
	
	static void UniqueNumberWhichIsNotRepeatedTwice() {

		int arr[] = { 1, 6, 2, 2, 3, 4, 4, 5, 5 };
		int end = arr.length;
		for (int i = 0; i < end; i++) {
			int count = 1;
			for (int j = i + 1; j < end; j++) {

				int leftShift = j;
				if (arr[i] == arr[j]) {
					for (int k = j + 1; k < end; leftShift++, k++) {
						arr[leftShift] = arr[k];

					}
					count++;
					j--;
					end--;
				}
			}

			if (count == 1)
				System.out.println("Not Repeated numbers: " + arr[i]);
		}
		for (int i = 0; i < end; i++) {

			System.out.println(arr[i]);
		}

	}
	
	
	static void unionOfTwoSortedArrayInJava(){
		
		int arr1[]={21, 22,34,35,41};
		int arr2[]={5,11,21,34,45,61};
		
		int arr1Length=arr1.length;
		int arr2Length=arr2.length;
		int x = 0,y=0;
		while(x<arr1Length && y<arr2Length){
			
			if(arr1[x]<arr2[y]){
				
				System.out.println(arr1[x]);
				x++;
			}
			
			else if(arr1[x]>arr2[y]){
				System.out.println(arr2[y]);
				y++;
			}
			
			else{
				System.out.println(arr1[x]);
				x++;y++;
			}
		}
		
		while(x<arr1Length){
			System.out.println(arr1[x]);
		x++;
		}
		
		while(y<arr2Length){
			System.out.println(arr2[y]);
		y++;
		}
	}
	
	static void intersectionOfTwoSortedArrayInJava(){
		
		int arr1[]={21, 22,34,35,41};
		int arr2[]={5,11,21,34,45,61};
		
		int arr1Length=arr1.length;
		int arr2Length=arr2.length;
		int x = 0,y=0;
		
		while(x<arr1Length && y<arr2Length){
			
			if(arr1[x]<arr2[y]){
				x++;
			}
			
			else if(arr1[x]>arr2[y]){
				y++;
			}
			
			else{
				System.out.println(arr1[x]);
				x++;y++;
			}
		}
	}
	
	
	/**How to check if array contains a duplicate number? (answer)
	 *
	 */
	
	static void arrayContainsADuplicateNumber() {

		int[] arr1 = { 1, 2, 4, 2, 1 };

		int end = arr1.length;

		for (int i = 0; i < end; i++) {
			int count = 1;
			for (int j = i + 1; j < end; j++) {

				if (arr1[i] == arr1[j]) {

					int leftShift = j;

					for (int k = j + 1; k < end;leftShift++, k++) {

						arr1[leftShift] = arr1[k];
					}

					end--;
					j--;
					count++;
				}
			}

			if (count > 1)
				System.out.println("Duplicates: " + arr1[i]);
		}
		
	}
	
	/*
	 * 28. How to reverse array in place in Java? (solution)

	 */
	
	static void reverseAnArray() {

		int arr[] = { 1, 2,3,4,2,1,9,5};

		int j = arr.length - 1;

		for (int i = 0; i < j; i++,j--) {
			
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				//j--;
		}	
		for(int i:arr)
			System.out.println(i);
		
	}
	
	/*
	 * Given an array of of size n and a number k, find all elements that appear more than n/k times? (solution)
	   Another tough array based coding questions from Interviews. You are given an array of size n, 
	   find all elements in array that appear more than n/k times. For example, if the input arrays is {3, 1, 2, 2, 1, 2, 3, 3}
	   and k is 4, then the output should be [2, 3]. Note that size of array is 8 (or n = 8), so we need to find all elements that
	   appear more than 2 (or 8/4) times. There are two elements that appear more than two times, 2 and 3.

	 */
	
	static void findAllElementsAppearMoreThanNKtimes(){
		
		int arr[]={3, 1, 2, 2, 1, 2, 3, 3};
		int kTimes=4;
		int end=arr.length;
		int nkTimes =end/kTimes;
		
		for(int i=0;i<end;i++){
			int count=1;
			for(int j=i+1;j<end;j++){
				
				if(arr[i]==arr[j]){
					
					int leftShift=j;
					
					for(int k=j+1;k<end;leftShift++,k++){
						
						arr[leftShift]=arr[k];
					}
					
					end--;
					j--;
					count++;
				}
			}
			
			if(count>nkTimes)
				System.out.println("MoreThan NTimes: "+nkTimes +": "+arr[i]);
		}
	}
	
	/*
	 *  Now we are coming on territory of tough array questions. Given three arrays sorted in non-decreasing order, 
	    print all common elements in these arrays.
		Examples:
		input1 = {1, 5, 10, 20, 40, 80}
		input2 = {6, 7, 20, 80, 100}
		input3 = {3, 4, 15, 20, 30, 70, 80, 120}
		Output: 20, 80

		*/

	static void commonEelementsInThreeSortedArray(){
		
		int []input1 = {1, 5, 10, 20, 40, 80};
		int []input2 = {6, 7, 20, 80, 100};
		int []input3 = {3, 4, 15, 20, 30, 70, 80, 120};
		
		int x=0;int y=0;
		
		while(x<input1.length && y<input2.length){
			
			if(input1[x]<input2[y]){
				x++;
			}
			
			else if(input1[x]>input2[y]){
				y++;
			}
			
			else{
				//System.out.println(input1[x]);
				int z=input1[x];
				for(int i=0;i<input3.length;i++){
					
					if(z==input3[i])
						System.out.println(z);
				}
				x++;
				y++;
				
				
			}
		}
	}
	
	/*
	 * How find the first repeating element in an array of integers? (solution)
	   Given an array of integers, find the first repeating element in it. We need to find the element that occurs more than once and whose index of the first occurrence is smallest.
		
		Examples:
		
		Input:  input [] = {10, 5, 3, 4, 3, 5, 6}
		Output: 5 [5 is the first element that repeats]
*/
	static void firstRrepeatingElementInAnArrayOfIntegers(){
		
		int input [] = {10, 5, 3, 4, 3, 5, 6};
		int end=input.length;
		for(int i=0;i<end;i++){
			int count=1;
			for(int j=i+1;j<end;j++){
				
				if(input[i]==input[j]){
					
					int leftShift=j;
					
					for(int k=j+1;k<end;leftShift++,k++){
						input[leftShift]=input[k];
					}
					
					end--;
					j--;
					count++;
					
				}
			}
			
			if(count>1){
				System.out.println("firstRrepeatingElementInAnArrayOfIntegers: "+input[i]);
				break;
			}
		}
	}
	
	/**
	 * How to find first non-repeating element in array of integers? (solution)
	 */
	
	static void firstNonRepeatingElementInArray() {
		int input[] = { 10, 5, 3, 4, 3, 5, 6 };
		int end = input.length;

		for (int i = 0; i < end; i++) {
			int count = 1;
			for (int j = i + 1; j < end; j++) {

				if (input[i] == input[j]) {
					int leftShift = j;
					for (int k = j + 1; k < end; k++, leftShift++) {
						input[leftShift] = input[k];
					}
					end--;
					j--;
					count++;
				}
			}
			
			if(count==1){
				System.out.println("firstNonRepeatingElementInArray: "+input[i]);
				break;
			}
		}

	}
	
	/**
	 * How to find top two numbers from an integer array? (solution)
	 */
	
	static void topTwoNumbersFromAnIntegerArray(){
		
		int input[] = {5,4,3,2,1};//{ 4, 9, 5, 8 };
		
		int longest=0;
		int secondLongest=0;
		
		for(int i=0;i<input.length;i++){
			
			if(input[i]>longest){
				
				secondLongest=longest;
				longest=input[i];
			}
			else if(input[i]>secondLongest){
				secondLongest=input[i];
			}
			
			
		}
		
		System.out.println("longest In Array: "+longest);
		System.out.println("Second longest In Array: "+secondLongest);
	}
	
	/*
	 * Java program to find the smallest positive value that cannot be
 	   represented as sum of subsets of a given sorted array
	 */
	
	static void findSmallest() {
		int res = 1; // Initialize result
		int arr[] = { 1, 2, 3, 4 };
		// Traverse the array and increment 'res' if arr[i] is
		// smaller than or equal to 'res'.
		for (int i = 0; i < arr.length && arr[i] <= res; i++)
			res = res + arr[i];

		System.out.println("smallest Postive Integer: " + res);
	}
	
	
	/**
	 * How to find if there is a sub array with sum equal to zero? (solution)
	   There is whole set of array related questions which are based upon sub-array or only selective
	    elements of array e.g. from some range, this is one of such problem. Here you are given an 
	    array of positive and negative numbers, find if there is a sub-array with 0 sum.
	    Examples:
		Input: {4, 2, -3, 1, 6}
		Output: true 
		There is a sub-array with zero sum from index 1 to 3.
	*/
	
	
	static void subArrayWithSumEqualToZero(){
		
		int input[]= {4, 2, -1, 1, 6};
		
		int res=1;
		for(int i=0;i<input.length;i++ ){
			int sum=input[i];
			for(int j=i+1;j<input.length;j++){
				
				sum+= input[j];
				if(sum==0){
					System.out.println("SubArrayHasZero: "+i);
					return ;
			}
			
			
			}
		}

	}
	
	
}