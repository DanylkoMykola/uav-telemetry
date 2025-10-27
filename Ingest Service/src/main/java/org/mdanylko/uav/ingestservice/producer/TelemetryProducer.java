package org.mdanylko.uav.ingestservice.producer;

public interface TelemetryProducer {
    void sendTelemetry(Object telemetry);
}
