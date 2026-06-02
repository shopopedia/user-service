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
        │               ├── config
        │               │   └── ApplicationConfig.java
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

Run the service locally:

```bash
./gradlew bootRun
```

Build the project:

```bash
./gradlew build
```

Base URL: `http://localhost:8082`

## API Docs

- [register.md](./register.md) - `POST http://localhost:8082/api/v1/users/register`
- [login.md](./login.md) - `GET http://localhost:8082/api/v1/users/{id}`
- [email.md](./email.md) - `GET http://localhost:8082/api/v1/users/email/{email}`
- [user-service.md](./user-service.md) - database structure and table mapping

## Notes

IntelliJ IDEA project files may be present locally under `.idea/`.
