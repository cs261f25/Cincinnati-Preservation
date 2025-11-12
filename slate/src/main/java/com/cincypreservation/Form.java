package com.cincypreservation;

import java.io.*;
public class Form {

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
    // Default Constructor @author Joshua Bagcat
    public Form() {
        // Basic Info
        this.propertyName = "Lorem";
        this.streetAddress = "Ipsum";
        this.propertyOwner = "Dolor";
        this.inspectorName = "Sit";
        this.inspectionDate = "01011970";
        this.inspectionId = "INSP-" + System.currentTimeMillis();
        InspectorInfo inspector = new InspectorInfo(inspectorName, inspectionId);

        // Feature Inspection
        // TO BE ADDED LATER

        // General Assessment - Not required yet for testing purposes
    }

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

            // Create inspection ID and inspector object
            inspectionId = "INSP-" + System.currentTimeMillis();
            InspectorInfo inspector = new InspectorInfo(inspectorName, inspectionId);

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

    // Setters: Basic Info
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }
    public void setStreetAddress(String streetAdress) { this.streetAddress = streetAdress; }
    public void setPropertyOwner(String propertyOwner) { this.propertyOwner = propertyOwner; }
    public void setInspectorName(String inspectorName) { this.inspectorName = inspectorName; }
    public void setInspectionDate(String inspectionDate) { this.inspectionDate = inspectionDate; }

    // Setters: General Assessment
    public void setOverallCondition(String overallCondition) { this.overallCondition = overallCondition; }
    public void setComments(String comments) { this.comments = comments; }
    public void setAdvice(String advice) { this.advice = advice; }
    public void setFollowUpActivity(String followUpActivity) {this.followUpActivity = followUpActivity; } 
}