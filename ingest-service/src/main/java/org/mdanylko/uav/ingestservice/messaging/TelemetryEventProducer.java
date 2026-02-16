package org.mdanylko.uav.ingestservice.messaging;

import org.mdanylko.uav.core.dto.TelemetryRequestDto;

public interface TelemetryEventProducer {
    //TODO replace with model instead of dto
    void publishEvent(TelemetryRequestDto telemetry);
}
