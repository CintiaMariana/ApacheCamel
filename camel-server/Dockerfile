FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/camel-server-0.0.1-SNAPSHOT.jar /app/camel-server.jar

CMD ["java", "-jar", "/app/camel-server.jar"]
