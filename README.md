# CourseHub – Course Registration System 🎓

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

**CourseHub** is a full-stack course registration application. It allows users to browse courses, register for a selected course, and view the list of enrolled students. The frontend is built with HTML, CSS and JavaScript, while the backend provides REST endpoints using Java, Spring Boot, Spring Data JPA and MySQL.

## ✨ Features

- **Course catalog:** Fetch and display available courses from the backend.
- **Dynamic course selection:** Registration form loads its course options from the `/courses` API.
- **Course enrollment:** Submit a student's name, email address and selected course.
- **Enrollment overview:** View registered students and the total enrollment count.
- **Responsive interface:** Dark CourseHub interface with layouts designed for desktop and mobile screens.
- **Persistent storage:** Store course and registration records in MySQL through Spring Data JPA.

## 🧰 Tech Stack

| Layer | Technologies |
|---|---|
| Frontend | HTML5, CSS3, JavaScript (Fetch API) |
| Backend | Java 21, Spring Boot 4.1.2, Spring Web MVC |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL |
| Build tool | Maven Wrapper |
| Development tools | IntelliJ IDEA, Git, GitHub |

## 🖥️ Application Pages

| Page | File | Purpose |
|---|---|---|
| Home | `frontend/index.html` | CourseHub landing page and navigation |
| Register | `frontend/register.html` | Student details form with dynamically loaded courses |
| Available Courses | `frontend/avaliableCourses.html` | Displays course ID, course name, trainer and duration |
| Enrolled Students | `frontend/enrolledCourses.html` | Displays registered students and enrollment count |

## 🏗️ Project Structure

The Spring Boot application is inside the `CourseRegistrationSystem/` directory.

```text
Course-Registration-System/
├── CourseRegistrationSystem/
│   ├── frontend/
│   │   ├── index.html
│   │   ├── register.html
│   │   ├── avaliableCourses.html
│   │   ├── enrolledCourses.html
│   │   └── style.css
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/CourseRegistrationSystem/
│   │   │   │   ├── controller/
│   │   │   │   │   └── CourseController.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Course.java
│   │   │   │   │   └── CourseRegistery.java
│   │   │   │   ├── repository/
│   │   │   │   │   ├── CourseRepo.java
│   │   │   │   │   └── CourseRegisteryRepo.java
│   │   │   │   ├── service/
│   │   │   │   │   └── CourseService.java
│   │   │   │   └── CourseRegistrationSystemApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── .mvn/
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
└── README.md
```

## ⚙️ Getting Started

### Prerequisites

Make sure the following are installed:

- **JDK 21**
- **MySQL Server**
- **Git**
- A code editor or IDE (IntelliJ IDEA is used for this project)

Verify Java and MySQL are available:

```bash
java -version
mysql --version
```

### 1. Clone the repository

```bash
git clone https://github.com/mohamedhasan-m/Course-Registration-System.git
cd Course-Registration-System/CourseRegistrationSystem
```

### 2. Create the MySQL database

Start MySQL and create the database:

```sql
CREATE DATABASE course_reg_sym;
```

### 3. Configure database credentials

Open:

`src/main/resources/application.properties`

Configure the connection for your local MySQL installation. For example:

```properties
spring.application.name=CourseRegistrationSystem
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/course_reg_sym
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

Replace the username and password with your own local credentials. **Do not commit real database passwords or other secrets to GitHub.** For a shared or deployed environment, prefer environment variables or an external secrets manager.

The application uses Hibernate's `update` setting to update the database schema automatically. Add course records to the `course` table before using the catalog, since the current project reads available courses from the database rather than defining a built-in course list.

### 4. Run the Spring Boot backend

From the `CourseRegistrationSystem/` directory, use the Maven Wrapper.

**Windows (PowerShell / CMD):**

```powershell
.\mvnw.cmd spring-boot:run
```

**macOS / Linux:**

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

The backend starts at:

`http://localhost:8080`

### 5. Run the frontend

The frontend uses browser Fetch requests to communicate with the backend. Serve the `frontend/` directory with a local web server; the current CORS configuration allows these origins:

- `http://localhost:5500`
- `http://127.0.0.1:5500`

For example, with the VS Code Live Server extension, open `frontend/index.html` and start Live Server on port 5500. Keep the Spring Boot backend running while using the frontend.

## 🔌 REST API

The backend currently exposes these endpoints:

| Method | Endpoint | Description | Parameters |
|---|---|---|---|
| `GET` | `/courses` | Returns all available courses | — |
| `GET` | `/courses/enrolled` | Returns all course registration records | — |
| `POST` | `/courses/register` | Registers a student for a course | `name`, `emailId`, `courseName` |

### Example: Get available courses

```http
GET http://localhost:8080/courses
```

### Example: Register for a course

The registration endpoint accepts form URL-encoded request parameters:

```bash
curl -X POST http://localhost:8080/courses/register \
  -H "Content-Type: application/x-www-form-urlencoded" \
  --data-urlencode "name=Alex" \
  --data-urlencode "emailId=alex@example.com" \
  --data-urlencode "courseName=Java"
```

### Example: View enrolled students

```http
GET http://localhost:8080/courses/enrolled
```

## 🧠 Backend Design

The backend follows a layered structure:

- **Controller – `CourseController`:** Handles HTTP requests and exposes the course and enrollment endpoints.
- **Service – `CourseService`:** Contains the application operations for retrieving courses and saving registrations.
- **Repository – `CourseRepo` and `CourseRegisteryRepo`:** Use Spring Data JPA to interact with MySQL.
- **Model – `Course` and `CourseRegistery`:** Represent course catalog records and student enrollment records.

## 🔮 Possible Future Improvements

- Add validation for student details and course selection.
- Add duplicate-enrollment checks and meaningful error responses.
- Add course creation and update functionality for administrators.
- Add search, filtering and pagination to the course catalog.
- Introduce DTOs and centralized exception handling.
- Move database credentials to environment variables.
- Add automated controller and service tests.
- Add authentication and role-based access for administrators.

## 👨‍💻 Author

**Mohamed Hasan**  
GitHub: [@mohamedhasan-m](https://github.com/mohamedhasan-m)

---

⭐ If you find this project useful, consider giving the repository a star.
