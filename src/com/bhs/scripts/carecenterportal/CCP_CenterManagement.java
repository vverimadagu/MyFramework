package com.bhs.scripts.carecenterportal;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.Utility;

public class CCP_CenterManagement extends INITIALIZE{
	
	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	CCP_BusinessComponents CCbusinessComponents = new CCP_BusinessComponents();

	//Reading Test Objects from Data excel 
	/*static{
		try{
			readTestObject(getDataTablePath("CCP"), "TO_CCP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/

	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 26/03/2014
	 
	//Test Case #7259: BUCA - CCP Landing - Ensure System Populates the Message Center

	@Test()
	public void CCP_Landing_MessageCenter() throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");
		
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);

		// Verify 'Message Center' is displayed
		if(COMMON_METHODS.driver.findElement(By.xpath("//div[@id='msgBox']")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Message Center' is displayed.", "'Message Center' is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Message Center' is displayed.", "'Message Center' is not displayed.", "");
		}

		// Logout from Care Center Portal
		Utility.logout();

		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}*/
	
	
	/* TFS ID:15517
	 * @CreationDate: 26/03/2014
	 * BUCA - CCP - Center Management - Ensure Update link works on the Center Activities Page
	 */


	@Test()
	public void CCP_CM_CenterActivities() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); */
		
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);

		/*COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));*/
		
		// Change the center
		CCP_BusinessComponents.changeCenter("Name",getTestData("CCP_TD_srchText"));
		
		Thread.sleep(2000);  

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");
		
		Thread.sleep(2000);
		
		
		//Click on Center Management link
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_14"));
		
		
		Thread.sleep(3000);
		
		//Click on Center Activities link
		//COMMON_METHODS.clickElement(getTestObject("S_CCP_OL7"));
		
		//Click on Update link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_22"));
		
		Thread.sleep(1000);
		
		//Input text in the notes textbox
		COMMON_METHODS.editAField(getTestObject("GCCP_OL_23"), "Test");
		
		Thread.sleep(2000);
		
		//Click on Update activity button
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_24"));
		
		//Click on Update link to verify the text
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_22"));
		
		Thread.sleep(2000);
		
		//Verify the text present in the notes textbox
		if(COMMON_METHODS.getText(getTestObject("GCCP_OL_23")).equals("Test"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verifing the text present in the Text box", "Text updated successfully" , "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifing the text present in the Text box", "Text NOT updated successfully" , "");
		}
		
		//Close the Activity details popup
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_25"));
				
		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	
	}
	/**
	 * This test script covers functionality of  CCP - Center Management - Ensure a Care Center Employee can Add Employee Time
	 * employee profile information 
	 * TFS ID : 10224
	 * @param TC
	 * @throws Exception
	 * Test Case #7259: BUCA - CCP Landing - Ensure System Populates the Message Center
	 */

	/** 
	 * @author Deepa
	 * @version 
	 * @return 
	 * @param 
	 * @CreationDate: 26/03/2014
	 */
	
	@Test
	public void CC_AddEmployeeTimeSheet() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*//Launch Browser
		CCbusinessComponents.CCP_LaunchBrowser(); 

		//User logs into the portal
		CCP_BusinessComponents.CCP_Login();*/
		
		/*// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		Thread.sleep(2000);
		this.loginCCPPortal();
		
		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));
		
		// Verify 'Message Center' is displayed
		if(COMMON_METHODS.driver.findElement(By.xpath("//div[@id='msgBox']")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Message Center' is displayed.", "'Message Center' is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Message Center' is displayed.", "'Message Center' is not displayed.", "");
		}

		
		//Navigate to Care Center Management
		//COMMON_METHODS.navigateToMenu(getTestObject("D_CCP_OL_61"));
		
		//Click on Center Management link
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_14"));
		
		//Navigate to Center Staffing
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_53"));
		Thread.sleep(2000);
		
			
				//Navigate to Add Employee link
	//	COMMON_METHODS.navigateToMenu(getTestObject("D_CCP_OL_63"));
		
		//Click on Add Employee link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_54"));
		
		Thread.sleep(2000);
		
		//Select Employee
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_64"), "Deepa Katewad", "byVisibleText");
		
		//Click on Monday Start time field
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_65"));
		
		//Drag the time slider
		COMMON_METHODS.DragandDrop(getTestObject("D_CCP_OL_66"),50,0);
		
		//Click on Done
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_70"));
		
		//Click on Monday end time field
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_67"));
		
		//Drag the time slider
		COMMON_METHODS.DragandDrop(getTestObject("D_CCP_OL_68"),100,0);
		
		//Click on Done
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_70"));
		
		//Click on Add time button
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_69"));
		
		//Logout from 'Portal'
		Utility.logout();
		
	}
	
	/*
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 31/03/2014
	 */
	//Test Case #10226 - BUCA - CCP - Care Management - Ensure a Care Center Employee can view the Center Profile (Matches UX Document)

	@Test()
	public void CCP_CenterProfile() throws Exception {
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside--------- "+methodName);

		// Read testdata for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch Browser
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// User logs into the portal
		CCP_BusinessComponents.CCP_Login();*/
		/*
		// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPPortal();
		
		// Navigate to Care Center Management
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_14"));
		
		Thread.sleep(2000);
		
		// Click Center Profile link
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_33"));
		
		Thread.sleep(5000);
		
		// Verify the Center Profile page is displayed
		if(COMMON_METHODS.driver.getTitle().equals("Center Management: Center Profile - Back-Up Care Center")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Center Profile' page is displayed.", "'Center Profile' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Center Profile' page is displayed.", "'Center Profile' page is not displayed.", "");
		}
		
		// Logout from Care Center Portal
		//Utility.logout();
		//Logout from 'Portal'
		Utility.logout();
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	
	
	/* 
	 * Author: Supraja
	 * TFS ID:10223
	 * @CreationDate: 27/03/2014
	 * BUCA - CCP - Center Management - Ensure user can change Center Capacity
	 */


	@Test()
	public void CCP_CM_CenterCapacity_ChangeNumbers() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); 
*/
		/*// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		this.loginCCPPortal();
		Thread.sleep(2000);

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		
		Thread.sleep(1000);
		
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		
		Thread.sleep(1000);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		
		Thread.sleep(1000);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		Thread.sleep(2000);
		
		
		//Verify Landing Page
		//COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");
		
		//Click on Center Management link
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_61"));
		
		Thread.sleep(5000);
		
		//Click on Center Capacity link
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_01"));
		
		Thread.sleep(2000);
		
		// Page should render with Center Capacity 
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_CM_OL_02"), "Center Capacity");
		
		Thread.sleep(2000);
			
		//Change numbers in Capacity
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_03"), getTestData("CCP_TD_CPInfant"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_04"), getTestData("CCP_TD_CPToddler"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_05"), getTestData("CCP_TD_CPPreschool"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_06"), getTestData("CCP_TD_CPSchoolAge"));
		
		Thread.sleep(2000);
		
		
		//Click Save Changes
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_07"));
		Thread.sleep(7000);
		
		//Verify system entered new numbers displays them when it refreshes.
			if(COMMON_METHODS.getText(getTestObject("CCP_CM_OL_03"),"value").contains(getTestData("CCP_TD_CPInfant")))
				{
					REPORTER.LogEvent(TestStatus.PASS, "Verifing the entered new numbers displays in the Text box", "Numbers updated successfully" , "");
					
					
				}
				else
				{
					REPORTER.LogEvent(TestStatus.FAIL, "Verifing the entered new numbers displays in the Text box", "Numbers NOT updated successfully" , "");
					
					
				}
			
			
		//Logout from 'Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
		
		
	}
	
	
	
	/* 
	 * Author: Supraja
	 * TFS ID:12817
	 * @CreationDate: 27/03/2014
	 * BUCA - CCP - Center Management - Ensure Selected Date works
	 */


	@Test()
	public void CCP_CM_CenterCapacity_SelectedDate() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

/*		// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); 
*/
		/*// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPPortal();
		Thread.sleep(2000);

		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		
		Thread.sleep(1000);
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		
		Thread.sleep(1000);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		Thread.sleep(1000);
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));
		Thread.sleep(1000);
		
	/*	CCP_BusinessComponents.changeCenter("Name",getTestData("CCP_TD_srchText"));*/
		
		Thread.sleep(2000);

		//Verify Landing Page
		//COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");
		
		//Click on Center Management link
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_61"));
		
		Thread.sleep(2000);
		
		//Click on Center Capacity link
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_01"));
		
		Thread.sleep(2000);
		
		// Page should render with Center Capacity 
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_CM_OL_02"), "Center Capacity");
		

		//Change date
		//COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_09"));
		//COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_10"));
		
		Thread.sleep(5000);
		
		//Test data for selecting the date from the data picker
		String resDate=getTestData("CCP_TD_CPDate");
		String dateArray[]=resDate.split("/");
		String date=dateArray[1];
		if(date.startsWith("0")){
			
			date=date.substring(1);
		}
		int month=Integer.parseInt(dateArray[0]);
		String objArray[]={getTestObject("CCP_OLP_01"),getTestObject("CCP_OLP_02")};
		String dataArray[]={date};
		
		//CLick Calendar
		//COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_08"));
		Thread.sleep(4000);
		Utility.selectDate(month,objArray,dataArray);
			
		
		
		//Change numbers in Capacity
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_03"), getTestData("CCP_TD_CPInfant"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_04"), getTestData("CCP_TD_CPToddler"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_05"), getTestData("CCP_TD_CPPreschool"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_06"), getTestData("CCP_TD_CPSchoolAge"));
		
		//Click Save Changes
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_07"));
		Thread.sleep(2000);
		
		//Once the page reloads navigate to the date you chose and verify that the date is correct.
			//if(COMMON_METHODS.getText(getTestObject("CCP_CM_OL_08"),"value").contains(("4/1/2014")))
				
			if("4/1/2014".contains(COMMON_METHODS.getText(getTestObject("CCP_CM_OL_08"))))
				{
					REPORTER.LogEvent(TestStatus.PASS, "Verifing the entered Date is correct", "Date is Correct" , "");
				}
				else
				{
					REPORTER.LogEvent(TestStatus.PASS, "Verifing the entered Date is NOT correct", "Date is NOT Correct" , "");
				}
					
		//Logout from 'Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
		
		
	}  
	
	
	/* 
	 * Author: Supraja
	 * TFS ID:12818
	 * @CreationDate: 28/03/2014
	 *  BUCA - CCP - Center Management - Ensure Selected Date range works
	 */


	@Test()
	public void CCP_CM_CenterCapacity_SelectedDateRange() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser 
		CCP_BusinessComponents.CCP_LaunchBrowser(); 

		// Login CareCenter Portal 
		CCP_BusinessComponents.CCP_Login(); */
		
		/*// Launch the browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPPortal();
		Thread.sleep(2000);

		/*COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));*/

		CCP_BusinessComponents.changeCenter("Name",getTestData("CCP_TD_srchText"));
		
		//Verify Landing Page
		//COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");
		
		Thread.sleep(2000);
		
		//Click on Center Management link
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_61"));
		
		Thread.sleep(2000);
		
		//Click on Center Capacity link
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_01"));
		
		Thread.sleep(3000);
		
		// Page should render with Center Capacity 
		COMMON_METHODS.VerifyTextPresent(getTestObject("CCP_CM_OL_02"), "Center Capacity");
		

		//Change date
		//COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_09"));
		//COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_10"));
		
		Thread.sleep(5000);
		
		//Change numbers in Capacity
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_03"), getTestData("CCP_TD_CPInfant"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_04"), getTestData("CCP_TD_CPToddler"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_05"), getTestData("CCP_TD_CPPreschool"));
		COMMON_METHODS.editAField(getTestObject("CCP_CM_OL_06"), getTestData("CCP_TD_CPSchoolAge"));
		
		Thread.sleep(2000);
		
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_11"));
		
		Thread.sleep(2000);
		
		//CLick Calendar
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_12"));		
		this.DateRange(getTestData("CCP_TD_Dateofreservation1"));
		
		//CLick Calendar
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_13"));
		this.DateRange(getTestData("CCP_TD_Dateofreservation2"));
		
		Thread.sleep(2000);
		
		//Click Save Changes
		COMMON_METHODS.clickElement(getTestObject("CCP_CM_OL_07"));
		Thread.sleep(2000);
		
		//Once the page reloads navigate to the date you chose and verify that the date is correct.
			//if(COMMON_METHODS.getText(getTestObject("CCP_CM_OL_08"),"value").contains(("4/1/2014")))
				
			if("4/1/2014".contains(COMMON_METHODS.getText(getTestObject("CCP_CM_OL_08"))))
				{
					REPORTER.LogEvent(TestStatus.PASS, "Verifing the entered Date is correct", "Date is Correct" , "");
				}
				else
				{
					REPORTER.LogEvent(TestStatus.PASS, "Verifing the entered Date is NOT correct", "Date is NOT Correct" , "");
				}
					
		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
		
		
	}  
	
	private void DateRange(String resDate) throws IOException, Exception
	{
		
		//Test data for selecting the date from the data picker
		String dateArray[]=resDate.split("/");
		String date=dateArray[1];
		if(date.startsWith("0")){
			
			date=date.substring(1);
		}
		int month=Integer.parseInt(dateArray[0]);
		String objArray[]={getTestObject("CCP_OLP_01"),getTestObject("CCP_OLP_02")};
		String dataArray[]={date};
		
		
		Thread.sleep(4000);
		Utility.selectDate(month,objArray,dataArray);
		
	}
		
	/* 
	 * @author: Kiran G
	 * TFS ID:10209: BUCA - CCP - Ensure a Care Center Employer can view Help
	 * TFS ID:10191: BUCA - CCP - Help - Ensure page loads correctly and matches UX
	 * TFS ID:10416: BUCA - CCP - Home - Ensure links navigate correctly
	 * TFS ID:20324: CCP - Navigate to Reservations Page
	 * TFS ID:7321:BUCA - CCP Reservations - Verify a user can view the Reservations Page
	 * @CreationDate: 29/03/2014
	 */


	@Test()
	public void CCP_Navigation() throws Exception {


		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser
		CCP_BusinessComponents.CCP_LaunchBrowser();

		// Login CareCenter Portal
		CCP_BusinessComponents.CCP_Login();*/
		
		// Launch the browser
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		Thread.sleep(2000);

		//Click on Help link in the navigation bar
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_31"));

		//Verify the text on the Help page
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_32"), "Help");
		
		//Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_38"));

		//Click on Day Sheet link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_33"));

		//Click on Emergency Contact link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_34"));

		//Click on Allergy & Alert link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_35"));

		//Click on Sign-In link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_36"));

		//Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		if(COMMON_METHODS.driver.getTitle().contains("Care Sessions - Back-Up Care Center"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Care Sessions: page is displayed", "Care Sessions: page is displayed", "");
		}

		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Care Sessions: page is displayed", "Care Sessions: page is NOT displayed", "");
		}

		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	/* 
	 * @author: Kiran G
	 * TFS ID:11021: BUCA - CCP - Landing Page - Ensure the date navigation arrows for Confirmed Reservations work
	 * TFS ID:11022: BUCA - CCP - Landing Page - Ensure the date navigation arrows for Daily Reservation Stats work
	 * TFS ID:7313: BUCA - CCP Ensure Footer Links work
	 * @CreationDate: 31/03/2014
	 */


	@Test()
	public void CCP_Resv_Links() throws Exception {


		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("CCP"), "TD_CCP");

		/*// Launch the browser
		CCP_BusinessComponents.CCP_LaunchBrowser();

		// Login CareCenter Portal
		CCP_BusinessComponents.CCP_Login();*/
		
		// Launch the browser
		//Utility.launchBrowser(getTestData("CCP_TD_URL"));
		/*String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
		// Login CareCenter Portal
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);*/
		this.loginCCPPortal();
		Thread.sleep(2000);

		//Click on Previous week link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_39"));

		Thread.sleep(2000);
		
		//Click on Next week link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_40"));

		Thread.sleep(2000);
		
		//Click on Daily Reservation Previous link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_41"));
		
		Thread.sleep(2000);

		//Click on Daily Reservation Next link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_42"));
		
		Thread.sleep(2000);

		//Click on Privacy Policy Link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_43"));
		
		Thread.sleep(2000);

		//Verify that the Privacy page is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_32"), "Privacy Policy");
		
		Thread.sleep(2000);

		//Click on Terms of Use Link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_44"));

		Thread.sleep(2000);
		
		//Verify that the Terms of Use page is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_32"), "Terms of Uses");

		Thread.sleep(2000);
		
		//Click on Trademark Notice Link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_45"));
		
		Thread.sleep(2000);

		//Verify TrademarkNotice text in the TrademarkNotice page
		//businessComponents.EP_verifyText("Trademark Notice", "Trademark Notice","h1");
		if(COMMON_METHODS.driver.getTitle().equals("Trademark - Back-Up Care Center")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Trademark' page is displayed.", "'Trademark' page is displayed.", "");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Trademark' page is displayed.", "'Trademark' page is not displayed.", "");
		}

		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}	

	/* @author: Kiran G
	 * TFS ID:12585: BUCA - CCP - Landing - Ensure the Change Care Center functions
	 * @CreationDate: 31/03/2014
	 */


	@Test()
	public void CCP_Change_CenterLink() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		
		this.loginCCPPortal();
		Thread.sleep(2000);

		//Verify Landing Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

		//Click on Change location link
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));

		//Click on Center Name Radiobutton
		COMMON_METHODS.radioButton(getTestObject("GCCP_OL_46"));

		//Enter Search Text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));

		//Click on Search button
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));

		//Clear the Textbox field
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),"");

		//Click on Center Number Radiobutton
		COMMON_METHODS.radioButton(getTestObject("KCCP_OL_25"));

		//Enter Search Text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),"1175");

		//Click on Search button
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));

		//Clear the Textbox field
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),"");

		//Click on State Radiobutton
		COMMON_METHODS.radioButton(getTestObject("KCCP_OL_26"));

		//Enter Search Text 
		COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),"IN");

		//Click on Search button
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));
		
		Thread.sleep(2000);

		//Click on Select link
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

		//Verify that the site now shows the selected center
		if(COMMON_METHODS.getText(getTestObject("D_CCP_OL_2")).contains("2218 - Bright Horizons at Leopardstown - UAT"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Selected center is displayed", "Selected center is displayed", "");
		}
		else
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Selected center is displayed", "Selected center is NOT displayed", "");
		}

		//Logout from 'Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	
private void loginCCPPortal() throws IOException, Exception {
	//Verify Login Page displayed
			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login - Back-Up Care Center")){
				
			String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		
			// Login CareCenter Portal
			Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
			
		}
	}
	
}
