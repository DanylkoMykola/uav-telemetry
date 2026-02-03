package org.mdanylko.uav.simulator.simulator.sensor;

import org.mdanylko.uav.simulator.sensor.Attitude;

public interface AttitudeDataSimulator {
    void simulateAtt(long intervalMs);
    Attitude getAttitude();
    void setAttitude(Attitude attitude);
}
