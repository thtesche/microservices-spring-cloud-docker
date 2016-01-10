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
`REPOSITORY               TAG                 IMAGE ID       CREATED             VIRTUAL SIZE
thtesche/auth-server     latest              10a3...        13 minutes ago      705.1 MB
thtesche/zuul-proxy      latest              1e3b...        13 minutes ago      698.4 MB
thtesche/eureka-server   latest              3fca...        14 minutes ago      717 MB
thtesche/user-service    latest              d4d6...        14 minutes ago      732.5 MB`


To run all services together type 

`docker-compose up`

After you see *Started Eureka Server* on the console (needs some seconds) you could start requesting an OAuth2 access token.

## Requesting an OAuth2 request token

### Authorization Code Grant
Visit in your browser
`http://localhost:8080/auth-server/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com&scope=users&state=22368`
Use user/password as credentials asked after calling the above url.
Aprove the access and save the returned code (see the url in the browser) in the current shell. Take the value from the code parameter: http://example.com/?code=HVe3yh&state=22368
This value will vary with every call because it's a one time token.

`CODE=HVe3yh`

Request the refresh and access token:
`curl acme:acmesecret@localhost:8080/auth-server/oauth/token -d grant_type=authorization_code -d client_id=acme -d redirect_uri=http://example.com -d code=$CODE -s | jq .`





## OAuth2 secured user service access

 goto http://localhost:8080/user-service/users . You should see all users bootstrap into the user-service.



