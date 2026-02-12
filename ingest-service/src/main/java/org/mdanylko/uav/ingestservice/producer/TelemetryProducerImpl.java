package org.mdanylko.uav.ingestservice.producer;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryProducerImpl implements IRecordSender<String, SpecificRecord> {

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    public TelemetryProducerImpl(KafkaTemplate<String, SpecificRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendToKafka(ProducerRecord<String, SpecificRecord> producerRecord) {
        sendRecord(kafkaTemplate, producerRecord);
    }
}
