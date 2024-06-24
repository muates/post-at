package com.muates.notificationservice.consumer.impl;

import com.muates.kafkaconfig.config.KafkaConfigData;
import com.muates.kafkaconfig.config.KafkaConsumerConfigData;
import com.muates.kafkamodel.avro.SocialInteractionNotificationAvro;
import com.muates.notificationservice.consumer.KafkaConsumer;
import com.muates.notificationservice.converter.AvroToJsonConverter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsOptions;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SocialInteractionNotificationConsumer implements KafkaConsumer<Long, SocialInteractionNotificationAvro> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialInteractionNotificationConsumer.class);

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final KafkaConfigData kafkaConfigData;
    private final KafkaConsumerConfigData kafkaConsumerConfigData;
    private final AdminClient adminClient;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AvroToJsonConverter avroToJsonConverter;

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event) {
        checkTopic(adminClient, kafkaConfigData.getTopicNamesToCreate().get(0));
        LOGGER.info("Topic with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
        Objects.requireNonNull(kafkaListenerEndpointRegistry
                .getListenerContainer(kafkaConsumerConfigData.getConsumerGroupIdList().get(0))).start();
    }

    @Override
    @KafkaListener(id = "social-interaction-notification-topic-consumer", topics = "social-interaction-notification-topic")
    public void receive(
            @Payload List<SocialInteractionNotificationAvro> messages,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Long> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets
    ) {
        LOGGER.info("Received Kafka Message - Messages: {}, Keys: {}, Partitions: {}, Offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        IntStream.range(0, messages.size()).forEach(i -> {
            SocialInteractionNotificationAvro notification = messages.get(i);
            String userId = keys.get(i).toString();

            Message<?> jsonMessage = avroToJsonConverter.toMessage(notification, null);

            assert jsonMessage != null;
            simpMessagingTemplate.convertAndSendToUser(userId, "/topic/notification/social-interaction", jsonMessage.getPayload());
        });
    }

    private void checkTopic(AdminClient adminClient, String topicName) {
        DescribeTopicsResult topicsResult = adminClient.describeTopics(Collections.singletonList(topicName),
                new DescribeTopicsOptions().timeoutMs(5000));

        try {
            TopicDescription topicDescription = topicsResult.values().get(topicName).get();
            LOGGER.info("Topic '{}' exists! Description: {}", topicName, topicDescription);
        } catch (Exception e) {
            LOGGER.warn("Topic '{}' does not exist!", topicName);
        }
    }
}
