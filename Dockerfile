FROM openjdk:17-oracle
ADD build/libs/psu-adapter-0.0.1-SNAPSHOT.jar psu-adapter-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "psu-adapter-0.0.1-SNAPSHOT.jar"]
