package com.campusflow.model;

public enum CancellationReason {
    FEE_OVERDUE("Fees remain overdue"),
    ATTENDANCE_SHORTAGE("Attendance is below the minimum requirement"),
    BOTH_CONCERNS("Attendance and fee concerns"),
    ADMIN_REVIEW("Administrative review");

    private final String label;

    CancellationReason(String label) { this.label = label; }
    public String getLabel() { return label; }
}
