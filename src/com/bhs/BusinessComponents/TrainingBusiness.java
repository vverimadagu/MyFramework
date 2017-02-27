package com.bhs.BusinessComponents;

import java.io.IOException;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;

public class TrainingBusiness extends INITIALIZE {
	
	
	public void search() throws IOException, Exception{
		
		COMMON_METHODS.editAField(getTestObject("EP_Search"), getTestData("TD_SearchTxt"));
		//driver.findElement(By.name("btnG")).click();
		COMMON_METHODS.clickElement(getTestObject("EP_SearchButton"));
		
		//driver.findElement(By.linkText("Selenium - Web Browser Automation")).click();
		
		COMMON_METHODS.clickElement(getTestObject("EP_SeleniumLink"));
		
		Thread.sleep(5000);
		
	}

}
