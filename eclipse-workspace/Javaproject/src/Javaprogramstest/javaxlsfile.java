package Javaprogramstest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class javaxlsfile {

	Row headerRow=null;
	Properties prop=new Properties();
	FileReader fis;
	 HSSFWorkbook workbook = new HSSFWorkbook(); 
	 HSSFWorkbook inputWorkbook=null;
		HSSFSheet inputSheet_Actual = null;
		HSSFSheet inputSheet_Expected=null;
		File inputFile=null;
		String secondary_source=null;
		String primary_source=null;
	 // Create a Sheet
   Sheet sheet = workbook.createSheet("SourceFile");
   public void propertyfiles() throws IOException
	{
		
		String workingDir = System.getProperty("user.dir");
		File inputFile_prop=new File(workingDir+"\\src\\Javaprogramstest\\properties_file.properties");
		fis=new FileReader(inputFile_prop);
		prop.load(fis);
		
	}
	
	public void split() throws IOException
	{
		 propertyfiles();
		String[] ar = null;
		int ar_siz=0;
		  try {
		        BufferedReader in = new BufferedReader(
		                               new FileReader(prop.getProperty("inupt_txt_file")));
		        String str;

		        while ((str = in.readLine())!= null) {
		            ar=str.split("\\|");
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
       FileOutputStream fileOut = new FileOutputStream(prop.getProperty("input_output_excel_file"));
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
		String sourceanddest=null;
		
		
		String[] soucearray=null;
		String sourcedest_column=null;
		String primary_source_column=null;
		String secondary_source_column=null;
		String[] soucearray_column=null;
		String convertingtostring=null;
		int original_array_size=0;
		String[] sourceanddextexactcolumn=null;
		String sourcecolumn_value=null;
		String destinationcolumn_value=null;
		int duplicate_count = 0;
		/*HSSFWorkbook inputWorkbook=null;
		HSSFSheet inputSheet_Actual = null;
		HSSFSheet inputSheet_Expected=null;
		File inputFile=null;
		*/
		propertyfiles();
		
		//####Getting the Primary key
		sourceanddest=prop.getProperty("Primarykeyinsourceanddestination");
		soucearray=sourceanddest.split(",");
		primary_source=soucearray[0];
		secondary_source=soucearray[1];
		if(prop.getProperty("Trailingspacesremove").equalsIgnoreCase("Y"))
		{
			primary_source=removeTrailingSpaces(primary_source);
			secondary_source=removeTrailingSpaces(secondary_source);
			
		}
		else if(prop.getProperty("LeadingTrailingspacesremove").equalsIgnoreCase("Y"))
		{
			primary_source=primary_source.trim();
			secondary_source=secondary_source.trim();
		}
		//######### getting the column values
		sourcedest_column=prop.getProperty("sourcedestination_comparison");
		soucearray_column=sourcedest_column.split("\\|");
		original_array_size=soucearray_column.length;
		
		
		// Step #1 : Locate path and file of input excel.
		 inputFile=new File(prop.getProperty("input_output_excel_file"));
		FileInputStream fis=new FileInputStream(inputFile);
		 inputWorkbook=new HSSFWorkbook(fis);
		int inputSheetCount=inputWorkbook.getNumberOfSheets();
		System.out.println("Input sheetCount: "+inputSheetCount);
		
			 inputSheet_Actual=inputWorkbook.getSheetAt(0); 
			 inputSheet_Expected=inputWorkbook.getSheetAt(1);
			
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
					Cell cell_actual   = row_actual.getCell(Integer.parseInt(primary_source));
					Cell cell_expected   = row_expected.getCell(Integer.parseInt(secondary_source));
					column_count_actual_original     = inputSheet_Actual.getRow(i).getLastCellNum();
					column_count_Expected_original   = inputSheet_Expected.getRow(j).getLastCellNum();
					String actual_value = getCellValueAsString(cell_actual);
					String Expected_value = getCellValueAsString(cell_expected);
					if(prop.getProperty("Trailingspacesremove").equalsIgnoreCase("Y"))
					{
						actual_value=removeTrailingSpaces(actual_value);
						Expected_value=removeTrailingSpaces(Expected_value);
						
					}
					else if(prop.getProperty("LeadingTrailingspacesremove").equalsIgnoreCase("Y"))
					{
						actual_value=actual_value.trim();
						Expected_value=Expected_value.trim();
					}
					if(actual_value.equals(Expected_value))
					{
						if(prop.getProperty("Duplicateprimaykey").equalsIgnoreCase("Y"))
						{
						duplicate_count=duplicateprimarykey(actual_value,j,inputSheet_Expected_rowcount);
						}
					
						column_count_actual     = inputSheet_Actual.getRow(i).getLastCellNum();
						column_count_Expected   = inputSheet_Expected.getRow(j).getLastCellNum();
						//Looping the column id values
						for(int n=0;n<original_array_size;n++)
						{
							convertingtostring=soucearray_column[n];
							sourceanddextexactcolumn=convertingtostring.split(",");
							sourcecolumn_value=sourceanddextexactcolumn[0];
							destinationcolumn_value=sourceanddextexactcolumn[1];
						
						
					//for(int k=0;k<column_count_actual ;k++)
					//{
						//for(int l=k;l<column_count_Expected ;l++)
						//{
							row_actual_equals     = inputSheet_Actual.getRow(i);
							row_expected_equals     = inputSheet_Expected.getRow(j); // first row
							Cell cell_actual_equals   = row_actual_equals.getCell(Integer.parseInt(sourcecolumn_value));
							Cell cell_expected_equals   = row_expected_equals.getCell(Integer.parseInt(destinationcolumn_value));
							String actual_value_equals = getCellValueAsString(cell_actual_equals);
							String Expected_value_equals = getCellValueAsString(cell_expected_equals);
							if(actual_value_equals.equals(Expected_value_equals))
							{
								Status=false;
								//break;
							}
							else
							{	 
								   CellStyle style = inputWorkbook.createCellStyle();  
						            // Setting Background color  
						            style.setFillBackgroundColor(IndexedColors.DARK_RED.getIndex());  
						            style.setFillPattern(FillPatternType.BRICKS);  
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
									System.out.println("The value in the Row no : "+i+1+" actaul file is : "+actual_value_equals+"The value in the ROW no: "+j+1+"Expected file is : "+Expected_value_equals);
									Status=true;
									fail_count++;
									break;
								}
							}
						}
					//}
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
	
			Row pass_row=inputSheet_Actual.createRow(inputSheet_Actual_rowcount+1);
			Cell pass_Status_word = pass_row.createCell(0);
			pass_Status_word.setCellValue("RECONCILED COUNT");
			Cell pass_Status = pass_row.createCell(1);
			pass_Status.setCellValue(pass_count);
			Row fail_row=inputSheet_Actual.createRow(inputSheet_Actual_rowcount+2);
			Cell fail_Status_word = fail_row.createCell(0);
			fail_Status_word.setCellValue("UNRECONCILED COUNT");
			Cell fail_Status = fail_row.createCell(1);
			fail_Status.setCellValue(fail_count);
			if(prop.getProperty("Duplicateprimaykey").equalsIgnoreCase("Y"))
			{
			Row duplicate_row=inputSheet_Actual.createRow(inputSheet_Actual_rowcount+3);
			Cell duplicate_Status_word = duplicate_row.createCell(0);
			duplicate_Status_word.setCellValue("DUPLICATE PRIMARYKEY COUNT");
			Cell duplicate_Status = duplicate_row.createCell(1);
			duplicate_Status.setCellValue(duplicate_count);
			}
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
	
	public int duplicateprimarykey(String actual_value,int j,int inputSheet_Expected_rowcount)
	{
		int count=0;
		
		for(int i=0;i<inputSheet_Expected_rowcount;i++)
		{
			for(int k=i+1;k<inputSheet_Expected_rowcount;k++)
			{
			
			Row row_act     = inputSheet_Actual.getRow(i);
			Cell cell_act   = row_act.getCell(Integer.parseInt(primary_source));
			String actua_value = getCellValueAsString(cell_act);
			Row row_expected     = inputSheet_Actual.getRow(k);
			Cell cell_expected   = row_expected.getCell(Integer.parseInt(primary_source));
			String Expd_value = getCellValueAsString(cell_expected);
			if(actua_value.equalsIgnoreCase(Expd_value))
			{
				//System.out.println("duplicate primary key identified at: "+i);
				CellStyle style = inputWorkbook.createCellStyle();  
	            // Setting Background color  
	            style.setFillBackgroundColor(IndexedColors.RED.getIndex());  
	            style.setFillPattern(FillPatternType.BRICKS);
				count++;
				
			}
			
		
			}
			
		}
		return count;
	}
	
	 public static String removeTrailingSpaces(String param)
	    {
	        if (param == null)
	            return null;
	        int len = param.length();
	        for (; len > 0; len--) {
	            if (!Character.isWhitespace(param.charAt(len - 1)))
	                break;
	        }
	        return param.substring(0, len);
	    }
	 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		javaxlsfile dm= new javaxlsfile();
	dm.excelcmopare();
	//dm.split();

	}

}

