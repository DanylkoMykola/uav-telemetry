package org.mdanylko.uav.processorservice.processor;

import org.mdanylko.uav.avro.UavTelemetryEvent;

public interface TelemetryEventProcessor {
    void processTelemetryEvent(UavTelemetryEvent event);
}
