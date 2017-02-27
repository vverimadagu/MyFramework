
package com.bhs.scripts.employeeportal;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;
import com.bhs.util.INITIALIZE.TestStatus;

public class EP_Reservation_Test extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();

	/*//Reading Test Objects from Data excel 
	static{
		try{
			readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	} */

	

	/**
	  * 
	  * Test Case #23715: 
	  *  CSC BUCA: High Level: In-Home Reservation Flow - To create In Home Reservation in EP so that we can use this in PP.
	  *
	  */
	
	
	
	String employer=null; 
	@Test @Parameters("client") 
	public void EP_InHomeReservation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		String resNum = businessComponents.EP_InHomeReservation(employer, getTestData("TD_Dateofreservation12"));
		
		System.out.println("Reservation Number "  + resNum);
		Thread.sleep(5000);
		
		 // Logout from 'Provider Portal'
		 // Utility.logout();
		
		
		}
	
	
	/**
	 * Reservations - ensure page is loaded correctly (UX doc)
	 * TFS ID: 11857
	 
	
	public void EP_Reservation_Navigation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client5
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		//Launch EP
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Verify Employer
		businessComponents.EP_verifyEmployer(employer);
		//Accept policy
		businessComponents.EP_AcceptPolicyAndSubmit();
	
		//Registration
		//Create dynamic user name
		String userName = createDyanamicUserData();
		// Register a New User
		businessComponents.EP_SignUpUser(userName,employer,"genres");
		
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
					
		//Center Based Reservation
		businessComponents.EP_Registration(addCrData3_5Years,"No", employer);
		
		
		//Login to Emp Portal
		/*String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"),signInArray);
		
		/* // Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Login to Emp Portal
		String userName=ReadwritDataFromProps.props.getProperty("client5.reservation.userName");
		String password=getTestData("TD_PWD");
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray); 

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Verify that the Reservations page title 
		businessComponents.EP_verifyText(getTestObject("OL_10"), "Reservations");

		// Logout from 'Employee Portal'
		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}  */
	
	/**
	 * Test Case #9382: 
	 * EE - Employee Portal - Reservation Process - In-Home Health Status Question
	 * 
	 * Reservations - ensure page is loaded correctly (UX doc)
	 * TFS ID: 11857 
	 */
	@Test()
	public void EP_Reservation_HealthStatus() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(5);
		
		/* // Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// login employee portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"),signInArray);*/
		
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Verify that the Reservations page title 
		businessComponents.EP_verifyText(getTestObject("OL_10"), "Reservations");
		
		//Click 'Request a New Reservation' link from top navigation menu
				COMMON_METHODS.clickElement(getTestObject("OL_11"));

		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");

		//verify radio button  Health Status: Mildly Ill enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("RES_ MildlyIll"), true);

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}
	
	
	

	/**
	 * Reservation Wizard - Step1 - Ensure user is able to continue to Step 2 via 'Continue' button
	 * TFS ID : 23382
	 * Rolling up Reservation Step 1, 2 and 3 into single method 
	 */
	@Test()
	public void EP_ReservationWizardStep1() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		
		this.loginEPPortal(5);
		
		/*// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//login employee portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"),signInArray);*/
		
		
		
		
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
		String[] careDates = {getTestData("TD_Dateofreservation1")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		String setpref = getTestData("TD_FirstChoice");
		businessComponents.EP_ReservationCareOptions(setpref);
		Thread.sleep(5000);

		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}

	
	/**
	 * TFS ID : 12109
	 * BUCA - Automation - Reservation Wizard - Step2 - Ensure user is able to Continue via the 'Continue' button
	 * 
	 */

	/*
	@Test()
	public void EP_ReservationWizardStep2() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		
		this.loginEPPortal(5);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		businessComponents.EP_LaunchBrowser();

		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"));

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
		String[] careDates = {getTestData("TD_Dateofreservation1")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options"); 

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}  */

	/**
	 * TFS ID 12130
	 * BUCA - Automation - Reservation Wizard - Step3 - Ensure user is able to continue to Step 4 via 'Continue' button
	 * 
	 */

	/*
	@Test()
	public void EP_ReservationWizardStep3() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		
		this.loginEPPortal(5);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");

		businessComponents.EP_LaunchBrowser();

		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.reservation.userName"), getTestData("TD_PWD"));


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
		String[] careDates = {getTestData("TD_Dateofreservation2")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		String setpref = getTestData("TD_FirstChoice");
		businessComponents.EP_ReservationCareOptions(setpref);

		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information"); 

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	} */


	/**
	 *TFS ID :23383
	 *BUCA - Automation - Reservation Wizard - Step 4.5 - Ensure you can get to step 4.5 of the reservation wizard
	 * 
	 */

	@Test()
	public void EP_ReservationWizardStep45() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

	
		this.loginEPPortal(5);
		
		/*//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		businessComponents.EP_LaunchBrowser();

		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD")); */

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
		String[] careDates = {getTestData("TD_Dateofreservation5")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		String setpref = getTestData("TD_FirstChoice");
		businessComponents.EP_ReservationCareOptions(setpref);

		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");

		businessComponents.EP_ReservationVerifyInfoYes();

		// To Do Write the code for the step 4.5 here.
		
		String title=BH_SetUp_TearDown.driver.getTitle();
		
		REPORTER.LogEvent(TestStatus.PASS, "Care Recipients page "+title, "Care Recipients page"+title+" -displayed".toUpperCase(),"");
			
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_01"),"Basic Information");
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_02"),"Health Information and Restrictions ");
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_03"),"Medical Information");
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_04"),"Care Forms");
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_05"),"Additional Information");
				

		//Verify the wizard moves to step 5.	
		//COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}

	/**
	 * TFS ID 23384:
	 * BUCA - Automation - Reservation Wizard - Step5 - Ensure user is able to continue to Step 5 via 'Continue' button
	 * 
	

	@Test()
	public void EP_ReservationWizardStep5() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

	
		this.loginEPPortal(5);
		
		/*	//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");

		businessComponents.EP_LaunchBrowser();

		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.reservation.userName"), getTestData("TD_PWD"));


	/*	//Click 'Reservations' link from top navigation menu
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
		//COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation3")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		String setpref = getTestData("TD_FirstChoice");
		businessComponents.EP_ReservationCareOptions(setpref);

		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");

		businessComponents.EP_ReservationVerifyInfoNo();

		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}   */

	/**
	 * Test Case : 23387
	 * BUCA - Automation - Reservations - Crisis Care Assist - Ensure CareAssist is displayed for a user who has it enabled in their contract
	 * 
	 This method  is covered in "EP_CrisisCareAssist_Reservation" CLASS

	@Test()
	public void EP_Reservations_CrisisCareAssist() throws Exception {


		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

	
		this.loginEPPortal(5);
		
		/*	//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		businessComponents.EP_LaunchBrowser();

		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"));


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

		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation6"));

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		//Crisis CareAssist should be the defaulted choice
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_08"), true);

		//Check I am selecting to use Crisis CareAssist and click Continue
		COMMON_METHODS.checkBox(getTestObject("ROL_09"), "check");

		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}   */
	
	
	/**
	 * Test Case : 23381
	 * BUCA - Automation - Reservations - Ensure you can request a new reservation.
	 * @param TC 
	 * 
	 */


	@Test()
	public void EP_CompleteReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.loginEPPortal(5);
		
		 //Read testdata for based on client 5
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		//Launch EP
		//Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.reservation.userName"), getTestData("TD_PWD"));
		//Thread.sleep(3000); 

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		Thread.sleep(3000); 

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

		//Select Date of reservation
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation7"));

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation8")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		String setpref = getTestData("TD_FirstChoice");
		businessComponents.EP_ReservationCareOptions(setpref);

		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");

		businessComponents.EP_ReservationVerifyInfoNo();

		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		businessComponents.EP_ReservationReveiwDetails();

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}


	

	/**
	  *Test Case #5241: 
	  *BUCA - Reservation Wizard - Verify the page lists all Care Recipients.
	 **/

	@Test()
	public void EP_Reservations_PageListAllCareRecipients() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		/*Read testdata for based on client 5
		readTestData(getDataTablePath("EP"), "TD_EP5");
		Thread.sleep(5000);

		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Verify Employer
		businessComponents.EP_verifyEmployer();
				
		//Accept policy
		businessComponents.EP_AcceptPolicyAndSubmit();
			
				//Registration
				//Create dynamic user name
				String userName = createDyanamicUserData();
				
				// Register a New User
				businessComponents.EP_SignUpUser(userName,5,"reservation");
				
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
				//Center Based Reservation
				businessComponents.EP_Registration(addCrData3_5Years,"No");
		

		//login employee portal
		/*String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client8.reservation.userName"), getTestData("TD_PWD"),signInArray);  */
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		List<WebElement> CareRecipientsListCR = COMMON_METHODS.driver.findElements(By.xpath("//*[@id='pageContent']//div[7]//ul"));

		//String CareRecipient = COMMON_METHODS.getText(getTestObject("ROL_02"));

		int CR=CareRecipientsListCR.size();


		for(WebElement element: CareRecipientsListCR)
		{
			System.out.println("Care Recipients in the care Profile Page " + element.getText());

		}

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");

		List<WebElement> CareRecipientsListRES = COMMON_METHODS.driver.findElements(By.xpath("//*[@id='formNewReservation']//div[3]//ul//li[2]"));

		int RES=CareRecipientsListRES.size();

		for(WebElement element1: CareRecipientsListRES)
		{
			System.out.println("Care Recipients in the Reservation Page " + element1.getText());

		}

		if(CR==RES)
		{
			REPORTER.LogEvent(TestStatus.PASS, "Care Profile page Care Recipients List size '"+ CareRecipientsListCR.size() , "Reservation page Care Recipients List size '"	+CareRecipientsListRES.size() ,"");	
		}else 
			REPORTER.LogEvent(TestStatus.FAIL, "Care Profile page Care Recipients List size '"+ CareRecipientsListCR.size() , "Reservation page Care Recipients List size '"	+CareRecipientsListRES.size() ,"");
			
		

		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	

	/**
	 * Reservation Wizard - BUCA - Reservation Wizard - Edit Reservations - page loads as UX document shows
	 * TFS ID: 13690
	
	@Test()
	public void EP_Reservation_Edit_Reservation_Navigation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(5);
		 /* Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to 'Employee Portal'
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client2.reservation.userName"), getTestData("TD_PWD"),signInArray);  
		
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);

		// Click on 'View' link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);

		// Click 'Edit/Cancel Care Sessions' link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		Thread.sleep(5000);

		// Logout from 'Employee Portal'
		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}	  */
	
	

	/**
	 * Reservation Wizard - BUCA - Reservation Wizard - Edit Reservations - ensure date and time pickers function correctly when editing a session
	 * TFS ID: 12208
	 * TFS ID: 13690 IS COVERED IS THIS TEST CASE
	 */	
	@Test()
	public void EP_Reservation_Edit_Reservation_Date_Time_Pickers() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client5.reservation
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(5);
		
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);

		// Click on "View" link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);

		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		Thread.sleep(5000);

		// Click a Check box in the Care Sessions section
		COMMON_METHODS.clickElement(getTestObject("RESV_04"));
		Thread.sleep(5000);

		// Click "Edit Selected"
		COMMON_METHODS.clickElement(getTestObject("RESV_05"));
		Thread.sleep(5000);

		// Enter the date
		COMMON_METHODS.editAField(getTestObject("RESV_06"), getTestData("TD_Dateofreservation8"));

		// Verify the date field is working correctly 
		COMMON_METHODS.VerifyTextPresent(getTestObject("RESV_06"), getTestData("TD_Dateofreservation8"), "value");

		// Click on time picker (Start Time)
		COMMON_METHODS.editAField(getTestObject("RESV_07"), getTestData("TD_StartTime"));

		// Verify the start time field is working correctly 
		//COMMON_METHODS.VerifyTextPresent(getTestObject("RESV_07"), getTestData("TD_StartTime"), "value");

		// Click on time picker (End Time)
		COMMON_METHODS.editAField(getTestObject("RESV_08"), getTestData("TD_EndTime"));
		Thread.sleep(5000);

		// Verify the start time field is working correctly 
		//COMMON_METHODS.VerifyTextPresent(getTestObject("RESV_08"), getTestData("TD_EndTime"), "value");

		// Logout from 'Employee Portal'
		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	

	/**
	 * Reservation Wizard - BUCA - Reservation Wizard - Edit Reservations - ensure 'Reason for Cancel' drop-down on 'Cancel Session' pop-up contains all options
	 * TFS ID: 12220
	
	@Test()
	public void EP_Reservation_Edit_Reservation_ReasonForCancel_Dropdown_Options() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(5);
		
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "In Progress", "byVisibleText");
		Thread.sleep(5000);

		// Click on 'View' link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);

		// Click 'Edit/Cancel Care Sessions' link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		Thread.sleep(5000);

		// Click a Check box in the Care Sessions section
		COMMON_METHODS.checkBox(getTestObject("RESV_04"), "check");  

		// Click 'Cancel Selected' link
		COMMON_METHODS.clickElement(getTestObject("RESV_09"));
		Thread.sleep(5000);  

		// Get the options from test data
		String strRFCOptions = getTestData("TD_ReasonForCanceling");
		//"Bad Weather,Family Illness/Emergency,Friends or Family Member Providing Care,Regular Provider has become available,Transportation Issues,Reservation Management,Change in Employees Work Schedule,Switching Care Type,Care Recipient ,No Longer Ill,No Show/Late Cancel,Cancel from WL Confirmed Elsewhere,Cancel from WL by Family";
		String[] arrOptions = strRFCOptions.split(",");

		// Verify each option is present in the drop-down
		for (String option : arrOptions) {

			COMMON_METHODS.isOptionPresentInListBox(getTestObject("RESV_11"),option);
		}

		// Logout from 'Employee Portal'
		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	  */

	/**
	 * Reservation Wizard - Edit Reservations - ensure 'Cancel' button on the 'Cancel Care' pop-up functions correctly
	 * TFS ID: 12222
	 * 
	 * Reservation Wizard - BUCA - Reservation Wizard - Edit Reservations - ensure 'Reason for Cancel' drop-down on 'Cancel Session' pop-up contains all options
	 * TFS ID: 12220 - This test case is covered in the below method
	 */
	@Test()
	public void EP_Reservation_Cancel_Reservation_Cancel_Button() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(5);
		/*// Launch EP browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to 'Employee Portal'
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client2.reservation.userName"), getTestData("TD_PWD"),signInArray);*/
		
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		Thread.sleep(5000);

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);
		
		// Click on 'View' link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);

		// Click 'Edit/Cancel Care Sessions' link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		Thread.sleep(5000);

		// Click a Check box in the Care Sessions section
		COMMON_METHODS.checkBox(getTestObject("RESV_04"), "check");
		Thread.sleep(5000);

		// Click 'Cancel Selected' link
		COMMON_METHODS.clickElement(getTestObject("RESV_09"));
		Thread.sleep(5000);
		
		// Get the options from test data
		String strRFCOptions = getTestData("TD_ReasonForCanceling");
		//"Bad Weather,Family Illness/Emergency,Friends or Family Member Providing Care,Regular Provider has become available,Transportation Issues,Reservation Management,Change in Employees Work Schedule,Switching Care Type,Care Recipient ,No Longer Ill,No Show/Late Cancel,Cancel from WL Confirmed Elsewhere,Cancel from WL by Family";
		String[] arrOptions = strRFCOptions.split(",");

		// Verify each option is present in the drop-down
		for (String option : arrOptions) {
					COMMON_METHODS.isOptionPresentInListBox(getTestObject("RESV_11"),option);
				}
				

		// Click 'Cancel' button on 'Cancel Care Session' pop-up
		COMMON_METHODS.clickElement(getTestObject("RESV_12"));
		Thread.sleep(5000);

		// Verify the pop-up is closed
		//COMMON_METHODS.verifyElementDisplayed(getTestObject("RESV_10"));

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Reservation Wizard - Edit Reservations - ensure 'Submit' button on the 'Cancel Care' pop-up functions correctly
	 * TFS ID: 12223
	 * This TFS ID: 12228 flow is covered in this test case
	 * BUCA - Reservation Wizard - Edit Reservations - ensure 'Request Update' button functions correctly for Center-Based Reservations
	 *  
	 */
	@Test()
	public void EP_Reservation_Cancel_Reservation_Submit_Button() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client5.reservation 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(5);
		
		/*Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to 'Employee Portal'
		//String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		//Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client1.reservation.userName"), getTestData("TD_PWD"),signInArray); */
		
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);

		// Click on 'View' link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));

		// Click 'Edit/Cancel Care Sessions' link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));

		// Click a Check box in the Care Sessions section
		COMMON_METHODS.checkBox(getTestObject("RESV_04"), "check");
		Thread.sleep(5000);

		// Click 'Cancel Selected' link
		COMMON_METHODS.clickElement(getTestObject("RESV_09"));
		Thread.sleep(5000);

		// Select a reason for Cancel
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_11"), "1", "byValue");
		Thread.sleep(5000);

		// Click 'Submit' button on 'Cancel Care Session' pop-up
		COMMON_METHODS.clickElement(getTestObject("RESV_13"));
		Thread.sleep(10000);

		// Logout from employee portal
		Utility.logout();
		Thread.sleep(5000);

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	

	
	
	
	private void loginEPPortal(int ClientNo) throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			
			//Login to Emp Portal
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"genres"+"."+"userName"), getTestData("TD_PWD"),signInArray);
			/*businessComponents.LoginEmployeeportal(
					ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"userName"),
					getTestData("TD_PWD"));*/
		}
	}
	
	
	/**
	 * BUCA - Reservation Wizard - Edit Reservations - ensure 'Request Update' button functions correctly for Center-Based Reservations
	 * TFS ID: 12228
	
	@Test()
	public void EP_Reservation_Edit_Reservation_RequestUpdate_Button() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(5);
		
		/*
		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to 'Employee Portal'
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client1.reservation.userName"), getTestData("TD_PWD"),signInArray);
		
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);

		// Click on 'View' link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));

		// Click 'Edit/Cancel Care Sessions' link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));

		// Click a Check box in the Care Sessions section
		COMMON_METHODS.checkBox(getTestObject("RESV_04"), "check");

		// Click 'Cancel Selected' link
		COMMON_METHODS.clickElement(getTestObject("RESV_09"));

		// Select a reason for Cancel
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_11"), "1", "byIndex");

		// Click 'Submit' button on 'Cancel Care Session' pop-up
		COMMON_METHODS.clickElement(getTestObject("RESV_13"));

		// Verify the 'Change Requested' message is displayed
		//COMMON_METHODS.VerifyTextPresent(getTestObject("RESV_10"), "Change Requested", "value");

		// Logout from employee portal
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}  */
	
	


	/**
	 * Author: Supraja
	 * CSC BUCA: Edit Reservation - Step 3: Verify if Client Employee can update information for a In Home Reservation
	 * TFS ID: 22962
	 * This method is covered in "EP_Edit_Reservation_Test.EP_Step3ChangeCenterloc "
	@Test()
	public void EP_EditReservation_UpdateInHomeReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");

		
		this.loginEPPortal(1);
		/*
		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to 'Employee Portal'
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client1.reservation.userName"), getTestData("TD_PWD"),signInArray); 
		
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);

		// Click on "View" link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);

		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		Thread.sleep(5000);

		//Click on 'Expand Search Radius' link present on the 'Care Providers' section
		//TO-DO code for 'Expand Search Radius' link present. This link is not present
		
		//Edit Reservation - Center-Based Care Options page should be displayed as per the mockups, 
		 with list of previously selected Providers and Providers Found on right, and Search Criteria on left 

		// Logout from 'Employee Portal'
		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	 */

	
	/**
	 * Author: Supraja
	 * CSC BUCA: Edit Reservation - Verify if Client Employee can change Location of Care for a reservation
	 * TFS ID: 22963
	 * This method is covered in "EP_Edit_Reservation_Test.EP_Step3ChangeCenterloc "
	 
	@Test()
	public void EP_EditReservation_UpdateEmpLocation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");

		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to 'Employee Portal'
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client1.reservation.userName"), getTestData("TD_PWD"),signInArray);
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);

		// Click on "View" link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);

		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		Thread.sleep(5000);

		//Click on 'Expand Search Radius' link present on the 'Care Providers' section	
		// Edit Reservation - Center-Based Care Options page should be displayed as per the mockups, with list of previously selected Providers and Providers Found on right, and Search Criteria on left
		//TO-DO code for 'Expand Search Radius' link. This link is not present
		
		

		// Logout from 'Employee Portal'
		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	*/

	
	

	/**
	 * Author: Supraja
	 * CSC BUCA :Edit Reservation - Change Center Location Details: 
	 * Verify 'CHANGE CENTER LOCATION DETAILS’ link is present for Center based Reservation
	 * TFS ID: 23078
	 * This test case is covered in "EP_Edit_Reservation_Test.EP_Step3ChangeCenterloc" 
	 
	@Test()
	public void EP_EditReservation_ChangeCenterLocationDetails() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");

		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to 'Employee Portal'
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client1.reservation.userName"), getTestData("TD_PWD"),signInArray);
		// Navigate to 'Reservations' tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);

		// Click on "View" link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);

		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		Thread.sleep(5000);

		//Verify 'CHANGE CENTER LOCATION DETAILS’ link is present under 'Care Providers' section	
		
		//TO-DO code for 'CHANGE CENTER LOCATION DETAILS’ link. 
		// This link is not present
		
		
		// Logout from 'Employee Portal'
		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	 */

	
	
	/**
	 * Test Case : 23029
	 * CSC BUCA: MCA: Confirmation and Release - Verify if Employee Clicks on "Confirmation and Release form" link on the My care Assist Reservation details page
	 * 
	 */

	@Test()
	public void EP_Reservations_ConfirmationAndRelease() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");


		this.loginEPPortal(5);
		
		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		//login employee portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"),signInArray);

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

		//Select Date of reservation
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation11"));

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation11")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		/* TO DO My CareAssist button is not available so I am not able to continue the script.Sanjeev Here*/
		//Check I am electing to use My CareAssist and click Continue
		COMMON_METHODS.clickElement(getTestObject("ROL_09"));
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		Thread.sleep(7000);
		
		//User will be navigated to the My CareAssist page
		COMMON_METHODS.VerifyTextPresent(getTestObject("CrisisCareAssist"), "Crisis Care Assist");
		
		// Verify  My CareAssist Reservation details page containing 4 sections with 2 links
		boolean ccResult = COMMON_METHODS.isElementPresent("//*[@id='pageTwoColRightSm']/li[1]/h5[1]", "xpath");
		boolean ccaResult =COMMON_METHODS.isElementPresent("//*[@id='pageTwoColRightSm']/li[1]/h5[2]", "xpath");
		boolean Step1LinkResult = COMMON_METHODS.isElementPresent("Step1Link", "linkText");
		boolean Step2LinkResult = COMMON_METHODS.isElementPresent("Step2Link", "xpath");
		if(ccResult && ccaResult && Step1LinkResult && Step2LinkResult)
			REPORTER.LogEvent(TestStatus.PASS, " Veryfying Link and sections to be displated", "My Care Assist Reservation details page containing all sections and both the links", "");
		else
			REPORTER.LogEvent(TestStatus.PASS, " Veryfying Link and sections to be displated", "My Care Assist Reservation details page NOT containing all sections and both the links", "");
		//Click on the "Confirmation and Release Form" button on Step-1 
		COMMON_METHODS.clickElement(getTestObject("CRLink"));
		
		//BUCA application should display the non-editable Confirmation and Release form
		boolean nm =COMMON_METHODS.isElementEnabledDisabled(getTestObject("EName"), true);
		boolean csz = COMMON_METHODS.isElementEnabledDisabled(getTestObject("CityStateZip"), true); 
		boolean add = COMMON_METHODS.isElementEnabledDisabled(getTestObject("EAddress"), true); 
		boolean emp =COMMON_METHODS.isElementEnabledDisabled(getTestObject("Employer"), true);
		
		if(nm && csz && add && emp)
			REPORTER.LogEvent(TestStatus.PASS, " Veryfying Employee Details fields are Editable or not?", "Employee Details fields are Editable", "");
		else
			REPORTER.LogEvent(TestStatus.PASS, " Veryfying Employee Details fields are Editable or not?", "Employee Details fields are NOT Editable", "");

		// getting values from all fields
		String Empname = COMMON_METHODS.getText(getTestObject("EName"));
		String EmpCityStateZip = COMMON_METHODS.getText(getTestObject("CityStateZip"));
		String EmpAddress = COMMON_METHODS.getText(getTestObject("EAddress"));
		String Employer = COMMON_METHODS.getText(getTestObject("Employer"));
		if((Empname!=null) && (EmpCityStateZip!=null) && (EmpAddress!=null) && (Employer!=null))
			REPORTER.LogEvent(TestStatus.PASS, " Veryfying Employee Details fields are pre populated", "Employee Details fields are pre populated", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, " Veryfying Employee Details fields are pre populated", "Employee Details fields are Not pre populated", "");

		// Logouts
		Utility.logout();
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
 

	}  
	
	
		

	/**
	 * Test Case : 23386
	 * BUCA - Automation - Reservations - My Care Assist - Ensure CareAssist is displayed for a user who has it enabled in their contract
	 * 
	 */

	@Test()
	public void EP_Reservations_MyCareAssist() throws Exception {

		//Lava Comments
		// This test case is not completed, because the application is not having My CareAssist button.

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		
		this.loginEPPortal(5);
		/*// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//login employee portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"),signInArray);
*/
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

		//Select Date of reservation
	//	COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation7"));
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation8"));

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation7"),getTestData("TD_Dateofreservation8")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		/* TO DO My CareAssist button is not available so I am not able to continue the script.Sanjeev Here*/
		//Check I am electing to use My CareAssist and click Continue
		COMMON_METHODS.clickElement(getTestObject("ROL_09"));
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		Thread.sleep(7000);
		
		//User will be navigated to the My CareAssist page
		COMMON_METHODS.VerifyTextPresent(getTestObject("CrisisCareAssist"), "Crisis Care Assist");
		

		//Utility.logout();
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}

	
	/**
	 * Author: Supraja
	 * Test Case : 23029
	 * CSC BUCA: MCA: Confirmation and Release - Verify if  Employee Clicks on "Confirmation and Release form" link on the My care Assist Reservation details page
	 * 
	 */

	@Test()
	public void EP_MCA_ConfirmationAndReleaseLink() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		
		this.loginEPPortal(5);
		/*// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//login employee portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"),signInArray);
		*/
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

		//Select Date of reservation
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation9"));

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation9")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		/* TO DO My CareAssist button is not available so I am not able to continue the script.*/
		
		//Check if "My Care Assist" tab is displayed as the default tab on Step 3 of the Reservation process
		

		//Utility.logout();
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}


	/**
	 * Author: Supraja
	 * Test Case : 23030
	 * CSC BUCA: MCA: Confirmation and Release - Verify if user enters data in ‘Acknowledgement and Release’ section 
	 * and clicks on the ‘Submit’ button on the Confirmation and Release form the My care Assist Reservation details page
	 * 
	 */

	@Test()
	public void EP_MCA_SubmitConfirmationAndReleaseForm() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(5);
		
		/*// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//login employee portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"),signInArray);*/
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

		//Select Date of reservation
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation10"));

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation10")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		/* TO DO My CareAssist button is not available so I am not able to continue the script.*/
		
		//Check if "My Care Assist" tab is displayed as the default tab on Step 3 of the Reservation process
		

		Utility.logout();
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}
	
	/**
	 * Author: Sanjeev
	 * Test Case : 23054
	 * CSC BUCA: (MCA): Back - BUCA Web Application should enable the Employee to go back to Step 2,
	 *  when (s) he clicks the ‘Back’ button on Step 3, on the My CareAssist tab
	 * Date : 12/04/2014
	 */

	@Test()
	public void EP_MCA_Step3ToStep2() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		/*//Verify Employer
		businessComponents.EP_verifyEmployer();
		//Accept policy
		businessComponents.EP_AcceptPolicyAndSubmit();

		//Registration
		businessComponents.EP_SignUpUser(5);
		
		//Center Based Reservation
		businessComponents.EP_Registration();*/	


		this.loginEPPortal(5);


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

		//Select Date of reservation
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation10"));
		//COMMON_METHODS.editAField(getTestObject("OL_85"), "05/05/2014");

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation2")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		// Verify Crisis care assist is by default selected with check box

		COMMON_METHODS.isElementEnabledDisabled(getTestObject("btnCrisisCareAssist"), false); 
		
		if(COMMON_METHODS.isElementPresent("VerifyCareAssist","id"))
			REPORTER.LogEvent(TestStatus.PASS, " Veryfying Crisis care assist is by default selected or Not?", "Crisis care assist is by default selected. with the check box 'I am electing to use Crisis Care Assist'", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, " Veryfying Crisis care assist is by default selected or Not?", "Crisis care assist is Not selected by default. with check box", "");

		// Click Back button and verify Step2
		COMMON_METHODS.clickElement(getTestObject("ROL_22"));
		Thread.sleep(7000);
		
		//Verify the wizard moves to step 2.		
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		// Logouts Finally
		Utility.logout();
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	
	
	


}
