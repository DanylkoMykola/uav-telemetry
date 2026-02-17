package org.mdanylko.uav.processorservice.messaging;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mdanylko.uav.avro.UavTelemetryAlertEvent;
import org.mdanylko.uav.avro.UavTelemetryProcessedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KafkaTelemetryEventProducerImpl implements TelemetryEventProducer, IRecordSender<String, SpecificRecord> {

    private KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    @Value("${app.kafka.telemetry.topics.processed}")
    private String processedTelemetryTopicName;

    @Value("${app.kafka.telemetry.topics.alert}")
    private String alertTelemetryTopicName;

    public KafkaTelemetryEventProducerImpl(KafkaTemplate<String, SpecificRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishProcessedEvent(UavTelemetryProcessedEvent event) {
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(processedTelemetryTopicName,
                0,
                event.getId(),
                event,
                Collections.EMPTY_LIST);

        sendToKafka(producerRecord);
    }

    @Override
    public void publishAlertEvent(UavTelemetryAlertEvent event) {
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(alertTelemetryTopicName,
                0,
                event.getId(),
                event,
                Collections.EMPTY_LIST);

        sendToKafka(producerRecord);
    }

    @Override
    public void sendToKafka(ProducerRecord producerRecord) {
        sendRecord(kafkaTemplate, producerRecord);
    }
}
