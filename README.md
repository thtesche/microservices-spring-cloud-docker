# Demonstration of Spring Boot/Cloud Docker builds and docker-compose/swarm

## Instruction

Build with `mvn clean install`

Run all services with `docker-compose up`

After you see *Started Eureka Server* on the console goto http://localhost:8080/user-service/users . You should see all users bootstrap into the user-service.

## Compoments

The Setup consists of 

* a user-service (microservice)
* a services registry (eureka)
* a api proxy (zuul)

