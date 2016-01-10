# Demonstration of an OAuth2 secured micorservice behind a request router using Spring Boot/Cloud (Zuul, Eureka, Authorization- and ResourceServer)

## Compoments

The Setup consists of 

* an user-service (microservice)
* an authorization server
* a services registry (eureka)
* a request router (zuul)
and uses docker to run all applications on a single machine.

## Prerequisites
* Docker
* maven
* Java8

## Instruction

Build from the root of the multi-module project with 

`mvn clean install`

This creates docker images for all components:

      REPOSITORY               TAG                 IMAGE ID       CREATED             VIRTUAL SIZE
      thtesche/auth-server     latest              10a3...        13 minutes ago      705.1 MB
      thtesche/zuul-proxy      latest              1e3b...        13 minutes ago      698.4 MB
      thtesche/eureka-server   latest              3fca...        14 minutes ago      717 MB
      thtesche/user-service    latest              d4d6...        14 minutes ago      732.5 MB


To run all services together type 

`docker-compose up`

After you see *Started Eureka Server* on the console (needs some seconds) you could start requesting an OAuth2 access token.

## Requesting an OAuth2 request token

### Authorization Code Grant
Visit in your browser

<a href="http://localhost:8080/auth-server/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com&scope=users&state=22368">http://localhost:8080/auth-server/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com&scope=users&state=22368</a>

Use user/password as credentials asked after calling the above url.
Approve the access and save the returned code (see the url in the browser) in the current shell. Take the value from the code parameter: http://example.com/?code=HVe3yh&state=22368
This value will vary with every call because it's a one time token.

`CODE=HVe3yh`

Request the refresh and access token:

      curl acme:acmesecret@localhost:8080/auth-server/oauth/token -d grant_type=authorization_code \\
      -d client_id=acme -d redirect_uri=http://example.com -d code=$CODE -s | jq .

      {
      "scope": "users",
      "expires_in": 43199,
      "refresh_token": "7bd3efa5-ad48-445e-b381-a1010c785162",
      "token_type": "bearer",
      "access_token": "e2c4c39b-8c96-4652-94a7-9ca14b647557"
      }

`TOKEN=e2c4c39b-8c96-4652-94a7-9ca14b647557`

See [OAuth2 secured user service access](#access_user_service) for the usage of the token.

### Implicit Grant
Visit in your browser

<a href="http://localhost:8080/auth-server/oauth/authorize?response_type=token&client_id=acme&redirect_uri=http://example.com&scope=users&state=457365">http://localhost:8080/auth-server/oauth/authorize?response_type=token&client_id=acme&redirect_uri=http://example.com&scope=users&state=457365</a>

Use user/password as credentials asked after calling the above url.
Approve the access and save the returned code (see the url in the browser) in the current shell. Take the value from the access_token parameter: http://example.com/#access_token=e2c4c39b-8c96-4652-94a7-9ca14b647557&token_type=bearer&state=457365&expires_in=42122

`TOKEN=e2c4c39b-8c96-4652-94a7-9ca14b647557`

See [OAuth2 secured user service access](#access_user_service) for the usage of the token.

### Resource Owner Password Credentials Grant

      curl -s acme:acmesecret@localhost:8080/auth-server/oauth/token -d grant_type=password -d client_id=acme -d scope=users -d username=user -d password=password | jq .
      {
      "scope": "users",
      "expires_in": 41716,
      "refresh_token": "7bd3efa5-ad48-445e-b381-a1010c785162",
      "token_type": "bearer",
      "access_token": "e2c4c39b-8c96-4652-94a7-9ca14b647557"
      }

`TOKEN=e2c4c39b-8c96-4652-94a7-9ca14b647557`

See [OAuth2 secured user service access](#access_user_service) for the usage of the token.

### Client Credentials Grant

      curl -s acme:acmesecret@localhost:8080/auth-server/oauth/token  -d grant_type=client_credentials -d scope=users | jq .
      {
      "scope": "users",
      "expires_in": 43199,
      "token_type": "bearer",
      "access_token": "b98ddcf0-ae90-40b2-83f9-5f22b28bf277"
      }

NOTE: This grant requests the access token for acme not for user. 

## OAuth2 secured user information resource in authentication server

This call delivers all information about the user for whom the access token was issued.

      curl -s -H  "Authorization: Bearer $TOKEN" http://localhost:8080/auth-server/user | jq .
      {
        "name": "user",
        "details": {
          "decodedDetails": null,
          "tokenType": "Bearer",
          "tokenValue": "e2c4c39b-8c96-4652-94a7-9ca14b647557",
          "sessionId": null,
          "remoteAddress": "172.17.0.3"
        },
        "authorities": [
          {
            "authority": "ROLE_ADMIN"
          },
          {
            "authority": "ROLE_USER"
          }
      ],
      "authenticated": true,
        "userAuthentication": {
          "name": "user",
          "credentials": null,
          "principal": {
            "enabled": true,
            "credentialsNonExpired": true,
       .....



## <a name="access_user_service"></a>OAuth2 secured user service access

      curl -s -H  "Authorization: Bearer $TOKEN" http://localhost:8080/user-service/users | jq . 
      {
        "page": {
          "number": 0,
          "totalPages": 1,
          "totalElements": 2,
          "size": 20
        },
        "_links": {
          "profile": {
            "href": "http://localhost:8080/user-service/profile/users"
          },
          "self": {
            "href": "http://localhost:8080/user-service/users"
          }
        },
        "_embedded": {
          "users": [
            {
              "_links": {
                "user": {
                  "href": "http://localhost:8080/user-service/users/1"
                },
                "self": {
                  "href": "http://localhost:8080/user-service/users/1"
                }
              },
              "email": "mick@mudder.com",
              "lastName": "Mudder",
              "nickName": "muddy",
              "firstName": "Mick"
            },
            {
              "_links": {
                "user": {
                  "href": "http://localhost:8080/user-service/users/2"
                },
                "self": {
                  "href": "http://localhost:8080/user-service/users/2"
                }
              },
              "email": "denis@dorgen.com",
              "lastName": "Dorgen",
              "nickName": "dorgy",
              "firstName": "Dennis"
            }
          ]
        }
      }


