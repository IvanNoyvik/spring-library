#
# Build stage
#
FROM maven:3.8.1-jdk-11-slim AS build
WORKDIR /usr/app
COPY src ./src
COPY pom.xml .
RUN mvn clean install -Dmaven.test.skip=true
#
# Package stage
#
FROM tomcat:latest
ARG path=/usr/app
# WORKDIR ${path}
COPY --from=build ${path}/target/*.war /usr/local/tomcat/webapps/
# ADD /target/*.war /usr/local/tomcat/webapps/
# EXPOSE 8080
# CMD [“catalina.sh”, “run”]


# CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx280m -jar app.jar"]

# COPY --from=build ${path}/target/*.jar ${path}/app.jar
# ADD ${path}/target/*.war /usr/local/tomcat/webapps/