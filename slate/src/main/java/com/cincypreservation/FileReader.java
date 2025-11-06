package com.cincypreservation;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FileReader extends JPanel implements ActionListener{
    private JButton b1;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private Dimension BUTTON_DIMENSIONS = new Dimension(200,50);
    public FileReader(){
        initialize();
    }
    private void initialize(){
        b1 = initializeButton(b1, "Launch File Reader");
        add(b1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == b1) {
            main(null);
        }
    }

    private JButton initializeButton(JButton b,String label){
        b = new JButton(label);
        b.setPreferredSize(BUTTON_DIMENSIONS);
        b.setActionCommand(label);
        b.addActionListener(this);
        buttons.add(b);
        return b;
    }

    public static void main(String[] args) {

        // Get file path from FileBrowser
        File selectedFile = FileBrowser.selectFile();
        
        if (selectedFile == null) {
            System.out.println("No file selected. Exiting.");
            return;
        }
        
        String filePath = selectedFile.getAbsolutePath();
        System.out.println("Selected file: " + filePath);
        
        // Collect user input using Form
        Form form = new Form();
        form.basicInfo();
        
        // Write collected data to the selected Excel file
        writeBasicInfo(filePath, form);
        writeFeatureInspections(filePath, form);
        writeGeneralAssessment(filePath, form);
    }
    
    /**
     * Writes basic info collected from Form to the Excel file
     * @param filePath The path to the Excel file
     * @param form The Form object containing collected data
     */
    public static void writeBasicInfo(String filePath, Form form) {    
        // Write property information to cells in column B
        writeCell(filePath, 0, 0, 1, form.getPropertyName());      // B1 - Property Name
        writeCell(filePath, 0, 1, 1, form.getStreetAddress());     // B2 - Property Address
        writeCell(filePath, 0, 2, 1, form.getPropertyOwner());     // B3 - Property Owner

        // Write inspector information to cells in column F
        writeCell(filePath, 0, 0, 5, form.getInspectorName());     // F1 - Inspector Name
        writeCell(filePath, 0, 1, 5, form.getInspectionId());      // F2 - Inspector ID
        writeCell(filePath, 0, 2, 5, form.getInspectionDate());    // F3 - Inspection Date
        
        System.out.println("All basic information written to Excel file.");
    }
    
    public static void writeFeatureInspections(String filePath, Form form) {    
        // TO BE ADDED LATER
    }

    public static void writeGeneralAssessment(String filePath, Form form) {        
        // Write general assessment information to cells in column B
        writeCell(filePath, 0, 6, 1, form.getOverallCondition());   // B7 - Overall Condition
        writeCell(filePath, 0, 7, 1, form.getComments());           // B8 - Comments
        writeCell(filePath, 0, 8, 1, form.getAdvice());             // B9 - Advice
        writeCell(filePath, 0, 9, 1, form.getFollowUpActivity());   // B10 - Follow-Up Activity
     
        System.out.println("All general assessment information written to Excel file.");
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