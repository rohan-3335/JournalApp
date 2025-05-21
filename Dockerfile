# Use minimal Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Create app directory inside container
WORKDIR /app

# Copy the compiled JAR
COPY target/*.jar app.jar

# Set active Spring profile to prod
ENV SPRING_PROFILES_ACTIVE=prod

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "journalApp.jar"]
