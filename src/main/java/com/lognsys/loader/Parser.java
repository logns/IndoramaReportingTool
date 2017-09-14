package com.lognsys.loader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Parser {


	/**
	 * 
	 * Return Cell Value from
	 * 
	 * @param cell
	 * @return String cell value
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = "";

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			cellValue = cell.getStringCellValue();
			break;

		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;

		case Cell.CELL_TYPE_NUMERIC:
			Double val = cell.getNumericCellValue();
			cellValue = Integer.toString(val.intValue());
			break;
		}

		return cellValue;

	}

}
