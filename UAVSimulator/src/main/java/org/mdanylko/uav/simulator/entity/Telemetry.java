package org.mdanylko.uav.simulator.entity;

import java.time.LocalDateTime;

public class Telemetry {
    private LocalDateTime timestamp;
    private Attitude attitude;
    private Battery battery;
    private GPS gps;
    private Status status;
    private Velocity velocity;

    public Telemetry(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Attitude getAttitude() {
        return attitude;
    }

    public void setAttitude(Attitude attitude) {
        this.attitude = attitude;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public GPS getGps() {
        return gps;
    }

    public void setGps(GPS gps) {
        this.gps = gps;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Telemetry{");
        sb.append("  timestamp=").append(timestamp);
        sb.append(", attitude=").append(attitude);
        sb.append(", battery=").append(battery);
        sb.append(", gps=").append(gps);
        sb.append(", status=").append(status);
        sb.append(", velocity=").append(velocity);
        sb.append('}');
        return sb.toString();
    }
}
