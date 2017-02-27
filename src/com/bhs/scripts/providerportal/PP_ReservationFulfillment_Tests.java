package com.bhs.scripts.providerportal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.Utility;

public class PP_ReservationFulfillment_Tests  extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();

//	//Reading Test Objects from Data excel 
//	static{
//		try{
//			readTestObject(getDataTablePath("PP"), "TO_PP");
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	// ######################################################################################################################################################################
	// @ TFS ID: 13505 - BUCA - Reservation Fulfillment - In Home - Verify after a reservation is marked as "Staff Later" a user can return and staff the reservation
	// @ Author: Krishna Chaitanya Maringanti
	// @ Date  : 02-APR-2014
	//TFS ID:18356 - BUCA - Reservation Fulfillment - In Home - Verify a Provider can mark a pending in home reservation assigned to them as
	 // Accept Queue(Staff Reservation Now)
	//TFS ID: 13107 -  In Home - Verify if an in home provider marks a reservation as "confirmed" the reservation is marked Staffed and Scheduled
	//TFS ID: 18355 -BUCA - Reservation Fulfillment - In Home - Verify a Provider can mark a pending in home reservation assigned to them as Accept Queue(Staff It Later)
	// ######################################################################################################################################################################
	
	@Test()
	public String PP_StaffLater() throws Exception {
	
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
	
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
	
		String resNum = businessComponents.EP_InHomeReservation("2", getTestData("TD_Dateofreservation1"));
		//String resNum="CAS-11601-J6S9Q6";
		System.out.println("Reservation Number "  + resNum);
		 
		// Logout from 'Employee Portal'
		//	Utility.logout();
		
		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray1[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};
		
		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray1);
		
		// Click on 'Reservations' link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));
 
		// Click 'Available Reservation' link
		COMMON_METHODS.clickElement(getTestObject("PPAM_27"));
		
		  String objArray[]={"ul","id","li"};
		  String objArrayPage[]={resNum,getTestObject("PPMA_193")};
		  int pageNo=Utility.goToLastPageInGridList(objArray);
		  try{
			  if(pageNo!=0){
				  COMMON_METHODS.driver.findElement(By.xpath("//ul[@id='pagination-clean']/li["+(pageNo-1)+"]")).click();
				  Thread.sleep(5000);
				  REPORTER.LogEvent(TestStatus.PASS, "Click on Pagination click", "Click on Pagination click - Successfull".toUpperCase(),"");
			  }
			  }
			 catch(Exception e){
				 REPORTER.catchException(e, "Unable to click on last page in pagination List grid");
			 }
		  Thread.sleep(5000);
		  Utility.selectAvailResInPP(objArrayPage); 
		  COMMON_METHODS.driver.findElement(
					By.xpath("//li[@class='resnumber' and text()='"
							+ objArrayPage[0] + "']/../li[4]/a")).click();
		// Click 'Accept Queue' button
		COMMON_METHODS.clickElement(getTestObject("PPAM_26"));
		
	
		// Click Staff It Later button
		COMMON_METHODS.clickElement(getTestObject("PPAM_23"));

		// Click Pending Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPMA_78"));
		
		 String objArray1[]={"ul","id","li"};
		  String objArrayPage1[]={resNum,getTestObject("PPMA_193")};
		  int pageNo1=Utility.goToLastPageInGridList(objArray1);
		  try{
			  if(pageNo1!=0){
				  COMMON_METHODS.driver.findElement(By.xpath("//ul[@id='pagination-clean']/li["+(pageNo1-1)+"]")).click();
				  Thread.sleep(5000);
				  REPORTER.LogEvent(TestStatus.PASS, "Click on Pagination click", "Click on Pagination click - Successfull".toUpperCase(),"");
			  }
			  }
			 catch(Exception e){
				 REPORTER.catchException(e, "Unable to click on last page in pagination List grid");
			 }
		  Thread.sleep(5000);
		  Utility.selectAvailResInPP(objArrayPage1);
		  COMMON_METHODS.driver.findElement(
					By.xpath("//li[@class='resnumber' and text()='"
							+ objArrayPage1[0] + "']/../li[4]/a")).click();
		  
		// Click Staff Reservation button
		COMMON_METHODS.clickElement(getTestObject("PPCR_11"));
		
		// Check the 'I have reviewed and understand the details of the care reservation' checkbox
		COMMON_METHODS.checkBox(getTestObject("PPCR_13"), "check");
		
		// Select the first active care giver
		COMMON_METHODS.listBoxSelect(getTestObject("PPMA_190"), "#1", "byIndex");

		// Click Submit Staffing Request button
		COMMON_METHODS.clickElement(getTestObject("PPMA_191"));

		// Click Scheduled Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPMA_79"));
		
		 String objArray2[]={"ul","id","li"};
		 String objArrayPage2[]={resNum,getTestObject("PPMA_193")};
		  int pageNo2=Utility.goToLastPageInGridList(objArray2);
		  try{
			  if(pageNo2!=0){
				  COMMON_METHODS.driver.findElement(By.xpath("//ul[@id='pagination-clean']/li["+(pageNo2-1)+"]")).click();
				  Thread.sleep(5000);
				  REPORTER.LogEvent(TestStatus.PASS, "Click on Pagination click", "Click on Pagination click - Successfull".toUpperCase(),"");
			  }
			  }
			 catch(Exception e){
				 REPORTER.catchException(e, "Unable to click on last page in pagination List grid");
			 }
		  Thread.sleep(5000);
		  Utility.selectAvailResInPP(objArrayPage2); 
		  COMMON_METHODS.driver.findElement(
					By.xpath("//li[@class='resnumber' and text()='"
							+ objArrayPage2[0] + "']/../li[5]/a")).click();
		
		// Verify the element is displayed
		WebElement we = COMMON_METHODS.driver.findElement(By.xpath("//li[@class='resnumber' and text()='"+resNum+"']"));
		if(we.isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Reservation' is confirmed.", "'Reservation' is confirmed.","");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Reservation' is confirmed.", "'Reservation' is not confirmed.","");
		}			
			
		// Logout from 'Provider Portal'
		Utility.logout();
		
		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
		
		return resNum;

	}
}

