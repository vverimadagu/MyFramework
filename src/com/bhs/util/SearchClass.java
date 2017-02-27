package com.bhs.util;

 public final class SearchClass {
	
	public  final void SearchClass(){}
//synchronized int k;
	final public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int searchArray[]={1,2,5,9,10,12,18,27};
		
		int low=0;   //-low-;
		int high=searchArray.length-1;
		int mid=0;
		int searchNum=9;
		
		while(high>=low){
			
			mid=low+(high-low)/2;
			if(searchNum==searchArray[mid])
			{
				System.out.println("Number is present: "+searchNum);
				
				return;
			}
				
			 if(searchNum>searchArray[mid]){
				low=mid+1;
			}
			
			 else
				 high=mid-1;
			
		}

	}

}
