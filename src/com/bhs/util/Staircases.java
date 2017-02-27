package com.bhs.util;

public class Staircases {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num=6;
		int num1=num;
		 for(int i=0;i<num;i++){
	         
			 int spaces=num1-1;
			 for(int k=0;k<spaces;k++){
				 System.out.print(" ");
			 }
			 num1--;
	         for(int j=0;j<=i;j++){
	             System.out.print("#");
	         }
	            System.out.println("");                    
	     }
	}

}
