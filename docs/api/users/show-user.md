# Show User

Returns a User with National Id Param.

**URL** : `/api/v1/users/12585496572`

**Method** : `GET`

## Success Responses

**Code** : `200 OK`

**Request URL** : `/api/v1/users/12585496572`

**Sample Response Body** :

```json
{
  "nationalIdNumber": "12585497585",
  "name": "Talha",
  "surname": "Arıç",
  "phone": "5395893797",
  "monthlyIncome": 8000.0
}
```

## Error Responses

**Code** : `404 NOT FOUND`

**Request URL** : `/api/v1/users/12218526572`

**Sample Response Body** :

```json
{
  "status": "NOT_FOUND",
  "message": "User not found!",
  "errors": [
    "User not found!"
  ]
}
```