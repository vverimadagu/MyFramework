package com.bhs.scripts.employeeportal;

import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_Resources extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	
	/*//Reading Test Objects from Data excel 
	static{
		try{
			readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
*/
	
	/**
	 * Reservation Wizard - Step1 - Ensure user is able to continue to Step 2 via 'Continue' button
	 * TFS ID : 23382
	 *
	 */
/*	@Test()
	public void EP_ResourcesTabNavigation(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
			// SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
		readTestObject(getDataTablePath("EP"), "TO_EP");
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));
		
		//Click the Resources tab
		COMMON_METHODS.clickElement(getTestObject("Res_Tab_01"));
		
		//select a state' and check to make sure all the states from managed content are listed
		
		businessComponents.logout();
			
	}*/
	
	@Test()
	public void EP_ResourcesPageLinksValidation () throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Get User Name and Password
		String userName=ReadwritDataFromProps.props.getProperty("client5.userName");
		String password=getTestData("TD_PWD");
				
		//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);
		
		//Click the Resources tab
		COMMON_METHODS.clickElement(getTestObject("Res_Tab_01"));
		
		//Verify Resources text in the Resources page
		businessComponents.EP_verifyText("Resources", "Resources");
				
		//Click the Link Child Care Center Care Tip Sheet under Center-based Care
		COMMON_METHODS.clickElement(getTestObject("Res_Tab_02"));
		//Switch to pop up
		COMMON_METHODS.switchToPopup();
		//Close the pop up
		COMMON_METHODS.driver.close();
		COMMON_METHODS.switchToNormal();
				
		//Click on Child In-Home Care Tip Sheet 
		COMMON_METHODS.clickElement(getTestObject("KRS_03"));
		//Switch to pop up
		COMMON_METHODS.switchToPopup();
		//Close the pop up
		COMMON_METHODS.driver.close();
		Thread.sleep(1000);
		COMMON_METHODS.switchToNormal();
		
		//Click on Child Daily Instructions and Activity Log 
		COMMON_METHODS.clickElement(getTestObject("Res_Tab_04"));
		//Switch to pop up
		COMMON_METHODS.switchToPopup();
		//Close the pop up
		COMMON_METHODS.driver.close();
		Thread.sleep(2000);
		COMMON_METHODS.switchToNormal();
		
		//Click on Adult Daily Instructions and Activity Log
		COMMON_METHODS.clickElement(getTestObject("Res_Tab_05"));
		//Switch to pop up
		COMMON_METHODS.switchToPopup();
		//Close the pop up
		COMMON_METHODS.driver.close();
		Thread.sleep(2000);
		COMMON_METHODS.switchToNormal();
				
		//Click Download our Back-Up Care Parent Handbook 
		COMMON_METHODS.clickElement(getTestObject("KRS_04"));
		COMMON_METHODS.driver.navigate().back();
		Thread.sleep(10000);

		//Click the link "Go To Bright Connections
		COMMON_METHODS.clickElement(getTestObject("Res_Tab_07"));
		//Switch to pop up
		COMMON_METHODS.switchToPopup();
		//Close the pop up
		COMMON_METHODS.driver.close();
		Thread.sleep(2000);
		COMMON_METHODS.switchToNormal();
				
		// Logout
		Utility.logout();
		
		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	

}
	
	