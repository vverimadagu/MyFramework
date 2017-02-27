package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;
import com.bhs.util.INITIALIZE.TestStatus;

public class EP_Edit_Reservation_Test extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	CCP_BusinessComponents ccpBusinessComponents = new CCP_BusinessComponents();
	String employer=null;
	
	/** 
	* @author Satya
	* @version 
	* @return 
	* @param 
	* @CreationDate: 31/03/2014
	*/ 
	
	@Test @Parameters("client")
	public void EP_SignupUserEditReservation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		this.employer = employer;
		
		/*//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");*/

		//launch browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		businessComponents.EP_verifyEmployer(employer);
		
		businessComponents.EP_AcceptPolicyAndSubmit();
		
		//Create dynamic user name
		String userName = createDyanamicUserData();

		//Sign Up
		businessComponents.EP_SignUpUser(userName,employer,"editres");
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		//Click on Employee Profile icon
		COMMON_METHODS.clickElement(getTestObject("OL_134"));
		
		businessComponents.EP_UpdateEmployeeprofile(employer);
		
		//User will be navigated to the Care Profile page with Green check mark on the employee profile icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_138"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Test data for adding Care Recipients
		String addCrData[] = {
				getTestData("TD_CR_FirstName"),
				getTestData("TD_CR_LastName"), "Son",
				getTestData("TDU1_DOB2"),
				getTestObject("OL_61"), getTestObject("OL_47"),
				getTestObject("OL_48"), getTestObject("OL_49"),
				getTestObject("OL_50"), getTestObject("OL_51"),
				getTestObject("OL_52"),
				getTestData("TD_AddInfo"),
				getTestObject("OL_54"), getTestObject("OL_55"),
				getTestData("TDU1_STATE1"),
				getTestData("TD_AddInfo"),
				getTestObject("OL_100"), null,
				getTestData("TDU1_CENTERLOCATION1"),
				null, null };	
		
		businessComponents.addCareRecipients(addCrData, "No",employer);
		
		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
					
		//User will be navigated to the Care Profile page with Green check mark on the care recipients icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_139"));
		
		//Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));
		
		businessComponents.EP_AddAuthorizedContacts();
		
		/*String[] reservationdate = {getTestData("TD_Dateofreservation")};
		
		this.EP_CBreservation(reservationdate);*/
		
		this.EP_InHomeReservation(getTestData("TD_Dateofreservation"));
		
		//businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #22642 
	 *CSC BUCA - Step 5: Edit Care Session-application should  allow a user to make changes by selecting the �Edit� 
	 *link on step 5 of a New Reservation process.
	 * 
	 */

	@Test
	public void EP_Res_Edit_CareSession_Application() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		
		/*COMMON_METHODS.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client90.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
		
		//businessComponents.EP_ReservationWhenandWhere();
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		//Click on Edit on Care Sessionss
		COMMON_METHODS.clickElement(getTestObject("RS_EDIT_01"));
		
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
		
		//businessComponents.EP_ReservationWhenandWhere();
		
		//Create  WhenandWhere Reservation
		String[] careDates2 = {getTestData("TD_Dateofreservation")};
		String[] actions2 = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates2,actions2);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
		
		businessComponents.EP_ReservationReveiwDetails();
	
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #22704 
	 *CSC BUCA - Edit Reservation - Step 3: Verify if Client Employee can update information for a In Home Reservation
	 * 
	 */

	@Test
	public void EP_Res_Edit_InHome() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		
		this.loginEPPoratl();
		
		/*String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(employer+".editres.userName"), getTestData("TD_PWD"),signInArray);
		*/
		this.EP_InHomeReservation(getTestData("TD_Edit_DOR2"));
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
				
		Thread.sleep(3000);
		
		//Ensure the system loads the reservation for editing.
		
		System.out.println(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString());
		
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString().contains("EDIT RESERVATION")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Edit Reservation Page displayed", "Edit Reservation page is dispaled", "");			
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Edit Reservation Page displayed", "Edit Reservation page not dispaled", "");
		}
		
		//Click on 'Edit Care Options' link present on the 'In-Home Care Options' section
		COMMON_METHODS.clickElement(getTestObject("RS_EDIT_04"));
		
		// TODO: need to Verify if the application allow the user to update the residential/Hotel radio buttons
		// because error display after clicking 'Edit Care Options' link Bug #25491
		
	}
	
	/**
	 *Test Case #22796 
	 *CSC BUCA : Step 5: Nanny Share - Display the Nanny Share field for CCP users on Step5 of Reservation process. 
	 *
	 * 
	 */

	@Test
	public void EP_Res_Edit_Display_NannyShare_Field() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		// Click the 'CHANGE' link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_4"));
		
		// Enter the center name in the search field
		COMMON_METHODS.editAField(getTestObject("CCP_OL_5"), getTestData("CCP_TD_CenterName"));
		
		// Click Search button
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));
		
		// Click 'SELECT' link 
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_7"));
		
		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		// Select Family Management Authorized Contacts radio option
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL22"));
		
		//enter search for and then verify the box allows text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), "author2 saaa");
		Thread.sleep(2000);

		//Click on Search button
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		Thread.sleep(3000);
		
		/*CCP_BusinessComponents.CCP_CreateReservation();*/
		
		//Click on New Reservation
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_51"));
		
		//Select Reason for Care
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_33"), "Looking for regular care", "byVisibleText");
		
		//Click on Care Recipient
		COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_34"), "check");
		
		/*//Click on second Care Recipient
		COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_49"), "check");*/
		
		//Select Healthy radio button
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_35"));
		
		//Click on Continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_36"));
		
		//Enter Reservaton date
		COMMON_METHODS.editAField(getTestObject("D_CCP_OL_37"),getTestData("CCP_TD_Dateofreservation1")+","+getTestData("CCP_TD_Dateofreservation2"));
				
		//Click on Time from field
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_52"));
		
		COMMON_METHODS.DragandDrop(getTestObject("D_CCP_OL_38"),60, 0);
		
		//Click on Done
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_54"));
		
		//Click on Time to field
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_53"));
		
		COMMON_METHODS.DragandDrop(getTestObject("D_CCP_OL_39"),100,0);
		
		//Click on Done
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_54"));
		
		//Click on Continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_40"));
		
		 //Select Class
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_41"), "Early Learners", "byVisibleText");
		
		//Select status
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_42"), "Confirmed", "byVisibleText");
	
		//Select status
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_56"), "Confirmed", "byVisibleText");
		
		//Click on continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_43"));
		
		//Select Yes Radio button
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_44"));
		
		//Select Yes Radio button
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_57"));
		
		//Click on continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_36"));
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #23089 
	 *CSC BUCA: Edit Reservation - Reservation Contact In-Home Only: Verify edit Releaser�s name by Client Employee 
	 *
	 * 
	 */

	@Test
	public void EP_Res_Edit_RelearsersName() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		
		/*Utility.launchBrowser("TD_EP_URL");*/
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client90.userName"), getTestData("TD_PWD"));
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
				
		Thread.sleep(3000);
		
		//Ensure the system loads the reservation for editing.
		
		System.out.println(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString());
		
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString().contains("EDIT RESERVATION")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Edit Reservation Page displayed", "Edit Reservation page is dispaled", "");			
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Edit Reservation Page displayed", "Edit Reservation page not dispaled", "");
		}
		
		// Verify below fields should be displayed under Releaser�s name --a. 'Same as the Greeter' (checkbox) 
		// b. 'Name' (text box) c. 'Relationship' (text box)
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("IH_102"), "true", "value");
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("RS_EDIT_02"), getTestData("TD_GreeterName"));
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("RS_EDIT_03"), getTestData("TD_GreetRelation"));
		
		// edit the Releaser name
		COMMON_METHODS.editAField(getTestObject("RS_EDIT_02"), "Adams2");
		
		// edit the Relationship 
		COMMON_METHODS.editAField(getTestObject("RS_EDIT_03"), "Mother2");
		
		// Update Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_15"));
				
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #23068 
	 *CSC BUCA ;Edit Reservation - Update Reservation: Verify if the Client Employee changes 'the 'Greeter' or 'Releaser' 
	 *for a In Home reservation 
	 *
	 * 
	 */

	@Test
	public void EP_Res_Edit_Greeter_InHome() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client90.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
				
		Thread.sleep(3000);
		
		//Ensure the system loads the reservation for editing.
		
		System.out.println(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString());
		
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString().contains("EDIT RESERVATION")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Edit Reservation Page displayed", "Edit Reservation page is dispaled", "");			
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Edit Reservation Page displayed", "Edit Reservation page not dispaled", "");
		}
		
		
		//Change the Greeter's 'Name'
		COMMON_METHODS.editAField(getTestObject("IH_100"), "EditAdams");
		
		//Change the 'Relationship'
		COMMON_METHODS.editAField(getTestObject("IH_101"), "EditsMother");
		
		// Update Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_15"));
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #22822 
	 *CSC BUCA: Edit Reservation - Step 3: Verify if Client Employee can update information for a In Home Reservation 
	 *
	 *
	 * 
	 */

	@Test
	public void EP_Res_Update_Info_InHome() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client90.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		this.EP_InHomeReservation(getTestData("TD_Edit_DOR3"));
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
				
		Thread.sleep(3000);
		
		//Ensure the system loads the reservation for editing.
		
		System.out.println(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString());
		
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString().contains("EDIT RESERVATION")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Edit Reservation Page displayed", "Edit Reservation page is dispaled", "");			
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Edit Reservation Page displayed", "Edit Reservation page not dispaled", "");
		}
		
		//Click on 'Edit Care Options' link present on the 'In-Home Care Options' section
		COMMON_METHODS.clickElement(getTestObject("RS_EDIT_04"));
		
		// TODO: need to Verify if the application allow the user to update the residential/Hotel radio buttons
		// because error display after clicking 'Edit Care Options' link Bug #25491
		
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #22661
	 *CSC BUCA - Step 5: Payment Information and Manage Payment Methods - Imputed Income message should display 
	 *dynamically for a client with imputed  income set up in CRM.
	 * 
	 */

	@Test
	public void EP_Res_Verify_ImputedIncome_Msg() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client90.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		//Click on Edit on Care Sessions
		COMMON_METHODS.clickElement(getTestObject("RS_EDIT_01"));
		
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
		
		//Create  WhenandWhere Reservation
		String[] careDates2 = {getTestData("TD_Dateofreservation")};
		String actions2[] = {"Locations","Continue"};
		businessComponents.EP_ReservationWhenandWhere(careDates2,actions2);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
		
		//Verify the Imputed Income message displayed or not
		COMMON_METHODS.VerifyTextPresent(getTestObject("RS_EDIT_01"), "Imputed Income message");
		
		businessComponents.EP_ReservationReveiwDetails();
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #23156
	 *CSC BUCA - Edit Reservation - Care Recipients: The application 
	 *should enable Care Center user to edit the Care Recipient information while on a Reservation, from the Reservations tab and clicking 'edit' next to a care session 
	 * 
	 */

	@Test
	public void EP_Res_Edit_CareRecipient_Info() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		/*businessComponents.EP_LaunchBrowser();*/
		
		/*//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client91.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");

				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		businessComponents.EP_ReservationReveiwDetails();
	
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #22840
	 *CSC BUCA - Edit Reservation - Reservation Comments: The changes done for Reservation comments on one session should be available on other Sessions of the same Care Recipient 
	 * 
	 */

	@Test
	public void EP_Res_Edit_ReservationComments() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client91.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");

				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Edit_DOR2"));
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		businessComponents.EP_ReservationReveiwDetails();
	
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #22635
	 *CSC BUCA -Step 5: Application should display the information "Care/Special Instructions,Health Information 
	 *and Restrictions and Upload Documents Required for care" as applicable (for each care-recipient) on step5. 
	 * 
	 */

	@Test
	public void EP_Res_Display_CareRecipient() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
	
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal("BHC91U04022014143041", getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		addCareReceipt(getTestData("TD_ADD_CR_FIRSTNAME"),getTestData("TD_ADD_CR_LASTNAME"));
		addCareReceipt(getTestData("TD_ADD_CR_FIRSTNAME2"),getTestData("TD_ADD_CR_LASTNAME2"));
	
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes",employer);
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		//Select a 'Reason for Care'
		COMMON_METHODS.listBoxSelect(getTestObject("OL_81"), getTestData("TD_ReasonForCare"), "byVisibleText");
		
		/*//Select any Care Recipient, by checking check box next to name
		COMMON_METHODS.checkBox(getTestObject("OL_82"), "check");
		
		//Select Health Status of the selected Care Recipient
		COMMON_METHODS.radioButton(getTestObject("OL_83"));*/
		
		for (int careRece=0;careRece < 3;careRece++) {
			
			BH_SetUp_TearDown.driver.findElement(By.id("Reservation_ReservationCareRecipients_" + careRece + "__IsSelected")).click();
			BH_SetUp_TearDown.driver.findElement(By.id("Reservation_ReservationCareRecipients_" + careRece + "__IsHealthy")).click();
		}
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
		COMMON_METHODS.editAField(getTestObject("OL_93"), "Care Special Instructions 1");
		
		//Select 'No' radio button for 'Any changes to profile details like allergies, etc.?'
		COMMON_METHODS.radioButton(getTestObject("OL_94"));
		
		//Enter any value in 'Care / Special Instructions' field for all the Care Recipients2
			COMMON_METHODS.editAField(getTestObject("IHR_01"), "Care Special Instructions 2");
			
		//Select 'No' radio button for 'Any changes to profile details like allergies, etc.?'
		COMMON_METHODS.radioButton(getTestObject("IHR_02"));
			
		//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
		COMMON_METHODS.editAField(getTestObject("IHR_03"), "Care Special Instructions 3");
			
		//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
		COMMON_METHODS.radioButton(getTestObject("IHR_04"));
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));

		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
		
		//Verify the care recipients are displayed with the details "Care/Special Instructions,Health Information and 
		// Restrictions and Upload Documents Required for care" on Step5 of reservation.
		String sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='resRecipientCareMod']/ul/li[2]/p")).getText().trim();
		
		if (sTemp.contains("Care Special Instructions 1")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Care/Special Instructions displayed for " +  getTestData("TD_CR_FirstName")+" "+getTestData("TD_CR_LastName")  + " care recipient 1", "Care/Special Instructions - " + sTemp + " displayed" , "");
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Care/Special Instructions displayed for " +  getTestData("TD_CR_FirstName")+" "+getTestData("TD_CR_LastName")  + " care recipient 1", "Care/Special Instructions - " + sTemp + " not displayed" , "");
		}
		
		sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='resRecipientCareMod'][2]/ul/li[2]/p")).getText().trim();
		
		if (sTemp.contains("Care Special Instructions 2")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Care/Special Instructions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME") + " " +getTestData("TD_ADD_CR_LASTNAME")  + " care recipient 2", "Care/Special Instructions - " + sTemp + " displayed" , "");
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Care/Special Instructions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME") + " " +getTestData("TD_ADD_CR_LASTNAME")  + " care recipient 2", "Care/Special Instructions - " + sTemp + " not displayed" , "");
		}
		
		sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='resRecipientCareMod'][2]/ul/li[2]/p[2]/span")).getText().trim();
		
		if (sTemp.contains("Food Restrictions")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Health Information and Restrictions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME") + " " +getTestData("TD_ADD_CR_LASTNAME")  + "  care recipient 2", "Health Information and Restrictions - " + sTemp + " displayed" , "");
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Health Information and Restrictions displayed for  " +  getTestData("TD_ADD_CR_FIRSTNAME") + " " +getTestData("TD_ADD_CR_LASTNAME")  + " care recipient 2", "Health Information and Restrictionss - " + sTemp + " not displayed" , "");
		}
		
		sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='resRecipientCareMod'][2]/ul/li[2]/p[2]/span[2]")).getText().trim();
		
		if (sTemp.contains("Food Allergies")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Health Information and Restrictions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME") + " " +getTestData("TD_ADD_CR_LASTNAME")  + "  care recipient 2", "Health Information and Restrictions - " + sTemp + " displayed" , "");
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Health Information and Restrictions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME") + " " +getTestData("TD_ADD_CR_LASTNAME")  + "  care recipient 2", "Health Information and Restrictionss - " + sTemp + " not displayed" , "");
		}
		
		sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='resRecipientCareMod'][3]/ul/li[2]/p")).getText().trim();
		
		if (sTemp.contains("Care Special Instructions 3")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Care/Special Instructions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME2") + " " +getTestData("TD_ADD_CR_LASTNAME2")  + "  care recipient 3", "Care/Special Instructions - " + sTemp + " displayed" , "");
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Care/Special Instructions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME2") + " " +getTestData("TD_ADD_CR_LASTNAME2")  + "  care recipient 3", "Care/Special Instructions - " + sTemp + " not displayed" , "");
		}
		
		
		sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='resRecipientCareMod'][3]/ul/li[2]/p[2]/span")).getText().trim();
		
		if (sTemp.contains("Food Restrictions")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Health Information and Restrictions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME2") + " " +getTestData("TD_ADD_CR_LASTNAME2")  + "  care recipient 3", "Health Information and Restrictions - " + sTemp + " displayed" , "");
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Health Information and Restrictions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME2") + " " +getTestData("TD_ADD_CR_LASTNAME2")  + "  care recipient 3", "Health Information and Restrictionss - " + sTemp + " not displayed" , "");
		}
		
		sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='resRecipientCareMod'][3]/ul/li[2]/p[2]/span[2]")).getText().trim();
		
		if (sTemp.contains("Food Allergies")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Health Information and Restrictions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME2") + " " +getTestData("TD_ADD_CR_LASTNAME2")  + "  care recipient 3", "Health Information and Restrictions - " + sTemp + " displayed" , "");
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Health Information and Restrictions displayed for " +  getTestData("TD_ADD_CR_FIRSTNAME2") + " " +getTestData("TD_ADD_CR_LASTNAME2")  + "  care recipient 3", "Health Information and Restrictionss - " + sTemp + " not displayed" , "");
		}
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #22932 
	 *CSC BUCA :Edit Reservation - Reservation Contact: Verify the user can ignore Warning while deactivating the 
	 *only Authorized Contact designated as Emergency contact 
	 * 
	 */

	@Test
	public void EP_Res_Edit_Ign_WarningDeactivitingAC() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client91.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Edit_DOR4"));
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		//Click on Edit on Care Sessions
		COMMON_METHODS.clickElement(getTestObject("RS_EDIT_01"));
		
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
		
		//Create  WhenandWhere Reservation
		String[] careDates2 = {getTestData("TD_Dateofreservation")};
		String actions2[] = {"Locations","Continue"};
		businessComponents.EP_ReservationWhenandWhere(careDates2,actions2);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
		
		businessComponents.EP_ReservationReveiwDetails();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
 	
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
				
		Thread.sleep(4000);
		
		//Click on the name of Emergency Contact, present in 'Reservation Contacts� section
		COMMON_METHODS.clickElement(getTestObject("RS_EDIT_04"));
		
		//Click on Inactive option
		COMMON_METHODS.clickElement(getTestObject("AUTH_INACTIVE"));

		Thread.sleep(10000);

		//Verify the Error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("AUTH_01"),"This contact is associated with an existing reservation. Please add a new authorized contact.");

		//Click on OK button
		COMMON_METHODS.clickElement(getTestObject("OK_btn"));

		//Add an Inactive comment
		COMMON_METHODS.editAField(getTestObject("AUTH_INACTIVE_COMM"), "Inactive");

		//Click on update Authorised contact button
		COMMON_METHODS.clickElement(getTestObject("OL_69"));
		
		/*********  Verify the Emergency contact marked as inactive is not present under 'Reservation Contacts� section **************/
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
				
		Thread.sleep(3000);
		
		//verify that name of Emergency Contact should not be present in 'Reservation Contacts� section
		
		if (!COMMON_METHODS.isElementPresent("//a[contains(@href,'AuthorizedContactList')]", "xpath"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify the Emergency contact marked as inactive is not present under 'Reservation Contacts� section", "Emergency contact is not displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the Emergency contact marked as inactive is not present under 'Reservation Contacts� section", "Emergency contact is displayed", "");

		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #23071 
	 *CSC BUCA :Edit Reservation - Update Reservation: Verify if the Client Employee can change 
	 *'care recipient information' for a reservation  
	 * 
	 */

	@Test
	public void EP_Res_Edit_Update_CareRecipient() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client91.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Edit_DOR4"));
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		businessComponents.EP_ReservationReveiwDetails();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
 	
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
				
		Thread.sleep(3000);
		
		//Click on Name of any Care Recipient present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("ER_CR"));
		
		//Make Changes in any of the fields in the Care Recipients Page
		
		//Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_42"), getTestData("TD_CR_FirstName") + "Mod");
						
		//Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_43"), getTestData("TD_CR_LastName") + "Mod");

		//Click 'Update Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Verify the application should update the changes and take the user back to the reservations detail p
		//age with the updated Care Recipient information
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString().contains("EDIT RESERVATION")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify the user back to the Edit reservations detail page", "Edit reservations detail page is displayed", "");			
		}else {
			REPORTER.LogEvent(TestStatus.PASS, "Verify the user back to the Edit reservations detail page", "Edit reservations detail page is not displayed", "");
		}
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
// TFS #23121 - CSC BUCA :Edit Reservation - In- Home Care Options: Verify 'EDIT CARE OPTIONS� link for Client Employee
	
	@Test
	public void EP_EditReservation_EditCareOptions() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

/*		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");*/

		/*// Launch Employee Portal
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Create an In-home reservation
		businessComponents.EP_InHomeReservation(2, getTestData("TD_Dateofreservation2"));*/
		
		/*Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		employer = "Client2";*/
		
		this.loginEPPoratl();
		
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		// Click on "View" link on a reservation (Either Confirm or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		Thread.sleep(10000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));
		
		// Click the Edit Reservation button
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));
		
		Thread.sleep(2000);
		
		// Click the Edit Care Options link
		COMMON_METHODS.clickElement(getTestObject("EDIT_RES_CN"));
		
		Thread.sleep(2000);
		
		// Verify 'Available Care Options' page is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("ROL_04"));
		
		/*************  
		Test Case #23068 
		 *CSC BUCA ;Edit Reservation - Update Reservation: Verify if the Client Employee changes 'the 'Greeter' or 'Releaser' 
		 *for a In Home reservation
		******************/
		
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		// Click on "View" link on a reservation (Either Confirm or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		Thread.sleep(10000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));
		
		// Click the Edit Reservation button
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));
		
		Thread.sleep(2000);
		
		// Click Edit Greeter link 
		COMMON_METHODS.clickElement(getTestObject("EDIT_RES_EG"));
		
		Thread.sleep(1000);
		
		//Change the Greeter's 'Name'
		COMMON_METHODS.editAField(getTestObject("IH_100"), "EditAdams");
				
		//Change the 'Relationship'
		COMMON_METHODS.editAField(getTestObject("IH_101"), "EditsMother");
				
		// Update Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_15"));
		
		/*************  
		 *Test Case #23089 
		 *CSC BUCA: Edit Reservation - Reservation Contact In-Home Only: Verify edit Releaser�s name by Client Employee 
		 *
		 * 
		 ***************/

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click on "View" link on a reservation
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
				
		Thread.sleep(3000);
		
		//Ensure the system loads the reservation for editing.
		
		System.out.println(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString());
		
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString().contains("EDIT RESERVATION")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Edit Reservation Page displayed", "Edit Reservation page is dispaled", "");			
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Edit Reservation Page displayed", "Edit Reservation page not dispaled", "");
		}
		
		// Verify below fields should be displayed under Releaser�s name --a. 'Same as the Greeter' (checkbox) 
		// b. 'Name' (text box) c. 'Relationship' (text box)
		
		//COMMON_METHODS.VerifyTextPresent(getTestObject("IH_102"), "true", "value");
		
		// Click Edit Greeter link 
		COMMON_METHODS.clickElement(getTestObject("EDIT_RES_ER"));
		
		Thread.sleep(1000);
		
		/*COMMON_METHODS.VerifyTextPresent(getTestObject("RS_EDIT_02"), getTestData("TD_GreeterName"));
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("RS_EDIT_03"), getTestData("TD_GreetRelation"));*/
		
		// edit the Releaser name
		COMMON_METHODS.editAField(getTestObject("RS_EDIT_02"), "Adams2");
		
		// edit the Relationship 
		COMMON_METHODS.editAField(getTestObject("RS_EDIT_03"), "Mother2");
		
		// Update Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_15"));
		
		
		
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	/**
	 * TFS ID: 12214 - Reservation Wizard - BUCA - Reservation Wizard - Edit Reservations - ensure the Provider name links function correctly when editting a session
	 * TFS ID: 23078 - CSC BUCA :Edit Reservation - Change Center Location Details: Verify 'CHANGE CENTER LOCATION DETAILS� link is present for Center based Reservation
	 */
	@Test
	public void EP_Reservation_EditReservation_CareProviderLink() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
	
		/*// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");*/

		/*// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		// Create an Center based reservation
		businessComponents.EP_CenterBasedReservation(2, getTestData("TD_Dateofreservation2"));*/

		this.loginEPPoratl();
		
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
				
		// Select the Reservations In Progress
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "In Progress", "byVisibleText");
				
		// Click on "View" link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
				
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		
		// Verify the CHANGE CENTER LOCATION DETAILS link is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("EP_ChangeCenterLocDetails"));
		
		// Click on a Care Provider Link
		COMMON_METHODS.clickElement(getTestObject("Care_Providers1"));
		
		// Verify the pop-up is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RESV_16"));
		
		// Close the pop-up
		COMMON_METHODS.clickElement(getTestObject("MA_38"));
		
		// Logout from Employee Portal
		 Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_InHomeReservation(String dateOfReservation) throws IOException, Exception  {
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Edit_DOR4"));
		
		//businessComponents.EP_ReservationWhenandWhere();
		businessComponents.ResWhenandWhereLocationInhome();
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(3000);
			
		//Filling and checking whether the continue button is Enabled and going to the next page.
		businessComponents.EP_ReservationInHomeCareOptions("CareOptions");
		
		/*//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		businessComponents.EP_ReservationCareOptions();*/
		
		Thread.sleep(2000);
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
		
		COMMON_METHODS.editAField(getTestObject("IH_100"), getTestData("TD_GreeterName"));
		
		COMMON_METHODS.editAField(getTestObject("IH_101"), getTestData("TD_GreetRelation"));
		
		COMMON_METHODS.checkBox(getTestObject("IH_102"), "check"); 
		
		businessComponents.EP_ReservationReveiwDetails();
		
	}
	
	public void addCareReceipt(String firstName,String lastName) throws IOException, Exception
	{
		//Click 'Care Profile' link from top navigation menu
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		 //Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
				
				
		String addCr[] = {
				
				//First name
				firstName,
				
				//Last name
				//getTestData("TD_ADD_CR_LASTNAME"),
				lastName,
				
				//Relationship to Client Employee*
				"Daughter",
				
				// DOB
				getTestData("TD_ADD_CR_DOB"),
				
				//Age
				getTestObject("OL_46"), 
				
				//Health Information and Restrictions
				getTestObject("CARE_Food_RES"),
				getTestObject("CARE_Food_ALL"), 
				getTestObject("OL_49"),
				getTestObject("OL_50"), 
				getTestObject("OL_51"),
				getTestObject("OL_52"),
				
				//Other Helpful Information
				getTestData("TD_AddInfo"),
				
				//Legal Guardian?*  
				getTestObject("OL_54"), 
				//Custody Issues/ Visitation Orders** 
				getTestObject("OL_55"),
				
				getTestData("TD_ADD_STATE"),
				getTestData("TD_AddInfo"),
				getTestObject("OL_100"), null,
				null,
				null, null,"Food Restrictions**","Food Allergies**" };

		// Click 'Add' link present in 'Care Recipients' section
		//COMMON_METHODS.clickElement(getTestObject("OL_41"));
		businessComponents.addCareRecipients(addCr,"No",employer);
		
		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		
		// Verify the newly added Care Recipient is present under
		// 'Care Recipients' section
		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(
					By.linkText(firstName
							+ " " + lastName))
					.getText().trim();
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Care Recipient created",
					"Care Recipient - " + sTemp + " Created", "");
		} catch (Exception e) {
			REPORTER.catchException(e,
					"Verify Care Recipient created");
		} 
	}
	
	/* PREREQUISITE: A Center based reservation is required which is in INPROGRESS status
	  * @author: Kiran G
	  * @CreationDate: 16/04/2014
	  * TFS ID:23078:CSC BUCA - Edit Reservation - Step 3: Verify if Care Center user can update information for a Center Based Reservation
	  * TFS ID:22963:CSC BUCA: Edit Reservation - Verify if Client Employee can change Location of Care for a reservation
	  * TFS ID:22962:SC BUCA: Edit Reservation - Step 3: Verify if Client Employee can update information for a In Home Reservation
	  * TFS ID:23081:CSC BUCA :Edit Reservation - Change Center Location Details: Verify 'CHANGE CENTER LOCATION DETAILS� link for Benefit Eligible Employee
	  * PREREQUISITE: A Center based reservation is required which is in INPROGRESS status
	  */


	 @Test
	 public void EP_Step3ChangeCenterloc() throws Exception {
	  
	  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	  System.out.println("Inside - " + methodName);
	  
	  /*readTestData(getDataTablePath("EP"), "TD_EP5");*/
	  
	  /*Utility.launchBrowser(getTestData("TD_EP_URL"));*/

	  // Create a center based reservation
	  String Resv = businessComponents.EP_CenterBasedReservation(employer,getTestData("TD_Dateofreservation1"));
	    
	  //Click on Reservations tab
	  COMMON_METHODS.clickElement(getTestObject("OL_10"));
	  
	  //Select Inprogress Reservation from the dropdown
	  COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "In Progress", "byVisibleText");
	  
	  //Click on View link
	  COMMON_METHODS.clickElement(getTestObject("RESV_02"));
	  Thread.sleep(5000);
	  
	  //Click on Edit/Cancel reservation
	  COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));
	  
	  //Verify that the Change Center location is present
	  COMMON_METHODS.isElementPresent("CHANGE CENTER LOCATION DETAILS", "linkText");
	  
	  //Click on Change Center location
	  COMMON_METHODS.clickElement(getTestObject("KMA_82"));
	  Thread.sleep(5000);
	  
	  //Click on back button
	  COMMON_METHODS.clickElement(getTestObject("RS_01"));
	  
	  //Verify that the Add a New location link is present
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_60"), "Add New Location");
	  
	  //Verify that At/Near a Location button is present
	  COMMON_METHODS.isElementPresent("radNearALocation", "id");
	  
	  //Verify that Along a Route button is present
	  COMMON_METHODS.isElementPresent("radAlongRoute", "id");
	  
	  //Verify that Distance dropdown is present
	  COMMON_METHODS.isElementPresent("DistanceList", "id");
	  
	  //Verify that Location dropdown is present
	  COMMON_METHODS.isElementPresent("SelectedLocationId", "id");
	  
	  //Verify that Saved location is present in the listbox
	  COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_89"), "Home");
	  
	  //Click on Along route radio button
	  COMMON_METHODS.clickElement(getTestObject("ROL_19"));
	  
	  //Verify that Point A and Point B location dropdown are displayed
	  COMMON_METHODS.isElementPresent("SelectedLocationIdPointA", "id");
	  COMMON_METHODS.isElementPresent("SelectedLocationIdPointB", "id");
	  
	  //Click on At/Near a Location button
	  COMMON_METHODS.clickElement(getTestObject("ROL_03"));
	  
	  //Click on Add a New location link
	  COMMON_METHODS.clickElement(getTestObject("ROL_60"));
	  
	  //Adding new location
	  businessComponents.EP_AddNewLocation_Reservation("Home1");
	  
	  //Click on back button
	  COMMON_METHODS.clickElement(getTestObject("RS_01"));
	  
	  //Verify that the Add a New location link is present
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_60"), "Add New Location");
	  
	  //Verify the newly added location in the location dropdown
	  COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_89"), "Home1");
	  
	  //Select  different location from 'Location' dropdown present under 'Select The Location For Care' section contains
	  COMMON_METHODS.listBoxSelect(getTestObject("OL_89"), "Home1", "byVisibleText");
	  
	  //Click on Continue button
	  COMMON_METHODS.clickElement(getTestObject("OL_84"));
	  
	  //Verify that the Step 3 page is displayed
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
	  Thread.sleep(2000);
	    
	  //Logout from EP
	  Utility.logout();
	 
	 }
	 
	
	 /* @author: Kiran G
	  * @CreationDate: 16/04/2014
	  * TFS ID:13523:BUCA - Reservation Fulfillment - Center Care - Verify reservations that are saved as a 
	  * Draft do not enter the Reservation fulfillment workflow
	  */
	 
	 @Test @Parameters("client")
	 public void EP_Reservation_Draft(String employer) throws Exception {

	  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	  System.out.println("Inside - " + methodName);

	  /*readTestData(getDataTablePath("EP"), "TD_EP5");*/
	  Utility.launchBrowser(getTestData("TD_EP_URL"));

	  // Verify Employer
	  businessComponents.EP_verifyEmployer(employer);

	  // Accept policy
	  businessComponents.EP_AcceptPolicyAndSubmit();

	  //Create dynamic user name
	  String userName = createDyanamicUserData();

	  // SignUp User
	  businessComponents.EP_SignUpUser(userName,employer,"cbudc5");

	  //Test data for adding Care Recipients
	  String addCrData3_5Years[] = {
	    getTestData("TD_CR_FirstName"),
	    getTestData("TD_CR_LastName"), "Son",
	    getTestData("TDU1_DOB2"),
	    getTestObject("OL_61"), getTestObject("OL_47"),
	    getTestObject("OL_48"), getTestObject("OL_49"),
	    getTestObject("OL_50"), getTestObject("OL_51"),
	    getTestObject("OL_52"),
	    getTestData("TD_AddInfo"),
	    getTestObject("OL_54"), getTestObject("OL_55"),
	    getTestData("TDU1_STATE1"),
	    getTestData("TD_AddInfo"),
	    getTestObject("OL_100"), null,
	    getTestData("TDU1_CENTERLOCATION1"),
	    null, null }; 
	  //Register User
	  businessComponents.EP_Registration(addCrData3_5Years,"No",employer);


	  //Click 'Reservations' link from top navigation menu
	  COMMON_METHODS.clickElement(getTestObject("OL_10"));

	  //Click 'Request a New Reservation' link from top navigation menu
	  COMMON_METHODS.clickElement(getTestObject("OL_11"));

	  //verify User is brought to Step 1 of the reservation
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");

	  //Create Care Recipients Reservation
	  int HealthStatus[] = {1};
	  String selectForReason = getTestData("TD_ReasonForCare");
	  businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
	  "Yes");

	  //Verify the wizard moves to step 2.
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");

	  //Create  WhenandWhere Reservation
	  String strDateofReservation = getTestData("TD_Dateofreservation1");
	  String[] careDates = {strDateofReservation};
	  String actions[] = {"Locations","Continue",null};
	  businessComponents.EP_ReservationWhenandWhere(careDates,actions);
	  
	  Thread.sleep(3000);
	  //Verify the wizard moves to step 3.
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
	  
	  //Click 'Center - based Care' button at the top of 'Available Care Options' section
	  COMMON_METHODS.clickElement(getTestObject("OL_90"));
	  
	  //Logout from EP
	  Utility.logout();
	  
	  //Launch CCP
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

	  //Click on Reservations to check the different Classrooms
	  COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
	  Thread.sleep(3000);

	  //Verify the Reservation page is displayed
	  if(COMMON_METHODS.driver.getPageSource().contains("Reservations:"))
	  {
	   REPORTER.LogEvent(TestStatus.PASS, "Reservations Page is displayed", "Reservations Page is displayed", "");
	  }

	  else
	  {
	   REPORTER.LogEvent(TestStatus.FAIL, "Reservations Page is displayed", "Reservations Page is NOT displayed", "");
	  }

	  //Click on Calendar, choose another date and Ensure the date chose loads
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
	  
	  // Click on the Reservation Header
	  COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));

	  // Click on the 'Expand All' link
	  COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));
	  
	  if(COMMON_METHODS.driver.getPageSource().contains(ReadwritDataFromProps.props.getProperty(employer + ".cbudc5.lastName") + ", " + ReadwritDataFromProps.props.getProperty(employer +".cbudc5.firstName"))){
	     
	   REPORTER.LogEvent(TestStatus.FAIL, "Reservation is created in CCP", "Reservation is created in CCP", "");
	    }  
	  else
	    {
	     REPORTER.LogEvent(TestStatus.PASS, "Reservation is created in CCP", "Reservation is NOT created in CCP", "");
	    }
	  //Logout from PP
	  Utility.logout();
	  
	   }
	 
	 /* @author: Kiran G
	  * @CreationDate: 18/04/2014
	  * TFS ID:23579:BUCA - BUCA - Client Employee Portal- Care Locations -Time zone Display & Save: 
	  * Verify the time zones on the 3rd step of Reservation process 
	  * TFS ID:24715:CSC BUCA - Validate Location Information - Verify on Step 2 of a Reservation, 
	  * if Location Information is missing and the user clicks 'Continue�, Application display an error 
	  * message helptext indicating that Location is required for reservation
	  * TFS ID:24716: CSC BUCA - Validate Location Information - Verify For an In-Home Care Reservation, 
	  * If Location is available but Information within location that is required for a reservation identified
	  * with **, is missing and the user clicks 'Continue� on Step
	  * TFS ID:24717:CSC BUCA -Validate Location Information - Verify For a Center-Based Reservation, 
	  * the user should leave at least one provider as acceptable or choose at least one as the First 
	  * Choice on Step 3
	  */

	 @Test(priority=18) @Parameters("client")
	 public void EP_ResvStep3Hours(String employer) throws Exception {

	  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	  System.out.println("Inside - " + methodName);

	  /*readTestData(getDataTablePath("EP"), "TD_EP5");*/
	  Utility.launchBrowser(getTestData("TD_EP_URL"));

	  businessComponents.EP_verifyEmployer(employer);

	  // Accept policy
	  businessComponents.EP_AcceptPolicyAndSubmit();

	  //Create dynamic user name
	  String userName = createDyanamicUserData();

	  // SignUp User
	  businessComponents.EP_SignUpUser(userName,employer,"cbudc5");

	  //Test data for adding Care Recipients
	  String addCrData3_5Years[] = {
	    getTestData("TD_CR_FirstName"),
	    getTestData("TD_CR_LastName"), "Son",
	    getTestData("TDU1_DOB2"),
	    getTestObject("OL_61"), getTestObject("OL_47"),
	    getTestObject("OL_48"), getTestObject("OL_49"),
	    getTestObject("OL_50"), getTestObject("OL_51"),
	    getTestObject("OL_52"),
	    getTestData("TD_AddInfo"),
	    getTestObject("OL_54"), getTestObject("OL_55"),
	    getTestData("TDU1_STATE1"),
	    getTestData("TD_AddInfo"),
	    getTestObject("OL_100"), null,
	    getTestData("TDU1_CENTERLOCATION1"),
	    null, null }; 
	  //Register User
	  businessComponents.EP_Registration(addCrData3_5Years,"No",employer);

	  Thread.sleep(20000);
	  //Click 'Reservations' link from top navigation menu
	  COMMON_METHODS.clickElement(getTestObject("OL_10"));

	  //Click 'Request a New Reservation' link from top navigation menu
	  COMMON_METHODS.clickElement(getTestObject("OL_11"));

	  //verify User is brought to Step 1 of the reservation
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");

	  //Create Care Recipients Reservation
	  int HealthStatus[] = {1};
	  String selectForReason = getTestData("TD_ReasonForCare");
	  businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
	  "Yes");

	  //Verify the wizard moves to step 2.
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");

	  //Create  WhenandWhere Reservation
	  String strDateofReservation = getTestData("TD_Dateofreservation1");
	  String[] careDates = {strDateofReservation};
	  String actions[] = {null,"Continue",null};
	  businessComponents.EP_ReservationWhenandWhere(careDates,actions);

	  //Verify the error message displayed
	  COMMON_METHODS.VerifyTextPresent(getTestObject("MA_44"), "Please select a location.");

	  //Create  WhenandWhere Reservation
	  String strDateofReservation1 = getTestData("TD_Dateofreservation2");
	  String[] careDates1 = {strDateofReservation1};
	  String actions1[] = {"Locations","Continue",null};
	  businessComponents.EP_ReservationWhenandWhere(careDates1,actions1);
	  Thread.sleep(3000);

	  //Verify the wizard moves to step 3.
	  COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

	  //Click on In Home care options button
	  COMMON_METHODS.clickElement(getTestObject("OL_108"));
	  Thread.sleep(20000);

	  //Click on Continue button without entering details that are ** marked
	  COMMON_METHODS.clickElement(getTestObject("OL_84"));

	  //Verify the error message for the fields which are not having details.
	  COMMON_METHODS.verifyElementDisplayed(getTestObject("MA_69"));

	  //Click 'Center - based Care' button at the top of 'Available Care Options' section
	  COMMON_METHODS.clickElement(getTestObject("OL_90"));
	  Thread.sleep(20000);

	  //Verify the working hours is present on the Step 3 page
	  COMMON_METHODS.VerifyTextPresent(getTestObject("KMA_83"), "HOURS");

	  //Select Unacceptable from the dropdown of prefrences
	  COMMON_METHODS.listBoxSelect(getTestObject("OL_91"), "Unacceptable", "byVisibleText");

	  //Cancel the pop up displayed
	  COMMON_METHODS.clickElement(getTestObject("HL_09"));

	  //Click on Continue button
	  COMMON_METHODS.clickElement(getTestObject("OL_84"));

	  //Verify the error message for no care center selected.
	  COMMON_METHODS.VerifyTextPresent(getTestObject("MA_44"), "If you would like to request center based care, you must select at least one provider as acceptable and/or indicate a provider preference.");

	  //Logout from EP
	  Utility.logout();
	 }
	
	 /* @author: Sanjeev Singh
	  * @CreationDate: 18/04/2014
	  * TFS ID:23084:CSC BUCA: Edit Reservation - Reservation Contact In-Home Only: Verify greeter�s name and an edit link next to it 
	  * TFS ID:23085CSC BUCA: Edit Reservation - Reservation Contact In-Home Only: Verify edit greeter�s name by Client Employee.
	  * TFS ID:23086: CSC BUCA: Edit Reservation - Reservation Contact In-Home Only: Verify edit greeter�s name by Authorized Contact
	  * TFS ID:23088: CSC BUCA: Edit Reservation - Reservation Contact In-Home Only: Verify Releaser�s name and an edit link next to it
	  * TFS ID:23090: CSC BUCA: Edit Reservation - Reservation Contact In-Home Only: Verify edit Releaser�s name by Authorized Contact
	  * TFS ID:23092: CSC BUCA :Edit Reservation - Reservation Contact In-Home Only: Verify if Client Employee selects 'Same as the Greeter' check box for edit Releaser�s name, when edit for Greeter is not clicked
	  * TFS ID:23094: CSC BUCA:Edit Reservation - Reservation Contact In-Home Only: Verify if Client Employee selects 'Same as the Greeter' check box for edit Releaser�s name, when edit for Greeter is clicked and Greeter's Name and Relationship is changed
	  * TFS ID:23095: CSC BUCA : Edit Reservation - Reservation Contact In-Home Only: Verify if Authorized Contact selects 'Same as the Greeter' check box for edit Releaser�s name, when edit for Greeter is not clicked
	  * TFS ID:23097: CSC BUCA:Edit Reservation - Reservation Contact In-Home Only: Verify if Authorized Contact selects 'Same as the Greeter' check box for edit Releaser�s name, when edit for Greeter is clicked and Greeter's Name and Relationship is changed
	  * 
	  */

	 @Test(priority=19) @Parameters("client")
	 public void EP_Step5PageValidation(String employer) throws Exception {

	  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	  System.out.println("Inside - " + methodName);

	  //readTestData(getDataTablePath("EP"), "TD_EP2");
	  // Utility.launchBrowser(getTestData("TD_EP_URL"));
	  String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		
	  // Login Provider Portal
	 // Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client2.cbudc2.userName"), getTestData("TD_PWD"), signInArray);//"TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/			//this.loginEPPortal(2);
	  
	  //EP_CBAndInHomeReservations_Tests IH = new EP_CBAndInHomeReservations_Tests();
	  //IH.EP_InHomeReservation("2");

	  Utility.launchBrowser(getTestData("TD_EP_URL"));
		
	  // Login Provider Portal String userName = ReadwritDataFromProps.props.getProperty("client2.cbudc2.userName");
	  Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(employer + ".editres.userName"), getTestData("TD_PWD"), signInArray);
	  //Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"), signInArray);
	  
	  //Click 'Reservations' link from top navigation menu
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		
		
		//Verify user moved to Reservations page.
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"), "Reservations");

		// Click on "View" link on a reservation (Either Confirm or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");
		Thread.sleep(5000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));
		
		// Click the Edit Reservation button
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));		

		// Verify Greeter Name and link presence
		//COMMON_METHODS.VerifyTextPresent(getTestObject("s_Greeter"), "CSC");
		COMMON_METHODS.VerifyTextPresent(getTestObject("s_Greeter"), getTestData("TD_GreeterName"));
		
		//Verifying e Releaser�s name that has been saved with the reservation with an edit link next to it	
		COMMON_METHODS.VerifyTextPresent(getTestObject("s_Releaser"), getTestData("TD_GreeterName"));
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("s_Greeter_editLink"), "Edit Greeter");
		COMMON_METHODS.VerifyTextPresent(getTestObject("s_Releaser_editLink"), "Edit Releaser");
		
		//Click 'Edit  link 
		COMMON_METHODS.clickElement(getTestObject("s_Greeter_editLink"));

		String txtName =COMMON_METHODS.driver.findElement(By.id("greeterName")).getAttribute("value");//.getText();
		System.out.println("name: "+txtName);		
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Greeter Name Field Prepopulated Value", "Name field exist and Prepopulated Value: "+txtName , "");
		//COMMON_METHODS.isOptionPresentInListBox(getTestObject("IH_100"), "CSC");
		
		String txtRel = COMMON_METHODS.driver.findElement(By.id("greeterRelation")).getAttribute("value");
		System.out.println(txtRel);
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Greeter Relation Field Prepopulated Value", "Name field exist and Prepopulated Value: "+txtRel , "");
		
		// Verifying greeter fields prepopulated values
		String gTxt = getTestData("TD_GreeterName")+getTestData("TD_GreeterName");
		String rTxt = getTestData("TD_GreetRelation")+getTestData("TD_GreetRelation");
		COMMON_METHODS.editAField(getTestObject("IH_100"), gTxt);//here
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Greeter Name Field", "Name field exist and Value entered: "+gTxt , "");
		//REPORTER.LogEvent(TestStatus.PASS, "Verifying Greeter Name Field", "Name field exist and Value entered: "+"CSC1" , "");
		COMMON_METHODS.editAField(getTestObject("IH_101"), rTxt);
		//REPORTER.LogEvent(TestStatus.PASS, "Verifying Greeter Name Field", "Name field exist and Value entered: "+gTxt , "");
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Greeter Relation Field", "Relation field exist and Value entered: "+"Mother" , "");
		
		//Click on the 'Edit' link present next to Releaser�s name	IH_102
		COMMON_METHODS.clickElement(getTestObject("s_Releaser_editLink"));
		
		//Below fields should be displayed under Releaser�s name --a. 'Same as the Greeter' (checkbox) 
		//b. 'Name' (text box) c. 'Relationship' (text box)		RS_EDIT_03
		COMMON_METHODS.isElementPresent("same", "id");
		COMMON_METHODS.isElementPresent("releaserName", "id");
		COMMON_METHODS.isElementPresent("releaserRelation", "id");
		
		//Check the checkbox 'Same as the Greeter and verify both Name and Relationship fields should be populated with what is saved on the reservation for the Greeter
		COMMON_METHODS.clickElement(getTestObject("IH_102"));
		String txtRName = COMMON_METHODS.driver.findElement(By.id("releaserName")).getAttribute("value");
		System.out.println(txtRName);
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Releaser Name Field Prepopulated Value", "Name field exist and Prepopulated Value: "+txtRName , "");
		
		String txtRRel = COMMON_METHODS.driver.findElement(By.id("releaserRelation")).getAttribute("value");
		System.out.println(txtRRel);
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Release Relation Field Prepopulated Value", "Relation field exist and Prepopulated Value: "+txtRRel , "");
		
		// Verifying the same data for both Greeter and Releaser
		if((gTxt.compareTo(txtRName) == 0) && (txtRRel.compareTo(rTxt) == 0))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifying the same data for both Greeter and Releaser", "Data Matches" , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying the same data for both Greeter and Releaser", "Data didn't Matches" , "");				
		}
		
		// Verifying Release name and relation fields are editable
		COMMON_METHODS.editAField(getTestObject("RS_EDIT_02"), "CSC2");
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Release Name Field", "Name field exist and Value entered: "+"CSC2" , "");
		COMMON_METHODS.editAField(getTestObject("RS_EDIT_03"), "Mother");
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Release Relation Field", "Relation field exist and Value entered: "+"Mother" , "");
		
		// Click Update
		COMMON_METHODS.clickElement(getTestObject("RESV_14"));
		Thread.sleep(4000);
		
		// Verify Success Message
		COMMON_METHODS.VerifyTextPresent(getTestObject("s_update_msg"), "Change Requested");//getTestData("TD_GreeterName"));
	  
	    //Logout from EP		
	    Utility.logout();
	  
	    //Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	 }
	 
	 private void loginEPPoratl() throws IOException, Exception {
			
			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
				//Verify Login Page displayed
				String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
				Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(employer +".editres.userName"), getTestData("TD_PWD"),signInArray);
							
			}
		}
	 
	 private void EP_CBreservation(String[] reservationDate) throws IOException, Exception {  
		 
		//Click 'Reservations' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_10"));
			
			//Click 'Request a New Reservation' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_11"));
			
			//verify User is brought to Step 1 of the reservation
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
					
			int HealthStatus[] = { 1 };
			String selectForReason = getTestData("TD_ReasonForCare");
			
			//businessComponents.EP_ReservationCareRecipients();
			businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
					"Yes");
					
			//Verify the wizard moves to step 2.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
			
			Thread.sleep(3000);
			
			//Select Date of reservation
			COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
			
			//businessComponents.EP_ReservationWhenandWhere();
			
			//Create  WhenandWhere Reservation
			String[] careDates = reservationDate;
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			businessComponents.EP_ReservationCareOptions();
			
			Thread.sleep(3000);
			
			//Verify the wizard moves to step 4.	
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
			
			businessComponents.EP_ReservationVerifyInfoNo();
			
			Thread.sleep(3000);
			
			//Verify the wizard moves to step 5.	
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
			
			businessComponents.EP_ReservationReveiwDetails();
	 }
	 
	 
}