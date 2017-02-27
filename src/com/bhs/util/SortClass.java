package com.bhs.util;

public class SortClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int selectionSort[] = { 4, 9, 2, 3 };

		int length = selectionSort.length;

		for (int i = 0; i < length - 1; i++) {

			for (int j = i; j < length - 1; j++) {

				if (selectionSort[i] > selectionSort[j + 1]) {
					int temp = selectionSort[i];
					selectionSort[i] = selectionSort[j + 1];
					selectionSort[j + 1] = temp;
				}
			}

		}

		System.out.print("selectionSort: ");

		for (int i = 0; i < length; i++) {
			System.out.print(selectionSort[i]);
		}

		int bubbleSort[] = { 4, 9, 2, 3, 5, 1 };

		int bubbleLenth = bubbleSort.length;

		for (int i = 0; i < bubbleLenth - 1; i++) {

			for (int j = 0; j < bubbleLenth-1 ; j++) {
				if (bubbleSort[j] > bubbleSort[j + 1]) {

					int temp = bubbleSort[j];
					bubbleSort[j] = bubbleSort[j + 1];
					bubbleSort[j + 1] = temp;
				}
			}
		}
		System.out.println("\n");

		System.out.print("bubbleSort: ");

		for (int i = 0; i < bubbleLenth; i++) {
			System.out.print(bubbleSort[i]);
		}
		
		
		
		//Quick Sort
		
		System.out.println();
		System.out.println("---------------------Quick Sort Result---------------------------");
		quickSort(0,quickSort.length-1);
		for(int i:quickSort){
			System.out.println(i);
			
		}
	}
	
	static int quickSort[] = { 4, 9, 2, 3, 5, 1 };
	static void quickSort( int startIndex, int endIndex) {

		int left = startIndex;
		int right = endIndex;
		int pivot = quickSort[startIndex + (endIndex - startIndex) / 2];

		// Divide into two arrays i.e Partition logic

		while (left <= right) {

			while (quickSort[left] < pivot) {
				left++;
			}

			while (quickSort[right] > pivot) {
				right--;
			}
			
			if(left<=right){
				
				int temp=quickSort[left];
				quickSort[left]=quickSort[right];
				quickSort[right]=temp;
				left++;
				right--;
			}
		}
		
		// calls quickSort() method recursively

		if(startIndex<right)
            quickSort(startIndex, right);
		
		if(left<endIndex)
            quickSort(left, endIndex);

	}

}
