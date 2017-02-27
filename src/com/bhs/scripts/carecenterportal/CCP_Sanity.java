package com.bhs.scripts.carecenterportal;

import java.io.IOException;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.scripts.employeeportal.EP_Reservation_Test;
import com.bhs.util.INITIALIZE;
import com.bhs.util.Utility;

public class CCP_Sanity extends INITIALIZE{

	EP_BusinessComponents businessComponents = new EP_BusinessComponents();
	EP_Reservation_Test EPRT = new EP_Reservation_Test();
	String[] dd = null;
	
	@Test()
	public void CCP_Sanity_test() throws IOException, Exception {
		
		// READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
		// SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
	//	readTestObject(getDataTablePath("EP"), "TO_EP");
		readTestData(getDataTablePath("EP"), "TD_EP");

		/*		
		//Launch EP
		COMMON_METHODS.openBrowser(getTestData("TD_URL"));
		Thread.sleep(5000);
		
		//Login To EP
		businessComponents.LoginEmployeeportal(getTestData("TD_UserID"), getTestData("TD_PWD"));*/
		
		//Launch EP
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		
		//Login to Emp Portal
		String signInArray[]={ getTestObject("OL_1"),getTestObject("OL_2"),getTestObject("OL_3")};
		Utility.loginToBUCA(getTestData("TD_UserID"), getTestData("TD_PWD"),signInArray);
		
		readTestData(getDataTablePath("EP"), "TD_EP1");
		
		//Click 'Reservations' link from top navigation menu
		//COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		//-----------------------------------------------------------------------------------
		
		
		
		//---------------------------------------------------------------------------------------------
		

/*		//Click 'Request a New Reservation' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_11"));

		//verify User is brought to Step 1 of the reservation
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_01"), "Who Needs Care and Why?");

		//Add Care Recipients
		businessComponents.EP_ReservationCareRecipients();

		//Verify the wizard moves to step 2.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_02"), "When and Where Do You Need Care?");

		//Select Date of reservation
		COMMON_METHODS.editAField(getTestObject("OL_85"), getTestData("TD_Dateofreservation"));
		
		dd = getTestData("TD_Dateofreservation").split("/");

		//Enter Date and Time
		businessComponents.EP_ReservationWhenandWhere();

		//Verify the wizard moves to step 3.
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_04"), "Available Care Options");

		//Enter Care Options
		businessComponents.EP_ReservationCareOptions();

		//Verify the wizard moves to step 4.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_05"), "Care Instructions & Verify Information");

		//Verify Reservation Information
		businessComponents.EP_ReservationVerifyInfoNo();

		//Verify the wizard moves to step 5.	
		COMMON_METHODS.VerifyTextPresent(getTestObject("ROL_06"), "Review Reservation and Payment Details");

		//Review Reservation Details
		businessComponents.EP_ReservationReveiwDetails();

		//Log Out of EP
		businessComponents.logout();
	

		// READ TEST DATA REQUIRED FOR THE SCRIPT FROM THE DATA SHEETS(SPREAD
		// SHEETS) LOCATED AT - C:\ROOT FOLDER\SUITE NAME\TestData
		readTestData(getDataTablePath("CCP"), "TD_CCP");
		readTestObject(getDataTablePath("CCP"), "TO_CCP");

		//Launch CCP
		COMMON_METHODS.openBrowser(getTestData("CCP_TD_URL"));
		Thread.sleep(5000);

		//Login to CCP
		CCP_BusinessComponents.CCP_Login();

		//Change Location
		CCP_BusinessComponents.CCP_ChangeLocation();
		
		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_8"));
		
		//CLick Calendar
		System.out.println("Src Value is - " + BH_SetUp_TearDown.driver.findElement(By.id("calendarImageCareDate")).getAttribute("src").toString().trim());
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_9"));
				
		//Click reservation Day
		BH_SetUp_TearDown.driver.findElement(By.linkText(dd[1])).click();
		
		//CLick the Expand Link for Preschool
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_10"));
		
		//List all the HTML Tag Names with Select
		List<WebElement> myListboxes = BH_SetUp_TearDown.driver.findElements(By.tagName("select"));
		
		//Get The Count Of List Boxes 
		int Listboxes = myListboxes.size();
		
		try {
			for (int i = 0; i < Listboxes; i++) {

				if(myListboxes.get(i).isDisplayed()){

					List<WebElement> OptionList = myListboxes.get(i).findElements(By.tagName("option"));
					int OptionCount = OptionList.size();

					for (int j = 0; j < OptionCount; j++) {

						String OptionValue = OptionList.get(j).getAttribute("selected");
						if(OptionValue!=null){
							String actualVal = OptionList.get(j).getText();	
							System.out.println("Selected Option Value is - " + actualVal);
							if(actualVal.equalsIgnoreCase("Pend")){
								String Obj_ID = myListboxes.get(i).getAttribute("id");
								new Select(BH_SetUp_TearDown.driver.findElement(By.id(Obj_ID))).selectByVisibleText("Conf");
							}

						}

					}

				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Click 'Submit' on the pop up
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_11"));

		//close the pop up
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_12"));

		//Click Logout
		COMMON_METHODS.clickElement(getTestObject("CCP_OL_13"));

		//Launch EP
		COMMON_METHODS.openBrowser(getTestData("TD_URL"));
		Thread.sleep(5000);

		//Login To EP
		businessComponents.LoginEmployeeportal(getTestData("TD_UserID"), getTestData("TD_PWD"));

		//readTestData(getDataTablePath("EP"), "TD_EP1");

		//Click 'Reservations' link from top navigation menu
		COMMON_METHODS.clickElement(getTestObject("OL_10"));*/


	}
	
	
}
