package com.cincypreservation;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FormPanel extends JPanel implements ActionListener {
    private Form form;
    private JButton getFileButton,draftButton,writeFormButton,emptyFieldsButton;
    private JTextField propertyNameField, streetAddressField, propertyOwnerField, inspectorNameField,inspectionDateField; // General Info Fields
    private JTextField overallConditionField, commentsField, adviceField, followUpActivityField; // General Assessment Fields
    private ArrayList<JTextField> textFields = new ArrayList<>();
    private JLabel filepathgui;
    private String filePath = "-1";
    private Font bold = new Font("Arial", Font.BOLD,12);

    // private ArrayList<JComponent> components = new ArrayList<>();
    private final static Dimension BUTTON_DIMENSIONS = new Dimension(200,25);
    private final static Dimension TEXTBOX_DIMENSIONS = new Dimension(200,20);
    public FormPanel(){
        form = new Form();
        initializeGUI();
    }

    private void initializeGUI(){
        // Layout
        setLayout(new GridBagLayout());
        JPanel contentPanel = new JPanel();
        JPanel infopanel = new JPanel();
        JPanel assessmentPanel = new JPanel();
        JPanel featurePanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        infopanel.setLayout(new BoxLayout(infopanel, BoxLayout.Y_AXIS));
        assessmentPanel.setLayout(new BoxLayout(assessmentPanel, BoxLayout.Y_AXIS));
        featurePanel.setLayout(new BoxLayout(featurePanel, BoxLayout.Y_AXIS));
        // UI elements
        getFileButton = initializeButton(getFileButton, "Choose Excel File");
        draftButton = initializeButton(draftButton, "Draft Changes");
        writeFormButton = initializeButton(writeFormButton, "Write Draft to Form");
        emptyFieldsButton = initializeButton(emptyFieldsButton, "Clear all Fields");
        filepathgui = initializeJLabel(filepathgui, "File Path: No file selected");
 
        // Basic Info Field Initialization
        propertyNameField = initializTextField(propertyNameField);
        streetAddressField = initializTextField(streetAddressField);
        propertyOwnerField = initializTextField(propertyOwnerField);
        inspectorNameField = initializTextField(inspectorNameField);
        inspectionDateField = initializTextField(inspectionDateField);

        // General Assessment Field 
        overallConditionField = initializTextField(overallConditionField);
        commentsField = initializTextField(commentsField);
        adviceField = initializTextField(adviceField);
        followUpActivityField = initializTextField(followUpActivityField);

        // Panel Construction
        contentPanel.add(createJLabelOnCenter("File Writer - Write to Excel Form"));
        contentPanel.add(getFileButton);
        contentPanel.add(filepathgui);
        contentPanel.add(createJLabelOnCenter("Fill out Form information Below"));

        JLabel title1 = createJLabelOnCenter("General Information");
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

        JLabel title2 = createJLabelOnCenter("General Assessment");
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

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        fieldPanel.add(infopanel);
        fieldPanel.add(Box.createRigidArea(new Dimension(25,25)));
        fieldPanel.add(assessmentPanel);
        fieldPanel.add(Box.createRigidArea(new Dimension(25,25)));
        fieldPanel.add(featurePanel);

        contentPanel.add(fieldPanel);
        contentPanel.add(emptyFieldsButton);
        contentPanel.add(draftButton);
        contentPanel.add(writeFormButton);
        add(contentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        FileReader fr = new FileReader();
        if (o == getFileButton) {
            getFile();
        } else if (o == draftButton) {
            if(isFileValid()){
                createFormDraft();
            }
        } else if (o == writeFormButton) {
            if(isFileValid()){
                writeForm(fr);
            }
        } else if (o == emptyFieldsButton) {
            emptyFields();
        } 
    }

    private void fillFieldsLoremIpsum(){
        final String loremipsum = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
        fillFields(loremipsum);
    }

    private void fillFields(String string){
        String[] stringarray = string.split(" ");
        for (int i = 0; i < textFields.size(); i++){
            String indice = stringarray[i];
            if (!indice.equals(null)){
                textFields.get(i).setText(indice);
            } else {
                textFields.get(i).setText("");
            }
        }        
    }

    private void emptyFields(){
        for (JTextField tf : textFields){
            tf.setText("");
        }
    }

    private void getFile() {
        File selectedFile = new FileBrowser().selectFile();
        filePath = selectedFile.getAbsolutePath();
        filepathgui.setText("File Path: " + filePath);
    }
    // to patch with a check for non-excel files
    private boolean isFileValid(){
        if(!filePath.equals("-1")){
            return true;
        } else { 
            JOptionPane.showMessageDialog(this,"File not selected! Choose an Excel file before writing.");
            return false;
        }
    }

    private void createFormDraft() {
        // Set Basic Info
        form.setPropertyName(propertyNameField.getText());
        form.setStreetAddress(streetAddressField.getText());
        form.setPropertyOwner(propertyOwnerField.getText());
        form.setInspectorName(inspectorNameField.getText());
        form.setInspectionDate(inspectionDateField.getText());
        form.setInspectionId("INSP-" + System.currentTimeMillis());
        // Set General Assessment
        form.setOverallCondition(overallConditionField.getText());
        form.setComments(commentsField.getText());
        form.setAdvice(adviceField.getText());
        form.setFollowUpActivity(followUpActivityField.getText());
    }
    
    private void writeForm(FileReader fr){
        fr.writeForm(filePath, form);
    }

    // Nested Class for Feature Panel, a panel with buttons that can perpetually add features to Form

    public class featurePanel extends JPanel {
        private RadioButtonArray northconditionarray,southconditionarray,westconditionarray,eastconditionarray;
        private JTextField featureNameField, featureDescriptionField;
        private ArrayList<RadioButtonArray> radiobuttons;
        private JButton submitfeaturebutton;
        public featurePanel(){
            initializeGUI();
        }
        public void initializeGUI(){
            
            featureNameField = initializTextField(featureNameField);
            featureDescriptionField = initializTextField(featureDescriptionField);

            radiobuttons = new ArrayList<>();
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            JLabel title3 = createJLabelOnCenter("Add a Feature");
            title3.setFont(bold);
            add(title3);

            add(createJLabelOnCenter("Feature Name"));
            add(featureNameField);
            add(createJLabelOnCenter("Feature Description"));
            add(featureDescriptionField);

            add(createJLabelOnCenter("North Condition"));
            northconditionarray = initializeCheckBoxArrayPanel(northconditionarray, "North Condition");
            add(northconditionarray);
            
            add(createJLabelOnCenter("South Condition"));
            southconditionarray = initializeCheckBoxArrayPanel(southconditionarray, "South Condition");
            add(southconditionarray);

            add(createJLabelOnCenter("East Condition"));
            eastconditionarray = initializeCheckBoxArrayPanel(eastconditionarray, "East Condition");
            add(eastconditionarray);

            add(createJLabelOnCenter("West Condition"));
            westconditionarray = initializeCheckBoxArrayPanel(westconditionarray, "West Condition");
            add(westconditionarray);

            add(createJLabelOnCenter(""));
            submitfeaturebutton = new JButton("Add a Feature");
            submitfeaturebutton.setAlignmentX(CENTER_ALIGNMENT);
            submitfeaturebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isFileValid()) {
                        System.out.println("New Feature created.");
                        System.out.println("Feature Name: " + featureNameField.getText());
                        System.out.println("Feature Description: " + featureDescriptionField.getText());
                        String outputString = "Conditions : ";
                        ArrayList<String> conditionStrings = new ArrayList<>();
                        for(RadioButtonArray rba : radiobuttons){
                            outputString = outputString + "\n" + rba.getName() + ": " + rba.getOutput();
                            conditionStrings.add(rba.getOutput());
                        }
                        Form.Feature feature = new Form.Feature(featureNameField.getText(),featureDescriptionField.getText(),conditionStrings.get(0),conditionStrings.get(2),conditionStrings.get(1),conditionStrings.get(3));
                        form.addFeature(feature);
                        System.out.println(outputString);
                        clearFields();
                    }
                }    
            });
            add(submitfeaturebutton);


        }

        public void clearFields(){
            featureNameField.setText("");
            featureDescriptionField.setText("");
            for(RadioButtonArray rba : radiobuttons){
                rba.clearFields();
            }
        }

        // A helper object containing multiple checkboxes that return one single output

        public RadioButtonArray initializeCheckBoxArrayPanel(RadioButtonArray rba, String name){
            rba = new RadioButtonArray(name);
            radiobuttons.add(rba);
            rba.setAlignmentX(CENTER_ALIGNMENT);
            return rba;
        }
        
        public class RadioButtonArray extends JPanel implements ItemListener {
            private String output = "";
            private final ButtonGroup group;
            private final JRadioButton nvBox,eBox,vgBox, gBox,pBox,vpBox;
            private final ArrayList<JRadioButton> radiobuttons;
            public RadioButtonArray(String name) {
                setName(name);
                setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
                radiobuttons = new ArrayList<>();
                group = new ButtonGroup();
                nvBox = initializeJRadioButton("Not Visible");
                eBox = initializeJRadioButton("Excellent");
                vgBox = initializeJRadioButton("Very Good");
                gBox = initializeJRadioButton("Good");
                pBox = initializeJRadioButton("Poor");
                vpBox = initializeJRadioButton("Very Poor");
                add(nvBox);
                add(eBox);
                add(vgBox);
                add(gBox);
                add(pBox);
                add(vpBox);
            }
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object o = e.getSource();
                int change = e.getStateChange();
                if (o == nvBox){
                    output = "NV";
                } else if (o == eBox){
                    output = "E";
                } else if (o == vgBox){
                    output = "VG";
                } else if (o == gBox){
                    output = "G";
                } else if (o == pBox){
                    output = "P";
                } else if (o == vpBox){
                    output = "VP";
                }
            }
            private JRadioButton initializeJRadioButton(String label){
                JRadioButton rb = new JRadioButton(label, null, false);
                rb.addItemListener(this);
                rb.setAlignmentX(CENTER_ALIGNMENT);
                radiobuttons.add(rb);
                group.add(rb);
                return rb;
            }
            private void clearFields(){
                output = "";
                group.clearSelection();
            }
            public String getOutput(){
                return output;
            }
        }
    }

    // Helper methods for initializing UI components

    private JButton initializeButton(JButton b,String label){
        b = new JButton(label);
        b.setPreferredSize(BUTTON_DIMENSIONS);
        b.setMinimumSize(BUTTON_DIMENSIONS);
        b.setMaximumSize(BUTTON_DIMENSIONS);
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
        textFields.add(f);
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
