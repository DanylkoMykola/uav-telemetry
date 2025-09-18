# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Install Maven and dependencies
RUN apk add --no-cache bash curl git maven

# Copy source
COPY pom.xml .
COPY src ./src

# Build the JAR inside the container
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy JAR from builder
COPY --from=builder /app/target/ingests-telemetry-0.0.1-SNAPSHOT.jar app.jar

# Expose gRPC port
#EXPOSE 9090

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]