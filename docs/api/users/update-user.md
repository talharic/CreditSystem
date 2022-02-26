# Update User

Updates a User.

**URL** : `/api/v1/users/{nationalIdNumber}`

**Method** : `PUT`

**Sample Request Body**

```json
{
  "name": "Talha",
  "surname": "Arıç",
  "phone": "5395893797"
}
```

## Success Response

**Code** : `200`

**Sample Response Body** :

```json
{
  "nationalIdNumber": "12585496572",
  "name": "Talha",
  "surname": "Arıç",
  "phone": "5395893797",
  "monthlyIncome": 8000
}
```

## Error Responses

**Code** : `404 NOT FOUND`

**Request URL** : `/api/v1/users/3215649876465112`

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