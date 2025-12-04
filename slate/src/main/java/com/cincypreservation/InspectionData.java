package com.cincypreservation;

import java.util.ArrayList;
import java.util.List;

public class InspectionData {

    public String propertyName;
    public String propertyAddress;
    public String ownerName;
    public String inspectorName;
    public String inspectionDate;

    public List<SectionRating> sections = new ArrayList<>();

    public static class SectionRating {
        public String sectionName;
        public String rating;
    }

    public String overallCondition;
    public String comments;
    public String followUpNotes;
}