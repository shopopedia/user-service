# User Service Database Structure

This document describes the database structure used by the `user-service` application.

## Database Overview

- Database engine: PostgreSQL
- Database name: `shopopedia_user_db`
- JDBC URL: `jdbc:postgresql://localhost:5432/shopopedia_user_db`
- Username: `shopopedia_user`
- Password: `shopopedia_pass`
- Server port: `8082`
- JPA mode: `ddl-auto: update`

## Table

### `users`

The `users` table stores registered user records.

| Column | Type | Nullable | Unique | Default | Description |
| --- | --- | --- | --- | --- | --- |
| `id` | `BIGSERIAL` / `BIGINT` | No | Yes, primary key | Auto-generated | Surrogate primary key |
| `email` | `VARCHAR(255)` | No | Yes | None | Normalized user email address |
| `first_name` | `VARCHAR(100)` | No | No | None | User first name |
| `contact` | `VARCHAR(20)` | Yes | No | None | User contact number or phone number |
| `password_hash` | `VARCHAR(255)` | Yes | No | None | Encoded password, stored as a hash |
| `last_name` | `VARCHAR(100)` | Yes | No | None | User last name |
| `status` | `VARCHAR(20)` | No | No | `ACTIVE` | User lifecycle status |
| `created_at` | `TIMESTAMP` | No | No | Set on insert | Record creation time |
| `updated_at` | `TIMESTAMP` | No | No | Set on insert, updated on update | Last modification time |

## Entity Mapping

The JPA entity maps to the `users` table:

- Entity class: `com.shopopedia.user.entity.User`
- Table name: `users`
- ID strategy: `GenerationType.IDENTITY`
- Enum storage: `EnumType.STRING`
- Lifecycle hooks:
  - `@PrePersist` sets `createdAt`, `updatedAt`, normalizes `email` and `contact`, and defaults `status` to `ACTIVE`
  - `@PreUpdate` updates `updatedAt` and normalizes `email` and `contact`

## Column Details

### `id`

- Java type: `Long`
- Database type: `BIGINT`
- Constraint: primary key
- Generation: database identity

### `email`

- Java type: `String`
- Database type: `VARCHAR(255)`
- Constraints: `NOT NULL`, `UNIQUE`
- Behavior: trimmed and lowercased before insert/update

### `first_name`

- Java type: `String`
- Database type: `VARCHAR(100)`
- Constraints: `NOT NULL`

### `contact`

- Java type: `String`
- Database type: `VARCHAR(20)`
- Constraints: nullable in the current schema, required by the registration API
- Behavior: trimmed before insert/update

### `password_hash`

- Java type: `String`
- Database type: `VARCHAR(255)`
- Constraints: nullable in the current schema, required by the registration API
- Behavior: stores the encoded password and is never returned by the API

### `last_name`

- Java type: `String`
- Database type: `VARCHAR(100)`
- Constraints: nullable

### `status`

- Java type: `UserStatus`
- Database type: `VARCHAR(20)`
- Constraints: `NOT NULL`
- Allowed values:
  - `ACTIVE`
  - `INACTIVE`
  - `BLOCKED`
- Default value: `ACTIVE`

### `created_at`

- Java type: `LocalDateTime`
- Database type: `TIMESTAMP`
- Constraints: `NOT NULL`
- Set automatically when the entity is first persisted

### `updated_at`

- Java type: `LocalDateTime`
- Database type: `TIMESTAMP`
- Constraints: `NOT NULL`
- Set automatically on insert and every update

## Suggested SQL DDL

If you want to create the table manually, this schema matches the current entity design:

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    contact VARCHAR(20),
    password_hash VARCHAR(255),
    last_name VARCHAR(100),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
```

## Indexes And Constraints

- Primary key: `id`
- Unique constraint: `email`
- Recommended index:
  - `email` for fast lookup by email address

Example:

```sql
CREATE INDEX idx_users_email ON users (email);
```

## Data Flow

1. `POST /api/v1/users/register` receives a new user payload.
2. The service validates the email, contact, and password fields.
3. The service normalizes the email to lowercase, trims the contact value, and hashes the password.
4. The repository checks for an existing record by email.
5. If no record exists, the entity is persisted to the `users` table.
6. `created_at` and `updated_at` are filled automatically.
7. `status` defaults to `ACTIVE` unless explicitly changed later.

## Sample Row

```json
{
  "id": 1,
  "email": "john.doe@example.com",
  "first_name": "John",
  "contact": "9876543210",
  "password_hash": "$2a$10$Qm2n8mYl8h7RjV4qk7K4wO6xv8VY3R1w8Q0j5cZlQmJ3J8x3TQn6S",
  "last_name": "Doe",
  "status": "ACTIVE",
  "created_at": "2026-06-02T12:00:00",
  "updated_at": "2026-06-02T12:00:00"
}
```

## Notes

- `ddl-auto: update` means Hibernate will try to align the table schema automatically at runtime.
- For production, a migration tool such as Flyway or Liquibase is usually preferred.
- The current code uses `LocalDateTime`, so timestamps are stored without timezone information.
