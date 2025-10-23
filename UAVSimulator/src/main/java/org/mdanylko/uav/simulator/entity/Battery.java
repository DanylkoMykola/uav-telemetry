package org.mdanylko.uav.simulator.entity;

public class Battery {
    private double voltage;
    private double current;

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "voltage=" + voltage +
                ", current=" + current +
                '}';
    }
}
