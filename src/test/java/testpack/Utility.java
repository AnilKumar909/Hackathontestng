package testpack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {

	public static void excelOutput(List<String>bikemodels,List<String>bikePrices,List<String>launchdates,List<String>popularmodels) throws IOException {
		String path=System.getProperty("user.dir")+"\\TestData\\ExcelOutput.xlsx";
		FileOutputStream file=new FileOutputStream(path);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Bike Details");
		
		for(int i=0;i<bikemodels.size();i++) {
			
				XSSFRow rows=sheet.createRow(i);
                rows.createCell(0).setCellValue(bikemodels.get(i));
                rows.createCell(1).setCellValue(bikePrices.get(i));
                rows.createCell(2).setCellValue(launchdates.get(i));
                
			
		}
		XSSFSheet sheet1=workbook.createSheet("models");
		
		for(int i=0;i<popularmodels.size();i++) {
			XSSFRow rows1=sheet1.createRow(i);
        rows1.createCell(0).setCellValue(popularmodels.get(i));
		}
		workbook.write(file);
		workbook.close();
		file.close();
	}
	/*public static String excelInput() throws IOException{
		String path=System.getProperty("user.dir")+"\\TestData\\Excel1.xlsx";
		FileInputStream file=new FileInputStream(path);
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		return String.valueOf(sheet.getRow(0).getCell(0));
	} */
}

