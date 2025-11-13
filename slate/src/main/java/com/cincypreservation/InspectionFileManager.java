package com.cincypreservation;

import java.io.*;
import java.util.ArrayList;

public class InspectionFileManager {

    private static final String FILE_NAME = "inspections.txt";

    // Save all inspections to a file
    public static void saveInspections(ArrayList<Form> inspections) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(FILE_NAME))) {
            for (Form f : inspections) {
                writer.write(
                        f.getPropertyName() + "," +
                                f.getStreetAddress() + "," +
                                f.getPropertyOwner() + "," +
                                f.getInspectorName() + "," +
                                f.getInspectionDate() + "," +
                                f.getInspectionId()
                );
                writer.newLine();
            }
            System.out.println("✔ Inspections saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving inspections: " + e.getMessage());
        }
    }

    // Load inspections from a file
    public static ArrayList<Form> loadInspections() {
        ArrayList<Form> inspections = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("ℹ️ No existing inspection file found (first run).");
            return inspections;
        }

        try (BufferedReader reader =
                     new BufferedReader(new java.io.FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Form f = new Form(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4],
                            parts[5]
                    );
                    inspections.add(f);
                }
            }

            System.out.println("✔ Inspections loaded from file.");

        } catch (FileNotFoundException e) {
            System.out.println("ℹ No saved inspections found yet.");
        } catch (IOException e) {
            System.out.println("Error loading inspections: " + e.getMessage());
        }

        return inspections;
    }}
