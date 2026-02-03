package org.mdanylko.uav.simulator.service;

import org.mdanylko.uav.simulator.sensor.Telemetry;

public interface TelemetryService {
    void sendTelemetry(Telemetry telemetry);
}
