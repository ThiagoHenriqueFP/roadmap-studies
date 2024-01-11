FROM amazoncorretto:17-alpine-jdk
COPY build/libs/gateway.jar gateway.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "gateway.jar"]