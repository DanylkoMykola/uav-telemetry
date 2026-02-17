package org.mdanylko.uav.processorservice.messaging;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.core.dto.TelemetryRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class KafkaTelemetryEventProducerImpl implements TelemetryEventProducer {

    private final IRecordSender<String, SpecificRecord> recordSender;

    @Value("${app.kafka.telemetry.topics.processed}")
    private String processedTelemetryTopicName;

    @Value("${app.kafka.telemetry.topics.alert}")
    private String alertTelemetryTopicName;

    public KafkaTelemetryEventProducerImpl(IRecordSender<String, SpecificRecord> recordSender) {
        this.recordSender = recordSender;
    }

    @Override
    public void publishProcessedEvent(UavTelemtryProcessedEvent event) {
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(processedTelemetryTopicName,
                0,
                event.getId(),
                event,
                Collections.EMPTY_LIST);

        recordSender.sendToKafka(producerRecord);
    }

    @Override
    public void publishAlertEvent(UavTelemtryAlertEvent event) {
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(alertTelemetryTopicName,
                0,
                event.getId(),
                event,
                Collections.EMPTY_LIST);

        recordSender.sendToKafka(producerRecord);
    }
}
