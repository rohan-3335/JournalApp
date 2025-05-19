# Use official OpenJDK 17 image
FROM eclipse-temurin:17-jdk-jammy

# Create app directory
WORKDIR /app

# Copy the JAR file from target folder
COPY target/*.jar app.jar

# Expose the port your app listens on (8081 here)
EXPOSE 8081

# Run the application with 'prod' profile active
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
