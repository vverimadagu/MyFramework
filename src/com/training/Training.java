package com.training;

import org.testng.annotations.Test;

import com.bhs.BusinessComponents.TrainingBusiness;
import com.bhs.scripts.employeeportal.BH_SetUp_TearDown;
import com.bhs.util.Utility;

public class Training extends BH_SetUp_TearDown {
	
	@Test
	public void loginToGoogle () throws Exception {
		
		TrainingBusiness tbusiness=new TrainingBusiness();
		Utility.launchBrowser("https://www.google.com");
		/*COMMON_METHODS.editAField1("lst-ib111", "Selenium.org");
		//driver.findElement(By.id("lst-ib")).sendKeys("Selenium.org");
		driver.findElement(By.name("btnG")).click();
		driver.findElement(By.linkText("Selenium - Web Browser Automation")).click();
		Thread.sleep(5000);*/
		tbusiness.search();
		
		System.out.println("");
		
	}
	
	
	
}
