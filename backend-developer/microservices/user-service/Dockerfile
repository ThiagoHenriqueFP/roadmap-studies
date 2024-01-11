FROM amazoncorretto:17-alpine-jdk
COPY build/libs/user-service.jar user-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "user-service.jar"]