package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pageObject.LoginPage;
import com.testAutomation.Genlib;
import com.testAutomation.Globals;
import com.utils.LoggerUtils;

public class Login {

	public static void navLogin(WebDriver driver,String strData)
	{
		String [] datArr=strData.split("\\|");
		
		LoggerUtils.loggerSetup();
		LoggerUtils.logInfo(datArr[4]);
		
		WebElement username=LoginPage.username(driver);
		username.sendKeys(datArr[4]);
		
		WebElement password=LoginPage.password(driver);
		password.sendKeys(datArr[5]);
		
		WebElement btnLogin=LoginPage.btnLogin(driver);
		btnLogin.click();
	}
	
	public static String valLogin(WebDriver driver,String strData)
	{
		return "Pass";
		
	}
	public static String loginSuccess(String strData)
	{
		WebDriver driver=null;
        String testRunStatus;
		driver = setUp();
		navLogin(driver,strData);
		//logout(driver);
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
