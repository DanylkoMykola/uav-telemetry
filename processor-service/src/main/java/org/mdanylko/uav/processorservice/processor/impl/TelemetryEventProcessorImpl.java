package org.mdanylko.uav.processorservice.processor.impl;

import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.processorservice.processor.TelemetryEventProcessor;
import org.springframework.stereotype.Component;

@Component
public class TelemetryEventProcessorImpl implements TelemetryEventProcessor {
    @Override
    public void processTelemetryEvent(UavTelemetryEvent event) {

    }
}
