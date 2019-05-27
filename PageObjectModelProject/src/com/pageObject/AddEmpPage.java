package com.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmpPage {
	
private static WebElement element=null; 
	
	public static WebElement pim(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='menu_pim_viewPimModule']/b")));
		return element;
	}
	
	public static WebElement addEmp(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add Employee")));
		return element;
	}
	
	public static WebElement fName(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='firstName']")));
		return element;
	}
	public static WebElement lName(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='lastName']")));
		return element;
	}
	
	public static WebElement saveBtn(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='btnSave']")));
		return element;
	}
	
}
