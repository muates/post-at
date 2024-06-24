package com.muates.kafkaconfig.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
public class KafkaProducerConfigData {
    private String keySerializerClass = "org.apache.kafka.common.serialization.LongSerializer";
    private String valueSerializerClass = "io.confluent.kafka.serializers.KafkaAvroSerializer";
    private String compressionType = "snappy";
    private String acks = "all";
    private Integer batchSize = 16384;
    private Integer batchSizeBoostFactor = 100;
    private Integer lingerMs = 5;
    private Integer requestTimeoutMs = 60000;
    private Integer retryCount = 5;
}
