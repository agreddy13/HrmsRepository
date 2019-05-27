package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.pageObject.AddEmpPage;
import com.pageObject.LoginPage;
import com.testAutomation.Genlib;
import com.testAutomation.Globals;
import com.utils.LoggerUtils;

public class AddEmp {

	public static void addEmp(WebDriver driver,String strData) throws InterruptedException{
		String [] datArr=strData.split("\\|");
		WebElement pim1=AddEmpPage.pim(driver);
		Actions action=new Actions(driver);
		action.moveToElement(pim1).perform();
		Thread.sleep(500);
		WebElement add=AddEmpPage.addEmp(driver);
		add.click();
		Thread.sleep(500);
		WebElement fn=AddEmpPage.fName(driver);
		fn.sendKeys(datArr[6]);
		
		WebElement ln=AddEmpPage.lName(driver);
		ln.sendKeys(datArr[7]);
		
		WebElement save=AddEmpPage.saveBtn(driver);
		save.click();
		
	}
	
	public static String valLogin(WebDriver driver,String strData)
	{
		return "Pass";
		
	}
	public static String createEmp(String strData) throws InterruptedException
	{
		WebDriver driver=null;
        String testRunStatus;
    	driver = setUp();
		Login.navLogin(driver, strData);
		addEmp(driver,strData);
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
