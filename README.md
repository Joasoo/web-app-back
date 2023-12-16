# Fakebook
## Introduction
This is a social media platform designed to be something along the lines of Facebook. Main idea being users, posts, groups, messaging, friends. Unfortunately we had to cut multiple features since we did not have the time to make them. But here is what this application is designed to manage:
- Accounts
- Profile data (name, age, bio, workplace etc.)
- User posts
- Friend requests
- User search
- Input validation

### Authors
- Markus Joasoo
- Allan Lip
- Johannes Jürgenson

### Used technologies
- Java 17+
- Spring Boot 
- PostgreSQL 42.5.4
- Spring Security
- Liquibase 4.17.2
- Mapstruct 1.5.5
- Lombok
- Hibernate validator 8.0.1
- OpenAPI 2.3.0

### How to start the application
Clone the repository:

`git clone [project url]`

Navigate to the project directory:

`cd [project folder]`

Start Docker and build the container:
`docker-compose up -d`

The application.properties and postgres database now running in Docker container should have the correct configuration.
If necessary, locate the application.properties file and change PostgreSQL user and password from there. 

### -develop vms tagi lisamise jutt siia vist

Build the project (this will also download the necessary dependencies):

`./gradlew build`

Run the application:

`./gradlew bootRun`
