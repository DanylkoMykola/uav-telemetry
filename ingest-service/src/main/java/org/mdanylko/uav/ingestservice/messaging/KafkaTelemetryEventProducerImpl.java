package org.mdanylko.uav.ingestservice.messaging;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.core.dto.TelemetryRequestDto;
import org.mdanylko.uav.ingestservice.mapper.TelemetryMapper;
import org.mdanylko.uav.ingestservice.producer.IRecordSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KafkaTelemetryEventProducerImpl implements KafkaTelemetryEventProducer {

    private final IRecordSender<String, SpecificRecord> recordSender;
    private final TelemetryMapper telemetryMapper;

    @Value("${app.kafka.telemetry.topic.name}")
    private String topicName;

    public KafkaTelemetryEventProducerImpl(IRecordSender<String, SpecificRecord> recordSender, TelemetryMapper telemetryMapper) {
        this.recordSender = recordSender;
        this.telemetryMapper = telemetryMapper;
    }

    @Override
    public void publishEvent(TelemetryRequestDto telemetryDto) {
        UavTelemetryEvent telemetryRecord = telemetryMapper.toEvent(telemetryDto);
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(topicName,
                0,
                telemetryRecord.getId(),
                telemetryRecord,
                Collections.EMPTY_LIST);

        recordSender.sendToKafka(producerRecord);
    }
}
