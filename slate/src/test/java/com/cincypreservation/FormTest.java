package com.cincypreservation;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FormTest {

    @Test
    public void testConstructorAndGetters() {

        Form form = new Form();

        // Basic Info
        form.setPropertyName("Test Property");
        form.setStreetAddress("123 Main St");
        form.setPropertyOwner("John Doe");
        form.setInspectorName("Inspector A");
        form.setInspectionDate("01/01/2025");
        //form.setInspectionId("INSP123");

        // New General Assessment fields
        form.setOverallCondition("Good");
        form.setComments("No major issues");
        form.setAdvice("Regular maintenance recommended");
        form.setFollowUpActivity("Recheck in 6 months");

        // Assertions (Basic Info)
        assertEquals("Test Property", form.getPropertyName());
        assertEquals("123 Main St", form.getStreetAddress());
        assertEquals("John Doe", form.getPropertyOwner());
        assertEquals("Inspector A", form.getInspectorName());
        assertEquals("01/01/2025", form.getInspectionDate());
       // assertEquals("INSP123", form.getInspectionId());

        // Assertions (General Assessment)
        assertEquals("Good", form.getOverallCondition());
        assertEquals("No major issues", form.getComments());
        assertEquals("Regular maintenance recommended", form.getAdvice());
        assertEquals("Recheck in 6 months", form.getFollowUpActivity());
    }

}
