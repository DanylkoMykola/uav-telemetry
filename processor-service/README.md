# UAV Telemetry Processor Service

## Overview

The **Processor Service** is responsible for consuming raw UAV telemetry events,
applying business logic, enriching the data, detecting anomalies,
and publishing processed events for downstream services.

It is part of a microservices-based telemetry pipeline.

---

## Architecture Position

UAV Simulator  
→ Ingest Service (validation)  
→ Kafka (`telemetry.raw`)  
→ **Processor Service**  
→ Kafka (`telemetry.processed`, `telemetry.alert`)  
→ Storage / Analytics Services

---

## Responsibilities

The Processor Service:

- Consumes raw telemetry events from Kafka
- Applies domain and business rules
- Calculates derived metrics (e.g. vertical speed, flight phase)
- Detects anomalies (e.g. altitude violation, battery issues)
- Publishes enriched or alert events
- Optionally persists processed state

---

## What It Does NOT Do

- Does not expose public telemetry ingestion endpoints
- Does not validate raw HTTP input (handled by Ingest Service)
- Does not blindly forward events without logic

---

## Example Processing Flow

1. Receive `TelemetryEvent`
2. Apply business rules
3. Enrich event with calculated fields
4. Publish:
    - `telemetry-processed`
    - `telemetry-alert` (if needed)

---

## Example Use Cases

- Detect abnormal altitude
- Detect unstable descent rate
- Classify flight phase (takeoff / cruise / landing)
- Detect battery anomaly
- Generate flight safety alerts

---

## Tech Stack

- Java 25
- Spring Boot
- Kafka
- Gradle

---

## Running Locally

1. Start Kafka
2. Start the Processor Service
3. Produce test messages to `telemetry-raw`
4. Observe output topics:
    - `telemetry-processed`
    - `telemetry-alert`

---

## Design Principles

- Event-driven architecture
- Clear separation of concerns
- Domain-focused business logic
- Stateless processing (preferred)
- Horizontal scalability

---
