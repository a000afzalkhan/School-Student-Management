package com.campusflow.config;

import com.campusflow.model.*;
import com.campusflow.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedStudents(StudentRepository repository) {
        return args -> {
            if (repository.count() > 0) return;
            add(repository, "CF-1001", "Aarav Sharma", "aarav.sharma@campusflow.edu", "Priya Sharma", "Grade 10", "A", 94, FeeStatus.CLEAR, EnrollmentStatus.ACTIVE, 90);
            add(repository, "CF-1002", "Aanya Gupta", "aanya.gupta@campusflow.edu", "Rohit Gupta", "Grade 9", "B", 88, FeeStatus.PENDING, EnrollmentStatus.ACTIVE, 72);
            add(repository, "CF-1003", "Vihaan Mehta", "vihaan.mehta@campusflow.edu", "Kavita Mehta", "Grade 10", "A", 76, FeeStatus.OVERDUE, EnrollmentStatus.ACTIVE, 53);
            add(repository, "CF-1004", "Diya Kapoor", "diya.kapoor@campusflow.edu", "Nisha Kapoor", "Grade 8", "C", 97, FeeStatus.CLEAR, EnrollmentStatus.ACTIVE, 40);
            add(repository, "CF-1005", "Arjun Nair", "arjun.nair@campusflow.edu", "Sanjay Nair", "Grade 9", "A", 82, FeeStatus.PENDING, EnrollmentStatus.ACTIVE, 26);
            add(repository, "CF-1006", "Ishita Verma", "ishita.verma@campusflow.edu", "Rakesh Verma", "Grade 8", "B", 68, FeeStatus.OVERDUE, EnrollmentStatus.ON_LEAVE, 18);
            add(repository, "CF-1007", "Kabir Singh", "kabir.singh@campusflow.edu", "Manish Singh", "Grade 10", "B", 91, FeeStatus.CLEAR, EnrollmentStatus.ACTIVE, 9);
            add(repository, "CF-1008", "Myra Das", "myra.das@campusflow.edu", "Anita Das", "Grade 9", "C", 85, FeeStatus.CLEAR, EnrollmentStatus.ACTIVE, 4);
        };
    }
    private void add(StudentRepository repository, String code, String name, String email, String guardian, String grade, String section, double attendance, FeeStatus fees, EnrollmentStatus status, int daysAgo) {
        repository.save(new Student(code, name, email, guardian, grade, section, attendance, fees, status, LocalDate.now().minusDays(daysAgo)));
    }
}
