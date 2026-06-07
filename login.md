# Fetch User by ID

Endpoint: `GET /api/v1/users/{id}`

Base URL: `http://localhost:8082`

## What this does

Fetches a user record by its numeric ID.

## Steps

1. Start the service.
2. Send a `GET` request to `/api/v1/users/{id}`.
3. Replace `{id}` with the user ID you want to look up.
4. The API searches the database.
5. If the user exists, the user details are returned.
6. If the user does not exist, a `404` response is returned.

## Path Parameter

- `id`: required, numeric user identifier

## Example cURL

```bash
curl -X GET "http://localhost:8082/api/v1/users/1" \
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
    "contact": "9876543210",
    "lastName": "Doe",
    "status": "ACTIVE"
  },
  "timestamp": "2026-06-02T12:00:00Z"
}
```

## Common Errors

- `404 Not Found`: user ID does not exist
- `500 Internal Server Error`: unexpected server error
