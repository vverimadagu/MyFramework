package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class EP_Reservation_step3_Test extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	
	
	//Reading Test Objects from Data excel 
	/*static{
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
			 * Test Case #12114: 
			 * BUCA - Reservation Wizard - Step3 - ensure 'Map Directions' link functions correctly
			 * 
			 */
			
			@Test @Parameters("client8")
			public void EP_ResStep3MapDirections(String employer) throws Exception {
		
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				System.out.println("Inside - " + methodName);
						
				//Read testdata for based on client 1
				//readTestData(getDataTablePath("EP"), "TD_EP8");
				
				
				this.loginEPPortal(employer);
				/*businessComponents.EP_LaunchBrowser();
				
				//login employee portal
				businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.resstep3.userName"), getTestData("TD_PWD"));
				*/
				
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
				
				/*//Select Date of reservation
				COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation7"));
				
				businessComponents.EP_ReservationWhenandWhere();*/
				
				String[] careDates = {getTestData("TD_Dateofreservation7")};
				String actions[] = {"Locations","Continue",null};
				businessComponents.EP_ReservationWhenandWhere(careDates,actions);
				
				
				//Verify the wizard moves to step 3.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
				
				//Click 'Center - based Care' button at the top of 'Available Care Options' section
				COMMON_METHODS.clickElement(getTestObject("OL_90"));
				Thread.sleep(7000);
				
				//click on the Map Directions Link
				COMMON_METHODS.clickElement(getTestObject("ROL_23"));
				Thread.sleep(1000);
						
				COMMON_METHODS.clickElement(getTestObject("ROL_24"));
				//Thread.sleep(1000);
						
				COMMON_METHODS.switchToPopup();
						
				COMMON_METHODS.driver.close();
						
				COMMON_METHODS.switchToNormal();
						
				Utility.logout();
						
				//Log to reports 
				COMMON_METHODS.logToReportAfterPass(methodName); 
					
			}
				
				
			/**
			 * Test Case #12115: 
			 * BUCA - Reservation Wizard - Step3 - ensure 'View Full Profile' opens up Provider Profile pop-up
			 * 
			 * Test Case #12116: 
			 * BUCA - Reservation Wizard - Step3 - ensure 'Visit Center Website' link on Provider Profile pop-up functions correctly
			 * 
			 */
			
			@Test @Parameters("client8")
			public void EP_ResStep3ViewFullProfile(String employer) throws Exception {
		
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				System.out.println("Inside - " + methodName);
					
				//Read testdata for based on client 1
				//readTestData(getDataTablePath("EP"), "TD_EP8");
				
				this.loginEPPortal(employer);
				/*businessComponents.EP_LaunchBrowser();
				
				//login employee portal
				businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));*/
				
				
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
				//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation1"));
				
				//businessComponents.EP_ReservationWhenandWhere();
				
				String[] careDates = {getTestData("TD_Dateofreservation1")};
				String actions[] = {"Locations","Continue",null};
				businessComponents.EP_ReservationWhenandWhere(careDates,actions);
				
				
				//Verify the wizard moves to step 3.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
				
				//Click 'Center - based Care' button at the top of 'Available Care Options' section
				COMMON_METHODS.clickElement(getTestObject("OL_90"));
				Thread.sleep(7000);
				
				//click on the View Full Profile Link
				COMMON_METHODS.clickElement(getTestObject("ROL_25"));
				Thread.sleep(1000);
				
				//Switching to the popup which is opened.
				COMMON_METHODS.switchToPopup();
				Thread.sleep(1000);
				
				//verify the text in the PopUp
				if(COMMON_METHODS.getText(getTestObject("ROL_26")).contains("Bright Horizons Centers provide back-up care"))
				{
					REPORTER.LogEvent(TestStatus.PASS, "Verify Text Present in the PopUp " + getTestObject("ROL_26") , "Verify Text Present in the PopUp " , "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify Text Not Present in the PopUp " + getTestObject("ROL_26"), "Verify Text Not Present in the PopUp ", "");				
			}		
			
				/*//closing the popup
				COMMON_METHODS.clickElement(getTestObject("ROL_27"));
				
				//moving the handle to the main window
				COMMON_METHODS.switchToNormal();
				
				//click on the View Full Profile Link
				COMMON_METHODS.clickElement(getTestObject("ROL_25"));
				Thread.sleep(1000);
				
				//Switching to the popup which is opened.
				COMMON_METHODS.switchToPopup();
				Thread.sleep(1000);*/
				
				//click on the Visit Center Website Link		
				COMMON_METHODS.clickElement(getTestObject("ROL_28"));	
				
				//logging out
				//businessComponents.logout();
				
				//Log to reports 
				COMMON_METHODS.logToReportAfterPass(methodName); 
			
			}
			
	

/**
 * Test Case #12116: 
 * BUCA - Reservation Wizard - Step3 - ensure 'Visit Center Website' link on Provider Profile pop-up functions correctly
 * 
 */

/*@Test
public void EP_ResStep3VisitCenterWebsite(ITestContext TC) throws Exception {

	String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	System.out.println("Inside - " + methodName);
		
	//Read testdata for based on client 1
	//readTestData(getDataTablePath("EP"), "TD_EP8");
	
	businessComponents.EP_LaunchBrowser(TC);
	
	//login employee portal
	businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
	
	
	//Click 'Reservations' link from top navigation menu
	COMMON_METHODS.clickElement(getTestObject("OL_10"));
	
	
	//Click 'Request a New Reservation' link from top navigation menu
	COMMON_METHODS.clickElement(getTestObject("OL_11"));
	
	//verify User is brought to Step 1 of the reservation
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
			
	businessComponents.EP_ReservationCareRecipients();
			
	//Verify the wizard moves to step 2.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
	
	//Select Date of reservation
	COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation2"));
	
	businessComponents.EP_ReservationWhenandWhere();
	
	//Verify the wizard moves to step 3.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
	
	//Click 'Center - based Care' button at the top of 'Available Care Options' section
	COMMON_METHODS.clickElement(getTestObject("OL_90"));
	Thread.sleep(7000);
	
	//click on the View Full Profile Link
	COMMON_METHODS.clickElement(getTestObject("ROL_25"));
	Thread.sleep(1000);
			
			
	//Switching to the popup which is opened.
	COMMON_METHODS.switchToPopup();
	Thread.sleep(1000);
			
	//verify the text in the PopUp
	if(COMMON_METHODS.getText(getTestObject("ROL_26")).contains("Bright Horizons Centers provide back-up care"))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verify Text Present in the PopUp ", "Verify Text Present in the PopUp " , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Text Present Not in the PopUp ", "Verify Text Present Not in the PopUp ", "");				
		}		
		
	//click on the Visit Center Website Link		
	COMMON_METHODS.clickElement(getTestObject("ROL_28"));
			
	Utility.logout();
	
	//Log to reports 
	COMMON_METHODS.logToReportAfterPass(methodName); 

}
	*/

			/**
			 * Author: Supraja
			 * Test Cases : 
			 * #12117: BUCA - Reservation Wizard - Step3 - ensure 'Exclude for Future Care' link opens up a pop-up for excluding a provider
			 * #12118: BUCA - Reservation Wizard - Step3 - ensure 'Cancel' button on the exclusion pop-up functions correctly
			 * #12119: BUCA - Reservation Wizard - Step3 - ensure Reason for Exclusion is required to submit an exclusion
			 * #12120: BUCA - Reservation Wizard - Step3 - ensure 'Submit' button on exclusion pop-up functions correctly
			 * 
			 */
			
			@Test @Parameters("client5")
			public void EP_ResStep3ExcludeforFutureCare(String employer) throws Exception {
			
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				System.out.println("Inside - " + methodName);
					
				//Read testdata for based on client 1
				//readTestData(getDataTablePath("EP"), "TD_EP5");
				
				//businessComponents.EP_LaunchBrowser();
				
				/*Utility.launchBrowser(getTestData("TD_EP_URL"));

				
				//Enter User Name
				COMMON_METHODS.editAField(getTestObject("OL_1"), ReadwritDataFromProps.props.getProperty("client5.resstep3.userName"));
			
				//Enter Password
				COMMON_METHODS.editPasswordField(getTestObject("OL_2"), getTestData("TD_PWD1"));
						
				//Click Sign In
				COMMON_METHODS.clickElement(getTestObject("OL_3"));*/
				this.loginEPPortal(employer);
				
							
				//Verify Home Page displayed
				if(BH_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Back-Up Care Advantage")){
							REPORTER.LogEvent(TestStatus.PASS, "Verify Home Page Displayed", "Home Page Displayed", ""); 
						}
							
				
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
				//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation1"));
				
				//businessComponents.EP_ReservationWhenandWhere();
				
				String[] careDates = {getTestData("TD_Dateofreservation")};
				String actions[] = {"Locations","Continue",null};
				businessComponents.EP_ReservationWhenandWhere(careDates,actions);
				
				//Verify the wizard moves to step 3.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
				Thread.sleep(7000);
				
				//Click 'Center - based Care' button at the top of 'Available Care Options' section
				//COMMON_METHODS.clickElement(getTestObject("OL_90"));
				//COMMON_METHODS.waitForObject(getTestObject("S_ExFCare3"), 120, "disappear");
				
				//Verify the center name
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_75"), "Bright Horizons at Redmond - Stabilization Testing");
				
				//click on the Exclude for Future Care	
				COMMON_METHODS.clickElement(getTestObject("S_ExFCare"));
				Thread.sleep(1000);
				
				//Ensure the system produces a popup for Excluded Provider Future Care
				COMMON_METHODS.VerifyTextPresent(getTestObject("S_ExFCare1"), "Exclude Provider For Future Care");
				
				//Click on "Cancel" button on the pop-up
				COMMON_METHODS.clickElement(getTestObject("S_ExFCare2"));
				
				//click on the Exclude for Future Care	
				COMMON_METHODS.clickElement(getTestObject("S_ExFCare"));
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("S_ExFCare4"), false);
				
				//Click on "Exclude due to location" Radio Button
				COMMON_METHODS.clickElement(getTestObject("S_ExFCare5"));
				
				//Click on "Click on "Exclude due to a bad experience or other reason" Radio Button
				COMMON_METHODS.clickElement(getTestObject("S_ExFCare6"));
				
				//Enter reason Comments
				COMMON_METHODS.editAField(getTestObject("S_ExFCare7"), getTestData("TD_ReasonComments"));
				
				//Click on Submit
				COMMON_METHODS.clickElement(getTestObject("S_ExFCare4"));
				Thread.sleep(7000);
					
				//Utility.logout();
			
				//Log to reports 
				COMMON_METHODS.logToReportAfterPass(methodName); 
				}
			
			
			
			
			
			//Test Case #12129: BUCA - Reservation Wizard - Step3 - ensure user is able to go back to Step2 via 'Back' button
			@Test @Parameters("client8")
			public void EP_Step3ToStep2(String employer) throws Exception {
			
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				System.out.println("Inside - " + methodName);
				
				//Read testdata for based on client 1
				//readTestData(getDataTablePath("EP"), "TD_EP8");
				
				this.loginEPPortal(employer);
				/*// Launch the browser
					businessComponents.EP_LaunchBrowser();
				
				//login employee portal
				businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));*/
				
				//Click 'Reservations' link from top navigation menu
				COMMON_METHODS.clickElement(getTestObject("OL_10"));
				
				// Verifying Reservation Page
				COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"),"Reservations");
				
				//Click 'Request a New Reservation' link from top navigation menu
				COMMON_METHODS.clickElement(getTestObject("OL_11"));
				
				//verify User is brought to Step 1 of the reservation
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
				//String Str1 =  COMMON_METHODS.driver.findElement(By.xpath("//*[@id='formNewReservation']//li[2]//div[2]")).getText();
			
				//businessComponents.EP_ReservationCareRecipients();
				int CareRecipients = 1;
				int HealthStatus[] = {1};
				String selectForReason = getTestData("TD_ReasonForCare");
				businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
			
				// Verify the wizard moves to step 2.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
				
				//Select Date of reservation
				/*COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation4"));
				
				businessComponents.EP_ReservationWhenandWhere();*/
				
				String[] careDates = {getTestData("TD_Dateofreservation4")};
				String actions[] = {"Locations","Continue",null};
				businessComponents.EP_ReservationWhenandWhere(careDates,actions);
				
				//Verify the wizard moves to step 3.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
				
				//Click 'Back'
				COMMON_METHODS.clickElement(getTestObject("ROL_22"));
				Thread.sleep(7000);
				
				// Verify the wizard moves to step 2.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
				
				// Logouts Finally
				//businessComponents.logout();
				
				//Log to reports 
				COMMON_METHODS.logToReportAfterPass(methodName); 
				}

		
		/**
		 * Test Case #12121: 
		 * BUCA - Reservation Wizard - Step3 - ensure 'Set My Preference' dropdown contains all options
		 * 
		 */
		
		/*@Test
		public void EP_ResStep3SetMyPreferencee(ITestContext TC) throws Exception {
		
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
				
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP8");
			
			businessComponents.EP_LaunchBrowser(TC);
			
			//login employee portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
			
			
			//Click 'Reservations' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_10"));
			
			
			//Click 'Request a New Reservation' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_11"));
			
			//verify User is brought to Step 1 of the reservation
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
					
			businessComponents.EP_ReservationCareRecipients();
					
			//Verify the wizard moves to step 2.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
			
			//Select Date of reservation
			COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation5"));
			
			businessComponents.EP_ReservationWhenandWhere();
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			//Click 'Center - based Care' button at the top of 'Available Care Options' section
			COMMON_METHODS.clickElement(getTestObject("OL_90"));
			Thread.sleep(7000);
			
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "Acceptable");
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "1st Choice");
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "2nd Choice");
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "3rd Choice");
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "Unacceptable");
			
			
			businessComponents.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName); 
		
		}*/
		
		
		
		/**
		 * Test Case #12122: 
		 * BUCA - Reservation Wizard - Step3 - ensure 'Reserve Now' button functions correctly
		 * 
		 */
		
		@Test @Parameters("client8")
		public void EP_ResStep3ReserveNow(String employer) throws Exception {
		
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
					
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP8");
			Utility.launchBrowser(getTestData("TD_EP_URL"));
			
			this.loginEPPortal(employer);
			/*businessComponents.EP_LaunchBrowser();
			
			//login employee portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));*/
			
			
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
			/*COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation6"));
			
			businessComponents.EP_ReservationWhenandWhere();*/
			
			String[] careDates = {getTestData("TD_Dateofreservation6")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			//Click 'Center - based Care' button at the top of 'Available Care Options' section
			COMMON_METHODS.clickElement(getTestObject("OL_90"));
			Thread.sleep(7000);
			
			
			//Click on "Reserve Now" on a provider.
			COMMON_METHODS.clickElement(getTestObject("ROL_29"));
			
			Thread.sleep(6000);
			
			//Verify the wizard moves to step 4.	
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
			
			//Utility.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName); 
		
		}
		
		/**
		 * Test Case #12121: 
		 * BUCA - Reservation Wizard - Step3 - ensure 'Set My Preference' dropdown contains all options
		 * 
		 * Test Case #12123: 
		 * BUCA - Reservation Wizard - Step3 - ensure 'Center-Based Care' page loads correctly (UX doc)
		 * 
		 * 
		 */
		 
		
		String employer=null;
		@Test @Parameters("client8")
		public void EP_ResStep3CenterBasedCare(String employer) throws Exception {
		
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
				
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP8");
			
			//businessComponents.EP_LaunchBrowser();
			Utility.launchBrowser(getTestData("TD_EP_URL"));

			
			//Verify Employer
			businessComponents.EP_verifyEmployer(employer);
			//Accept policy
			businessComponents.EP_AcceptPolicyAndSubmit();
		
			//Registration
			//Create dynamic user name
			String userName = createDyanamicUserData();
			// Register a New User
						businessComponents.EP_SignUpUser(userName,employer,"resstep3");
			
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
			
			
			/*
			//Registration
			businessComponents.EP_SignUpUser(8);
			
			//Center Based Reservation
			businessComponents.EP_Registration();*/	
			
			//Login to Emp Portal
			/*String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client8.resstep3.userName"), getTestData("TD_PWD"),signInArray);*/
			
			/*//login employee portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));*/
			
			
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
			/*COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
			
			businessComponents.EP_ReservationWhenandWhere();*/
			
			String[] careDates = {getTestData("TD_Dateofreservation2")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			//Click 'Center - based Care' button at the top of 'Available Care Options' section
			COMMON_METHODS.clickElement(getTestObject("OL_90"));
			Thread.sleep(15000);
			
		    if(COMMON_METHODS.getText(getTestObject("ROL_30")).contains("HAVE A PREFERENCE ON A PROVIDER?"))
				{
				REPORTER.LogEvent(TestStatus.PASS, "Verifying Text Present in the CenterBased Page." + COMMON_METHODS.getText(getTestObject("ROL_30")), "Text Present in the CenterBased Page" , "");
				}
			else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying Text Present in the CenterBased Page."+ COMMON_METHODS.getText(getTestObject("ROL_30")), "Text Not Found in the CenterBased Page. ", "");				
				}	
			
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "Acceptable");
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "1st Choice");
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "2nd Choice");
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "3rd Choice");
			
			COMMON_METHODS.isOptionPresentInListBox(getTestObject("OL_91"), "Unacceptable");
			
			//businessComponents.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName); 
		
		}


			/**
			 * Test Case #12124: 
			 * BUCA - Reservation Wizard - Step3 - ensure 'In-Home Care' page loads correctly (UX doc)
			 * 
			 * TFS ID 12142:
			* BUCA - Reservation Wizard - Step3 - ensure page is loaded correctly (UX doc)
			* 
			*  12495 - 
			 * BUCA - Reservation Wizard - Step3 - ensure 'Requested Provider/Caregiver' & 'Provider not to use' is a textfield for user that has never had In-Home Care
			 * 
			 */
			 
			
		String employer1=null;
		@Test @Parameters("client5")
			public void EP_ResStep3InHomeCare(String employer1) throws Exception {
			
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				System.out.println("Inside - " + methodName);
					
				//Read testdata for based on client 1
				//readTestData(getDataTablePath("EP"), "TD_EP5");
				
				//businessComponents.EP_LaunchBrowser();
				
				//Launch EP
				Utility.launchBrowser(getTestData("TD_EP_URL"));
				
				//Verify Employer
				businessComponents.EP_verifyEmployer(employer1);
				//Accept policy
				businessComponents.EP_AcceptPolicyAndSubmit();
			
				/*//Registration
				businessComponents.EP_SignUpUser(5);
				
				//Center Based Reservation
				businessComponents.EP_Registration();*/
				
				//Registration
				//Create dynamic user name
				String userName = createDyanamicUserData();
				// Register a New User
							businessComponents.EP_SignUpUser(userName,employer1,"resstep3");
				
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
				businessComponents.EP_Registration(addCrData3_5Years,"No",employer1);
				
				//login employee portal
				//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.resstep3.userName"), getTestData("TD_PWD"));
				//Login to Emp Portal
				/*String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
				Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.resstep3.userName"), getTestData("TD_PWD"),signInArray);*/
				
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
				/*COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
				
				businessComponents.EP_ReservationWhenandWhere();*/
				
				String[] careDates = {getTestData("TD_Dateofreservation1")};
				String actions[] = {"Locations","Continue",null};
				businessComponents.EP_ReservationWhenandWhere(careDates,actions);
				
				//Verify the wizard moves to step 3.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
				
				//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
				COMMON_METHODS.clickElement(getTestObject("OL_108"));
				Thread.sleep(10000);
				
				// Verify the 'Requested Provider' text field
				if(COMMON_METHODS.isElementPresent("txtRequestedProviderName", "id")){
					REPORTER.LogEvent(TestStatus.PASS, "Verify 'Requested Provider' text field is displayed.", "'Requested Provider' text field is displayed." , "");
				}
				else{
					REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Requested Provider' text field is displayed.", "'Requested Provider' text field is not displayed." , "");
				}	
				
				//closing the popup which is comming in the In Home Poral.
				//COMMON_METHODS.clickElement(getTestObject("ROL_31"));
				
				//Verify Text In Home Care Options 
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_32"), "In-Home Care Options");
					
				//Verify Text In-Home Care Election
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_33"), "In-Home Care Election");
					
				//Verify Text Provider Preferences
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_34"), "Provider Preferences");
					
				//Verify Text Tell Us About The Care Environment
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_35"), "Tell Us About The Care Environment");
				
				
				//Verify continue button is disabled
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_84"), false);
				
				//Verify InHotel Care Options page displayed
				businessComponents.EP_ReservationInHomeCareOptions("HotelCareOptions");	
				
				//businessComponents.logout();
				
				//Log to reports 
				COMMON_METHODS.logToReportAfterPass(methodName); 
			
			}
			
						
			/** 
			 * @author LavaKumar
			 * TFS ID 12143:
			 * BUCA - Reservation Wizard - Step3 - ensure all required fields are validated and have error messages
			 * @version 
			 * @return 
			 * @param 
			 * @CreationDate: 12/03/2014
			 */
			
				@Test @Parameters("client5")
			   public void EP_ResStep3_InHome_HotelCare_ErrormessageValidation(String employer) throws Exception {
				
				String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
				System.out.println(methodName);
					
				//Read testdata for based on client 1
				//readTestData(getDataTablePath("EP"), "TD_EP5");
				
				//Launch Browser
					//businessComponents.EP_LaunchBrowser(); 
				
				//Launch EP
				/*Utility.launchBrowser(getTestData("TD_EP_URL"));
				
				//User logs into the portal
				//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
				
				//Login to Emp Portal
				String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
				Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.resstep3.userName"), getTestData("TD_PWD"),signInArray);
				*/
				
				this.loginEPPortal(employer);
				
				//Click 'Reservations' link from top navigation menu
					COMMON_METHODS.clickElement(getTestObject("OL_10"));
					
					
				//Click 'Request a New Reservation' link from top navigation menu
				COMMON_METHODS.clickElement(getTestObject("OL_11"));
				
				//verify User is brought to Step 1 of the reservation
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
				//Create Care Recipients Reservation
				//businessComponents.EP_ReservationCareRecipients();
				int CareRecipients = 1;
				int HealthStatus[] = {1};
				String selectForReason = getTestData("TD_ReasonForCare");
				businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
				
				//Verify the wizard moves to step 2.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
				
				
				/*COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation13"));
				//Create  WhenandWhere Reservation
				businessComponents.EP_ReservationWhenandWhere();*/
				
				String[] careDates = {getTestData("TD_Dateofreservation13")};
				String actions[] = {"Locations","Continue",null};
				businessComponents.EP_ReservationWhenandWhere(careDates,actions);
				
				//Verify the wizard moves to step 3.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
				
				//Verify continue button is disabled
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_84"), false);
				
				//Create InHome Hotel Care Options
				businessComponents.EP_ReservationInHomeCareOptions("HotelCareOptions");		
				
				//Ensure the system moves to Reservation Step 4
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
				
				//Click 'Back'
				COMMON_METHODS.clickElement(getTestObject("ROL_22"));
				Thread.sleep(9000);
				
				//Select 'Hotel' radio button for Location type
				COMMON_METHODS.radioButton(getTestObject("RESV_TELL_06"));
				Thread.sleep(9000);
				
				//Verify continue button is Enabled
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_84"), true);
				
				// Enter the Hotel name in the "Name of the Hotel you need care at?*" field
				COMMON_METHODS.editAField(getTestObject("Step3_HotelName"), " ");
				
				//Verify continue button is disabled
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_84"), false);
				
				//Utility.logout();
				
				//Log to reports 
			   COMMON_METHODS.logToReportAfterPass(methodName);
				
				
			}





/** 
* @author Supraja
* TFS ID 12142:
* BUCA - Reservation Wizard - Step3 - ensure page is loaded correctly (UX doc)
* @version 
* @return 
* @param 
* @CreationDate: 12/03/2014
*/

/*
	@Test
  public void EP_ResStep3_InHome_HotelCare(ITestContext TC) throws Exception {
	
	

	String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
	System.out.println(methodName);
		
	//Read testdata for based on client 1
	//readTestData(getDataTablePath("EP"), "TD_EP5");
	
	//Launch Browser
		businessComponents.EP_LaunchBrowser(TC); 
	
	
	//User logs into the portal
	businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client83.userName"), getTestData("TD_PWD"));
	
	//Navigate To Reservation
	businessComponents.EP_NavigateToReservation();
	
	//Create Care Recipients Reservation
	businessComponents.EP_ReservationCareRecipients();
	
	COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
	//Create  WhenandWhere Reservation
	businessComponents.EP_ReservationWhenandWhere();
	
	//Verify the wizard moves to step 3.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
	
	
	//Verify InHotel Care Options page displayed
	businessComponents.EP_ReservationInHomeHotelCareOptions();	
		
	
	businessComponents.logout();
	
	//Log to reports 
  COMMON_METHODS.logToReportAfterPass(methodName);
	
	
}
*/



			/**
			 * Test Case #12125: 
			 * BUCA - Reservation Wizard - Step3 - ensure user is brought to a blank form when choosing In-Home care
			 *
			 * Test Case #12126: 
			 * BUCA - Reservation Wizard - Step3 - ensure 'Care Locations' dropdown populates with user Locations on In-Home Care page
			 * 
			 *Test Case #12127: 
			 * BUCA - Reservation Wizard - Step3 - ensure user is able to choose between 'Residential' and 'Hotel' locations
			 *
			 *Test Case #12128: 
			 * BUCA - Reservation Wizard - Step3 - ensure required fields are validated and error messages are displayed
			 * 
			 * 
			 */
			 
			
			@Test @Parameters("client5")
			public void EP_ResStep3InHomeCare_12125To28(String employer) throws Exception {
			
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				System.out.println("Inside - " + methodName);
					
				//Read testdata for based on client 1
				//readTestData(getDataTablePath("EP"), "TD_EP5");
				
				/*businessComponents.EP_LaunchBrowser();
				
				//login employee portal
				businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
				
				this.loginEPPortal(employer);
				
				
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
				/*COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation6"));
				
				businessComponents.EP_ReservationWhenandWhere();*/
				
				String[] careDates = {getTestData("TD_Dateofreservation6")};
				String actions[] = {"Locations","Continue",null};
				businessComponents.EP_ReservationWhenandWhere(careDates,actions);
				
				
				
				//Verify the wizard moves to step 3.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
				
				//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
				COMMON_METHODS.clickElement(getTestObject("OL_108"));
				Thread.sleep(7000);
				
				//closing the popup which is comming in the In Home Poral.
				//COMMON_METHODS.clickElement(getTestObject("ROL_31"));
				
				//verify Location Type Residential Radio Button is selected
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_36"), true);
				
				//verify Location Type Hotel Radio Button is selected
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_37"), false);
				
				//Select Location Type Hotel Radio Button
				COMMON_METHODS.radioButton(getTestObject("ROL_37"));
				Thread.sleep(9000);
				
				//Select Location Type Residential Radio Button
				COMMON_METHODS.radioButton(getTestObject("ROL_36"));
				Thread.sleep(9000);
			
				COMMON_METHODS.isOptionPresentInListBox(getTestObject("ROL_38"),"Home");
				
				//verify Care Location select box
				if("Home".equalsIgnoreCase(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("ROL_38"))))
					REPORTER.LogEvent(TestStatus.PASS, "Looking for regular care is Selected", "Looking for regular care is Selected", "");
				else
					REPORTER.LogEvent(TestStatus.FAIL, "Drop down box does not have saved list selected", "Looking for regular care is not Selected", "");
				
				//verify In-Home Care Election
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_39"), false);
				
				//Verify Do you have a requested Provider? is empty
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_40"), " ");
				
				//verify radio button CanStaffDifferentProvider No
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_41"), false);
					
				//verify radio button CanStaffDifferentProvider Yes
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_42"), false);
				
				//Verify Do you have a requested Caregiver? is empty
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_43"), " ");
					
				
				//verify radio button CanStaffDifferentCareGiver No
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_44"), false);
						
				//verify radio button CanStaffDifferentCareGiver Yes
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_45"), false);
					
					
				//Verify Is there a Provider you do not wish to use? is empty
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_46"), " ");
					
				//Verify SpecialInstructions is empty
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_47"), " ");
							
							
				//Verify Helpful instructions to find location is empty
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_48"), " ");
				
				//verify radio button Does anyone smoke in the care location No
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_49"), false);
								
				//verify radio button Does anyone smoke in the care location Yes
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_50"), false);
							
				//verify radio button Are there any pets in the care location? No
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_51"), false);
										
				//verify radio button Are there any pets in the care location? Yes
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_52"), false);
									
				//verify radio button Is anyone other than the care recipient(s) expected to be at the care location NO
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_53"), false);
										
				//verify radio button Is anyone other than the care recipient(s) expected to be at the care location Yes
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_54"), false);
				
				//check whether the 'Continue' button is enabled
				COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_84"), false);
					
				//Filling and checking whether the continue button is Enabled and going to the next page.
				businessComponents.EP_ReservationInHomeCareOptions("CareOptions");
				
				//businessComponents.logout();
				
				//Log to reports 
				COMMON_METHODS.logToReportAfterPass(methodName); 
			
			}



/**
 * Test Case #12126: 
 * BUCA - Reservation Wizard - Step3 - ensure 'Care Locations' dropdown populates with user Locations on In-Home Care page
 * 
 *//*
 

@Test
public void EP_ResStep3InHomeCare_CareLocationsDropDown(ITestContext TC) throws Exception {

	String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	System.out.println("Inside - " + methodName);
		
	//Read testdata for based on client 1
	//readTestData(getDataTablePath("EP"), "TD_EP5");
	
	businessComponents.EP_LaunchBrowser(TC);
	
	//login employee portal
	businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
	
	
	//Click 'Reservations' link from top navigation menu
	COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
	//Click 'Request a New Reservation' link from top navigation menu
	COMMON_METHODS.clickElement(getTestObject("OL_11"));
	
	//verify User is brought to Step 1 of the reservation
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
			
	businessComponents.EP_ReservationCareRecipients();
			
	//Verify the wizard moves to step 2.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
	
	//Select Date of reservation
	COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation9"));
	
	businessComponents.EP_ReservationWhenandWhere();
	
	//Verify the wizard moves to step 3.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
	
	//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
	COMMON_METHODS.clickElement(getTestObject("OL_108"));
	Thread.sleep(3000);
	
	//closing the popup which is comming in the In Home Poral.
	//COMMON_METHODS.clickElement(getTestObject("ROL_31"));
	
	
	//verify Care Location select box
	if("Home".equalsIgnoreCase(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("ROL_38"))))
		REPORTER.LogEvent(TestStatus.PASS, "Looking for regular care is Selected", "Looking for regular care is Selected", "");
	else
		REPORTER.LogEvent(TestStatus.FAIL, "Drop down box does not have saved list selected", "Looking for regular care is not Selected", "");
	
	COMMON_METHODS.isOptionPresentInListBox(getTestObject("ROL_38"),"Home");
	
	Utility.logout();
	
	//Log to reports 
	COMMON_METHODS.logToReportAfterPass(methodName); 

}*/


/**
 * Test Case #12127: 
 * BUCA - Reservation Wizard - Step3 - ensure user is able to choose between 'Residential' and 'Hotel' locations
 * 
 */
 

/*

@Test
public void EP_ResStep3InHomeCare_RadioResHotel(ITestContext TC) throws Exception {

	String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	System.out.println("Inside - " + methodName);
		
	//Read testdata for based on client 1
	//readTestData(getDataTablePath("EP"), "TD_EP5");
	
	businessComponents.EP_LaunchBrowser(TC);
	
	//login employee portal
	businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
	
	
	//Click 'Reservations' link from top navigation menu
	COMMON_METHODS.clickElement(getTestObject("OL_10"));
	
	
	//Click 'Request a New Reservation' link from top navigation menu
	COMMON_METHODS.clickElement(getTestObject("OL_11"));
	
	//verify User is brought to Step 1 of the reservation
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
			
	businessComponents.EP_ReservationCareRecipients();
			
	//Verify the wizard moves to step 2.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
	
	//Select Date of reservation
	COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation10"));
	
	businessComponents.EP_ReservationWhenandWhere();
	
	//Verify the wizard moves to step 3.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
	
	//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
	COMMON_METHODS.clickElement(getTestObject("OL_108"));
	Thread.sleep(3000);
	
	//closing the popup which is comming in the In Home Poral.
	//COMMON_METHODS.clickElement(getTestObject("ROL_31"));
	
	//verify Location Type Residential Radio Button is selected
	COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_36"), true);
		
	//verify Location Type Hotel Radio Button is selected
	COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_37"), false);
	
	//Select Location Type Hotel Radio Button
	COMMON_METHODS.radioButton(getTestObject("ROL_37"));
	
	//Select Location Type Residential Radio Button
	COMMON_METHODS.radioButton(getTestObject("ROL_36"));
	
	businessComponents.logout();
	
	//Log to reports 
	COMMON_METHODS.logToReportAfterPass(methodName); 

}

*/

/**
 * Test Case #12128: 
 * BUCA - Reservation Wizard - Step3 - ensure required fields are validated and error messages are displayed
 * 
 */
 
/*
@Test
public void EP_ResStep3InHomeCare_ErrorMessageValidation(ITestContext TC) throws Exception {

	String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	System.out.println("Inside - " + methodName);
		
	//Read testdata for based on client 1
	//readTestData(getDataTablePath("EP"), "TD_EP5");
	
	businessComponents.EP_LaunchBrowser(TC);
	
	//login employee portal
	businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
	
	
	//Click 'Reservations' link from top navigation menu
	COMMON_METHODS.clickElement(getTestObject("OL_10"));
	
	
	//Click 'Request a New Reservation' link from top navigation menu
	COMMON_METHODS.clickElement(getTestObject("OL_11"));
	
	//verify User is brought to Step 1 of the reservation
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
			
	businessComponents.EP_ReservationCareRecipients();
			
	//Verify the wizard moves to step 2.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
	
	//Select Date of reservation
	COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation6"));
	
	businessComponents.EP_ReservationWhenandWhere();
	
	//Verify the wizard moves to step 3.
	COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
	
	//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
	COMMON_METHODS.clickElement(getTestObject("OL_108"));
	Thread.sleep(3000);
	
	//closing the popup which is comming in the In Home Poral.
	//COMMON_METHODS.clickElement(getTestObject("ROL_31"));
	
	//check whether the 'Continue' button is enabled
	COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_84"), false);
	
	//Filling and checking whether the continue button is Enabled and going to the next page.
	businessComponents.EP_ReservationInHomeCareOptions();
	
	businessComponents.logout();
	
	//Log to reports 
	COMMON_METHODS.logToReportAfterPass(methodName); 

}

*/

		/** 
		* @author Supraja
		* @version 
		* @return 
		* @param 
		* @CreationDate: 10/03/2014
		*/
		
		@Test @Parameters("client5")
		public void EP_ResStep3_CRExpectedAtCareLocation(String employer) throws Exception {
			
		
			/**
			 * TFS ID 12140:
			 * BUCA - Reservation Wizard - Step3 - ensure if 'is anyone other can care recip. expected...' is 'yes', the 'Who & When' fields are validated and have error messages
			 * 
			 */
		
			String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println(methodName);
				
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP5");
			
			//Launch Browser
			//Utility.launchBrowser(getTestData("TD_EP_URL"));
			
			this.loginEPPortal(employer);
			
			/*//Launch Browser
			businessComponents.EP_LaunchBrowser(); 
			
			//User logs into the portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
			
			//Navigate To Reservation
			businessComponents.EP_NavigateToReservation();
			
			//Create Care Recipients Reservation
			//businessComponents.EP_ReservationCareRecipients();
			int CareRecipients = 1;
			int HealthStatus[] = {1};
			String selectForReason = getTestData("TD_ReasonForCare");
			businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
			
			/*COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation7"));
			//Create  WhenandWhere Reservation
			businessComponents.EP_ReservationWhenandWhere();*/
			
			String[] careDates = {getTestData("TD_Dateofreservation7")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			
			//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
			COMMON_METHODS.clickElement(getTestObject("OL_108"));
			Thread.sleep(7000);
			
			//closing the pop-up which is coming in the In Home Portal.
				//COMMON_METHODS.clickElement(getTestObject("ROL_31")); 
					
			//Select 'Residential' radio button for Location type
			COMMON_METHODS.radioButton(getTestObject("OL_109"));
			Thread.sleep(9000);
					
			//Check 'I am electing to use In-Home Care and DO NOT wish to use a Care Center' check box present under 'In-Home Care Election' section
			COMMON_METHODS.checkBox(getTestObject("OL_110"), "check");
					
			//Select 'No' radio button for 'Does anyone smoke in the care location?'
			COMMON_METHODS.radioButton(getTestObject("OL_111"));
					
			//Select 'No' radio button for 'Are there any pets at the care location?'
			COMMON_METHODS.radioButton(getTestObject("OL_112"));
				
			//Select 'Yes' radio button for 'Is anyone other than the care recipient(s) expected to be at the care location?'
			COMMON_METHODS.radioButton(getTestObject("Step3AnyCRPresent_01"));
			
			//Ensure Who and When fields appear  
			//COMMON_METHODS.VerifyTextPresent(getTestObject("Step3AnyCRPresent_02"), "Reservation_ReservationLocation_GuestsName");
			//COMMON_METHODS.VerifyTextPresent(getTestObject("Step3AnyCRPresent_03"), "Reservation_ReservationLocation_GuestsTime");

			COMMON_METHODS.isElementPresent("Reservation_ReservationLocation_GuestsName", "id"); 
			COMMON_METHODS.isElementPresent("Reservation_ReservationLocation_GuestsTime", "id"); 


			
			
			//Click Continue
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
			Thread.sleep(8000);
			
			//Code for "Who and When" fields validation
			COMMON_METHODS.verifyElementDisplayed(getTestObject("Step3_ValError"));
			
			
			//Verify Who and When fields accept text
			COMMON_METHODS.editAField(getTestObject("Step3AnyCRPresent_02"), getTestData("TD_TellAboutCareEnv_01"));
			COMMON_METHODS.editAField(getTestObject("Step3AnyCRPresent_03"), getTestData("TD_TellAboutCareEnv_02"));
			
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
						
			//businessComponents.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName); 
			
		}



		//Test Case #12130:  BUCA - Automation - Reservation Wizard - Step3 - Ensure user is able to continue to Step 4 via 'Continue' button
		@Test @Parameters("client8")
		public void EP_Step3ToStep4(String employer) throws Exception {
		
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
				
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP8");
			
			this.loginEPPortal(employer);
			/*// Launch the browser
			businessComponents.EP_LaunchBrowser();
			
			//login employee portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));*/
			
			//Click 'Reservations' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_10"));
			
			// Verifying Reservation Page
			COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"),"Reservations");
			
			//Click 'Request a New Reservation' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_11"));
			
			//verify User is brought to Step 1 of the reservation
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
			
			//businessComponents.EP_ReservationCareRecipients();
			int CareRecipients = 1;
			int HealthStatus[] = {1};
			String selectForReason = getTestData("TD_ReasonForCare");
			businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
		
			// Verify the wizard moves to step 2.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
			
			//Select Date of reservation
			//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation3"));
			
			//businessComponents.EP_ReservationWhenandWhere();
			//Create  WhenandWhere Reservation
			String[] careDates = {getTestData("TD_Dateofreservation3")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			//Click 'Center Based' button at the top of 'Available Care Options' section
			COMMON_METHODS.clickElement(getTestObject("OL_90"));
			Thread.sleep(15000);
			
			//Select first Provider by clicking Set My Preference
			COMMON_METHODS.listBoxSelect(getTestObject("OL_91"), getTestData("TD_FirstChoice"), "byVisibleText");
			
			//Ensure system allows the user to select 1st Choice from drop down.
			//COMMON_METHODS.VerifyTextPresent(getTestObject("OL_91"), getTestData("TD_FirstChoice"), "1st Choice");
			
			//Click 'Continue'
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
			Thread.sleep(8000);
			
			//Ensure the system moves to Reservation Step 4
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
			
			// Logouts Finally
			//Utility.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName); 
		
			}

			
	/**
	 * Test Case #12141: 
	 * BUCA - Reservation Wizard - Step3 - ensure user is able to add a location from In-Home care page
	 * 
	 * Test Case #12144: 
	 * BUCA - Reservation Wizard - Step3 - ensure if 'is Care Recip. allowed to order food...' is 'Yes', 'Amount Allowed' field appears and is validated with error 
	 * 
	 * Test Case #12145: 
	 * BUCA - Reservation Wizard - Step3 - ensure the 'Provider Preferences' section matches directly (UI should be the same) with In-Home & In-Hotel
	 * 
	 * Test Case #12146: 
	 * BUCA - Reservation Wizard - Step3 - ensure the 'Tell Us About The Care Environment' section matches directly (UI should be the same) with In-Home & In-Hotel
	 */
	 

	@Test @Parameters("client5")
	public void EP_ResStep3InHomeCare_Hotel_AddLocation(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPortal(employer);
		
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
		
		/*//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation10"));
		
		businessComponents.EP_ReservationWhenandWhere();*/
		
		String[] careDates = {getTestData("TD_Dateofreservation10")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(7000);
		
		//Select Location Type Hotel Radio Button
		COMMON_METHODS.radioButton(getTestObject("ROL_37"));
		Thread.sleep(9000);
		
		//verify radio button CanStaffDifferentProvider No
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_41"), false);
			
		//verify radio button CanStaffDifferentProvider Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_42"), false);
				
		//verify radio button CanStaffDifferentCareGiver No
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_44"), false);
				
		//verify radio button CanStaffDifferentCareGiver Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_45"), false);
			
		//Verify Is there a Provider you do not wish to use? is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_46"), " ");
			
		//Verify SpecialInstructions is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_47"), " ");
				
		//Verify Helpful instructions to find location is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_48"), " ");
		
		//verify radio button Does anyone smoke in the care location No
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_49"), false);
						
		//verify radio button Does anyone smoke in the care location Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_50"), false);
					
		//verify radio button Are there any pets in the care location? No
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_51"), false);
								
		//verify radio button Are there any pets in the care location? Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_52"), false);
							
		//verify radio button Is anyone other than the care recipient(s) expected to be at the care location NO
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_53"), false);
								
		//verify radio button Is anyone other than the care recipient(s) expected to be at the care location Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_54"), false);
		
		//Select Yes radio button for "Is the care recipient allowed to order food and/or snacks and beverages from room service or in-room mini bar?*"
		COMMON_METHODS.radioButton(getTestObject("ROL_56"));
		
		//Ensure the Amount Allowed box appears. . Also ensure the system will not allow completion without information filled in. 
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_57"), true);
		
		//Verify Do you have a requested Provider? is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_58"), " ");
		
		//Verify Do you have a requested Caregiver? is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_59"), " ");
		
		//Click "Add New Location"
		COMMON_METHODS.clickElement(getTestObject("ROL_60"));
				
		businessComponents.EP_AddNewLocation_Reservation(getTestData("TD_LocationName"));
				
		//Verify InHotel Care Options page displayed
		businessComponents.EP_ReservationInHomeCareOptions("HotelCareOptions");
		
		
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
	 * Test Case #12144: 
	 * BUCA - Reservation Wizard - Step3 - ensure if 'is Care Recip. allowed to order food...' is 'Yes', 'Amount Allowed' field appears and is validated with error 
	 * 
	 */
	 

	/*@Test
	public void EP_ResStep3InHomeCare_Hotel_allowtoorderfood(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		businessComponents.EP_LaunchBrowser(TC);
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
		
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		businessComponents.EP_ReservationCareRecipients();
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation4"));
		
		businessComponents.EP_ReservationWhenandWhere();
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(3000);
		
		//Select Location Type Hotel Radio Button
		COMMON_METHODS.radioButton(getTestObject("ROL_37"));
		
		//Select Yes radio button for "Is the care recipient allowed to order food and/or snacks and beverages from room service or in-room mini bar?*"
		COMMON_METHODS.radioButton(getTestObject("ROL_56"));
		
		//Ensure the Amount Allowed box appears. . Also ensure the system will not allow completion without information filled in. 
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_57"), true);
		
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}*/

	
	/**
	 * Test Case #12145: 
	 * BUCA - Reservation Wizard - Step3 - ensure the 'Provider Preferences' section matches directly (UI should be the same) with In-Home & In-Hotel
	 * 
	 */
	 

	/*@Test
	public void EP_ResStep3InHomeCare_Hotel_ProviderPreferences() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
		
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		businessComponents.EP_ReservationCareRecipients();
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation5"));
		
		businessComponents.EP_ReservationWhenandWhere();
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(3000);
		
		//Select Location Type Hotel Radio Button
		COMMON_METHODS.radioButton(getTestObject("ROL_37"));
		
		//Verify Do you have a requested Provider? is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_58"), " ");
		
		//verify radio button CanStaffDifferentProvider No
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_41"), false);
			
		//verify radio button CanStaffDifferentProvider Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_42"), false);
		
		//Verify Do you have a requested Caregiver? is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_59"), " ");
			
		
		//verify radio button CanStaffDifferentCareGiver No
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_44"), false);
				
		//verify radio button CanStaffDifferentCareGiver Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_45"), false);
			
			
		//Verify Is there a Provider you do not wish to use? is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_46"), " ");
			
		//Verify SpecialInstructions is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_47"), " ");
		
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}*/

	
	/**
	 * Test Case #12146: 
	 * BUCA - Reservation Wizard - Step3 - ensure the 'Tell Us About The Care Environment' section matches directly (UI should be the same) with In-Home & In-Hotel
	 * 
	 */
	/*@Test
	public void EP_ResStep3InHomeCare_Hotel_TellUsAboutTheCareEnvironment(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		businessComponents.EP_LaunchBrowser(TC);
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
		
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		businessComponents.EP_ReservationCareRecipients();
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation1"));
		
		businessComponents.EP_ReservationWhenandWhere();
		
		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(3000);
		
		//Select Location Type Hotel Radio Button
		COMMON_METHODS.radioButton(getTestObject("ROL_37"));
		
		//Verify Helpful instructions to find location is empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_48"), " ");
		
		//verify radio button Does anyone smoke in the care location No
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_49"), false);
						
		//verify radio button Does anyone smoke in the care location Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_50"), false);
					
		//verify radio button Are there any pets in the care location? No
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_51"), false);
								
		//verify radio button Are there any pets in the care location? Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_52"), false);
							
		//verify radio button Is anyone other than the care recipient(s) expected to be at the care location NO
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_53"), false);
								
		//verify radio button Is anyone other than the care recipient(s) expected to be at the care location Yes
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("ROL_54"), false);
		
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}
	*/
	
	
	
	
	/**
	 *Test Case #19123: 
	 *BUCA - Reservation Wizard - Step3 - ensure user is able to continue to Step4 via 'Continue' button (In-Home reservation)
	 * 
	 */
	@Test @Parameters("client5")
	public void EP_ResStep3InHomeCare_Step3toStep4(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
		
		this.loginEPPortal(employer);
		
		
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
				
		// Step 2 When and where
		COMMON_METHODS.editAField(getTestObject("OL_85"),getTestData("TD_Dateofreservation11"));

		businessComponents.ResWhenandWhereLocationInhome();
			
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(7000);
			
		//Filling and checking whether the continue button is Enabled and going to the next page.
		businessComponents.EP_ReservationInHomeCareOptions("CareOptions");
		
		Thread.sleep(8000);
			
		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}
	
	
	/**
	 * @author LavaKumar
	 *Test Case #21838: 
	 *Step 3: Center Based Care Option - user should be able to schedule care for adults at care centers on Step 3 of the reservation process, if applicable to the care center.
	 * 
	 */
	@Test @Parameters("client5")
	public void EP_ResStep3ScheduleAdultsCare(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		this.loginEPPortal(employer);
		
		/*businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
		
		
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
				
		// Step 2 When and where
		COMMON_METHODS.editAField(getTestObject("OL_85"),getTestData("TD_Dateofreservation4"));

		businessComponents.ResWhenandWhereLocationInhome();
			
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(7000);
			
		//Filling and checking whether the continue button is Enabled and going to the next page.
		businessComponents.EP_ReservationInHomeCareOptions("CareOptions");
			
		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();
		
		Utility.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 

	}
	
	/**
	 * Test Case #12134,12135,12133,12496,12316,12317
	 * BUCA - Reservation Wizard - Step3 - ensure 'Requested Caregiver' dropdown is populated with correct Caregivers
	 * BUCA - Reservation Wizard - Step3 - ensure 'Providers not to use' dropdown is populated with correct Providers
	 * BUCA - Reservation Wizard - Step3 - ensure 'Requested Provider' dropdown is populated with correct providers
	 * BUCA - Reservation Wizard - Step3 - ensure 'Requested Provider/Caregiver' & 'Provider not to use' is a dropdown 
	 * for user that have had In-Home Care before
	 * BUCA - Reservation Wizard - Step3 - ensure 'Exclude for Future Care' link only shows for 
	 * providers that have already completed a reservation for that user
	 *  BUCA - Reservation Wizard - Step3 - ensure information for Centers is displayed correctly
	 * @author Kiran G, Krishna Chaitanya
	 */
	@Test @Parameters("client5")
	public void EP_ResStep3InHomeReqProviderCaregiver(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		Utility.launchBrowser(getTestData("TD_EP_URL"));

		/*//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/

		this.loginEPPortal(employer);
		
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

		/*//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation3"));

		businessComponents.EP_ReservationWhenandWhere();*/
		
		String[] careDates = {getTestData("TD_Dateofreservation3")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		Thread.sleep(10000);
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(15000);

		// Verify the Requested Provider Listbox
		if(COMMON_METHODS.isElementPresent("ddlRequestedProviderId", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Requested Provider' dropdown is displayed.", "'Requested Provider' dropdown is displayed." , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Requested Provider' dropdown is displayed.", "'Requested Provider' dropdown is not displayed." , "");
		}		

		// Verify the Requested Caregiver Listbox
		if(COMMON_METHODS.isElementPresent("ddlRequestedCareGiverId", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Requested Caregiver' dropdown is displayed.", "'Requested Caregiver' dropdown is displayed." , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Requested Caregiver' dropdown is displayed.", "'Requested Caregiver' dropdown is not displayed." , "");
		}		

		// Verify the Providers you wish not to use
		if(COMMON_METHODS.isElementPresent("Reservation_ReservationPreferences_0__ExcludedProviderId", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Providers not to use' dropdown is displayed.", "'Providers not to use' dropdown is displayed." , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Providers not to use' dropdown is displayed.", "'Providers not to use' dropdown is not displayed." , "");
		}		
		
		//Click 'Center Based' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_90"));
		Thread.sleep(200000);
		
		//Verify that the Exclude for Future Care link 
		COMMON_METHODS.VerifyTextPresent(getTestObject("KMA_77"), "Exclude for Future Care");
		
		//Click on View Full Profile link
		COMMON_METHODS.clickElement(getTestObject("ROL_25"));
		Thread.sleep(10000);
		
		//Verify the text on the popup displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("KMA_79"), "Ages Served");
		
		//Click on Cancel to close the pop up
		COMMON_METHODS.clickElement(getTestObject("KMA_78"));
		
		// Logout from employee portal
		//Utility.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	// ######################################################################################################################################################################
	// @ TFS ID: 12495 - BUCA - Reservation Wizard - Step3 - ensure 'Requested Provider/Caregiver' & 'Provider not to use' is a textfield for user that has never had In-Home Care
	// @ Author: Krishna Chaitanya Maringanti
	// @ Date  : 03-APR-2014
	// ######################################################################################################################################################################

	/*@Test
	public void EP_ResStep3_InHome_RequestedProviderTextFields() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		// Launch the Employee Portal
		businessComponents.EP_LaunchBrowser();
		
		// Verify Employer
		businessComponents.EP_verifyEmployer();
		
		// Accept policy
		businessComponents.EP_AcceptPolicyAndSubmit();
		
		//Create dynamic user name
		String userName = createDyanamicUserData();
					
		// SignUp User
		businessComponents.EP_SignUpUser(userName,5,"cbudc"+5);

		//Registration
		businessComponents.EP_Registration();

		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.cbudc5.userName"), getTestData("TD_PWD"));

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");

		businessComponents.EP_ReservationCareRecipients();

		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");

		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));

		businessComponents.EP_ReservationWhenandWhere();

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		Thread.sleep(10000);
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(15000);

		// Verify the 'Requested Provider' text field
		if(COMMON_METHODS.isElementPresent("txtRequestedProviderName", "id")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify 'Requested Provider' text field is displayed.", "'Requested Provider' text field is displayed." , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Requested Provider' text field is displayed.", "'Requested Provider' text field is not displayed." , "");
		}		

		// Logout from employee portal
		businessComponents.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}*/
	
	/**
	 * Test Case: 12138
	 * BUCA - Reservation Wizard - Step3 - ensure 'pet type' drop-down contains all options for In-Home Care
	 * 
	 */	
/*	
	@Test
	public void EP_ReservationWizardStep3_PetTypeDropdown() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
			
		//Read test data for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		
		// Launch browser
		businessComponents.EP_LaunchBrowser();
		
		// Login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
				
		// Step #1
		businessComponents.EP_ReservationCareRecipients();
		
		// Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation8"));

		// Step #2
		businessComponents.EP_ReservationWhenandWhere();

		// Close the 'No Centers Found' pop-up
		//COMMON_METHODS.clickElement(getTestObject("RESV_17"));

		// Click 'In-Home Care' button
		COMMON_METHODS.clickElement(getTestObject("RESV_18"));
		
		// Close the 'No Centers Found' pop-up
		//COMMON_METHODS.clickElement(getTestObject("RESV_19"));

		// Select 'Yes' of radio button of 'Are there any pets in the care location?**' 
		COMMON_METHODS.radioButton(getTestObject("RESV_20"));
		
		// Get the options from test data
		String strPTOptions = getTestData("TD_Pets");
		String[] arrOptions = strPTOptions.split(",");
		
		// Verify each option is present in the drop-down
		for (String option : arrOptions) {

			COMMON_METHODS.isOptionPresentInListBox(getTestObject("RESV_21"),option);
		}

		// Logout from Employee Portal
		businessComponents.logout();
		
		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName); 
	}
	*/
		/**
		 * Test Case: 12139
		 * BUCA - Reservation Wizard - Step3 - ensure if 'pet at this location' is selected yes, the information for pets is validated and error messages appear
		 * 
		 * Test Case: 12138
		 * BUCA - Reservation Wizard - Step3 - ensure 'pet type' drop-down contains all options for In-Home Care	 * 
		 * 
		 */	
		
		@Test @Parameters("client5")
		public void EP_ReservationWizardStep3_PetFieldsValidations(String employer) throws Exception {
	
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
		
					
			//Read test data for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP5");
			
			// Launch browser
			//businessComponents.EP_LaunchBrowser();
			
			/*Utility.launchBrowser(getTestData("TD_EP_URL"));
			
			// Login employee portal
			//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));
			
			//Login to Emp Portal
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.resstep3.userName"), getTestData("TD_PWD"),signInArray);
			*/
			
			loginEPPortal(employer);
			
			//Click 'Reservations' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_10"));
			
			//Click 'Request a New Reservation' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_11"));
					
			// Step #1
			//businessComponents.EP_ReservationCareRecipients();
			
			int CareRecipients = 1;
			int HealthStatus[] = {1};
			String selectForReason = getTestData("TD_ReasonForCare");
			businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
			
			/*// Select Date of reservation
			COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation9"));
	
			// Step #2
			businessComponents.EP_ReservationWhenandWhere();*/
			
			//businessComponents.EP_ReservationWhenandWhere();
			//Create  WhenandWhere Reservation
			String[] careDates = {getTestData("TD_Dateofreservation9")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			Thread.sleep(3000);
			
			// Close the 'No Centers Found' pop-up
			if(COMMON_METHODS.isElementPresent("//div[10]/div/a/span", "xpath")){
				COMMON_METHODS.clickElement(getTestObject("RESV_17"));
				Thread.sleep(3000);
			}
	
			// Click 'In-Home Care' button
			COMMON_METHODS.clickElement(getTestObject("RESV_18"));
			Thread.sleep(3000);
			
			// Close the 'No Centers Found' pop-up
			if(COMMON_METHODS.isElementPresent("//div[11]/div/a/span", "xpath")){
				COMMON_METHODS.clickElement(getTestObject("RESV_19"));
				Thread.sleep(3000);
			}
			
			//Check 'I am electing to use In-Home Care and DO NOT wish to use a Care Center' check box present under 'In-Home Care Election' section
			COMMON_METHODS.checkBox(getTestObject("OL_110"), "check");
	
			// Select 'Yes' of radio button of 'Are there any pets in the care location?**' 
			COMMON_METHODS.radioButton(getTestObject("RESV_20"));
			
			// Get the options from test data
			String strPTOptions = getTestData("TD_Pets");
			String[] arrOptions = strPTOptions.split(",");
			
			// Verify each option is present in the drop-down
			for (String option : arrOptions) {
	
				COMMON_METHODS.isOptionPresentInListBox(getTestObject("RESV_21"),option);
			}
			
			// Click 'Continue'
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
	
			// Verify the error messages displayed
			COMMON_METHODS.VerifyTextPresent(getTestObject("RESV_24"), "The PetTypeId field is required.");
			COMMON_METHODS.VerifyTextPresent(getTestObject("RESV_24"), "The Count field is required.");
			
			// Select the 'Dog' from the 'Pet Type' drop-down 
			COMMON_METHODS.listBoxSelect(getTestObject("RESV_21"), "Dog", "byVisibleText");
			
			// Select '1' from 'How Many?' drop-down   
			COMMON_METHODS.listBoxSelect(getTestObject("RESV_26"), "1", "byVisibleText");
			Thread.sleep(3000);
	
			// Enter the breed 
			COMMON_METHODS.editAField(getTestObject("RESV_23"), "Bulldog");
			
			// Click 'Remove Pet' link 
			COMMON_METHODS.clickElement(getTestObject("RESV_25"));
			Thread.sleep(5000);
			
			// Verify the 'breed' field is removed
			if(COMMON_METHODS.isElementPresent("id", "Reservation_ReservationLocation_PetList_0__Breeds")){
				REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Breed' field is removed.", "'Breed' field is not removed.","");
			}else{
				REPORTER.LogEvent(TestStatus.PASS, "Verify 'Breed' field is removed.", "'Breed' field is removed.","");
			}
			
			// Add another pet 
			businessComponents.addAnotherPet("Dog", "1","Bulldog");
			
			// Click 'Continue'
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
			
			// Logout from Employee Portal
			//Utility.logout();
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName); 
		}
		
		/**
		 * Test Case: 12137: BUCA - Reservation Wizard - Step3 - ensure user is 
		 * able to delete a pet for a location from In-Home Care
		 * 
		 * Test Case: 12136:BUCA - Reservation Wizard - Step3 - ensure user is 
		 * able to add a pet for a care location for In-Home Care
		 */	
		
		@Test @Parameters("client5")
		public void EP_ReservationWizardStep3_Add_Delete_Pet(String employer) throws Exception {
	
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
		
					
			//Read test data for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP1");
			/*
			// Launch browser
			businessComponents.EP_LaunchBrowser();
			
			// Login employee portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
			
			//Launch EP
			/*Utility.launchBrowser(getTestData("TD_EP_URL"));
					
			//Login to Emp Portal
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client5.resstep3.userName"), getTestData("TD_PWD"),signInArray);
			*/
			
			loginEPPortal(employer);
			//Click 'Reservations' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_10"));
			
			//Click 'Request a New Reservation' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_11"));
					
			// Step #1
			//businessComponents.EP_ReservationCareRecipients();
			
			int CareRecipients = 1;
			int HealthStatus[] = {1};
			String selectForReason = getTestData("TD_ReasonForCare");
			businessComponents.EP_ReservationCareRecipients(CareRecipients, HealthStatus, selectForReason,"Yes");
			
			// Select Date of reservation
			/*COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation8"));
	
			// Step #2
			businessComponents.EP_ReservationWhenandWhere();*/
			
			//Create  WhenandWhere Reservation
			String[] careDates = {getTestData("TD_Dateofreservation8")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			
			
			Thread.sleep(3000);
					
			//Select 'Residential' radio button for Location type
			COMMON_METHODS.radioButton(getTestObject("OL_109"));
			Thread.sleep(9000);
			
			//Check 'I am electing to use In-Home Care and DO NOT wish to use a Care Center' check box present under 'In-Home Care Election' section
			COMMON_METHODS.checkBox(getTestObject("OL_110"), "check");
					
			//Input in Special instructions
			COMMON_METHODS.editAField(getTestObject("MA_55"), "Test");
			
			//Input Location instructions
			COMMON_METHODS.editAField(getTestObject("MA_56"), "Test");
			
			//Select 'No' radio button for 'Does anyone smoke in the care location?'
			COMMON_METHODS.radioButton(getTestObject("OL_111"));
			
			// Select 'Yes' of radio button of 'Are there any pets in the care location?**' 
			COMMON_METHODS.radioButton(getTestObject("RESV_20"));
			
			// Select the 'Dog' from the 'Pet Type' drop-down 
			COMMON_METHODS.listBoxSelect(getTestObject("RESV_21"), "Dog", "byVisibleText");
			
			// Select '1' from 'How Many?' drop-down   
			COMMON_METHODS.listBoxSelect(getTestObject("RESV_26"), "1", "byVisibleText");
			Thread.sleep(3000);
	
			// Enter the breed 
			COMMON_METHODS.editAField(getTestObject("RESV_23"), "Test");
			
			// Click 'Remove Pet' link 
			COMMON_METHODS.clickElement(getTestObject("RESV_25"));
			Thread.sleep(5000);
			
			// Verify the 'breed' field is removed
			if(COMMON_METHODS.isElementPresent("Reservation_ReservationLocation_PetList_0__Breeds", "id")){
				REPORTER.LogEvent(TestStatus.FAIL, "Verify 'Breed' field is removed.", "'Breed' field is not removed.","");
			}else{
				REPORTER.LogEvent(TestStatus.PASS, "Verify 'Breed' field is removed.", "'Breed' field is removed.","");
			}
			
			//Click Add Another Pet link
	    	COMMON_METHODS.clickElement(getTestObject("MA_57"));
	    	
	    	// Select the 'Cat' from the 'Pet Type' drop-down 
			COMMON_METHODS.listBoxSelect(getTestObject("MA_58"), "Cat", "byVisibleText");
			
			// Select '3' from 'How Many?' drop-down   
			COMMON_METHODS.listBoxSelect(getTestObject("MA_59"), "3", "byVisibleText");
			Thread.sleep(3000);    	
			
			// Click 'Continue'
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
			
			// Logout from Employee Portal
			Utility.logout();
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName); 
		}
		
	
	
		/**
		 * Test Case #22710: 
		 * CSC BUCA - Edit Reservation - Step 3: Verify if Client Employee can update information for a Center Based Reservation
		 * @param TC 
		 * 
		 */


		String employer2=null;
		@Test @Parameters("client2")
		public void EP_EditReservation_UpdateInfoforCBRes(String employer2) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//businessComponents.EP_LaunchBrowser();
		
		//Launch EP
		//Utility.launchBrowser(getTestData("TD_EP_URL"));

		loginEPPortal(employer2);
		/*//Read testdata for based on client 1
		//readTestData(getDataTablePath("EP"), "TD_EP5");

		businessComponents.EP_LaunchBrowser();

		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.cbudc5.userName"), getTestData("TD_PWD"));
		Thread.sleep(3000);*/
		
		
		businessComponents.EP_CenterBasedReservation(employer2,getTestData("TD_Dateofreservation2"));
		
		//--------TO DO ---------Change center location details link is not visible

		//businessComponents.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


	}
	
	
			/**
			 * Test Case #13553
			 * BUCA - Reservation Wizard - Step3 - Special Programs - Select a center with Special Program
			 */
			
			
			String employer3=null;
			@Test @Parameters("client1")
			public void EP_ResStep3SpecialPrograms(String employer3) throws Exception {
			
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				System.out.println("Inside - " + methodName);
					
				//Read testdata for based on client 1
				//readTestData(getDataTablePath("EP"), "TD_EP1");
				
				//businessComponents.EP_LaunchBrowser();
				
				//Launch EP
				Utility.launchBrowser(getTestData("TD_EP_URL"));
				
				//Verify Employer
				businessComponents.EP_verifyEmployer(employer3);
				//Accept policy
				businessComponents.EP_AcceptPolicyAndSubmit();
			
				/*//Registration
				businessComponents.EP_SignUpUser(1);
				
				//Center Based Reservation
				businessComponents.EP_Registration();	*/
				
				//Registration
				//Create dynamic user name
				String userName = createDyanamicUserData();
				// Register a New User
							businessComponents.EP_SignUpUser(userName,employer3,"resstep3");
				
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
				businessComponents.EP_Registration(addCrData3_5Years,"No",employer3);
				
				//login employee portal
				//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));
				
				/*String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
				Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client1.resstep3.userName"), getTestData("TD_PWD"),signInArray);*/
				
				//Navigate to My First Reservation
				businessComponents.EP_NavigateToReservation();
				
				//Select Special Programs Radio Button shows.
				COMMON_METHODS.radioButton(getTestObject("CR_12"));
				
				//Verify Special Programs text
				businessComponents.EP_verifyText("Special Programs", "Special Programs","h2");
				
				//select A Special Program
				COMMON_METHODS.radioButton(getTestObject("CR_15"));
				
				//Select any Care Recipient, by checking check box next to name
				COMMON_METHODS.checkBox(getTestObject("OL_82"), "check");
				
				//Click 'Continue'
				COMMON_METHODS.clickElement(getTestObject("OL_84"));
				
				//Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
				COMMON_METHODS.radioButton(getTestObject("ROL_03"));
				
				//Select Distance from drop down
				COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
				
				//Select any Location from 'Location' drop down
				COMMON_METHODS.listBoxSelect(getTestObject("OL_89"), getTestData("TD_Location"), "byVisibleText");
				
				//Click 'Continue'
				COMMON_METHODS.clickElement(getTestObject("OL_84"));
				Thread.sleep(7000);
				
				//TO Do  Not able to proceed to step 3 It is giving error
				//SORRY, AN ERROR OCCURRED WHILE PROCESSING YOUR REQUEST.
							
				Utility.logout();
				
				//Log to reports 
				COMMON_METHODS.logToReportAfterPass(methodName); 
			
			}


			
			/**
			 * Test Case #14195: 
			 * BUCA - Reservation Wizard - Step3 - ensure if user selects a health status of 'ill' on step 1, Center-Based Care is disabled on step 3
			 */
			@Test @Parameters("client5")
			public void EP_ResStep3CenterBasedDisabledforill(String employer) throws Exception {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
					
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP5");
			
			this.loginEPPortal(employer);
			/*businessComponents.EP_LaunchBrowser();
			
			//login employee portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
			
			
			//Click 'Reservations' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_10"));
			
			
			//Click 'Request a New Reservation' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_11"));
			
			//verify User is brought to Step 1 of the reservation
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
					
			// ******* Care Recipients for Reservation ******************
			
			//Select a 'Reason for Care'
			COMMON_METHODS.listBoxSelect(getTestObject("OL_81"), getTestData("TD_ReasonForCare"), "byVisibleText");
			
			//Select any Care Recipient, by checking check box next to name
			COMMON_METHODS.checkBox(getTestObject("OL_82"), "check");
			
			COMMON_METHODS.radioButton(getTestObject("RES_ MildlyIll"));
			
			
			//Click 'Continue'
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
			
			// Step 2 When and where
			COMMON_METHODS.editAField(getTestObject("OL_85"),getTestData("TD_Dateofreservation5"));
			
			businessComponents.ResWhenandWhereLocationInhome();
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			
			//Verify In Home Button Enabled
			COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_108"), true);
					
			//Verify Center Based Button Enabled
			COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_90"), true);
							
			//Verify In Home Button is selected Default. 			
			COMMON_METHODS.verifyElementIsSelected(getTestObject("OL_108"), true);
			
			//Utility.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName);
				
			}



			/**
			 * Test Case #14196: 
			 * BUCA - Reservation Wizard - Step3 - ensure if user selects self as Care Recipient on step 1, Center-Based Care is disabled on step 3
			 */
			@Test @Parameters("client5")
			public void EP_ResStep3_SelfcareRecipient_CCDisabled(String employer) throws Exception {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
				
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP5");
			
			//Utility.launchBrowser(getTestData("TD_EP_URL"));
			this.loginEPPortal(employer);
			/*businessComponents.EP_LaunchBrowser();
			
			//login employee portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
			
			
			// Click 'Care Profile' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_25"));
			
			// Click link under 'Employee Profile' section
			String strEmployeeName = COMMON_METHODS.getText(getTestObject("OL_26"));
			COMMON_METHODS.clickElement(getTestObject("OL_26"));
			
			
			if(COMMON_METHODS.isElementPresent("selfCareLink", "id")){
			//Click 'Add Me As a Care Recipient' link
				COMMON_METHODS.clickElement(getTestObject("OL_115"));
			
				// Enter the 'Birth Date' of the employee 
				COMMON_METHODS.editAField(getTestObject("CP_27"), getTestData("TD_DOB1"));
				
				// Enter the 'Height' of the employee 
				COMMON_METHODS.editAField(getTestObject("CP_28"), getTestData("TD_Height"));
				
				// Enter the 'Width' of the employee 
				COMMON_METHODS.editAField(getTestObject("CP_29"), getTestData("TD_Weight"));
				
				Thread.sleep(7000);
				
				//Allergies to Medication**
				COMMON_METHODS.radioButton(getTestObject("CP_32"));
				
				// Other Allergies**
				COMMON_METHODS.radioButton(getTestObject("CP_33"));
				
				//Diagnosed Special Needs / Medical Conditions**
				COMMON_METHODS.radioButton(getTestObject("CP_34"));
				
				//Activity Restrictions**
				COMMON_METHODS.radioButton(getTestObject("CP_35"));
				
				//Food Restrictions**
				COMMON_METHODS.radioButton(getTestObject("CP_30"));
				
				//Food Allergies**
				COMMON_METHODS.radioButton(getTestObject("CP_31"));
				
				// Click 'Update Employee Profile' button
				COMMON_METHODS.clickElement(getTestObject("OL_40"));
					
			}
			//Click on 'Add' link for Authorized Contacts section
			COMMON_METHODS.clickElement(getTestObject("AuthorizedContacts"));
			
			//Food Allergies**
			COMMON_METHODS.radioButton(getTestObject("CP_15"));
			
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("RelationshipDD"), getTestData("TD_Relationship"), "byVisibleText");
			
			//Select Emergency Contact
			COMMON_METHODS.checkBox(getTestObject("EmergencyContact"), "check");
			
			//Click on the Update Authorized Contact
			COMMON_METHODS.clickElement(getTestObject("OL_69"));
			Thread.sleep(7000);

			
			//Click 'Reservations' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_10"));
			
			
			//Click 'Request a New Reservation' link from top navigation menu
			COMMON_METHODS.clickElement(getTestObject("OL_11"));
			
			//verify User is brought to Step 1 of the reservation
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
			// ******* Care Recipients for Reservation ******************
			
			//Select a 'Reason for Care'
			COMMON_METHODS.listBoxSelect(getTestObject("OL_81"), getTestData("TD_ReasonForCare"), "byVisibleText");
			
			//Select any Care Recipient, by checking check box next to name
			COMMON_METHODS.checkBox(getTestObject("ROL_71"), "check");
			
			//Select Health Status of the selected Care Recipient
			COMMON_METHODS.radioButton(getTestObject("ROL_72"));
			
			//Click 'Continue'
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
			
			// Step 2 When and where
			COMMON_METHODS.editAField(getTestObject("OL_85"),getTestData("TD_Dateofreservation12"));
			
			businessComponents.ResWhenandWhereLocationInhome();
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			
			//Verify In Home Button Enabled
			COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_108"), true);
				
			//Verify Center Based Button Enabled
			COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_90"), false);
						
			//Verify In Home Button is selected Default. 			
			COMMON_METHODS.verifyElementIsSelected(getTestObject("OL_108"), true);
			
			//Utility.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName);
				
			}


			/**
			 * Test Case #8564: 
			 * BUCA - Reservation Wizard - Step3 - ensure 'Search Criteria' section can be edited (brings user back to step 2)
			 */
			
			
			/**
			 * Test Case #8371: 
			 * BUCA - Reservation Wizard - Step3 -ensure search distance slider is functioning correctly 
			 * 
			 */
			
			
			@Test @Parameters("client5")
			public void EP_ResStep3_SrchCriteriaAndDistanceSldrEdited(String employer) throws Exception {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
					
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP5");
			
			this.loginEPPortal(employer);
			/*businessComponents.EP_LaunchBrowser();
			
			//login employee portal
			businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client5.userName"), getTestData("TD_PWD"));*/
			
			
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
			
			// Step 2 When and where
			/*COMMON_METHODS.editAField(getTestObject("OL_85"),getTestData("TD_Dateofreservation2"));
			
			businessComponents.EP_ReservationWhenandWhere();*/
			
			//Create  WhenandWhere Reservation
			String[] careDates = {getTestData("TD_Dateofreservation2")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			//Click on the Center based 
			COMMON_METHODS.clickElement(getTestObject("OL_90"));
			Thread.sleep(15000);
			
			//Click Edit At/Near a Location
			COMMON_METHODS.clickElement(getTestObject("ROL_73"));
			
			//Verify the wizard moves to step 2.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
			
			//Select Distance from drop down
			COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
			
			//Click 'Continue'
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
			Thread.sleep(7000);
			
			//Click on the Center based 
			COMMON_METHODS.clickElement(getTestObject("OL_90"));
			Thread.sleep(7000);
			
			//Click Date and Time Edit
			COMMON_METHODS.clickElement(getTestObject("ROL_74"));
			
			//Verify the wizard moves to step 2.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
			
			//Select Distance from drop down
			COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
			
			//Click 'Continue'
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
			//Thread.sleep(7000);
			
			//Click on the Center based 
			COMMON_METHODS.clickElement(getTestObject("OL_90"));
			Thread.sleep(7000);
			
			COMMON_METHODS.distanceSlider();
			Thread.sleep(5000);
			
			//Verify the center name
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_75"), "Rob's center");
			
			
			//businessComponents.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName);
			
			}
			
			/**
			 * Test Case #23862
			 * CSC BUCA - Care Locations - Provider Match & Display for In-Home Care - Verify if employee selects In-Home as the Backup Care option and answers questions on their Provider Preferences,
			 * perform provider match based on the care location State, City , Zip C
			 * Sanjeev Singh 19/04/2014 
			 */
			
			
			
			@Test @Parameters("client2")
			public void EP_DisplayForInHomeCare(String employer2) throws Exception {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);
					
			//Read testdata for based on client 1
			//readTestData(getDataTablePath("EP"), "TD_EP2");
			
			/*Utility.launchBrowser(getTestData("TD_EP_URL"));
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			
			// Login Provider Portal
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"), signInArray);//"TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/			//this.loginEPPortal(2);
			this.loginEPPortal(employer2);
			
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
			
			// Step 2 When and where
			/*COMMON_METHODS.editAField(getTestObject("OL_85"),getTestData("TD_Dateofreservation2"));
			
			businessComponents.EP_ReservationWhenandWhere();*/
			
			//Create  WhenandWhere Reservation
			String[] careDates = {getTestData("TD_Dateofreservation11")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);
			
			//Verify the wizard moves to step 3.
			COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
			
			// Click In Home 
			COMMON_METHODS.clickElement(getTestObject("OL_108"));
			Thread.sleep(5000);
			
			//Check if the application should drive the options displayed on Step 3 of a In-Home care option
			String loc = COMMON_METHODS.getSelectedValueFromListBox(getTestObject("ROL_38"));
			System.out.println("Drop down "+loc);
			
			//COMMON_METHODS.VerifyTextPresent(loc, getTestData("TD_Location"));
			if(loc.compareTo(getTestData("TD_Location")) == 0)
			{
				REPORTER.LogEvent(TestStatus.PASS, "Verifying Location" + loc, "Selected Location is: "+loc , "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verifying Location" + loc, "Location Verification Fails" , "");				
			}
			
		    Utility.logout();//	businessComponents.logout();
			
			//Log to reports 
			COMMON_METHODS.logToReportAfterPass(methodName);
			
			}

			
			private void loginEPPortal(String clientName) throws IOException, Exception {
				
				if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
					//Verify Login Page displayed
					
					//Login to Emp Portal
					String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
					Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(clientName + ".resstep3.userName"), getTestData("TD_PWD"),signInArray);
					/*businessComponents.LoginEmployeeportal(
							ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"userName"),
							getTestData("TD_PWD"));*/
				}
			}
			
			
}