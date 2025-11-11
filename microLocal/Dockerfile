FROM openjdk:17
EXPOSE 8500
ADD target/microLocal-0.0.1-SNAPSHOT.jar msLocal.jar
ENTRYPOINT ["java", "-jar", "msLocal.jar"]