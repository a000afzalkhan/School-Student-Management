# 🎓 CampusFlow – Student Management Portal

A modern and secure **Student Management System** built using Java and Spring Boot. CampusFlow is designed to simplify school administration by providing an easy-to-use platform for managing student records, admissions, attendance, fees, and enrolment status.

The application features a secure admin dashboard, searchable student directory, student registration, and real-time dashboard statistics.

## ✨ Key Features

- 🔐 **Secure Authentication** – Login system powered by Spring Security and BCrypt password hashing.
- 📊 **Admin Dashboard** – View total students, active enrolments, average attendance, and pending fees.
- 👨‍🎓 **Student Management** – Register, view, and manage student records.
- 🔍 **Smart Search** – Search students by name, student code, guardian name, or email.
- 🎯 **Grade Filtering** – Filter students according to their grade.
- 📝 **Student Admission** – Register new students with form validation and automatically generated student IDs.
- 📅 **Attendance Management** – Update and monitor student attendance percentages.
- 💰 **Fee Management** – Track fee payment status and pending fees.
- ⚠️ **Penalty Calculation** – Calculate attendance-related and late-fee penalties.
- 🚫 **Admission Cancellation** – Cancel eligible student admissions with a specified reason.
- 📱 **Responsive UI** – Clean and user-friendly interface using Thymeleaf, HTML, and CSS.
- 💾 **Database Management** – Store and manage student information using an H2 in-memory database.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming Language |
| Spring Boot 3.5 | Backend Framework |
| Spring MVC | Web Application Architecture |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Database Operations |
| Hibernate | ORM |
| Thymeleaf | Frontend Template Engine |
| HTML5 & CSS3 | User Interface |
| H2 Database | In-Memory Database |
| Maven | Dependency Management & Build Tool |
| JUnit | Testing |

## 🏗️ Project Architecture

The project follows a layered architecture to keep the application organized and maintainable.

- **Controller Layer:** Handles HTTP requests and application routes.
- **Service Layer:** Contains business logic and application operations.
- **Repository Layer:** Handles database interactions using Spring Data JPA.
- **Model Layer:** Defines student entities and related enums.
- **DTO Layer:** Transfers and validates form data.
- **Config Layer:** Manages application configuration and security.

## 📂 Project Structure

```text
School-Student-Management/
│
├── src/
│   ├── main/
│   │   ├── java/com/campusflow/
│   │   │   ├── config/
│   │   │   │   ├── DataSeeder.java
│   │   │   │   └── SecurityConfig.java
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   └── SchoolController.java
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── AdmissionCancellationForm.java
│   │   │   │   ├── DashboardMetrics.java
│   │   │   │   ├── StudentRegistrationForm.java
│   │   │   │   └── StudentUpdateForm.java
│   │   │   │
│   │   │   ├── model/
│   │   │   │   ├── CancellationReason.java
│   │   │   │   ├── EnrollmentStatus.java
│   │   │   │   ├── FeeStatus.java
│   │   │   │   └── Student.java
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java
│   │   │   │
│   │   │   ├── service/
│   │   │   │   └── StudentService.java
│   │   │   │
│   │   │   └── CampusFlowApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│
├── Dockerfile
├── pom.xml
└── README.md
```

## ⚙️ Getting Started

Follow these steps to run the project on your local machine.

### Prerequisites

Make sure you have installed:

- Java JDK 21 or above
- Apache Maven
- IntelliJ IDEA / VS Code
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/a000afzalkhan/School-Student-Management.git
```

### 2. Navigate to Project Directory

```bash
cd School-Student-Management
```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

### 5. Open in Browser

```text
http://localhost:8081
```

## 🔑 Default Login Credentials

Use the following credentials to access the admin dashboard in the development setup.

| Field | Credentials |
|---|---|
| Username | `admin` |
| Password | `admin123` |

**Security Note:** These are development credentials. Change them before using the application in a production environment.

## 📱 Application Modules

### 1. Admin Dashboard
- Total number of students.
- Active student enrolments.
- Average attendance percentage.
- Pending fee information.
- Recently registered students.
- Students requiring attention.

### 2. Student Directory
- View all registered students.
- Search student information.
- Filter students by grade.
- Access individual student profiles.

### 3. Student Admission
- Register new students.
- Validate admission information.
- Generate unique student codes automatically.

### 4. Student Details & Updates
- View complete student information.
- Update attendance percentages.
- Manage fee payment status.
- Update enrolment status.
- Cancel eligible admissions.

## 🔌 Application Routes

| HTTP Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Admin Dashboard |
| GET | `/login` | Login Page |
| GET | `/students` | Student Directory |
| GET | `/students/new` | Admission Form |
| POST | `/students` | Register Student |
| GET | `/students/{id}` | Student Details |
| POST | `/students/{id}/record` | Update Student Record |
| POST | `/students/{id}/cancel-admission` | Cancel Admission |

## 🗄️ Database Configuration

CampusFlow currently uses an H2 in-memory database.

**Database Details:**

- Database: H2
- Database Name: `campusflow`
- Username: `sa`
- Password: Empty
- JDBC URL: `jdbc:h2:mem:campusflow;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`

### H2 Database Console

Open:

```text
http://localhost:8081/h2-console
```

Use the JDBC URL and credentials mentioned above.

**Note:** The database is in-memory, so application data does not persist after restarting the application. Eight sample student records are loaded during startup.

## 🧪 Running Tests

Execute the following command to run the project's tests:

```bash
mvn test
```

## 🚀 Future Enhancements

Some features that could be added in future versions:

- [ ] MySQL / PostgreSQL database integration
- [ ] Student and Teacher role management
- [ ] Parent login portal
- [ ] Student performance reports
- [ ] Fee payment gateway integration
- [ ] Email and notification system
- [ ] REST API integration
- [ ] Cloud deployment

## 👨‍💻 Author

**MD Afzal Khan**

- GitHub: [@a000afzalkhan](https://github.com/a000afzalkhan)
- Project Repository: [School-Student-Management](https://github.com/a000afzalkhan/School-Student-Management)

---

⭐ If you find this project useful, consider giving it a star!

**Built with ❤️ using Java and Spring Boot.**
