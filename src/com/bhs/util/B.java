package com.bhs.util;

public class B extends A{

	B(){
	super(1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a=new B();
		a.A2();
		//a.A1();

	}
	
	//@Override
	public void A2() {
		System.out.println("in B A2");
	}
	
	private void A1() {
		// TODO Auto-generated method stub

	}

}
