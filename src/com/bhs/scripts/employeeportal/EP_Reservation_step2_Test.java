package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.INITIALIZE.TestStatus;
import com.bhs.util.Utility;

public class EP_Reservation_step2_Test extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	
	//Reading Test Objects from Data excel 
/*	static{
		try{
			readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/

	
	/** 
	* @author LavaKumar
	* @version 
	* @return 
	* @param 
	* @CreationDate: 04/03/2014
	*/ 
	
	/**
	 *Test Case #11847 
	 *BUCA - Reservation Wizard - Step2 - ensure all care recipient names are checked by default when page loads
	 * 
	 */

		String employer=null;
		@Test @Parameters("client")
		public void EP_Res_Step2_Care_Recepient_Checked(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

	
		// Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		/*businessComponents.EP_LaunchBrowser();
		*/
		//Launch EP
		Utility.launchBrowser(getTestData("TD_EP_URL"));
				
		this.employer = employer;
		
		//Verify Employer
		businessComponents.EP_verifyEmployer(employer);
		//Accept policy
		businessComponents.EP_AcceptPolicyAndSubmit();
	
		//Registration
		//Create dynamic user name
		String userName = createDyanamicUserData();
		// Register a New User
					businessComponents.EP_SignUpUser(userName,employer,"resstep2");
		
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
		//Center Based Reservation
		businessComponents.EP_Registration(addCrData3_5Years,"No",employer);
		
		//Login to Emp Portal
		/*String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client.resstep2.userName"), getTestData("TD_PWD"),signInArray);*/
		/*// login employee portal
				businessComponents.LoginEmployeeportal(
						ReadwritDataFromProps.props.getProperty("client.resstep2.userName"),
						getTestData("TD_PWD"));*/
					
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		// verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"),
				"Who Needs Care and Why?");
		
		String Str1 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='formNewReservation']//li[2]//div[2]")).getText();

		//businessComponents.EP_ReservationCareRecipients();
		
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");

		// Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Getting the Care Recipients Information
		String Str2 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='RecipientDateTimeInfo']//label")).getText();
		
		//Checking Whether the Care Recipients Checkbox is Selected
		WebElement Str3 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='Reservation_ReservationTimeOfCares_0__SelectableRecipientList_0__IsSelected']"));
		
		//Checking whether the care Recipients Select in the step1 is default selected in the step2
		if(Str1.contains(Str2) && Str3.isSelected())
		{
			REPORTER.LogEvent(TestStatus.PASS, "Care Recipients '"+ Str1 + "'is Present", "in Step2 '"	+ Str2 + "' And is Selected","");	
		}else 
		REPORTER.LogEvent(TestStatus.FAIL, "Care Recipients '"+ Str1 + "'is not Present", "in Step2 '"	+ Str2 + "'And is Selected","");
		
		
		//businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}
	
	/**
	 *Test Case #12106:
	 *BUCA - Reservation Wizard - Step2 - ensure that locations added without the optional name field filled are NOT saved to Locations
	 * 
	 */

	@Test @Parameters("client")
	public void EP_Res_Step2_AddLocation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		
	/*	businessComponents.EP_LaunchBrowser();
		
		// login employee portal
		businessComponents.LoginEmployeeportal(
				ReadwritDataFromProps.props.getProperty("client.resstep2.userName"),
				getTestData("TD_PWD"));*/
		
		this.loginEPPortal(employer);

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		// verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"),
				"Who Needs Care and Why?");
		
		String Str1 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='formNewReservation']//li[2]//div[2]")).getText();

		
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
		
/*		businessComponents.EP_ReservationCareRecipients();
*/
		// Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Getting the Care Recipients Information
		String Str2 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='RecipientDateTimeInfo']//label")).getText();
		
		//Checking Whether the Care Recipients Checkbox is Selected
		WebElement Str3 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='Reservation_ReservationTimeOfCares_0__SelectableRecipientList_0__IsSelected']"));
		
		//Checking whether the care Recipients Select in the step1 is default selected in the step2
		if(Str1.contains(Str2) && Str3.isSelected())
		{
			REPORTER.LogEvent(TestStatus.PASS, "Care Recipients '"+ Str1 + "'is Present", "in Step2 '"	+ Str2 + "' And is Selected","");	
		}else 
		REPORTER.LogEvent(TestStatus.FAIL, "Care Recipients '"+ Str1 + "'is not Present", "in Step2 '"	+ Str2 + "'And is Selected","");
		
		//selecting the date 
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation10"));
		
		
		try {
			//Select 'Start Time'
			COMMON_METHODS.clickElement(getTestObject("OL_86"));
			
			BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeFrom']/dl/dd[2]/div[2]/table/tbody/tr/td[3]")).click();
			BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			
			//Select 'End Time'
			COMMON_METHODS.clickElement(getTestObject("OL_87"));
			BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeTo']/dl/dd[2]/div[2]/table/tbody/tr/td[5]")).click();
			BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			
		} catch (Exception e) {
			REPORTER.catchException(e, "Enter Start Time / End Time");
		}
			
		//Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
		COMMON_METHODS.radioButton(getTestObject("ROL_03"));
		
		//Select Distance from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
		
		
		//Click "Add New Location"
		COMMON_METHODS.clickElement(getTestObject("ROL_10"));
		
		//adding the new location method
		businessComponents.EP_AddNewLocation_Reservation(" ");
		
		//Click 'Center - based Care' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_90"));
		
		//Verify the Address 
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_18"),"Bright Horizons at Cook County/Chicago CDC - Stabilization Testing");
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		
		//getting the address from the care profile and validating with the zip code.
		String Str4 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='pageContent']/div[9]/ul/li[2]")).getText();
		
		//New location is not present in the Care Profile
		if(Str4.contains(getTestData("TD_Zip1")))
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Address '"+ getTestData("TD_Zip1") + "'is Present", "in Care Profile '"	+ getTestData("TD_Zip1") + "' Address is Present","");	
		}else 
		REPORTER.LogEvent(TestStatus.PASS, "Address '"+ getTestData("TD_Zip1") + "'is not Present", "in Care Profile '"	+ getTestData("TD_Zip1") + "'Address is not Present","");
		
		
		//businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}
	
	/**
	 *Test Case #12107: 
	 *BUCA - Reservation Wizard - Step2 - ensure user can select to have care along a route
	 * 
	 */

	@Test @Parameters("client") 
	public void EP_ReservationStep2_AlongaRoute(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		//Utility.launchBrowser(getTestData("TD_EP_URL"));
		this.loginEPPortal(employer);
/*		businessComponents.EP_LaunchBrowser();
		
		// login employee portal
		businessComponents.LoginEmployeeportal(
				ReadwritDataFromProps.props.getProperty("client.resstep2.userName"),
				getTestData("TD_PWD"));
*/
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		// verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"),
				"Who Needs Care and Why?");
		
/*		businessComponents.EP_ReservationCareRecipients();
*/
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
		// Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
						
		//selecting the date 
	//	COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation2"));
		
		
		//System should allow you to select Along a Route and display Point A and Point B dropdowns.
		//businessComponents.EP_ReservationWhenandWhere_AlongRoute();
		
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation2")};
		String actions[] = {null,"Continue","AlongRoute"};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		//logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}
	
	/**
	 *Test Case #12108: 
	 *BUCA - Reservation Wizard - Step2 - ensure user is able to navigate back via 'Back' button
	 *
	 */
	
	@Test @Parameters("client")
	public void EP_ReservationStep2_NavBack( String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		this.loginEPPortal(employer);
		/*
		businessComponents.EP_LaunchBrowser();
		
		// login employee portal
		businessComponents.LoginEmployeeportal(
				ReadwritDataFromProps.props.getProperty("client.resstep2.userName"),
				getTestData("TD_PWD"));*/

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		// verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"),
				"Who Needs Care and Why?");
		
		String Str1 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='formNewReservation']//li[2]//div[2]")).getText();
		
		//String Str3 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='Reservation_ReservationCareRecipients_0__IsHealthy']"));
		
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
		/*
		
		businessComponents.EP_ReservationCareRecipients();
		*/
		// Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
								
		//Click 'Back'
		COMMON_METHODS.clickElement(getTestObject("ROL_22"));
		Thread.sleep(7000);
		
		String Str4 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='formNewReservation']//li[2]//div[2]")).getText();
		WebElement Str2 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='Reservation_ReservationCareRecipients_0__IsSelected']"));	
		//Checking whether the care Recipients Select in the step1 is default selected in the step2
		if(Str1.contains(Str4) && Str2.isSelected())
		{
			REPORTER.LogEvent(TestStatus.PASS, "Care Recipients '"+ Str1 + "'is Present and selected,Health Status Health is selected", "In Step1 '"	+ Str4 + "'is Present and selected,Health Status Health is selected","");	
		}else 
		REPORTER.LogEvent(TestStatus.FAIL, "Care Recipients '"+ Str1 + "'is not Present and not selected,Health Status Health is not selected", "In Step1 '"	+ Str4 + "'is not Present and not selected,Health Status Health is not selected","");
		
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_83"));
		
		
		if("Looking for regular care".equalsIgnoreCase(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_81"))))
			REPORTER.LogEvent(TestStatus.PASS, "Looking for regular care is Selected", "Looking for regular care is Selected", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Drop down box does not have saved list selected", "Looking for regular care is not Selected", "");
		
		//businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}
	
	/**
	 * TFS ID : 12109
	 * BUCA - Automation - Reservation Wizard - Step2 - Ensure user is able to Continue via the 'Continue' button
	 * 
	 */

	@Test @Parameters("client")
	public void EP_ReservationWizardStep2to3( String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
	
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		this.loginEPPortal(employer);
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client.resstep2.userName"), getTestData("TD_PWD"));
		*/
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
		/*		
		businessComponents.EP_ReservationCareRecipients();
		*/
		
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
		
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation3"));
		
		/*
		businessComponents.EP_ReservationWhenandWhere();
		*/
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation3")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	
	}
	
	/**
	 *Test Case #13656: 
	 *BUCA - Reservation Wizard - Step2 - ensure the date and the time fields are not pre-selected
	 * 
	 */

	@Test @Parameters("client")
	public void EP_ResStep2DateTimenotPreselected( String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		this.loginEPPortal(employer);
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client.resstep2.userName"), getTestData("TD_PWD"));*/
		
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		/*businessComponents.EP_ReservationCareRecipients();*/
		
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_85"), " ");
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_86"), " ");
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_87"), " ");
		
		/*
		 * Below tests covers TFS 8369
		 * BUCA - Reservation Wizard - Step 2 - Ensure user can not select a 
		 * date in the past for a reservation
		 * */
		
		//selecting the date 
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_PreviousDate"));
		
		//Click on Continue button
		 COMMON_METHODS.clickElement(getTestObject("OL_84"));
		 
		 try {
				if("Time of Care must not be in the past".equalsIgnoreCase(COMMON_METHODS.getText(getTestObject("CP_25")))){
					REPORTER.LogEvent(TestStatus.PASS, "Time of Care must not be in the past error message is displayed", "Time of Care must not be in the past error message is displayed", "");
				}
				else
					REPORTER.LogEvent(TestStatus.PASS, "Time of Care must not be in the past error message is not displayed", "Time of Care must not be in the past error message is not displayed", "");
			} catch (Exception e) {
				REPORTER.catchException(e, "Please select Recipient(s) for reservation is not displayed");
			}
		 
			/*
			 * End of test case TFS 8369
			 * */
			
			
			/*
			 * Below tests covers TFS 8339
			 * BUCA - Reservation Wizard - Step 2 - Ensure user can add another date via 
			 * 'Add Another Care Session' link
			 * */
			String[] careDates={getTestData("TD_Dateofreservation"),getTestData("TD_Dateofreservation1")};
			String actions[]={"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			/*
			 * End of test case TFS 8339
			 * */
			
			//Logout 
			//businessComponents.logout();
		
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName); 
	
	}
	
		
	/**
	 * #22884: 
	 * CSC BUCA :Step 2: Remove Care Session - BUCA Web Application should allow EMPLOYEE to remove a Care Session by clicking on ‘REMOVE CARE SESSION’ link.
	 * 
	 * Test Case #22885: 
	 * CSC BUCA :Step 2: Remove Care Session - BUCA Web Application should allow EMPLOYEE to remove a Care Session by clicking on ‘REMOVE CARE SESSION’ link.
	 * 
	 * TFS ID:22892
	  * CSC BUCA :Step 2: Remove Care Session - Verify BUCA web application validates
	  * the Step-2 reservation page if EMPLOYEE removes all care sessions & attempts to proceed to step-3
	 * 
	 */

	@Test @Parameters("client")
	public void EP_ReserStep3to2RemoveCareSession( String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
	
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		this.loginEPPortal(employer);
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client10.userName"), getTestData("TD_PWD"));*/
				
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		//businessComponents.EP_ReservationCareRecipients();
		
		int CareRecipients = 1;
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
		
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation7"));
				
		try {
			//Select 'Start Time'
			COMMON_METHODS.clickElement(getTestObject("OL_86"));
			
			BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeFrom']/dl/dd[2]/div[2]/table/tbody/tr/td[4]")).click();
			BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			
			//Select 'End Time'
			COMMON_METHODS.clickElement(getTestObject("OL_87"));
			BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeTo']/dl/dd[2]/div[2]/table/tbody/tr/td[5]")).click();
			BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			
		} catch (Exception e) {
			REPORTER.catchException(e, "Enter Start Time / End Time");
		}
							
		// click link Remove care Session
		COMMON_METHODS.clickElement(getTestObject("ROL_61"));
		Thread.sleep(5000);
		
		// Verify the 'Requested Provider' text field
		if(!COMMON_METHODS.isElementPresent("Reservation_ReservationTimeOfCares_0__ViewOnlyTextDateFrom", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Date is not displayed", "Reservation Date is not displayed" , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Reservation Date not is displayed", "Reservation Date is displayed" , "");
		}
		
		if(!COMMON_METHODS.isElementPresent("txtTimeFrom", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Start Time is not displayed", "Start Time is not displayed" , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Start Time not is displayed", "Start Time is displayed" , "");
		}
		
		if(!COMMON_METHODS.isElementPresent("txtTimeTo", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify End Time is not displayed", "End Time is not displayed" , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify End Time not is displayed", "End Time is displayed" , "");
		}
		
		// click Add Another Date Link
		COMMON_METHODS.clickElement(getTestObject("ROL_76"));
		Thread.sleep(5000);
		
		//Verify the Reservation Date is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_85"), " ");
				
		//Verify the Start Time is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_86"), " ");
						
		//Verify the End Time is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_87"), " ");
		
		//Select Date of reservation
		//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation7"));
		
		//businessComponents.EP_ReservationWhenandWhere();
		
		String[] careDates = {getTestData("TD_Dateofreservation7")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//Click 'Back'
		COMMON_METHODS.clickElement(getTestObject("ROL_22"));
		Thread.sleep(7000);
		
		// click link Remove care Session
		COMMON_METHODS.clickElement(getTestObject("ROL_61"));
		Thread.sleep(5000);
		
		// Verify the 'Requested Provider' text field
		if(!COMMON_METHODS.isElementPresent("Reservation_ReservationTimeOfCares_0__ViewOnlyTextDateFrom", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Date is not displayed", "Reservation Date is not displayed" , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Reservation Date not is displayed", "Reservation Date is displayed" , "");
		}
		
		if(!COMMON_METHODS.isElementPresent("txtTimeFrom", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Start Time is not displayed", "Start Time is not displayed" , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Start Time not is displayed", "Start Time is displayed" , "");
		}
		
		if(!COMMON_METHODS.isElementPresent("txtTimeTo", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify End Time is not displayed", "End Time is not displayed" , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify End Time not is displayed", "End Time is displayed" , "");
		}
						
		Utility.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	
	}
	
	/**
	  * TFS ID:22892
	  * CSC BUCA :Step 2: Remove Care Session - Verify BUCA web application validates
	  * the Step-2 reservation page if EMPLOYEE removes all care sessions & attempts to proceed to step-3
	  * @param TC
	  * @throws Exception
	  */
	/* @Test
	 public void EP_ReservationStep2_Rem_Care_Ses_Continue() throws Exception
	 {
	  String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
	  System.out.println(methodname);

	  //Read testdata for based on client 1
	  //readTestData(getDataTablePath("EP"), "TD_EP1");

	  //Launch Browser
	  businessComponents.EP_LaunchBrowser(); 

	  //User logs into the portal
	  businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client10.userName"), getTestData("TD_PWD"));

	  businessComponents.EP_NavigateToReservation();

	  //Create Care Recipients Reservation
	  businessComponents.EP_ReservationCareRecipients();

	  COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
	  //Create  WhenandWhere Reservation
	  try {
	   //Select 'Start Time'
	   COMMON_METHODS.clickElement(getTestObject("OL_86"));

	   BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeFrom']/dl/dd[2]/div[2]/table/tbody/tr/td[4]")).click();
	   BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();

	   //Select 'End Time'
	   COMMON_METHODS.clickElement(getTestObject("OL_87"));
	   BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeTo']/dl/dd[2]/div[2]/table/tbody/tr/td[5]")).click();
	   BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();

	  } catch (Exception e) {
	   REPORTER.catchException(e, "Enter Start Time / End Time");
	  }

	  Thread.sleep(2000);
	  //Click on Remove session
	  COMMON_METHODS.clickElement(getTestObject("MA_73")); 
	  Thread.sleep(3000);
	  
	  //Click on Continue button
	  COMMON_METHODS.clickElement(getTestObject("OL_84"));
	  
	  //Verify the text in the error message for invalid fields
	  businessComponents.verifyElementDisplayed(getTestObject("MA_69"));

	  //Logout from Employee portal
	  businessComponents.logout();

	  //Log to reports 
	  COMMON_METHODS.logToReportAfterPass(methodname); 
	 }
	*/ 
	private void loginEPPortal(String clientName) throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(clientName + ".resstep2.userName"), getTestData("TD_PWD"),signInArray);
			
			/*
			businessComponents.LoginEmployeeportal(
					ReadwritDataFromProps.props.getProperty("client.resstep2.userName"),
					getTestData("TD_PWD"));*/
		}
	}
	
}