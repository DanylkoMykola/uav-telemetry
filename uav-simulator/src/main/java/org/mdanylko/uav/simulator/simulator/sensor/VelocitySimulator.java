package org.mdanylko.uav.simulator.simulator.sensor;

import org.mdanylko.uav.simulator.sensor.GPS;
import org.mdanylko.uav.simulator.sensor.Velocity;

public interface VelocitySimulator {
    void simulateVel(long intervalMs, GPS gps);
    Velocity getVelocity();
    void setVelocity(Velocity velocity);
}
