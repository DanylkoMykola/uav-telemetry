package org.mdanylko.uav.simulator.entity;

public class Velocity {
    private double vx;
    private double vy;
    private double vz;

    public Velocity(double vx, double vy, double vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVz() {
        return vz;
    }

    public void setVz(double vz) {
        this.vz = vz;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Velocity{");
        sb.append("vx=").append(vx);
        sb.append(", vy=").append(vy);
        sb.append(", vz=").append(vz);
        sb.append('}');
        return sb.toString();
    }
}
