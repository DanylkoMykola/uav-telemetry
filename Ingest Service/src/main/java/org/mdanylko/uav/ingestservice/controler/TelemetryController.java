package org.mdanylko.uav.ingestservice.controler;

import org.mdanylko.uav.ingestservice.producer.TelemetryProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    private final TelemetryProducer producer;

    public TelemetryController(TelemetryProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> ingest(@RequestBody Map<String, Object> telemetry) {
        producer.sendTelemetry(telemetry);
        return ResponseEntity.ok("Telemetry ingested");
    }
}
