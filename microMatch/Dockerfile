FROM openjdk:17
EXPOSE 8600
ADD target/microMatch-0.0.1-SNAPSHOT.jar msMatch.jar
ENTRYPOINT ["java", "-jar", "msMatch.jar"]