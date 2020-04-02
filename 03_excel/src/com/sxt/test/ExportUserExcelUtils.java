package com.sxt.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import com.sxt.domain.User;

/**
 * 导出用户数据
 * @author LJH
 *
 */
public class ExportUserExcelUtils {

	/**
	 * 导出用户数据方法
	 */
	public static void exportUsers(List<User> users,String titleName,String sheetName,String fileName) {
		//创建一个簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);
		//设置sheet的样式
		sheet.setDefaultColumnWidth(30);
//		sheet.setDefaultRowHeightInPoints(20);
		//设置合并
		CellRangeAddress range1=new CellRangeAddress(0, 0, 0, 3);
		CellRangeAddress range2=new CellRangeAddress(1, 1, 0, 3);
		sheet.addMergedRegion(range1);
		sheet.addMergedRegion(range2);
		//写数据
		//第一行
		int row=0;
		HSSFRow row0 = sheet.createRow(row);
		HSSFCell row0_cell0 = row0.createCell(0);
		row0_cell0.setCellStyle(getTitleStyle(workbook));
		row0_cell0.setCellValue(titleName);
		//第二行
		row++;
		HSSFRow row1 = sheet.createRow(row);
		HSSFCell row1_cell0 = row1.createCell(0);
		row1_cell0.setCellStyle(getSubTitleStyle(workbook));
		row1_cell0.setCellValue("总数:"+users.size()+",  导出时间:"+new Date().toLocaleString());
		//第三行
		row++;
		HSSFRow row2 = sheet.createRow(row);
		String [] titles= {"用户ID","用户姓名","用户地址","入职时间"};
		HSSFCellStyle tableHeaderStyle = getTableHeaderStyle(workbook);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row2.createCell(i);
			cell.setCellStyle(tableHeaderStyle);
			cell.setCellValue(titles[i]);
		}
		//第四行
		HSSFCellStyle baseStyle = getBaseStyle(workbook);
		for (int i = 0; i < users.size(); i++) {
			User user=users.get(i);
			row++;
			HSSFRow rowx = sheet.createRow(row);
			//第一列
			HSSFCell rowx_cell0 = rowx.createCell(0);
			rowx_cell0.setCellStyle(baseStyle);
			rowx_cell0.setCellValue(user.getId());
			
			//第二列
			HSSFCell rowx_cell1 = rowx.createCell(1);
			rowx_cell1.setCellStyle(baseStyle);
			rowx_cell1.setCellValue(user.getName());
			
			//第三列
			HSSFCell rowx_cell2 = rowx.createCell(2);
			rowx_cell2.setCellStyle(baseStyle);
			rowx_cell2.setCellValue(user.getAddress());
			
			//第四列
			HSSFCell rowx_cell3 = rowx.createCell(3);
			rowx_cell3.setCellStyle(baseStyle);
			rowx_cell3.setCellValue(user.getHiredate().toLocaleString());
		}
		try {
			workbook.write(new File("D:/", fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 水平和垂直居中
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle getBaseStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}
	
	/**
	 * 基础字体
	 */
	public static HSSFFont getBaseFont(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		font.setFontHeightInPoints((short)16);
		return font;
	}
	
	
	/**
	 * 标题样式
	 */
	public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = getBaseStyle(workbook);
		
		HSSFFont font = getBaseFont(workbook);
		font.setBold(true);
		font.setFontName("华文行楷");
		font.setFontHeightInPoints((short)35);
		style.setFont(font);
		
		return style;
	}
	/**
	 * 小标题样式
	 */
	public static HSSFCellStyle getSubTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = getBaseStyle(workbook);
		HSSFFont font = getBaseFont(workbook);
		font.setBold(true);
		font.setFontName("幼圆");
		font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
		font.setFontHeightInPoints((short)20);
		style.setFont(font);
		return style;
	}
	/**
	 * 表头样式
	 */
	public static HSSFCellStyle getTableHeaderStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = getBaseStyle(workbook);
		HSSFFont font = getBaseFont(workbook);
		font.setBold(true);
		font.setFontName("黑体");
		font.setColor(HSSFColor.HSSFColorPredefined.BRIGHT_GREEN.getIndex());
		font.setFontHeightInPoints((short)25);
		style.setFont(font);
		return style;
	}
	
	
	
	
	
	public static void main(String[] args) {
		List<User> users=new ArrayList<>();
		for (int i = 1; i <=10; i++) {
			users.add(new User(i, "小明"+i, "武汉"+i, new Date()));
		}
		exportUsers(users, "用户数据", "用户数据", "所有用户数据.xls");
		
	}
	
}
