FROM openjdk:8-jdk-alpine
EXPOSE 9090
COPY ./target/delayservice-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
