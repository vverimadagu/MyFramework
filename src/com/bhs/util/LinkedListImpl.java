package com.bhs.util;

public class LinkedListImpl {

	public static void main(String[] args) throws Exception {
		LinkedList likList=new LinkedListImpl().new LinkedList();
		likList.insertFirstLink(1, "One");
		likList.insertFirstLink(2, "Two");
		//likList.removeFirst().display();
		//likList.removeFirst().display();
		likList.navigateLinks();
	}
	
	class Link {
		
		Link next;
		
		int empId;
		String empNo;
		
		Link(int empId,String empName){
			this.empId=empId;
			this.empNo=empName;
		}
		
		void display(){
			
			System.out.println("empId: "+empId +"  empNo: "+empNo);
		}
		
		
	}
	
	class LinkedList{
		
		Link firstLink;
		
		LinkedList(){
			firstLink=null;
		}
		
		void insertFirstLink(int empId, String empName){
			
			Link newLink=new Link(empId,empName);
			newLink.next=firstLink;
			firstLink=newLink;
			newLink.display();
		}
		
		boolean isEmpty(){
			
			return firstLink==null;
		}
		
		
		Link removeFirst() throws Exception{
			Link linkRef=firstLink;
			
			if(!isEmpty()){
				
				firstLink=firstLink.next;
			}
			
			else
				throw new Exception("Linked List IsEmpty");
			
			return linkRef;
			
		}
		
		void navigateLinks(){
			
			Link navLink=firstLink;
			while(navLink!=null){
				navLink.display();
				navLink=navLink.next;
				
			}
		}
	}
	

}
