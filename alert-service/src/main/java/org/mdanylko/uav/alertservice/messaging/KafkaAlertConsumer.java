package org.mdanylko.uav.alertservice.messaging;

import org.mdanylko.uav.avro.UavTelemetryAlertEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaAlertConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAlertConsumer.class);

    @KafkaListener(topics = "${app.kafka.topics.alert}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(UavTelemetryAlertEvent alert) {
        logger.error("!!! ALERT RECEIVED !!! Drone ID: {}, Type: {}, Message: {}", 
                     alert.getId(), alert.getAlertType(), alert.getAlertMessage());
        
        // This is where you would integrate with an external notification system
        // like Email, SMS, or a WebSocket push to the dashboard.
    }
}
