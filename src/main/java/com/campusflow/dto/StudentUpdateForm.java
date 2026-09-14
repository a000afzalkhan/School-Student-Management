package com.campusflow.dto;

import com.campusflow.model.EnrollmentStatus;
import com.campusflow.model.FeeStatus;
import jakarta.validation.constraints.*;

public class StudentUpdateForm {
    @NotNull @DecimalMin(value = "0.0") @DecimalMax(value = "100.0") private Double attendancePercentage;
    @NotNull private FeeStatus feeStatus;
    @NotNull private EnrollmentStatus enrollmentStatus;

    public Double getAttendancePercentage() { return attendancePercentage; }
    public void setAttendancePercentage(Double attendancePercentage) { this.attendancePercentage = attendancePercentage; }
    public FeeStatus getFeeStatus() { return feeStatus; }
    public void setFeeStatus(FeeStatus feeStatus) { this.feeStatus = feeStatus; }
    public EnrollmentStatus getEnrollmentStatus() { return enrollmentStatus; }
    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) { this.enrollmentStatus = enrollmentStatus; }
}
