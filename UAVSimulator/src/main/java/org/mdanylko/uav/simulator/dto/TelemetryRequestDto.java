package org.mdanylko.uav.simulator.dto;

public class TelemetryRequestDto {
    private String timestamp;

    public static class GPS {
        private double lat;
        private double lon;
        private double alt;

        public double getLat() { return lat; }
        public void setLat(double lat) { this.lat = lat; }
        public double getLon() { return lon; }
        public void setLon(double lon) { this.lon = lon; }
        public double getAlt() { return alt; }
        public void setAlt(double alt) { this.alt = alt; }
    }

    public static class Velocity {
        private double vx;
        private double vy;
        private double vz;

        public double getVx() { return vx; }
        public void setVx(double vx) { this.vx = vx; }
        public double getVy() { return vy; }
        public void setVy(double vy) { this.vy = vy; }
        public double getVz() { return vz; }
        public void setVz(double vz) { this.vz = vz; }
    }

    public static class Attitude {
        private double roll;
        private double pitch;
        private double yaw;

        public double getRoll() { return roll; }
        public void setRoll(double roll) { this.roll = roll; }
        public double getPitch() { return pitch; }
        public void setPitch(double pitch) { this.pitch = pitch; }
        public double getYaw() { return yaw; }
        public void setYaw(double yaw) { this.yaw = yaw; }
    }

    public static class Battery {
        private double voltage;
        private double current;

        public double getVoltage() { return voltage; }
        public void setVoltage(double voltage) { this.voltage = voltage; }
        public double getCurrent() { return current; }
        public void setCurrent(double current) { this.current = current; }
    }

    public static class Status {
        private boolean armed;
        private boolean failsafe;

        public boolean isArmed() { return armed; }
        public void setArmed(boolean armed) { this.armed = armed; }
        public boolean isFailsafe() { return failsafe; }
        public void setFailsafe(boolean failsafe) { this.failsafe = failsafe; }
    }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}

