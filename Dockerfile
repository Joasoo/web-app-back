FROM --platform=linux/amd64 ubuntu:latest
LABEL authors="allan"
ADD /build/libs/veebi-back-0.0.1-SNAPSHOT.jar /app/app.jar
ADD /src/main/resources/application.properties /app/application.properties
RUN apt-get update
RUN apt-get install -y openjdk-17-jre
CMD java -jar /app/app.jar --spring.config.location=/app/application.properites