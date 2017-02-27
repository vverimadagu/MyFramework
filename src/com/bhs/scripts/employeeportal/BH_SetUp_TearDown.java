package com.bhs.scripts.employeeportal;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class BH_SetUp_TearDown extends INITIALIZE {
	
	public static WebDriver driver;
	public Selenium selenium;
	public long vScriptStartTime = Calendar.getInstance().getTimeInMillis();
	
	COMMON_METHODS CM = null;
	
	public static String scriptName = null;
	String browserName = null;
	String Env = "CSC QA";
	
	
	// Get the script start time
	//vScriptStartTime = Calendar.getInstance().getTimeInMillis();
	
	@BeforeSuite
	public void AtBeforeSuite(ITestContext ic) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside Method - " + methodName);
						
		scriptName = ic.getCurrentXmlTest().getName().toString();
						
		//Close any Open IEDriverServer instances
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		
		//Close any Open iexplore instances
		Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
		
		//Close any Open firefox instances
		//Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		
		//Close any Open firefox instances
		//Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
		
		//Loading test Objects and Data for Employee Portal
		readTestObject(getDataTablePath("EP"), "TO_EP");
		readTestData(getDataTablePath("EP"), "TD_EP");
		
		//Loading test Objects and Data for Care Center Portal
		//readTestObject(getDataTablePath("CCP"), "TO_CCP");
		//readTestData(getDataTablePath("CCP"), "TD_CCP");
		
		//Loading test Objects and Data for Provider Portal
		//readTestObject(getDataTablePath("PP"), "TO_PP");
		//readTestData(getDataTablePath("PP"), "TD_PP");
		
	}
	
	
	@BeforeTest
	public void AtBeforeTest(ITestContext ic) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// CREATE AN INSTANCE OF REPORTER CLASS BY PASSING CLASS NAME(SDP_E2E_Sanity_DO_Script) AND DATE/TIME
		//scriptName = getClass().getSimpleName();
		
		scriptName = ic.getCurrentXmlTest().getName().toString();
		new REPORTER(scriptName,REPORTER.getDateFormat(REPORTER.vDatetype8));

		// CREATE AN INSTANCE OF INTERNETEXPLORERDRIVER
		File file = new File(System.getProperty("user.dir") + "\\IEDriver\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

		DesiredCapabilities Dsrdcaps = DesiredCapabilities.internetExplorer();
		Dsrdcaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	//	BH_SetUp_TearDown.driver = new InternetExplorerDriver(Dsrdcaps);

		/*File file = new File("C:/IE_Chrome_DriverServer/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		this.driver = new ChromeDriver();*/

		BH_SetUp_TearDown.driver = new FirefoxDriver();
		//this.driver = new InternetExplorerDriver();

		// CREATE AN INSTANCE OF WebDriverBackedSelenium
		selenium = new WebDriverBackedSelenium(driver, COMMON_METHODS.GoogleURL);
		System.out.println("Exiting Method - " + methodName.toUpperCase());

		// CREATE AN INSTANCE OF Common_Methods
		CM = new COMMON_METHODS(BH_SetUp_TearDown.driver, "off");

		//Code to get the Browser Name and Version
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		
		
		// UPDATE EXCEL, NOTEPAD AND HTML REPORT WITH THE BROWSER NAME
		REPORTER.updateReports(updateValue.bName, caps.getBrowserName().toString().toUpperCase()+" / "+caps.getVersion().toString(), "");
		browserName = caps.getBrowserName().toString().toUpperCase()+" / "+caps.getVersion().toString();

		REPORTER.updateReports(updateValue.Env, Env, "");

		methodName=null;

	}
	


	@AfterTest
	public void AtAfterTest(ITestContext ic) throws IOException, Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		String finalScriptStatus;
		
		System.out.println("---------------------"+ic.getFailedTests().toString());
		String sTemp = ic.getFailedTests().toString();
		
		REPORTER.updateReports(updateValue.tEndTime, "", "");
		REPORTER.updateReports(updateValue.execTime,formatIntoHHMMSS(Calendar.getInstance().getTimeInMillis() - vScriptStartTime).toString(), "");
		REPORTER.updateReports(updateValue.totalSteps, "", "");
		
		if(!sTemp.contains("FAILURE")){
			finalScriptStatus = "PASS";
			REPORTER.updateReports(updateValue.execStatus, "", finalScriptStatus);
			REPORTER.updateReports(updateValue.failedStepNo, "", finalScriptStatus);
			//REPORTER.takeScreenShot(finalScriptStatus);
		
			
			
		}else{
			finalScriptStatus = "FAIL";
			REPORTER.updateReports(updateValue.execStatus, "", finalScriptStatus);
			REPORTER.updateReports(updateValue.failedStepNo, "", finalScriptStatus);
		}
		REPORTER.LogEvent(TestStatus.INFO, "", "", "-------->[ End Of Script Execution ]<--------");
		
		//Rename the result log
		REPORTER.RenameResultLog(finalScriptStatus);
		
		
		//String mailFormat = EMAIL.HTMLMailFormat(scriptName, getTestData("TD_ENV").toString(), browserName, REPORTER.ScriptStartTime, REPORTER.ScriptEndTime, REPORTER.ScriptexecTime, REPORTER.ScripttotalSteps, finalScriptStatus);
		
		//EMAIL.SendEmail(mailFormat);
	
		// Reset The Hash Map
		//clearHashMap();
		REPORTER.stepCount=1;
		//driver.manage().deleteAllCookies();
		driver.quit();

	}
	
	@AfterSuite
	public void AtAfterSuite(ITestContext ic) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
						
		scriptName = ic.getCurrentXmlTest().getName().toString();
		clearHashMap();				
		
	}
	

}
