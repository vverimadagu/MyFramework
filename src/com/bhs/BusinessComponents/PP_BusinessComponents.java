package com.bhs.BusinessComponents;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;

public class PP_BusinessComponents extends INITIALIZE{

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();

	
	/*public void PP_logout() throws IOException, Exception
	{
		//Click Logout
		COMMON_METHODS.clickElement(getTestObject("OL_4"));
	}*/
	
	
	public void PPAddCaregiverBlankPage() throws IOException, Exception {
		  //Verify First Name is Empty
		  		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_06"), " ");
		  		
		  		//Verify Middle Name is Empty
		  		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_07"), " ");
		  		
		  		//Verify Last Name is Empty
		  		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_12"), " ");
		  		
		  		//Select Female Radio Button
		  		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_10"), false);
		  		
		  		//Select Male Radio Button
		  		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_08"), false);
		  		
		  		//Verify Date is Empty
		  		COMMON_METHODS.VerifyTextPresent(getTestObject("PPPOL_13"), " ");
		  		
		  		//CPR/First Aid Trained No
		  		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_14"), false);
		  		
		  		//CPR/First Aid Trained Yes
		  		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_15"), false);
		  		
		  		//Smoker No
		  		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_16"), false);
		  		
		  		//Smoker Yes
		  		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_17"), false);
		  		
		  		//Background Check No
		  		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_18"), false);
		  		
		  		//Background Check Yes
		  		COMMON_METHODS.isElementEnabledDisabled(getTestObject("PPPOL_19"), false);
		    }
	
	
	public void PPAddCaregiver() throws IOException, Exception {
		  //Enter First Name
		  		COMMON_METHODS.editAField(getTestObject("PPPOL_06"), "AutomationTest");
		  		
		  		//Enter Middle Name
		  		COMMON_METHODS.editAField(getTestObject("PPPOL_07"), "D");
		  		
		  		//Enter Last Name 
		  		COMMON_METHODS.editAField(getTestObject("PPPOL_12"), "CareGiver");
		  		
		  	//Select the Gender
				COMMON_METHODS.radioButton(getTestObject("PPPOL_10_F"));
				Thread.sleep(2000);
				//Enter Hire Date
				COMMON_METHODS.editAField(getTestObject("PPPOL_13"), getTestData("TD_Hire_Date"));

				//Click on FirstAid radio button
				COMMON_METHODS.radioButton(getTestObject("PPPOL_14"));

				//Click the Smoker radio button
				COMMON_METHODS.radioButton(getTestObject("PPPOL_16"));

				//Click the Background check radio button
				COMMON_METHODS.radioButton(getTestObject("PPPOL_18"));
				
				//Click on Experience Button
				COMMON_METHODS.clickElement(getTestObject("PPPOL_20"));
				
				//Experience
				COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_21"), "Adults", "byVisibleText");
				
				//Years of Experience
				COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_22"), "1 year", "byVisibleText");
				
				//Enter some Description
				COMMON_METHODS.editAField(getTestObject("PPPOL_23"), getTestData("TD_PP_DescriptionExp"));
				
				//Click on Add button
				COMMON_METHODS.clickElement(getTestObject("PPPOL_24"));
				
				//Click on Languages
				COMMON_METHODS.clickElement(getTestObject("PPPOL_25"));
				
				
				//Enter some Description
				COMMON_METHODS.editAField(getTestObject("PPPOL_26"), getTestData("TD_PP_DescriptionLang"));
				
				//Click on Add button
				COMMON_METHODS.clickElement(getTestObject("PPPOL_27"));
				
				//Click on the Education/Certifications Tab
				COMMON_METHODS.clickElement(getTestObject("PPPOL_28"));
				
				//Education/Certification
				COMMON_METHODS.listBoxSelect(getTestObject("PPPOL_29"), "Caregiver", "byVisibleText");
				
				//Enter Education/Certifications TextBox
				COMMON_METHODS.editAField(getTestObject("PPPOL_30"), getTestData("TD_PP_DescriptionEdu"));
				
				//Click on Add button
				COMMON_METHODS.clickElement(getTestObject("PPPOL_31"));
				
				//Click on the Assessments Tab
				COMMON_METHODS.clickElement(getTestObject("PPPOL_32"));
				
				//Assessment Date
				COMMON_METHODS.editAField(getTestObject("PPPOL_33"), getTestData("TD_PP_AssessmentDate"));
				
				//Assessment Score
				COMMON_METHODS.editAField(getTestObject("PPPOL_34"), getTestData("TD_PP_AssessmentScore"));
				
				//Click on Add button
				COMMON_METHODS.clickElement(getTestObject("PPPOL_35"));
				
				//Click on the Skills Tab
				COMMON_METHODS.clickElement(getTestObject("PPPOL_36"));
				
				//check all the checkboxes in the Skill tab
				businessComponents.PP_SkillSets("0");
				
				//Click on Personality/Interests tab
				COMMON_METHODS.clickElement(getTestObject("PPPOL_59"));

				//Check all the checkboxes in the Personality/Interests tab
				businessComponents.PP_Interests_Personality("0");
				
				
				//Click on the Button Submit Caregiver
				COMMON_METHODS.clickElement(getTestObject("PPPOL_101"));
				
				
				
				
		    }
	
	/**
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void PPAddProviderContact() throws IOException, Exception {
		  
		//Enter First Name
  		COMMON_METHODS.editAField(getTestObject("PPAM_05"), getTestData("TD_PP_CONTACT_FNAME"));
  		
  		//Enter Last Name
  		COMMON_METHODS.editAField(getTestObject("PPAM_06"), getTestData("TD_PP_CONTACT_LNAME"));
  		
  		//Enter Email Address
  		COMMON_METHODS.editAField(getTestObject("PPAM_07"), getTestData("TD_PP_CONTACT_EMAIL"));
  		
  		//Enter Middle Initial
  		COMMON_METHODS.editAField(getTestObject("PPPC_01"), getTestData("TD_PP_CONTACT_MINITIAL"));
  		
  		//Enter Work Phone
  		COMMON_METHODS.editAField(getTestObject("PPPC_02"), getTestData("TD_PP_CONTACT_WPHONE"));
  		
  		//Enter Mobile Phone
  		COMMON_METHODS.editAField(getTestObject("PPPC_03"), getTestData("TD_PP_CONTACT_MPHONE"));
  		
  		//Check the Primary Contact in Contact Type* section
  		COMMON_METHODS.checkBox(getTestObject("PPPC_04"), "check");
  		
  		//Check the  Billing Contact in Contact Type* section
  		COMMON_METHODS.checkBox(getTestObject("PPPC_05"), "check");
  		
  		//Check the   Franchise Owner in Contact Type* section
  		COMMON_METHODS.checkBox(getTestObject("PPPC_06"), "check");
  		
  		//Check the  Score cards in Provider Feature Access* section
  		COMMON_METHODS.checkBox(getTestObject("PPPC_07"), "check");
  		
  		//Check the  Exclusions in Provider Feature Access* section
  		COMMON_METHODS.checkBox(getTestObject("PPPC_08"), "check");
  		
  		//Check the  Service Authorizations in Communication Preferences* section
  		COMMON_METHODS.checkBox(getTestObject("PPPC_09"), "check");
  		
  		//Check the Cancel Search notification in Communication Preferences* section
  		COMMON_METHODS.checkBox(getTestObject("PPPC_10"), "check");
  		
  		//Click on the Button Submit Contact
		COMMON_METHODS.clickElement(getTestObject("PPPC_11"));
				
	}
	
	/** 
	 * This methods will check all the check boxes present in the Skills tab of Provider portal
	 * @author Kiran G
	 * @version 
	 * @return void 
	 * @param 
	 * Creation Date 19-03-2014 
	 *//* 
	public void PP_SkillSets() throws IOException, Exception
	{
		COMMON_METHODS.checkBox(getTestObject("PPMA_118"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_119"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_120"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_121"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_122"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_123"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_124"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_125"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_126"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_127"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_128"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_129"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_130"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_131"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_132"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_133"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_134"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_135"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_136"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_137"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_138"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_139"), "check");
						
	}
	
	
	*//** 
	 * This methods will check all the check boxes present in the Personality/Interests tab of Provider portal
	 * @author Kiran G
	 * @version 
	 * @return void 
	 * @param 
	 * Creation Date 19-03-2014 
	 *//* 
	public void PP_Interests_Personality() throws IOException, Exception
	{
		COMMON_METHODS.checkBox(getTestObject("PPMA_140"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_141"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_142"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_143"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_144"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_145"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_146"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_147"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_148"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_149"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_150"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_151"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_152"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_153"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_154"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_155"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_156"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_157"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_158"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_159"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_160"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_161"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_162"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_163"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_164"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_165"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_166"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_167"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_168"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_169"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_170"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_171"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_172"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_173"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_174"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_175"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_176"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_177"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_178"), "check");
		COMMON_METHODS.checkBox(getTestObject("PPMA_179"), "check");
			
	}
	*/
	public void clickMapLocation(String resvtype) throws Exception
	{
		//Click on Reservations link
		COMMON_METHODS.navigateToMenu(getTestObject("PPCR_06"));
		
		if(resvtype.equalsIgnoreCase("Available Reservations"))
		{
			COMMON_METHODS.clickElement(getTestObject("PPCR_17"));
			//Click on down arrow
			COMMON_METHODS.clickElement(getTestObject("PPCR_141"));
			
			//Click on map location
			COMMON_METHODS.clickElement(getTestObject("PPMap_Location"));
			Thread.sleep(5000);
			
			COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMap_PopUp"));
			COMMON_METHODS.clickElement(getTestObject("PPCR_16"));
					
		}
		else if(resvtype.equalsIgnoreCase("Pending Reservations"))
		{
			COMMON_METHODS.clickElement(getTestObject("PPCR_08"));
			//Click on down arrow
			COMMON_METHODS.clickElement(getTestObject("PPCR_141"));
			
			//Click on map location
			COMMON_METHODS.clickElement(getTestObject("PPMap_Location"));
			Thread.sleep(5000);
			
			COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMap_PopUp"));
			COMMON_METHODS.clickElement(getTestObject("PPCR_16"));
			
		}
		else if(resvtype.equalsIgnoreCase("Scheduled Reservations"))
		{
			COMMON_METHODS.clickElement(getTestObject("PPCR_18"));
			//Click on down arrow
			COMMON_METHODS.clickElement(getTestObject("PPCR_14"));
			
			//Click on map location
			COMMON_METHODS.clickElement(getTestObject("PPCR_15"));
			Thread.sleep(5000);
			
			COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMap_PopUp"));
			COMMON_METHODS.clickElement(getTestObject("CR_CloseMapPopup"));
		}
		else if(resvtype.equalsIgnoreCase("Completed Reservations"))
		{
			COMMON_METHODS.clickElement(getTestObject("PPCR_07"));
			//Click on down arrow
			COMMON_METHODS.clickElement(getTestObject("PPCR_14"));
			
			//Click on map location
			COMMON_METHODS.clickElement(getTestObject("PPCR_15"));
			Thread.sleep(5000);
			
			COMMON_METHODS.verifyElementDisplayed(getTestObject("PPMap_PopUp"));
			COMMON_METHODS.clickElement(getTestObject("CR_CloseMapPopup"));
		}
				
	}
	
	public void viewAuthorization(String resvtype) throws Exception
	{
		//Click on Reservations link
		COMMON_METHODS.navigateToMenu(getTestObject("PPCR_06"));
		
		if(resvtype.equalsIgnoreCase("Scheduled Reservations"))
			COMMON_METHODS.clickElement(getTestObject("PPCR_18"));
		else if(resvtype.equalsIgnoreCase("Completed Reservations"))
			COMMON_METHODS.clickElement(getTestObject("PPCR_07"));
		
		Thread.sleep(10000);
		//Click on down arrow
		COMMON_METHODS.clickElement(getTestObject("PPCR_14"));
		
		//Click on View Authorization
		COMMON_METHODS.clickElement(getTestObject("PPCR_19"));
		
		COMMON_METHODS.SwitchWindow("Provider Authorization - Back-up Care Advantage Provider Portal");
		String title=BH_SetUp_TearDown.driver.getTitle();
		
		if(title.equalsIgnoreCase("Provider Authorization - Back-up Care Advantage Provider Portal"))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Page title "+title+" is displayed","Page title "+title+" is displayed" ,"");
		else
			REPORTER.LogEvent(TestStatus.FAIL, "Verify Page title "+title+" is displayed","Page title "+title+" is not displayed" ,"");
		
		COMMON_METHODS.driver.close();
		
		COMMON_METHODS.switchToNormal();
	}
	
	/* ***************************************************************************************
	 * PURPOSE: Checks AcceptQueue button is displayed and clicks on the AcceptQueue button
	 * RETURN * VALUE: void
	 * AUTHOR: vverimadugu 
	 * CREATION DATE: April 05 2014
	 * 
	 * *********************************************************************
	 * *****************
	 */
	public  void clickAcceptQueue()
			throws IOException {

		try {
			WebElement we = null;
			for (int i = 1; i <= 20; i++) {
				we = COMMON_METHODS.driver.findElement(By
						.xpath("(//a[contains(text(),'Accept Queue')])[" + i
								+ "]"));
				System.out.println("we.isDisplayed==" + we.isDisplayed());
				System.out.println("we.isEnabled==" + we.isEnabled());
				if (we.isDisplayed())
					break;

			}
			we.click();
			Thread.sleep(5000);
			REPORTER.LogEvent(TestStatus.PASS, "Able to click Accept Queue button",
					"Click on Accept Queue button - Successfull".toUpperCase(), "");
		} catch (Exception e) {
			REPORTER.catchException(e,
					"Unable to clcik on Accept Queue button");
		}

	}
	
	/* ***************************************************************************************
	 * PURPOSE: Checks click Not Interested link is displayed and clicks on the Not Interested link
	 * RETURN * VALUE: void
	 * AUTHOR: vverimadugu 
	 * CREATION DATE: April 05 2014
	 * 
	 * *********************************************************************
	 * *****************
	 */
	public  void clickNotInterestedLink()
			throws IOException {

		try {
			WebElement we = null;
			for (int i = 1; i <= 20; i++) {
				we = COMMON_METHODS.driver.findElement(By
						.xpath("(//a[contains(text(),'Not Interested')])[" + i
								+ "]"));
				
				if (we.isDisplayed())
					break;

			}
			we.click();
			Thread.sleep(5000);
			REPORTER.LogEvent(TestStatus.PASS, "Able to click Not Interested link",
					"Click on NotInterested link - Successfull".toUpperCase(), "");
		} catch (Exception e) {
			REPORTER.catchException(e,
					"Unable to clcik on Not Interested Link");
		}

	}
}