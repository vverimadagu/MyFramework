/**
 * 
 */
package com.bhs.scripts.employeeportal;

import java.io.IOException;
import java.util.List;

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

/**
 * @author vverimadugu
 *
 */
public class EP_Home_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	String employer=null;
	
	/**
	 * This Test cover Home page Navigation - Bottom Bar 'Feedback' 
	 * 'Privacy Policy' 'Terms of Use' 'Trademark Notice'
	 * TFS ID:3498
	 * 
	 * */
	@Test  @Parameters("client")
	public void EP_HomepageNavigation (String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		this.employer = employer;

		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty(employer + ".signup.userName");
		String password=getTestData("TD_PWD");

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//Login with 
		//businessComponents.LoginEmployeeportal(userName,password);
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);

		//Click 'Feedback' link
		businessComponents.EP_ClickHomeBottomLinks("Feedback");

		//Verify Feedback text in the Feedback page
		businessComponents.EP_verifyText("Feedback", "Feedback","h1");

		//Go back to Home Page
		businessComponents.EP_ClickHomeBottomLinks("Home");

		//Click 'Privacy Policy' link 
		businessComponents.EP_ClickHomeBottomLinks("PrivacyPolicy");

		//Verify PrivacyPolicy text in the PrivacyPolicy page
		businessComponents.EP_verifyText("Privacy Policy", "Privacy Policy","h1");

		//Go back to Home Page
		businessComponents.EP_ClickHomeBottomLinks("Home");

		//Click 'TermsofUse' link 
		businessComponents.EP_ClickHomeBottomLinks("TermsofUse");

		//Verify TermsofUse text in the TermsofUse page
		businessComponents.EP_verifyText("Terms of Use", "Terms of Use","h1");

		//Go back to Home Page
		businessComponents.EP_ClickHomeBottomLinks("Home");

		//Click 'TrademarkNotice' link
		businessComponents.EP_ClickHomeBottomLinks("TrademarkNotice");

		//Verify TrademarkNotice text in the TrademarkNotice page
		businessComponents.EP_verifyText("Trademark Notice", "Trademark Notice","h1");

		/*//Click Logout
		businessComponents.logout();*/

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * TFS ID:4522 - BUCA - Automation - Resources - Ensure page links are linked to desired locations
	 * */
	
	@Test @Parameters("client")
	public void EP_ResourcesPageLinks (String employer) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty("client" +  employer + " .userName");
		String password=getTestData("TD_PWD");
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		// Login with Employee Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);
		
		// Navigate to Resources tab
		COMMON_METHODS.clickElement(getTestObject("Res_Tab_01"));
		
		// Verify the 'Child Care Center Care Tip Sheet' link is opening a PDF
		WebElement wCCTipSheetLink = COMMON_METHODS.driver.findElement(By.linkText("Child Care Center Care Tip Sheet"));
		
		if(wCCTipSheetLink.getAttribute("href").endsWith(".pdf")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify '" + wCCTipSheetLink.getText() + "' link opens a PDF.", "'" + wCCTipSheetLink.getText() + "' link opens a PDF.","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify '" + wCCTipSheetLink.getText() + "' link opens a PDF.", "'" + wCCTipSheetLink.getText() + "' link does not open a PDF.","");
		}	
		
		// Verify all 50 states including Not in US/Canada 
		String strStates = getTestData("TD_US_States"); 
		String[] arrStates = strStates.split(",");
		
		// Verify each state is present in the drop-down
		for (String strState : arrStates) {
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("KRS_02"), strState);
		}

		// Verify the 'Child In-Home Care Tip Sheet' link is opening a PDF
		WebElement wInhomeTipSheetLink = COMMON_METHODS.driver.findElement(By.linkText("Child In-Home Care Tip Sheet"));
		
		if(wInhomeTipSheetLink.getAttribute("href").endsWith(".pdf")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify '" + wInhomeTipSheetLink.getText() + "' link opens a PDF.", "'" + wInhomeTipSheetLink.getText() + "' link opens a PDF.","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify '" + wInhomeTipSheetLink.getText() + "' link opens a PDF.", "'" + wInhomeTipSheetLink.getText() + "' link does not open a PDF.","");
		}	
		
		// Verify the 'Download our Back-Up Care Parent Handbook' link is opening a PDF
		WebElement wParentHandbookLink = COMMON_METHODS.driver.findElement(By.linkText("Download our Back-Up Care Parent Handbook"));
		
		if(wParentHandbookLink.getAttribute("href").endsWith(".pdf")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify '" + wParentHandbookLink.getText() + "' link opens a PDF.", "'" + wParentHandbookLink.getText() + "' link opens a PDF.","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify '" + wParentHandbookLink.getText() + "' link opens a PDF.", "'" + wParentHandbookLink.getText() + "' link does not open a PDF.","");
		}	
		
		// Verify the 'Go to Bright Connections' link is opening a PDF
		String strGoToBHConn = COMMON_METHODS.getText(getTestObject("KRS_05"));
		if(COMMON_METHODS.getText(getTestObject("KRS_05"),"href").equals("http://www.brighthorizons.com/brightconnections")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify '" + strGoToBHConn + "' link opens a PDF.", "'" + strGoToBHConn + "' link opens a PDF.","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify '" + strGoToBHConn + "' link opens a PDF.", "'" + strGoToBHConn + "' link does not open a PDF.","");
		}	
		
		// Verify user is taken to Bright Connections website displayed in separate tab/window
		COMMON_METHODS.verifyElementDisplayed(getTestObject("KRS_06"));

		// Click Logout
		Utility.logout();
		
		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This Test cover Logout functionality 
	 * TFS ID:23357
	 * */
	
	@Test @Parameters("client")
	public void EP_General (String employer) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty("client" + employer + ".userName");
		String password=getTestData("TD_PWD");
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Login with 
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);
						
		//Click Logout
		Utility.logout();
		
		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test covers My Account - User is able to update My Account information
	 * TFS ID: 3647
	 * 
	 * */
	@Test @Parameters("client")
	public void EP_MyAccount(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty("client" + employer + " .userName");
		String password=getTestData("TD_PWD");

		/*// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);*/

		/*// User logs into the portal
		businessComponents.LoginEmployeeportal(userName,password);*/
		
		this.loginEPPoratl();

		// Click 'My Account' tab
		Utility.clickLink(getTestObject("MA_01"));

		// Expand General Settings section
		Utility.clickLink(getTestObject("MA_02"));

		// Edit First Name
		businessComponents.EP_Update_General_Settings(getTestObject("MA_03"), getTestData("TD_FirstName"));

		// Click 'My Account' tab
		Utility.clickLink(getTestObject("MA_01"));

		// Expand General Settings section
		Utility.clickLink(getTestObject("MA_02"));

		// Verify First Name is updated
		COMMON_METHODS.VerifyTextPresent(getTestObject("MA_03"), getTestData("TD_FirstName"));

		// Edit Last Name
		businessComponents.EP_Update_General_Settings(getTestObject("MA_05"), getTestData("TD_LastName"));		

		// Click 'My Account' tab
		Utility.clickLink(getTestObject("MA_01"));

		// Expand General Settings section
		Utility.clickLink(getTestObject("MA_02"));

		// Verify Last Name is updated
		COMMON_METHODS.VerifyTextPresent(getTestObject("MA_05"), getTestData("TD_LastName"));

		// Edit Email address
		businessComponents.EP_Update_General_Settings(getTestObject("MA_06"), getTestData("TD_Email"));		

		// Click 'My Account' tab
		Utility.clickLink(getTestObject("MA_01"));

		// Expand General Settings section
		Utility.clickLink(getTestObject("MA_02"));

		// Verify Email address is updated
		COMMON_METHODS.VerifyTextPresent(getTestObject("MA_06"), getTestData("TD_Email"));

		/*// Logout Employee portal
		businessComponents.logout();*/

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}	

	/**
	 * EE- Register –  Verify My Profile Page Information
	 * TFS ID: 6622
	 * */
	
	@Test @Parameters("client")
	public void EP_MyProfile_Information(String employer) throws Exception {

		boolean bGenderNotSelected = false;
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//Verify Employer
		businessComponents.EP_verifyEmployer(employer);
		
		//Accept policy
		businessComponents.EP_AcceptPolicyAndSubmit();
	
		//Sign Up
		businessComponents.EP_SignUpUser("1",employer,"bh");
		
		// Click Employee Profile link
		COMMON_METHODS.clickElement(getTestObject("SOL_02"));
	
		// Verify the First name, Last name and Email fields contain values which have been entered during signup
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_76"), ReadwritDataFromProps.props.getProperty("client2.firstName"), "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_77"), ReadwritDataFromProps.props.getProperty("client2.lastName"), "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_78"), ReadwritDataFromProps.props.getProperty("client2.userEmail"), "value");
		
		// Verify the Middle Initial, Preferred Name fields are blank
		COMMON_METHODS.VerifyTextPresent(getTestObject("EP_01"), "", "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("EP_02"), "", "value");
		if(!COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_27"))){
			bGenderNotSelected = true;
		}
		
		// Verify the Home Address fields are blank
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_30"), "", "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_31"), "", "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_32"), "", "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_33"), "", "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_34"), "", "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_35"), "", "value");
		
		// Verify the Employer field is disabled and pre-populated
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("EP_04"), true);
		COMMON_METHODS.VerifyTextPresent(getTestObject("EP_04"), getTestData("TD_ClientName"));
		
		// Verify the Work City, Work State, Job Title fields are blank
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_36"), "", "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_37"), "", "value");
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_38"), "", "value");
		
		// Logout Employee portal
		Utility.logout();
		
		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 * This test covers 
	 * TFS ID: 10176 - Help - Ensure 'Email Us' link functions correctly
	 * TFS ID: 18511 - Help - Ensure 'Send Us An Email' pop-up has a 'Cancel' button that functions correctly
	 * */
	
	@Test @Parameters("client")
	public void Help_EmailUs_Link(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty("client" + employer + ".userName");
		String password=getTestData("TD_PWD");

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);

		// Click the Help navigation tab
		COMMON_METHODS.clickElement(getTestObject("HL_01"));

		// click on the Send us an email link
		COMMON_METHODS.clickElement(getTestObject("HL_02"));

		// Input Full Name
		COMMON_METHODS.editAField(getTestObject("HL_03"), getTestData("TD_FullName"));

		// Input Email ID
		COMMON_METHODS.editAField(getTestObject("HL_04"), getTestData("TD_Email"));

		// Select any option from the Subject drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("HL_05"), getTestData("TD_Subject"), "byVisibleText");
		
		// Enter the message in the Text Area
		COMMON_METHODS.editAField(getTestObject("HL_06"), getTestData("TD_Message"));

		// Click on Cancel button
		COMMON_METHODS.clickElement(getTestObject("HL_09"));
		
		// Verify the full name field is cleared
		if(COMMON_METHODS.getText(getTestObject("HL_03")).equals(""))
			REPORTER.LogEvent(TestStatus.PASS, "Verify full name field is cleared", "full name field is cleared", "");
		else{
			REPORTER.LogEvent(TestStatus.WARNING, "Verify full name field is cleared", "full name field is not cleared", "");
		}
		
		// Verify Email field is cleared
		if(COMMON_METHODS.getText(getTestObject("HL_04")).equals(""))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Email field is cleared", "Email field is cleared", "");
		else{
			REPORTER.LogEvent(TestStatus.WARNING, "Verify Email field is cleared", "Email field is not cleared", "");
		}

		// Verify Subject field value is reset to Account Maintenance
		if(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("HL_05")).equalsIgnoreCase("Account Maintenance"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Subject field value is reset to Account Maintenance", "Subject field value is reset to Account Maintenance", "");
		else{
			REPORTER.LogEvent(TestStatus.WARNING, "Verify Subject field value is reset to Account Maintenance", "Subject field value is not reset to Account Maintenance", "");
		}

		// Verify Test Area message is cleared
		if(COMMON_METHODS.getText(getTestObject("HL_06")).equals(""))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Test Area message field is cleared", "full name field is cleared", "");
		else{
			REPORTER.LogEvent(TestStatus.WARNING, "Verify Test Area message is cleared", "full name field is not cleared", "");
		}

		// Input Full Name
		COMMON_METHODS.editAField(getTestObject("HL_03"), getTestData("TD_FullName"));

		// Input Email ID
		COMMON_METHODS.editAField(getTestObject("HL_04"),getTestData("TD_Email"));

		// Select any option from the Subject drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("HL_05"), getTestData("TD_Subject"), "byVisibleText");
		
		// Enter the message in the Text Area
		COMMON_METHODS.editAField(getTestObject("HL_06"),getTestData("TD_Message"));

		// Click on Submit button
		COMMON_METHODS.clickElement(getTestObject("HL_07"));
		
		// Verify the appropriate message is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("HL_08"), getTestData("TD_SubmittedMessage"));
					
		// Logout Employee portal
		Utility.logout();
		
		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test covers Help - Ensure 'Email Us' link functions correctly
	 * TFS ID: 10176
	 * */

	@Test @Parameters("client")
	public void Help_Email_Us_Link_Submit(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty("client" + employer  +".userName");
		String password=getTestData("TD_PWD");

		/*// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);

		// User logs into the portal
		businessComponents.LoginEmployeeportal(userName,password);*/
		
		this.loginEPPoratl();

		// Click the Help navigation tab
		COMMON_METHODS.clickElement(getTestObject("HL_01"));

		// click on the Send us an email link
		COMMON_METHODS.clickElement(getTestObject("HL_02"));

		// Input Full Name
		COMMON_METHODS.editAField(getTestObject("HL_03"), getTestData("TD_FullName"));

		// Input Email ID
		COMMON_METHODS.editAField(getTestObject("HL_04"),getTestData("TD_Email"));

		// Select any option from the Subject drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("HL_05"), getTestData("TD_Subject"), "byVisibleText");

		// Enter the message in the Text Area
		COMMON_METHODS.editAField(getTestObject("HL_06"),getTestData("TD_Message"));

		// Click on Submit button
		COMMON_METHODS.clickElement(getTestObject("HL_07"));

		// Verify the appropriate message is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("HL_08"), getTestData("TD_SubmittedMessage"));

		// Logout Employee portal
		Utility.logout();

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	

	/**
	 * This test covers Help - Ensure 'Send Us An Email' pop-up has a 'Cancel' button that functions correctly
	 * TFS ID: 18511
	 * 
	 * */

	@Test @Parameters("client")
	public void Help_Email_Us_Link_Cancel(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		this.loginEPPoratl();

		// Click the Help navigation tab
		COMMON_METHODS.clickElement(getTestObject("HL_01"));

		// click on the Send us an email link
		COMMON_METHODS.clickElement(getTestObject("HL_02"));

		// Input Full Name
		COMMON_METHODS.editAField(getTestObject("HL_03"), getTestData("TD_FullName"));

		// Input Email ID
		COMMON_METHODS.editAField(getTestObject("HL_04"), getTestData("TD_Email"));

		// Select any option from the Subject drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("HL_05"), getTestData("TD_Subject"), "byVisibleText");

		// Enter the message in the Text Area
		COMMON_METHODS.editAField(getTestObject("HL_06"), getTestData("TD_Message"));

		// Click on Cancel button
		COMMON_METHODS.clickElement(getTestObject("HL_09"));
		
		// click on the Send us an email link
		COMMON_METHODS.clickElement(getTestObject("HL_02"));

		// Verify the full name field is cleared
		if(COMMON_METHODS.getText(getTestObject("HL_03")).equals(""))
			REPORTER.LogEvent(TestStatus.PASS, "full name field is cleared", "full name field is cleared", "");
		else{
			// Close on the Send us an email link
			COMMON_METHODS.clickElement(getTestObject("HL_23"));
			REPORTER.LogEvent(TestStatus.FAIL, "full name field is not cleared", "full name field is not cleared", "");
		}


		// Verify Email field is cleared
		if(COMMON_METHODS.getText(getTestObject("HL_04")).equals(""))
			REPORTER.LogEvent(TestStatus.PASS, "full name field is cleared", "full name field is cleared", "");
		else{
			// Close on the Send us an email link
			COMMON_METHODS.clickElement(getTestObject("HL_23"));
			REPORTER.LogEvent(TestStatus.FAIL, "full name field is not cleared", "full name field is not cleared", "");
		}

		// Verify Subject field value is reset to Account Maintenance
		if(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("HL_05")).equalsIgnoreCase("Account Maintenance"))
			REPORTER.LogEvent(TestStatus.PASS, "Subject field value is reset to Account Maintenance", "Subject field value is reset to Account Maintenance", "");
		else{
			// Close on the Send us an email link
			COMMON_METHODS.clickElement(getTestObject("HL_23"));
			REPORTER.LogEvent(TestStatus.FAIL, "Subject field value is not reset to Account Maintenance", "Subject field value is not reset to Account Maintenance", "");
		}

		// Verify Test Area message is cleared
		if(COMMON_METHODS.getText(getTestObject("HL_06")).equals(""))
			REPORTER.LogEvent(TestStatus.PASS, "Test Area message field is cleared", "full name field is cleared", "");
		else{
			// Close on the Send us an email link
			COMMON_METHODS.clickElement(getTestObject("HL_23"));
			REPORTER.LogEvent(TestStatus.FAIL, "Test Area message is not cleared", "full name field is not cleared", "");
		}

		// Close on the Send us an email link
		COMMON_METHODS.clickElement(getTestObject("HL_23"));

		// Logout Employee portal
		//businessComponents.logout();

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	} 


	/**
	 * This Test cover Feedback - User is able to submit feedback
	 * TFS ID:8381
	 * This Test case is rolled up to My_Profile_ensure_funding_links(8793) method
	 * 
	 * *//*
	@Test()
	public void EP_Feedback_Submit() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty("client1.userName");
		String password=getTestData("TD_PWD");

		//Launch the browser
		businessComponents.EP_LaunchBrowser();

		//Login to Employee Portal 
		businessComponents.LoginEmployeeportal(userName,password);
		
		this.loginEPPoratl();

		//Click 'Feedback' link
		COMMON_METHODS.clickElement(getTestObject("FD_01"));

		//Select any option from the drop down
		COMMON_METHODS.listBoxSelect(getTestObject("FD_02"), getTestData("TD_Feedback"), "byVisibleText");

		//Type 'test feedback' in 'Comments' text box
		COMMON_METHODS.editAField(getTestObject("FD_03"), "test feedback");

		//Select 'I wish to be contacted' check box
		COMMON_METHODS.checkBox(getTestObject("FD_04"), "check");

		//Select both yes anonymously radio buttons
		COMMON_METHODS.radioButton(getTestObject("FD_05"));
		COMMON_METHODS.radioButton(getTestObject("FD_06"));

		//Click 'Submit' button
		COMMON_METHODS.clickElement(getTestObject("FD_07"));

		// Logout Employee portal
		businessComponents.logout();

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}*/	

	/**
	 * This Test cover Feedback - My Account - ensure 'Payment Information' 
	 * module functions correctly, user able to add a credit card payment method

	 * TFS ID:15830
	 * 
	 * */
	@Test @Parameters("client")
	public void EP_Add_Payment_Information(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty(employer + ".signup.userName");
		String password=getTestData("TD_PWD");

		//Login with 
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);

		//Click 'My Account' link 
		COMMON_METHODS.clickElement(getTestObject("MA_01"));
		Thread.sleep(1000);

		//click the 'Payment Information' link
		COMMON_METHODS.clickElement(getTestObject("MA_20"));

		//click 'View Payment policies'
		COMMON_METHODS.clickElement(getTestObject("MA_21"));


		//ensure view payment policies link pop up functions correctly(close the pop up)
		COMMON_METHODS.clickElement(getTestObject("MA_22"));

		//click 'Add a Payment Method'
		COMMON_METHODS.clickElement(getTestObject("OL_117"));


		//Switch to iframe
		COMMON_METHODS.driver.switchTo().frame(COMMON_METHODS.driver.findElement(By.name("ezdraft-iframe")));

		//Ensure user is able to successfully add a credit card payment method with all the req fields
		COMMON_METHODS.editAField(getTestObject("MA_23"),getTestData("TD_Feedback"));
		Thread.sleep(2000);
		COMMON_METHODS.editAField(getTestObject("MA_24"),"Test");
		Thread.sleep(2000);	
		COMMON_METHODS.radioButton(getTestObject("MA_25"));
		Thread.sleep(2000);
		COMMON_METHODS.radioButton(getTestObject("MA_26"));
		Thread.sleep(2000);
		COMMON_METHODS.editAField(getTestObject("MA_27"),"4111111111111111");
		Thread.sleep(2000);
		COMMON_METHODS.listBoxSelect(getTestObject("MA_28"), getTestData("TD_Month"), "byVisibleText");
		Thread.sleep(2000);
		COMMON_METHODS.listBoxSelect(getTestObject("MA_29"), getTestData("TD_Year"), "byVisibleText");
		Thread.sleep(2000);
		COMMON_METHODS.clickElement(getTestObject("MA_30"));
		Thread.sleep(2000);
		COMMON_METHODS.clickElement(getTestObject("MA_31"));
		Thread.sleep(2000);

		//Switch to normal window
		COMMON_METHODS.driver.switchTo().defaultContent();

		//click on cancel on the pop up
		COMMON_METHODS.clickElement(getTestObject("MA_32"));
		Thread.sleep(2000);		

		// Logout Employee portal
		Utility.logout();

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	


	/**
	 * TFS ID:8793:This Test cover My Profile - ensure the funding links are present and functional
	 * TFS ID:8381:This Test cover Feedback - User is able to submit feedback
	 * TFS ID:4621:BUCA - My Benefit - Overview - Ensure 'Utilization terms' link functions
	 * TFS ID:5240:Reservations - Ensure user can view Benefits on My Profile summary
	 * TFS ID:23385:This Test cover Help - FAQ - Ensure 'Topics Title' link functions correctly
	 * */

	@Test @Parameters("client")
	public void My_Profile_ensure_funding_links (String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		/*  // Launch the browser
		  Utility.launchBrowser(getTestData("TD_EP_URL"));

		  //Get User Name and Password
		  String userName=ReadwritDataFromProps.props.getProperty("client"+ employer + ".userName");
		  String password=getTestData("TD_PWD");

		  // READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
		  // SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
		  readTestObject(getDataTablePath("EP"), "TO_EP");
		  
		  //Login with 
		  String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		  Utility.loginToBUCA(userName, password,signInArray);*/
		
		  this.loginEPPoratl();
		  
		  businessComponents.EP_ClickHomeBottomLinks("Home");

		  //Find the Employee profile section on the right side of the page and click the employee name
		  COMMON_METHODS.clickElement(getTestObject("EMP_NAME"));

		  //Find the payment method section and click the view payment policies
		  COMMON_METHODS.clickElement(getTestObject("MA_21"));

		  //ensure view payment policies link pop up functions correctly(close the pop up)
		  COMMON_METHODS.clickElement(getTestObject("MA_34"));

		  //Click 'Feedback' link
		  COMMON_METHODS.clickElement(getTestObject("FD_01"));

		  //Select any option from the drop down
		  COMMON_METHODS.listBoxSelect(getTestObject("FD_02"), getTestData("TD_Feedback"), "byVisibleText");

		  //Type 'test feedback' in 'Comments' text box
		  COMMON_METHODS.editAField(getTestObject("FD_03"), "test feedback");

		  //Select 'I wish to be contacted' check box
		  COMMON_METHODS.checkBox(getTestObject("FD_04"), "check");

		  //Select both yes anonymously radio buttons
		  COMMON_METHODS.radioButton(getTestObject("FD_05"));
		  COMMON_METHODS.radioButton(getTestObject("FD_06"));

		  //Click 'Submit' button
		  COMMON_METHODS.clickElement(getTestObject("FD_07"));

		  //Click 'Benefit' link from top navigation menu
		  COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		  //Click on Utilisation Terms Link 
		  COMMON_METHODS.clickElement(getTestObject("MA_37"));

		  //Close the pop up Utilisation terms
		  COMMON_METHODS.clickElement(getTestObject("MA_38"));

		  // Navigate to 'Reservations' tab
		  COMMON_METHODS.clickElement(getTestObject("OL_10"));

		  //Click on View Benefits link
		  COMMON_METHODS.clickElement(getTestObject("MA_42"));

		  Thread.sleep(2000);

		  //Click on Help Navigation Button
		  COMMON_METHODS.clickElement(getTestObject("HL_20"));

		  //Click on 'Topic1' link under most requested Help topics
		  COMMON_METHODS.clickElement(getTestObject("HL_21"));

		  //Verify the text present on the next page
		  COMMON_METHODS.VerifyTextPresent(getTestObject("HL_22"), "Topic Title 1");

		  // Logout Employee portal
		  Utility.logout();
	}

	/**
	 * This Test cover Help - FAQ - Ensure 'Topics Title' link functions correctly
	 * TFS ID:23385
	 * This Test case is rolled up to My_Profile_ensure_funding_links(8793)
	 * */ 
	@Test @Parameters("client")
	public void EP_Help_FAQ_Topic_Links(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		/*// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);

		//Login with 
		businessComponents.LoginEmployeeportal(userName,password);*/
		
		this.loginEPPoratl();

		//Click on Help Navigation Button
		COMMON_METHODS.clickElement(getTestObject("HL_20"));

		//Click on 'Topic1' link under most requested Help topics
		COMMON_METHODS.clickElement(getTestObject("HL_21"));

		//Verify the text present on the next page
		COMMON_METHODS.VerifyTextPresent(getTestObject("HL_22"), "Topic Title 1");

		// Logout Employee portal
		//businessComponents.logout();

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	} 

	//Test Case #10175: 
	//BUCA - Help - ensure Help page loads correctly
	@Test @Parameters("client")
	public void EP_LoadsHelpPage (String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.loginEPPoratl();
		
		// Click the Help navigation tab
		COMMON_METHODS.clickElement(getTestObject("HL_01"));

		// Verifying Help Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("HL_01"), "Help");				

		//Log out
		Utility.logout();

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	/**
	 * TFS ID: 24006 - CSC BUCA- Forgot Password - Verify if  BUCA Web Application should able to view the Forgot Password link
	 * TFS ID: 24007 - CSC BUCA- Forgot Password - Verify Forgot Password link points to the Reset Password page
	 * */
	
	@Test @Parameters("client")
	public void EP_Home_ResetPassword_Page(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		// Verify the 'Forgot Password?' link is displayed
		if(COMMON_METHODS.driver.findElement(By.linkText("Forgot Password?")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Forgot Password?' link is displayed.", "'Forgot Password?' link is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Forgot Password?' link is displayed.", "'Forgot Password?' link is not displayed.", "");
		}
		
		// Click the Forgot password link
		COMMON_METHODS.clickElement(getTestObject("LP_01"));
		
		// Verify the 'Reset Password' page is displayed
		if(COMMON_METHODS.driver.findElement(By.xpath("//div[contains(text(),'Reset Password')]")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Reset Password' page is displayed.", "'Reset Password' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Reset Password' page is displayed.", "'Reset Password' page is not displayed.", "");
		}
		
		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * TFS ID: 24006 - CSC BUCA- Forgot Password - Verify if  BUCA Web Application should able to view the Forgot Password link
	 * 
	 * *//*

	@Test()

	public void EP_Home_ForgotPassword_Link() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Launch the browser
		businessComponents.EP_LaunchBrowser();

		// Verify the 'Forgot Password?' link is displayed
		if(COMMON_METHODS.driver.findElement(By.linkText("Forgot Password?")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Forgot Password?' link is displayed.", "'Forgot Password?' link is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Forgot Password?' link is displayed.", "'Forgot Password?' link is not displayed.", "");
		}

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	*//**
	 * TFS ID: 24007 - CSC BUCA- Forgot Password - Verify Forgot Password link points to the Reset Password page
	 * *//*

	@Test()

	public void EP_Home_ResetPassword_Page() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Launch the browser
		businessComponents.EP_LaunchBrowser();

		// Click the Forgot password link
		COMMON_METHODS.clickElement(getTestObject("LP_01"));

		// Verify the 'Reset Password' page is displayed
		if(COMMON_METHODS.driver.findElement(By.xpath("//div[contains(text(),'Reset Password')]")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Reset Password' page is displayed.", "'Reset Password' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Reset Password' page is displayed.", "'Reset Password' page is not displayed.", "");
		}

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}*/

	//Test Case #No TFS ID
	//BUCA - Forgot Password -Employee portal
	@Test @Parameters("client")
	public void EP_ForgetPassword (String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Launch Browser with EP URL
		Utility.launchBrowser("TD_EP_URL");

		//Clicks on ForgetPassword link, enters email and click on Submit button
		String email[]={getTestData("TD_FORGETEMAIL")};
		businessComponents.forGetAndResetPassword("ForgetPassword",email);

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	//Test Case #No TFS ID
	//BUCA - Reset Password -Employee portal
	@Test @Parameters("client")
	public void EP_ResetPassword (String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Verify Employer after Entering Employer ID and Password 
		businessComponents.EP_verifyEmployer(employer);

		// Accept privacy policy
		businessComponents.EP_AcceptPolicyAndSubmit();

		//Create dynamic user name
		String userName = createDyanamicUserData();

		//Login with 
		businessComponents.EP_SignUpUser(userName, employer, "resetPassword");

		// Click 'My Account' tab
		Utility.clickLink(getTestObject("MA_01"));

		// Expand General Settings section
		Utility.clickLink(getTestObject("MA_21"));

		//Clicks on Reset Password link, enters old/New password and click on Submit button
		String passwords[]={getTestData("TD_PASSWORD"),getTestData("TD_NEWPASSWORD")};
		businessComponents.forGetAndResetPassword("ResetPassword",passwords);

		//Logout from Ep
		Utility.logout();

		//Login with 
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA("BHU"+userName, passwords[0],signInArray);
		
		//Logout from Ep
		Utility.logout();

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	//Test Case #BLANK
	//BUCA - Cancel IH Reservation And Confirm
	// Sanjeev 28/03/2014
	@Test @Parameters("client")
	public void EP_CancelReservation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		Utility.launchBrowser(getTestData("TD_EP_URL"));

		String userName = ReadwritDataFromProps.props.getProperty(employer + ".signup.userName");
		String password =  getTestData("TD_PWD");

		//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);		

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));		

		//Verify user moved to Reservations page.
		businessComponents.EP_verifyText("Reservations", "Reservations");

		// Click on "View" link on a reservation (Either Confrim or In Progress)
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "In Progress", "byVisibleText");

		Thread.sleep(10000);
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));

		//click the Cancel Reservation button
		COMMON_METHODS.clickElement(getTestObject("Resrv_Cancel"));

		COMMON_METHODS.listBoxSelect(getTestObject("RESV_11"), "1", "byIndex");

		// Click 'Submit' button on 'Cancel Care Session' pop-up
		COMMON_METHODS.clickElement(getTestObject("RESV_13"));

		// Logout from Application	
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}

	/**
	 * This Test cover BUCA - My Account - Balance Summary - ensure user is 
	 * able to edit a payment method
	 * TFS ID:18472
	 **/

	//Edit link is not available in the new build,but it was available in the old build.Issue is reported 

	@Test @Parameters("client")
	public void EP_Edit_Payment_Information(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty(employer + ".signup.userName");
		String password=getTestData("TD_PWD");


		//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);

		//Click 'My Account' link 
		COMMON_METHODS.clickElement(getTestObject("MA_01"));
		Thread.sleep(1000);

		//click the 'Payment Information' link
		COMMON_METHODS.clickElement(getTestObject("MA_20"));

		//click 'View Payment policies'
		COMMON_METHODS.clickElement(getTestObject("MA_21"));


		//ensure view payment policies link pop up functions correctly(close the pop up)
		COMMON_METHODS.clickElement(getTestObject("MA_22"));

		//click 'Edit a Payment Method'
		COMMON_METHODS.clickElement(getTestObject("MA_41"));


		//Ensure user is able to successfully add a credit card payment method with all the req fields 

		//Switch to iframe
		COMMON_METHODS.driver.switchTo().frame(COMMON_METHODS.driver.findElement(By.name("ezdraft-iframe")));
		COMMON_METHODS.editAField(getTestObject("MA_23"),getTestData("TD_Feedback"));
		Thread.sleep(2000);
		COMMON_METHODS.editAField(getTestObject("MA_24"),"Test");
		Thread.sleep(2000);
		COMMON_METHODS.radioButton(getTestObject("MA_25"));
		Thread.sleep(2000);
		COMMON_METHODS.radioButton(getTestObject("MA_26"));
		Thread.sleep(2000);
		COMMON_METHODS.editAField(getTestObject("MA_27"),"4111111111111111");
		Thread.sleep(2000);

		//Editing the Month and Year of issue of the card
		COMMON_METHODS.listBoxSelect(getTestObject("MA_28"), getTestData("TD_Month1"), "byVisibleText");
		Thread.sleep(2000);
		COMMON_METHODS.listBoxSelect(getTestObject("MA_29"), getTestData("TD_Year1"), "byVisibleText");
		Thread.sleep(2000);

		COMMON_METHODS.clickElement(getTestObject("MA_30"));
		Thread.sleep(2000);
		COMMON_METHODS.clickElement(getTestObject("MA_31"));
		Thread.sleep(2000);

		//Switch to normal window
		COMMON_METHODS.driver.switchTo().defaultContent();

		//click on cancel on the pop up
		COMMON_METHODS.clickElement(getTestObject("MA_32"));
		Thread.sleep(2000);  

		// Logout Employee portal
		Utility.logout();
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	}

	/**
	 * This Test cover BUCA - My Account - ensure 'Payment Information' 
	 * module functions correctly, user able to add a ACH payment method
	 * TFS ID:18250
	 * */

	@Test @Parameters("client")
	public void EP_Add_ACH_Payment(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty(employer + ".signup.userName");
		String password=getTestData("TD_PWD");

		//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);

		//Click 'My Account' link 
		COMMON_METHODS.clickElement(getTestObject("MA_01"));
		Thread.sleep(1000);

		//click the 'Payment Information' link
		COMMON_METHODS.clickElement(getTestObject("MA_20"));

		//click 'View Payment policies'
		COMMON_METHODS.clickElement(getTestObject("MA_21"));

		//ensure view payment policies link pop up functions correctly(close the pop up)
		COMMON_METHODS.clickElement(getTestObject("MA_22"));

		//click 'Add a Payment Method' 
		COMMON_METHODS.clickElement(getTestObject("OL_117")); 

		//Ensure user is able to successfully add a payment method with all the req fields 

		//Switch to iframe 
		COMMON_METHODS.driver.switchTo().frame(COMMON_METHODS.driver.findElement(By.name("ezdraft-iframe"))); 
		COMMON_METHODS.editAField(getTestObject("MA_23"),getTestData("TD_Feedback")); 
		Thread.sleep(2000); 
		COMMON_METHODS.editAField(getTestObject("MA_24"),"Test"); 
		Thread.sleep(2000); 

		//Click on Checkings/Savings Radio button
		COMMON_METHODS.clickElement(getTestObject("MA_61"));
		Thread.sleep(2000);

		//Input value for Bank Routing number
		COMMON_METHODS.editAField(getTestObject("MA_62"),"122000247");

		//Input value for Bank Account number
		COMMON_METHODS.editAField(getTestObject("MA_63"),"122000247");
		Thread.sleep(2000); 

		//Click on review button
		COMMON_METHODS.clickElement(getTestObject("MA_30")); 
		Thread.sleep(2000); 

		//Click on Submit button
		COMMON_METHODS.clickElement(getTestObject("MA_31")); 
		Thread.sleep(2000);

		//Switch to normal window 
		COMMON_METHODS.driver.switchTo().defaultContent(); 

		//click on cancel on the pop up 
		COMMON_METHODS.clickElement(getTestObject("MA_32")); 
		Thread.sleep(2000);

		// Logout Employee portal
		Utility.logout();

	}

	
	private void loginEPPoratl() throws IOException, Exception {

			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
				//Verify Login Page displayed
				String userName  =ReadwritDataFromProps.props.getProperty(this.employer + ".signup.userName");
				String password	= getTestData("TD_PWD");
				
				//Login to Emp Portal
				String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
				Utility.loginToBUCA(userName, password,signInArray);
				
			}
		}


}
