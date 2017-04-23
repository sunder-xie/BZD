package com.bizideal.whoami.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelIO {

	public static int START_CELL;
	public static int LAST_CELL;

	/**
	 * 读取excel内容
	 * 
	 * @param inputStream
	 * @param d
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static ArrayList<ArrayList<String>> readExcel(
			InputStream inputStream, int d) throws IOException,
			InvalidFormatException {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Workbook book = WorkbookFactory.create(inputStream);
		Sheet sheet = book.getSheetAt(0);
		String value = null;
		Row row = null;
		Cell cell = null;
		int counter = 0;
		for (int i = sheet.getFirstRowNum(); counter < sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (null == row) {
				// 过滤为空的行
				continue;
			} else {
				counter++;
			}
			if (counter == 1) {
				START_CELL = row.getFirstCellNum();
				LAST_CELL = row.getLastCellNum();
			}
			ArrayList<String> linked = new ArrayList<String>();
			for (int j = START_CELL; j < LAST_CELL; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add("null");
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				DecimalFormat nf = new DecimalFormat("0.00");
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle()
							.getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell
								.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue() + "";
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
				if (StringUtils.isBlank(value)) {
					linked.add("null");
				} else {
					linked.add(value);
				}

			}
			list.add(linked);
		}
		return list;
	}

	public static Object changeType(String string, Class<?> clazz) {
		if ("null".equals(string)) {
			if ("int".equals(clazz.toString())) {
				return 0;
			} else {
				return "";
			}
		}
		if ("int".equals(clazz.toString())) {
			int a = 0;
			try {
				String[] split = StringUtils.split(string, ".");
				a = Integer.valueOf(split[0]);
			} catch (Exception e) {
				return 0;
			}
			return a;
		} else {
			return string;
		}
	}

}
