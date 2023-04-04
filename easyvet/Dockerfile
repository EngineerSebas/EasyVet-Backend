FROM amazoncorretto:11-alpine-jdk
COPY easyvet/target /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
