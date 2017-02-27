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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.bhs.util.COMMON_METHODS;
import com.bhs.util.INITIALIZE;
import com.bhs.util.REPORTER;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class EP_SetUp_TearDown extends INITIALIZE {
	
	public static WebDriver driver;
	public Selenium selenium;
	public long vScriptStartTime = Calendar.getInstance().getTimeInMillis();
	
	COMMON_METHODS CM = null;
	
	public static String scriptName = null;
	String browserName = null;
	
	
	// Get the script start time
	//vScriptStartTime = Calendar.getInstance().getTimeInMillis();
	
	@BeforeSuite
	public void AtBeforeSuite(ITestContext ic) throws IOException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
		
		scriptName = ic.getCurrentXmlTest().getName().toString();
						
		//Close any Open IEDriverServer instances
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		
		//Close any Open iexplore instances
		Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
		
		//Close any Open firefox instances
		//Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		
		//Close any Open firefox instances
		//Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");

	}
	
	
	@BeforeTest
	public void AtBeforeTest() throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);

		// CREATE AN INSTANCE OF REPORTER CLASS BY PASSING CLASS NAME(SDP_E2E_Sanity_DO_Script) AND DATE/TIME
		//scriptName = getClass().getSimpleName();
		
		new REPORTER(scriptName,REPORTER.getDateFormat(REPORTER.vDatetype8));

		// CREATE AN INSTANCE OF INTERNETEXPLORERDRIVER
	//	File file = new File(System.getProperty("user.dir") + "\\IEDriver\\IEDriverServer.exe");
		//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

		//DesiredCapabilities Dsrdcaps = DesiredCapabilities.internetExplorer();
	//	Dsrdcaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		//EP_SetUp_TearDown.driver = new InternetExplorerDriver(Dsrdcaps);

		/*File file = new File("C:/IE_Chrome_DriverServer/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		this.driver = new ChromeDriver();*/

		EP_SetUp_TearDown.driver = new FirefoxDriver();
		//this.driver = new InternetExplorerDriver();

		// CREATE AN INSTANCE OF WebDriverBackedSelenium
		selenium = new WebDriverBackedSelenium(driver, COMMON_METHODS.GoogleURL);
		System.out.println("Exiting Method - " + methodName.toUpperCase());

		// CREATE AN INSTANCE OF Common_Methods
		CM = new COMMON_METHODS(EP_SetUp_TearDown.driver, "off");

		//Code to get the Browser Name and Version
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		
		readTestData(getDataTablePath("EP"), "TD_EP");

		// UPDATE EXCEL, NOTEPAD AND HTML REPORT WITH THE BROWSER NAME
		REPORTER.updateReports(updateValue.bName, caps.getBrowserName().toString().toUpperCase()+" / "+caps.getVersion().toString(), "");
		browserName = caps.getBrowserName().toString().toUpperCase()+" / "+caps.getVersion().toString();

		REPORTER.updateReports(updateValue.Env, getTestData("TD_ENV"), "");

		methodName=null;

	}
	
	//@Test()
	public void EP_Launch_Test(ITestContext TC) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Inside - " + methodName);
				
		//Launch Broswer with EP URL
		COMMON_METHODS.openBrowser(getTestData("TD_URL"));
		Thread.sleep(5000);

		//Verify Login Page displayed
		if(driver.getTitle().equalsIgnoreCase("Login")){
			REPORTER.LogEvent(TestStatus.PASS, "Verify Login Page Displayed", "Login Page Displayed", "");
		}

				
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
			REPORTER.takeScreenShot(finalScriptStatus);
			//Click Logout
			//COMMON_METHODS.clickElement(getTestObject("OL_9"));
			REPORTER.LogEvent(TestStatus.INFO, "", "", "-------->[ End Of Script Execution ]<--------");
			
		}else{
			finalScriptStatus = "FAIL";
			REPORTER.updateReports(updateValue.execStatus, "", finalScriptStatus);
			REPORTER.updateReports(updateValue.failedStepNo, "", finalScriptStatus);
		}
		
		//Rename the result log
		REPORTER.RenameResultLog(finalScriptStatus);
		
		
		//String mailFormat = EMAIL.HTMLMailFormat(scriptName, getTestData("TD_ENV").toString(), browserName, REPORTER.ScriptStartTime, REPORTER.ScriptEndTime, REPORTER.ScriptexecTime, REPORTER.ScripttotalSteps, finalScriptStatus);
		
		//EMAIL.SendEmail(mailFormat);
	
		// Reset The Hash Map
		clearHashMap();
		
		//driver.manage().deleteAllCookies();
		driver.quit();

	}
	

}
