package com.jambocloud.qa.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    private Sheet sheet;

    public ExcelUtil(String filePath, String sheetName) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Cell cell = sheet.getRow(rowNum).getCell(colNum);
        return cell.toString();
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }
}
