package Javaprogramstest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Delimitterfilesplit {
	 Row headerRow=null;
		
	 XSSFWorkbook workbook = new XSSFWorkbook(); 
	 // Create a Sheet
    Sheet sheet = workbook.createSheet("SourceFile");
	
	public void split()
	{
		String[] ar = null;
		int ar_siz=0;
		  try {
		        BufferedReader in = new BufferedReader(
		                               new FileReader("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\DM_Automation\\DM_autm.txt"));
		        String str;

		        while ((str = in.readLine())!= null) {
		            ar=str.split("\\|");
		           // System.out.println(ar);
		            ar_siz=ar.length;;
		            excelwrite(ar_siz,ar); 
		           
		           
		        }
		        workbook.createSheet("DestSheet");
		        // Closing the workbook
		        System.out.println("convert in to Excel Task has been completed");
	            workbook.close();
		        in.close();
		    } catch (IOException e) {
		        System.out.println("File Read Error");
		    }
	}
	
	public void excelwrite(int Column_size,String[] coulmns) throws IOException
	{
		
		 //XSSFWorkbook workbook = new XSSFWorkbook(); 
		if(sheet!=null)
		{
		}
		
		else
		{
		  sheet = workbook.createSheet("SourceFile");
		}
        // Create the number of Row
		 
        int noOfRows = sheet.getPhysicalNumberOfRows();
        if (noOfRows<1)
        {
         headerRow = sheet.createRow(0);
        }
        else
        {
        	headerRow = sheet.createRow(noOfRows++);
        }
        // Create cells
        for(int i = 0; i < Column_size; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(coulmns[i]);
           
        }
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\DM_Automation\\DM_AT.xlsx");
        workbook.write(fileOut);

       fileOut.close();
       //workbook.close();
       
        
	}
	
	public void excelcmopare() throws IOException
	{
		int inputSheet_Actual_rowcount=0;
		int inputSheet_Expected_rowcount=0;
		Row row_actual_equals = null;
		Row row_expected_equals=null;
		int column_count_actual     = 0;
		int column_count_Expected=0;
		int column_count_actual_original= 0;
		int column_count_Expected_original=0;
		int pass_count=0;
		int fail_count=0;
		boolean Status;
		
		// Step #1 : Locate path and file of input excel.
		File inputFile=new File("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\DM_Automation\\DM_AT.xlsx");
		FileInputStream fis=new FileInputStream(inputFile);
		XSSFWorkbook inputWorkbook=new XSSFWorkbook(fis);
		int inputSheetCount=inputWorkbook.getNumberOfSheets();
		System.out.println("Input sheetCount: "+inputSheetCount);
		
			XSSFSheet inputSheet_Actual=inputWorkbook.getSheetAt(0); 
			XSSFSheet inputSheet_Expected=inputWorkbook.getSheetAt(1);
			
			inputSheet_Actual_rowcount=inputSheet_Actual.getPhysicalNumberOfRows();
			inputSheet_Expected_rowcount=inputSheet_Expected.getPhysicalNumberOfRows();
			
			System.out.println("The inputSheet_Actual_rowcount:"+inputSheet_Actual_rowcount+"inputSheet_Expected_rowcount:"+inputSheet_Expected_rowcount);
			
			for(int i=0;i<inputSheet_Actual_rowcount;i++)
			{
				Status = false;
				for(int j=0;j<inputSheet_Expected_rowcount;j++)
				{
					Row row_actual     = inputSheet_Actual.getRow(i);
					Row row_expected     = inputSheet_Expected.getRow(j); // first row
					Cell cell_actual   = row_actual.getCell(0);
					Cell cell_expected   = row_expected.getCell(0);
					column_count_actual_original     = inputSheet_Actual.getRow(i).getLastCellNum();
					column_count_Expected_original   = inputSheet_Expected.getRow(j).getLastCellNum();
					String actual_value = getCellValueAsString(cell_actual);
					String Expected_value = getCellValueAsString(cell_expected);;
					if(actual_value.equals(Expected_value))
					{
						column_count_actual     = inputSheet_Actual.getRow(i).getLastCellNum();
						column_count_Expected   = inputSheet_Expected.getRow(j).getLastCellNum();
						
					for(int k=0;k<column_count_actual ;k++)
					{
						for(int l=k;l<column_count_Expected ;l++)
						{
							row_actual_equals     = inputSheet_Actual.getRow(i);
							row_expected_equals     = inputSheet_Expected.getRow(j); // first row
							Cell cell_actual_equals   = row_actual_equals.getCell(k);
							Cell cell_expected_equals   = row_expected_equals.getCell(l);
							String actual_value_equals = getCellValueAsString(cell_actual_equals);
							String Expected_value_equals = getCellValueAsString(cell_expected_equals);
							if(actual_value_equals.equals(Expected_value_equals))
							{
								break;
							}
							else
							{
								 
								   CellStyle style = inputWorkbook.createCellStyle();  
						            // Setting Background color  
						            style.setFillBackgroundColor(IndexedColors.RED1.getIndex());  
						            style.setFillPattern(FillPatternType.LEAST_DOTS);  
						            cell_actual_equals.setCellStyle(style); 
						            cell_expected_equals.setCellStyle(style);
								if(column_count_actual<=column_count_actual_original)
								{
								Cell cell_actual_Status = row_actual_equals.createCell(column_count_actual+1);
								cell_actual_Status.setCellValue("Fail");
								cell_actual_Status.setCellStyle(style);
								Cell cell_expected_Status = row_expected_equals.createCell(column_count_Expected+1);
								cell_expected_Status.setCellValue("Fail");
								cell_expected_Status.setCellStyle(style);
								System.out.println("The value in the Row no : "+i+" actaul file is : "+actual_value_equals+"The value in the ROW no: "+j+"Expected file is : "+Expected_value_equals);
								Status=true;
								fail_count++;
								break;
								}
								else
								{
									Cell cell_actual_Status = row_actual_equals.createCell(column_count_actual_original+1);
									cell_actual_Status.setCellValue("");
									cell_actual_Status.setCellValue("Fail");
									cell_actual_Status.setCellStyle(style);
									Cell cell_expected_Status = row_expected_equals.createCell(column_count_actual_original+1);
									cell_expected_Status.setCellValue("");
									cell_expected_Status.setCellValue("Fail");
									cell_expected_Status.setCellStyle(style);
									System.out.println("The value in the Row no : "+i+" actaul file is : "+actual_value_equals+"The value in the ROW no: "+j+"Expected file is : "+Expected_value_equals);
									Status=true;
									fail_count++;
									break;
								}
							}
						}
					}
					if(Status == false)
					{
					Cell cell_actual_Status = row_actual_equals.createCell(column_count_actual_original+1);
					cell_actual_Status.setCellValue("Pass");
					Cell cell_expected_Status = row_expected_equals.createCell(column_count_Expected_original+1);
					cell_expected_Status.setCellValue("Pass");
					pass_count++;
					break;
					}
					else
					{
						break;
					}
					}
				
				}
			}
			Row pass_row=sheet.createRow(inputSheet_Actual_rowcount+1);
			Cell pass_Status = pass_row.createCell(0);
			pass_Status.setCellValue(pass_count);
			Row fail_row=sheet.createRow(inputSheet_Actual_rowcount+2);
			Cell fail_Status = fail_row.createCell(0);
			fail_Status.setCellValue(fail_count);
			 fis.close();
			  //Create an object of FileOutputStream class to create write data in excel file

			    FileOutputStream outputStream = new FileOutputStream(inputFile);

			    //write data in the excel file

			    inputWorkbook.write(outputStream);

			    //close output stream

			    outputStream.close();
			 inputWorkbook.close();
		
		System.out.print("complterd");
		
	}

	public static String getCellValueAsString(Cell cell) {
        String strCellValue = null;
       // System.out.println(cell.getCellType());
        if (cell != null) {
            switch (cell.getCellType()) {
          case STRING:
                strCellValue = cell.toString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "dd/MM/yyyy");
                    strCellValue = dateFormat.format(cell.getDateCellValue());
                } else {
                    Double value = cell.getNumericCellValue();
                    Long longValue = value.longValue();
                    strCellValue = new String(longValue.toString());
                }
                break;
            case BOOLEAN:
                strCellValue = new String(new Boolean(
                        cell.getBooleanCellValue()).toString());
                break;
            case BLANK:
                strCellValue = "";
                break;
            }
        }
        return strCellValue;
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Delimitterfilesplit dm= new Delimitterfilesplit();
	dm.excelcmopare();
		//dm.split();

	}

}
