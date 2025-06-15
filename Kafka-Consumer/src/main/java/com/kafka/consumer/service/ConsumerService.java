package com.kafka.consumer.service;

import com.kafka.consumer.constants.AppConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    @KafkaListener(topics = AppConstant.LOCATION_TRACKER_KAFKA_TOPIC, groupId = AppConstant.GROUP_ID)
    public void updatedLocation(String value) {
        log.info("Got : {}", value);
    }

}
