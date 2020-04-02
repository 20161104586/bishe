package com.sxt.test;

import java.io.File;
import java.io.IOException;

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

public class TestExportExcel {

	public static void main(String[] args) {
		//1,创建工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//2,在工作簿上面创建sheet
		//workbook.createSheet();
		HSSFSheet sheet = workbook.createSheet("学生信息");  //创建sheet并重命名
			//相关设置
		sheet.setDefaultColumnWidth(30);//设置所有列的默认宽度
//		sheet.setDefaultRowHeight((short)(40*20));//设置默认行高  为设置值的1/20
		sheet.setDefaultRowHeightInPoints(40); //设置行高可以精确到小数
		
		//		sheet.setColumnWidth(columnIndex, width);//设置columnIndex下标处的列的宽度  值为设置值的1/256
//		sheet.setColumnHidden(columnIndex, hidden);//设置columnIndex下标处提列是否隐藏
		//合并A1-D1
		CellRangeAddress region=new CellRangeAddress(0, 0, 0, 3);
		sheet.addMergedRegion(region);
		
		//合并D2-7
		sheet.addMergedRegion(new CellRangeAddress(1, 6, 3, 3));
		
		//3,在sheet里面创建行
		HSSFRow row = sheet.createRow(0);
//		row.setHeight(height);//设置当前行的行高  值为设置值的1/20
		row.setHeightInPoints(60);//设置当前行的行高 值可以精确到小数
		HSSFCell cell0 = row.createCell(0);
		HSSFCellStyle style=workbook.createCellStyle();
		//设置样式
		style.setVerticalAlignment(VerticalAlignment.CENTER);  //设置垂直居中
		style.setAlignment(HorizontalAlignment.CENTER);//设置水平居中
		
		//创建字体对象
		HSSFFont font=workbook.createFont();
		font.setBold(true);//是否加粗
//		font.setColor(HSSFFont.COLOR_RED);
		font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
//		font.setFontHeight(height);
		font.setFontHeightInPoints((short)30);
		font.setFontName("华文彩云");
		font.setItalic(true);//是否斜体
		font.setUnderline(HSSFFont.U_DOUBLE); //设置下滑线
		style.setFont(font);
		
		cell0.setCellStyle(style);
		
		//向单元格里面放内容
		cell0.setCellValue("所有学生信息列表");
		
		//保存
		try {
			workbook.write(new File("D:/user.xls"));
			System.out.println("导出成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		workbook.write(stream);
		
		
	}

}
