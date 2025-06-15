package com.kafka.producer.config;

import com.kafka.producer.constants.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    /**
     *  If Topic is already exists than it won't create or modify the partition or replicas
     *  will be using existing topic specifications.
     *  To change topic's specification need to do manually
     */
    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(AppConstant.LOCATION_TRACKER_KAFKA_TOPIC)
                .partitions(2)
                .replicas(1)
                .build();
    }

}
