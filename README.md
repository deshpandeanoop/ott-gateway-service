# ott-gateway-service
OTT Gateway Service

## API End points
End point for user to register onto OTT gatway platform
```
curl --location --request POST 'http://localhost:8080/ott-gateway-service/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userDetails" : {
        "firstName" : "test_firstname",
        "lastName" : "test_lastname",
        "username" : "test_username",
        "password" : "test_password@123"
    }
}'
```
End point for validating the user signed upon the OTT gateway platform
```
curl --location --request POST 'http://localhost:8080/ott-gateway-service/v1/users/validate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "test_username",
    "password": "test_password@123"
}'
```
End point for adding in the OTT details
```
curl --location --request POST 'http://localhost:8080/ott-gateway-service/v1/ottaccounts' \
--header 'Content-Type: application/json' \
--data-raw '{
    "ottServiceAccountsDetails" : [
        {
            "username" : "ott1_username",
            "password" : "ott1_password",
            "platformType" : "AMAZON_PRIME",
            "gateWayServiceUserId" : "test_username"
        }
    ]
}'
```
End point for fetching the OTT details of a user
```
curl --location --request GET 'http://localhost:8080/ott-gateway-service/v1/ottaccounts?gatewayUserId=test_username' \
--data-raw ''
```
