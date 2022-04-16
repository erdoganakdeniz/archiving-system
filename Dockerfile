FROM openjdk:15.0.2-jdk
COPY target/archiving-system-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080