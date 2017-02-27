package com.bhs.util;

public class MissingNumber {

	//How to find missing number in integer array of 1 to 100? 
	public static void main(String[] args) {
	
    int[] missArray={1,3,5,6,10};
    
    for(int i=0;i<missArray.length-1;i++){
    	
    	if(missArray[i]!=missArray[i+1]-1)
    		System.out.println(missArray[i+1]-1);
    	
    }
	}

}
