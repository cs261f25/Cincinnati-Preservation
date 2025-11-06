package com.cincypreservation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Form {

    private static final String FILE_PATH = "inspections.txt"; // save file name
    private static List<Form> inspections = new ArrayList<>();

    // Fields: Basic Info
    private String propertyName;
    private String streetAddress;
    private String propertyOwner;
    private String inspectorName;
    private String inspectionDate;
    private String inspectionId;

    // Fields: Feature Inspection
    // TO BE ADDED LATER

    // Fields: General Assessment
    private String overallCondition;
    private String comments;
    private String advice;
    private String followUpActivity;


    // Constructor
    public Form() {}

    public Form(String propertyName, String streetAddress, String propertyOwner,
                String inspectorName, String inspectionDate, String inspectionId,
                String overallCondition, String comments, String advice,
                String followUpActivity) {

        // Basic Info
        this.propertyName = propertyName;
        this.streetAddress = streetAddress;
        this.propertyOwner = propertyOwner;
        this.inspectorName = inspectorName;
        this.inspectionDate = inspectionDate;
        this.inspectionId = inspectionId;

        // Feature Inspection
        // TO BE ADDED LATER

        // General Assessment
        this.overallCondition = overallCondition;
        this.comments = comments;
        this.advice = advice;
        this.followUpActivity = followUpActivity;
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
            InspectorInfo inspector = new InspectorInfo(inspectorName, inspectionId);

            inspections.add(this);
            saveToFile();

            System.out.println("Inspection Recorded Successfully!");
            System.out.println(propertyName + " owned by " + propertyOwner);
            System.out.println("Located at " + streetAddress);
            System.out.println("Inspected by " + inspector.getInspectorName() + " on " + inspectionDate);
            System.out.println(inspector.toString());

        } catch (IOException e) {
            System.out.println(" Error while reading input.");
        }
    }

    public void featureInspection() {
        // TO BE ADDED LATER
    }

    public void generalAssessment() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Eventually, change this to a drop-down menu of the condition codes (NV, E, VG, G, P, VP)
            System.out.print("Overall Condition: ");
            overallCondition = reader.readLine();

            System.out.print("Comments: ");
            comments = reader.readLine();

            System.out.print("Advice: ");
            advice = reader.readLine();

            System.out.print("Follow Up Activity: ");
            followUpActivity = reader.readLine();

        } catch (IOException e) {
            System.out.println(" Error while reading input.");
        }
    }


    // Save all inspections to text file
    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Form f : inspections) {
                writer.write(f.propertyName + "," + f.streetAddress + "," + f.propertyOwner + ","
                        + f.inspectorName + "," + f.inspectionDate + "," + f.inspectionId);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(" Error saving inspections to file.");
        }
    }

    // Load inspections from file
    private static void loadFromFile() {
        inspections.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    inspections.add(new Form(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            System.out.println(" Error loading inspections from file.");
        }
    }

    // Look up a property
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
            System.out.println(" Error while searching for property.");
        }
    }

    // Getters: Basic Info
    public String getPropertyName() { return propertyName; }
    public String getStreetAddress() { return streetAddress; }
    public String getPropertyOwner() { return propertyOwner; }
    public String getInspectorName() { return inspectorName; }
    public String getInspectionDate() { return inspectionDate; }
    public String getInspectionId() { return inspectionId; }
    
    // Getters: Feature Inspections
    // TO BE ADDED LATER

    // Getters: General Assessment
    public String getOverallCondition() { return overallCondition; }
    public String getComments() { return comments; }
    public String getAdvice() { return advice; }
    public String getFollowUpActivity() { return followUpActivity; }

    // Menu
    public static void main(String[] args) {
        loadFromFile(); // Load existing inspections

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
            System.out.println(" An error occurred while running the menu.");
        }
    }
}
