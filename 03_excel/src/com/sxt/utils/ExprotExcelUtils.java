package com.sxt.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.sxt.domain.User;

public class ExprotExcelUtils {

	static HSSFWorkbook workbook = new HSSFWorkbook();
	static HSSFCellStyle baseStyle = ExcelStyleUtils.getBaseStyle(workbook);
	static HSSFCellStyle titleStyle = ExcelStyleUtils.getTitleStyle(workbook);
	static HSSFCellStyle subTitleStyle = ExcelStyleUtils.getSubTitleStyle(workbook);
	static HSSFCellStyle tableHeadStyle = ExcelStyleUtils.getTableHeaderStyle(workbook);

	/**
	 * 导出数据方法
	 */
	public static void exportObject(List<?> lists, String titleName, String sheetName, String fileName) {
		try {
			// 创建sheet
			HSSFSheet sheet = workbook.createSheet(sheetName);
			// 设置sheet的样式
			sheet.setDefaultColumnWidth(30);
			// sheet.setDefaultRowHeightInPoints(20);
			// 设置合并
			CellRangeAddress range1 = new CellRangeAddress(0, 0, 0, 3);
			CellRangeAddress range2 = new CellRangeAddress(1, 1, 0, 3);
			sheet.addMergedRegion(range1);
			sheet.addMergedRegion(range2);
			// 写数据
			// 第一行
			int row = 0;
			HSSFRow row0 = sheet.createRow(row);
			HSSFCell row0_cell0 = row0.createCell(0);
			row0_cell0.setCellStyle(titleStyle);
			row0_cell0.setCellValue(titleName);
			// 第二行
			row++;
			HSSFRow row1 = sheet.createRow(row);
			HSSFCell row1_cell0 = row1.createCell(0);
			row1_cell0.setCellStyle(subTitleStyle);
			row1_cell0.setCellValue("总数:" + lists.size() + ",  导出时间:" + new Date().toLocaleString());
			// 第三行
			row++;
			HSSFRow row2 = sheet.createRow(row);
			if (null != lists && lists.size() > 0) {
				Object obj = lists.get(0);
				Field[] fields = obj.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					String key = obj.getClass().getSimpleName() + "." + fields[i].getName();
					HSSFCell cell = row2.createCell(i);
					cell.setCellStyle(tableHeadStyle);
					cell.setCellValue(PropertiesUtils.getProperty(key));
				}

				// 第四行
				for (int i = 0; i < lists.size(); i++) {
					Object object = lists.get(i);
					row++;
					HSSFRow rowx = sheet.createRow(row);
					for (int j = 0; j < fields.length; j++) {
						Field field = fields[j];
						field.setAccessible(true);
						Object value = field.get(object);
						// 第一列
						HSSFCell rowx_cell0 = rowx.createCell(j);
						rowx_cell0.setCellStyle(baseStyle);
						if(field.getType().getSimpleName().equals("Date")) {
							Date d=(Date) value;
							rowx_cell0.setCellValue(d.toLocaleString());
						}else {
							rowx_cell0.setCellValue(value.toString());
						}
					}
				}
			}
			workbook.write(new File("D:/", fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
