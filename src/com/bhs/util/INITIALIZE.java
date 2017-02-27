package com.bhs.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook; 

/**
 * @author AA24358
 *
 */

public class INITIALIZE {

	public enum updateValue {bName, Env, tEndTime, execTime, execStatus, totalSteps, failedStepNo, ScreenPrint };
	public enum TestStatus {PASS, FAIL, WARNING, MSG, INFO };

	private static Map<String, String> TDMap = new HashMap<String, String>();
	private static Map<String, String> TOMap = new HashMap<String, String>();

	public static int stepCount = 1;
	
	
	static boolean flag = false;
	public static String nStatus = null;
	public static Document document =null;
	
	static{
		
		try{
			File fXmlFile = new File(System.getProperty("user.dir") + "\\TestData\\EP_TestData.xml");
			 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.parse(fXmlFile);
			document.getDocumentElement().normalize();
	}
		catch(Exception e){
			e.printStackTrace();
		}
}

	/**
	 * PURPOSE: To Get DataTable Path
	 * INPUT(s): Data Table Name
	 * @param vTablename
	 * @return Complete path Of The DataTable
	 */
	public static String getDataTablePath(String vTablename) {

		String vDTP = System.getProperty("user.dir") + "\\TestData\\DATA_"
				+ vTablename + ".xls";
		System.out.println("Complete path Of The DataTable is - " + vDTP);
		
		return vDTP;

	}
		
	/**
	 * PURPOSE: Read Data From Excel 
	 * RETURN VALUE: Test Data 
	 * INPUT(s): excel File Name and Sheet Name
	 * AUTHOR: Tarun K (txkx) 
	 * CREATION DATE: Nov 01 2011 
	 * Modified By: Vijeth
	 * Thamme gowda (vthamme)
	 * @param excelDataSheet
	 * @param sheetName
	 */
	public static void readTestData(String excelDataSheet, String sheetName) {
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(excelDataSheet));
		} catch (Exception e) {
			String vException = e.getMessage().toString();
						
			REPORTER.LogEvent(TestStatus.FAIL, "Check Data File "
					+ excelDataSheet, (vException.substring(
							(excelDataSheet.length()) + 2, (vException.length()) - 1))
							.toUpperCase(),"");
			
		}
		Sheet sheet = null;
		sheet = workbook.getSheet(sheetName);

		for (int i = 0; i < sheet.getRows(); i++) {
			Cell[] cell = sheet.getRow(i);
			if (cell[0].getContents() != null) {
				TDMap.put(cell[0].getContents().toString(), cell[1]
						.getContents().toString());
			}
		}

		sheet = null;
		workbook = null;
		//System.out.println("TDMap=="+TDMap);
	}

	public static String getTestData(String KeyName) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String sTemp = null;
		try {
			sTemp = TDMap.get(KeyName).toString();
			//System.out.println("getTestData value is - ***************" +sTemp);

		} catch (Exception e) {
						
			REPORTER.LogEvent(TestStatus.FAIL, "Read Test Data From Hash Map",
							"Check - '***KEYNAME***' Used As Parameter For Method - '"+methodName+"' In Main Script","");
			
		}
		methodName=null;return (sTemp);

	}

	public static void readTestObject(String excelDataSheet, String SheetName)
			throws Exception {
		
		//excelDataSheet=C:\BH_Automation_WorkSpace\BH_Automation\TestData\DATA_EP.xls

		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(excelDataSheet));
		} catch (Exception e) {
		
			String vException = e.getMessage().toString();
		
			REPORTER.LogEvent(TestStatus.FAIL, "Check Data File "
					+ excelDataSheet, (vException.substring(
							(excelDataSheet.length()) + 2, (vException.length()) - 1))
							.toUpperCase(),"");
		
		}
		Sheet sheet = workbook.getSheet(SheetName);

		for (int i = 0; i < sheet.getRows(); i++)

		{
			Cell[] cell = sheet.getRow(i);
			if (!(cell[0].getContents().toString() == null)) {
				TOMap.put(cell[0].getContents().toString(), cell[0]
						.getContents().toString()
						+ ","
						+ cell[1].getContents().toString()
						+ ","
						+ cell[2].getContents().toString()
						+ ","
						+ cell[3].getContents().toString());
				
			}
		}

		sheet = null;
		workbook = null;
	}

	public static String getTestObject(String KeyName) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String sTemp = null;
		try {
			sTemp = TOMap.get(KeyName).toString();
			System.out.println("getTestObject value is - ***************" +sTemp);
		} catch (Exception e) {
			// System.out.println("-------------- " + e.getLocalizedMessage());
			
			REPORTER.LogEvent(TestStatus.FAIL, "Read Test Object Value From Hash Map",
					"Check - '*KeyName*' Used As Parameter For Method - '"+methodName+"' In Main Script","");
			
		}
		methodName=null;
		return (sTemp);
	}
	
	/**
	 * PURPOSE: Reset The Hash map - Clear The hashmap after use 
	 * RETURN VALUE:
	 * Test Data INPUT(s): excel File Name and Sheet Name 
	 * AUTHOR: Tarun K (txkx)
	 * CREATION DATE: Nov 01 2011
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void clearHashMap() {
		TOMap = new HashMap();
		TDMap = new HashMap();

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void clearTOHashMap() {
		TOMap = new HashMap();
		

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void clearTDHashMap() {
		
		TDMap = new HashMap();

	}
	/**
	 * PURPOSE: To get the Browser name 
	 * RETURN VALUE: Browser name 
	 * INPUT(s): Driver 
	 * AUTHOR: Ravi Naidu (rnaidu) 
	 * CREATION DATE: Jan 25 2012
	 * @param BrowserName
	 * @return
	 */
	public String getBrowserName(String BrowserName) {

		if (BrowserName.contains("ie")) {
			BrowserName = "INTERNET EXPLORER";
			
		} else if (BrowserName.contains("firefox")) {
			BrowserName = "FIREFOX";
		} else if (BrowserName.contains("chrome")) {
			BrowserName = "CHROME";
		} else {
			BrowserName = "HTMLUNIT DRIVER";
		}

		return BrowserName;
	}

	
	/**
	 * PURPOSE: To return month values 
	 * RETURN VALUE: Month INPUT(s): Noting
	 * AUTHOR: Ravi Naidu (rnaidu) 
	 * CREATION DATE: Feb 01 2011
	 * @author
	 * @return
	 */
	public String getMonth() {

		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };

		Calendar cal = Calendar.getInstance();
		String month = months[cal.get(Calendar.MONTH)];
		// System.out.println("Month name: " + month);
		return month;
	}

	/**
	 * PURPOSE: To Convert Milliseconds to HHMMSS format RETURN VALUE: HHMMSS format
	 * INPUT(s): Milli Seconds AUTHOR: rbtLong Garden Grove, California
	 * CREATION DATE: Feb 14 2012
	 * @param diffSeconds
	 * @return
	 * @see 
	 * @since 
	 * @author 
	 **/
	protected static String formatIntoHHMMSS(long diffSeconds) {
		// Convert Milli Seconds into Seconds
		diffSeconds = diffSeconds / 1000;

		long hours = diffSeconds / 3600;
		long remainder = diffSeconds % 3600;
		long minutes = remainder / 60;
		long seconds = remainder % 60;

		return ((hours < 10 ? "0" : "") + hours + ":"
				+ (minutes < 10 ? "0" : "") + minutes + ":"
				+ (seconds < 10 ? "0" : "") + seconds);

	}
	
	/**
	 * PURPOSE: To get dynamic data
	 * @author vverimadugu
	 * @param String userData
	 * @param int absoluteRow
	 * @return String
	 * 	 **/
	public static String createDyanamicUserData() {
    	
		DateFormat dateFormat = new SimpleDateFormat("MMddyyHHmmss");
	    Date date = new Date();
	    String uniqueValue= dateFormat.format(date);

	    //ReadwritDataFromProps.writeDyanamicData("client"+absoluteRow+userData,uniqueValue);
	    return uniqueValue;
		}

	//*****************************************************End Of File*****************************************************
		
	/**
	 * PURPOSE: To get Test data from XML file
	 * @parm String clientName
	 * @parm String columnName
	 * AUTHOR: vverimadu
	 * CREATION DATE: April 15 2012
	 * @return String
	 **/
	public static String getXMLData(String clientName, String columnName) {

		String nodeValue = "";

		try {

			
			NodeList nList = document.getElementsByTagName(clientName);
			Element nNode = (Element) nList.item(0);
			nodeValue=nNode.getElementsByTagName(columnName).item(0).getTextContent();	
			System.out.println("nodeValue :"+
					nodeValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nodeValue;

	}

}
