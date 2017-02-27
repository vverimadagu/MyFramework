package com.bhs.scripts.employeeportal;

import java.io.IOException;
import java.util.Set;

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

public class EP_Benefit_Test extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	
	
	
	//Reading Test Objects from Data excel 
		static{
			try{
				readTestObject(getDataTablePath("EP"), "TO_EP");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		String employer=null;
		
		@Test @Parameters("client")
		public void EP_BenefitSignupUser(String employer) throws Exception {

			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("Inside - " + methodName);

			this.employer = employer;
			
			//launch browser
			Utility.launchBrowser(getTestData("TD_EP_URL"));
			
			//Verify Employer
			businessComponents.EP_verifyEmployer(employer);

			//Accept  policy and submit
			businessComponents.EP_AcceptPolicyAndSubmit();
			
			//Create dynamic user name
			String userName = createDyanamicUserData();
			
			//Sign Up
			businessComponents.EP_SignUpUser(userName,employer,"benefit");


			//Click 'Care Profile' link from top navigation menu
			Utility.clickLink(getTestObject("OL_25"));
			
			//Click on Employee Profile icon
			COMMON_METHODS.clickElement(getTestObject("OL_134"));
			
			businessComponents.EP_UpdateEmployeeprofile(employer);
			
			//User will be navigated to the Care Profile page with Green check mark on the employee profile icon
			COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_138"));
			
			//Click 'Add' link present in 'Care Recipients' section
			COMMON_METHODS.clickElement(getTestObject("OL_41"));
			
			//Test data for adding Care Recipients
			String addCrData[] = {
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
			
			businessComponents.addCareRecipients(addCrData, "No",employer);
			
			// Click 'Add Care Recipients' button
			COMMON_METHODS.clickElement(getTestObject("OL_57"));
			
			//User will be navigated to the Care Profile page with Green check mark on the care recipients icon
			COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_139"));
			
			//Click on Authorized Contacts icon
			COMMON_METHODS.clickElement(getTestObject("OL_136"));
			
			businessComponents.EP_AddAuthorizedContacts();

			//Log Pass to Result logs
			COMMON_METHODS.logToReportAfterPass(methodName);

		}

	/**
	 * BUCA - Automation - Benefits - Ensure links on left hand side of page
	 * display correctly TFS ID : 23378
	 * 
	 */
	@Test()
	public void EP_Benefits() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch browser
		//Utility.launchBrowser(getTestData("TD_EP_URL"));

		// login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props
		//		.getProperty("client5.userName"), getTestData("TD_PWD"));
		
		this.loginEPPoratl();

		// Click 'Benefit' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		// Verify 'Overview' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_02"), "Overview");

		// Verify 'Overview' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_03"),
				"What Is Back-up Care?");

		// Click 'Policies' link
		COMMON_METHODS.clickElement(getTestObject("BOL_04"));

		// Verify 'Policies' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_05"), "Policies");

		// Click Click 'Center-Based Child Care' link
		COMMON_METHODS.clickElement(getTestObject("BOL_06"));

		// Verify 'Center-Based Child Care' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_07"),
				"Center-Based Child Care");

		// Click 'In-Home Child Care' link
		COMMON_METHODS.clickElement(getTestObject("BOL_08"));

		// Verify 'In-Home Child Care' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_09"),
				"In-Home Child Care");

		// Click 'Adult/Elder Care' link
		COMMON_METHODS.clickElement(getTestObject("BOL_10"));

		// Verify 'Adult/Elder Care' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_11"),
				"Adult/Elder Care");

		// Click 'Special Programs' link
		COMMON_METHODS.clickElement(getTestObject("BOL_12"));

		// Click 'Special Programs' Page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_13"),
				"Special Programs");

		// Click 'Priority Access' link
		COMMON_METHODS.clickElement(getTestObject("BOL_14"));

		// Click 'Priority Access' Page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_15"),
				"Priority Access");
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);

		//businessComponents.logout();

	}

	/**
	 * BUCA - Automation - Benefits - Priority Access - Ensure 'Priority Access
	 * Provider Search' module is functioning correctly TFS ID : 23379
	 * 
	 */
	@Test()
	public void EP_BenefitsPriorityAccess() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);


		this.loginEPPoratl();
		
		//Navigate to the home page
		COMMON_METHODS.clickElement(getTestObject("OL_8"));

		// Click 'Benefit' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		Thread.sleep(1000);
		
		// Verify 'Overview' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_02"), "Overview");

		// Verify 'Overview' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_03"),
				"What Is Back-up Care?");

		// Click 'Priority Access' link
		COMMON_METHODS.clickElement(getTestObject("BOL_14"));

		// Verify 'Priority Access' Page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_15"),
				"Priority Access");

		// Check 'Priority Access' page has the Priority Access Provider Search
		// box
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_16"),
				"Priority Access Provider Search");

		// Check Search by Your Locations text is present
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_17"),
				"Search by your Locations");

		// Select Location from listbox drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_89"),
				getTestData("TD_Location"), "byVisibleText");

		// Check 'Distance' text is present
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_18"),
				"Search by Zip Code");

		// Enter 'Zipcode'
		COMMON_METHODS.editAField(getTestObject("BOL_19"),
				getTestData("TD_ZipCode"));

		// Check 'Distance' text is present
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_20"), "Distance:");

		// Select 'Distance' from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_21"),
				getTestData("TD_Distance"), "byVisibleText");

		// Click 'Priority Access' link
		COMMON_METHODS.clickElement(getTestObject("BOL_22"));

		// Check Center is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_23"), "Center One");

		//businessComponents.logout();
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	 /*
	 * BUCA - My Benefits - Policies - ensure 'Back to Top' links function
	 * correctly TFS ID : 12739
	 */
	 
	@Test()
	public void EP_BenefitsPolicies_BacktoTopLinks()
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

	
		this.loginEPPoratl();

		// Click 'Benefit' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		// Verify 'Overview' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_02"), "Overview");

		// Click 'Policies' link
		COMMON_METHODS.clickElement(getTestObject("BOL_04"));

		// click first 'Back to Top' link
		COMMON_METHODS.clickElement(getTestObject("BOL_24"));

		// Verify 'Policies' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_05"), "Policies");

		// click Second 'Back to Top' link
		COMMON_METHODS.clickElement(getTestObject("BOL_25"));

		// Verify 'Policies' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_05"), "Policies");

		// click third 'Back to Top' link
		COMMON_METHODS.clickElement(getTestObject("BOL_26"));

		// Verify 'Policies' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_05"), "Policies");

		// click fourth 'Back to Top' link
		COMMON_METHODS.clickElement(getTestObject("BOL_27"));

		// Verify 'Policies' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_05"), "Policies");

		//businessComponents.logout();
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);

	} 

	/**
	 * BUCA - My Benefits - Policies - ensure 'Jump to Policy' drop-down
	 * functions correctly TFS ID : 12743
	 * BUCA - My Benefits - Policies - ensure 'Back to Top' links function
	 * correctly TFS ID : 12739
	 * 
	 */
	@Test()
	public void EP_BenefitsPolicies_JumpToPolicy()
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

				
		this.loginEPPoratl();

		// Click 'Benefit' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		// Verify 'Overview' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_02"), "Overview");

		// Click 'Policies' link
		COMMON_METHODS.clickElement(getTestObject("BOL_04"));

		// Verify user is navigated to 'Policies' page
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_05"), "Policies");

		// Verify if Jump to Policy text is present
		if((COMMON_METHODS.getText(getTestObject("BOL_28"))).contains("Jump to Policy:"))
			REPORTER.LogEvent(TestStatus.PASS, "Jump to Policy: is Displayed", "Policy text is is Displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Jump to Policy: is Not Displayed", "Policy text is Not Displayed", "");

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "4 hour minimum", "byVisibleText");

		// Verify if it jumps to the 4 Hour Minimum section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_30"),"4 hour minimum");

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "Cancellation", "byVisibleText");

		// Verify if it jumps to the Cancellation section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_31"),"Cancellation");

		// click first 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_24"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-centerFees", "byValue");

		// Verify if it jumps to the Center fees section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_32"), "Center fees");

		// click first 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_24"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-custodyIssues", "byValue");

		// Verify if it jumps to the Custody issues section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_33"), "Custody issues");

		// click first 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_24"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-greetRelease", "byValue");

		// Verify if it jumps to the Greet and release
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_34"), "Greet and release");

		// click first 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_24"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-hotelCare", "byValue");

		// Verify if it jumps to the Hotel Care section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_35"), "Hotel care");

		// click first 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_24"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-housekeeping", "byValue");

		// Verify if it jumps to the Housekeeping/ meal preparation
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_36"), "Housekeeping/ meal preparation");

		// click Second 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_25"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-infantSleep", "byValue");

		// Verify if it jumps to the Infant sleep position
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_37"), "Infant sleep position");

		// click Second 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_25"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-medAdmin", "byValue");

		// Verify if it jumps to the Medication administration
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_38"), "Medication administration");

		// click Second 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_25"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-meetGreet", "byValue");

		// Verify if it jumps to the 4 Hour Minimum section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_39"), "Meet and greet");

		// click third 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_26"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-mildlyIll", "byValue");

		// Verify if it jumps to the 4 Hour Minimum section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_40"), "Mildly ill"); 
		
		// click third 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_26"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-outdoorAct",
				"byValue");

		// Verify if it jumps to the Outdoor activities section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_41"), "Outdoor activities");

		// click third 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_26"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-reschedule", "byValue");

		// Verify if it jumps to the Reschedule section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_42"), "Reschedule");

		// click third 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_26"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-swimming", "byValue");

		// Verify if it jumps to the Swimming section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_43"), "Swimming");

		// click third 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_26"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-theft", "byValue");

		// Verify if it jumps to the Theft/compensation section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_44"), "Theft/compensation");

		// click fourth 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_27"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-transportation", "byValue");

		// Verify if it jumps to the Transportation section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_45"), "Transportation");

		// click fourth 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_27"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-visitors",
				"byValue");

		// Verify if it jumps to the Visitors during care section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_46"), "Visitors during care");

		// click fourth 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_27"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-advancedRes", "byValue");

		// Verify if it jumps to the Advanced Reservation Rule section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_47"), "Advanced Reservation Rule");

		// click fourth 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_27"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-consecDayRule", "byValue");

		// Verify if it jumps to the Consecutive Day Rule section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_48"), "Consecutive Day Rule");

		// click fourth 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_27"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-extUseRule",	"byValue");

		// Verify if it jumps to the Extended Use Rule section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_49"), "Extended Use Rule");

		// click fourth 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_27"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-centerCareDisclaimer", "byValue");

		// Verify if it jumps to the Center Care Disclaimer section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_50"), "Center Care Disclaimer");

		// click fourth 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_27"));

		// Select each option in the 'Jump to Policy' drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("BOL_29"), "pol-careOverview", "byValue");

		// Verify if it jumps to the Overview for Care section
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_51"), "Overview for Care");

		// click fourth 'Back to Top' link
				COMMON_METHODS.clickElement(getTestObject("BOL_27"));
		
		Utility.logout();
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	

	// Test Case #12590:
	// BUCA - My Benefits - Special Programs - Ensure the 'Make A Reservation'
	// link functions
	@Test()
	public void EP_MakeAReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch browser
		//Utility.launchBrowser(getTestData("TD_URL"));
		//launch browser
	//	Utility.launchBrowser(getTestData("TD_URL"));

		
		this.loginEPPoratl();
		
		//Navigate to the home page
		COMMON_METHODS.clickElement(getTestObject("OL_8"));
		
		// Click 'Benefit' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		Thread.sleep(5000);

		// Click 'Special Programs' link
		COMMON_METHODS.clickElement(getTestObject("BOL_12"));

		// Click 'Special Programs' Page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_13"),"Special Programs");

		// Waiting for make a reservation to be enabled
		Thread.sleep(4000);

		// Click 'Make A Reservation' link one
		COMMON_METHODS.clickElement(getTestObject("MKReservation1"));

		// User will be navigated to the reservation wizard and the type of the
		// reservation will be special program
		COMMON_METHODS.VerifyTextPresent(getTestObject("SplProgramPage"),"Special Program Reservation");

		// Repeating from Step three till end to verify 2nd Make a Reservation link
		// Click 'Benefit' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		// Click 'Special Programs' link
		COMMON_METHODS.clickElement(getTestObject("BOL_12"));

		// Click 'Special Programs' Page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_13"),"Special Programs");

		// Waiting for make a reservation to be enabled
		Thread.sleep(4000);

		// Click 'Make A Reservation' link one
		COMMON_METHODS.clickElement(getTestObject("MKReservation2"));

		// User will be navigated to the reservation wizard and the type of the
		// reservation will be special program
		COMMON_METHODS.VerifyTextPresent(getTestObject("SplProgramPage"),"Special Program Reservation");

		Utility.logout();
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	// Test Case #4624
	//BUCA - My Benefit - Overivew - Ensure all link on the 'Overview' page function correctly
	@Test()
	public void EP_OverviewLinksValidation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		 this.loginEPPoratl();

		//Navigate to the home page
		COMMON_METHODS.clickElement(getTestObject("OL_8"));
		
		// Click 'Benefit' link from top navigation menu		
		COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		// My Benefits page is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_02"),"Overview");
		
		//Click View Details like on page body
		COMMON_METHODS.clickElement(getTestObject("View_Details"));  

		Thread.sleep(5000);
		
		//Click Hide Details like on page body
		COMMON_METHODS.clickElement(getTestObject("Hide_Details"));
		
		//Commenting this Code as this functionality is removed now & will be implemented in future
		/* Getting the Website details 
		String str = COMMON_METHODS.getText(getTestObject("Center_Website_Add"));
		String[] sTemp = str.split(" - ");
		String Temp = sTemp[0].trim();
		
		//click 'Visit Center Website'
		COMMON_METHODS.clickElement(getTestObject("Visit_Center_Website"));			
		Thread.sleep(2000);

		//Switching to the popup which is opened.
		COMMON_METHODS.switchToPopup();
		Thread.sleep(1000);
		
		//verify the text in the PopUp
		if(COMMON_METHODS.getText(getTestObject("Center_Website_Add2")).toUpperCase().contains(Temp.toUpperCase()))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verify Text Present in the PopUp ", "Verify Text Present in the PopUp " , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Text Present in the PopUp ", "Verify Text is not Present in the PopUp ", "");				
		}		
	
		//closing the popup
		//COMMON_METHODS.clickElement(getTestObject("ROL_27"));
		
		//moving the handle to the main window
		COMMON_METHODS.switchToNormal();  */
		
		//logging out
		Utility.logout();
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);
	
	}
	
	/**
	  * BUCA - My Benefit - Overview - Ensure 'utilization terms' link functions
	  * TFS ID : 4621
	  * This method is rolled up with My_Profile_ensure_funding_links(8793)
	  *//*
	 @Test()
	 public void EP_Utilisation_Terms(ITestContext TC) throws Exception 
	 {

	 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	 System.out.println("Inside - " + methodName);
	 
	  
	 // READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
	  // SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
	 readTestObject(getDataTablePath("EP"), "TO_EP");
	 
	 //Read testdata for based on client 2
	 readTestData(getDataTablePath("EP"), "TD_EP2");
	 
	 //Launch Browser with EP URL
	  COMMON_METHODS.openBrowser(getTestData("TD_URL"));
	  Thread.sleep(5000);
	 
	 //login employee portal
	 businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"));
	 
	 //Click 'Benefit' link from top navigation menu
	 COMMON_METHODS.clickElement(getTestObject("BOL_01"));
	 
	 //Click on Utilisation Terms Link 
	 COMMON_METHODS.clickElement(getTestObject("MA_37"));
	 
	 //Close the pop up Utilisation terms
	 COMMON_METHODS.clickElement(getTestObject("MA_38"));
	 
	 //Logout from the portal
	 businessComponents.logout();
	  
	 }*/
	 
	/* *//**
	  * Reservations - Ensure user can view Benefits on My Profile summary
	  * TFS ID: 5240
	  * This method is rolled up with My_Profile_ensure_funding_links(8793)
	  *//* 
	 @Test()
	 public void EP_Reservation_View_Benefits(ITestContext TC) throws Exception {
	  
	  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	  System.out.println("Inside - " + methodName);
	  
	  // READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
	   // SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
	  readTestObject(getDataTablePath("EP"), "TO_EP");
	  
	  // Read test data for based on client 1
	  readTestData(getDataTablePath("EP"), "TD_EP");

	  // Launch the browser
	  businessComponents.EP_LaunchBrowser(TC);
	  
	  // Login to 'Employee Portal'
	  businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));

	  // Navigate to 'Reservations' tab
	  COMMON_METHODS.clickElement(getTestObject("OL_10"));
	  
	  //Click on View Benefits link
	  COMMON_METHODS.clickElement(getTestObject("MA_42"));
	  
	  Thread.sleep(2000);
	  
	  //Logout from the employee portal
	  businessComponents.logout();
	  
	}*/
	
	
	
	
	// Test Case #5240
	//BUCA - Reservations - Ensure user can view Benefits on My Profile summary
	@Test()
	public void EP_ViewBenefits() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		//launch browser
		Utility.launchBrowser(getTestData("TD_URL"));

		// login employee portal
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));

		/*String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"),signInArray);*/
		
		
		//Navigate to the home page
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		// My Benefits page is displayed
		businessComponents.EP_verifyText(getTestObject("OL_10"), "Reservations");
		
		//Click View Details like on page body
		COMMON_METHODS.clickElement(getTestObject("ViewBenefits"));  
		Thread.sleep(5000);
		
		// Verify Overview Page
		businessComponents.EP_verifyText(getTestObject("BOL_02"), "Overview");
		
		//logging out	
		Utility.logout();
		
		//Log the method pass
		COMMON_METHODS.logToReportAfterPass(methodName);
	
	}
	
	private void loginEPPoratl() throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			/*businessComponents.LoginEmployeeportal(
					ReadwritDataFromProps.props.getProperty("client5.userName"),
					getTestData("TD_PWD"))*/;
					
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3") };
			Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(this.employer + ".benefit.userName"), getTestData("TD_PWD"),signInArray);
					
		}
	}
}