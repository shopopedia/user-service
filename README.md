# Shopopedia User Service

User service for the Shopopedia platform.

## Project Flow

The service is organized as a Gradle-based Spring Boot application.

```text
user-service
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle
└── src
    └── main
        ├── java
        │   └── com
        │       └── shopopedia
        │           └── user
        │               ├── UserServiceApplication.java
        │               ├── controller
        │               │   └── UserController.java
        │               ├── dto
        │               │   ├── ApiResponse.java
        │               │   ├── RegisterUserRequest.java
        │               │   └── UserResponse.java
        │               ├── entity
        │               │   ├── User.java
        │               │   └── UserStatus.java
        │               ├── exception
        │               │   ├── GlobalExceptionHandler.java
        │               │   ├── UserAlreadyExistsException.java
        │               │   └── UserNotFoundException.java
        │               ├── repository
        │               │   └── UserRepository.java
        │               └── service
        │                   ├── UserService.java
        │                   └── UserServiceImpl.java
        └── resources
            └── application.yml
```

## Package Responsibilities

- `Controller`: Receives HTTP requests.
- `Service`: Handles business logic.
- `Repository`: Talks to the database.
- `Entity`: Maps database tables.
- `DTO`: Defines request and response objects.
- `Exception`: Handles errors.
- `Config`: Contains application settings.

## Package Details

- `controller`: Handles HTTP requests and responses.
- `dto`: Defines request and response payloads.
- `entity`: Contains persistence entities and enums.
- `exception`: Contains custom exceptions and global exception handling.
- `repository`: Handles database access.
- `service`: Contains business logic and service interfaces.
- `config`: Contains application configuration classes.

## Getting Started

Add the service implementation and update this README with:

- Project setup requirements
- Local development commands
- Environment variables
- API endpoints
- Test and build commands
- Deployment notes

## Notes

IntelliJ IDEA project files may be present locally under `.idea/`.
