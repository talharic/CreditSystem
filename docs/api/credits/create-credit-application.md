# Create Credit Application for User


**URL** : `/api/v1/credit-applications`

**Method** : `POST`

## Request

**Code** : `200 OK`

**Request URL** : `/api/v1/credit-applications`


**Sample Request Body** :

```json
{
  "nationalIdNumber": "15882275492",
  "name": "Talha",
  "surname": "Arıç",
  "phone": "5395893797",
  "monthlyIncome": 8000
}
```

## Success Responses

**Sample Response Body** :

```json
{
  "nationalIdNumber": "15882275492",
  "creditLimitAmount": 20000,
  "applicationDate": "2022-02-24T18:25:56.563063",
  "creditApplicationResult": "APPROVED"
}
```
