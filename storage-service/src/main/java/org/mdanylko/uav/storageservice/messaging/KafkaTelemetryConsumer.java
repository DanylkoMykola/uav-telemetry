package org.mdanylko.uav.storageservice.messaging;

import org.mdanylko.uav.avro.UavTelemetryProcessedEvent;
import org.mdanylko.uav.storageservice.service.TelemetryPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTelemetryConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTelemetryConsumer.class);
    private final TelemetryPersistenceService persistenceService;

    public KafkaTelemetryConsumer(TelemetryPersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @KafkaListener(topics = "${app.kafka.topics.processed}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(UavTelemetryProcessedEvent event) {
        logger.info("Received processed telemetry for storage: {}", event.getId());
        persistenceService.save(event);
    }
}
