package com.bhs.util;

public class PerfectNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		for(int i=1;i<=1000;i++){
			perFectNo(i);
			
		}
	}
	
	static void perFectNo(int num){
		int perfect=0;
		for(int i=1;i<num;i++){
			
			if(num%i==0){
				perfect+=i;
			}
		}
		
		if(perfect==num)
			System.out.println("perFectNo== "+perfect);
		
	}

}
