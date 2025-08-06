package com.byzwiz.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtils {

    public static Object[][] getData(String excelFilePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount];

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (int colIndex = 0; colIndex < colCount; colIndex++) {
                Cell cell = row.getCell(colIndex);
                data[rowIndex][colIndex] = getCellValueAsString(cell);
            }
            rowIndex++;
        }

        workbook.close();
        fis.close();

        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
