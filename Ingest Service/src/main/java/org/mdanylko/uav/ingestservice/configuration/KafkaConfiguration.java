package org.mdanylko.uav.ingestservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic telemetryTopic() {
        return TopicBuilder.name("uav.telemetry")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
