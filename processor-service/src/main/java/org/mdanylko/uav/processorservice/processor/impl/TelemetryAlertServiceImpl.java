package org.mdanylko.uav.processorservice.processor.impl;

import org.mdanylko.uav.avro.UavTelemetryAlertEvent;
import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.processorservice.messaging.TelemetryEventProducer;
import org.mdanylko.uav.processorservice.processor.TelemetryAlertService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TelemetryAlertServiceImpl implements TelemetryAlertService {

    private final TelemetryEventProducer producer;

    public TelemetryAlertServiceImpl(TelemetryEventProducer producer) {
        this.producer = producer;
    }

    @Override
    public void checkForAlerts(UavTelemetryEvent event) {
        if (event.getBattery().getVoltage() < 20.0) {
            sendAlert(event, "LOW_BATTERY", "Battery voltage is low: " + event.getBattery().getVoltage());
        }
        if (event.getStatus().getFailsafe()) {
            sendAlert(event, "FAILSAFE_ACTIVE", "Drone has entered failsafe mode");
        }
    }

    @Override
    public void sendAlert(String alertMessage) {
        // Generic alert without event context - could be used for system alerts
    }

    private void sendAlert(UavTelemetryEvent event, String type, String message) {
        UavTelemetryAlertEvent alert = UavTelemetryAlertEvent.newBuilder()
                .setId(event.getId())
                .setTimestamp(event.getTimestamp())
                .setProcessingTime(Instant.now().toString())
                .setAlertType(type)
                .setAlertMessage(message)
                .setGps(event.getGps())
                .setAttitude(event.getAttitude())
                .setVelocity(event.getVelocity())
                .setBattery(event.getBattery())
                .setStatus(event.getStatus())
                .build();
        
        producer.publishAlertEvent(alert);
    }
}
