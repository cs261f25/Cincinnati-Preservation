package com.cincypreservation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    private final String baseDirectory;


    public ExcelWriter(String baseDirectory) {
        this.baseDirectory = baseDirectory;
        File dir = new File(baseDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    //save inspection data to excel file
    public void saveInspection(InspectionData data) throws IOException {

        // get filename from property address
        String fileName = sanitizeFileName(data.propertyAddress) + ".xlsx";
        File file = new File(baseDirectory, fileName);

        Workbook workbook;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = WorkbookFactory.create(fis);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
        }

        // Create sheet name using inspection date or fallback
        String sheetName = createUniqueSheetName(workbook, data.inspectionDate);
        Sheet sheet = workbook.createSheet(sheetName);

        writeInspectionData(sheet, data);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    //write inspection data to current sheet
    private void writeInspectionData(Sheet sheet, InspectionData data) {
        int rowIndex = 0;

        rowIndex = writeRow(sheet, rowIndex, "Property Name", data.propertyName);
        rowIndex = writeRow(sheet, rowIndex, "Address", data.propertyAddress);
        rowIndex = writeRow(sheet, rowIndex, "Owner", data.ownerName);
        rowIndex = writeRow(sheet, rowIndex, "Inspector", data.inspectorName);
        rowIndex = writeRow(sheet, rowIndex, "Inspection Date", data.inspectionDate);

        rowIndex++;

        //section ratings
        writeHeader(sheet, rowIndex++, "Section Ratings");

        Row headerRow = sheet.createRow(rowIndex++);
        headerRow.createCell(0).setCellValue("Section");
        headerRow.createCell(1).setCellValue("Rating");

        for (InspectionData.SectionRating sr : data.sections) {
            Row r = sheet.createRow(rowIndex++);
            r.createCell(0).setCellValue(sr.sectionName);
            r.createCell(1).setCellValue(sr.rating);
        }

        rowIndex++;

        rowIndex = writeRow(sheet, rowIndex, "Overall Condition", data.overallCondition);
        rowIndex = writeRow(sheet, rowIndex, "Comments", data.comments);
        writeRow(sheet, rowIndex, "Follow-Up Notes", data.followUpNotes);
    }

    private int writeRow(Sheet sheet, int rowIndex, String label, String value) {
        Row row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue(label);
        row.createCell(1).setCellValue(value != null ? value : "");
        return rowIndex + 1;
    }

    private void writeHeader(Sheet sheet, int rowIndex, String title) {
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue(title);

        CellStyle style = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        style.setFont(font);

        cell.setCellStyle(style);
    }

    //ensure sheet name is unique
    private String createUniqueSheetName(Workbook workbook, String baseName) {
        if (baseName == null || baseName.isEmpty()) {
            baseName = "Inspection";
        }

        String name = baseName;
        int counter = 1;

        while (workbook.getSheet(name) != null) {
            name = baseName + " (" + counter++ + ")";
        }

        return name;
    }

    //make file name gud
    private String sanitizeFileName(String name) {
        if (name == null) return "unknown_property";
        return name.replaceAll("[\\\\/:*?\"<>|]", "_");
    }

    //return file for specified address
    public File findFileByAddress(String address) {
        String fileName = sanitizeFileName(address) + ".xlsx";
        File file = new File(baseDirectory, fileName);
        return file.exists() ? file : null;
    }
}