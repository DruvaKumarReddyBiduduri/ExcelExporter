package org.drublip.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.drublip.models.Entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ExcelService {
    Workbook workbook;

    public ExcelService() {
        workbook = new XSSFWorkbook();
    }

    /**
     * Writes the workbook to a file , if Excel file does not exist it will be created.
     */
    public void writeToFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the header row of an Excel sheet with the given headers.
     */
    public void setSheetHeader(String[] headers, Sheet sheet) {
        Row sheetHeader = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell column = sheetHeader.createCell(i);
            column.setCellValue(headers[i]);
        }
    }

    /**
     * Sets the width of each column in the given sheet to a fixed value.
     */
    public void setColumnWidth(Sheet sheet, int columns) {
        for (int i = 0; i < columns; i++) {
            sheet.setColumnWidth(i, 6000);
        }
    }

    /**
     * Writes data to an Excel sheet.
     * This function creates a new sheet with the given name and fills it with the data from the provided list of entities.
     */
    public void toSheet(String sheetName, String[] headers, ArrayList<Entity> sheetData) {
        Sheet sheet = workbook.createSheet(sheetName);
        setColumnWidth(sheet, headers.length);
        setSheetHeader(headers, sheet);
        fillSheetRows(sheet, sheetData, headers);
    }

    /**
     * Fills the given sheet with data from the provided list of entities.
     */
    public void fillSheetRows(Sheet sheet, ArrayList<Entity> data, String[] headers) {
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> rowData = data.get(i).toMap();
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < headers.length; j++) {
                String cellValue = rowData.get(headers[j]);
                Cell cell = row.createCell(j);
                cell.setCellValue(cellValue);
            }
        }
    }
}
