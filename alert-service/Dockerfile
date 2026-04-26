# Stage 1: Build Core and Service
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# 1. Build and publish Core library
COPY core ./core
WORKDIR /app/core
RUN chmod +x gradlew && ./gradlew publishToMavenLocal

# 2. Build the Alert Service
WORKDIR /app/alert-service
COPY alert-service/gradlew .
COPY alert-service/gradle ./gradle
COPY alert-service/build.gradle .
COPY alert-service/settings.gradle .
COPY alert-service/src ./src

RUN chmod +x gradlew && ./gradlew bootJar -x test

# Stage 2: Runtime
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/alert-service/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
