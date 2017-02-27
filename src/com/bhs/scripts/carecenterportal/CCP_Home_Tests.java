package com.bhs.scripts.carecenterportal;

import org.testng.annotations.Test;

import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.Utility;

public class CCP_Home_Tests extends INITIALIZE{

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	CCP_BusinessComponents CCbusinessComponents = new CCP_BusinessComponents();

	//Reading Test Objects from Data excel 
	/*static{
		try{
			readTestObject(getDataTablePath("CCP"), "TO_CCP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 31/03/2014
	 * Test Case #12621 - BUCA - CCP - Login - Ensure Footer Links function when not logged in
	 * Test Case #12626 - BUCA - CCP - Login - Ensure the user can log out of the portal
	 * Test Case #12724 - BUCA - CCP - Ensure the Backup Care Logo navigation works
	 */
	@Test()
	public void CCP_FooterLinks() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");
				
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		// Click Privacy Policy link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_30"));
		
		// Verify the Privacy Policy page is displayed
		if(COMMON_METHODS.driver.getTitle().equals("PrivacyPolicy - Back-Up Care Center")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Privacy Policy' page is displayed.", "'Privacy Policy' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Privacy Policy' page is displayed.", "'Privacy Policy' page is not displayed.", "");
		}
				
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		// Click Terms of Use link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_31"));
		
		// Verify the Terms of Use page is displayed
		if(COMMON_METHODS.driver.getTitle().equals("Terms Of Use - Back-Up Care Center")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Terms of Use' page is displayed.", "'Terms of Use' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Terms of Use' page is displayed.", "'Terms of Use' page is not displayed.", "");
		}
					
		// Click Trademark Notice link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_32"));
		
		// Verify the Trademark page is displayed
		if(COMMON_METHODS.driver.getTitle().equals("Trademark - Back-Up Care Center")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Trademark' page is displayed.", "'Trademark' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Trademark' page is displayed.", "'Trademark' page is not displayed.", "");
		}

		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		
		// Click BH logo
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_29"));

		if(COMMON_METHODS.driver.getTitle().equals("Welcome - Back-Up Care Center")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Care Center Portal' home page is displayed.", "'Care Center Portal' home page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Care Center Portal' home page is displayed.", "'Care Center Portal' home page is not displayed.", "");
		}		
		
		// Logout from Care Center Portal
		Utility.logout();
		
		// Verify login screen is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("CCP_OL_1"));
		
		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	/*
	 * Rolled up with TFS #12621
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 28/03/2014
	 
	 
	//Test Case #12626 - BUCA - CCP - Login - Ensure the user can log out of the portal

	@Test()
	public void CCP_PortalLogout() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("CCP"), "TD_CCP");
		
		// Launch the browser
		CCP_BusinessComponents.CCP_LaunchBrowser();
		
		// Login CareCenter Portal
		CCP_BusinessComponents.CCP_Login();
		
		
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		// Logout from Care Center Portal
		Utility.logout();
		
		// Verify login screen is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("CCP_OL_1"));

		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	 * Rolled up with TFS #12621
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 28/03/2014
	 
	//Test Case #12724 - BUCA - CCP - Ensure the Backup Care Logo navigation works

	@Test()
	public void CCP_Logo() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("CCP"), "TD_CCP");
		
		// Launch the browser
		CCP_BusinessComponents.CCP_LaunchBrowser();
		
		// Login CareCenter Portal
		CCP_BusinessComponents.CCP_Login();
		
		
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		
		// Click logo
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_29"));

		if(COMMON_METHODS.driver.getTitle().equals("Welcome - Back-Up Care Center")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Care Center Portal' home page is displayed.", "'Care Center Portal' home page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Care Center Portal' home page is displayed.", "'Care Center Portal' home page is not displayed.", "");
		}
		
		// Logout from Care Center Portal
		Utility.logout();
		
		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}*/
	
	
}
