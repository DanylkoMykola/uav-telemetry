package org.mdanylko.uav.processorservice.processor.impl;

import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.processorservice.processor.TelemetryValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelemetryValidatorImpl implements TelemetryValidator {

    Logger logger = LoggerFactory.getLogger(TelemetryValidatorImpl.class);

    @Override
    public boolean validateTelemetryEvent(Optional<UavTelemetryEvent> event) {
        UavTelemetryEvent telemetryEvent = event.orElseThrow(() -> new RuntimeException("Received empty telemetry event"));
        if (!isValidUavId(telemetryEvent.getId())) {
            logger.warn("Telemetry event has invalid UAV ID: {}", telemetryEvent.getId());
            return false;
        }
        if (telemetryEvent.getGps() == null
                && !isValidLatitude(telemetryEvent.getGps().getLat())
                && !isValidLongitude(telemetryEvent.getGps().getLon())) {
            logger.warn("Telemetry event with ID {} has invalid  GPS data", telemetryEvent.getId());
            return false;
        }
        return true;
    }

    private boolean isValidUavId(String uavId) {
        return uavId != null && !uavId.trim().isEmpty();
    }

    private boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    private boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }
}
