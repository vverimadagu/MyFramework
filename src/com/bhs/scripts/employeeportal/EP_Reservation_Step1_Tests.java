/**
 * 
 */
package com.bhs.scripts.employeeportal;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

/**
 * @author vverimadugu
 *
 */
public class EP_Reservation_Step1_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents(); 
	String aCareReciText;
	int careCount;
	String aContactsText;
	int aContactsCount;
	String utilization;
	String userName;
	String password;
	
	
	/** This test will cover below 2 test cases
	 * BUCA - Reservation Wizard - Step1 - ensure user can view and select all Care Recipients
	 * TFS ID#8563
	 * 
	 * BUCA - Step 1: Select and verify Reason for Care values in the drop down  
		 * TFS ID#
		 * @author vverimadugu
	 * @author vverimadugu
	 * 
	 * */
	
	
	String employer=null;
	@Test @Parameters("client")
	public void viewAndSelectReasonForcare(String employer) throws Exception {
		//@Parameters("client")
		//String client=TC.getCurrentXmlTest().
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//System.out.println("Inside - " + methodName);
		//int clientNo=Integer.parseInt(1);	
		//Read testdata for based on specific clients
		//readTestData(getDataTablePath("EP"), "TD_EP"+clientNo);
		
		//Create user with client1
		createUser(employer);
		
		//Read test data from config file
		userName=ReadwritDataFromProps.props.getProperty(employer + ".resstep1.userName");
		password=getTestData("TD_PWD");
		 
		 //Click on Home link
		 Utility.clickLink(getTestObject("HT_05"));
		 
		//Get CareReciText count
		 aCareReciText = COMMON_METHODS.getText(getTestObject("RES_02"));
		 careCount= Integer.parseInt(aCareReciText.substring(17, 18));
		 
		//Get authorised contact count
		aContactsText = COMMON_METHODS.getText(getTestObject("RES_01"));
		aContactsCount= Integer.parseInt(aContactsText.substring(21, 22));
		
		//Get utilization hours
		utilization=COMMON_METHODS.getText(getTestObject("CR_09"));
		
		businessComponents.EP_NavigateToReservation();
		//Verify Select a Reason For Care text
		businessComponents.EP_verifyText("1. Select a reason For Care", "Care Recipients","h5");
		
		//Select and verify Reason for Care values in the drop down 
		String selectForreason[]={"Select a Reason For Care","Business travel/conference",
				   "Flexible work conflict","Flexible work conflict",
				   "Flexible work conflict","Introductory visit",
				   "Jury duty/service","Looking for regular care",
				   "Maternity/paternity transition","Provider family illness or emergency",
				   "Provider ill, on vacation, or unavailable","Relocation",
				   "School not in session","Stay-at-home spouse unavailable",
				   "Teacher conference"};
		
		for(int i=0;i<selectForreason.length;i++){
			COMMON_METHODS.listBoxSelect(getTestObject("OL_81"),selectForreason[i],"byVisibleText");
		}
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	/**
	 * BUCA - Reservation Wizard - Step1 - ensure user can view and select all Care Recipients
	 * TFS ID#12103
	 * 
	 * */
	
	
	@Test
	public void  healthStatusInHomeEligible() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Verify login page and login
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP();
			
		}
		
		//Navigate to My First Reservation
		businessComponents.EP_NavigateToReservation();
		
		/**
		 * @TODO
		 * Functionality not yet developed
		 * 	Verify the Health Status displays
		 * 
		 * */
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	/**
	 * BUCA - Reservation Wizard - Step1 - ensure page loads with default fields in correct state
	 * TFS ID#13657
	 * 
	 * */
	@Test
	public void careRecipientsWithDefaultFields() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Verify login page and login
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP();
			
		}
		
		//Navigate to Reservation page
		businessComponents.EP_NavigateToReservation();
		
		//Test data for Step 1 CareRecipients
		//int CareRecipients=3;
		int healthStatus[]=new int[careCount];
		for(int i=0;i<careCount;i++){
			if(i!=1 || i!=3)
				healthStatus[i]=1;
			else 
				healthStatus[i]=2;
			
		}
		/*
		 
		 * */
		
		//Ensure Type of Reservation is left blank 
		businessComponents.EP_ReservationCareRecipients(0,healthStatus,null,"Yes");
		
		/*Ensure that you can chose a Reservation Type and that the system provides 
		an error message and will not conitnue if the type is not present.*/
		try {
			if("Care reason required".equalsIgnoreCase(COMMON_METHODS.getText(getTestObject("CR_11")))){
				REPORTER.LogEvent(TestStatus.PASS, "Care reason required is displayed", "Care reason required is displayed", "");
			}
		} catch (Exception e) {
			REPORTER.catchException(e, "Care reason required is not displayed");
		}
		
		/*  Ensure the Care Recipients list populates
		 * 	Verify the system puts all the Care Recipients
		 *  in the list and that Health Status is NOT selected.
		 *  Select a Care Recipient
		 * */
		String[] sTempCare = COMMON_METHODS.splitTestObject(getTestObject("WW_02"));
		String[] sTempHealth = COMMON_METHODS.splitTestObject(getTestObject("WW_03"));
		//Select any Care Recipient, by checking check box next to name
		for(int i=0;i<careCount;i++){
			String careRecipient;
			String health;
			String tempCare=sTempCare[3]+i+"__IsSelected";
			careRecipient=sTempCare[0]+","+sTempCare[1]+","+sTempCare[2]+","+tempCare;
			String tempHealth=sTempHealth[3]+i+"__IsHealthy']"+"["+healthStatus[i]+"]";
			health=sTempHealth[0]+","+sTempHealth[1]+","+sTempHealth[2]+","+tempHealth;
			if(COMMON_METHODS.isElementPresent("id",tempCare)){
				//Select any Care Recipient, by checking check box next to name
				COMMON_METHODS.checkBox(careRecipient, "check");
				
				//Select any Care Recipient, by checking check box next to name
				COMMON_METHODS.checkBox(careRecipient, "uncheck");
				
				//Select Health Status of the selected Care Recipient
				if(!COMMON_METHODS.VerifyRadioButtonSelected(health))
					REPORTER.LogEvent(TestStatus.PASS, "Health Status is not selected by default "," Health Status is not selected by default","");
				else
					REPORTER.LogEvent(TestStatus.WARNING, "Health Status is  selected by default "," Health Status is  selected by default","");
				
				tempCare=null;
				tempHealth=null;
			}
		}
		
		//select reason for care
		COMMON_METHODS.listBoxSelect(getTestObject("OL_81"), getTestData("TD_ReasonForCare"), "byVisibleText");
		
		//Click continue
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
		/* Ensure that none are selected on page load and that error
		  message will display if none is selected when trying to move to step 2.*/
		try {
			if("Please select Recipient(s) for reservation".equalsIgnoreCase(COMMON_METHODS.getText(getTestObject("CR_11")))){
				REPORTER.LogEvent(TestStatus.PASS, "Please select Recipient(s) for reservation is displayed", "Please select Recipient(s) for reservation", "");
			}
		} catch (Exception e) {
			REPORTER.catchException(e, "Please select Recipient(s) for reservation is not displayed");
		}
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	/** This Test cover below 2 Test cases
	 * BUCA - Reservation Wizard - Step1 - Ensure user is able to continue to Step 2 via 'Continue' button
	 * TFS ID#12102
	 * BUCA - Reservation Wizard - Step1 - Verify the system populates the
	 * My Reservations page with Care Recipients.
	 * 
	 * TFS ID#5237
	 * @author vverimadugu
	 * */
	
	@Test
	public void verifyCareRecipientsAndContinueToStep2() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		
		//Verify login page and login
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP();
			
		}
		//Navigate to Reservation page
		businessComponents.EP_NavigateToReservation();
		
		//Test data for Step 1 CareRecipients
		//int CareRecipients=3;
		int healthStatus[]=new int[careCount];
		for(int i=0;i<careCount;i++){
			if(i==1 || i==3)
				healthStatus[i]=2;
			else 
				healthStatus[i]=1;
			
		}
		String selectForReason=getTestData("TD_ReasonForCare");
		
		//Verify Care Recipients displayed
		if(COMMON_METHODS.getText(getTestObject("CR_14")).contains(getTestData("TD_CR_FirstName")+" "+getTestData("TD_CR_LastName")))
			REPORTER.LogEvent(TestStatus.PASS, "Care Recipients Displayed", "Care Recipients Displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Care Recipients Not Displayed", "Care Recipients Not Displayed", "");	
	
		
		/*
		 * Select a Reason For Care from drop down
		 * Select a Care Recipient from the list.
		 * Select a Health Status.
		 * Click on "Continue"
		 * */
		
		//Step 1 CareRecipients
		businessComponents.EP_ReservationCareRecipients(careCount,healthStatus,selectForReason,"Yes");
		
		// 	Ensure the wizard moves to step 2
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Logout
		//businessComponents.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	
	}
	
	/**
	 * BUCA - Reservation Wizard - Step1 - ensure user can view and select all Care Recipients
	 * TFS ID#8562
	 * @author vverimadugu
	 * */
	
	@Test 
	public void viewAndSelectCareRecipients() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
						
		
		//Verify login page and login
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP();
			
		}
		//Navigate to Reservation page
		businessComponents.EP_NavigateToReservation();
		
		
		/* Ensure the following sections are displayed: Who needs care and why?, 1. Select a Reason for care, 
		   2> select who needs care and their health status, Overview for Care policy	
		*/
		
		//Verify Who Needs Care and Why? text
		businessComponents.EP_verifyText("Who Needs Care and Why?", "Care Recipients","h2");
				 
		//Verify Select a Reason For Care text
		businessComponents.EP_verifyText("1. Select a reason For Care", "Care Recipients","h5");
		
		//Verify 2. Select Who Needs Care text
		businessComponents.EP_verifyText("2. Select Who Needs Care", "Care Recipients","#rightOther > h5.yellow");
		
		//Verify 2. Select Who Needs Care text
		businessComponents.EP_verifyText("Overview for Care Policy", "Care Recipients","div.t14Caps");
		
		//Test data for Step 1 CareRecipients 
		//int CareRecipients=3;
		int healthStatus[]=new int[careCount];
		for(int i=0;i<careCount;i++){
			if(i!=1 && i!=3)
				healthStatus[i]=1;
			else 
				healthStatus[i]=2;
			
		}
		String selectForReason=getTestData("TD_ReasonForCare");
		
		/*
		 * Ensure care recipients with their check boxed are displayed correctly
		 * Ensure Healthy/Mildly-Ill (In-Home Only) radio buttons display correctly
		 * Ensure User can add a care recipient within step 1
		 * Ensure remaining care for the care recipients is displayed correctly
		 * */
		//Step 1 CareRecipients
		businessComponents.EP_ReservationCareRecipients(careCount,healthStatus,selectForReason,"No");
		Utility.clickLink(getTestObject("CR_01"));
		String[][] tesData = { { "Yes", "Friend", "", "", "" },
			{ "No", "", "", "", "" }, { "No", "", "", "", "" },
			{ "No", "", "", "", "" }};
		//businessComponents.EP_AuthorizedContactsAccess(aContactsCount,tesData,"Yes","No");
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
		businessComponents.addCareRecipients(addCrData3_5Years,"Yes",employer);
		//Ensure continue button is present
		
		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		Thread.sleep(2000);
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_84"), "Continue","value");
		
		
		//Ensure utilization numbers are correct 
		businessComponents.EP_ClickHomeBottomLinks("Home");
		//Veriy care count is increased by 1
		aCareReciText = COMMON_METHODS.getText(getTestObject("RES_02"));
		int careCountAfterAdd= Integer.parseInt(aCareReciText.substring(17, 18));
		
		if(careCountAfterAdd==(careCount+1))
			REPORTER.LogEvent(TestStatus.PASS, "Care Recipent is Added", "Care Recipent Added", "");
		
		else
			REPORTER.LogEvent(TestStatus.WARNING, "Care Recipent count is not getting increased", "Care Recipent count is not getting increased", "");
		
		try {
			if(utilization.equalsIgnoreCase(COMMON_METHODS.getText(getTestObject("CR_09")))){
				REPORTER.LogEvent(TestStatus.PASS, "Verify Utilization is Correct", "Utilization value is verified", "");
			}
		} catch (Exception e) {
			REPORTER.catchException(e, "Utilization is wrong");
		}
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
		
		/**
		 * BUCA - Reservations Wizard - Ensure user can chose a Special Program reservation on Step 1.
		 * BUCA - Reservations Wizard - Ensure user can view details of a Special Program
		 * TFS ID# 13548 and 13551
		 * @author vverimadugu
		 * */
		
		@Test
		public void specialProgramReservation() throws Exception {
			
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
						
			//Verify login page and login
			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
				loginEP();
				
			}
			//Navigate to Reservation page
			businessComponents.EP_NavigateToReservation();
			
			//Verify that the Special Programs Radio Button shows.
			COMMON_METHODS.radioButton(getTestObject("CR_12"));
			
			//Verify that the 1. Select A Special Program text displays.
			businessComponents.EP_verifyText("1. Select A Special Program", "Special Program", "#SpecialPrograms > h5.yellow");
			
			//Verify that the Acc_Stab_SP_NEW CAMP (ACC_Stab_Test 1 text displays.
			businessComponents.EP_verifyText("Acc_Stab_SP_NEW CAMP (ACC_Stab_Test 1)", "Special Program", "span.t14Caps");
			
			//Click on View details link
			COMMON_METHODS.clickElement(getTestObject("CR_17"));
			
			//View Select special program 
			//verify text Default Description value for Special Program
			COMMON_METHODS.VerifyTextPresent(getTestObject("CR_18"), "Default Description value for Special Program");
			//verify text Utilization
			COMMON_METHODS.VerifyTextPresent(getTestObject("CR_19"), "Utilization");
			//verify text Default Utilization value for Special Program
			if(COMMON_METHODS.getText(getTestObject("CR_21")).contains("Default Utilization value for Special Program"))
				REPORTER.LogEvent(TestStatus.PASS, "Default Utilization value for Special Program Displayed", "Default Utilization value for Special Program Displayed", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL,"Excepted= Default Utilization value for Special Program","Actual= "+COMMON_METHODS.getText(getTestObject("CR_21")), "");
			//verify text Co-Payments
			COMMON_METHODS.VerifyTextPresent(getTestObject("CR_20"), "Co-Payments");
			
			//verify text Default Copayments value for Special Program
			if(COMMON_METHODS.getText(getTestObject("CR_22")).contains("Default Copayments value for Special Program"))
				REPORTER.LogEvent(TestStatus.PASS, "Default Copayments value for Special Program Displayed", "Default Copayments value for Special Program Displayed", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL, "Default Copayments value for Special Program Not Displayed", "Default Copayments value for Special Program Not Displayed", "");
			//verify text Program Dates
			COMMON_METHODS.VerifyTextPresent(getTestObject("CR_23"), "Program Dates");
			
			
			//Select special program and select care recipient and click on continue
			businessComponents.selectSpecialProgram();
			
			// 	Ensure the wizard moves to step 2
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
			
			//Logout from ep
			//businessComponents.logout();
			
			//Log to reports
			COMMON_METHODS.logToReportAfterPass(methodName);
		}
		
		
		
		/** This Test covers below 2 test cases
		 * BUCA - Reservation Wizard - Step1 - ensure 'Learn More' link by special programs functions correctly
		 * TFS ID#11844
		 * 
		 * BUCA - Reservation Wizard - Step1 - ensure the 'Special Programs' is only viewable by eligable 
		 * clients (is not shown by default)
		 * 
		 * TFS ID# 11835
		 * 
		 * @author vverimadugu
		 * */
		
		@Test
		public void  learnMoreSpecialPrograms() throws Exception {
			
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
			
			//Verify login page and login
			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
				loginEP();
				
			}
			//Navigate to Reservation page
			businessComponents.EP_NavigateToReservation();
			
			//Verify that the Special Programs Radio Button shows.
			COMMON_METHODS.radioButton(getTestObject("CR_12"));
			
			//Verify Special Programs text
			businessComponents.EP_verifyText("Who Needs Care and Why?", "Special Programs","h2");
			
			//Verify Select A Special Program text 
			
			try {
				if("1. Select A Special Program".equalsIgnoreCase(COMMON_METHODS.getText(getTestObject("CR_13")))){
					REPORTER.LogEvent(TestStatus.PASS, "Select A Special Program is displayed", "Select A Special Program", "");
				}
			} catch (Exception e) {
				REPORTER.catchException(e, "Select A Special Program is not displayed");
			}
			
			
			//Click Learn More link
			COMMON_METHODS.clickElement(getTestObject("CR_10"));
			
			//Verify Special Programs text
			businessComponents.EP_verifyText("Special Programs", "Special Programs","h2");
			
			//Logout from ep
			Utility.logout();
			
			String userName1=ReadwritDataFromProps.props.getProperty("client2.userName");
			String password1=getTestData("TD_PWD");
			//Login EP 
			loginEP();

			//Navigate to My First Reservation
			businessComponents.EP_NavigateToReservation();
			
			// ensure the 'Special Programs' is not viewable by non eligible clients
			if(!COMMON_METHODS.isElementPresent("xpath",getTestObject("CR_12")))
				REPORTER.LogEvent(TestStatus.PASS, "Special Programs is not visible for client2", "Special Programs is not visible", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL, "Special Programs visible for client2", "Special Programs is visible", "");
					
			//Logout from ep
			Utility.logout();
			
			//Log to reports
			COMMON_METHODS.logToReportAfterPass(methodName);
			
		}
		
		private void loginEP() throws Exception {
			
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(userName, password,signInArray);
			
	}
		private void createUser(String clientNo) throws Exception {
			
		//Read testdata for based on specific clients
		//readTestObject(getDataTablePath("EP"), "TO_EP");		
		//readTestData(getDataTablePath("EP"), "TD_EP"+clientNo);
		
		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		// Verify Employer after Entering Employer ID and Password 
		businessComponents.EP_verifyEmployer(clientNo);
		
		// Accept privacy policy
		businessComponents.EP_AcceptPolicyAndSubmit();
		
		//Create dynamic user name
		String userName = createDyanamicUserData();
		
		// Register a New User
		businessComponents.EP_SignUpUser(userName,clientNo,"resstep1");

		//Center Based Reservation
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
		businessComponents.EP_Registration(addCrData3_5Years,"No",clientNo);
		
		}
		
		@DataProvider(name = "clients")
	    public static Object[] createClients() {
	        return new Object[] {1,2,3,4,5,6,7,8,9,10};
	}
		}
