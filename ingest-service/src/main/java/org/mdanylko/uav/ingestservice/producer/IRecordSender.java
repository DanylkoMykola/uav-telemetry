package org.mdanylko.uav.ingestservice.producer;


import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@FunctionalInterface
public interface IRecordSender<K, V> {
    Logger log = LoggerFactory.getLogger(IRecordSender.class);

    void sendToKafka(ProducerRecord<K, V> producerRecord);

    default void sendRecord(KafkaTemplate<K, V> kafkaTemplate, ProducerRecord<K, V> producerRecord) {
        CompletableFuture<SendResult<K, V>> future = kafkaTemplate.send(producerRecord);
        try {
            future.get();
        } catch (ExecutionException e) {
            log.warn("Error when sending message {} into {} : ", producerRecord.value(), producerRecord.topic(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Error when sending message {} into {} : ", producerRecord.value(), producerRecord.topic(), e);
        }
        log.info("Successfully sent message : {} into {}", producerRecord.value(), producerRecord.topic());
    }
}
