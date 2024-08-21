package com.muates.postservice.init.impl;

import com.muates.kafkaadmin.client.KafkaAdminClient;
import com.muates.kafkaconfig.config.KafkaConfigData;
import com.muates.postservice.init.StreamInitializer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaStreamInitializer implements StreamInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamInitializer.class);

    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        LOGGER.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
