package org.mdanylko.uav.dashboardservice.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "telemetry")
public class TelemetryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uav_id", nullable = false)
    private String uavId;

    @Column(name = "event_timestamp", nullable = false)
    private Instant eventTimestamp;

    @Column(name = "processing_timestamp")
    private Instant processingTimestamp;

    @Embedded
    private GpsData gps;

    @Embedded
    private VelocityData velocity;

    @Embedded
    private AttitudeData attitude;

    @Embedded
    private BatteryData battery;

    @Embedded
    private StatusData status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUavId() { return uavId; }
    public void setUavId(String uavId) { this.uavId = uavId; }
    public Instant getEventTimestamp() { return eventTimestamp; }
    public void setEventTimestamp(Instant eventTimestamp) { this.eventTimestamp = eventTimestamp; }
    public Instant getProcessingTimestamp() { return processingTimestamp; }
    public void setProcessingTimestamp(Instant processingTimestamp) { this.processingTimestamp = processingTimestamp; }
    public GpsData getGps() { return gps; }
    public void setGps(GpsData gps) { this.gps = gps; }
    public VelocityData getVelocity() { return velocity; }
    public void setVelocity(VelocityData velocity) { this.velocity = velocity; }
    public AttitudeData getAttitude() { return attitude; }
    public void setAttitude(AttitudeData attitude) { this.attitude = attitude; }
    public BatteryData getBattery() { return battery; }
    public void setBattery(BatteryData battery) { this.battery = battery; }
    public StatusData getStatus() { return status; }
    public void setStatus(StatusData status) { this.status = status; }

    @Embeddable
    public static class GpsData {
        private double latitude;
        private double longitude;
        private double altitude;
        // Getters and Setters
        public double getLatitude() { return latitude; }
        public void setLatitude(double latitude) { this.latitude = latitude; }
        public double getLongitude() { return longitude; }
        public void setLongitude(double longitude) { this.longitude = longitude; }
        public double getAltitude() { return altitude; }
        public void setAltitude(double altitude) { this.altitude = altitude; }
    }

    @Embeddable
    public static class VelocityData {
        private double vx;
        private double vy;
        private double vz;
        // Getters and Setters
        public double getVx() { return vx; }
        public void setVx(double vx) { this.vx = vx; }
        public double getVy() { return vy; }
        public void setVy(double vy) { this.vy = vy; }
        public double getVz() { return vz; }
        public void setVz(double vz) { this.vz = vz; }
    }

    @Embeddable
    public static class AttitudeData {
        private double roll;
        private double pitch;
        private double yaw;
        // Getters and Setters
        public double getRoll() { return roll; }
        public void setRoll(double roll) { this.roll = roll; }
        public double getPitch() { return pitch; }
        public void setPitch(double pitch) { this.pitch = pitch; }
        public double getYaw() { return yaw; }
        public void setYaw(double yaw) { this.yaw = yaw; }
    }

    @Embeddable
    public static class BatteryData {
        private double voltage;
        private double current;
        // Getters and Setters
        public double getVoltage() { return voltage; }
        public void setVoltage(double voltage) { this.voltage = voltage; }
        public double getCurrent() { return current; }
        public void setCurrent(double current) { this.current = current; }
    }

    @Embeddable
    public static class StatusData {
        private boolean armed;
        private boolean failsafe;
        // Getters and Setters
        public boolean isArmed() { return armed; }
        public void setArmed(boolean armed) { this.armed = armed; }
        public boolean isFailsafe() { return failsafe; }
        public void setFailsafe(boolean failsafe) { this.failsafe = failsafe; }
    }
}
