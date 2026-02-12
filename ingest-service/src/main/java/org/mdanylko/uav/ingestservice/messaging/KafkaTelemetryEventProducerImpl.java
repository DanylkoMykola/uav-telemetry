package org.mdanylko.uav.ingestservice.messaging;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mdanylko.uav.core.dto.TelemetryRequestDto;
import org.mdanylko.uav.ingestservice.producer.IRecordSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KafkaTelemetryEventProducerImpl implements KafkaTelemetryEventProducer {

    private final IRecordSender<String, SpecificRecord> recordSender;

    @Value("${app.kafka.telemetry.topic.name}")
    private String topicName;

    public KafkaTelemetryEventProducerImpl(IRecordSender<String, SpecificRecord> recordSender) {
        this.recordSender = recordSender;
    }

    @Override
    public void publishEvent(TelemetryRequestDto telemetry) {
        //TODO add kye generation strategy
        // Convert TelemetryRequestDto to SpecificRecord (e.g., UavTelemetryEvent)
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(topicName,
                0,
                "",
                telemetry,
                Collections.EMPTY_LIST);

        recordSender.sendToKafka(producerRecord);
    }
}
