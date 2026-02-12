package org.mdanylko.uav.ingestservice.controller;

import org.mdanylko.uav.core.dto.TelemetryRequestDto;
import org.mdanylko.uav.core.dto.TelemetryResponseDto;
import org.mdanylko.uav.ingestservice.messaging.KafkaTelemetryEventProducer;
import org.mdanylko.uav.ingestservice.producer.IRecordSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    private final KafkaTelemetryEventProducer producer;

    public TelemetryController(KafkaTelemetryEventProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> ingest(@RequestBody TelemetryRequestDto telemetry) {
        producer.publishEvent(telemetry);
        return ResponseEntity.ok("Telemetry ingested");
    }
}
