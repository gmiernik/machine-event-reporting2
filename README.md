# Reporting of Machine Events

Test project of backend services in hexagonal architecture.

## Change Log

### v0.2.0

- Added `Get Machine to focus on` use case
- Added Machine Event Report REST API 

### v0.1.0

- Defined Business Domain objects
- Added `Send Machine Error`port with JMS Adapter
- Added `Machine Event` repository with JPA Adapter

## How to run

From command line or IDE run maven with following goal:
```
spring-boot:run
```

## REST API Service

Base url: [http://localhost:8080/api/v1/machine/event/]()

- Operation: get machine to focus on => [/api/v1/machine/event/machine-to-focus-on?ndays=22]()


