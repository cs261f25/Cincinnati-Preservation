package com.cincypreservation;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import java.io.File;

public class FormPanel extends JPanel implements ActionListener {
    private JButton b1,b2; 
    private JTextField propertyNameField, streetAddressField, propertyOwnerField, inspectorNameField,inspectionDateField;
    private JTextField overallConditionField, commentsField, adviceField, followUpActivityField;
    private JLabel filepathgui;
    private String filePath = "-1";
    private Font bold = new Font("Arial", Font.BOLD,12);

    // private ArrayList<JComponent> components = new ArrayList<>();
    private final static Dimension BUTTON_DIMENSIONS = new Dimension(200,50);
    private final static Dimension TEXTBOX_DIMENSIONS = new Dimension(200,20);
    public FormPanel(){
        initializeGUI();
    }

    private void initializeGUI(){
        // Layout
        setLayout(new GridBagLayout());
        JPanel contentPanel = new JPanel();
        JPanel infopanel = new JPanel();
        JPanel assessmentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        infopanel.setLayout(new BoxLayout(infopanel, BoxLayout.Y_AXIS));
        assessmentPanel.setLayout(new BoxLayout(assessmentPanel, BoxLayout.Y_AXIS));
        // UI elements
        b1 = initializeButton(b1, "Choose Excel File");
        b2 = initializeButton(b2, "Write to Excel File");
        filepathgui = initializeJLabel(filepathgui, "File Path: No file selected");

        // Basic Info Field Initialization
        propertyNameField = initializTextField(propertyNameField);
        streetAddressField = initializTextField(streetAddressField);
        propertyOwnerField = initializTextField(propertyOwnerField);
        inspectorNameField = initializTextField(inspectorNameField);
        inspectionDateField = initializTextField(inspectionDateField);

        // General Assessment Field (WIP: Will replace with check boxes)
        overallConditionField = initializTextField(overallConditionField);
        commentsField = initializTextField(commentsField);
        adviceField = initializTextField(adviceField);
        followUpActivityField = initializTextField(followUpActivityField);

        // Panel Construction
        contentPanel.add(createJLabelOnCenter("File Writer - Write to Excel Form"));
        contentPanel.add(b1);
        contentPanel.add(filepathgui);
        contentPanel.add(createJLabelOnCenter("Fill out Form information Below"));
        contentPanel.add(Box.createRigidArea(new Dimension(5,15)));

        JLabel title1 = createJLabelOnCenter("I - General Information");
        title1.setFont(bold);
        infopanel.add(title1);

        infopanel.add(createJLabelOnCenter("Property Name"));
        infopanel.add(propertyNameField);
        infopanel.add(createJLabelOnCenter("Street Address"));
        infopanel.add(streetAddressField);
        infopanel.add(createJLabelOnCenter("Property Owner"));
        infopanel.add(propertyOwnerField);
        infopanel.add(createJLabelOnCenter("Inspector Name"));
        infopanel.add(inspectorNameField);
        infopanel.add(createJLabelOnCenter("Inspection Date (MM/DD/YYYY)"));
        infopanel.add(inspectionDateField);
        infopanel.add(Box.createRigidArea(new Dimension(5,15)));

        JLabel title2 = createJLabelOnCenter("II - General Assessment");
        title2.setFont(bold);
        assessmentPanel.add(title2);

        assessmentPanel.add(createJLabelOnCenter("Overall Condition"));
        assessmentPanel.add(overallConditionField);
        assessmentPanel.add(createJLabelOnCenter("Comments"));
        assessmentPanel.add(commentsField);
        assessmentPanel.add(createJLabelOnCenter("Advice"));
        assessmentPanel.add(adviceField);
        assessmentPanel.add(createJLabelOnCenter("Follow Up Activity"));
        assessmentPanel.add(followUpActivityField);
        assessmentPanel.add(Box.createRigidArea(new Dimension(5,15)));

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        fieldPanel.add(infopanel);
        fieldPanel.add(Box.createRigidArea(new Dimension(25,25)));
        fieldPanel.add(assessmentPanel);

        contentPanel.add(fieldPanel);
        contentPanel.add(b2);
        add(contentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        FileReader fr = new FileReader();
        if (o == b1) {
            File selectedFile = new FileBrowser().selectFile();
            filePath = selectedFile.getAbsolutePath();
            filepathgui.setText("File Path: " + filePath);
        } else if (o == b2) {
            if (!filePath.equals("-1")){
                Form form = new Form();
                
                // Set Basic Info
                form.setPropertyName(propertyNameField.getText());
                form.setStreetAddress(streetAddressField.getText());
                form.setPropertyOwner(propertyOwnerField.getText());
                form.setInspectorName(inspectorNameField.getText());
                form.setInspectionDate(inspectionDateField.getText());
                // Set General Assessment
                form.setOverallCondition(overallConditionField.getText());
                form.setComments(commentsField.getText());
                form.setAdvice(adviceField.getText());
                form.setFollowUpActivity(followUpActivityField.getText());
                // Write to Form
                fr.writeForm(filePath, form);

            } else { 
                JOptionPane.showMessageDialog(this,"File not selected! Choose an Excel file before submitting.");
            }
        }
    }


    // Helper methods for initializing UI components

    private JButton initializeButton(JButton b,String label){
        b = new JButton(label);
        b.setPreferredSize(BUTTON_DIMENSIONS);
        b.setActionCommand(label);
        b.addActionListener(this);
        b.setAlignmentX(CENTER_ALIGNMENT);
        return b;
    }

    private JTextField initializTextField(JTextField f){
        f = new JTextField(20);
        f.setText("");
        f.setMaximumSize(TEXTBOX_DIMENSIONS);
        f.setAlignmentX(CENTER_ALIGNMENT);
        return f;
    }

    private JLabel initializeJLabel(JLabel l,String label){
        l = new JLabel(label);
        l.setAlignmentX(CENTER_ALIGNMENT);
        return l;
    }

    private JLabel createJLabelOnCenter(String label){
        JLabel l = new JLabel(label);
        l.setAlignmentX(CENTER_ALIGNMENT);
        return l;
    }
}
