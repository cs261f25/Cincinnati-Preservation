package com.cincypreservation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Template for creating a new inspection form, uses CardLayout class to create different sections for inspectors to enter info
 * and buttons to traverse through the cards. On finish, collects all data to create an InspectionForm object to pass to the ExcelWriter.
 */
public class NewForm extends JPanel {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    private JButton backBtn;
    private JButton nextBtn;
    private JButton finishBtn;

    private int currentCard = 0;
    private final int TOTAL_CARDS = 3;

    JTextField nameField;
    JTextField addressField;
    JTextField ownerField;
    JTextField inspNameField;
    JTextField inspDateField;

    private List<SectionRow> sectionRows = new ArrayList<>();
    private JPanel sectionsListPanel;

    JTextField overallConditionField;
    JTextField otherCommentsField;
    JTextField followUpInfoField;

    JFrame parentFrame;
    private AddressHandler ah;

    public NewForm(String address, AddressHandler ah, JFrame parentFrame) {
        this.ah = ah;
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        //init cards
        cardPanel.add(createGeneralInfoPanel(), "general");
        cardPanel.add(createSectionsPanel(), "sections");
        cardPanel.add(createInspectorPanel(), "inspector");

        add(cardPanel, BorderLayout.CENTER);
        add(createNavigationPanel(), BorderLayout.SOUTH);

        cardLayout.show(cardPanel, "general");
    }

    //general property/inspection info
    private JPanel createGeneralInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("General Property Information"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        nameField = new JTextField(20);
        addressField = new JTextField(20);
        ownerField = new JTextField(20);
        inspNameField = new JTextField(20);
        inspDateField = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Property Name:"), gbc);
        gbc.gridx = 1; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Street Address:"), gbc);
        gbc.gridx = 1; panel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Property Owner:"), gbc);
        gbc.gridx = 1; panel.add(ownerField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Inspector Name:"), gbc);
        gbc.gridx = 1; panel.add(inspNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Inspection Date:"), gbc);
        gbc.gridx = 1; panel.add(inspDateField, gbc);

        return wrap(cardPanelSize(panel));
    }

    //allows user to enter as many features and conditions as they want
    private JPanel createSectionsPanel() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createTitledBorder("Building Sections Ratings"));

        sectionsListPanel = new JPanel();
        sectionsListPanel.setLayout(new BoxLayout(sectionsListPanel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(sectionsListPanel);

        JButton addSectionButton = new JButton("Add Section");
        addSectionButton.addActionListener(e -> addNewSectionRow());

        container.add(scroll, BorderLayout.CENTER);
        container.add(addSectionButton, BorderLayout.SOUTH);

        return wrap(cardPanelSize(container));
    }

    private void addNewSectionRow() {
        SectionRow row = new SectionRow();
        sectionRows.add(row);
        sectionsListPanel.add(row);
        sectionsListPanel.revalidate();
        sectionsListPanel.repaint();
    }

    //organize section row
    private static class SectionRow extends JPanel {
        JTextField nameField = new JTextField(15);
        JComboBox<String> ratingBox;

        SectionRow() {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            String[] ratings = { "Bad", "Poor", "Fair", "Good", "Very Good" };
            ratingBox = new JComboBox<>(ratings);

            add(new JLabel("Section:"));
            add(nameField);
            add(new JLabel("Rating:"));
            add(ratingBox);
        }
    }

    //final comments from inspector
    private JPanel createInspectorPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Final Comments"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        overallConditionField = new JTextField(20);
        otherCommentsField = new JTextField(20);
        followUpInfoField = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Overall Condition:"), gbc);
        gbc.gridx = 1; panel.add(overallConditionField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(new JLabel("Additional Comments:"), gbc);

        JTextArea commentsArea = new JTextArea(6, 40);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane commentsScroll = new JScrollPane(commentsArea);
        gbc.gridy = 4;
        panel.add(commentsScroll, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Follow-Up Information:"), gbc);

        JTextArea followUpArea = new JTextArea(6, 40);
        followUpArea.setLineWrap(true);
        followUpArea.setWrapStyleWord(true);
        JScrollPane followUpScroll = new JScrollPane(followUpArea);
        gbc.gridy = 6;
        panel.add(followUpScroll, gbc);

        return wrap(cardPanelSize(panel));
    }

    //navigation buttons
    private JPanel createNavigationPanel() {
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backBtn = new JButton("Back");
        nextBtn = new JButton("Next");
        finishBtn = new JButton("Finish");

        backBtn.setEnabled(false);
        finishBtn.setVisible(false);

        backBtn.addActionListener(e -> goBack());
        nextBtn.addActionListener(e -> goNext());
        finishBtn.addActionListener(e -> finishWizard());

        nav.add(backBtn);
        nav.add(nextBtn);
        nav.add(finishBtn);

        return nav;
    }
    //previous card
    private void goBack() {
        if (currentCard > 0) {
            currentCard--;
            updateCards();
        }
    }
    //next card
    private void goNext() {
        if (currentCard < TOTAL_CARDS - 1) {
            currentCard++;
            updateCards();
        }
    }
    //switch between cards and update buttons
    private void updateCards() {
        switch (currentCard) {
            case 0 -> cardLayout.show(cardPanel, "general");
            case 1 -> cardLayout.show(cardPanel, "sections");
            case 2 -> cardLayout.show(cardPanel, "inspector");
        }

        backBtn.setEnabled(currentCard > 0);
        nextBtn.setVisible(currentCard < TOTAL_CARDS - 1);
        finishBtn.setVisible(currentCard == TOTAL_CARDS - 1);
    }

    private void finishWizard() {
        //build inspection data object
        InspectionData data = new InspectionData();
    
        //general info
        data.propertyName = nameField.getText().trim();
        data.propertyAddress = addressField.getText().trim();
        data.ownerName = ownerField.getText().trim();
        data.inspectorName = inspNameField.getText().trim();
        data.inspectionDate = inspDateField.getText().trim();
    
        //sections
        for (SectionRow row : sectionRows) {
            InspectionData.SectionRating rating = new InspectionData.SectionRating();
            rating.sectionName = row.nameField.getText().trim();
            rating.rating = row.ratingBox.getSelectedItem().toString();
            data.sections.add(rating);
        }
    
        //inspector comments panel
        data.overallCondition = overallConditionField.getText().trim();
        data.comments = otherCommentsField.getText().trim();
        data.followUpNotes = followUpInfoField.getText().trim();
    
        //save to excel file
        try {
            ExcelWriter ew = new ExcelWriter("~/");
            ew.saveInspection(data);
            JOptionPane.showMessageDialog(this, "Form saved successfully!");
            ah.addAddress(addressField.getText().trim());
            parentFrame.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
        }
    }

    //keep panels sized consistently
    private JPanel wrap(JPanel inner) {
        JPanel outer = new JPanel(new BorderLayout());
        outer.add(inner, BorderLayout.NORTH);
        return outer;
    }

    private JPanel cardPanelSize(JPanel p) {
        p.setPreferredSize(new Dimension(600, 400));
        return p;
    }
}