# servicemanual-java
A backend application for a pre-job interview assingment. A Spring boot application using MySQL database. My first time touching Java and dockerizing an app (which didn't go too well).

## Requirements
- [JDK 13](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/) and Docker-compose

## Installation
- Clone the project
- Run `mvn package -DskipTests` in the project's root to build the project
- Build the docker image with `docker image build -t service-manual .`
- Run `docker-compose up` twice to start the application (something goes wrong on the first try but on the second try it works, I have no idea why).
  On Windows, the app is running on docker-machines default ip, which can be found with `$ docker-machine ip`, for example `192.168.99.100:8080`. On Linux, the app is running on `localhost:8080`.
- Initialize some factory devices with a `GET` request to `localhost:8080/init`. I know this is not a proper way to populate a database, I tried to do it with docker-compose and with a data.sql file but didn't get anything to work. Atleast the app can be tested.

## Available endpoints
Import `Maintenance Task Example Requests.postman_collection.json` file into [Postman](https://www.getpostman.com/) for example queries and brief descriptions.

