package com.bhs.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InterviewsClass {

	private static char[] ch;
	static String rev = "";
	
	public void InterviewsClass11(){
		//binarySearch(12);
		
	}
	
public   InterviewsClass(int i){
		
	rev="666";
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		fileRelateStuff();
		int ss[]={4,9,2,3};
		doSelectionSort(ss);
		linearSearch(20);
		binarySearch(4);
	}

	/**
	 * @throws IOException
	 */
	private static void fileRelateStuff() throws IOException {

		/*System.out.println("user.dir===" + System.getProperty("user.dir"));
		// File Reader
		FileReader fr = new FileReader(new File(System.getProperty("user.dir")
				+ "\\textFile1.txt"));
		System.out.println("fr====" + fr);
		System.out.println("fr====" + fr.read());
*/
		// palndrome number
		int pal = 121;
		int newPal = 0;

		int qut = 0;
		int div = 0;

		do {
			qut = pal % 10;
			div = pal / 10;
			pal = div;
			newPal = newPal * 10 + qut;
		} while (div > 0);

		System.out.println("newpal=" + newPal);

		// String Reverse

		String s1 = "Reverse Str";
		int len = s1.length();
		System.out.println("len=" + len);

		for (int i = len - 1; i >= 0; i--) {
			System.out.println("String Rev=" + s1.charAt(i));
			rev += s1.charAt(i);
			System.out.print("String Rev=" + rev);

		}
		System.out.print("String Rev=" + rev);

		int arr[] = { 4, 9, 2, 3 };
		int length = arr.length;
		for (int i = 0; i < length-1; i++) {

			for (int j = i + 1; j < length; j++) {

				int temp = 0;
				if (arr[i] > arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}

		}
		System.out.println("\n");
		for (int i = 0; i < length; i++) {

			System.out.println(arr[i]);
		}

		int sort2[] = { 4, 9, 6, 25, 12, 78, 1, 23 };

		int length1 = sort2.length;
		for (int i = 0; i < length1; i++) {

			for (int j = 0; j < length1 - 1; j++) {

				int temp = 0;
				if (sort2[j] > sort2[j + 1]) {
					temp = sort2[j];
					sort2[j] = sort2[j + 1];
					sort2[j + 1] = temp;
				}
			}

		}
		System.out.println("\n");
		for (int i = 0; i < length1; i++) {

			System.out.println(sort2[i]);
		}

		int ss[] = { 4, 9, 2, 3 };

		int length9 = ss.length;
		int min = 0;
		int k = 0;
		int temp = 0;
		for (int i = 0; i < length9 - 1; i++) {
			min = i;
			for (int m = i + 1; m < length9; m++) {
				if (ss[m] < ss[min]) {
					min = m;
				}
				}
				temp = ss[min];
				ss[min] = ss[i];
				ss[i] = temp;
				System.out.println("selectionsort===" + ss[0] + ","
						+ ss[1] + "," + ss[2] + "," + ss[3]);

			}

		

		System.out.println("\n");
		for (int i = 0; i < length9; i++) {

			System.out.println(ss[i]);
		}
	}

	public static int[] doSelectionSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[index]>arr[j] )
					index = j;

			int smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
			System.out.println("selectionsort===" + arr[0] + ","
					+ arr[1] + "," + arr[2] + "," + arr[3]);

		}
		
		for (int i = 0; i < arr.length; i++) {

			System.out.println(arr[i]);
		}

		return arr;
	}
	
	public static void linearSearch(int ls) {
		int lsearch[]={4,9,2,3};
		boolean seachExist=false;
		for(int i=0;i<lsearch.length;i++){
			
			if(ls==lsearch[i]){
				System.out.println("search found:"+ ls);
				seachExist=true;
				break;
			}
			
		}
		if(!seachExist)
			System.out.println("search not found:"+ ls);

	}
	
	
	public static void binarySearch(int bs) {
		int bsearch[]={2,3,4,9};
		boolean seachExist=false;
		int low=0;
		int high=bsearch.length-1;
		int mid=0;
		while(low<=high){
			mid=low+(high-low)/2;
			
			if(bs<bsearch[mid])
				high=mid-1;
			else if(bs>bsearch[mid])
				low=mid+1;
			else{
				System.out.println("search found:"+ bs);
				seachExist=true;
				break;
			}
		}
				if(!seachExist)
			System.out.println("search not found:"+ bs);

	}
	
	public static void quickSort(int bs) {
		int bsearch[]={2,3,4,9};
		/*		public class Quicksort  {
			  private int[] numbers;
			  private int number;

		  public void sort(int[] values) {
			    // check for empty or null array
			    if (values ==null || values.length==0){
			      return;
			    }
			    this.numbers = values;
			    number = values.length;
			    quicksort(0, number - 1);
			  }

			  private void quicksort(int low, int high) {
			    int i = low, j = high;
			    // Get the pivot element from the middle of the list
			    int pivot = numbers[low + (high-low)/2];

			    // Divide into two lists
			    while (i <= j) {
			      // If the current value from the left list is smaller then the pivot
			      // element then get the next element from the left list
			      while (numbers[i] < pivot) {
			        i++;
			      }
			      // If the current value from the right list is larger then the pivot
			      // element then get the next element from the right list
			      while (numbers[j] > pivot) {
			        j--;
			      }

			      // If we have found a values in the left list which is larger then
			      // the pivot element and if we have found a value in the right list
			      // whiuch is smaller then the pivot element then we exchange the
			      // values.
			      // As we are done we can increase i and j
			      if (i <= j) {
			        exchange(i, j);
			        i++;
			        j--;
			      }
			    }
			    // Recursion
			    if (low < j)
			      quicksort(low, j);
			    if (i < high)
			      quicksort(i, high);
			  }

			  private void exchange(int i, int j) {
			    int temp = numbers[i];
			    numbers[i] = numbers[j];
			    numbers[j] = temp;
			  }
			} 
	}*/

		}



}
