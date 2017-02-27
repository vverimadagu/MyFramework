package com.bhs.scripts.employeeportal;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_CBReservation extends INITIALIZE {

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
	
	
	String employer1=null;
	@Test @Parameters("client")
	public void EP_CBReservationTest(String employer1 ) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Sign Up and Registering 10 clients
		for (int i = 1; i <= 10; i++) {
			
			// Creating users
			if (i == 1 || i == 2 || i == 5 || i == 7 || i == 8) {
				for (int j = 1; j <= 1; j++) {
					
					// Launch Browser
					Utility.launchBrowser(getTestData("TD_EP_URL"));
					
					// Verify Employer
					businessComponents.EP_verifyEmployer(employer1);
					// Accept policy
					businessComponents.EP_AcceptPolicyAndSubmit();
					//Create dynamic user name
					String userName = createDyanamicUserData();
					// Registration
					businessComponents.EP_SignUpUser(userName, Integer.toString(i), "cbu" + j);

					/*// Center Based Reservation
					// businessComponents.EP_Registration();
					businessComponents.EP_ClickCareProfileLink();

					// Verify Alert Text
					businessComponents.EP_VerifyAlertText();

					// Link Employee Name
					businessComponents.EP_Link_EmployeeName();

					// Update Employee profile
					businessComponents.EP_VerifyPersonalInformation();

					businessComponents.EP_UpdateEmployeeprofile();

					// Click 'Add' link present in 'Care Recipients' section
					COMMON_METHODS.clickElement(getTestObject("OL_41"));

					// Add Care Recipients
					businessComponents.EP_AddCareRecipients();

					// Click on 'Add' link for Authorized Contacts section
					COMMON_METHODS.clickElement(getTestObject("OL_58"));

					// Add Authorized Contacts
					businessComponents.EP_AddAuthorizedContacts();*/
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
					businessComponents.EP_Registration(addCrData3_5Years, "No",employer1);
					// Navigate to My First Reservation
					businessComponents.EP_NavigateToReservation();

					// Test data for Step 1 CareRecipients
					int CareRecipients = 1;
					int HealthStatus[] = { 1 };
					String selectForReason = getTestData("TD_ReasonForCare");

					// Step 1 CareRecipients
					businessComponents.EP_ReservationCareRecipients(
							CareRecipients, HealthStatus, selectForReason,
							"Yes");

					// Step 2 When and where
					//Create  WhenandWhere Reservation
					String[] careDates = {getTestData("TD_Dateofreservation")};
					String actions[] = {"Locations","Continue",null};
					businessComponents.EP_ReservationWhenandWhere(careDates,actions);

					// Step 3 Select Care Options Center based
					businessComponents.EP_ReservationCareOptions();

					// Step 4 Verify Info
					businessComponents.EP_ReservationVerifyInfoNo();

					// Step 5 ReveiwDetails
					businessComponents.EP_ReservationReveiwDetails();
					
					//Verify usage text and days
					verifyUsage(i);
					
					// Logout
					Utility.logout();
					}
				
			}
		}
	}

	/**
	 * Test Case #25375: "CSC BUCA - Care Profile1 - Care Profile Set up with 4 care recipient , 
	 * 2 authorized Contacts, Locations, and Payment method & Request a CB Reservation
	 * 
	 * @param employer
	 * @throws Exception
	 */
	@Test @Parameters("client")
	public void EP_CBReservationprofile1(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Launch Browser with EP Url
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		/*String userName = ReadwritDataFromProps.props
				.getProperty("Client1.cbprofile1.userName");
		String password = getTestData("TD_PWD");

		//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);*/

		// Verify Employer after Entering Employer ID and Password
		businessComponents.EP_verifyEmployer(employer);

		// Accept privacy policy
		businessComponents.EP_AcceptPolicyAndSubmit();

		// Create dynamic user name
		String userName = createDyanamicUserData();

		// Register a New User
		businessComponents.EP_SignUpUser(userName, employer, "cbprofile1");

		// Test data for adding Care Recipients
		String testDataCareRecipients1[] = { getTestData("TD_CR_FirstName"),
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
		
		// Test data for adding Care Recipients
		String testDataCareRecipients2[] = { "CareRece2FN",
				"CareRece2LN", "Son", getTestData("TD_DOB"),
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
		
		// Test data for adding Care Recipients
		String testDataCareRecipients3[] = { "CareRece3FN",
		"CareRece3LN", "Son", getTestData("TD_DOB"),
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
		
		// Test data for adding Care Recipients
		String testDataCareRecipients4[] = { "CareRece4FN",
		"CareRece4LN", "Son", getTestData("TD_DOB"),
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
			
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		//Verify alert text
		businessComponents.EP_VerifyAlertText();
		
		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));
		
		businessComponents.EP_UpdateEmployeeprofile(employer);
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		businessComponents.addCareRecipients(testDataCareRecipients1,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		businessComponents.addCareRecipients(testDataCareRecipients2,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		businessComponents.addCareRecipients(testDataCareRecipients3,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		businessComponents.addCareRecipients(testDataCareRecipients4,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		Thread.sleep(5000);
		
		//Click on 'Add' link for Authorized Contacts section
		COMMON_METHODS.clickElement(getTestObject("OL_58"));
		
		//Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_59"), getTestData("TD_AC_FirstName"));

		//Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_60"), getTestData("TD_AC_LastName"));
		
		//Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_61"));

		//Select Primary Phone
		COMMON_METHODS.listBoxSelect(getTestObject("OL_62"), "Work", "byVisibleText");
		
		//Enter Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_63"), getTestData("TD_Phone"));
		
		//Select  Benefit Access
		COMMON_METHODS.radioButton(getTestObject("OL_64"));
		
		String[][] authorizedContacttesData = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		businessComponents.EP_AuthorizedContactsAccess(4, authorizedContacttesData,
				"No","Yes",employer);
		
		// Navigate to My First Reservation
		businessComponents.EP_NavigateToReservation();

		// Test data for Step 1 CareRecipients
		int CareRecipients = 4;
		int HealthStatus[] = { 1, 1, 1,1 };
		String selectForReason = getTestData("TD_ReasonForCare");

		// Step 1 CareRecipients
		businessComponents.EP_ReservationCareRecipients(CareRecipients,
				HealthStatus, selectForReason, "Yes");

		// Test data for Step 3 When and where
		/*String[] careDates = { getTestData("TD_Caresession1") + ","
				+ getTestData("TD_Caresession2") };*/
		String[] careDates = { getTestData("TD_Dateofreservation1") };
		
		String actions[] = { "Locations", "Continue", null };
		// Step 2 When and where
		businessComponents.EP_ReservationWhenandWhere(careDates, actions);

		// Step 3 Select Care Options Center based
		businessComponents.EP_ReservationCareOptions();

		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();

		// Step 5 ReveiwDetails
		businessComponents.EP_ReservationReveiwDetails();

	}
	
	/**
	 * Test Case #25376: "CSC BUCA - Care Profile1 - Care Profile Set up with 4 care recipient , 
	 * 2 authorized Contacts, Locations, and Payment method & Request a CB Reservation
	 *  
	 * CSC BUCA - Care Profile Set up with 2 child care recipient , 1 Adult care recipient and  
	 * 2 authorized Contacts, Locations, and Payment method with the below conditions: 
	 * Care Recipients: Medical Information set to "YES", HEALTH INFORMATION AND RESTRICTIONS
	 * 
	 * @param employer
	 * @throws Exception
	 */
	@Test @Parameters("client")
	public void EP_CBReservationprofile2(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		
		// Launch Browser with EP Url
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		/*String userName = ReadwritDataFromProps.props
				.getProperty("Client1.cbprofile1.userName");
		String password = getTestData("TD_PWD");

		//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);*/

		// Verify Employer after Entering Employer ID and Password
		businessComponents.EP_verifyEmployer(employer);

		// Accept privacy policy
		businessComponents.EP_AcceptPolicyAndSubmit();

		// Create dynamic user name
		String userName = createDyanamicUserData();

		// Register a New User
		businessComponents.EP_SignUpUser(userName, employer, "cbprofile2");

		// Test data for adding Care Recipients
		String testDataCareRecipients1[] = { getTestData("TD_CR_FirstName"),
				getTestData("TD_CR_LastName"), "Son", getTestData("TDU1_DOB1"),
				getTestObject("OL_61"), getTestObject("OL_47"),
				getTestObject("OL_48"), getTestObject("OL_49"),
				getTestObject("OL_50"), getTestObject("OL_51"),
				getTestObject("OL_52"), getTestData("TD_AddInfo"),
				getTestObject("OL_54"), getTestObject("OL_55"),
				getTestData("TDU1_STATE1"), getTestData("TD_AddInfo"),
				 	 null, null,
				 	 getTestData("TDU1_CENTERLOCATION1"),null,null };
		
		// Test data for adding Care Recipients
		String testDataCareRecipients2[] = { "CareRece2FN",
				"CareRece2LN", "Son", getTestData("TDU1_DOB2"),
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
		
		// Test data for adding Care Recipients
		String testDataCareRecipients3[] = { "CareRece3FN",
		"CareRece3LN", "Son", getTestData("TDU1_DOB3"),
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
		
		// Test data for adding Care Recipients
		String testDataCareRecipients4[] = { "CareRece3FN",
		"CareRece3LN", "Son", getTestData("TDU1_DOB4"),
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
		
		/*// Test data for adding Care Recipients with adults
		String testDataCareRecipients4[] = { "CareRece4FN",
		"CareRece4LN", "Son", getTestData("TD_DOB3"),
		getTestObject("OL_61"), getTestObject("OL_47"),
		getTestObject("OL_48"), getTestObject("OL_49"),
		getTestObject("OL_50"), getTestObject("OL_51"),
		getTestObject("OL_52"), getTestData("TD_AddInfo"),
		null, null,
		null
		 * getXMLData(employer,"TDU1_STATE1"
		 * )
		 , null,
		 	 null, null,
		 	 null
					 * getXMLData(employer
					 * ,"TDU1_STATE1")
					 , null, null };*/
			
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		//Verify alert text
		businessComponents.EP_VerifyAlertText();
		
		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));
		
		businessComponents.EP_UpdateEmployeeprofile(employer);
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		businessComponents.addCareRecipients(testDataCareRecipients1,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		businessComponents.addCareRecipients(testDataCareRecipients2,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		businessComponents.addCareRecipients(testDataCareRecipients3,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));

		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		businessComponents.addCareRecipients(testDataCareRecipients4,"No",employer);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		Thread.sleep(5000);
		
		//Click on 'Add' link for Authorized Contacts section
		COMMON_METHODS.clickElement(getTestObject("OL_58"));
		
		//Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_59"), getTestData("TD_AC_FirstName"));

		//Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_60"), getTestData("TD_AC_LastName"));
		
		//Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_61"));

		//Select Primary Phone
		COMMON_METHODS.listBoxSelect(getTestObject("OL_62"), "Work", "byVisibleText");
		
		//Enter Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_63"), getTestData("TD_Phone"));
		
		//Select  Benefit Access
		COMMON_METHODS.radioButton(getTestObject("OL_64"));
		
		/*// selecting radio option is Yes for Do you want this contact to have
		// Benefit Access?**
		COMMON_METHODS.radioButton(getTestObject("CP_13"));

		Thread.sleep(2000);

		COMMON_METHODS.editAField(getTestObject("REG_01"), "test2@mail.com");*/
		
		String[][] authorizedContacttesData = { { "Yes", "Friend", "", "", "" },
				{ "Yes", "Brother", "", "", "" },{ "Yes", "Friend", "", "", "" },{ "Yes", "Friend", "", "", "" }};
		
		businessComponents.EP_AuthorizedContactsAccess(4, authorizedContacttesData,
				"No","Yes",employer);
		
		Thread.sleep(8000);
		
		//Click on 'Add' link for Authorized Contacts section
		COMMON_METHODS.clickElement(getTestObject("OL_58"));
		
		//Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_59"), "BHAC2FN");

		//Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_60"), "BHAC2LN");
		
		//Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_61"));

		//Select Primary Phone
		COMMON_METHODS.listBoxSelect(getTestObject("OL_62"), "Work", "byVisibleText");
		
		//Enter Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_63"), getTestData("TD_Phone"));
		
		COMMON_METHODS.radioButton(getTestObject("OL_64"));
		
		/*// selecting radio option is Yes for Do you want this contact to have
		// Benefit Access?**
		COMMON_METHODS.radioButton(getTestObject("CP_13"));*/

		/*Thread.sleep(2000);

		COMMON_METHODS.editAField(getTestObject("REG_01"), "test@mail.com");*/
		
		String[][] authorizedContacttesData2 = { { "No", "Friend", "", "", "" },
				{ "No", "Brother", "", "", "" },{ "No", "Friend", "", "", "" },{ "No", "Friend", "", "", "" }};
		
		businessComponents.EP_AuthorizedContactsAccess(4, authorizedContacttesData2,
				"No","Yes",employer);
		
		// Navigate to My First Reservation
		businessComponents.EP_NavigateToReservation();

		// Test data for Step 1 CareRecipients
		int CareRecipients = 4;
		int HealthStatus[] = { 1, 1, 1,1 };
		String selectForReason = getTestData("TD_ReasonForCare");

		this.verifyCareRecipientsImages();
		
		// Step 1 CareRecipients
		businessComponents.EP_ReservationCareRecipients(CareRecipients,
				HealthStatus, selectForReason, "Yes");

		// Test data for Step 3 When and where
		/*String[] careDates = { getTestData("TD_Caresession1") + ","
				+ getTestData("TD_Caresession2") };*/
		String[] careDates = { getTestData("TD_Dateofreservation1") };
		
		String actions[] = { "Locations", "Continue", null };
		// Step 2 When and where
		businessComponents.EP_ReservationWhenandWhere(careDates, actions);

		// Step 3 Select Care Options Center based
		businessComponents.EP_ReservationCareOptions();

		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();

		// Step 5 ReveiwDetails
		businessComponents.EP_ReservationReveiwDetails();

	}

	/*String employer2=null;
	@Test @Parameters("client")
	public void EP_CBReservationprofile2(String employer2) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);

		// Launch Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//businessComponents.LoginEmployeeportal("BHU031714181128", getTestData("TD_PWD"));
		// Sign Up and Registering 10 clients
		for (int i = 1; i <= 1; i++) {

			// Creating users
			for (int j = 1; j < 2; j++) {

				// Verify Employer
				businessComponents.EP_verifyEmployer(employer2);
				// Accept policy
				businessComponents.EP_AcceptPolicyAndSubmit();

				String userName = createDyanamicUserData();
				// Registration
				businessComponents.EP_SignUpUser(userName,Integer.toString(i), "cbu" + j);
				
				// businessComponents.EP_Registration();
				Utility.clickLink(getTestObject("OL_25"));
				
				//Verify alert Text
				businessComponents.EP_VerifyAlertText();

				//Click on Employee link
				Utility.clickLink(getTestObject("OL_26"));

				businessComponents.EP_VerifyPersonalInformation();

				businessComponents.EP_UpdateEmployeeprofile(employer2);

				for (int k = 2; k < 5; k++) {

					if (k == 1) {
						String addCrData0_2Years[] = {
								getTestData("TD_CR_FirstName"),
								getTestData("TD_CR_LastName"), "Daughter",
								getTestData("TDU" + j + "_DOB" + k),
								getTestObject("OL_46"), getTestObject("OL_47"),
								getTestObject("OL_48"), getTestObject("OL_49"),
								getTestObject("OL_50"), getTestObject("OL_51"),
								getTestObject("OL_52"),
								getTestData("TD_AddInfo"),
								getTestObject("OL_54"), getTestObject("OL_55"),
								getTestData("TDU" + j + "_STATE" + k),
								getTestData("TD_AddInfo"), null,
								getTestObject("CR_24"),
								getTestData("TDU" + j + "_CENTERLOCATION" + k),
								null, null };
						// Click 'Add' link present in 'Care Recipients' section
						COMMON_METHODS.clickElement(getTestObject("OL_41"));
						businessComponents.addCareRecipients(addCrData0_2Years);
						// Click 'Add Care Recipients' button
						COMMON_METHODS.clickElement(getTestObject("OL_57"));
					}
					if (k == 2) {
						String addCrData3_5Years[] = {
								getTestData("TD_CR_FirstName"),
								getTestData("TD_CR_LastName"), "Daughter",
								getTestData("TDU" + j + "_DOB" + k),
								getTestObject("OL_46"), getTestObject("OL_47"),
								getTestObject("OL_48"), getTestObject("OL_49"),
								getTestObject("OL_50"), getTestObject("OL_51"),
								getTestObject("OL_52"),
								getTestData("TD_AddInfo"),
								getTestObject("OL_54"), getTestObject("OL_55"),
								getTestData("TDU" + j + "_STATE" + k),
								getTestData("TD_AddInfo"),
								getTestObject("OL_100"), null,
								getTestData("TDU" + j + "_CENTERLOCATION" + k),
								null, null };

						// Click 'Add' link present in 'Care Recipients' section
						COMMON_METHODS.clickElement(getTestObject("OL_41"));
						businessComponents.addCareRecipients(addCrData3_5Years,"No",employer2);
						// Click 'Add Care Recipients' button
						COMMON_METHODS.clickElement(getTestObject("OL_57"));
					}
					if (k == 3) {
						String addCrData6_18Years[] = {
								getTestData("TD_CR_FirstName"),
								getTestData("TD_CR_LastName"), "Daughter",
								getTestData("TDU" + j + "_DOB" + k),
								getTestObject("OL_46"), getTestObject("OL_47"),
								getTestObject("OL_48"), getTestObject("OL_49"),
								getTestObject("OL_50"), getTestObject("OL_51"),
								getTestObject("OL_52"),
								getTestData("TD_AddInfo"),
								getTestObject("OL_54"), getTestObject("OL_55"),
								getTestData("TDU" + j + "_STATE" + k),
								getTestData("TD_AddInfo"), null, null,
								getTestData("TDU" + j + "_CENTERLOCATION" + k),
								null, null };

						// Click 'Add' link present in 'Care Recipients' section
						COMMON_METHODS.clickElement(getTestObject("OL_41"));
						businessComponents
								.addCareRecipients(addCrData6_18Years,"No",employer2);
						// Click 'Add Care Recipients' button
						COMMON_METHODS.clickElement(getTestObject("OL_57"));
					}
					
					  if(k==4){ String
					  addCrDataMoreThan18Years[]={getTestData("TD_CR_FirstName"),
					  getTestData
					  ("TD_CR_LastName"),"Daughter",getTestData("TDU"
					  +j+"_DOB"+k),getTestObject("OL_46"),
					  getTestObject("OL_47"
					  ),getTestObject("OL_48"),getTestObject("OL_49"),
					  getTestObject
					  ("OL_50"),getTestObject("OL_51"),getTestObject
					  ("OL_52"),getTestData("TD_AddInfo"),
					  null,null,null,null,
					  null,null,getTestData
					  ("TDU"+j+"_CENTERLOCATION"+k),"5 ft","56 lbs"};
					  
					  //Click 'Add' link present in 'Care Recipients' section
					  COMMON_METHODS.clickElement(getTestObject("OL_41"));
					  businessComponents.addCareRecipients(addCrDataMoreThan18Years,"No",employer2);
					  //Click 'Add Care Recipients' button
					  COMMON_METHODS.clickElement(getTestObject("OL_57"));
					  
					}
					 

					// Verify the newly added Care Recipient is present under
					// 'Care Recipients' section
					try {
						String sTemp = BH_SetUp_TearDown.driver.findElement(
								By.linkText(getTestData("TD_CR_FirstName")
										+ " " + getTestData("TD_CR_LastName")))
								.getText().trim();
						REPORTER.LogEvent(TestStatus.PASS,
								"Verify Care Recipient created",
								"Care Recipient - " + sTemp + " Created", "");
					} catch (Exception e) {
						REPORTER.catchException(e,
								"Verify Care Recipient created");
					}

				}
		
			String[][] tesData1 = { { "Yes", "Friend", "", "", "" },
				{ "No", "", "", "", "" }, { "No", "", "", "", "" },
				{ "No", "", "", "", "" }};
			String[][] tesData2 = { { "No", "", "", "", "" },
					{ "Yes", "Aunt", "", "", "" }, { "Yes", "Aunt", "", "", "" },
					{ "No", "", "", "", "" }};
			String[][] tesData3 = { { "No", "", "", "", "" },
					{ "No", "", "", "", "" }, { "No", "", "", "", "" },
					{ "Yes", "Neighbor", "", "", "" }};
				for (int l = 0; l < 3; l++) {
					// Click on 'Add' link for Authorized Contacts section
					COMMON_METHODS.clickElement(getTestObject("OL_58"));
					
					businessComponents
							.EP_AuthorizedContactsVerifyHasAccessSection("NO");
					if(l==0)
					businessComponents.EP_AuthorizedContactsAccess(3, tesData1,
					"No","No",employer2);
					if(l==1)
					businessComponents.EP_AuthorizedContactsAccess(3, tesData2,
					"No","No",employer2);
					if(l==2)
					businessComponents.EP_AuthorizedContactsAccess(3, tesData3,
							"No","No",employer2);
				}
				
				String data[]={"Office","60601","103 Fox Road","Flag st","Chicago","Cook",
						"United States",null};
				
				for (int l = 0; l < 2; l++) {
					

					//Click the 'Add' link next to the Locations section 
					COMMON_METHODS.clickElement(getTestObject("OL_124"));
					//Create locations
					businessComponents.EP_AddLocation(data);
				}
			}
			// Navigate to My First Reservation
			businessComponents.EP_NavigateToReservation();

			verifyCareRecipientsImages();

			// Test data for Step 1 CareRecipients
			int CareRecipients = 3;
			int HealthStatus[] = { 1, 1, 1 };
			String selectForReason = getTestData("TD_ReasonForCare");

			// Step 1 CareRecipients
			businessComponents.EP_ReservationCareRecipients(CareRecipients,
					HealthStatus, selectForReason, "Yes");

			// Test data for Step 3 When and where
			String[] careDates = { getTestData("TD_Caresession1") + ","
					+ getTestData("TD_Caresession2") };
			String actions[] = {null,null,null};
			// Step 2 When and where
			businessComponents.EP_ReservationWhenandWhere(careDates, actions);

			// Step 3 Select Care Options Center based
			businessComponents.EP_ReservationCareOptions();

			// Step 4 Verify Info
			businessComponents.EP_ReservationVerifyInfoNo();

			// Step 5 ReveiwDetails
			businessComponents.EP_ReservationReveiwDetails();
			Utility.logout();
		}

	}*/
		
		@Test
		public void EP_CBReservation2() throws Exception {

			String methodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			System.out.println("Inside - " + methodName);

			// Launch Browser
			Utility.launchBrowser(getTestData("TD_EP_URL"));
	
	//Doing Reservation
	for (int i = 1; i <=1; i++) {

		// Read testdata for based on specific clients
		readTestData(getDataTablePath("EP"), "TD_EP" + i);
		for (int j = 1; j < 2; j++) {
			String userName = ReadwritDataFromProps.props
					.getProperty("client" + i + "." + "cbu" + 1
							+ ".userName");
			String password = getTestData("TD_PWD");

			//Login to Emp Portal
			String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
			Utility.loginToBUCA(userName, password,signInArray);
			
			// Navigate to My First Reservation
			businessComponents.EP_NavigateToReservation();

			// Test data for Step 1 CareRecipients
			int CareRecipients = 1;
			int HealthStatus[] = { 1 };
			String selectForReason = getTestData("TD_ReasonForCare");

			// Step 1 CareRecipients
			businessComponents.EP_ReservationCareRecipients(
					CareRecipients, HealthStatus, selectForReason,
					"Yes");

			// Step 2 When and where
			//Create  WhenandWhere Reservation
			String[] careDates = {getTestData("TD_Dateofreservation")};
			String actions[] = {"Locations","Continue",null};
			businessComponents.EP_ReservationWhenandWhere(careDates,actions);

			// Step 3 Select Care Options Center based
			businessComponents.EP_ReservationCareOptions();

			// Step 4 Verify Info
			businessComponents.EP_ReservationVerifyInfoNo();

			// Step 5 ReveiwDetails
			businessComponents.EP_ReservationReveiwDetails();
			
			//Verify usage text and days
			verifyUsage(i);
			
			// Logout
			Utility.logout();
		}	}
	}
	
	private void verifyUsage(int i){
	
		try{
	//Verify Usage Text and Days
	String usageText = COMMON_METHODS
			.getText(getTestObject("RS_28"));
	String usageDays = COMMON_METHODS
			.getText(getTestObject("RS_29"));
	if (i == 1) {
		if (usageText.trim().equalsIgnoreCase("The Back-Up Care Advantage Available Usage"))
			REPORTER.LogEvent(TestStatus.PASS,
					"Usage text is Correct",
					"Usage text is verified", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Expected Value The Back-Up Care Advantage Available Usage",
					"Actual="+usageText, "");

		if (usageDays.contains("1.00 Days"))
			REPORTER.LogEvent(TestStatus.PASS,
					"Usage text is Correct",
					"Usage text is verified", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Expected Value= 1.00 Days",
					"Actual="+usageDays, "");

	}

	if (i == 2) {
		if (usageText.trim().equalsIgnoreCase("The Back-Up Care Advantage Available Usage"))
			REPORTER.LogEvent(TestStatus.PASS,
					"Usage text is Correct",
					"Usage text is verified", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Expected Value= The Back-Up Care Advantage Available Usage",
					"Actual="+usageText, "");

		if (usageDays.contains("4.00 Hours"))
			REPORTER.LogEvent(TestStatus.PASS,
					"Verify Days is Correct",
					"Usage Days is verified", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Expected Value= 4.00 Hours",
					"Actual="+usageDays, "");

	}

	if (i == 5) {
		if (usageText.trim().equalsIgnoreCase("Backup Usage"))
			REPORTER.LogEvent(TestStatus.PASS,
					"Usage text is Correct",
					"Usage text is verified", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Expected Value= Backup Usage",
					"Actual="+usageText, "");

		if (usageDays.contains("1.00 Days"))
			REPORTER.LogEvent(TestStatus.PASS,
					"Usage Days is Correct",
					"Usage Days is verified", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Expected Value= 1.00 Days",
					"Actual="+usageDays, "");

	}

	if (i == 7) {
		if (usageText.trim().equalsIgnoreCase("Back-Up Days"))
			REPORTER.LogEvent(TestStatus.PASS,
					"Usage text is Correct",
					"Usage text is verified", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Expected Value= Back-Up Days",
					"Actual="+usageText, "");

		if (usageDays.contains("1.00 Days"))
			REPORTER.LogEvent(TestStatus.PASS,
					"Usage Days is Correct",
					"Usage Days is verified", "");
		else
			REPORTER.LogEvent(TestStatus.FAIL,
					"Expected Value= 1.00 Days",
					"Actual="+usageDays, "");
	}
		if (i == 8) {
			if (usageText.trim().equalsIgnoreCase("Back Up Care Usage"))
				REPORTER.LogEvent(TestStatus.PASS,
						"Usage text is Correct",
						"Usage text is verified", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL,
						"Expected Value= Back Up Care Usage",
						"Actual="+usageText, "");

			if (usageDays.contains("1.00 Days"))
				REPORTER.LogEvent(TestStatus.PASS,
						"Days is Correct",
						"Usage Days is verified", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL,
						"Expected Value= 1.00 Days",
						"Actual="+usageDays, "");

		}
	}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
	}
	
	private void verifyCareRecipientsImages(){
		try{
			
			
			if (COMMON_METHODS.verifyElementDisplayed("CARE_INF_IMG") )  
				REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recepient Infant image displayed or not", "Care Recepient Infant image displayed successfully", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL, "Verify Care Recepient Infant image displayed or not", "Care Recepient Infant image not displayed ", "");
				
			if (COMMON_METHODS.verifyElementDisplayed("CARE_CHLD_IMG") )  
				REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recepient child-boy image displayed or not", "Care Recepient child-boy image displayed successfully", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL, "Verify Care Recepient child-boy image displayed or not", "Care Recepient child-boy image not displayed", "");
			
			if (COMMON_METHODS.verifyElementDisplayed("CARE_CHLD_IMG2") )  
				REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recepient child-girl image displayed or not", "Care Recepient child-girl image displayed successfully", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL, "Verify Care Recepient child-girl image displayed or not", "Care Recepient child-girl image not displayed", "");
			
			if (COMMON_METHODS.verifyElementDisplayed("CARE_ADL_IMG3") )  
				REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recepient Adult imagee displayed or not", "Care Recepient Adult image displayed successfully", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL, "Verify Care Recepient Adult image displayed or not", "Care Recepient Adult image not displayed ", "");
			
			
			
			/*
			
			
			if(COMMON_METHODS.getText(getTestObject("CR_21")).contains("Default Utilization value for Special Program"))
				REPORTER.LogEvent(TestStatus.PASS, "Default Utilization value for Special Program Displayed", "Default Utilization value for Special Program Displayed", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL,"Excepted= Default Utilization value for Special Program","Actual= "+COMMON_METHODS.getText(getTestObject("CR_21")), "");
			if(COMMON_METHODS.getText(getTestObject("CR_21")).contains("Default Utilization value for Special Program"))
				REPORTER.LogEvent(TestStatus.PASS, "Default Utilization value for Special Program Displayed", "Default Utilization value for Special Program Displayed", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL,"Excepted= Default Utilization value for Special Program","Actual= "+COMMON_METHODS.getText(getTestObject("CR_21")), "");
			if(COMMON_METHODS.getText(getTestObject("CR_21")).contains("Default Utilization value for Special Program"))
				REPORTER.LogEvent(TestStatus.PASS, "Default Utilization value for Special Program Displayed", "Default Utilization value for Special Program Displayed", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL,"Excepted= Default Utilization value for Special Program","Actual= "+COMMON_METHODS.getText(getTestObject("CR_21")), "");
			if(COMMON_METHODS.getText(getTestObject("CR_21")).contains("Default Utilization value for Special Program"))
				REPORTER.LogEvent(TestStatus.PASS, "Default Utilization value for Special Program Displayed", "Default Utilization value for Special Program Displayed", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL,"Excepted= Default Utilization value for Special Program","Actual= "+COMMON_METHODS.getText(getTestObject("CR_21")), "");*/
			
		}
		catch(Exception e){
			REPORTER.catchException(e, "Erron in verfying the image");
		}
	}
}
