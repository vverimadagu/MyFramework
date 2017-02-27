/*package com.bhs.scripts.archive;

import org.testng.annotations.Test;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;


public class EP_CopayClient9_Tests extends INITIALIZE {

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
	* @author Supraja
	* @version 
	* @return 
	* @param 
	* @CreationDate: 20/03/2014
	*//* 
	
	@Test(priority=1)
	public void EP_CopaySignupUser() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP9");

		businessComponents.EP_LaunchBrowser();
		
		businessComponents.EP_verifyEmployer();
		
		businessComponents.EP_AcceptPolicyAndSubmit();

		//Sign Up
		businessComponents.EP_SignUpUser(89);
		
		//Click 'Care Profile' link from top navigation menu
		businessComponents.EP_ClickCareProfileLink();
		
		//Click on Employee Profile icon
		COMMON_METHODS.clickElement(getTestObject("OL_134"));
		
		businessComponents.EP_UpdateEmployeeprofile();
		Thread.sleep(2000);
		//User will be navigated to the Care Profile page with Green check mark on the employee profile icon
		//businessComponents.verifyElementDisplayed(getTestObject("OL_138"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		businessComponents.EP_AddCareRecipients();
		
		//User will be navigated to the Care Profile page with Green check mark on the care recipients icon
		businessComponents.verifyElementDisplayed(getTestObject("OL_139"));
		
		//Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));
		
		businessComponents.EP_AddAuthorizedContacts();
		
			
	}
	
	*//**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 9 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 *//*

	@Test(priority=2)
	public void EP_CopayRule_Client9_CenterBasedOneCRHealthy() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP9");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	*//**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 9 - Verify Copay for InHome reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 *//*

	@Test(priority=2)
	public void EP_CopayRule_Client9_InHomeHealthyOneCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP9");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	*//**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 9 - Verify Copay for In-Home reservation for Three CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 12:00 PM"
	 * 
	 *//*

	@Test(priority=4)
	public void EP_CopayRule_Client9_3CRInHome_Lessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP9");
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,3,"Healthy",dateOfReservation,"10:00 AM","12:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$50.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
}*/