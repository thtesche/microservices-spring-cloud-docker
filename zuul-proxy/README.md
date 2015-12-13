# lab09-udemy-spring-cloud-Zuul
## Spring Cloud Simple Zuul Gateway

Start the following modules:
* lab01 with all 3 instances (serviceId will be lab-04)
* lab04 Cloud Eureka Server with the default profile

Then run with `mvn spring-boot:run` and go to `http://localhost:8080/lab-04`

To iterate through all 3 lab-04 instances call `http://localhost:8080/lab-04/content` multiple times. Then you should see the text 'Content from lab-04-inst-0X' with numbers from 1-3 for the X.
