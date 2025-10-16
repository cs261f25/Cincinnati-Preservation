package com.cincypreservation;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReader {
    
    private static final String FILE_PATH = System.getProperty("user.home") + "\\Documents\\example.xlsx";
    
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            
            // Read value from A1
            Row row = sheet.getRow(0);
            if (row != null) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String value = cell.toString();
                    System.out.println(value);
                }
            }
            
            // Write to B1
            if (row == null) {
                row = sheet.createRow(0);
            }
            Cell cellB1 = row.createCell(1);
            cellB1.setCellValue("Sample text");
            
            // Save the file
            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }
            
            System.out.println("Successfully wrote to B1");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}