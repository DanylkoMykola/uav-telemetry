package org.mdanylko.uav.simulator.entity;

public class Status {
    private boolean armed;
    private boolean failsafe;

    public Status(boolean armed, boolean failsafe) {
        this.armed = armed;
        this.failsafe = failsafe;
    }

    public boolean isArmed() {
        return armed;
    }

    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    public boolean isFailsafe() {
        return failsafe;
    }

    public void setFailsafe(boolean failsafe) {
        this.failsafe = failsafe;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Status{");
        sb.append("armed=").append(armed);
        sb.append(", failsafe=").append(failsafe);
        sb.append('}');
        return sb.toString();
    }
}
