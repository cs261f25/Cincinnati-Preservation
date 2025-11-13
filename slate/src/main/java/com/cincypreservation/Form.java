package com.cincypreservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Form {

    // Fields
    private String propertyName;
    private String streetAddress;
    private String propertyOwner;
    private String inspectorName;
    private String inspectionDate;
    private String inspectionId;

    // Stored inspections (loaded at start)
    private static List<Form> inspections = new ArrayList<>();

    // Constructors
    public Form() {}

    public Form(String propertyName, String streetAddress, String propertyOwner,
                String inspectorName, String inspectionDate, String inspectionId) {

        this.propertyName = propertyName;
        this.streetAddress = streetAddress;
        this.propertyOwner = propertyOwner;
        this.inspectorName = inspectorName;
        this.inspectionDate = inspectionDate;
        this.inspectionId = inspectionId;
    }

    // Collect info from user
    public void basicInfo() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

            inspectionId = "INSP-" + System.currentTimeMillis();

            inspections.add(this);

            // Save via InspectionFileManager
            InspectionFileManager.saveInspections(new ArrayList<>(inspections));

            System.out.println("\n✅ Inspection Recorded Successfully!");
            System.out.println(propertyName + " owned by " + propertyOwner);
            System.out.println("Located at " + streetAddress);
            System.out.println("Inspected by " + inspectorName + " on " + inspectionDate);
            System.out.println("Inspection ID: " + inspectionId);

        } catch (IOException e) {
            System.out.println("Error while reading input.");
        }
    }

    // Search properties
    public static void lookupProperty() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\nEnter the property name to look up: ");
            String searchName = reader.readLine();

            boolean found = false;

            for (Form form : inspections) {
                if (form.getPropertyName().equalsIgnoreCase(searchName)) {

                    System.out.println("\nInspection Found:");
                    System.out.println("------------------");
                    System.out.println("Property: " + form.getPropertyName());
                    System.out.println("Address: " + form.getStreetAddress());
                    System.out.println("Owner: " + form.getPropertyOwner());
                    System.out.println("Inspector: " + form.getInspectorName());
                    System.out.println("Date: " + form.getInspectionDate());
                    System.out.println("Inspection ID: " + form.getInspectionId());

                    found = true;
                }
            }

            if (!found) {
                System.out.println("No inspections found for property: " + searchName);
            }

        } catch (IOException e) {
            System.out.println("Error while searching for property.");
        }
    }

    // Getters
    public String getPropertyName() { return propertyName; }
    public String getStreetAddress() { return streetAddress; }
    public String getPropertyOwner() { return propertyOwner; }
    public String getInspectorName() { return inspectorName; }
    public String getInspectionDate() { return inspectionDate; }
    public String getInspectionId() { return inspectionId; }

    // Menu
    public static void main(String[] args) {

        // Load existing inspections from file (via manager)
        inspections = InspectionFileManager.loadInspections();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("\n--- Property Inspection Menu ---");
                System.out.println("1. Enter New Inspection");
                System.out.println("2. Look Up Property");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                String choice = reader.readLine();

                if (choice.equals("1")) {
                    Form form = new Form();
                    form.basicInfo();

                } else if (choice.equals("2")) {
                    lookupProperty();

                } else if (choice.equals("3")) {
                    System.out.println("Exiting program.");
                    break;

                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while running the menu.");
        }
    }
}
