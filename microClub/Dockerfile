FROM amazoncorretto:17
EXPOSE 8200
ADD target/microClub-0.0.1-SNAPSHOT.jar msClub.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar msClub.jar"]