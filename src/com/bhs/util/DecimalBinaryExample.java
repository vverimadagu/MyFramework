package com.bhs.util;

class DecimalBinaryExample{
	 
	  public void convertBinary(int num){
	     int binary[] = new int[40];
	     int index = 0;
	     while(num > 0){
	       binary[index++] = num%2;
	       num = num/2;
	     }
	     for(int i = index-1;i >= 0;i--){
	       System.out.println("11111111" +binary[i]);
	     }
	  }
	 
	  public static void main(String a[]){
	     DecimalBinaryExample obj = new DecimalBinaryExample();
	     System.out.println("Binary representation of 124: ");
	    // obj.convertBinary(5);
	     //String bs=Integer.toBinaryString(15);
	     int num=5;
	     int rem=0;
	     while(num>1){
	    	 rem=num%2;
	    	 num=num/2;
	    	 System.out.print(rem);
	     }
	     System.out.print(num);
	     //System.out.println(bs);
	     /*System.out.println("\nBinary representation of 45: ");
	     obj.convertBinary(45);
	     System.out.println("\nBinary representation of 999: ");
	     obj.convertBinary(999);
	*/  }
	}

