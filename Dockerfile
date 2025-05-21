FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/journalApp-0.0.1-SNAPSHOT.jar journalApp.jar

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar journalApp.jar"]
