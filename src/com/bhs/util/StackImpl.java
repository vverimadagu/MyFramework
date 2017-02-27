package com.bhs.util;

public class StackImpl {

	int size;
	int sa[];
	int top;
	StackImpl(int size){
		this.size=size;
		sa=new int[size];
		top=-1;
		
	}
	
	void push(int pValue) throws Exception{
		if(overFlow()){
			
			throw new Exception("Stack Is OverFlow");
			
		}
		else{
			
			sa[++top]=pValue;
			System.out.println("Push Value: "+sa[top]);
		}
	}
	
	
	void pop() throws Exception{
		if(underFlow()){
			
			throw new Exception("Stack Is UnderFlow");
			
		}
		else{
			
			System.out.println("Pop Value: "+sa[top--]);
		}
	}
	
	
	void peek(){
		System.out.println("Top in the stack: "+sa[top]);
	}
	/*private void StackImpl() {
		System.out.println("in StackImpl");

	}*/
	
	

	private boolean overFlow() {
		return top==size-1;
	}

	
	private boolean underFlow() {
		return top==-1;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StackImpl stackImpl=new StackImpl(4);
		
		stackImpl.push(1);
		stackImpl.push(2);
		stackImpl.push(3);
		stackImpl.push(4);
		stackImpl.pop();
		stackImpl.peek();
		 
	}

}
