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

public class EP_CrisisCareAssist_Reservation extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();

	/* //Reading Test Objects from Data excel 
	static{
		try{
			readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	
	/** @Author: Supraja
	 * Test Case : 23387 BUCA - Automation - Reservations - Crisis Care Assist - 
	 * Ensure CareAssist is displayed for a user who has it enabled in their contract
	 * 
	 * Test Case #24836: CSC BUCA - Step 3 (CCA): Crisis Care Assist Tab - 
	 * Verify if Employee is able to view ‘Crisis Care Assist’ tab as default tab in the Step 3 reservation if the client activated to use Crisis Care Assist
	 */
	
	String employer=null; 
	@Test @Parameters("client")
	public void EP_CCARes_Navigation(String employer) throws Exception {

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
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason, "Yes");

		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");

		//Select Date of reservation
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation6"));

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		Thread.sleep(5000);

		//Crisis CareAssist should be the defaulted choice
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_08"), true);

		//Check I am selecting to use Crisis CareAssist and click Continue
		COMMON_METHODS.checkBox(getTestObject("ROL_09"), "check");

		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);



	}
	
	/**
	 * @Author: Supraja
	 * Test Case #24836: CSC BUCA - Step 3 (CCA): Crisis Care Assist Tab - Verify if Employee is able to view ‘Crisis Care Assist’ tab 
	 * as default tab in the Step 3 reservation if the client activated to use Crisis Care Assist
	 * 
	 * Test Case #24795: CSC BUCA: CCA: Confirmation and Release - Verify if Employee Clicks on "Confirmation and Release form" link on the Crisis care Assist Reservation details page, 
	 * the BUCA application should display the non-editable data prepopulated
	 * 
	 */
	

	@Test()
	public void EP_Reservations_CrisisCareAssist() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		this.loginEPPortal("Client5");
		
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
		//COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation_CCA1")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		Thread.sleep(5000);

		//Crisis CareAssist should be the defaulted choice
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_08"), true);

		//Check I am selecting to use Crisis CareAssist and click Continue
		COMMON_METHODS.checkBox(getTestObject("ROL_09"), "check");

		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
		//Click on the "Confirmation and Release Form" button on Step-1
		COMMON_METHODS.clickElement(getTestObject("CCA_CRForm_01"));
		
		//Verify Confirmation and Release Form is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCA_CRForm_02"), "Confirmation and Release Form");
					
		
		//BUCA application should display the non-editable Confirmation and Release form with Employee name, 
		//Employee home address, City, State, Zip and Employer pre-populated.
		
		String EmployeeNameAddress = COMMON_METHODS.getText(getTestObject("CCA_CRForm_03"));
		
		System.out.println("EmployeeNameAddress :" + EmployeeNameAddress);

		if (EmployeeNameAddress.contains("BHAutoFN BHAutoLN"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName is pre-populated :", "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName Not pre-populated :", "");
		
		if (EmployeeNameAddress.contains("103 Fox Road "))
			REPORTER.LogEvent(TestStatus.PASS, "Verify  Address is populated or not", "Address is pre-populated :", "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Verify Address is populated or not", "Address Not pre-populated :", "");
		
		
		String cityStateEmployeer = COMMON_METHODS.getText(getTestObject("CCA_CRForm_04"));
		
		System.out.println("cityStateEmployeer :" + cityStateEmployeer);

		if (cityStateEmployeer.contains("Chicago, Illinois 98101"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify City, State, Zip is populated or not", "City, State, Zip is pre-populated :", "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Verify City, State, Zip is populated or not", "City, State, Zip Not pre-populated :", "");
		
		if (cityStateEmployeer.contains("ACC_Stab_Test 5"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify  Employer is populated or not", "Employer is pre-populated :", "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employer is populated or not", "Employer Not pre-populated :", "");
		
		
		
		/*
		
		String EmployeeName = COMMON_METHODS.getText(getTestObject("CCA_CRForm_03"), "value");
		//Verify Employee Name is pre-populated 
			if (EmployeeName.equalsIgnoreCase("BHAutoFN BHAutoLN"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName pre-populated :" + EmployeeName, "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName Not pre-populated :" + EmployeeName, "");
		
		
			String CityStateZip = COMMON_METHODS.getText(getTestObject("CCA_CRForm_04"), "value");
			
			
			
			
			
			
		//Verify CityStateZip is pre-populated 
				if (CityStateZip.equalsIgnoreCase("BHAutoFN BHAutoLN"))
					REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName pre-populated :" + EmployeeName, "");
				else
					REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName Not pre-populated :" + EmployeeName, "");
		
		
				String EmployeeAddress = COMMON_METHODS.getText(getTestObject("CCA_CRForm_05"), "value");
		//Verify EmployeeAddress is pre-populated 
				if (EmployeeAddress.equalsIgnoreCase("BHAutoFN BHAutoLN"))
					REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName pre-populated :" + EmployeeName, "");
				else
					REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName Not pre-populated :" + EmployeeName, "");
		
		
		
				String Employer = COMMON_METHODS.getText(getTestObject("CCA_CRForm_06"), "value");
		//Verify Employer is pre-populated 
				if (Employer.equalsIgnoreCase("BHAutoFN BHAutoLN"))
					REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName pre-populated :" + EmployeeName, "");
				else
					REPORTER.LogEvent(TestStatus.PASS, "Verify EmployeeName is populated or not", "EmployeeName Not pre-populated :" + EmployeeName, "");
		*/
				
				//Logout of the employee portal
				//Utility.logout();

				//Log to reports
				COMMON_METHODS.logToReportAfterPass(methodName);
		
		
	}
	
	
	/**
	 *  @Author: Supraja
	 * Test Case #24796: CSC BUCA: CCA: Confirmation and Release - Verify if user enters information in ‘Acknowledgement and Release’ section and Submits the Confirmation and Release form, 
	 * the BUCA Web application should display the Crisis CareAssist Reservation details page
	 * 
	 * Test Case #24815: CSC BUCA: Step 3 (CCA): Continue - 
	 * Verify Employee is able to view the Crisis CareAssist Reservation details page when user clicks on Continue on step-3
	 */
	

	@Test()
	public void EP_CCA_SubmitConfirmationandReleaseForm() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		this.loginEPPortal("Client5");
		
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
		String[] careDates = {getTestData("TD_Dateofreservation_CCA2")};
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
		Thread.sleep(5000);
		
		//Verify "Go To Reimbursement form" link is disabled link untill user fills the Confirmation and Release Form
		//COMMON_METHODS.isElementEnabledDisabled(getTestObject("CCA_RRForm_01"), false);
		
		//Click on the "Confirmation and Release Form" button on Step-1
		COMMON_METHODS.clickElement(getTestObject("CCA_CRForm_01"));
		Thread.sleep(5000);
		
		//Verify Confirmation and Release Form is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCA_CRForm_02"), "Confirmation and Release Form");
		
		//Employee enters his/her ‘Employee Full name’ and ‘Date’ under the ‘Acknowledgement and Release’ section 
		COMMON_METHODS.editAField(getTestObject("CCA_CRForm_07"), getTestData("TD_CRForm_EName"));
		COMMON_METHODS.editAField(getTestObject("CCA_CRForm_08"), getTestData("TD_CRForm_Date"));
		
		//Click on the ‘Submit’ button
		COMMON_METHODS.clickElement(getTestObject("CCA_CRForm_09"));
		Thread.sleep(5000);
		
		/* BUCA Web application should display the Crisis CareAssist Reservation details page with the Successful submission message on the top */
		if(COMMON_METHODS.getText(getTestObject("CCA_CRForm_10")).contains("Your Confirmation and Release form has been submitted")){
			REPORTER.LogEvent(TestStatus.PASS, "Confirmation and Release form is submitted", "Confirmation and Release form is submitted", "");
		}
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Confirmation and Release form is NOT submitted", "Confirmation and Release form is NOT submitted", "");
		}
		

		//Verify Step 1: Confirmation and Release Module displays with link to "View Form on File
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCA_CRForm_11"), "View Form On File");
		
		//Verify "Go To Reimbursement form is enabled/disabled" link is enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("CCA_RRForm_01"), true);
		
		//Logout of the employee portal
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
		
		
	}
	
	
	
	/**
	 * @Author: Kiran
	 * Test Case #24765: CSC BUCA - CCA: Submit Request for Reimbursement - 
	 * Verify the Crisis CareAssist Reservation details page once the user submits the Reimbursement Request Form
	 * 
	 * This method should be executed 1 day after the above methods is executed
	 */
	
	@Test()
	public void EP_CCA_SubmitReimbursementForm() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");
		
		this.loginEPPortal("Client5");
		
		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//login employee portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.reservation.userName"), getTestData("TD_PWD"),signInArray);
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), "InProgress", "byValue");
		Thread.sleep(5000);
				
		// Click on 'View' link on a reservation 
		COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		Thread.sleep(5000);

		//Verify Step 1: Confirmation and Release Module displays with link to "View Form on File
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCA_CRForm_11"), "View Form On File");
		
		//Verify "Go To Reimbursement form is enabled/disabled" link is enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("CCA_RRForm_01"), true);
		COMMON_METHODS.clickElement(getTestObject("CCA_RRForm_01"));
			
					
		//Verify that the Employee name field is pre populated
		COMMON_METHODS.isElementPresent("EmployeeName", "id");

		//Verify the value present in the Employee text box
		if(!COMMON_METHODS.getText(getTestObject("EName"),"value").isEmpty()){
			REPORTER.LogEvent(TestStatus.PASS, "Employee name field is updated", "Employee name field is NOT empty", "");
		}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Employee name field is updated", "Employee name field is empty", "");
		}

		//Verify that the Employer field is present
		COMMON_METHODS.isElementPresent("Employer", "id");

		//Verify the value present in the Employer Text box
		if(!COMMON_METHODS.getText(getTestObject("Employer"),"value").isEmpty()){
			REPORTER.LogEvent(TestStatus.PASS, "Employer field is updated", "Employer field is NOT empty", "");
		}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Employer field is updated", "Employer field is empty", "");
		}

		//Verify that the Employer Address field is present
		COMMON_METHODS.isElementPresent("EmployeeAddress", "id");

		//Verify the value present in the Employer Address Text box
		if(!COMMON_METHODS.getText(getTestObject("EAddress"),"value").isEmpty()){
			REPORTER.LogEvent(TestStatus.PASS, "Adress field is updated", "Adress field is NOT empty", "");
		}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Adress field is updated", "Adress field is empty", "");
		}

		//Verify that the Child Care Center/Caregiver Name field is present
		COMMON_METHODS.isElementPresent("Caregiver", "id");

		//Input a value for Care giver
		COMMON_METHODS.editAField(getTestObject("KMA_85"), "CareGiver");

		//Verify that the City, State, Zip field is present
		COMMON_METHODS.isElementPresent("CityStateZip", "id");

		//Verify the value present in the City, State, Zip Text box
		if(!COMMON_METHODS.getText(getTestObject("CityStateZip"),"value").isEmpty()){
			REPORTER.LogEvent(TestStatus.PASS, "City field is updated", "City field is NOT empty", "");
		}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "City field is updated", "City field is empty", "");
		}

		//Verify that the Reservation Number field is present
		COMMON_METHODS.isElementPresent("ReservationNumber", "id");

		//Verify the Reservation Number Text box is NOT empty
		if(!COMMON_METHODS.getText(getTestObject("CityStateZip"),"value").isEmpty()){
			REPORTER.LogEvent(TestStatus.PASS, "Reservation field is updated", "Reservation field is NOT empty", "");
		}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Reservation field is updated", "Reservation field is empty", "");
		}

		// Click on Child care center radio button
		//COMMON_METHODS.radioButton(getTestObject("KMA_87"));

		//Verify the Reimbursement total
		if(COMMON_METHODS.getText(getTestObject("KMA_88"),"value").equals("($50.00/day)")){
			REPORTER.LogEvent(TestStatus.PASS, "Reimbursement Total field is updated", "Reimbursement Total field is updated correctly", "");
		}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Reimbursement Total field is Not updated", "Reimbursement Total field is Not updated correctly", "");
		}

		//Click on In-Home Center radio button
		COMMON_METHODS.radioButton(getTestObject("KMA_89"));

		//Verify the Reimbursement total	
		if(COMMON_METHODS.getText(getTestObject("KMA_92"),"value").equals("($80.00/day)")){
			REPORTER.LogEvent(TestStatus.PASS, "Reimbursement Total field is updated", "Reimbursement Total field is updated correctly", "");
		}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Reimbursement Total field is Not updated", "Reimbursement Total field is Not updated correctly", "");
		}

		//Verify that the date field is present
		COMMON_METHODS.isElementPresent("//table[@class='tableGrid']/tbody/tr[2]/td[1]", "xpath");
		
		//Verify that the Hours drop down is present
		COMMON_METHODS.isElementPresent("ddlHrs00", "id");
		
		//Verify that the Minutes dropdown is present
		COMMON_METHODS.isElementPresent("ddlMinutes00", "id");
		
		//Verify that the Care Recipient field is present
		COMMON_METHODS.isElementPresent("//table[@class='tableGrid']/tbody/tr[2]/td[4]", "xpath");
		
		//Input a value for Employee name field
		COMMON_METHODS.editAField(getTestObject("EmpFullName"),getTestData("TD_GreeterName"));
		
		//Input a value for a date field
		COMMON_METHODS.editAField(getTestObject("RDate"),"04/18/2014");
		
		//Click on Submit button
		//COMMON_METHODS.clickElement(getTestObject("Submit_form"));
		
		
		//Logout of the employee portal
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
				
		
		
	}
	
	
	
	private void loginEPPortal(String ClientName) throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			
			//Login to Emp Portal
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(ClientName+"."+"genres"+"."+"userName"), getTestData("TD_PWD"),signInArray);
			/*businessComponents.LoginEmployeeportal(
					ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"userName"),
					getTestData("TD_PWD"));*/
		}
	}
	
		
	
}
	
	