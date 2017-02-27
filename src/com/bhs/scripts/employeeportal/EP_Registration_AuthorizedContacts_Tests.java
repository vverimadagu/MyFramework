package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.INITIALIZE.TestStatus;
import com.bhs.util.Utility;

public class EP_Registration_AuthorizedContacts_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	String emailAddress;
	String employer=null; 

	@Parameters("client")
	@Test(priority = 1) 
	public void EP_AuthorizedContactsSignupUser(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		this.employer = employer;

		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		businessComponents.EP_verifyEmployer(employer);

		businessComponents.EP_AcceptPolicyAndSubmit();

		// Sign Up
		//Sign Up
		//Create dynamic user name
		String userName = createDyanamicUserData();
		businessComponents.EP_SignUpUser(userName,employer,"ac");

		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		// Click on Employee Profile icon
		COMMON_METHODS.clickElement(getTestObject("OL_134"));

		businessComponents.EP_UpdateEmployeeprofile(employer);

		// User will be navigated to the Care Profile page with Green check mark
		// on the employee profile icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_138"));

		// Click 'Add' link present in 'Care Recipients' section
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

		// businessComponents.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case # 4136 BUCA - Automation - Authorized Contacts - Ensure user is
	 * able to add a new authorized contacts
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void EP_AddAuthorizedContactsTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		/*
		 * //login employee portal
		 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps
		 * .props.getProperty("client63.userName"), getTestData("TD_PWD"));
		 */

		this.loginEPPoratl();
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		// Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));

		businessComponents.EP_AddAuthorizedContacts();

		// businessComponents.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case # 4135 BUCA - Authorized Contacts - Ensure a list of Authorized
	 * Contacts will be displayed
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void EP_ListAuthorizedContactsTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		this.loginEPPoratl();
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		// businessComponents.EP_AddAuthorizedContacts();

		try {

			String sTemp = BH_SetUp_TearDown.driver
					.findElement(
							By.linkText(getTestData("TD_AC_FirstName") + " "
									+ getTestData("TD_AC_LastName"))).getText()
					.trim();
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Authorized Contact created",
					"Authorized Contact - " + sTemp + " Created", "");

		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Authorized Contact created");
		}

		/* businessComponents.logout(); */

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case # 4139 BUCA - Authorized Contacts - Selecting an Authorized
	 * Contact displays details
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void EP_AuthorizedContactsDisplayTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// login employee portal
		/*
		 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.
		 * getProperty("client63.userName"), getTestData("TD_PWD"));
		 */
		this.employer = "5";

		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		this.loginEPPoratl();

		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));

		// Make some changes to the Authorized Contact Information
		COMMON_METHODS.editAField(getTestObject("OL_63"), "7702279345");

		// Click 'Update' button
		COMMON_METHODS.clickElement(getTestObject("AUTH_UPDATE"));

		/* businessComponents.logout(); */

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case # 4140 BUCA - Automation - Authorized Contacts - User is able
	 * to update an Authorized Contact's options
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void EP_UpdateAuthorizedContactsTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// login employee portal
		/*
		 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.
		 * getProperty("client63.userName"), getTestData("TD_PWD"));
		 */
		
		this.loginEPPoratl();

		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		// Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));

		// Make some changes to the Authorized Contact Information
		COMMON_METHODS.editAField(getTestObject("OL_63"), "7709999345");

		// Click 'Update' button
		COMMON_METHODS.clickElement(getTestObject("AUTH_UPDATE"));

		/* businessComponents.logout(); */

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case # 16220 BUCA - Authorized Contacts - New Authorized Contacts
	 * can be added by only filling required for save fields
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 6)
	public void EP_NewAuthorizedContactsTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

	 	/*
		 * //login employee portal
		 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps
		 * .props.getProperty("client63.userName"), getTestData("TD_PWD"));
		 */
		
		this.loginEPPoratl();

		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		/*
		 * //Click on Authorized Contacts icon
		 * COMMON_METHODS.clickElement(getTestObject("OL_136"));
		 */

		// Click on 'Add' link for Authorized Contacts section
		COMMON_METHODS.clickElement(getTestObject("OL_58"));

		this.addAuthorizedContact();

		/* businessComponents.logout(); */

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case # 4137 BUCA - Authorized Contacts - ensure an email is send to
	 * an authorized contact to create an account
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 7)
	public void EP_AddEmailAuthorizedContactsTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// login employee portal
		/*
		 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.
		 * getProperty("client63.userName"), getTestData("TD_PWD"));
		 */
		
		this.loginEPPoratl();

		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		this.emailAddress = "E_" + createDyanamicUserData() + "@gmail.com";

		this.addEmailAddAuthorizedContact();

		// TODO : Not Verified email is received by Authorized contact

		/* businessComponents.logout(); */

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case # 3730 BUCA - Authorized Contacts - Update Contact - Ensure
	 * system send email to user when changes to authorized contacts has been
	 * saved
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 8)
	public void EP_UpdateEmailAuthorizedContactsTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		/*
		 * //login employee portal
		 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps
		 * .props.getProperty("client63.userName"), getTestData("TD_PWD"));
		 */
		
		this.loginEPPoratl();
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		// click authorized contact to edit
		BH_SetUp_TearDown.driver.findElement(
				By.linkText("NewFirstEmail" + " " + "NewLastEmail")).click();

		// Enter Modify Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_63"), "8809999999");

		// Click 'Update Authorized Contact' button
		COMMON_METHODS.clickElement(getTestObject("AUTH_UPDATE"));

		// TODO : Not Verifying User gets an email that information has been
		// changed

		/* businessComponents.logout(); */

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case # 18559 BUCA - Authorized Contact - user is able to set a
	 * authorized contact to inactive
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void EP_InactiveAuthorizedContactsTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		/*Utility.launchBrowser(getTestData("TD_EP_URL"));

		//Verify Login Page displayed
		String userName = ReadwritDataFromProps.props.getProperty("client5.ac.userName");
		String password = getTestData("TD_PWD");*/
		
		/*//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);
		*/
		this.loginEPPoratl();
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		// click authorized contact to In active
		BH_SetUp_TearDown.driver.findElement(
				By.linkText("NewFirstEmail" + " " + "NewLastEmail")).click();

		// click the 'inactive' radio button
		COMMON_METHODS.radioButton(getTestObject("AUTH_INACTIVE"));
		
		//Click ok on the Pop up
		COMMON_METHODS.clickElement(getTestObject("OK_btn"));

		// Type in an inactive comment
		COMMON_METHODS.editAField(getTestObject("AUTH_INACTIVE_COMM"),
				"In active Comment");
		
	/*	//Verify the text present on the pop up
		COMMON_METHODS.VerifyTextPresent(getTestObject("MA_36"), "Email Exists");*/

		

		// Click 'Update Authorized Contact' button
		COMMON_METHODS.clickElement(getTestObject("AUTH_UPDATE"));

		try {
			WebElement webElement = BH_SetUp_TearDown.driver.findElement(By
					.linkText("NewFirstEmail" + " " + "NewLastEmail"));
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify authorized contact is hidden from the UI ",
					"Authorized contact is not hidden from the UI ", "");
		} catch (Exception e) {

			REPORTER.LogEvent(TestStatus.PASS,
					"Ensure authorized contact is hidden from the UI ",
					"Authorized contact is hidden from the UI successfully", "");
		}

		Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/*	*//**
	 * Test Case # 3727 BUCA - Authorized Contacts - when updating
	 * authorized contacts, system is able to identify if updates will affect
	 * upcoming reservations
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/*
	 * @Test(priority=10,)
	 * public void EP_AuthorizedContactsAffectReservationsTest() throws
	 * Exception {
	 * 
	 * String methodName =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * //Read testdata for based on client 1
	 * readTestData(getDataTablePath("EP"), "TD_EP1");
	 * 
	 * this.EP_SignUpReservation();
	 * 
	 * businessComponents.logout();
	 * 
	 * //Log to reports COMMON_METHODS.logToReportAfterPass(methodName);
	 * 
	 * }
	 */

	/**
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void addAuthorizedContact() throws IOException, Exception {

		// Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_59"), "NewF");

		// Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_60"), "NewL");

		// Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_61"));

		// Select Primary Phone
		COMMON_METHODS.listBoxSelect(getTestObject("OL_62"), "Work",
				"byVisibleText");

		// Enter Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_63"), "8809999345");

		// Select Relationship
		COMMON_METHODS.listBoxSelect(getTestObject("OL_65"),
				getTestData("TD_Relationship"), "byVisibleText");

		// Click 'Add Authorized Contact' button
		COMMON_METHODS.clickElement(getTestObject("OL_69"));

		// Verify the newly added Authorized Contact is present under
		// 'Authorized Contacts' section
		try {
			String sTemp = BH_SetUp_TearDown.driver
					.findElement(By.linkText("NewF" + " " + "NewL")).getText()
					.trim();
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Authorized Contact created",
					"Authorized Contact - " + sTemp + " Created", "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Authorized Contact created");
		}
	}

	public void addEmailAddAuthorizedContact() throws IOException, Exception {

		/*
		 * //Click on Authorized Contacts icon
		 * COMMON_METHODS.clickElement(getTestObject("OL_136"));
		 */

		// Click on 'Add' link for Authorized Contacts section
		COMMON_METHODS.clickElement(getTestObject("OL_58"));

		// Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_59"), "NewFirstEmail");

		// Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_60"), "NewLastEmail");

		// Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_61"));

		// Select Primary Phone
		COMMON_METHODS.listBoxSelect(getTestObject("OL_62"), "Work",
				"byVisibleText");

		// Enter Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_63"), "8809999345");

		// selecting radio option is Yes for Do you want this contact to have
		// Benefit Access?**
		COMMON_METHODS.radioButton(getTestObject("CP_13"));

		Thread.sleep(2000);

		COMMON_METHODS.editAField(getTestObject("REG_01"), this.emailAddress);

		// Select Relationship
		COMMON_METHODS.listBoxSelect(getTestObject("OL_65"),
				getTestData("TD_Relationship"), "byVisibleText");

		// Click 'Add Authorized Contact' button
		COMMON_METHODS.clickElement(getTestObject("OL_69"));

		// Verify the newly added Authorized Contact is present under
		// 'Authorized Contacts' section
		try {
			String sTemp = BH_SetUp_TearDown.driver
					.findElement(
							By.linkText("NewFirstEmail" + " " + "NewLastEmail"))
					.getText().trim();
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Authorized Contact created",
					"Authorized Contact - " + sTemp + " Created", "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Authorized Contact created");
		}
	}

	/**
	 * @throws Exception
	 * @throws IOException
	 * 
	 */
	public void EP_SignUpReservation() throws IOException, Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		businessComponents.EP_verifyEmployer(employer);

		businessComponents.EP_AcceptPolicyAndSubmit();
		//Create dynamic user name
		String userName = createDyanamicUserData();
		// Sign Up
		businessComponents.EP_SignUpUser(userName,employer,"ac");
		

		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		// Click on Employee Profile icon
		COMMON_METHODS.clickElement(getTestObject("OL_134"));

		businessComponents.EP_UpdateEmployeeprofile(employer);

		// User will be navigated to the Care Profile page with Green check mark
		// on the employee profile icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_138"));

		// Click 'Add' link present in 'Care Recipients' section
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

		// User will be navigated to the Care Profile page with Green check mark
		// on the care recipients icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_139"));

		// Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));

		businessComponents.EP_AddAuthorizedContacts();

		this.EP_reservation();

		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * BUCA - Automation - Reservations - Ensure you can request a new
	 * reservation.
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	public void EP_reservation() throws Exception {

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		// verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"),
				"Who Needs Care and Why?");

		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");

		// Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"),
				"When and Where Do You Need Care?");

		// Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"),
				getTestData("TD_Dateofreservation"));


		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		

		// Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"),
				"Available Care Options");

		businessComponents.EP_ReservationCareOptions();

		// Verify the wizard moves to step 4.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"),
				"Care Instructions & Verify Information");

		businessComponents.EP_ReservationVerifyInfoNo();

		// Verify the wizard moves to step 5.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"),
				"Review Reservation and Payment Details");

		businessComponents.EP_ReservationReveiwDetails();

	}
	
	private void loginEPPoratl() throws IOException, Exception {
			
			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
				//Verify Login Page displayed
				String userName = ReadwritDataFromProps.props.getProperty(employer +".ac.userName");
				String password = getTestData("TD_PWD");
				
				//Login to Emp Portal
				String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
				Utility.loginToBUCA(userName, password,signInArray);
			}
		}

}
