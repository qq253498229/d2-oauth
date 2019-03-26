FROM maven:3-jdk-8-alpine
WORKDIR /app
COPY pom.xml ./pom.xml
RUN mvn package

FROM maven:3-jdk-8-alpine
WORKDIR /app
COPY src ./src
COPY --from=0 /app/pom.xml ./pom.xml
COPY --from=0 /root/.m2 /root/.m2
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=1 /app/target/app.jar ./app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app/app.jar"]
