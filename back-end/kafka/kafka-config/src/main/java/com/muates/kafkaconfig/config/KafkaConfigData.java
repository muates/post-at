package com.muates.kafkaconfig.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    private String bootstrapServers = "localhost:19092,localhost:29092,localhost:39092";
    private String schemaRegistryUrlKey = "schema.registry.url";
    private String schemaRegistryUrl = "http://localhost:8081";
    private List<String> topicNamesToCreate = List.of("social-interaction-notification-topic");
    private Integer numOfPartitions = 3;
    private Short replicationFactor = 3;
}
