package com.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils 
{
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;
	
	private static String[][] retArray;
	
	public static String[][] readExcel(String file, String sheetName)
	{
		Double dblCellVal = null;
		FileInputStream excelFile = null;
		String strCellVal = null;
		Boolean blnCellVal = false;
		int rowCount =0;
		int colCount = 0;
		int rowIndex = 0;
		int cellIndex = 0;
		
		try {
			excelFile = new FileInputStream(file);
			excelWBook = new XSSFWorkbook(excelFile);	
			excelWSheet = excelWBook.getSheet(sheetName);
			Iterator <Row> rowIterator = excelWSheet.iterator();
			rowCount = excelWSheet.getPhysicalNumberOfRows();
			if (rowIterator.hasNext())
			{
				row = (XSSFRow) rowIterator.next();
				colCount = row.getPhysicalNumberOfCells();
			}
			retArray = new String[rowCount][colCount];
			while (rowIterator.hasNext())
			{
				row = (XSSFRow) rowIterator.next();
				cellIndex = 0;
				Iterator <Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					switch (cell.getCellType())
					{
					case Cell.CELL_TYPE_NUMERIC:
						dblCellVal = cell.getNumericCellValue();
						strCellVal = dblCellVal.toString();
						break;
					case Cell.CELL_TYPE_STRING:
						strCellVal = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						blnCellVal = cell.getBooleanCellValue();
						strCellVal = blnCellVal.toString();
						break;
					}
					
					retArray[rowIndex][cellIndex] = strCellVal;
					cellIndex++;
				}
				rowIndex++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retArray;
		
	}
	
	public static String readExcelRowWithChkVal(String file, String sheetName, String chkVal)
	{
		FileInputStream excelFile = null;
		String strCellVal = null;
		Boolean blnCellVal = false;
		Boolean rowFound = false;
		int cellIndex, rowNum;
		String strRowNum = null;
		Integer intRowNum;
		String retVal = null;
		
		try {
			excelFile = new FileInputStream(file);
			excelWBook = new XSSFWorkbook(excelFile);	
			excelWSheet = excelWBook.getSheet(sheetName);
			Iterator <Row> rowIterator = excelWSheet.iterator();

			while (rowIterator.hasNext())
			{
				
				row = (XSSFRow) rowIterator.next();
				rowNum = row.getRowNum();
				if (rowNum == 0)
				{
					continue;
				}
				intRowNum = (Integer) rowNum;
				strRowNum = intRowNum.toString();
				retVal = "";

				cellIndex = 0;
				Iterator <Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					switch (cell.getCellType())
					{
					case Cell.CELL_TYPE_NUMERIC:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						strCellVal = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_STRING:
						strCellVal = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						blnCellVal = cell.getBooleanCellValue();
						strCellVal = blnCellVal.toString();
						break;
					}					
					
					if ((cellIndex == 0))
					{
						if (strCellVal.equals(chkVal))
						{
							rowFound = true;
							retVal = retVal + strCellVal;
						}
						else
						{
							rowFound = false;
							break;
						}
					}
					else
					{
						retVal = retVal + "|" + strCellVal;
					}	
					cellIndex++;
				}
				if (rowFound)
				{
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal + "|" + strRowNum;
	}

	public static void setExcelFile(String path, String sheetName)
	{
		try
		{
			FileInputStream excelFile = new FileInputStream(path);
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String getCellData(int rowNum, int colNum)
	{
		try
		{
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			String cellData = cell.getStringCellValue();
			return cellData;
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	public static void setCellData(String result,  String testDataFile, int rowNum, int colNum)	
	{

		try
		{
			row  = excelWSheet.getRow(rowNum);
			cell = row.getCell(colNum, Row.RETURN_BLANK_AS_NULL);

			if (cell == null) 
			{
				cell = row.createCell(colNum);
			} 
			
			cell.setCellValue(result);
			cell.getColumnIndex();
			cell.getStringCellValue();

			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(testDataFile);
			excelWBook.write(fileOut);

			fileOut.flush();
			fileOut.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
