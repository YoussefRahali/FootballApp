FROM amazoncorretto:17
EXPOSE 8500
ADD target/microLocal-0.0.1-SNAPSHOT.jar msLocal.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar msLocal.jar"]