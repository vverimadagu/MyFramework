package com.bhs.scripts.employeeportal;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;

public class EP_SignUp extends INITIALIZE {

	@Test()
	public void EP_SignUpTest(ITestContext TC) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		// READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
		// SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
		readTestObject(getDataTablePath("EP_old"), "TO_EP");
		readTestData(getDataTablePath("EP_old"), "TD_EP");
		

		//Launch Broswer with BH test Env URL
		COMMON_METHODS.openBrowser(getTestData("TD_URL"));
		
		//Click 'Sign Up' link
		COMMON_METHODS.clickElement(getTestObject("OL_12"));
		
		//Enter Employer ID
		COMMON_METHODS.editAField(getTestObject("OL_13"), getTestData("TD_EmployerId"));
		
		//Enter Employer Password
		COMMON_METHODS.editAField(getTestObject("OL_14"), getTestData("TD_EmployerPassword"));
		
		//Click Verify Employer button
		COMMON_METHODS.clickElement(getTestObject("OL_15"));
		
		//Check 'I have read and agree to the Acceptable Use Policy' check Box
		COMMON_METHODS.checkBox(getTestObject("OL_23"), "check");

		//Click 'Submit'
		COMMON_METHODS.clickElement(getTestObject("OL_24"));
		
		//Verify Client Name Field is Pre populated
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_75"), getTestData("TD_ClientName"), "value");
				
		//Enter value in First Name
		COMMON_METHODS.editAField(getTestObject("OL_17"), getTestData("TD_FirstName"));
		
		//Enter value in Last Name
		COMMON_METHODS.editAField(getTestObject("OL_18"), getTestData("TD_LastName"));
		
		//Enter value in Primary Email
		COMMON_METHODS.editAField(getTestObject("OL_19"), getTestData("TD_UserID"));
		
		//Enter value in Username
		COMMON_METHODS.editAField(getTestObject("OL_20"), getTestData("TD_Email"));
		
		//Enter value in Password
		COMMON_METHODS.editPasswordField(getTestObject("OL_2"), getTestData("TD_PWD"));
		
		//Enter value in Confirm Password
		COMMON_METHODS.editPasswordField(getTestObject("OL_21"), getTestData("TD_PWD"));
		
		//Click Sign Up
		COMMON_METHODS.clickElement(getTestObject("OL_22"));
		
		/*//Check 'I have read and agree to the Acceptable Use Policy' check Box
		COMMON_METHODS.checkBox(getTestObject("OL_23"), "check");
		
		//Click 'Submit'
		COMMON_METHODS.clickElement(getTestObject("OL_24"));*/
		
		//Verify Employee name and Client name Displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_74"), getTestData("TD_EmployeeClientName"),"value");
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Verify alert text under 'Employee Profile' Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_70"), getTestData("TD_Profile"));
		
		//Verify alert text under 'Care Recipients' Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_71"), getTestData("TD_CareRecipient"));
		
		//Verify alert text under 'Authorized Contacts' Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_72"), getTestData("TD_AuthorizedContact"));
		
		//Verify alert text under 'Locations' Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_73"), getTestData("TD_CareLocation"));
			
		//Click on employee name link present under 'Employee Profile' section
		COMMON_METHODS.clickElement(getTestObject("OL_26"));

		//Verify below fields in 'Personal Information' section are already populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_76"), getTestData("TD_FirstName"), "value");
	
		//Verify below fields in 'Personal Information' section are already populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_77"), getTestData("TD_LastName"), "value");
		
		//Verify below fields in 'Personal Information' section are already populated with correct values
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_78"), getTestData("TD_Email"), "value");
		
		//Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_27"));
		
		//Select Primary Phone Type
		COMMON_METHODS.listBoxSelect(getTestObject("OL_28"), "Work", "byVisibleText");

		//Enter Primary Phone Number
		COMMON_METHODS.editAField(getTestObject("OL_29"), getTestData("TD_Phone"));

		//Enter 'Home Address' 
		COMMON_METHODS.editAField(getTestObject("OL_30"), getTestData("TD_Address"));

		//Enter 'City' 
		COMMON_METHODS.editAField(getTestObject("OL_31"), getTestData("TD_City"));

		//Select State/Province
		COMMON_METHODS.listBoxSelect(getTestObject("OL_32"), getTestData("TD_State"), "byVisibleText");

		//Enter 'County' 
		COMMON_METHODS.editAField(getTestObject("OL_33"), getTestData("TD_County"));

		//Select Country
		COMMON_METHODS.listBoxSelect(getTestObject("OL_34"), getTestData("TD_Country"), "byVisibleText");

		//Enter 'Postal Code' 
		COMMON_METHODS.editAField(getTestObject("OL_35"), getTestData("TD_Zip"));

		//Enter 'Work City' 
		COMMON_METHODS.editAField(getTestObject("OL_36"), getTestData("TD_City"));

		//Select Work State
		COMMON_METHODS.listBoxSelect(getTestObject("OL_37"), getTestData("TD_State"), "byVisibleText");

		//Enter 'Job Title' 
		COMMON_METHODS.editAField(getTestObject("OL_38"), getTestData("TD_JobTitle"));

		//Select Salary Range
		COMMON_METHODS.listBoxSelect(getTestObject("OL_39"), getTestData("TD_SalaryRange"), "byValue");

		//Click 'Update Employee Profile' button
		COMMON_METHODS.clickElement(getTestObject("OL_40"));
		
		//Verify Employee Profile and Locations updated
		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_FirstName")+" "+getTestData("TD_LastName"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employee Profile created", "Employee Profile - " + sTemp + " Created" , "");
			
			String sTemp1 = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_Location"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employee Location created", "Employee Location - " + sTemp1 + " Created" , "");
			
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Employee Profile / Location created");
		}
			
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_42"), getTestData("TD_CR_FirstName"));
				
		//Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_43"), getTestData("TD_CR_LastName"));
		
		//Select Relationship to Client Employee
		COMMON_METHODS.listBoxSelect(getTestObject("OL_44"), "Daughter", "byVisibleText");
		
		//Enter Birth Date
		COMMON_METHODS.editAField(getTestObject("OL_45"), getTestData("TD_DOB"));
		
		//Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_46"));
		
		//Select Food Restrictions
		COMMON_METHODS.radioButton(getTestObject("OL_47"));
		
		//Select Food Allergies
		COMMON_METHODS.radioButton(getTestObject("OL_48"));
		
		//Select Allergies to Medication
		COMMON_METHODS.radioButton(getTestObject("OL_49"));
		
		//Select Other Allergies
		COMMON_METHODS.radioButton(getTestObject("OL_50"));
		
		//Select Diagnosed Special Needs / Medical Conditions
		COMMON_METHODS.radioButton(getTestObject("OL_51"));
		
		//Select Activity Restrictions
		COMMON_METHODS.radioButton(getTestObject("OL_52"));
		
		//Enter Additional Information
		COMMON_METHODS.editAField(getTestObject("OL_53"), getTestData("TD_AddInfo"));
		
		//Select Is Client Employee a Parent or Legal Guardian
		COMMON_METHODS.radioButton(getTestObject("OL_54"));
		
		//Select Custody Issues/ Visitation Orders
		COMMON_METHODS.radioButton(getTestObject("OL_55"));
		
		//Select Where do you primarily need care
		COMMON_METHODS.listBoxSelect(getTestObject("OL_56"), getTestData("TD_State"), "byVisibleText");
		
		//Enter Regular Care Arrangements
		COMMON_METHODS.editAField(getTestObject("OL_99"), getTestData("TD_AddInfo"));
				
		//Select Toilet Trained
		COMMON_METHODS.radioButton(getTestObject("OL_100"));
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
			
		//Verify the newly added Care Recipient in present under 'Care Recipients' section
		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_CR_FirstName")+" "+getTestData("TD_CR_LastName"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recipient created", "Care Recipient - " + sTemp + " Created" , "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Care Recipient created");
		}
			
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
		
		//Select Relationship
		COMMON_METHODS.listBoxSelect(getTestObject("OL_65"), getTestData("TD_Relationship"), "byVisibleText");
		
		//Select 'Parent/Guardian' to No
		COMMON_METHODS.radioButton(getTestObject("OL_66"));
		
		//Select Emergency Contact
		COMMON_METHODS.checkBox(getTestObject("OL_67"), "check");
		
		//Select Authorized Pick-Up 
		COMMON_METHODS.checkBox(getTestObject("OL_68"), "check");
		
		//Click 'Add Authorized Contact' button
		COMMON_METHODS.clickElement(getTestObject("OL_69"));
		
		
		
		//Verify the newly added Authorized Contact is present under 'Authorized Contacts' section
		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_AC_FirstName")+" "+getTestData("TD_AC_LastName"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Authorized Contact created", "Authorized Contact - " + sTemp + " Created" , "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Authorized Contact created");
		}
	
		//Verify Care Profile Creates
		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//h1[contains(text(),'Great Job! your care profile is complete')]")).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify care profile created", sTemp, "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify care profile created");
		}
		
	}

	
	
}
