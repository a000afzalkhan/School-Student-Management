package com.campusflow.dto;

public class DashboardMetrics {
    private final long totalStudents;
    private final long activeStudents;
    private final int averageAttendance;
    private final long pendingFees;

    public DashboardMetrics(long totalStudents, long activeStudents, int averageAttendance, long pendingFees) {
        this.totalStudents = totalStudents; this.activeStudents = activeStudents;
        this.averageAttendance = averageAttendance; this.pendingFees = pendingFees;
    }
    public long getTotalStudents() { return totalStudents; }
    public long getActiveStudents() { return activeStudents; }
    public int getAverageAttendance() { return averageAttendance; }
    public long getPendingFees() { return pendingFees; }
}
