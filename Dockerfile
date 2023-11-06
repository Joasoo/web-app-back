FROM ubuntu:20.04
LABEL authors="allan"
ADD /build/libs/veebi-back-0.0.1-SNAPSHOT.jar /app/app.jar
ADD /src/main/resources/application.properties /app/config/application.properties
ADD /src/main/resources/config/application-prod.properties /app/config/application-prod.properties
RUN apt-get update
RUN apt-get install -y openjdk-17-jre-headless
CMD java -jar /app/app.jar --spring.config.location=/app/config/ --spring.profiles.active=prod
