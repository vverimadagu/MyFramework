package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_Copay_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();

	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 1 - Verify Copay for Center Based reservation
	 * 
	 */

	@Test(priority=1)
	public void EP_CopayRule_Client1_CareReceipents1() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//signup user
		this.EP_CopaySignupUser("1","copay1");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$70.00");
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Mildly ILL Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=2)
	public void EP_CopayRule_Client1_CareRecepients1_MidlyIll() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		//this.EP_CopaySignupUser(90);
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		this.loginClient("Client1", "copay1");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR2");
		
		businessComponents.EP_CoPayReservation(2,1,"Mildly Ill",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$14.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR more than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=3)
	public void EP_CopayRule_Client1_HealthyOneCR_3hours() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		//this.EP_CopaySignupUser(90);
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		this.loginClient("Client1", "copay1");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR3");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$6.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR more than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=4)
	public void EP_CopayRule_Client1_Healthy_3CRs() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		/*businessComponents.EP_LaunchBrowser();
		
		 //login employee portal
		 businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client80.userName"), getTestData("TD_PWD"));
*/	 
		//signup user
		//this.EP_CopaySignupUser(90);
		
		this.loginClient("Client1", "copay1");
		
		addCareReceipt("Client1");
		
		addCareReceipt("Client1");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client1");
	
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR4");
		
		businessComponents.EP_CoPayReservation(1,3,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$6.00"); 
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=5)
	public void EP_CopayRule_Client2_CareReceipents1() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		this.EP_CopaySignupUser("2","copay2");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$14.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=6)
	public void EP_CopayRule_Client2_3CRHealthyLessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		addCareReceipt("Client2");
		
		addCareReceipt("Client2");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client2");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,3,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$14.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Mildly ILL Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=7)
	public void EP_CopayRule_Client2_CareRecepients1_MidlyIll() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Mildly Ill",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$14.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR more than 18 years, Mildly ILL Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=8)
	public void EP_CopayRule_Client2_CRMoreThan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$6.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=9)
	public void EP_CopayRule_Client2_InHomeHealthyOneCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
	
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$28.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=10)
	public void EP_CopayRule_Client2_InHomeHealthyOneCR_3hours() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$12.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=11)
	public void EP_CopayRule_Client2_3CRInHome_Lessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		addCareReceipt("Client2");
		
		addCareReceipt("Client2");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client2");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$12.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation and Center based reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */
	@Test(priority=12)
	public void EP_CopayRule_Client2_InHomeFourCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

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
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=13)
	public void EP_CopayRule_Client3_CareReceipents1() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		this.EP_CopaySignupUser("3","copay3");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$14.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR, Mildly ILL Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=14)
	public void EP_CopayRule_Client3_CareRecepients1_MidlyIll() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Mildly Ill",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$14.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR more than 18 years, Mildly ILL Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=15)
	public void EP_CopayRule_Client3_CRMoreThan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$6.00");
		
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR more than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=16)
	public void EP_CopayRule_Client3_HealthyTwoCR_3hours() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

	/*		businessComponents.EP_LaunchBrowser();
		
		 //login employee portal
		 businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client80.userName"), getTestData("TD_PWD"));
*/	 
		addCareReceipt("Client3");
		
		addCareReceipt("Client3");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client3");
	
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(3,3,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$6.00"); 
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 4 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=17)
	public void EP_CopayRule_Client4_CenterBasedOneCRHealthy() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//signup user
		this.EP_CopaySignupUser("4","copay4");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 4 - Verify Copay for InHome reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=18)
	public void EP_CopayRule_Client4_InHomeHealthyOneCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for In-Home reservation for One CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=19)
	public void EP_CopayRule_Client4_3CRInHome_Lessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		addCareReceipt("Client4");
		
		addCareReceipt("Client4");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client3");
	
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$12.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 5 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=20)
	public void EP_CopayRule_Client5_CenterBasedOneCRHealthy() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		this.EP_CopaySignupUser("1","copay5");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *Test Case #24572 
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 2 - Verify Copay for Center Based reservation for One CR more than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 1:00 PM"
	 * 
	 */

	@Test(priority=21)
	public void EP_CopayRule_Client5_CRMoreThan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(2,1,"Healthy",dateOfReservation,"10:00 AM","1:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$6.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *@author Satya
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 4 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=22)
	public void EP_CopayRule_Client6_CenterBasedOneCRHealthy() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		this.EP_CopaySignupUser("6","copay6");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *@author Satya
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 4 - Verify Copay for InHome reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=23)
	public void EP_CopayRule_Client8_2CRInHome() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 * @author Supraja
	 * CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 7 - Verify Copay for Center Based reservation for One CR, Less then 18 Years,  Healthy Status, 
	 * Reservation time  "10:00 AM to 3:00 PM"
	 * 
	 */

	@Test(priority=24)
	public void EP_CopayRule_Client7_OneCRHealthyLessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		this.EP_CopaySignupUser("7","copay7");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","3:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$15.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 * @author Supraja
	 * CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 7- Verify Copay for InHome reservation for One CR, More than 18 Years, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=25)
	public void EP_CopayRule_Client7_3CRHealthyLessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		addCareReceipt("Client7");
		
		addCareReceipt("Client7");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client7");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$35.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 * @author Supraja
	 * CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 7- Verify Copay for InHome reservation for One CR, More than 18 Years, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=26)
	public void EP_CopayRule_Client7_4CRHealthyLessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		addCareReceipt("Client7");
		
		addCareReceipt("Client7");
		
		addCareReceipt("Client7");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(4, tesData1,
				"No","Yes","Client7");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Mildly Ill",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$35.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 8 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=27)
	public void EP_CopayRule_Client8_CenterBasedOneCRHealthy() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		this.EP_CopaySignupUser("8","copay8");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 8 - Verify Copay for InHome reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=28)
	public void EP_CopayRule_Client8_InHomeHealthyOneCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 8 - Verify Copay for In-Home reservation for Three CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 12:00 PM"
	 * 
	 */

	@Test(priority=29)
	public void EP_CopayRule_Client8_3CRInHome_Lessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		addCareReceipt("Client8");
		
		addCareReceipt("Client8");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client8");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,3,"Healthy",dateOfReservation,"10:00 AM","12:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$50.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 9 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=30)
	public void EP_CopayRule_Client9_CenterBasedOneCRHealthy() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		this.EP_CopaySignupUser("9","copay9");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 9 - Verify Copay for InHome reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=31)
	public void EP_CopayRule_Client9_InHomeHealthyOneCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 9 - Verify Copay for In-Home reservation for Three CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 12:00 PM"
	 * 
	 */

	@Test(priority=32)
	public void EP_CopayRule_Client9_3CRInHome_Lessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		
		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		addCareReceipt("Client9");
		
		addCareReceipt("Client9");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client9");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,3,"Healthy",dateOfReservation,"10:00 AM","12:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$50.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 10 - Verify Copay for Center Based reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=33)
	public void EP_CopayRule_Client10_CenterBasedOneCRHealthy() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//signup user
		this.EP_CopaySignupUser("10","copay10");
		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *@author Supraja
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 10 - Verify Copay for InHome reservation for One CR, Healthy Status, 
	 * Reservation time  "10:00 AM to 5:00 PM"
	 * 
	 */

	@Test(priority=34)
	public void EP_CopayRule_Client10_InHomeHealthyOneCR() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		
		//businessComponents.EP_LaunchBrowser();
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,1,"Healthy",dateOfReservation,"10:00 AM","5:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$7.00");
		
		//businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	/**
	 *
	 *CSC BUCA - Copay Rule - Copay Calculations: ACC_Stab_Test 8 - Verify Copay for In-Home reservation for Three CR Less than 18 years, Healthy Status, 
	  Reservation time  "10:00 AM to 12:00 PM"
	 * 
	 */

	@Test(priority=35)
	public void EP_CopayRule_Client10_3CRInHome_Lessthan18Years() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client81.userName"), getTestData("TD_PWD"));
		
		addCareReceipt("Client10");

		addCareReceipt("Client10");
		 
		String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
				"No","Yes","Client10");
		
		//Select Date of reservation
		String dateOfReservation = getTestData("TD_COPAYDOR");
		
		businessComponents.EP_CoPayInHomeReservation(1,3,"Healthy",dateOfReservation,"10:00 AM","12:00 PM");
		
		businessComponents.EP_CoPayPaymentInformation("$50.00");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	public void addCareReceipt(String clientName) throws IOException, Exception
	{
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
				
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
		businessComponents.addCareRecipients(addCrData3_5Years,"No",clientName);
		
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
	
	
	
	/** 
	* @author Satya
	* @version 
	* @return fv
	* @param 
	* @CreationDate: 04/03/2014
	*/ 
	public void EP_CopaySignupUser(String clientNo,String userId) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		businessComponents.EP_verifyEmployer(clientNo);
		
		businessComponents.EP_AcceptPolicyAndSubmit();

		String userName = createDyanamicUserData();
		
		//Sign Up
		businessComponents.EP_SignUpUser(userName, clientNo, userId);
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		//Click on Employee Profile icon
		COMMON_METHODS.clickElement(getTestObject("OL_134"));
		
		businessComponents.EP_UpdateEmployeeprofile(clientNo);
		
		//User will be navigated to the Care Profile page with Green check mark on the employee profile icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_138"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
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
				
		businessComponents.addCareRecipients(addCrData, "No",clientNo);
		
		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//User will be navigated to the Care Profile page with Green check mark on the care recipients icon
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_139"));
		
		//Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));
		
		businessComponents.EP_AddAuthorizedContacts();
		
		//this.EP_reservation();
		
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	private void loginClient(String clientNo,String userId) throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3") };
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(clientNo  + "." + userId + ".userName"), getTestData("TD_PWD"),signInArray);
		}
	}
	
}