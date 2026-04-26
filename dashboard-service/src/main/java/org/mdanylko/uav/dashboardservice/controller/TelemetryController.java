package org.mdanylko.uav.dashboardservice.controller;

import org.mdanylko.uav.dashboardservice.domain.TelemetryEntity;
import org.mdanylko.uav.dashboardservice.repository.TelemetryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    private final TelemetryRepository repository;

    public TelemetryController(TelemetryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{uavId}")
    public ResponseEntity<List<TelemetryEntity>> getHistory(@PathVariable String uavId) {
        return ResponseEntity.ok(repository.findByUavIdOrderByEventTimestampDesc(uavId));
    }

    @GetMapping("/replay/{uavId}")
    public ResponseEntity<List<TelemetryEntity>> getReplayData(@PathVariable String uavId) {
        // For replay, we might want ascending order
        List<TelemetryEntity> data = repository.findByUavIdOrderByEventTimestampDesc(uavId);
        return ResponseEntity.ok(data.stream().sorted((a, b) -> a.getEventTimestamp().compareTo(b.getEventTimestamp())).toList());
    }
}
