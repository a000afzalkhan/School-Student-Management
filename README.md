# CampusFlow — Student Management Portal

CampusFlow is a school administration app built with the same Java Spring Boot stack as the main project. It provides a protected dashboard, searchable student directory, student admission form, and in-place updates for attendance, fees, and enrolment status.

## Tech stack

- Java 21, Spring Boot 3.5, Spring MVC, Spring Data JPA
- Spring Security with BCrypt password hashing
- Thymeleaf, HTML5, CSS3
- H2 in-memory database and Maven

## Run locally

From this folder, run:

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
..\mvnw.cmd -f pom.xml spring-boot:run
```

Open http://localhost:8081 and sign in with:

| Username | Password |
| --- | --- |
| `admin` | `admin123` |

The app starts with eight sample student records. Any students added or changed during a run are stored in the embedded H2 database for that session.

## Verify

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
..\mvnw.cmd -f pom.xml test
```
