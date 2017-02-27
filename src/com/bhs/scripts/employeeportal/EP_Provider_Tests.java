package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;
import com.bhs.BusinessComponents.*;

public class EP_Provider_Tests extends INITIALIZE {

	EP_BusinessComponents busscomp = new EP_BusinessComponents();
	String employer=null;
	
	/**
	 * This test script covers functionality of  Providers - Ensure user can perform a standard search. 
	 * TFS ID : 23375
	 * */
	@Test @Parameters("client") 
	public void EP_ProviderStandardSearchTest(String employer)
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside -" + methodName);

		this.employer = employer;
		//Launch browser
		// busscomp.EP_LaunchBrowser();
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//login employee portal
		String userName = ReadwritDataFromProps.props.getProperty(employer + ".signup.userName");
		String password = getTestData("TD_PWD");
		
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);
		
		//Click 'Providers' tab
		COMMON_METHODS.clickElement(getTestObject("PT_01"));

		//Find the "Find Care Near You" module and click the dropdown and Select one of the location
		COMMON_METHODS.listBoxSelect(getTestObject("PT_02"), "Home",
				"byVisibleText");

		//	Click the Search button
		COMMON_METHODS.clickElement(getTestObject("PT_03"));

		// Drag the distance slider to get provider results
		//COMMON_METHODS.DragandDrop(getTestObject("PT_04"), 220, 0);
	
		Thread.sleep(10000);
		
		//Verify the user can view all providers the employee has access to
		busscomp.EP_ProviderVerifyNoOfProviders();
		
		//busscomp.logout();
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	/**
	 * This test script covers functionality of Providers - Ensure client can fill out the 'Nominate a New Provider' form and submit to system. 
	 * TFS ID : 23376
	 * */
	@Test @Parameters("client")
	public void EP_ProviderSubmitNomination(String employer) throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside -" + methodName);

		//Launch browser
		//busscomp.EP_LaunchBrowser(itc);

		// login employee portal
		//busscomp.LoginEmployeeportal(
				//ReadwritDataFromProps.props.getProperty("client1.userName"),
				//getTestData("TD_PWD"));

		this.loginEPPoratl();
		
		// Click 'Providers' tab
		COMMON_METHODS.clickElement(getTestObject("PT_01"));
		
		//Click 'NOMINATE A CARE PROVIDER' link
		COMMON_METHODS.clickElement(getTestObject("PT_06"));
		
		//Type '<name>' in 'Provider Name' text box
		busscomp.EP_ProviderEnterText("Name", "CSC");
		
		//Type '<address>' in 'Address' text box
		busscomp.EP_ProviderEnterText("Address", "Denver");
		
		//	Type '<city, state, zip>' in 'City, State, Zip/Postal Code' text box
		busscomp.EP_ProviderEnterText("ZipCode", "80231");
		
		//Type '<phone>' in '' Telephone Number' text box
		busscomp.EP_ProviderEnterText("Contactno", "7202279345");
		
		//Type '<email>' in 'Email Address' text box
		busscomp.EP_ProviderEnterText("Email", "deepamca8@gmail.com");
		
		//Type '<name>' in 'Agency/Center Contact' text box
		busscomp.EP_ProviderEnterText("Agent", "Vicky");
		
		//	Type '<relationship>' in 'How do you know of this provider?' text box
		busscomp.EP_ProviderEnterText("Providerdata", "Online");
		
		//Select 'NO, you may share my name and company with the provider.' check box
		//COMMON_METHODS.radioButton(getTestObject("PT_14"));
		
		//Select 'YES, you may share my name and company with the provider.' check box
		COMMON_METHODS.radioButton(getTestObject("PT_15"));
		
		//Click 'Submit' button
		COMMON_METHODS.clickElement(getTestObject("PT_16"));
		
		//Log Out Of the application
		Utility.logout();
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);
	
		
	}
	
	private void loginEPPoratl() throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			String userName = ReadwritDataFromProps.props.getProperty(employer + ".signup.userName");
			String password = getTestData("TD_PWD");
			
			//Login to Emp Portal
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(userName, password,signInArray);
			
		}
	}

}
