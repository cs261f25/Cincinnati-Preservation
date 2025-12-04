package com.cincypreservation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PropertySelector extends JPanel{

    private JFrame frame;
    private JTextField searchField;
    private JPanel resultsPanel;

    private List<String> allAddresses = List.of(
            "123 Main St",
            "45 Oak Avenue",
            "900 Maple Drive",
            "78 Pine Street",
            "1200 Sunset Blvd"
    );

    public PropertySelector() {
        frame = new JFrame("Address Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Search Field
        searchField = new JTextField("Please enter an address");
        mainPanel.add(searchField, BorderLayout.NORTH);

        JButton addPropertyButton = new JButton("Add New Property");
        addPropertyButton.addActionListener(e -> {
            JFrame addPropertyFrame = new JFrame("Add New Property");
            addPropertyFrame.setSize(300,300);
            addPropertyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JTextField newAddress = new JTextField("Type New Address Here", 20);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            JButton saveButton = new JButton("Save");
            JButton cancelButton = new JButton("Cancel");
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            addPropertyFrame.add(buttonPanel, BorderLayout.SOUTH);

            saveButton.addActionListener(f -> {
                addPropertyFrame.dispose();
                openAddressForm(newAddress.getText());
            });
            cancelButton.addActionListener(g -> {
                addPropertyFrame.dispose();
            });
            addPropertyFrame.add(newAddress, BorderLayout.CENTER);

            addPropertyFrame.setVisible(true);
        });

        mainPanel.add(addPropertyButton, BorderLayout.SOUTH);

        // Results Panel
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Trigger search when user clicks or types
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateResults();
            }
        });

        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateResults();
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void updateResults() {
        String query = searchField.getText().toLowerCase();

        // Filter addresses
        List<String> filtered = allAddresses.stream()
                .filter(addr -> addr.toLowerCase().contains(query))
                .collect(Collectors.toList());

        // Clear old results
        resultsPanel.removeAll();

        // Add each result as a row with a button
        for (String addr : filtered) {
            resultsPanel.add(createResultRow(addr));
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private JPanel createResultRow(String address) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // keeps sizing clean

        JLabel label = new JLabel(address);
        JButton selectButton = new JButton("Select");

        selectButton.addActionListener(e -> openAddressForm(address));

        panel.add(label, BorderLayout.CENTER);
        panel.add(selectButton, BorderLayout.EAST);

        return panel;
    }

    // Open new form
    private void openAddressForm(String address) {
        JFrame formFrame = new JFrame("Form for " + address);
        formFrame.setSize(600,600);
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        NewForm nf = new NewForm(address);
        formFrame.add(nf);
        formFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PropertySelector::new);
    }
}