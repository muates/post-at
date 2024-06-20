package com.muates.notificationservice.config;

import com.muates.notificationservice.config.data.KafkaConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicConfig.class);

    private final KafkaConfigData kafkaConfigData;

    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(Map.of(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigData.getBootstrapServers()));
    }

    @Bean
    public List<NewTopic> createTopics() {
        List<NewTopic> newTopics = kafkaConfigData.getTopicNamesToCreate().stream()
                .map(topicName -> TopicBuilder
                        .name(topicName)
                        .partitions(kafkaConfigData.getNumOfPartitions())
                        .replicas(kafkaConfigData.getReplicationFactor())
                        .build())
                .collect(Collectors.toList());

        logTopics(newTopics);

        return newTopics;
    }

    private void logTopics(List<NewTopic> topics) {
        topics.forEach(topic -> {
            LOGGER.info("Creating Kafka topic: {}", topic.name());
            LOGGER.info("Partitions: {}, Replicas: {}", topic.numPartitions(), topic.replicationFactor());
        });
    }
}
