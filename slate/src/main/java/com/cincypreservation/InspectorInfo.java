package com.cincypreservation;

public class InspectorInfo {
    private String inspectorName;
    private String inspectionId;

    public InspectorInfo(String inspectorName, String inspectionId) {
        this.inspectorName = inspectorName;
        this.inspectionId = inspectionId;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    @Override
    public String toString() {
        return "Inspection ID: " + inspectionId + " | Inspector: " + inspectorName;
    }
}

