FROM openjdk:17
EXPOSE 8222
ADD target/gateway-0.0.1-SNAPSHOT.jar msgateway.jar
ENTRYPOINT ["java", "-jar", "msgateway.jar"]