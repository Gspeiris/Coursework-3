# Research Project Tracker

This is a backend API for a Research Project Tracker system, developed as part of the Comprehensive Master Java Developer (CMJD) course. It enables users to create, manage, and monitor academic research projects within an educational environment.

The system is built with Spring Boot and includes features like authentication, project management, and role-based access control.

## Technology Stack

- **Backend Framework**: Spring Boot
- **Database Layer**: Spring Data JPA with MySQL
- **Security**: Spring Security with JWT (JSON Web Token) Authentication
- **Build Tool**: Maven
- **Java Version**: 11

## Features

- **User Management**: User registration and login with role-based access (ADMIN, PI, MEMBER, VIEWER).
- **Project Management**: CRUD operations for research projects.
- **Milestone Tracking**: Add, update, and delete project milestones.
- **Document Handling**: Upload and manage project-related documents.
- **Secure API**: Endpoints are secured based on user roles.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.2+
- MySQL 8.0+

### Configuration

1.  **Database**:
    - Create a MySQL database (e.g., `research_tracker_db`).
    - Open `src/main/resources/application.properties`.
    - Update the `spring.datasource.username` and `spring.datasource.password` properties with your MySQL credentials.

2.  **JWT Secret**:
    - In `src/main/resources/application.properties`, replace the placeholder value for `jwt.secret` with a strong, Base64-encoded secret key. You can generate one using an online tool.

### Running the Application

1.  **Build the project**:
    ```bash
    mvn clean install
    ```

2.  **Run the application**:
    ```bash
    mvn spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

## API Endpoints

The API endpoints are documented in the code and follow RESTful conventions. Here is a summary of the main endpoints:

-   `POST /api/auth/signup`: Register a new user.
-   `POST /api/auth/login`: Authenticate and receive a JWT.
-   `GET /api/projects`: List all projects.
-   `POST /api/projects`: Create a new project.
-   ... and many more for managing projects, milestones, documents, and users.

Refer to the controller classes for detailed information on each endpoint and the required roles.
