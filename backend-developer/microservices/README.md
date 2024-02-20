# Microservices
For this project i will enter in the microservices world, creating some projects with each with a single responsability, such as client or user service.

## Scope
A insurance company want to manage your clients, they need to store customer data in a service and consume a service that allow consulting additional data from a single customer, since this additional consulting is in third-party servers, a time and fault tolerant service must be implemented. This third-party servers are another insurance company systems, all of the data isn't structured and the system must lead with theses data.

## Architecture

### gateway and discovery server
The gateway will act as entrypoint to this system and will redirect to the correct service, using the eureka server to discover services and redirect a request.

### client service
Must store clients basic data, such as first and last name, email, insurance type etc. Will be used to perform CRUD operations against client entity.

### user service
Will consume client service to get pertinent data and display information for each client, this info's can be lazy fetched. Example, if a user has some information in a third-party service, this will be fetched asynchronously. This service will send a request to a third-party servers by messaging services and after a new client data is come up from other server, this client will be updated at respective service.

### third-party servers
This service is a "black box" full of "servers" that will display independent information about a client and return these for other insurance company.

## Technologies


**client service:**
- Java 17
- Spring boot 3.2 (web, data jpa, h2 and eureka client projects)

**user service:**
- Java 17
- Spring boot 3.2 (web, data jpa, h2 and eureka client projects)

**discovery server:**
- Java 17
- Spring boot 3.2 (eureka server)

**gateway:**
- Java 17
- Spring cloud gateway

## TODO
 - Implement auth service
 - Implement circuit breakers
 - Implement a single view actuator page

