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

Run all services together with 

`docker-compose up`

## OAuth2 secured user service access

After you see *Started Eureka Server* on the console goto http://localhost:8080/user-service/users . You should see all users bootstrap into the user-service.



