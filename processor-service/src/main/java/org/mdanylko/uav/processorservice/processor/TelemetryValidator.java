package org.mdanylko.uav.processorservice.processor;

import org.mdanylko.uav.avro.UavTelemetryEvent;

import java.util.Optional;

public interface TelemetryValidator {
        boolean validateTelemetryEvent(Optional<UavTelemetryEvent> event);
}
