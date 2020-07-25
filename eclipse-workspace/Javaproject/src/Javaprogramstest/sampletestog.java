package Javaprogramstest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
public class sampletestog {
	String description=null;
	  String TestSuite_Name=null;
	  String step_name=null;
	  String[] newarr;
	  boolean arrayvalue=false;
	public void xmltoexcelfile() throws SAXException, IOException, ParserConfigurationException
	{
		File xmlFile= new File("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\Laurette\\automation\\Laureate_IntegerationTesting.xml");
		XSSFWorkbook workbook = new XSSFWorkbook();
		Row row_1=null;
		Cell cell_1=null;
		 int n = 1;
		 HashMap<Integer,String> hm=new HashMap<Integer,String>();   
	/*	CellStyle style = workbook.createCellStyle();
		Font boldFont = workbook.createFont();
		boldFont.setBold(true);
		style.setFont(boldFont);*/
		

		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		Row row = sheet.createRow(rowNum++);
		Cell cell = row.createCell(0);
		cell.setCellValue("TEST_SUITE_NAME");
		//cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("TESTCASE_NAME");
	//	cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("TESTCASE_DESCRIPTION");
		cell = row.createCell(3);
		cell.setCellValue("TESTSTEP_NAME");
		cell = row.createCell(4);
		//cell.setCellStyle(style);
		 sheet = workbook.getSheetAt(0);
		 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		 Document doc = dBuilder.parse(xmlFile);
		 doc.getDocumentElement().normalize();
		 
		 //Getting the suite name

		    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    NodeList nList_suitename = doc.getElementsByTagName("con:testSuite");
		    for (int j = 0; j < nList_suitename.getLength(); j++) {
		        System.out.println("Processing element " + (j+1) + "/" + nList_suitename.getLength());
		        Node node_2 = nList_suitename.item(j);
		        if (node_2.getNodeType() == Node.ELEMENT_NODE) {
		            Element element_2 = (Element) node_2;
		            TestSuite_Name = element_2.getAttribute("name");;
		          
		        }
		   }
		   /* int siz=nList_suitename.getLength();
		    Node node_1 = nList_suitename.item(0);
		    if (node_1.getNodeType() == Node.ELEMENT_NODE) {
		        Element element_1 = (Element) node_1;
		        String TestSuite_Name=element_1.getElementsByTagName("name").item(0).getTextContent();
		    }*/
		   // String TestSuite_Name= doc.getElementsByTagName("con:testSuite").item(0).getTextContent();
		 NodeList nList = doc.getElementsByTagName("con:testCase");
		 for (int i = 0; i < nList.getLength(); i++) {
			  //  System.out.println("Processing element " + (i+1) + "/" + nList.getLength());
			    Node node = nList.item(i);
			    System.out.println("Entering in to the test case: "+i);
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			        Element element = (Element) node;
			        String name=element.getAttribute("name");
			        NodeList check=element.getElementsByTagName("con:description");
			        if(check.getLength()>0)
			        {
			         description= element.getElementsByTagName("con:description").item(0).getTextContent();
			        }
			        else
			        {
			        	description="To verify the "+name;
			        }
			        
			        //Finding the steps
			        NodeList Steps = doc.getElementsByTagName("con:testStep");
			        System.out.println("Entering in to test steps");
			        for (int k = i; k <Steps.getLength(); k++) {
					  //  System.out.println("Processing element " + (k+1) + "/" + Steps.getLength());
					    Node node_step = Steps.item(k);
					   
					    
					    if (node_step.getNodeType() == node_step.ELEMENT_NODE) {
					        Element element_step = (Element) node_step;
					        System.out.println(k);
					        System.out.println(nList.getLength());
					        if((i==k))
					        {
					         step_name =element_step.getAttribute("name");
					        n=1;
					        }
					        else if((i>k) ||(nList.getLength()<=k) )
					        {
					        	if(n==1)
					        	{
					        		hm.put(k-1, step_name);
					        	}
					        	arrayvalue=true;
					        	step_name=element_step.getAttribute("name");
					        	hm.put(k, step_name);
					        	n=2;
					        }
					        else
					        {
					        	break;
					        }
					         
					         // to get the post request
					       /*  if(Steps.getLength()>0) {
					      String as =  element.getElementsByTagName("con:testStep").item(k).getTextContent();
					      System.out.print(as);
					         }*/
					        
					    }
			        }
			        //String testCase = element.getElementsByTagName("name").item(0).getTextContent();
			        //String entryForce = element.getElementsByTagName("entry_force").item(0).getTextContent();
			        //String directive = element.getElementsByTagName("directive").item(0).getTextContent();

			        
			         row_1 = sheet.createRow(rowNum++);
			        
			         cell_1 = row_1.createCell(0);
			        cell_1.setCellValue(TestSuite_Name);
			        
			        cell_1 = row_1.createCell(1);
			        cell_1.setCellValue(name);

			        cell_1 = row_1.createCell(2);
			        cell_1.setCellValue(description);
			        if(arrayvalue == false)
			        {
			        cell_1 = row_1.createCell(3);
			       // cell_1.setCellValue(step_name);
			        cell_1.setCellValue(step_name);
			        }
			        else if(arrayvalue == true)
			        {
			        for(int l=i;l<Steps.getLength();l++)
			        {
			        	
			        cell_1 = row_1.createCell(1+l);
			        cell_1.setCellValue(hm.get(l));
			        	
			        }
			        }

			       
			    }
			  
		 }
	//}
		  FileOutputStream fileOut = new FileOutputStream("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\Laurette\\automation\\testcase.xlsx");
			 workbook.write(fileOut);
			 workbook.close();
			 fileOut.close();
			System.out.print("completed");
		
	}
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		sampletestog sb= new sampletestog();
		sb.xmltoexcelfile();

	}

}
