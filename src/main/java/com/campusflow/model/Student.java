package com.campusflow.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Student {
    private static final double MINIMUM_ATTENDANCE = 75.0;
    private static final int ATTENDANCE_PENALTY = 1_000;
    private static final int LATE_FEE_PENALTY = 750;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private String studentCode;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String guardianName;
    @Column(nullable = false)
    private String grade;
    @Column(nullable = false)
    private String section;
    @Column(nullable = false)
    private double attendancePercentage;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private FeeStatus feeStatus;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private EnrollmentStatus enrollmentStatus;
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;
    private LocalDate joinedOn;
    private LocalDate admissionCancelledOn;
    private LocalDateTime createdAt;

    protected Student() { }

    public Student(String studentCode, String fullName, String email, String guardianName, String grade,
                   String section, double attendancePercentage, FeeStatus feeStatus,
                   EnrollmentStatus enrollmentStatus, LocalDate joinedOn) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.email = email;
        this.guardianName = guardianName;
        this.grade = grade;
        this.section = section;
        this.attendancePercentage = attendancePercentage;
        this.feeStatus = feeStatus;
        this.enrollmentStatus = enrollmentStatus;
        this.joinedOn = joinedOn;
        this.createdAt = LocalDateTime.now();
    }

    public void updateAcademicRecord(double attendancePercentage, FeeStatus feeStatus, EnrollmentStatus enrollmentStatus) {
        this.attendancePercentage = attendancePercentage;
        this.feeStatus = feeStatus;
        if (this.enrollmentStatus != EnrollmentStatus.CANCELLED) {
            this.enrollmentStatus = enrollmentStatus;
        }
    }

    public void cancelAdmission(CancellationReason reason) {
        this.enrollmentStatus = EnrollmentStatus.CANCELLED;
        this.cancellationReason = reason;
        this.admissionCancelledOn = LocalDate.now();
    }

    public Long getId() { return id; }
    public String getStudentCode() { return studentCode; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getGuardianName() { return guardianName; }
    public String getGrade() { return grade; }
    public String getSection() { return section; }
    public double getAttendancePercentage() { return attendancePercentage; }
    public FeeStatus getFeeStatus() { return feeStatus; }
    public EnrollmentStatus getEnrollmentStatus() { return enrollmentStatus; }
    public CancellationReason getCancellationReason() { return cancellationReason; }
    public LocalDate getJoinedOn() { return joinedOn; }
    public LocalDate getAdmissionCancelledOn() { return admissionCancelledOn; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public boolean isAttendanceBelowMinimum() { return attendancePercentage < MINIMUM_ATTENDANCE; }
    public boolean isFeeOverdue() { return feeStatus == FeeStatus.OVERDUE; }
    public int getAttendancePenalty() { return isAttendanceBelowMinimum() ? ATTENDANCE_PENALTY : 0; }
    public int getLateFeePenalty() { return isFeeOverdue() ? LATE_FEE_PENALTY : 0; }
    public int getTotalPenalty() { return getAttendancePenalty() + getLateFeePenalty(); }
    public boolean isPenaltyApplicable() { return getTotalPenalty() > 0; }
    public boolean isCancellationEligible() {
        return enrollmentStatus != EnrollmentStatus.CANCELLED && (isAttendanceBelowMinimum() || isFeeOverdue());
    }
    public boolean isAdmissionCancelled() { return enrollmentStatus == EnrollmentStatus.CANCELLED; }
    public String getInitials() {
        String[] parts = fullName.trim().split("\\s+");
        return (parts[0].substring(0, 1) + (parts.length > 1 ? parts[parts.length - 1].substring(0, 1) : "")).toUpperCase();
    }
}
