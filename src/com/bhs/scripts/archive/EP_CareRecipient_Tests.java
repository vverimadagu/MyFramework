/*package com.bhs.scripts.archive;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.INITIALIZE.TestStatus;
import com.opera.core.systems.internal.input.KeyEvent;

public class EP_CareRecipient_Tests extends INITIALIZE {

	EP_BusinessComponents businessComponents  = new EP_BusinessComponents();
	

	//Reading Test Objects from Data excel 
		static{
			try{
				readTestObject(getDataTablePath("EP"), "TO_EP");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	
 	 *//**
	 * This test script covers functionality of Verify if  BUCA web application user verifies the employee as eligible. 
	 * 	 * 
	 * Requirement ID : 21284
	 * @param TC
	 * @throws Exception
	 *//*
	@Test(priority=1)
	public void EP_SignUpUser(ITestContext TC) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		businessComponents.EP_LaunchBrowser(TC);
		
		businessComponents.EP_SignUpUser(2);
		
		businessComponents.EP_AcceptPolicyAndSubmit();
		
		businessComponents.EP_ClickCareProfileLink();
		
		businessComponents.EP_Link_EmployeeName();
		
		businessComponents.EP_UpdateEmployeeprofile();
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);

	} 
	
	
	*//**
	 * This test script covers functionality of BUCA Web Application - Care Recipients-  User is able to add New Care Recipients. 
	 * 	  
	 * Requirement ID : 21330
	 * @param TC
	 * @throws Exception ,
	 *//*
	@Test(priority=2)
	public void EP_AddCareRecipients(ITestContext TC) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		businessComponents.EP_AddCareRecipients();
		
		businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	
	*//**
	 * This test script covers functionality of BUCA Web Application - Care Recipients-  System displays error message if user does not enters all required fields 
	 * 	  
	 * Requirement ID : 21330
	 * @param TC
	 * @throws Exception ,
	 *//*
	@Test(priority=3)
	public void EP_AddCareRecipientsErrorMsg(ITestContext TC) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
	
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//An error message should be displayed as "Missing Fields" for appropriate fields and The missing fields should also be highlighted in red.
		businessComponents.verifyElementDisplayed(getTestObject("OL_CR_Error"));
		
		businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	*//**
	 * This test script covers functionality of BUCA Web Application - Care Recipients- .The printable version of the Care Profile page should be displayed in  a read only format. 
	 * 	  
	 * Requirement ID : 21330
	 * @param TC
	 * @throws Exception ,
	 *//*
	@Test(priority=4)
	public void EP_AddCareRecipientsPrintProfile(ITestContext TC) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Verify 'Print Care Profile' link is available on top of the Care Recipient Profile page
		// TODO: print Care Profile link not yet developed  
		
		businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	*//**
	 * This test script covers functionality of BUCA Web Application - Care Recipients- Ensure user can use birth dates in the future 
	 * 	  
	 * Requirement ID : 21330
	 * @param TC
	 * @throws Exception ,dependsOnMethods="EP_SignUpUser",
	 *//*
	@Test(priority=5)
	public void EP_AddCareRecipientsBirthDate(ITestContext TC) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Verify maximum no. of months for future birth date
		// Adding 9 months to current date for DOB
		this.EP_AddCareRecipientsDOB(9);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//An error message should be displayed when selecting above 9 months for the current date 
		businessComponents.verifyElementDisplayed(getTestObject("OL_CR_Error"));
		
		//Verify if the care recipient date of birth date occurs in the future and The age field should be left blank in all parts of the portal 
		//where care recipient age is being displayed.

		//TODO : This functionality is not yet implemented. showing the error message when selecting the future date is less then before 9 months for the DOB.  

		businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	*//**
	 * This test script covers functionality of BUCA Web Application - Care Recipients- user is able to download a care form 
	 * 	  
	 * Requirement ID : 21330
	 * @param TC
	 * @throws Exception ,dependsOnMethods="EP_SignUpUser",
	 *//*
	@Test(priority=6)
	public void EP_AddCareRecipientsDownloadCareForm(ITestContext TC) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client 1
		readTestData(getDataTablePath("EP"), "TD_EP2");
		
		//login employee portal
		businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		this.EP_AddCareRecipientsDOB(-4);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Click a Upload Form
		COMMON_METHODS.clickElement(getTestObject("OL_CR_Upload"));
		
		 //Click a File in Upload
		COMMON_METHODS.clickElement(getTestObject("OL_CR_File")); 
		
		//COMMON_METHODS.editAField(getTestObject("OL_CR_File"),"E:\\sample.txt");
		
		//TODO : The file upload text box is read only.  So we cant give the directly the path of the file. We need ask development team for enable the file upload text box 

		businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	*//**
	 * Adding the Care Recipients in Care profile
	 * 
	 * @throws IOException
	 * @throws Exception
	 *//*
	public void EP_AddCareRecipientsDOB(int noOfMonths) throws IOException, Exception
	{
		
		//Enter First Name
		COMMON_METHODS.editAField(getTestObject("OL_42"), getTestData("TD_CR_FirstName"));
				
		//Enter Last Name
		COMMON_METHODS.editAField(getTestObject("OL_43"), getTestData("TD_CR_LastName"));
		
		//Select Relationship to Client Employee
		COMMON_METHODS.listBoxSelect(getTestObject("OL_44"), "Daughter", "byVisibleText");
		
		//Enter Birth Date
		COMMON_METHODS.editAField(getTestObject("OL_45"), dateAddMonths(noOfMonths));
		
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
				
	}
	
	
	
	*//**
	 *  This method adding months for the current date.
	 * 
	 * @param noOfMonths
	 * @return
	 *//*
	public static String dateAddMonths(int noOfMonths) {
    	
		String date;
		
		Calendar c = Calendar.getInstance();
		
		System.out.println("Current date : " + (c.get(Calendar.MONTH) + 1) +
				      "/" + c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR));
		
		// add months to current date
		 c.add(Calendar.MONTH, noOfMonths);
		
		 date = (c.get(Calendar.MONTH) + 1) +"/" + c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR);
		
		 return date;
	}
    
	*//**
	 * BUCA - Care Recipients - ensure 'Preferred Center Location' field only shows when adding a Care Recipient 
	 * 	  
	 * Requirement ID : 15713
	 * @Autor: Sanjeev Singh
	 * @since 03/04/2014
	 * @throws Exception 
	 * 
	 *//*	
	
	@Test
	public void EP_CareRecipientsPreferredLocation() throws Exception	
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Read testdata for based on client
		readTestData(getDataTablePath("EP"), "TD_EP5");
		
		// Launch Browser
		businessComponents.EP_LaunchBrowser();
		
		//login employee portal	
		//businessComponents.LoginEmployeeportal(ReadwritDataFromProps.props.getProperty("client2.userName"), getTestData("TD_PWD"));
		businessComponents.LoginEmployeeportal(getTestData("TD_Username"), getTestData("TD_EmployerPassword"));
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//String whichClient=null;
        //whichClient=getTestData("TD_EmployerId");
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
		Thread.sleep(4000);
		
		//Select Toilet Trained
		COMMON_METHODS.radioButton(getTestObject("OL_100"));
		
		//Select Salary Range	
		//if(whichClient!=null && (whichClient.equalsIgnoreCase("TCTHREE")|| whichClient.equalsIgnoreCase("TCSIX")))
		COMMON_METHODS.radioButton(getTestObject("CP_14"));
		COMMON_METHODS.listBoxSelect(getTestObject("OL_65"), "Father", "byVisibleText");
		COMMON_METHODS.radioButton(getTestObject("s_parentGardian"));
		
		// Submit MainFormSubmit
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
			
		//Verify the newly added Care Recipient in present under 'Care Recipients' section
		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_CR_FirstName")+" "+getTestData("TD_CR_LastName"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recipient created", "Care Recipient - " + sTemp + " Created" , "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Care Recipient created");
		}
		
		// Click on new care recipient link
		String CR = getTestData("TD_CR_FirstName")+" "+getTestData("TD_CR_LastName");
		COMMON_METHODS.driver.findElement(By.linkText(CR));
		
		// The user can view the information saved	
		String FName=COMMON_METHODS.getTestData("TD_CR_FirstName");
		String LName=COMMON_METHODS.getTestData("TD_CR_LastName");
		if((FName.trim()== getTestData("TD_CR_FirstName").trim()) && (LName.trim()== getTestData("TD_CR_LastName").trim()))
			REPORTER.LogEvent(TestStatus.PASS, "Verifying user can view the information saved", "Viewed Info for: "+FName+LName+" Successfully" , "");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verifying user can view the information saved", "Viewed Info for: "+FName+LName+" Failed" , "");
		
		// Logouts and Log Report
		businessComponents.logout();
		COMMON_METHODS.logToReportAfterPass(methodName);
	}
	
	
}
*/