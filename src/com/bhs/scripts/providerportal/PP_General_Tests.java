package com.bhs.scripts.providerportal;

import java.io.IOException;

import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.BusinessComponents.PP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.Utility;

public class PP_General_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	PP_BusinessComponents ppbubusinessComponents=new PP_BusinessComponents();

	//Reading Test Objects from Data excel 
//	static{
//		try{
//			readTestObject(getDataTablePath("EP"), "TO_EP");
//			readTestObject(getDataTablePath("PP"), "TO_PP");
//			
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 18/03/2014
	 */
	//Test Case #9525: BUCA - Provider - Provider Contacts - ensure all error messages and field validation is displayed correctly

	@Test(priority=1)
	public void PP_AccountManagement_AddNewContact() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);


		// Click 'Account Management' link
		COMMON_METHODS.clickElement(getTestObject("PPAM_01"));

		// Click 'Provider Contacts' link
		COMMON_METHODS.clickElement(getTestObject("PPAM_02"));

		// Click 'Add a New Contact' link
		COMMON_METHODS.clickElement(getTestObject("PPAM_03"));

		// Click 'Submit Contact' button
		COMMON_METHODS.clickElement(getTestObject("PPAM_04"));

		// Verify the error messages displayed
		String strErrorMsgs = COMMON_METHODS.getText(getTestObject("PPRESV_24"));

		// Get the error messages from test data
		String strPCErrMsgs = getTestData("TD_PP_ProviderContacts_ErrMsgs");
		String[] arrPCErrMsgs = strPCErrMsgs.split(",");

		// Verify each error message is displayed 
		for (String msg : arrPCErrMsgs) {

			if(strErrorMsgs.contains(msg)){
				REPORTER.LogEvent(TestStatus.PASS, "Verify '" + msg + "' message is displayed.", "'" + msg + "' message is displayed.","");
			}else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify '" + msg + "' message is displayed.", "'" + msg + "' message is not displayed.","");
			}
		}

		// Verify fields are highlighted in red
		boolean bFirstNameInRed = COMMON_METHODS.getText(getTestObject("PPAM_05"), "class").equals("input-validation-error");
		boolean bLastNameInRed = COMMON_METHODS.getText(getTestObject("PPAM_06"), "class").equals("input-validation-error");
		boolean bEmailInRed = COMMON_METHODS.getText(getTestObject("PPAM_07"), "class").equals("input-validation-error");

		if(bFirstNameInRed && bLastNameInRed && bEmailInRed){
			REPORTER.LogEvent(TestStatus.PASS, "Verify First Name, Last Name, Email fields are displayed red in color.", "First Name, Last Name, Email fields are displayed red in color.","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify First Name, Last Name, Email fields are displayed red in color.", "First Name, Last Name, Email fields are not displayed red in color.","");
		}

		// Logout from 'Provider Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 18/03/2014
	 */
	//Test Case #12275: BUCA - Provider - Provider Newsletter - ensure page is loaded correctly (UX doc)

	@Test(priority=8)
	public void PP_Provider_MonthlyNewsletter() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();
		
		// Click 'Provider Newsletter' link in the footer
		COMMON_METHODS.clickElement(getTestObject("PPAM_08"));

		// Click on a month link
		COMMON_METHODS.clickElement(getTestObject("PPAM_09"));

		if(COMMON_METHODS.getText(getTestObject("PPAM_09"), "href").endsWith(".pdf")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify month link opens a PDF.", "Month link opens a PDF.","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify month link opens a PDF.", "Month link does not open a PDF.","");
		}
		
		// Moving contorl to Pop Up
		COMMON_METHODS.switchToPopup();
		COMMON_METHODS.driver.close();
		COMMON_METHODS.switchToNormal();
		
		// Logout from 'Provider Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}	

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 19/03/2014
	 */
	//Test Case #3796:   BUCA - Locations - authorized contacts are able to add address (if given permission)

	/*@Test(priority=3)
	public void PP_AuthorizedContactsAddLocation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);
		this.loginPPPortal();

		// Click 'Account Management' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("PPAcc_Mgmt"));

		// Verifying Care Account Management Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPAcc_Mgmt"),"Account Management");

		//Click "Service Areas" link
		COMMON_METHODS.clickElement(getTestObject("PPService_Area"));

		// Verifying Service Area Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPVerify_Loc"),"Service Areas");

		//add and save a new location to the list available to the Authorized Contact
		COMMON_METHODS.clickElement(getTestObject("PPAdd_Loc"));
		COMMON_METHODS.editAField(getTestObject("PPLoc_Text"),getTestData("TD_PP_Loc"));
		COMMON_METHODS.clickElement(getTestObject("PPSave_Loc"));

		// Verifying Location Confirmation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPLoc_Confirmation"),"A new Service Area was successfully submitted.");

		// Logout from Employee Portal	Loc_Confirmation
		//Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}*/

	/**
	 * BUCA - Provider - Caregivers - ensure 'Download Training Guide' link functions as desired
	 * TFS ID: 13053
	 * Its There in PP_careGiver_Tests, so commenting Here -- "Sanjeev"
	 */
/*	@Test(priority=4)
	public void PP_CareGiver_TrainingGuide() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);
		this.loginPPPortal();

		//Click on Care Giver link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));

		//Verify the Download Link of Training Guide
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPMA_87"), "Download Training Guide");

		//Logout from 'Provider Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}*/



	/**
	 * Provider - Homepage - ensure homepage displays available and pending reservations
	 * TFS ID: 18780
	 */
	@Test(priority=5)
	public void PP_ReservationTypes() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();
		
		//Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPPOL_10"));

		//Click on Available Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_76"));
		Thread.sleep(1000);

		//Click on Pending Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_78"));
		Thread.sleep(1000);

		//Click on Scheduled Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_79"));
		Thread.sleep(1000);

		//Click on Completed Reservations
		COMMON_METHODS.clickElement(getTestObject("PPMA_80"));
		Thread.sleep(1000); 

		//Logout from 'Provider Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 
	}



	/**
	 * BUCA - Provider - Caregivers - ensure a 'Request to Deactivate' is sent to CRM
	 * TFS ID: 10538
	 */
	@Test(priority=6)
	public void PP_Deactivate_CareGiver() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Care Giver link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));

		//Click on existing Caregiver to edit
		COMMON_METHODS.clickElement(getTestObject("PPMA_82"));

		//Click on Request to Deactivate caregiver
		COMMON_METHODS.clickElement(getTestObject("PPMA_83"));

		//Select a reason from the dropdown for deactivating
		COMMON_METHODS.listBoxSelect(getTestObject("PPMA_84"), "Other Caregiver Issue", "byVisibleText");

		//Input the Notes in the box
		COMMON_METHODS.editAField(getTestObject("PPMA_85"), "Test");
		Thread.sleep(1000);  

		//Click on submit button
		COMMON_METHODS.clickElement(getTestObject("PPMA_86")); 

		//Logout from 'Provider Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}


	//Test Case #No TFS ID
	//BUCA - Forgot Password -Provider portal
	@Test(priority=9)
	public void PP_ForgetPassword () throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//readTestData(getDataTablePath("PP"), "TD_PP");
		//readTestData(getDataTablePath("EP"), "TD_EP1");

		//Launch Browser with EP URL
		Utility.launchBrowser(getTestData("TD_PP_URL"));

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP1");

		//Clicks on ForgetPassword link, enters email and click on Submit button
		String email[]={getTestData("TD_FORGETEMAIL")};
		businessComponents.forGetAndResetPassword("ForgetPassword",email);

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	//Test Case #No TFS ID
	//BUCA - Reset Password -Provider portal
	@Test(priority=10)
	public void PP_ResetPassword () throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);
		
		// Click 'My Account' tab
		Utility.clickLink(getTestObject("MA_01"));
		//Clicks on Reset Password link, enters old/New password and click on Submit button
		String passwords[]={getTestData("TD_NEWPASSWORD")};

		businessComponents.forGetAndResetPassword("ResetPassword",passwords);
				
		//Logout from PP
		Utility.logout();

		String signInArray1[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		Utility.loginToBUCA(getTestData("TD_UserID"), getTestData("TD_NEWPASSWORD"),signInArray1);
	
		//Logout from Ep
		Utility.logout();

		//Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/**
	 * requirement iD #21739: 
	 * BUCA - Provider Portal - Provider Contacts: Submit Contact - Verify the functionality of �Submit Contact� 
	 * button in Provider Contacts under Account Management 
	 */

	@Test(priority=7)
	public void PP_Contacts_SubmitContact() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");

		/// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Hower the mouse or Click on Account Management tab
		COMMON_METHODS.clickElement(getTestObject("PPAM_01"));

		//Select 'Provider Contacts' option
		COMMON_METHODS.clickElement(getTestObject("PPAM_02"));

		// Click 'Add a New Contact' link
		COMMON_METHODS.clickElement(getTestObject("PPAM_03"));

		ppbubusinessComponents.PPAddProviderContact();

		//On selecting �Submit Contact�, the BUCA web application should generate a pop up message with content �Your request has been submitted�.

		// TODO : need to verify popup message due to Error message is displayed when adding contacts

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/** Req ID:22106,22107,22108,22109,22110,22111
	 * Help Link: Tell us Your Question
	 * Help Link: Cancel
	 * Help Link: Send
	 * Help Link: Frequently Asked Questions
	 * Help Link: Other Provider Questions
	 * Live Chat Icon
	 * @author Kiran G
	 */

	@Test(priority=2)
	public void PP_HelpLinks() throws Exception { 

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName(); 
		System.out.println("Inside - " + methodName); 

		// Read test data for based on client 1 
		//readTestData(getDataTablePath("PP"), "TD_PP"); 

		// Launch the browser
		//Utility.launchBrowser(getTestData("TD_PP_URL"));
		/*String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Help Link 
		COMMON_METHODS.clickElement(getTestObject("PPMA_203")); 

		//Click on Tell us you question link 
		COMMON_METHODS.clickElement(getTestObject("PPMA_204")); 

		//Select any question from the dropdown 
		COMMON_METHODS.listBoxSelect(getTestObject("PPMA_205"), getTestData("TD_PP_Category_Ques"), "byVisibleText"); 

		//Input the Reservation number 
		COMMON_METHODS.editAField(getTestObject("PPMA_206"), getTestData("TD_PP_Category_Ques")); 

		//Select the contact preferences from the dropdown 
		//COMMON_METHODS.listBoxSelect(getTestObject("PPMA_207"), getTestData("TD_PP_Contact_Pref"), "byVisibleText"); 

		//Input the text message in the Message text box 
		COMMON_METHODS.editAField(getTestObject("PPMA_208"), "Test"); 

		//Click on submit button 
		COMMON_METHODS.clickElement(getTestObject("PPMA_209")); 

		//Verify the text after submitting the query 
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPMA_210"), "Your question has been submitted and will be addressed by Provider Relations."/*"Thank you, your email has been sent. You can expect to hear back within 3 business days."*/); 

		//Click on Tell us you question link 
		COMMON_METHODS.clickElement(getTestObject("PPMA_204")); 

		//Input the text message in the Message text box 
		COMMON_METHODS.editAField(getTestObject("PPMA_208"), "Cancel button"); 

		//Click on Cancel button 
		COMMON_METHODS.clickElement(getTestObject("PPMA_211")); 

		//Verify the usage of email link which should be used for feedback or questions after submitting the query 
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPMA_212"), "providerportalgroup@brighthorizons.com"); 

		//Verify the usage of email link which is under 'Other Provider Questions' section 
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPMA_213"), "providerportalgroup@brighthorizons.com"); 

		//Click on Home tab 
		COMMON_METHODS.clickElement(getTestObject("PPMA_215")); 

		//Highlight the Live chat icon symbol 
		if(COMMON_METHODS.isElementPresent("liveChatWrapper", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Live Chat' is displayed.", "'Live Chat' is displayed." , "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Live Chat' is displayed.", "'Live Chat' is NOT displayed." , "");
		}

		//Click on Reservations link 
		COMMON_METHODS.clickElement(getTestObject("PPPOL_10")); 

		//Highlight the Live chat icon symbol 
		if(COMMON_METHODS.isElementPresent("liveChatWrapper", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Live Chat' is displayed.", "'Live Chat' is displayed." , "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Live Chat' is displayed.", "'Live Chat' is NOT displayed." , "");
		}

		//Click on Caregiver link 
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01")); 

		//Highlight the Live chat icon symbol 
		if(COMMON_METHODS.isElementPresent("liveChatWrapper", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Live Chat' is displayed.", "'Live Chat' is displayed." , "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Live Chat' is displayed.", "'Live Chat' is NOT displayed." , "");
		}

		//Click on Account Management link 
		COMMON_METHODS.clickElement(getTestObject("PPAM_01")); 

		//Highlight the Live chat icon symbol 
		if(COMMON_METHODS.isElementPresent("liveChatWrapper", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Live Chat' is displayed.", "'Live Chat' is displayed." , "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Live Chat' is displayed.", "'Live Chat' is NOT displayed." , "");
		} 

		//Click on Reports link 
		COMMON_METHODS.clickElement(getTestObject("PPMA_216")); 

		//Highlight the Live chat icon symbol 
		if(COMMON_METHODS.isElementPresent("liveChatWrapper", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Live Chat' is displayed.", "'Live Chat' is displayed." , "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Live Chat' is displayed.", "'Live Chat' is NOT displayed." , "");
		} 

		//Click on Resources link 
		COMMON_METHODS.clickElement(getTestObject("PPPOL_115")); 

		//Highlight the Live chat icon symbol 
		if(COMMON_METHODS.isElementPresent("liveChatWrapper", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Live Chat' is displayed.", "'Live Chat' is displayed." , "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Live Chat' is displayed.", "'Live Chat' is NOT displayed." , "");
		}

		//Logout from PP 
		Utility.logout(); 

		//Log to Reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	} 
	

	/*
	 * @author: Sanjeev Singh
	 * @CreationDate: 20/03/2014
	 */
	//Test Case #12083:    BUCA - Provider - Reservations - Pending Reservations - Staff - ensure 'Map Location' link functions correctly

	@Test()
	public void PP_EnsureMapLocationFunctionality() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_EP5");
		
		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("PPPOL_10"));
		
		//Verify Resrvation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_10"), "Reservations");
		
		//Click "Service Areas" link
		COMMON_METHODS.clickElement(getTestObject("PPPending_Resv_Link"));
		
		// Verifying Service Area Page
		boolean temp = COMMON_METHODS.isElementPresent("//*[@id='pageTwoColLeftSm']/li[2]/h2", "xpath");
		if (temp == true)
		{
				REPORTER.LogEvent(TestStatus.PASS, "Verify Pending reservation", "Pending reservation -- Succeeds" , "");
		}
		else
		{	
				REPORTER.LogEvent(TestStatus.FAIL, "Verify Pending reservation", "Pending reservation -- Failed", "");				
		}	
		
		
		//click Staff Reservation Button  and Verify
		COMMON_METHODS.clickElement(getTestObject("PPPending_Resv_Arrow"));
		COMMON_METHODS.clickElement(getTestObject("PPResrv_Staff"));
		
		// Verifying Location Confirmation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPStaff_Reserv_Page"),"STAFF A RESERVATION");
		
		//click Show Summary link and Verify
		COMMON_METHODS.clickElement(getTestObject("PPShowSummary"));
		boolean Hide = COMMON_METHODS.isElementPresent("HIDE SUMMARY", "linkText");
		if (Hide = true)
		{
				REPORTER.LogEvent(TestStatus.PASS, "Verify user can view summary", "user can view summary -- Successfully" , "");
		}
		else
		{	
				REPORTER.LogEvent(TestStatus.FAIL, "Verify user can view summary", "user can not view summary" , "");				
		}
		
		// click Map Location link -- verify Pop up and Close that pop up
		COMMON_METHODS.clickElement(getTestObject("PPMap_Location"));
		Thread.sleep(2000);
		COMMON_METHODS.switchToPopup();
		COMMON_METHODS.isElementPresent("Map Location", "id");
		REPORTER.LogEvent(TestStatus.PASS, "Verifying Map Location Popup", "Map Location Popup -- Verified Successfully" , "");
		COMMON_METHODS.clickElement(getTestObject("PPClose_PopUp"));;
		
		// Logout from Employee Portal	Loc_Confirmation
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	
	
	
	private void loginPPPortal() throws IOException, Exception { 
        
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login - Back-up Care Advantage Provider Portal")){ 
                //Verify Login Page displayed 
                
                //Login to Emp Portal 
                String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")}; 
                Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"),signInArray);
                //Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"resstep3"+"."+"userName"), getTestData("TD_PWD"),signInArray); 
                /*businessComponents.LoginEmployeeportal( 
                                ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"userName"), 
                                getTestData("TD_PWD"));*/ 
        } 
	} 
	
}
