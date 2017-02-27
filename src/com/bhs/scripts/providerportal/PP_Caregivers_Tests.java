package com.bhs.scripts.providerportal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.BusinessComponents.PP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.Utility;
import com.bhs.util.INITIALIZE.TestStatus;

public class PP_Caregivers_Tests  extends INITIALIZE {

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	PP_BusinessComponents ppbubusinessComponents=new PP_BusinessComponents();

	//Reading Test Objects from Data excel 
	/*static{
		try{
			readTestObject(getDataTablePath("PP"), "TO_PP");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/

	/**
	 * Test Case #10520: 
	 * BUCA - Provider - Caregivers - ensure 'View By' field has all dropdown options and functions correctly
	 * TFS ID:23583:BUCA - BUCA - Provider Portal- Add Caregivers - Verify the addition of Caregivers based on the entry of Basic Information
	 */
	@Test
	public void PP_Caregivers_ViewByDropDown() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_EP5");
		////readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		//this.loginPPPortal();

		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_02"),"Active");

		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_02"),"Inactive");

		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_02"),"Suspended");

		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_02"),"Enhanced Screened");

		// Select Active care giver
		COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_02"), "Active", "byVisibleText");
		Thread.sleep(10000);

		//Verify the title of the care giver	10520
		String temp = COMMON_METHODS.getText(getTestObject("PPPOL_03"));
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_03"), temp);

		//Verify the Status of the care giver
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_04"), "Status: Active");

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiver();


		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * Test Case #10522: 
	 * BUCA - Provider - Caregivers - ensure user is able to update existing Caregivers
	 * TFS ID:20829:PP -Caregivers: Ability to edit caregiver records
	 */

	@Test 
	public void PP_Caregivers_UpdateExistingCaregivers() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		Utility.launchBrowser(getTestData("TD_PP_URL"));
		/*String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/

		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));
		Thread.sleep(5000);
		// Select Active care giver
		COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_02"), "Active", "byVisibleText");
		Thread.sleep(15000);

		//Verify the title of the care giver
		//COMMON_METHODS.VerifyTextPresent(getTestObject("POL_03"), "Test Caregiver");
		String temp = COMMON_METHODS.getText(getTestObject("PPPOL_03"));
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_03"), temp);

		//Verify the Status of the care giver
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_04"), "Status: Active");

		//Select the active care giver.
		COMMON_METHODS.clickElement(getTestObject("PPPOL_05"));

		Thread.sleep(4000);
		//Enter Some Text in the Middle Name.		
		COMMON_METHODS.editAField(getTestObject("PPPOL_06"), "AutomationMiddle");

		//Enter Some Text in the Middle Name.		
		COMMON_METHODS.editAField(getTestObject("PPPOL_07"), "D");

		//Select male Radio Button
		//boolean selection = COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_10"), false); 

		COMMON_METHODS.radioButton(getTestObject("PPPOL_08"));

		//Submit changes Button click
		COMMON_METHODS.clickElement(getTestObject("PPPOL_09"));
		Thread.sleep(10000);

		//Verify text AutomationMiddle Caregiver is present
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_03"), "AutomationMiddle Caregiver");


		//Select the active care giver.
		COMMON_METHODS.clickElement(getTestObject("PPPOL_05"));
		Thread.sleep(6000);

		//Enter Some Text in the Middle Name.		
		COMMON_METHODS.editAField(getTestObject("PPPOL_06"), "AutomationMiddle");

		//Enter Some Text in the Middle Name.		
		COMMON_METHODS.editAField(getTestObject("PPPOL_07"), "D");

		//Click on Experience Button
		COMMON_METHODS.clickElement(getTestObject("PPPOL_20"));

		//Years of Experience
		COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_135"), "2 years", "byVisibleText");

		//Click on Add button
		COMMON_METHODS.clickElement(getTestObject("PPPOL_24"));

		//Click on Languages
		COMMON_METHODS.clickElement(getTestObject("PPPOL_25"));

		//Enter some Description
		COMMON_METHODS.editAField(getTestObject("PPPOL_126"), getTestData("TD_PP_Feedback_PP"));

		//Click on Add button
		COMMON_METHODS.clickElement(getTestObject("PPPOL_27"));

		//Click on the Education/Certifications Tab
		COMMON_METHODS.clickElement(getTestObject("PPPOL_28"));

		//Education/Certification
		COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_129"), "Caregiver", "byVisibleText");

		//Enter Education/Certifications TextBox
		COMMON_METHODS.editAField(getTestObject("PPPOL_130"), getTestData("TD_PP_Feedback_PP"));

		//Click on Add button
		COMMON_METHODS.clickElement(getTestObject("PPPOL_31"));

		//Click on the Assessments Tab
		COMMON_METHODS.clickElement(getTestObject("PPPOL_32"));

		//Assessment Date
		COMMON_METHODS.editAField(getTestObject("PPPOL_133"), PP_Caregivers_Tests.getPastDate(1)/*getTestData("TD_PP_AssessmentDate")*/);

		//Assessment Score
		COMMON_METHODS.editAField(getTestObject("PPPOL_134"), "130");

		//Click on Add button
		COMMON_METHODS.clickElement(getTestObject("PPPOL_35"));

		//Click on the Skills Tab
		COMMON_METHODS.clickElement(getTestObject("PPPOL_36"));

		//check all the checkboxes in the Skill tab
		businessComponents.PP_SkillSets("0");

		//Uncheck any 2 Chechboxes
	/*	COMMON_METHODS.checkBox(getTestObject("PPMA_138"), "uncheck");
		COMMON_METHODS.checkBox(getTestObject("PPMA_139"), "uncheck");*/
		
		COMMON_METHODS.driver.findElement(By.id("CaregiverList_0__ProviderCaregiverSkill_Instructing")).click();
		COMMON_METHODS.driver.findElement(By.id("CaregiverList_0__ProviderCaregiverSkill_CustomerService")).click();
		//Click on Personality/Interests tab
		COMMON_METHODS.clickElement(getTestObject("PPPOL_59"));

		//Check all the checkboxes in the Personality/Interests tab
		//businessComponents.PP_Interests_Personality();

		//Uncheck any 2 Chechboxes
		//COMMON_METHODS.checkBox(getTestObject("PPMA_176"), "uncheck");
		//COMMON_METHODS.checkBox(getTestObject("PPMA_177"), "uncheck");

		//Submit changes Button click
		COMMON_METHODS.clickElement(getTestObject("PPPOL_09"));

		Thread.sleep(5000);
		//COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_03"), "AutomationMiddle Caregiver");


		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}



	/**
	 * Test Case #13039: 
	 * BUCA - Provider - Caregivers - Add Caregivers - ensure user is brought to a blank form when page loads
	 * TFS ID:18461:BUCA - Provider - Caregivers - Add Caregivers - ensure the enhanced screened field is left blank by default when adding a caregiver
	 */


	@Test
	public void PP_Caregivers_AddCaregiversBlankPage() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/

		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiverBlankPage();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiver();

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/**
	 * Test Case #13041: 
	 * BUCA - Provider - Caregivers - Add Caregivers - ensure page is loaded correctly (UX doc)
	 */

	@Test
	public void PP_Caregivers_AddCaregiversUXDOC() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");
		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);
		 */
		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiverBlankPage();

		//Click on Experience Button
		COMMON_METHODS.clickElement(getTestObject("PPPOL_20"));

		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Adults");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Alzheimer");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Autism");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Dementia");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Infants");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Other");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Preschool");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"School Age");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Seniors");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Special Needs");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Teens");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Toddlers");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_21"),"Young Adults");


		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"Less than 1 Year");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"1 year");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"2 years");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"3 years");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"4 years");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"5 years");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"6 years");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"7 years");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"8 years");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"9 years");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_22"),"More than 10 years");

		//Verify Description Textbox is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_23"), " ");


		//Verify Experience Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_24"), true);

		//Click on Languages
		COMMON_METHODS.clickElement(getTestObject("PPPOL_25"));

		//Verify Language TextBox is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_26"), " ");

		//Verify Experience Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_27"), true);

		//Click on the Education/Certifications Tab
		COMMON_METHODS.clickElement(getTestObject("PPPOL_28"));

		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"C.N.A.");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"Caregiver");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"Companion");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"HHA");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"LPN");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"Nanny");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"PCA");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"PCP");
		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_29"),"RN");

		//Verify Education/Certifications TextBox is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_30"), " ");

		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_31"), true);

		//Click on the Assessments Tab
		COMMON_METHODS.clickElement(getTestObject("PPPOL_32"));

		//Verify Education/Certifications TextBox is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_33"), " ");

		//Verify Education/Certifications TextBox is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_34"), " ");

		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_35"), true);

		//Click on the Assessments Tab
		COMMON_METHODS.clickElement(getTestObject("PPPOL_36"));

		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_37"), false);

		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_38"), false);

		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_39"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_40"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_41"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_42"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_43"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_44"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_45"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_46"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_47"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_48"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_49"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_50"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_51"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_52"), false);
		//Verify Education/Certifications Add Button Enabled
		//COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_53"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_54"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_55"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_56"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_57"), false);
		//Verify Education/Certifications Add Button Enabled
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_58"), false);

		//Click on the Personality/Interests Tab
		COMMON_METHODS.clickElement(getTestObject("PPPOL_59"));

		//Energetic Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_60"), false);

		//Creative Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_61"), false);

		//SoftSpoken Check Box 
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_62"), false);

		//Imaginative Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_63"), false);

		//Soothing Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_64"), false);

		//GoodListener Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_65"), false);

		//Empathetic Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_66"), false);

		//Flexible Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_67"), false);

		//Compassionate Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_68"), false);

		//Understanding Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_69"), false);

		//Adventurous Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_70"), false);

		//Conscientious Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_71"), false);

		//Discreet Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_72"), false);

		//Observant Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_73"), false);

		//Independent Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_74"), false);

		//Optimistic Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_75"), false);

		//Intelligent Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_76"), false);

		//Charming Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_77"), false);

		//Precise Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_78"), false);

		//Confident Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_79"), false);

		//Encouraging Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_80"), false);

		//Reliable Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_81"), false);

		//Exuberant Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_82"), false);

		//Helpful Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_83"), false);

		//OutdoorSports Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_84"), false);

		//Reading Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_85"), false);

		//Scarpbooking Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_86"), false);

		//Cooking Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_87"), false);

		//Theater Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_88"), false);

		//Choir Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_89"), false);

		//Quilting Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_90"), false);

		//Knitting Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_91"), false);

		//Crocheting Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_92"), false);

		//Sewing Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_93"), false);

		//PartyPlanning Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_94"), false);

		//ArtsAndCrafts Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_95"), false);

		//BoardGames Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_96"), false);

		//Music Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_97"), false);

		//Writing  Check box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_98"), false);

		//Video Games Check Box
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_99"), false);

		//Cancel button present
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_100"), true);

		//Submit Caregiver button
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_101"), true);

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}



	/**
	 * Test Case #13040:
	 *  BUCA - Provider - Caregivers - Add Caregivers - ensure if background check is 'yes', a datepicker field appears
	 */	



	@Test
	public void PP_Caregivers_AddCaregiversBGYDP() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		COMMON_METHODS.radioButton(getTestObject("PPPOL_19"));

		//Verify Education/Certifications TextBox is Empty
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_102"), " ");

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/**
	 * 
Test Case #13044: BUCA - Provider - Caregivers - Add Caregivers - ensure Hire Date field can not allow future dates
	 */	



	@Test
	public void PP_AddCaregivers_HireDatefutureDateNotAllowed() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("PPPOL_13"), getTestData("TD_PP_Dateofreservation1"));

		//Click on the Button Submit Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_101"));

		//verify the error message
		//COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_103"), "HireDate cannot be in the future.");
		//COMMON_METHODS.VerifyTextPresent(getTestObject("MA_69"), "HireDate cannot be in the future.");
		if(COMMON_METHODS.getText(getTestObject("MA_69")).contains("HireDate cannot be in the future."))
		{
			REPORTER.LogEvent(TestStatus.PASS, "The Error is displayed", "The Error is displayed", "");
		}
		else

		{
			REPORTER.LogEvent(TestStatus.FAIL, "The Error is displayed", "The Error is NOT displayed", "");
		}

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}



	/**
	 * BUCA - Provider - Caregivers - ensure a 'Request to Deactivate' is sent to CRM
	 * TFS ID: 10538
	 */
	@Test
	public void PP_Deactivate_CareGiver() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/

		this.loginPPPortal();

		//Click on Care Giver link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));

		Thread.sleep(4000);
		//Click on existing Caregiver to edit
		COMMON_METHODS.clickElement(getTestObject("PPMA_82"));
		Thread.sleep(4000);

		//Click on Request to Deactivate caregiver
		COMMON_METHODS.clickElement(getTestObject("PPMA_83"));
		Thread.sleep(4000);

		//Select a reason from the dropdown for deactivating
		COMMON_METHODS.listBoxSelect(getTestObject("PPMA_84"), "Other Caregiver Issue", "byVisibleText");

		//Input the Notes in the box
		COMMON_METHODS.editAField(getTestObject("PPMA_85"), "Test");
		Thread.sleep(1000);		

		//Click on submit button
		COMMON_METHODS.clickElement(getTestObject("PPMA_86"));	

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);		

	}

	/**
	 * BUCA - Provider - Caregivers - ensure 'Download Training Guide' link functions as desired
	 * TFS ID: 13053
	 */
	@Test
	public void PP_CareGiver_TrainingGuide() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Care Giver link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));

		//Verify the Download Link of Training Guide
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPMA_87"), "Download Training Guide");

		// Logout from 'Employee Portal'
		//Utility.logout();		

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);		

	}



	/**
	 * Test Case #12877: 
	 * BUCA - Provider - Caregivers - Add Caregivers - ensure user is able to use Cancel when Adding or Editing a Caregiver
	 * TFS ID:20827:PP - Caregivers: Ability to add caregiver (manually)
	 */	



	@Test
	public void PP_AddCaregivers_CancelButton() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		//Enter the First Name
		COMMON_METHODS.editAField(getTestObject("PPPOL_06"), getTestData("TD_PP_CG_FirstName"));

		//Enter the Middle Name
		COMMON_METHODS.editAField(getTestObject("PPPOL_07"), getTestData("TD_PP_CG_MiddleName"));

		//Enter the last Name
		COMMON_METHODS.editAField(getTestObject("PPPOL_12"), getTestData("TD_PP_CG_LastName"));

		//Snelect Female Radio Button
		COMMON_METHODS.radioButton(getTestObject("PPPOL_10_F"));

		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("PPPOL_13"), getTestData("TD_Hire_Date"));


		//CPR/First Aid Trained No
		COMMON_METHODS.radioButton(getTestObject("PPPOL_14"));

		//Smoker No
		COMMON_METHODS.radioButton(getTestObject("PPPOL_16"));

		//Background Check No
		COMMON_METHODS.radioButton(getTestObject("PPPOL_18"));

		//Click on the Cancel Button 
		COMMON_METHODS.clickElement(getTestObject("PPPOL_100"));


		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiverBlankPage();

		//Click on the Cancel Button 
		COMMON_METHODS.clickElement(getTestObject("PPPOL_100"));

		//Select Active care giver
		COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_02"), "Inactive", "byVisibleText");
		Thread.sleep(4000);

		// Select Active care giver
		COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_02"), "Active", "byVisibleText");
		Thread.sleep(10000);

		//Select the active care giver.
		COMMON_METHODS.clickElement(getTestObject("PPPOL_05"));
		Thread.sleep(5000);

		//Enter Some Text in the First Name.		
		COMMON_METHODS.editAField(getTestObject("PPPOL_06"), "AutomationMiddle");

		//Enter Some Text in the Middle Name.		
		COMMON_METHODS.editAField(getTestObject("PPPOL_07"), "D");

		//Select male Radio Button
		COMMON_METHODS.radioButton(getTestObject("PPPOL_08"));

		//CPR/First Aid Trained yes
		COMMON_METHODS.radioButton(getTestObject("PPPOL_15"));

		//Smoker yes
		COMMON_METHODS.radioButton(getTestObject("PPPOL_17"));

		//Background Check yes
		COMMON_METHODS.radioButton(getTestObject("PPPOL_19"));

		//Click on the Cancel Button 
		COMMON_METHODS.clickElement(getTestObject("PPPOL_100"));

		//Select the active care giver.
		COMMON_METHODS.clickElement(getTestObject("PPPOL_05"));

		//verify the First Name
		//String temp = COMMON_METHODS.getText(getTestObject("POL_03"));
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_03"), "AutomationMiddle Caregiver");
		//COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_06"), "Test");

		//verify the Middle Name
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_07"), " ");

		//verify the Last Name
		//COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_12"), "Caregiver");


		//CPR/First Aid Trained yes is not selected
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_15"), false);

		//Smoker yes is not selected
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_17"), false);

		//Background yes is not selected
		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_19"), false);

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiver();

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);


		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}



	/**
	 * Test Case #13046: 
	 * BUCA - Provider - Caregivers - Add Caregivers - user is able to cancel from adding multiple caregivers
	 * TFS ID:7069: BUCA - Provider - Caregivers - Add Caregivers - ensure user is able to add Caregivers
	 */	



	@Test
	public void PP_AddCaregivers_CancelMultipleCareGiver() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		/// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));
		Thread.sleep(10000);

		//Click Upload Multiple Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_104"));

		//verify Upload Caregivers Title
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_105"), "Upload Caregivers");

		//Click on the Cancel Button 
		COMMON_METHODS.clickElement(getTestObject("PPPOL_100"));

		//verify user is able to view the main caregivers page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_105"), "Manage Caregivers");

		COMMON_METHODS.isOptionPresentInListBox(getTestObject("PPPOL_02"),"Active");

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiver();

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}



	/**
	 * Test Case #8872: 
	 * BUCA - Provider - Caregivers - Add Caregivers - ensure all required fields are indeed required
	 * TFS ID:23584:BUCA - Provider Portal- Add Caregivers - Verify the mandotory fields and mandatory tabs while adding a new Caregiver
	 */	



	@Test
	public void PP_AddCaregivers_AllFiledsRequired() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		//Click on the Button Submit Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_101"));

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_106"), "The HireDate field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_107"), "The FirstName field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_108"), "The LastName field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_109"), "Gender required");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_110"), "The FirstAidTrained field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_111"), "The Smoker field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_112"), "The BackgroundCheck field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_113"), "An experience is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_114"), "An assessment is required.");

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiver();

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/**
	 * Test Case #8573: 
	 * BUCA - Provider - Caregivers - Add Caregivers - when adding a caregiver, ensure 'tabbed' options function correctly
	 */

	@Test
	public void PP_Caregivers_AddCaregiversTabbedOptions() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiver();

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/**
	 * Test Case #8574:
	 * BUCA - Provider - Resources - Ensure Caregiver Guidelines sublinks are displayed
	 */

	@Test
	public void PP_Resources_Caregiverguidelines() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Resources
		COMMON_METHODS.clickElement(getTestObject("PPPOL_115"));

		//Click on the Caregiver Guidelines
		COMMON_METHODS.clickElement(getTestObject("PPPOL_116"));

		//verify the Caregiver Guidelines link open properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_117"), "Caregiver Guidelines: Qualifications");

		//Click on the Expectations link
		COMMON_METHODS.clickElement(getTestObject("PPPOL_118"));

		//verify the Expectations page load properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_117"), "Caregiver Guidelines: Expectations");

		//Click on the Training link
		COMMON_METHODS.clickElement(getTestObject("PPPOL_119"));

		//verify the Training page load properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_117"), "Caregiver Guidelines: Training");

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}


	/**
	 * Test Case Test Case #8577: 
	 * BUCA - Provider - Ensure Program policies sublinks are displayed
	 */

	@Test
	public void PP_Resources_ProgramPolicies() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Resources
		COMMON_METHODS.clickElement(getTestObject("PPPOL_115"));

		//Click on the Program policies
		COMMON_METHODS.clickElement(getTestObject("PPPOL_120"));

		//verify the Caregiver Guidelines link open properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_117"), "Program Policies: Use of Bright Horizons's Brand");

		//Click on the Expectations link
		COMMON_METHODS.clickElement(getTestObject("PPPOL_121"));

		//verify the Expectations page load properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_117"), "Program Policies: Infant Back to Sleep");

		//Click on the Training link
		COMMON_METHODS.clickElement(getTestObject("PPPOL_122"));

		//verify the Training page load properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_117"), "Program Policies: Background Screening");

		//Click on the Training link
		COMMON_METHODS.clickElement(getTestObject("PPPOL_123"));

		//verify the Training page load properly
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_117"), "Program Policies: Care Guidelines");

		// Logout from 'Employee Portal'
		//Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * BUCA - Provider - Caregivers - Add Caregivers - when adding caregiver, 
	 * load test the tabbed options at the bottom of the page (add 20+ for each tab)
	 * TFS ID: 10518
	 */
	@Test
	public void PP_Multiple_Caregiver() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP5"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Care Giver link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));

		//Click on Add a Caregiver button
		COMMON_METHODS.clickElement(getTestObject("PPMA_88"));

		//Input Text for Firt name
		COMMON_METHODS.editAField(getTestObject("PPMA_89"), "Test");

		//Input text for middle name
		COMMON_METHODS.editAField(getTestObject("PPMA_90"), "T");

		//Input Text for Last name
		COMMON_METHODS.editAField(getTestObject("PPMA_91"), "Test");

		//Select the Gender
		COMMON_METHODS.radioButton(getTestObject("PPPOL_10_F"));

		//Enter Hire Date
		COMMON_METHODS.editAField(getTestObject("PPMA_181"), getTestData("TD_Hire_Date"));

		//Click on FirstAid radio button
		COMMON_METHODS.radioButton(getTestObject("PPMA_104"));

		//Click the Smoker radio button
		COMMON_METHODS.radioButton(getTestObject("PPMA_105"));

		//Click the Background check radio button
		COMMON_METHODS.radioButton(getTestObject("PPMA_106"));

		//Click on Experience tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_107"));

		//Add 21 Experiences
		Random rand = new Random(); 
		int iRandNumber; 
		for(int i=1;i<=21;i++)
		{
			iRandNumber = rand.nextInt(9) + 1;
			COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_21"),"#"+Integer.toString(iRandNumber), "byIndex");
			COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_22"),"#"+Integer.toString(iRandNumber), "byIndex");
			COMMON_METHODS.editAField(getTestObject("PPMA_108"), "Test"+i);
			COMMON_METHODS.clickElement(getTestObject("PPMA_109"));
		}

		//Click on Languages tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_110"));

		//Add 21 Languages
		for(int i=1;i<=21;i++)
		{
			COMMON_METHODS.editAField(getTestObject("PPMA_115"), "Test"+i);
			COMMON_METHODS.clickElement(getTestObject("PPMA_109"));
		}

		//Click on Education/Certifications tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_111"));
		//Add 21 Certifications
		for(int i=1;i<=21;i++)
		{
			iRandNumber = rand.nextInt(7) + 1;
			COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_29"),"#"+Integer.toString(iRandNumber), "byIndex");
			COMMON_METHODS.editAField(getTestObject("PPMA_116"), "Test"+i);
			COMMON_METHODS.clickElement(getTestObject("PPMA_109"));
		}

		//Click on Asessments tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_112"));

		//Add 21 Assessments
		for(int i=1;i<=21;i++)
		{
			COMMON_METHODS.editAField(getTestObject("PPMA_182"), PP_Caregivers_Tests.getPastDate(i));
			String str = Integer.toString(i);
			COMMON_METHODS.editAField(getTestObject("PPMA_117"),str+10);
			COMMON_METHODS.clickElement(getTestObject("PPMA_109"));

		}

		//Click on Skills tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_113"));

		//Check all the checkboxes present in the Skills tab
		businessComponents.PP_SkillSets("0");

		//Click on Personality/Interests tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_114"));

		//Check all the checkboxes in the Personality/Interests tab
		businessComponents.PP_Interests_Personality("0");

		//Click on Submit button of Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPMA_180"));

		// Logout from 'Employee Portal'
		//Utility.logout();		

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}

	/**
	 * 
	 * @param iAge
	 * @return
	 */
	public static String getPastDate(int iAge){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date myDate = new Date(System.currentTimeMillis());
		Date oneDayBefore = new Date(myDate.getTime() - (iAge * 24 * 60 * 60 * 1000));    
		return dateFormat.format(oneDayBefore);
	}

	/**
	 * BUCA - Provider - Caregivers - ensure pagination control functions as desired 
	 * TFS ID: 13052
	 */
	@Test
	public void PP_Caregiver_Pagination() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Care Giver link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));

		//Click on "2" button on the page
		COMMON_METHODS.clickElement(getTestObject("PPMA_192"));

		//Click on "Previous" button on the Page
		COMMON_METHODS.clickElement(getTestObject("PPMA_193"));

		//Click on "Next" button on the page
		COMMON_METHODS.clickElement(getTestObject("PPMA_194"));

		// Logout from 'Employee Portal'
		//Utility.logout();  

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	/**
	 * BUCA - Provider - Caregivers - Update Caregivers - when updating caregiver, 
	 * load test the tabbed options at the bottom of the page (add 20+ for each tab)
	 * TFS ID: 10519
	 */
	@Test
	public void PP_Multiple_Update_Caregiver() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		//Click on Care Giver link
		COMMON_METHODS.clickElement(getTestObject("PPMA_81"));

		//Click on Arrow mark of already existing Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPMA_195"));

		//Click on Experience tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_107"));

		//Add 21 Experiences
		for(int i=1;i<=21;i++)
		{
			COMMON_METHODS.editAField(getTestObject("PPMA_196"), "Test"+i);
			COMMON_METHODS.clickElement(getTestObject("PPMA_109"));
		}

		//Click on Languages tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_110"));

		//Add 21 Languages
		for(int i=1;i<=21;i++)
		{
			COMMON_METHODS.editAField(getTestObject("PPMA_197"), "Test"+i);
			COMMON_METHODS.clickElement(getTestObject("PPMA_109"));
		}

		//Click on Education/Certifications tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_111"));

		//Add 21 Certifications
		for(int i=1;i<=21;i++)
		{
			COMMON_METHODS.editAField(getTestObject("PPMA_198"), "Test"+i);
			COMMON_METHODS.clickElement(getTestObject("PPMA_109"));
		}

		//Click on Asessments tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_112"));

		//Add 21 Assessments
		for(int i=1;i<=21;i++)
		{
			COMMON_METHODS.editAField(getTestObject("PPMA_199"), PP_Caregivers_Tests.getPastDate(i));
			String str = Integer.toString(i);
			COMMON_METHODS.editAField(getTestObject("PPMA_200"),str+10);
			COMMON_METHODS.clickElement(getTestObject("PPMA_109"));

		}

		//Click on Skills tab
		COMMON_METHODS.clickElement(getTestObject("PPMA_113"));
        Thread.sleep(5000);
		//Check all the checkboxes present in the Skills tab
		businessComponents.PP_SkillSets("4");

		//Click on Personality/Interests tab
		//COMMON_METHODS.clickElement(getTestObject("PPMA_114"));

		//Check all the checkboxes in the Personality/Interests tab
	//	businessComponents.PP_Interests_Personality();

		//Click on Submit changes button of Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_155"));

		// Logout from 'Employee Portal'
		Utility.logout();  

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}
	/* TFS ID:12865
	 * @author: Sanjeev Singh
	 * @CreationDate: 02/04/2014
	 *   BUCA - Provider - Caregivers - ensure 'request to RE-activate' link functions correctly
	 */


	@Test
	public void PP_CaregiversRequestToREActivate() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Create a center based reservation
		//EP_CBAndInHomeReservations_Tests cbih = new EP_CBAndInHomeReservations_Tests();
		//cbih.EP_SignUpAndCBReservationTest();

		// Launch the browser for provider portal
		//readTestData(getDataTablePath("EP"), "TD_EP5");
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal();

		// Navigate Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Verify Resrvation Page
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPVerify_Loc"), "MANAGE CAREGIVERS");

		//
		COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_02"), "Inactive", "byVisibleText");
		Thread.sleep(5000);

		COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_02"), "Active", "byVisibleText");
		Thread.sleep(10000);

		//open an existing caregiver for edit		
		// Get the index of the class with Pending Reservations count > 0
		/*		int i =0;
		List<WebElement> lwe = COMMON_METHODS.driver.findElements(By.xpath("//div[@class='formModHdrSm')]"));
		for(WebElement we: lwe){
			 i = i + 1;
			 String roomClassText = we.getText();
			 if(roomClassText.contains(ReadwritDataFromProps.props.getProperty("client2.cbudc2.firstName") + " " + ReadwritDataFromProps.props.getProperty("client2.cbudc2.lastName"))){
				 break;
			 }
		 }*/

		// Click the arrow
		COMMON_METHODS.driver.findElement(By.xpath("//html/body/div[1]/div[4]/div/div/div/div[3]/ul/li[1]/div[1]/form/div[1]/ul/li[3]")).click();

		//user has opened an existing caregiver	
		COMMON_METHODS.isElementPresent("PPs_Submit", "id");
		/*if(COMMON_METHODS.driver.findElement(By.id("s_Submit")).isDisplayed()){
			REPORTER.LogEvent(TestStatus.PASS, "Verify opened an existing caregiver", "existing caregiver Opened","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify opened an existing caregiver", "existing caregiver Opened Failed","");
		}*/

		// Click Submit
		COMMON_METHODS.clickElement(getTestObject("PPs_Submit"));
		Thread.sleep(5000);
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPVerify_Loc"), "Manage Caregivers");

		/*if(COMMON_METHODS.driver.findElement(By.linkText("s_Req_to_reactivate")).isDisplayed()!= true){
			REPORTER.LogEvent(TestStatus.PASS, "Verify page refreshed with the request to deactivate fields minimized", "Verified successfully","");
		}
		else{
			REPORTER.LogEvent(TestStatus.FAIL, "Verify page refreshed with the request to deactivate fields minimized", "Verification Failed","");
		}*/
		// Logout from 'Employee Portal'
		//Utility.logout();  

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);
	}


	/**
	 * requirement iD #21737: 
	 * BUCA - Provider Portal - 1.3.4. Add a Caregiver: Submit Caregiver - Verify the functionality of selects 
	 * "Submit Caregiver," under "Add a Caregiver" section
	 */

	@Test
	public void PP_Caregivers_SubmitCaregive() throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// Read test data for based on client 1
		//readTestData(getDataTablePath("PP"), "TD_PP");

		// Launch the browser
		/*Utility.launchBrowser(getTestData("TD_PP_URL"));
		String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")};

		// Login Provider Portal
		Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"), signInArray);*/
		this.loginPPPortal(); 

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		ppbubusinessComponents.PPAddCaregiver();

		//Click on Caregivers
		COMMON_METHODS.clickElement(getTestObject("PPPOL_01"));

		//Click on the Button Add a Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_11"));

		//Click on the Button Submit Caregiver
		COMMON_METHODS.clickElement(getTestObject("PPPOL_101"));

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_106"), "The HireDate field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_107"), "The FirstName field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_108"), "The LastName field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_109"), "Gender required");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_110"), "The FirstAidTrained field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_111"), "The Smoker field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_112"), "The BackgroundCheck field is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_113"), "An experience is required.");

		//verify the error message
		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_114"), "An assessment is required.");


		// Logout from 'Employee Portal'
		Utility.logout();

		//Log to reports
		COMMON_METHODS.logToReportAfterPass(methodName);

	}

	private void loginPPPortal() throws IOException, Exception { 

		if(COMMON_METHODS.driver.getTitle().equalsIgnoreCase("Login - Back-up Care Advantage Provider Portal")){ 
			//Verify Login Page displayed 

			//Login to Emp Portal 
			String signInArray[]={ getTestObject("PPOL_1"),getTestObject("PPOL_2"),getTestObject("PPOL_3")}; 
			Utility.loginToBUCA(getTestData("TD_PP_UserID"), getTestData("TD_PP_PWD"),signInArray);
			//Utility.loginToBUCA(ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"resstep3"+"."+"userName"), getTestData("TD_PWD"),signInArray); 
			/*businessComponents.LoginEmployeeportal( 
                                ReadwritDataFromProps.props.getProperty("client"+ClientNo+"."+"userName"), 
                                getTestData("TD_PWD"));*/ 
		} 
	} 



}
