package org.mdanylko.uav.simulator.simulator.sensor;

import org.mdanylko.uav.simulator.sensor.GPS;

public interface GpsDataSimulator {
    void generateGps(long intervalMs);
    GPS getGps();
    void setGps(GPS gps);
}

