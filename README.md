# Generic-Application

## Summary
This repository stores a generic application that posteriorly we're going to deploy into the cloud.
Its purpose is to reproduce common operations of any application, write/read the database, process data, and communications.

In a further step a benchmark is going to be responsible to manage those operations and with them analyze and conclude some advantages/disadvantages of the scalability and elasticity of different cloud providers.

## How to start

### Dependencies

This generic application is all implemented based on [Spring Boot](https://spring.io/projects/spring-boot) framework, a very common one when talking of Java Web Development.

---
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.3.RELEASE</version>
    </parent>
---

While not integrated with a database set by the cloud provider _Generic-Application_ is using [mariaDB](https://mariadb.org/). The object mapping operated by [_Hibernate_](https://hibernate.org/) and within the implementation provided by [_Spring Data JPA_](https://spring.io/projects/spring-data-jpa) integrates our application to data.

For reasons of consistency, we configured [Flyway](https://flywaydb.org/) to apply the respective data migration before the app execution. 

### Docker

First we need to set up the MariaDB docker container to support our application.

---

docker run --name mariadb_pfg -e MYSQL_ROOT_PASSWORD=pass -d mariadb/server:10.3

---

And then use the IP 172.17.0.2 to connect.


