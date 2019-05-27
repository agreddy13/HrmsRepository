package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.testAutomation.Globals;

public class TestXmlSuite {

	public XmlSuite createXmlSuite(String testId) throws IOException {
		// Create an instance of XML Suite and assign a name for it.
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName(Globals.SUITE_NAME);

		// Create a list of XmlTests and add the XmlTest you created earlier to it.
		List<XmlTest> myTests = new ArrayList<XmlTest>();

		XSSFSheet excelWSheet = null;
		XSSFWorkbook excelWBook = null;
		XSSFCell cell = null;
		XSSFRow row = null;

		Double dblCellVal;
		String strCellVal = null;
		Boolean blnCellVal;

		FileInputStream excelFile = new FileInputStream(Globals.CONFIG_FILE_NAME);
		excelWBook = new XSSFWorkbook(excelFile);
		excelWSheet = excelWBook.getSheet(Globals.CONFIG_SHEET_NAME);

		Iterator<Row> rowIterator = excelWSheet.iterator();
		String testName = null;

		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			System.out.println(row.getCell(0).getStringCellValue());
			if (testId.equals(row.getCell(0).getStringCellValue())) {
				break;
			}
		}
		if (row != null) {
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				cell = (XSSFCell) cellIterator.next();

				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_NUMERIC:

					dblCellVal = cell.getNumericCellValue();
					strCellVal = dblCellVal.toString();
					break;

				case XSSFCell.CELL_TYPE_STRING:

					strCellVal = cell.getStringCellValue();
					break;

				case XSSFCell.CELL_TYPE_BOOLEAN:

					blnCellVal = cell.getBooleanCellValue();
					strCellVal = blnCellVal.toString();
					break;
				}

				// If it is first cell then store the Test Name
				if (cell.getColumnIndex() == 0) {
					testName = strCellVal;
				}
			}

			if (strCellVal.equals("1.0")) {
				// Adding to suite
				// Create an instance of XmlTest and assign a name for it.
				XmlTest myTest = new XmlTest(mySuite);
				myTest.setName(testName);

				// Add any parameters that you want to set to the Test.
				Map<String, String> testngParams = new HashMap<String, String>();
				testngParams.put("testId", testName);
				myTest.setParameters(testngParams);

				// Create a list which can contain the classes that you want to run.
				List<XmlClass> myClasses = new ArrayList<XmlClass>();
				myClasses.add(new XmlClass("com.testDrivers.TestDriver"));

				// Assign that to the XmlTest Object created earlier.
				myTest.setXmlClasses(myClasses);

				// Adding the test to test list created earlier
				myTests.add(myTest);
			}
		}

		// add the list of tests to your Suite.
		mySuite.setTests(myTests);
		return mySuite;
	}
}
