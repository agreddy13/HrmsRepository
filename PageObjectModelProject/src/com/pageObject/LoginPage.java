package com.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private static WebElement element=null; 
	
	public static WebElement username(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.presenceOfElementLocated(By.name("txtUsername")));
		return element;
	}
	public static WebElement user(WebDriver driver)
	{
		element=driver.findElement(By.className(""));
		return element;
	}
	
	public static WebElement password(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.presenceOfElementLocated(By.name("txtPassword")));
		return element;
	}
	
	public static WebElement btnLogin(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.elementToBeClickable(By.name("Submit")));
		return element;
	}
	
	public static WebElement lnkLogout(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
		return element;
	}
}
