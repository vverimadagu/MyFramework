package com.bhs.util;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class GridWithWebdriver {
	public WebDriver driver;
	public String browser;

	/*@Parameters( { "browser", "url" })*/
	@BeforeClass
	public void setup(/*String browser, String URL*/) throws MalformedURLException {
		DesiredCapabilities capability = null;
		/*this.browser=browser;
		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("firefox");
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}*/
		/*if ("iexplore".equalsIgnoreCase("iexplore")) {
			System.out.println("iexplore");
			File file = new File(System.getProperty("user.dir") + "\\IEDriver\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("iexplore");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}*/
		/*if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("chrome");
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}*/
		//driver = new RemoteWebDriver(new URL(URL), capability);
		//driver=new InternetExplorerDriver(capability);
		//driver = new FirefoxDriver();
		//System.out.println("driver===="+browser+driver);
		driver=new FirefoxDriver();
		driver.navigate().to("http://wikisend.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); 
	}

	//@Test
	public void test_first() throws InterruptedException {
		
		Thread.sleep(3000);
		System.out.println("driver===="+browser+driver);
		WebElement search_editbox = driver.findElement(By.name("q"));
		WebElement search_button = driver.findElement(By.name("btnG"));
		search_editbox.clear();
		search_editbox.sendKeys("first");
		search_button.click();
		Thread.sleep(3000);
		brokenImages();
		brokenLinks();
	}

	//@Test
	public void test_second() throws InterruptedException {
		System.out.println("driver===="+browser+driver);
		WebElement search_editbox = driver.findElement(By.name("q"));
		WebElement search_button = driver.findElement(By.name("btnG"));
		search_editbox.clear();
		search_editbox.sendKeys("second");
		search_button.click();
		Thread.sleep(3000);
	}
	
	@Test
	public void uploadDocs() throws InterruptedException {
		driver.findElement(By.id("uploadfield")).click();
 		Thread.sleep(4000);
		
		try {
	         String[] commands = new String[]{};
	         commands = new String[]{"D:\\autoit\\uloadfile.exe"}; //location of the autoit executable
	         Runtime.getRuntime().exec(commands);
	         Thread.sleep(4000);
	         
	         driver.findElement(By.id("upload_but")).click();
	         Thread.sleep(4000);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      
		
	}
	

	@AfterClass
	public void tearDown() {
		System.out.println("driver===="+browser+driver);
		driver.quit();
	}
	
	/*
	 * @param workingDays
	 * 
	 * @return
	 */
	public  String brokenImages() {
		DefaultHttpClient hc=new DefaultHttpClient();
		HttpResponse hp=null;
		
		try {
			List<WebElement> images =driver.findElements(By
					.tagName("img"));
			
			for (WebElement bi : images) {
				System.out.println("URL===="+bi.getAttribute("src"));
				hp=hc.execute(new HttpGet(bi.getAttribute("src")));
				if(hp.getStatusLine().getStatusCode()!=200){
					System.out.println("Broken Images==="+bi.getAttribute("src"));
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/*
	 * @param workingDays
	 * 
	 * @return
	 */
	public  String brokenLinks() {
		URL url=null;
		HttpURLConnection urlCon=null;
	
		
		try {
			List <WebElement> linkElements=driver.findElements(By.tagName("a"));
			
			for(WebElement linkUrl:linkElements){
				url=new URL(linkUrl.getAttribute("href"));
				urlCon=(HttpURLConnection)url.openConnection();
				urlCon.setRequestMethod("GET");
				urlCon.connect();
				if(urlCon.getResponseCode()!=404)
				System.out.println("Not Broken Links==="+linkUrl.getAttribute("href"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}