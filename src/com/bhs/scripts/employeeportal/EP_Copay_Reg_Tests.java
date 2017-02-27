package com.bhs.scripts.employeeportal;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bhs.BusinessComponents.EP_BusinessComponents;
import com.bhs.util.COMMON_METHODS;
import com.bhs.util.REPORTER;
import com.bhs.util.ReadwritDataFromProps;
import com.bhs.util.Utility;

public class EP_Copay_Reg_Tests extends BH_SetUp_TearDown{
	EP_BusinessComponents businessComponents=new EP_BusinessComponents();
	
	@Test(dataProvider="dataProviderCoPay")
	public void EP_AddCRAndDoCBReservation(String clientName, String userName,
			String password, String reservationType, String careRecipient1, String careRecipient2,
			String careRecipient3, String careRecipient4, String careRecipient5, String dateOfCare,
			String startTime, String endTime, String careLocation, String expectedUsage,
			String firstChoiceCenter, String expectedCopay, String Result, String ReservationNo,
			String actualUsage, String actualCopay)
			throws Exception {

		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		System.out.println("Inside - " + methodName);
	//	System.out.println("Inside - " + param2.toString());
		//String userName =""; /*ReadwritDataFromProps.props.getProperty(employer + ".cbresvreg.userName");
		//String password ="BHAuto2014#"; getTestData("TD_PWD");
		// Launch Browser with EP Browser
		Utility.launchBrowser(getTestData("TD_EP_URL"));
		// Login in to EP
		String signInArray[] = { getTestObject("OL_1"), getTestObject("OL_2"),
				getTestObject("OL_3") };
		Utility.loginToBUCA(userName, password, signInArray);
		Thread.sleep(2000);
		
		// Test data for Step 1 CareRecipients
		
		String crData[] = {careRecipient1,careRecipient2,careRecipient3,careRecipient4,careRecipient5 };
		String selectForReason = getTestData("TD_ReasonForCare");

		
		// Navigate to My First Reservation
		businessComponents.EP_NavigateToReservation();
		
		//Step1 Reservation
		this.EP_ReservationCareRecipients(crData,
				selectForReason, "Yes");

		// Create WhenandWhere Reservation
		String[] careDates = { dateOfCare };
		String actions[] = { null, null, null };
		this.EP_ReservationWhenandWhere(careDates, actions);
		Thread.sleep(2000);
		
		//Select 'Start Time'
		COMMON_METHODS.clickElement(getTestObject("OL_86"));

		businessComponents.setStartEndTime(startTime);
		
		BH_SetUp_TearDown.driver.findElement(By.xpath("//button[@type='button'][2]")).click();
		
		//Select 'End Time'
		COMMON_METHODS.clickElement(getTestObject("OL_87"));
		
		businessComponents.setStartEndTime(endTime);
		
		BH_SetUp_TearDown.driver.findElement(By.xpath("//button[@type='button'][2]")).click();
		
		Thread.sleep(2000);
			
		//Select 'At/Near a Location' radio button from 'Search Type' in 'Select The Location For Care' section
		COMMON_METHODS.radioButton(getTestObject("ROL_03"));
		
		//Select Distance from drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_88"), getTestData("TD_Distance"), "byVisibleText");
		
		//Select any Location from 'Location' drop down
		COMMON_METHODS.listBoxSelect(getTestObject("OL_89"), careLocation, "byVisibleText");
		
		//Click 'Continue'
		COMMON_METHODS.clickElement(getTestObject("OL_84"));
		
		Thread.sleep(7000);
		if(reservationType.equalsIgnoreCase("CB")){
		// Click 'Center - based Care' button at the top of 'Available Care
		// Options' section
		COMMON_METHODS.clickElement(getTestObject("OL_90"));
		Thread.sleep(5000);

		//To close the 'No Centers Found pop up'
		try {
			boolean isElementPresent = COMMON_METHODS.driver.findElements(
					By.xpath("//div[10]/div/a/span")).size() != 0;
			System.out.println("isElementPresent==" + isElementPresent);
			if (isElementPresent) {
				COMMON_METHODS.clickElement(getTestObject("EP_ModalContinue"));
				Thread.sleep(5000);
				REPORTER.LogEvent(TestStatus.PASS,
						"No Centers Found pop up is closed",
						"No Centers Found pop up is closed", "");
			}
		} catch (Exception e) {
			REPORTER.catchException(e, "Error in No Centers Found pop up ");
		}
		

		// Step 4 Verify Info
		businessComponents.EP_ReservationVerifyInfoNo();

		// Step 5 ReveiwDetails
		String resNum = EP_ReservationReveiwDetails(expectedCopay, expectedUsage);
		// Write INHome reservation to data properties file
		ReadwritDataFromProps.props.setProperty(clientName
				+ ".cbresvregcopay.CBRNresv", resNum);
		}
		
		
		if(reservationType.equalsIgnoreCase("IH")){
			
			
			
			// Filling and checking whether the continue button is Enabled and going
			// to the next page.
			businessComponents.EP_ReservationInHomeCareOptions("CareOptions");

			// Step 4 Verify Info
			businessComponents.EP_ReservationVerifyInfoNo();

			// Step 5 ReveiwDetails
			String resNum1 = this.EP_ReservationReveiwDetailsInHome(expectedCopay, expectedUsage);

			// Write INHome reservation to data properties file
			ReadwritDataFromProps.writeDyanamicData(clientName
					+ ".cbresvregcopay.IHResv", resNum1);
			}
		
		

		// Logout
		Utility.logout();
		Thread.sleep(9000);
		// Log to Reports
		COMMON_METHODS.logToReportAfterPass(methodName);

		
	}
	Object[][] data ;
	@DataProvider(name="dataProviderCoPay")
	public Object [][] coPayTestDataProvider() throws Exception {

		Workbook wb=null;
		Sheet sheet=null;
		
		try{
			wb=Workbook.getWorkbook(new File(System.getProperty("user.dir")+"\\TestData\\DATA_EP.xls"));
			sheet=wb.getSheet("TD_CoPay");
			data=new Object[sheet.getRows()-1][sheet.getColumns()];
			int k=0;
			for(int i=1;i<sheet.getRows();i++,k++){
				
				Cell[] cell=sheet.getRow(i);
				for(int j=0;j<sheet.getColumns();j++){
					if(cell[j].getContents()!=null){
						data[k][j]=cell[j].getContents().toString().trim();
					
				}
					else
						break;
			}
			}
			
		
		}
		
		catch(Exception e ){
			
			String vException = e.getMessage().toString();
			e.printStackTrace();
			REPORTER.LogEvent(TestStatus.FAIL, "Check Data File EP_TestData.xml"
					, (vException.substring(
							("TD_CoPay".length()) + 2, (vException.length()) - 1))
							.toUpperCase(),"");
		}
	
		
		return data;
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
	public void EP_ReservationCareRecipients(String crData[],
			String selectForReason, String continueNext) throws IOException,
			Exception {

		// Select a 'Reason for Care'
		if (selectForReason != null && !selectForReason.equals(""))
			COMMON_METHODS.listBoxSelect(getTestObject("OL_81"),
					selectForReason, "byVisibleText");

		// Select any Care Recipient, by checking check box next to name
		for (int i = 0; i < crData.length; i++) {
			if (crData[i] != null && !crData[i].equals("")) {
				String cr1[] = crData[i].split("-");
				if (cr1.length > 0) {
					String locator = "//div[contains(text(),'" + cr1[0]
							+ "')]/../../li/input";
					String healthLocator = "//div[contains(text(),'" + cr1[0]
							+ "')]/../../li[3]/div/input[" + cr1[1] + "]";
					if (COMMON_METHODS.isElementPresent(locator, "xpath")) {
						// Select any Care Recipient, by checking check box next
						// to
						// name
						COMMON_METHODS.driver.findElement(By.xpath(locator))
								.click();
						// Select Health Status of the selected Care Recipient
						COMMON_METHODS.driver.findElement(
								By.xpath(healthLocator)).click();

					} else {
						REPORTER.LogEvent(TestStatus.FAIL, "Object '" + locator
								+ "' not Present", "ObjId '" + locator
								+ "' is not displayed", "");
						break;
					}
				}
			}
		}

		// Click 'Continue'
		if (continueNext.equalsIgnoreCase("yes"))
			COMMON_METHODS.clickElement(getTestObject("OL_84"));

	}
	
	public String EP_ReservationReveiwDetailsInHome(String expectedCopay, String expectedUsage) throws IOException,
			Exception {

		COMMON_METHODS.editAField(getTestObject("IH_100"),
				getTestData("TD_GreeterName"));

		COMMON_METHODS.editAField(getTestObject("IH_101"),
				getTestData("TD_GreetRelation"));

		COMMON_METHODS.checkBox(getTestObject("IH_102"), "check");

		// Check 'I have read and agree to the Payment Policy' check box present
		// under 'Payment Terms'
		COMMON_METHODS.checkBox(getTestObject("OL_95"), "check");

		// Check 'Accept the Cancellation Policy for this reservation' checkbox
		// present under 'Cancellation Policy'
		COMMON_METHODS.radioButton(getTestObject("OL_96"));
		
		//verify Copay 
		businessComponents.EP_CoPayPaymentInformation(expectedCopay);

		// Verify usage text and days
		String usageDays = COMMON_METHODS.getText(getTestObject("RS_29"));
		
		if (usageDays.contains(expectedUsage))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Days is Correct",
					"Usage Days is verified", "");
		else
			REPORTER
					.LogEvent(TestStatus.FAIL,
							"Expected Value= 8.00 Hours", "Actual="
									+ usageDays, "");

		// Click 'Request Reservation'
		COMMON_METHODS.clickElement(getTestObject("OL_97"));

		System.out.println("Reservation # - "
				+ BH_SetUp_TearDown.driver.findElement(
						By.cssSelector("h1 > span")).getText().trim());
		System.out.println("#################################"
				+ BH_SetUp_TearDown.driver.findElement(By.cssSelector("h1"))
						.getText().trim().toString());

		// Verify the Reservation Number and Status present at the top
		String resNum = "";
		try {
			resNum = BH_SetUp_TearDown.driver.findElement(
					By.cssSelector("h1 > span")).getText().trim();
			String ResStatus = BH_SetUp_TearDown.driver.findElement(
					By.cssSelector("span.hdrStatus")).getText().trim();
			REPORTER.LogEvent(TestStatus.PASS, "Verify Reservation Confirmed",
					"Reservation # and Reservation Status : " + resNum + " "
							+ ResStatus, "");

		} catch (Exception e) {
			REPORTER.catchException(e, "Verify Reservation Confirmed");
		}

		return resNum;

	}
	
	/**
	 * When and Where for Reservation
	 * @throws IOException
	 * @throws Exception
	 */
	public void EP_ReservationWhenandWhere(String[] careDates,String actions[]) throws IOException, Exception {
		
			try {
				
				
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
	
public String EP_ReservationReveiwDetails(String expectedCopay,String expectedUsage) throws IOException, Exception {
		
		//Check 'I have read and agree to the Payment Policy' check box present under 'Payment Terms'
		COMMON_METHODS.checkBox(getTestObject("OL_95"), "check");
		
		//Check 'Accept the Cancellation Policy for this reservation' checkbox present under 'Cancellation Policy'
		COMMON_METHODS.radioButton(getTestObject("OL_96"));
		
		//verify Copay 
		businessComponents.EP_CoPayPaymentInformation(expectedCopay);

		// Verify usage text and days
		String usageDays = COMMON_METHODS.getText(getTestObject("RS_29"));
		
		if (usageDays.contains(expectedUsage))
			REPORTER.LogEvent(TestStatus.PASS, "Verify Days is Correct",
					"Usage Days is verified", "");
		else
			REPORTER
					.LogEvent(TestStatus.FAIL,
							"Expected Value= 8.00 Hours", "Actual="
									+ usageDays, "");
		
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
	
	/*public class Login {

	    private static WebDriver driver;
	    XcelParserTestNGLogin login1 = new XcelParserTestNGLogin();
	    Object[][] data1;

	    public Login() throws IOException, InterruptedException {
	        FileInputStream fis = new FileInputStream("Data//LoginPage.xls");
	        XcelParserTestNGLogin login1 = new XcelParserTestNGLogin(fis, "Login");

	        //this.data1 = login1.loadFromSpreadsheet(fis, "Login");
	    }

	    @BeforeClass
	    public void test() throws Exception {
	        System.setProperty("webdriver.chrome.driver",
	                "C:\\Chrome\\chromedriver_win_26.0.1383.0\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.get("Any Url");
	    }
	    @DataProvider
	     public Object[][] dp() throws IOException {
	        //login1.fileName = "Data//Login.xls";
	        //login1.sheetName = "Sheet1";
	        FileInputStream fis = new FileInputStream("Data//LoginPage.xls");
	        String sheetName = "Login";
	        login1.loadFromSpreadsheet(fis,sheetName);
	        return login1.getData();        
	    }

	    @Test(dataProvider = "dp")
	    public void devLogin(String UserName,String PassWord) throws InterruptedException, IOException {

	        driver.findElement(By.name("txtUserName")).sendKeys(UserName);
	        driver.findElement(By.name("txtPwd")).sendKeys(PassWord);
	        driver.findElement(By.name("btnSignIn")).click();
	        Thread.sleep(5000);

	        if (driver.findElement(By.linkText("DashBoard")).isDisplayed()) {
	            List<String> arrayList = new ArrayList<String>();
	            arrayList.add("Pass");
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            login1.createSheet("Login", workbook, arrayList);
	        } 
	        else {
	            try{
	            Alert alert=driver.switchTo().alert();
	            String alertText=alert.getText();

	            Assert.assertEquals("invalid username or password,please try again",alertText);
	            alert.accept();
	            }catch(UnhandledAlertException e){
	                e.printStackTrace();
	            }
	            List<String> arrayList = new ArrayList<String>();
	            arrayList.add("Fail");
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            login1.createSheet("Login", workbook, arrayList);
	        }
	    }
	}
	Here is my code for XcelParserTestNGLogin()

	public class XcelParserTestNGLogin {
	    private transient Object[][] data;
	    String fileName,sheetName;

	    public XcelParserTestNGLogin() {

	    }

	    public XcelParserTestNGLogin(InputStream excelInputStream, String sheetName)
	            throws IOException {
	        this.data = loadFromSpreadsheet(excelInputStream, sheetName);
	    }

	    public Object[][] getData() {
	        return data;

	    }

	    Object[][] loadFromSpreadsheet(InputStream excelFile, String sheetName)
	            throws IOException {
	        // TODO Auto-generated method stub
	        HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
	        Sheet sheet = workbook.getSheet(sheetName);

	        int numberOfColumns = countNonEmptyColumns(sheet);
	        int numberOfRows = sheet.getLastRowNum() + 1;

	        data = new Object[numberOfRows - 1][numberOfColumns - 1];

	        for (int rowNum = 1; rowNum < numberOfRows; rowNum++) {
	            Row row = sheet.getRow(rowNum);
	            if (isEmpty(row)) {
	                break;
	            } else {
	                for (int column = 1; column < numberOfColumns; column++) {
	                    Cell cell = row.getCell(column);
	                    if (cell == null
	                            || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
	                        data[rowNum - 1][column - 1] = "";
	                    } else {
	                        data[rowNum - 1][column - 1] = objectFrom(workbook,
	                                cell);
	                    }
	                }
	            }
	        }

	        return data;
	    }

	    private boolean isEmpty(Row row) {
	        // TODO Auto-generated method stub
	        Cell firstCell = row.getCell(0);
	        boolean rowIsEmpty = (firstCell == null)
	                || (firstCell.getCellType() == Cell.CELL_TYPE_BLANK);
	        return rowIsEmpty;
	    }

	    *//**
	     * Count the number of columns, using the number of non-empty cells in the
	     * first row.
	     *//*
	    private int countNonEmptyColumns(Sheet sheet) {
	        // TODO Auto-generated method stub
	        Row firstRow = sheet.getRow(0);
	        return firstEmptyCellPosition(firstRow);
	    }

	    private int firstEmptyCellPosition(Row cells) {
	        // TODO Auto-generated method stub
	        int columnCount = 0;
	        for (Cell cell : cells) {
	            if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
	                break;
	            }
	            columnCount++;
	        }
	        return columnCount;
	    }

	    private Object objectFrom(HSSFWorkbook workbook, Cell cell) {
	        // TODO Auto-generated method stub
	        Object cellValue = null;
	        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
	            cellValue = cell.getRichStringCellValue().getString();
	        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            cellValue = getNumericCellValue(cell);
	        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
	            cellValue = cell.getBooleanCellValue();
	        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
	            cellValue = evaluateCellFormula(workbook, cell);
	        }

	        return cellValue;
	    }

	    private Object getNumericCellValue(final Cell cell) {
	        Object cellValue;
	        if (DateUtil.isCellDateFormatted(cell)) {
	            cellValue = new Date(cell.getDateCellValue().getTime());
	        } else {
	            cellValue = cell.getNumericCellValue();
	        }
	        return cellValue;
	    }

	    private Object evaluateCellFormula(final HSSFWorkbook workbook,
	            final Cell cell) {
	        FormulaEvaluator evaluator = workbook.getCreationHelper()
	                .createFormulaEvaluator();
	        CellValue cellValue = evaluator.evaluate(cell);
	        Object result = null;

	        if (cellValue.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
	            result = cellValue.getBooleanValue();
	        } else if (cellValue.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            result = cellValue.getNumberValue();
	        } else if (cellValue.getCellType() == Cell.CELL_TYPE_STRING) {
	            result = cellValue.getStringValue();
	        }

	        return result;
	    }
	    public void updateExcel(final InputStream excelFile, String SheetName,
	            List<String> list) {
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        Sheet sheet = null;
	        if (workbook.getSheetIndex(SheetName) > 0) {
	            sheet = workbook.getSheet(SheetName);
	            if (list != null && list.size() != sheet.getLastRowNum()) {
	                workbook.removeSheetAt(workbook.getSheetIndex(SheetName));
	                createSheet(SheetName, workbook, list);
	            } else {
	                createSheet(SheetName, workbook, list);
	            }
	        }

	    }
	    void createSheet(String SheetName, HSSFWorkbook workbook, List<String> list) {
	        // TODO Auto-generated method stub
	        String[] Heading = {"UserName", "Password",
	                "Result" };
	        Sheet sheet = workbook.createSheet(SheetName);
	        HSSFRow row = null;
	        HSSFCell cell = null;

	        row = (HSSFRow) sheet.createRow(0);
	        for (int cellNum = 0; cellNum < Heading.length; cellNum++) {
	            cell = row.createCell(cellNum);
	            cell.setCellValue(Heading[cellNum]);
	        }   
	        for (int rowNum = 1; rowNum <= list.size(); rowNum++) {
	            String[] cellVals = {"uname",
	                    "pswd", list.get(rowNum - 1) };

	            row = (HSSFRow) sheet.createRow(rowNum);
	            for (int cellNum = 0; cellNum < cellVals.length; cellNum++) {
	                cell = row.createCell(cellNum);
	                if (!(cellNum == cellVals.length))
	                    cell.setCellValue(cellVals[cellNum]);
	                else
	                    cell.setCellValue(true);
	            }
	        }
	        try {
	            FileOutputStream out = new FileOutputStream("Data//LoginPage.xls");
	            workbook.write(out);
	            out.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    }*/

	
}
