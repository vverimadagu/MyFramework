/*package com.bhs.scripts.archive;


import org.testng.annotations.Test;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;


public class EP_CopayClient2_Tests extends INITIALIZE {

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
		readTestData(getDataTablePath("EP"), "TD_EP2");

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
		
			
	}
	
	*//**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 *//*

	@Test(priority=2)
	public void EP_CopayRule_Client2_CareReceipents1() throws Exception {
		
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
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 *//*

	@Test(priority=3)
	public void EP_CopayRule_3CRHealthyLessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,3,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
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

	@Test(priority=4)
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

	@Test(priority=5)
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
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 *//*

	@Test(priority=6)
	public void EP_CopayRule_Client2_InHomeHealthyOneCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$28.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	*//**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 *//*

	@Test(priority=7)
	public void EP_CopayRule_Client2_InHomeHealthyOneCR_3hours() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$12.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	*//**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 *//*

	@Test(priority=8)
	public void EP_CopayRule_Client2_3CRInHome_Lessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$12.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	*//**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation and Center based reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 *//*

	@Test(priority=9)
	public void EP_CopayRule_Client2_InHomeandCenterBasedHealthyOneCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on clientNo
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation2 = getTestData("TD_COPAYDOR2");
				
		businessComponents.EP_CoPayInHomeReservation(2,1,"Healthy",dateOfReservation2,"10:00 AM","5:00 PM");
		
		//businessComponents.EP_CoPayPaymentInformation("$28.00");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$28.00");
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
}*/