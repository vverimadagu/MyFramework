package com.bhs.scripts.employeeportal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;
import com.bhs.util.INITIALIZE.TestStatus;

/**
 * @author Deepa
 * @version
 * @return
 * @param
 * @CreationDate: 10/03/2014
 */

public class EP_Reservation_Step5_Test extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	String userName;
	String password;
	String employer;
	
	
	public void EP_NavigateToStep4(String num,boolean verifyinfo) throws Exception
	{
		// Navigate To Reservation
		businessComponents.EP_NavigateToReservation();

		//Create Care Recipients Reservation
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
		"Yes");

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation"+num)};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		// Create Care Options Reservation
		businessComponents.EP_ReservationCareOptions();
		
		if(!verifyinfo)
			businessComponents.EP_ReservationVerifyInfoNo();
		else
			businessComponents.EP_ReservationVerifyInfoYes();
	}
	private void loginEP(String clientName) throws Exception {
		
		userName=ReadwritDataFromProps.props.getProperty(clientName+".resstep5.userName");
		password=getTestData("TD_PWD");
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);
		
}

	@Test(priority = 1)
	@Parameters("client")	
	public void EP_ReservationSignupUser(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		this.employer=employer;
		// Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		// Verify Employer after Entering Employer ID and Password 
		businessComponents.EP_verifyEmployer(employer);
		
		// Accept privacy policy
		businessComponents.EP_AcceptPolicyAndSubmit();
		
		//Create dynamic user name
		String userName = createDyanamicUserData();
		
		// Register a New User
		businessComponents.EP_SignUpUser(userName,employer,"resstep5");
		
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
		//Register User
		businessComponents.EP_Registration(addCrData3_5Years,"No",employer);

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * ensure "Preferred Method of Contact' dropdown contains correct
	 * information employee profile information TFS ID : 12178
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 10/03/2014
	 */
	@Test(priority = 2)
	public void EP_ReservationWizardStep5_PrefMethodOfContact()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("1",false);
		
		// Verify if Email option is available in preferred contacts
		COMMON_METHODS
				.isOptionPresentInListBox(getTestObject("RS_09"), "Email");

		// Verify if Contact option is available in preferred contacts
		COMMON_METHODS
				.isOptionPresentInListBox(getTestObject("RS_09"), "Phone");

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * ensure 'Care Providers' module is displayed correctly (UX doc) employee
	 * profile information TFS ID : 12159
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 10/03/2014
	 */

	@Test(priority = 3)
	public void EP_ReservationWizardStep5_VerifyCareProviders()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("2",false);
		
		// Verify Care Provider data is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_10"));

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * ensure 'Care Sessions' module is displayed correctly (UX doc) employee
	 * profile information TFS ID : 8408
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 11/03/2014
	 */

	@Test(priority = 4)
	public void EP_ReservationWizardStep5_VerifyCareSessions() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
		}
		
		EP_NavigateToStep4("3",false);

		// Verify Care Session date is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_11"));

		// Verify Care Receipient name is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_12"));

		// Verify if Contact option is available in preferred contacts
		// COMMON_METHODS.isOptionPresentInListBox(getTestObject("RS_09"),
		// "Phone");

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * Ensure correct care recipients and their information are displayed
	 * employee profile information TFS ID : 8407,12152
	 *TFS ID 12155: BUCA - Reservation Wizard - Step5 - ensure user is able
		 * to edit each care recipient by clicking their name
	* @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 11/03/2014
	 */

	@Test(priority = 5)
	public void EP_ReservationWizardStep5_VerifyCareRecipients()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("4",true);

		
		// Edit First Name
		COMMON_METHODS.editAField(getTestObject("OL_42"), "BHCRFN 2");

		// Edit Last Name
		COMMON_METHODS.editAField(getTestObject("OL_43"), "BHCRLN 2");

		// Click on "BasicInfoVerification" checkbox
		COMMON_METHODS.clickElement(getTestObject("RS_14"));

		// Click on "HealthInfoVerification" checkbox
		COMMON_METHODS.clickElement(getTestObject("RS_15"));

		// Click on "AdditionalInfoVerification" checkbox
		COMMON_METHODS.clickElement(getTestObject("RS_16"));

		// Click on "Continue"
		COMMON_METHODS.clickElement(getTestObject("RS_17"));
		
		String name = COMMON_METHODS.getText(getTestObject("RS_13"));
		if (name.equalsIgnoreCase("BHCRFN 2 BHCRLN 2"))
			REPORTER.LogEvent(TestStatus.PASS, "Care Recipient edit",
					"Care Recipient edit - Successfull".toUpperCase(), "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Care Recipient edit",
					"Care Recipient edit - not Successfull".toUpperCase(), "");
		
		
		COMMON_METHODS.clickElement(getTestObject("RS_13"));
		
		// Ensure the system opens the Care Recipient in the profile manager for
		// Care Recipients.
		COMMON_METHODS.VerifyTextPresent(getTestObject("CRName_02"),
				"Verify Profile Information");
		
		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * ensure links on 'Reservations Contact' names funcion correctly employee
	 * profile information TFS ID : 11017
	 * Reservation Wizard - Step5 - ensure 'Reservations Contacts' module is displayed correctly (UX doc)
	 * employee profile information TFS ID : 8771
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 11/03/2014
	 */

	@Test(priority = 6)
	public void EP_ReservationWizardStep5_VerifyReservationContacts()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
			
		EP_NavigateToStep4("5",false);
		
		// Verify Reservation Contact name is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_18"));

		// Verify Emergency Contact name is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_25"));

		// Verify Authorized pickup is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_26"));

		String str = COMMON_METHODS.getText(getTestObject("RS_18"));
		
		// Click on Care Recipient
		COMMON_METHODS.clickElement(getTestObject("RS_18"));

		String title = BH_SetUp_TearDown.driver.getTitle();

		REPORTER.LogEvent(TestStatus.PASS, "Employee Profile page " + title,
				"Employee Profile page" + title + " -displayed".toUpperCase(),
				"");

		String ename = COMMON_METHODS.getText(getTestObject("RS_19"));

		if (str.equalsIgnoreCase(ename))
			REPORTER.LogEvent(TestStatus.PASS, "Employee Profile page " + ename
					+ " is displayed", "Employee Profile page" + ename
					+ " -displayed".toUpperCase(), "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Employee Profile page " + ename
					+ " is displayed", "Employee Profile page" + ename
					+ " - not displayed".toUpperCase(), "");

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of BUCA - Reservation Wizard -
	 * Step5 - ensure 'Manage Payment Methods' Link functions correctly
	 * 
	 * employee profile information TFS ID : 12182
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 11/03/2014
	 */

	@Test(priority = 7)
	public void EP_ReservationWizardStep5_VerifyPaymentMethodEdit(
			ITestContext TC) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("6",false);

		// Click on Manage Payment Method
		COMMON_METHODS.clickElement(getTestObject("RS_20"));

		// Click on Close button in popup
		COMMON_METHODS.clickElement(getTestObject("RS_21"));

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * ensure 'Payment Terms' link functions correctly employee profile
	 * information TFS ID : 12185
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 12/03/2014
	 */

	@Test(priority = 8)
	public void EP_ReservationWizardStep5_VerifyPaymentTerms() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("7",false);

		// Click on Payment Terms link
		COMMON_METHODS.clickElement(getTestObject("RS_22"));

		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_23"));

		// Click on Close button in popup
		COMMON_METHODS.clickElement(getTestObject("RS_24"));

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * ensure 'Total Utilization' module is displayed accurately employee
	 * profile information TFS ID : 12188
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 12/03/2014
	 */

	@Test(priority = 10)
	public void EP_ReservationWizardStep5_VerifyUtilizationModule(
			ITestContext TC) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("8",false);

		// Verify Employee name is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_27"));

		// Verify Backup days text is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_28"));

		// Verify Backup days is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_29"));

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * Provider Names are listed on Step 5 employee profile information TFS ID :
	 * 8411
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Krishna
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 12/03/2014
	 */

	/*
	 * @Test() public void
	 * EP_ReservationWizardStep5VerifyProviderNames(ITestContext TC) throws
	 * Exception {
	 * 
	 * String methodName =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * // READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD //
	 * SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
	 * readTestObject(getDataTablePath("EP"), "TO_EP");
	 * 
	 * //Read test data for based on client 1
	 * readTestData(getDataTablePath("EP"), "TD_EP1");
	 * 
	 * // Launch browser businessComponents.EP_LaunchBrowser(TC);
	 * 
	 * // Login employee portal
	 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps
	 * .props.getProperty("client7.userName"), getTestData("TD_PWD"));
	 * 
	 * //Click 'Reservations' link from top navigation menu
	 * COMMON_METHODS.clickElement(getTestObject("OL_10"));
	 * 
	 * //Click 'Request a New Reservation' link from top navigation menu
	 * COMMON_METHODS.clickElement(getTestObject("OL_11"));
	 * 
	 * // Step #2 businessComponents.EP_ReservationCareRecipients();
	 * 
	 * // Select Date of reservation
	 * COMMON_METHODS.editAField(getTestObject("OL_85"), "03/31/2014");
	 * 
	 * // Step #3 businessComponents.EP_ReservationWhenandWhere();
	 * businessComponents.EP_ReservationCareOptions();
	 * 
	 * // Step #4 businessComponents.EP_ReservationVerifyInfoNo();
	 * 
	 * // Verify the Provider names are displayed
	 * COMMON_METHODS.isElementPresent("xpath",
	 * "//h5[@class='hdrLine' and text()='Care Providers']");
	 * 
	 * // Logout from Employee Portal businessComponents.logout(); }
	 */
	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * Special Programs - Ensure the Special Programs module appears on Step 5
	 * of Reservations employee profile information TFS ID : 13554
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Krishna
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 12/03/2014
	 */
	/*
	 * @Test() public void
	 * EP_ReservationWizardStep5VerifySpecialProgramsModule(ITestContext TC)
	 * throws Exception {
	 * 
	 * String methodName =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * // READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD //
	 * SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
	 * readTestObject(getDataTablePath("EP"), "TO_EP");
	 * 
	 * //Read test data for based on client 1
	 * readTestData(getDataTablePath("EP"), "TD_EP11");
	 * 
	 * // Launch browser businessComponents.EP_LaunchBrowser(TC);
	 * 
	 * // Login employee portal
	 * businessComponents.LoginEmployeeportal(ReadwritDataFromProps
	 * .props.getProperty("client11.userName"), getTestData("TD_PWD"));
	 * 
	 * //Click 'Reservations' link from top navigation menu
	 * COMMON_METHODS.clickElement(getTestObject("OL_10"));
	 * 
	 * //Click 'Request a New Reservation' link from top navigation menu
	 * COMMON_METHODS.clickElement(getTestObject("OL_11"));
	 * 
	 * // Select the Special Program radio button
	 * COMMON_METHODS.clickElement(getTestObject("CR_12"));
	 * 
	 * // Select Special Program radio button in Step-1
	 * businessComponents.selectSpecialProgram();
	 * 
	 * // Step #2 businessComponents.EP_ReservationCareRecipients();
	 * 
	 * // Select Date of reservation
	 * COMMON_METHODS.editAField(getTestObject("OL_85"),
	 * getTestData("TD_Dateofreservation4"));
	 * 
	 * // Step #3 businessComponents.EP_ReservationWhenandWhere();
	 * businessComponents.EP_ReservationCareOptions();
	 * 
	 * // Step #4 businessComponents.EP_ReservationVerifyInfoNo();
	 * 
	 * // Verify the Provider names are displayed
	 * COMMON_METHODS.isElementPresent("xpath",
	 * "//h5[@class='hdrLine' and text()='Care Providers']");
	 * 
	 * // Logout from Employee Portal businessComponents.logout(); }
	 */
	/**
	 * TFS ID 12161: BUCA - Reservation Wizard - Step5 - ensure 'Use Other
	 * Method of Contact' link functions as UX doc shows
	 * 
	 */
	/**
	 * @author Supraja
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 10/03/2014
	 */

	
	/**
	 * @author Supraja
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 10/03/2014
	 */

	@Test(priority = 13)
	public void EP_ReservationStep5_TermsofPayment() throws Exception {

		/**
		 * TFS ID 12187: BUCA - Reservation Wizard - Step5 - ensure user must
		 * fill out Terms of Payment and Cancellation Policy to continue
		 * 
		 */

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}

		EP_NavigateToStep4("9",false);

		// Verify the Payment Terms text on step 5.
		// COMMON_METHODS.VerifyTextPresent(getTestObject("PT_01"),
		// "Payment Terms");

		// Verify if 'Request Reservation' Button is disabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_97"), false);

		// Select Payment Terms check box
		COMMON_METHODS.checkBox(getTestObject("OL_95"), "check");

		// Verify if 'Request Reservation' Button is Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_97"), true);

		// Verify if radio button is selected
		boolean bStatus = COMMON_METHODS
				.VerifyRadioButtonSelected(getTestObject("OL_96"));

		if (!bStatus)
			COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click 'Request Reservation'
		COMMON_METHODS.clickElement(getTestObject("OL_97"));
		Thread.sleep(5000);

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * @author Supraja
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 12/03/2014
	 */

	@Test(priority = 14)
	public void EP_Step5ToStep4() throws Exception {

		/**
		 * TFS ID 12189: BUCA - Reservation Wizard - Step5 - ensure user is able
		 * to go to previous step via 'Back' button
		 * 
		 */

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("10",false);
		
		// Verify the wizard moves to step 5.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"),
				"Review Reservation and Payment Details");

		// Click on back button
		COMMON_METHODS.clickElement(getTestObject("RS_01"));

		// Verify the wizard moves to step 4.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"),
				"Care Instructions & Verify Information");

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step 5:
	 * Reservation Update time (link also) - BEE-Display a link  ‘Request
	 * Different Time’  on Step5 of reservation process. employee profile
	 * information TFS ID : 23167
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 17/03/2014
	 */

	@Test(priority = 15)
	public void EP_Step5_VerifyReqDifferentTime(ITestContext TC)
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("11",false);

		// Click on Reservation Update Time
		COMMON_METHODS.clickElement(getTestObject("Resv_update1"));

		// Verify Prefer Update Date Checkbox
		COMMON_METHODS.verifyElementDisplayed(getTestObject("Resv_update2"));

		// Verify Date Picker
		COMMON_METHODS
				.verifyElementDisplayed(getTestObject("Resv_update3"));

		// Verify Time Picker
		COMMON_METHODS
				.verifyElementDisplayed(getTestObject("Resv_update4"));

	//	businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of Reservation Wizard -
	 * Edit reservation - Update method of contact (CB) employee profile
	 * information TFS ID : 20608
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 17/03/2014
	 */
	@Test(priority = 16)
	public void EP_ReservationWizardStep5_EditResv() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		Utility.launchBrowser(getTestData("TD_EP_URL"));
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("12",false);

		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		// Click on Edit or Cancel Reservation
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));

		// Click "Use Other Method of Contact" fill in information
		COMMON_METHODS.clickElement(getTestObject("UOMC_01"));

		// Select FIRST OPTION from the List box
		COMMON_METHODS.listBoxSelect(getTestObject("UOMC_02"),
				getTestData("TD_Email1"), "byVisibleText");

		// Verify if Contact option is available in preferred contacts
		COMMON_METHODS.editAField(getTestObject("UOMC_03"),
				getTestData("TD_EnterEmail"));

		// Select SECOND OPTION from the List box
		COMMON_METHODS.listBoxSelect(getTestObject("UOMC_02"),
				getTestData("TD_Phone1"), "byVisibleText");

		// Verify if Contact option is available in preferred contacts
		COMMON_METHODS.editAField(getTestObject("UOMC_03"),
				getTestData("TD_EnterPhone"));

		// Select THIRD OPTION from the List box
		COMMON_METHODS.listBoxSelect(getTestObject("UOMC_02"),
				getTestData("TD_Text"), "byVisibleText");

		// Verify if Contact option is available in preferred contacts
		COMMON_METHODS.editAField(getTestObject("UOMC_03"),
				getTestData("TD_EnterText"));

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of Reservation Wizard -
	 * Edit reservation - update payment method employee profile information TFS
	 * ID : 20607
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 18/03/2014
	 */
	@Test(priority = 17)
	public void EP_ReservationWizardStep5_EditResv_PaymentMethod()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		// Navigate To Reservation
		businessComponents.EP_NavigateToReservation();

		EP_NavigateToStep4("13",false);
		
		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		// Click on Edit or Cancel Reservation
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));

		/** Edit Payment Method */

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of BUCA -
	 *  Edit Reservation - Payment
	 *  Information: Verify Client Employee can add new Payment Method
	 * 
	 * employee profile information TFS ID : 23107
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 18/03/2014
	 */

	@Test(priority = 18)
	public void EP_ReservationWizardStep5_AddNewPaymentMethod()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}


		EP_NavigateToStep4("14",false);

		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		// Click on Edit or Cancel Reservation
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));

		/** Add New Payment Method */

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of BUCA -
	 *  :Edit Reservation - Payment
	 *  Information: Verify 'Payment Method’ drop down for Client Employee
	 * 
	 * employee profile information TFS ID : 23101
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 11/03/2014
	 */

	@Test(priority = 19)
	public void EP_ReservationWizardStep5_VerifyPaymentMethod_Dropdown()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}

		EP_NavigateToStep4("15",false);

		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		// Click on Edit or Cancel Reservation
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));

		/** Verify 'Payment Method’ drop down */

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of
	 * BUCA - Reservation wizard - Ensure user can change payment method TFS ID
	 * : 8299
	 * 
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 18/03/2014
	 */

	@Test(priority = 20)
	public void EP_ReservationWizardStep5_SelectfromPaymentMethod_Dropdown()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}


		EP_NavigateToStep4("16",false);

		// COMMON_METHODS.listBoxSelect(getTestObject("PM_01"), "Value",
		// "Identifiers");

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * Test Case #19124: BUCA - Reservation Wizard - Step5 - ensure user is able
	 * to continue with the reservation process via 'Request Reservation' button
	 * (in-home)
	 * 
	 * @author lavaKumar
	 */

	@Test(priority = 25)
	public void EP_ReservationWizardStep5_InHome_RequestReservation()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		// verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"),
				"Who Needs Care and Why?");

		//Create Care Recipients Reservation
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
		"Yes");

		// Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"),
				"When and Where Do You Need Care?");

		// Step 2 When and where
		COMMON_METHODS.editAField(getTestObject("OL_85"),
				getTestData("TD_Dateofreservation17"));

		businessComponents.ResWhenandWhereLocationInhome();

		// Click 'IN - HomeCare' button at the top of 'Available Care Options'
		// section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(3000);

		// Filling and checking whether the continue button is Enabled and going
		// to the next page.
		businessComponents.EP_ReservationInHomeCareOptions("CareOptions");

		// Care Instructions and verify information
		businessComponents.EP_ReservationVerifyInfoYes();

		// Click on "BasicInfoVerification" checkbox
		COMMON_METHODS.clickElement(getTestObject("RS_14"));

		// Click on "HealthInfoVerification" checkbox
		COMMON_METHODS.clickElement(getTestObject("RS_15"));

		// Click on "AdditionalInfoVerification" checkbox
		COMMON_METHODS.clickElement(getTestObject("RS_16"));

		// Click on "Continue"
		COMMON_METHODS.clickElement(getTestObject("RS_17"));

		businessComponents.EP_ReservationReveiwDetailsInHome();

		// businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 - CSC
	 * TFS ID: 22755--BUCA -Step 5: Preferred Method of Contact ( link also) - Validation of
	 * the the data entered for email or phone number formats. TFS ID : 
	 * TFS ID:22756 -- Step 5: Preferred Method of Contact ( link also) - Validation of the  the data entered for email or phone number formats.(email)
	 *  TFS ID:22753 -- BUCA -Step 5: Preferred Method of Contact ( link also) - Verify ‘Use
	 * other method of Contact’ will allow the user to add another method of
	 * contact (PHONE) for the reservation being processed on Step5. TFS ID :
	 * 
	 * @param TC
	 * @throws Exception
	 */

	/**
	 * @author Satya
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 25/03/2014
	 */
	@Test(priority = 22) @Parameters("client") 
	public void EP_ReservationWizardStep5_VerifyValidationEmailPhoneNum(String employer)
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);
		
		// Launch Browser
		//Utility.launchBrowser(getTestData("TD_EP_URL"));

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}


		EP_NavigateToStep4("4",false);
		
		// Verify the wizard moves to step 5.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"),
				"Review Reservation and Payment Details");

		// Click "Use Other Method of Contact" fill in information
		COMMON_METHODS.clickElement(getTestObject("UOMC_01"));
		
		Thread.sleep(2000);

		// Select FIRST OPTION from the List box
		COMMON_METHODS.listBoxSelect(getTestObject("UOMC_02"),
				getTestData("TD_Email1"), "byVisibleText");

		Thread.sleep(2000);
		
		// Enter Invalid email address
		COMMON_METHODS.editAField(getTestObject("UOMC_03"),
				"abcgmail.com");
		
		Thread.sleep(1000);
		
		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));
		
		String errorMsg = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='validation-summary-errors']/ul/li")).getText().trim();
		
		if (errorMsg.contains("Other method of contact email address is not in a valid format.")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify system will show an error message to enter correct email Message", "Error Message showing as " + errorMsg , "");
		}else {
			REPORTER.LogEvent(TestStatus.PASS, "Verify system will show an error message to enter correct email Message", "Error Message not showing as " + errorMsg , "");
		}
		
		//Verify the error message displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("ERR_MSG"), "Other method of contact email address is not in a valid format.");
		
		// Select Second OPTION from the List box
		COMMON_METHODS.listBoxSelect(getTestObject("UOMC_02"),
				getTestData("TD_Phone1"), "byVisibleText");

		Thread.sleep(2000);
		
		// Enter Invalid phone number format
		COMMON_METHODS.editAField(getTestObject("UOMC_03"),
				"12a1212a12");
		
		Thread.sleep(2000);
		
		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));
		
		Thread.sleep(2000);

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));
		
		Thread.sleep(1000);

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));
		
		/*//Verify the error message displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("ERR_MSG"), "Other method of contact phone Number is not in a valid format.");*/
		
		String errorMsg2 = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='validation-summary-errors']/ul/li")).getText().trim();
		
		if (errorMsg2.contains("Other method of contact email address is not in a valid format.")) {
			REPORTER.LogEvent(TestStatus.PASS, "Verify system will show an error message to enter correct phone format", "Error Message showing as " + errorMsg2 , "");
		}else {
			REPORTER.LogEvent(TestStatus.PASS, "Verify system will show an error message to enter correct phone format", "Error Message not showing as " + errorMsg , "");
		}
		
		
		
		/*// Select Phone from the List box
		COMMON_METHODS.listBoxSelect(getTestObject("UOMC_02"), getTestData("TD_Phone1"),
				"byVisibleText");
		
		
		// TODO : not able to verify the error message due to defect in error
		// message is not displayed


		// Enter wrong format of phone number
		COMMON_METHODS.editAField(getTestObject("UOMC_03"), "91827111");

		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		// TODO : not able to verify the error message due to defect in error
		// message is not displayed
		
		// Select Phone from the List box
		COMMON_METHODS.listBoxSelect(getTestObject("UOMC_03"), getTestData("TD_Text"),
				"byVisibleText");

		// Enter Special characters in text field for phone number
		COMMON_METHODS.editAField(getTestObject("UOMC_03"), "12!89!*(&");

		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		// TODO : not able to verify the error message due to defect in error
		// message is not displayed
*/
	//	businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	
	/**
	 * This test script covers functionality of BUCA - Reservation Wizard -
	 * Step5 - ensure user is able to continue with the reservation process via
	 * 'Request Reservation' button (Center based care)
	 * 
	 * TFS ID : 12190
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Satya
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 25/03/2014
	 */

	@Test(priority = 21)
	public void EP_ReservationWizardStep5_VerifyRequestReservation()
			throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodName);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}


		EP_NavigateToStep4("19",false);

		// Click on Agreement checkbox
		COMMON_METHODS.clickElement(getTestObject("OL_95"));

		// Click on Always Agree radiobutton
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click on Request Reservation
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		/*
		 * Below steps will cover test case with TFS ID:13783
		 */
		// Click "Edit/Cancel Care Sessions" link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));

		Thread.sleep(5000);

		// Ensure the system loads the reservation for editing.
		if (BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1"))
				.getText().trim().toString().contains("EDIT RESERVATION")) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Edit Reservation Page displayed",
					"Edit Reservation page is dispaled", "");
		} else {
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify Edit Reservation Page displayed",
					"Edit Reservation page not dispaled", "");
		}

		/*
		 * Test case with TFS ID:13783 end here
		 */

		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	/**
	 * This test script covers functionality of BUCA - Edit Reservation -
	 * Reservation Contact: Verify while deactivating the only Authorized
	 * Contact designated as 'Authorized Pick-Up', user is able to save profile
	 * without all fields required to complete a reservation 
	 * Edit Reservation - Reservation Contact: Verify the user can ignore Warning while
	 * deactivating the only Authorized Contact designated as Emergency contact
	 * TFS ID : 22941,22932
	 * 
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 28/03/2014
	 */
	@Test(priority = 23)
	public void EP_ReservationWizardStep5_Editresv() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);

		Utility.launchBrowser(getTestData("TD_EP_URL"));
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		Thread.sleep(12000);

		// Select Inprogress
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"),
				"In Progress", "byVisibleText");

		Thread.sleep(10000);

		// Click on View
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));

		// Click on Edit
		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));

		Thread.sleep(10000);

		// Click on Authorised contact
		COMMON_METHODS.clickElement(getTestObject("Authorised pickup"));

		Thread.sleep(10000);

		// Click on Inactive option
		COMMON_METHODS.clickElement(getTestObject("AUTH_INACTIVE"));

		Thread.sleep(10000);

		// Verify the Error message
		COMMON_METHODS
				.VerifyTextPresent(
						getTestObject("Auth_contact_error"),
						"This contact is associated with an existing reservation. Please add a new authorized contact.");

		// Click on OK button
		COMMON_METHODS.clickElement(getTestObject("OK_btn"));

		// Add an Inactive comment
		COMMON_METHODS.editAField(getTestObject("AUTH_INACTIVE_COMM"),
				"Inactive");

		// Click on update Authorised contact button
		COMMON_METHODS.clickElement(getTestObject("OL_69"));

		// Verify if authorised contact is present
		boolean isPresent = COMMON_METHODS.isElementPresent(
				getTestObject("Resv_EmergencyContact_link"), "xpath");
		if (isPresent == false)
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Authorised Contact is not available",
					"Authorised contact is not available", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Verify Authorised Contact is not available",
					"Authorised contact is available", "");
	}

	/**
	 * This test script covers functionality of BUCA - Reservation Review -
	 * ensure 'View All Acceptable Providers' link functions correctly TFS ID :
	 * 8810
	 * 
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 01/04/2014
	 */
	@Test(priority = 26)
	public void EP_ReservationWizardStep5_ViewallAcceptableProviderslink()
			throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);

		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		Thread.sleep(12000);

		// Select Inprogress
		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"), "Draft",
				"byVisibleText");

		Thread.sleep(10000);

		// Click on View
		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));

		// Select Distance from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_88"),
				getTestData("TD_Distance"), "byVisibleText");

		// Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));

		// Click 'Center - based Care' button at the top of 'Available Care
		// Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_90"));

		Thread.sleep(15000);

		// Select '1st Choice' from 'Set My Preference' drop down for any BH
		// Center in the list
		COMMON_METHODS.listBoxSelect(getTestObject("OL_91"),
				getTestData("TD_FirstChoice"), "byVisibleText");

		// Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));

		// Select 'No' radio button for 'Any changes to profile details like
		// allergies, etc.?'
		COMMON_METHODS.radioButton(getTestObject("OL_94"));

		// Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));

		// Click 'View All Acceptable Providers link'
		COMMON_METHODS.clickElement(getTestObject("Step5_ProviderLink"));

		String prov1 = COMMON_METHODS
				.getText(getTestObject("Acceptable_Provider1"));

		String prov2 = COMMON_METHODS
				.getText(getTestObject("Acceptable_Provider2"));

		String prov3 = COMMON_METHODS
				.getText(getTestObject("Acceptable_Provider3"));

		REPORTER.LogEvent(TestStatus.PASS,
				"Verify Acceptable Providers  is available",
				"Acceptable Providers " + prov1 + " is available", "");
		REPORTER.LogEvent(TestStatus.PASS,
				"Verify Acceptable Providers  is available",
				"Acceptable Providers " + prov2 + " is available", "");
		REPORTER.LogEvent(TestStatus.PASS,
				"Verify Acceptable Providers  is available",
				"Acceptable Providers " + prov3 + " is available", "");
	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * ensure message is displayed if user does not have an emergency contact
	 * created TFS ID : 11018
	 * 
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 01/04/2014
	 */
	@Test(priority = 27)
	public void EP_ReservationWizardStep5_AuthContact_Error() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);

			if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		//Click on Home link
		COMMON_METHODS.clickElement(getTestObject("OL_8"));
		
		// click on auth contact arrow
		COMMON_METHODS.clickElement(getTestObject("Auth_Contact_arrow"));

		// click on auth contact link
		COMMON_METHODS.clickElement(getTestObject("Authorised_Contact"));

		// Click on Inactive option
		COMMON_METHODS.clickElement(getTestObject("AUTH_INACTIVE"));

		Thread.sleep(10000);

		// Verify the Error message
		COMMON_METHODS
				.VerifyTextPresent(
						getTestObject("Auth_contact_error"),
						"This contact is associated with an existing reservation. Please add a new authorized contact.");

		// Click on OK button
		COMMON_METHODS.clickElement(getTestObject("OK_btn"));

		// Add an Inactive comment
		COMMON_METHODS.editAField(getTestObject("AUTH_INACTIVE_COMM"),
				"Inactive");

		// Click on update Authorised contact button
		COMMON_METHODS.clickElement(getTestObject("OL_69"));

		businessComponents.EP_NavigateToReservation();

		COMMON_METHODS
				.verifyElementDisplayed(getTestObject("Resv_Err_popup"));

		String err = COMMON_METHODS.getText(getTestObject("Resv_error"));

		REPORTER.LogEvent(TestStatus.PASS,
				"Verify Reservation error Message is displayed",
				"Reservation error \" " + err + "\" is displayed", "");

		Thread.sleep(1000);

		//Click on Closr btn
		COMMON_METHODS.clickElement(getTestObject("Close_ArrBtn"));

		//Click on Home link
		COMMON_METHODS.clickElement(getTestObject("OL_8"));

		//Click on Add link
		COMMON_METHODS.clickElement(getTestObject("Add_Auth_Contact"));

		businessComponents.EP_AddAuthorizedContacts();
		
		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodname);

	}

	/**
	  * TFS ID: 23152 CSC BUCA - Edit Reservation - Care Recipients: The
	  * application should enable Client Employee to edit the Care Recipient
	  * information while on a Reservation
	  * TFS ID: 22855 CSC BUCA :Edit Reservation - General: Verify changes done
	  * by Care Center user to care sessions are saved.
	  * TFS ID: 22923 CSC BUCA :Edit Reservation - Reservation Contact: Verify
	  * for Care Center user updates made to a reservation contact are updated in
	  * Care Profile 
	  * @author Kiran G
	  */

	 @Test(priority = 32)
	 public void EP_EditResv_CareProfile() throws Exception {
	  
	  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	  System.out.println("Inside - " + methodName);

	 //Launch CCP
	  Utility.launchBrowser(getTestData("CCP_TD_URL"));
	  String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};

	  // Login CareCenter Portal
	  Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
	  Thread.sleep(2000);

	  //Verify that the user can select Care Center using Change link at the top
	  COMMON_METHODS.clickElement(getTestObject("S_CCP_OL1"));
	  COMMON_METHODS.editAField(getTestObject("S_CCP_OL2"),getTestData("CCP_TD_srchText"));
	  COMMON_METHODS.clickElement(getTestObject("S_CCP_OL3"));
	  COMMON_METHODS.clickElement(getTestObject("S_CCP_OL4"));

	  //Verify Home Page is displayed properly
	  COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"), "Upcoming Care Sessions");

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

	  //Click on Calendar, choose another date and Ensure the date chose loads
	  COMMON_METHODS.clickElement(getTestObject("S_CCP_OL6"));
	  Thread.sleep(2000);

	  // Click on the Reservation Header
	  COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
	  
	  //Click on Arrow mark of Preschool class
	  COMMON_METHODS.clickElement(getTestObject("S_CCP_OL9"));

	  // Get the index of Edit link of the reservation
	  int j = 0;
	  List<WebElement> lweReservations = COMMON_METHODS.driver.findElements(By.xpath("//tr[@id='row']"));
	  for(WebElement weRow: lweReservations){
	   j = j + 1;

	  }

	  // Click 'Edit' link
	  COMMON_METHODS.driver.findElement(By.xpath("//td[@id='data_" + (j-1) + "']/..//span/a")).click();

	  //Verify the Care Recepient is present in the Edit reservation page
	  COMMON_METHODS.isElementPresent("BHCRLN, BHCRFN", "linkText");
	  //We are getting impersonation screen after this
	  
	  //Verify the Authorised contact is present
	  COMMON_METHODS.isElementPresent("BHACFN BHACLN", "linkText");
	  //We are getting impersonation screen after this
	  
	  //Check checkbox present in front of any Care Session in the 'Care Sessions' grid
	  COMMON_METHODS.checkBox(getTestObject("GCCP_OL_47"), "check");

	  //Click 'Edit Selected' link present below the 'Care Sessions' grid
	  COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_12"));

	  //Select the Confirmed status from the dropdown
	  COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_13"), "Confirmed", "byVisibleText");

	  //Click on Update reservation
	  COMMON_METHODS.clickElement(getTestObject("KCCP_OL_18"));
	  
	  // Logout from portal
	  // Utility.logout();

	  // Log to reports
	  COMMON_METHODS.logToReportAfterPass(methodName);

	 }

	/**
	 * TFS ID: 22855 CSC BUCA :Edit Reservation - General: Verify changes done
	 * by Care Center user to care sessions are saved.
	 * 
	 * @author Kiran G
	 * @param <WebElement>
	 *//*

	@Test(priority=29)
	public void EP_EditResv_CareSessions() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Create a center based reservation
		EP_CBAndInHomeReservations_Tests cbih = new EP_CBAndInHomeReservations_Tests();
		cbih.EP_SignUpAndCBReservationTest();

		// Test data for selection the reservation date from the data picker
		String resDate = getTestData("TD_Dateofreservation");
		String dateArray[] = resDate.split("/");
		String date = dateArray[1];
		if (date.startsWith("0")) {
			date = date.substring(1);
		}

		int month = Integer.parseInt(dateArray[0]);
		String objArray[] = { getTestObject("CCP_OLP_01"),
				getTestObject("CCP_OLP_02") };
		String dataArray[] = { date };

		// Read test data for based on client 1
		readTestData(getDataTablePath("CCP"), "TD_CCP");

		//Launch CCP browser
		Utility.launchBrowser(getTestData("CCP_TD_URL"));

		//Login to CCP
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);
		

		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Click Calendar and select the date of reservation
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
		Thread.sleep(4000);
		Utility.selectDate(month, objArray, dataArray);

		// Change the center to the center reserved
		CCP_BusinessComponents.changeCenter("CenterName",
				getTestData("CCP_TD_CenterName"));
		int i = 0;
		List<WebElement> lwe = (List<WebElement>) COMMON_METHODS.driver
				.findElements(By.xpath("//div[starts-with(@id,'RoomClass')]"));
		for (WebElement we : lwe) {
			i = i + 1;
			String roomClassText = we.getText();
			String[] arrroomClassText = roomClassText.split("Pending: ");
			String[] arrPending = arrroomClassText[1].split("Wait Listed:");
			if (Integer.parseInt(arrPending[0].trim()) > 0) {
				break;
			}
		}

		// Expand the class
		COMMON_METHODS.driver.findElement(
				By.xpath("//div[starts-with(@id,'RoomClass')][" + i + "]//a"))
				.click();

		// Click Edit link of the reservation created above
		int j = 0;
		List<WebElement> lweReservations = (List<WebElement>) COMMON_METHODS.driver
				.findElements(By.xpath("//tr[@id='row']"));
		for (WebElement weRow : lweReservations) {
			j = j + 1;
			String rowText = weRow.getText();
			if (rowText.contains(ReadwritDataFromProps.props
					.getProperty("client2.cbudc2.lastName")
					+ ", "
					+ ReadwritDataFromProps.props
							.getProperty("client2.cbudc2.firstName"))) {
				break;
			}
		}

		// Click 'Edit' link
		COMMON_METHODS.driver.findElement(
				By.xpath("//td[@id='data_" + (j - 1) + "']/..//span/a"))
				.click();

		// Check checkbox present in front of any Care Session in the 'Care
		// Sessions' grid
		COMMON_METHODS.checkBox(getTestObject("CCP_OL_9"), "check");

		// Click 'Edit Selected' link present below the 'Care Sessions' grid
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_48"));

		// Change any of the fields
		COMMON_METHODS.listBoxSelect(getTestObject("GCCP_OL_50"), "Infant",
				"byVisibleText");
		COMMON_METHODS.editAField(getTestObject("GCCP_OL_51"), "Test");

		// Click 'Update Reservation' button present on bottom of the page
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_18"));

		// We are getting error after this step

	}

	*//**
	 * TFS ID: 22923 CSC BUCA :Edit Reservation - Reservation Contact: Verify
	 * for Care Center user updates made to a reservation contact are updated in
	 * Care Profile
	 * 
	 * @author Kiran G
	 * @param <WebElement>
	 *//*

	@Test(priority=30)
	public void EP_EditResv_Contact() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Create a center based reservation
		EP_CBAndInHomeReservations_Tests cbih = new EP_CBAndInHomeReservations_Tests();
		cbih.EP_SignUpAndCBReservationTest();

		// Test data for selection the reservation date from the data picker
		String resDate = getTestData("TD_Dateofreservation");
		String dateArray[] = resDate.split("/");
		String date = dateArray[1];
		if (date.startsWith("0")) {
			date = date.substring(1);
		}

		int month = Integer.parseInt(dateArray[0]);
		String objArray[] = { getTestObject("CCP_OLP_01"),
				getTestObject("CCP_OLP_02") };
		String dataArray[] = { date };

		// Read test data for based on client 1
		readTestData(getDataTablePath("CCP"), "TD_CCP");

		//Launch CCP
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		Thread.sleep(5000);

		//Login to CCP
		String signInArray[]={ getTestObject("CCP_OL_1"),getTestObject("CCP_OL_2"),getTestObject("CCP_OL_3")};
		Utility.loginToBUCA(getTestData("CCP_TD_Username"), getTestData("CCP_TD_password"), signInArray);


		// Click on the Reservation tab
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Click Calendar and select the date of reservation
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
		Thread.sleep(4000);
		Utility.selectDate(month, objArray, dataArray);

		// Change the center to the center reserved
		CCP_BusinessComponents.changeCenter("CenterName",
				getTestData("CCP_TD_CenterName"));
		int i = 0;
		List<WebElement> lwe = (List<WebElement>) COMMON_METHODS.driver
				.findElements(By.xpath("//div[starts-with(@id,'RoomClass')]"));
		for (WebElement we : lwe) {
			i = i + 1;
			String roomClassText = we.getText();
			String[] arrroomClassText = roomClassText.split("Pending: ");
			String[] arrPending = arrroomClassText[1].split("Wait Listed:");
			if (Integer.parseInt(arrPending[0].trim()) > 0) {
				break;
			}
		}

		// Expand the class
		COMMON_METHODS.driver.findElement(
				By.xpath("//div[starts-with(@id,'RoomClass')][" + i + "]//a"))
				.click();

		// Click Edit link of the reservation created above
		int j = 0;
		List<WebElement> lweReservations = (List<WebElement>) COMMON_METHODS.driver
				.findElements(By.xpath("//tr[@id='row']"));
		for (WebElement weRow : lweReservations) {
			j = j + 1;
			String rowText = weRow.getText();
			if (rowText.contains(ReadwritDataFromProps.props
					.getProperty("client2.cbudc2.lastName")
					+ ", "
					+ ReadwritDataFromProps.props
							.getProperty("client2.cbudc2.firstName"))) {
				break;
			}
		}

		// Click 'Edit' link
		COMMON_METHODS.driver.findElement(
				By.xpath("//td[@id='data_" + (j - 1) + "']/..//span/a"))
				.click();

		// Check checkbox present in front of any Care Session in the 'Care
		// Sessions' grid
		COMMON_METHODS.checkBox(getTestObject("CCP_OL_9"), "check");

		// Click 'Edit Selected' link present below the 'Care Sessions' grid
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_48"));

		// Click on the name of any Authorized Contact present in 'Reservation
		// Contacts’ section
		COMMON_METHODS.VerifyTextPresent(getTestObject("GCCP_OL_52"),
				"BHACFN BHACLN");

		// Logout from portal
		Utility.logout();

		// Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
*/
	/**
	 * This test script covers functionality of Edit Reservation - Step 4:
	 * Verify if Client Employee can update Care Instructions for a Reservation
	 * TFS ID : 22719
	 * 
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/**
	 * @author Deepa
	 * @version
	 * @return
	 * @param
	 * @CreationDate: 28/03/2014
	 */

	@Test(priority=31)
	public void EP_ReservationWizardStep5_EditSplInst() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);

		// Launch EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Login EP 
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty(employer+".resstep5.userName"),
				getTestData("TD_PWD"),signInArray);
		
		//Navigate to Reservation page
		businessComponents.EP_NavigateToReservation();

		COMMON_METHODS.listBoxSelect(getTestObject("Resevation_type"),
				"In Progress", "byVisibleText");

		COMMON_METHODS.clickElement(getTestObject("Resrv_view"));

		COMMON_METHODS.clickElement(getTestObject("Resrv_Edit"));

		// TODO : not able to find change contact centers link

	}

	/**
	 * This test script covers functionality of Reservation Wizard - Step5 -
	 * ensure user can edit Care Sessions via 'Edit' button (brings user to
	 * Step2) employee profile information TFS ID : 8410
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority = 28)
	public void EP_ReservationWizardStep5_EditButton() throws Exception {
		String methodname = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println(methodname);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}

		EP_NavigateToStep4("20",false);

		// Click on Edit in Step5
		COMMON_METHODS.clickElement(getTestObject("RS_02"));

		// VErify Step 2
		COMMON_METHODS.VerifyTextPresent(getTestObject("RS_03"),
				"When and Where Do You Need Care?");

		 Utility.logout();

		COMMON_METHODS.logToReportAfterPass(methodname);
	}
}
