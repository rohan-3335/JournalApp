# Use Maven + JDK 17 image to build
FROM maven:3.9.0-eclipse-temurin-17-jdk-jammy AS build

WORKDIR /app

# Copy your pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project and package the jar
RUN mvn clean package -DskipTests

# Use smaller JRE base image to run
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/journalApp-0.0.1-SNAPSHOT.jar journalApp.jar

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar journalApp.jar"]
