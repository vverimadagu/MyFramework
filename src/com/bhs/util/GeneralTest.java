package com.bhs.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GeneralTest {
	
	
	public static Properties props; 

	public static Document document =null;
	static{
	
		try{
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\bhs\\config\\testdata.properties");
		props=new Properties();
		props.load(fis);
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		document=db.parse(new File(System.getProperty("user.dir")+"\\TestData\\TestData.xml"));
		document.getDocumentElement().normalize();
		
		System.out.println("mod ===="+9%10);
	}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void writeDatatoProps(String key, String value) {
		FileOutputStream fos=null;
		try {
			 fos = new FileOutputStream(System
					.getProperty("user.dir")
					+ "\\src\\com\\bhs\\config\\testdata.properties");
			props.setProperty("key", "value");
			props.store(fos, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
			if(fos!=null){
				try{
				fos.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public static void getDataFromXml(String datanode, String datatag1) {
		try {
			//NodeList nlist=(NodeList)document.getElementsByTagName("datanode");
			Element element=(Element)document.getElementsByTagName("datanode").item(0);
			String nodeValue=element.getElementsByTagName("datatag1").item(0).getTextContent();
			System.out.println("nodeValue==="+nodeValue);
			
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	GeneralTest(){
		//super();
		//this(10);
		
		System.out.print("first statement in constructor");
		//this(10);
		
	}
	GeneralTest(int i){}
	GeneralTest(int i,int j){}
	
	@SuppressWarnings("unchecked")
	HashMap hm=new HashMap();
	
	static ArrayList<String> al=new ArrayList<String>();
	static HashSet<String> hs=new HashSet<String>();
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Iterator ie=hs.iterator();
		
		filewriteRead();
		
		//Swapping with out third variable
		writeDatatoProps("","");
		//get data from the pros
		System.out.println(props.getProperty("key"));
		
		getDataFromXml("","");
		int a=2;
		int b=5;
		
		a=b-a;//5-2=3
		b=b-a;//5-3=2
		a=a+b;//2+3=5
		System.out.println("a="+a+" b="+b);
		 int oneremten=1%10;
		System.out.println("oneremten"+oneremten);
		//palndrome num
		int pal=151;
		int newpal=0,rem=0,div=0;
		
		do{
		rem=pal%10;
		System.out.println("qut=="+rem);
		div=pal/10;
		pal=div;
		System.out.println("div=="+div);
		newpal=newpal*10+rem;
		
		System.out.println("newpal=="+newpal);
		}
		while(div>0);
		
		//Fibannci series 
		int fo=0,f1=1,fib=0;
		System.out.print(fo);
		
		for(int i=0;i<3;i++){
			fib=fo+f1;
			fo=f1;
			f1=fib;
			System.out.print(f1);
		}
		System.out.println();

	//Pyramid structure
	
	for(int i=0;i<5;i++){
		
		for(int j=0;j<=i;j++){
			System.out.print("*");
			
			}
		System.out.println();
		}
	
	
//Reverse Pyramid structure
	
	for(int i=4;i>=0;i--){
		
		for(int j=0;j<=i;j++){
			System.out.print("*");
			
			}
		System.out.println();
		}
	
	
	//FizzBuzz problem : Write a Java program that prints the numbers from 1 to 50.
	//But for multiples of three print "Fizz" instead of the number and for the multiples of five print "Buzz". 
	//For numbers which are multiples of both three and five print "FizzBuzz"
	
	for(int i=1;i<=50;i++){
		
		if(i%3==0 && i%5==0)
		System.out.println(i+" FizzBuzz");
		else if(i%3==0)
			System.out.println(i+" Fizz");
		else if(i%5==0)
			System.out.println(i+" Buzz");
		
		
			System.out.println(i);
			
	}
	
	
	/* for(int i = 1; i <= 50; i++) {
         if(i % (3*5) == 0) System.out.println("FizzBuzz");
         else if(i % 5 == 0) System.out.println("Buzz");
         else if(i % 3 == 0) System.out.println("Fizz");
         else System.out.println(i);
     } */
	
	
	 //Example of Armstrong number is 153 as 153= 1+ 125+27 which 1^3+5^3+3^3.
	int armstrong=0;
	int rem1=0;
	for(int i=153;i<=154;i++){
		
	String s1=Integer.toString(i);
	
	int j=i;
	/*for(int j=0;j<=2;j++){
		char ch=s1.charAt(j);
		int s2=Integer.parseInt(""+ch);
		//System.out.println("s2 ==="+i);
		armstrong+=(s2*s2*s2);
		
	}*/
	while(j>0){
		
		rem1=j%10;
		armstrong+=(rem1*rem1*rem1);
		j=j/10;
		System.out.println("new num ==="+j);
	}
	
	if(armstrong==i)
		System.out.println("Armstrong number is ==="+i);
	armstrong=0;
	//in.toString(i);
	}
	
	String ts="Bright Horizons at Cook County/Chicago CDC - Stabilizat ...";
	
	if(ts.contains("...")){
		String arr[]=ts.split("-");
		System.out.println(arr);
		System.out.println("arr[0]="+arr[0]);
		
	}
	
	//print cube of a number 123
	
	int cube=123;
	
	
	//Print factoral 5
	
	
	
			
	int fact1=factorial(5);
	System.out.println("factorial of "+fact1);
	myRecursiveMethod(10);
	}
	static int fact=1;
	public static int factorial(int num){
		
		
		/*while(num>0){
			fact=fact*num;
			factorial(--num);	
			//num--;
		}
		System.out.println("factorial of "+num +"="+fact);
		return fact;*/
		if(num==1)
			return 1;
		else{
			//System.out.println("factorial of "+num*factorial(--num));
			return num*factorial(--num);
		}
		
		
	}
	
	public static int myRecursiveMethod (int aVariable)  
	{  
	  System.out.println(aVariable);  
	  aVariable--;  
	  if (aVariable == 0)  
	    return 0;  
	  return myRecursiveMethod(aVariable);  
	} 
	
	public static void  filewriteRead () throws IOException  
	{  
		
		//Create File
		File fw=new File("D:\\Reports");
		if(!fw.exists()){
			//fw.delete();
			fw.mkdir();
		}
		BufferedWriter bfw=new BufferedWriter(new FileWriter(new File("D:\\Reports\\textReports.txt")));
		bfw.write(" value1");
		bfw.newLine();
		bfw.write(" value2");
		bfw.write(" value3");
		bfw.newLine();
		bfw.write(" value4");
		bfw.close();
		BufferedReader bfr=new BufferedReader(new FileReader(new File("D:\\Reports\\textReports.txt")));
		while(bfr.read()!=-1){
			System.out.println(bfr.readLine());
		}
		bfr.close();
		
		BufferedWriter bfw1=new BufferedWriter(new FileWriter(new File("D:\\Reports\\textReports.txt"),true));
		bfw1.write(" value5");
		bfw1.newLine();
		bfw1.write(" value6");
		bfw1.close();
	}
	
}
