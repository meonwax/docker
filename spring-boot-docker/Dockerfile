FROM openjdk:10-jre
VOLUME /tmp
EXPOSE 8080
ADD demo/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-XX:MaxRAMPercentage=100", "-jar", "/app.jar"]
