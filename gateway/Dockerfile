# Stage 1: Build
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM amazoncorretto:17
EXPOSE 8222
COPY --from=build /app/target/gateway-0.0.1-SNAPSHOT.jar msgateway.jar
ENTRYPOINT ["java", "-jar", "msgateway.jar"]