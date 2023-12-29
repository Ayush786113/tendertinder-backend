FROM openjdk:20-jdk
EXPOSE 8080
ADD target/tendertinder-0.0.1-SNAPSHOT.jar tendertinder-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "tendertinder-0.0.1-SNAPSHOT.jar"]
