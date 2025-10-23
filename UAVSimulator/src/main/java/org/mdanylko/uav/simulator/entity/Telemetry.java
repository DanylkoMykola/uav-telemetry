package org.mdanylko.uav.simulator.entity;

import java.time.LocalDateTime;

public class Telemetry {
    private LocalDateTime timestamp;
    private Attitude attitude;
    private Battery battery;
    private GPS gps;
    private Status status;
    private Velocity velocity;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
