package org.mdanylko.uav.storageservice.repository;

import org.mdanylko.uav.storageservice.domain.TelemetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryEntity, Long> {
    List<TelemetryEntity> findByUavIdOrderByEventTimestampDesc(String uavId);
}
