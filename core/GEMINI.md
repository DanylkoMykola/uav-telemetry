# Core Module: Shared Schemas & Models

This module contains the shared data models used across the UAV Telemetry system.

## Avro Schemas
Located in `src/main/resources/avro/`:
- `uav-telemetry-event.avsc`: Raw telemetry data.
- `uav-telemetry-processed-event.avsc`: Processed/filtered telemetry.
- `uav-telemetry-alert-event.avsc`: Alert events triggered by anomalies.

## Build & Distribution
- **Generate Java from Avro**: `./gradlew generateAvroJava`
- **Publish to Maven Local**: `./gradlew publishToMavenLocal` (Mandatory for other modules to see changes)

## Conventions
- Do not manually edit files in `build/generated-sources`.
- Update the `.avsc` file and regenerate if you need to change a data structure.
- Ensure all fields in schemas have proper documentation strings.
