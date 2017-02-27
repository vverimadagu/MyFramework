package com.bhs.scripts.providerportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.BusinessComponents.PP_BusinessComponents;
import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.Utility;

public class PP_Reservations_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	PP_BusinessComponents ppbusinessComponents = new PP_BusinessComponents();

	String resNum=null;

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * 
	 * @CreationDate: 19/03/2014
	 * 
	 * @ Test Case #11990: BUCA - Provider - Reservations - Available
	 * Reservations - ensure 'Map Location' link brings up google map pop-up
	 */

	/*
	 * @Test() public void PP_Reservations_MapLocation_Popup() throws Exception
	 * {
	 * 
	 * String methodName =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * // Read test data readTestData(getDataTablePath("PP"), "TD_PP");
	 * 
	 * // Launch the browser Utility.launchBrowser(getTestData("TD_PP_URL"));
	 * String signInArray[]={
	 * getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
	 * 
	 * // Login Provider Portal Utility.loginToBUCA(getTestData("TD_PP_UserID"),
	 * getTestData("TD_PP_PWD"), signInArray);
	 * 
	 * ppbusinessComponents.clickMapLocation("Available Reservations");
	 * 
	 * // Logout from 'Provider Portal' Utility.logout();
	 * 
	 * // Log to reports COMMON_METHODS.logToReportAfterPass(methodName);
	 * 
	 * }
	 */
	/*
	 * @author: Krishna Chaitanya Maringanti
	 * 
	 * @CreationDate: 18/03/2014
	 * 
	 * @ Test Case #11992: BUCA - Provider - Provider Newsletter - ensure page
	 * is loaded correctly (UX doc)
	 */

	/*
	 * @Test() public void PP_Reservations_Navigation() throws Exception {
	 * 
	 * String methodName =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * // Read test data readTestData(getDataTablePath("PP"), "TD_PP");
	 * 
	 * this.loginPPPortal(); // Launch the browser
	 * Utility.launchBrowser(getTestData("TD_PP_URL")); String signInArray[]={
	 * getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
	 * 
	 * // Login Provider Portal Utility.loginToBUCA(getTestData("TD_PP_UserID"),
	 * getTestData("TD_PP_PWD"), signInArray); //Click on Reservations link
	 * COMMON_METHODS.clickElement(getTestObject("OL_10"));
	 * 
	 * // Verify the Reservations page header is displayed
	 * if(COMMON_METHODS.driver
	 * .findElement(By.xpath("//h2[contains(text(),'Available Reservations')]"
	 * )).isDisplayed()){ REPORTER.LogEvent(TestStatus.PASS,
	 * "Verify month link opens a PDF.", "Month link opens a PDF.",""); } else{
	 * REPORTER.LogEvent(TestStatus.FAIL, "Verify month link opens a PDF.",
	 * "Month link does not open a PDF.",""); }
	 * 
	 * // Logout from 'Employee Portal' Utility.logout();;
	 * 
	 * //Log to reports COMMON_METHODS.logToReportAfterPass(methodName); }
	 */

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * 
	 * @CreationDate: 19/03/2014
	 * 
	 * @ Test Case #12084: BUCA - Provider - Reservations - Pending Reservations - Staff - ensure user is brought to a blank form when loading 'Staff
	 * Reservation' page


	@Test()
	public void PP_Reservations_PendingReservations_StaffReservations()
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data
		readTestData(getDataTablePath("PP"), "TD_PP");

		this.loginPPPortal();

	 * // Launch the browser
	 * Utility.launchBrowser(getTestData("TD_PP_URL")); String
	 * signInArray[]={
	 * getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
	 * 
	 * // Login Provider Portal
	 * Utility.loginToBUCA(getTestData("TD_PP_UserID"),
	 * getTestData("TD_PP_PWD"), signInArray);

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPAM_17"));

		// Click on Pending Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPAM_10"));

		// Click on arrow to expand the reservation
		COMMON_METHODS.clickElement(getTestObject("PPAM_15"));

		// Click on Staff Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPAM_16"));

		// Verify the STAFF A RESERVATION page header is displayed
		if (COMMON_METHODS.driver.findElement(
				By.xpath("//h2[text()='STAFF A RESERVATION']")).isDisplayed()) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify 'STAFF A RESERVATION' is displayed.",
					"'STAFF A RESERVATION' is displayed.", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify 'STAFF A RESERVATION' is displayed.",
					"'STAFF A RESERVATION' is not displayed.", "");
		}

		// Logout from 'Provider Portal'
		// Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	 */
	
	/*
	 * To create In Home Reservation in EP so that we can use this in PP.
	 */

	@Test
	public void EP_InHomeReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		this.resNum = businessComponents.EP_InHomeReservation("Client5",
				getTestData("TD_Dateofreservation2"));

		System.out.println("Reservation Number " + resNum);

		// Logout from 'Emp Portal'
		Utility.logout();

		//return resNum;
		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	/*
	 * @author: Krishna Chaitanya Maringanti
	 * 
	 * @CreationDate: 19/03/2014
	 * 
	 * @ Test Case #12096: BUCA - Provider - Reservations - PendingReservations
	 * - Staff - ensure 'Time Remaining' corresponds to the time of exclusivity
	 * displayed on the page
	 * 
	 * @ Test Case #12080: BUCA - Provider - Reservations - Pending
	 *  Reservations -Staff - ensure 'Select a Caregiver' dropdown populates
	 *  from the list of caregivers for the user
	   
	 * Test Case #12082: BUCA - Provider - Reservations - Pending
	 * Reservations -Staff - ensure 'Show/Hide Summary' Links function
	 * correctly

	 */

	@Test
	public void PP_Reservations_PendingReservations_TimeRemaining()
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		Utility.launchBrowser(getTestData("TD_PP_URL"));

		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPAM_17"));

		NavigatetoReservation(this.resNum);

		// Click 'Accept Queue' button
		COMMON_METHODS.clickElement(getTestObject("PPAM_26"));

		// Click Staff It Later button
		COMMON_METHODS.clickElement(getTestObject("PPAM_23"));

		// Click on Pending Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPAM_10"));

		NavigatetoReservation(this.resNum);

		// click Staff Reservation Button and Verify
		COMMON_METHODS.clickElement(getTestObject("PPResrv_Staff"));


		// Verify the Time Remaining field is displayed
		if (COMMON_METHODS.driver.findElement(By.id("clock")).isDisplayed()) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Time Remaining field is displayed.",
					"Time Remaining field is displayed.", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify Time Remaining field is displayed.",
					"Time Remaining field is not displayed.", "");
		}
		
		// Test Case #12080: BUCA - Provider - Reservations - Pending
		// Reservations -Staff - ensure 'Select a Caregiver' dropdown populates
		// from the list of caregivers for the user

		// Select a Caregiver and Verify Caregiver Dropdown populated correctly

		String strCareGiver = COMMON_METHODS.getSelectedValueFromListBox(
				getTestObject("PPSelect_Caregiver")).trim();
		if (strCareGiver.contains("Select an Active Caregiver") == true) {
			REPORTER.LogEvent(TestStatus.PASS, "Verifying Caregiver",
					"Caregiver Verification -- Succeeds", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying Caregiver",
					"Caregiver Verification -- Failed", "");
		}
		
		// Test Case #12082: BUCA - Provider - Reservations - Pending
		// Reservations -Staff - ensure 'Show/Hide Summary' Links function
		// correctly

		// click Show Summary link and Verify
		COMMON_METHODS.clickElement(getTestObject("PPShowSummary"));
		Thread.sleep(2000);
		boolean hide = COMMON_METHODS.isElementPresent("HIDE SUMMARY"
				/*getTestObject("PPHideSummary")*/, "linkText");
		if (hide) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify user can view summary",
					"user can view summary -- Successfully", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify user can view summary",
					"user can not view summary", "");
		}

		// click Hide Summary link and Verify
		COMMON_METHODS.clickElement(getTestObject("PPHideSummary"));
		Thread.sleep(2000);
		boolean show = COMMON_METHODS.isElementPresent("SHOW SUMMARY"
				/*getTestObject("PPShowSummary")*/, "linkText");
		if (show) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify user can not view summary",
					"user can not view summary -- Successfully verified", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify user can view summary",
					"user can view summary -- Verification failed", "");
		}

		// Logout from 'Provider Portal'
		// Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * Provider - Homepage - ensure homepage displays available and pending
	 * reservations TFS ID: 18780
	 */
	@Test
	public void PP_ReservationTypes() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		//Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Click on Available Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_76"));
		Thread.sleep(1000);

		// Click on Pending Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_78"));
		Thread.sleep(1000);

		// Click on Scheduled Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_79"));
		Thread.sleep(1000);

		// Click on Completed Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_80"));
		Thread.sleep(1000);

		// Logout from 'Provider Portal'
		// Utility.logout();;

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of Completed Reservations - ensure
	 * reservation header bar is displayed and functions correctly employee
	 * profile information TFS ID : 11998
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 19/03/2014
	 */

	/*
	 * @Test() public void PP_Reservation_VerifyCRPage() throws Exception {
	 * String methodName =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * // Read test data for based on client 1
	 * readTestData(getDataTablePath("PP"), "TD_PP");
	 * 
	 * this.loginPPPortal();
	 * 
	 * /// Launch the browser Utility.launchBrowser(getTestData("TD_PP_URL"));
	 * String signInArray[]={
	 * getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
	 * 
	 * // Login Provider Portal Utility.loginToBUCA(getTestData("TD_PP_UserID"),
	 * getTestData("TD_PP_PWD"), signInArray);
	 * 
	 * //Click on Reservations link
	 * //COMMON_METHODS.navigateToMenu(getTestObject("CR_06"));
	 * 
	 * //Click on Completed Reservations
	 * COMMON_METHODS.clickElement(getTestObject("CR_07"));
	 * 
	 * //Verify Completed Reservation heading is present
	 * COMMON_METHODS.VerifyTextPresent(getTestObject("CR_01"),
	 * "Completed Reservations ");
	 * 
	 * //Verify View By drop down is present
	 * COMMON_METHODS.verifyElementDisplayed(getTestObject("CR_02"));
	 * 
	 * //Verify Start Date text box is present
	 * COMMON_METHODS.verifyElementDisplayed(getTestObject("CR_03"));
	 * 
	 * //Verify To Date text box is present
	 * COMMON_METHODS.verifyElementDisplayed(getTestObject("CR_04"));
	 * 
	 * //Verify Sort By drop down is present
	 * COMMON_METHODS.verifyElementDisplayed(getTestObject("CR_05"));
	 * 
	 * // Logout from 'Provider Portal' //Utility.logout();;
	 * 
	 * //Log to reports COMMON_METHODS.logToReportAfterPass(methodName); }
	 */

	/**
	 * This test script covers functionality of Reservations - Completed
	 * Reservations - ensure 'View By' dropdown functions correctly employee
	 * profile information TFS ID : 11999
	 * 
	 * This test script covers functionality of Completed Reservations - ensure
	 * reservation header bar is displayed and functions correctly employee
	 * profile information TFS ID : 11998
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 19/03/2014
	 */

	@Test
	public void PP_Reservation_VerifyCRDropdown() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.navigateToMenu(getTestObject("PPCR_06"));

		// Click on Completed Reservations
		COMMON_METHODS.clickElement(getTestObject("PPCR_07"));

		String s1 = COMMON_METHODS.getText(getTestObject("PPCR_01"));

		if (s1.contains("COMPLETED RESERVATIONS")) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verifying Completed Reservations Text " + s1,
					"Completed Reservations Text Verification -- Succeeds", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verifying Completed Reservations Text " + s1,
					"Completed Reservations Text Verification -- Failed", "");
		}

		// Verify View By drop down is present
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPCR_02"));

		// Verify Start Date text box is present
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPCR_03"));

		// Verify To Date text box is present
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPCR_04"));

		// Verify Sort By drop down is present
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPCR_05"));

		// Select all options in ViewBy dropdown
		COMMON_METHODS.listBoxSelect(getTestObject("PPCR_02"), "DateRange",
				"byValue");
		COMMON_METHODS.listBoxSelect(getTestObject("PPCR_02"),
				"ReservationNumber", "byValue");

		/**
		 * Verify Start Date text box is present
		 * businessComponents.verifyElementDisplayed(getTestObject("CR_03"));
		 * 
		 * //Verify To Date text box is present
		 * businessComponents.verifyElementDisplayed(getTestObject("CR_04"));
		 */

		// Verify Sort By drop down is present
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPCR_05"));

		// Select all options in SortBy dropdown
		COMMON_METHODS.listBoxSelect(getTestObject("PPCR_05"), "EmployeeName",
				"byValue");
		COMMON_METHODS.listBoxSelect(getTestObject("PPCR_05"), "CareDate",
				"byValue");
		COMMON_METHODS.listBoxSelect(getTestObject("PPCR_05"),
				"ReservationNumber", "byValue");
		COMMON_METHODS.listBoxSelect(getTestObject("PPCR_05"), "CareGiver",
				"byValue");

		// Logout from 'Provider Portal'
		// Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * 12000 BUCA - Provider - Reservations - Pending Queue - Validate 'Staff
	 * Reservation' link brings user to 'Staff a Reservation' page
	 * 
	 * This test script covers functionality of Reservations - Pending
	 * Reservations - Staff - ensure user is brought to a blank form when
	 * loading 'Staff Reservation' page employee profile information TFS ID :
	 * 12084
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 19/03/2014
	 */
	/*@Test()
	public void PP_Reservation_PRStaffResv() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		readTestData(getDataTablePath("PP"), "TD_PP");

		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.navigateToMenu(getTestObject("PPCR_06"));
		Thread.sleep(6000);

		// Click on Pending Reservations
		COMMON_METHODS.clickElement(getTestObject("PPCR_08"));
		Thread.sleep(6000);
		// Verify Pending reservations page
		// COMMON_METHODS.VerifyTextPresent(getTestObject("CR_09"),
		// "Pending Reservations");

		// Click on Arrow
		COMMON_METHODS.clickElement(getTestObject("PPCR_141"));

		// Click on Staff reservation button
		COMMON_METHODS.clickElement(getTestObject("PPCR_11"));

		// Verify staff reservations page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPCR_12"),
				"Staff A Reservation");

		// Verify staff reservations page
		COMMON_METHODS.checkBox(getTestObject("PPCR_13"), "uncheck");

		// Logout from 'Provider Portal'
		// Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	 */
	/**
	 * This test script covers functionality of Reservations - Pending
	 * Reservations - Staff - ensure user is brought to a blank form when
	 * loading 'Staff Reservation' page employee profile information TFS ID :
	 * 12084
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 19/03/2014
	 */
	/*
	 * @Test() public void PP_Reservation_PRStaffResvBlankForm() throws
	 * Exception { String methodName =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * // Read test data for based on client 1
	 * readTestData(getDataTablePath("PP"), "TD_PP");
	 * 
	 * // Launch the browser Utility.launchBrowser(getTestData("TD_PP_URL"));
	 * String signInArray[]={
	 * getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
	 * 
	 * // Login Provider Portal Utility.loginToBUCA(getTestData("TD_PP_UserID"),
	 * getTestData("TD_PP_PWD"), signInArray);
	 * 
	 * //Click on Reservations link
	 * COMMON_METHODS.clickElement(getTestObject("CR_06"));
	 * 
	 * Thread.sleep(6000); //Click on Pending Reservations
	 * COMMON_METHODS.clickElement(getTestObject("CR_08"));
	 * 
	 * Thread.sleep(6000); //Verify Pending reservations page //
	 * COMMON_METHODS.VerifyTextPresent(getTestObject("CR_09"),
	 * "Pending Reservations");
	 * 
	 * //Click on Arrow COMMON_METHODS.clickElement(getTestObject("CR_10"));
	 * 
	 * //Click on Staff reservation button
	 * COMMON_METHODS.clickElement(getTestObject("CR_11"));
	 * 
	 * //Verify staff reservations page
	 * COMMON_METHODS.checkBox(getTestObject("CR_13"), "uncheck");
	 * 
	 * 
	 * // Logout from 'Provider Portal' Utility.logout();
	 * 
	 * //Log to reports COMMON_METHODS.logToReportAfterPass(methodName); }
	 */

	/*
	 * @author: Sanjeev Singh
	 * 
	 * @CreationDate: 19/03/2014
	 */
	// Test Case #12081: BUCA - Provider - Reservations - Pending Reservations -
	// Staff - ensure 'Staff Reservation' page loads correctly

	/*@Test()
	public void PP_StaffReservationLoads() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		readTestData(getDataTablePath("PP"), "TD_PP");
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();


		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Verify Resrvation Page
		COMMON_METHODS
				.VerifyTextPresent(getTestObject("PPCR_06"), "Reservations");

		// Click "Service Areas" link
		COMMON_METHODS.clickElement(getTestObject("PPPending_Resv_Link"));

		// Verifying Service Area Page
		boolean temp = COMMON_METHODS.isElementPresent(
				getTestObject("PPVerify_Resrv"), "xpath");
		if (temp = true) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Pending reservation",
					"Pending reservation -- Succeeds", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Pending reservation",
					"Pending reservation -- Failed", "");
		}

		// click Staff Reservation Button and Verify
		COMMON_METHODS.clickElement(getTestObject("PPPending_Resv_Arrow"));
		COMMON_METHODS.clickElement(getTestObject("PPResrv_Staff"));

		// Verifying Location Confirmation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPStaff_Reserv_Page"),
				"STAFF A RESERVATION");

		// Logout from Employee Portal Loc_Confirmation
		// Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}*/

	/*
	 * @author: Kiran G
	 * 
	 * @CreationDate: 19/03/2014
	 * 
	 * @ Test Case #13096: BUCA - Reservation Fulfillment - In Home - Verify a
	 * Provider can mark a pending in home reservation assigned to them as Not
	 * Interested, Staff It Later, Confirmed (staffed)
	 * 
	 * @ Test Case #11989: BUCA - Provider - Reservations - Available
	 * Reservations - ensure tabbed fields function correctly (UX doc)
	 */

	@Test
	public void PP_AvailableReservations_Links() throws Exception {

		// Since we have an open defect for selecting Caregiver,hence we have
		// just scripted the TC

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		 String resNum1=EP_InHomeReservation("Client2");
		
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Click on Available Reservations
		//COMMON_METHODS.clickElement(getTestObject("PPMA_76"));
		Thread.sleep(1000);

		NavigatetoReservation(resNum1);

		Thread.sleep(5000);
		 /** @ Test Case #11989: BUCA - Provider - Reservations - Available
		  Reservations - ensure tabbed fields function correctly (UX doc)*/
		// Click on the arrow of Available reservations
		//COMMON_METHODS.clickElement(getTestObject("PPMA_94"));

		// Click on Care Environment
		COMMON_METHODS.clickElement(getTestObject("PPMA_95"));

		// Click on Care Instructions
		COMMON_METHODS.clickElement(getTestObject("PPMA_96"));

		// Click on Special Requests
		COMMON_METHODS.clickElement(getTestObject("PPMA_97"));
		
		/**End of #11989*/
		
		// Click on Not Interested link
		//COMMON_METHODS.clickElement(getTestObject("PPMA_98"));
		ppbusinessComponents.clickNotInterestedLink();

		// Click on Cancel button on the Popup
		COMMON_METHODS.clickElement(getTestObject("PPMA_99"));

		// Click on Accept Queue button
		COMMON_METHODS.clickElement(getTestObject("PPMA_187"));

		// Click on Staff Reservation button on Popup
		COMMON_METHODS.clickElement(getTestObject("PPMA_188"));

		// Select the option from the listbox
		COMMON_METHODS.listBoxSelect(getTestObject("PPMA_190"), "#1", "byIndex");
		
		// Check the Accept policies checkbox
		COMMON_METHODS.checkBox(getTestObject("PPMA_189"), "check");

		// Click on Submit Staff Request button
		COMMON_METHODS.clickElement(getTestObject("PPMA_191"));

		// Logout from 'Provider Portal'
		//	Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	/*
	 * @author: Sanjeev Singh
	 * 
	 * @CreationDate: 19/03/2014
	 
	// Test Case #12080: BUCA - Provider - Reservations - Pending Reservations -Staff - ensure 'Select a Caregiver' dropdown populates from the list of caregivers for the user

	@Test
	public void PP_EnsureSelectCaregiverForPendingReservation()
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		resNum= EP_InHomeReservation("Client2");

		System.out.println(resNum);
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Verify Resrvation Page
		COMMON_METHODS
		.VerifyTextPresent(getTestObject("PPCR_06"), "Reservations");

		// Click "Pending Reservation" link
		COMMON_METHODS.clickElement(getTestObject("PPPending_Resv_Link"));

		// Verifying Pending Reservation Page
		boolean temp = COMMON_METHODS.isElementPresent(
				getTestObject("PPVerify_Resrv"), "xpath");
		if (temp = true) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Pending reservation",
					"Pending reservation -- Succeeds", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Pending reservation",
					"Pending reservation -- Failed", "");
		}
		NavigatetoReservation(resNum);

		// click Staff Reservation Button and Verify
		COMMON_METHODS.clickElement(getTestObject("PPResrv_Staff"));

		// Verifying Location Confirmation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPStaff_Reserv_Page"),
				"STAFF A RESERVATION");

		// Select a Caregiver and Verify Caregiver Dropdown populated correctly
		//COMMON_METHODS.clickElement(getTestObject("PPSelect_Caregiver"));
		String strCareGiver = COMMON_METHODS.getSelectedValueFromListBox(
				getTestObject("PPSelect_Caregiver")).trim();
		if (strCareGiver.contains("Select an Active Caregiver") == true) {
			REPORTER.LogEvent(TestStatus.PASS, "Verifying Caregiver",
					"Caregiver Verification -- Succeeds", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying Caregiver",
					"Caregiver Verification -- Failed", "");
		}

		// Logout from Employee Portal Loc_Confirmation
		// Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}*/

	/*
	 * @author: Sanjeev Singh
	 * 
	 * @CreationDate: 19/03/2014
	 */
	// Test Case #12082: BUCA - Provider - Reservations - Pending Reservations -Staff - ensure 'Show/Hide Summary' Links function correctly

	/*@Test
	public void PP_EnsureShowHideLinkFunctionality() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		//resNum=EP_InHomeReservation();


		System.out.println(resNum);
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Verify Resrvation Page
		COMMON_METHODS
		.VerifyTextPresent(getTestObject("PPCR_06"), "Reservations");

		// Click "Pending reservations" link
		COMMON_METHODS.clickElement(getTestObject("PPPending_Resv_Link"));

		// Verifying pending reservation Page
		boolean temp = COMMON_METHODS.isElementPresent(
				getTestObject("PPVerify_Resrv"), "xpath");
		if (temp = true) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Pending reservation",
					"Pending reservation -- Succeeds", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Pending reservation",
					"Pending reservation -- Failed", "");
		}

		NavigatetoReservation(resNum);

		// click Staff Reservation Button and Verify
		COMMON_METHODS.clickElement(getTestObject("PPResrv_Staff"));

		// Verifying Location Confirmation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPStaff_Reserv_Page"),
				"STAFF A RESERVATION");

		// click Show Summary link and Verify
		COMMON_METHODS.clickElement(getTestObject("PPShowSummary"));
		boolean hide = COMMON_METHODS.isElementPresent(
				getTestObject("PPHideSummary"), "linkText");
		if (hide) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify user can view summary",
					"user can view summary -- Successfully", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify user can view summary",
					"user can not view summary", "");
		}

		// click Hide Summary link and Verify
		COMMON_METHODS.clickElement(getTestObject("PPHideSummary"));
		boolean show = COMMON_METHODS.isElementPresent(
				getTestObject("PPShowSummary"), "linkText");
		if (show) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify user can not view summary",
					"user can not view summary -- Successfully verified", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify user can view summary",
					"user can view summary -- Verification failed", "");
		}

		// Logout from Employee Portal Loc_Confirmation
		// Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}*/

	/*
	 * @author: Sanjeev Singh
	 * 
	 * @CreationDate: 20/03/2014
	 
	// Test Case #12083: BUCA - Provider - Reservations - Pending Reservations -
	// Staff - ensure 'Map Location' link functions correctly
	// TFS 13105:BUCA - Reservation Fulfillment - In Home - Verify if a provider
	// clicks Staff Later it goes into the pending queue until the exclusivity
	// period ends

	@Test
	public void PP_EnsureMapLocationFunctionality() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		//	resNum=EP_InHomeReservation();

		System.out.println(resNum);
		Utility.launchBrowser(getTestData("TD_PP_URL"));

		this.loginPPPortal();

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		ppbusinessComponents.clickMapLocation("Pending Reservations");

		NavigatetoReservation(resNum);

		COMMON_METHODS.clickElement(getTestObject("PPResrv_Staff"));

		// Verifying Location Confirmation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPStaff_Reserv_Page"),
				"STAFF A RESERVATION");

		// click Show Summary link and Verify
		COMMON_METHODS.clickElement(getTestObject("PPShowSummary"));
		boolean Hide = COMMON_METHODS.isElementPresent(
				getTestObject("PPHideSummary"), "linkText");
		if (Hide = true) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify user can view summary",
					"user can view summary -- Successfully", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify user can view summary",
					"user can not view summary", "");
		}

		// Logout from Employee Portal Loc_Confirmation
		// Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}*/

	
	
	/*
	 * @author: Sanjeev Singh
	 * 
	 * @CreationDate: 20/03/2014
	 * 
	 * Test Case #13506: BUCA - Reservation Fulfillment - In Home - Verify after
	 * a reservation is marked as "Staff Later" a user can return and mark it as
	 * Unavailable In Home - Verify a Provider can mark a pending in home
	 * reservation assigned to them as Accept Queue(Staff It Later) TFS ID :
	 * 18355
	 * 
	 * 
	 * /*
	 * 
	 * @CreationDate: 19/03/2014
	 * 
	 * @ Test Case #12092: BUCA - Provider - Reservations - PendingReservations
	 * - Staff - ensure 'Cancel' button functions correctly- Test Case #7592:
	 * BUCA - Provider - Reservations - Available Reservations - ensure user can
	 * view 'Available Reservations' page (Matching UX doc) TFS ID -12081 - BUCA
	 * - Provider - Reservations - Pending Reservations - Staff - ensure 'Staff
	 * Reservation' page loads correctly 12000 BUCA - Provider - Reservations -
	 * Pending Queue - Validate 'Staff Reservation' link brings user to 'Staff a
	 * Reservation' page
	 * 
	 * TFS ID :12084 - Pending Reservations - Staff - ensure user is brought to
	 * a blank form when loading 'Staff Reservation' page employee profile
	 * information
	 */
	 

	@Test
	public void PP_StaffLater() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		//String resNum=EP_InHomeReservation();

		System.out.println(this.resNum);		//Utility.launchBrowser(getTestData("TD_PP_URL"));

		this.loginPPPortal();

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Verify Reservation Page
		COMMON_METHODS
		.VerifyTextPresent(getTestObject("PPCR_06"), "Reservations");

		// Click "Pending Resrvation" link
		COMMON_METHODS.clickElement(getTestObject("PPMA_78"));
		Thread.sleep(10000);
		String s2 = COMMON_METHODS.getText(getTestObject("PPCR_01"));

		if (s2.contains("PENDING RESERVATIONS")) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify PENDING reservation Text " + s2,
					"PENDING reservation Text Verification -- Succeeds", "");
		} else {
			REPORTER.LogEvent(TestStatus.WARNING,
					"Verifying PENDING reservation Text " + s2,
					"PENDING reservation Text Verification -- Failed", "");
		}

		//Navigate to the Reservation
		NavigatetoReservation(this.resNum);	  
		
		// Click on Staff Reservation button
		COMMON_METHODS.clickElement(getTestObject("PPCR_11"));
		Thread.sleep(5000);

		// Verify staff reservations page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPCR_12"),
				"Staff A Reservation");

		
		// Click on cancel button
		COMMON_METHODS.clickElement(getTestObject("PPMA_185"));
		
		// Verify the Pending Reservations page  is displayed
		if (COMMON_METHODS.driver.findElement(
				By.xpath("//h2[contains(text(),'Pending Reservations')]"))
				.isDisplayed()) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify 'Pending Reservations' is displayed.",
					"'Pending Reservations' is displayed.", "");
		} else {
			REPORTER.LogEvent(TestStatus.WARNING,
					"Verify 'Pending Reservations' is displayed.",
					"'Pending Reservations' is not displayed.", "");
		}
		
		//Navigate to the Reservation
		NavigatetoReservation(this.resNum);
		
		//Click on Not Interested link
		//COMMON_METHODS.clickElement(getTestObject("PPMA_98"));
		ppbusinessComponents.clickNotInterestedLink();
		// Unavailable
		COMMON_METHODS.clickElement(getTestObject("PPCR_142"));
		COMMON_METHODS.clickElement(getTestObject("PPCR_143"));

		// Verifying the Reservation is not present there

		// Logout from Employee Portal Loc_Confirmation
		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @CreationDate: 19/03/2014
	 * 
	 * @ Test Case #11989: BUCA - Provider - Reservations - Available
	 * Reservations - ensure tabbed fields function correctly (UX doc)
	 */

	/*@Test
	public void PP_Reservations_Tabs() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Click on Available Reservations
		//		COMMON_METHODS.clickElement(getTestObject("PPMA_76"));
		Thread.sleep(1000);

		// Click on the arrow of Available reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_94"));

		// Click on Care Environment
		COMMON_METHODS.clickElement(getTestObject("PPMA_95"));

		// Click on Care Instructions
		COMMON_METHODS.clickElement(getTestObject("PPMA_96"));

		// Click on Special Requests
		COMMON_METHODS.clickElement(getTestObject("PPMA_97"));

		// Click on Not Interested link
		COMMON_METHODS.clickElement(getTestObject("PPMA_98"));

		// Click on Cancel button on the Popup
		COMMON_METHODS.clickElement(getTestObject("PPMA_99"));

		// Logout from 'Employee Portal'
		// Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}*/

	/*
	 * @CreationDate: 19/03/2014
	 * 
	 * @ Test Case #11995: BUCA - Provider - Reservations - Completed
	 * Reservations - ensure 'Feedback' link brings user to the 'Feedback' page
	 * - ensure tabbed fields function correctly (UX doc)
	 */

	@Test
	public void PP_Reservations_Feedback() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);		//Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();


		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Click on Completed Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_80"));
		Thread.sleep(1000);

		//Click on down arrow
		COMMON_METHODS.clickElement(getTestObject("PPCR_14"));

		// Click 'Feedback' link
		COMMON_METHODS.clickElement(getTestObject("PPCR_144"));

		// Verify Feedback text in the Feedback page
		businessComponents.EP_verifyText("Feedback", "Feedback", "h1");

		// Select any option from the drop down
		COMMON_METHODS.listBoxSelect(getTestObject("PPMA_100"),
				getTestData("TD_PP_Feedback_PP"), "byVisibleText");

		// Type 'test feedback' in 'Comments' text box
		COMMON_METHODS.editAField(getTestObject("PPMA_101"), "Test feedback");

		// Click on Submit Feedback button
		COMMON_METHODS.clickElement(getTestObject("PPMA_102"));

		// Verify the Text on the Feedback submitted page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPMA_103"),
				"Thank you for submitting your feedback.");

		// Logout from 'Employee Portal'//
		//Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*
	 * @CreationDate: 19/03/2014
	 * 
	 * @ Test Case #12092: BUCA - Provider - Reservations - PendingReservations
	 * - Staff - ensure 'Cancel' button functions correctly-
	 * Test Case #7592: BUCA - Provider - Reservations - Available
	 * Reservations - ensure user can view 'Available Reservations' page
	 * (Matching UX doc)
	 * TFS ID -12081 - BUCA - Provider - Reservations - Pending Reservations - Staff - ensure 'Staff Reservation' page loads correctly
	 * 12000 BUCA - Provider - Reservations - Pending Queue - Validate 'Staff Reservation' link brings user to 'Staff a Reservation' page
	 * 
	 * TFS ID :12084 - Pending Reservations - Staff - ensure user is brought to a blank form when loading 'Staff Reservation' page employee profile information 
	 */

	/*@Test
	public void PP_Pending_Reservations_Staff_Cancel() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		//	String resNum=EP_InHomeReservation();

		System.out.println(resNum);
		System.out.println("Reservation Number " + resNum);

		Utility.launchBrowser(getTestData("TD_PP_URL"));
		// Launch the browser
		this.loginPPPortal();

		//Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Click on Pending Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_78"));
		Thread.sleep(1000);

		NavigatetoReservation(resNum);

		// Click on Staff Reservation button
		COMMON_METHODS.clickElement(getTestObject("PPCR_11"));
		Thread.sleep(2000);

		// Verify staff reservations page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPCR_12"),
				"Staff A Reservation");

		// Click on cancel button
		COMMON_METHODS.clickElement(getTestObject("PPMA_185"));

		// Verify the Pending Reservations page  is displayed
		if (COMMON_METHODS.driver.findElement(
				By.xpath("//h2[contains(text(),'Pending Reservations')]"))
				.isDisplayed()) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify 'Pending Reservations' is displayed.",
					"'Pending Reservations' is displayed.", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify 'Pending Reservations' is displayed.",
					"'Pending Reservations' is not displayed.", "");
		}

		// Click on Pending Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_78"));
		Thread.sleep(1000);

		NavigatetoReservation(resNum);

		// Click on Staff Reservation button
		COMMON_METHODS.clickElement(getTestObject("PPCR_11"));
		Thread.sleep(2000);

		// Verify staff reservations page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPCR_12"),
				"Staff A Reservation");
		

		// Logout from 'Provider Portal'
		//Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
*/

	/*
	 * @author: Sanjeev Singh
	 * 
	 * @CreationDate: 28/03/2014
	 */
	// Test Case #7068: BUCA - Provider - Account Management - ensure user is
	// able to add Service Areas (via file upload and by submitting text)
	/*
	 * This Test Case #21741 is covered in this flow: BUCA - Provider Portal -
	 * Add Service Areas: Submit Service Areas - Verify that the user is having
	 * the access for 'Service Areas' feature in the BUCA web application
	 */

	@Test
	public void PP_AddServiceAreas() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		//Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click 'Account Management' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("PPAM_01"));

		// Verifying Care Account Management Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPAM_01"),
				"Account Management");
		

		// Click "Service Areas" link
		COMMON_METHODS.clickElement(getTestObject("PPService_Area"));

		// Verifying Service Area Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPVerify_Loc"),
				"Service Areas");
		
		//Save Service Area 
		COMMON_METHODS.clickElement(getTestObject("PPSave_Loc"));
		
		//Verify Validation Messages
		for(int i=1;i<=3;i++){
			String getText=COMMON_METHODS.driver.findElement(By.xpath("//div[@class='validation-summary-errors']/ul/li["+i+"]")).getText();
			if(getText.equalsIgnoreCase("ServiceAreas required")||getText.equalsIgnoreCase("StateList required")
			   ||getText.equalsIgnoreCase("CountyList required"))
			REPORTER.LogEvent(TestStatus.PASS, "Validation Message is correct", 
					("Validation Message is " +getText).toUpperCase(),"");
			else
				REPORTER.LogEvent(TestStatus.PASS, "Validation Message failed", 
						("Validation Message is " +getText).toUpperCase(),"");
		}
		

		// Select State
		COMMON_METHODS.listBoxSelect(getTestObject("PPSA_01"), "Illinois", "byVisibleText");
		
		// Select Country
		COMMON_METHODS.listBoxSelect(getTestObject("PPSA_02"), "Cook", "byVisibleText");
		
		//Click Add
		COMMON_METHODS.clickElement(getTestObject("PPPOL_24"));
		
		//Enter Postal code
		COMMON_METHODS.editAField(getTestObject("PPLoc_Text"), "60601");
		
		//Save Service Area 
		COMMON_METHODS.clickElement(getTestObject("PPSave_Loc"));

		// Verifying Location Confirmation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPLoc_Confirmation"),
				"A new Service Area was successfully submitted.");

		// Logout from Employee Portal Loc_Confirmation
		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	
	/**
	 * This test script covers functionality of BUCA - Provider - Reservations -
	 * Completed Reservations - ensure 'Map Location' link functions correctly
	 * BUCA - Provider - Reservations - Scheduled Reservations - ensure 'Map
	 * Location' link functions correctly TFS ID : 11997,12021
	 * 
	 * @CreationDate: 19/03/2014 @ Test Case #11990: BUCA - Provider -
	 *                Reservations - Available Reservations - ensure 'Map
	 *                Location' link brings up google map pop-up
	 * 
	 *                Test Case #11992: BUCA - Provider - Provider Newsletter -
	 *                ensure page is loaded correctly (UX doc)
	 * 
	 * @param TC
	 * @throws Exception
	 * TFS ID:12070:BUCA - Provider - Reservations - Scheduled Reservations - Change Staff - ensure 'Map Location' 
	 * link functions correctly
	 * 
	 * TFS ID:12083:BUCA - Provider - Reservations - Pending Reservations - Staff - ensure 'Map Location' link functions correctly
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 02/04/2014
	 */
	@Test
	public void CR_MapLocation() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data
		//	readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		//Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Verify Available Reservations - ensure 'Map Location' link functions
		// correctly
		ppbusinessComponents.clickMapLocation("Available Reservations");

		// Verify Completed Reservations - ensure 'Map Location' link functions
		// correctly
		ppbusinessComponents.clickMapLocation("Completed Reservations");

		// Verify Scheduled Reservations - ensure 'Map Location' link functions
		// correctly
		ppbusinessComponents.clickMapLocation("Scheduled Reservations");

		// Logout from 'Provider Portal'
		// Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	/**
	 * This test script covers functionality of BUCA - Provider - Reservations -
	 * Completed Reservations - ensure 'View Authorization' link functions
	 * correctly BUCA - Provider - Reservations - Scheduled Reservations -
	 * ensure 'View Authorization' link functions correctly TFS ID : 11996,12022
	 * TFS ID:20822:PP - Completed reservations -> Verify content of Authorization
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 02/04/2014
	 */
	@Test
	public void CR_ViewAuthorization() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data
		//Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		ppbusinessComponents.viewAuthorization("Completed Reservations");

		ppbusinessComponents.viewAuthorization("Scheduled Reservations");

		// Logout from 'Provider Portal'
		//Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	/**
	 * This test script covers functionality of
	 *  BUCA - Provider - Reservations - Scheduled Reservations - Cancel - Validate 'Change or Cancel Reservation'
	 * page. 
	 * BUCA - Provider - Reservations - Scheduled Reservations - ChangeStaff - ensure 'New Caregiver' dropdown populates from the list of
	 * caregivers for the user 
	 * BUCA - Provider - Reservations - Scheduled Reservations - Change Staff - ensure 'Reason for Change' dropdown has all
	 * options 
	 * BUCA - Provider - Reservations - Scheduled Reservations - Change Staff - ensure 'Show/Hide Summary' links function correctly 
	 * BUCA - Provider - Reservations - Scheduled Reservations - ensure 'Sort By' dropdown functions correctly 
	 * BUCA - Provider - Reservations - Scheduled Reservations - ensure 'Change Caregiver' link brings user to 'Change
	 * Caregiver Staffing' page
	 * BUCA - Provider - Reservations - Scheduled Reservations - Change Staff - ensure 'Staff' button sends data to CRM
	 *  
	 * TFS ID : 12037,12066,12068,12061,12064, 12020,12026
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 03/04/2014
	 */

	@Test
	public void SR_ChangeCareGiverStaffingPage() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// / Launch the browser
		//Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		PP_ReservationFulfillment_Tests obj=new PP_ReservationFulfillment_Tests();
		String resNum=obj.PP_StaffLater();

		// Click on 'Reservations' link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Click Scheduled Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPMA_79"));

		String objArray2[]={"ul","id","li"};
		String objArrayPage2[]={resNum,getTestObject("PPMA_193")};
		int pageNo2=Utility.goToLastPageInGridList(objArray2);
		try{
			if(pageNo2!=0){
				COMMON_METHODS.driver.findElement(By.xpath("//ul[@id='pagination-clean']/li["+(pageNo2-1)+"]")).click();
				Thread.sleep(5000);
				REPORTER.LogEvent(TestStatus.PASS, "Click on Pagination click", "Click on Pagination click - Successfull".toUpperCase(),"");
			}
		}
		catch(Exception e){
			REPORTER.catchException(e, "Unable to click on last page in pagination List grid");
		}
		Thread.sleep(5000);
		Utility.selectAvailResInPP(objArrayPage2); 
		COMMON_METHODS.driver.findElement(
				By.xpath("//li[@class='resnumber' and text()='"
						+ objArrayPage2[0] + "']/../li[5]/a")).click();
		Thread.sleep(6000);

		COMMON_METHODS.getAllOptions(getTestObject("PPSR_06"));


		// Click on Change CareGiver link
		//	  COMMON_METHODS.driver.findElement(By.xpath("//div[@id='sched" + (j-1) + "Scheduled']/div/ul/li[2]/a[2]")).click();

		Thread.sleep(6000);

		String title = BH_SetUp_TearDown.driver.getTitle();

		if (title.equalsIgnoreCase("Change Caregiver Staffing"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify " + title
					+ " Page is loaded", "Verify " + title
					+ " Page is loaded -- SUCCESSFULL", "");

		COMMON_METHODS.getAllOptions(getTestObject("PPSR_03"));

		COMMON_METHODS.getAllOptions(getTestObject("PPSR_07"));

		// Click on Show Summary
		COMMON_METHODS.clickElement(getTestObject("PPSR_04"));

		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPSR_05"));

		COMMON_METHODS.clickElement(getTestObject("PPSR_04"));

		Utility.logout();

	}

	/*
	 * @CreationDate: 27/03/2014
	 * 
	 * @ Test Case #20788: PP -Available queue -> test accept queue timeline ->
	 * care is more than 48 hours away
	 * 
	 * @Sanjeev Singh
	 */

	@Test
	public void PP_ReservationForMoreThan48Hours() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		String resNum = EP_InHomeReservation("Client2");

		System.out.println("Reservation Number " + resNum);

		// Logout from 'Employee Portal'
		//	Utility.logout();

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Verify Reservation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPCR_06"), "Reservations");

		/*// Click on Available Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_76"));*/
		Thread.sleep(1000);

		NavigatetoReservation(resNum);

		// Logout from 'Provider Portal'
		Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	/*
	 * @CreationDate: 27/03/2014
	 * 
	 * @ Test Case #20787: PP -Available queue -> test exclusivity timeline ->
	 * care is within 24.1 - 48 hours Close Editor
	 * 
	 * @Sanjeev Singh
	 */

	@Test
	public void PP_ReservationFor24to48Hours() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		String resNum =EP_InHomeReservation("Client2");
		System.out.println("Reservation Number " + resNum);

		Utility.launchBrowser(getTestData("TD_PP_URL"));
		// Launch the browser
		this.loginPPPortal();

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));

		// Verify Reservation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPCR_06"), "Reservations");

		// Click on Available Reservations
		//COMMON_METHODS.clickElement(getTestObject("PPMA_76"));
		Thread.sleep(1000);

		NavigatetoReservation(resNum);

		// Logout from 'Provider Portal'
		Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	

	
	


	

	

	
	
	/* TFS ID:12070
	 * @author: Sanjeev Singh
	 * @CreationDate: 02/04/2014
	 *   BUCA - Provider - Reservations - Scheduled Reservations - Change Staff - ensure 'Map Location' link functions correctly
	 


	@Test()
	public void PP_ScheduledReservationsLinksValidation() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//readTestObject(getDataTablePath("EP"), "TO_EP");
		//readTestData(getDataTablePath("PP"), "TD_PP5");
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		// Create a center based reservation
		//EP_CBAndInHomeReservations_Tests cbih = new EP_CBAndInHomeReservations_Tests();
		//cbih.EP_SignUpAndCBReservationTest();
		
		this.EP_InHomeReservation("5");

		//Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		this.loginPPPortal();

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		//Verify Resrvation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"), "Reservations");

		//Click "Scheduled Resrvation" link		
		COMMON_METHODS.clickElement(getTestObject("s_Sch_Resv_Link"));
		COMMON_METHODS.VerifyTextPresent(getTestObject("s_Sch_Resv_Link"), COMMON_METHODS.getText(getTestObject("s_Sch_Verify_Link")));
		
		
		
		// Get the index of the class with Pending Reservations count > 0
		int i =0;
		List<WebElement> lwe = COMMON_METHODS.driver.findElements(By.xpath("//div[@class='formModHdrSm resHdr')]"));
		for(WebElement we: lwe){
			 i = i + 1;
			 String roomClassText = we.getText();
			 if(roomClassText.contains(ReadwritDataFromProps.props.getProperty("client2.cbudc2.firstName") + " " + ReadwritDataFromProps.props.getProperty("client2.cbudc2.lastName"))){
				 break;
			 }
		 }
		
		// Click the arrow
		COMMON_METHODS.driver.findElement(By.xpath("//div[@class='formModHdrSm resHdr'][" + i + "]/ul/li/a")).click();
		
		// click 'Change Caregiver' link
		COMMON_METHODS.clickElement(getTestObject("s_Caregiver"));
		
		//user has clicked Change Caregiver link
		COMMON_METHODS.isElementPresent(getTestObject("s_Caregiver"), "xpath");
		
		// click 'Show Summary' link
		COMMON_METHODS.clickElement(getTestObject("ShowSummary"));
		
		boolean Hide = COMMON_METHODS.isElementPresent(getTestObject("HideSummary"), "linkText");
		if (Hide)
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verify user can view summary", "user can view summary -- Successfully" , "");
		}
		else
		{	
			REPORTER.LogEvent(TestStatus.FAIL, "Verify user can view summary", "user can not view summary" , "");				
		}
		
		// click Map Location link -- verify Pop up and Close that pop up
		COMMON_METHODS.clickElement(getTestObject("Map_Location"));
		Thread.sleep(2000);
		COMMON_METHODS.switchToPopup();
		COMMON_METHODS.isElementPresent(getTestObject("Map_Location"), "id");
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Map Location Popup", "Map Location Popup -- Verified Successfully" , "");
		COMMON_METHODS.clickElement(getTestObject("Close_PopUp"));;

		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}*/

	private void loginPPPortal() throws IOException, Exception {

		if (COMMON_METHODS.driver.getTitle().equalsIgnoreCase(
				"Login - Back-up Care Advantage Provider Portal")) {
			// Verify Login Page displayed

			// Login to Emp Portal
			String signInArray[] = { getTestObject("PPOL_1"),
					getTestObject("PPOL_2"), getTestObject("PPOL_3") };
			Utility.loginToBUCA(getTestData("TD_PP_UserID"),
					getTestData("TD_PP_PWD"), signInArray);

		}
	}

	private void NavigatetoReservation(String resNum)  throws IOException, Exception
	{
		String objArray[]={"ul","id","li"};
		String objArrayPage[]={resNum,getTestObject("PPMA_193")};
		int pageNo=Utility.goToLastPageInGridList(objArray);
		try{
			if(pageNo!=0){
				COMMON_METHODS.driver.findElement(By.xpath("//ul[@id='pagination-clean']/li["+(pageNo-1)+"]")).click();
				Thread.sleep(5000);
				REPORTER.LogEvent(TestStatus.PASS, "Click on Pagination click", "Click on Pagination click - Successfull".toUpperCase(),"");
			}
		}
		catch(Exception e){
			REPORTER.catchException(e, "Unable to click on last page in pagination List grid");
		}
		Thread.sleep(5000);
		Utility.selectAvailResInPP(objArrayPage); 
		COMMON_METHODS.driver.findElement(
				By.xpath("//li[@class='resnumber' and text()='"
						+ objArrayPage[0] + "']/../li[4]/a")).click();


	}
	
	public String EP_InHomeReservation(String client) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		/*String signInArray[] = { getTestObject("OL_1"), getTestObject("OL_2"),
				getTestObject("OL_3") };*/

		String resNum = businessComponents.EP_InHomeReservation(client,
				getTestData("TD_Dateofreservation2"));

		System.out.println("Reservation Number " + resNum);

		// Logout from 'Emp Portal'
		Utility.logout();

		return resNum;

	}
}
