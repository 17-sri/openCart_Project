package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	// DataProvider 1
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\Opencart_LoginData.xlsx";// taking Excel file from tastData
		ExcelUtility xlUtil = new ExcelUtility(path);// creating an object for ExcelUtility
		int totalRows = xlUtil.getRowCount("Sheet1");
		int totalCols = xlUtil.getCellCount("Sheet1", 1);
		String loginData[][] = new String[totalRows][totalCols];// created for two dimension array which can store rows and columns
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);// 1,0
			}
		}
		return loginData;// returning two dimension array
	}

	// DataProvider 2

	// DataProvider 3
}