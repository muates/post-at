package com.muates.kafkaproducer.service.impl;

import com.muates.kafkamodel.avro.SocialInteractionNotificationAvro;
import com.muates.kafkaproducer.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;

@Service
@RequiredArgsConstructor
public class SocialInteractionNotificationProducer implements KafkaProducer<Long, SocialInteractionNotificationAvro> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialInteractionNotificationProducer.class);

    private final KafkaTemplate<Long, SocialInteractionNotificationAvro> kafkaTemplate;

    @Override
    public void send(String topicName, Long key, SocialInteractionNotificationAvro message) {
        LOGGER.info("Sending message= '{}' to topic= '{}'", message, topicName);
        ListenableFuture<SendResult<Long, SocialInteractionNotificationAvro>> result = kafkaTemplate.send(topicName, key, message);
        addCallback(topicName, message, result);
    }

    @PreDestroy
    private void close() {
        if (kafkaTemplate != null) {
            LOGGER.info("Closing kafka producer");
            kafkaTemplate.destroy();
        }
    }

    private static void addCallback(String topicName, SocialInteractionNotificationAvro value, ListenableFuture<SendResult<Long, SocialInteractionNotificationAvro>> result) {
        result.addCallback(new ListenableFutureCallback<SendResult<Long, SocialInteractionNotificationAvro>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.error("Error while sending message {} to topic {}", value.toString(), topicName, throwable);
            }

            @Override
            public void onSuccess(SendResult<Long, SocialInteractionNotificationAvro> sendResult) {
                RecordMetadata metadata =  sendResult.getRecordMetadata();
                LOGGER.debug("Received new metadata. Topic: {}; Partition: {}; Offset: {}; Timestamp {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            }
        });
    }
}
