package com.bhs.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;

public class Utility extends INITIALIZE {

	/* ***************************************************************************************
	 * PURPOSE: Selecting date from calendar RETURN VALUE: void AUTHOR:
	 * vverimadugu CREATION DATE: Mar 21 2014
	 * 
	 * *********************************************************************
	 * *****************
	 */
	public static void selectDate(int month, String[] objArray,
			String[] dataArray) throws IOException {

		String monthStr = getMonth(month);

		try {
			for (int i = 1; i <= 12; i++) {

				if (!monthStr.equalsIgnoreCase(COMMON_METHODS
						.getText(objArray[0]))) {
					COMMON_METHODS.clickElement(objArray[1]);
					Thread.sleep(2000);

				} else {
					// Click reservation Day
					BH_SetUp_TearDown.driver
							.findElement(
									By
											.xpath("//a[@class='ui-state-default' and text()='"
													+ dataArray[0] + "']"))
							.click();
					Thread.sleep(5000);
					REPORTER.LogEvent(TestStatus.PASS, "Reservation date "+objArray[0]+" selected", "Reservation date selected ", "");
					break;
				}
			}
		}

		catch (Exception e) {
			
			REPORTER.catchException(e, "Unable to select date ");
		}

	}

	/* ***************************************************************************************
	 * PURPOSE: Getting the date calendar RETURN VALUE: void AUTHOR: vverimadugu
	 * CREATION DATE: Mar 21 2014
	 * 
	 * *********************************************************************
	 * *****************
	 */
	public static String getMonth(int month) throws IOException {

		String monthStr = null;

		try {
			switch (month) {

			case 1: {
				monthStr = "January";
				break;
			}
			case 2: {
				monthStr = "February";
				break;
			}
			case 3: {
				monthStr = "March";
				break;
			}
			case 4: {
				monthStr = "April";
				break;
			}
			case 5: {
				monthStr = "May";
				break;
			}
			case 6: {
				monthStr = "June";
				break;
			}
			case 7: {
				monthStr = "July";
				break;
			}
			case 8: {
				monthStr = "August";
				break;
			}
			case 9: {
				monthStr = "September";
				break;
			}
			case 10: {
				monthStr = "October";
				break;
			}
			case 11: {
				monthStr = "November";
				break;
			}

			case 12: {
				monthStr = "December";
				break;
			}
			}

		} catch (Exception e) {

			REPORTER.catchException(e, "Unable to select date ");
		}

		return monthStr;

	}

	/* ***************************************************************************************
	 * PURPOSE: Selecting the reservation 
	 * RETURN VALUE: void 
	 * AUTHOR: vverimadugu
	 * CREATION DATE: Mar 28 2014
	 * 
	 * *********************************************************************
	 * *****************
	 */
	public static void selectAvailResInPP(String[] objIdentifier)
			throws IOException {

		boolean isPresent = false;
		try {

			do {

				boolean isElementPresent = COMMON_METHODS.driver.findElements(
						By.xpath("//li[@class='resnumber' and text()='"
								+ objIdentifier[0] + "']")).size() != 0;
				if (!isElementPresent) {
					COMMON_METHODS.clickElement(objIdentifier[1]/*getTestObject("PPR_01")*/);
					Thread.sleep(9000);
					isPresent = true;

				} else {
					isPresent = false;
				}

			} while (isPresent);
			 
		/*	COMMON_METHODS.driver.findElement(
					By.xpath("//li[@class='resnumber' and text()='"
							+ objIdentifier[0] + "']/../li[4]/a")).click();*/
			//Thread.sleep(4000);
		}

		catch (Exception e) {

			REPORTER.catchException(e, "Unable to select Reservation ");
		}

	}

	
	/* ***************************************************************************************
	 * PURPOSE: navigating to last page in the pagination list grid 
	 * RETURN VALUE: int
	 *  AUTHOR: vverimadugu
	 * CREATION DATE: April 04 2014
	 * 
	 * *********************************************************************
	 * *****************
	 */
	public static int goToLastPageInGridList(String[] objIndentifier)
			throws IOException {
		int pageNo=0;

		try {

			List<WebElement> pagnationArray = COMMON_METHODS.driver
					.findElements(By.tagName(objIndentifier[0]));
			for (int i = 0; i < pagnationArray.size(); i++) {
				String ulId = pagnationArray.get(i).getAttribute(objIndentifier[1]);
				if (ulId.equalsIgnoreCase("pagination-clean")) {
					List<WebElement> pagnationArray1 = pagnationArray.get(i)
							.findElements(By.tagName(objIndentifier[2]));
					pageNo= pagnationArray1.size(); 
					break;
				}
				
			}

		}

		catch (Exception e) {

			REPORTER.catchException(e, "Unable to get pagination count");
		}
		
		return pageNo; 

	}
	
	
	/**
	 * This method is used to launch the url
	 * @param String url
	 * @throws Exception
	 */
	public static void launchBrowser(String url) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Launch Broswer with Qcentral URL
		COMMON_METHODS.openBrowser(url);
		Thread.sleep(5000);

		//Verify Login Page displayed
		/*if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Login Page Displayed", "Login Page Displayed", "");
		}*/

				
	}
	
	/**
	 * This method will login in to BUCA portals
	 * @param String userName
	 * @param String password
	 * @param String[] objArray
	 * String objArray[]={getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
	 */
	
	public static void loginToBUCA(String userName,String password,String[] objArray) throws IOException, Exception {
	
	//Enter User Name
	//COMMON_METHODS.editAField(getTestObject("OL_1"), getTestData("TD_UserID"));
	COMMON_METHODS.editAField(objArray[0], userName);

	//Enter Password
	COMMON_METHODS.editPasswordField(objArray[1], password);
	
	
	//Click Sign In
	COMMON_METHODS.clickElement(objArray[2]);
	
	//Verify Home Page displayed
	if(BH_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Back-Up Care Advantage")){
		REPORTER.LogEvent(TestStatus.PASS, "Verify Home Page Displayed", "Home Page Displayed", "");
	}
}
	
	/**
	 * This method clicks on the link based on the parameters 
	 * @return void
	 * @throws IOException
	 * @throws Exception  
	 * */
	public static void clickLink(String obj) throws IOException, Exception
	{
		//Click on employee name link present under 'Employee Profile' section
		COMMON_METHODS.clickElement(obj);
		Thread.sleep(5000);
	}
	
	
	/**
	 * This method will logout from the portal
	 * @throws IOException
	 * @throws Exception
	 */
	public static void logout() throws IOException, Exception
	{
		//Click Logout
		COMMON_METHODS.clickElement(getTestObject("OL_9"));
	}
	
	
	/**
	 * 
	 * @param workingDays
	 * @return
	 */
	public static String reservationDays(int workingDays) {
		
		Calendar cal = Calendar.getInstance();  
		
		String date;
		
		// Add one day  
		cal.add(Calendar.DATE,workingDays);
		
		// Get the day of the week that the calendar is set to  
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		
		//checking weekday is sunday  
		if (weekday == 1) {
			cal.add(Calendar.DATE,2);
		}
		
		//checking weekday is saturday
		if (weekday == 7) {
			cal.add(Calendar.DATE,2);
		}
		
		date = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/"
				+ cal.get(Calendar.YEAR);
		
		return date;

	}
	
	/*
	 * @param workingDays
	 * 
	 * @return
	 */
	public static String brokenImages() {
		DefaultHttpClient hc=new DefaultHttpClient();
		HttpResponse hp=null;
		
		try {
			List<WebElement> images = COMMON_METHODS.driver.findElements(By
					.tagName("img"));
			
			for (WebElement bi : images) {
				hp=hc.execute(new HttpGet(bi.getAttribute("src")));
				if(hp.getStatusLine().getStatusCode()!=200){
					System.out.println("Broken Images==="+bi.getAttribute("src"));
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/*
	 * @param workingDays
	 * 
	 * @return
	 */
	public static String brokenLinks() {
		URL url=null;
		HttpURLConnection urlCon=null;
	
		
		try {
			List <WebElement> linkElements=COMMON_METHODS.driver.findElements(By.tagName("a"));
			
			for(WebElement linkUrl:linkElements){
				url=new URL(linkUrl.getAttribute("href"));
				urlCon=(HttpURLConnection)url.openConnection();
				urlCon.setRequestMethod("Get");
				urlCon.connect();
				if(urlCon.getResponseCode()!=404)
				System.out.println("Not Broken Links==="+linkUrl.getAttribute("href"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
