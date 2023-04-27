FROM maven:latest

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn package

EXPOSE 8080

CMD ["java", "-jar", "target/purejs-0.0.1-SNAPSHOT.jar"]