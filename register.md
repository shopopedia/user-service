# User Registration

Endpoint: `POST /api/v1/users/register`

Base URL: `http://localhost:8082`

## What this does

Creates a new user record in the system.

## Steps

1. Start the service.
2. Send a `POST` request to `/api/v1/users/register`.
3. Include a JSON body with `email`, `firstName`, and optionally `lastName`.
4. The API validates the request.
5. If the email does not already exist, the user is stored in the database.
6. A success response is returned with the created user details.

## Request Headers

```http
Content-Type: application/json
Accept: application/json
```

## Request Body

```json
{
  "email": "john.doe@example.com",
  "firstName": "John",
  "lastName": "Doe"
}
```

## Field Rules

- `email`: required, must be a valid email address
- `firstName`: required, maximum 100 characters
- `lastName`: optional, maximum 100 characters

## Example cURL

```bash
curl -X POST "http://localhost:8082/api/v1/users/register" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

## Success Response

```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "status": "ACTIVE"
  },
  "timestamp": "2026-06-02T12:00:00Z"
}
```

## Common Errors

- `400 Bad Request`: request body is missing or invalid
- `409 Conflict`: user already exists with the same email
- `500 Internal Server Error`: unexpected server error
