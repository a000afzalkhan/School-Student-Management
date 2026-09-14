package com.campusflow.service;

import com.campusflow.dto.*;
import com.campusflow.model.*;
import com.campusflow.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) { this.studentRepository = studentRepository; }

    @Transactional
    public Student register(StudentRegistrationForm form) {
        Student student = new Student(nextStudentCode(), clean(form.getFullName()), clean(form.getEmail()),
                clean(form.getGuardianName()), clean(form.getGrade()), clean(form.getSection()).toUpperCase(),
                form.getAttendancePercentage(), FeeStatus.PENDING, EnrollmentStatus.ACTIVE, LocalDate.now());
        return studentRepository.save(student);
    }

    public List<Student> findAll(String search, String grade) {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "fullName")).stream()
                .filter(student -> grade == null || grade.isBlank() || student.getGrade().equalsIgnoreCase(grade))
                .filter(student -> matches(student, search)).toList();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found."));
    }

    @Transactional
    public Student updateRecord(Long id, StudentUpdateForm form) {
        Student student = findById(id);
        student.updateAcademicRecord(form.getAttendancePercentage(), form.getFeeStatus(), form.getEnrollmentStatus());
        return studentRepository.save(student);
    }

    @Transactional
    public boolean cancelAdmission(Long id, CancellationReason reason) {
        Student student = findById(id);
        if (!student.isCancellationEligible()) {
            return false;
        }
        student.cancelAdmission(reason);
        studentRepository.save(student);
        return true;
    }

    public DashboardMetrics dashboardMetrics() {
        List<Student> students = studentRepository.findAll();
        long active = students.stream().filter(s -> s.getEnrollmentStatus() == EnrollmentStatus.ACTIVE).count();
        long pending = students.stream()
                .filter(s -> !s.isAdmissionCancelled())
                .filter(s -> s.getFeeStatus() != FeeStatus.CLEAR).count();
        int attendance = students.isEmpty() ? 0 : (int) Math.round(students.stream().mapToDouble(Student::getAttendancePercentage).average().orElse(0));
        return new DashboardMetrics(students.size(), active, attendance, pending);
    }

    public List<Student> newestStudents() { return studentRepository.findTop6ByOrderByCreatedAtDesc(); }

    public List<Student> attentionStudents() {
        return studentRepository.findAll().stream()
                .filter(s -> !s.isAdmissionCancelled())
                .filter(s -> s.getAttendancePercentage() < 80 || s.getFeeStatus() == FeeStatus.OVERDUE)
                .sorted(Comparator.comparing(Student::getAttendancePercentage)).limit(4).toList();
    }

    public List<String> grades() { return studentRepository.findAll().stream().map(Student::getGrade).distinct().sorted().toList(); }

    private boolean matches(Student student, String search) {
        if (search == null || search.isBlank()) return true;
        String q = search.trim().toLowerCase();
        return student.getFullName().toLowerCase().contains(q) || student.getStudentCode().toLowerCase().contains(q)
                || student.getGuardianName().toLowerCase().contains(q) || student.getEmail().toLowerCase().contains(q);
    }
    private String nextStudentCode() { return "CF-" + (1000 + studentRepository.count() + 1); }
    private String clean(String value) { return value == null ? "" : value.trim(); }
}
