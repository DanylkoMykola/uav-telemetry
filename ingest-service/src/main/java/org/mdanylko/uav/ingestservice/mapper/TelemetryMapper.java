package org.mdanylko.uav.ingestservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.core.dto.TelemetryRequestDto;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TelemetryMapper {

    UavTelemetryEvent toEvent(TelemetryRequestDto dto);
}
