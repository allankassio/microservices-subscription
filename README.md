# microservices-subscription

[![GPLv3 License](https://img.shields.io/badge/license-GPL%20v3-yellow.svg)](https://opensource.org/licenses/) 
![Maven](https://img.shields.io/badge/maven-3.8.6-green) 
![Java](https://img.shields.io/badge/java-17-red) 
![Spring Boot](https://img.shields.io/badge/springboot-2.7.0-ligthgreen)
![Netflix Eureka](https://img.shields.io/badge/eureka-netflix-darkred)
![Apache Kafka](https://img.shields.io/badge/kafka-3.2.0-black)
![Project Lombok](https://img.shields.io/badge/lombok-1.18.24-darkred)
![Zookeeper](https://img.shields.io/badge/zookeeper-3.8.0-darkgreen)
![Swagger](https://img.shields.io/badge/swagger-2022-blue)
![PostgreSQL](https://img.shields.io/badge/postgreSQL-14.4-purple)
![Alpine](https://img.shields.io/badge/alpine-3.16-darkblue)
![Docker](https://img.shields.io/badge/docker-4.9.1-blue)


This system was implemented through independent microservices to enable subscriptions. This system allows for layers of reuse. Architecturally, the system is divided into a public layer, which can be consulted and accessed by other systems and graphical interfaces. And by a security-encapsulated layer, which accesses the databases and allows communication between services and the public layer. The system consists of a pool of three microservices.

## System Architecture Design

![alt text](https://github.com/allankassio/microservices-subscription/blob/main/docs/diagrams/microservices-subscription-diagram.jpg)

## PUBLIC NETWORK ZONE (Public Layer)

### Public Service

Provides an API so that other systems, usually frontend systems, can communicate with the application.
This service is linked with the Eureka Server. And to work properly, it depends on Eureka Server containers and Subscription Service being active.

## SECURE NETWORK ZONE (Encapsuled Layer)

### Subscription Service

Allows e-mails to be registered or removed from the registration list. In addition, it is related to its own database, in which the data from the registration list are manipulated.
This service links with Eureka Server and Apache Kafka. And to work correctly, it depends on Eureka Server, Apache Kafka and PostgreSQL containers being active.

### Email Service

It remains active by sending emails to users registered in the subscription list. It relates to its own database, in which data from sent emails are manipulated.
This service links with Eureka Server and Apache Kafka. And to work correctly, it depends on Eureka Server, Apache Kafka and PostgreSQL containers being active.

### Apache Kafka

It is an open-source stream processing platform. It aims to provide a unified, high-capacity, low-latency platform for real-time data handling. In this system, Apache Kafka is responsible for sending messages to the E-mail Service, to which e-mails, which have been registered in the Subscription Service, the e-mails should be sent.

### Apache Zookeeper

ZooKeeper is a centralized service for maintaining configuration information, naming, providing distributed synchronization, and providing group services. In this system, it helps to avoid bugs generated by race conditions that may occur.

### Netflix Eureka Server

For Netflix Eureka, each client can simultaneously act as a server to replicate its status to a connected peer. In other words, a client retrieves a list of all connected peers in a service registry and makes all additional requests to other services through a load balancing algorithm. To be informed of the presence of a client, they need to send a heartbeat signal to the registry. In this system, Netflix Eureka orchestrates the communications that take place between the public layer and the encapsulated layer.

## How to Use

The build and execution of the system is based on docker containers and Apache Maven.

### Build .jar

Initially, before running docker compose, it is necessary to create the .jar build of each service. To do this, navigate to the folder, and use the following commands:

```sh
cd email-service
mvn clean package

cd subscription-service
mvn clean package

cd public-service
mvn clean package

cd eureka-server
mvn clean package
```

### Isolated run

If you want to run that service alone, use the following command (but remember that to work, the database, kafka and zookeeper containers must already be running.

```sh
cd email-service
mvn spring-boot:run

cd subscription-service
mvn spring-boot:run

cd public-service
mvn spring-boot:run

cd eureka-server
mvn spring-boot:run
```

### Docker

To run all containers simultaneously, it is recommended to use the following command based on docker compose.

```sh
docker compose -f "docker-compose.yml" up -d --build
```
