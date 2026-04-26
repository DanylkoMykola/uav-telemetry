# Stage 1: Build Core and Service
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# 1. Build and publish Core library
COPY core ./core
WORKDIR /app/core
RUN chmod +x gradlew && ./gradlew publishToMavenLocal

# 2. Build the Simulator Service
WORKDIR /app/uav-simulator
COPY uav-simulator/gradlew .
COPY uav-simulator/gradle ./gradle
COPY uav-simulator/build.gradle .
COPY uav-simulator/settings.gradle .
COPY uav-simulator/src ./src

RUN chmod +x gradlew && ./gradlew bootJar -x test

# Stage 2: Runtime
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/uav-simulator/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
