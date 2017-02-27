package com.bhs.scripts.employeeportal;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_ReservationManagement_Tests extends INITIALIZE{

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	CCP_BusinessComponents CCbusinessComponents = new CCP_BusinessComponents();
	
	

	//Test Case #8992
	//BUCA - Reservations - ensure user is able to delete a reservation draft
	
	/*@Test()
	public void EP_DeleteDraftReservation(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
			// SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
		readTestObject(getDataTablePath("EP"), "TO_EP");
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
			
		//Verify user moved to Reservations page.
		businessComponents.EP_verifyText("Reservations", "Reservations");
		
		//Click on "Delete" link on a Draft Reservation and select YES
	//		NEED to be DONE
		
		//Click on "Delete" link on a Draft Reservation and select YES
		//NEED to be DONE	
		
		// Logout from Application
		businessComponents.logout();
			
	}*/
	
	
	//Test Case #8792		rolled up with "BUCA - Cancel IH Reservation And Confirm" TFS ID : BLANK
	// under EP_Home_Tests class
	//BUCA - Reservation Review - ensure 'Cancel Entire Reservation' button functions correctly
/*	@Test()
	public void EP_CancelEntireReservation(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch browser
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		
		
		//Verify user moved to Reservations page.
		businessComponents.EP_verifyText(getTestObject("OL_10"), "Reservations");

				// Click on "View" link on a reservation (Either Confrim or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		
		Thread.sleep(10000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));
		
		//click the Cancel Reservation button
		COMMON_METHODS.clickElement(getTestObject("Resrv_Cancel"));
		COMMON_METHODS.switchToPopup();
		Thread.sleep(1000);
		COMMON_METHODS.driver.findElement(By.xpath("//*[@id='CancelResFormText']/input[4]")).click();
		
		// Logout from Application	
		businessComponents.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
			
	}*/
	
	
	
/*	//Test Case #8414
	//BUCA - Reservation Review - ensure page is loaded correclty (UX doc)
	@Test()
	public void EP_EnsurePageLoad(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch browser
		businessComponents.EP_LaunchBrowser();

		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		
		
		//Verify user moved to Reservations page.
		businessComponents.EP_verifyText(getTestObject("OL_10"), "Reservations");

		// Click on "View" link on a reservation (Either Confrim or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		Thread.sleep(4000);
		
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));
		
		//Verify user moved to Reservations Details Page.
		String str = COMMON_METHODS.getText(getTestObject("Help_pg"));
		System.out.println(str);
		if(str.contains("RESERVATION:")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservations Details Page is displayed.", "Reservations Details Page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Reservations Details Page is displayed.", "Reservations Details Page Not displayed.", "");
		}
		
		// Logout from Application	
		businessComponents.logout();
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
			
	}*/
	
	//Test Case #14188
	//BUCA - Reservation Review - the 'Edit Reservation' button on a reservation in DRAFT state should take user back into the wizard
	@Test @Parameters("client")
	public void EP_EditReservation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		
		// Launch Employee Portal
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Create an In-home reservation
		businessComponents.EP_InHomeReservation(employer, getTestData("TD_Dateofreservation2"));
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		
		
		//Verify user moved to Reservations page.
		businessComponents.EP_verifyText(getTestObject("OL_10"), "Reservations");

		// Click on "View" link on a reservation (Either Confirm or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		Thread.sleep(10000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));
		
		// Click the Edit Reservation button
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));
		
//		// Logout from Application	
//		Utility.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
			
	}
	
	//Test Case #13546
	//BUCA - Reservation Review - ensure 'Reservation Contact' links function as desired
	@Test @Parameters("client")
	public void EP_ReservationContact(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

//		// Read test data for based on client 1
//		readTestData(getDataTablePath("EP"), "TD_EP2");
//
//		// Launch Employee Portal
//		Utility.launchBrowser(getTestData("TD_EP_URL"));
//
//		// Create an In-home reservation
//		businessComponents.EP_InHomeReservation(2, getTestData("TD_Dateofreservation2"));
		
		this.loginEPPortal(employer); 
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		
		
		//Verify user moved to Reservations page.
		businessComponents.EP_verifyText(getTestObject("OL_10"), "Reservations");

		// Click on "View" link on a reservation (Either Confirm or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		Thread.sleep(10000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));
		
		// Ensuring Reservation contact links function as desired
		COMMON_METHODS.clickElement(getTestObject("Resrv_contact"));
		
		// Verifying Next Page i.e Care Profile Info page
		//Verify the Address 
		COMMON_METHODS.VerifyTextPresent(getTestObject("CareProfile_Info"),"Edit Reservation:");
		
//		// Logout from Application	
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
			
	}

	//Test Case #13769: Test Case #8414 BUCA - Reservation Review - ensure 'Care Providers' links function as desired
	@Test @Parameters("client")
	public void EP_CareProvidersLinksValidation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

//		// Read test data for based on client 1
//		readTestData(getDataTablePath("EP"), "TD_EP2");
//
//		// Launch Employee Portal
//		Utility.launchBrowser(getTestData("TD_EP_URL"));
//
//		// Create an In-home reservation
//		businessComponents.EP_InHomeReservation(2, getTestData("TD_Dateofreservation2"));
		
		this.loginEPPortal(employer); 
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		
		
		//Verify the text present on the next page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"), "Reservations");
		
		// Click on "View" link on a reservation (Either Confirm or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		Thread.sleep(10000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));
		
		//Ensure the system takes you to the reservation review i.e Reservations Details Page
		String str = COMMON_METHODS.getText(getTestObject("Help_pg"));
		System.out.println(str);
		if(str.contains("RESERVATION:")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservations Details Page is displayed.", "Reservations Details Page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Reservations Details Page is displayed.", "Reservations Details Page Not displayed.", "");
		}
		
		//Ensure Care Providers links function as desired -- Click the link and closes the pop ups & moves to next link
		COMMON_METHODS.clickElement(getTestObject("Care_Providers1"));
		Thread.sleep(5000);
		COMMON_METHODS.clickElement(getTestObject("Close_Btn"));
		
		//Ensure Care Providers2 links function as desired -- Click the link and closes the pop ups & moves to next link
		COMMON_METHODS.clickElement(getTestObject("Care_Providers2"));
		Thread.sleep(5000);
		COMMON_METHODS.clickElement(getTestObject("Close_Btn"));
		
		//Ensure Care Providers3 links function as desired -- Click the link and closes the pop ups & moves to next link
		COMMON_METHODS.clickElement(getTestObject("Care_Providers3"));
		Thread.sleep(5000);
		COMMON_METHODS.clickElement(getTestObject("Close_Btn"));
		
//		// Logout from Application	
//		Utility.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	} 

	// Test Case #BLANK
	// BUCA - Cancel IH Reservation And Confirm
	// Sanjeev 28/03/2014
	@Test @Parameters("client")
	public void EP_CancelReservation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		
		// Launch Employee Portal
		Utility.launchBrowser(getTestData("TD_EP_URL"));

//		// Create an In-home reservation
//		businessComponents.EP_InHomeReservation(2, getTestData("TD_Dateofreservation2"));
		this.loginEPPortal(employer); 

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		

		//Verify user moved to Reservations page.
		businessComponents.EP_verifyText("Reservations", "Reservations");

		// Click on "View" link on a reservation (Either Confirm or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		Thread.sleep(10000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));

		// Click the Cancel Reservation button
		COMMON_METHODS.clickElement(getTestObject("Resrv_Cancel"));

		// Select the reason for cancellation
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_11"), "#1", "byIndex");

		// Click 'Submit' button on 'Cancel Care Session' pop-up
		COMMON_METHODS.clickElement(getTestObject("RESV_13"));
		Thread.sleep(15000);

		// Logout from Application	
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}
	
	/* @tc #11876: BUCA - Reservations - Ensure "What you need for care" link works on a confirmed reservation
	 * @author Sanjeev Singh
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 07/04/2014
	 */
	
	@Test @Parameters("client")
	public void CC_WhatYouNeedForCarelink(String employer) throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
	
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		// Create a Center based reservation
		String strDateofReservation = getTestData("TD_Dateofreservation2");
		businessComponents.EP_CenterBasedReservation(employer, strDateofReservation);
				
		// Logout from Employee Portal
		Utility.logout();
		Thread.sleep(5000);

		
		// Launch CCP
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		// Login to CCP
		String signInArray[]={getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		// Verify Text Up-coming Care Sessions
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");
		
		// Changing the center
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));
		
		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(5000);

		// *******************************************
		// Confirm the reservation
		// *******************************************

//		String resDate=getTestData("TD_CPDate");
//		String strDateofReservation = getTestData("TD_CPDate");
		String dateArray[]=strDateofReservation.split("/");
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
		
		// Click Expand All link
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_3"));
		
		// Get the index of Edit link of the reservation
		int j = 0;
		List<WebElement> lweReservations = COMMON_METHODS.driver.findElements(By.xpath("//tr[@id='row']"));
		for(WebElement weRow: lweReservations){
			j = j + 1;
		}

		// Click 'Edit' link
		COMMON_METHODS.driver.findElement(By.xpath("//td[@id='data_" + (j-1) + "']/..//span/a")).click();
		Thread.sleep(10000);
		
		// Change the reservation status to confirmed
		COMMON_METHODS.checkBox(getTestObject("KCCP_OL_15"),"check");
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_16"));
		COMMON_METHODS.listBoxSelect(getTestObject("KCCP_OL_17"),"Confirmed" , "byVisibleText");
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_18"));
		Thread.sleep(3000);
		
		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(5000);
		
		// *******************************************
		// Add External note
		// *******************************************
		
		// Click Edit Notes link
		COMMON_METHODS.clickElement(getTestObject("CCP_EditNotes"));
		
		// Enter external note
		COMMON_METHODS.editAField(getTestObject("GCCP_OL_09"), getTestData("TD_CenterNote"));
		
		// Click Save Notes button
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_10"));

		// Logout from Care Center Portal
		Utility.logout();
		Thread.sleep(5000);


		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to Emp Portal
		String userName=ReadwritDataFromProps.props.getProperty(employer + ".cbudc" + ".userName");
		String password=getTestData("TD_PWD");
		String signInObjArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInObjArray);
			
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		
	
		// Select Confirmed Reservation
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "Confirmed", "byVisibleText");
		Thread.sleep(5000);
		
		// Click on 'What you need for care' link
		COMMON_METHODS.driver.findElement(By.linkText("What you need for care")).click();
		Thread.sleep(5000);
		
		// Ensure the system takes you to the information needed.
		COMMON_METHODS.verifyElementDisplayed(getTestObject("EP_WhatYouNeedForCareHeader"));
		
		// Logout from Application	
		Utility.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	private void loginEPPortal(String ClientNo) throws IOException, Exception { 
        
        if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){ 
                //Verify Login Page displayed 
                
                //Login to Emp Portal 
                String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")}; 
                Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(ClientNo + ".cbudc" + ".userName"), getTestData("TD_PWD"),signInArray); 
                /*businessComponents.LoginEmployeeportal( 
                                ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"userName"), 
                                getTestData("TD_PWD"));*/ 
        } 
} 
}
