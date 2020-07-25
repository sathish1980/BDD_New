package Javaprogramstest;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLTOEXCEL {
	
	public void xmltoexcelfile() throws SAXException, IOException, ParserConfigurationException
	{
		File xmlFile= new File("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\Laurette\\automation\\Laureate_IntegerationTesting_05042019.xml");
		XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\sathish.kumar15\\Desktop\\SathishkumarR\\Laurette\\automation\\testcase.xlsx");

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
		//cell.setCellStyle(style);
		 sheet = workbook.getSheetAt(0);
		 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		 Document doc = dBuilder.parse(xmlFile);

		 NodeList nList = doc.getElementsByTagName("con:testSuite id");
		 
		 for (int i = 0; i < nList.getLength(); i++) {
			    System.out.println("Processing element " + (i+1) + "/" + nList.getLength());
			    Node node = nList.item(i);
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			        Element element = (Element) node;
			        String substanceName = element.getElementsByTagName("Name").item(0).getTextContent();
			        String entryForce = element.getElementsByTagName("entry_force").item(0).getTextContent();
			        String directive = element.getElementsByTagName("directive").item(0).getTextContent();

			        NodeList prods = element.getElementsByTagName("Product");
			    }
		 }
	}

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		XMLTOEXCEL xml= new XMLTOEXCEL();
		xml.xmltoexcelfile();

	}

}
