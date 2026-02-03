package org.mdanylko.uav.simulator.simulator.sensor;

import org.mdanylko.uav.simulator.sensor.Attitude;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AttitudeDataSimulatorImpl implements AttitudeDataSimulator {

    private Attitude attitude;
    private final Random rnd = new Random();

    public AttitudeDataSimulatorImpl() {
        this.attitude = new Attitude(0.0, 0.0, 0.0);
    }

    public AttitudeDataSimulatorImpl(Attitude attitude) {
        this.attitude = attitude;
    }

    @Override
    public void simulateAtt(long intervalMs) {
        // simple dynamics: small changes per tick
        attitude.setRoll(attitude.getRoll() + (rnd.nextDouble() - 0.5) * 2.0);  // ±1 degree per tick
        attitude.setPitch(attitude.getPitch() + (rnd.nextDouble() - 0.5) * 1.5);
        attitude.setYaw(attitude.getYaw() + (rnd.nextDouble() - 0.5) * 3.0);

        // clamp roll/pitch to realistic UAV limits
        attitude.setRoll(Math.max(-30, Math.min(30, attitude.getRoll())));
        attitude.setPitch(Math.max(-20, Math.min(20, attitude.getPitch())));
        attitude.setYaw((attitude.getYaw() + 360) % 360); // normalize 0-360
    }

    @Override
    public Attitude getAttitude() {
        return attitude;
    }

    @Override
    public void setAttitude(Attitude attitude) {
        this.attitude = attitude;
    }
}
