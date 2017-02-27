package com.bhs.scripts.employeeportal;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_Reservation_Step4_Test extends INITIALIZE 
{
	EP_BusinessComponents businessComponents=new EP_BusinessComponents();
	String userName;
	String password;
	String employer;
	//Reading Test Objects from Data excel 
	static{
		try{
			readTestObject(getDataTablePath("EP"), "TO_EP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void EP_NavigateToStep4(String num) throws Exception
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
		
	}
	private void loginEP(String clientName) throws Exception {
		
		userName=ReadwritDataFromProps.props.getProperty(clientName +".resstep4.userName");
		password=getTestData("TD_PWD");
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName, password,signInArray);
		
}
	/**
	 * This test script covers functionality of   Reservation Wizard - Step4.5 - ensure user is able to go to previous step via 'Back' button
	 * employee profile information 
	 * TFS ID : 12151
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority=1)
	@Parameters("client")
	public void EP_ReservationSignupUser(String employer) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		this.employer=employer;

		Utility.launchBrowser(getTestData("TD_EP_URL"));

		businessComponents.EP_verifyEmployer(employer);

		businessComponents.EP_AcceptPolicyAndSubmit();

		//Sign Up
		String userName = createDyanamicUserData();
		businessComponents.EP_SignUpUser(userName,employer,"resstep4");		
		
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
				
		businessComponents.EP_Registration(addCrData3_5Years,"No",employer);

		//businessComponents.logout();

		COMMON_METHODS.logToReportAfterPass(methodName);
		

	}
	@Test(priority=2)
	public void EP_ReservationWizardStep45_backButton() throws Exception
	{
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodname);
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("1");
		
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//Click on back button
		COMMON_METHODS.clickElement(getTestObject("RS_01"));
		
		//Verify the Content in Step4
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_93"),getTestData("TD_AddInfo"));
		
		COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_94"));
		
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodname);
		
	}
	
	/**
	 * This test script covers functionality of  Reservation Wizard - Step4 - Ensure user is able to enter in 'Care/Special Instructions'
	 * employee profile information 
	 * TFS ID : 11763
	 * 
	 * @param TC
	 * @throws Exception
	 */
	@Test(priority=3)
	public void EP_ReservationWizardStep4_Instructions() throws Exception
	{
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodname);

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("2");
		
		//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
		COMMON_METHODS.editAField(getTestObject("OL_93"), getTestData("TD_AddInfo"));
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodname);
	}
	
	/**
	 * This test script covers functionality of Reservation Wizard - Step4 - Ensure you can select Any Changes to Medical Info, Allergies or Custody?
	 * employee profile information 
	 * TFS ID : 11765
	 * 
	 * @param TC
	 * @throws Exception
	 */
	
	@Test(priority=4)
	public void EP_ReservationWizardStep4_RadioButton() throws Exception
	{
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodname);
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("3");
		//Select 'No' radio button for 'Any changes to profile details like allergies, etc.?'
		COMMON_METHODS.radioButton(getTestObject("OL_94"));
		
		//Select 'Yes' radio button in Any changes to profile details like allergies, etc.
		COMMON_METHODS.radioButton(getTestObject("ROL_07"));
		
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodname);
	}
	
	
	
	/**
	 * This test script covers functionality of  Reservation Wizard - Step4 - Ensure system lists names of CR's and has an icon for them on upper right
	 * TFS ID : 11767
	 * Reservation Wizard - Step4 - Ensure the selected Care Recipients are listed with Update date and Special Instructions AND
	 * Reservation Wizard - Step4 - Ensure the system displays the Update date for each CR
	 *  TFS ID : 11762 , 11766
	 * @param TC
	 * @throws Exception
	 
	*/
	@Test(priority=6)
	public void EP_ReservationWizardStep4_VerifyCR() throws Exception
	{
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodname);
				
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("4");
				
		// Verify Last updated date is displayed
		String str=COMMON_METHODS.getText(getTestObject("RS_06"));
		
		REPORTER.LogEvent(TestStatus.PASS, "Verify if last updated date is displayed", "Last updated date "+str+" is displayed", "");

		//	Verify Care Recipient
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_04"));
		
		String crname=COMMON_METHODS.getText(getTestObject("RS_04"));
		
		REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recipient name", "Care Recipient name: "+crname+" is diaplayed", "");
		
		//Verify Care Recipient image
		COMMON_METHODS.verifyElementDisplayed(getTestObject("RS_05"));
		
		//Verify Special Instructions text field is displayed
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_93"));
		
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodname);
	}
	
	/**
	 * This test script covers functionality of Reservation Wizard - Step4 - ensure user is able to Continue to next step via 'Continue' button AND
	 * Reservation Wizard - Step4 - ensure user is able to go 'Back' to step 3 via 'Back' button
	 * Step 4: Continue-User will NOT be navigated to step 4.5 if all care-recipients have been reviewed / updated in the last 12 months and the user selects NO to the question on Step 4.
	 * employee profile information 
	 * TFS ID : 12148,12147,22625
	 * 
	 * @param TC
	 * @throws Exception
	 
	*/
	
	@Test(priority=7)
	public void EP_ReservationWizardStep4_VerifyPageNavigation() throws Exception
	{
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodname);
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("5");
		//Care Instructions and verify information
		businessComponents.EP_ReservationVerifyInfoNo();
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("RS_08"),"Review Reservation and Payment Details");
		
		//Click on back button
		COMMON_METHODS.clickElement(getTestObject("RS_01"));

		//Click on back button
		COMMON_METHODS.clickElement(getTestObject("RS_01"));
		
		//Verify Step3 
		COMMON_METHODS.VerifyTextPresent(getTestObject("RS_07"),"Available Care Options");
		
		//Verify Selected Options in Step3
		if("1st Choice".equalsIgnoreCase(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_91"))))
			
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodname);
	}
	
	/**
	 * This test script covers functionality of Reservation Wizard - Step4 - ensure user is brought to a blank form when reaching page
	 * employee profile information 
	 * TFS ID : 12395 
	 * 
	 * @param TC
	 * @throws Exception
	 
	*/
	
	@Test(priority=8)
	public void EP_ReservationWizardStep4_VerifySplInstIsBlank() throws Exception
	{
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodname);
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("6");
		try
		{
			COMMON_METHODS.VerifyTextPresent(getTestObject("OL_93"),"");
			REPORTER.LogEvent(TestStatus.PASS, "Verify Text in Special Instructions'", "Text in Special Instructions is Blank","");
					
		}
		catch(Exception e)
		{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Text in Special Instructions'", "Text in Special Instructions is available","");
		}
		
		
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodname);
	}
	/**
	 * This test script covers functionality of Reservation Wizard - Step4.5 - ensure all fields and validation match that of the Care Recipients page
	 * employee profile information 
	 * TFS ID : 12153
	 * Reservation Wizard - Step4.5 - Ensure that known user data autopopulates fields on form
	 * BUCA - Reservation Wizard - Step4.5 - Verify that all fields in Basic Information section work.
	 * TFS ID : 11773,11782
	 * Reservation Wizard - Step4.5 - ensure the page is loaded correctly (UX doc)
	 * BUCA - Reservation Wizard - Step4 - Ensure that the wizard moves to the verification page for CR's who are flagged in first part of step 4
	 * BUCA - Reservation Wizard - Step4.5 - ensure ONLY Care Recipients with old information and picked 'Yes' on step4 needs to be verified (flagged on previous step)
	 * TFS ID : 12150,12154,11770
	 * TFS ID: 22614 - CSC BUCA - Step 4: Continue-User will be navigated to step 4.5 if all care-recipients have not been reviewed / updated in the last 12 months and the user selects YES to the question on Step 4.
	 * @param TC
	 * @throws Exception
	 
	*/
	@Test(priority=9)
	public void EP_ReservationWizardStep45_VerifyCareProfilePage() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodName);
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		//Navigate To Reservation
		EP_NavigateToStep4("7");
		//Care Instructions and verify information
		businessComponents.EP_ReservationVerifyInfoYes();
		
		String title=BH_SetUp_TearDown.driver.getTitle();
		
		REPORTER.LogEvent(TestStatus.PASS, "Care Recipients page "+title, "Care Recipients page"+title+" -displayed".toUpperCase(),"");
			
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_01"),"Basic Information");
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_02"),"Health Information and Restrictions ");
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_03"),"Medical Information");
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_04"),"Care Forms");
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_05"),"Additional Information");
		
		String fname=COMMON_METHODS.getText(getTestObject("OL_42"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + fname +" is displayed","First Name: "+fname+" is displayed", "");
		
		String lname=COMMON_METHODS.getText(getTestObject("OL_43"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + lname +" is displayed","Last name: "+lname+" is displayed", "");
		
		String rel=COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_44"));
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + rel +" is displayed","Relation: "+rel+" is displayed", "");
		
		String bdate=COMMON_METHODS.getText(getTestObject("OL_45"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + bdate +" is displayed","Birth Date: "+bdate+" is displayed", "");
		
		String gender=COMMON_METHODS.getText(getTestObject("OL_46"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + gender +" is displayed","Gender: "+gender+" is displayed", "");
		
		String st1=COMMON_METHODS.getText(getTestObject("OL_47"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st1 +" is displayed","Food Restrictions: "+st1+" is displayed", "");
		
		String st2=COMMON_METHODS.getText(getTestObject("OL_48"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st2 +" is displayed","Food Allergies: "+st2+" is displayed", "");
		
		String st3=COMMON_METHODS.getText(getTestObject("OL_49"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st3 +" is displayed","Allergies to Medication: "+st3+" is displayed", "");
		
		String st4=	COMMON_METHODS.getText(getTestObject("OL_50"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st4 +" is displayed","Other Allergies: "+st4+" is displayed", "");
		
		String st5=COMMON_METHODS.getText(getTestObject("OL_51"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st5 +" is displayed","Diagnosed Special Needs / Medical Conditions: "+st5+" is displayed", "");
		
		String st6=COMMON_METHODS.getText(getTestObject("OL_52"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st6 +" is displayed","Activity Restrictions: "+st6+" is displayed", "");
		
		String st7=COMMON_METHODS.getText(getTestObject("OL_53"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st7 +" is displayed","Other Helpful Information: "+st7+" is displayed", "");
		
		String st8=COMMON_METHODS.getText(getTestObject("OL_54"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st8 +" is displayed","Legal Guardian: "+st8+" is displayed", "");
		
		String st9=COMMON_METHODS.getText(getTestObject("OL_55"),"value");
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st9 +" is displayed","Custody Issues/ Visitation Orders: "+st9+" is displayed", "");
		
		String st10=COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_56"));
		REPORTER.LogEvent(TestStatus.PASS, "Verify " + st10 +" is displayed","Where do you primarily need care: "+st10+" is displayed", "");
		
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	
	
	
	/**
	 * This test script covers functionality of - Reservation Wizard - Step4.5 - ensure user is able to go to previous step via 'Back' button
	 * employee profile information 
	 * TFS ID : 12151
	 * 
	 * @param TC
	 * @throws Exception
	 
	*/
	@Test(priority=10)
	public void EP_ReservationWizardStep4_VerifyCareProfilePageBackBtn() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodName);
				
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		
		EP_NavigateToStep4("8");
		
		//Care Instructions and verify information
		businessComponents.EP_ReservationVerifyInfoYes();
		
		COMMON_METHODS.clickElement(getTestObject("VerifyCP_10"));
		
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_09"),"Care Instructions & Verify Information" );
		
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	/**
	 * This test script covers functionality of  Reservation Wizard - Step4.5 - ensure each verification checkbox is required to continue on to the next step and has error messages if not
	 * employee profile information 
	 * TFS ID : 12149
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
	
	@Test(priority=11)
	public void EP_ReservationWizardStep45_VerifyCareRecipientsMsg() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodName);
				
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("9");
		
		//Care Instructions and verify information
		businessComponents.EP_ReservationVerifyInfoYes();
		
		//Click on "Continue" 
		COMMON_METHODS.clickElement(getTestObject("RS_17"));
		
		//verify Basic Information must be verified. text
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_06"), "Basic Information must be verified.");
		
		//verify Health Information must be verified text
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_07"), "Health Information must be verified.");
		
		//verify Additional Information must be verified text
		COMMON_METHODS.VerifyTextPresent(getTestObject("VerifyCP_08"), "Additional Information must be verified.");
		
	//	businessComponents.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
		
	}
	/**
	 * This test script covers functionality of 
	 * employee profile information 
	 * TFS ID : 22625
	 * 
	 * @param TC
	 * @throws Exception
	 
	*/
	@Test(priority=12)
	public void EP_ReservationWizardStep4_NavigatetoStep5() throws Exception
	{
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodName);
		
		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login")){
			loginEP(employer);
			
		}
		EP_NavigateToStep4("10");
		
		//Care Instructions and verify information
		businessComponents.EP_ReservationVerifyInfoNo();
		
		//verify step 5 page is displayed
		COMMON_METHODS.VerifyTextPresent(getTestObject("RS_08"), "REVIEW RESERVATION AND PAYMENT DETAILS");
		
		Utility.logout();
		
		COMMON_METHODS.logToReportAfterPass(methodName);
			
		
	}
	
}
