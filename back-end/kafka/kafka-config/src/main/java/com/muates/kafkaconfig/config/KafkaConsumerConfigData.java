package com.muates.kafkaconfig.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {
    private String keyDeserializer = "org.apache.kafka.common.serialization.LongDeserializer";
    private String valueDeserializer = "io.confluent.kafka.serializers.KafkaAvroDeserializer";
    private List<String> consumerGroupIdList = List.of("social-interaction-notification-topic-consumer");
    private String autoOffsetReset = "earliest";
    private String specificAvroReaderKey = "specific.avro.reader";
    private String specificAvroReader = "true";
    private Boolean batchListener = true;
    private Boolean autoStartup = false;
    private Integer concurrencyLevel = 3;
    private Integer sessionTimeoutMs = 10000;
    private Integer heartbeatIntervalMs = 3000;
    private Integer maxPollIntervalMs = 300000;
    private Integer maxPollRecords = 500;
    private Integer maxPartitionFetchBytesDefault = 1048576;
    private Integer maxPartitionFetchBytesBoostFactor = 1;
    private Long pollTimeoutMs = 150L;
}
