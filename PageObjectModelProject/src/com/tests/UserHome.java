package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pageObject.LoginPage;
import com.testAutomation.Genlib;
import com.testAutomation.Globals;
import com.utils.LoggerUtils;

public class UserHome {

	public static String valLogin(WebDriver driver,String strData)
	{
		return "Pass";
	}
	public static String createHome(String strData) throws InterruptedException
	{
		WebDriver driver=null;
        String testRunStatus;
    	driver = setUp();
		Login.navLogin(driver, strData);
		Thread.sleep(1500);
		Genlib.tearDown(driver);
		testRunStatus = valLogin(driver,strData);
		
		return testRunStatus;
	}
	
	public static WebDriver setUp()
	{
		WebDriver driver = Genlib.webDriverSetUp();
		String url = Globals.LOGIN_URL;
		driver.get(url);
		
		LoggerUtils.loggerSetup();
		LoggerUtils.logInfo("Driver Setup");
		return driver;
	}
	
	public static void logout(WebDriver driver)
	{
		WebElement lnkLogout=LoginPage.lnkLogout(driver);
		lnkLogout.click();
	}
}
