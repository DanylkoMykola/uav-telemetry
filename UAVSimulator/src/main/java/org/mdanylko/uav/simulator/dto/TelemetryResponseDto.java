package org.mdanylko.ingests.telemetry.entity;

public class CopyTelemetryResponseDto {
    private String timestamp;
    private boolean processed;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}

