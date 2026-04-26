# Stage 1: Build Core and Service
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# 1. Build and publish Core library
COPY core ./core
WORKDIR /app/core
RUN chmod +x gradlew && ./gradlew publishToMavenLocal

# 2. Build the Dashboard Service
WORKDIR /app/dashboard-service
COPY dashboard-service/gradlew .
COPY dashboard-service/gradle ./gradle
COPY dashboard-service/build.gradle .
COPY dashboard-service/settings.gradle .
COPY dashboard-service/src ./src

RUN chmod +x gradlew && ./gradlew bootJar -x test

# Stage 2: Runtime
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/dashboard-service/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
