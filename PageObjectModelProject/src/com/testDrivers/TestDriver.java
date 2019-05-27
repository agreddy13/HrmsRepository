package com.testDrivers;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testAutomation.Genlib;

import org.testng.annotations.Parameters;


public class TestDriver 
{
	@Parameters({"testId"})
	@Test
	public static void tDriver(String testId )
	{
		// Read the name of test to call from excel
		String testResult = Genlib.invokeTest(testId);
		Assert.assertTrue(testResult.equals("Pass"));
	}

}
