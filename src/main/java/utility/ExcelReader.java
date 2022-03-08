package utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/** Read data from excel. */

public class ExcelReader {
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;

	public static String getData(int SheetNumber,int row,int col) {
		try {
			File _File = new File("./src/main/resources/TestData/TestData.xlsx");
			FileInputStream fis = new FileInputStream(_File);
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {

		}
		sheet = wb.getSheetAt(SheetNumber);
		String data;

		try {
			data = String.valueOf(sheet.getRow(row).getCell(col).getStringCellValue());
		} catch (Exception ex) {
			data = String.valueOf(sheet.getRow(row).getCell(col).getNumericCellValue());

		}
		try {
			data = String.valueOf(sheet.getRow(row).getCell(col).getStringCellValue());
		} catch (Exception ex) {
			data = String.valueOf(sheet.getRow(row).getCell(col).getDateCellValue());
		}

		return data;
	}
}
