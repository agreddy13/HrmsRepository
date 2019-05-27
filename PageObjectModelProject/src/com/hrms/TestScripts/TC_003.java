package com.hrms.TestScripts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import com.testAutomation.Globals;
import com.utils.LoggerUtils;
import com.utils.TestXmlSuite;

public class TC_003 {

	public void runHrmsTest() throws IOException 
	{
		 
		//Create an instance on TestNG
		TestNG myTestNG = new TestNG();

		TestXmlSuite testXmlSuite = new TestXmlSuite();
		String testId = "TC_Login_003";
		XmlSuite mySuite = testXmlSuite.createXmlSuite(testId);
		
		//Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);

		//Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);
		
		File file = new File(Globals.TESTNG_FILE_NAME);
		LoggerUtils.loggerSetup();
		LoggerUtils.logInfo("Test ID: " + testId);
		LoggerUtils.logInfo("File is: " + file);
		
		FileWriter writer = new FileWriter(file);
		writer.write(mySuite.toXml());
		writer.close();
		
		//invoke run() - this will run your class.
		myTestNG.run();
	}
	
	@Test
	public void hrmsAddEmpTest() throws IOException
	{
		TC_003 hrmsAddEmpTest = new TC_003();
		hrmsAddEmpTest.runHrmsTest();
	}
}
