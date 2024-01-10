FROM amazoncorretto:17-alpine-jdk
COPY build/libs/gateway.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]