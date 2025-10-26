package org.mdanylko.uav.simulator.simulator.sensor;

import org.mdanylko.uav.simulator.sensor.Battery;

public interface BatterySimulator {
    void simulateBattery();
    Battery getBattery();
    void setBattery(Battery battery);
}
