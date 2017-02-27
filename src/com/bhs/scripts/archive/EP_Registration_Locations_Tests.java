/*package com.bhs.scripts.archive;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
//import com.bhs.util.INITIALIZE.TestStatus;
import com.bhs.util.INITIALIZE.TestStatus;


 * @author: Sanjeev Singh
 * @CreationDate: 12/03/2014
 
public class EP_Registration_Locations_Tests extends INITIALIZE {

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
	
	
	 * Adding the Locations in Care profile
	 * @throws Exception 
	 * @throws IOException 
	 * @author ssingh375
	 * @since 12/03/2014
	 * @return null
	 
	
	public static void EP_AddLocation_Registration() throws IOException, Exception {
		
		// Enter the Location Name
		COMMON_METHODS.editAField(getTestObject("OL_125"), getTestData("TD_Name_loc"));

		// Enter the Postal Code
		COMMON_METHODS.editAField(getTestObject("OL_126"), getTestData("TD_Zip_loc"));
		
		// Enter the Address1
		COMMON_METHODS.editAField(getTestObject("OL_127"), getTestData("TD_Address_loc"));
		
		// Enter the City
		COMMON_METHODS.editAField(getTestObject("OL_129"), getTestData("TD_City_loc"));
		
		// Enter the County
		COMMON_METHODS.editAField(getTestObject("OL_130"), getTestData("TD_County_loc"));
		
		// Select the Country
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_Country_loc"), "byVisibleText");
		
		// Select the State/Province*
		COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), getTestData("TD_State_loc"), "byVisibleText");
		
		// Selecting Radio Button for care at this location
		COMMON_METHODS.radioButton(getTestObject("CareAtThisLoction"));
		
		//Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		Thread.sleep(8000);
		//Verify Employee Profile and Locations updated	
		// Getting the Website details
		String str = COMMON_METHODS.getText(getTestObject("TD_Zip0_Verify"));
		String[] sTemp = str.split(", ");
		String Temp = sTemp[3].trim();		
		
		//COMMON_METHODS.VerifyTextPresent(getTestObject("TD_Zip0_Verify"), "Locations");
		if(COMMON_METHODS.getText(getTestObject("TD_Zip0_Verify")).toUpperCase().contains(Temp.toUpperCase()))
		{
			REPORTER.LogEvent(TestStatus.PASS, "Verify Zip Present in Address field ", "Verify Zip Present in Address field -- Succeeds" , "");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Zip Present in Address field ", "Verify Zip Present in Address field -- Failed" , "");				
		}	
		
	}
	
	
	
	 * @author: Sanjeev Singh
	 * @CreationDate: 18/03/2014
	 
	//Test Case #3810:   BUCA - Locations - any address stored in a past reservation is not affected by edits or changes

	@Test()
	public void EP_PastReservationNotAffectedByEdits() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser();
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Verifying Care Profile Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Click "Add New Location" link
		COMMON_METHODS.clickElement(getTestObject("Location_Add"));
		
		//User should be brought to the add locations page
		COMMON_METHODS.VerifyTextPresent(getTestObject("Location_Add"), "Locations");
		Thread.sleep(2000);
		
		// Enter the Location Name
		COMMON_METHODS.editAField(getTestObject("OL_125"), getTestData("TD_Name_loc"));

		// Enter the Postal Code
		COMMON_METHODS.editAField(getTestObject("OL_126"), getTestData("TD_Zip_loc"));
		
		// Enter the Address1
		COMMON_METHODS.editAField(getTestObject("OL_127"), getTestData("TD_Address_loc"));
		
		// Enter the City
		COMMON_METHODS.editAField(getTestObject("OL_129"), getTestData("TD_City_loc"));
		
		// Enter the County
		COMMON_METHODS.editAField(getTestObject("OL_130"), getTestData("TD_County_loc"));
		
		// Select the Country
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_Country_loc"), "byVisibleText");
		
		// Select the State/Province*
		COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), getTestData("TD_State_loc"), "byVisibleText");
		Thread.sleep(2000);
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"), "Reservations");
		
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		businessComponents.EP_ReservationCareRecipients();
				
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		Thread.sleep(2000);
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Verifying Care Profile Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Click ANy location to edit 
		COMMON_METHODS.clickElement(getTestObject("TD_DelPetLocation"));
		Thread.sleep(2000);
		// Getting the current add and adding "csc" to that and again entering to add field
		String strAdd = COMMON_METHODS.getText(getTestObject("OL_127"));
		strAdd = strAdd.concat("csc");
		COMMON_METHODS.editAField(getTestObject("OL_127"), strAdd);
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_10"), "Reservations");
		
		// Verify that address has not changed in booked reservation
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Verifying Care Profile Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Click ANy location to edit 
		COMMON_METHODS.clickElement(getTestObject("TD_DelPetLocation"));
		Thread.sleep(5000);
		
		// Getting the current add and adding "csc" to that and again entering to add field
		String strAdd2 = COMMON_METHODS.getText(getTestObject("OL_127"));
		
		if (strAdd.trim() != strAdd2.trim())
		{	
				REPORTER.LogEvent(TestStatus.PASS, "Address Edit Verification", "Address Verified Successfully - Edit Not Done","");
		}
		else
		{
				REPORTER.LogEvent(TestStatus.FAIL, "Address Edit Verification", "Address NOT Verified Successfully - Edit Done","");
		}

		
		// Logout from Employee Portal
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	
	 * @author: Sanjeev Singh
	 * @CreationDate: 12/03/2014
	 
	//Test Case #13924:   BUCA - Locations - Able to use zip codes with leading zeros

	@Test()
	public void EP_LeadingZerosZipCode() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Verifying Care Profile Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Click "Add New Location" link
		COMMON_METHODS.clickElement(getTestObject("Location_Add"));
		
		//User should be brought to the add locations page
		//COMMON_METHODS.VerifyTextPresent(getTestObject("Location_Add"), "Locations");
		
		EP_Registration_Locations_Tests.EP_AddLocation_Registration();
		Thread.sleep(5000);
		
		// Deleting newly created location otherwise next time error occurs
		COMMON_METHODS.driver.findElement(By.linkText("My Location")).click();
		Thread.sleep(2000);
		COMMON_METHODS.driver.findElement(By.xpath("//*[@id='0']/div[4]/p/a")).click();
		
		// Logouts Finally
		businessComponents.logout();
		
		//Log Report		
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
		
	
	 * @author: Sanjeev Singh
	 * @CreationDate: 13/03/2014
	 
	//Test Case #14621:   BUCA - Locations - Users without 'In-Home Care' should not be able to see "Will There be Care at this Location?

	@Test()
	public void EP_CareAtLocatioWithoutHome() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Verifying Care Profile Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Click "Add New Location" link
		COMMON_METHODS.clickElement(getTestObject("Location_Add"));
		
		//User should be brought to the add locations page
		COMMON_METHODS.VerifyTextPresent(getTestObject("BenefitOverview"), "Locations");
		
		Thread.sleep(5000);
		
		// Enter the Location Name
		COMMON_METHODS.editAField(getTestObject("OL_125"), getTestData("TD_Name_loc"));

		// Enter the Postal Code
		COMMON_METHODS.editAField(getTestObject("OL_126"), getTestData("TD_Zip_loc"));
		
		// Enter the Address1
		COMMON_METHODS.editAField(getTestObject("OL_127"), getTestData("TD_Address_loc"));
		
		// Enter the City
		COMMON_METHODS.editAField(getTestObject("OL_129"), getTestData("TD_City_loc"));
		
		// Select the Country
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_Country_loc"), "byVisibleText");

		// Enter the County
		COMMON_METHODS.editAField(getTestObject("OL_130"), getTestData("TD_County_loc"));
		Thread.sleep(1000);
		
		// Select the State/Province*
		COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), getTestData("TD_State_loc"), "byVisibleText");
		
		Thread.sleep(1000);
		
		// Selecting Radio Button for care at this location
		COMMON_METHODS.radioButton(getTestObject("CareAtThisLoction"));
		
		//ensure 'Will there be Care at this Location?' only displays for clients with in-home care
		COMMON_METHODS.VerifyTextPresent(getTestObject("Label_CareAtThis_Loc"), "Will there be care at this location? ");
		
		//Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		
		// Deleting newly created location otherwise next time error occurs
		COMMON_METHODS.driver.findElement(By.linkText("My Location")).click();
		Thread.sleep(5000);
		COMMON_METHODS.driver.findElement(By.xpath("//*[@id='0']/div[4]/p/a")).click();
		
		// Logouts Finally
		businessComponents.logout();
		
		//Log Report		
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	
	 * @author: Sanjeev Singh
	 * @CreationDate: 13/03/2014
	 
	//Test Case #11164: BUCA - Locations - ensure user is able to remove a pet from a location

	@Test()
	public void EP_RemoveAPetFromALocation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		Thread.sleep(2000);
		
		// Verifying Care Profile Page
		//COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Click an existing location
		COMMON_METHODS.clickElement(getTestObject("TD_DelPetLocation"));
		Thread.sleep(5000);
		
		// A Pop Comes - need to click OK/Escape to move further as location is under process to completion
		//COMMON_METHODS.driver.findElement(By.id("//button[@type='button']")).sendKeys(Keys.ESCAPE);
		
		//User should be brought to the add locations page
		COMMON_METHODS.VerifyTextPresent(getTestObject("BenefitOverview"), "Locations");
		
		Thread.sleep(5000);
		
		// Click on Remove pet button to remove a pet
		COMMON_METHODS.clickElement(getTestObject("Remove_Pet"));
		
		// Verify --  pet removed
		//System.out.println(COMMON_METHODS.driver.findElements(By.xpath("//div[@id='petInfo0']/div/div/div[contains(@id,'petContainer_')]")).size()); 
		
		
		// Logouts Finally
		businessComponents.logout();
		
		//Log Report
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	
	 * @author: Sanjeev Singh
	 * @CreationDate: 13/03/2014
	 
	//Test Case #11165: BUCA - Locations - ensure 'pet type' dropdown has all its options (Data Elements)

	@Test()
	public void EP_PetTypeDropdownValidation() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Verifying Care Profile Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Click "Add New Location" link
		COMMON_METHODS.clickElement(getTestObject("Location_Add"));
		
		//User will be navigated to the locations page
		COMMON_METHODS.VerifyTextPresent(getTestObject("BenefitOverview"), "Locations");
		
		//Find the radio button yes for "will there be care at this location" and click
		COMMON_METHODS.radioButton(getTestObject("CareAt_This_Location_Yes"));
		
		//Verify -- Hidden fields will appear on the page	
		COMMON_METHODS.VerifyTextPresent(getTestObject("Verify_CareAtThis_Loc_Yes"), "Tell us about the care environment");
		REPORTER.LogEvent(TestStatus.PASS, "Verify Hidden fields", "Hidden fields Appears on the Page" , "");
		
		//Find yes radio button for "are there any pets in this care location and select it
		COMMON_METHODS.radioButton(getTestObject("Pets_Care_Loc_Yes"));
		
		//Hidden fields will appear: type of pet, How many, What breed, and Add pet link
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("Verify_Pets_Type"), true);
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("Verify_HowMany"), true);
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("Verify_Add_PetLink"), true);
		
		//Click on the drop down for pet type
		COMMON_METHODS.clickElement(getTestObject("Verify_Pets_Type"));
		
		//Drop down options should consist of Cat, dog, other
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("Verify_Pets_Type"), "Cat");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("Verify_Pets_Type"), "Dog");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("Verify_Pets_Type"), "Other");
		
		// Logouts Finally
		businessComponents.logout();
		
		//Log Report		
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	 * @author: Sanjeev Singh
	 * @CreationDate: 13/03/2014
	 
	//Test Case #4206: BUCA - Locations - A list of addresses will be displayed

	@Test()
	public void EP_ValidateLocations() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Verifying Care Profile Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Ensure My Locations links are displaying
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("CP_Loc1"), true);
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("CP_Loc2"), true);
		
		// Logouts Finally
		businessComponents.logout();
		
		//Log Report		
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	 * @author: Sanjeev Singh
	 * @CreationDate: 13/03/2014
	 
	//Test Case #4209: BUCA - Locations - A 'Home' address is required and user is not able to change Location name once created

	@Test()
	public void EP_CannotUpdateLocations() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Verifying Care Profile Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_25"),"Care Profile");
		
		//Find the existing location named 'Home' and click on the name link
		COMMON_METHODS.clickElement(getTestObject("Loc_Home"));
		
		//User is navigated to the locations edit page	
		COMMON_METHODS.VerifyTextPresent(getTestObject("Edit_Home"),"Home");
		
		//Ensure the Location name field displays home and is disabled so the user cannot make a change		
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("Edit_Home"), false);
		
		// Logout Finally
		businessComponents.logout();
		
		//Log Report		
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 18/03/2014
	 
	//Test Case #3800: BUCA - My Locations - Add Location - system displays error message if there are missing or invalid fields

	@Test()
	public void EP_Locations_AddLocation_LocationFieldValidations(ITestContext TC) throws Exception {
		
		// Strings to store messages
		String strErrorMsgs;
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP10");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client10.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click ADD button of Locations section
		COMMON_METHODS.clickElement(getTestObject("CareProfile_Locations_Add"));
		
		// Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		
		// Verify the error messages displayed
		strErrorMsgs = COMMON_METHODS.getText(getTestObject("RESV_24"));
		
		// Get the options from test data
		String strLocErrMsgs = getTestData("TD_Loc_ErrorMsgs");
		String[] arrLocErrMsgs = strLocErrMsgs.split(",");
		
		// Verify each option is present in the drop-down
		for (String msg : arrLocErrMsgs) {

			if(strErrorMsgs.contains(msg)){
				REPORTER.LogEvent(TestStatus.PASS, "Verify '" + msg + "' message is displayed.", "'" + msg + "' message is displayed.","");
			}else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify '" + msg + "' message is displayed.", "'" + msg + "' message is not displayed.","");
			}
		}
		
		// Enter the location name
		COMMON_METHODS.editAField(getTestObject("OL_125"),getTestData("TD_SpecialChars"));

		// Enter the Address
		COMMON_METHODS.editAField(getTestObject("OL_127"),getTestData("TD_SpecialChars"));

		// Select United States from the Country drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_Country_loc"), "byVisibleText");
		
		// Enter the Postal code  
		COMMON_METHODS.editAField(getTestObject("OL_126"),getTestData("TD_SpecialChars"));
		Thread.sleep(3000);
		
		// Enter the city 
		COMMON_METHODS.editAField(getTestObject("OL_129"),getTestData("TD_SpecialChars"));
		
		// Select the state 
		COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), getTestData("TD_State_loc"), "byVisibleText");

		// Enter the county 
		COMMON_METHODS.editAField(getTestObject("OL_130"), getTestData("TD_SpecialChars"));

		// Select No from Will there be care at this location?   
		COMMON_METHODS.radioButton(getTestObject("WillThereBeCare_No"));
		
		//Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		
		// Verify the error message displayed
		String strErrorMsg = COMMON_METHODS.getText(getTestObject("RESV_24"));
		String msg = getTestData("TD_PostalCode_ErrorMsg");
		
		// Verify the Postal code error message is displayed
		if(strErrorMsg.contains(msg)){
			REPORTER.LogEvent(TestStatus.PASS, "Verify '" + msg + "' message is displayed.", "'" + msg + "' message is displayed.","");
		}else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify '" + msg + "' message is displayed.", "'" + msg + "' message is not displayed.","");
		}
		
		// Logout from Employee Portal
		businessComponents.logout();

		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 13/03/2014
	 
	//Test Case #3801: BUCA - Locations - ensure system uses zipcode pre-populates city, state and county fields

	@Test()
	public void EP_Locations_ZipCode_AutoComlpete(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client10.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click ADD button of Locations section
		COMMON_METHODS.clickElement(getTestObject("CareProfile_Locations_Add"));

		// Select United States from the Country drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_County_loc"), "byVisibleText");
		
		// Enter the Postal code  
		COMMON_METHODS.editAField(getTestObject("OL_126"),getTestData("TD_Zip_loc"));
		Thread.sleep(3000);

		// Click the Postal code field to close the auto-complete
		COMMON_METHODS.clickElement(getTestObject("OL_126"));
		
		// Verify the correct city is populated 
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_129"), getTestData("TD_City_loc"));

		// Verify the correct state is populated 
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_133"), getTestData("TD_State_loc"));

		// Verify the correct country is populated 
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_130"), getTestData("TD_County_loc"));

		// Logout from Employee Portal
		businessComponents.logout();

		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 14/03/2014
	 
	//Test Case #3803: BUCA - Locations - user must create a unique "short name" for each address

	@Test()
	public void EP_Locations_UniqueLocationName(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
		// SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
		readTestObject(getDataTablePath("EP"), "TO_EP");
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// Login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client10.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click ADD button of Locations section
		COMMON_METHODS.clickElement(getTestObject("CareProfile_Locations_Add"));
		
		// Enter the location name
		COMMON_METHODS.editAField(getTestObject("OL_125"),getTestData("TD_Name_loc"));

		// Enter the Address
		COMMON_METHODS.editAField(getTestObject("OL_127"),getTestData("TD_Address_loc"));

		// Select United States from the Country drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_Country_loc"), "byVisibleText");
		
		// Enter the Postal code  
		COMMON_METHODS.editAField(getTestObject("OL_126"),getTestData("TD_Zip_loc"));
		Thread.sleep(3000);

		// Click the Postal code field to close the auto-complete
		COMMON_METHODS.clickElement(getTestObject("OL_126"));
		
		// Enter the city 
		COMMON_METHODS.editAField(getTestObject("OL_129"),getTestData("TD_City_loc"));
		
		// Select the state 
		COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), getTestData("TD_State_loc"), "byVisibleText");

		// Enter the country 
		COMMON_METHODS.editAField(getTestObject("OL_130"), getTestData("TD_County_loc"));

		// Select No from Will there be care at this location?   
		COMMON_METHODS.radioButton(getTestObject("WillThereBeCare_No"));
		
		//Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		
		// Click ADD button of Locations section
		COMMON_METHODS.clickElement(getTestObject("CareProfile_Locations_Add"));
		
		// Enter the location name
		COMMON_METHODS.editAField(getTestObject("OL_125"),getTestData("TD_Name_loc"));

		// Enter the Address
		COMMON_METHODS.editAField(getTestObject("OL_127"),getTestData("TD_Address_loc"));

		// Select United States from the Country drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_Country_loc"), "byVisibleText");
		
		// Enter the Postal code  
		COMMON_METHODS.editAField(getTestObject("OL_126"),getTestData("TD_Zip_loc"));
		Thread.sleep(3000);

		// Click the Postal code field to close the auto-complete
		COMMON_METHODS.clickElement(getTestObject("OL_126"));
		
		// Enter the city 
		COMMON_METHODS.editAField(getTestObject("OL_129"),getTestData("TD_City_loc"));
		
		// Select the state 
		COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), getTestData("TD_State_loc"), "byVisibleText");

		// Enter the country 
		COMMON_METHODS.editAField(getTestObject("OL_130"), getTestData("TD_County_loc"));

		// Select No from Will there be care at this location?   
		COMMON_METHODS.radioButton(getTestObject("WillThereBeCare_No"));
		
		//Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		
		// Verify the location is displayed
		try {
			WebElement weLocations = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='validation-summary-errors']"));
			if(weLocations.isDisplayed()){
				REPORTER.LogEvent(TestStatus.PASS, "Verify the 'Duplicate location is not allowed.' message is displayed.", "'Duplicate location is not allowed.' message is displayed.", "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify the 'Duplicate location is not allowed.' message is displayed.", "'Duplicate location is not allowed.' message is not displayed.", "");
			}
		}catch (Exception e) {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the 'Duplicate location is not allowed.' message is displayed.", "'Duplicate location is not allowed.' message is not displayed.", "");
		} 
		
		// Logout from Employee Portal
		businessComponents.logout();

		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 12/03/2014
	 
	//Test Case #3809: BUCA - Locations - authorized contacts can update addresses (if given permission)

	@Test()
	public void EP_Locations_UpdateAddress(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Click the first link under Locations section
		COMMON_METHODS.clickElement(getTestObject("RESV_27"));
		
		// Update the address
		COMMON_METHODS.editAField(getTestObject("OL_127"), "Updated Address");
		
		// Click the Update Address button
		COMMON_METHODS.clickElement(getTestObject("CP_09"));
		
		// Logout from Employee Portal
		businessComponents.logout();
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));

		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click the first link under Locations section
		COMMON_METHODS.clickElement(getTestObject("RESV_27"));
		
		// Verify the address field is showing updated value 
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_127"), "Updated Address");

		// Logout from Employee Portal
		businessComponents.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 13/03/2014
	 
	//Test Case #3813: BUCA - Locations - system displays an error message if there are missing or invalid fields

	@Test()
	public void EP_Locations_AddLocation_ValidationMessages(ITestContext TC) throws Exception {
		
		// Strings to store messages
		String strErrorMsgs;
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client10.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click ADD button of Locations section
		COMMON_METHODS.clickElement(getTestObject("CareProfile_Locations_Add"));
		
		// Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		
		// Verify the error messages displayed
		strErrorMsgs = COMMON_METHODS.getText(getTestObject("RESV_24"));
		
		// Get the options from test data
		String strLocErrMsgs = getTestData("TD_Loc_ErrorMsgs");
		String[] arrLocErrMsgs = strLocErrMsgs.split(",");
		
		// Verify each option is present in the drop-down
		for (String msg : arrLocErrMsgs) {

			if(strErrorMsgs.contains(msg)){
				REPORTER.LogEvent(TestStatus.PASS, "Verify '" + msg + "' message is displayed.", "'" + msg + "' message is displayed.","");
			}else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify '" + msg + "' message is displayed.", "'" + msg + "' message is not displayed.","");
			}
		}
		
		// Logout from Employee Portal
		businessComponents.logout();

		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 13/03/2014
	 
	//Test Case #4208: BUCA - Locations - User is able to add an address

	@Test()
	public void EP_Locations_Add_Address(ITestContext TC) throws Exception {

		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// Login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client10.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click ADD button of Locations section
		COMMON_METHODS.clickElement(getTestObject("CareProfile_Locations_Add"));
		
		// Enter the location name
		COMMON_METHODS.editAField(getTestObject("OL_125"),getTestData("TD_Name_loc"));

		// Enter the Address
		COMMON_METHODS.editAField(getTestObject("OL_127"),getTestData("TD_Address_loc"));

		// Select United States from the Country drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_Country_loc"), "byVisibleText");
		
		// Enter the Postal code  
		COMMON_METHODS.editAField(getTestObject("OL_126"),getTestData("TD_Zip_loc"));
		Thread.sleep(3000);

		// Click the Postal code field to close the auto-complete
		COMMON_METHODS.clickElement(getTestObject("OL_126"));
		
		// Enter the city 
		COMMON_METHODS.editAField(getTestObject("OL_129"),getTestData("TD_City_loc"));
		
		// Select the state 
		COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), getTestData("TD_State_loc"), "byVisibleText");

		// Enter the county 
		COMMON_METHODS.editAField(getTestObject("OL_130"), getTestData("TD_County_loc"));

		// Select No from Will there be care at this location?   
		COMMON_METHODS.radioButton(getTestObject("WillThereBeCare_No"));
		
		//Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		
		// Verify the location is displayed
		try {
			WebElement weLocations = BH_SetUp_TearDown.driver.findElement(By.xpath("//a[text()='" + getTestData("TD_Name_loc").trim()+ "']"));
			if(weLocations.isDisplayed()){
				REPORTER.LogEvent(TestStatus.PASS, "Verify the location is added.", "Location: " + getTestData("TD_Name_loc") + " is added succesfully.", "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify the location is added.", "Location: " + getTestData("TD_Name_loc") + " is not added.", "");
			}
		}catch (Exception e) {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the location is added.", "Location: " + getTestData("TD_Name_loc") + " is not added.", "");
		} 

		String strAddress = getTestData("TD_Address_loc") + ", " + getTestData("TD_City_loc") + ", " + getTestData("TD_State_loc") + ", " + getTestData("TD_Zip_loc");		
		try {
			WebElement weLocations = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='pageContent']/div[9]"));
			if(weLocations.getText().contains(strAddress)){
				REPORTER.LogEvent(TestStatus.PASS, "Verify the address is added.", "Address is added as follows:\n" + strAddress , "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify the address is added.", "Address is added as follows:\n" + strAddress , "");
			}
		}catch (Exception e) {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the address is added.", "Address is not updated." , "");
		} 

		// Logout from Employee Portal
		businessComponents.logout();

		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 14/03/2014
	 
	//Test Case #4212: BUCA - Automation -  Locations - User is able to update an existing address

	@Test()
	public void EP_Locations_Update_Address(ITestContext TC) throws Exception {

		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// Login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client10.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));

		// Click location link 'Home'
		COMMON_METHODS.clickElement(getTestObject("CP_03"));
	
		// Verify the location name is disabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_125"), false);
		
		// Enter the Address
		COMMON_METHODS.editAField(getTestObject("OL_127"),getTestData("TD_Address_loc"));

		// Select United States from the Country drop-down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), getTestData("TD_Country_loc"), "byVisibleText");
		
		// Enter the Postal code  
		int intNewZip =  Integer.parseInt(getTestData("TD_Zip_loc")) + 1;
		String strNewZip = Integer.toString(intNewZip);
		
		while(strNewZip.length()<5){
			strNewZip = "0" + strNewZip;
		}
		
		COMMON_METHODS.editAField(getTestObject("OL_126"),strNewZip);
		Thread.sleep(3000);

		// Click the Postal code field to close the auto-complete
		COMMON_METHODS.clickElement(getTestObject("OL_126"));
		
		// Enter the city 
		COMMON_METHODS.editAField(getTestObject("OL_129"),getTestData("TD_City_loc"));
		
		// Select the state 
		COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), getTestData("TD_State_loc"), "byVisibleText");

		// Enter the country 
		COMMON_METHODS.editAField(getTestObject("OL_130"), getTestData("TD_County_loc"));

		//Click 'Update Location' button
		COMMON_METHODS.clickElement(getTestObject("CP_09"));
		
		// Verify the location is displayed
		try {
			WebElement weLocations = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='pageContent']/div[9]/ul/li/a[contains(text(),'" + getTestData("TD_Name_loc") + "'"));
			if(weLocations.isDisplayed()){
				REPORTER.LogEvent(TestStatus.PASS, "Verify the location is updated.", "Location: " + getTestData("TD_Name_loc") + " is updated succesfully.", "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify the location is updated.", "Location: " + getTestData("TD_Name_loc") + " is not updated.", "");
			}
		}catch (Exception e) {
			
		} 

		String strAddress = getTestData("TD_Address_loc") + ", " + getTestData("TD_City_loc") + ", " + getTestData("TD_State_loc") + ", " + getTestData("TD_Zip_loc");		
		try {
			WebElement weLocations = BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='pageContent']/div[9]"));
			if(weLocations.getText().contains(strAddress)){
				REPORTER.LogEvent(TestStatus.PASS, "Verify the address is updated.", "Address is updated as follows:\n" + strAddress , "");
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify the address is updated.", "Address is updated as follows:\n" + strAddress , "");
			}
		}catch (Exception e) {
			REPORTER.LogEvent(TestStatus.FAIL, "Verify the address is updated.", "Address is not updated." , "");
		} 

		// Logout from Employee Portal
		businessComponents.logout();
		 
		//Log to reports 
		 COMMON_METHODS.logToReportAfterPass(methodName);
	}

	
	 * @author: Krishna Chaitanya Maringanti
	 * @CreationDate: 12/03/2014
	 
	//Test Case #11166: BUCA - Locations - ensure 'How Many Pets' field goes up to 10

	@Test()
	public void EP_Locations_AddLocation_PetFieldValidations(ITestContext TC) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client8.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Click ADD button of Locations section
		COMMON_METHODS.clickElement(getTestObject("CareProfile_Locations_Add"));
		
		// Select 'Yes' from the Will there be care at this location?  
		COMMON_METHODS.radioButton(getTestObject("WillThereBeCare_Yes"));
		Thread.sleep(3000);
		
		// Select 'Yes' from the Are there any pets in the care location?
		COMMON_METHODS.radioButton(getTestObject("AreThereAnyPets_Yes"));
		Thread.sleep(3000);
	
		// Verify 1-10 numbers are present in the drop-down
		for(int iNoOfPets=1;iNoOfPets<=10;iNoOfPets++) {

			COMMON_METHODS.isOptionPresentInListBox(getTestObject("HowManyPets"),Integer.toString(iNoOfPets));
		}
		
		// Logout from Employee Portal
		businessComponents.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	 * @author: Deepa
	 * @CreationDate: 13/03/2014
	 
	//Test Case #4134: BUCA - My Care Profile - Able to remove a care recipient from profile

	@Test()
	public void EP_CareRecipient_Inactive(ITestContext TC) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("RG_01"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("RG_02"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.editAField(getTestObject("RG_03"), "Inactive");
		
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		businessComponents.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	
	 * @author: Deepa
	 * @CreationDate: 13/03/2014
	 
	//Test Case #4211: BUCA - Locations - Selecting an address displays all address information

	@Test()
	public void EP_CareProfile_VerifyAddressInfo(ITestContext TC) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Read test data for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		// Launch the browser
		businessComponents.EP_LaunchBrowser(TC);
		
		// login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client1.userName"), getTestData("TD_PWD"));
		
		// Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		// Click 'Care Profile' link from top navigation menu
	//	COMMON_METHODS.clickElement(getTestObject("RG_01"));
		
		// Click 'Home' location
		COMMON_METHODS.clickElement(getTestObject("CP_03"));
		
		//Verify Address
		String addr=COMMON_METHODS.getText(getTestObject("CP_04"),"value");
		System.out.println(addr);
		if(addr!=null)
			REPORTER.LogEvent(TestStatus.PASS, "Verify " + addr +" is displayed","Address: "+addr+" is displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify " + addr +" is displayed","Address: "+addr+" is Empty", "");
		
		//Verify Postalcode
		String postcode=COMMON_METHODS.getText(getTestObject("CP_05"),"value");
		System.out.println(postcode);
		if(postcode!=null)
			REPORTER.LogEvent(TestStatus.PASS, "Verify" + postcode +" is displayed","Postal code: "+postcode+" is displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify" + postcode +"is displayed","Postal code: "+postcode+" is Empty", "");
		
		//Verify City
		String city=COMMON_METHODS.getText(getTestObject("CP_06"),"value");
		System.out.println(city);
		if(city!=null)
			REPORTER.LogEvent(TestStatus.PASS, "Verify " + city +" is displayed","City: "+city+" is displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify " + city +" is displayed","City: "+city+" is Empty", "");
		
		//Verify County
		String county=COMMON_METHODS.getText(getTestObject("OL_130"),"value");
		System.out.println(county);
		if(county!=null)
			REPORTER.LogEvent(TestStatus.PASS, "Verify " + county +" is displayed","County: "+county+" is displayed", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify " + county +" is displayed","County: "+county+" is Empty", "");
		
		//Verify Country
		String country=COMMON_METHODS.getSelectedValueFromListBox(getTestObject("CP_08"));
		System.out.println(country);
		if(country!=null)
			REPORTER.LogEvent(TestStatus.PASS, "Verify " + country +" is displayed","Country: "+country+" is selected", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify " + country +" is displayed","Country: "+country+" is Empty", "");
		
		//Verify State
		String state=COMMON_METHODS.getSelectedValueFromListBox(getTestObject("CP_07"));
		System.out.println(state);
		if(state!=null)
			REPORTER.LogEvent(TestStatus.PASS, "Verify " + state +" is displayed","State: "+state+" is seleced", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify " + state +" is displayed","State: "+state+" is Empty", "");
		
		businessComponents.logout();

		//Log to reports 
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	

	private void loginEPPoratl() throws IOException, Exception {
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			//Verify Login Page displayed
			businessComponents.LoginEmployeeportal(
					ReadwritDataFromProps.props.getProperty("client63.userName"),
					getTestData("TD_PWD"));
		}
	}
}
 
*/