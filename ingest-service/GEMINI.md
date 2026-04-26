# Ingest Service

The entry point for telemetry data from UAVs.

## Responsibilities
- Provide REST and gRPC endpoints for telemetry ingestion.
- Validate incoming packets.
- Convert raw formats (Protobuf/JSON) to Avro.
- Publish to Kafka topic `telemetry.raw`.

## Dependencies
- Depends on `org.mdanylko.uav.core`. Ensure `core` is published to `mavenLocal()` before building.

## Configuration
- `src/main/resources/application.yaml` contains Kafka and Server settings.
- Kafka topic names should be consistent with `core` definitions.

## Key Components
- **Controllers**: Handle REST requests.
- **Service Layer**: Handles validation and mapping.
- **Kafka Producers**: Send events to Kafka.
- **MapStruct Mappers**: Convert DTOs to Avro models.
