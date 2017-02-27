package com.bhs.scripts.employeeportal;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.bhs.util.INITIALIZE.TestStatus;

public class EP_CCReservation extends INITIALIZE {


	@Test()
	public void EP_CCReservationTest(ITestContext TC) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		//Verify Employee name and Client name Displayed
		if(EP_SetUp_TearDown.driver.findElement(By.cssSelector("li.loginAcct")).getText().trim().equalsIgnoreCase("Ravi Naidu Benefit - (ACC_Stab_Test 2)")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Employee name and Client name Displayed", "Employee name and Client name Displayed", "");   							
		}
		
		//Click "Reservations"
		COMMON_METHODS.clickElement(getTestObject("OL_10"));
		
		
		
		/*Click "Reservations"
		Click "Request a New Reservation"
		Verify that Care Recipients are listed
		Select a 'Reason for Care'
		Select any Care Recipient, by checking check box next to his name
		Select Health Status of the selected Care Recipient
		Click 'Continue'
		Verify the Care recipients selected in Step 1 are present in 'Select The Date and Time For Care' section
		Select Date of reservation
		Select 'Start Time'
		Select 'End Time'
		Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
		Select Distance from drop down
		Select any Location from 'Location' drop down
		Click 'Continue'
		Click 'Center - based Care' button at the top of 'Available Care Options' section
		Verify 'Search Criteria' section has all the  correct information as per selections in previous reservation steps
		Select '1st Choice' from 'Set My Preference' drop down for any BH Center in the list
		Select 'Acceptable' from 'Set My Preference' drop down for all other Center in the list
		Click 'Continue' button
		Verify all the Care Recipients selected in Step 1 are displayed
		Enter any value in 'Care / Special Instructions' field for all the Care Recipients
		Select 'No' radio button for 'Any changes to profile details like allergies, etc.?'
		Click 'Continue'
		Check 'I have read and agree to the Payment Policy' check box present under 'Payment Terms'
		Check 'Accept the Cancellation Policy for this reservation' checkbox present under 'Cancellation Policy'
		Click 'Request Reservation'
		Verify the Reservation Number and Status present at the top
		Verify Usage for the Care Recipient present under 'Total Utilization for Care Request' section at the bottom right of the page*/

		
	}

}
