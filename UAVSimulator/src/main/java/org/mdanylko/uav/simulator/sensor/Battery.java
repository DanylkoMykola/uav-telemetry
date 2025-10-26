package org.mdanylko.uav.simulator.sensor;

public class Battery implements Cloneable {
    private double voltage;
    private double current;


    public Battery(double current, double voltage) {
        this.current = current;
        this.voltage = voltage;
    }

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

    @Override
    protected Battery clone() {
        try {
            return (Battery) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
