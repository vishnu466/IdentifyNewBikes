package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook w;
	
	public static void createExcelFile() throws IOException {
		fos = new FileOutputStream(System.getProperty("user.dir")+"//target//HackExcelData.xlsx");
		w = new XSSFWorkbook();
		
		XSSFSheet sheet1 = w.createSheet("NewBikes");
		XSSFSheet sheet2 = w.createSheet("UsedCars");
		XSSFSheet sheet3 = w.createSheet("GoogleLogin");
		
		//Sheet1
		XSSFRow Bikes = sheet1.createRow(0);
		
		XSSFCell BikeCell1 = Bikes.createCell(0);
		XSSFCell BikeCell2 = Bikes.createCell(1);
		XSSFCell BikeCell3 = Bikes.createCell(2);
		
		BikeCell1.setCellValue("BikeNames");
		BikeCell2.setCellValue("Expected Price");
		BikeCell3.setCellValue("Launch Date");
		
		//Sheet2
		XSSFRow cars = sheet2.createRow(0);
		
		XSSFCell usedCars1 = cars.createCell(0);
		XSSFCell usedCars2 = cars.createCell(1);
		
		System.out.println();
		
		usedCars1.setCellValue("CarNames");
		usedCars2.setCellValue("Price");
		
		//Sheet3
		XSSFRow ErrorMessage = sheet3.createRow(0);
		
		XSSFCell msg = ErrorMessage.createCell(0);
		
		msg.setCellValue("Error Message");
		
		w.write(fos);
		fos.close();
		w.close();
	}
	
	public static void setCellData(String value,String sheetName,int rownum,int cellnum) throws IOException {
		String file = System.getProperty("user.dir")+"//target//HackExcelData.xlsx";
		fis = new FileInputStream(file);
        w = new XSSFWorkbook(fis);
	    XSSFSheet sheet = w.getSheet(sheetName);

	    // Create the row if it doesn't exist
	    XSSFRow row = sheet.getRow(rownum);
	    if (row == null) {
	        row = sheet.createRow(rownum);
	    }
	    
	    XSSFCell cell = row.createCell(cellnum);
	    
	    // Set the cell value 
	    cell.setCellValue(value);

	    // Write the changes and close the workbook
	    FileOutputStream fos = new FileOutputStream(file);
	    w.write(fos);
	    fis.close();
	    fos.close();
	    w.close();
		
	}

}
