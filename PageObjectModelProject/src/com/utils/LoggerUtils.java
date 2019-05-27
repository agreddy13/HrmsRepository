package com.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggerUtils 
{
	static Logger log = Logger.getLogger(LoggerUtils.class.getName());
	
	
	public static void loggerSetup()
	{
		DOMConfigurator.configure("log4j.xml");
	}
	
	
	public static void logInfo(String logMsg)
	{
		log.info(logMsg);
	}
	
}
