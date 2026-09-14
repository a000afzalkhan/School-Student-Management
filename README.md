🎓 CampusFlow — Student Management Portal
A modern, secure school administration web application built with Java Spring Boot. CampusFlow provides a protected dashboard, a searchable student directory, a student admission form, and in-place updates for attendance, fees, and enrolment status.

https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
https://img.shields.io/badge/Spring%2520Boot-3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
https://img.shields.io/badge/Spring%2520Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white
https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white
https://img.shields.io/badge/H2-0000BB?style=for-the-badge&logo=h2&logoColor=white
https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white

📋 Table of Contents
About the Project

Features

Tech Stack

Getting Started

Prerequisites

Run Locally

Verify with Tests

Default Credentials

Usage

Dashboard

Student Directory

Student Admission

Student Detail & Updates

Project Structure

API Endpoints

Database

Contributing

License

Contact

📖 About the Project
CampusFlow is a school administration application that simplifies the management of student records. It is built with the same Java Spring Boot stack as its parent project and offers a clean, role-protected interface for administrators. The app comes pre-loaded with eight sample student records and stores any changes in an embedded H2 database for the duration of the session.

Whether you're an educator, administrator, or developer exploring Spring Boot, CampusFlow demonstrates a complete web application with authentication, CRUD operations, search, filtering, and dynamic dashboard metrics.

✨ Features
🔐 Secure Authentication — Login-protected dashboard using Spring Security with BCrypt password hashing.

📊 Dynamic Dashboard — Real-time metrics: total students, active enrolments, average attendance, and pending fee counts. Highlights recent students and those needing attention (low attendance or overdue fees).

🔍 Searchable Student Directory — Search by name, student code, guardian name, or email. Filter by grade.

📝 Student Admission Form — Register new students with validation. Auto-generated student codes (e.g., CF-1001).

✏️ In-Place Updates — Update attendance percentage, fee status, and enrolment status directly from the student detail page.

🚫 Admission Cancellation — Cancel admissions with a predefined reason (only eligible students).

⚠️ Penalty Calculation — Automatic attendance and late-fee penalties computed on the student detail page.

💾 Embedded H2 Database — Zero-configuration, in-memory database with an H2 console for debugging.

🎨 Clean UI — Responsive templates built with Thymeleaf, HTML5, and CSS3.

🛠 Tech Stack
Layer	Technology
Language	Java 21
Framework	Spring Boot 3.5
Web	Spring MVC, Thymeleaf
Persistence	Spring Data JPA
Security	Spring Security (BCrypt)
Validation	Spring Boot Starter Validation
Database	H2 (in-memory)
Build Tool	Maven
Frontend	HTML5, CSS3
🚀 Getting Started
Prerequisites
Java 21 (or Java 23 as shown in the example below)

Maven (or use the included Maven Wrapper)

Run Locally
Clone the repository

bash
git clone https://github.com/a000afzalkhan/School-Student-Management.git
cd School-Student-Management
Set your JAVA_HOME (Windows example)

powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
Run the application

bash
..\mvnw.cmd -f pom.xml spring-boot:run
On macOS/Linux, use ./mvnw instead.

Open your browser

text
http://localhost:8081
Verify with Tests
bash
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
..\mvnw.cmd -f pom.xml test
🔑 Default Credentials
Username	Password
admin	admin123
⚠️ Security Note: These credentials are for development only. Change them before deploying to production.

📱 Usage
Dashboard
View total students, active enrolments, average attendance, and pending fees.

See the 6 most recently added students.

See students needing attention (attendance < 80% or overdue fees).

Student Directory
Search by name, student code, guardian name, or email.

Filter by grade.

Click any student to view or edit their details.

Student Admission
Navigate to "New Student" to open the admission form.

Fill in required fields (name, email, guardian, grade, section, attendance).

A unique student code is generated automatically (e.g., CF-1001).

Student Detail & Updates
View all student information.

Update attendance percentage, fee status, and enrolment status.

Cancel admission with a reason (only if eligible).

View calculated attendance and late-fee penalties.

📁 Project Structure
text
School-Student-Management/
├── src/
│   ├── main/
│   │   ├── java/com/campusflow/
│   │   │   ├── config/
│   │   │   │   ├── DataSeeder.java          # Seeds sample student data
│   │   │   │   └── SecurityConfig.java      # Spring Security configuration
│   │   │   ├── controller/
│   │   │   │   └── SchoolController.java    # All web routes
│   │   │   ├── dto/
│   │   │   │   ├── AdmissionCancellationForm.java
│   │   │   │   ├── DashboardMetrics.java
│   │   │   │   ├── StudentRegistrationForm.java
│   │   │   │   └── StudentUpdateForm.java
│   │   │   ├── model/
│   │   │   │   ├── CancellationReason.java  # Enum
│   │   │   │   ├── EnrollmentStatus.java    # Enum
│   │   │   │   ├── FeeStatus.java           # Enum
│   │   │   │   └── Student.java             # JPA Entity
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java
│   │   │   ├── service/
│   │   │   │   └── StudentService.java      # Business logic
│   │   │   └── CampusFlowApplication.java   # Main entry point
│   │   └── resources/
│   │       ├── static/                      # CSS, JS, images
│   │       ├── templates/                   # Thymeleaf templates
│   │       │   ├── dashboard.html
│   │       │   ├── login.html
│   │       │   ├── student-detail.html
│   │       │   ├── student-form.html
│   │       │   └── students.html
│   │       └── application.properties       # App configuration
│   └── test/java/com/campusflow/            # Unit & integration tests
├── pom.xml                                  # Maven dependencies
└── README.md
🔌 API Endpoints
Method	Endpoint	Description
GET	/	Dashboard with metrics
GET	/students	Student directory (search & filter)
GET	/students/new	New student admission form
POST	/students	Create a new student
GET	/students/{id}	Student detail page
POST	/students/{id}/record	Update student record
POST	/students/{id}/cancel-admission	Cancel admission
GET	/login	Login page
🗄 Database
Type: H2 In-Memory

JDBC URL: jdbc:h2:mem:campusflow;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE

Username: sa

Password: (empty)

H2 Console: http://localhost:8081/h2-console

💡 Data is not persisted across restarts. The app seeds 8 sample students on every startup.

🤝 Contributing
Contributions are welcome! Here's how you can help:

Fork the repository

Create a feature branch (git checkout -b feature/amazing-feature)

Commit your changes (git commit -m 'Add amazing feature')

Push to the branch (git push origin feature/amazing-feature)

Open a Pull Request

Please ensure your code follows the existing style and includes appropriate tests.

📄 License
This project is open-source and available for educational and personal use. Please check with the repository owner for specific licensing terms.

📬 Contact
Repository Owner: a000afzalkhan

Project Link: https://github.com/a000afzalkhan/School-Student-Management

<div align="center">
⭐ If you find this project useful, please give it a star!

Made with ❤️ using Java Spring Boot
