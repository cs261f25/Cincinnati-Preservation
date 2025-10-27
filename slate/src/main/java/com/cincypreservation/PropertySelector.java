package com.cincypreservation;

import javax.swing.*;

public class PropertySelector extends JPanel {

    public PropertySelector() {

        // Example neighborhoods
        String[] neighborhoods = {"Downtown", "Midtown", "Uptown"};
        JComboBox<String> neighborhoodBox = new JComboBox<>(neighborhoods);

        // Example properties (hardcoded for now)
        String[] properties = {"Property A", "Property B", "Property C"};
        JComboBox<String> propertyBox = new JComboBox<>(properties);

        // Add items to the panel
        add(new JLabel("Select Neighborhood:"));
        add(neighborhoodBox);

        add(new JLabel("Select Property:"));
        add(propertyBox);
    }
}
