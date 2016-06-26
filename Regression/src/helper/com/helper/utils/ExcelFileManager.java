package com.helper.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelFileManager {

	private String EXCEL_FILE = "";

	public ExcelFileManager(String strFilePath) {
		EXCEL_FILE = strFilePath;
	}

	public HSSFWorkbook getWorkBook() {
		HSSFWorkbook workBook = null;
		try {
			InputStream ExcelFileToRead = new FileInputStream(EXCEL_FILE);
			workBook = new HSSFWorkbook(ExcelFileToRead);
			ExcelFileToRead.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workBook;

	}

	public HSSFSheet getSheet(String strSheetName) {
		HSSFSheet sheet = null;
		try {
			sheet = getWorkBook().getSheet(strSheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	public Map<String, String> getRowValue(String strSheetName,String strReference) {
		Map<String, String> rowValue = new HashMap<String, String>();
		try {
			HSSFSheet sheet = getSheet(strSheetName); 
			Iterator<Cell> keyCells = sheet.getRow(0).cellIterator();
			Iterator<Cell> valueCells = sheet.getRow(getRowIndex(sheet, strReference)).cellIterator();
			while (keyCells.hasNext()) {
				String key = strSheetName.trim()+"_"+keyCells.next().toString().trim();
				String value = valueCells.next().toString().trim();
				rowValue.put(key.toUpperCase(),value);
//				System.out.print(key+ " "+value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowValue;
	}

	public int getRowIndex(HSSFSheet sheet, String strReference) {
		try {
			HSSFRow row;
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				if (row.getCell(0).toString().trim().equals(strReference.trim())) {
					return row.getRowNum();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
