package com.testAutomation;

import java.util.Properties;

public class Globals 
{
	
	static Properties prop = Genlib.readConfig();
	
	public static final String LOGIN_URL = prop.getProperty("loginUrl");
	public static final String TESTS_ROOT = prop.getProperty("testsRoot");
	public static final String CONFIG_FILE_NAME = prop.getProperty("testConfig");
	public static final String CONFIG_SHEET_NAME = prop.getProperty("sheetConfig");
	public static final String DATA_FILE_PATH = prop.getProperty("dataFilePath");
	public static final String CHROME_DRIVER_PATH = prop.getProperty("chromeDriver");
	public static final String CHROME_DATA_DIR = prop.getProperty("chromeDataDir");
	public static final String IE_DRIVER_PATH = prop.getProperty("ieDriver");
	public static final String FIREFOX_DRIVER_PATH = prop.getProperty("firefoxDriver");
	public static final String SUITE_NAME = prop.getProperty("suiteName");
	public static final String TESTNG_FILE_NAME = prop.getProperty("testNGFileName");
	public static final String BROWSER = prop.getProperty("browser");
	public static final String QUERY = prop.getProperty("SqlQuery");
	public static final String SQLSERVER = prop.getProperty("SqlServer");
	
}
