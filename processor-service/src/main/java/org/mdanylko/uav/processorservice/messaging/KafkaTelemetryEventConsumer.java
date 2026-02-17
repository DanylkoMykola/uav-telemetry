package org.mdanylko.uav.processorservice.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.mdanylko.uav.avro.UavTelemetryEvent;
import org.mdanylko.uav.processorservice.processor.TelemetryEventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTelemetryEventConsumer {

    private final Logger log = LoggerFactory.getLogger(KafkaTelemetryEventConsumer.class);
    private final TelemetryEventProcessor telemetryEventProcessor;

    public KafkaTelemetryEventConsumer(TelemetryEventProcessor telemetryEventProcessor) {
        this.telemetryEventProcessor = telemetryEventProcessor;
    }

    @KafkaListener(topics = "${app.kafka.telemetry.topics.raw}",
            groupId = "processor-service-group",
            containerFactory = "kafkaListenerContainerFactory",
            clientIdPrefix = "processor-service-consumer",
            autoStartup = "true"
    )
    public void consumeTelemetryEvent(ConsumerRecord<String, UavTelemetryEvent> consumerRecord) {
        log.info("Received telemetry event: {}", consumerRecord);
        telemetryEventProcessor.processTelemetryEvent(consumerRecord.value());
        // Here you would add logic to process the telemetry event, e.g., parse the message and update the database
    }
}
