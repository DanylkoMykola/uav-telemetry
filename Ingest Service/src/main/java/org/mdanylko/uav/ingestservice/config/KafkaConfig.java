package org.mdanylko.uav.ingestservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.mdanylko.uav.ingestservice.utils.KafkaTopics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic telemetryTopic() {
        return TopicBuilder.name(KafkaTopics.TELEMETRY_TOPIC)
                .partitions(3)
                .replicas(3)
                .build();
    }
}
