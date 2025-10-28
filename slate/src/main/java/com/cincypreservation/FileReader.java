package com.cincypreservation;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReader {
    
    public static void main(String[] args) {
        // Get file path from FileBrowser
        File selectedFile = FileBrowser.selectFile();
        
        if (selectedFile == null) {
            System.out.println("No file selected. Exiting.");
            return;
        }
        
        String filePath = selectedFile.getAbsolutePath();
        System.out.println("Selected file: " + filePath);
        
        // Perform read/write operations
        processExcelFile(filePath);
    }
    
    /**
     * Reads from and writes to the specified Excel file
     * @param filePath The path to the Excel file
     */
    public static void processExcelFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            
            // Read value from A1
            Row row = sheet.getRow(0);
            if (row != null) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String value = cell.toString();
                    System.out.println("Value in A1: " + value);
                }
            }
            
            // Write to B1
            if (row == null) {
                row = sheet.createRow(0);
            }
            Cell cellB1 = row.createCell(1);
            cellB1.setCellValue("Sample text");
            
            // Save the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
            System.out.println("Successfully wrote to B1");
            
        } catch (IOException e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Reads a value from a specific cell in the Excel file
     * @param filePath The path to the Excel file
     * @param sheetIndex The sheet index (0-based)
     * @param rowIndex The row index (0-based)
     * @param colIndex The column index (0-based)
     * @return The cell value as a String, or null if not found
     */
    public static String readCell(String filePath, int sheetIndex, int rowIndex, int colIndex) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowIndex);
            
            if (row != null) {
                Cell cell = row.getCell(colIndex);
                if (cell != null) {
                    return cell.toString();
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error reading cell: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Writes a value to a specific cell in the Excel file
     * @param filePath The path to the Excel file
     * @param sheetIndex The sheet index (0-based)
     * @param rowIndex The row index (0-based)
     * @param colIndex The column index (0-based)
     * @param value The value to write
     */
    public static void writeCell(String filePath, int sheetIndex, int rowIndex, int colIndex, String value) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowIndex);
            
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(value);
            
            // Save the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
            System.out.println("Successfully wrote to cell [" + rowIndex + "," + colIndex + "]");
            
        } catch (IOException e) {
            System.err.println("Error writing cell: " + e.getMessage());
            e.printStackTrace();
        }
    }
}