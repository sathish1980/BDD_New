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

public class checkwithnodesxmltoexcel {
	String description=null;
	  String TestSuite_Name=null;
	  String step_name=null;
	  String[] newarr;
	  boolean arrayvalue=false;
	public void xmltoexcelfile() throws SAXException, IOException, ParserConfigurationException
	{
		File xmlFile= new File("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\Laurette\\automation\\9411_BLOCKS_X_COURSES.xml");
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
		
		//cell.setCellStyle(style);
		 sheet = workbook.getSheetAt(0);
		
	   
		 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		 Document doc = dBuilder.parse(xmlFile);
		 doc.getDocumentElement().normalize();
		 
		 //Getting the suite name

		    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    System.out.println("Root element :" + doc.getDocumentElement().getFirstChild().getNextSibling().getNextSibling()); 
		    NodeList nList_suitename = doc.getElementsByTagName("con:testSuite");
		    for (int j = 0; j < nList_suitename.getLength(); j++) {
		        System.out.println("Processing element " + (j+1) + "/" + nList_suitename.getLength());
		        Node node_2 = nList_suitename.item(j);
		        if (node_2.getNodeType() == Node.ELEMENT_NODE) {
		            Element element_2 = (Element) node_2;
		            TestSuite_Name = element_2.getAttribute("name");
		            System.out.println(TestSuite_Name);
		          
		        
		      NodeList nodeList = element_2.getElementsByTagName("con:testCase");
		     //  NodeList nodeList = node_2.getNodeValue()
		       
		        for (int i = 0; i < nodeList.getLength(); i++) {
		            Node currentNode = nodeList.item(i);
		            if (currentNode.getNodeType() == currentNode.ELEMENT_NODE) {
		                //calls this method for all the children which is Element
		            	Element element_3 = (Element) currentNode;
			           String TestCase_Name = element_3.getAttribute("name");
			           System.out.println(TestCase_Name);
			           NodeList check=element_3.getElementsByTagName("con:description");
				        if(check.getLength()>0)
				        {
				         description= element_3.getElementsByTagName("con:description").item(0).getTextContent();
				        }
				        else
				        {
				        	description="To verify the "+TestCase_Name;
				        }
			           
			           NodeList nodesteplist = element_3.getElementsByTagName("con:testStep");
			           for (int k = 0; k < nodesteplist.getLength(); k++) {
				            Node TestStepNode = nodesteplist.item(k);
				            if (TestStepNode.getNodeType() == TestStepNode.ELEMENT_NODE) {
				                //calls this method for all the children which is Element
				            	Element element_testStep = (Element) TestStepNode;
					           String TestStep_Name = element_testStep.getAttribute("name");
					           System.out.println(TestStep_Name);
					           
					           row_1 = sheet.createRow(rowNum++);
						        
						         cell_1 = row_1.createCell(0);
						        cell_1.setCellValue(TestSuite_Name);
						        
						        cell_1 = row_1.createCell(1);
						        cell_1.setCellValue(TestCase_Name);

						        cell_1 = row_1.createCell(2);
						        cell_1.setCellValue(description);
						        
						        cell_1 = row_1.createCell(3);
						       // cell_1.setCellValue(step_name);
						        cell_1.setCellValue(TestStep_Name);
					           NodeList nodeassertionlist = element_3.getElementsByTagName("con:assertion");
					           for (int l = 0; l < nodeassertionlist.getLength(); l++) {
						            Node assertionNode = nodeassertionlist.item(l);
						            if (assertionNode.getNodeType() == assertionNode.ELEMENT_NODE) {
						                //calls this method for all the children which is Element
						            	Element element_assertion= (Element) assertionNode;
							           String assertion = element_assertion.getAttribute("name");
							           System.out.println(assertion);
							           
							           cell = row.createCell(4+l);
							        cell.setCellValue("ASSERTION");
							   		
							   		cell_1 = row_1.createCell(4+l);
							       // cell_1.setCellValue(step_name);
							        cell_1.setCellValue(assertion);
				            }
			           }
		            }
		        }
		     }
		   
		  }
	  }
}
	
		  FileOutputStream fileOut = new FileOutputStream("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\Laurette\\automation\\testcase.xlsx");
			 workbook.write(fileOut);
			 workbook.close();
			 fileOut.close();
			System.out.print("completed");
			
}
	
	public static void printTags(Node nodes){
	       if(nodes.hasChildNodes()  || nodes.getNodeType()!=3){
	           System.out.println(nodes.getNodeName()+" : "+nodes.getTextContent());
	           NodeList nl=nodes.getChildNodes();
	           for(int j=0;j<nl.getLength();j++)printTags(nl.item(j));
	       }
	   }

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		checkwithnodesxmltoexcel excl= new checkwithnodesxmltoexcel();
		excl.xmltoexcelfile();

	}

}
