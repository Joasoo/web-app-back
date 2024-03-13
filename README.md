# Fakebook
## Introduction
This is a social media platform designed to be something along the lines of Facebook. Main idea being users, posts, groups, messaging, friends. Unfortunately we had to cut multiple features since we did not have the time to make them. But here is what this application is designed to manage:
- Accounts
- Profile data (name, age, bio, workplace etc.)
- User posts
- Friend requests
- User search
- Input validation
- External API integration (quotes on Login page)

### Authors
- Markus Joasoo
- Allan Lip
- Johannes Jürgenson

### Used technologies
- Java 17+
- Spring Boot 3.1.3
- PostgreSQL 42.5.4
- Spring Security
- Liquibase 4.17.2
- Mapstruct 1.5.5
- Lombok 1.18.30
- Hibernate Validator 8.0.1
- OpenAPI 2.3.0

### How to start the application
Clone the repository:

`git clone [project url]`

Navigate to the project directory:

`cd [project folder]`

Open the included `docker-compose.yml` and set PostgreSQL username and password as desired or leave them as is.
Start Docker and run the included Docker Compose file:
`docker-compose up -d`

PostgreSQL database should now be running in a Docker container.
Go to `src/main/resources/application.properties` and make sure the `spring.datasource.username`
and `spring.datasource.password` match the username and password in `docker-compose.yml`.

Build the project (this will also download the necessary dependencies):

`./gradlew build`

Run the application:

`./gradlew bootRun --args='--spring.profiles.active=dev'`
