package Javaprogramstest;



	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	public class Combinedata  {

		public static void main(String[] args) throws IOException, InvalidFormatException 
		{
			// Step #1 : Locate path and file of input excel.
			File inputFile=new File("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\DM_Automation\\DM_AT.xlsx");
			FileInputStream fis=new FileInputStream(inputFile);
			XSSFWorkbook inputWorkbook=new XSSFWorkbook(fis);
			int inputSheetCount=inputWorkbook.getNumberOfSheets();
			System.out.println("Input sheetCount: "+inputSheetCount);
			
			// Step #2 : Locate path and file of output excel.
			File outputFile=new File("C:\\\\Users\\\\sathish.kumar15\\\\Desktop\\\\SathishkumarR\\\\DM_Automation\\Originak_File.xlsx");
			FileOutputStream fos=new FileOutputStream(outputFile);
			
			
			// Step #3 : Creating workbook for output excel file.
			XSSFWorkbook outputWorkbook=new XSSFWorkbook();
			
		
			
			// Step #4 : Creating sheets with the same name as appearing in input file.
			for(int i=0;i<inputSheetCount;i++) 
	                { 
	                  XSSFSheet inputSheet=inputWorkbook.getSheetAt(i); 
	                  String inputSheetName=inputWorkbook.getSheetName(i); 
	                  XSSFSheet outputSheet=outputWorkbook.createSheet(inputSheetName); 

	                 // Create and call method to copy the sheet and content in new workbook. 
	                 copySheet(inputSheet,outputSheet); 
	                }
			
	               // Step #9 : Write all the sheets in the new Workbook(testData_Copy.xlsx) using FileOutStream Object
	               outputWorkbook.write(fos); 
	              // Step #10 : At the end of the Program close the FileOutputStream object. 
	               fos.close(); 
	          }

	           public static void copySheet(XSSFSheet inputSheet,XSSFSheet outputSheet) 
	           { 
	              int rowCount=inputSheet.getLastRowNum(); 
	              System.out.println(rowCount+" rows in inputsheet "+inputSheet.getSheetName()); 
	               
	                int currentRowIndex=0; if(rowCount>0)
			{
				Iterator rowIterator=inputSheet.iterator();
				while(rowIterator.hasNext())
				{
					int currentCellIndex=0;
					Iterator cellIterator=((Row) rowIterator.next()).cellIterator();
					while(cellIterator.hasNext())
					{
					// Step #5-8 : Creating new Row, Cell and Input value in the newly created sheet. 
						String cellData=cellIterator.next().toString();
						if(currentCellIndex==0)
							outputSheet.createRow(currentRowIndex).createCell(currentCellIndex).setCellValue(cellData);
						else
							outputSheet.getRow(currentRowIndex).createCell(currentCellIndex).setCellValue(cellData);
						
						currentCellIndex++;
					}
					currentRowIndex++;
				}
				System.out.println((currentRowIndex-1)+" rows added to outputsheet "+outputSheet.getSheetName());
				System.out.println();
			}
		}
	}

