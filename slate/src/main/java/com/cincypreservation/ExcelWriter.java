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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    private final String baseDirectory;

    public ExcelWriter(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public void saveInspection(InspectionData data) throws IOException {

        // directory where JAR is running
        File dir = getJarDirectory();

        // Ensure directory exists
        if (!dir.exists()) {
            dir.mkdirs();  // Will not fail even inside JAR
        }

        // Safe filename
        String sanitized = data.propertyName.replaceAll("[^a-zA-Z0-9\\-_]", "_");
        if (sanitized.isBlank()) {
            sanitized = "UnnamedProperty";
        }

        File file = new File(dir, sanitized + ".xlsx");

        Workbook workbook;

        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(in);
            }
        } else {
            workbook = new XSSFWorkbook();
        }

        //separate sheet for each inspection
        String sheetName = "Inspection " + data.inspectionDate;
        sheetName = makeUniqueSheetName(workbook, sheetName);

        Sheet sheet = workbook.createSheet(sheetName);

        int rowIndex = 0;

        CellStyle bold = createBoldStyle(workbook);

        //save general info
        rowIndex = writeSectionHeader(sheet, rowIndex, "General Property Information", bold);

        rowIndex = writeRow(sheet, rowIndex, "Property Name", data.propertyName);
        rowIndex = writeRow(sheet, rowIndex, "Address", data.propertyAddress);
        rowIndex = writeRow(sheet, rowIndex, "Owner", data.ownerName);
        rowIndex = writeRow(sheet, rowIndex, "Inspector Name", data.inspectorName);
        rowIndex = writeRow(sheet, rowIndex, "Inspection Date", data.inspectionDate);

        rowIndex += 2;

        //save building section ratings
        rowIndex = writeSectionHeader(sheet, rowIndex, "Building Section Ratings", bold);

        Row head = sheet.createRow(rowIndex++);
        head.createCell(0).setCellValue("Section Name");
        head.createCell(1).setCellValue("Rating");

        for (InspectionData.SectionRating s : data.sections) {
            Row r = sheet.createRow(rowIndex++);
            r.createCell(0).setCellValue(s.sectionName);
            r.createCell(1).setCellValue(s.rating);
        }

        rowIndex += 2;

        //save additional comments
        rowIndex = writeSectionHeader(sheet, rowIndex, "Final Assessment", bold);

        rowIndex = writeRow(sheet, rowIndex, "Overall Condition", data.overallCondition);

        rowIndex += 1;
        rowIndex = writeSectionHeader(sheet, rowIndex, "Comments", bold);
        rowIndex = writeMultiline(sheet, rowIndex, data.comments);

        rowIndex += 1;
        rowIndex = writeSectionHeader(sheet, rowIndex, "Follow-Up Notes", bold);
        rowIndex = writeMultiline(sheet, rowIndex, data.followUpNotes);

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        try (FileOutputStream out = new FileOutputStream(file)) {
            workbook.write(out);
        }

        workbook.close();
    }

    private static File getJarDirectory() {
        try {
            String path = ExcelWriter.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
    
            File jarFile = new File(path);
            return jarFile.getParentFile();   // folder containing the JAR
        } catch (Exception e) {
            return new File(".");  // fallback to working directory
        }
    }

    private String makeUniqueSheetName(Workbook wb, String name) {
        String base = name;
        int n = 1;

        while (wb.getSheet(name) != null) {
            name = base + " (" + n++ + ")";
        }
        return name;
    }

    private int writeSectionHeader(Sheet sheet, int index, String title, CellStyle style) {
        Row r = sheet.createRow(index++);
        Cell c = r.createCell(0);
        c.setCellValue(title);
        c.setCellStyle(style);
        return index;
    }

    private int writeRow(Sheet sheet, int index, String label, String value) {
        Row r = sheet.createRow(index++);
        r.createCell(0).setCellValue(label);
        r.createCell(1).setCellValue(value != null ? value : "");
        return index;
    }

    private int writeMultiline(Sheet sheet, int index, String text) {
        if (text == null) text = "";
        Row r = sheet.createRow(index++);
        Cell cell = r.createCell(0);
        cell.setCellValue(text);

        CellStyle wrap = sheet.getWorkbook().createCellStyle();
        wrap.setWrapText(true);
        cell.setCellStyle(wrap);

        int lines = Math.max(2, text.split("\n").length);
        r.setHeightInPoints(lines * sheet.getDefaultRowHeightInPoints());

        return index;
    }

    private CellStyle createBoldStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}