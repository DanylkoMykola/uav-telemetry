package org.mdanylko.uav.processorservice.processor.impl;

import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.avro.UavTelemetryProcessedEvent;
import org.mdanylko.uav.processorservice.messaging.TelemetryEventProducer;
import org.mdanylko.uav.processorservice.processor.TelemetryAlertService;
import org.mdanylko.uav.processorservice.processor.TelemetryEventProcessor;
import org.mdanylko.uav.processorservice.processor.TelemetryValidator;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TelemetryEventProcessorImpl implements TelemetryEventProcessor {

    private final TelemetryValidator validator;
    private final TelemetryAlertService alertService;
    private final TelemetryEventProducer producer;

    public TelemetryEventProcessorImpl(TelemetryValidator validator, 
                                     TelemetryAlertService alertService, 
                                     TelemetryEventProducer producer) {
        this.validator = validator;
        this.alertService = alertService;
        this.producer = producer;
    }

    @Override
    public void processTelemetryEvent(UavTelemetryEvent event) {
        if (!validator.validate(event)) {
            // Log invalid event or handle accordingly
            return;
        }

        // Check for alerts
        alertService.checkForAlerts(event);

        // Transform to processed event
        UavTelemetryProcessedEvent processedEvent = UavTelemetryProcessedEvent.newBuilder()
                .setId(event.getId())
                .setTimestamp(event.getTimestamp())
                .setProcessingTime(Instant.now().toString())
                .setGps(event.getGps())
                .setAttitude(event.getAttitude())
                .setVelocity(event.getVelocity())
                .setBattery(event.getBattery())
                .setStatus(event.getStatus())
                .build();

        // Publish processed event
        producer.publishProcessedEvent(processedEvent);
    }
}
