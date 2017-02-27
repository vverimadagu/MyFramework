/*package com.bhs.scripts.archive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.INITIALIZE.TestStatus;

public class EP_Registration_CareRecepients_Tests extends INITIALIZE {
	
	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	
	//Reading Test Objects from Data excel 
	static{
		try{
			readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	

	*//**
	 * Care Recipients - user is able to add new care recipient using required for save fields
	 *  TFS ID : 16222
	 *  This Test case is rolled up with EP_Add_Update_New_Care_Recepient
	 *//*
	@Test(priority=1)
	public void EP_Add_New_Care_Recepient(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		// Launch browser
		businessComponents.EP_LaunchBrowser(TC);

		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				.getProperty("client5.userName"), getTestData("TD_PWD"));

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on Add New care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_64"));
		
		//Fill all the req fields for new Care recepient
		businessComponents.EP_AddCareRecipients();
		
		//Logout from the Employee portal
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	}

	*//**
	 * BUCA - Care Recipients - user is able to set a care recipient to inactive
	 *  TFS ID : 18557
	 *//*
	@Test(priority=2)
	public void EP_Care_Recepient_Inactive() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		// Launch browser
		businessComponents.EP_LaunchBrowser();

		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				.getProperty("client5.userName"), getTestData("TD_PWD"));

		String addCrData3_5Years[] = {
				getTestData("TD_CR_FirstName1"),
				getTestData("TD_CR_LastName1"), "Son",
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

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		Thread.sleep(5000);
		businessComponents.addCareRecipients(addCrData3_5Years,"Yes");

		//Calling a Add a new care recepient method
		//businessComponents.Inactive_Care_Recepients();

		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_71"));

		//Click on Inactive Radio button
		COMMON_METHODS.radioButton(getTestObject("MA_66"));

		//Input an text for Inactive Care Recepient
		COMMON_METHODS.editAField(getTestObject("MA_67"), "Test");

		//Click on Update care Recepient
		COMMON_METHODS.clickElement(getTestObject("MA_68"));
		
		//Verify the Inactive Care Recipient is hidden 
		
		try {
			   COMMON_METHODS.driver.findElement(By.linkText("TD_CR_FirstName1"+" "+"TD_CR_LastName1"));
			   REPORTER.LogEvent(TestStatus.FAIL, "Verify authorized contact is hidden from the UI ", "Authorized contact is not hidden from the UI " , "");
			  } catch (Exception e) {

			   REPORTER.LogEvent(TestStatus.PASS, "Ensure authorized contact is hidden from the UI ", "Authorized contact is hidden from the UI successfully" , "");
			  }

		
		//Logout from the Employee portal
		businessComponents.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	*//**
	 * Care Recipients - Selecting a Care Recipient displays their profile
	 *  TFS ID : 4126
	 *//*
	@Test(priority=3)
	public void EP_Care_Recepient_Display_Profile() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		// Launch browser
		businessComponents.EP_LaunchBrowser();

		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				.getProperty("client5.userName"), getTestData("TD_PWD"));

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_65"));
		
		businessComponents.Care_Profile_Verify();
		
		//Logout from the Employee portal
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
			
	}	
	
	*//**
	 * Care Recipients - system displays error message if there are missing or invalid fields
	 *  TFS ID : 3787
	 *//*
	@Test(priority=4)	
	public void EP_Add_New_Care_Recepient_Error(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		// Launch browser
		businessComponents.EP_LaunchBrowser(TC);

		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				.getProperty("client5.userName"), getTestData("TD_PWD"));

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on Add New care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_64"));
		
		//Click on Add Care recepient
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Verify the Text in the error message
		businessComponents.verifyElementDisplayed(getTestObject("MA_69"));
				
		//Logout from the Employee portal
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	}

	*//**
	 * Care Recipients - User is able to update a Care Recipient profile
	 *  TFS ID : 4127
	 *  This Test case is rolled up to EP_Add_Update_New_Care_Recepient method
	 *//*
	@Test(priority=5)
	public void EP_Update_Care_Recepient(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP5");

		// Launch browser
		businessComponents.EP_LaunchBrowser(TC);

		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				.getProperty("client5.userName"), getTestData("TD_PWD"));

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
		
					
		//Logout from the Employee portal
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	}
	
	*//**
	 * TFS ID:3784:BUCA - Care Recipients - user is able and update to add new care recipients
	 * TFS ID:4127:Care Recipients - User is able to update a Care Recipient profile
	 * TFS ID:16222:Care Recipients - user is able to add new care recipient using required for save fields
	 *//*
	@Test(priority=6)
	public void EP_Add_Update_New_Care_Recepient(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");

		// Launch browser
		businessComponents.EP_LaunchBrowser(TC);

		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
				.getProperty("client5.userName"), getTestData("TD_PWD"));

		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on Add New care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_64"));
		
		//Fill all the req fields for new Care recepient
		businessComponents.EP_AddCareRecipients();
		
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
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	
	}
	
	*//**
	 * This test script covers functionality of BUCA - My Care Profile - Care Recipients - 
	 * system displays error message if there are missing or invalid fields
	 * Requirement ID : 13800
	 * @param TC
	 * @throws Exception
	 *//*
	@Test(priority=7)
	public void EP_SignUp_CareRecipients_Error() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 5
		readTestData(getDataTablePath("EP"), "TD_EP5");

		//Launch Browser
		businessComponents.EP_LaunchBrowser();

		//Enter Employee details for verification
		businessComponents.EP_verifyEmployer();
		
		//Accept the policy and click on Submit button
		businessComponents.EP_AcceptPolicyAndSubmit();
				
		businessComponents.EP_SignUpUser(102);
		
		COMMON_METHODS.clickElement(getTestObject("SOL_02"));
		
		//Update the Employee profile
		businessComponents.EP_UpdateEmployeeprofile();
						
		//Click on Add New care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_64"));
				
		//Click on Continue button without filling any information 
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Verify the Text in the error message for missing fields
		businessComponents.verifyElementDisplayed(getTestObject("MA_69"));
		
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
		businessComponents.verifyElementDisplayed(getTestObject("MA_69"));
		
		//Logout from Emp portal
		businessComponents.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

}
*/