package org.mdanylko.uav.processorservice.processor;

import org.mdanylko.uav.avro.UavTelemetryEvent;

public interface TelemetryValidator {
    boolean validate(UavTelemetryEvent event);
}
