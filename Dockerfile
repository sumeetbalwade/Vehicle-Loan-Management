FROM openjdk:18-jdk-alpine3.15
VOLUME [ "/tmp" ]
COPY target/VLoanManagment-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]