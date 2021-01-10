FROM maven:3.6.3-openjdk-15 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:14.0.2-jdk-oraclelinux8

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/Generic-Application-1.0-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-Dspring.profiles.active=container", "-jar", "Generic-Application-1.0-SNAPSHOT.jar"]