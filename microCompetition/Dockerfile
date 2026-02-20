FROM amazoncorretto:17
EXPOSE 8300
ADD target/microCompetition-0.0.1-SNAPSHOT.jar msCompetition.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar msCompetition.jar"]