package com.campusflow.model;

public enum FeeStatus {
    CLEAR("Clear", "green"), PENDING("Pending", "amber"), OVERDUE("Overdue", "rose");

    private final String label;
    private final String color;

    FeeStatus(String label, String color) { this.label = label; this.color = color; }
    public String getLabel() { return label; }
    public String getColor() { return color; }
}
