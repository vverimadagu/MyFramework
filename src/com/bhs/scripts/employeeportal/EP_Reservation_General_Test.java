package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;
import com.bhs.util.INITIALIZE.TestStatus;

public class EP_Reservation_General_Test extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	String employer=null; 
	
	/** 
	* @author Satya
	* @version 
	* @return 
	* @param 
	* @CreationDate: 04/03/2014
	*/ 
		@Test(priority=1) @Parameters("client")
		public void EP_CareProfileSignupUserCBReservation(String employer) throws Exception {

			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
			
			this.employer = employer;
			
			Utility.launchBrowser(getTestData("TD_EP_URL"));
			
			businessComponents.EP_verifyEmployer(employer);
			
			businessComponents.EP_AcceptPolicyAndSubmit();

			//Sign Up
			// Sign Up
			String userName = createDyanamicUserData();
			businessComponents.EP_SignUpUser(userName,employer,"resgen");
			
			//Click 'Care Profile' link from top navigation menu
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
			
			//Click on Authorized Contacts icon
			COMMON_METHODS.clickElement(getTestObject("OL_136"));
			
			businessComponents.EP_AddAuthorizedContacts();
			
			this.EP_reservation();
			
			//businessComponents.logout();
			COMMON_METHODS.logToReportAfterPass(methodName);
			
			
			/*//Create Care Recipients Reservation
			int CareRecipients = 1;
			int HealthStatus[] = {1};
			String selectForReason = getTestData("TD_ReasonForCare");
			businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
			
			//User will be navigated to the Care Profile page with Green check mark on the care recipients icon
			COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_139"));
			
			//Click on Authorized Contacts icon
			COMMON_METHODS.clickElement(getTestObject("OL_136"));
			
			businessComponents.EP_AddAuthorizedContacts();
			
			this.EP_reservation();*/
			
			//businessComponents.logout();
			COMMON_METHODS.logToReportAfterPass(methodName);
			
		}
	 
	
	/**
	 *Test Case #8415 
	 *BUCA - Reservation Review - ensure 'Edit/Cancel Care Sessions' button brings user to 'Edit Reservation' page
	 * 
	 */

	@Test(priority=2) @Parameters("client")
	public void EP_Res_Edit_Cancel_CareSessions(String employer) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		this.employer = employer;
		//need to comment this line
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client70.userName"), getTestData("TD_PWD"));
		
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
		
		System.out.println(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h2")).getText().trim().toString());
		
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h2")).getText().trim().toString().contains("EDIT RESERVATION")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify Edit Reservation Page displayed", "Edit Reservation page is dispaled", "");			
		}else {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Edit Reservation Page displayed", "Edit Reservation page not dispaled", "");
		}
	
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #18422 
	 *BUCA - Reservation Fulfillment - Reserve Now - Verify Employees allowed to use "Reserve Now" can make a "Reserve Now" reservation
	 * 
	 */

	@Test(priority=3)
	public void EP_ReservationFulfillment_ReserveNow() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		this.loginEPPoratl();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		//Create Care Recipients Reservation
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation2")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//Step 3 click on the "Reserve Now" button next to a Care Center
		COMMON_METHODS.clickElement(getTestObject("ROL_29"));
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
		
		businessComponents.EP_ReservationReveiwDetails();
		
		//Verify the Reservation Number and Status present at the top
		try {
			if(BH_SetUp_TearDown.driver.findElement(By.xpath("//span[@class='hdrStatus']")).getText().trim().toString().contains("(Confirmed)")){
				REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Confirmed", "Reservation Confirmed", "");
			}
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Reservation Confirmed");
		}
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	
	/**
	 *Test Case #18425 
	 *BUCA - Reservation Fulfillment - Reserve Now - Verify never to allow a reservation for more than 3 days to use "Reserve Now" during peak times
	 * 
	 */

	@Test(priority=4)
	public void EP_ReservationFulfillment_ReserveNow3days() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
			// SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
		readTestObject(getDataTablePath("EP"), "TO_EP");
		
		
		 //TODO : Need to get some more information on how this will work.

	}
	
	/**
	 *Test Case #12146 
	 *BUCA - Reservation Wizard - Step3 - ensure the 'Tell Us About The Care Environment' section matches directly (UI should be the same) with In-Home & In-Hotel
	 * 
	 */

	@Test(priority=5)
	public void EP_Reservation_TellUsAbout_Section() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		this.loginEPPoratl();

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		//Create Care Recipients Reservation
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation9")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		
		//Select 'Residential' radio button for Location type
		COMMON_METHODS.radioButton(getTestObject("OL_109"));
		
		//	Verify the "Tell Us About the Care Environment" section matches 
			
		String residentialTellusAboutTitle = COMMON_METHODS.getText(getTestObject("RESV_TELL_01"));
		String residentialHelpfulinstructionsLabel = COMMON_METHODS.getText(getTestObject("RESV_TELL_02"));
		String residentialDoesanyonesmoketTitle = COMMON_METHODS.getText(getTestObject("RESV_TELL_03"));
		String residentialArethereAnyPetsTitle = COMMON_METHODS.getText(getTestObject("RESV_TELL_04"));
		String residentialCareRecipientExpectedTitle = COMMON_METHODS.getText(getTestObject("RESV_TELL_05"));

		//Select 'Residential' radio button for Location type
		COMMON_METHODS.radioButton(getTestObject("RESV_TELL_06"));
		
		String homeTellusAboutTitle = COMMON_METHODS.getText(getTestObject("HTL_TELL_01"));
		String homeHelpfulinstructionsLabel = COMMON_METHODS.getText(getTestObject("HTL_TELL_02"));
		String homeDoesanyonesmoketTitle = COMMON_METHODS.getText(getTestObject("HTL_TELL_03"));
		String homeArethereAnyPetsTitle = COMMON_METHODS.getText(getTestObject("HTL_TELL_04"));
		String homeCareRecipientExpectedTitle = COMMON_METHODS.getText(getTestObject("HTL_TELL_05"));
		
		System.out.println("residentialTellusAboutTitle" + residentialTellusAboutTitle);
		System.out.println("residentialHelpfulinstructionsLabel"  + residentialHelpfulinstructionsLabel);
		System.out.println("residentialDoesanyonesmoketTitle" +  residentialDoesanyonesmoketTitle);
		System.out.println("residentialArethereAnyPetsTitle" +  residentialArethereAnyPetsTitle);
		System.out.println("residentialCareRecipientExpectedTitle"  + residentialCareRecipientExpectedTitle);
		System.out.println("homeTellusAboutTitle"  + homeTellusAboutTitle);
		System.out.println("homeHelpfulinstructionsLabel" +  homeHelpfulinstructionsLabel);
		System.out.println("homeDoesanyonesmoketTitle"  + homeDoesanyonesmoketTitle);
		System.out.println("homeArethereAnyPetsTitle"  + homeArethereAnyPetsTitle);
		System.out.println("homeCareRecipientExpectedTitle"  + homeCareRecipientExpectedTitle);
		

		//Ensure that the Tell us About Care Environment section is the same between Residential and Hotel
		
		/*if (residentialTellusAboutTitle.equals(homeTellusAboutTitle))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Tell us About section title same between Residential and Hotel ", homeTellusAboutTitle + " Verified title is same in Residential and Hotel ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Tell us About section title same between Residential and Hotel ", homeTellusAboutTitle + " Verified title is not same in Residential and Hotel ", "");*/
		
		if (residentialTellusAboutTitle.contains("IN-HOME CARE ELECTION"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Tell us About section title for Residential ", residentialTellusAboutTitle + " title is displayed for  Residential", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Tell us About section title for Residential ", residentialTellusAboutTitle + " title not displayed for  Residential", "");
		
		if (homeTellusAboutTitle.contains("IN-HOTEL CARE OPTIONS"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Tell us About section title for Hotel ", homeTellusAboutTitle + " title is displayed for  Hotel", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Tell us About section title for Hotel ", homeTellusAboutTitle + " title not displayed for  Hotel", "");
		
		if (residentialHelpfulinstructionsLabel.contains(homeHelpfulinstructionsLabel))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Helpful instructions label same between Residential and Hotel ", homeHelpfulinstructionsLabel + " Verified Helpful instructions label is same in Residential and Hotel ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Helpful instructions label same between Residential and Hotel ", homeHelpfulinstructionsLabel + " Verified Verify Helpful instructions label is not same in Residential and Hotel ", "");
		
		if (residentialDoesanyonesmoketTitle.contains(homeDoesanyonesmoketTitle))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Does anyone smoke label same between Residential and Hotel ", homeDoesanyonesmoketTitle + "Verified Does anyone smoke label is same in Residential and Hotel ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Does anyone smoke label same between Residential and Hotel ", homeDoesanyonesmoketTitle + "Verified Does anyone smoke label is not same in Residential and Hotel ", "");
		
		/*if (homeArethereAnyPetsTitle.contains(residentialArethereAnyPetsTitle))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Are there any pets label same between Residential and Hotel ", homeArethereAnyPetsTitle + "Verified Are there any pets label is same in Residential and Hotel ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Are there any pets label same between Residential and Hotel ", homeArethereAnyPetsTitle + "Verified Are there any pets label is not same in Residential and Hotel ", "");*/
		
		if (residentialCareRecipientExpectedTitle.contains(homeCareRecipientExpectedTitle))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Is anyone other than the care recipient(s) label same between Residential and Hotel ", homeCareRecipientExpectedTitle + "Verified Is anyone other than the care recipient(s) label is same in Residential and Hotel ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Is anyone other than the care recipient(s) label between Residential and Hotel ", homeCareRecipientExpectedTitle + "Verified Is anyone other than the care recipient(s) label is not same in Residential and Hotel ", "");
		
		Utility.logout();

	}
	
	/**
	 *Test Case #11876 
	 *BUCA - Reservations - Ensure "What you need for care" link works on a confirmed reservation
	 * 
	 */

	@Test(priority=2)
	public void EP_Res_Confirmed_Reservation() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//businessComponents.EP_LaunchBrowser(TC);
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client70.userName"), getTestData("TD_PWD"));
		
		// TODO : Pre - Req: On the Care Center Portal, create a Center Note for External on the day of a reservation
		// due to confirmed reservation automatically after creating reservation.
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click on "What you need for care" link on a confirmed reservation for the date that was added in CCP
		COMMON_METHODS.clickElement(getTestObject("CARE_LINK"));
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 * BUCA - Automation - Reservations - Ensure you can request a new reservation.
	 * @throws Exception 
	 * 
	 * 
	 */
	public void EP_reservation() throws Exception {
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		//Create Care Recipients Reservation
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation1")};
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
		
		
		
	}
	
	private void loginEPPoratl() throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(employer + ".resgen.userName"), getTestData("TD_PWD"),signInArray);
						
		}
	}
	
}