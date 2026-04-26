package org.mdanylko.uav.processorservice.processor.impl;

import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.processorservice.processor.TelemetryValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelemetryValidatorImpl implements TelemetryValidator {

    private static final Logger logger = LoggerFactory.getLogger(TelemetryValidatorImpl.class);

    @Override
    public boolean validate(UavTelemetryEvent event) {
        if (event == null) {
            logger.warn("Received null telemetry event");
            return false;
        }
        if (!isValidUavId(event.getId())) {
            logger.warn("Telemetry event has invalid UAV ID: {}", event.getId());
            return false;
        }
        if (event.getGps() == null) {
            logger.warn("Telemetry event with ID {} has null GPS data", event.getId());
            return false;
        }
        if (!isValidLatitude(event.getGps().getLat()) || !isValidLongitude(event.getGps().getLon())) {
            logger.warn("Telemetry event with ID {} has invalid GPS coordinates: lat={}, lon={}", 
                        event.getId(), event.getGps().getLat(), event.getGps().getLon());
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
