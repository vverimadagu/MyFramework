package com.bhs.scripts.employeeportal;

import java.io.IOException;
import java.util.Calendar;

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
import com.bhs.util.Utility;
import com.bhs.util.INITIALIZE.TestStatus;

public class EP_CareProfiles_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	String employer=null;
	
	
	@Test(priority = 1) @Parameters("client") 
	public void EP_CareProfileSignupUser(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		
		this.employer = employer;

		// launch browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Verify Employer
		businessComponents.EP_verifyEmployer(employer);

		// Accept policy and submit
		businessComponents.EP_AcceptPolicyAndSubmit();

		// Sign Up
		//businessComponents.EP_SignUpUser(60);
		//Create dynamic user name
		String userName = createDyanamicUserData();
		// Registration
		businessComponents.EP_SignUpUser(userName, employer, "cpu");

		// logout
		// businessComponents.logout();

		// Log Pass to Result logs
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Care Profile - My Profile -
	 * Ensure user is able to update employee profile information 
	 * TFS ID : 23359
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 2) @Parameters("client") 
	public void EP_CareProfileMyProfileTest(String client) throws Exception {

		
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		/*employer=client;
		
		Utility.launchBrowser(getTestData("TD_EP_URL"));*/
		
		/*this.employer="5";*/
		
		loginClient();

		// Go back to Home Page
		businessComponents.EP_ClickHomeBottomLinks("Home");

		// Find the 'Create Your Care Profile' button and click the link
		COMMON_METHODS.clickElement(getTestObject("OL_114"));
		
/*		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));*/

/*		// Click the employee's name link under Employee Profile
		Utility.clickLink(getTestObject("OL_26"));*/

		// Fill in all fields in the Employee Profile of the Care Profile and
		// click 'Update Employee Profile' button
		businessComponents.EP_UpdateEmployeeprofile(client);

		// Click the employee's name link under Employee Profile
		Utility.clickLink(getTestObject("OL_26"));

		// Ensure all fields that were filled out contain the correct data
		this.EP_VerifyPersonalInformation(employer);

		// businessComponents.logout();

		// Log Pass to Result logs
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Care Profile - Employee Profile
	 * - Ensure 'Add Me As a Care Recipient' link functions correctly TFS ID :
	 * 23360
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void EP_CareProfileEmployeeProfileTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
		// SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
		/*readTestObject(getDataTablePath("EP"), "TO_EP");

		 1
		

		// launch browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));*/

		/*// login employee portal
		businessComponents.LoginEmployeeportal(
				ReadwritDataFromProps.props.getProperty("client2.userName"),
				getTestData("TD_PWD"));*/
		 
		/*//Verify Login Page displayed
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3") };
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.cpu.userName"), getTestData("TD_PWD"),signInArray);*/
		
		/*loginClient();*/
		
		loginClient();

		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click the employee's name link under Employee Profile
		Utility.clickLink(getTestObject("OL_26"));

		// Make sure the following links are there (Add Me As a Care Recipient,
		// View Payment Policies, Add a Payment Method)
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_115"),
				"Add Me as a Care Recipient");

		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_116"),
				"View Payment Policies");

		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_117"),
				"ADD A PAYMENT METHOD");

		// Click Add Me As a Care Recipient link
		COMMON_METHODS.clickElement(getTestObject("OL_115"));

		// Hidden fields Birthday Date, Height, Weight, Health Information and
		// Restrictions, Other Helpful Information and a link "Cancel Self Care"
		// will be display

		// Verify Hidden field Birthday Date displaying or not
		COMMON_METHODS
				.VerifyTextPresent(getTestObject("OL_118"), "Birth Date*");

		// Verify Hidden field Height displaying or not
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_119"), "Height**");

		// Verify Hidden field Weight displaying or not
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_120"), "Weight**");

		// Verify Hidden field Health Information and Restrictions displaying or
		// not
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_121"),
				"Health Information and Restrictions");

		// Verify Hidden field Other Helpful Information displaying or not
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_122"),
				"Other Helpful Information");

		// Verify hidden field link "Cancel Self Care" displaying or not
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_123"),
				"Cancel Self Care");

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of Care Profile - Add Care
	 * Recipients - Ensure user is able to add multiple Care Recipients employee
	 * profile information TFS ID : 23361
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 4) @Parameters("client") 
	public void EP_CareProfileAddCareRecipientsTest(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// businessComponents.EP_LaunchBrowser(TC);

		// login employee portal
		// businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		// getTestData("TD_PWD"));
		
		loginClient();

		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));

		//Test data for adding Care Recipients
		String addCrData[] = {
				getTestData("TD_CR_FirstName"),
				getTestData("TD_CR_LastName"), "Father",
				getTestData("TD_DOB"),
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

		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	/**
	 * BUCA - Care Recipients - user is able to set a care recipient to inactive
	 *  TFS ID : 18557
	 */
	@Test(priority=5) @Parameters("client") 
	public void EP_Care_Recepient_Inactive(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		System.out.println("Inside - " + methodName);

		this.employer = employer;
		
		
		// Launch browser
		//Utility.launchBrowser(getTestData("TD_URL"));
		//Utility.launchBrowser(getTestData("TD_EP_URL"));

		// login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				//.getProperty("client60.userName"), getTestData("TD_PWD"));
		
		loginClient();

	 	String addCrData3_5Years[] = {
				getTestData("TD_CR_FirstName1"),
				getTestData("TD_CR_LastName1"), "Father",
				getTestData("TD_DOB"),
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

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		Thread.sleep(5000);
		
		
		businessComponents.addCareRecipients(addCrData3_5Years,"No",employer);
		
		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		//Calling a Add a new care recepient method
		// businessComponents.Inactive_Care_Recepients();
 
		/*//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));*/
 
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_71"));

		//Click on Inactive Radio button
		COMMON_METHODS.radioButton(getTestObject("MA_66"));

		//Input an text for Inactive Care Recepient
		COMMON_METHODS.editAField(getTestObject("MA_67"), "Test");

		//Click on Update care Recepient
		COMMON_METHODS.clickElement(getTestObject("MA_68"));

		try {
			WebElement webElement = BH_SetUp_TearDown.driver.findElement(By.linkText("TD_CR_FirstName1"+" "+"TD_CR_LastName1"));
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Care Recepient is hidden from the UI ", "Care Recepient is not hidden from the UI " , "");
		} catch (Exception e) {

			REPORTER.LogEvent(TestStatus.PASS, "Ensure Care Recepient is hidden from the UI ", "Care Recepient is hidden from the UI successfully" , "");
		}

		//Logout from the Employee portal
		//businessComponents.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	/**
	 * Care Recipients - Selecting a Care Recipient displays their profile
	 *  TFS ID : 4126
	 */
	@Test(priority=6)
	public void EP_Care_Recepient_Display_Profile() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		//this.employer = "5";

		// launch browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		loginClient();

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_65"));
		
		Thread.sleep(3000);
		
		businessComponents.Care_Profile_Verify();
		
		//Logout from the Employee portal
		//businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
			
	}	
	
	/**
	 * Care Recipients - system displays error message if there are missing or invalid fields
	 *  TFS ID : 3787
	 */
	@Test(priority=7)
	public void EP_Add_New_Care_Recepient_Error() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		loginClient();

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on Add New care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_64"));
		
		//Click on Add Care recepient
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Verify the Text in the error message
		COMMON_METHODS.verifyElementDisplayed(getTestObject("MA_69"));
				
		//Logout from the Employee portal
		//businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	}

	/**
	 * Care Recipients - User is able to update a Care Recipient profile
	 *  TFS ID : 4127
	 */
	@Test(priority=8)
	public void EP_Update_Care_Recepient() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		/*// Launch browser
		businessComponents.EP_LaunchBrowser(TC);

		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				.getProperty("client5.userName"), getTestData("TD_PWD"));*/
		
		
		
		loginClient();

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_65"));
		
		//Clear the Primary language field is cleared
		COMMON_METHODS.editAField(getTestObject("MA_72"), " ");
		
		//Update the Primary language field to Spanish
		COMMON_METHODS.editAField(getTestObject("MA_72"), getTestData("TD_Language"));
		
		//Click on Update Care Recepient
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_65"));
		
		//Verify the text in the Primary language field is updated or not
		if(COMMON_METHODS.getText(getTestObject("MA_72"),"value").equals(getTestData("TD_Language")))
			REPORTER.LogEvent(TestStatus.PASS, "Primary language field is updated", "Primary language field is updated", "");
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Primary language field is Not updated", "Primary language field is Not updated", "");
		}
		
					
		/*//Logout from the Employee portal
		businessComponents.logout();*/
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	}
	
	/**
	 * BUCA - Care Recipients - user is able and update to add new care recipients
	 *  TFS ID : 3784
	 */
	@Test(priority=9) @Parameters("client") 
	public void EP_Add_Update_New_Care_Recepient(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

/*		// Launch browser
		businessComponents.EP_LaunchBrowser(TC);

		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				.getProperty("client5.userName"), getTestData("TD_PWD"));
*/
		loginClient();
		
		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on Add New care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_64"));
		
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
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_75"));
		
		//Clear the Primary language field is cleared
		COMMON_METHODS.editAField(getTestObject("MA_72"), " ");
		
		//Update the Primary language field to Spanish
		COMMON_METHODS.editAField(getTestObject("MA_72"), getTestData("TD_Language"));
		
		//Click on Update Care Recepient
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_75"));
		
		//Verify the text in the Primary language field is updated or not
		if(COMMON_METHODS.getText(getTestObject("MA_72"),"value").equals(getTestData("TD_Language")))
			REPORTER.LogEvent(TestStatus.PASS, "Primary language field is updated", "Primary language field is updated", "");
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Primary language field is Not updated", "Primary language field is Not updated", "");
		}
							
		//Logout from the Employee portal
		//businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	
	}
	
	/**
	 * This test script covers functionality of BUCA - My Care Profile - Care Recipients - 
	 * system displays error message if there are missing or invalid fields
	 * Requirement ID : 13800
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority=10)
	public void EP_SignUp_CareRecipients_Error() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		/*//Launch Browser
		businessComponents.EP_LaunchBrowser(TC);*/

		/*//Enter Employee details for verification
		businessComponents.EP_verifyEmployer();
		
		//Accept the policy and click on Submit button
		businessComponents.EP_AcceptPolicyAndSubmit();
				
		businessComponents.EP_SignUpUser(102);
		
		COMMON_METHODS.clickElement(getTestObject("SOL_02"));
		
		//Update the Employee profile
		businessComponents.EP_UpdateEmployeeprofile();*/
		
		loginClient();
						
		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on Add New care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_64"));
				
		//Click on Continue button without filling any information 
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Verify the Text in the error message for missing fields
		COMMON_METHODS.verifyElementDisplayed(getTestObject("MA_69"));
		
		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on Add New care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_64"));
		
		//Enter Birth Date
		COMMON_METHODS.editAField(getTestObject("OL_45"), getTestData("TD_DOB"));
		//Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_46"));
		
		Thread.sleep(3000);
				
		//Input a numeric value in invalid format in Physician Phone number field
		COMMON_METHODS.editAField(getTestObject("MA_74"),getTestData("TD_Language"));
		
		//Click on Continue button without filling any information 
		COMMON_METHODS.clickElement(getTestObject("OL_57"));		
		
		//Verify the Text in the error message for invalid fields
		COMMON_METHODS.verifyElementDisplayed(getTestObject("MA_69"));
		
		//Logout from Emp portal
		//businessComponents.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Care Profile - Locations - User
	 * is able to add an address TFS ID : 23368
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority =11)
	public void EP_CareProfileLocations() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		loginClient();

		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click the 'Add' link next to the Locations section
		COMMON_METHODS.clickElement(getTestObject("OL_124"));

		// Add location
		//Create locations
		String data[]={"Office","60601","103 Fox Road","Flag st","Chicago","Cook",
						"United States",null};
		businessComponents.EP_AddLocation(data);

		// log out
		//businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of Care Profile - User can complete
	 * profile clicking on 4 sections of the care profile icons TFS ID : 23362
	 * 
	 * @param TC
	 * @throws Exception
	 */

	@Test(priority = 12)
	public void EP_CareProfileICONS() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// launch browser
		// businessComponents.EP_LaunchBrowser(TC);

		// Verify Employer
		// businessComponents.EP_verifyEmployer();

		// Accept policy and submit
		// businessComponents.EP_AcceptPolicyAndSubmit();

		// Sign Up
		// businessComponents.EP_SignUpUser(58);
		
		loginClient();

		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_114"));
		

		/*// Click on Employee Profile icon
		COMMON_METHODS.clickElement(getTestObject("OL_134"));

		businessComponents.EP_UpdateEmployeeprofile();
*/
		// User will be navigated to the Care Profile page with Green check mark
		// on the employee profile icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_138"));

	/*	// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));

		
		 * //Click on Care Recipients icon
		 * COMMON_METHODS.clickElement(getTestObject("OL_135"));
		 

		businessComponents.EP_AddCareRecipients();*/

		// User will be navigated to the Care Profile page with Green check mark
		// on the care recipients icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_139"));

		// Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));

		businessComponents.EP_AddAuthorizedContacts();

		// User will be navigated to Care Profile page with Green check mark on
		// the Authorized Contacts icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_140"));

		// User will be navigated to Care Profile page with Green check mark on
		// the Locations icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_141"));

		// Ensure all 4 icons are no more clickable now
		COMMON_METHODS.verifyElementIsSelected(getTestObject("OL_138"),
				false);

		COMMON_METHODS.verifyElementIsSelected(getTestObject("OL_139"),
				false);

		COMMON_METHODS.verifyElementIsSelected(getTestObject("OL_140"),
				false);

		COMMON_METHODS.verifyElementIsSelected(getTestObject("OL_141"),
				false);

		//businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	/**
	 * This test script covers functionality of  Authorized Contact - if email address added is already in use, 
	 * display a popup to notify user that the account exists, ask if they want to send confirmation 
	 * to that email
	 * TFS ID : 11014

	 */	

	@Test(priority=13)	
	public void EP_Authorized_Contacts_Popup_Already_Exists() throws Exception
	{		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//this.employer = "5";

		// launch browser
		//Utility.launchBrowser(getTestData("TD_EP_URL"));

		loginClient();
		
		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click on "ADD Authorized Contact"
		COMMON_METHODS.clickElement(getTestObject("CP_01"));

		this.addEmailAuthorizedAccount("testBH@mail.com");
		
		// Click on Add Authorized contact
		COMMON_METHODS.clickElement(getTestObject("OL_69"));
		
		/*// Click on "ADD Authorized Contact"
		COMMON_METHODS.clickElement(getTestObject("CP_01"));

		this.addEmailAuthorizedAccount("testBH@mail.com");
		
		// Click on Add Authorized contact
		COMMON_METHODS.clickElement(getTestObject("OL_69"));
		
		Thread.sleep(3000);*/
		
		//Verify the text present on the pop up
		COMMON_METHODS.VerifyTextPresent(getTestObject("MA_36"), "Email Exists");

		//Click No on the Pop up
		COMMON_METHODS.clickElement(getTestObject("MA_40"));
		
		/*//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		COMMON_METHODS.clickElement(getTestObject("MA_39"));
*/		
		

		/*//Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_59"), getTestData("TD_AC_FirstName"));

		//Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_60"), getTestData("TD_AC_LastName"));

		//Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_61"));

		//Select Primary Phone
		COMMON_METHODS.listBoxSelect(getTestObject("OL_62"), "Work", "byVisibleText");

		//Enter Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_63"), getTestData("TD_Phone"));

		//Click Yes in Benefit Access
		COMMON_METHODS.radioButton(getTestObject("CP_13"));

		//Enter already used Email ID		
		COMMON_METHODS.editAField(getTestObject("MA_35"), getTestData("TD_Email_Use"));

		//Select the Yes radio button on 'Has Access'
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("CP_14"));

		//Verify the Radio button selected in the Care Recipient Access
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_61"));

		//Click on Add Authorised contact 
		COMMON_METHODS.clickElement(getTestObject("OL_69"));

		//Verify the text present on the pop up
		COMMON_METHODS.VerifyTextPresent(getTestObject("MA_36"), "Email Exists");

		//Click No on the Pop up
		COMMON_METHODS.clickElement(getTestObject("MA_40"));*/

		//Logout from the portal
		//Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	

	/**
	 * This test script covers functionality of Authorized Contacts - Ensure
	 * user is able to add a new authorized contacts TFS ID : 4136
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 14)
	public void EP_CareProfileAddAuthorizationContacts() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);
		
		/*this.employer="5";
		
		// launch browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));*/
		
		loginClient();

		/*	// launch browser
		Utility.launchBrowser(getTestData("
		
		TD_URL"));

		// login EP Portal
		 businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		getTestData("TD_PWD"));*/

		// click care profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));

		// Add authorized contacts
		businessComponents.EP_AddAuthorizedContacts();

		// log out
		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);
	}

	/**
	 * This test script covers functionality of Authorized Contacts - User is
	 * able to update an Authorized Contact's options TFS ID : 4140
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void EP_CareProfileUpdateAuthorizationContacts() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);

		// Sign into the client employee portal
		// businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		// getTestData("TD_PWD"));
		
		loginClient();

		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));

		// Verify the Authorized contacts information is displayed
		/*
		 * Below verifications covers test case with TFS ID:3725
		 */
		// Verify first Name
		verifyAuthorizedContactInfo(getTestObject("OL_59"),
				getTestData("TD_AC_FirstName"));

		// Verify last Name
		verifyAuthorizedContactInfo(getTestObject("OL_60"),
				getTestData("TD_AC_LastName"));

		// Verify Gender is selected
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_61"));

		// Verify Primary Phone from drop down

		if ("Work".equalsIgnoreCase(COMMON_METHODS
				.getSelectedValueFromListBox(getTestObject("OL_62"))))
			REPORTER.LogEvent(TestStatus.PASS, "Work Displayed",
					"Work Displayed", "");
		else
			REPORTER.LogEvent(
					TestStatus.FAIL,
					"Expected Value=" + "Work",
					"Actual Value="
							+ COMMON_METHODS
									.getSelectedValueFromListBox(getTestObject("OL_62")),
					"");

		// Verify Primary Phone Number
		verifyAuthorizedContactInfo(getTestObject("OL_63"),
				getTestData("TD_Phone"));

		/*
		 * TFS ID:3725 ends here
		 */

		// Make some changes to the Authorized Contact Information
		COMMON_METHODS.editAField(getTestObject("OL_63"), "7702279345");

		// Click 'Update' button
		COMMON_METHODS.clickElement(getTestObject("OL_69"));

		// log out
		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);
	}

	/**
	 * This test script covers functionality of Locations - User is able to
	 * update an existing address TFS ID : 4212
	 * 
	 * @param TC
	 * @throws Exception
	 */

	@Test(priority = 16)
	public void EP_CareProfileUpdateHomeLocation() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);

		// User logs into the portal
		// businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		// getTestData("TD_PWD"));

		loginClient();
		
		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Clicking Existing office link
		// COMMON_METHODS.driver.findElement(By.linkText("Office")).click();
		COMMON_METHODS.clickElement(getTestObject("Loc_Home"));

		// Update location
		businessComponents.EP_CareprofileUpdateLocation();

		// logout
		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);

	}

	/**
	 * This test script covers functionality of Authorized Contact Creation:
	 * Verify Add Authorized Contact Page Requirement ID : 21331
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 17)
	public void EP_VerifyAuthorizedContactPage() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);
		

		// businessComponents.EP_LaunchBrowser(TC);

		// User logs into the portal
		// businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		// getTestData("TD_PWD"));
		
		loginClient();

		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		Thread.sleep(5000);

		// Click on "ADD Authorized Contact"
		COMMON_METHODS.clickElement(getTestObject("CP_01"));

		// Verify Personal Information Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("CP_10"),
				"Personal Information");

		// Verify Benefit Access Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("CP_11"),
				"Benefit Access");

		// Verify Care Receipents Access Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("CP_12"),
				"Care Recipient Access");

		// logout
		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);
	}

	/**
	 * This test script covers functionality of Authorized Contact Creation:
	 * Verify Authorized Contacts provided with benefit access, have automatic
	 * access to all the Care Recipients Requirement ID : 21331
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 18)
	public void EP_VerifyAuthorizedContactBenefitAccessYes() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);
	
		// businessComponents.EP_LaunchBrowser(TC);

		// User logs into the portal
		// businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		// getTestData("TD_PWD"));

		loginClient();
		
		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click on "ADD Authorized Contact"
		COMMON_METHODS.clickElement(getTestObject("CP_01"));

		// setting yes in HasAccessSection at AuthorizedContacts
		businessComponents.EP_AuthorizedContactsVerifyHasAccessSection("Yes");

		// logout
		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);

	}

	/**
	 * This test script covers functionality of Authorized Contact Creation:
	 * Verify for Authorized Contacts not provided with benefit access, the
	 * System should enable the employee to select the Care Recipients
	 * Requirement ID : 21331
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void EP_VerifyAuthorizedContactBenefitAccessNo() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);

		// businessComponents.EP_LaunchBrowser(TC);

		// User logs into the portal
		// businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		// getTestData("TD_PWD"));
		
		loginClient();

		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click on "ADD Authorized Contact"
		COMMON_METHODS.clickElement(getTestObject("CP_01"));

		// setting no in HasAccessSection at AuthorizedContacts
		businessComponents.EP_AuthorizedContactsVerifyHasAccessSection("No");

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);
	}

	/**
	 * This test script covers functionality of Authorized Contact Creation:
	 * Verify for Authorized Contacts not provided with benefit access, the
	 * System should enable the employee to select the Care Recipients
	 * Requirement ID : 21331
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 20)
	public void EP_VerifyAuthorizedContactRelationshipandPrivilages()
			throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);
	
		// businessComponents.EP_LaunchBrowser(TC);

		// User logs into the portal
		// businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		// getTestData("TD_PWD"));
		
		loginClient();

		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click on "ADD Authorized Contact"
		COMMON_METHODS.clickElement(getTestObject("CP_01"));

		// setting no in HasAccessSection at AuthorizedContacts
		businessComponents.EP_AuthorizedContactsVerifyHasAccessSection("No");

		if (!this
				.verifyCareRecipientAccessElementsDisplayed(getTestObject("OL_65"))) {
			REPORTER.LogEvent(
					TestStatus.PASS,
					"Relationship and Privilege option displayed",
					"Contact Privileges**"
							+ " is not displayed -- ".toUpperCase(), "");
		} else {
			REPORTER.LogEvent(
					TestStatus.FAIL,
					"Relationship and Privilege option not displayed",
					"Contact Privileges**"
							+ " displayed -- Successfull".toUpperCase(), "");
		}

		if (!this
				.verifyCareRecipientAccessElementsDisplayed(getTestObject("OL_66"))) {
			REPORTER.LogEvent(
					TestStatus.PASS,
					"Parent/Guardian radio button should be displayed",
					"Parent/Guardian**" + " is not displayed -- ".toUpperCase(),
					"");
		} else {
			REPORTER.LogEvent(
					TestStatus.FAIL,
					"Parent/Guardian radio button should not be displayed",
					"Parent/Guardian**"
							+ " displayed -- Successfull".toUpperCase(), "");

		}

		if (!this
				.verifyCareRecipientAccessElementsDisplayed(getTestObject("OL_67"))) {
			REPORTER.LogEvent(
					TestStatus.PASS,
					"Emergency Contact should be displayed",
					"Emergency Contact" + " is not displayed -- ".toUpperCase(),
					"");
		} else {
			REPORTER.LogEvent(
					TestStatus.FAIL,
					"Emergency Contact should not be displayed",
					"Emergency Contact"
							+ " displayed -- Successfull".toUpperCase(), "");
		}

		if (!this
				.verifyCareRecipientAccessElementsDisplayed(getTestObject("OL_68"))) {
			REPORTER.LogEvent(
					TestStatus.PASS,
					"Authorized pickup radio button should be displayed",
					"Authorized pickup" + " is not displayed -- ".toUpperCase(),
					"");
		} else {
			REPORTER.LogEvent(
					TestStatus.FAIL,
					"Authorized pickup should not be displayed",
					"Authorized pickup"
							+ " not displayed -- Successfull".toUpperCase(), "");
		}

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);
	}

	/**
	 * This test script covers functionality of Authorized Contact Creation:
	 * Verify for Authorized Contacts not provided with benefit access, the
	 * System should enable the employee to select the Care Recipients
	 * Requirement ID : 21331
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 21)
	public void EP_VerifyEmptyAuthorizedContactMessages() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);
	
		/* Utility.launchBrowser(getTestData("TD_URL"));

		// User logs into the portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client60.userName"),
		 getTestData("TD_PWD"));*/
		
		loginClient();

		// Click 'Care Profile' link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click on "ADD Authorized Contact"
		COMMON_METHODS.clickElement(getTestObject("CP_01"));

		COMMON_METHODS.clickElement(getTestObject("OL_69"));
		
		//Verify the error message label is displaying or not
		COMMON_METHODS.isElementPresent(getTestObject("RESV_24"),"xpath");
		
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("CP_22"),
				"Relationship Required.");
		
		
		
	/*	//verifying 3rd Relationship to Recipient displayed for c are recipient 3
		if (COMMON_METHODS.isElementPresent("AuthorizedContactList_0__Permissions_2__RelationshipTypeId","id")) {

			COMMON_METHODS.VerifyTextPresent(getTestObject("CP_23"),
					"Relationship Required.");
			COMMON_METHODS.VerifyTextPresent(getTestObject("CP_24"),
					"Relationship Required.");
			COMMON_METHODS.VerifyTextPresent(getTestObject("CP_25"),
					"Primary Phone Number is required.");
			COMMON_METHODS.VerifyTextPresent(getTestObject("CP_26"),
					"The FirstName field is required.");
			COMMON_METHODS.VerifyTextPresent(getTestObject("CP_27"),
					"The LastName field is required.");
			COMMON_METHODS.VerifyTextPresent(getTestObject("CP_28"),
					"Gender required");
			
		}
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("CP_23"),
				"Primary Phone Number is required.");
		COMMON_METHODS.VerifyTextPresent(getTestObject("CP_24"),
				"The FirstName field is required.");
		COMMON_METHODS.VerifyTextPresent(getTestObject("CP_25"),
				"The LastName field is required.");
		COMMON_METHODS.VerifyTextPresent(getTestObject("CP_26"),
				"Gender required");*/

		// Logout from the portal
		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);

	}

	

	/**
	 * Test Case #13091: BUCA - My profile - The "Is textable" check box appears
	 * when secondary, primay, or other phone are set to cell
	 */

	@Test(priority = 22)
	public void EP_MyProfile_IsTextable() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		/*// Launch the browser
		 Utility.launchBrowser(getTestData("TD_URL"));

		// login employee portal
		
		 businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.
		 getProperty("client60.userName"), getTestData("TD_PWD"));
*/		
		/*this.employer = "5";

		// launch browser
		Utility.launchBrowser(getTestEP_CareProfileAddAuthorizationContactsData("TD_EP_URL"));*/
		
		
		loginClient();
		
		/*// Find the 'Create Your Care Profile' button and click the link
		COMMON_METHODS.clickElement(getTestObject("OL_114"));*/
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		// Click the employee's name link under Employee Profile
		COMMON_METHODS.clickElement(getTestObject("MA_33"));

		// find the primary and secondary phone fields
		COMMON_METHODS.VerifyTextPresent(getTestObject("PrimaryPhoneText"),
				"Primary Phone*");

		// Enter Primary Phone Number
		COMMON_METHODS.listBoxSelect(getTestObject("OL_28"),
				"Cell", "byVisibleText");

		// Check if Is Textable is displayed
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PP_01"), true);

		// Verify Secondary Phone Number
		COMMON_METHODS.listBoxSelect(getTestObject("SP_01"),
				"Cell", "byVisibleText");

		// Check if Is Textable is displayed
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("SP_02"), true);

		// Verify Secondary Phone Number
		COMMON_METHODS.listBoxSelect(getTestObject("OP_01"),
				"Home", "byVisibleText");

		// Check if Is Textable is displayed
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("OP_02"), true);

		//businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}
		
	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 17/03/2014
	 */
	//Test Case #18133: 11242A - BUCA - Profile Wizard - My Profile - ensure 'Add me as a care Recipient' link functions correctly

	@Test(priority = 23) @Parameters("client")
	public void EP_CareProfile_AddMeAsACareRecipient(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
	
			
		loginClient();
		
		//Registration
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
				
		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));

		businessComponents.EP_UpdateEmployeeprofile(employer);
	
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Click link under 'Employee Profile' section
		String strEmployeeName = COMMON_METHODS.getText(getTestObject("OL_26"));
		
		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));
				
		Thread.sleep(3000);
		System.out.println("Employee name: " + strEmployeeName);
		
		// Verify 'Add Me As a Care Recipient' and 'View Payment Policies' links are displayed.
		try {
			WebElement weAddMeLink = BH_SetUp_TearDown.driver.findElement(By.xpath("//a[@id='selfCareLink']"));
			WebElement weViewPaymentPoliciesLink = BH_SetUp_TearDown.driver.findElement(By.linkText("View Payment Policies"));
			if(weAddMeLink.isDisplayed() && weViewPaymentPoliciesLink.isDisplayed()){
				REPORTER.LogEvent(TestStatus.PASS, "Verify the 'Add Me As a Care Recipient' and 'View Payment Policies' links are displayed.", "'Add Me As a Care Recipient' and 'View Payment Policies' links are displayed.", "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify the 'Add Me As a Care Recipient' and 'View Payment Policies' links are displayed.", "'Add Me As a Care Recipient' and 'View Payment Policies' links are not displayed.", "");
			}
		}catch (Exception e) {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the 'Add Me As a Care Recipient' and 'View Payment Policies' links are displayed.", "'Add Me As a Care Recipient' and 'View Payment Policies' links are not displayed.", "");
		} 
		
		// Click 'Add Me As a Care Recipient' link
		COMMON_METHODS.clickElement(getTestObject("OL_115"));
	
		// Enter the 'Birth Date' of the employee 
		COMMON_METHODS.editAField(getTestObject("CP_27"), getTestData("TD_Emp_DOB"));
		
		// Enter the 'Height' of the employee 
		COMMON_METHODS.editAField(getTestObject("CP_28"), getTestData("TD_Height"));
		
		// Enter the 'Width' of the employee 
		COMMON_METHODS.editAField(getTestObject("CP_29"), getTestData("TD_Weight"));
		
		/*// Enter the employee number
		COMMON_METHODS.editAField(getTestObject("OL_106"), getTestData("TD_EMPLOYEENO"));*/

		// Click 'Update Employee Profile' button
		COMMON_METHODS.clickElement(getTestObject("OL_40"));
		Thread.sleep(5000);

		// Verify the employee is added as a Care Recipient
		try {
			WebElement weEmpLink = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='formModHdr']//li[contains(text(),'Care Recipients')]/../../following-sibling::*//a[text()='" + strEmployeeName + "']"));
			if(weEmpLink.isEnabled()){
				REPORTER.LogEvent(TestStatus.PASS, "Verify the employee is added as a Care Recipient.", "The employee is added as a Care Recipient.", "");
				
				// Click the employee link
				weEmpLink.click();
				
				String strEmpBD = COMMON_METHODS.getText(getTestObject("OL_45"),"value");
				String strEmpHeight = COMMON_METHODS.getText(getTestObject("CR_25"),"value");
				String strEmpWeight = COMMON_METHODS.getText(getTestObject("CR_26"),"value");
				System.out.println("Emp BD: "+strEmpBD);
				System.out.println("Emp Ht: "+strEmpHeight);
				System.out.println("Emp Wt: "+strEmpWeight);
				if (strEmpBD.equalsIgnoreCase(getTestData("TD_Emp_DOB")) && strEmpHeight.equalsIgnoreCase(getTestData("TD_Height")) && strEmpWeight.equalsIgnoreCase(getTestData("TD_Weight"))){
					REPORTER.LogEvent(TestStatus.PASS, "Verify the employee details are displayed as follows:\nBirth Date: " + strEmpBD + "\nHeight: " + strEmpHeight+ "\nWeight: " + strEmpWeight, "The employee details are displayed as follows:\nBirth Date: " + strEmpBD + "\nHeight: " + strEmpHeight+ "\nWeight: " + strEmpWeight, "");
				}else{
					REPORTER.LogEvent(TestStatus.FAIL, "Verify the employee details are displayed as follows:\nBirth Date: " + strEmpBD + "\nHeight: " + strEmpHeight+ "\nWeight: " + strEmpWeight, "The employee details are not displayed as follows:\nBirth Date: " + strEmpBD + "\nHeight: " + strEmpHeight+ "\nWeight: " + strEmpWeight, "");
				}
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify the employee is added as a Care Recipient.", "The employee is not added as a Care Recipient.", "");
			}
		}catch (Exception e) {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the employee is added as a Care Recipient.", "The employee is not added as a Care Recipient.", "");
		} 

		// Logout Employee portal
		//Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * 
	 * @CreationDate: 17/03/2014
	 */
	// Test Case #N/A: Application should allow the Benefit Eligible Employee to
	// upload the ‘Signed Care Recipient Profile document’ into the forms
	// section of a Care Recipient’s profile.

	@Test(priority = 24)
	public void EP_CareProfile_UploadProfileDocument() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		
		loginClient();

		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click Care Recipient link
		//COMMON_METHODS.clickElement(getTestObject("CP_30"));
		COMMON_METHODS.clickElement(getTestObject("MA_65"));

		// Select NewYork
		COMMON_METHODS.listBoxSelect(getTestObject("OL_56"), "New York",
				"byVisibleText");

		// Click Upload Form link
		COMMON_METHODS.clickElement(getTestObject("UPL_DOC"));
		
		//cancel upload a document or form
		COMMON_METHODS.clickElement(getTestObject("UPL_CANCEL"));
		
		//TODO : Need to write code for upload document due to not editable for the upload document 
		

		/*// Get the selected form type
		String strFormType = COMMON_METHODS
				.getSelectedValueFromListBox(getTestObject("CP_32"));

		// Click Browse... button
		COMMON_METHODS.editAField(getTestObject("CP_33"),
				System.getProperty("user.dir") + "\\TestData\\SCRP.doc");

		// Click Submit button
		COMMON_METHODS.clickElement(getTestObject("CP_34"));

		// Get the text from the row in which the newly uploaded document is
		// displayed
		WebElement weNewUploadedDocRow = COMMON_METHODS.driver
				.findElement(By
						.xpath("//div[@id='CareForm_0']//div/table//tr/td[contains(text(),'Form Submitted')]/../"));
		String strweNewUploadedDocRowText = COMMON_METHODS
				.getText(weNewUploadedDocRow.toString());

		if (strweNewUploadedDocRowText.contains(strFormType)) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify the document is uploaded successfully.",
					"The document is uploaded successfully.", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify the document is uploaded successfully.",
					"The document is not uploaded.", "");
		}*/

		// Logout Employee Portal
		//Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	

	/**
	 * BUCA - My Care Profile - Ensure an email is sent when an update or
	 * addition is made
	 * 
	 * Requirement ID : 4128
	 * 
	 * @Autor: Sanjeev Singh
	 * @since 04/04/2014
	 * @throws Exception
	 * 
	 */

	@Test(priority = 25)
	public void EP_CareProfile_Ensureemail() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		loginClient();
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		Thread.sleep(4000);
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_65"));
		
		// Enter Regular Care Arrangements
		COMMON_METHODS.editAField(getTestObject("OL_99"),
				getTestData("TD_AddInfo"));
				
		
		Thread.sleep(2000);
		
		// Submit MainFormSubmit
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		// TODO Email Verification (Till now we are not doing Email Verification kind of things)

		// Logouts and Log Report
		// businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**********************************************
	 * SATYA/
	 * 
	 * /** This test script covers functionality of BUCA Web Application - Care
	 * Recipients- System displays error message if user does not enters all
	 * required fields
	 * 
	 * Requirement ID : 21330
	 * 
	 * @param TC
	 * @throws Exception
	 *             ,
	 */
	@Test(priority = 26)
	public void EP_AddCareRecipientsErrorMsg() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// login employee portal
		/*
		 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.
		 * getProperty("client2.userName"), getTestData("TD_PWD"));
		 */
		loginClient();

		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));

		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		// An error message should be displayed as "Missing Fields" for
		// appropriate fields and The missing fields should also be highlighted
		// in red.
		COMMON_METHODS.isElementPresent(getTestObject("RESV_24"),"xpath");

		// businessComponents.logout();
		//COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of BUCA Web Application - Care
	 * Recipients- .The printable version of the Care Profile page should be
	 * displayed in a read only format.
	 * 
	 * Requirement ID : 21330
	 * 
	 * @param TC
	 * @throws Exception
	 *             ,
	 */
	@Test(priority = 27)
	public void EP_AddCareRecipientsPrintProfile()
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		loginClient();
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));

		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		// Verify 'Print Care Profile' link is available on top of the Care
		// Recipient Profile page
		// TODO: print Care Profile link not yet developed

		/* businessComponents.logout(); */
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of BUCA Web Application - Care
	 * Recipients- Ensure user can use birth dates in the future
	 * 
	 * Requirement ID : 21330
	 * 
	 * @param TC
	 * @throws Exception
	 *             ,dependsOnMethods="EP_SignUpUser",
	 */
	@Test(priority = 28)
	public void EP_AddCareRecipientsBirthDate() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		loginClient();
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));

		// Verify maximum no. of months for future birth date
		// Adding 9 months to current date for DOB
		this.EP_AddCareRecipientsDOB(9);

		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		// An error message should be displayed when selecting above 9 months
		// for the current date
		COMMON_METHODS.isElementPresent(getTestObject("RESV_24"),"xpath");

		// Verify if the care recipient date of birth date occurs in the future
		// and The age field should be left blank in all parts of the portal
		// where care recipient age is being displayed.

		// TODO : This functionality is not yet implemented. showing the error
		// message when selecting the future date is less then before 9 months
		// for the DOB.

		/* businessComponents.logout(); */
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of BUCA Web Application - Care
	 * Recipients- user is able to download a care form
	 * 
	 * Requirement ID : 21330
	 * 
	 * @param TC
	 * @throws Exception
	 *             ,dependsOnMethods="EP_SignUpUser",
	 */
	@Test(priority = 29)
	public void EP_AddCareRecipientsDownloadCareForm()
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);


		loginClient();
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));

		this.EP_AddCareRecipientsDOB(-4);

		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		/*
		 * //Click a Upload Form
		 * COMMON_METHODS.clickElement(getTestObject("OL_CR_Upload"));
		 * 
		 * //Click a File in Upload
		 * COMMON_METHODS.clickElement(getTestObject("OL_CR_File"));
		 */

		// COMMON_METHODS.editAField(getTestObject("OL_CR_File"),"E:\\sample.txt");

		// TODO : The file upload text box is read only. So we cant give the
		// directly the path of the file. We need ask development team for
		// enable the file upload text box

		/* businessComponents.logout(); */
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * BUCA - Care Recipients - ensure 'Preferred Center Location' field only
	 * shows when adding a Care Recipient
	 * 
	 * Requirement ID : 15713
	 * 
	 * @Autor: Sanjeev Singh
	 * @since 03/04/2014
	 * @throws Exception
	 * 
	 */

	@Test(priority = 30)
	public void EP_CareRecipientsPreferredLocation() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		/*Utility.launchBrowser(getTestData("TD_URL"));*/
		
		/*this.employer = "5";*/
		
		loginClient();

		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));

		// String whichClient=null;
		// whichClient=getTestData("TD_EmployerId");
		// Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_42"),
				getTestData("TD_CR_FirstName"));

		// Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_43"),
				getTestData("TD_CR_LastName"));

		// Select Relationship to Client Employee
		COMMON_METHODS.listBoxSelect(getTestObject("OL_44"), "Daughter",
				"byVisibleText");

		// Enter Birth Date
		COMMON_METHODS
				.editAField(getTestObject("OL_45"), getTestData("TD_DOB"));

		// Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_46"));

		// Select Food Restrictions
		COMMON_METHODS.radioButton(getTestObject("OL_47"));

		// Select Food Allergies
		COMMON_METHODS.radioButton(getTestObject("OL_48"));

		// Select Allergies to Medication
		COMMON_METHODS.radioButton(getTestObject("OL_49"));

		// Select Other Allergies
		COMMON_METHODS.radioButton(getTestObject("OL_50"));

		// Select Diagnosed Special Needs / Medical Conditions
		COMMON_METHODS.radioButton(getTestObject("OL_51"));

		// Select Activity Restrictions
		COMMON_METHODS.radioButton(getTestObject("OL_52"));

		// Enter Additional Information
		COMMON_METHODS.editAField(getTestObject("OL_53"),
				getTestData("TD_AddInfo"));

		// Select Is Client Employee a Parent or Legal Guardian
		COMMON_METHODS.radioButton(getTestObject("OL_54"));

		// Select Custody Issues/ Visitation Orders
		COMMON_METHODS.radioButton(getTestObject("OL_55"));

		// Select Where do you primarily need care
		COMMON_METHODS.listBoxSelect(getTestObject("OL_56"),
				getTestData("TD_State"), "byVisibleText");

		// Enter Regular Care Arrangements
		COMMON_METHODS.editAField(getTestObject("OL_99"),
				getTestData("TD_AddInfo"));
		Thread.sleep(4000);

		// Select Toilet Trained
		COMMON_METHODS.radioButton(getTestObject("OL_100"));

		/*// Select Salary Range
		if(whichClient!=null && (whichClient.equalsIgnoreCase("TCTHREE")||
		 whichClient.equalsIgnoreCase("TCSIX")))
		COMMON_METHODS.radioButton(getTestObject("CP_14"));
		COMMON_METHODS.listBoxSelect(getTestObject("OL_65"), "Father",
				"byVisibleText");
		COMMON_METHODS.radioButton(getTestObject("s_parentGardian"));*/

		// Submit MainFormSubmit
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		// Verify the newly added Care Recipient in present under 'Care
		// Recipients' section
		try {
			String sTemp = BH_SetUp_TearDown.driver
					.findElement(
							By.linkText(getTestData("TD_CR_FirstName") + " "
									+ getTestData("TD_CR_LastName"))).getText()
					.trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recipient created",
					"Care Recipient - " + sTemp + " Created", "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Care Recipient created");
		}

		// Click on new care recipient link
		String CR = getTestData("TD_CR_FirstName") + " "
				+ getTestData("TD_CR_LastName");
		COMMON_METHODS.driver.findElement(By.linkText(CR));

		// The user can view the information saved
		String FName = COMMON_METHODS.getTestData("TD_CR_FirstName");
		String LName = COMMON_METHODS.getTestData("TD_CR_LastName");
		if ((FName.trim() == getTestData("TD_CR_FirstName").trim())
				&& (LName.trim() == getTestData("TD_CR_LastName").trim()))
			REPORTER.LogEvent(TestStatus.PASS,
					"Verifying user can view the information saved",
					"Viewed Info for: " + FName + LName + " Successfully", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verifying user can view the information saved",
					"Viewed Info for: " + FName + LName + " Failed", "");

		// Logouts and Log Report
		Utility.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	/**
	 * Adding the Care Recipients in Care profile
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_AddCareRecipientsDOB(int noOfMonths) throws IOException,
			Exception {

		// Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_42"),
				getTestData("TD_CR_FirstName"));

		// Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_43"),
				getTestData("TD_CR_LastName"));

		// Select Relationship to Client Employee
		COMMON_METHODS.listBoxSelect(getTestObject("OL_44"), "Daughter",
				"byVisibleText");

		// Enter Birth Date
		COMMON_METHODS.editAField(getTestObject("OL_45"),
				dateAddMonths(noOfMonths));

		// Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_46"));

		// Select Food Restrictions
		COMMON_METHODS.radioButton(getTestObject("OL_47"));

		// Select Food Allergies
		COMMON_METHODS.radioButton(getTestObject("OL_48"));

		// Select Allergies to Medication
		COMMON_METHODS.radioButton(getTestObject("OL_49"));

		// Select Other Allergies
		COMMON_METHODS.radioButton(getTestObject("OL_50"));

		// Select Diagnosed Special Needs / Medical Conditions
		COMMON_METHODS.radioButton(getTestObject("OL_51"));

		// Select Activity Restrictions
		COMMON_METHODS.radioButton(getTestObject("OL_52"));

		// Enter Additional Information
		COMMON_METHODS.editAField(getTestObject("OL_53"),
				getTestData("TD_AddInfo"));

		// Select Is Client Employee a Parent or Legal Guardian
		COMMON_METHODS.radioButton(getTestObject("OL_54"));

		// Select Custody Issues/ Visitation Orders
		COMMON_METHODS.radioButton(getTestObject("OL_55"));

		// Select Where do you primarily need care
		COMMON_METHODS.listBoxSelect(getTestObject("OL_56"),
				getTestData("TD_State"), "byVisibleText");

		// Enter Regular Care Arrangements
		COMMON_METHODS.editAField(getTestObject("OL_99"),
				getTestData("TD_AddInfo"));

	}

	/**
	 * This method adding months for the current date.
	 * 
	 * @param noOfMonths
	 * @return
	 */
	public static String dateAddMonths(int noOfMonths) {

		String date;

		Calendar c = Calendar.getInstance();

		System.out.println("Current date : " + (c.get(Calendar.MONTH) + 1)
				+ "/" + c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR));

		// add months to current date
		c.add(Calendar.MONTH, noOfMonths);

		date = (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DATE) + "/"
				+ c.get(Calendar.YEAR);

		return date;
	}
	
	/**
	 * @throws Exception
	 * @throws IOException
	 * 
	 * 
	 */
	private void EP_VerifyPersonalInformation(String client) throws IOException, Exception {

		// Verifying First Name in 'Personal Information' section are populated
		// with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_76"),
				ReadwritDataFromProps.props.getProperty(client+".cpu.firstName"),
				"value");

		// Verifying Last Name in 'Personal Information' section are populated
		// with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_77"),
				ReadwritDataFromProps.props.getProperty(client+".cpu.lastName"),
				"value");

		// Verifying Email in 'Personal Information' section are populated with
		// correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_78"),
				ReadwritDataFromProps.props.getProperty(client+".cpu.userEmail"),
				"value");

		// Verifying Gender in 'Personal Information' section are populated with
		// correct values
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_27"));

		// Verifying Primary Phone Type in 'Personal Information' section are
		// populated with correct values
		if (COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_28"))
				.equals("Work"))
			REPORTER.LogEvent(
					TestStatus.PASS,
					"Verify Primary Phone Type in 'Personal Information' section ",
					"Correct value displayed as " + "Work", "");
		else
			REPORTER.LogEvent(
					TestStatus.PASS,
					"Verify Primary Phone Type in 'Personal Information' section ",
					"Correct value not displayed as" + "Work", "");

		// Verifying Primary Phone Number in 'Personal Information' section are
		// populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_29"),
				getTestData("TD_Phone"), "value");

		// Verifying Home Address in 'Personal Information' section are
		// populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_30"),
				getTestData("TD_Address"), "value");

		// Verifying City in 'Personal Information' section are populated with
		// correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_31"),
				getTestData("TD_City"), "value");

		// Select State/Province
		COMMON_METHODS.listBoxSelect(getTestObject("OL_32"),
				getTestData("TD_State"), "byVisibleText");

		// Verifying County in 'Personal Information' section are populated with
		// correct values
		/*COMMON_METHODS.VerifyTextPresent(getTestObject("OL_33"),
				getTestData("TD_County"), "value");*/
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_33"),
				"King", "value");

		// Verifying Country in 'Personal Information' section are populated
		// with correct values
		if (COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_34"))
				.equals(getTestData("TD_Country")))
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Country in 'Personal Information' section ",
					"Country displayed as " + getTestData("TD_Country")
							+ " in 'Personal Information' section ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify Country in 'Personal Information' section ",
					"Country displayed wrongly as " + getTestData("TD_Country")
							+ " in 'Personal Information' section ", "");

		// Verifying 'Postal Code' in 'Personal Information' section are
		// populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_35"),
				getXMLData(client,"TD_Zip"), "value");

		// Verifying 'Work City' in 'Personal Information' section are populated
		// with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_36"),
				getTestData("TD_City"), "value");

		// Verifying Work State in 'Personal Information' section are populated
		// with correct values
		if (COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_37"))
				.equals(getTestData("TD_State")))
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify State in 'Personal Information' section ",
					"State displayed as " + getTestData("TD_Country")
							+ " in 'Personal Information' section ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify State in 'Personal Information' section ",
					"state displayed wrongly as " + getTestData("TD_Country")
							+ " in 'Personal Information' section ", "");

		// Verifying 'Job Title' in 'Personal Information' section are populated
		// with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_38"),
				getTestData("TD_JobTitle"), "value");

	}

	/**
	 * Adding AUthorized Account with Email
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void addEmailAuthorizedAccount(String eMail) throws IOException, Exception {
		// Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_59"),
				getTestData("TD_AC_FirstName"));

		// Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_60"),
				getTestData("TD_AC_LastName"));

		// Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_61"));

		// Select Primary Phone
		COMMON_METHODS.listBoxSelect(getTestObject("OL_62"), "Work",
				"byVisibleText");

		// Enter Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_63"),
				getTestData("TD_Phone"));

		// Click Yes in Benefit Access
		COMMON_METHODS.radioButton(getTestObject("CP_13"));

		// Enter already used Email ID
		// Verifying Email in 'Personal Information' section are populated with
		// correct values
		COMMON_METHODS.editAField(getTestObject("MA_35"),
				eMail);
		/*System.out
				.println("User Email : "
						+ ReadwritDataFromProps.props
								.getProperty("client60.userEmail"));*/

		// Select the Yes radio button on 'Has Access'
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("CP_14"));

		// Verify the Radio button selected in the Care Recipient Access
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_61"));
		
		//Select Relationship
		COMMON_METHODS.listBoxSelect(getTestObject("OL_65"), getTestData("TD_Relationship"), "byVisibleText");
		
		//verifying 2nd Relationship to Recipient displayed for c are recipient 2
		if (COMMON_METHODS.isElementPresent("AuthorizedContactList_0__Permissions_1__RelationshipTypeId","id")) {
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("AUTH_CONT1"), getTestData("TD_Relationship"), "byVisibleText");
		}
		
		//verifying 3rd Relationship to Recipient displayed for c are recipient 2
		if (COMMON_METHODS.isElementPresent("AuthorizedContactList_0__Permissions_2__RelationshipTypeId","id")) {
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("AUTH_CONT2"), getTestData("TD_Relationship"), "byVisibleText");
		}
		
		//verifying 3rd Relationship to Recipient displayed for c are recipient 3
		if (COMMON_METHODS.isElementPresent("AuthorizedContactList_0__Permissions_3__RelationshipTypeId","id")) {
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("AUTH_CONT3"), getTestData("TD_Relationship"), "byVisibleText");
		}

		
	}

	/**
	 * 
	 * @param TestObject
	 * @return
	 * @throws IOException
	 */
	public boolean verifyCareRecipientAccessElementsDisplayed(String TestObject)
			throws IOException {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		REPORTER.writeLog(methodName);

		String[] sTemp = COMMON_METHODS.splitTestObject(TestObject);
		WebElement we = COMMON_METHODS.checkElement(sTemp[0], sTemp[1],
				sTemp[2], sTemp[3], methodName);

		try {

			if (we.isDisplayed()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			REPORTER.catchException(e, "Element '" + sTemp[2] + "'");
		}

		sTemp = null;/* objType = null; */

		return false;
	}

	/*
	 * This method is the helper method to verify the contact information of
	 * Authorized contacts
	 */
	private void verifyAuthorizedContactInfo(String object, String data)
			throws Exception {

		// Enter First Name
		if (COMMON_METHODS.getText(object, "value").equalsIgnoreCase(data))
			REPORTER.LogEvent(TestStatus.PASS, data + " Displayed", data
					+ " Displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Expected Value=" + data,
					"Actual Value=" + COMMON_METHODS.getText(object, "value"),
					"");

	}
	
	
	private void loginClient() throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3") };
			System.out.println("fffffff="+ReadwritDataFromProps.props.getProperty(employer +".cpu.userName"));
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(employer +".cpu.userName"), getTestData("TD_PWD"),signInArray);
			
		}
	}
}
