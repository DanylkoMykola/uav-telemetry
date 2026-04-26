# Stage 1: Build Core and Service
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# 1. Build and publish Core library to local Maven repo inside the container
COPY core ./core
WORKDIR /app/core
RUN chmod +x gradlew && ./gradlew publishToMavenLocal

# 2. Build the Ingest Service
WORKDIR /app/ingest-service
COPY ingest-service/gradlew .
COPY ingest-service/gradle ./gradle
COPY ingest-service/build.gradle .
COPY ingest-service/settings.gradle .
COPY ingest-service/src ./src

RUN chmod +x gradlew && ./gradlew bootJar -x test

# Stage 2: Runtime
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/ingest-service/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
