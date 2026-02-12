package org.mdanylko.uav.core.model;

import java.time.Instant;
import java.util.Objects;

/**
 * Domain model representing UAV telemetry data.
 * This is the core business object used across the application layers.
 */
public class Telemetry {
    private Instant timestamp;
    private GPS gps;
    private Velocity velocity;
    private Attitude attitude;
    private Battery battery;
    private Status status;

    public Telemetry() {
    }

    public Telemetry(Instant timestamp, GPS gps, Velocity velocity, Attitude attitude, Battery battery, Status status) {
        this.timestamp = timestamp;
        this.gps = gps;
        this.velocity = velocity;
        this.attitude = attitude;
        this.battery = battery;
        this.status = status;
    }

    public static class GPS {
        private double lat;
        private double lon;
        private double alt;

        public GPS() {
        }

        public GPS(double lat, double lon, double alt) {
            this.lat = lat;
            this.lon = lon;
            this.alt = alt;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getAlt() {
            return alt;
        }

        public void setAlt(double alt) {
            this.alt = alt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GPS gps = (GPS) o;
            return Double.compare(gps.lat, lat) == 0 &&
                   Double.compare(gps.lon, lon) == 0 &&
                   Double.compare(gps.alt, alt) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(lat, lon, alt);
        }

        @Override
        public String toString() {
            return "GPS{" +
                   "lat=" + lat +
                   ", lon=" + lon +
                   ", alt=" + alt +
                   '}';
        }
    }

    public static class Velocity {
        private double vx;
        private double vy;
        private double vz;

        public Velocity() {
        }

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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Velocity velocity = (Velocity) o;
            return Double.compare(velocity.vx, vx) == 0 &&
                   Double.compare(velocity.vy, vy) == 0 &&
                   Double.compare(velocity.vz, vz) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vx, vy, vz);
        }

        @Override
        public String toString() {
            return "Velocity{" +
                   "vx=" + vx +
                   ", vy=" + vy +
                   ", vz=" + vz +
                   '}';
        }
    }

    public static class Attitude {
        private double roll;
        private double pitch;
        private double yaw;

        public Attitude() {
        }

        public Attitude(double roll, double pitch, double yaw) {
            this.roll = roll;
            this.pitch = pitch;
            this.yaw = yaw;
        }

        public double getRoll() {
            return roll;
        }

        public void setRoll(double roll) {
            this.roll = roll;
        }

        public double getPitch() {
            return pitch;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public double getYaw() {
            return yaw;
        }

        public void setYaw(double yaw) {
            this.yaw = yaw;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Attitude attitude = (Attitude) o;
            return Double.compare(attitude.roll, roll) == 0 &&
                   Double.compare(attitude.pitch, pitch) == 0 &&
                   Double.compare(attitude.yaw, yaw) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(roll, pitch, yaw);
        }

        @Override
        public String toString() {
            return "Attitude{" +
                   "roll=" + roll +
                   ", pitch=" + pitch +
                   ", yaw=" + yaw +
                   '}';
        }
    }

    public static class Battery {
        private double voltage;
        private double current;

        public Battery() {
        }

        public Battery(double voltage, double current) {
            this.voltage = voltage;
            this.current = current;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Battery battery = (Battery) o;
            return Double.compare(battery.voltage, voltage) == 0 &&
                   Double.compare(battery.current, current) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(voltage, current);
        }

        @Override
        public String toString() {
            return "Battery{" +
                   "voltage=" + voltage +
                   ", current=" + current +
                   '}';
        }
    }

    public static class Status {
        private boolean armed;
        private boolean failsafe;

        public Status() {
        }

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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Status status = (Status) o;
            return armed == status.armed && failsafe == status.failsafe;
        }

        @Override
        public int hashCode() {
            return Objects.hash(armed, failsafe);
        }

        @Override
        public String toString() {
            return "Status{" +
                   "armed=" + armed +
                   ", failsafe=" + failsafe +
                   '}';
        }
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telemetry telemetry = (Telemetry) o;
        return Objects.equals(timestamp, telemetry.timestamp) &&
               Objects.equals(gps, telemetry.gps) &&
               Objects.equals(velocity, telemetry.velocity) &&
               Objects.equals(attitude, telemetry.attitude) &&
               Objects.equals(battery, telemetry.battery) &&
               Objects.equals(status, telemetry.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, gps, velocity, attitude, battery, status);
    }

    @Override
    public String toString() {
        return "Telemetry{" +
               "timestamp=" + timestamp +
               ", gps=" + gps +
               ", velocity=" + velocity +
               ", attitude=" + attitude +
               ", battery=" + battery +
               ", status=" + status +
               '}';
    }
}

