package com.bhs.scripts.providerportal;

import java.io.IOException;

import org.testng.annotations.Test;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.Utility;

public class PP_AccountManagement extends INITIALIZE
{

	
	/*//Reading Test Objects from Data excel 
	static{
		try{
			readTestObject(getDataTablePath("PP"), "TO_PP");
			//readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/

	/**
	 * This test script covers functionality of BUCA - Provider - Account Management - user is able to edit an exclusion
	 * TFS ID : 19150
	 * This test case is mapped to TFS ID: 21887 
	 * 
	 * 
	 * This test script covers functionality of "BUCA - Provider Portal - 1.6.5. Exclusions: Submit/Cancel Request -
     * Verify the Submit/Cancel a Exclusion Request in the BUCA web application "
	 * TFS ID : 21888
	 * 
	 * 
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
	public void PP_EditExclusion() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data
		//readTestData(getDataTablePath("PP"), "TD_PP");
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);

		
		//Navigate to Account Management
		//COMMON_METHODS.navigateToMenu(getTestObject("PPAM_01"));
		//Navigate to Account Management
		COMMON_METHODS.clickElement(getTestObject("PPAcc_Mgmt"));
		Thread.sleep(10000);
		//Click on Exclusion
		COMMON_METHODS.clickElement(getTestObject("PPAM_34"));
		
		//Click on Add an Exclusion
		COMMON_METHODS.clickElement(getTestObject("PPAM_35"));
		
		COMMON_METHODS.listBoxSelect(getTestObject("PPAM_36"), "#1", "byIndex");
		
		COMMON_METHODS.listBoxSelect(getTestObject("PPAM_37"), "#1", "byIndex");
		
		COMMON_METHODS.listBoxSelect(getTestObject("PPAM_38"), "#1", "byIndex");
		
		COMMON_METHODS.editAField(getTestObject("PPAM_39"), "New Exclusion");
		
		COMMON_METHODS.radioButton(getTestObject("PPAM_40"));
		
		COMMON_METHODS.clickElement(getTestObject("PPAM_41"));
		
		//String msg=COMMON_METHODS.getText(getTestObject("PPAM_42"));
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPAM_42"), "Exclusion was successfully addded.");
		
		//Click on Exclusion
		COMMON_METHODS.clickElement(getTestObject("PPAM_43"));
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	
	 
	
	
	/**
	 * TFS ID : 21886:This test script covers functionality of BUCA - Provider Portal - Provider Profile - 
	 * Verify that the BUCA web application displays the Tax ID on the Provider Profile from CRM. 
	 * 
	 * TFS ID:22533:Verify blank tax ID field when the field is blank in CRM.
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/** 
	 * @author Supraja
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 04/04/2014
	 */
	
	@Test
	public void PP_AM_ProviderProfile_VerifyTaxID() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data
		//readTestData(getDataTablePath("PP"), "TD_PP");
		
		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/

		this.loginPPPortal();
		
		//Navigate to Account Management
		COMMON_METHODS.clickElement(getTestObject("PPAM_01"));
		Thread.sleep(10000);
		//Click on 'Provider Profile'
		//COMMON_METHODS.clickElement(getTestObject("SP_01"));
		
		//Verify Provider Profile section should be displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPSP_02"), "Provider Profile");

		//Check for the Tax ID in BASIC INFORMATION 
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPSP_03"), "Basic Information");
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPSP_04"), "Tax ID:");
		
		
		//BUCA web application should display tax ID on corporate provider portal/web app when it is entered in CRM at the corporate level. 
		// Verify application displays a blank tax ID field when the field is blank in CRM.
		// This code has dependency on CRM
		
		
		// Logout from 'Employee Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	
	/**
	 * This test script covers functionality of "BUCA - Provider Portal - 1.6.5. Exclusions: Submit/Cancel Request -
     * Verify the Submit/Cancel a Exclusion Request in the BUCA web application "

	 * TFS ID : 21888
	 * @param TC
	 * @throws Exception
	 */
	/** This Test case is executed in UAT
	 * @author Supraja
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 04/04/2014
	 */
	
	/*@Test
	public void PP_AM_SubmitCancelExclusion() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data
		//readTestData(getDataTablePath("PP"), "TO_PP");
		//readTestData(getDataTablePath("PP"), "TD_PP");
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);
		
		this.loginPPPortal();

		
		//Navigate to Account Management
		COMMON_METHODS.clickElement(getTestObject("PPAcc_Mgmt"));
		
			//Click on Exclusion
			COMMON_METHODS.clickElement(getTestObject("PPAM_34"));
			
			//Click on Add an Exclusion
			COMMON_METHODS.clickElement(getTestObject("PPAM_35"));
			
			//COMMON_METHODS.listBoxSelect(getTestObject("PPAM_36"), "2", "byIndex");
			COMMON_METHODS.listBoxSelect(getTestObject("PPAM_36"), "All Caregivers", "byVisibleText");
			
			//COMMON_METHODS.listBoxSelect(getTestObject("PPAM_37"), "2", "byIndex");
			COMMON_METHODS.listBoxSelect(getTestObject("PPAM_37"), "kiran madasu", "byVisibleText");
			
			COMMON_METHODS.listBoxSelect(getTestObject("PPAM_38"), "Service Issues", "byVisibleText");
			
			COMMON_METHODS.editAField(getTestObject("PPAM_39"), "New Exclusion");
			
			COMMON_METHODS.radioButton(getTestObject("PPAM_40"));
			
			COMMON_METHODS.clickElement(getTestObject("PPSP_05"));
			
			COMMON_METHODS.clickElement(getTestObject("PPAM_41"));
			
			//String msg=COMMON_METHODS.getText(getTestObject("PPAM_42"));
			
			COMMON_METHODS.VerifyTextPresent(getTestObject("PPAM_42"), "Exclusion was successfully addded.");
			
			//Logout from 'Provider Portal'
			Utility.logout();

			//Log to reports
			COMMON_METHODS.logToReportAfterPass(methodName); 		

		}
	*/
	

	/**
	 * Test Case #23751: 
	 * CSC BUCA - PP- Account Management-Licensing and Documents: Verify error message when user not chosen a document type or file for upload
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/** 
	 * @author Lava
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 04/04/2014
	 */
	
	@Test
	public void PP_AM_LicAndDoc_ErrorMessageValidation() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data
		readTestData(getDataTablePath("PP"), "TD_PP");
		
		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		
		this.loginPPPortal();
		
		//Navigate to Account Management
		COMMON_METHODS.navigateToMenu(getTestObject("PPAM_01"));
		
		//Click on Licensing And Documents
		COMMON_METHODS.clickElement(getTestObject("PPAM_50"));
		
		//Click on Add or Update Document
		COMMON_METHODS.clickElement(getTestObject("PPAM_52"));
		
		
		// Verifying Document Type List Box present
		boolean temp = COMMON_METHODS.isElementPresent(getTestObject("PPAM_53"), "id");
		if (temp)
		{
			REPORTER.LogEvent(TestStatus.PASS, "Document List Box Present", "Document List Box -- Succeeds" , "");
		}
		else
		{	
			REPORTER.LogEvent(TestStatus.FAIL, "Document List Box Not Present", "Document List Box -- Failed", "");				
		}	

		// Verifying Upload Document button is present
		boolean temp1 = COMMON_METHODS.isElementPresent(getTestObject("PPAM_54"), "id");
		if (temp1)
		{
			REPORTER.LogEvent(TestStatus.PASS, "Upload Document Button present", "Upload Document Button present -- Succeeds" , "");
		}
		else
		{	
			REPORTER.LogEvent(TestStatus.FAIL, "Upload Document Button Not present", "Upload Document Button not present -- Failed", "");				
		}
		
		// Verifying Change Request Text Box
		boolean temp2 = COMMON_METHODS.isElementPresent(getTestObject("PPAM_54"), "id");
		if (temp2)
		{
			REPORTER.LogEvent(TestStatus.PASS, "Change Request Text Box present", "Change Request Text Box present -- Succeeds" , "");
		}
		else
		{	
			REPORTER.LogEvent(TestStatus.FAIL, "Change Request Text Box Not present", "Change Request Text Box not present -- Failed", "");				
		}
		
		
		//Click on Submit Request Button
		COMMON_METHODS.clickElement(getTestObject("PPAM_56"));
		
		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPAM_56"), "Please select a document type");
				
		
	}
	
	
	/**
	 * Test Case #24526: 
	 * CSC BUCA - Provider Portal - Upload Service Areas: Submit Service Areas - Verify that the application should allow users who have the feature access of "Service Areas" established within their contact profile to add/edit service areas
	 * Test Case #24527: 
	 * CSC BUCA-Provider Portal-Account Management-Service Area-Verify the error message when user clicks Submit button when user has not chosen a file for upload
	 * 
	 */
	/** 
	 * @author Lava
	 * @CreationDate: 04/04/2014
	 */
	
	@Test
	public void PP_AM_ServiceAreas_AddorEditSAandErrMsgValidation() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data
		readTestData(getDataTablePath("PP"), "TD_PP");
		
		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));

		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		
		this.loginPPPortal();
		
		//Navigate to Account Management
		COMMON_METHODS.navigateToMenu(getTestObject("PPAM_01"));
		
		//Click on Service Areas link
		COMMON_METHODS.clickElement(getTestObject("PPAM_51"));
		
		//Click on Add service Areas Button
		COMMON_METHODS.clickElement(getTestObject("PPAM_52"));

		// Verifying Service Areas Text Box
				boolean temp1 = COMMON_METHODS.isElementPresent(getTestObject("PPAM_58"), "id");
				if (temp1)
				{
					REPORTER.LogEvent(TestStatus.PASS, "Service Areas Text Box present", "Service Areas Text Box present -- Succeeds" , "");
				}
				else
				{	
					REPORTER.LogEvent(TestStatus.FAIL, "Service Areas Text Box Not present", "Service Areas Text Box not present -- Failed", "");				
				}
				
				// Verifying Request New Service Areas button is present
				boolean temp2= COMMON_METHODS.isElementPresent(getTestObject("PPAM_59"), "id");
				if (temp2)
				{
					REPORTER.LogEvent(TestStatus.PASS, "Request New Service Areas Button present", "Request New Service Areas Button present -- Succeeds" , "");
				}
				else
				{	
					REPORTER.LogEvent(TestStatus.FAIL, "Request New Service Areas Button Not present", "Request New Service Areas Button not present -- Failed", "");				
				}
				
				
		//Click on Request New Service Areas Button
		COMMON_METHODS.clickElement(getTestObject("PPAM59"));
		
		//verify the error message for the service areas
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPAM60"), "Please select a document");
				
		Utility.logout();
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
	
	
	
	
	
	
	
	


