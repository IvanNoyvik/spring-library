#
# Build stage
#
FROM maven:3.8.1-amazoncorretto-11 AS build
WORKDIR /usr/app
COPY src ./src
COPY pom.xml .
RUN mvn clean package -Dmaven.test.skip=true
#
# Package stage
#
FROM openjdk:11-jdk-slim
ARG path=/usr/app
WORKDIR ${path}
COPY --from=build ${path}/target/*.war ${path}/app.war
EXPOSE 8080
CMD [ "sh", "-c", "java -jar app.war"]
# CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx280m -jar app.jar"]