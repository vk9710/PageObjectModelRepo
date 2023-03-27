package com.w2a.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.w2a.base.Page;

public class DataUtil extends Page {

	@DataProvider(name="dp")
	public static Object[][] getData(Method m) {
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;
		for (int rowNo = 2; rowNo <= rows; rowNo++) {
			table = new Hashtable<String, String>();
			for (int colNo = 0; colNo < cols; colNo++) {
				table.put(excel.getCellData(sheetName, colNo, 1), excel.getCellData(sheetName, colNo, rowNo));
				data[rowNo - 2][0] = table;
			}
		}
		return data;
	}

}
