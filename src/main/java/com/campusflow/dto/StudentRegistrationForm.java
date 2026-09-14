package com.campusflow.dto;

import jakarta.validation.constraints.*;

public class StudentRegistrationForm {
    @NotBlank(message = "Student name is required.") private String fullName;
    @NotBlank(message = "Email is required.") @Email(message = "Enter a valid email address.") private String email;
    @NotBlank(message = "Guardian name is required.") private String guardianName;
    @NotBlank(message = "Grade is required.") private String grade;
    @NotBlank(message = "Section is required.") private String section;
    @NotNull(message = "Attendance is required.") @DecimalMin(value = "0.0", message = "Attendance cannot be negative.") @DecimalMax(value = "100.0", message = "Attendance cannot exceed 100%.") private Double attendancePercentage;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGuardianName() { return guardianName; }
    public void setGuardianName(String guardianName) { this.guardianName = guardianName; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public Double getAttendancePercentage() { return attendancePercentage; }
    public void setAttendancePercentage(Double attendancePercentage) { this.attendancePercentage = attendancePercentage; }
}
