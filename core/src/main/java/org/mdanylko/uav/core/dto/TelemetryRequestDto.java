package org.mdanylko.uav.core.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TelemetryRequestDto {
    @NotBlank(message = "UAV ID is required")
    private String id;

    @NotBlank(message = "Timestamp is required")
    private String timestamp;

    @NotNull(message = "GPS data is required")
    @Valid
    private GPS gps;

    @NotNull(message = "Velocity data is required")
    @Valid
    private Velocity velocity;

    @NotNull(message = "Attitude data is required")
    @Valid
    private Attitude attitude;

    @NotNull(message = "Battery data is required")
    @Valid
    private Battery battery;

    @NotNull(message = "Status data is required")
    @Valid
    private Status status;

    public GPS getGps() {
        return gps;
    }

    public void setGps(GPS gps) {
        this.gps = gps;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class GPS {
        @NotNull
        private Double lat;
        @NotNull
        private Double lon;
        @NotNull
        private Double alt;

        public Double getLat() { return lat; }
        public void setLat(Double lat) { this.lat = lat; }
        public Double getLon() { return lon; }
        public void setLon(Double lon) { this.lon = lon; }
        public Double getAlt() { return alt; }
        public void setAlt(Double alt) { this.alt = alt; }
    }

    public static class Velocity {
        @NotNull
        private Double vx;
        @NotNull
        private Double vy;
        @NotNull
        private Double vz;

        public Double getVx() { return vx; }
        public void setVx(Double vx) { this.vx = vx; }
        public Double getVy() { return vy; }
        public void setVy(Double vy) { this.vy = vy; }
        public Double getVz() { return vz; }
        public void setVz(Double vz) { this.vz = vz; }
    }

    public static class Attitude {
        @NotNull
        private Double roll;
        @NotNull
        private Double pitch;
        @NotNull
        private Double yaw;

        public Double getRoll() { return roll; }
        public void setRoll(Double roll) { this.roll = roll; }
        public Double getPitch() { return pitch; }
        public void setPitch(Double pitch) { this.pitch = pitch; }
        public Double getYaw() { return yaw; }
        public void setYaw(Double yaw) { this.yaw = yaw; }
    }

    public static class Battery {
        @NotNull
        private Double voltage;
        @NotNull
        private Double current;

        public Double getVoltage() { return voltage; }
        public void setVoltage(Double voltage) { this.voltage = voltage; }
        public Double getCurrent() { return current; }
        public void setCurrent(Double current) { this.current = current; }
    }

    public static class Status {
        @NotNull
        private Boolean armed;
        @NotNull
        private Boolean failsafe;

        public Boolean isArmed() { return armed; }
        public void setArmed(Boolean armed) { this.armed = armed; }
        public Boolean isFailsafe() { return failsafe; }
        public void setFailsafe(Boolean failsafe) { this.failsafe = failsafe; }
    }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
