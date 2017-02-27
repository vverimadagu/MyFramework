package com.bhs.scripts.employeeportal;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;

public class VerifyLinks extends INITIALIZE {


	@Test()
	public void VerifyLinksTest(ITestContext TC) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Click Feedback
		COMMON_METHODS.clickElement(getTestObject("OL_4"));

		//Verify Feedback Page displayed
		if(EP_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Feedback")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Feedback Page Displayed", "Feedback Page Displayed", "");
		}

		//Click Home
		COMMON_METHODS.clickElement(getTestObject("OL_8"));

		//Click Privacy Policy
		COMMON_METHODS.clickElement(getTestObject("OL_5"));

		//Verify Privacy Policy Page displayed
		if(EP_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Privacy Policy")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Privacy Policy Page Displayed", "Privacy Policy Page Displayed", "");
		}

		//Click Home
		COMMON_METHODS.clickElement(getTestObject("OL_8"));

		//Click Terms of Use
		COMMON_METHODS.clickElement(getTestObject("OL_6"));

		//Verify Terms of Use Page displayed
		if(EP_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Terms Of Use")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Terms Of Use Page Displayed", "Terms Of Use Page Displayed", "");
		}

		//Click Home
		COMMON_METHODS.clickElement(getTestObject("OL_8"));

		//Click Trademark Notice
		COMMON_METHODS.clickElement(getTestObject("OL_7"));


		//Verify Trademark Notice Page displayed
		if(EP_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Trademark Notice")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Trademark Notice Page Displayed", "Trademark Notice Page Displayed", "");
		}

	}

}
