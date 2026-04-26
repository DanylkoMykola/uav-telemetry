package org.mdanylko.uav.dashboardservice.repository;

import org.mdanylko.uav.dashboardservice.domain.TelemetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryEntity, Long> {
    List<TelemetryEntity> findByUavIdOrderByEventTimestampDesc(String uavId);
}
