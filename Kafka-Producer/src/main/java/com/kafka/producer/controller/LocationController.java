package com.kafka.producer.controller;

import com.kafka.producer.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final KafkaService locationService;

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation() {
        locationService.updateLocation();
        return ResponseEntity.ok().build();
    }

}
