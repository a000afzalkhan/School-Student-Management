package com.campusflow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void dashboardIsAvailableToAnAdministrator() throws Exception {
        mockMvc.perform(get("/").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("CampusFlow")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Total students")));
    }

    @Test
    void publicCampusImageIsAvailableForTheLoginExperience() throws Exception {
        mockMvc.perform(get("/images/campus-hero.png"))
                .andExpect(status().isOk());
    }

    @Test
    void studentDirectoryAndAdmissionFormRenderForAnAdministrator() throws Exception {
        mockMvc.perform(get("/students").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("All students")));

        mockMvc.perform(get("/students/new").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Admission details")));

        mockMvc.perform(get("/students/1").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Student information")));
    }

    @Test
    void administratorCanCreateAStudentRecord() throws Exception {
        mockMvc.perform(post("/students").with(user("admin").roles("ADMIN")).with(csrf())
                        .param("fullName", "Neel Joshi")
                        .param("email", "neel.joshi@campusflow.edu")
                        .param("guardianName", "Asha Joshi")
                        .param("grade", "Grade 11")
                        .param("section", "B")
                        .param("attendancePercentage", "93"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/students/*"));
    }

    @Test
    void administratorCanReviewPenaltiesAndCancelAnEligibleAdmission() throws Exception {
        mockMvc.perform(get("/students/3").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Outstanding concerns")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Cancel admission")));

        mockMvc.perform(post("/students/3/cancel-admission").with(user("admin").roles("ADMIN")).with(csrf())
                        .param("reason", "FEE_OVERDUE"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/students/3?cancelled"));
    }
}
