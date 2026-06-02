# Fetch User by Email

Endpoint: `GET /api/v1/users/email/{email}`

Base URL: `http://localhost:8082`

## What this does

Fetches a user record by email address.

## Steps

1. Start the service.
2. Send a `GET` request to `/api/v1/users/email/{email}`.
3. Replace `{email}` with the email address you want to look up.
4. The API normalizes the email and searches the database.
5. If the user exists, the user details are returned.
6. If the user does not exist, a `404` response is returned.

## Path Parameter

- `email`: required, valid email address

## Example cURL

```bash
curl -X GET "http://localhost:8082/api/v1/users/email/john.doe@example.com" \
  -H "Accept: application/json"
```

## Success Response

```json
{
  "success": true,
  "message": "User fetched successfully",
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

- `404 Not Found`: user email does not exist
- `500 Internal Server Error`: unexpected server error
