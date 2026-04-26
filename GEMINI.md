# Project Context: UAV Telemetry Ingests

This project is a distributed system for ingesting and processing UAV (Unmanned Aerial Vehicle) telemetry data.

## Architecture Overview

The system follows a microservices architecture:
- **UAV Simulator**: Generates and sends raw telemetry data.
- **Ingest Service**: Receives telemetry via REST/gRPC, validates it, and publishes to Kafka (`telemetry.raw`).
- **Processor Service**: Consumes from Kafka, processes/filters data, and identifies anomalies.
- **Storage Service**: Consumes processed telemetry and persists it to PostgreSQL.
- **Dashboard Service (API)**: Exposes REST APIs for historical telemetry and flight replay.
- **Alert Service**: Consumes alerts and handles notifications.
- **Core**: Shared module containing Avro schemas and generated telemetry models.

## Technical Stack
- **Language**: Java 25
- **Framework**: Spring Boot 3.x / 4.x
- **Messaging**: Apache Kafka
- **Serialization**: Apache Avro, Protocol Buffers
- **Build System**: Hybrid (Maven in root, Gradle for microservices)
- **Database**: PostgreSQL (planned for Storage Service)

## Development Standards

### 1. Build & Dependency Management
- Each microservice is an independent Gradle project.
- **`core`** must be built and published to `mavenLocal()` (`./gradlew publishToMavenLocal`) before other services can use it.
- Root `pom.xml` exists but microservices use their own `gradlew`.

### 2. Schema Management
- Avro schemas are located in `core/src/main/resources/avro`.
- Run `./gradlew generateAvroJava` in `core` to update generated classes.
- Protocol Buffers are used in `uav-simulator`.

### 3. Testing
- Use JUnit 5 and Spring Boot Test.
- Mock Kafka where appropriate, or use `@EmbeddedKafka`.

## Code Style & Conventions
- Follow standard Spring Boot and Java idioms.
- Use MapStruct for object mapping between layers.
- Prefer explicit configuration over "magic" when dealing with Kafka and Avro.

## Context Efficiency
- When working on a specific service, focus on its `build.gradle` and `src` directory.
- Always check `core` for shared data structures.
