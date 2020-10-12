package com.authine.cloudpivot.web.api.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @Author:wangyong
 * @Date:2020/3/20 14:30
 * @Description: excel表格工具类
 */
public class ExcelUtils {

    public static XSSFRow getTableTtileRow(XSSFSheet sheet, int rowNum, List<String> titles) {
        XSSFRow row = sheet.createRow(rowNum);

        for (int i = 0; i < titles.size(); i++) {
            row.createCell(i).setCellValue(titles.get(i));
        }

        return row;
    }

    public static XSSFCellStyle getStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        


        return cellStyle;
    }

    public static void setCellStyle(XSSFWorkbook workbook, XSSFRow row) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            row.getCell(i).setCellStyle(ExcelUtils.getStyle(workbook));
        }
    }

}
