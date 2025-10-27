package org.mdanylko.uav.ingestservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryProducerImpl implements TelemetryProducer {

    private static final Logger log = LoggerFactory.getLogger(TelemetryProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public TelemetryProducerImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendTelemetry(Object telemetry) {
        kafkaTemplate.send("uav.telemetry", telemetry);
        log.info("Sent telemetry to Kafka: {}", telemetry);
    }
}
