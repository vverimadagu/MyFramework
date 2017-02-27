package com.bhs.scripts.employeeportal;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.Utility;

public class EP_SignupRegistration extends INITIALIZE {

	EP_BusinessComponents businessComponents  = new EP_BusinessComponents();
	
	//Reading Test Objects from Data excel 
/*	static{
		try{
			readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/

	String employer1=null;
	@Test @Parameters("client")
	public void EP_CBReservationTest(String employer1) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		//Test data for adding Care Recipients
		String addCrData3_5Years[] = {
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
				
		// Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Sign Up and Registering 10 clients 
		for(int i=1;i<=10;i++){
			
			//Read testdata for based on specific clients
			readTestData(getDataTablePath("EP"), "TD_EP"+i);
			
			//Verify Employer
			businessComponents.EP_verifyEmployer(employer1);
			//Accept policy
			businessComponents.EP_AcceptPolicyAndSubmit();
		
			//Create dynamic user name
			String userName = createDyanamicUserData();
			// Register a New User
			businessComponents.EP_SignUpUser(userName,Integer.toString(i),"");
			
			//Register User
			businessComponents.EP_Registration(addCrData3_5Years,"No",employer1);
			Utility.logout();
			
		}
				/*
		Verify that Care Recipients are listed
				
		Verify the Care recipients selected in Step 1 are present in 'Select The Date and Time For Care' section
				
		Verify 'Search Criteria' section has all the  correct information as per selections in previous reservation steps
		
		Verify all the Care Recipients selected in Step 1 are displayed
			
		Verify Usage for the Care Recipient present under 'Total Utilization for Care Request' section at the bottom right of the page*/

		
	}
	
	@Test @Parameters("client")
	public void EP_60ClientsCBReservationTest(String client) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		//Test data for adding Care Recipients
		String addCrData3_5Years[] = {
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
				
		// Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Verify Employer
		businessComponents.EP_verifyEmployer(client);
		
		//Accept policy
		businessComponents.EP_AcceptPolicyAndSubmit();
		
		//Create dynamic user name
		String userName = createDyanamicUserData();
		
		// Register a New User
		businessComponents.EP_SignUpUser(userName,client,"client");
			
		//Register User
		businessComponents.EP_Registration(addCrData3_5Years,"No",client);
		
		//Center based Reservation
		businessComponents.EP_Reservation();
		
		//Center based Reservation
		businessComponents.EP_IHReservation();

		// Logout
		Utility.logout();

		// Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
			
	}

	
}
