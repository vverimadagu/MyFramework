/*package com.bhs.scripts.archive;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.INITIALIZE.TestStatus;

public class EP_CopayClient3_Tests extends INITIALIZE {

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
	* @author Satya
	* @version 
	* @return 
	* @param 
	* @CreationDate: 04/03/2014
	*//* 
	
	@Test(priority=1)
	public void EP_CopaySignupUser() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP3");

		businessComponents.EP_LaunchBrowser();
		
		businessComponents.EP_verifyEmployer();
		
		businessComponents.EP_AcceptPolicyAndSubmit();

		//Sign Up
		businessComponents.EP_SignUpUser(81);
		
		//Click 'Care Profile' link from top navigation menu
		businessComponents.EP_ClickCareProfileLink();
		
		//Click on Employee Profile icon
		COMMON_METHODS.clickElement(getTestObject("OL_134"));
		
		businessComponents.EP_UpdateEmployeeprofile();
		
		//User will be navigated to the Care Profile page with Green check mark on the employee profile icon
		businessComponents.verifyElementDisplayed(getTestObject("OL_138"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		businessComponents.EP_AddCareRecipients();
		
		//User will be navigated to the Care Profile page with Green check mark on the care recipients icon
		businessComponents.verifyElementDisplayed(getTestObject("OL_139"));
		
		//Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));
		
		businessComponents.EP_AddAuthorizedContacts();
		
		//this.EP_reservation();
		
		//businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	*//**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 *//*

	@Test(priority=2)
	public void EP_CopayRule_CareReceipents1() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$14.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	*//**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Mildly ILL Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 *//*

	@Test(priority=3)
	public void EP_CopayRule_CareRecepients1_MidlyIll() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Mildly Ill",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$14.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	*//**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR more than 18 years, Mildly ILL Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 *//*

	@Test(priority=3)
	public void EP_CopayRule_CRMoreThan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$6.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	*//**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR more than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 *//*

	@Test(priority=3)
	public void EP_CopayRule_Client3_HealthyTwoCR_3hours() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		businessComponents.EP_LaunchBrowser();
		
		 //login employee portal
		 businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client80.userName"), getTestData("TD_PWD"));
	 
		addCareReceipt();
		
		addCareReceipt();
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes");
	
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(3,3,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$6.00"); 
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	public void addCareReceipt() throws IOException, Exception
	{
		//Click 'Care Profile' link from top navigation menu
				businessComponents.EP_ClickCareProfileLink();
				
				 //Click 'Add' link present in 'Care Recipients' section
				COMMON_METHODS.clickElement(getTestObject("OL_41"));
				
				
				String addCrData3_5Years[] = {
						
						//First name
						getTestData("TD_COPAY_CR_FIRSTNAME"),
						
						//Last name
						getTestData("TD_COPAY_CR_LASTNAME"), 
						
						//Relationship to Client Employee*
						"Daughter",
						
						// DOB
						getTestData("TD_COPAY_CR_DOB"),
						
						//Age
						getTestObject("OL_46"), 
						
						//Health Information and Restrictions
						getTestObject("OL_47"),
						getTestObject("OL_48"), 
						getTestObject("OL_49"),
						getTestObject("OL_50"), 
						getTestObject("OL_51"),
						getTestObject("OL_52"),
						
						//Other Helpful Information
						getTestData("TD_AddInfo"),
						
						//Legal Guardian?*  
						getTestObject("OL_54"), 
						//Custody Issues/ Visitation Orders** 
						getTestObject("OL_55"),
						
						getTestData("TD_COPAY_STATE"),
						getTestData("TD_AddInfo"),
						getTestObject("OL_100"), null,
						getTestData("TDU1_CENTERLOCATION1"),
						null, null };

				// Click 'Add' link present in 'Care Recipients' section
				//COMMON_METHODS.clickElement(getTestObject("OL_41"));
				businessComponents.addCareRecipients(addCrData3_5Years,"No");
				
				// Click 'Add Care Recipients' button
				COMMON_METHODS.clickElement(getTestObject("OL_57"));
				
				
				// Verify the newly added Care Recipient is present under
				// 'Care Recipients' section
				try {
					String sTemp = BH_SetUp_TearDown.driver.findElement(
							By.linkText(getTestData("TD_COPAY_CR_FIRSTNAME")
									+ " " + getTestData("TD_COPAY_CR_LASTNAME")))
							.getText().trim();
					REPORTER.LogEvent(TestStatus.PASS,
							"Verify Care Recipient created",
							"Care Recipient - " + sTemp + " Created", "");
				} catch (Exception e) {
					REPORTER.catchException(e,
							"Verify Care Recipient created");
				} 
	}
	
	
}*/