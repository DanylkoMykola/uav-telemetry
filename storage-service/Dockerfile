# Stage 1: Build Core and Service
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# 1. Build and publish Core library
COPY core ./core
WORKDIR /app/core
RUN chmod +x gradlew && ./gradlew publishToMavenLocal

# 2. Build the Storage Service
WORKDIR /app/storage-service
COPY storage-service/gradlew .
COPY storage-service/gradle ./gradle
COPY storage-service/build.gradle .
COPY storage-service/settings.gradle .
COPY storage-service/src ./src

RUN chmod +x gradlew && ./gradlew bootJar -x test

# Stage 2: Runtime
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/storage-service/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
