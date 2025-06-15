package com.kafka.producer.service;

import com.kafka.producer.constants.AppConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final SecureRandom random = new SecureRandom();

    @Async
    public void updateLocation() {
        for (int i = 0; i < 100000; i++) {
            String location = random.doubles().findAny().getAsDouble() + ", " + random.doubles().findAny().getAsDouble();
            kafkaTemplate.send(AppConstant.LOCATION_TRACKER_KAFKA_TOPIC, location);
            log.info("Message produced : {}", location);
        }
    }
}
