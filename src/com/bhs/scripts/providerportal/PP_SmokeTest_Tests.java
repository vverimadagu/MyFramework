package com.bhs.scripts.providerportal;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;
import com.bhs.util.INITIALIZE.TestStatus;

public class PP_SmokeTest_Tests  extends INITIALIZE {
	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
		
	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 27/03/2014
	 */
	//Test Case #N/A: Provider Portal smoke test

	@Test()
	public void PP_SmokeTest() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Create an In-home reservation
		//readTestData(getDataTablePath("EP"), "TD_EP2");

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		//String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
 
		String resNum = businessComponents.EP_InHomeReservation("Client2", getTestData("TD_Dateofreservation1"));//.EP_InHomeReservation(5);
		System.out.println("Reservation Number "  + resNum);
		 
		// Logout from 'Employee Portal'
		Utility.logout();
		
		/*// Read test data for based on client 1
		readTestObject(getDataTablePath("PP"), "TO_PP");
		readTestData(getDataTablePath("PP"), "TD_PP");*/
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray1[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray1);

		// Verify the 'Provider Portal' home page is displayed
		if(COMMON_METHODS.driver.getTitle().equals("Back-Up Care Advantage Provider Portal")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Provider Portal' home page is displayed.", "'Provider Portal' home page is displayed.","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Provider Portal' home page is displayed.", "'Provider Portal' home page is not displayed.","");
		}
		
		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));
		
		// Verify the links displayed on Reservations page
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMA_76"));
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMA_77"));
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMA_78"));
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMA_79"));
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMA_80"));
		
		String objArray[]={"ul","id","li"};
		String objArrayPage[]={resNum,getTestObject("PPMA_193")};
		int pageNo=Utility.goToLastPageInGridList(objArray);
		try{
		  if(pageNo!=0){
			  COMMON_METHODS.driver.findElement(By.xpath("//ul[@id='pagination-clean']/li["+(pageNo-1)+"]")).click();
			  Thread.sleep(5000);
			  REPORTER.LogEvent(TestStatus.PASS, "Click on Pagination click", "Click on Pagination click - Successfull".toUpperCase(),"");
		  }
		}catch(Exception e){
			 REPORTER.catchException(e, "Unable to click on last page in pagination List grid");
		}
		//selects available reservation
		Utility.selectAvailResInPP(objArrayPage);
		  
		COMMON_METHODS.driver.findElement(
					By.xpath("//li[@class='resnumber' and text()='"
							+ objArrayPage[0] + "']/../li[4]/a")).click();
		
		// Click Accept Queue button
		COMMON_METHODS.clickElement(getTestObject("PPAM_26"));
		
		// Verify Staff Reservation Now, Staff It Later buttons are displayed 
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPAM_22"));
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPAM_23"));
		
		// Close the pop-up
		COMMON_METHODS.clickElement(getTestObject("PPAM_24"));
		
		// Verify Not Interested link is displayed
		//COMMON_METHODS.verifyElementDisplayed(getTestObject("PPAM_25"));
		
		// Click Caregivers link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));
		
		// Verify the 'Caregivers' page is displayed
		if(COMMON_METHODS.driver.getTitle().equals("Caregivers")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Caregivers' page is displayed.", "'Caregivers' page is displayed.","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Caregivers' page is displayed.", "'Caregivers' page is not displayed.","");
		}

		// Click Account Management link
		COMMON_METHODS.clickElement(getTestObject("PPAM_01"));
		
		// Verify the 'Account Management' page is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPAM_28"));
		
		// Click Reports link
		COMMON_METHODS.clickElement(getTestObject("PPAM_29"));
		
		// Verify the 'Reports' page is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPAM_30"));

		// Click Resources link
		COMMON_METHODS.clickElement(getTestObject("PPAM_31"));

		// Verify the 'Resources' page is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPAM_32"));
		
		// Click My Account link
		COMMON_METHODS.clickElement(getTestObject("PPMA_218"));

		// Verify the 'My Account' page is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("PPAM_33"));

		// Logout from 'Provider Portal'
		Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

		
	}
}
