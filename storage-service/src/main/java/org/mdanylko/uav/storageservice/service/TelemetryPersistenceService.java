package org.mdanylko.uav.storageservice.service;

import org.mdanylko.uav.avro.UavTelemetryProcessedEvent;
import org.mdanylko.uav.storageservice.domain.TelemetryEntity;
import org.mdanylko.uav.storageservice.repository.TelemetryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class TelemetryPersistenceService {

    private final TelemetryRepository repository;

    public TelemetryPersistenceService(TelemetryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(UavTelemetryProcessedEvent event) {
        TelemetryEntity entity = new TelemetryEntity();
        entity.setUavId(event.getId());
        entity.setEventTimestamp(Instant.parse(event.getTimestamp()));
        entity.setProcessingTimestamp(Instant.parse(event.getProcessingTime()));

        TelemetryEntity.GpsData gps = new TelemetryEntity.GpsData();
        gps.setLatitude(event.getGps().getLat());
        gps.setLongitude(event.getGps().getLon());
        gps.setAltitude(event.getGps().getAlt());
        entity.setGps(gps);

        TelemetryEntity.VelocityData velocity = new TelemetryEntity.VelocityData();
        velocity.setVx(event.getVelocity().getVx());
        velocity.setVy(event.getVelocity().getVy());
        velocity.setVz(event.getVelocity().getVz());
        entity.setVelocity(velocity);

        TelemetryEntity.AttitudeData attitude = new TelemetryEntity.AttitudeData();
        attitude.setRoll(event.getAttitude().getRoll());
        attitude.setPitch(event.getAttitude().getPitch());
        attitude.setYaw(event.getAttitude().getYaw());
        entity.setAttitude(attitude);

        TelemetryEntity.BatteryData battery = new TelemetryEntity.BatteryData();
        battery.setVoltage(event.getBattery().getVoltage());
        battery.setCurrent(event.getBattery().getCurrent());
        entity.setBattery(battery);

        TelemetryEntity.StatusData status = new TelemetryEntity.StatusData();
        status.setArmed(event.getStatus().getArmed());
        status.setFailsafe(event.getStatus().getFailsafe());
        entity.setStatus(status);

        repository.save(entity);
    }
}
