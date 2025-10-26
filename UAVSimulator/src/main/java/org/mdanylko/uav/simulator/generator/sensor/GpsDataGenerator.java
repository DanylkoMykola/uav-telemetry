package org.mdanylko.uav.simulator.generator.sensor;

import org.mdanylko.uav.simulator.sensor.GPS;

public interface GpsDataGenerator {
    void generateGps(long intervalMs);
    GPS getGps();
    void setGps(GPS gps);
}

