package info.seleniumcucumber.utils.dataproviders;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import info.seleniumcucumber.utils.BaseTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import info.seleniumcucumber.testDataTypes.Customer;

public class ExcelDataReader implements BaseTest {
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	static Customer customer;

	private static final String customerFilePath = configFileReader.getTestDataResourcePath() + "TestData.xlsx";

	private static final String sheetName = "LoginPage";

	//This method is set to the file path and to open the excel file, pass excel path and
	//sheet name as arguments to this method
	public void setExcelFile() throws Exception{

		try {
			// Open the excel file

			FileInputStream excelFile = new FileInputStream(customerFilePath);

			//Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(excelFile);

			ExcelWSheet = ExcelWBook.getSheet(sheetName);

			} catch (Exception e) {

			throw(e);
		}
	}

	//This method is to read the test data from the excel cell,
	//in this we are passing parametres as Row num and Col num
	public Customer getExcelData(int rowNum)
	{
		try {

            FileInputStream excelFile = new FileInputStream(customerFilePath);

			//Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(excelFile);

			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			XSSFRow row = ExcelWSheet.getRow(rowNum);

			//Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);

			//String cellData = Cell.getStringCellValue();

		    customer = new Customer();

			customer.setUserName(row.getCell(1).getStringCellValue());

			customer.setPassword(row.getCell(2).getStringCellValue());

			return customer;

		} catch (Exception e) {

            return customer;
		}
	}

	//This method is to write in the excel cell, row num and col num are the parameters
	public static void setCellData(String result, int rowNum, int colNum) throws Exception{
		try{

	           Row  = ExcelWSheet.getRow(rowNum);

	// Cell = Row.getCell(colNum, Row.RETURN_BLANK_AS_NULL);

	 if (Cell == null) {

	 Cell = Row.createCell(colNum);

	 Cell.setCellValue(result);

	 } else {

	 Cell.setCellValue(result);

	 }

	          // Constant variables Test Data path and Test Data file name

	           FileOutputStream fileOut = new FileOutputStream(customerFilePath);

	           ExcelWBook.write(fileOut);

	           fileOut.flush();

	 fileOut.close();

	 }catch(Exception e){

	 throw (e);

	 }

	}

}
