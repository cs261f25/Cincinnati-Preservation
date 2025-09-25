package com.cincypreservation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Form {
    public static void main(String[] args) {
        try {
            // Create BufferedReader to read from the keyboard
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            //Prompt user to enter various information about the property
            System.out.print("Property Name: ");
            String propertyName = reader.readLine();

            System.out.print("Street Address: ");
            String streetAddress = reader.readLine();

            System.out.print("Property Owner: ");
            String propertyOwner = reader.readLine();

            System.out.print("Inspector Name: ");
            String inspectorName = reader.readLine();

            System.out.print("Inspection Date (MM/DD/YYYY): ");
            String inspectionDate = reader.readLine(); //Better way to format date?

            //Print out property information to verify that input workedf
            System.out.println();
            System.out.println("Property Information");
            System.out.println(propertyName + ", owned by " + propertyOwner);
            System.out.println("Located at " + streetAddress);
            System.out.println("Inspected by " + inspectorName + " on " + inspectionDate);

        } catch (IOException e) {
            System.out.println("An error occurred while reading input."); //Error handling
            e.printStackTrace();
        }
    }
}