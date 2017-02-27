package com.bhs.scripts.employeeportal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_Registration_SmokeTest_Tests extends INITIALIZE {
	
	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	String employer;
	
	//Create dynamic user name
	String userName = createDyanamicUserData();
			
	//Reading Test Objects from Data excel 
	
	/**
	 * BUCA - SMOKE TEST 
	 *  TFS ID : 16222
	 */
	@Test @Parameters("client") 
	public void EP_Smoke_Tests(String client) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		
		employer=client;
		
		// Launch browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Verify Employer
		businessComponents.EP_verifyEmployer(client);

		//Accept  policy and submit
		businessComponents.EP_AcceptPolicyAndSubmit();

		//Sign Up
		businessComponents.EP_SignUpUser(userName,client,"smoketest");
		
		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
				
		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));
				
	   //this.EP_VerifyPersonalInformation();
				
		//Update Employee profile
		businessComponents.EP_UpdateEmployeeprofile(client);

		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));

		//Test data for adding Care Recipients
		String addCrData[] = {
				getTestData("TD_CR_FirstName"),
				getTestData("TD_CR_LastName"), "Father",
				getTestData("TD_DOB"),
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
		
		businessComponents.addCareRecipients(addCrData, "No",client);
		
		// Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click on already existing care Recipient link
		COMMON_METHODS.clickElement(getTestObject("MA_65"));
		
		//Verify the care recipient details are updated
		businessComponents.Care_Profile_Verify();
		
		//Click 'Cancel' in 'Care Recipients'
		COMMON_METHODS.clickElement(getTestObject("CARE_01"));
		
		Thread.sleep(1000);
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_65"));
		
		//Update Care recipients
		this.updateCareRecipients();
		
		//Click on Care Profile link
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
				
		Thread.sleep(1000);
		
		//Click on already existing care Recepient link
		COMMON_METHODS.clickElement(getTestObject("MA_65"));
		
		//verify Update Care recipients
		this.verifyUpdateCareRecipients();
		
		//Click 'Cancel' in 'Care Recipients'
		COMMON_METHODS.clickElement(getTestObject("CARE_01"));
		
		Thread.sleep(1000);
		
		//Click on Authorized Contacts icon
		COMMON_METHODS.clickElement(getTestObject("OL_136"));
				
		//Add Authorization Contacts
		businessComponents.EP_AddAuthorizedContacts();
		
		try {
			
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_AC_FirstName")+" "+getTestData("TD_AC_LastName"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Authorized Contact created", "Authorized Contact - " + sTemp + " Created" , "");
			
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Authorized Contact created");
		}
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		Thread.sleep(2000);
		
		//Make some changes to the Authorized Contact Information
		COMMON_METHODS.editAField(getTestObject("OL_63"), "6666666666");
		
		//Click 'Update' button
		COMMON_METHODS.clickElement(getTestObject("AUTH_UPDATE"));
		
		//Click on an existing Authorized Contact's link
		COMMON_METHODS.clickElement(getTestObject("CP_02"));
		
		Thread.sleep(2000);
		
		//Verify Authorized Contact Information in 'Care Profile' section are already populated with correct values
    	if(COMMON_METHODS.getText(getTestObject("OL_63"),"value").equals("6666666666")){
			REPORTER.LogEvent(TestStatus.PASS, "Authorized Contact Information field is updated", "Other Helpful Information field is updated", "");
    	}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Authorized Contact Information field is Not updated", "Other Helpful Information field is Not updated", "");
		}
    	
    	//Click 'Update' button
		COMMON_METHODS.clickElement(getTestObject("AUTH_UPDATE"));
		
		Thread.sleep(2000);
		
		//Click the 'Add' link next to the Locations section 
		COMMON_METHODS.clickElement(getTestObject("OL_124"));

		//Create locations
		String data[]={"Office","60601","103 Fox Road","Flag st","Chicago","Cook",
						"United States",null};
		
		businessComponents.EP_AddLocation(data);
		
		//Clicking Existing office link
		COMMON_METHODS.driver.findElement(By.linkText("Office")).click();

		//Update location
		businessComponents.EP_CareprofileUpdateLocation();
		
		// Create new Reservation
		this.EP_NewReservation();
		
		//Navigate to the home page
		COMMON_METHODS.clickElement(getTestObject("OL_8"));
		
		//Click Client employee name (update profile) 
		COMMON_METHODS.clickElement(getTestObject("SOL_02"));
		
		//Fill in all fields in the Employee Profile of the Care Profile and click 'Update Employee Profile' button 
		businessComponents.EP_UpdateEmployeeprofile(client);

		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));

		//Ensure all fields that were filled out contain the correct data
		this.EP_VerifyPersonalInformation(client);
		
		// Click 'Benefit' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("BOL_01"));

		// Verify 'Overview' page loads correctly
		COMMON_METHODS.VerifyTextPresent(getTestObject("BOL_02"), "Overview");
		
		// Click 'Providers' tab
		COMMON_METHODS.clickElement(getTestObject("PT_01"));
		
		//Click 'NOMINATE A CARE PROVIDER' link
		COMMON_METHODS.clickElement(getTestObject("PT_06"));
		
		// Click 'Providers' tab
		COMMON_METHODS.clickElement(getTestObject("PT_01"));
		
		//Click 'NOMINATE A CARE PROVIDER' link
		COMMON_METHODS.clickElement(getTestObject("PT_06"));
		
		//Type '<name>' in 'Provider Name' text box
		businessComponents.EP_ProviderEnterText("Name", "CSC");
		
		//Type '<address>' in 'Address' text box
		businessComponents.EP_ProviderEnterText("Address", "Denver");
		
		//	Type '<city, state, zip>' in 'City, State, Zip/Postal Code' text box
		businessComponents.EP_ProviderEnterText("ZipCode", "80231");
		
		//Type '<phone>' in '' Telephone Number' text box
		businessComponents.EP_ProviderEnterText("Contactno", "7202279345");
		
		//Type '<email>' in 'Email Address' text box
		businessComponents.EP_ProviderEnterText("Email", "deepamca8@gmail.com");
		
		//Type '<name>' in 'Agency/Center Contact' text box
		businessComponents.EP_ProviderEnterText("Agent", "Vicky");
		
		//	Type '<relationship>' in 'How do you know of this provider?' text box
		businessComponents.EP_ProviderEnterText("Providerdata", "Online");
		
		//Select 'YES, you may share my name and company with the provider.' check box
		COMMON_METHODS.radioButton(getTestObject("PT_15"));
		
		//Click 'Submit' button
		COMMON_METHODS.clickElement(getTestObject("PT_16"));

		//Find the "Find Care Near You" module and click the dropdown and Select one of the location
		COMMON_METHODS.listBoxSelect(getTestObject("PT_02"), "Home",
				"byVisibleText");

		//	Click the Search button
		COMMON_METHODS.clickElement(getTestObject("PT_03"));

		// Drag the distance slider to get provider results
		//COMMON_METHODS.DragandDrop(getTestObject("PT_04"), 220, 0);
	
		Thread.sleep(10000);
		
		//Verify the user can view all providers the employee has access to
		businessComponents.EP_ProviderVerifyNoOfProviders();
		
		//Clicks My Account link
		//Click on Employee link
		Utility.clickLink(getTestObject("MA_01"));
		
		//Expands General Settings section
		Utility.clickLink(getTestObject("MA_02"));
		
		// Click the Help navigation tab
		COMMON_METHODS.clickElement(getTestObject("HL_01"));
		
		//Verify Help Page displayed
		if(BH_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Help")){
			REPORTER.LogEvent(TestStatus.PASS, "Help Page Displayed", "Help Page Displayed", "");
		}
				
		//Click Feedback
		COMMON_METHODS.clickElement(getTestObject("OL_4"));

		//Verify Feedback Page displayed
		if(BH_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Feedback")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Feedback Page Displayed", "Feedback Page Displayed", "");
		}

		//Click Home
		COMMON_METHODS.clickElement(getTestObject("OL_8"));

		//Click Privacy Policy
		COMMON_METHODS.clickElement(getTestObject("OL_5"));

		//Verify Privacy Policy Page displayed
		if(BH_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Privacy Policy")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Privacy Policy Page Displayed", "Privacy Policy Page Displayed", "");
		}

		//Click Home
		COMMON_METHODS.clickElement(getTestObject("OL_8"));

		//Click Terms of Use
		COMMON_METHODS.clickElement(getTestObject("OL_6"));

		//Verify Terms of Use Page displayed
		if(BH_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Terms Of Use")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Terms Of Use Page Displayed", "Terms Of Use Page Displayed", "");
		}

		//Click Home
		COMMON_METHODS.clickElement(getTestObject("OL_8"));

		//Click Trademark Notice
		COMMON_METHODS.clickElement(getTestObject("OL_7"));

		//Verify Trademark Notice Page displayed
		if(BH_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Trademark Notice")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Trademark Notice Page Displayed", "Trademark Notice Page Displayed", "");
		}
		
		//logout
		Utility.logout();
		
		//login employee portal
		 String userName = ReadwritDataFromProps.props.getProperty(client + ".smoketest.userName");
		 String password = getTestData("TD_PWD");
		 
		 String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		 Utility.loginToBUCA(userName, password,signInArray);
		
		//Verify Home Page displayed
		if(BH_SetUp_TearDown.driver.getTitle().equalsIgnoreCase("Back-Up Care Advantage")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Home Page Displayed", "Home Page Displayed", "");
		}
		
		//logout
		Utility.logout();
	}
	
	
	
	
	/**
	 * Update the Care Recipients in Care profile
	 * 
	 * @author vverimadugu
	 * @version 
	 * @return void
	 * @param 
	 * Creation Date 05-03-2014
	 */
	private void updateCareRecipients() throws IOException, Exception
	{
		
		
		//Select Relationship to Client Employee
		COMMON_METHODS.listBoxSelect(getTestObject("OL_44"), "Parent", "byVisibleText");
		
		//Enter Birth Date
		COMMON_METHODS.editAField(getTestObject("OL_45"), getTestData("TD_CareReci_DOB"));
		
		//Enter Other Helpful Information under  Additional Information section
		COMMON_METHODS.editAField(getTestObject("OL_53"), getTestData("TD_CareReci_UpdateInfo"));
		
		//Select Where do you primarily need care
		COMMON_METHODS.listBoxSelect(getTestObject("OL_56"), getTestData("TD_State2"), "byVisibleText");
		
		//Enter Regular Care Arrangements
		COMMON_METHODS.editAField(getTestObject("OL_99"), getTestData("TD_Regular_CareArrang"));
		
		//Click on Update Care Recepient
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
				
	}

	/**
	 * Verify Update the Care Recipients in Care profile
	 * 
	 * @author vverimadugu
	 * @version 
	 * @return void
	 * @param 
	 * Creation Date 05-03-2014
	 */
	private void verifyUpdateCareRecipients() throws IOException, Exception
	{
		
	    	//Verify the Relation is populated or not
	    	if(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_44")).equals("Parent"))
	    		REPORTER.LogEvent(TestStatus.PASS, "Relation name field is updated", "First name field is updated", "");
			else{
				// Report the below statement
				REPORTER.LogEvent(TestStatus.FAIL, "Relation name field is Not updated", "First name field is Not updated", "");
			}
	    	
	    	//Verify correct date of birth is selected or not
	    	if(COMMON_METHODS.getText(getTestObject("OL_45"),"value").equals(getTestData("TD_CareReci_DOB"))) 
				REPORTER.LogEvent(TestStatus.PASS, "Date of Birth field is updated", "Date of Birth field is updated", "");
			else{
				// Report the below statement
				REPORTER.LogEvent(TestStatus.FAIL, "Date of Birth field is Not updated", "Date of Birth field is Not updated", "");
			}
	    	
	    	//Verify Other Helpful Information in 'Care Profile' section are already populated with correct values
	    	if(COMMON_METHODS.getText(getTestObject("OL_53"),"value").equals(getTestData("TD_CareReci_UpdateInfo"))){
				REPORTER.LogEvent(TestStatus.PASS, "Other Helpful Information field is updated", "Other Helpful Information field is updated", "");
	    	}else{
				// Report the below statement
				REPORTER.LogEvent(TestStatus.FAIL, "Other Helpful Information field is Not updated", "Other Helpful Information field is Not updated", "");
			}
	    	
	    	//Verify the Select Where do you primarily need care is populated or not
	    	if(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_56")).equals(getTestData("TD_State2")))
	    		REPORTER.LogEvent(TestStatus.PASS, "Relation name field is updated", "First name field is updated", "");
			else{
				// Report the below statement
				REPORTER.LogEvent(TestStatus.FAIL, "Relation name field is Not updated", "First name field is Not updated", "");
			}
	    				
	    	//Verify Regular Care Arrangements in 'Care Profile' section are already populated with correct values
	    	if(COMMON_METHODS.getText(getTestObject("OL_99"),"value").equals(getTestData("TD_Regular_CareArrang"))){
				REPORTER.LogEvent(TestStatus.PASS, "Regular Care Arrangements field is updated", "Regular Care Arrangements field is updated", "");
	    	}else{
				// Report the below statement
				REPORTER.LogEvent(TestStatus.FAIL, "Regular Care Arrangements field is Not updated", "Regular Care Arrangements field is Not updated", "");
			}
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public void EP_NewReservation() throws Exception {

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));

		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");

		int HealthStatus[] = { 1 };
		String selectForReason = getTestData("TD_ReasonForCare");
		
		//businessComponents.EP_ReservationCareRecipients();
		businessComponents.EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");

		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");

		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation4"));

		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		businessComponents.EP_ReservationWhenandWhere(careDates,actions);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		businessComponents.EP_ReservationCareOptions();

		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");

		businessComponents.EP_ReservationVerifyInfoNo();

		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		businessComponents.EP_ReservationReveiwDetails();

	}
	
	/**
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 * 
	 */
	private void EP_VerifyPersonalInformation(String client) throws IOException, Exception {

		//Verifying First Name in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_76"), ReadwritDataFromProps.props.getProperty(client + ".smoketest.firstName"), "value");

		//Verifying Last Name in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_77"), ReadwritDataFromProps.props.getProperty(client + ".smoketest.lastName"), "value");

		//Verifying Email in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_78"), ReadwritDataFromProps.props.getProperty(client + ".smoketest.userEmail"), "value");

		//Verifying Gender in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_27"));

		//Verifying Primary Phone Type in 'Personal Information' section are populated with correct values
		if ( COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_28")).equals("Work") )
			REPORTER.LogEvent(TestStatus.PASS, "Verify Primary Phone Type in 'Personal Information' section ", "Correct value displayed as " + "Work", "");
		else
			REPORTER.LogEvent(TestStatus.PASS, "Verify Primary Phone Type in 'Personal Information' section ", "Correct value not displayed as" + "Work", "");

		//Verifying Primary Phone Number in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_29"), getTestData("TD_Phone"), "value");		

		//Verifying Home Address in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_30"), getTestData("TD_Address"), "value");

		//Verifying City in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_31"), getTestData("TD_City"), "value");

		//Select State/Province
		COMMON_METHODS.listBoxSelect(getTestObject("OL_32"), getTestData("TD_State"), "byVisibleText");

		//Verifying County in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_33"), getTestData("TD_County"), "value");

		//Verifying Country in 'Personal Information' section are populated with correct values
		if ( COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_34")).equals(getTestData("TD_Country")) )
			REPORTER.LogEvent(TestStatus.PASS, "Verify Country in 'Personal Information' section ", "Country displayed as " + getTestData("TD_Country") + " in 'Personal Information' section ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Country in 'Personal Information' section ", "Country displayed wrongly as " + getTestData("TD_Country") + " in 'Personal Information' section ", "");

		//Verifying 'Postal Code' in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_35"), getXMLData("Client"+client,"TD_Zip"), "value");

		//Verifying 'Work City' in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_36"), getTestData("TD_City"), "value");

		//Verifying Work State in 'Personal Information' section are populated with correct values
		if ( COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_37")).equals(getTestData("TD_State")) )
			REPORTER.LogEvent(TestStatus.PASS, "Verify State in 'Personal Information' section ", "State displayed as " + getTestData("TD_Country") + " in 'Personal Information' section ", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify State in 'Personal Information' section ", "state displayed wrongly as " + getTestData("TD_Country") + " in 'Personal Information' section ", "");

		//Verifying 'Job Title' in 'Personal Information' section are populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_38"), getTestData("TD_JobTitle"), "value");

	}
}
