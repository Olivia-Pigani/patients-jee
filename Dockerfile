FROM maven:3.8-amazoncorretto-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package

FROM tomcat:10-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/patients-jee.war

EXPOSE 8080
