package com.muates.kafkaadmin.client;

import com.muates.kafkaconfig.config.KafkaConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KafkaAdminClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaAdminClient.class);

    private final KafkaConfigData kafkaConfigData;
    private final AdminClient adminClient;

    public void createTopics() {
        List<NewTopic> newTopics = kafkaConfigData.getTopicNamesToCreate().stream()
                .map(topicName -> TopicBuilder
                        .name(topicName)
                        .partitions(kafkaConfigData.getNumOfPartitions())
                        .replicas(kafkaConfigData.getReplicationFactor())
                        .build())
                .collect(Collectors.toList());

        try {
            Set<String> existingTopics = adminClient.listTopics().names().get();

            var topicsToCreate = newTopics.stream()
                    .filter(topic -> !existingTopics.contains(topic.name()))
                    .collect(Collectors.toList());

            logTopics(topicsToCreate);

            adminClient.createTopics(topicsToCreate);
        } catch (Exception e) {
            throw new RuntimeException("Error checking existing topics", e);
        }
    }

    private void logTopics(List<NewTopic> topics) {
        topics.forEach(topic -> {
            LOGGER.info("Creating Kafka topic: {}", topic.name());
            LOGGER.info("Partitions: {}, Replicas: {}", topic.numPartitions(), topic.replicationFactor());
        });
    }
}
