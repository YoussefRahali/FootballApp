FROM amazoncorretto:17
EXPOSE 8100
ADD target/microBillet-0.0.1-SNAPSHOT.jar msbillet.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar msbillet.jar"]