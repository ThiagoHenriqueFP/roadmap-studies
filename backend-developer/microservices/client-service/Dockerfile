FROM amazoncorretto:17-alpine-jdk
COPY build/libs/client-service.jar client-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "client-service.jar"]