package com.bhs.BusinessComponents;

import java.io.IOException;

import org.apache.log.LogEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_BusinessComponents extends INITIALIZE{
	
	
	
	String whichClient=null;
	String firstName=null;
	String lastName=null;
	String userName=null;
	String userEmail=null;
	String eligibilityClient=null;
	
	public enum epHomePageLinks 

	{Feedback,PrivacyPolicy,TermsofUse,TrademarkNotice,Home}
	
	/**
	 * Login to Client Employee and verifying home page displaying or not
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public enum nominateproviderenum{Name,Address,ZipCode,Contactno,Email,Agent,Providerdata};
	
	/**
	 * Registration for the Employee 
	 * 
	 * @param clientNo - Passing the client number
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_SignUpUser(String uName,String client, String user) throws IOException, Exception { 
		
		//Getting current client 
       // whichClient=getXMLData("Client"+client, "TD_EmployerId");
         whichClient=getXMLData(client, "TD_EmployerId");
         String clientName =getXMLData(client, "TD_ClientName");
         eligibilityClient =getXMLData(client, "TD_Eligibility");
        String fN=null;
        String lN=null;
        
        if(user.equalsIgnoreCase("cbresvreg") || user.equalsIgnoreCase("ccp") || user.equalsIgnoreCase("cpuser")) {
        	
        	fN="BH_FN_"+createDyanamicUserData();
	        lN="BH_LN_"+createDyanamicUserData();
        	
        }else {
        	 fN=getTestData("TD_FirstName");
	         lN=getTestData("TD_LastName");
        	
        }
        
        /*
        if(!user.equalsIgnoreCase("cbresvreg")){
	         fN=getTestData("TD_FirstName");
	         lN=getTestData("TD_LastName");*/
        
        String uE=createDyanamicUserData()+"@gmail.com";
        this.userEmail=uE;
        /* String keyArray[]={"client"+client+"."+user+".firstName","client"+client+"."+user+".lastName", 
                         "client"+client+"."+user+".userName","client"+client+"."+user+".userEmail"};*/ 
        
         String keyArray[]={client+"."+user+".firstName",client+"."+user+".lastName", 
                 client+"."+user+".userName",client+"."+user+".userEmail"}; 
         
         //if (whichClient.equalsIgnoreCase("TCTHREE") || whichClient.equalsIgnoreCase("TCNINE")) { 
         if(eligibilityClient.equalsIgnoreCase("Yes")){
        	 fN = getXMLData(client, "TD_FN");
        	 lN = getXMLData(client, "TD_LN");
         }
         
         String valueArray[]={fN,lN,"BHU"+uName,uE}; 
        
        //Verify Client Name Field is Pre populated 
        //COMMON_METHODS.VerifyTextPresent(getTestObject("OL_75"), getXMLData("Client"+client, "TD_ClientName"));
                        
        //Enter value in First Name 
        COMMON_METHODS.editAField(getTestObject("OL_17"), fN);//getTestData("TD_FirstName")); 
        
        //Enter value in Last Name 
        COMMON_METHODS.editAField(getTestObject("OL_18"), lN);//getTestData("TD_LastName")); 
        
        //Enter value in Username 
        COMMON_METHODS.editAField(getTestObject("OL_19"),"BHU"+uName);// getTestData("TD_Email")); 
        
        
		if (eligibilityClient.equalsIgnoreCase("Yes")/*whichClient.equalsIgnoreCase("TCTHREE")	|| whichClient.equalsIgnoreCase("TCNINE")*/) {
			// Enter value in Employee ID
			COMMON_METHODS.editAField(getTestObject("SIGNUP_EMPLOYER"),getXMLData(client, "TD_EmpID"));
		} /*else if (!(whichClient.equalsIgnoreCase("Dell")
				|| whichClient.equalsIgnoreCase("ShearmanSterling")
				|| whichClient.equalsIgnoreCase("SPE")
				|| whichClient.equalsIgnoreCase("TRowePrice")
				|| whichClient.equalsIgnoreCase("columbia")
				|| whichClient.equalsIgnoreCase("Ingersoll")
				|| whichClient.equalsIgnoreCase("Microsoft")
				|| whichClient.equalsIgnoreCase("sempra")
				|| whichClient.equalsIgnoreCase("homedepot")
				|| whichClient.equalsIgnoreCase("Thomson")
				|| whichClient.equalsIgnoreCase("ADSC")
				|| whichClient.equalsIgnoreCase("AMEX") 
				|| whichClient.equalsIgnoreCase("BakerMcKenzie") 
				|| whichClient.equalsIgnoreCase("JPMC")
				|| whichClient.equalsIgnoreCase("JPMorgan")
				|| whichClient.equalsIgnoreCase("MSKCC")
				|| whichClient.equalsIgnoreCase("Rice")
				|| whichClient.equalsIgnoreCase("BofABUCA")
				|| whichClient.equalsIgnoreCase("CreditSuisse")
				|| whichClient.equalsIgnoreCase("BMGF")
				|| whichClient.equalsIgnoreCase("BCBSNC")
				|| whichClient.equalsIgnoreCase("SidleyAustin")
				|| whichClient.equalsIgnoreCase("Skadden")
				|| whichClient.equalsIgnoreCase("timewarner")
				|| whichClient.equalsIgnoreCase("Google")
				|| whichClient.equalsIgnoreCase("GoogleIE")
				|| whichClient.equalsIgnoreCase("GoldmanSachs")
				|| whichClient.equalsIgnoreCase("ESPN")
				//|| whichClient.equalsIgnoreCase("DRAFTFCB")
				|| whichClient.equalsIgnoreCase("Backup")
				|| whichClient.equalsIgnoreCase("HoganLovells"))){ */ 
			else
				COMMON_METHODS.editAField(getTestObject("OL_20"), uE); // getTestData("TD_UserID"));
		
        
		/*if (whichClient.equalsIgnoreCase("Backup") && clientName.equals("Deutsche Bank UK")){
			// Enter value in Primary Email
			COMMON_METHODS.editAField(getTestObject("OL_20"), uE); // getTestData("TD_UserID"));
		}*/
        //Enter value in Password 
        COMMON_METHODS.editPasswordField(getTestObject("OL_2"), getTestData("TD_PWD")); 
        
        //Enter value in Confirm Password 
        COMMON_METHODS.editPasswordField(getTestObject("OL_21"), getTestData("TD_PWD")); 
        
        //Click Sign Up 
        COMMON_METHODS.clickElement(getTestObject("OL_22")); 
        
        //Set dynamic data to properties file 
        ReadwritDataFromProps.writeDyanamicData(keyArray, valueArray);
        
  } 

	/**
	 * Clicks sign up link and enter Employer deatils
	 * 
	 * @author vverimadugu
	 * @throws IOException
	 * @throws Exception
	 */

	public void EP_verifyEmployer(String clientName) throws IOException,
			Exception {

		// Click 'Sign Up' link
		COMMON_METHODS.clickElement(getTestObject("OL_12"));

		// Enter Employer ID
		// COMMON_METHODS.editAField(getTestObject("OL_13"),
		// getXMLData("Client"+clientName, "TD_EmployerId"));
		COMMON_METHODS.editAField(getTestObject("OL_13"),
				getXMLData(clientName, "TD_EmployerId"));
		// Enter Employer Password
		// COMMON_METHODS.editAField(getTestObject("OL_14"),
		// getXMLData("Client"+clientName, "TD_EmployerPassword"));
		COMMON_METHODS.editAField(getTestObject("OL_14"),
				getXMLData(clientName, "TD_EmployerPassword"));

		// Click Verify Employer button
		COMMON_METHODS.clickElement(getTestObject("OL_15"));

	}
	
	public void EP_AcceptPolicyAndSubmit() throws IOException, Exception {
		
		 //Check 'I have read and agree to the Acceptable Use Policy' check Box 
        COMMON_METHODS.checkBox(getTestObject("OL_23"), "check"); 
        
        //Click 'Submit' 
        COMMON_METHODS.clickElement(getTestObject("OL_24")); 
        
        //Verify Employee name and Client name Displayed 
      //  COMMON_METHODS.VerifyTextPresent(getTestObject("OL_74"), getTestData("TD_EmployeeClientName"));
		
	}
	
	/**
	 * Updating the Care Profile
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_Registration(String testdata[],String isAC,String client) throws IOException, Exception {
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		//Verify alert text
		this.EP_VerifyAlertText();
		
		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));
		
	   //this.EP_VerifyPersonalInformation();
		
		//Update Employee profile
		this.EP_UpdateEmployeeprofile(client);
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		Thread.sleep(2000);
		
		//Add Care recipients 
		addCareRecipients(testdata,"No",client);
		
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
		
		EP_AddAuthorizedContacts();
		
		//Click Logout
		//COMMON_METHODS.clickElement(getTestObject("OL_9"));
	}
	
	
	/**
	 * Verifying the alert text in the Care profile page
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_VerifyAlertText() throws IOException, Exception
	{

		//Verify alert text under 'Employee Profile' Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_70"), getTestData("TD_Profile"));
		
		//Verify alert text under 'Care Recipients' Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_71"), getTestData("TD_CareRecipient"));
		
		//Verify alert text under 'Authorized Contacts' Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_72"), getTestData("TD_AuthorizedContact"));
		
		//Verify alert text under 'Locations' Section
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_73"), getTestData("TD_CareLocation"));
		
	}
	
	
	public void EP_VerifyPersonalInformation() throws IOException, Exception 
	{
		//Verify below fields in 'Personal Information' section are already populated with correct values
		//COMMON_METHODS.VerifyTextPresent(getTestObject("OL_76"), getTestData("TD_FirstName"), "value");
	
		//Verify below fields in 'Personal Information' section are already populated with correct values
		//COMMON_METHODS.VerifyTextPresent(getTestObject("OL_77"), getTestData("TD_LastName"), "value");
		
		//Verify below fields in 'Personal Information' section are already populated with correct values
		//COMMON_METHODS.VerifyTextPresent(getTestObject("OL_78"), getTestData("TD_Email"), "value");
		
	}
	
	
	/**
	 * Updating the Employee Profile in Care profile
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_UpdateEmployeeprofile(String client) throws IOException, Exception
	{

		//Select Gender
		COMMON_METHODS.radioButton(getTestObject("OL_27"));
		
		//Select Primary email Type
		if (!(eligibilityClient.equalsIgnoreCase("Yes")
				&& whichClient.equalsIgnoreCase("Ingersoll")
						|| whichClient.equalsIgnoreCase("JPMC")
						|| whichClient.equalsIgnoreCase("JPMorgan")
						|| whichClient.equalsIgnoreCase("MSKCC")
						|| whichClient.equalsIgnoreCase("Rice")
						|| whichClient.equalsIgnoreCase("sempra")
						|| whichClient.equalsIgnoreCase("ShearmanSterling")
						|| whichClient.equalsIgnoreCase("homedepot") || whichClient
						.equalsIgnoreCase("TRowePrice")|| whichClient.equalsIgnoreCase("Microsoft")
						||whichClient.equalsIgnoreCase("SidleyAustin"))){
			
			COMMON_METHODS.listBoxSelect(getTestObject("UP_Primary_Email"),
					"Work", "byVisibleText");

			// Enter Primary email Number
			COMMON_METHODS.editAField(getTestObject("UP_Primary_Email_Text"),
					this.userEmail);
		}
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
		//COMMON_METHODS.editAField(getTestObject("OL_35"), getXMLData("Client"+client,"TD_Zip"));
		if(!(eligibilityClient.equalsIgnoreCase("Yes")&& whichClient.equalsIgnoreCase("CreditSuisse")))
		COMMON_METHODS.editAField(getTestObject("OL_35"), getXMLData(client,"TD_Zip"));
		
		//Enter 'Work City' 
		COMMON_METHODS.editAField(getTestObject("OL_36"), getTestData("TD_City"));
		
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_SignUp_03"), false);
		
		//Verify Client Name Field is Pre populated 
        //COMMON_METHODS.VerifyTextPresent(getTestObject("OL_SignUp_03"), getXMLData("Client"+client,"TD_ClientName"));

		//Select Work State
		COMMON_METHODS.listBoxSelect(getTestObject("OL_37"), getTestData("TD_State"), "byVisibleText");

		//Enter 'Job Title' 
		COMMON_METHODS.editAField(getTestObject("OL_38"), getTestData("TD_JobTitle"));

		//Select Salary Range
		//if(whichClient!=null && whichClient.equalsIgnoreCase("TCTWO") )
		//COMMON_METHODS.listBoxSelect(getTestObject("OL_39"), getXMLData("Client"+client,"TD_SalaryRange"), "byValue");
		
		//Select Employee Pay code and 8 Week Advantage
		if(whichClient!=null && whichClient.equalsIgnoreCase("TCTHREE") ){
			COMMON_METHODS.listBoxSelect(getTestObject("OL_101"), getXMLData(client,"TD_PAYCODE"), "byValue");
			COMMON_METHODS.listBoxSelect(getTestObject("OL_102"), getXMLData(client, "TD_8WeekAdvantage"), "byValue");
		}
		
		//Enter Employee Pay code and select Work Group
		if(whichClient!=null && whichClient.equalsIgnoreCase("TCFOUR") ){
			COMMON_METHODS.editAField(getTestObject("OL_104"), getXMLData(client, "TD_EMPPAYCODE"));
			COMMON_METHODS.listBoxSelect(getTestObject("OL_105"), getXMLData(client, "TD_CLIENT4WORKGROUP"), "byValue");		
		}
		
		//Enter Employee No and Select whatEverYouAre
		if(whichClient!=null && whichClient.equalsIgnoreCase("TCFIVE") ){
			COMMON_METHODS.editAField(getTestObject("OL_106"), getXMLData(client, "TD_EMPLOYEENO"));
			COMMON_METHODS.listBoxSelect(getTestObject("OL_107"), getXMLData(client, "TD_WHATLEVELAREYOU"), "byValue");
		}
		
		
		//Enter Staff number
		if(whichClient!=null && whichClient.equalsIgnoreCase("BNP")){
			/*COMMON_METHODS.editAField(getTestObject("OL_104"), getXMLData("Client"+client, "TD_EMPPAYCODE"));*/
			COMMON_METHODS.listBoxSelect(getTestObject("BNP_STFNO"), getXMLData(client, "TD_StaffNumber"), "byValue");
			//COMMON_METHODS.editAField(getTestObject("JOBTITLE"), getXMLData(client, "TD_JOBTITLE"));
			
		}
		
		//Lands' End Job Category
		if(whichClient!=null && whichClient.equalsIgnoreCase("lands end")){
			/*COMMON_METHODS.editAField(getTestObject("OL_104"), getXMLData("Client"+client, "TD_EMPPAYCODE"));*/
			COMMON_METHODS.listBoxSelect(getTestObject("LAND_JOBCAT"), getXMLData(client, "TD_LandsEndJobCategory"), "byValue");
			//COMMON_METHODS.editAField(getTestObject("JOBTITLE"), getXMLData(client, "TD_JOBTITLE"));
			
		}
				
		//Employee Number
		if(whichClient!=null && whichClient.equalsIgnoreCase("NYL")){
			COMMON_METHODS.editAField(getTestObject("EMP_NO"), getXMLData(client, "TD_EmpNumber"));
			//COMMON_METHODS.listBoxSelect(getTestObject("EMP_NO"), getXMLData(client, "TD_EmpNumber"), "byValue");
			//COMMON_METHODS.editAField(getTestObject("JOBTITLE"), getXMLData(client, "TD_JOBTITLE"));
		}
		
		//Partner/Non-Partner
		if(whichClient!=null && whichClient.equalsIgnoreCase("Reebok")){
			//COMMON_METHODS.editAField(getTestObject("EMP_NO"), getXMLData(client, "TD_EmpNumber"));
			COMMON_METHODS.listBoxSelect(getTestObject("PARTNER"), getXMLData(client, "TD_PartnerNonPartner"), "byValue");
			//COMMON_METHODS.editAField(getTestObject("JOBTITLE"), getXMLData(client, "TD_JOBTITLE"));
		}
		
		//Business Unit
		if(whichClient!=null && whichClient.equalsIgnoreCase("StateStreetCorp")){
			//COMMON_METHODS.editAField(getTestObject("EMP_NO"), getXMLData(client, "TD_EmpNumber"));
			COMMON_METHODS.listBoxSelect(getTestObject("BUSINESS_UNIT"), getXMLData(client, "TD_BUSINESSUNIT"), "byValue");
			//COMMON_METHODS.editAField(getTestObject("JOBTITLE"), getXMLData(client, "TD_JOBTITLE"));
		}
		
		//Employee #
		if(whichClient!=null && whichClient.equalsIgnoreCase("StateStreetCorp")){
			COMMON_METHODS.editAField(getTestObject("EMP_NO2"), getXMLData(client, "TD_EmpNumber"));
			//COMMON_METHODS.listBoxSelect(getTestObject("EMP_NO"), getXMLData(client, "TD_EmpNumber"), "byValue");
			//COMMON_METHODS.editAField(getTestObject("JOBTITLE"), getXMLData(client, "TD_JOBTITLE"));
		}
		
		//Employment Status 
		if(whichClient!=null && whichClient.equalsIgnoreCase("VZW")){
			//COMMON_METHODS.editAField(getTestObject("EMP_NO2"), getXMLData(client, "TD_EmpNumber"));
			COMMON_METHODS.listBoxSelect(getTestObject("EMP_STATUS"), getXMLData(client, "TD_EmploymentStatus"), "byValue");
			//COMMON_METHODS.editAField(getTestObject("JOBTITLE"), getXMLData(client, "TD_JOBTITLE"));
		}
		
 
		//test data for Dell client
		if(whichClient!=null && whichClient.equalsIgnoreCase("Dell")){
			COMMON_METHODS.editAField(getTestObject("COMP_CODE"), getXMLData(client, "TD_COMPCODE"));
			COMMON_METHODS.editAField(getTestObject("BADGE_NUM"), getXMLData(client, "TD_BADGENUM"));
			COMMON_METHODS.editAField(getTestObject("COST_CENTR"), getXMLData(client, "TD_COSTCENTR"));
		}
		
		//test data for NationalInstitutesofHealth client
		if(whichClient!=null && whichClient.equalsIgnoreCase("NIH")){
			COMMON_METHODS.listBoxSelect(getTestObject("NIH_01"), getXMLData(client, "TD_JobCategory"), "byValue");
			Thread.sleep(1000);
			COMMON_METHODS.listBoxSelect(getTestObject("NIH_04"), getXMLData(client, "TD_Grade"), "byValue");
			Thread.sleep(1000);
			COMMON_METHODS.listBoxSelect(getTestObject("NIH_05"), getXMLData(client, "TD_Gender"), "byValue");
			Thread.sleep(1000);
			COMMON_METHODS.listBoxSelect(getTestObject("NIH_02"), getXMLData(client, "TD_InstituteCenter"), "byValue");
			Thread.sleep(1000);
			COMMON_METHODS.listBoxSelect(getTestObject("NIH_03"), getXMLData(client, "TD_EmploymentCategory"), "byValue");
			Thread.sleep(1000);
		}
		
		
		//test data for GoogleIncIRE client
		if(whichClient!=null && whichClient.equalsIgnoreCase("GoogleIE")){
			//Employee # 
			COMMON_METHODS.editAField(getTestObject("GOG_EMPNUM"), getXMLData(client, "TD_EmployeeNo"));
			//Function
			COMMON_METHODS.listBoxSelect(getTestObject("GOG_FUNCTION"), getXMLData(client, "TD_Function"), "byValue");

		}
		
		//test data for GoldmanSachs client
		if(whichClient!=null && whichClient.equalsIgnoreCase("goldmansachs")){
			//Job Type
			COMMON_METHODS.listBoxSelect(getTestObject("GOLDSAS_JOBTYPE"), getXMLData(client, "TD_JobType"), "byValue");

		}
		
		//Enter What did you have for breakfast
		if(whichClient!=null && whichClient.equalsIgnoreCase("sempra")){
			COMMON_METHODS.listBoxSelect(getTestObject("EP_BFST"), getXMLData(client, "TD_BreakFast"), "byValue");

		}
		
		//test data for Deutsche Bank client
		if(whichClient!=null && whichClient.equalsIgnoreCase("Backup") && getXMLData(client, "TD_ClientName").equals("Deutsche Bank")){
			COMMON_METHODS.editAField(getTestObject("EP_Location"), getXMLData(client, "TD_Location"));
			COMMON_METHODS.editAField(getTestObject("EP_dbID"), getXMLData(client, "TD_dbID"));

		}
		
		//test data for Bright Horizons client
				if(whichClient != null && whichClient.equalsIgnoreCase("BrightHorizonsFamilySolutions") && getXMLData(client, "TD_ClientName").equals("Bright Horizons Family Solutions")){
					COMMON_METHODS.editAField(getTestObject("EP_BHTitle"), "tester");
					COMMON_METHODS.editAField("EP_BHEmployeeNo", "BH12345");
					COMMON_METHODS.editAField("EP_BHCity", "Columbia");
					COMMON_METHODS.listBoxSelect("EP_BHDiscount", "I will not use the BH employee discount during the current calendar year", "byVisibleText");
					COMMON_METHODS.listBoxSelect("EP_BHState", "South Carolina", "ByVisibleText");
					COMMON_METHODS.editAField("EP_BHEmail", "test@brighthorizons.com");
				}
				
				
		
		//Click 'Update Employee Profile' button
		COMMON_METHODS.clickElement(getTestObject("OL_40"));
		Thread.sleep(9000);
		
		//Verify Employee Profile and Locations updated
		/*try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(firstName+" "+lastName)).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employee Profile created", "Employee Profile - " + sTemp + " Created" , "");
			
			String sTemp1 = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_Location"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employee Location created", "Employee Location - " + sTemp1 + " Created" , "");
			
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Employee Profile / Location created");
		}*/
	}
	
	/**
	 * Adding the Authorized Contacts in Care profile
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_AddAuthorizedContacts() throws IOException, Exception
	{
		
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
		Thread.sleep(1000);
		//verifying 2nd Relationship to Recipient displayed for c are recipient 2
		if (COMMON_METHODS.isElementPresent("AuthorizedContactList_0__Permissions_1__RelationshipTypeId","id")) {
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("AUTH_CONT1"), getTestData("TD_Relationship"), "byVisibleText");
		}
		
		//verifying 3rd Relationship to Recipient displayed for c are recipient 2
		if (COMMON_METHODS.isElementPresent("AuthorizedContactList_0__Permissions_2__RelationshipTypeId","id")) {
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("AUTH_CONT2"), getTestData("TD_Relationship"), "byVisibleText");
		}
		
		//verifying 3rd Relationship to Recipient displayed for c are recipient 3
		if (COMMON_METHODS.isElementPresent("AuthorizedContactList_0__Permissions_3__RelationshipTypeId","id")) {
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("AUTH_CONT3"), getTestData("TD_Relationship"), "byVisibleText");
		}
		
		Thread.sleep(3000);
		
		//Click 'Add Authorized Contact' button
		COMMON_METHODS.clickElement(getTestObject("OL_69"));
		
		Thread.sleep(2000);
		
		
		//Verify the newly added Authorized Contact is present under 'Authorized Contacts' section
		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_AC_FirstName")+" "+getTestData("TD_AC_LastName"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Authorized Contact created", "Authorized Contact - " + sTemp + " Created" , "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Authorized Contact created");
		}
	
		//Verify Care Profile Creates
		/*try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//h1[contains(text(),'Great Job! your care profile is complete')]")).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify care profile created", sTemp, "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify care profile created");
		}*/
	}
	
	/**
	 * Adding the Authorized Contacts in Care profile
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_AuthorizedContactsVerifyHasAccessSection(String option) throws IOException, Exception
	{
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
		if(option.toUpperCase().equals("NO"))
		{
			/*COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_64"));*/
			
			COMMON_METHODS.radioButton(getTestObject("OL_64"));
			
			COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("CP_14"));
			
			try
			{
				
				COMMON_METHODS.radioButton(getTestObject("CP_21"));
				
				REPORTER.LogEvent(TestStatus.PASS, "Verify if No option can be clicked", "able to click Has Access No option", "");
			}
			catch(Exception e)
			{
				REPORTER.LogEvent(TestStatus.FAIL, "Verify if No option can be clicked", "UnAble to click Has Access No option", "");
			}
		}
		else
		{
			COMMON_METHODS.radioButton(getTestObject("CP_13"));
		
		try
		{
			String disabledAttribute = BH_SetUp_TearDown.driver.findElement(By.id("PermissionsDenyYes_0_0")).getAttribute("disabled");
			System.out.println(disabledAttribute);
			if (disabledAttribute.equalsIgnoreCase("true"))
				REPORTER.LogEvent(TestStatus.PASS, "Verify if No option can be clicked", "unable to Click No option", "");
			else
				REPORTER.LogEvent(TestStatus.FAIL, "Verify if No option can be clicked", "Unable to click Has Access No option", "");
		}
		catch(Exception e)
		{
			REPORTER.catchException(e, "Verify Authorized Contacts Has Access Section");
		}
	
		}
	}
	
	
	/**
	 * Adding the Locations in Care profile
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 */
	public void EP_AddLocation(String data[]) throws IOException, Exception {
		
		// Enter the Location Name
		if(data[0]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_125"),data[0] );

		// Enter the Postal Code
		if(data[1]!=null){
			COMMON_METHODS.editAField(getTestObject("OL_126"), data[1]);
			Thread.sleep(4000);
		}
		// Enter the Address1
		if(data[2]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_127"),data[2] );
		
		// Enter the Address2
		if(data[3]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_128"),data[3]);
		
		// Enter the City
		if(data[4]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_129"),data[4] );
		
		// Enter the County Text
		if(data[5]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_130"), data[5]);
		
		// Select the Country drop down
		if(data[6]!=null)
			COMMON_METHODS.listBoxSelect(getTestObject("OL_131"), data[6], "byVisibleText");
		
		// Select the State/Province*
		if(data[7]!=null)
			COMMON_METHODS.listBoxSelect(getTestObject("OL_133"), data[7], "byVisibleText");
		
		//Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_132"));
		
		//Verify Employee Profile and Locations updated
		try {
			
			String sTemp1 = BH_SetUp_TearDown.driver.findElement(By.linkText(data[0])).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employee Location created", "Employee Location - " + sTemp1 + " Created" , "");
			
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Employee Location created");
		}

	}
	
	public void EP_CareprofileUpdateLocation() throws IOException, Exception
	{
		
		//Type a different 'address' in 'Address' text box
		COMMON_METHODS.editAField(getTestObject("CP_04"), "7575 E Arkansas Ave");

		//Change 'zip code' in 'Postal Code' text box
		COMMON_METHODS.editAField(getTestObject("CP_05"), "80231");

		//Select Country in combo box
		COMMON_METHODS.listBoxSelect(getTestObject("CP_08"), "United States", "byVisibleText");
		
		Thread.sleep(2000);
		
		String city = COMMON_METHODS.getText(getTestObject("CP_06"), "value");
		String state = COMMON_METHODS.getText(getTestObject("CP_07"), "value");
		
		//Verify city and state is populated after entering the zip code
		if (city.equals("Denver"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify city is populated or not corresponding zipcode", "City populated as : " + city , "");	
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify city is populated or not corresponding zipcode", "City not populated : " + city , "");
			
		//Verify city and state is populated after entering the zip code
		if (state.equals("Colorado"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify state is populated or not corresponding zipcode", "state populated as : " + state , "");	
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify state is populated or not corresponding zipcode", "state not populated : " + state , "");
				
		//Click 'Update Location' button
		COMMON_METHODS.clickElement(getTestObject("CP_09"));
		
	}
	
	/**
	 * Creating a New Reservation
	 * 
	 * @throws IOException	
	 * @throws Exception
	 * 
	 */
	public void EP_Reservation() throws IOException, Exception {
		
		//Click 'Care Profile' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_25"));
		
		//Click "Make My First Reservation"
		COMMON_METHODS.clickElement(getTestObject("OL_79"));
		
		//Create Care Recipients Reservation
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
		"Yes");
		
		//Create  WhenandWhere Reservation
		String[] careDates = {getTestData("TD_Dateofreservation")};
		String actions[] = {"Locations","Continue",null};
		EP_ReservationWhenandWhere(careDates,actions);
		
		//Create Care Options Reservation
		this.EP_ReservationCareOptions();
		
		//home care
		//this.EP_ResverationInHomeCareOptions();
		
		this.EP_ReservationVerifyInfoNo();
		this.EP_ReservationReveiwDetails();
		
	}
	
	/**
	 * Creating a New Reservation
	 * 
	 * @throws IOException	
	 * @throws Exception
	 * 
	 */
	public void EP_IHReservation() throws IOException, Exception {
		
		// Test data for Step 1 CareRecipients
		//int CareRecipients = 1;
		int HealthStatus[] = { 1, 2, 1, 1 };
		String selectForReason = getTestData("TD_ReasonForCare");

		//String aCareReciText = COMMON_METHODS.getText(getTestObject("RES_02"));
		//int careCount = Integer.parseInt(aCareReciText.substring(17, 18));
		// Navigate to Reservation Page
		this.EP_NavigateToReservation();

		// Select CareRecipients and Health status in Step1 and click on
		// continue button
		this.EP_ReservationCareRecipients( 1/* careCount*/,HealthStatus, selectForReason, "Yes");

		// Step 2 When and where
		COMMON_METHODS.editAField(getTestObject("OL_85"),
				getTestData("TD_Dateofreservation9"));

		

		// Select Location for IH resrvation
		this.ResWhenandWhereLocationInhome();

		// Filling and checking whether the continue button is Enabled and going
		// to the next page.
		this.EP_ReservationInHomeCareOptions("CareOptions");

		// Step 4 Verify Info
		this.EP_ReservationVerifyInfoNo();
		
		// Step 5 Verify Review Details
		this.EP_ReservationReveiwDetailsInHome();

	}
	 
	/**
	 * Creating a New Reservation
	 * 
	 * @throws IOException	
	 * @throws Exception
	 * 
	 */
	public void EP_NavigateToReservation() throws IOException, Exception {
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		Thread.sleep(12000);
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		Thread.sleep(5000);
		
	}
	
	/**
	 * When and Where for Reservation
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_ReservationWhenandWhere(String[] careDates,String actions[]) throws IOException, Exception {
		
			try {
				boolean isMemberShipClient=isMemberShipClient(whichClient);
				
				if(isMemberShipClient)
					actions[0]=null;
				
				Thread.sleep(1000);
				
				int careDateCount=careDates.length;
				
				String[] sDateCare = COMMON_METHODS.splitTestObject(getTestObject("WW_04"));
				String[] sStartTime = COMMON_METHODS.splitTestObject(getTestObject("WW_05"));
				String[] sEndTime = COMMON_METHODS.splitTestObject(getTestObject("WW_06"));
				
				for(int i=0;i<careDateCount-1;i++)
					COMMON_METHODS.clickElement(getTestObject("WW_01"));
				
				Thread.sleep(2000);
				
				for(int i=0;i<careDateCount;i++){
					
					String dateCare;
					String startTime;
					String endTime;
					String tempDateCare=sDateCare[3]+i+"__ViewOnlyTextDateFrom";
					String tempStartTime=sStartTime[3]+i+"].ViewOnlyTimeFrom";
					String tempEndTime=sEndTime[3]+i+"].ViewOnlyTimeTo";
					
					dateCare=sDateCare[0]+","+sDateCare[1]+","+sDateCare[2]+","+tempDateCare;
					startTime=sStartTime[0]+","+sStartTime[1]+","+sStartTime[2]+","+tempStartTime;
					endTime=sEndTime[0]+","+sEndTime[1]+","+sEndTime[2]+","+tempEndTime;
				
					Thread.sleep(3000);
				
				//Select Date of reservation
				COMMON_METHODS.editAField(dateCare, careDates[i]);
				Thread.sleep(5000);
				//COMMON_METHODS.editAField(startTime, "09:00 am");
				//COMMON_METHODS.editAField(endTime, "02:00 pm");
				//Select 'Start Time'
				COMMON_METHODS.clickElement(startTime);//getTestObject("OL_86"));
				
				BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeFrom']/dl/dd[2]/div[2]/table/tbody/tr/td[4]")).click();
				BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
				
				//Select 'End Time'
				COMMON_METHODS.clickElement(endTime);//getTestObject("OL_87"));
				BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeTo']/dl/dd[2]/div[2]/table/tbody/tr/td[5]")).click();
				BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
				//if(careDateCount>1){
					//Click on Add Another Date
				//	COMMON_METHODS.clickElement(getTestObject("WW_01"));
					//COMMON_METHODS.clickElement(getTestObject("WW_01"));
					//Thread.sleep(5000);
				//}
				tempDateCare=null;
				tempStartTime=null;
				tempEndTime=null;
			}
				if(actions[0]!=null){
					//Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
					COMMON_METHODS.radioButton(getTestObject("ROL_03"));
					
					//Select Distance from drop down
					COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
					
					//Select any Location from 'Location' drop down
					COMMON_METHODS.listBoxSelect(getTestObject("OL_89"), getTestData("TD_Location"), "byVisibleText");
					}
				
				if(actions[2]!=null){
					//Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
					COMMON_METHODS.radioButton(getTestObject("ROL_19"));
					
					//Select Distance from drop down
					COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
					
					//Select any Location from 'Location' drop down
					COMMON_METHODS.listBoxSelect(getTestObject("ROL_20"), getTestData("TD_Location"), "byVisibleText");
					
					//Select any Location from 'Location' drop down
					COMMON_METHODS.listBoxSelect(getTestObject("ROL_21"), getTestData("TD_Location"), "byVisibleText");
					}
				
				
					//Click 'Continue'
				if(actions[1]!=null){
					COMMON_METHODS.clickElement(getTestObject("OL_84"));
					Thread.sleep(18000);
				}
			} catch (Exception e) {
				REPORTER.catchException(e, "Enter Start Time / End Time");
			}
				
	}
	
	/**
	 * Care Options for Reservation
	 * @throws IOException
	 * @throws Exception
	 */
	
	public String EP_ReservationCareOptions() throws IOException, Exception {
		
		//Click 'Center - based Care' button at the top of 'Available Care Options' section
		if(isMemberShipClient(whichClient) == false){
			COMMON_METHODS.clickElement(getTestObject("OL_90"));
		}
		
		if(whichClient.equalsIgnoreCase("NYL") || whichClient.equalsIgnoreCase("DraftFCB") || whichClient.equalsIgnoreCase("LandsEnd")){
			String value = "New York,NY";
			COMMON_METHODS.listBoxSelect(getTestObject("OL_142"), value, "byVisibleText");
		}
		
		if(whichClient.equalsIgnoreCase("Reebok")){
			String value = "Washington,DC";
			COMMON_METHODS.listBoxSelect(getTestObject("OL_142"), value, "byVisibleText");
		}
		
		if(whichClient.equalsIgnoreCase("BCG") || whichClient.equalsIgnoreCase("Chevron") || whichClient.equalsIgnoreCase("Target") || whichClient.equalsIgnoreCase("JPL")){
			String value = "Chicago,IL";
			COMMON_METHODS.listBoxSelect(getTestObject("OL_142"), value, "byVisibleText");
		}
		
		if(whichClient.equalsIgnoreCase("Backup")){
			String value = "London,";
			COMMON_METHODS.listBoxSelect(getTestObject("OL_142"), value, "byVisibleText");
		}

		if(whichClient.equalsIgnoreCase("AllenOveryUK")){
			String value = "London,LO";
			COMMON_METHODS.listBoxSelect(getTestObject("OL_142"), value, "byVisibleText");
		}
		
		
		Thread.sleep(20000);
		
		String location = "";
		
		try {
			
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.xpath("//ul[@class='lrBox']/li/h5")).getText().trim();
			
			String totalCenters = sTemp.substring(13, 14);

			int noCenters =  Integer.parseInt(totalCenters);
			
			long totpages = Math.round(Math.ceil(noCenters / (double) 5)) ;
			
			boolean bStatus=false;
			
			for (int i = 0 ; i < totpages; i++ ) {
				
				String sTemp2 = BH_SetUp_TearDown.driver.findElement(By.xpath("//ul[@class='lrBox']/li/h5")).getText().trim();
				
				String noOfRowsPage = sTemp.substring(8, 9);
				
				int noRows =  Integer.parseInt(noOfRowsPage);
				
				int j =1;
				
				 while (j <= noRows) {
					 
						
						boolean isElementfound = this.isElementPresent(By.xpath("//div[@class='providerMod'][" + j +"]/div[@class='providerModFtr']/ul/form/li/input"));
						
						if (!isElementfound) {
							
							/*//Select '1st Choice' from 'Set My Preference' drop down for any BH Center in the list
							COMMON_METHODS.listBoxSelect(getTestObject("OL_91"), getTestData("TD_FirstChoice"), "byVisibleText");*/
							
							Select dropdown = new Select(BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='providerMod'][" + j +"]/div[@class='providerModFtr']/ul/li/select")));
							dropdown.selectByVisibleText(getTestData("TD_FirstChoice"));
							
							location= BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@class='providerMod']["+ j +"]/div/ul/li[2]")).getText().trim();
							
							bStatus=true;
							break; 
						}
						
				   j = j+1;

				 }
				 
				 if (bStatus)
					 break;
					 
				 if (COMMON_METHODS.isElementPresent("li[@class='next']/a", "xpath")) 
					  BH_SetUp_TearDown.driver.findElement(By.xpath("li[@class='next']/a")).click();
				
			}
		}catch(Exception e) {
			e.getMessage();
		}
		
		
		/*//Select '1st Choice' from 'Set My Preference' drop down for any BH Center in the list
		COMMON_METHODS.listBoxSelect(getTestObject("OL_91"), getTestData("TD_FirstChoice"), "byVisibleText");*/
		
		/*String location=COMMON_METHODS.getText(getTestObject("CB_FC"));*/
		
		/*//Select 'Acceptable' from 'Set My Preference' drop down for all other Center in the list
		boolean isPresent=COMMON_METHODS.isElementPresent("ProviderSearchModel.MatchingProvidersList[1].Preference","name");
		if(isPresent){
			COMMON_METHODS.listBoxSelect(getTestObject("OL_92"), getTestData("TD_Acceptable"), "byVisibleText");
			Thread.sleep(2000);
		}*/
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		return location;
	}
	
	private boolean isElementPresent(By by) {
		try {       
			BH_SetUp_TearDown.driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		        return false;
		    }
		    catch (Exception e)
		    {       
		        return false;
		    }

	}
	
public void EP_ReservationCareOptions(String setpref) throws IOException, Exception {
		
		//Click 'Center - based Care' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_90"));
		
		//String viewText = COMMON_METHODS.getText(getTestObject("CB_02"));
		//String viewTextArray[]=viewText.split(" ");
		String[] sSetPref= COMMON_METHODS.splitTestObject(getTestObject("CB_01"));
		
		//Select '1st Choice' from 'Set My Preference' drop down for any BH Center in the list
		//COMMON_METHODS.listBoxSelect(getTestObject("OL_91"), getTestData("TD_FirstChoice"), "byVisibleText");
		COMMON_METHODS.listBoxSelect(getTestObject("OL_91"), setpref, "byVisibleText");
		
		//Select 'Acceptable' from 'Set My Preference' drop down for all other Center in the list
		//COMMON_METHODS.listBoxSelect(getTestObject("OL_92"), getTestData("TD_Acceptable"), "byVisibleText");
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
	}
	/**
	 * Care Options In Home, for Reservation
	 * @throws IOException
	 * @throws Exception
	 */
	
	/*public void EP_ReservationInHomeCareOptions  () throws IOException, Exception {
		
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(9000);
		
		//Select 'Residential' radio button for Location type
		COMMON_METHODS.radioButton(getTestObject("OL_109"));
		
		//Check 'I am electing to use In-Home Care and DO NOT wish to use a Care Center' check box present under 'In-Home Care Election' section
		COMMON_METHODS.checkBox(getTestObject("OL_110"), "check");
		
		//Select 'No' radio button for 'Does anyone smoke in the care location?'
		COMMON_METHODS.radioButton(getTestObject("OL_111"));
		
		//Select 'No' radio button for 'Are there any pets at the care location?'
		COMMON_METHODS.radioButton(getTestObject("OL_112"));
		
		//Select 'No' radio button for 'Is anyone other than the care recipient(s) expected to be at the care location?'
		COMMON_METHODS.radioButton(getTestObject("OL_113"));
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
	}*/
	
	
	/*
	 * Care Options In Home, for Reservation
	 * @throws IOException
	 * @throws Exception
	 */

	public void EP_ReservationInHomeCareOptions(String action) throws IOException, Exception {

		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
			COMMON_METHODS.clickElement(getTestObject("OL_108"));
			Thread.sleep(9000);
			if(action.equalsIgnoreCase("CareOptions")){
			//Select 'Residential' radio button for Location type
			COMMON_METHODS.radioButton(getTestObject("OL_109"));

			//Check 'I am electing to use In-Home Care and DO NOT wish to use a Care Center' check box present under 'In-Home Care Election' section
			COMMON_METHODS.checkBox(getTestObject("OL_110"), "check");

			//Select 'No' radio button for 'Does anyone smoke in the care location?'
			COMMON_METHODS.radioButton(getTestObject("OL_111"));

			//Select 'No' radio button for 'Are there any pets at the care location?'
			COMMON_METHODS.radioButton(getTestObject("OL_112"));

			//Select 'No' radio button for 'Is anyone other than the care recipient(s) expected to be at the care location?'
			COMMON_METHODS.radioButton(getTestObject("OL_113"));
		}
		if(action.equalsIgnoreCase("HotelCareOptions")){
			//Select 'Hotel' radio button for Location type
			COMMON_METHODS.radioButton(getTestObject("RESV_TELL_06"));

			//Check 'I am electing to use In-Home Care and DO NOT wish to use a Care Center' check box present under 'In-Home Care Election' section
			COMMON_METHODS.checkBox(getTestObject("OL_110"), "check");

			//Verify Hotel Preferences section displays
			//COMMON_METHODS.VerifyTextPresent(getTestObject("Step3_HotelPreferences"), "Hotel Preferences");

			// Enter the Hotel name in the "Name of the Hotel you need care at?*" field
			COMMON_METHODS.editAField(getTestObject("Step3_HotelName"), getTestData("TD_HotelName"));

			//Select No radio button for "Will toys, games and activities be provided to occupy the care recipient?*"
			COMMON_METHODS.radioButton(getTestObject("Step3InHotelCare_ToysProvided"));

			//Select No radio button for "Is the care recipient allowed to order games and/or movies on the television?*"
			COMMON_METHODS.radioButton(getTestObject("Step3InHotelCare_MoviesAllowed"));

			//Select No radio button for "Is the care recipient allowed to use the internet?*"
			COMMON_METHODS.radioButton(getTestObject("Step3InHotelCare_InternetAllowed"));

			//Select No radio button for "Is the care recipient allowed to order food and/or snacks and beverages from room service or in-room mini bar?*"
			COMMON_METHODS.radioButton(getTestObject("Step3InHotelCare_RoomServiceAllowed"));

			//Select check box for "Do you understand that all parking fees for the caregiver are my responsibility?*"
			//COMMON_METHODS.clickElement(getTestObject("Step3InHotelCare_ParkingFee"));
			COMMON_METHODS.checkBox(getTestObject("Step3InHotelCare_ParkingFee"), "check");

			//Verify Provider Preferences section displays
			COMMON_METHODS.VerifyTextPresent(getTestObject("Step3_ProviderPreferences"), "Provider Preferences");

			//Verify Provider Preferences section displays
			COMMON_METHODS.VerifyTextPresent(getTestObject("Step3_TellUsAboutCareEnv"), "Tell Us About The Care Environment");

			//Verify Hotel Care Policy section displays
			COMMON_METHODS.VerifyTextPresent(getTestObject("Step3_HotelCarePolicy"), "Hotel Care Policy");
			
			//Select 'No' radio button for 'Does anyone smoke in the care location?'
			COMMON_METHODS.radioButton(getTestObject("HotelSmokeFalse"));

			//Select 'No' radio button for 'Are there any pets at the care location?'
			COMMON_METHODS.radioButton(getTestObject("HotelHasPetsFalse"));

			//Select 'No' radio button for 'Is anyone other than the care recipient(s) expected to be at the care location?'
			COMMON_METHODS.radioButton(getTestObject("OL_113"));
			
			//Check the HotelPolicy checkbox
			COMMON_METHODS.checkBox(getTestObject("HotelPolicy"), "check");
			
		}
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
	}
		
	
	
	
	/**
	 * Verify Info for Reservation
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_ReservationVerifyInfoNo() throws IOException, Exception {
		
		//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
		COMMON_METHODS.editAField(getTestObject("OL_93"), getTestData("TD_AddInfo"));
		
		//Select 'No' radio button for 'Any changes to profile details like allergies, etc.?'
		COMMON_METHODS.radioButton(getTestObject("OL_94"));
		
		
		if(COMMON_METHODS.isElementPresent("CareRecipients_1__SpecialInstructions", "id")){
			//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
			COMMON_METHODS.editAField(getTestObject("IHR_01"), getTestData("TD_AddInfo"));
			
		}
			
		if(COMMON_METHODS.isElementPresent("CareRecipients_1__ChangesInInfo", "id")){
			//Select 'No' radio button for 'Any changes to profile details like allergies, etc.?'
			COMMON_METHODS.radioButton(getTestObject("IHR_02"));
			
		}
		if(COMMON_METHODS.isElementPresent("CareRecipients_2__SpecialInstructions", "id")){
			//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
			COMMON_METHODS.editAField(getTestObject("IHR_03"), getTestData("TD_AddInfo"));
			
		}
		if(COMMON_METHODS.isElementPresent("CareRecipients_2__ChangesInInfo", "id")){
			//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
			COMMON_METHODS.radioButton(getTestObject("IHR_04"));
			
		}	
		
		if(COMMON_METHODS.isElementPresent("CareRecipients_3__SpecialInstructions", "id")){
			//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
			COMMON_METHODS.editAField(getTestObject("IHR_05"), getTestData("TD_AddInfo"));
			
		}
		if(COMMON_METHODS.isElementPresent("CareRecipients_3__ChangesInInfo", "id")){
			//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
			COMMON_METHODS.radioButton(getTestObject("IHR_06"));
			
		}
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
	}
	
	
	/**
	 * Verify Info for Reservation
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_ReservationVerifyInfoYes() throws IOException, Exception {
		
		//Enter any value in 'Care / Special Instructions' field for all the Care Recipients
		COMMON_METHODS.editAField(getTestObject("OL_93"), getTestData("TD_AddInfo"));
		
		//Select 'Yes' radio button in Any changes to profile details like allergies, etc.
		COMMON_METHODS.radioButton(getTestObject("ROL_07"));
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
	}
	
		
	/**
	 * Review Details for Reservation
	 * @throws IOException
	 * @throws Exception
	 */
	public String EP_ReservationReveiwDetails() throws IOException, Exception {
		
		//Add payment method in 5th step
		this.addPaymentMethod("ManagePaymentMethod");
		
		//Check 'I have read and agree to the Payment Policy' check box present under 'Payment Terms'
		COMMON_METHODS.checkBox(getTestObject("OL_95"), "check");
		
		//Check 'Accept the Cancellation Policy for this reservation' checkbox present under 'Cancellation Policy'
		COMMON_METHODS.radioButton(getTestObject("OL_96"));
		
		
		
		//Click 'Request Reservation'
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		//System.out.println("Reservation # - " + BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1 > span")).getText().trim());
		//System.out.println("#################################" + BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString());

		//Verify the Reservation Number and Status present at the top
		String resNum="";
		try {
			 resNum = BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1 > span")).getText().trim();
			String ResStatus = BH_SetUp_TearDown.driver.findElement(By.cssSelector("span.hdrStatus")).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Confirmed", "Reservation # and Reservation Status : " + resNum +" "+ ResStatus , "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Reservation Confirmed");
		}
		
		return resNum;
	
	}

	/**
	 * Verify number of providers
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_ProviderVerifyNoOfProviders() throws IOException,Exception
	{
		try
		{
		//String providercount=getTestObject("PT_05");
		//COMMON_METHODS.waitForObject(getTestObject("PT_05"), 3000, "appear");
		String count=BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='divCenrterBasedCare']/h2[contains(text(),'Providers Found')]")).getText();
		//System.out.println("+++++"+count);
		String [] totalcount=count.split("FOUND ");
		String countno=totalcount[1];
	//	System.out.println("--------"+countno+"length...."+countno.length());
		String count2=countno.substring(1, 2);
		if(countno.length()>3)
			 count2=countno.substring(1,3);
		int actualcount=Integer.parseInt(count2);
		// System.out.println("--------"+actualcount);
		if(actualcount>=0)
			REPORTER.LogEvent(TestStatus.PASS, "Verify Search results", actualcount +" Provider search results available", "");
		}
		catch(Exception e)
		{
			REPORTER.catchException(e, "Verify Search radius");
		}
		
		
	}
	
	/**
	 * Adding the Locations from Reservation Flow
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 */
	public void EP_AddNewLocation_Reservation(String LocationName) throws IOException, Exception {
		
		
		//Enter the Location Name as Empty
		COMMON_METHODS.editAField(getTestObject("ROL_11"), LocationName);

		// Enter the Postal Code 
		COMMON_METHODS.editAField(getTestObject("ROL_12"), getTestData("TD_Zip1"));
		
		// Enter the Address   
		COMMON_METHODS.editAField(getTestObject("ROL_13"), getTestData("TD_Address1"));
		
				
		// Enter the City
		COMMON_METHODS.editAField(getTestObject("ROL_14"), getTestData("TD_City1"));
		
		// Enter the County
		COMMON_METHODS.editAField(getTestObject("ROL_15"), getTestData("TD_County1"));
		
		// Select the Country
		COMMON_METHODS.listBoxSelect(getTestObject("ROL_16"), getTestData("TD_Country1"), "byVisibleText");
		
		// Select the State/Province*
		COMMON_METHODS.listBoxSelect(getTestObject("ROL_17"), getTestData("TD_State1"), "byVisibleText");
		
		//Click 'Add Location' button
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		Thread.sleep(5000);
		
		
	
	}
	
	
	/**
	 * Enter nomination data
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_ProviderEnterText(String field,String value) throws IOException,Exception
	{
		nominateproviderenum npe=nominateproviderenum.valueOf(field);
		switch(npe)
		{
		case Name:
		{
			COMMON_METHODS.editAField(getTestObject("PT_07"), value);
			break;
		}
		case Address:
		{
			COMMON_METHODS.editAField(getTestObject("PT_08"), value);
			break;
		}
		case ZipCode:
		{	COMMON_METHODS.editAField(getTestObject("PT_09"), value);
			break;
		}
		case Contactno:
		{
			COMMON_METHODS.editAField(getTestObject("PT_10"), value);
			break;
		}
		case Email:
		{
			COMMON_METHODS.editAField(getTestObject("PT_11"), value);
			break;
		}
		case Agent:
		{
			COMMON_METHODS.editAField(getTestObject("PT_12"), value);
			break;
		}
		case Providerdata:
		{
			COMMON_METHODS.editAField(getTestObject("PT_13"), value);
			break;
		}
		
	}
}
	
	/**
	 * Verifying Employer Sign-Up page should display all the  fields like Employer ID, Employer Password, 
	 * Verify Employer button,  and a message ‘Already Registered’  with a link ‘SIGN IN’
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 */
	public void EP_SignUpPage() throws IOException, Exception {
		
		//Verifying the Employer ID displayed in the Sign up page
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_13"));
		
		//Verifying the Employer Password displayed  in the Sign up page
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_14"));
		
		//Verifying the Employer button displayed in the Sign up page
		COMMON_METHODS.verifyElementDisplayed(getTestObject("OL_15"));
		
		//verifying link ‘SIGN IN’ displayed in the Sign up page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_SignUp_01"), "Sign In");
		
		//verifying link ‘Already Registered’ displayed in the Sign up page
		COMMON_METHODS.isTextPresent("Already Registered?");
			
	}
	
	
	/**
	 * Employer information should be verified and BUCA web application should display the Participation Agreement and 
	 * Acceptable Use Policy page           
	 * 
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 */
	public void EP_SignUpAcceptableUsePolicyPage() throws IOException, Exception {
		
		//Verifying display the Participation Agreement and Acceptable Use Policy page
		COMMON_METHODS.VerifyTextPresent(getTestObject("OL_SignUp_02"), "Acceptable Use Policy");
			
	}
	
	
	/**
	 * Employer information should be Verify if BUCA web application user accepts the policy in order to continue. 
	 * Acceptable Use Policy page           
	 * 
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 */
	public void EP_SignUpSubmitPage() throws IOException, Exception {
		
		this.EP_AcceptPolicyAndSubmit();
			
	}
	
	/**
	 * Performs click actions in the Home page
	 * @param String linkName
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_ClickHomeBottomLinks(String linkName) throws IOException, Exception {
		
		epHomePageLinks homePageLinks = epHomePageLinks
		.valueOf(linkName);
		switch (homePageLinks) {
		case Feedback: {
			COMMON_METHODS.clickElement(getTestObject("HT_01"));break;}
		case PrivacyPolicy: {
			COMMON_METHODS.clickElement(getTestObject("HT_02"));break;}
		case TermsofUse: {
			COMMON_METHODS.clickElement(getTestObject("HT_03"));break;}
		case TrademarkNotice: {
			COMMON_METHODS.clickElement(getTestObject("HT_04"));break;}
		case Home: {
			COMMON_METHODS.clickElement(getTestObject("HT_05"));break;}

		default:
			break;
		
		}
	}
	
	/**
	 * Verify text in the opened page
	 * @param String text
	 * @param String whichPage
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_verifyText(String text,String whichPage) throws IOException, Exception {
	//Verify the Reservation Number and Status present at the top
	try {
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString().equalsIgnoreCase(text)){
			REPORTER.LogEvent(TestStatus.PASS, text+" Verified in"+whichPage +" Page", text+" Verified", "");
		}
		
	} catch (Exception e) {
		REPORTER.catchException(e, text+"Verification Failed in"+ whichPage);
	}
	}
	

	/**
	 * @author: Krishna Chaitanya Maringanti
	 * @version: 0.0 
	 * Description: Updates General Settings section fields
	 * @return: void
	 * @param String setting
	 * @param String value
	 * @throws IOException
	 * @throws Exception
	 */

	public void EP_Update_General_Settings(String fieldName, String value) throws IOException, Exception {
		try{
			// Update the field under General Settings section
			COMMON_METHODS.editAField(fieldName, value);
			
			// Click 'Save' button
			COMMON_METHODS.clickElement(getTestObject("MA_04"));

		} catch (Exception e) {
			REPORTER.catchException(e, "Field: " + fieldName + " is not displayed under General Settings section.");
		}
	}
	
	/**
	 * Verify text in the opened page
	 * @param String text
	 * @param String whichPage
	 * @throws IOException
	 * @throws Exception
	 * Creation Date 05-03-2014
	 */
	public void EP_verifyText(String text,String whichPage,String cssLocator) throws IOException, Exception {
	//Verify the Reservation Number and Status present at the top
	try {
		if(BH_SetUp_TearDown.driver.findElement(By.cssSelector(cssLocator)).getText().trim().toString().equalsIgnoreCase(text)){
			REPORTER.LogEvent(TestStatus.PASS, text+" Verified in"+whichPage +" Page", text+" Verified", "");
		}
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Excepted= "+text, "Actual=  "+BH_SetUp_TearDown.driver.findElement(By.cssSelector(cssLocator)).getText().trim(), "");
			
		
	} catch (Exception e) {
		REPORTER.catchException(e, text+"Verification Failed in"+ whichPage);
	}
	}
	
	
	/**
	 * Care Recipients for Reservation
	 * @author vverimadugu
	 * @param int CareRecipients
	 * @param int HealthStatus[]
	 * @param String selectForReason
	 * @param String continueNext
	 * @throws IOException
	 * @throws Exception
	 * @return void
	 * Creation Date 05-03-2014
	 */
	public void EP_ReservationCareRecipients(int CareRecipients,int HealthStatus[],String selectForReason,String continueNext) throws IOException, Exception {
		
		
		//Select a 'Reason for Care'
		if(selectForReason!=null && !selectForReason.equals(""))
			COMMON_METHODS.listBoxSelect(getTestObject("OL_81"), selectForReason, "byVisibleText");
		
		String[] sTempCare = COMMON_METHODS.splitTestObject(getTestObject("WW_02"));
		String[] sTempHealth = COMMON_METHODS.splitTestObject(getTestObject("WW_03"));
		//Select any Care Recipient, by checking check box next to name
		for(int i=0;i<CareRecipients;i++){
			String careRecipient;
			String health;
			String tempCare=sTempCare[3]+i+"__IsSelected";
			careRecipient=sTempCare[0]+","+sTempCare[1]+","+sTempCare[2]+","+tempCare;
			String tempHealth=sTempHealth[3]+i+"__IsHealthy']"+"["+HealthStatus[i]+"]";
			health=sTempHealth[0]+","+sTempHealth[1]+","+sTempHealth[2]+","+tempHealth;
			if(COMMON_METHODS.isElementPresent(tempCare,"id")){
				//Select any Care Recipient, by checking check box next to name
				COMMON_METHODS.checkBox(careRecipient, "check");
				//Select Health Status of the selected Care Recipient
				COMMON_METHODS.radioButton(health);
				tempCare=null;
				tempHealth=null;
			}
			else{
				REPORTER.LogEvent(TestStatus.FAIL, "Object '"+ tempCare + "' not Present", "ObjId '"	+ tempCare + "' is not displayed","");
				break;
			}
			
		}
		
		//Click 'Continue'
		if(continueNext.equalsIgnoreCase("yes"))
			COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
	}
	
	
	
	/**
	 * Adding the Authorized Contacts in Care profile
	 * @author vverimadugu
	 * @param int aContacts,
	 * @param String [][] testdata
	 * @throws IOException
	 * @throws Exception
	 * @return void
	 * Creation Date 05-03-2014
	 */
	public void EP_AuthorizedContactsAccess(int aContacts,String [][] testdata,String addCr,String coPayRule,String client) throws IOException, Exception
	{
		
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
		
		if(addCr.equalsIgnoreCase("Yes"))
			addCareRecipients(addCrData3_5Years,"No",client);
	    
		String[] tAcessYes = COMMON_METHODS.splitTestObject(getTestObject("CR_02"));
		String[] tAcessNo = COMMON_METHODS.splitTestObject(getTestObject("CR_03"));
		String[] tRelationshipRecipient = COMMON_METHODS.splitTestObject(getTestObject("CR_04"));
		String[] tParentOrGuardianNo = COMMON_METHODS.splitTestObject(getTestObject("CR_05"));
		String[] tParentOrGuardianYes = COMMON_METHODS.splitTestObject(getTestObject("CR_06"));
		String[] tEmergencyContact = COMMON_METHODS.splitTestObject(getTestObject("CR_07"));
		String[] tAuthorizedPickUp = COMMON_METHODS.splitTestObject(getTestObject("CR_08"));
		//Select any Care Recipient, by checking check box next to namevxcv
		for(int i=0;i<aContacts;i++){
			String acessYes;
			String acessNo;
			String relationshipRecipient;
			String parentOrGuardianNo;
			String parentOrGuardianYes;
			String emergencyContact;
			String authorizedPickUp;
			
			String acessYes1=tAcessYes[3]+i;
			String acessNo1=tAcessNo[3]+i;
			String relationshipRecipient1=tRelationshipRecipient[3]+i+"__RelationshipTypeId";
			String parentOrGuardianNo1=tParentOrGuardianNo[3]+i+"']/input[1]";
			String parentOrGuardianYes1=tParentOrGuardianYes[3]+i+"']/input[2]";
			String emergencyContact1=tEmergencyContact[3]+i;
			String authorizedPickUp1=tAuthorizedPickUp[3]+i;
			
			 acessYes=tAcessYes[0]+","+tAcessYes[1]+","+tAcessYes[2]+","+acessYes1;
			 acessNo=tAcessNo[0]+","+tAcessNo[1]+","+tAcessNo[2]+","+acessNo1;
			 relationshipRecipient=tRelationshipRecipient[0]+","+tRelationshipRecipient[1]+","+tRelationshipRecipient[2]+","+relationshipRecipient1;
			 parentOrGuardianNo=tParentOrGuardianNo[0]+","+tParentOrGuardianNo[1]+","+tParentOrGuardianNo[2]+","+parentOrGuardianNo1;
			 parentOrGuardianYes=tParentOrGuardianYes[0]+","+tParentOrGuardianYes[1]+","+tParentOrGuardianYes[2]+","+parentOrGuardianYes1;
			 emergencyContact=tEmergencyContact[0]+","+tEmergencyContact[1]+","+tEmergencyContact[2]+","+emergencyContact1;
			 authorizedPickUp=tAuthorizedPickUp[0]+","+tAuthorizedPickUp[1]+","+tAuthorizedPickUp[2]+","+authorizedPickUp1;
			
			 Thread.sleep(1000);
			 
			 if(testdata[i][0].equalsIgnoreCase("Yes")){
				 COMMON_METHODS.radioButton(acessYes);
			 
			 if(COMMON_METHODS.isElementPresent(relationshipRecipient1, "id"))
				 COMMON_METHODS.listBoxSelect(relationshipRecipient, testdata[i][1], "byVisibleText");
		
			 Thread.sleep(1000);
			 
			 //Select 'Parent/Guardian' to No
			 if(i!=2){
				 if(COMMON_METHODS.isElementPresent(parentOrGuardianNo1, "xpath"))
				 COMMON_METHODS.radioButton(parentOrGuardianNo);
			 }else if (coPayRule.equalsIgnoreCase("yes")) {
					 COMMON_METHODS.radioButton(parentOrGuardianNo);
			 }
			 
			 Thread.sleep(1000);
			 
			 //Select Emergency Contact
			 if(COMMON_METHODS.isElementPresent(emergencyContact1, "id"))
				 COMMON_METHODS.checkBox(emergencyContact, "check");
			 else if (coPayRule.equalsIgnoreCase("yes")) {
				 COMMON_METHODS.checkBox(emergencyContact, "check");
			 }
			 
			 Thread.sleep(1000);
		
			 //Select Authorized Pick-Up 
			 if(i!=2){
				 if(COMMON_METHODS.isElementPresent(authorizedPickUp1, "id"))
					 COMMON_METHODS.checkBox(authorizedPickUp, "check");
			 }else if (coPayRule.equalsIgnoreCase("yes")) {
				 COMMON_METHODS.checkBox(authorizedPickUp, "check");
			 }
	}
			 
			else
				COMMON_METHODS.radioButton(acessNo);
			 
		 acessYes=null;
		 acessNo=null;
		 relationshipRecipient=null;
		 parentOrGuardianNo=null;
		 parentOrGuardianYes=null;
		 emergencyContact=null;
		 authorizedPickUp=null;
		
		}
		
		//Click 'Add Care Recipients' button
		if(addCr.equalsIgnoreCase("Yes"))
			COMMON_METHODS.clickElement(getTestObject("OL_57"));
		
		//Click 'Add Authorized Contact' button
		else
		COMMON_METHODS.clickElement(getTestObject("OL_69"));	
	}
	
	
	/**
	 * Adding multiple Care Recipients in Care profile
	 * 
	 * @author vverimadugu
	 * @version 
	 * @return void
	 * @param 
	 * Creation Date 13-03-2014
	 */
	public void addCareRecipients(String data[],String isAC,String clientName) throws IOException, Exception
	{

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Getting current client 
		//whichClient=getTestData("TD_EmployerId");
		whichClient=getXMLData(clientName, "TD_EmployerId"); 
		Thread.sleep(5000);

		//Enter First Name
		if(data[0]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_42"), data[0]);

		//Enter Last Name
		if(data[1]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_43"),data[1]);

		//Select Relationship to Client Employee
		if(data[2]!=null)
			COMMON_METHODS.listBoxSelect(getTestObject("OL_44"), data[2], "byVisibleText");

		//Enter Birth Date
		if(data[3]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_45"), data[3]);

		//Select Gender
		if(data[4]!=null)
			COMMON_METHODS.radioButton(data[4]);

		//Select Food Restrictions
		if(data[5]!=null)
			COMMON_METHODS.radioButton((data[5]));

		//Select Food Allergies
		if(data[6]!=null)
			COMMON_METHODS.radioButton(data[6]);

		//Select Allergies to Medication
		if(data[7]!=null)
			COMMON_METHODS.radioButton(data[7]);

		//Select Other Allergies
		if(data[8]!=null)
			COMMON_METHODS.radioButton(data[8]);

		//Select Diagnosed Special Needs / Medical Conditions
		if(data[9]!=null)
			COMMON_METHODS.radioButton(data[9]);

		//Select Activity Restrictions
		if(data[10]!=null)
			COMMON_METHODS.radioButton(data[10]);

		//Enter Additional Information
		if(data[11]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_53"), data[11]);

		//Select Is Client Employee a Parent or Legal Guardian
		if(data[12]!=null)
			COMMON_METHODS.radioButton(data[12]);

		//Select Custody Issues/ Visitation Orders
		if(data[13]!=null)
			COMMON_METHODS.radioButton(data[13]);

		//Select Where do you primarily need care
		if(data[14]!=null)
			COMMON_METHODS.listBoxSelect(getTestObject("OL_56"), data[14], "byVisibleText");

		//Input Approx. Height
		if(data[19]!=null)
			COMMON_METHODS.editAField(getTestObject("CR_25"), data[19]);

		//Input Approx. weight
		if(data[20]!=null)
			COMMON_METHODS.editAField(getTestObject("CR_26"), data[20]);

		//Enter Regular Care Arrangements
		if(data[15]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_99"), data[15]);

		//Select Toilet Trained
		if(data[16]!=null)
			COMMON_METHODS.radioButton(data[16]);

		//SelectCrawling Trained
		if(data[17]!=null)
			COMMON_METHODS.radioButton(data[17]);

		//Please include the food restriction as well as any relevant details 
		/*if(data[21]!=null)
		COMMON_METHODS.editAField(getTestObject("CARE_Food_RES_NOTES"),data[21]);
		
	    //Please include the allergy as well as symptoms and treatment required
	    if(data[22]!=null)
	    	COMMON_METHODS.editAField(getTestObject("CARE_Food_ALL_NOTES"),data[22]);*/
	    
		//Select Preferred Center Location*
		if (whichClient != null
				&& (whichClient.equalsIgnoreCase("TCTHREE")
						|| whichClient.equalsIgnoreCase("TCSIX")
						|| whichClient.equalsIgnoreCase("Chevron")
						|| whichClient.equalsIgnoreCase("JPL")
						|| whichClient.equalsIgnoreCase("lands end")
						|| whichClient.equalsIgnoreCase("NYL")
						|| whichClient.equalsIgnoreCase("Reebok")
						|| whichClient.equalsIgnoreCase("Target")
						|| whichClient.equalsIgnoreCase("BCG")
						|| whichClient.equalsIgnoreCase("DRAFTFCB") || whichClient
						.equalsIgnoreCase("AllenOveryUK")))
			COMMON_METHODS.listBoxSelect(getTestObject("OL_103"), getXMLData(
					clientName, "TD_CENTERLOCATION"), "byVisibleText");

		//Select Preferred Center Location for Deutsche bank UK*
		if (whichClient.equalsIgnoreCase("Backup")
				&& getXMLData(clientName, "TD_ClientName").equals(
						"Deutsche Bank UK")) {
			COMMON_METHODS.listBoxSelect(getTestObject("OL_103"), getXMLData(
					clientName, "TD_CENTERLOCATION"), "byVisibleText");
		}
		
		
					
		// COMMON_METHODS.radioButton(acessYes);
		if(isAC.equalsIgnoreCase("Yes")){
			//COMMON_METHODS.listBoxSelect(getTestObject("CR_27"),"Father", "byVisibleText");
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("OL_65"), getTestData("TD_Relationship"), "byVisibleText");

			//Select 'Parent/Guardian' to No
			COMMON_METHODS.radioButton(getTestObject("CR_28"));
			//COMMON_METHODS.radioButton(getTestObject("OL_65"));
			
			//Select Emergency Contact
			COMMON_METHODS.checkBox(getTestObject("CR_29"), "check");

			//Select Authorized Pick-Up 
			COMMON_METHODS.checkBox(getTestObject("CR_30"), "check");
		}	
		System.out.println("Exiting - " + methodName);
		
	}
	
	/** 
     * This test will cover adding payment details
     * 
     * @author Kiran 
     * @version 
     * @return void 
     * @param 
     * Creation Date 10-03-2014 
     */ 
    public void addPaymentMethod(String fromWhere) throws IOException, Exception 
    { 
    	    if(fromWhere!=null &&fromWhere.equalsIgnoreCase("ManagePaymentMethod")){
    	    	boolean isElementPresent = COMMON_METHODS.driver.findElements(
    					By.xpath("//ul[@id='pageTwoColRightSm']/li[1]/ul[5]/li[2]/a")).size() != 0;
    			System.out.println("isElementPresent=="+isElementPresent);
    	    	    if(isElementPresent)
    	    		// Click on Manage Payment Method7
    	    		COMMON_METHODS.clickElement(getTestObject("RS_20"));
    	    	    else
    	    	    	REPORTER.LogEvent(TestStatus.INFO,"No Manage Payment Method link","No Payment Method Functionality for this client","");
    	    }
    		//click 'Add a Payment Method' 
            COMMON_METHODS.clickElement(getTestObject("OL_117")); 
            
            //Ensure user is able to successfully add a credit card payment method with all the req fields 
    
            //Switch to iframe 
            COMMON_METHODS.driver.switchTo().frame(COMMON_METHODS.driver.findElement(By.name("ezdraft-iframe"))); 
            COMMON_METHODS.editAField(getTestObject("MA_23"),getTestData("TD_Feedback")); 
            Thread.sleep(2000); 
            COMMON_METHODS.editAField(getTestObject("MA_24"),"Test"); 
            Thread.sleep(2000); 
            COMMON_METHODS.radioButton(getTestObject("MA_25")); 
            Thread.sleep(2000); 
            COMMON_METHODS.radioButton(getTestObject("MA_26")); 
            Thread.sleep(2000); 
            COMMON_METHODS.editAField(getTestObject("MA_27"),"4111111111111111"); 
            Thread.sleep(2000); 
            COMMON_METHODS.listBoxSelect(getTestObject("MA_28"), getTestData("TD_Month"), "byVisibleText"); 
            Thread.sleep(2000); 
            COMMON_METHODS.listBoxSelect(getTestObject("MA_29"), getTestData("TD_Year"), "byVisibleText"); 
            Thread.sleep(2000); 
            COMMON_METHODS.clickElement(getTestObject("MA_30")); 
            Thread.sleep(2000); 
            COMMON_METHODS.clickElement(getTestObject("MA_31")); 
            Thread.sleep(10000); 
            //Switch to normal window 
            COMMON_METHODS.driver.switchTo().defaultContent(); 
            //click on cancel on the pop up 
            COMMON_METHODS.clickElement(getTestObject("MA_32")); 
            Thread.sleep(10000);                    
            
            
                        
    } 
    
    /** 
     * This methods has logic to select special program and select care recipient
     * 
     * @author vverimadugu 
     * @version 
     * @return void 
     * @param 
     * Creation Date 10-03-2014 
     */ 
    public void selectSpecialProgram() throws IOException, Exception 
    { 
    	//select select Special Program radio button
    	COMMON_METHODS.radioButton(getTestObject("CR_15"));
    	
    	//select care recipients 
    	COMMON_METHODS.clickElement(getTestObject("OL_82"));
    	
    	//Click continue button
    	COMMON_METHODS.clickElement(getTestObject("OL_84"));
    	
    }
    
   	
   	/** 
     * This methods has logic to verify the care recepient details are updated
     * @author Kiran G 
     * @version 
     * @return void 
     * @param 
     * Creation Date 10-03-2014 
     */ 
    public void Care_Profile_Verify() throws IOException, Exception 
    { 
    	//Verify first name in 'Care Profile' section are already populated with correct values
    	if(COMMON_METHODS.getText(getTestObject("OL_42"),"value").equals(getTestData("TD_CR_FirstName"))){
			REPORTER.LogEvent(TestStatus.PASS, "First name field is updated", "First name field is updated", "");
    	}else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "First name field is Not updated", "First name field is Not updated", "");
		}
    	
    	//Verify Last name in 'Care Profile' section are already populated with correct values
    	if(COMMON_METHODS.getText(getTestObject("OL_43"),"value").equals(getTestData("TD_CR_LastName")))
			REPORTER.LogEvent(TestStatus.PASS, "Last name field is updated", "Last name field is updated", "");
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Last name field is Not updated", "Last name field is Not updated", "");
		}
    	
    	//Verify the Relation is populated or not
    	if(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_44")).equals(getTestData("TD_Relationship")))
    		REPORTER.LogEvent(TestStatus.PASS, "Relation name field is updated", "Relation name field is updated", "");
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Relation name field is Not updated", "Relation name field is Not updated", "");
		}
    	
    	//Verify the Gender is selected or not
    	if(COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_61")))
			REPORTER.LogEvent(TestStatus.PASS, "Gender field is updated", "Gender field is updated", "");
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Gender field is Not updated", "Gender field is Not updated", "");
		}
    	
    	//Verify the Custody Issues/ Visitation Orders is selected or not
    	if(COMMON_METHODS.VerifyRadioButtonSelected(getTestObject("OL_55")))
			REPORTER.LogEvent(TestStatus.PASS, "Custody Issues field is updated", "Custody Issues field is updated", "");
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Custody Issues field is Not updated", "Custody Issues field is Not updated", "");
		}
    	
    	//Verify the Primarily need care is populated or not
    	if(COMMON_METHODS.getSelectedValueFromListBox(getTestObject("OL_56")).equals(getTestData("TD_State")))
    		REPORTER.LogEvent(TestStatus.PASS, "Primarily need care field is updated", "Primarily need care field is updated", "");
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Primarily need care field is Not updated", "Primarily need care field is Not updated", "");
		}
    	
    	//Verify correct date of birth is selected or not
    	if(COMMON_METHODS.getText(getTestObject("OL_45"),"value").equals(getTestData("TD_DOB")))
			REPORTER.LogEvent(TestStatus.PASS, "Date of Birth field is updated", "Date of Birth field is updated", "");
		else{
			// Report the below statement
			REPORTER.LogEvent(TestStatus.FAIL, "Date of Birth field is Not updated", "Date of Birth field is Not updated", "");
		}
    	 	
       
    } 
   	

/** 
     * This methods adds a pet
     * 
     * @author Krishna Chaitanya Maringanti 
     * @version 
     * @return void 
     * @param 
     * Creation Date 12-03-2014 
     */ 
	
    public void addAnotherPet(String strPetType, String strPetsCount, String strBreed) throws IOException, Exception 
    { 

    	//Click Add Another Pet link
    	COMMON_METHODS.clickElement(getTestObject("MA_57"));

		// Select the 'Dog' from the 'Pet Type' drop-down 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_21"), strPetType, "byVisibleText");
		
		// Select '1' from 'How Many?' drop-down   
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_26"), strPetsCount, "byVisibleText");
		Thread.sleep(5000);
		
		// Enter the breed 
		COMMON_METHODS.editAField(getTestObject("RESV_23"), strBreed);

    }
    
    /** 
     * This methods covers the functionality of ForgetPassword and Reset Password
     * 
     * 
     * @author vverimadugu
     * @version 
     * @return void 
     * @param 
     * Creation Date 17-03-2014 
     */ 
	
    public void forGetAndResetPassword(String type,String testData[]) throws IOException, Exception 
    { 

    	
    	if(type.equalsIgnoreCase("ForgetPassword")){
    		//Click ForgetPassword link
    		COMMON_METHODS.clickElement(getTestObject("LP_01"));
    		Thread.sleep(5000);
    		//Enter email id
    		COMMON_METHODS.editAField(getTestObject("OL_20"), testData[0]);
    		////Click Submit button
    		COMMON_METHODS.clickElement(getTestObject("MA_31"));
    	}
    	else{
    		//Click Reset Password link
    		COMMON_METHODS.clickElement(getTestObject("RP_02"));
    		Thread.sleep(5000);
    		//Enter old password
    		COMMON_METHODS.editAField(getTestObject("RP_01"), testData[0]);
    		//Enter new password
    		COMMON_METHODS.editAField(getTestObject("OL_2"), testData[1]);
    		//Enter confirm password
    		COMMON_METHODS.editAField(getTestObject("OL_21"), testData[1]);
    		//Click Submit button
    		COMMON_METHODS.clickElement(getTestObject("HL_07"));
    		Thread.sleep(9000);
    	}
    	
    }
    
    
    /**
	 * Create reservation for CoPay
	 * 
	 * @param clientNo
	 * @param noOfCRs
	 * @param healthStatus
	 * @param startTime
	 * @param endTime
	 * @throws Exception
	 */
	public void EP_CoPayReservation(int clientNo,int noOfCRs,String healthStatus,String dateOfReservation,String startTime,String endTime) throws Exception {
		
	try {
		//Read testdata for based on clientNo
		/*readTestData(getDataTablePath("EP"), "TD_EP" + clientNo);*/
				
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		// ******* Care Recipients for Reservation ******************
		
		//Select a 'Reason for Care'
		COMMON_METHODS.listBoxSelect(getTestObject("OL_81"), getTestData("TD_ReasonForCare"), "byVisibleText");
		
		
		for (int careRece=0;careRece < noOfCRs;careRece++) {
			
			BH_SetUp_TearDown.driver.findElement(By.id("Reservation_ReservationCareRecipients_" + careRece + "__IsSelected")).click();
			
			if (healthStatus.equalsIgnoreCase("Healthy")) 
				BH_SetUp_TearDown.driver.findElement(By.id("Reservation_ReservationCareRecipients_" + careRece + "__IsHealthy")).click();
			else if (healthStatus.equalsIgnoreCase("Mildly Ill"))
				BH_SetUp_TearDown.driver.findElement(By.xpath("//input[@id='Reservation_ReservationCareRecipients_" + careRece + "__IsHealthy'][2]")).click();
		}
		

		//Select any Care Recipient, by checking check box next to name
		COMMON_METHODS.checkBox(getTestObject("OL_82"), "check");
		
/*		if (healthStatus.equalsIgnoreCase("Healthy")) {
			//Select Health Status of the selected Care Recipient
			COMMON_METHODS.radioButton(getTestObject("OL_83"));
		}else if (healthStatus.equalsIgnoreCase("Mildly Ill")) {
			//Select Mildly Ill Status of the selected Care Recipient
			COMMON_METHODS.radioButton(getTestObject("RES_ MildlyIll"));
		}
*/		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
				
		//Verify the wizard moves to step 2.
		//COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//System.out.println("Copay Date of reservation : " + getTestData("TD_COPAYDOR"));
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), dateOfReservation);
		
		//Select 'Start Time'
		COMMON_METHODS.clickElement(getTestObject("OL_86"));

		this.setStartEndTime(startTime);
		
		BH_SetUp_TearDown.driver.findElement(By.xpath("//button[@type='button'][2]")).click();
		
		//Select 'End Time'
		COMMON_METHODS.clickElement(getTestObject("OL_87"));
		
		this.setStartEndTime(endTime);
		
		BH_SetUp_TearDown.driver.findElement(By.xpath("//button[@type='button'][2]")).click();
		
		Thread.sleep(2000);
			
		//Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
		COMMON_METHODS.radioButton(getTestObject("ROL_03"));
		
		//Select Distance from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
		
		//Select any Location from 'Location' drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_89"), getTestData("TD_Location"), "byVisibleText");
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
		Thread.sleep(7000);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		this.EP_ReservationCareOptions();
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		this.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
		
		this.EP_CoPayReservationReveiwDetails();
	}catch(Exception e) {
		System.out.println("aaaaaaaaaaaaa" + e.getMessage());
	}
		
	}
	

	/**
	 * Create InHome reservation for CoPay
	 * 
	 * @param clientNo
	 * @param noOfCRs
	 * @param healthStatus
	 * @param startTime
	 * @param endTime
	 * @throws Exception
	 */
	public void EP_CoPayInHomeReservation(int clientNo,int noOfCRs,String healthStatus,String dateOfReservation,String startTime,String endTime) throws Exception {
		
		//Read testdata for based on clientNo
		/*readTestData(getDataTablePath("EP"), "TD_EP" + clientNo);*/
				
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
				
		// ******* Care Recipients for Reservation ******************
		
		//Select a 'Reason for Care'
		COMMON_METHODS.listBoxSelect(getTestObject("OL_81"), getTestData("TD_ReasonForCare"), "byVisibleText");
		
		//Select any Care Recipient, by checking check box next to name
		COMMON_METHODS.checkBox(getTestObject("OL_82"), "check");
		
		if (healthStatus.equalsIgnoreCase("Healthy")) {
			//Select Health Status of the selected Care Recipient
			COMMON_METHODS.radioButton(getTestObject("OL_83"));
		}else if (healthStatus.equalsIgnoreCase("Mildly Ill")) {
			//Select Mildly Ill Status of the selected Care Recipient
			COMMON_METHODS.radioButton(getTestObject("RES_ MildlyIll"));
		}
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
				
		//Verify the wizard moves to step 2.
		//COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
		
		//System.out.println("Copay Date of reservation : " + getTestData("TD_COPAYDOR"));
		
		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), dateOfReservation);
		
		//Select 'Start Time'
		COMMON_METHODS.clickElement(getTestObject("OL_86"));

		this.setStartEndTime(startTime);
		
		BH_SetUp_TearDown.driver.findElement(By.xpath("//button[@type='button'][2]")).click();
		
		//Select 'End Time'
		COMMON_METHODS.clickElement(getTestObject("OL_87"));
		
		this.setStartEndTime(endTime);
		
		BH_SetUp_TearDown.driver.findElement(By.xpath("//button[@type='button'][2]")).click();
		
		Thread.sleep(2000);
			
		//Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
		COMMON_METHODS.radioButton(getTestObject("ROL_03"));
		
		//Select Distance from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
		
		//Select any Location from 'Location' drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_89"), getTestData("TD_Location"), "byVisibleText");
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
		Thread.sleep(7000);

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");
		
		this.EP_ReservationInHomeCareOptions("CareOptions");
		
		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");
		
		this.EP_ReservationVerifyInfoNo();
		
		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");
		
		COMMON_METHODS.editAField(getTestObject("IH_100"), getTestData("TD_GreeterName"));
		
		COMMON_METHODS.editAField(getTestObject("IH_101"), getTestData("TD_GreetRelation"));
		
		COMMON_METHODS.checkBox(getTestObject("IH_102"), "check");
		
		this.EP_CoPayReservationReveiwDetails();
		
	}
	
		
	
	
	/**
	 * Review Details for CoPay Reservation
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_CoPayReservationReveiwDetails() throws IOException, Exception {
		
		//Check 'I have read and agree to the Payment Policy' check box present under 'Payment Terms'
		COMMON_METHODS.checkBox(getTestObject("OL_95"), "check");
		
		//Check 'Accept the Cancellation Policy for this reservation' checkbox present under 'Cancellation Policy'
		COMMON_METHODS.radioButton(getTestObject("OL_96"));

		//Click 'Request Reservation'
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		System.out.println("Reservation # - " + BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1 > span")).getText().trim());
		System.out.println("#################################" + BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString());

		//Verify the Reservation Number and Status present at the top
		try {
			String ResNum = BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1 > span")).getText().trim();
			String ResStatus = BH_SetUp_TearDown.driver.findElement(By.cssSelector("span.hdrStatus")).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Confirmed", "Reservation # and Reservation Status : " + ResNum +" "+ ResStatus , "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Reservation Confirmed");
		}
		
	
	}
	
	
	/**
	 * 
	 * @param amountCharged
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_CoPayPaymentInformation(String expectedAmountCharged) throws IOException, Exception {
		
		try {
			System.out.println(getTestObject("COPAY_PAYMENT"));
			
			String actualCopayamountcharges = COMMON_METHODS.getText(getTestObject("COPAY_PAYMENT"));
			
			if (expectedAmountCharged.equals(actualCopayamountcharges)) {
				REPORTER.LogEvent(TestStatus.PASS, "Verify copay amount", "CoPay amount displayed as : " + expectedAmountCharged, "");
			}else {
				REPORTER.LogEvent(TestStatus.FAIL, "Verify copay amount", "CoPay amount charged incorrectly" + " Actual  : " + actualCopayamountcharges + "Expected Copay Amount : " + expectedAmountCharged, "");
			}
			
		}catch(Exception exp)  {
			exp.printStackTrace();
		}
	}
	
	
	/**
	 * setting time for start and end time
	 * 
	 * @param startTime
	 */
	public void setStartEndTime(String stTime) {
		
		try {
			
			String time[] =  stTime.split(":");
			
			int hours = Integer.parseInt(time[0]);
			
			String mins = time[1];
			
			String min[] = mins.split(" ");
			
			String aft = min[1];
			
			WebElement sliderHour = COMMON_METHODS.driver.findElement(By.xpath("//dd[@class='ui_tpicker_hour']/div/a"));
			Actions moveHour = new Actions(COMMON_METHODS.driver);
			
			if (aft.equalsIgnoreCase("PM")) {
				if(hours!=12)
					hours = 12+hours;
			}
			
			for (int i=0;i<hours;i++) {
				moveHour.moveToElement(sliderHour).click(sliderHour).sendKeys(Keys.ARROW_UP).perform();
			}
			
		}catch (Exception e) {

			REPORTER.catchException(e, "Enter Start Time / End Time");
		}

	}
	
	/** 
	 * This methods will check all the check boxes present in the Skills tab of Provider portal
	 * @author Kiran G
	 * @version 
	 * @return void 
	 * @param 
	 * Creation Date 19-03-2014 
	 */ 
	public void PP_SkillSets(String row) throws IOException, Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1]
		                                           				.getMethodName();
		try {
			
		
		/*String selectSkillSet[]={getTestObject("PPMA_118"),getTestObject("PPMA_119"),getTestObject("PPMA_120"),
				getTestObject("PPMA_121"),getTestObject("PPMA_122"),getTestObject("PPMA_123"),getTestObject("PPMA_124"),
				getTestObject("PPMA_125"),getTestObject("PPMA_126"),getTestObject("PPMA_127"),getTestObject("PPMA_128"),
				getTestObject("PPMA_129"),getTestObject("PPMA_130"),getTestObject("PPMA_131"),getTestObject("PPMA_132"),
				getTestObject("PPMA_133"),getTestObject("PPMA_134"),getTestObject("PPMA_135"),getTestObject("PPMA_136"),
				getTestObject("PPMA_137"),getTestObject("PPMA_138"),getTestObject("PPMA_139")};*/

		/*for(int i=0;i<selectSkillSet.length;i++){
			COMMON_METHODS.checkBox(selectSkillSet[i],"check");}*/
			
			String selectSkillSet[]={"ProblemSolving","EffectiveCommunicator","TransferringCareRecipients","LiftBelt",
			"SocialPerceptiveness","ActiveListening","ActiveLearning","DetailOriented",
			"MealPreparation","Tutoring","HoyerLiftExperience","SpecialNeedsExperience",
			"CoordinatingActivities","Writing","LearningStrategies","TimeManagement",
			"Organizing","DementiaAlzheimersExperience","AutismExperience","JudgementAndDecisionMaking",
			"Instructing","CustomerService"};
		
		for(int i=0;i<selectSkillSet.length;i++){
			//String ojbId="CaregiverList_"+i+"__ProviderCaregiverSkill_"+selectSkillSet[i];
			COMMON_METHODS.driver.findElement(By.id("CaregiverList_"+row+"__ProviderCaregiverSkill_"+selectSkillSet[i])).click();
			REPORTER.LogEvent(TestStatus.PASS, "Check Checkbox '"
					+ selectSkillSet[i] + "'", "Check checkbox '" + selectSkillSet[i]
					+ "'" + "' - Successfull".toUpperCase(), "");
		} 
		}
		catch (Exception e) {

			REPORTER.catchException(e, "Click '" + "locators not found" + "'");
		}
	}


	/** 
	 * This methods will check all the check boxes present in the Personality/Interests tab of Provider portal
	 * @author Kiran G
	 * @version 
	 * @return void 
	 * @param 
	 * Creation Date 19-03-2014 
	 */ 
	public void PP_Interests_Personality(String row) throws IOException, Exception
	{
		try {
		/*String selectInterest[]={getTestObject("PPMA_140"),getTestObject("PPMA_141"),getTestObject("PPMA_142"),
				getTestObject("PPMA_143"),getTestObject("PPMA_144"),getTestObject("PPMA_145"),getTestObject("PPMA_146"),
				getTestObject("PPMA_147"),getTestObject("PPMA_148"),getTestObject("PPMA_149"),getTestObject("PPMA_150"),
				getTestObject("PPMA_151"),getTestObject("PPMA_152"),getTestObject("PPMA_153"),getTestObject("PPMA_154"),
				getTestObject("PPMA_155"),getTestObject("PPMA_156"),getTestObject("PPMA_157"),getTestObject("PPMA_158"),
				getTestObject("PPMA_159"),getTestObject("PPMA_160"),getTestObject("PPMA_161"),getTestObject("PPMA_162"),
				getTestObject("PPMA_163"),getTestObject("PPMA_164"),getTestObject("PPMA_165"),getTestObject("PPMA_166"),
				getTestObject("PPMA_167"),getTestObject("PPMA_168"),getTestObject("PPMA_169"),getTestObject("PPMA_170"),
				getTestObject("PPMA_171"),getTestObject("PPMA_172"),getTestObject("PPMA_173"),getTestObject("PPMA_174"),
				getTestObject("PPMA_175"),getTestObject("PPMA_176"),getTestObject("PPMA_177"),getTestObject("PPMA_178"),getTestObject("PPMA_179")};

		for(int k=0;k<selectInterest.length;k++){
			COMMON_METHODS.checkBox(selectInterest[k],"check");}*/
		
		String selectSkillSet[]={"Energetic","Imaginative","Empathetic","Understanding","Discreet","Optimistic",
				"Precise","Reliable","Creative","Soothing","Flexible","Adventurous","Observant","Intelligent",
				"Confident","Exuberant","SoftSpoken","GoodListener","Compassionate","Conscientious",
				"Independent","Charming","Encouraging","Helpful"};
			
			for(int i=0;i<selectSkillSet.length;i++){
				//String ojbId="CaregiverList_"+i+"__ProviderCaregiverSkill_"+selectSkillSet[i];
				COMMON_METHODS.driver.findElement(By.id("CaregiverList_"+row+"__ProviderCaregiverTrait_"+selectSkillSet[i])).click();
				REPORTER.LogEvent(TestStatus.PASS, "Check Checkbox '"
						+ selectSkillSet[i] + "'", "Check checkbox '" + selectSkillSet[i]
						+ "'" + "' - Successfull".toUpperCase(), "");
			} 
			}
			catch (Exception e) {

				REPORTER.catchException(e, "Click '" + "locators not found" + "'");
			}
	}
	
	/*
	 * This method cancel the CB Reservation
	 * 
	 * */
	
	public void CancelReservation(String status[],String action,String objIdentifier[]) throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Select the Reservations in progress 
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_01"), status[0], status[1]);
		Thread.sleep(15000);
		// Click on 'View' link on a reservation 
		//COMMON_METHODS.clickElement(getTestObject("RESV_02"));
		if(action!=null){
			if(COMMON_METHODS.isElementPresent(objIdentifier[0], "xpath"))
			COMMON_METHODS.driver.findElement(By.xpath(objIdentifier[0])).click();
			else
			REPORTER.LogEvent(TestStatus.FAIL, objIdentifier[0]+" objIdentifier not found", objIdentifier[0]+" objIdentifier not found","");
		// Click 'Edit/Cancel Care Sessions' link
		COMMON_METHODS.clickElement(getTestObject("RESV_03"));
		Thread.sleep(9000);
		// Click a Check box in the Care Sessions section
		COMMON_METHODS.checkBox(getTestObject("RESV_04"), "check");

		// Click 'Cancel Selected' link
		COMMON_METHODS.clickElement(getTestObject("RESV_09"));
		Thread.sleep(5000);
		// Select a reason for Cancel
		COMMON_METHODS.listBoxSelect(getTestObject("RESV_11"), "Bad Weather", "byVisibleText");
		Thread.sleep(4000);
		// Click 'Submit' button on 'Cancel Care Session' pop-up
		COMMON_METHODS.clickElement(getTestObject("RESV_13"));
		//Thread.sleep(180000);
		
		
			for (int second = 0;; second++) {
				if (second >= 180)
					Assert.fail("timeout");
					//REPORTER.LogEvent(TestStatus.FAIL, "Verify Reservation Cancelled", "Timeout after 180 seconds of Wait" , "");
				try {
					if ("There are no reservations of this type. Please select a different Reservation Type."
							.equals(BH_SetUp_TearDown.driver
									.findElement(
											By.cssSelector("#ReservationsListContainer > p"))
									.getText()))
						//REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Cancelled", "Reservation Cancelled SUCCESSFULLY" , "");
						break;
				} catch (Exception e) {
					System.out.println(e.getMessage().toString());
				}
				Thread.sleep(1000);
			}
		
		}
	}
	
	
	
	
public void ResWhenandWhereLocationInhome() throws IOException, Exception{
		
		try {
			//Select 'Start Time'
			COMMON_METHODS.clickElement(getTestObject("OL_86"));
			
			BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeFrom']/dl/dd[2]/div[2]/table/tbody/tr/td[4]")).click();
			BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			
			//Select 'End Time'
			COMMON_METHODS.clickElement(getTestObject("OL_87"));
			BH_SetUp_TearDown.driver.findElement(By.xpath("//div[@id='ui-timepicker-div-txtTimeTo']/dl/dd[2]/div[2]/table/tbody/tr/td[5]")).click();
			BH_SetUp_TearDown.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			
		} catch (Exception e) {
			REPORTER.catchException(e, "Enter Start Time / End Time");
		}
			
		//Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
		COMMON_METHODS.radioButton(getTestObject("ROL_03"));
		
		//Select Distance from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
		
		//Select any Location from 'Location' drop down
		//COMMON_METHODS.listBoxSelect(getTestObject("OL_89"), getTestData("TD_Location"), "byVisibleText");
		// Select any Location from 'Location' drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_89"),getTestData("TD_Location1"), "byVisibleText");
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		Thread.sleep(7000);
	}
	
	public String EP_ReservationReveiwDetailsInHome() throws IOException, Exception{
	
		String ResNum="";
		String ResStatus="";
	
		COMMON_METHODS.editAField(getTestObject("IH_100"), getTestData("TD_GreeterName"));
		
		COMMON_METHODS.editAField(getTestObject("IH_101"), getTestData("TD_GreetRelation"));
		
		COMMON_METHODS.checkBox(getTestObject("IH_102"), "check");
		
		//Add payment method in 5th step
		this.addPaymentMethod("ManagePaymentMethod");
		
	//Check 'I have read and agree to the Payment Policy' check box present under 'Payment Terms'
	COMMON_METHODS.checkBox(getTestObject("OL_95"), "check");
	
	//Check 'Accept the Cancellation Policy for this reservation' checkbox present under 'Cancellation Policy'
	COMMON_METHODS.radioButton(getTestObject("OL_96"));

	//Click 'Request Reservation'
	COMMON_METHODS.clickElement(getTestObject("OL_97"));

	System.out.println("Reservation # - " + BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1 > span")).getText().trim());
	System.out.println("#################################" + BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1")).getText().trim().toString());

	//Verify the Reservation Number and Status present at the top
	try {
		ResNum = BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1 > span")).getText().trim();
		ResStatus = BH_SetUp_TearDown.driver.findElement(By.cssSelector("span.hdrStatus")).getText().trim();
		REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Confirmed", "Reservation # and Reservation Status : " + ResNum +" "+ ResStatus , "");
		
	} catch (Exception e) {
		REPORTER.catchException(e, "Verify Reservation Confirmed");
	}
	
		return ResNum;
	
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String EP_InHomeReservation(String clientNo, String dt) throws Exception {

		/*String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
		readTestData(getDataTablePath("EP"), "TD_EP5");*/
		
		//readTestData(getDataTablePath("EP"), "TD_EP" + clientNo);
			
		// Verify Employer
		this.EP_verifyEmployer(clientNo);
			
		// Accept policy
		this.EP_AcceptPolicyAndSubmit();
		
		//Create dynamic user name
		String userName = createDyanamicUserData();
					
		// SignUp User
		this.EP_SignUpUser(userName,clientNo,"cbudc");
		
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
		EP_Registration(addCrData3_5Years,"No",clientNo);
		
		// Click the 'Add' link next to the Locations section
		COMMON_METHODS.clickElement(getTestObject("OL_124"));
		
		Thread.sleep(2000);
		// Create locations
		String data[] = { "InHome", "60601", "103 Fox Road", "Flag st", null,
				null, "United States", null };
		this.EP_AddLocation(data);

		Thread.sleep(2000);
		
		/*String userName1 = ReadwritDataFromProps.props.getProperty("client"  + clientNo + ".cbudc"  + clientNo + ".userName");
		String password1 = getTestData("TD_PWD");
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};

		Utility.logout();
		
		//Login to Emp Portal
		 Utility.loginToBUCA(userName1, password1,signInArray);				
		*/
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
				
				
		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));
		
		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");
		
		//Create Care Recipients Reservation
		int HealthStatus[] = {1};
		String selectForReason = getTestData("TD_ReasonForCare");
		EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
		"Yes");
						
		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");
				
		// Step 2 When and where
		Thread.sleep(5000);
		COMMON_METHODS.editAField(getTestObject("OL_85"),dt);//getTestData("TD_Dateofreservation2"));
		Thread.sleep(1000);
		//businessComponents.EP_ReservationWhenandWhere();

		this.ResWhenandWhereLocationInhome();
			
			
		// Step 3 Select Care Options Center based
		//businessComponents.EP_ReservationCareOptions();
			
		//Click 'IN - HomeCare' button at the top of 'Available Care Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_108"));
		Thread.sleep(9000);
			
		//Filling and checking whether the continue button is Enabled and going to the next page.
		this.EP_ReservationInHomeCareOptions("CareOptions");
			
		// Step 4 Verify Info
		this.EP_ReservationVerifyInfoNo();

		// Step 5 ReveiwDetails
		String resNum = this.EP_ReservationReveiwDetailsInHome();
		
		System.out.println("Reservation Number : " + resNum);
		
		
		return resNum;
		//Include cancel functionality
		//Verify usage text and days
			
		}
	
	
	public String EP_CenterBasedReservation(String clientNo, String dt) throws Exception {
		
		//readTestData(getDataTablePath("EP"), "TD_EP" + clientNo);
		
		
		// Verify Employer
		this.EP_verifyEmployer(clientNo);
		
		
		// Accept policy
		this.EP_AcceptPolicyAndSubmit();
		
		//Create dynamic user name
		String userName = createDyanamicUserData();
					
		// SignUp User
		this.EP_SignUpUser(userName,clientNo,"cbudc" + clientNo);
		
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
		EP_Registration(addCrData3_5Years,"No",clientNo);
		
					
		/*String userName1 = ReadwritDataFromProps.props.getProperty("client"  + clientNo + ".cbudc"  + clientNo + ".userName");
		String password1 = getTestData("TD_PWD");
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(userName1, password1, signInArray);
		*/
		//Click 'Reservations' link from top navigation menu
				COMMON_METHODS.clickElement(getTestObject("OL_10"));

				//Click 'Request a New Reservation' link from top navigation menu
				COMMON_METHODS.clickElement(getTestObject("OL_11"));

				//verify User is brought to Step 1 of the reservation
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");

				//Create Care Recipients Reservation
				int HealthStatus[] = {1};
				String selectForReason = getTestData("TD_ReasonForCare");
				EP_ReservationCareRecipients(1, HealthStatus, selectForReason,
				"Yes");
				
				//Verify the wizard moves to step 2.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");

				//Select Date of reservation
				//COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation4"));

				//Create  WhenandWhere Reservation
				String[] careDates = {dt};
				String actions[] = {"Locations","Continue",null};
				EP_ReservationWhenandWhere(careDates,actions);

				//Verify the wizard moves to step 3.
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

				this.EP_ReservationCareOptions();

				//Verify the wizard moves to step 4.	
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");

				this.EP_ReservationVerifyInfoNo();

				Thread.sleep(2000);
				
				//Verify the wizard moves to step 5.	
				COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

				Thread.sleep(3000);
				
				// Step 5 ReveiwDetails
				String resNum = this.EP_ReservationReveiwDetails();
				
				System.out.println("Reservation Number : " + resNum);
				
				
				return resNum;

		
		}
	
	
	/**
	 * Registration for the Employee 
	 * 
	 * @param clientNo - Passing the client number
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_SignUpUser1(String uName,String client, String user) throws IOException, Exception { 
		
        //Getting current client 
        whichClient=getXMLData("Client"+client, "TD_EmployerId"); 
        String fN=null;
        String lN=null;
        if(!user.equalsIgnoreCase("cbresvreg")){
	         fN=getTestData("TD_FirstName");
	         lN=getTestData("TD_LastName");
        }
        else
        {
        	 fN="BH_FN_"+createDyanamicUserData();
	         lN="BH_LN_"+createDyanamicUserData();
        }
        String uE=createDyanamicUserData()+"@gmail.com";
         String keyArray[]={"client"+client+"."+user+".firstName","client"+client+"."+user+".lastName", 
                         "client"+client+"."+user+".userName","client"+client+"."+user+".userEmail"}; 
         String valueArray[]={fN,lN,"BHU"+uName,uE}; 
        
        //Verify Client Name Field is Pre populated 
        COMMON_METHODS.VerifyTextPresent(getTestObject("OL_75"), getXMLData("Client"+client, "TD_ClientName")); 
                        
        //Enter value in First Name 
        COMMON_METHODS.editAField(getTestObject("OL_17"), fN);//getTestData("TD_FirstName")); 
        
        //Enter value in Last Name 
        COMMON_METHODS.editAField(getTestObject("OL_18"), lN);//getTestData("TD_LastName")); 
        
        //Enter value in Username 
        COMMON_METHODS.editAField(getTestObject("OL_19"),"BHU"+uName);// getTestData("TD_Email")); 
        
        //Enter value in Primary Email 
        COMMON_METHODS.editAField(getTestObject("OL_20"),uE); // getTestData("TD_UserID")); 
        
        
        //Enter value in Password 
        COMMON_METHODS.editPasswordField(getTestObject("OL_2"), getTestData("TD_PWD")); 
        
        //Enter value in Confirm Password 
        COMMON_METHODS.editPasswordField(getTestObject("OL_21"), getTestData("TD_PWD")); 
        
        //Click Sign Up 
        COMMON_METHODS.clickElement(getTestObject("OL_22")); 
        
        //Set dynamic data to properties file 
        ReadwritDataFromProps.writeDyanamicData(keyArray, valueArray);
        
  } 
	
	/**
	 * Updating the Care Profile
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_Registration1(String testdata[],String isAC,String client) throws IOException, Exception {
		
		//Click care Profile link
		Utility.clickLink(getTestObject("OL_25"));
		
		//Verify alert text
		this.EP_VerifyAlertText();
		
		//Click on Employee link
		Utility.clickLink(getTestObject("OL_26"));
		
	   //this.EP_VerifyPersonalInformation();
		
		//Update Employee profile
		this.EP_UpdateEmployeeprofile1(client);
		
		//Click 'Add' link present in 'Care Recipients' section
		COMMON_METHODS.clickElement(getTestObject("OL_41"));
		
		//Add Care recipients 
		addCareRecipients1(testdata,"No",client);
		
		//Click 'Add Care Recipients' button
		COMMON_METHODS.clickElement(getTestObject("OL_57"));
		Thread.sleep(5000);	
		//Verify the newly added Care Recipient in present under 'Care Recipients' section
		try {
			String sTemp = BH_SetUp_TearDown.driver.findElement(By.linkText(getTestData("TD_CR_FirstName")+" "+getTestData("TD_CR_LastName"))).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Care Recipient created", "Care Recipient - " + sTemp + " Created" , "");
		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Care Recipient created");
		}
		
		//Click on 'Add' link for Authorized Contacts section
		COMMON_METHODS.clickElement(getTestObject("OL_58"));
		
		EP_AddAuthorizedContacts();
		
		//Click Logout
		//COMMON_METHODS.clickElement(getTestObject("OL_9"));
	}
	
	/**
	 * Updating the Employee Profile in Care profile
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_UpdateEmployeeprofile1(String client) throws IOException, Exception
	{

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
		COMMON_METHODS.editAField(getTestObject("OL_35"), getXMLData("Client"+client,"TD_Zip"));

		//Enter 'Work City' 
		COMMON_METHODS.editAField(getTestObject("OL_36"), getTestData("TD_City"));
		
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("OL_SignUp_03"), false);
		
		//Verify Client Name Field is Pre populated 
        COMMON_METHODS.VerifyTextPresent(getTestObject("OL_SignUp_03"), getXMLData("Client"+client,"TD_ClientName"));

		//Select Work State
		COMMON_METHODS.listBoxSelect(getTestObject("OL_37"), getTestData("TD_State"), "byVisibleText");

		//Enter 'Job Title' 
		COMMON_METHODS.editAField(getTestObject("OL_38"), getTestData("TD_JobTitle"));

		//Select Salary Range
		//if(whichClient!=null && whichClient.equalsIgnoreCase("TCTWO") )
		//COMMON_METHODS.listBoxSelect(getTestObject("OL_39"), getXMLData("Client"+client,"TD_SalaryRange"), "byValue");
		
		//Select Employee Pay code and 8 Week Advantage
		if(whichClient!=null && whichClient.equalsIgnoreCase("TCTHREE") ){
			COMMON_METHODS.listBoxSelect(getTestObject("OL_101"), getXMLData("Client"+client,"TD_PAYCODE"), "byValue");
			COMMON_METHODS.listBoxSelect(getTestObject("OL_102"), getXMLData("Client"+client, "TD_8WeekAdvantage"), "byValue");
		}
		
		//Enter Employee Pay code and select Work Group
		if(whichClient!=null && whichClient.equalsIgnoreCase("TCFOUR") ){
			COMMON_METHODS.editAField(getTestObject("OL_104"), getXMLData("Client"+client, "TD_EMPPAYCODE"));
			COMMON_METHODS.listBoxSelect(getTestObject("OL_105"), getXMLData("Client"+client, "TD_CLIENT4WORKGROUP"), "byValue");
		}
		
		//Enter Employee No and Select whatEverYouAre
		if(whichClient!=null && whichClient.equalsIgnoreCase("TCFIVE") ){
			COMMON_METHODS.editAField(getTestObject("OL_106"), getXMLData("Client"+client, "TD_EMPLOYEENO"));
			COMMON_METHODS.listBoxSelect(getTestObject("OL_107"), getXMLData("Client"+client, "TD_WHATLEVELAREYOU"), "byValue");
		}
		
		//Click 'Update Employee Profile' button
		COMMON_METHODS.clickElement(getTestObject("OL_40"));
		Thread.sleep(9000);
		
		
	}

  /**
	 * Adding multiple Care Recipients in Care profile
	 * 
	 * @author vverimadugu
	 * @version 
	 * @return void
	 * @param 
	 * Creation Date 13-03-2014
	 */
	public void addCareRecipients1(String data[],String isAC,String clientName) throws IOException, Exception
	{

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Getting current client 
		whichClient=getTestData("TD_EmployerId"); 


		//Enter First Name
		if(data[0]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_42"), data[0]);

		//Enter Last Name
		if(data[1]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_43"),data[1]);

		//Select Relationship to Client Employee
		if(data[2]!=null)
			COMMON_METHODS.listBoxSelect(getTestObject("OL_44"), data[2], "byVisibleText");

		//Enter Birth Date
		if(data[3]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_45"), data[3]);

		//Select Gender
		if(data[4]!=null)
			COMMON_METHODS.radioButton(data[4]);

		//Select Food Restrictions
		if(data[5]!=null)
			COMMON_METHODS.radioButton((data[5]));

		//Select Food Allergies
		if(data[6]!=null)
			COMMON_METHODS.radioButton(data[6]);

		//Select Allergies to Medication
		if(data[7]!=null)
			COMMON_METHODS.radioButton(data[7]);

		//Select Other Allergies
		if(data[8]!=null)
			COMMON_METHODS.radioButton(data[8]);

		//Select Diagnosed Special Needs / Medical Conditions
		if(data[9]!=null)
			COMMON_METHODS.radioButton(data[9]);

		//Select Activity Restrictions
		if(data[10]!=null)
			COMMON_METHODS.radioButton(data[10]);

		//Enter Additional Information
		if(data[11]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_53"), data[11]);

		//Select Is Client Employee a Parent or Legal Guardian
		if(data[12]!=null)
			COMMON_METHODS.radioButton(data[12]);

		//Select Custody Issues/ Visitation Orders
		if(data[13]!=null)
			COMMON_METHODS.radioButton(data[13]);

		//Select Where do you primarily need care
		if(data[14]!=null)
			COMMON_METHODS.listBoxSelect(getTestObject("OL_56"), data[14], "byVisibleText");

		//Input Approx. Height
		if(data[19]!=null)
			COMMON_METHODS.editAField(getTestObject("CR_25"), data[19]);

		//Input Approx. weight
		if(data[20]!=null)
			COMMON_METHODS.editAField(getTestObject("CR_26"), data[20]);

		//Enter Regular Care Arrangements
		if(data[15]!=null)
			COMMON_METHODS.editAField(getTestObject("OL_99"), data[15]);

		//Select Toilet Trained
		if(data[16]!=null)
			COMMON_METHODS.radioButton(data[16]);

		//SelectCrawling Trained
		if(data[17]!=null)
			COMMON_METHODS.radioButton(data[17]);

		//Please include the food restriction as well as any relevant details 
		/*if(data[21]!=null)
		COMMON_METHODS.editAField(getTestObject("CARE_Food_RES_NOTES"),data[21]);
		
	    //Please include the allergy as well as symptoms and treatment required
	    if(data[22]!=null)
	    	COMMON_METHODS.editAField(getTestObject("CARE_Food_ALL_NOTES"),data[22]);*/
	    
		//Select Salary Range
		if(whichClient!=null && (whichClient.equalsIgnoreCase("TCTHREE")|| whichClient.equalsIgnoreCase("TCSIX")))
			COMMON_METHODS.listBoxSelect(getTestObject("OL_103"), getXMLData("Client"+clientName,"TD_CENTERLOCATION"), "byVisibleText");
		// COMMON_METHODS.radioButton(acessYes);
		if(isAC.equalsIgnoreCase("Yes")){
			//COMMON_METHODS.listBoxSelect(getTestObject("CR_27"),"Father", "byVisibleText");
			//Select Relationship
			COMMON_METHODS.listBoxSelect(getTestObject("OL_65"), getTestData("TD_Relationship"), "byVisibleText");

			//Select 'Parent/Guardian' to No
			COMMON_METHODS.radioButton(getTestObject("CR_28"));
			//COMMON_METHODS.radioButton(getTestObject("OL_65"));
			
			//Select Emergency Contact
			COMMON_METHODS.checkBox(getTestObject("CR_29"), "check");

			//Select Authorized Pick-Up 
			COMMON_METHODS.checkBox(getTestObject("CR_30"), "check");
		}	
		System.out.println("Exiting - " + methodName);
		
	}
	
	/**
	 * Check clients for only membership
	 * @param String cName
	 * @throws Exception
	 */
	public  boolean isMemberShipClient(String cName) throws  Exception {
		boolean isMemClient=false;
		if(cName!=null &&(cName.equalsIgnoreCase("AllenOveryUK")||cName.equalsIgnoreCase("Chevron")||
		   cName.equalsIgnoreCase("Backup")||cName.equalsIgnoreCase("DRAFTFCB")||
		   cName.equalsIgnoreCase("JPL")||cName.equalsIgnoreCase("lands end")||
		   cName.equalsIgnoreCase("NYL")||cName.equalsIgnoreCase("Reebok")||
		   cName.equalsIgnoreCase("Target")||cName.equalsIgnoreCase("BCG")))
				isMemClient=true;
		
		return isMemClient;
	}
	
	
	
	
	
}
