package com.campusflow.controller;

import com.campusflow.dto.*;
import com.campusflow.model.*;
import com.campusflow.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SchoolController {
    private final StudentService studentService;
    public SchoolController(StudentService studentService) { this.studentService = studentService; }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("metrics", studentService.dashboardMetrics());
        model.addAttribute("recentStudents", studentService.newestStudents());
        model.addAttribute("attentionStudents", studentService.attentionStudents());
        return "dashboard";
    }
    @GetMapping("/students")
    public String students(@RequestParam(required = false) String search, @RequestParam(required = false) String grade, Model model) {
        model.addAttribute("students", studentService.findAll(search, grade));
        model.addAttribute("grades", studentService.grades());
        model.addAttribute("search", search == null ? "" : search);
        model.addAttribute("selectedGrade", grade == null ? "" : grade);
        return "students";
    }
    @GetMapping("/students/new")
    public String newStudent(Model model) {
        model.addAttribute("studentForm", new StudentRegistrationForm());
        return "student-form";
    }
    @PostMapping("/students")
    public String createStudent(@Valid @ModelAttribute("studentForm") StudentRegistrationForm form, BindingResult result) {
        if (result.hasErrors()) return "student-form";
        Student student = studentService.register(form);
        return "redirect:/students/" + student.getId() + "?created";
    }
    @GetMapping("/students/{id}")
    public String studentDetail(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        StudentUpdateForm form = new StudentUpdateForm();
        form.setAttendancePercentage(student.getAttendancePercentage());
        form.setFeeStatus(student.getFeeStatus());
        form.setEnrollmentStatus(student.getEnrollmentStatus());
        model.addAttribute("student", student);
        model.addAttribute("updateForm", form);
        model.addAttribute("feeStatuses", FeeStatus.values());
        model.addAttribute("enrollmentStatuses", new EnrollmentStatus[]{EnrollmentStatus.ACTIVE, EnrollmentStatus.ON_LEAVE});
        model.addAttribute("cancellationForm", new AdmissionCancellationForm());
        model.addAttribute("cancellationReasons", CancellationReason.values());
        return "student-detail";
    }
    @PostMapping("/students/{id}/record")
    public String updateRecord(@PathVariable Long id, @Valid @ModelAttribute("updateForm") StudentUpdateForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", studentService.findById(id));
            model.addAttribute("feeStatuses", FeeStatus.values());
            model.addAttribute("enrollmentStatuses", new EnrollmentStatus[]{EnrollmentStatus.ACTIVE, EnrollmentStatus.ON_LEAVE});
            model.addAttribute("cancellationForm", new AdmissionCancellationForm());
            model.addAttribute("cancellationReasons", CancellationReason.values());
            return "student-detail";
        }
        studentService.updateRecord(id, form);
        return "redirect:/students/" + id + "?updated";
    }

    @PostMapping("/students/{id}/cancel-admission")
    public String cancelAdmission(@PathVariable Long id,
                                  @Valid @ModelAttribute("cancellationForm") AdmissionCancellationForm form,
                                  BindingResult result) {
        if (result.hasErrors() || !studentService.cancelAdmission(id, form.getReason())) {
            return "redirect:/students/" + id + "?notEligible";
        }
        return "redirect:/students/" + id + "?cancelled";
    }
    @GetMapping("/login")
    public String login() { return "login"; }
}
