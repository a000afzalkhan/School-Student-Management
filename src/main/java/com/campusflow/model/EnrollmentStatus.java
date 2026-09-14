package com.campusflow.model;

public enum EnrollmentStatus {
    ACTIVE("Active", "green"), ON_LEAVE("On leave", "amber"), CANCELLED("Cancelled", "rose");

    private final String label;
    private final String color;

    EnrollmentStatus(String label, String color) { this.label = label; this.color = color; }
    public String getLabel() { return label; }
    public String getColor() { return color; }
}
