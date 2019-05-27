package com.testAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.utils.ExcelUtils;
import com.utils.LoggerUtils;

public class Genlib {

	public static String invokeTest(String className) 	{
		String strData;
		String [] arrData = null;
		String datFile, sheetName;
		int testRunStatusCol;
		
		try {
			
			// Get the data file and data file sheet name from TestConfig file
			strData = ExcelUtils.readExcelRowWithChkVal(Globals.CONFIG_FILE_NAME, Globals.CONFIG_SHEET_NAME, className);
			arrData = strData.split("\\|");
			if (arrData[0].equals(className))
			{
				datFile = Globals.DATA_FILE_PATH + arrData[1];
				sheetName = arrData[2];
				testRunStatusCol = Integer.parseInt(arrData[3]);
				// Reading data from the data file
				strData = ExcelUtils.readExcelRowWithChkVal(datFile, sheetName, className);
				arrData = strData.split("\\|");
				if (arrData[0].equals(className))
				{
					Class cls = Class.forName(Globals.TESTS_ROOT + "." + arrData[2]);
					Object obj = cls.newInstance();
					Class<String>[] params = new Class[] {String.class};
					Object [] args = new Object [] {new String(strData)};
					Method method = cls.getDeclaredMethod(arrData[3], params);
					Object testRunStatus = method.invoke(obj, args);
					setTestRunStatus(strData, (String) testRunStatus, datFile, sheetName, testRunStatusCol);
					return (String) testRunStatus;
				} else {
					return "Fail";
				}
			}
			else {
				return "Fail";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}

	private static void setTestRunStatus(String strData, String testRunStatus, String datFile, String sheetName, int testRunStatusCol)
	{
		String [] datArr = strData.split("\\|");
		String strRowNum = datArr[(datArr.length - 1)];
		int rowNum = Integer.parseInt(strRowNum);
		ExcelUtils.setExcelFile(datFile, sheetName);
		ExcelUtils.setCellData(testRunStatus, datFile, rowNum, testRunStatusCol);
		
	}

	public static WebDriver webDriverSetUp()
	{
		WebDriver driver = null;
		
		switch (Globals.BROWSER)
		{
			case "chrome":
				System.setProperty("webdriver.chrome.driver", Globals.CHROME_DRIVER_PATH);
				ChromeOptions chromeOptions = new ChromeOptions();
				List<String> chromeArgs = new ArrayList<String>();
				chromeArgs.add("start-maximized");
				chromeOptions.addArguments(chromeArgs);
				
				driver = new ChromeDriver(chromeOptions);
				break;
				
			case "firefox":
				System.setProperty("webdriver.gecko.driver", Globals.FIREFOX_DRIVER_PATH);
				driver = new FirefoxDriver();
				break;

			default:
		}

		LoggerUtils.loggerSetup();
		LoggerUtils.logInfo("Driver Setup");

		return driver;
	}
	
	public static void webDriverTearDown(WebDriver driver)
	{
		driver.close();
		driver.quit();
		LoggerUtils.logInfo("Driver tear down");
	}

	public static Properties readConfig()
	{
		File file = new File("config.properties");
		FileInputStream fileInputStream = null;
		
		try 
		{
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		Properties prop = new Properties();

		try 
		{
			prop.load(fileInputStream);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public static void generate_screenshot(WebDriver driver, String path) {
		 File screenshot_page= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  try {
			FileUtils.copyFile(screenshot_page, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sleep(int mSec)
	{
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void tearDown(WebDriver driver)
	{
		driver.close();
	}
}
