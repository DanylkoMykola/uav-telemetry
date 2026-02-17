package org.mdanylko.uav.processorservice.messaging;

import org.mdanylko.uav.avro.UavTelemetryAlertEvent;
import org.mdanylko.uav.avro.UavTelemetryProcessedEvent;
import org.mdanylko.uav.core.dto.TelemetryRequestDto;

import java.util.Optional;

public interface TelemetryEventProducer {
    //TODO replace with model instead of dto
    void publishProcessedEvent(UavTelemetryProcessedEvent event);
    void publishAlertEvent(UavTelemetryAlertEvent event);
}
