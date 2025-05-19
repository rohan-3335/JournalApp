# Use official OpenJDK 17 image (Eclipse Temurin)
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from target folder into the container
COPY target/*.jar app.jar

# Expose the port your app listens on (change to 8080 if that's your default)
EXPOSE 8080

# Run the application with 'prod' profile active
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
