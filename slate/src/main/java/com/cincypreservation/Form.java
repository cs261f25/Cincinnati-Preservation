package com.cincypreservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.List;

public class Form {

    // Fields: Basic Info
=======

public class Form {

    // Fields
>>>>>>> Stashed changes
    private String propertyName;
    private String streetAddress;
    private String propertyOwner;
    private String inspectorName;
    private String inspectionDate;
    private String inspectionId;

    // Fields: Feature Inspection - list of Feature objects, each with its own fields
    private List<Feature> features;

    // Nested class for a Feature object, with all needed Feature fields
    public static class Feature {
        
        // Fields for Feature object
        private String featureName;
        private String featureDescription;
        private String northCondition;
        private String eastCondition;
        private String southCondition;
        private String westCondition;

        // Constructor for Feature object
        public Feature(String featureName, String featureDescription, 
                      String northCondition, String eastCondition, 
                      String southCondition, String westCondition) {
            this.featureName = featureName;
            this.featureDescription = featureDescription;
            this.northCondition = northCondition;
            this.eastCondition = eastCondition;
            this.southCondition = southCondition;
            this.westCondition = westCondition;
            }

        // Getters for Feature object
        public String getFeatureName() { return featureName; }
        public String getFeatureDescription() { return featureDescription; }
        public String getNorthCondition() { return northCondition; }
        public String getEastCondition() { return eastCondition; }
        public String getSouthCondition() { return southCondition; }
        public String getWestCondition() { return westCondition; }  
    }

    // Fields: General Assessment
    private String overallCondition;
    private String comments;
    private String advice;
    private String followUpActivity;

    // Constructor
    public Form() {
        this.features = new ArrayList<>();  //Initialize Feature list
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
        this.features = new ArrayList<>();  //Initialize Feature list

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

<<<<<<< Updated upstream
            // Create inspection ID and inspector object
            inspectionId = "INSP-" + System.currentTimeMillis();
            InspectorInfo inspector = new InspectorInfo(inspectorName, inspectionId);

=======
            // Auto-generate ID
            inspectionId = "INSP-" + System.currentTimeMillis();
            InspectorInfo inspector = new InspectorInfo(inspectorName, inspectionId);

            // Load existing inspections
            ArrayList<Form> allInspections = InspectionFileManager.loadInspections();

            // Add new one
            allInspections.add(this);

            // Save back to file
            InspectionFileManager.saveInspections(allInspections);

            System.out.println("\n✅ Inspection Recorded Successfully!");
            System.out.println(propertyName + " owned by " + propertyOwner);
            System.out.println("Located at " + streetAddress);
            System.out.println("Inspected by " + inspector.getInspectorName() +
                    " on " + inspectionDate);
            System.out.println(inspector.toString());

>>>>>>> Stashed changes
        } catch (IOException e) {
            System.out.println(" Error while reading input.");
        }
    }

<<<<<<< Updated upstream
    public void featureInspection() {
=======
    // Look up a property
    public static void lookupProperty() {
>>>>>>> Stashed changes
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            while (true) {
                System.out.println("--- Add Feature ---");
                
                System.out.print("Feature Name: ");
                String featureName = reader.readLine();
                
                System.out.print("Feature Description: ");
                String featureDescription = reader.readLine();
                
                // User should enter one of the following for the condition fields.
                // For now, leave this as String input, but consider changing to dropdown in next sprint
                // NV: not visible | E: excellent | VG: very good | G: good | P: poor | VP: very poor

<<<<<<< Updated upstream
                System.out.print("North Condition: ");
                String northCondition = reader.readLine();
                
                System.out.print("East Condition: ");
                String eastCondition = reader.readLine();
                
                System.out.print("South Condition: ");
                String southCondition = reader.readLine();
                
                System.out.print("West Condition: ");
                String westCondition = reader.readLine();
                
                // Add the Feature to the list
                features.add(new Feature(featureName, featureDescription, 
                                        northCondition, eastCondition, 
                                        southCondition, westCondition));
                
                System.out.print("Inspect another feature? (y/n): ");
                String response = reader.readLine();
                
                if (!response.equalsIgnoreCase("y")) {
                    break;
                }
            }
            
            System.out.println(features.size() + " feature(s) recorded.");
            
=======
            ArrayList<Form> allInspections = InspectionFileManager.loadInspections();
            boolean found = false;

            for (Form form : allInspections) {
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

>>>>>>> Stashed changes
        } catch (IOException e) {
            System.out.println(" Error while reading input.");
        }
    }

    public void addFeature(Feature f){
        features.add(f);
    }

    public void generalAssessment() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // User should enter one of the following for the condition field.
            // For now, leave this as String input, but consider changing to dropdown in next sprint
            // NV: not visible | E: excellent | VG: very good | G: good | P: poor | VP: very poor            
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
    public List<Feature> getFeatures() { return features; }

<<<<<<< Updated upstream
    // Getters: General Assessment
    public String getOverallCondition() { return overallCondition; }
    public String getComments() { return comments; }
    public String getAdvice() { return advice; }
    public String getFollowUpActivity() { return followUpActivity; }
=======
    // Menu
    public static void main(String[] args) {
>>>>>>> Stashed changes

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