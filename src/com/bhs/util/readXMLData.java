package com.bhs.util;


import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder; 


public class readXMLData {
	
	public static void main(String[] arg)
	{
		
		String nodeValue = getXMLData("Client10","TD_UserID3");
		
		System.out.println("TD_ClientName : " + nodeValue );
		 
	}
	
	
	public static String getXMLData(String clientName,String columnName) {
		
		String nodeValue="";
		
		try {
			 
			 File fXmlFile = new File("E://xml//bh2.xml");
			 
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
			 
				//optional, but recommended
				//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
				doc.getDocumentElement().normalize();
			 
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
				NodeList nList = doc.getElementsByTagName("" + clientName  + "");
			 
				System.out.println("----------------------------");
			 
				for (int temp = 0; temp < nList.getLength(); temp++) {
			 
					Node nNode = nList.item(temp);
			 
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
			 
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
						Element eElement = (Element) nNode;
						
						System.out.println("Client No : " + eElement.getElementsByTagName("ClientNo").item(0).getTextContent());
						/*System.out.println("TD_ClientName : " + eElement.getElementsByTagName("TD_ClientName").item(0).getTextContent());
						System.out.println("TD_EmployeeClientName : " + eElement.getElementsByTagName("TD_EmployeeClientName").item(0).getTextContent());*/
						
						/*System.out.println(("" + columnName + ""));
						
						System.out.println(columnName + ":" + eElement.getElementsByTagName("" + columnName + "").item(0).getTextContent());
						*/
						nodeValue =  eElement.getElementsByTagName("" + columnName + "").item(0).getTextContent(); 
						
					}
				}

	    } catch (Exception e) {
	    		e.printStackTrace();
	    }
		
		return nodeValue;
		
	}

}
