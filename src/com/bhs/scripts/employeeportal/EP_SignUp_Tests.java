package com.bhs.scripts.employeeportal;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_SignUp_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents  = new EP_BusinessComponents();
	String employer=null; 
	
	/**
	 * This test script covers functionality of BUCA web application user registration/sign-up process should display 
	 * the Employer Sign up page
	 * 
	 * Requirement ID : 21284
	 * @param TC
	 * @throws Exception
	 */
	@Test()
	public void EP_SignUpPageTest() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//Click 'Sign Up' link 
		COMMON_METHODS.clickElement(getTestObject("OL_12"));

		// Verifying Employer Sign-Up page should display all the  fields like Employer ID, Employer Password, Verify Employer button,  and a message ‘Already Registered’  with a link ‘SIGN IN’           
		businessComponents.EP_SignUpPage();
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of BUCA web application should verify the employer by entering employer's 
	 * unique ID and password  
	 * 
	 * Requirement ID : 21284
	 * @param TC
	 * @throws Exception
	 */
	@Test @Parameters("client") 
	public void EP_SignUpPolicyPageTest(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.employer = employer;
		
		//Navigate to Landing page
		COMMON_METHODS.openBrowser(getTestData("TD_URL")+"Account/Login?ReturnUrl=%2f");
		
		//Enter Valid Client ID and Password and verify employer
		businessComponents.EP_verifyEmployer(employer);

		// Verifying Employer information should be verified and BUCA web application should display the 
		// Participation Agreement and Acceptable Use Policy page  
		businessComponents.EP_SignUpAcceptableUsePolicyPage();

		//Accept Policy
		businessComponents.EP_AcceptPolicyAndSubmit(); 

		//businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	/**
	 * This test script covers functionality of Verify if  BUCA web application user verifies the employee as eligible. 
	 * 	 * 
	 * Requirement ID : 21284
	 * @param TC
	 * @throws Exception
	 */
	@Test @Parameters("client")
	public void EP_SignUpUserUpdateEmpProfile(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.employer = employer;
		
		//Launch Browser
		//businessComponents.EP_LaunchBrowser(TC);

		//businessComponents.EP_verifyEmployer();
		//businessComponents.EP_SignUpUser(1);

		//businessComponents.EP_AcceptPolicyAndSubmit();

		//Create dynamic user name
		String userName = createDyanamicUserData();
		// Register a New User
		businessComponents.EP_SignUpUser(userName,employer,"signup");

		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));

		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));

		//Update employee profile
		businessComponents.EP_UpdateEmployeeprofile(employer);

		Utility.logout();
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of BUCA web application user accepts the policy in order to continue. 
	 * 	 * 
	 * Requirement ID : 21284
	 * @param TC
	 * @throws Exception
	 */
	@Test @Parameters("client")
	public void EP_SignUpUserSubmitPolicyTest(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.employer = employer;
		
		// Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		//businessComponents.EP_SignUpUser(1);
		businessComponents.EP_verifyEmployer(employer);

		businessComponents.EP_AcceptPolicyAndSubmit();

		//businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	

	/**
	 * This test script covers functionality of Verify if  BUCA web application user update employee. 
	 * 	 * 
	 * Requirement ID : 21284
	 * @param TC
	 * @throws Exception
	 */
	@Test @Parameters("client")
	public void EP_UpdateEmpProfile(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.employer = employer;
		
		//Launch Browser
		//businessComponents.EP_LaunchBrowser(TC);

		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));

		//Click on employee name link under Employee Profile located right side of the Home page
		COMMON_METHODS.clickElement(getTestObject("OL_EmpName"));

		businessComponents.EP_UpdateEmployeeprofile(employer);

		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(ReadwritDataFromProps.props.getProperty("client1.firstName")+" "
					+ReadwritDataFromProps.props.getProperty("client1.lastName"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employee Profile created", "Employee Profile - " + sTemp + " Created" , "");

			String sTemp1 = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_Location"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employee Location created", "Employee Location - " + sTemp1 + " Created" , "");

		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Employee Profile / Location created");
		}

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}


	/**
	 * Test Case #23358: 
	 * BUCA - Automation - Sign In - Successful log in
	 *
	 */
	@Test()
	public void EP_SignInSuccessfullogin() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		

		/*//Launch Browser
		businessComponents.EP_LaunchBrowser(TC);

		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));*/

		String Str = ReadwritDataFromProps.props.getProperty("client1.firstName") +" "+ ReadwritDataFromProps.props.getProperty("client1.lastName");

		//Verify Employee profile Text.
		COMMON_METHODS.VerifyTextPresent(getTestObject("SOL_01"), "Employee Profile");

		//Verify User was able to successfully login and is now logged in.
		COMMON_METHODS.VerifyTextPresent(getTestObject("SOL_02"), Str);
		
		//Logout from EP
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}


	/**
	 * Test Case #23356: 
	 * BUCA - Automation - Sign Up - Ensure user must accept agreement policy to proceed in order to continue
	 *
	 */
	@Test @Parameters("client")
	public void EP_SignUpAgreementPolicy(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.employer = employer;

		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");

		// Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//businessComponents.EP_SignUpUser(1);
		businessComponents.EP_verifyEmployer(employer);
		
		businessComponents.EP_AcceptPolicyAndSubmit();

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);	
	}

	/**
	 * Test Case #23355: 
	 * BUCA - Automation - Sign Up - Successful Sign Up
	 *
	 */
	@Test @Parameters("client")
	public void EP_SignUpSuccessfulSignUp(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		this.employer = employer;
		
		//Create dynamic user name
		String userName = createDyanamicUserData();
		// Register a New User
		businessComponents.EP_SignUpUser(userName,employer,"");

		String Str = ReadwritDataFromProps.props.getProperty("client102.firstName") +" "+ ReadwritDataFromProps.props.getProperty("client102.lastName");

		System.out.println("The Logged in user First Name and Last Name " + Str);
		//Verify Employee profile Text.
		COMMON_METHODS.VerifyTextPresent(getTestObject("SOL_01"), "Employee Profile");

		//Verify User was able to successfully login and is now logged in.
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_EmpName"), Str);

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	/**
	 *   BUCA - Sign Up - ensure user is able to add to benefit 
	 * 	  
	 * Requirement ID : 15957
	 * @Autor: Sanjeev Singh
	 * @since 04/04/2014
	 * @throws Exception 
	 * 
	 */	
	
	@Test
	public void EP_AddBenefit () throws Exception	
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		/*// Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));*/
		
		//Click 'Add Benefit' Link
		COMMON_METHODS.clickElement(getTestObject("Add_Benefit"));
		
		COMMON_METHODS.editAField(getTestObject("OL_1"), getTestData("TD_Username"));

		//Enter Password
		COMMON_METHODS.editPasswordField(getTestObject("OL_2"), getTestData("TD_EmployerPassword"));
		
		COMMON_METHODS.driver.findElement(By.xpath("//input[@value='Add Benefit']")).click();
		REPORTER.LogEvent(TestStatus.PASS, "Add Benefit link ", "Add Benefit link clicked successfully", "");
		Thread.sleep(4000);
		
		//login employer portal	
		//businessComponents.LoginEmployeeportal(getTestData("TD_EmployerId"), getTestData("TD_EmployerPassword"));
		COMMON_METHODS.editAField(getTestObject("OL_13"),"TCSIX" );//getTestData("TD_EmployerId"));

		//Enter Password
		COMMON_METHODS.editPasswordField(getTestObject("OL_14"), getTestData("TD_EmployerPassword"));
		
		COMMON_METHODS.driver.findElement(By.xpath("//input[@value='submit']")).click();
		REPORTER.LogEvent(TestStatus.PASS, "submit Button ", "submit Button clicked successfully", "");
		
		/*String str = getTestData("TD_Username")+" "+getTestData("TD_Username")+" ";
		String strUserBenefit =str+getTestData("TD_user_Benefit");
		System.out.println(strUserBenefit);
		if (COMMON_METHODS.driver.findElement(By.linkText(strUserBenefit)).isDisplayed())
			REPORTER.LogEvent(TestStatus.PASS, "Benefit creation", "Benefit created - " + strUserBenefit, "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Benefit creation", "Benefit Added Before Only - " + strUserBenefit, "");*/
		// Verifying data saved
		//COMMON_METHODS.VerifyTextPresent(getTestObject("Help_pg"),"Care Profile");	TD_user_Benefit
		
		// Change Benefit	
		COMMON_METHODS.driver.findElement(By.xpath("//*[@id='loginHeader']/ul/li[2]/a[1]")).click();
		
		COMMON_METHODS.clickElement(getTestObject("Next_Benefit"));
		REPORTER.LogEvent(TestStatus.PASS, "Next Benefit", "Next Benefit got selected successfully", "");
		
		// Logouts and Log Report
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

}
