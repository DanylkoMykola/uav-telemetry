package org.mdanylko.uav.processorservice.processor;

import org.mdanylko.uav.avro.UavTelemetryEvent;

public interface TelemetryAlertService {
    void checkForAlerts(UavTelemetryEvent event);
    void sendAlert(String alertMessage);
}
