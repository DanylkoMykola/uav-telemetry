        ┌────────────────────┐
        │  UAV (Simulator)   │
        │ Generates telemetry│
        └─────────┬──────────┘
                  │  REST
                  ▼
        ┌────────────────────┐
        │  Ingest Service    │
        │Receives & validates│
        │ telemetry packets  │
        └─────────┬──────────┘
                  │ Kafka (topic: telemetry.raw)
                  ▼
        ┌────────────────────┐
        │ Processor Service  │
        │ Filters, aggregates│
        │ detects anomalies  │
        └─────────┬──────────┘
                  │ Kafka (topic: telemetry.processed)
                  ▼
        ┌────────────────────┐
        │ Storage Service    │
        │ Stores telemetry   │
        │ in PostgreSQL      │
        └─────────┬──────────┘
                  │
                  ▼
        ┌────────────────────┐
        │ Dashboard Service  │
        │ REST API for data  │
        │ visualization (UI) │
        └─────────┬──────────┘
                  │
                  ▼
        ┌────────────────────┐
        │ Alert Service      │
        │ Listens to Kafka   │
        │ Sends alerts if    │
        │ abnormal data seen │
        └────────────────────┘
