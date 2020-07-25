package Javaprogramstest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class reconsample {
	
	 Row headerRow=null;
		
	 XSSFWorkbook workbook = new XSSFWorkbook(); 
	 // Create a Sheet
    Sheet sheet = workbook.createSheet("SourceFile");
	public void split()
	{
		  try {
		        BufferedReader in = new BufferedReader(
		                               new FileReader("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\DM_Automation\\DM_autm.txt"));
		        String str;

		        while ((str = in.readLine())!= null) {
		            String[] ar=str.split("||");
		            System.out.println(ar);
		            int ar_siz=ar.length;;
		            excelwrite(ar_siz,ar); 
		           
		        }
		        in.close();
		    } catch (IOException e) {
		        System.out.println("File Read Error");
		    }
	}

	private void excelwrite(int ar_siz, String[] ar) throws IOException {
		// TODO Auto-generated method stub
		
        // Create a Row
        int noOfRows = sheet.getLastRowNum();
        if (noOfRows<1)
        {
         headerRow = sheet.createRow(0);
        }
        else
        {
        	headerRow = sheet.createRow(noOfRows++);
        }
	}

	
	public void excelCompare()
	{
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		reconsample rs=new reconsample();
		rs.split();

	}

}
