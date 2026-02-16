package org.mdanylko.uav.ingestservice.controller;

import org.mdanylko.uav.core.dto.TelemetryRequestDto;
import org.mdanylko.uav.ingestservice.messaging.TelemetryEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    private final TelemetryEventProducer producer;

    public TelemetryController(TelemetryEventProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> ingest(@RequestBody TelemetryRequestDto telemetry) {
        producer.publishEvent(telemetry);
        return ResponseEntity.ok("Telemetry ingested");
    }
}
