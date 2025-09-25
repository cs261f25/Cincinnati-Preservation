package com.cincypreservation;
import java.util.Scanner;

public class Form {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        //Input for basic informational fields
        System.out.print("Property Name: ");
        String propertyName = scanner.nextLine();

        System.out.print("Property Address: ");
        String propertyAddress = scanner.nextLine(); //Just street address

        System.out.print("Property Owner: ");
        String propertyOwner = scanner.nextLine();

        System.out.print("Inspector Name: ");
        String inspectorName = scanner.nextLine();

        System.out.print("Inspection Date (MM/DD/YYYY): ");
        String inspectionDate = scanner.nextLine(); //Better way to format date?

        /*
        Input for masonry portion of inspection. This is an example of how we might approach one section of the form.
        Each property needs different form fields though, so this will need to read from prev year's inspection to determine what to ask.
        */
        System.out.print("Masonry type: ");
        String masonryType = scanner.nextLine();

        System.out.print("Masonry condition (north): ");
        String masonryNorth = scanner.nextLine();
        
        System.out.print("Masonry condition (east): ");
        String masonryEast = scanner.nextLine();

        System.out.print("Masonry condition (south): ");
        String masonrySouth = scanner.nextLine();

        System.out.print("Masonry condition (west): ");
        String masonryWest = scanner.nextLine();

        System.out.print("Comments on masonry: ");
        String masonryComments = scanner.nextLine();

    }
}
