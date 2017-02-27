package com.bhs.scripts.carecenterportal;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.scripts.employeeportal.EP_CBAndInHomeReservations_Tests;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.Utility;

public class CCP_Smoke_Test extends INITIALIZE{

	CCP_BusinessComponents CCbusinessComponents = new CCP_BusinessComponents();
	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	EP_CBAndInHomeReservations_Tests CB = new EP_CBAndInHomeReservations_Tests();

	//Reading Test Objects from Data excel 
/*	static{
		try{
			readTestObject(getDataTablePath("CCP"), "TO_CCP");
			readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}*/


	/* Smoke Test
	 * @author: Kiran G
	 * @CreationDate: 27/03/2014
	 */

	//PREREQUISITE: We need atleast 1 Center based reservation in INPROGRESS status at 
	//Center(Bright Horizons at Chicago East Loop - Stabilization Testing) on the day when we execute this
	//Test case

	@Test()
	public void CCP_Smoke() throws Exception {


		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Creating Center based reservation
		//CB.EP_SignUpAndCBReservationTest("5");
				
		//Logging in CC Portal
		// Read test data form CCP Sheet
		//readTestData(getDataTablePath("CCP"), "TD_CCP");
		
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		businessComponents.EP_CenterBasedReservation("Client1", getTestData("CCP_TD_CPDate"));
		Utility.logout();

		//Launch CCP
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(5000);

		//Verify that the user can select Care Center using Change link at the top
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		
		/*Thread.sleep(1000);
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_CenterName2"));
		Thread.sleep(1000);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		Thread.sleep(1000);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));*/
		
		// Change the center
		CCP_BusinessComponents.changeCenter("Number",getTestData("CCP_TD_CenterNumber2"));

		Thread.sleep(1000);
		//Verify Home Page is displayed properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		Thread.sleep(3000);
		
		//Click on Reservations to check the different Classrooms
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		
		Thread.sleep(3000);

		//Verify the Reservation page is displayed
		if(COMMON_METHODS.driver.getPageSource().contains("Reservations:"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Reservations Page is displayed", "Reservations Page is displayed", "");
		}

		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Reservations Page is displayed", "Reservations Page is NOT displayed", "");
		}
						
	/*	 //Click on Calendar, choose another date and Ensure the date chose loads
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL6"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_date"));

		// Click on the 'Expand All' link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));

		//Click on Collapse All link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));

		//Click on Arrow mark of Preschool class
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL9"));*/ 
		
		Thread.sleep(2000);
		
		this.selectReservationDate(getTestData("CCP_TD_CPDate"));
		
		this.expandClassRoom("Early Learners","BHAutoLN");

		Thread.sleep(3000);
		
		//Verify the options in the Status dropdown
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("GCCP_OL_55"), "WL");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("GCCP_OL_55"), "Pend");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("GCCP_OL_55"), "Conf");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("GCCP_OL_55"), "Cancel");

		// Get the index of Edit link of the reservation
		int j = 0;
		List<WebElement> lweReservations = COMMON_METHODS.driver.findElements(By.xpath("//tr[@id='row']"));
		for(WebElement weRow: lweReservations){
			j = j + 1;

		}

		// Click 'Edit' link
		COMMON_METHODS.driver.findElement(By.xpath("//td[@id='data_" + (j-1) + "']/..//span/a")).click();
		
		Thread.sleep(2000);

		//Check checkbox present in front of any Care Session in the 'Care Sessions' grid
		COMMON_METHODS.checkBox(getTestObject("GCCP_OL_47"), "check");

		Thread.sleep(2000);
		
		//Click 'Edit Selected' link present below the 'Care Sessions' grid
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_12"));

		Thread.sleep(2000);
		
		//Select the Confirmed status from the dropdown
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_13"), "Confirmed", "byVisibleText");
		
		Thread.sleep(2000);

		//Click on Update reservation
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_18"));
		
		Thread.sleep(2000);

		//Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		//Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"), "Family Management");

		//enter search for and then verify the box allows text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL23"), "BHAutoFN");
		Thread.sleep(2000);

		//Click on Click on Client Employee and search and enter search for and click submit and verify   
		COMMON_METHODS.radioButton(getTestObject("S_CCP_OL21"));

		//Click on "Advanced search"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL26"));

		// Verify that the advanced search options open up.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL27"), "Advanced Criteria");

		//Click on Search button
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL30"));
		Thread.sleep(7000);

		//Verify that the results are displayed
		if(COMMON_METHODS.driver.getPageSource().contains("Results"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Results are displayed", "Results are displayed", "");
		}

		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Results are displayed", "Results are NOT displayed", "");
		}

		Thread.sleep(3000);

		//Verify that the Family Details link is displayed
		if(COMMON_METHODS.driver.findElement(By.linkText("Family Details")).isDisplayed())
		{
			REPORTER.LogEvent(TestStatus.PASS, "Family Details link is displayed", "Family Details link displayed", "");
		}

		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Family Details link displayed", "Family Details link NOT displayed", "");
		}

		//Click on Client Programs tab
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL38"));

		//Verify that the Client Programs page is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL39"), "Client Programs");

		//Input text into Client search text box
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL36"),getTestData("CCP_TD_Client_Prog"));

		//Click on Search button
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL37"));

		//Verify that the results are displayed
		if(COMMON_METHODS.driver.getPageSource().contains("Results"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Results are displayed", "Results are displayed", "");
		}

		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Results are displayed", "Results are NOT displayed", "");
		}

		Thread.sleep(3000);
		//Verify the "Add New Employee link"
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL40"), "Add New Employee");

		//Click on Center Management link
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_14"));

		//Verify Center Management page is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL39"), "Center Management");

		// Logout from CC Portal	
		Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);


	}	

	/**
	 * 
	 */
 	private void expandClassRoom(String classType,String lastName) {
	
	if (classType.contains("Infant")) {
		COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][1]/div/ul/li[3]/a")).click();
	}else if (classType.contains("Toddler")) {
		COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][2]/div/ul/li[3]/a")).click();
	}else if (classType.contains("Preschool")) {
		COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][3]/div/ul/li[3]/a")).click();
	}else if (classType.contains("School Age")) {
		COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][4]/div/ul/li[3]/a")).click();
	} if (classType.contains("Young Explorers")) {
		COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][1]/div/ul/li[3]/a")).click();
	}else if (classType.contains("New Adventures")) {
		COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][2]/div/ul/li[3]/a")).click();
	}else if (classType.contains("Early Learners")) {
		COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][3]/div/ul/li[3]/a")).click();
	}else if (classType.contains("Discovery Zone ")) {
		COMMON_METHODS.driver.findElement(By.xpath("//div[contains(@id,'RoomClass')][4]/div/ul/li[3]/a")).click();
	}

	//click edit link
	//COMMON_METHODS.driver.findElement(By.xpath("//span[contains(text(),'" + lastName +"')]/../../td[11]/span")).click();
	
 }
 	
 	public void selectReservationDate(String reservationDate) throws Exception {

		// Test data for selecting the date from the data picker
		//String resDate=getTestData("CCP_TD_CPDate");
		
		String resDate=reservationDate;
		
		String dateArray[]=resDate.split("/");
		String date=dateArray[1];
		if(date.startsWith("0")){

			date=date.substring(1);
		}

		int month=Integer.parseInt(dateArray[0]);

		String objArray[]={getTestObject("CCP_OLP_01"),getTestObject("CCP_OLP_02")};
		String dataArray[]={date};

		// Click Calendar and select the date of reservation
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
		Thread.sleep(4000);
		Utility.selectDate(month,objArray,dataArray);

	}
 	
}
