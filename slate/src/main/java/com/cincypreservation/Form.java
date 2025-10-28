package com.cincypreservation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Form {

    // Store the collected data as fields
    private String propertyName;
    private String streetAddress;
    private String propertyOwner;
    private String inspectorName;
    private String inspectionDate;
    private String inspectionId;

    public void basicInfo() {
        try {
            // Create BufferedReader to read from the keyboard
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Prompt user to enter various information about the property
            System.out.print("Property Name: ");
            propertyName = reader.readLine();

            System.out.print("Street Address: ");
            streetAddress = reader.readLine();

            System.out.print("Property Owner: ");
            propertyOwner = reader.readLine();

            System.out.print("Inspector Name: ");
            inspectorName = reader.readLine();

            System.out.print("Inspection Date (MM/DD/YYYY): ");
            inspectionDate = reader.readLine();

            // Generate a simple inspection ID using current time
            inspectionId = "INSP-" + System.currentTimeMillis();

            // Create an InspectorInfo object to store inspector data
            InspectorInfo inspector = new InspectorInfo(inspectorName, inspectionId);

            // Print out property information to verify input
            System.out.println();
            System.out.println("Property Information");
            System.out.println(propertyName + ", owned by " + propertyOwner);
            System.out.println("Located at " + streetAddress);
            System.out.println("Inspected by " + inspector.getInspectorName() + " on " + inspectionDate);
            System.out.println(inspector.toString());

        } catch (IOException e) {
            System.out.println("An error occurred while reading input."); // Error handling
            e.printStackTrace();
        }
    }

    // Getter methods to allow FileReader to access the collected data
    public String getPropertyName() {
        return propertyName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPropertyOwner() {
        return propertyOwner;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public static void main(String[] args) {

        Form form = new Form();
        form.basicInfo();

    }
}