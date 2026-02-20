FROM amazoncorretto:17
EXPOSE 8400
ADD target/microJoueur-0.0.1-SNAPSHOT.jar msJoueur.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar msJoueur.jar"]