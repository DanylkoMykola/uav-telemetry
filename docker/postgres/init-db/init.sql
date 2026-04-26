CREATE TABLE IF NOT EXISTS telemetry (
    id BIGSERIAL PRIMARY KEY,
    uav_id VARCHAR(255) NOT NULL,
    event_timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
    processing_timestamp TIMESTAMP WITH TIME ZONE,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    altitude DOUBLE PRECISION,
    vx DOUBLE PRECISION,
    vy DOUBLE PRECISION,
    vz DOUBLE PRECISION,
    roll DOUBLE PRECISION,
    pitch DOUBLE PRECISION,
    yaw DOUBLE PRECISION,
    voltage DOUBLE PRECISION,
    current DOUBLE PRECISION,
    armed BOOLEAN,
    failsafe BOOLEAN
);

CREATE INDEX idx_telemetry_uav_id ON telemetry(uav_id);
CREATE INDEX idx_telemetry_event_timestamp ON telemetry(event_timestamp);
