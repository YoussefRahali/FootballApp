FROM openjdk:17
EXPOSE 8880
ADD target/config-server-0.0.1-SNAPSHOT.jar msconfig.jar
ENTRYPOINT ["java", "-jar", "msconfig.jar"]