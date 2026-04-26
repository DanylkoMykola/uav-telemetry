# Stage 1: Build Core and Service
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

# 1. Build and publish Core library
COPY core ./core
WORKDIR /app/core
RUN chmod +x gradlew && ./gradlew publishToMavenLocal

# 2. Build the Processor Service
WORKDIR /app/processor-service
COPY processor-service/gradlew .
COPY processor-service/gradle ./gradle
COPY processor-service/build.gradle .
COPY processor-service/settings.gradle .
COPY processor-service/src ./src

RUN chmod +x gradlew && ./gradlew bootJar -x test

# Stage 2: Runtime
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/processor-service/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
