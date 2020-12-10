# Siemanejro RestApi

[![](https://img.shields.io/badge/Spring_Boot-2.3.0-yellowgreen)](https://spring.io/projects/spring-boot)
[![](https://img.shields.io/badge/Maven-4.0.0-green)](https://maven.apache.org)
[![](https://img.shields.io/badge/Webflux-2.3.0-red)](https://docs.spring.io/spring-framework/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html)
[![](https://img.shields.io/badge/Google_Api_Client-1.30.8-blue)](https://developers.google.com/api-client-library/java)
[![](https://img.shields.io/badge/Spring_Security_OAuth2-2.3.6-orange)](https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html5/)
[![](https://img.shields.io/badge/SpringDoc_OpenApi_Ui-1.2.32-lightgrey)](https://github.com/springdoc/springdoc-openapi)
[![](https://img.shields.io/badge/Javax%20Persistence%20Api-2.2-yellowgreen)](https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html)

Spring Boot backend application (rest api) for Siemanejro. 

### Technologies stack

- Spring Boot application based on [REST](https://restfulapi.net/) architecture style using [Basic Authentication](https://en.wikipedia.org/wiki/Basic_access_authentication). 
- Data is fetching from https://www.football-data.org/ using [Spring WebFlux Library](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html) and saving into MySQL database.
- Both Spring Boot application and MySQL server are running in [Docker](https://www.docker.com/) containers described below.

## RestApi Documentation

The Rest Api documentation with description of endpoints is located here:<br>
* https://app.swaggerhub.com/apis/nazkordd/Siemanejro-Bets-API/1.0.1

## Running locally using Docker

- Navigate to the root of project directory
- Build the spring-boot application and create docker image by running
```bash
mvn clean package
```
- Run the created docker image and initialize mysql database using docker-compose
```bash
docker-compose up -d
```

### Health checking 

Type the following command to ensure that:
- The application is up and running (the response should be just "OK"):
```bash
curl localhost:8080/healthCheck
```
- Admin (user is already in db) has been authorized by application: 
```bash
curl -u admin:admin localhost:8080/healthCheck/authorized
```
