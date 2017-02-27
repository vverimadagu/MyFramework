package com.bhs.scripts.carecenterportal;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.scripts.employeeportal.EP_CBAndInHomeReservations_Tests;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;
import com.bhs.util.INITIALIZE.TestStatus;

public class CCP_EditReservation_Tests  extends INITIALIZE{

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	CCP_BusinessComponents CCbusinessComponents = new CCP_BusinessComponents();
	String employer=null; 




	/* TFS ID:23156
	 * @author: Sanjeev Singh
	 * @CreationDate: 01/04/2014
	 *  CSC BUCA - Edit Reservation - Care Recipients: The application should enable Care Center user to edit the Care Recipient information while on a Reservation, from the Reservations tab and clicking 'edit' next to a care session
	 */


	@Test @Parameters("client") 
	public void CCP_EditCareRecipients(String employer) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		/*businessComponents.EP_LaunchBrowser();

		String resNum = businessComponents.EP_InHomeReservation(5, getTestData("TD_Dateofreservation2"));

		System.out.println("Reservation Number "  + resNum);

		// Logout from 'Employee Portal'
		businessComponents.logout();*/

		this.employer = employer;
		
		
		/*// Create a center based reservation
		EP_CBAndInHomeReservations_Tests cbih = new EP_CBAndInHomeReservations_Tests();
		cbih.EP_SignUpAndCBReservationTest(employer);

		// Test data for selection the reservation date from the data picker
		String resDate=getTestData("TD_Dateofreservation");
		String dateArray[]=resDate.split("/");
		String date=dateArray[1];
		if(date.startsWith("0")){
			date=date.substring(1);
		}
		
		int month=Integer.parseInt(dateArray[0]);
		String objArray[]={getTestObject("CCP_OLP_01"),getTestObject("CCP_OLP_02")};
		String dataArray[]={date};*/
		
		// Read test data for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser
		CCP_BusinessComponents.CCP_LaunchBrowser();
		
		// Login CareCenter Portal
		CCP_BusinessComponents.CCP_Login();*/
		
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		CCP_BusinessComponents.EP_CB_Reservation(employer,getTestData("CCP_TD_CPDate"));
		
		Utility.logout();
		
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		
		// Change the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));

		Thread.sleep(1000);
		//Verify Home Page is displayed properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		Thread.sleep(3000);
		
		//Click on Reservations to check the different Classrooms
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		
		Thread.sleep(3000);
		
		Thread.sleep(2000);
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.clickEditReservation(lastName);

		/*// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);*/
		

		/*// Click Calendar and select the date of reservation
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
		Thread.sleep(4000);
		Utility.selectDate(month,objArray,dataArray);
		
		// Change the center to the center reserved
		CCP_BusinessComponents.changeCenter("CenterName", getTestData("CCP_TD_CenterName"));*/
		
	/*	// Get the index of the class with Pending Reservations count > 0
		int i =0;
		List<WebElement> lwe = COMMON_METHODS.driver.findElements(By.xpath("//div[starts-with(@id,'RoomClass')]"));
		for(WebElement we: lwe){
			 i = i + 1;
			 String roomClassText = we.getText();
			 String[] arrroomClassText = roomClassText.split("Pending: ");
			 String[] arrPending = arrroomClassText[1].split("Wait Listed:");
			 if(Integer.parseInt(arrPending[0].trim())>0){
				 break;
			 }
		 }
		
		// Click the arrow
		COMMON_METHODS.driver.findElement(By.xpath("//div[starts-with(@id,'RoomClass')][" + i + "]//a")).click();
				
		// Get the index of Edit link of the reservation created above
		int j = 0;
		List<WebElement> lweReservations = COMMON_METHODS.driver.findElements(By.xpath("//tr[@id='row']"));
		for(WebElement weRow: lweReservations){
			 j = j + 1;
			 String rowText = weRow.getText();
			 if(rowText.contains(ReadwritDataFromProps.props.getProperty("client2.cbudc2.lastName") + ", " + ReadwritDataFromProps.props.getProperty("client2.cbudc2.firstName"))){
				 break;
			 }
		 }
		
		// Click 'Edit' link
		COMMON_METHODS.driver.findElement(By.xpath("//td[@id='data_" + (j-1) + "']/..//span/a")).click();*/
		
		/*// Click Edit link of the confirmed reservation	
		COMMON_METHODS.driver.findElement(By.xpath("//td[contains(@id,'data_')]/span/select/option[@selected='selected']/../../../../td/span/a[text()='Edit'][1]")).click();*/

		// Verify Edit Reservation page is loaded
		COMMON_METHODS.verifyElementDisplayed(getTestObject("KCCP_OL_18"));
		
		//Click on the Care Recipient's name link under the Care Recipients section
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL45"));
		
		//	The application should take the user to the Edit Profile Information page
		//REPORTER.LogEvent(TestStatus.PASS, "Authentication Window", "Authentication Window Appears, so cann't move forward", "");
		
		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/* TFS ID:22965
	 * @author: Sanjeev Singh
	 * @CreationDate: 01/04/2014
	 *  CSC BUCA - CSC BUCA: Edit Reservation - Verify if Authorized Contact can change Location of Care for a reservation
	 */


	@Test @Parameters("client")
	public void CCP_ACChangeLocation(String employer) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		businessComponents.EP_CenterBasedReservation(employer, getTestData("CCP_TD_CPDate"));
		
		// Adding the 2nd location 
		  
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click the 'Add' link next to the Locations section
		COMMON_METHODS.clickElement(getTestObject("OL_124"));

		// Add location
		//Create locations
		String data[]={"Office","60604","103 Fox Road","Flag st","Chicago","Cook",
						"United States",null};
		businessComponents.EP_AddLocation(data);
		
		Utility.logout();
	

		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Login to Emp Portal
		String signInArray1[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client1.cbudc1.userName"), getTestData("TD_PWD"),signInArray1);
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"), "Reservations");
		
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");		
		Thread.sleep(8000);
		
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		
		// click CHANGE CENTER LOCATION DETAILS
		COMMON_METHODS.clickElement(getTestObject("KMA_82"));
		
		Thread.sleep(3000);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//To close the 'No Centers Found pop up'
		try{
		boolean isElementPresent = COMMON_METHODS.driver.findElements(
				By.xpath("//div[10]/div/a/span")).size() != 0;
		System.out.println("isElementPresent=="+isElementPresent);
		if(isElementPresent){
			COMMON_METHODS.clickElement(getTestObject("RESV_17"));
			Thread.sleep(5000);
			REPORTER.LogEvent(TestStatus.PASS, "No Centers Found pop up is closed", "No Centers Found pop up is closed", "");
		}
		}
		catch(Exception e){
			REPORTER.catchException(e, "Error in No Centers Found pop up ");
		}
		
		
		// Click 'Back' button
		COMMON_METHODS.clickElement(getTestObject("RS_01"));
		
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		Thread.sleep(3000);
		
		//Select Distance from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
		
		//Select any Location from 'Location' drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_89"), "Office", "byVisibleText");
		
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		Thread.sleep(18000);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//To close the 'No Centers Found pop up'
		try{
		boolean isElementPresent = COMMON_METHODS.driver.findElements(
				By.xpath("//div[10]/div/a/span")).size() != 0;
		System.out.println("isElementPresent=="+isElementPresent);
		if(isElementPresent){
			COMMON_METHODS.clickElement(getTestObject("RESV_17"));
			Thread.sleep(5000);
			REPORTER.LogEvent(TestStatus.PASS, "No Centers Found pop up is closed", "No Centers Found pop up is closed", "");
		}
		}
		catch(Exception e){
			REPORTER.catchException(e, "Error in No Centers Found pop up ");
		}
		
		Utility.logout();
		//Log to reports
		//COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	/**
	 * Test Case #22714: 
	 * CSC BUCA - Edit Reservation - Step 3: Verify if Care Center user can update information for a Center Based Reservation
	 *  
	 * 
	 */


	@Test @Parameters("client")
	public void CCP_EditReservation_UpdateInfoforCBRes(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
	
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		businessComponents.EP_CenterBasedReservation(employer, getTestData("CCP_TD_CPDate"));
		
		// Adding the 2nd location 
		  
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click the 'Add' link next to the Locations section
		COMMON_METHODS.clickElement(getTestObject("OL_124"));

		Utility.logout();
		
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
      
		//logout
		Utility.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}
	
	public void selectDate() throws Exception {
		 
		//Test data for selecting the date from the data picker
			String resDate=getTestData("CCP_TD_CPDate");
			String dateArray[]=resDate.split("/");
			String date=dateArray[1];
			if(date.startsWith("0")){
				
				date=date.substring(1);
			}
			
			int month=Integer.parseInt(dateArray[0]);
			
			String objArray[]={getTestObject("CCP_OLP_01"),getTestObject("CCP_OLP_02")};
			String dataArray[]={date};
			
			// Click Calendar and select the date of reservation
			COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
			Thread.sleep(4000);
			Utility.selectDate(month,objArray,dataArray);
			
	 }
	 
	 public void editReservation() throws IOException, Exception {
		// Click on the 'Expand All' link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));

		// Click on the 'Edit' button for a pending reservation
//		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_19"));
		
		// Click Edit link of the confirmed reservation	
		COMMON_METHODS.driver.findElement(By.xpath("//td[contains(@id,'data_')]/span/select/option[@selected='selected']/../../../../td/span/a[text()='Edit'][1]")).click();

		// Verify Edit Reservation page is loaded
		COMMON_METHODS.verifyElementDisplayed(getTestObject("KCCP_OL_18"));
		
	 }
	 
	 /**
		 * 
		 */
	 	private void expandClassRoom(String classType,String lastName) {
		
		if (classType.contains("Infant")) {
			COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][1]/div/ul/li[3]/a")).click();
		}else if (classType.contains("Toddler")) {
			COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][2]/div/ul/li[3]/a")).click();
		}else if (classType.contains("Preschool")) {
			COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][3]/div/ul/li[3]/a")).click();
		}else if (classType.contains("School Age")) {
			COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][4]/div/ul/li[3]/a")).click();
		} if (classType.contains("Young Explorers")) {
			COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][1]/div/ul/li[3]/a")).click();
		}else if (classType.contains("New Adventures")) {
			COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][2]/div/ul/li[3]/a")).click();
		}else if (classType.contains("Early Learners")) {
			COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][3]/div/ul/li[3]/a")).click();
		}else if (classType.contains("Discovery Zone ")) {
			COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][4]/div/ul/li[3]/a")).click();
		}

		//click edit link
		//COMMON_METHODS.driver.findElement(By.xpath("//span[contains(text(),'" + lastName +"')]/../../td[11]/span")).click();
		
	 }
	 	
	 	public void selectReservationDate(String reservationDate) throws Exception {

			// Test data for selecting the date from the data picker
			//String resDate=getTestData("CCP_TD_CPDate");
			
			String resDate=reservationDate;
			
			String dateArray[]=resDate.split("/");
			String date=dateArray[1];
			if(date.startsWith("0")){

				date=date.substring(1);
			}

			int month=Integer.parseInt(dateArray[0]);

			String objArray[]={getTestObject("CCP_OLP_01"),getTestObject("CCP_OLP_02")};
			String dataArray[]={date};

			// Click Calendar and select the date of reservation
			COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
			Thread.sleep(4000);
			Utility.selectDate(month,objArray,dataArray);

		}
	
	
}
