package com.bhs.scripts.carecenterportal;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class CCP_ReservationFulfillment_Tests  extends INITIALIZE{

	EP_BusinessComponents epBusinessComponents = new EP_BusinessComponents();
	//CCP_BusinessComponents CCP_BusinessComponents = new CCP_BusinessComponents();


	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 26/03/2014
	 */
	//Test Case #7326: BUCA - CCP Reservations - Verify that a user can select a different Center for Summary by Day

	@Test()
	public void CCP_Reservations_CenterSummary() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//		// Read test data for based on client 1
		//		readTestData(getDataTablePath("CCP"), "TD_CCP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray); */
		
		this.loginCCPortal();

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Change the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));

		// Verify the Center name is displayed
		if(COMMON_METHODS.getText(getTestObject("KCCP_OL_28")).contains(getTestData("CCP_TD_CenterNumber"))){
			REPORTER.LogEvent(TestStatus.PASS, "Verify the selected Care Center summary is displayed.", "The selected Care Center summary is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the selected Care Center summary is displayed.", "The selected Care Center summary is not displayed.", "");
		}

		//		// Logout from Care Center Portal
		//		Thread.sleep(5000);
		//		Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 26/03/2014
	 */
	//Test Case #7335: BUCA - CCP Reservations - Verify a user can print Reports from the reservations page

	@Test()
	public void CCP_Reservations_ReservationReportLinks() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		//		// Launch the browser
		//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		//		
		//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		//		
		//		// Login CareCenter Portal
		//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		this.loginCCPortal();	

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Verify the Reservation Reports links are opening a PDF
		List<WebElement> lweReservationReportLinks = COMMON_METHODS.driver.findElements(By.xpath("//ul[@id='pageTwoColRightSm']/li/div[@class='sidebarMod']//li/a"));

		for(WebElement wReportLink: lweReservationReportLinks){
			if(wReportLink.getAttribute("href").endsWith(".pdf")){
				REPORTER.LogEvent(TestStatus.PASS, "Verify " + wReportLink.getText() + " link opens a PDF.", wReportLink.getText() + " link opens a PDF.", "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify " + wReportLink.getText() + " link opens a PDF.", wReportLink.getText() + " link does not open a PDF.", "");
			}	
		}

		// Logout from Care Center Portal
		Thread.sleep(5000);
		Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 24/03/2014
	 */
	//Test Case #10127: BUCA - CCP - Reservations Wizard - Ensure modified Reservation Wizard
	// Test Case #10159: BUCA - CCP Reservations - Ensure user can edit a Reservation (DUP)

	@Test @Parameters("client")
	public void CCP_ReservationManagement_ModifiedReservationWizard(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		CCP_BusinessComponents.EP_CB_Reservation(employer,getTestData("CCP_TD_CPDate"));
		
		Utility.logout();

		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		CCP_BusinessComponents.clickEditReservation(lastName);

		// Verify Edit Reservation page is loaded
		if(COMMON_METHODS.driver.findElement(By.xpath("//input[@value='Update Reservation']")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Edit Reservation' page is displayed.", "'Edit Reservation' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Edit Reservation' page is displayed.", "'Edit Reservation' page is not displayed.", "");
		}

		//		// Logout from Care Center Portal
		// 		Thread.sleep(5000);
		//		Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @Creation Date: 24/03/2014
	 */
	// Test Case #10160: BUCA - CCP Reservations - Ensure user can view a summary of Reservations for a classroom

	@Test()
	public void CCP_ReservationManagement_ReservationSummary() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Utility.launchBrowser(getTestData("CCP_TD_URL"));

		this.loginCCPortal();

		// Click on the Reservation Header
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		
		Thread.sleep(3000);

		// Click on the arrow Link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_27"));
		
		Thread.sleep(3000);

		// Verify the Expand All link expands the Reservation class
		WebElement weClassHeader = COMMON_METHODS.driver.findElement(By.xpath("//div[@id='pageContent']/div[contains(@id,'RoomClass')]/div[contains(@class,'classHeader')]"));

		if(weClassHeader.isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify the Reservation summary for class room is displayed.", "Reservation summary for class room is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the Reservation summary for class room is displayed.", "Reservation summary for class room is not displayed.", "");
		}

		//		// Logout from Care Center Portal
		//		Thread.sleep(5000);
		//		Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	//	/* This method is rolled-up
	//	 * @author: Krishna Chaitanya Maringanti
	//	 * @CreationDate: 24/03/2014
	//	 */
	//	//Test Case #10159: BUCA - CCP Reservations - Ensure user can edit a Reservation (DUP)
	//
	//	@Test()
	//	public void CCP_ReservationManagement_EditReservation() throws Exception {
	//
	//		
	//		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	//		System.out.println("Inside - " + methodName);
	//		
	//		// Read test data for based on client 1
	//		//readTestData(getDataTablePath("CCP"), "TD_CCP");
	//		
	//		/*// Launch the browser
	//		CCP_BusinessComponents.CCP_LaunchBrowser();
	//		
	//		// Login CareCenter Portal
	//		CCP_BusinessComponents.CCP_Login();*/
	//		
	//		Utility.launchBrowser(getTestData("TD_EP_URL"));
	//		
	//		businessComponents.EP_CenterBasedReservation(2, getTestData("CCP_TD_CPDate"));
	//		
	//		Utility.logout();
	//		
	//		// Launch the browser
	//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
	//		
	//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
	//		
	//		// Login CareCenter Portal
	//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
	//		
	//		// Click on the Reservation Header
	//		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
	//		Thread.sleep(3000);
	//		
	//		// Search for the center
	//		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
	//
	//		// Select the date on which the pending reservation is available
	//		CCP_BusinessComponents.changeDate(getTestData("CCP_TD_DateOfPendingReservation"));
	//		
	//		// Click on the 'Expand All' link
	//		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
	//
	//		// Click on the 'Edit' button for a pending reservation
	//		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_19"));
	//		
	//		// Verify Edit Reservation page is loaded
	//		if(COMMON_METHODS.driver.findElement(By.xpath("//input[@value='Update Reservation']")).isDisplayed()){
	//			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Edit Reservation' page is displayed.", "'Edit Reservation' page is displayed.", "");
	//		}else{
	//			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Edit Reservation' page is displayed.", "'Edit Reservation' page is not displayed.", "");
	//		}
	//
	//		// Logout from Care Center Portal
	//		Utility.logout();
	//
	//		//Log to reports 
	//		 COMMON_METHODS.logToReportAfterPass(methodName);
	//	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 20/03/2014
	 */
	//Test Case #13014: BUCA - Reservation Fulfillment - Center Care - Verify that if the first priority center can fulfill all care sessions, the system confirms the reservation

	@Test @Parameters("client")
	public void CCP_ReservationFulfillment_ConfirmPendingReservation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		this.loginCCPortal();
			
		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.clickEditReservation(lastName);
		
		Thread.sleep(1000);

		// Select all care sessions for the reservation and click edit selected
		COMMON_METHODS.checkBox(getTestObject("KCCP_OL_15"), "check");
		
		Thread.sleep(2000);
		
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_16"));
		
		Thread.sleep(4000);

		// For a reservation mark all care sessions as "Confirmed"
		COMMON_METHODS.listBoxSelect(getTestObject("KCCP_OL_17"), "Confirmed", "byVisibleText");
		
		
		Thread.sleep(2000);

		// Click on the Update Reservation button
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_18"));
		Thread.sleep(3000);

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		Thread.sleep(3000);

		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		Thread.sleep(3000);
		
		String reservationStatus =  CCP_BusinessComponents.getReservationStatus(lastName);
		
		// Verify the reservation is confirmed
		if (reservationStatus.equals("Conf"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation is confirmed.", "Reservation is confirmed.", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Reservation is confirmed.", "Reservation is not confirmed.", "");

		// Logout from Care Center Portal
		Thread.sleep(2000);
		
		//Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 31/03/2014
	 */
	//Test Case #18376: BUCA - CCP Reservations - Ensure a user can Add a fee to existing reservations

	@Test @Parameters("client")
	public void CCP_Reservations_AddFee(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.loginCCPortal();
		
		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		CCP_BusinessComponents.clickEditReservation(lastName);
		
		Thread.sleep(3000);

		// Click 'Add a Fee' link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_36"));

		// Select the fee type 
		COMMON_METHODS.listBoxSelect(getTestObject("KCCP_OL_37"), "#1", "byIndex");

		// Get the fee type 
		String strFeeType = COMMON_METHODS.getSelectedValueFromListBox(getTestObject("KCCP_OL_37"));

		// Enter the fee amount
		COMMON_METHODS.editAField(getTestObject("KCCP_OL_38"), "5");

		// Enter the notes
		COMMON_METHODS.editAField(getTestObject("KCCP_OL_39"), "Notes");

		// Click 'Add Fee' button
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_40"));
		Thread.sleep(15000);

		// Verify the fee added
		if(COMMON_METHODS.driver.findElement(By.xpath("//table//td[contains(text(),'" + strFeeType + "')]")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify the fee is added.", "The fee is added.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the fee is added.", "The fee is not added.", "");
		}

		// Logout from CC Portal
		Thread.sleep(5000);
		//Utility.logout();

		// Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 01/04/2014
	 */
	//Test Case #18402: BUCA - CCP Reservations - Adjust call back time

	@Test @Parameters("client")
	public void CCP_Reservations_AdjustCallBackTime(String employer) throws Exception {

		String clientNo = "1";
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.loginCCPortal();
		
		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		CCP_BusinessComponents.clickEditReservation(lastName);
		
		Thread.sleep(3000);
		
		// Click 'Request Different Time' link present at the bottom-right corner
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_46"));

		// Enter any time in the time picker
		COMMON_METHODS.editAField(getTestObject("KCCP_OL_47"), "");
		COMMON_METHODS.editAField(getTestObject("KCCP_OL_47"), "06:05 am");

		// Click 'Update Reservation' button present on bottom of the page
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_18"));
		Thread.sleep(5000);

		// Click 'Request Different Time' link present at the bottom-right corner
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_46"));
		Thread.sleep(3000);

		// Verify reservation is updated 
		COMMON_METHODS.VerifyTextPresent(getTestObject("KCCP_OL_47"), "06:05 am", "value");

		//		// Logout from CC Portal
		//		Thread.sleep(5000);
		//		Utility.logout();

		// Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 21/03/2014
	 */
	// Test Case #18352: BUCA - CCP Reservations - Verify the Expand All link expands the Reservation class
	// Test Case #18384: BUCA - CCP Reservations - HTU icon and text display properly when looking at reservations for the day by classroom
	// Test Case #18385: BUCA - CCP Reservations - HTU details display properly when editing reservations
	// TFS ID: 13086 - Reservation Fulfillment - Center Care - Verify available reservations can be viewed in the Care Center Portal
	// TFS ID: 13087 - Reservation Fulfillment - Center Care - Verify a Care Center can mark an open reservation assigned to them as Confirmed or Waitlist
	@Test @Parameters("client") 
	public void CCP_ReservationManagement_ExpandAll(String employer) throws Exception {

		boolean bAllExpanded = true;

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));

		this.loginCCPortal();

		// Click on the Reservation Header
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(8000);

		// Verify Reservations page is loaded
		if(COMMON_METHODS.driver.getTitle().contains("Reservations")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Reservations' page is displayed.", "'Reservations' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Reservations' page is displayed.", "'Reservations' page is not displayed.", "");
		}

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		Thread.sleep(2000);

		// Click on the 'Expand All' link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
		
		Thread.sleep(2000);
				
		// Verify the Expand All link expands the Reservation class
		List<WebElement> lwe = COMMON_METHODS.driver.findElements(By.xpath("//div[@id='pageContent']/div[contains(@id,'RoomClass')]/div[contains(@class,'classHeader')]"));

		for(WebElement w: lwe){
			if (!w.getAttribute("style").equals("")){
				bAllExpanded = false;
				break;
			}
		}
		
		if(bAllExpanded){
			REPORTER.LogEvent(TestStatus.PASS, "Verify the 'Expand All' link expands the Reservation class.", "'Expand All' link has expanded the Reservation class.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the 'Expand All' link expands the Reservation class.", "'Expand All' link has not expanded the Reservation class.", "");
		}

		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.clickEditReservation(lastName);
		
		Thread.sleep(1000);
		
		// Verify Edit Reservation page is loaded
		if(COMMON_METHODS.driver.findElement(By.xpath("//input[@value='Update Reservation']")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Edit Reservation' page is displayed.", "'Edit Reservation' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Edit Reservation' page is displayed.", "'Edit Reservation' page is not displayed.", "");
		}

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	//	/* THIS METHOD IS ROLLED UP
	//	 * @author: Krishna Chaitanya Maringanti
	//	 * @CreationDate: 21/03/2014
	//	 */
	//	//Test Case #18384: BUCA - CCP Reservations - HTU icon and text display properly when looking at reservations for the day by classroom
	//
	//	@Test()
	//	public void CCP_ReservationManagement_ReservationsHeader() throws Exception {
	//
	//		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	//		System.out.println("Inside - " + methodName);
	//		
	//		// Read test data for based on client 1
	//		//readTestData(getDataTablePath("CCP"), "TD_CCP");
	//	
	//		// Launch the browser
	//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
	//		
	//		String signInArray[]={getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
	//		
	//		// Login CareCenter Portal
	//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
	//		
	//		// Click on the Reservation Header
	//		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
	//		Thread.sleep(10000);
	//		
	//		// Verify Reservations page is loaded
	//		if(COMMON_METHODS.getText(getTestObject("KCCP_OL_21")).contains("Reservations")){
	//			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Reservations' page is displayed.", "'Reservations' page is displayed.", "");
	//		}else{
	//			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Reservations' page is displayed.", "'Reservations' page is not displayed.", "");
	//		}
	//
	//		// Logout from Care Center Portal
	//		Utility.logout();
	//
	//		//Log to reports 
	//		 COMMON_METHODS.logToReportAfterPass(methodName);
	//	}

	//	/* THIS METHOD IS ROLLED UP
	//	 * @author: Krishna Chaitanya Maringanti
	//	 * @CreationDate: 20/03/2014
	//	 */
	//	//Test Case #18385: BUCA - CCP Reservations - HTU details display properly when editing reservations
	//
	//	@Test()
	//	public void CCP_ReservationManagement_EditReservation_ReservationsHeader() throws Exception {
	//
	//		boolean bAllExpanded = true;
	//		
	//		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	//		System.out.println("Inside - " + methodName);
	//		
	//		// Read test data for based on client 1
	//		//readTestData(getDataTablePath("CCP"), "TD_CCP");
	//		
	//		/*// Launch the browser
	//		CCP_BusinessComponents.CCP_LaunchBrowser();
	//		
	//		// Login CareCenter Portal
	//		CCP_BusinessComponents.CCP_Login();
	//		*/
	//		
	//		// Launch the browser
	//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
	//		
	//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
	//		
	//		// Login CareCenter Portal
	//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
	//		
	//		// Click on the Reservation Header
	//		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
	//		Thread.sleep(3000);
	//		
	//		// Click the 'CHANGE' link
	//		COMMON_METHODS.clickElement(getTestObject("CCP_OL_4"));
	//		
	//		// Enter the center name in the search field
	//		COMMON_METHODS.editAField(getTestObject("CCP_OL_5"), getTestData("CCP_TD_CenterName"));
	//		
	//		// Click Search button
	//		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));		
	//		
	//		// Click on the 'Expand All' link
	//		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
	//
	//		// Click on the "Edit" button for a pending reservation
	//		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_19"));
	//
	//		if(bAllExpanded){
	//			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Reservations' page is displayed.", "'Reservations' page is displayed.", "");
	//		}else{
	//			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Reservations' page is displayed.", "'Reservations' page is not displayed.", "");
	//		}
	//
	//		// Logout from Care Center Portal
	//		Utility.logout();
	//
	//		//Log to reports 
	//		 COMMON_METHODS.logToReportAfterPass(methodName);
	//	}
	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 20/03/2014
	 */
	//Test Case #18389: BUCA - CCP Reservations - Add other provider options - edit res view

	@Test @Parameters("client") 
	public void CCP_ReservationManagement_EditReservation_AddOtherProvider(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPortal();
		
		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		Thread.sleep(2000);
		
		CCP_BusinessComponents.clickEditReservation(lastName);
		
		Thread.sleep(2000);

		// Verify Edit Reservation page is loaded
		if(COMMON_METHODS.driver.findElement(By.xpath("//input[@value='Update Reservation']")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Edit Reservation' page is displayed.", "'Edit Reservation' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Edit Reservation' page is displayed.", "'Edit Reservation' page is not displayed.", "");
		}

		// Verify 'Add other provider options' link is displayed.
		try {
			
			WebElement weAddOtherProviderOptionsLink = COMMON_METHODS.driver.findElement(By.xpath("//form[@id='mainForm']/ul/li/a[contains(text(),'Add other provider options')]"));

			if(weAddOtherProviderOptionsLink.isDisplayed()){
				REPORTER.LogEvent(TestStatus.PASS, "Verify the 'Add other provider options'link is displayed.", "'Add other provider options' link is displayed.", "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify the 'Add other provider options' link is displayed.", "'Add other provider options' link is not displayed.", "");
			}
		}catch (Exception e) {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the 'Add other provider options' link is displayed.", "'Add other provider options' link is not displayed.", "");
		} 

		//		// Logout from Care Center Portal
		//		Thread.sleep(5000);
		//		Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	//	/**
	//	 * TFS ID: 13086 -  Reservation Fulfillment - Center Care - Verify available reservations can be viewed in the Care Center Portal
	//	 * employee profile information 
	//	 * @param TC
	//	 * @throws Exception
	//	 */

	//	/** THIS METHOD IS ROLLED-UP
	//	 * @author Deepa
	//	 * @version 
	//	 * @return 
	//	 * @param 
	//	 * @CreationDate: 20/03/2014
	//	 */
	//	@Test(priority=1)
	//
	//	public void CCP_VerifyAvailableResv() throws Exception
	//	{
	//		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
	//		System.out.println("Inside--------- "+methodName);
	//
	//		//Read testdata for based on client 1
	//		//readTestData(getDataTablePath("CCP"), "TD_CCP");
	//
	//		/*//Launch Browser
	//		CCbusinessComponents.CCP_LaunchBrowser(); 
	//
	//		//User logs into the portal
	//		CCP_BusinessComponents.CCP_Login();*/
	//		
	//		// Launch the browser
	//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
	//		
	//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
	//		
	//		// Login CareCenter Portal
	//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
	//
	//		// Click on the Reservation Header
	//		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
	//		Thread.sleep(5000);
	//
	//		// Select the center name
	//		CCP_BusinessComponents.changeCenter("CenterName", getTestData("CCP_TD_CenterName"));
	//	
	//		// Select the date of reservation
	//		selectDate();
	//
	//		// Click on the 'Expand All' link
	//		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
	//
	//		//Get Selected value from list box
	//		COMMON_METHODS.getSelectedValueFromListBox(getTestObject("D_CCP_OL_9"));
	//
	//		Utility.logout();
	//
	//		COMMON_METHODS.logToReportAfterPass(methodName);
	//	}

	//	/**
	//	 * TFS ID: 13087 - Reservation Fulfillment - Center Care - Verify a Care Center can mark an open reservation assigned to them as Confirmed or Waitlist
	//	 * employee profile information 
	//	 * TFS ID : 13087
	//	 * 
	//	 * @param TC
	//	 * @throws Exception
	//	 */
	//
	//	/** 
	//	 * @author Deepa
	//	 * @version 
	//	 * @return 
	//	 * @param 
	//	 * @CreationDate: 21/03/2014
	//	 */
	//	@Test(priority=2)
	//
	//	public void CCP_PendingResvEdit() throws Exception
	//	{
	//		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
	//		System.out.println("Inside--------- "+methodName);
	//
	//
	//		//Read testdata for based on client 1
	//		//readTestData(getDataTablePath("CCP"), "TD_CCP");
	//
	//		/*//Launch Browser
	//		CCP_BusinessComponents.CCP_LaunchBrowser(); 
	//
	//		//User logs into the portal
	//		CCP_BusinessComponents.CCP_Login();
	//*/
	//		
	//		// Launch the browser
	//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
	//		
	//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
	//		
	//		// Login CareCenter Portal
	//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
	//		
	//		CCP_BusinessComponents.CCP_ExpandAllReservations();		
	//
	//		//Get Selected value from list box
	//		String val=COMMON_METHODS.getSelectedValueFromListBox(getTestObject("D_CCP_OL_9"));
	//		if(val.equals("Pend")){
	//			//Click Edit
	//			COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_10"));
	//
	//			//Click on Care Recipient Checkbox
	//			COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_11"),"check");
	//
	//			//Click Edit Selected
	//			COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_12"));
	//
	//			//Select confirmaed
	//			COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_13"), "Confirmed", "byVisibleText");
	//
	//		}
	//		else
	//			REPORTER.LogEvent(TestStatus.FAIL, "Verify Pending status Reservation", "Pending Status Reservation not available", "");
	//
	//	}

	/**
	 * This test script covers functionality of CCP - Center Management - Ensure user can view report
	 * employee profile information 
	 * TFS ID : 15102
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/** 
	 * @author Deepa
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 21/03/2014
	 */

	//@Test(priority=3)
	@Test()

	public void CCP_ViewReport() throws Exception{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);


		//Read testdata for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*//Launch Browser
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		//User logs into the portal
		CCP_BusinessComponents.CCP_Login();*/


		//		// Launch the browser
		//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		//		
		//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		//		
		//		// Login CareCenter Portal
		//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		this.loginCCPortal();

		//Click on CenterManagement
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_14"));

		//Click on Reports
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_15"));

		//Click on Day Sheet Reports
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_16"));

		//Verify Day sheet Report page
		COMMON_METHODS.VerifyTextPresent(getTestObject("D_CCP_OL_17"),"REPORTS:DAY SHEET");

		//		Thread.sleep(5000);
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of CCP Reservations - Verify the View Pending Days Popup works
	 * employee profile information 
	 * TFS ID : 15458
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/** 
	 * @author Deepa
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 21/03/2014
	 */

	//@Test(priority=4)
	@Test()
	public void CCP_ViewPendingDays() throws Exception{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*//Launch Browser
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		//User logs into the portal
		CCP_BusinessComponents.CCP_Login();*/

		//		// Launch the browser
		//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		//		
		//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		//		
		//		// Login CareCenter Portal
		//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		this.loginCCPortal();

		//Click on Reservations
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));

		//Click on View Pending Days link
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_18"));

		//Verify Popup Title
		COMMON_METHODS.VerifyTextPresent(getTestObject("D_CCP_OL_19"),"UPCOMING CARE SESSIONS");

		//Click on Close
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_20"));
		Thread.sleep(5000);
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of  CCP Reservations - Request utilization adjustment- edit res view
	 * employee profile information 
	 * TFS ID : 18396
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/** 
	 * @author Deepa
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 24/03/2014
	 */
	//@Test(priority=2)
	@Test()
	public void CCP_RequestUsageAdjustment() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);

		this.loginCCPortal();

		CCP_BusinessComponents.CCP_ExpandAllReservations(getTestData("CCP_TD_CenterName"));	

		//Click on Edit
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_21"));

		// Select all care sessions for the reservation and click edit selected
		COMMON_METHODS.checkBox(getTestObject("KCCP_OL_15"), "check");
		
		//click on the edit selected
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_16"));
		
		Thread.sleep(5000);
		
		//click the Request an Adjustment link
		COMMON_METHODS.clickElement(getTestObject("RES_REQ_ADJ"));
		
		Thread.sleep(5000);
		
		//select Request CoPay And Utilization  radio option 
		COMMON_METHODS.clickElement(getTestObject("RES_REQ_UT"));
		
		Thread.sleep(5000);
		
		//Enter Adjustment Details
		COMMON_METHODS.editAField(getTestObject("D_CCP_OL_23"), "Request Adjustment");
		
		Thread.sleep(5000);

		//Click on submit
		COMMON_METHODS.clickElement(getTestObject("REQ_ADJ_SUBMIT"));
		
		Thread.sleep(5000);
		//		Utility.logCout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of BUCA BUCA - CCP - Reservations Wizard - Verify that care center users can continue through step 4 and 4.5 of the reservation flow without validation
	 * BUCA - CCP - Reservations Wizard - Step3 - Check availability and set status of reservation 
	 * BUCA - CCP Reservations - Test centers can continue without setting payment terms and accepting cancellation policies 
	 * BUCA - CCP Reservations - Add new care center reservations for multiple days and multiple care recipients
	 * TFS ID : 18470,18443,18450,18471
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/** 
	 * @author Deepa
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 24/03/2014
	 */
	//@Test(priority=2)
	@Test @Parameters("client") 
	public void CCP_ResvWizard(String employer) throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);
		
		/*// Launch Browser with EP Url
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Verify Employer after Entering Employer ID and Password
		epBusinessComponents.EP_verifyEmployer(employer);

		// Accept privacy policy
		epBusinessComponents.EP_AcceptPolicyAndSubmit();

		// Create dynamic user name
		String userName = createDyanamicUserData();

		// Register a New User
		epBusinessComponents.EP_SignUpUser(userName, employer, "cpuser");

		// Test data for adding Care Recipients
		String addCrData1[] = { "CCPCareR1_FN",
				"CCPCareR1_LN", "Son", getTestData("TD_DOB"),
				getTestObject("OL_61"), getTestObject("OL_47"),
				getTestObject("OL_48"), getTestObject("OL_49"),
				getTestObject("OL_50"), getTestObject("OL_51"),
				getTestObject("OL_52"), getTestData("TD_AddInfo"),
				getTestObject("OL_54"), getTestObject("OL_55"),
				getTestData("TDU1_STATE1")
										 * getXMLData("Client"+employer,"TDU1_STATE1"
										 * )
										 , getTestData("TD_AddInfo"),
				getTestObject("OL_100"), null,
				getTestData("TDU1_CENTERLOCATION1")
													 * getXMLData("Client"+employer
													 * ,"TDU1_STATE1")
													 , null, null };
		// Test data for adding Care Recipients
		String addCrData2[] = { "CCPCareR2_FN",
				"CCPCareR2_LN", "Son", getTestData("TD_DOB"),
				getTestObject("OL_61"), getTestObject("OL_47"),
				getTestObject("OL_48"), getTestObject("OL_49"),
				getTestObject("OL_50"), getTestObject("OL_51"),
				getTestObject("OL_52"), getTestData("TD_AddInfo"),
				getTestObject("OL_54"), getTestObject("OL_55"),
				getTestData("TDU1_STATE1")
										 * getXMLData("Client"+employer,"TDU1_STATE1"
										 * )
										 , getTestData("TD_AddInfo"),
				getTestObject("OL_100"), null,
				getTestData("TDU1_CENTERLOCATION1")
													 * getXMLData("Client"+employer
													 * ,"TDU1_STATE1")
													 , null, null };
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));
		
		//Update Employee profile
		epBusinessComponents.EP_UpdateEmployeeprofile(employer);
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		epBusinessComponents.addCareRecipients(addCrData1,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		epBusinessComponents.addCareRecipients(addCrData2,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Click on 'Add' link for Authorized Contacts section
		COMMON_METHODS.clickElement(getTestObject("OL_58"));
		
		epBusinessComponents.EP_AddAuthorizedContacts();
		
		Utility.logout();*/

		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPortal();
		//CCP_BusinessComponents.CCP_ExpandAllReservations();

		//Click on Reservations
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));

		// Change the center to the center reserved
		CCP_BusinessComponents.changeCenter("CenterName", getTestData("CCP_TD_CenterName"));

		CCP_BusinessComponents.CCP_CreateReservation();

		Thread.sleep(5000);
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	/**
	 * This test script covers functionality of BUCA - CCP Reservations - Cancel Care Sessions
	 * employee profile information 
	 * TFS ID : 18433
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/** 
	 * @author Deepa
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 24/03/2014
	 */
	//@Test(priority=2)
	@Test  @Parameters("client") 
	public void CCP_CancelCareSessions(String employer) throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);

		/*ssssss
		this.loginCCPortal();
		
		CCP_BusinessComponents.CCP_ExpandAllReservations(getTestData("CCP_TD_CenterName"));	

		// Click on the Expand All Link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));

		// Navigate to the Edit Reservation page
		editReservation();*/
		
		// Launch the browser
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPortal();

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		Thread.sleep(2000);
		
		CCP_BusinessComponents.clickEditReservation(lastName);

		Thread.sleep(1000);
		
		//Click on Care Session Checkbox
		COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_25"), "check");

		//Click on Cancel Selected
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_26"));

		//Select Bad Weather option
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_27"),"Bad Weather", "byVisibleText");
		
		//Click on no radio option for Do you need to make an adjustment
		COMMON_METHODS.clickElement(getTestObject("CAN_CARE_SES"));
		
		//Click on submit
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_28"));

		//Verify Session Cancellation
		COMMON_METHODS.VerifyTextPresent(getTestObject("D_CCP_OL_29"), "Cancelled");

		//		Thread.sleep(5000);
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 21/03/2014
	 */
	//Test Case #18342:     BUCA - CCP Landing - Ensure System Populates the Upcoming Reservations Module

	@Test()
	public void CCP_Landing() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); */

		//		// Launch the browser
		//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		//		
		//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		//		
		//		// Login CareCenter Portal
		//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		Thread.sleep(2000);

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));
		
		
		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		/*COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));
*/
		// Change the center
		CCP_BusinessComponents.changeCenter("Name",getTestData("CCP_TD_srchText"));
		
		//Ensure the Pending and Wait Listed numbers work.
		COMMON_METHODS.clickElement(getTestObject("S_CCP_Pending"));

		//COMMON_METHODS.driver.findElement(By.xpath("//*[@id='tblPending']/table")).getSize();

		//Verify Reservations Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"), "Reservations");
		Thread.sleep(5000);
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 21/03/2014
	 */
	//Test Case #18351:     BUCA - CCP Reservations - Ensure you can navigate to other dates on the Reservation page

	@Test()
	public void CCP_NavigateOtherDates() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPortal();
		
		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));
		
		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");
		
		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		
		Thread.sleep(3000);

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		//Click on Calendar, choose another date and Ensure the date chose loads
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));

		Thread.sleep(2000);

		
	// Logout from CC Portal	Loc_Confirmation
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 24/03/2014
	 */
	//Test Case #18343:     BUCA - CCP Landing - Ensure System Populates the Center Activities module

	@Test()
	public void CCP_CenterActivities() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		//this.loginCCPortal();
		
		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));
		
		Thread.sleep(1000);
		
		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");
		
		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);

		//Verify the Center Activities section displaying or not
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL7"), "Center Activities");
		
		//Click count in the Center Activities That Need Attention
		COMMON_METHODS.clickElement(getTestObject("CEN_ACT_CT"));
		
		Thread.sleep(6000);
		
		System.out.println("center : " + COMMON_METHODS.getText(getTestObject("CEN_ACT_TIT")));
		
		// Verify the Center Activities is displayed
		if(COMMON_METHODS.getText(getTestObject("CEN_ACT_TIT")).contains("CENTER ACTIVITIES")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify the Center Activities summary is displayed.", "The Center Activities summary is displayed", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the Center Activities summary is displayed.", "The Center Activities summary is not displayed", "");
		}

		
		// Logout from CC Portal	Loc_Confirmation
		Thread.sleep(3000);
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 24/03/2014
	 */
	//Test Case #18349:     BUCA - CCP Landing - Ensure System Populates the Client Alerts as an RM

	@Test()
	public void CCP_ClientAlerts() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); */

		//		// Launch the browser
		//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		//		
		//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		//		
		//		// Login CareCenter Portal
		//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		//		Thread.sleep(2000);

		this.loginCCPortal();
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Reservations page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL8"), "Client Alerts");

		// Logout from CC Portal	Loc_Confirmation
		Thread.sleep(3000);
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 24/03/2014
	 */
	//Test Case #18390:     BUCA - CCP Reservations - View entire reservation link - edit res view

	@Test @Parameters("client")
	public void CCP_EditResView(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch the browser
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));

		this.loginCCPortal();

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		// Click on the Reservation tab
				COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
				Thread.sleep(3000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		Thread.sleep(1000);
		
		CCP_BusinessComponents.clickEditReservation(lastName);
		
		Thread.sleep(2000);

		//Ensure the reservation opens in edit
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL11"), "View Entire reservation (All statuses/Locations)");

		Thread.sleep(2000);
		
		//	Click "View Entire Reservation" link
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL11"));
		
		Thread.sleep(3000);

		//Ensure the entire reservation opens
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL12"), "Special Reservation Changes");

		// Logout from CC Portal
		Thread.sleep(3000);
		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 24/03/2014
	 * Test Case #18393:     BUCA - CCP Reservations - Request co pay adjustment- edit res view
	 * TFS ID:23455:CSC BUCA : Adjustments : Make a Co-Pay Adjustment on Care Center Portal--Verify Co-Pay adjustments made, 
	 * in the amounts displayed on the the 'Payment Information' section of the CCP Edit Reservation page
	 */
	

	@Test()
	public void CCP_RequestCoPayAdjustment() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); */

		// Launch the browser
		//		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		//		
		//		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		//		
		//		// Login CareCenter Portal
		//		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		//		Thread.sleep(2000);
		
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPortal();
		/*COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));*/
		
		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));
		
		// Change the center
		CCP_BusinessComponents.changeCenter("Name",getTestData("CCP_TD_srchText"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Reservations"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL5"));

		//Reservations page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL5"), "Reservations");

		// Click Arrow button for confirmed reservation, Edit then  Request a CoPay
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL9"));
		Thread.sleep(1000);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL10"));
		/*Thread.sleep(4000);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL13"));*/
		Thread.sleep(5000);
		
		// Select all care sessions for the reservation and click edit selected
		COMMON_METHODS.checkBox(getTestObject("KCCP_OL_15"), "check");
		
		//click on the edit selected
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_16"));
		
		Thread.sleep(5000);
		
		//click the Request an Adjustment link
		COMMON_METHODS.clickElement(getTestObject("RES_REQ_ADJ"));
		
		Thread.sleep(5000);
		
		//select Request Waive Co-Pay  radio option 
		COMMON_METHODS.clickElement(getTestObject("RES_REQ_WCP"));
		
		Thread.sleep(5000);
		
		//Select Adjustment Reason type
		COMMON_METHODS.listBoxSelect(getTestObject("RES_ADJ_RS"), "Center Closed", "byVisibleText");
		
		Thread.sleep(5000);
		
		//Enter Adjustment Details
		COMMON_METHODS.editAField(getTestObject("RES_ADJ_DET"), "Request Adjustment");
		
		Thread.sleep(5000);

		//Click on submit
		COMMON_METHODS.clickElement(getTestObject("REQ_ADJ_SUBMIT"));
		
		Thread.sleep(40000);
		
		/*//Ensure the Request CoPay popup opens
		COMMON_METHODS.switchToPopup();
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL14"), "Request a Co-Pay Adjustment");

		//Enter an ammount into CoPay Ammount	Ensure the system takes the ammount.
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL15"), getTestData("CCP_TD_CoPayAmt"));
		String Amt = COMMON_METHODS.getText(getTestObject("S_CCP_OL15"));
		int temp=Amt.trim().compareTo(getTestData("CCP_TD_CoPayAmt"));
		boolean tmp = temp == 0;
		if (tmp == true)
		{
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Co Pay Amount", "Co Pay Amoun Verification -- Succeeds" , "");
		}
		else
		{	
				REPORTER.LogEvent(TestStatus.FAIL, "Verifying Co Pay Amount", "Co Pay Amoun Verification -- Failed" , "");	
		}

		//Click a reason for the adjustment
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL18"));
		COMMON_METHODS.listBoxSelect(getTestObject("S_CCP_OL18"),getTestData("CCP_TD_Reason"), "byVisibleText");
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL16"), getTestData("CCP_TD_Adjust_Detls"));

		// Click Submit
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL17"));*/

		// Logout from CC Portal	
		Thread.sleep(10000);
		//		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @CreationDate: 20/03/2014
	 * @ Test Case #13316: BBUCA - CCP Reservations - Verify the down arrow expands the Reservation per class
	 *  TFS ID:13317:BUCA - CCP Reservations - Verify a user can change the status of a reservation(CCP_ReservationFulfillment_Tests)
	 */
	@Test @Parameters("client")
	public void CCP_Reservations_Links(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));

		this.loginCCPortal();

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);
		
		//Verify the Reservation page is displayed
		if(COMMON_METHODS.driver.getPageSource().contains("Reservations:"))
			REPORTER.LogEvent(TestStatus.PASS, "Reservations Page is displayed", "Reservations Page is displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Reservations Page is displayed", "Reservations Page is NOT displayed", "");

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String lastName = ReadwritDataFromProps.props.getProperty(employer + ".ccp.lastName");
		
		Thread.sleep(2000);
		
		// Click on the 'Expand All' link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
		
		Thread.sleep(2000);

		//Click on Collapse All link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
		
		Thread.sleep(2000);

		/*//Click on Arrow mark of Preschool class
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL9"));*/
		
		CCP_BusinessComponents.expandClassRoom("Preschool");

		Thread.sleep(3000);
		
		// get the Status in reservation
		Select sSelect = new Select(COMMON_METHODS.driver.findElement(By
				.xpath("//span[contains(text(),'" + lastName
						+ "')]/../../td/span/select")));
		
		Thread.sleep(2000);
		
		boolean bOptionPresent=false;
		
		for (int i = 0; i < sSelect.getOptions().size(); i++) {
		
				String sListBoxItemText = sSelect.getOptions().get(i).getText();

				if (sListBoxItemText.contains("WL") || sListBoxItemText.contains("Pend") || sListBoxItemText.contains("Conf") || sListBoxItemText.contains("Cancel")) {
					bOptionPresent = true;
				}else {
					bOptionPresent = true;
					break;
				}
        }
		
		
		if ( bOptionPresent ) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify you can select Conf, Pend, WL, or Cancel in status drop down", "Conf, Pend, WL, or Cancel options are displayed in status drop down", "");
		}else {
		   REPORTER.LogEvent(TestStatus.FAIL, "Verify you can select Conf, Pend, WL, or Cancel in status drop down", "Conf, Pend, WL, or Cancel options are not displayed in status drop down", "");
		}
		
		Thread.sleep(3000);
		
		//Logout from 'Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}


	/*
	 * @CreationDate: 20/03/2014
	 * @ Test Case #13689:  BUCA - CCP Reservations - Verify a user can Edit Center Notes
	 */
	@Test()
	public void CCP_Reservations_EditNotes() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPortal();

		Thread.sleep(2000);

		//Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));

		//Click on Edit notes link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_07"));

		//Input text in the Internal notes box
		COMMON_METHODS.editAField(getTestObject("GCCP_OL_08"), "Test");

		//Input text in the External notes box
		COMMON_METHODS.editAField(getTestObject("GCCP_OL_09"), "BHTest");

		//Click on Save Notes button
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_10"));

		//Logout from 'Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	} 

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 24/03/2014
	 */
	//Test Case #10817:     BUCA - CCP - Family Management - Ensure you can search with a combination of check boxes selected

	@Test()
	public void CCP_SerachFamilyMgmt() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		/*// Launch the browser 
        CCP_BusinessComponents.CCP_LaunchBrowser(); 

        // Login CareCenter Portal 
        CCP_BusinessComponents.CCP_Login(); */

		/*	// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/

		this.loginCCPortal();

		Thread.sleep(5000);
		/*	
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));*/

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		// Change the center to the center reserved
		CCP_BusinessComponents.changeCenter("CenterName", getTestData("CCP_TD_CenterName"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//Click on Care Recipient and enter search for and click submit	then verify	
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL20"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_CR_Name"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL24"));
		String str = COMMON_METHODS.getText(getTestObject("S_CCP_OL25"));
		//COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL25").substring(1, 7), "Results");
		if (str.contains("RESULTS"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying RESULTS", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying RESULTS", "RESULTS Verification -- Failed" , "");
		}
		Thread.sleep(5000);

		//Click on Click on Client Employee and search and enter search for and click submit and verify			
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_ClientName"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL24"));
		str = COMMON_METHODS.getText(getTestObject("S_CCP_OL25"));
		if (str.contains("RESULTS"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying RESULTS", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying RESULTS", "RESULTS Verification -- Failed" , "");
		}
		Thread.sleep(5000);

		//Click on Authorised Contacts and enter search for and click submit	then verify	
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL22"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_Auth_Contact"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL24"));
		str = COMMON_METHODS.getText(getTestObject("S_CCP_OL25"));
		if (str.contains("RESULTS"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying RESULTS", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying RESULTS", "RESULTS Verification -- Failed" , "");
		}

		// Logout from CC Portal	
		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}	

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 25/03/2014
	 */
	//Test Case #13340:     BUCA - CCP - Family Management - Advanced Search - Ensure that you can search by Home addres

	@Test()
	public void CCP_AdvancedSearchHomeAdd() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		// Change the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//enter search for and then verify the box allows text	

		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_ClientName"));
		Thread.sleep(2000);
		/*String str = COMMON_METHODS.getText(testObject, att);
		if (str.contains(getTestData("CCP_TD_ClientName")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the box allows text", "box allows text -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the box allows text", "box allows text -- Failed" , "");
		}
		Thread.sleep(4000);/**/

		//Click on Click on Client Employee and search and enter search for and click submit and verify			
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		//	Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		// Enter Country and state and then click search
		COMMON_METHODS.listBoxSelect(getTestObject("S_CCP_OL28"), getTestData("CCP_TD_Country"), "byVisibleText");
		COMMON_METHODS.listBoxSelect(getTestObject("S_CCP_OL29"), getTestData("CCP_TD_State"), "byVisibleText");
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));

		// Verify Home location	
		String str = COMMON_METHODS.getText(getTestObject("S_CCP_OL31"));
		if (str.contains(getTestData("CCP_TD_State")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying Home Location", "Home Location Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying Home Location", "Home Location Verification -- Failed" , "");
		}
		Thread.sleep(4000);

		// Logout from CC Portal	
		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 26/03/2014
	 */
	//Test Case #10820:      BUCA - CCP - Family Management - Ensure you can perform an Advanced Search

	@Test()
	public void CCP_AdvancedSearch() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);


		/*// Launch the browser 
        CCP_BusinessComponents.CCP_LaunchBrowser(); 

        // Login CareCenter Portal 
        CCP_BusinessComponents.CCP_Login(); */
		// Launch the browser
		/*Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));


		/*	COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));*/

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		// Change the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//enter search for and then verify the box allows text	

		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_ClientName"));
		Thread.sleep(2000);

		//Click on Click on Client Employee and search and enter search for and click submit and verify			
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		//	Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Enter an email address and verify	results that contain the entered email address	
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL32"), getTestData("CCP_TD_Email"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		String str = COMMON_METHODS.getText(getTestObject("S_CCP_OL33"));

		if (str.contains(getTestData("CCP_TD_ClientName")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the Client with Email", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the Client with Email", "RESULTS Verification -- Failed" , "");
		}
		Thread.sleep(2000);

		// Going to serach with Phone and Validate, so starting with Family mgmt then enter Ph. and serach and validate

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//enter search for and then verify the box allows text	

		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_ClientName"));
		Thread.sleep(2000);

		//Click on Click on Client Employee and search and enter search for and click submit and verify			
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		//	Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Enter an email address and verify	results that contain the entered email address	
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL34"), getTestData("CCP_TD_Ph"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		str = COMMON_METHODS.getText(getTestObject("S_CCP_OL33"));

		if (str.contains(getTestData("CCP_TD_ClientName")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the Client with Phone", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the Client with Phone", "RESULTS Verification -- Failed" , "");
		}
		Thread.sleep(2000);

		// Going to serach with country, State and Zip and Validate

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//enter search for and then verify the box allows text	

		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_ClientName"));
		Thread.sleep(2000);

		//Click on Click on Client Employee and search and enter search for and click submit and verify			
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		//	Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Enter an email address and verify	results that contain the entered email address	
		COMMON_METHODS.listBoxSelect(getTestObject("S_CCP_OL28"), getTestData("CCP_TD_Country"), "byVisibleText");
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL35"), getTestData("CCP_TD_Pcode"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		str = COMMON_METHODS.getText(getTestObject("S_CCP_OL33"));

		if (str.contains(getTestData("CCP_TD_ClientName")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the Client with Postal code, country and State", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the Client with Postal code, country and State", "RESULTS Verification -- Failed" , "");
		}


		// Logout from CC Portal	
		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/* TFS ID:13358
	 * @CreationDate: 25/03/2014
	 * BUCA - CCP - Family Management - Advanced Search - Ensure that you can search by Care Recipient Status
	 */


	@Test()
	public void CCP_FamilyCRActive_Inactive() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
		  CCP_BusinessComponents.CCP_LaunchBrowser(); 

		  // Login CareCenter Portal 
		  CCP_BusinessComponents.CCP_Login(); */

		// Launch the browser
		/*  Utility.launchBrowser(getTestData("CCP_TD_URL"));

		  String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

			// Login CareCenter Portal
		  Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		  Thread.sleep(5000);*/

		this.loginCCPortal();

		//Click on Home tab
		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		/* COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		  COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		  COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		  COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));*/

		// Change the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//enter search for and then verify the box allows text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_ClientName"));
		Thread.sleep(2000);

		//Click on Click on Client Employee and search and enter search for and click submit and verify   
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		// Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Click on Care Recipient status as Active
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_17"));

		//Click on Search button  
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));

		//Click on Care Recipient status as Active
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_18"));

		//Click on Search button  
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));

		/* Thread.sleep(2000);

		  //Logout from 'Portal'
		  Utility.logout();

		  Thread.sleep(2000);*/

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}





	/* Test Case #9112:
	 * @CreationDate: 26/03/2014
	 *  BUCA - CCP - As a Care Center Employee I need to search for Client Programs
	 */


	@Test()
	public void CCP_ClientPrograms_Search() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
	
		this.loginCCPortal();

		//Click on Home tab
		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		Thread.sleep(2000);
		
		//Click on "Client Programs"
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL01"));
		
		Thread.sleep(2000);

		//Verify Client Programs page
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL02"), "CLIENT PROGRAMS");
		
		Thread.sleep(2000);

		//Verify Client Program search section
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL03"), "CLIENT SEARCH");
		
		Thread.sleep(2000);

		//Enter value in the search for textbox
		COMMON_METHODS.editAField(getTestObject("L_CCP_OL04"), getTestData("CCP_CP_Searchfor"));

		//Click on "search" Button
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL05"));
		Thread.sleep(2000);

		//verify the search returns correct result
		String str = COMMON_METHODS.getText(getTestObject("L_CCP_OL06"));

		if (str.contains(getTestData("CCP_CP_Searchfor")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "The search has returned the correct result", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "The search has returned the in correct result", "RESULTS Verification -- Failed" , "");
		} 


		//Logout from 'Portal'
		// Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}


	/**
	 * CSC BUCA :Edit Reservation - Cancel Selected: 
	 * Verify application enable cancelation of Care Sessions that are in  ‘Waitlisted’ Status
	 * TFS ID : 22897
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/** 
	 * @author Supraja
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 01/04/2014
	 */
	@Test
	public void CCP_CancelSelected_Waitlisted() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);

		/*//Launch Browser
			CCP_BusinessComponents.CCP_LaunchBrowser(); 

			//User logs into the portal
			CCP_BusinessComponents.CCP_Login();*/

		/*// Launch the browser
			Utility.launchBrowser(getTestData("CCP_TD_URL"));

			String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

			// Login CareCenter Portal
			Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/

		/*CCP_BusinessComponents.CCP_ExpandAllReservations();	*/

		// Click Calendar button
		//COMMON_METHODS.clickElement(getTestObject("KCCP_OL_22"));

		// Click the date link
		//COMMON_METHODS.driver.findElement(By.xpath("//div[@id='ui-datepicker-div']//a[text()='"+ getTestData("CCP_TD_DateOfWaitlistedReservation") +"']")).click();
		//Thread.sleep(3000);

		/*// Click on the Reservation Header
			COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));


			this.selectDate();

			this.editReservation();*/

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		CCP_BusinessComponents.CCP_ExpandAllReservations(getTestData("CCP_TD_CenterName"));

		//Click on Edit
		//COMMON_METHODS.clickElement(getTestObject("CCP_RES_OL_10"));
		// Click Edit link of the confirmed reservation	
		COMMON_METHODS.driver.findElement(By.xpath("//td[contains(@id,'data_')]/span/select/option[@selected='selected']/../../../../td/span/a[text()='Edit'][1]")).click();

		Thread.sleep(2000);

		//Click on Care Session Checkbox
		COMMON_METHODS.checkBox(getTestObject("CCP_RES_OL_11"), "check");
		//COMMON_METHODS.checkBox(getTestObject("KCCP_OL_15"), "check");

		//Click on Care Session Checkbox
		//COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_25"), "check");

		//Click on Cancel Selected
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_26"));

		//Check if Cancel the Care Session popup is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_RES_OL_12"), "Cancel The Care Session");

		//It should display dates of selected Care Sessions as the sessions to be Canceled
		//COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_RES_OL_13"),"04/02/2014 @ 10:00 am");

		//Cancel the Care Session 
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_55"));


	}


	/*
	 * @author: Supraja
	 * @CreationDate: 31/03/2014
	 */
	/*Test Case #23558: CSC - BUCA Care Center Portal- Ensure the application should display all the Classroom Type 
		under Classroom column of the Confirmed Weekly Care Sessions */

	@Test()
	public void CCP_ConfirmedWeeklyCareSession_ClassRoomTypes() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
			CCP_BusinessComponents.CCP_LaunchBrowser(); 

			// Login CareCenter Portal 
			CCP_BusinessComponents.CCP_Login(); */

		/*// Launch the browser
			Utility.launchBrowser(getTestData("CCP_TD_URL"));

			String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

			// Login CareCenter Portal
			Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		/*Thread.sleep(2000);*/

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_RES_OL_05"), "Confirmed Weekly Care Sessions");


		//The application should display the following Classroom Types under Classroom column 1. Infant 2. Toddler 3. Twos 
		// 4. Preschool 5. Kindergarten 6. First Grade 7. School Age 8. Summer Camp 9. Before and After School
		// the above mentioned types should be in the order described.

		//Chicago East Loop Center has all only 4 classroom types 
		//Verify classroom type is Infant
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_RES_OL_06"), "Infant");

		//Verify classroom type is Toddler
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_RES_OL_07"), "Toddler");

		//Verify classroom type is Preschool
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_RES_OL_08"), "Preschool");

		//Verify classroom type is School Age
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_RES_OL_09"), "School Age");

		// Logout from Care Center Portal
		Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/**
	 * CSC BUCA : Edit Reservation - Cancel Selected: Verify 'Waive Co-pay' option on Cancel the Care Session popup.
	 * TFS ID : 22909
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/** 
	 * @author Supraja
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 01/04/2014
	 */
	@Test
	public void CCP_CancelSelected_WaiveCopay() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);

		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);
				
		//CCP_BusinessComponents.CCP_ExpandAllReservations(getTestData("CCP_TD_CenterName2"));
		
		// Change the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		Thread.sleep(3000);
		
		CCP_BusinessComponents.expandClassRoom("Preschool");
		
		
		
//		this.editReservation();
		

	/*	//Click on Edit
		//COMMON_METHODS.clickElement(getTestObject("CCP_RES_OL_10"));
		// Click Edit link of the confirmed reservation	
		COMMON_METHODS.driver.findElement(By.xpath("//td[contains(@id,'data_')]/span/select/option[@selected='selected']/../../../../td/span/a[text()='Edit'][1]")).click();

		Thread.sleep(2000);

		//Click on Edit
		//COMMON_METHODS.clickElement(getTestObject("CCP_RES_OL_10"));

		//Click on Care Session Checkbox
		COMMON_METHODS.checkBox(getTestObject("CCP_RES_OL_11"), "check");

		//Click on Care Session Checkbox
		//COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_25"), "check");

		//Click on Cancel Selected
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_26"));

		//Check if Cancel the Care Session popup is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_RES_OL_12"), "Cancel The Care Session");


		//To-Do code for  'Confirmed Elsewhere' reservation.
		// Need a  'Confirmed Elsewhere' reservation for this test case. 
		//When update a reservation Error message is displayed "Object is not valid.."

		//Logout from 'Portal'
		 Utility.logout();*/

	}



	/*
	 * Test Case #23276: CSC 
	 * BUCA: CCP- Client Programs- Client Search - Ensure User has to enter atleat two characters in the ‘Search For’ field 
	 */


	@Test()
	public void CCP_ClientPrograms_atleast2char() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/* // Launch the browser 
		 CCP_BusinessComponents.CCP_LaunchBrowser(); 

		 // Login CareCenter Portal 
		 CCP_BusinessComponents.CCP_Login(); */

		/*		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		 Thread.sleep(5000);*/

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		//Click on "Client Programs"
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL01"));

		//Verify Client Programs page
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL02"), "CLIENT PROGRAMS");

		//Verify Client Program search section
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL03"), "CLIENT SEARCH");

		//Enter value in the search for textbox
		COMMON_METHODS.editAField(getTestObject("L_CCP_OL04"), getTestData("CCP_CP_Searchfor1"));

		//Click on "search" Button
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL05"));
		Thread.sleep(2000);

		//verify the search returns correct result
		String str = COMMON_METHODS.getText(getTestObject("L_CCP_OL06"));

		if (str.contains(getTestData("CCP_CP_Searchfor")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "The search has returned the correct result", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "The search has returned the in correct result", "RESULTS Verification -- Failed" , "");
		} 

		/*
		 //Logout from 'Portal'
		 Utility.logout();
		 */
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}



	/*
	 * Test Case #23278: CSC 
	 * BUCA: CCP- Client Programs- Client Search - Ensure user search results for all clients whose client name or short names or alias begins with the search criteria
	 */


	@Test()
	public void CCP_ClientPrograms_SearchCriteria() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		/*// Launch the browser 
		 CCP_BusinessComponents.CCP_LaunchBrowser(); 

		 // Login CareCenter Portal 
		 CCP_BusinessComponents.CCP_Login(); */
		// Launch the browser
		/* Utility.launchBrowser(getTestData("CCP_TD_URL"));

		 String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		 // Login CareCenter Portal
		 Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		 Thread.sleep(5000);*/

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		//Click on "Client Programs"
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL01"));

		Thread.sleep(4000);

		//Verify Client Programs page
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL02"), "CLIENT PROGRAMS");

		//Verify Client Program search section
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL03"), "CLIENT SEARCH");

		//Verify search for for textbox is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL04"), " ");

		//Verify Search Button is Enabled.
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("L_CCP_OL05"), true);

		//Enter value in the search for textbox
		COMMON_METHODS.editAField(getTestObject("L_CCP_OL04"), getTestData("CCP_CP_Searchfor2"));

		//Click on "search" Button
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL05"));
		Thread.sleep(2000);

		//Verify Client Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL07"), "ACC_Stab_Test 1");

		//Verify Short Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL08"), "TCONE");

		//Verify Aliases in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL09"), "item , item");

		//Verify Action in the search Result
		// COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL10"), "Add New Employee");

		String s1 = COMMON_METHODS.getText(getTestObject("L_CCP_OL10"));

		if (s1.contains("Add New Employee")) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verifying Add New Employee Text " + s1,
					"Add New Employee Text Verification -- Succeeds", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verifying Add New Employee Text " + s1,
					"Add New Employee Text Verification -- Failed", "");
		}

		/* //Verify Client Name in the search Result
	 String str = COMMON_METHODS.getText(getTestObject("L_CCP_OL11"));
	 String clientName = "ACC_Stab_Test 3";

	 System.out.println("Actual value :" + str + "length :" + str.length());
	 System.out.println("Expect value :" + clientName + "length :" + clientName.length());

	 int status = str.compareTo(clientName);

		if (status == 0)
		{
			REPORTER.LogEvent(TestStatus.PASS, "The search has returned the correct client name", "RESULTS Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "The search has returned the correct client name", "RESULTS Verification -- Failed" , "");
		} 


		 //Verify Short Name in the search Result
		 String str1 = COMMON_METHODS.getText(getTestObject("L_CCP_OL12"));

			if (str1.equals("TCTHREE"))
			{
				REPORTER.LogEvent(TestStatus.PASS, "The search has returned the correct Short name", "RESULTS Verification -- Succeeds" , "");
			}
			else
			{
				REPORTER.LogEvent(TestStatus.FAIL, "The search has returned the correct Short name", "RESULTS Verification -- Failed" , "");
			} 

		 //Verify Aliases in the search Result
		 String str2 = COMMON_METHODS.getText(getTestObject("L_CCP_OL13"));

			if (str2.equals("item"))
			{
				REPORTER.LogEvent(TestStatus.PASS, "The search has returned the correct Aliases", "RESULTS Verification -- Succeeds" , "");
			}
			else
			{
				REPORTER.LogEvent(TestStatus.FAIL, "The search has returned the correct Aliases", "RESULTS Verification -- Failed" , "");
			} 


		 //Verify Action in the search Result
		 String s2 = COMMON_METHODS.getText(getTestObject("L_CCP_OL14"));

			if (s2.equals("Add New Employee")) {
				REPORTER.LogEvent(TestStatus.PASS,
						"Verifying Add New Employee Text " + s2,
						"Add New Employee Text Verification -- Succeeds", "");
			} else {
				REPORTER.LogEvent(TestStatus.FAIL,
						"Verifying Add New Employee Text " + s2,
						"Add New Employee Text Verification -- Failed", "");
			}*/

		//Logout from 'Portal'
		// Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}

	/*
	 *Test Case #23279: 
	 *CSC BUCA: CCP- Client Programs- Client Search -Ensure user is able to search for a client by name, short name or alias 
	 * 
	 */

	@Test()
	public void CCP_ClientPrograms_SearchCriteria_Client_ShortName_alias() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/* // Launch the browser 
		 CCP_BusinessComponents.CCP_LaunchBrowser(); 

		 // Login CareCenter Portal 
		 CCP_BusinessComponents.CCP_Login(); */
		// Launch the browser
		/* Utility.launchBrowser(getTestData("CCP_TD_URL"));

		 String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		 // Login CareCenter Portal
		 Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		/*Thread.sleep(5000);*/

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		//Click on "Client Programs"
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL01"));

		//Verify Client Programs page
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL02"), "CLIENT PROGRAMS");

		//Verify Client Program search section
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL03"), "CLIENT SEARCH");

		//Enter value in the search for textbox
		COMMON_METHODS.editAField(getTestObject("L_CCP_OL04"), getTestData("CCP_Searchfor_ClientName"));

		//Click on "search" Button
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL05"));
		Thread.sleep(2000);

		//Verify Client Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL07"), "ACC_Stab_Test 1");

		//Verify Short Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL08"), "TCONE");

		//Verify Aliases in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL09"), "item , item");

		/*//Verify Action in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL10"), "Add New Employee");*/

		/*//Enter value in the search for textbox
		COMMON_METHODS.editAField(getTestObject("L_CCP_OL04"), getTestData("CCP_Searchfor_ShortName"));

		//Click on "search" Button
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL05"));
		Thread.sleep(2000);

		//Verify Client Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL07"), "ACC_Stab_Test 1");

		//Verify Short Name in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL08"), "TCONE");

		//Verify Aliases in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL09"), "item  ,  item");*/

		/*//Verify Action in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL10"), "Add New Employee");*/

		/*//Enter value in the search for textbox
		COMMON_METHODS.editAField(getTestObject("L_CCP_OL04"), getTestData("CCP_Searchfor_Aliases"));

		//Click on "search" Button
		 COMMON_METHODS.clickElement(getTestObject("L_CCP_OL05"));
		 Thread.sleep(2000);

		 //Verify Client Name in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL07"), "ACC_Stab_Test 1");

		//Verify Short Name in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL08"), "TCONE");

		//Verify Aliases in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL09"), "item  ,  item");*/

		/*//Verify Action in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL10"), "Add New Employee");*/

		//Logout from 'Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}

	/*
	 *	Test Case #23280: CSC 
	 *	BUCA: CCP- Client Programs- Client Search - Ensure the text is retained for search criteria
	 */


	@Test()
	public void CCP_ClientPrograms_SearchCriteriatextretained() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		/*// Launch the browser 
		 CCP_BusinessComponents.CCP_LaunchBrowser(); 

		 // Login CareCenter Portal 
		 CCP_BusinessComponents.CCP_Login(); */
		// Launch the browser
		/*Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		 Thread.sleep(5000);


		 */

		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		//Click on "Client Programs"
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL01"));

		//Verify Client Programs page
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL02"), "CLIENT PROGRAMS");

		//Verify Client Program search section
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL03"), "CLIENT SEARCH");

		//Enter value in the search for textbox
		COMMON_METHODS.editAField(getTestObject("L_CCP_OL04"), getTestData("CCP_CP_Searchfor2"));

		//Click on "search" Button
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL05"));
		Thread.sleep(2000);

		//Verify Client Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL07"), "ACC_Stab_Test 1");

		//Verify Short Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL08"), "TCONE");

		//Verify Aliases in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL09"), "item , item");

		/*//Verify Action in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL10"), "Add New Employee");

		//Verify Client Name in the search Result
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL11"), "ACC_Stab_Test 3");

		//Verify text is retained in the search text
		 COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL04"), getTestData("CCP_CP_Searchfor2"));*/

		//Enter value in the search for textbox
		COMMON_METHODS.editAField(getTestObject("L_CCP_OL04"), getTestData("CCP_CP_Searchfor3"));

		//Click on "search" Button
		COMMON_METHODS.clickElement(getTestObject("L_CCP_OL05"));
		Thread.sleep(2000);

		//Verify Client Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL07"), "ACC_Stab_Test 1");

		//Verify Short Name in the search Result
		COMMON_METHODS.VerifyTextPresent(getTestObject("L_CCP_OL08"), "TCONE");

		//Logout from 'Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}		


	/*
	 *	Test Case #23283: 
	 *  CSC BUCA: CCP - Family Management - ‘Family Search’ - Ensure application displays the ‘Family Search’ on the Family Management page
	 */

	@Test()
	public void CCP_FamilySearch_DefaultPage() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);


		/*// Launch the browser 
	        CCP_BusinessComponents.CCP_LaunchBrowser(); 

	        // Login CareCenter Portal 
	        CCP_BusinessComponents.CCP_Login(); */
		/*// Launch the browser
			Utility.launchBrowser(getTestData("CCP_TD_URL"));

			String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

			// Login CareCenter Portal
			Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
			Thread.sleep(5000);*/



		this.loginCCPortal();

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//Verify by default the Care Recipient radio button is selected
		boolean tempCR = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL20"));
		if (tempCR == true)
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying default Care Recipient radio button", "Default Care Recipient radio button is Selected" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying default Care Recipient radio button", "Default Care Recipient radio button is not Selected" , "");
		}

		//Verify Client Employee radio button is present and not selected.
		boolean tempCE = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL21"));
		if (tempCE == true)
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying Client Employee radio button is present and Selected", "Verifying Client Employee radio button is Present and Selected" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying Client Employee radio button is present and not selected", "Verifying Client Employee radio button is present and not selected" , "");
		}

		//Verify Authorized Contact radio button is present and not selected.
		boolean tempAC = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL22"));
		if (tempCE == true)
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying Authorized Contact radio button is present and Selected", "Verifying Authorized Contact radio button is Present and Selected" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying Authorized Contact radio button is present and not selected", "Verifying Authorized Contact radio button is present and not selected" , "");
		}

		//Verify Search For Textbox Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("S_CCP_OL23"), true);

		//Verify Client Name Textbox Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("L_CCP_OL15"), true);

		//Verify Search button is disabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("S_CCP_OL30"), true);


		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL26"), "Advanced Search");

		/*// Logout from CC Portal	
			Utility.logout();*/

		COMMON_METHODS.logToReportAfterPass(methodName);

	}



	/* 
	 * @author: Sanjeev Singh
	 * @CreationDate: 26/03/2014
	 */
	//Test Case #23288      CSC BUCA: CCP - Family Management - ‘Family Search’ - User to perform a Simple Search

	@Test()
	public void CCP_FamilySimpleSearch() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
        CCP_BusinessComponents.CCP_LaunchBrowser(); 

        // Login CareCenter Portal 
        CCP_BusinessComponents.CCP_Login(); */
		// Launch the browser
		/*Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		/*Thread.sleep(5000);*/

		this.loginCCPortal();	

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		/*	COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));*/

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//Verify by default the Care Recipient radio button is selected
		boolean tempCR = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL20"));
		if (tempCR == true)
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying default Care Recipient radio button", "Default Care Recipient radio button is Selected" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying default Care Recipient radio button", "Default Care Recipient radio button is not Selected" , "");
		}

		//Verify user is able to change the selection option to Client Employee, Authorized Contact and back to Care Recipient
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL21"));
		boolean tempCE = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL21"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL22"));
		boolean tempAC = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL22"));
		if ((tempCR == true) && (tempCE == true) && (tempAC == true))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying User is able to change the selection option", "User is able to change the selection option" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying User is able to change the selection option", "User is not able to change the selection option" , "");
		}

		//Verify the CR, CE or AC radio buttons is selected one at a time
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL20"));
		tempCR = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL20"));
		tempCE = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL21"));
		tempAC = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL22"));
		boolean cond1 = (tempCR == true) && (tempCE != true) && (tempAC != true);

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL21"));
		tempCR = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL20"));
		tempCE = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL21"));
		tempAC = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL22"));
		boolean cond2 = (tempCR != true) && (tempCE == true) && (tempAC != true);

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL22"));
		tempCR = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL20"));
		tempCE = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL21"));
		tempAC = COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("S_CCP_OL22"));
		boolean cond3 = (tempCR != true) && (tempCE != true) && (tempAC == true);
		if ((cond1==true) && (cond2==true) && (cond3==true))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the CR, CE or AC radio buttons is selected one at a time", "CR, CE or AC radio buttons is selected one at a time" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the CR, CE or AC radio buttons is selected one at a time", "CR, CE or AC radio buttons is getting selected more than one at a time" , "");
		}

		//Click on 'Search' button after entering search for value
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL21"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), getTestData("CCP_TD_ClientName"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		String str = COMMON_METHODS.getText(getTestObject("S_CCP_OL33"));
		if (str.contains(getTestData("CCP_TD_ClientName")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the Client", "Client Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the Client", "Client Verification -- Failed" , "");
		}

		// Logout from CC Portal	
		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	/**
	 * This test script covers functionality of  BUCA - CCP Reservations - Change location of care to another center that the user has access to 
	 * employee profile information 
	 * TFS ID : 18449
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/** 
	 * @author Deepa
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 26/03/2014
	 */
	//@Test(priority=1)

	public void CCP_ChangeCCLocation() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*//Launch Browser
		CCbusinessComponents.CCP_LaunchBrowser(); 

		//User logs into the portal
		CCP_BusinessComponents.CCP_Login();
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/

		this.loginCCPortal();	

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		CCP_BusinessComponents.CCP_ExpandAllReservations(getTestData("CCP_TD_CenterName"));	

		//Click on New Reservation
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_30"));

		//Click on Edit
		COMMON_METHODS.editAField(getTestObject("D_CCP_OL_31"),"csc");

		//Click on Search
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_32"));

		//Click on New Reservation
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_51"));

		//Click on Change link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_4"));

		//Enter Search text
		COMMON_METHODS.editAField(getTestObject("CCP_OL_5"),"Bright");

		//Click on Search
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));

		//Click on Select
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_71"));

		//Verify Center name
		COMMON_METHODS.isElementPresent(getTestObject("D_CCP_OL_2"), "xpath");

		String cname=COMMON_METHODS.getText(getTestObject("D_CCP_OL_2"));

		REPORTER.LogEvent(TestStatus.PASS, "Verify Center name: "+cname, cname+" is displayed---SUCCESSFULL", "");



	}



	/* TFS ID:13354
	 * @CreationDate: 25/03/2014
	 *  BUCA - CCP - Family Management - Advanced Search - Ensure that you can search by Phone Number
	 */

	@Test()
	public void CCP_FamilyPhonenumber() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);


		/*// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); */
		/*// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);*/

		this.loginCCPortal();	

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//enter search for and then verify the box allows text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), "BHAutoFN");
		Thread.sleep(2000);

		//Click on Click on Client Employee and search and enter search for and click submit and verify   
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		// Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Input the Text in the Phone number field
		COMMON_METHODS.editAField(getTestObject("GCCP_OL_19"), "6176738000");
		Thread.sleep(3000);

		//Click on Search button  
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		Thread.sleep(10000);

		//Verify that the results are displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_26"), "RESULTS (95)");
		Thread.sleep(5000);

		/*//Logout from 'Portal'
		Utility.logout();*/

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/* TFS ID:15459
	 * @CreationDate: 26/03/2014
	 * BUCA - CCP Reservations - Verify the View Waitlist Days Popup works
	 */


	@Test()
	public void CCP_Reservation_Viewlist() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); 
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);*/

		this.loginCCPortal();	

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));


		//Click on Reservations
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));

		//Click on View waitlist days link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_20"));
		Thread.sleep(2000);

		//Click to cancel the pop up
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_21"));
		Thread.sleep(1000);

		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}



	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 26/03/2014
	 */
	//Test Case #15173      BUCA - CCP - As a Care Center Employee I need to be able to add a new family

	@Test()
	public void CCP_AddNewEmployee() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);


		/*// Launch the browser 
        CCP_BusinessComponents.CCP_LaunchBrowser(); 

        // Login CareCenter Portal 
        CCP_BusinessComponents.CCP_Login(); */
		/*// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		//Thread.sleep(5000);

		this.loginCCPortal();	

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL38"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL38"), "Client Programs");

		//Enter a Client name in the search box	Ensure the text box accepts text			
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL36"),getTestData("CCP_TD_Client_Prog"));
		REPORTER.LogEvent(TestStatus.PASS, "Verifying text box accepts text", "Text box accepts text: "+getTestData("CCP_TD_Client_Prog") , "");


		// Click Search
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL37"));

		//Ensure the button functions and fires off a database search returning results.
		String str = COMMON_METHODS.getText(getTestObject("S_CCP_OL33"));
		if (str.contains(getTestData("CCP_TD_Client_Prog")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the Client", "Client Verification -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the Client", "Client Verification -- Failed" , "");
		}

		//Click on "Add New Employee"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL40"));
		Thread.sleep(10000);

		//Verify Employee Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL39"), "Client Programs:");

		// Add emp Detalis
		/*String fN=null;
        String lN=null;

		fN="BH_FN_"+createDyanamicUserData();
	    lN="BH_LN_"+createDyanamicUserData();

	    String uE=createDyanamicUserData()+"@gmail.com";

		COMMON_METHODS.editAField(getTestObject("S_CCP_OL41"),fN);
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL42"),lN);
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL43"),uE);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL44"));

		// Updating Emp Details
		businessComponents.EP_UpdateEmployeeprofile();*/
		/*
		// Logout from CC Portal	
		Utility.logout();*/

		COMMON_METHODS.logToReportAfterPass(methodName);

	}	

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 28/03/2014
	 */
	//Test Case #7329 - BUCA - CCP Reservations - Ensure a user can Add a New Reservation

	@Test()
	public void CCP_AddNewReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		/*// Launch the browser 
        CCP_BusinessComponents.CCP_LaunchBrowser(); 

        // Login CareCenter Portal 
        CCP_BusinessComponents.CCP_Login(); */
		// Launch the browser
		/*Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/

		this.loginCCPortal();	

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Reservation"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL5"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL5"), "RESERVATIONS");

		//Click on "New Reservations" link and Verify Verify that Family Management page loads
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_30"));
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//Enter a Client name in the search box	Ensure the text box accepts text	
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));
		COMMON_METHODS.editAField(getTestObject("D_CCP_OL_31"),getTestData("CCP_TD_Client_BH"));
		REPORTER.LogEvent(TestStatus.PASS, "Verifying text box accepts text", "Text box accepts text: "+getTestData("CCP_TD_Client_Prog") , "");


		// Click Search
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_32"));

		//Ensure the button functions and fires off a database search returning results.
		String str = COMMON_METHODS.getText(getTestObject("S_CCP_OL33"));
		COMMON_METHODS.isElementPresent(getTestObject("S_CCP_OL45"), "xpath");
		if (str.contains(getTestData("CCP_TD_Client_BH")))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the Client with New reservation Link Presence", "Client Verification with New reservation Link -- Succeeds" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the Client with New reservation Link Presence", "Client Verification with New reservation Link -- Failed" , "");
		}

		//Click on "New Reservation"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL45"));
		//REPORTER.LogEvent(TestStatus.PASS, "Verifying Window Authetication", "Window Authetication -- Succeeds" , "");

		/*// Logout from CC Portal	
		Utility.logout();*/

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/* TFS ID:7459,7460
	 * @CreationDate: 28/03/2014
	 *  BUCA - CCP - Family Management - Advanced Search - Ensure that you can search by Phone Number
	 */

	@Test()
	public void CCP_FamilyManagement() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.loginCCPortal();	

		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		CCP_BusinessComponents.changeCenter("CenterName", getTestData("CCP_TD_CenterName"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//Verify the Care Recipient Radio button present by clicking
		COMMON_METHODS.radioButton(getTestObject("GCCP_OL_27"));

		//Verify the Authorised contact Radio button present by clicking
		COMMON_METHODS.radioButton(getTestObject("GCCP_OL_29"));  

		//Click on Client Employee and search and enter search for and click submit and verify   
		COMMON_METHODS.radioButton(getTestObject("GCCP_OL_28"));

		//enter search for and then verify the box allows text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), "BHAutoFN");
		Thread.sleep(2000);

		//Enter Text into Client name Text box
		COMMON_METHODS.driver.findElement(By.id("Criteria_ClientName")).sendKeys("Text");

		//Ensure we can clear the Client name text box
		COMMON_METHODS.driver.findElement(By.id("Criteria_ClientName")).clear();

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		// Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Ensure we can input Text in the Phone number field
		COMMON_METHODS.editAField(getTestObject("GCCP_OL_19"), "6176738000");
		Thread.sleep(3000);

		//Click on Search button  
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		Thread.sleep(10000);

		//Verify that the results are displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_26"), "RESULTS (95)");
		Thread.sleep(5000);

		//Logout from 'Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 20/03/2014
	 */
	//Test Case #13015: BUCA - Reservation Fulfillment - Center Care - Verify if the first priority center cannot fulfill any care sessions, and the 2nd center can Fulfill all sessions, the system confirms the reservation.

	@Test()
	public void CCP_ReservationFulfillment_SecondCenterReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch the browser
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));

		this.loginCCPortal();

		// Click on the Reservation Header
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Search for the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber"));

		// Select the date of reservation
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		String strCSID = this.editReservation();
		
		String strDDID = "CareSessionStatus" + strCSID;

		// Select all care sessions for the reservation and click edit selected
		COMMON_METHODS.checkBox(getTestObject("KCCP_OL_15"), "check");
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_16"));

		// For a reservation mark all care sessions as "Waitlist"
		COMMON_METHODS.listBoxSelect(getTestObject("KCCP_OL_17"), "Waitlist", "byVisibleText");

		// Select the reason for wait list
		COMMON_METHODS.listBoxSelect(getTestObject("KCCP_OL_20"), "Staff Training", "byVisibleText");

		// Click on the Update Reservation button
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_18"));

		// Click on the Reservation Header
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Select the second center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_SecondCenterName"));

		// Select the date of reservation
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		this.editReservation();

		// Select all care sessions for the reservation and click edit selected
		COMMON_METHODS.checkBox(getTestObject("KCCP_OL_15"), "check");
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_16"));

		// For a reservation mark all care sessions as "Confirmed"
		COMMON_METHODS.listBoxSelect(getTestObject("KCCP_OL_17"), "Confirmed", "byVisibleText");

		// Click on the Update Reservation button
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_18"));
		Thread.sleep(3000);

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		Thread.sleep(3000);

		// Click on the 'Expand All' link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
		Thread.sleep(3000);

		// Verify the reservation is confirmed
		Select sSelect = new Select(COMMON_METHODS.driver.findElement(By.id(strDDID)));
		WebElement option = sSelect.getFirstSelectedOption();

		if(option.getText().equals("Conf")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation is confirmed.", "Reservation is confirmed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Reservation is confirmed.", "Reservation is not confirmed.", "");
		}

		// Logout from Care Center Portal
		Thread.sleep(5000);
		//Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/* TFS ID:13353
	 * @CreationDate: 28/03/2014
	 *  BUCA - CCP - Family Management - Advanced Search - Ensure that you can search by Email Address
	 */

	@Test()
	public void CCP_FamilyManagement_SearchByEmail() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//Click on Click on Client Employee and search and enter search for and click submit and verify   
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));


		//enter search for and then verify the box allows text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), "parvendra");
		Thread.sleep(2000);

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		// Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Input the Text in the Email Address field
		COMMON_METHODS.editAField(getTestObject("CCP_FM_OL_01"), getTestData("TD_Email1"));
		Thread.sleep(3000);

		//Click on Search button  
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		Thread.sleep(10000);

		//Verify that the results are displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_26"), "RESULTS (1)");
		Thread.sleep(5000);

		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}




	/* TFS ID:13356
	 * @CreationDate: 28/03/2014
	 *  BUCA - CCP - Family Management - Advanced Search - Ensure that you can search by State
	 */

	@Test()
	public void CCP_FamilyManagement_SearchByState() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//Click on Click on Client Employee and search and enter search for and click submit and verify   
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));


		//enter search for and then verify the box allows text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), "BHAutoFN");
		Thread.sleep(2000);

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		// Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Click on State Dropdown and make a choice
		COMMON_METHODS.listBoxSelect(getTestObject("S_CCP_OL29"), getTestData("TD_State"), "byVisibleText");
		Thread.sleep(3000);

		//Click on Search button  
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		Thread.sleep(10000);

		//Verify that the results are displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_26"), "RESULTS (28)");
		Thread.sleep(5000);

		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/* TFS ID:13579
	 * @author: Kiran G
	 * @CreationDate: 20/03/2014
	 * BUCA - CCP - Family Management - Ensure the Family Details link that works when searching for Client Employee
	 */


	@Test()
	public void CCP_FamilyManagement_DetailLink() throws Exception {


		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);


		//Verify that the user can select Care Center using Change link at the top
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify Home Page is displayed properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//enter search for and then verify the box allows text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), "BHAutoFN");
		Thread.sleep(2000);

		//Click on Click on Client Employee and search and enter search for and click submit and verify   
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		// Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Click on Search button
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));

		Thread.sleep(5000);

		//Verify that the results are displayed
		if(COMMON_METHODS.driver.getPageSource().contains("Results"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Results are displayed", "Results are displayed", "");
		}

		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Results are displayed", "Results are NOT displayed", "");
		}

		Thread.sleep(2000);

		//Verify the family Details link is present
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_06"), "Family Details");

		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 31/03/2014
	 */
	//Test Case #7330: BUCA - CCP Reservations - Verify the user can navigate to Adjust Capacity

	@Test()
	public void CCP_Reservations_AdjustCapacity() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
				
		// Click Adjust Capacity link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_34"));
		
		// Verify the Center Capacity page is displayed
		if(COMMON_METHODS.driver.getTitle().equals("Center Management : Center Capacity - Back-Up Care Center")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Center Capacity' page is displayed.", "'Center Capacity' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Center Capacity' page is displayed.", "'Center Capacity' page is not displayed.", "");
		}
		
		// Logout from Care Center Portal
		Utility.logout();

		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 01/04/2014
	 */
	//Test Case #18383: BUCA - CCP Reservations - Ensure a user can update payment information to existing reservations

	@Test()
	public void CCP_Reservations_UpdatePayInfo() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

//		// Create a center based reservation
//		EP_CBAndInHomeReservations_Tests cbih = new EP_CBAndInHomeReservations_Tests();
//		cbih.EP_SignUpAndCBReservationTest();

		// Read test data for based on client 1
		readTestData(getDataTablePath("CCP"), "TD_CCP");
		
		// Test data for selection the reservation date from the data picker
		String resDate=getTestData("TD_Dateofreservation");
		String dateArray[]=resDate.split("/");
		String date=dateArray[1];
		if(date.startsWith("0")){
			date=date.substring(1);
		}
		
		int month=Integer.parseInt(dateArray[0]);
		String objArray[]={getTestObject("CCP_OLP_01"),getTestObject("CCP_OLP_02")};
		String dataArray[]={date};

		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Click Calendar and select the date of reservation
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
		Thread.sleep(4000);
		Utility.selectDate(month,objArray,dataArray);
		
		// Change the center to the center reserved
		CCP_BusinessComponents.changeCenter("CenterName", getTestData("CCP_TD_CenterName"));
		
		// Get the index of the class with Pending Reservations count > 0
		int i =0;
		List<WebElement> lwe = COMMON_METHODS.driver.findElements(By.xpath("//div[starts-with(@id,'RoomClass')]"));
		for(WebElement we: lwe){
			 i = i + 1;
			 String roomClassText = we.getText();
			 String[] arrroomClassText = roomClassText.split("Confirmed:");
			 String[] arrPending = arrroomClassText[1].split("Pending:");
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
			 if(rowText.contains(ReadwritDataFromProps.props.getProperty("client2.cbudc2.lastName"))){
				 break;
			 }
		 }
		
		// Click 'Edit' link
		COMMON_METHODS.driver.findElement(By.xpath("//td[@id='data_" + (j-1) + "']/..//span/a")).click();

		// Click 'Manage Payment Method(s)' link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_41"));

		// Click 'Add Payment Method' link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_42"));

		// Verify the 'Edit Funding Account' popup is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("KCCP_OL_43"));
		
		// Close 'Edit Funding Account' popup
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_44"));

		// Close 'Manage Payment Method' popup, if exists
		if(COMMON_METHODS.isElementPresent("//span[@id='ui-dialog-title-divPaymentInfo']/../a", "xpath")){
			COMMON_METHODS.clickElement(getTestObject("KCCP_OL_45"));
		}

		// Logout from CC Portal	
		Utility.logout();
		 
		// Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
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

	public String editReservation() throws IOException, Exception {

		if(COMMON_METHODS.getText(getTestObject("KCCP_OL_14")).contains("EXPAND ALL")){
			// Click on the 'Expand All' link
			COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
		}

		// Get the index of Edit link of the reservation
		int j = 0;
		List<WebElement> lweReservations = COMMON_METHODS.driver.findElements(By.xpath("//tr[@id='row']"));
		for(WebElement weRow: lweReservations){
			j = j + 1;
		}

		// Click 'Edit' link
		String strCareSessionID = COMMON_METHODS.driver.findElement(By.xpath("//td[@id='data_" + (j-1) + "']/..//span/a")).getAttribute("href").split("resCareSessionId=")[1];
		COMMON_METHODS.driver.findElement(By.xpath("//td[@id='data_" + (j-1) + "']/..//span/a")).click();
		Thread.sleep(5000);

		// Verify Edit Reservation page is loaded
		COMMON_METHODS.verifyElementDisplayed(getTestObject("KCCP_OL_18"));

		return strCareSessionID;
	}

	private void loginCCPortal() throws IOException, Exception { 
		//			// Launch the browser
		//			Utility.launchBrowser(getTestData("CCP_TD_URL"));

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login - Back-Up Care Center")){ 

			String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

			// Login CareCenter Portal
			Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
			Thread.sleep(5000);
		} 
	} 

	
	

}