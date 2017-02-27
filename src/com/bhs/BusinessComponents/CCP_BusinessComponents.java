package com.bhs.BusinessComponents;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class CCP_BusinessComponents extends INITIALIZE {

	static EP_BusinessComponents businessComponents = new EP_BusinessComponents();

	/**
	 * 
	 * @param TC
	 * @throws Exception
	 */
	/*
	 * public static void CCP_LaunchBrowser() throws Exception { String
	 * methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * //Launch Broswer with Qcentral URL
	 * COMMON_METHODS.openBrowser(getTestData("CCP_TD_URL"));
	 * Thread.sleep(5000);
	 * 
	 * //Verify Login Page displayed
	 * if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
	 * REPORTER.LogEvent(TestStatus.PASS, "Verify Login Page Displayed",
	 * "Login Page Displayed", ""); }
	 * 
	 * 
	 * }
	 */

	/*
	 * public static void CCP_Login() throws IOException, Exception {
	 * 
	 * String methodName =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * System.out.println("Inside - " + methodName);
	 * 
	 * //Enter User Name COMMON_METHODS.editAField(getTestObject("CCP_OL_1"),
	 * getTestData("CCP_TD_Username"));
	 * 
	 * //Enter Password
	 * COMMON_METHODS.editPasswordField(getTestObject("CCP_OL_2"),
	 * getTestData("CCP_TD_password"));
	 * 
	 * //Click Sign In COMMON_METHODS.clickElement(getTestObject("CCP_OL_3"));
	 * 
	 * System.out.println("Page Title is - " +
	 * BH_SetUp_TearDown.driver.getTitle());
	 * 
	 * //Verify Home Page displayed
	 * if(BH_SetUp_TearDown.driver.getTitle().trim()
	 * .equalsIgnoreCase("- Back-Up Care Center")){
	 * REPORTER.LogEvent(TestStatus.PASS, "Verify Home Page Displayed",
	 * "Home Page Displayed", ""); }
	 * 
	 * 
	 * }
	 */

	public static void CCP_ChangeLocation(String location) throws IOException,
			Exception {
		
		

		// Click on Change Location
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_4"));
		
		// Enter the Center Name
		if(location.contains("...")){
			String locArr[]=location.split("-");
			// Enter the Center Name
			COMMON_METHODS.editAField(getTestObject("CCP_OL_5"), locArr[0]);
			
		}
		else		
		COMMON_METHODS.editAField(getTestObject("CCP_OL_5"), location);

		// CLick on Search
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));

		// Click on Select
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_7"));

	}

	/*
	 * public void CC_logout() throws IOException, Exception { //Click Logout
	 * COMMON_METHODS.clickElement(getTestObject("CCP_OL_13")); }
	 */

	public static void CCP_ExpandAllReservations(String centerName)
			throws Exception {

		// Click on Change link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_4"));

		// Enter Search text
		COMMON_METHODS.editAField(getTestObject("CCP_OL_5"), centerName);

		// Click on Search
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));

		// Click on Select
		/* COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_1")); */

		// Click 'SELECT' link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_7"));

		// Verify Center name
		COMMON_METHODS.isElementPresent(getTestObject("D_CCP_OL_2"), "xpath");

		String cname = COMMON_METHODS.getText(getTestObject("D_CCP_OL_2"));

		REPORTER.LogEvent(TestStatus.PASS, "Verify Center name: " + cname,
				cname + " is displayed---SUCCESSFULL", "");

		// Click on Reservations
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));

		// Click on Expand All
		// COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_3"));
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_14"));

	}

	public static void changeCenter(String strSearchBy, String strSearchFor)
			throws IOException, Exception {

		// Click the 'CHANGE' link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_4"));

		if (strSearchBy.equalsIgnoreCase("Number")) {
			COMMON_METHODS.clickElement(getTestObject("KCCP_OL_25"));
		} else {
			if (strSearchBy.equalsIgnoreCase("State")) {
				COMMON_METHODS.clickElement(getTestObject("KCCP_OL_26"));
			}
		}

		// Enter the center name in the search field
		COMMON_METHODS.editAField(getTestObject("CCP_OL_5"), strSearchFor);

		// Click Search button
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));

		// Click 'SELECT' link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_7"));

	}

	public static void changeDate(String strDate) throws IOException, Exception {

		// Click Calendar button
		COMMON_METHODS.clickElement(getTestObject("KCCP_OL_22"));

		// Click the date link
		COMMON_METHODS.driver
				.findElement(
						By.xpath("//div[@id='ui-datepicker-div']//a[text()='"
								+ getTestData("CCP_TD_DateOfPendingReservation")
								+ "']")).click();
		Thread.sleep(3000);
	}

	public static void CCP_CreateReservation() throws Exception {
		// Click on New Reservation
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_30"));

		// Click on Edit
		COMMON_METHODS.editAField(getTestObject("D_CCP_OL_31"), "csc");

		// Click on Search
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_32"));

		// Click on New Reservation
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_51"));

		// Select Reason for Care
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_33"),
				"Looking for regular care", "byVisibleText");

		// Click on Care Recipient
		COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_34"), "check");

		// Click on second Care Recipient
		COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_49"), "check");

		// Select Healthy radio button
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_35"));

		// Select seconf Healthy radio button
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_50"));

		// Click on Continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_36"));

		// Enter Reservaton date
		COMMON_METHODS.editAField(getTestObject("D_CCP_OL_37"),
				getTestData("CCP_TD_Dateofreservation1") + ","
						+ getTestData("CCP_TD_Dateofreservation2"));

		// Click on Time from field
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_52"));

		COMMON_METHODS.DragandDrop(getTestObject("D_CCP_OL_38"), 60, 0);

		// Click on Done
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_54"));

		// Click on Time to field
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_53"));

		COMMON_METHODS.DragandDrop(getTestObject("D_CCP_OL_39"), 100, 0);

		// Click on Done
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_54"));

		// Click on Continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_40"));

		// Select Class
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_41"),
				"Early Learners", "byVisibleText");

		// Select status
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_42"), "Confirmed",
				"byVisibleText");

		// Select Class
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_55"),
				"Early Learners", "byVisibleText");

		// Select status
		COMMON_METHODS.listBoxSelect(getTestObject("D_CCP_OL_56"), "Confirmed",
				"byVisibleText");

		// Click on continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_43"));

		// Select Yes Radio button
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_44"));

		// Select Yes Radio button
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_57"));

		// Click on continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_36"));

		// Click on Verification Check box
		COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_45"), "check");

		// Click on continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_60"));

		// Click on Verification Check box
		COMMON_METHODS.checkBox(getTestObject("D_CCP_OL_72"), "check");

		// Click on continue
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_60"));

		// Verify Step5 page
		COMMON_METHODS.VerifyTextPresent(getTestObject("D_CCP_OL_46"),
				"Review Details - New Reservation");

		// Verify CR status
		String status1 = COMMON_METHODS.getText(getTestObject("D_CCP_OL_73"));

		REPORTER.LogEvent(TestStatus.PASS, "Verify Status",
				"Care Recipient1 status is: ' " + status1, "");

		// Verify Second CR status
		String status2 = COMMON_METHODS.getText(getTestObject("D_CCP_OL_74"));

		REPORTER.LogEvent(TestStatus.PASS, "Verify Status",
				"Care Recipient2 status is: ' " + status2, "");

		// Select Yes Radio button
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_58"));

		// Click on Submit
		COMMON_METHODS.radioButton(getTestObject("D_CCP_OL_59"));

		// Verify Second CR status
		COMMON_METHODS.VerifyTextPresent(getTestObject("D_CCP_OL_75"),
				"Congratulations!");

		String resvnum = COMMON_METHODS.getText(getTestObject("D_CCP_OL_76"));

		REPORTER.LogEvent(TestStatus.PASS,
				"Check if Reservation number is available",
				"Reservation Number' " + resvnum + "is available", "");

	}

	/*
	 * public void EP_ReservationCareOptions(String[] setpref ) throws
	 * IOException, Exception {
	 * 
	 * //Click 'Center - based Care' button at the top of 'Available Care
	 * Options' section COMMON_METHODS.clickElement(getTestObject("OL_90"));
	 * 
	 * //Select '1st Choice' from 'Set My Preference' drop down for any BH
	 * Center in the list COMMON_METHODS.listBoxSelect(getTestObject("OL_91"),
	 * getTestData("TD_FirstChoice"), "byVisibleText");
	 * 
	 * //Select 'Acceptable' from 'Set My Preference' drop down for all other
	 * Center in the list //COMMON_METHODS.listBoxSelect(getTestObject("OL_92"),
	 * getTestData("TD_Acceptable"), "byVisibleText");
	 * 
	 * //Click 'Continue' COMMON_METHODS.clickElement(getTestObject("OL_84"));
	 * 
	 * }
	 */

	public static void CCP_Reservation_AtMultipleCenters(String centername)
			throws Exception

	{
		// Click on Change link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_4"));

		// Enter the center name in the search field
		COMMON_METHODS.editAField(getTestObject("CCP_OL_5"), centername);

		// Click Search button
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_6"));

		// Click 'SELECT' link
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_7"));

		// Click on Expand All
		COMMON_METHODS.clickElement(getTestObject("D_CCP_OL_3"));
	}

	public static void EP_CB_Reservation(String employer,
			String dateofreservation) throws Exception {

		// Launch Browser with EP Url
		Utility.launchBrowser(getTestData("TD_EP_URL"));

		// Verify Employer after Entering Employer ID and Password
		businessComponents.EP_verifyEmployer(employer);

		// Accept privacy policy
		businessComponents.EP_AcceptPolicyAndSubmit();

		// Create dynamic user name
		String userName = createDyanamicUserData();

		// Register a New User
		businessComponents.EP_SignUpUser(userName, employer, "ccp");

		// Test data for adding Care Recipients
		String addCrData3_5Years[] = { getTestData("TD_CR_FirstName"),
				getTestData("TD_CR_LastName"), "Son", getTestData("TD_DOB"),
				getTestObject("OL_61"), getTestObject("OL_47"),
				getTestObject("OL_48"), getTestObject("OL_49"),
				getTestObject("OL_50"), getTestObject("OL_51"),
				getTestObject("OL_52"), getTestData("TD_AddInfo"),
				getTestObject("OL_54"), getTestObject("OL_55"),
				getTestData("TDU1_STATE1")/*
										 * getXMLData("Client"+employer,
										 * "TDU1_STATE1" )
										 */, getTestData("TD_AddInfo"),
				getTestObject("OL_100"), null,
				getTestData("TDU1_CENTERLOCATION1")/*
													 * getXMLData("Client"+employer
													 * ,"TDU1_STATE1")
													 */, null, null };
		// Register User
		businessComponents.EP_Registration(addCrData3_5Years, "No", employer);

		// Click Make My First reservation
		COMMON_METHODS.clickElement(getTestObject("OL_79"));

		// Test data for Step 1 CareRecipients
		int CareRecipients = 1;
		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");

		// Step 1 CareRecipients
		businessComponents.EP_ReservationCareRecipients(CareRecipients,
				HealthStatus, selectForReason, "Yes");

		// Step 2 When and where
		// COMMON_METHODS.editAField(getTestObject("OL_85"),getTestData("TD_Dateofreservation"));

		// Create WhenandWhere Reservation
		String[] careDates = { dateofreservation };

		// Write CB reservation date to data properties file
		ReadwritDataFromProps.writeDyanamicData("client" + employer
				+ ".cbresvreg.CBDate", getTestData("TD_Dateofreservation"));

		String actions[] = { "Locations", "Continue", null };
		businessComponents.EP_ReservationWhenandWhere(careDates, actions);

		// Step 3 Select Care Options Center based
		String location = businessComponents.EP_ReservationCareOptions();

		// Write CB reservation to data properties file
		ReadwritDataFromProps.writeDyanamicData("client" + employer
				+ ".cbresvreg.cb.location", location);

		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();

		String resNum = businessComponents.EP_ReservationReveiwDetails();
	}

	public static void expandClassRoom(String classType) throws InterruptedException {

		Thread.sleep(2000);

		if (classType.contains("Infant")) {
			COMMON_METHODS.driver
					.findElement(
							By.xpath("//div[contains(@id,'RoomClass')][1]/div/ul/li[3]/a"))
					.click();
		} else if (classType.contains("Toddler")) {
			COMMON_METHODS.driver
					.findElement(
							By.xpath("//div[contains(@id,'RoomClass')][2]/div/ul/li[3]/a"))
					.click();
		} else if (classType.contains("Preschool")) {
			COMMON_METHODS.driver
					.findElement(
							By.xpath("//div[contains(@id,'RoomClass')][3]/div/ul/li[3]/a"))
					.click();
		} else if (classType.contains("School Age")) {
			COMMON_METHODS.driver
					.findElement(
							By.xpath("//div[contains(@id,'RoomClass')][4]/div/ul/li[3]/a"))
					.click();
		}
		if (classType.contains("Young Explorers")) {
			COMMON_METHODS.driver
					.findElement(
							By.xpath("//div[contains(@id,'RoomClass')][1]/div/ul/li[3]/a"))
					.click();
		} else if (classType.contains("New Adventures")) {
			COMMON_METHODS.driver
					.findElement(
							By.xpath("//div[contains(@id,'RoomClass')][2]/div/ul/li[3]/a"))
					.click();
		} else if (classType.contains("Early Learners")) {
			COMMON_METHODS.driver
					.findElement(
							By.xpath("//div[contains(@id,'RoomClass')][3]/div/ul/li[3]/a"))
					.click();
		} else if (classType.contains("Discovery Zone ")) {
			COMMON_METHODS.driver
					.findElement(
							By.xpath("//div[contains(@id,'RoomClass')][4]/div/ul/li[3]/a"))
					.click();
		}

	}

	/**
	 * click the Edit reservation link
	 * 
	 * @param lastName
	 */
	public static void clickEditReservation(String lastName) {

		// click edit link
		COMMON_METHODS.driver.findElement(
				By.xpath("//span[contains(text(),'" + lastName
						+ "')]/../../td[11]/span")).click();

	}

	/**
	 * get the reservation status
	 * 
	 * @return
	 */
	public static String getReservationStatus(String lastName) {

		// get the Status in reservation
		Select sSelect = new Select(COMMON_METHODS.driver.findElement(By
				.xpath("//span[contains(text(),'" + lastName
						+ "')]/../../td/span/select")));

		WebElement option = sSelect.getFirstSelectedOption();

		return (option.getText());

	}
}