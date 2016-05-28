package hbs.schoolwide.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	static String currentDir = System.getProperty("user.dir");
	static ExcelReader objExcelFile;
	public static String excelName = "TestData";


	private static Sheet readExcel(String sheetName, String excelName) throws IOException {
		Sheet templateSheet = null;
		String fileName = excelName + ".xlsx";
		String filePath = currentDir + "\\src\\test\\resources\\testdata";

		try {
			

			File file = new File(filePath + File.separator + fileName);

			FileInputStream inputStream = new FileInputStream(file);

			Workbook wrkbkTemplates = null;

			String fileExtensionName = fileName.substring(fileName.indexOf("."));

			if (fileExtensionName.equals(".xlsx")) {
				wrkbkTemplates = new XSSFWorkbook(inputStream);
			}

			else if (fileExtensionName.equals(".xls")) {
				wrkbkTemplates = new HSSFWorkbook(inputStream);
			}
			templateSheet = wrkbkTemplates.getSheet(sheetName);
			
			inputStream.close();
			

		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return templateSheet;
	}
	
	
	public static List<Object[]> TestDataReader(String sheetname) {

		Sheet login = null;
		List<Object[]> testData = null;
		try {
			

			try {
				login = readExcel(sheetname, excelName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			testData = getFileContentList(login);

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return testData;
	}

	public static List<Object[]> getFileContentList(Sheet sheet) {
		List<Object[]> data = new ArrayList<Object[]>();

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		for (int i = 1; i <= rowCount; i++) {
			Row row = sheet.getRow(i);

			DataFormatter formatter = new DataFormatter();
			Cell cell = row.getCell(0);
			String errorCode = formatter.formatCellValue(cell).trim();

			Object[] arr1 = { errorCode, row.getCell(1).getStringCellValue().trim(),
					row.getCell(2).getStringCellValue().trim() };
			data.add(arr1);
		}

		return data;
	}

}
