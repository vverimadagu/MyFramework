package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.CCP_BusinessComponents;
import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.BusinessComponents.PP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_CBAndInHomeReservations_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();

	/*
	 * This test covers the functionality of creating cilent2 user and does CB
	 * Reservation through Make MY First Reservation
	 */
	String employer = null;

	@Test
	@Parameters("client")
	public void EP_SignUpAndCBReservationTest(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		this.employer = employer;

		// Launch Browser with EP Url
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Verify Employer after Entering Employer ID and Password
		businessComponents.EP_verifyEmployer(employer);

		// Accept privacy policy
		businessComponents.EP_AcceptPolicyAndSubmit();

		// Create dynamic user name
		String userName = createDyanamicUserData();

		// Register a New User
		businessComponents.EP_SignUpUser(userName, employer, "cbresvreg");

		// Test data for adding Care Recipients
		String addCrData3_5Years[] = { getTestData("TD_CR_FirstName"),
				getTestData("TD_CR_LastName"), "Son", getTestData("TD_DOB"),
				getTestObject("OL_61"), getTestObject("OL_47"),
				getTestObject("OL_48"), getTestObject("OL_49"),
				getTestObject("OL_50"), getTestObject("OL_51"),
				getTestObject("OL_52"), getTestData("TD_AddInfo"),
				getTestObject("OL_54"), getTestObject("OL_55"),
				getTestData("TDU1_STATE1")/*
										 * getXMLData(employer,"TDU1_STATE1"
										 * )
										 */, getTestData("TD_AddInfo"),
				getTestObject("OL_100"), null,
				getTestData("TDU1_CENTERLOCATION1")/*
													 * getXMLData(employer
													 * ,"TDU1_STATE1")
													 */, null, null };
		// Register User
		businessComponents.EP_Registration(addCrData3_5Years, "No", employer);

		/*// Click Make My First reservation
		COMMON_METHODS.clickElement(getTestObject("OL_79"));

		// Test data for Step 1 CareRecipients
		int CareRecipients = 1;
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");

		// Step 1 CareRecipients
		businessComponents.EP_ReservationCareRecipients(CareRecipients,
				HealthStatus, selectForReason, "Yes");
		Thread.sleep(5000);

		// Step 2 When and where
		// COMMON_METHODS.editAField(getTestObject("OL_85"),getTestData("TD_Dateofreservation"));

		// Create WhenandWhere Reservation
		String[] careDates = { getTestData("TD_Dateofreservation") };

		// Write CB reservation date to data properties file
		ReadwritDataFromProps.writeDyanamicData(employer
				+ ".cbresvreg.CBDate", getTestData("TD_Dateofreservation"));
		//boolean isMemberShipClient=businessComponents.isMemberShipClient(employer);
		String actions[] = { "Locations", "Continue", null };
		//if(isMemberShipClient)
			//actions[0]=null;
		
		
		businessComponents.EP_ReservationWhenandWhere(careDates, actions);
		boolean isExist = false;
		try {
			isExist = COMMON_METHODS.driver
					.findElements(By.id("btnInhomeCare")).size() != 0;
			System.out.println("isExist==" + isExist);
		}

		catch (Exception e) {
			REPORTER.catchException(e, "INHOme");
		}

		// Verify 1,3,6,7,8 client users will not have INHome button
		if (employer.equals("1") || employer.equals("3")
				|| employer.equals("6") || employer.equals("7")
				|| employer.equals("8")) {

			if (!isExist)
				REPORTER.LogEvent(TestStatus.PASS,
						"INHome Reservation button not Present for ClientUser "
								+ employer,
						"INHome Reservation button not Present ", "");

			else
				REPORTER.LogEvent(TestStatus.FAIL,
						"INHome Reservation button is Present for ClientUser "
								+ employer,
						"INHome Reservation button is Present ", "");
			// COMMON_METHODS.isElementPresent("btnInhomeCare", "id");
		}

		// Step 3 Select Care Options Center based
		String location = businessComponents.EP_ReservationCareOptions();

		// Write CB reservation to data properties file
		ReadwritDataFromProps.writeDyanamicData(employer
				+ ".cbresvreg.cb.location", location);

		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Step 5 
		String resNum = businessComponents.EP_ReservationReveiwDetails();

		//Take Screen Print
		REPORTER.takeScreenShot("PASS");
		// Write CB reservation to data properties file
		ReadwritDataFromProps.writeDyanamicData(employer
				+ ".cbresvreg.CBResv", resNum);

		// Verify usage text and days
		// verifyUsage();

		// Verify all EP Tabs
		// employePortal_AllTabs();
*/
		// Logout
		Utility.logout();

		// Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * This test covers Confirming the CB Reservation in CC portal and logs in
	 * EP and Cancel the confirmed CB Reservation
	 * 
	 * */
	
	//(dependsOnMethods = { "EP_SignUpAndCBReservationTest" })
	@Test
	public void CC_ConfirmCBReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch CCP
		Utility.launchBrowser(getTestData("CCP_TD_URL"));
		Thread.sleep(5000);

		// Login to CCP
		String signInArray[] = { getTestObject("CCP_OL_1"),
				getTestObject("CCP_OL_2"), getTestObject("CCP_OL_3") };
		Utility.loginToBUCA(getTestData("CCP_TD_Username"),
				getTestData("CCP_TD_password"), signInArray);

		// verify all tabs in CC portal
		//careCenterPortal_AllTabs();

		// Change Location
		CCP_BusinessComponents.CCP_ChangeLocation(ReadwritDataFromProps.props
				.getProperty(employer + ".cbresvreg.cb.location"));

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));

		// Test data for selection the reservation date from the data picker
		String resDate = ReadwritDataFromProps.props.getProperty(employer + ".cbresvreg.CBDate")/*
												 * getTestData("TD_Dateofreservation"
												 * )
												 */;

		String dateArray[] = resDate.split("/");
		String date = dateArray[1];
		if (date.startsWith("0")) {

			date = date.substring(1);
		}
		int month = Integer.parseInt(dateArray[0]);
		String objArray[] = { getTestObject("CCP_OLP_01"),
				getTestObject("CCP_OLP_02") };
		String dataArray[] = { date };

		// CLick Calendar
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
		Thread.sleep(4000);
		Utility.selectDate(month, objArray, dataArray);

		/*CLick the Expand Link for Preschool
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_10"));*/
		
		//CLick the Expand All
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_3"));
		Thread.sleep(2000);
		
		String lastName = ReadwritDataFromProps.props.getProperty(
				employer + ".cbresvreg.lastName");
		
		Thread.sleep(3000);

		// Select confirmation status from drop down
		try {
			new Select(BH_SetUp_TearDown.driver.findElement(By
					.xpath("//span[contains(text(),'" + lastName
							+ ",')]/../../td/span/select")))
					.selectByVisibleText("Conf");
			Thread.sleep(4000);
			REPORTER.LogEvent(TestStatus.PASS, "Conf status selected",
					"Conf status selected from drop down", "");
		} catch (Exception e) {
			REPORTER.catchException(e,
					"Unable to select Conf status from drop down");
		}
		
		
		Thread.sleep(4000);
		
		// Click 'Submit' on the pop up
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_11"));
		
		Thread.sleep(3000);

		// close the pop up
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_12"));
		
		Thread.sleep(2000);

		// Click Logout fromm CCP
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_13"));

		// Launch EP
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		String userName = ReadwritDataFromProps.props.getProperty(
				employer + ".cbresvreg.userName");
		String password = getTestData("TD_PWD");
		String signInCCArray[] = { getTestObject("OL_1"),
				getTestObject("OL_2"), getTestObject("OL_3") };
		Utility.loginToBUCA(userName, password, signInCCArray);

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		String monthToView = Utility.getMonth(month);
		String objPath = monthToView + " " + date;

		String status[] = { "Confirmed", "byVisibleText" };
		String objId = "//li[@class='date' and contains(text(),'" + objPath
				+ "')]/../li/a";
		String objIdentifier[] = { objId };

		// Cancel confirmed reservation
		businessComponents.CancelReservation(status, "Cancel", objIdentifier);

		// Logout
		Utility.logout();

		// Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/*
	 * This test covers the functionality of creating 2 CRs and IN Home Location
	 * and does CB Reservation through Reserve Now
	 */
	@Test()
	public void EP_AddCRAndDoCBReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		//this.employer="2";
		// readTestData(getDataTablePath("EP"), "TD_EP2");
		String userName = ReadwritDataFromProps.props.getProperty(employer + ".cbresvreg.userName");
		String password = getTestData("TD_PWD");
		// Launch Browser with EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		// Login in to EP
		String signInArray[] = { getTestObject("OL_1"), getTestObject("OL_2"),
				getTestObject("OL_3") };
		Utility.loginToBUCA(userName, password, signInArray);
		
		Thread.sleep(2000);

		// Test data for adding Care Recipients
		String addCrData3_5Years[] = { getTestData("TD_CR_FirstName"),
				getTestData("TD_CR_LastName"), "Son", getTestData("TDU1_DOB2"),
				getTestObject("OL_61"), getTestObject("OL_47"),
				getTestObject("OL_48"), getTestObject("OL_49"),
				getTestObject("OL_50"), getTestObject("OL_51"),
				getTestObject("OL_52"), getTestData("TD_AddInfo"),
				getTestObject("OL_54"), getTestObject("OL_55"),
				getTestData("TDU1_STATE1")/*
										 * getXMLData(employer,"TDU1_STATE1"
										 * )
										 */, getTestData("TD_AddInfo"),
				getTestObject("OL_100"), null,
				getTestData("TDU1_CENTERLOCATION1")/*
													 * getXMLData(employer
													 * ,"TDU1_CENTERLOCATION1")
													 */, null, null };

		String addCrData6_18Years[] = { getTestData("TD_CR_FirstName"),
				getTestData("TD_CR_LastName"), "Daughter",
				getTestData("TDU1_DOB3"), getTestObject("OL_46"),
				getTestObject("OL_47"), getTestObject("OL_48"),
				getTestObject("OL_49"), getTestObject("OL_50"),
				getTestObject("OL_51"), getTestObject("OL_52"),
				getTestData("TD_AddInfo"), getTestObject("OL_54"),
				getTestObject("OL_55"), getTestData("TDU1_STATE3")/*
																 * getXMLData(employer,
																 * "TDU1_STATE3"
																 * )
																 */,
				getTestData("TD_AddInfo"), null, null,
				getTestData("TDU1_CENTERLOCATION3")/*
													 * getXMLData(employer
													 * ,"TDU1_CENTERLOCATION3")
													 */, null, null };

		// Click on care profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		Thread.sleep(2000);

		// String[][] tesData1 = { { "Yes", "Friend", "", "", "" }};
		for (int i = 1; i <= 2; i++) {
			// Click 'Add' link present in 'Care Recipients' section
			COMMON_METHODS.clickElement(getTestObject("OL_41"));
			Thread.sleep(5000);
			if (i == 1) {
				businessComponents.addCareRecipients(addCrData3_5Years, "Yes",
						employer);
				Thread.sleep(2000);

			}
			if (i == 2) {
				businessComponents.addCareRecipients(addCrData6_18Years, "Yes",
						employer);
				Thread.sleep(2000);

			}
			
			Thread.sleep(2000);
			// Click 'Add Care Recipients' button
			COMMON_METHODS.clickElement(getTestObject("OL_57"));
			Thread.sleep(20000);
		}
		
		// Click the 'Add' link next to the Locations section
		Thread.sleep(5000);
		COMMON_METHODS.clickElement(getTestObject("OL_124"));
		
		Thread.sleep(2000);
		
		// Create locations
		String data[] = { "InHome", "60601", "103 Fox Road", "Flag st", null,
				null, "United States", null };
		businessComponents.EP_AddLocation(data);

		Thread.sleep(2000);
		
		// Test data for Step 1 CareRecipients
		//int CareRecipients = 1;
		int HealthStatus[] = { 1, 1, 1, 1 };
		String selectForReason = getTestData("TD_ReasonForCare");

		// for(int i=0;i<=3;i++){
		// Navigate to My First Reservation
		businessComponents.EP_NavigateToReservation();
		// if(i==3){
		businessComponents.EP_ReservationCareRecipients(3, HealthStatus,
				selectForReason, "Yes");
		// }
		// else{

		// Step 1 CareRecipients
		// businessComponents.EP_SelectedCareRecipients(i, HealthStatus,
		// selectForReason,
		// "Yes");
		// }

		// Create WhenandWhere Reservation
		String[] careDates = { getTestData("TD_Dateofreservation1") };
		String actions[] = { "Locations", "Continue", null };
		businessComponents.EP_ReservationWhenandWhere(careDates, actions);
		

		Thread.sleep(2000);
		
		//To close the 'No Centers Found pop up'
		try{
		boolean isElementPresent = COMMON_METHODS.driver.findElements(
				By.xpath("//div[10]/div/a/span")).size() != 0;
		System.out.println("isElementPresent=="+isElementPresent);
		if(isElementPresent){
			COMMON_METHODS.clickElement(getTestObject("RESV_17"));
			Thread.sleep(5000);
			REPORTER.LogEvent(TestStatus.PASS, "No Centers Found pop up is closed", "No Centers Found pop up is closed", "");
		}
		}
		catch(Exception e){
			REPORTER.catchException(e, "Error in No Centers Found pop up ");
		}

		// Click 'Center - based Care' button at the top of 'Available Care
		// Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_90"));
		Thread.sleep(5000);

		// Click on "Reserve Now".
		COMMON_METHODS.clickElement(getTestObject("ROL_29"));
		Thread.sleep(5000);
		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();

		// Step 5 ReveiwDetails
		String resNum = businessComponents.EP_ReservationReveiwDetails();
		// Write INHome reservation to data properties file
		ReadwritDataFromProps.props.setProperty(employer
				+ ".cbresvreg.CBRNresv", resNum);

		// Verify usage text and days
		//verifyUsage();

		// Logout
		Utility.logout();
		Thread.sleep(9000);
		// Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/*
	 * This test covers creating the INHome Reservation
	 */

	@Test
	public void EP_InHomeReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		// readTestData(getDataTablePath("EP"), "TD_EP2");
		Thread.sleep(9000);
		String userName = ReadwritDataFromProps.props.getProperty(employer + ".cbresvreg.userName");
		String password = getTestData("TD_PWD");
				
		// Login in to EP
		String signInArray[] = { getTestObject("OL_1"), getTestObject("OL_2"),
				getTestObject("OL_3") };
		Utility.loginToBUCA(userName, password, signInArray);

		// Test data for Step 1 CareRecipients
		//int CareRecipients = 1;
		int HealthStatus[] = { 1, 2, 1, 1 };
		String selectForReason = getTestData("TD_ReasonForCare");

		//String aCareReciText = COMMON_METHODS.getText(getTestObject("RES_02"));
		//int careCount = Integer.parseInt(aCareReciText.substring(17, 18));
		// Navigate to Reservation Page
		businessComponents.EP_NavigateToReservation();

		// Select CareRecipients and Health status in Step1 and click on
		// continue button
		businessComponents.EP_ReservationCareRecipients( 3/* careCount*/,HealthStatus, selectForReason, "Yes");

		// Step 2 When and where
		Thread.sleep(4000);
		COMMON_METHODS.editAField(getTestObject("OL_85"),
				getTestData("TD_Dateofreservation9"));

		// Write IH reservation date to data properties file
		ReadwritDataFromProps.writeDyanamicData(employer
				+ ".cbresvreg.IHDate", getTestData("TD_Dateofreservation9"));

		// Select Location for IH resrvation
		ResWhenandWhereLocationInhome();

		// Filling and checking whether the continue button is Enabled and going
		// to the next page.
		businessComponents.EP_ReservationInHomeCareOptions("CareOptions");

		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();

		// Step 5 ReveiwDetails
		String resNum = this.EP_ReservationReveiwDetailsInHome();

		// Write INHome reservation to data properties file
		ReadwritDataFromProps.writeDyanamicData(employer
				+ ".cbresvreg.IHResv", resNum);

		// Logout
		Utility.logout();

		// Verify usage text and days
		// verifyUsage();

		// Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/*
	 * This test covers Confirming the CB Reservation in CC portal and logs in
	 * EP and Cancel the confirmed CB Reservation
	 */
	@Test(dependsOnMethods = { "EP_InHomeReservation" })
	public void PP_ConfirmIHReservation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		PP_BusinessComponents ppBusinessComp = new PP_BusinessComponents();

		// Launch browser with PP url
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		Thread.sleep(5000);

		// login employee portal
		String SignInObjArray[] = { getTestObject("PPOL_1"),
				getTestObject("PPOL_2"), getTestObject("PPOL_3") };
		Utility.loginToBUCA(getTestData("TD_PP_UserID"),
				getTestData("TD_PP_PWD"), SignInObjArray);
		// Verify all tabs in Provider portal
		//providePoratl_AllTabs();

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));
		Thread.sleep(5000);

		String objArray[] = { "ul", "id", "li" };
		String iHResNo = ReadwritDataFromProps.props.getProperty(employer + ".cbresvreg.IHResv");
		String objArrayPage[] = { iHResNo, getTestObject("PPMA_193") };
		int pageNo = Utility.goToLastPageInGridList(objArray);
		try {
			if (pageNo != 0) {
				COMMON_METHODS.driver.findElement(
						By.xpath("//ul[@id='pagination-clean']/li["
								+ (pageNo - 1) + "]")).click();
				Thread.sleep(5000);
				REPORTER
						.LogEvent(TestStatus.PASS, "Click on Pagination click",
								"Click on Pagination click - Successfull"
										.toUpperCase(), "");
			}
		} catch (Exception e) {
			REPORTER.catchException(e,
					"Unable to click on last page in pagination List grid");
		}
		// selects available reservation
		Utility.selectAvailResInPP(objArrayPage);

		COMMON_METHODS.driver.findElement(
				By.xpath("//li[@class='resnumber' and text()='"
						+ objArrayPage[0] + "']/../li[4]/a")).click();

		// clicks on Accept queue
		ppBusinessComp.clickAcceptQueue();

		// Click on Staff Reservation button on Popup
		COMMON_METHODS.clickElement(getTestObject("PPMA_188"));

		// Select the option from the listbox
		COMMON_METHODS
				.listBoxSelect(getTestObject("PPMA_190"), "#1", "byIndex");

		// Check the Accept policies checkbox
		COMMON_METHODS.checkBox(getTestObject("PPMA_189"), "check");

		// Click on Submit Staff Request button
		COMMON_METHODS.clickElement(getTestObject("PPMA_191"));
		Thread.sleep(5000);
		// Click Logout
		COMMON_METHODS.clickElement(getTestObject("OL_4"));

		String userName = ReadwritDataFromProps.props.getProperty(employer + ".cbresvreg.userName");
		String password = getTestData("TD_PWD");

		// Launch browser with EP Url
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Login to Emp Portal
		String signInArray[] = { getTestObject("OL_1"), getTestObject("OL_2"),
				getTestObject("OL_3") };
		Utility.loginToBUCA(userName, password, signInArray);

		// Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		// Test data for selection the reservation date from the data picker
		String resDate = ReadwritDataFromProps.props.getProperty(employer + ".cbresvreg.IHDate");/*
												 * getTestData("TD_Dateofreservation2"
												 * );
												 */
		String dateArray[] = resDate.split("/");
		String date = dateArray[1];
		int month = Integer.parseInt(dateArray[0]);
		if (date.startsWith("0")) {

			date = date.substring(1);
		}
		String monthToView = Utility.getMonth(month);
		String objPath = monthToView + " " + date;

		String status[] = { "Confirmed", "byVisibleText" };
		String objId = "//li[@class='date' and contains(text(),'" + objPath
				+ "')]/../li/a";
		String objIdentifier[] = { objId };

		// Select confirmed reservation
		businessComponents.CancelReservation(status, null, objIdentifier);

		//
		String resStatus = COMMON_METHODS.driver.findElement(
				By.xpath("//li[@class='date' and contains(text(),'" + objPath
						+ "')]/../li")).getText();
		if (resStatus.contains("Confirmed"))
			REPORTER.LogEvent(TestStatus.PASS, "Confirmed is Verified",
					"IHResrvation Confirmed", "");
		else
			REPORTER.LogEvent(TestStatus.WARNING,
					"Expected=   Confirmed",
					"Actual= " + resStatus, "");
		// Logout
		Utility.logout();

		// Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	private void verifyUsage() {

		try {
			// Verify Usage Text and Days
			String usageText = COMMON_METHODS.getText(getTestObject("RS_28"));
			String usageDays = COMMON_METHODS.getText(getTestObject("RS_29"));

			if (usageText.trim().equalsIgnoreCase(
					"The Back-Up Care Advantage Available Usage"))
				REPORTER.LogEvent(TestStatus.PASS, "Usage text is Correct",
						"Usage text is verified", "");
			else
				REPORTER
						.LogEvent(
								TestStatus.FAIL,
								"Expected Value= The Back-Up Care Advantage Available Usage",
								"Actual=" + usageText, "");

			if (usageDays.contains("4.00 Hours"))
				REPORTER.LogEvent(TestStatus.PASS, "Verify Days is Correct",
						"Usage Days is verified", "");
			else
				REPORTER
						.LogEvent(TestStatus.FAIL,
								"Expected Value= 8.00 Hours", "Actual="
										+ usageDays, "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void ResWhenandWhereLocationInhome() throws IOException, Exception {

		try {
			// Select 'Start Time'
			COMMON_METHODS.clickElement(getTestObject("OL_86"));

			BH_SetUp_TearDown.driver
					.findElement(
							By
									.xpath("//div[@id='ui-timepicker-div-txtTimeFrom']/dl/dd[2]/div[2]/table/tbody/tr/td[4]"))
					.click();
			BH_SetUp_TearDown.driver.findElement(
					By.xpath("(//button[@type='button'])[4]")).click();

			// Select 'End Time'
			COMMON_METHODS.clickElement(getTestObject("OL_87"));
			BH_SetUp_TearDown.driver
					.findElement(
							By
									.xpath("//div[@id='ui-timepicker-div-txtTimeTo']/dl/dd[2]/div[2]/table/tbody/tr/td[5]"))
					.click();
			BH_SetUp_TearDown.driver.findElement(
					By.xpath("(//button[@type='button'])[4]")).click();

		} catch (Exception e) {
			REPORTER.catchException(e, "Enter Start Time / End Time");
		}

		// Select 'At/Near a Location' radio button from 'Search Type' in
		// 'Select The Location For Care' section
		COMMON_METHODS.radioButton(getTestObject("ROL_03"));

		// Select Distance from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_88"),
				getTestData("TD_Distance"), "byVisibleText");

		// Select any Location from 'Location' drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_89"),
				getTestData("TD_Location1"), "byVisibleText");

		// Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		Thread.sleep(7000);
	}

	public String EP_ReservationReveiwDetailsInHome() throws IOException,
			Exception {

		COMMON_METHODS.editAField(getTestObject("IH_100"),
				getTestData("TD_GreeterName"));

		COMMON_METHODS.editAField(getTestObject("IH_101"),
				getTestData("TD_GreetRelation"));

		COMMON_METHODS.checkBox(getTestObject("IH_102"), "check");
		
		//Add payment method in 5th step
		businessComponents.addPaymentMethod("ManagePaymentMethod");

		// Check 'I have read and agree to the Payment Policy' check box present
		// under 'Payment Terms'
		COMMON_METHODS.checkBox(getTestObject("OL_95"), "check");

		// Check 'Accept the Cancellation Policy for this reservation' checkbox
		// present under 'Cancellation Policy'
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		// Click 'Request Reservation'
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		System.out.println("Reservation # - "
				+ BH_SetUp_TearDown.driver.findElement(
						By.cssSelector("h1 > span")).getText().trim());
		System.out.println("#################################"
				+ BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1"))
						.getText().trim().toString());

		// Verify the Reservation Number and Status present at the top
		String resNum = "";
		try {
			resNum = BH_SetUp_TearDown.driver.findElement(
					By.cssSelector("h1 > span")).getText().trim();
			String ResStatus = BH_SetUp_TearDown.driver.findElement(
					By.cssSelector("span.hdrStatus")).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Confirmed",
					"Reservation # and Reservation Status : " + resNum + " "
							+ ResStatus, "");

		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Reservation Confirmed");
		}

		return resNum;

	}

	private void employePortal_AllTabs() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("OL_8"));
		businessComponents.EP_verifyText("Message Center", "Home Tab",
				"#msgHdr");

		// Click on Reservation tab
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		businessComponents.EP_verifyText("Reservations", "Reservation tab",
				"h1");

		// Click on Providers tab
		COMMON_METHODS.clickElement(getTestObject("PT_01"));
		businessComponents.EP_verifyText("Providers", "Providers tab", "h1");

		// Click on Benefit tab
		COMMON_METHODS.clickElement(getTestObject("KMA_80"));
		businessComponents.EP_verifyText("Benefit: Overview", "Benefit tab",
				"h1");

		// Click on Care profile tab
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		businessComponents.EP_verifyText("Care Profile", "Care profile tab",
				"h1");

		// Click on Resources tab
		COMMON_METHODS.clickElement(getTestObject("Res_Tab_01"));
		businessComponents.EP_verifyText("Resources", "Resources tab", "h1");

		// Click on My Account link
		COMMON_METHODS.clickElement(getTestObject("MA_01"));
		businessComponents.EP_verifyText("My Account", "My Account", "h1");

		// Click on help link
		COMMON_METHODS.clickElement(getTestObject("HL_20"));
		businessComponents.EP_verifyText("Help", "Help", "h1");

	}

	private void providePoratl_AllTabs() throws Exception {

		// Click on Home tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_215"));
		businessComponents.EP_verifyText("Message Center", "Home tab",
				"#msgHdr");

		// Click on Reservations link
		COMMON_METHODS.clickElement(getTestObject("PPCR_06"));
		businessComponents.EP_verifyText("Reservations", "Reservation tab",
				"h1");

		// Click Caregivers tab link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));
		businessComponents.EP_verifyText("Caregivers", "Caregivers tab", "h1");

		// Click Account Management link
		COMMON_METHODS.clickElement(getTestObject("PPAM_01"));
		businessComponents.EP_verifyText("Account Management",
				"Account Management tab", "h1");

		// Click Reports link
		COMMON_METHODS.clickElement(getTestObject("PPAM_29"));
		businessComponents.EP_verifyText("Reports", "Reports tab", "h1");

		// Click Resources link
		COMMON_METHODS.clickElement(getTestObject("PPAM_31"));
		businessComponents.EP_verifyText("Resources", "Resources tab", "h1");

		// Click My Account link
		COMMON_METHODS.clickElement(getTestObject("PPMA_218"));
		businessComponents.EP_verifyText("My Account", "My Account tab", "h1");

		// Click on Help link
		COMMON_METHODS.clickElement(getTestObject("PPMA_203"));
		businessComponents.EP_verifyText("Help", "Help tab", "h1");

	}

	private void careCenterPortal_AllTabs() throws Exception {

		// Verify Home Page is displayed properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_S1"),
				"Upcoming Care Sessions");

		// Click on Reservations to check the different Classrooms
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		Thread.sleep(3000);

		// Verify the Reservation page is displayed
		if (COMMON_METHODS.driver.getPageSource().contains("Reservations:")) {
			REPORTER.LogEvent(TestStatus.PASS,
					"Reservations Page is displayed",
					"Reservations Page is displayed", "");
		}

		else {
			REPORTER.LogEvent(TestStatus.FAIL,
					"Reservations Page is displayed",
					"Reservations Page is NOT displayed", "");
		}

		// Click on "Family Management"
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL19"));

		// Family Management page should load.
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL19"),
				"Family Management");

		// Click on Client Programs tab
		COMMON_METHODS.clickElement(getTestObject("S_CCP_OL38"));

		// Verify that the Client Programs page is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL39"),
				"Client Programs");

		// Click on Center Management tab
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_14"));

		// Verify Center Management page is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("S_CCP_OL39"),
				"Center Management");

		// Click on Help link
		COMMON_METHODS.clickElement(getTestObject("GCCP_OL_31"));
		businessComponents.EP_verifyText("Help", "Help tab", "h1");
	}

}
